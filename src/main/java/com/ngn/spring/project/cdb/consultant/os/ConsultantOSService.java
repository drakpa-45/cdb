package com.ngn.spring.project.cdb.consultant.os;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.common.dto.ServiceFeeDTO;
import com.ngn.spring.project.cdb.consultant.model.*;
import com.ngn.spring.project.cdb.consultant.registration.ConsultantNRDao;
import com.ngn.spring.project.cdb.consultant.registration.ConsultantNRService;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTO;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTOFetch;
import com.ngn.spring.project.cdb.consultant.renewal.ConsultantRCDao;
import com.ngn.spring.project.cdb.consultant.renewal.ConsultantRCService;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.cdb.contractor.renewal.LateFeeDTO;
import com.ngn.spring.project.cdb.contractor.renewal.RenewalServiceType;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.DropdownDTO;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Service
public class ConsultantOSService extends BaseService {
    private String UPLOAD_LOC;

    @Autowired
    private ConsultantRCDao consultantRCDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ConsultantNRDao consultantDao;

    @Autowired
    ConsultantOSDao consultantOSDao;

    @Autowired
    private ConsultantNRService consultantNRService;
    @Autowired
    private ConsultantRCService consultantRCService;


    public DropdownDTO getConsultantStatus(String cdbNo){
        return consultantRCDao.getConsultantStatus(cdbNo);
    }

    /**
     * The main save method for consultant which calls specific save methods
     * @param consultantDTO   --  ConsultantDTO
     * @param loggedInUser    --  Current logged in user
     * @param request
     * @return ResponseMessage  -- response message
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public ResponseMessage save(ConsultantDTO consultantDTO, RenewalServiceType renewalServiceType, LoggedInUser loggedInUser, HttpServletRequest request) throws Exception {
        if(renewalServiceType == null){
            return responseMessage;
        }
        String cdbNo = loggedInUser.getCdbNo().split("999")[1];
        List<String> appliedServicesList = new ArrayList<>();
        responseMessage = check4Renewal(cdbNo);
        /*if(responseMessage.getStatus() == UNSUCCESSFUL_STATUS){
            return responseMessage;
        }*/
        ConsultantFinal consultantFinal = (ConsultantFinal)responseMessage.getDto();

        Consultant consultant = new Consultant();
        consultant.setpGewogId(consultantFinal.getpGewogId());
        consultant.setpVillageId(consultantFinal.getpVillageId());
        BeanUtils.copyProperties(consultantFinal,consultant);

        String consultantId = commonService.getRandomGeneratedId();
        String appliedService;
        consultant.setConsultantId(consultantId);
        //insert undertaking letter
        if(consultantDTO.getcAttachments() != null && !consultantDTO.getcAttachments().isEmpty())
            updateIncorporation(consultantDTO.getcAttachments(), loggedInUser, consultantId);

        if(consultantDTO.getOwnerAttachments() != null && !consultantDTO.getOwnerAttachments().isEmpty())
            updateIncorporation(consultantDTO.getOwnerAttachments(), loggedInUser, consultantId);

        if(consultantDTO.getCategoryAttachments() != null && !consultantDTO.getCategoryAttachments().isEmpty())
            updateIncorporation(consultantDTO.getCategoryAttachments(), loggedInUser, consultantId);

        //region incorporation (Name are also allowed to change)
        if(renewalServiceType.getIncorporation() != null){
            String ownershipTypeId = consultantDTO.getConsultant().getOwnershipTypeId();
            //consultantRCService.updateIncorporation(consultantDTO.getcAttachments(), loggedInUser, consultant.getConsultantId());
            consultant.setOwnershipTypeId(ownershipTypeId);
            consultant.setFirmName(consultantDTO.getConsultant().getFirmName());
            consultant.setOwnershipChangeRemarks(consultantDTO.getConsultant().getOwnershipChangeRemarks());

            List<ConsultantHR> ownerList = consultantDTO.getConsultantOWs();
            for(ConsultantHR consultantHR:ownerList){
                if(consultantHR.getDeleteRequest() != null && consultantHR.getDeleteRequest() == 1){
                    //to save deleted owner
                    //consultantHR.setDeleteRequest(1);
                    consultantRCDao.saveDeleteHrRequest(consultantHR.getId());
                }else {
                    if(emptyNullCheck(consultantHR.getId())){
                        consultantHR.setId(commonService.getRandomGeneratedId());
                    }
                    if(emptyNullCheck(consultantHR.getCidNo())){
                        continue;
                    }
                   // String hrId = commonService.getRandomGeneratedId();
                   // consultantHR.setId(hrId);
                    consultantHR.setConsultantID(consultantId);
                    consultantHR.setIsPartnerOrOwner(TRUE_INT);
                    consultantNRService.saveHR(consultantHR, loggedInUser);
                }
            }
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "12");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region change of location
        if(renewalServiceType.getChangeOfLocation() != null){
            consultant.setEstAddress(consultantDTO.getConsultant().getEstAddress());
            consultant.setRegDzongkhagId(consultantDTO.getConsultant().getRegDzongkhagId());
            consultant.setRegFaxNo(consultantDTO.getConsultant().getRegFaxNo());
            consultant.setRegPhoneNo(consultantDTO.getConsultant().getRegPhoneNo());
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "6");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region change of firm name
        if(renewalServiceType.getChangeOfFirmName() != null){
            consultant.setFirmName(consultantDTO.getConsultant().getFirmName());
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "10");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region edit in contractor information
        consultant.setId(consultantId);
        String referenceNo = saveOS(consultant, loggedInUser);
        //endregion

        //region Upgrade or downgrade
        if(renewalServiceType.getUpgradeDowngrade() != null){
            List<ConsultantCategory> categories = consultantDTO.getCategories();
            //region save contractor category

           // categories.stream().filter(c->c.getServiceCateID() != null).forEach(c -> {
                categories.stream().filter(c->c.getServiceCateID() != null).forEach(c -> {
                c.setConsultantID(consultantId);
                saveCC(c, loggedInUser, consultant, request);
            });

          saveCCUpgrade(consultant, consultantDTO.getCategories(), loggedInUser);
        }
        //endregion

        //region change of owner or partner
        if(renewalServiceType.getChangeOfOwner() != null){
            //List<ConsultantHR> ownerList = consultantDTO.getConsultant().getConsultantHRs();
            List<ConsultantHR> ownerList = consultantDTO.getConsultantOWs();
            for(ConsultantHR consultantHR:ownerList){
                if(consultantHR.getDeleteRequest() != null && consultantHR.getDeleteRequest() == 1){
                    //to save deleted owner
                   // consultantHR.setDeleteRequest(1);
                    consultantRCDao.saveDeleteHrRequest(consultantHR.getId());
                }else {
                    if(emptyNullCheck(consultantHR.getId())){
                        consultantHR.setId(commonService.getRandomGeneratedId());
                    }
                    if(emptyNullCheck(consultantHR.getCidNo())){
                        continue;
                    }
                    // String hrId = commonService.getRandomGeneratedId();
                    // consultantHR.setId(hrId);
                    consultantHR.setConsultantID(consultantId);
                    consultantHR.setIsPartnerOrOwner(TRUE_INT);
                    consultantNRService.saveHR(consultantHR, loggedInUser);
                }
            }
            consultant.setOwnershipChangeRemarks(consultantDTO.getConsultant().getOwnershipChangeRemarks());
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "4");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region update HR
        if(renewalServiceType.getUpdateHR() != null){
            List<ConsultantHR> hrList = consultantDTO.getConsultantHRs();
            for(ConsultantHR consultantHR:hrList){
                if(consultantHR.getDeleteRequest() != null && consultantHR.getDeleteRequest() == 1){
                    //to save deleted hr
                    consultantRCDao.saveDeleteHrRequest(consultantHR.getId());
                }else{
                    if(emptyNullCheck(consultantHR.getId())){
                        consultantHR.setId(commonService.getRandomGeneratedId());
                    }
                    //currentHRs.add(contractorHR.getId());
                    if(emptyNullCheck(consultantHR.getCidNo())){
                        continue;
                    }
                    consultantHR.setConsultantID(consultantId);
                    consultantHR.setIsPartnerOrOwner(FALSE_INT);
                    consultantNRService.saveHR(consultantHR, loggedInUser);

                    //Save Human resource attachment
                    for (ConsultantHRAttachment consultantHRA : consultantHR.getConsultantHRAs()) {
                        if(!emptyNullCheck(consultantHRA.getId())){
                            if(consultantHRA.getAttachment() == null){ // no changes
                                consultantHRA = consultantNRService.getHRAttachmentFinal(consultantHRA.getId());
                            }else{ // for edit
                                consultantHRA.setEditedBy(loggedInUser.getUserID());
                                consultantHRA.setEditedOn(loggedInUser.getServerDate());
                            }
                        }else {
                            if (consultantHRA.getAttachment() == null) { //No changes, so no need to save
                                continue;
                            }
                        }
                        consultantHRA.setConsultantHrId(consultantHR.getId());
                        consultantNRService.saveHRA(consultantHRA, loggedInUser);
                    }
                }
            }
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "8");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region update EQ
        if(renewalServiceType.getUpdateEq() != null){
            List<ConsultantEQ> eqList = consultantDTO.getEquipments();
            for(ConsultantEQ consultantEQ:eqList){
                if(consultantEQ.getDeleteRequest() != null && consultantEQ.getDeleteRequest() == 1) {
                    //to save deleted hr
                    consultantRCDao.saveDeleteEqRequest(consultantEQ.getId());
                }
                if(emptyNullCheck(consultantEQ.getId())){
                    consultantEQ.setId(commonService.getRandomGeneratedId());
                }
                if(emptyNullCheck(consultantEQ.getEquipmentId())){
                    continue;
                }
                consultantEQ.setConsultantId(consultantId);
                consultantNRService.saveEQ(consultantEQ, loggedInUser);
                //Save Human resource attachment
                for (ConsultantEQAttachment consultantEQA : consultantEQ.getConsultantEQAs()) {
                    if(!emptyNullCheck(consultantEQA.getId())){
                        if(consultantEQA.getAttachment() == null){ // no changes
                            consultantEQA = consultantNRService.getEQAttachmentFinal(consultantEQA.getId());
                        }else{ // for edit
                            consultantEQA.setEditedBy(loggedInUser.getUserID());
                            consultantEQA.setEditedOn(loggedInUser.getServerDate());
                        }
                    }else {
                        if (consultantEQA.getAttachment() == null) { //No changes, so no need to save
                            continue;
                        }
                    }
                    consultantEQA.setEquipmentId(consultantEQ.getId());
                    consultantNRService.saveEQA(consultantEQA, loggedInUser);
                }
            }
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "9");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region save applied service and payment
        appliedServicesList.stream().filter(c-> !c.isEmpty()).forEach(
                c->consultantRCService.saveAppliedS(consultantId,c,loggedInUser, consultantDTO)
        );
        //endregion

        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your application for Other service Of Consultant has been submitted and your application number is "+referenceNo+"<br>" +
                "You will receive an email with the Application summary.<br><br>" +
                "You can track your application using above Application Number. <br>" +
                "Thanks You.");
        return responseMessage;
    }

    public void updateIncorporation(List<ConsultantAttachment> cAttachments, LoggedInUser loggedInUser,String consultantId) throws Exception{
        if(cAttachments.size() > 1) {
           /* for(ConsultantAttachment cAttachment:cAttachments) {
                cAttachment.setConsultantId(consultantId);
                consultantNRService.saveAttachment(cAttachment, loggedInUser);
            }*/
            for (ConsultantAttachment cAttachment : cAttachments) {
                if(!emptyNullCheck(cAttachment.getId())){
                    if(cAttachment.getAttachment() == null){ // no changes
                        cAttachment = consultantNRService.getAttachmentFinal(cAttachment.getId());
                        cAttachment.setAttachmentFor(cAttachment.getAttachmentFor());
                    }else{ // for edit
                        cAttachment.setEditedBy(loggedInUser.getUserID());
                        cAttachment.setEditedOn(loggedInUser.getServerDate());
                    }
                }else {
                    if (cAttachment.getAttachment() == null) { //No changes, so no need to save
                        continue;
                    }
                }
                cAttachment.setConsultantId(consultantId);
                consultantNRService.saveAttachment(cAttachment, loggedInUser);
            }
        }
    }
   /* private void saveCC(List<ConsultantCategory> consultantCategory, String[] serviceCateID, String[] appliedClassID, LoggedInUser loggedInUser, HttpServletRequest request) {
        ConsultantCategory conCategory = new ConsultantCategory();
        for (int i =0; i<serviceCateID.length;i++) {
            conCategory.setId(commonService.getRandomGeneratedId());
            conCategory.setConsultantID(consultantCategory.get(i).getConsultantID());
            conCategory.setServiceCateID(consultantCategory.getServiceCateID());
            conCategory.setAppliedServiceID(s);
            conCategory.setCreatedBy(loggedInUser.getUserID());
            conCategory.setCreatedOn(loggedInUser.getServerDate());
        }
    }*/


    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipmentFinal(String consultantId){
        List<EquipmentDTO> equipmentDTOs = consultantRCDao.getEquipmentFinal(consultantId);
        equipmentDTOs.forEach(e->e.setEqAttachments(consultantRCDao.getEQAttachmentsFinal(e.getId())));
        return equipmentDTOs;
    }

    public String saveOS(Consultant consultant,LoggedInUser loggedInUser){
        String referenceNo = commonService.getNextID("crpconsultant", "ReferenceNo").toString();
        consultant.setReferenceNo(referenceNo);
        if(consultant.getConsultantId() == null){
            consultant.setConsultantId(consultant.getId());
        }
        consultant.setLockedByUserId(null);
        consultant.setWaiveOffLateFee(BigDecimal.ZERO);
        consultant.setAppStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        consultant.setHasNotification("0");
        consultant.setVerifierRemarks(null);
        consultant.setSysVerifierUserId(null);
        consultant.setVerifiedDate(null);
        consultant.setApproverRemarks(null);
        consultant.setApproverUserId(null);
        consultant.setRegApprovedDate(null);
        consultant.setFinalApproverUserId(null);
        consultant.setFinalApproverRemarks(null);
        consultant.setPaymentApprovedDate(null);
        consultant.setPaymentReceiptDate(null);
        consultant.setPaymentApproverRemark(null);
        consultant.setPaymentApproverUserId(null);
        consultant.setApplicationDate(loggedInUser.getServerDate());
        consultant.setCreatedBy(loggedInUser.getUserID());
        consultant.setCreatedOn(loggedInUser.getServerDate());
        consultantRCDao.saveOrUpdate(consultant);

        return referenceNo;
    }

    public void saveCC(ConsultantCategory consultantCategory, LoggedInUser loggedInUser,Consultant consultant, HttpServletRequest request) {
        String aServices[] = consultantCategory.getAppliedServiceID().split(",");
        String id = commonService.getRandomGeneratedId();
        List<String> aServiceList = Arrays.asList(aServices);
        aServiceList.stream().filter(s -> !s.isEmpty()).forEach(s -> {
            ConsultantCategory conCategory = new ConsultantCategory();
            conCategory.setId(commonService.getRandomGeneratedId());
            conCategory.setConsultantID(consultant.getConsultantId());
            conCategory.setServiceCateID(consultantCategory.getServiceCateID());
            conCategory.setAppliedServiceID(s);
            conCategory.setCreatedBy(loggedInUser.getUserID());
            conCategory.setCreatedOn(loggedInUser.getServerDate());
            consultantDao.saveUpdate(conCategory);
        });

        String serviceCodes = null;
        int noOfServices = 0;

        for(String aService : aServiceList) {
            String serviceCode = commonService.getValue("cmnconsultantservice", "Code", "Id", aService).toString();
            if (serviceCodes == null || serviceCodes.isEmpty()) {
                serviceCodes = serviceCode;
            }else{
                serviceCodes = serviceCodes.concat(",").concat(serviceCode);
            }
            noOfServices++;
        }

        //set consultantPayment values
        ConsultantRegPayment consultantPayment = new ConsultantRegPayment();
        consultantPayment.setConsultantId(consultant.getConsultantId());
        consultantPayment.setCategoryId(consultantCategory.getServiceCateID());
        consultantPayment.setAppliedServices(serviceCodes);
        consultantPayment.setServiceXFee(String.valueOf(noOfServices +"*"+ "3000"));
        consultantPayment.setAmount(BigDecimal.valueOf(noOfServices * 3000));
        consultantPayment.setId(id);
        consultantPayment.setCreatedBy(loggedInUser.getUserID());
        consultantPayment.setCreatedOn(loggedInUser.getServerDate());
        consultantNRService.savePayment(consultantPayment, loggedInUser);

        //set consultantServicePaymentDetail values
        ConsultantServicePaymentDetail consultantServicePaymentDetail = new ConsultantServicePaymentDetail();
        consultantServicePaymentDetail.setId(id);
        consultantServicePaymentDetail.setServicePaymentId(id);
        consultantServicePaymentDetail.setCategoryId(consultantCategory.getServiceCateID());
        consultantServicePaymentDetail.setAppliedClassId(serviceCodes);

        String consultantFinalId = (String)commonService.getValue("crpconsultantfinal","Id","CDBNo",consultant.getCdbNo());
        List<ServiceFeeDTO> appliedService = consultantOSDao.getRegisteredService(consultantFinalId);
        Integer amount = 0; String totalServices = null; Integer amountOS = 0;
        for(String aService : aServiceList) {
             for(int i = 0; i < appliedService.size(); i++){
                if (appliedService.get(i).getServiceName() != aService) {
                    totalServices = String.valueOf(noOfServices + "*" + "3000");
                    amount = noOfServices * 3000;
                }else {
                    totalServices = String.valueOf(noOfServices + "*" + "0000");
                    amountOS =noOfServices * 0;
                }
            }
        }
        Integer totalAmt = amountOS + amount;
        consultantServicePaymentDetail.setServiceXFee(totalServices.concat(",").concat(totalServices));
        consultantServicePaymentDetail.setAmount(BigDecimal.valueOf(totalAmt));
        consultantServicePaymentDetail.setCreatedBy(loggedInUser.getUserID());
        consultantServicePaymentDetail.setCreatedOn(loggedInUser.getServerDate());
        consultantRCDao.saveUpdate(consultantServicePaymentDetail);

        //consultantRCService.saveServicePaymentDetailOS(consultantPayment, loggedInUser);

        BigDecimal totalRenewalFee = BigDecimal.ZERO;
        BigDecimal totalCCUpDownFee = BigDecimal.ZERO;
        List<CategoryClassDTO> ccRenewal; //renewal
    }

    public void saveCCUpgrade(Consultant consultant,List<ConsultantCategory> categories, LoggedInUser loggedInUser){
        //save upgrade downgrade change of category
        String appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "7"); //upgrade downgrade service id
        ConsultantAppliedS consultantAppliedS = new ConsultantAppliedS();
        consultantAppliedS.setConsultantId(consultant.getConsultantId());
        consultantAppliedS.setServiceTypeId(appliedServiceId);
        consultantNRService.saveAppliedService(consultantAppliedS, loggedInUser);

        ConsultantServicePayment servicePaymentUD = new ConsultantServicePayment();
        servicePaymentUD.setId(commonService.getRandomGeneratedId());
        servicePaymentUD.setConsultantId(consultant.getId());
        servicePaymentUD.setCmnServiceTypeId(appliedServiceId); //upgrade downgrade
        servicePaymentUD.setTotalAmount(BigDecimal.valueOf(00));
        servicePaymentUD.setPaymentAmount(BigDecimal.valueOf(00));
        servicePaymentUD.setCreatedBy(loggedInUser.getUserID());
        servicePaymentUD.setCreatedOn(loggedInUser.getServerDate());
        consultantRCDao.saveUpdate(servicePaymentUD);

    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getCategoryClassFinal(String contractorId) {
        return consultantRCDao.getCategoryClassFinal(contractorId);
    }

    public String getOngoingAppStatusMsg(String cdbNo){
        ConsultantDTOFetch ongoingApp = consultantNRService.getConsultantOngoingApp(cdbNo);
        if(ongoingApp == null || ongoingApp.getReferenceNo() == null){
            return  null;
        }
        return "You have ongoing application with reference number: <b>"+ongoingApp.getReferenceNo()+
                "</b> submitted on "+ongoingApp.getApplicationDate()+". This application is <b>"
                +ongoingApp.getAppStatusName()
                +". Please wait until complete process for this application.";
    }
    /**
     * To check for validations for renewal. If any condition is fulfilled or not. and returns the message to be displayed
     * @param cdbNo  -- CBDNo
     * @return Response message
     */
    @Transactional(readOnly = true)
    public ResponseMessage check4Renewal(String cdbNo){
        responseMessage.reset();
        LateFeeDTO lateFeeDTO = new LateFeeDTO();
        ConsultantFinal cFinal = consultantRCService.getConsultantFinal(cdbNo);
        responseMessage.setId(cFinal.getId());

        //region check for terminated or end of contractor certificate
        List<String> statusList = new ArrayList<>();
        statusList.add(ApplicationStatus.BLACKLISTED.getCode());
        statusList.add(ApplicationStatus.DEBARRED.getCode());
        statusList.add(ApplicationStatus.DEREGISTERED.getCode());
        statusList.add(ApplicationStatus.REJECTED.getCode());
        statusList.add(ApplicationStatus.REVOKED.getCode());
        statusList.add(ApplicationStatus.SUSPENDED.getCode());
        statusList.add(ApplicationStatus.SURRENDERED.getCode());
        statusList.add(ApplicationStatus.CANCELLATION.getCode());
        if(statusList.contains(cFinal.getAppStatusId())){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("You are not allow to avail this service as your certificate is " +
                    "<b> "+ApplicationStatus.valueOf(cFinal.getAppStatusId()).getName()+"</b>");
            return responseMessage;
        }
        //endregion

        String ongoingApp = getOngoingAppStatusMsg(cdbNo);
        if(ongoingApp != null){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText(ongoingApp);
            return responseMessage;
        }
        responseMessage.setText("You are applying for Other Services of consultant CDB certificate. There will be renewal fee according to service you applied and your category and classes.");

        //endregion
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setDto(cFinal);
        responseMessage.setDto1(lateFeeDTO);
        //responseMessage.setValue(String.valueOf(waiveOffLateFee));
        //responseMessage.setVal2(lateFee.toString());
        return responseMessage;
    }
    @Transactional(readOnly = false)
    public void saveAttachment(ConsultantAttachment cAttachment,LoggedInUser loggedInUser) throws Exception {
        MultipartFile attachment = cAttachment.getAttachment();
        String docName = cAttachment.getDocumentName()+commonService.getFileEXT(attachment);
        String specificLoc = UPLOAD_LOC+"//CertIncorporation";
        String docPath = commonService.uploadDocument(attachment, specificLoc, docName);        String attachmentId = commonService.getRandomGeneratedId();
        cAttachment.setId(attachmentId);
        cAttachment.setDocumentPath(docPath);
        cAttachment.setDocumentName(docName);
        cAttachment.setFileType(attachment.getContentType());
        cAttachment.setCreatedBy(loggedInUser.getUserID());
        cAttachment.setCreatedOn(loggedInUser.getServerDate());
        consultantDao.saveUpdate(cAttachment);
    }

    @Transactional(readOnly = true)
    public String getRegisteredClass(String consultantFinalId,String categoryId){
        String condition = "CrpConsultantFinalId = '"+consultantFinalId+"' AND CmnServiceCategoryId = '"+categoryId+"'";
        String classId = "";
        classId = (String)commonService.getValue("crpconsultantworkclassificationfinal", "CmnApprovedServiceId", condition);
        return classId == null?"":classId;
    }
}
