package com.ngn.spring.project.cdb.consultant.os;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.consultant.model.*;
import com.ngn.spring.project.cdb.consultant.registration.ConsultantNRDao;
import com.ngn.spring.project.cdb.consultant.registration.ConsultantNRService;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTO;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTOFetch;
import com.ngn.spring.project.cdb.consultant.renewal.ConsultantRCDao;
import com.ngn.spring.project.cdb.consultant.renewal.ConsultantRCService;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.*;
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
        consultant.setConsultantId(consultantFinal.getId());
        BeanUtils.copyProperties(consultantFinal,consultant);

        String consultantId = commonService.getRandomGeneratedId();
        String appliedService;

        //insert undertaking letter
        if(consultantDTO.getcAttachments() != null && !consultantDTO.getcAttachments().isEmpty())
            consultantRCService.updateIncorporation(consultantDTO.getcAttachments(), loggedInUser, consultant.getConsultantId());

        //region incorporation (Name are also allowed to change)
        if(renewalServiceType.getIncorporation() != null){
            String ownershipTypeId = consultantDTO.getConsultant().getOwnershipTypeId();
            //consultantRCService.updateIncorporation(consultantDTO.getcAttachments(), loggedInUser, consultant.getConsultantId());
            consultant.setOwnershipTypeId(ownershipTypeId);
            consultant.setFirmName(consultantDTO.getConsultant().getFirmName());
            consultant.setOwnershipChangeRemarks(consultantDTO.getConsultant().getOwnershipChangeRemarks());

            List<ConsultantHR> ownerList = consultantDTO.getConsultant().getConsultantHRs();
            for(ConsultantHR consultantHR:ownerList){
                String hrId = commonService.getRandomGeneratedId();
                consultantHR.setId(hrId);
                consultantHR.setConsultantID(consultantId);
                consultantHR.setIsPartnerOrOwner(TRUE_INT);
                consultantNRService.saveHR(consultantHR, loggedInUser);
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
            String serviceCateID[] = request.getParameterValues("serviceCateID");
            String appliedClassID[] = request.getParameterValues("appliedClassID");

            for (int i =0; i<serviceCateID.length-1;i++) {
                ConsultantCategory conCategory = new ConsultantCategory();
                categories.get(i).setConsultantID(consultantId);
                for (int j =0; j<serviceCateID.length-1;j++) {
                    conCategory.setId(commonService.getRandomGeneratedId());
                    conCategory.setConsultantID(consultantId);
                    conCategory.setServiceCateID(serviceCateID[i]);
                    conCategory.setAppliedServiceID(appliedClassID[i]);
                    conCategory.setCreatedBy(loggedInUser.getUserID());
                    conCategory.setCreatedOn(loggedInUser.getServerDate());
                }
                //saveCC(categories,serviceCateID,appliedClassID, loggedInUser, request);
            }
            saveCCUpgrade(consultant, serviceCateID,appliedClassID, loggedInUser);

           /* categories.stream().filter(c->c.getProjectCateID() != null).forEach(c -> {
                c.setContractorID(contractorId);
              //  consultantNRService.saveCC(c, loggedInUser);
            });
            saveCCUpgrade(contractor, consultantDTO.getCategories(), loggedInUser);*/
        }
        //endregion

        //region change of owner or partner
        if(renewalServiceType.getChangeOfOwner() != null){
            List<ConsultantHR> ownerList = consultantDTO.getConsultant().getConsultantHRs();

            for(ConsultantHR consultantHR:ownerList){
                String hrId = commonService.getRandomGeneratedId();
                consultantHR.setId(hrId);
                consultantHR.setConsultantID(consultantId);
                consultantHR.setIsPartnerOrOwner(TRUE_INT);
                consultantNRService.saveHR(consultantHR, loggedInUser);
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
                        if(consultantHRA.getAttachment() == null){ //No changes, so no need to save
                            continue;
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
                    if(consultantEQA.getAttachment() == null){ //No changes, so no need to save
                        continue;
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
                c->consultantRCService.saveAppliedS(consultantId,c,loggedInUser)
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

   /* public void saveCCUpgrade(Consultant consultant,List<ConCategory> categories, LoggedInUser loggedInUser){
        BigDecimal totalCCUpDownFee = BigDecimal.ZERO;
        final List<CategoryClassDTO> ccUpDown = new ArrayList<>();  //upgrade downgrade
        String contractorFinalId = (String)commonService.getValue("crpconsultantfinal","Id","CDBNo",consultant.getCdbNo());

        if(categories != null && !categories.isEmpty()){
            categories.stream().forEach(r->ccUpDown.add(new CategoryClassDTO(r.getProjectCateID(),r.getAppliedClassID()
                    ,consultantRCService.getRegisteredClass(contractorFinalId, r.getProjectCateID()))));
            for(CategoryClassDTO classDTO : ccUpDown){
                BigDecimal fee = ((FeeStructureDTO) consultantNRService.gFeeStructure(classDTO.getaClassId()).get(0)).getRegistrationFee();
                classDTO.setvAmount(fee);
                totalCCUpDownFee = totalCCUpDownFee.add(fee);
            }
        }
        //save upgrade downgrade change of category
        if(!Objects.equals(totalCCUpDownFee, BigDecimal.ZERO)){
            String appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "7"); //upgrade downgrade service id
            ConsultantAppliedS consultantAppliedS = new ConsultantAppliedS();
            consultantAppliedS.setConsultantId(consultant.getId());
            consultantAppliedS.setServiceTypeId(appliedServiceId);
         //   consultantNRService.saveAppliedService(contractorAppliedS, loggedInUser);

            ConsultantServicePayment servicePaymentUD = new ConsultantServicePayment();
            servicePaymentUD.setId(commonService.getRandomGeneratedId());
            servicePaymentUD.setConsultantId(consultant.getId());
            servicePaymentUD.setCmnServiceTypeId(appliedServiceId); //upgrade downgrade
            servicePaymentUD.setTotalAmount(totalCCUpDownFee);
            servicePaymentUD.setPaymentAmount(totalCCUpDownFee);
            servicePaymentUD.setCreatedBy(loggedInUser.getUserID());
            servicePaymentUD.setCreatedOn(loggedInUser.getServerDate());
            consultantRCDao.saveUpdate(servicePaymentUD);

            consultantRCService.saveServicePaymentDetail(servicePaymentUD.getId(), ccUpDown,loggedInUser);
        }
    }*/

    private void saveCCUpgrade(Consultant consultant, String[] serviceCateID, String[] appliedClassID, LoggedInUser loggedInUser) {
        BigDecimal totalCCUpDownFee = BigDecimal.ZERO;
        final List<CategoryClassDTO> ccUpDown = new ArrayList<>();  //upgrade downgrade
        String consultantFinalId = (String) commonService.getValue("crpconsultantfinal", "Id", "CDBNo", consultant.getCdbNo());

        List<ConsultantCategory> categories = new ArrayList<>();
        BigDecimal totalRenewalFee = BigDecimal.ZERO;
        List<CategoryClassDTO> ccRenewal; //renewal

        if (serviceCateID != null) {
            final List<CategoryClassDTO> renewal = new ArrayList<>(); //renewal
            //upgrade or downgrade or change of category
           // List<ConsultantCategory> conCategoryR = categories.stream().filter(c -> appliedClassID != null).filter(c -> getRegisteredClass(consultantFinalId, serviceCateID).equals(appliedClassID)).collect(Collectors.toList());

            List<ConsultantCategory> conCategoryR = null;
            for(int i =0; i<=serviceCateID.length;i++){
              //  conCategoryR = getRegisteredClass(consultantFinalId,serviceCateID[i]);
                String condition = "CrpConsultantFinalId = '"+consultantFinalId+"' AND CmnServiceCategoryId = '"+serviceCateID[i]+"'";
                List<ConsultantCategory> classId = null;
                conCategoryR = (List<ConsultantCategory>) commonService.getValue("crpconsultantworkclassification", "CmnApprovedServiceId", condition);
            }
            List<ConsultantCategory> conCategoryUD = null;

            conCategoryR.addAll(conCategoryR.stream().filter(c -> serviceCateID != null && appliedClassID != null).collect(Collectors.toList()));
            final List<ConsultantCategory> finalConCategoryR = conCategoryR;
            conCategoryUD = conCategoryR.stream().filter(c -> !finalConCategoryR.contains(c)).collect(Collectors.toList());

            conCategoryR.stream().forEach(r -> {
                String classId = getRegisteredClass(consultantFinalId, r.getServiceCateID());
                renewal.add(new CategoryClassDTO(r.getServiceCateID(), classId, classId));
            });
            conCategoryUD.stream().forEach(r -> ccUpDown.add(new CategoryClassDTO(r.getServiceCateID(), r.getAppliedServiceID(), getRegisteredClass(consultantFinalId, r.getServiceCateID()))));
            ccRenewal = renewal;
        } else { // no upgrade or downgrade or change of category
            ccRenewal = getCategoryClassFinal(consultantFinalId);
        }

        for (CategoryClassDTO classDTO : ccRenewal) {
            BigDecimal renewalFee = ((FeeStructureDTO) consultantNRService.gFeeStructure(classDTO.getaClassId()).get(0)).getRenewalFee();
            classDTO.setvAmount(BigDecimal.valueOf(00.00));
            totalRenewalFee = totalRenewalFee.add(renewalFee);
        }

        for (CategoryClassDTO classDTO : ccUpDown) {
            if (classDTO.getCategoryId() != null) {
                BigDecimal fee = ((FeeStructureDTO) consultantNRService.gFeeStructure(classDTO.getaClassId()).get(0)).getRegistrationFee();
                classDTO.setvAmount(fee);
                totalCCUpDownFee = totalCCUpDownFee.add(fee);
            }
        }

        //save upgrade downgrade change of category
        if (!Objects.equals(totalCCUpDownFee, BigDecimal.ZERO)) {
            String appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "7"); //upgrade downgrade service id
            ConsultantAppliedS consultantAppliedS = new ConsultantAppliedS();
            consultantAppliedS.setConsultantId(consultant.getId());
            consultantAppliedS.setServiceTypeId(appliedServiceId);
            consultantNRService.saveAppliedService(consultantAppliedS, loggedInUser);

            ConsultantServicePayment servicePaymentUD = new ConsultantServicePayment();
            servicePaymentUD.setId(commonService.getRandomGeneratedId());
            servicePaymentUD.setConsultantId(consultant.getId());
            servicePaymentUD.setCmnServiceTypeId(appliedServiceId); //upgrade downgrade
            servicePaymentUD.setTotalAmount(totalCCUpDownFee);
            servicePaymentUD.setPaymentAmount(totalCCUpDownFee);
            servicePaymentUD.setCreatedBy(loggedInUser.getUserID());
            servicePaymentUD.setCreatedOn(loggedInUser.getServerDate());
            consultantRCDao.saveUpdate(servicePaymentUD);

            consultantRCService.saveServicePaymentDetail(servicePaymentUD.getId(), ccUpDown, loggedInUser);
        }
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
        classId = (String)commonService.getValue("crpconsultantworkclassification", "CmnApprovedServiceId", condition);
        return classId == null?"":classId;
    }
}
