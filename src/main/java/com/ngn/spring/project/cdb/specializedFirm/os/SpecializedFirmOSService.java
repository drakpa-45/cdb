package com.ngn.spring.project.cdb.specializedFirm.os;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.*;
import com.ngn.spring.project.cdb.contractor.renewal.LateFeeDTO;
import com.ngn.spring.project.cdb.contractor.renewal.RenewalServiceType;
import com.ngn.spring.project.cdb.specializedFirm.SpecializedFirmService;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmDTO;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmDTOFetch;
import com.ngn.spring.project.cdb.specializedFirm.model.*;
import com.ngn.spring.project.cdb.specializedFirm.renewal.SpecializedFirmRDao;
import com.ngn.spring.project.cdb.specializedFirm.renewal.SpecializedFirmRService;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.DropdownDTO;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
public class SpecializedFirmOSService extends BaseService {
    private String UPLOAD_LOC;

    @Autowired
    private SpecializedFirmRDao specializedFirmRDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SpecializedFirmService specializedFirmService;
    @Autowired
    private SpecializedFirmRService specializedFirmRService;


    public DropdownDTO getSpecializedFirmStatus(String cdbNo){
        return specializedFirmRDao.getSpFirmStatus(cdbNo);
    }

    /**
     * The main save method for specializedFirm which calls specific save methods
     * @param spFirmDTO   --  SpFirmDTO
     * @param loggedInUser    --  Current logged in user
     * @return ResponseMessage  -- response message
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public ResponseMessage save(SpFirmDTO spFirmDTO,RenewalServiceType renewalServiceType, LoggedInUser loggedInUser) throws Exception {

        if(renewalServiceType == null){
            return responseMessage;
        }
        String cdbNo = loggedInUser.getCdbNo().split("999")[1];
        List<String> appliedServicesList = new ArrayList<>();
        responseMessage = check4Renewal(cdbNo);
        /*if(responseMessage.getStatus() == UNSUCCESSFUL_STATUS){
            return responseMessage;
        }*/
        SpecializedFirmFinal specializedFirmFinal = (SpecializedFirmFinal)responseMessage.getDto();

        SpecializedFirm specializedFirm = new SpecializedFirm();
        specializedFirm.setpGewogId(specializedFirmFinal.getpGewogId());
        specializedFirm.setpVillageId(specializedFirmFinal.getpVillageId());
        BeanUtils.copyProperties(specializedFirmFinal,specializedFirm);

        String specializedFirmId = commonService.getRandomGeneratedId();
        String appliedService;

        //insert undertaking letter
        if(spFirmDTO.getcAttachments() != null && !spFirmDTO.getcAttachments().isEmpty())
            specializedFirmRService.updateIncorporation(spFirmDTO.getcAttachments(), loggedInUser, specializedFirm.getCrpSpecializedTradeId());


        //region incorporation (Name are also allowed to change)
        if(renewalServiceType.getIncorporation() != null){
            String ownershipTypeId = spFirmDTO.getSpecializedFirm().getOwnershipTypeId();
          //  specializedFirmRService.updateIncorporation(spFirmDTO.getcAttachments(), loggedInUser, specializedFirm.getCrpSpecializedTradeId());
            specializedFirm.setOwnershipTypeId(ownershipTypeId);
            specializedFirm.setFirmName(spFirmDTO.getSpecializedFirm().getFirmName());

            List<SpFirmHR> ownerList = spFirmDTO.getSpecializedFirm().getSpFirmHRs();
            specializedFirm.setOwnershipChangeRemarks(spFirmDTO.getSpecializedFirm().getOwnershipChangeRemarks());
            for(SpFirmHR spFirmHR:ownerList){
                String hrId = commonService.getRandomGeneratedId();
                spFirmHR.setId(hrId);
                spFirmHR.setSpecializedID(specializedFirmId);
                spFirmHR.setIsPartnerOrOwner(TRUE_INT);
                specializedFirmService.saveHR(spFirmHR, loggedInUser);
            }

            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "12");
            appliedServicesList.add(appliedService);

        }
        //endregion

        //region change of location
        if(renewalServiceType.getChangeOfLocation() != null){
            specializedFirm.setEstAddress(spFirmDTO.getSpecializedFirm().getEstAddress());
            specializedFirm.setRegDzongkhagId(spFirmDTO.getSpecializedFirm().getRegDzongkhagId());
            specializedFirm.setRegFaxNo(spFirmDTO.getSpecializedFirm().getRegFaxNo());
            specializedFirm.setRegPhoneNo(spFirmDTO.getSpecializedFirm().getRegPhoneNo());
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "6");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region change of firm name
        if(renewalServiceType.getChangeOfFirmName() != null) {
            specializedFirm.setFirmName(spFirmDTO.getSpecializedFirm().getFirmName());
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "10");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region edit in specializedFirm information
        specializedFirm.setId(specializedFirmId);
        String referenceNo = saveOS(specializedFirm, loggedInUser);
        //endregion

        //region Upgrade or downgrade
        if(renewalServiceType.getUpgradeDowngrade() != null){
            List<SpFirmCategory> categories = spFirmDTO.getCategories();
            //region save specializedFirm category
            categories.stream().filter(c->c.getAppliedCategoryId()!= null).forEach(c -> {
                        c.setSpecializedID(specializedFirmId);
                        specializedFirmService.saveCC(c, loggedInUser);
                    }
            );
            saveCCUpgrade(specializedFirm, spFirmDTO.getCategories(), loggedInUser);
        }
        //endregion

        //region change of owner or partner
        if(renewalServiceType.getChangeOfOwner() != null){
            List<SpFirmHR> ownerList = spFirmDTO.getSpecializedFirm().getSpFirmHRs();
            specializedFirm.setOwnershipChangeRemarks(spFirmDTO.getSpecializedFirm().getOwnershipChangeRemarks());
            for(SpFirmHR spFirmHR:ownerList){
                String hrId = commonService.getRandomGeneratedId();
                spFirmHR.setId(hrId);
                spFirmHR.setSpecializedID(specializedFirmId);
                spFirmHR.setIsPartnerOrOwner(TRUE_INT);
                specializedFirmService.saveHR(spFirmHR, loggedInUser);
            }
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "4");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region update HR
        if(renewalServiceType.getUpdateHR() != null){
            List<SpFirmHR> hrList = spFirmDTO.getSpFirmHRs();
            for(SpFirmHR spFirmHR:hrList){
                if(spFirmHR.getDeleteRequest() != null && spFirmHR.getDeleteRequest() == 1){
                    //to save deleted hr
                    specializedFirmRDao.saveDeleteHrRequest(spFirmHR.getId());
                }else{
                    if(emptyNullCheck(spFirmHR.getId())){
                        spFirmHR.setId(commonService.getRandomGeneratedId());
                    }
                    //currentHRs.add(contractorHR.getId());
                    if(emptyNullCheck(spFirmHR.getCidNo())){
                        continue;
                    }
                    spFirmHR.setSpecializedID(specializedFirmId);
                    spFirmHR.setIsPartnerOrOwner(FALSE_INT);
                    specializedFirmService.saveHR(spFirmHR, loggedInUser);
                    //Save Human resource attachment
                    for (SpFirmtHRAttachment spFirmtHRA : spFirmHR.getSpFirmHRAs()) {
                        if(spFirmtHRA.getAttachment() == null){ //No changes, so no need to save
                            continue;
                        }
                        spFirmtHRA.setSpecializedHrId(spFirmHR.getId());
                        specializedFirmService.saveHRA(spFirmtHRA, loggedInUser);
                    }
                }
            }
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "8");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region update EQ
        if(renewalServiceType.getUpdateEq() != null){
            List<SpFirmEQ> eqList = spFirmDTO.getEquipments();
            for(SpFirmEQ spFirmEQ:eqList){
                if(spFirmEQ.getDeleteRequest() != null && spFirmEQ.getDeleteRequest() == 1) {
                    //to save deleted hr
                    specializedFirmRDao.saveDeleteEqRequest(spFirmEQ.getId());
                }
                if(emptyNullCheck(spFirmEQ.getId())){
                    spFirmEQ.setId(commonService.getRandomGeneratedId());
                }
                if(emptyNullCheck(spFirmEQ.getEquipmentId())){
                    continue;
                }
                spFirmEQ.setSpecializedTradeId(specializedFirmId);
                specializedFirmService.saveEQ(spFirmEQ, loggedInUser);
                //Save Human resource attachment
                for (SpFirmEQAttachment spFirmEQA : spFirmEQ.getSpFirmEQAs()) {
                    if(spFirmEQA.getAttachment() == null){ //No changes, so no need to save
                        continue;
                    }
                    spFirmEQA.setEquipmentId(spFirmEQ.getId());
                    specializedFirmService.saveEQA(spFirmEQA, loggedInUser);
                }
            }

            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "9");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region save applied service and payment
        appliedServicesList.stream().filter(c-> !c.isEmpty()).forEach(
                c->specializedFirmRService.saveAppliedS(specializedFirmId,c,loggedInUser)
        );
        //endregion

        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your application for Renewal Of specializedFirm has been submitted and your application number is "+referenceNo+"<br>" +
                "You will receive an email with the Application summary.<br><br>" +
                "You can track your application using above Application Number. <br>" +
                "Thanks You.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public void saveAttachment(SpFirmAttachment cAttachment,LoggedInUser loggedInUser) throws Exception {
        MultipartFile attachment = cAttachment.getAttachment();
        String docName = cAttachment.getDocumentName()+commonService.getFileEXT(attachment);
        String specificLoc = UPLOAD_LOC+"//CertIncorporation";
        String docPath = commonService.uploadDocument(attachment, specificLoc, docName);
        String attachmentId = commonService.getRandomGeneratedId();
        cAttachment.setId(attachmentId);
        cAttachment.setDocumentPath(docPath);
        cAttachment.setDocumentName(docName);
        cAttachment.setFileType(attachment.getContentType());
        cAttachment.setCreatedBy(loggedInUser.getUserID());
        cAttachment.setCreatedOn(loggedInUser.getServerDate());
        specializedFirmRDao.saveUpdate(cAttachment);
    }
    public String saveOS(SpecializedFirm specializedFirm,LoggedInUser loggedInUser){
        String referenceNo = commonService.getNextID("crpspecializedtrade", "ReferenceNo").toString();
        specializedFirm.setReferenceNo(referenceNo);
        if(specializedFirm.getCrpSpecializedTradeId() == null){
            specializedFirm.setCrpSpecializedTradeId(specializedFirm.getId());
        }
        specializedFirm.setLockedByUserId(null);
        specializedFirm.setWaiveOffLateFee(BigDecimal.ZERO);
        specializedFirm.setAppStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        specializedFirm.setHasNotification("0");
        specializedFirm.setVerifierRemarks(null);
        specializedFirm.setSysVerifierUserId(null);
        specializedFirm.setVerifiedDate(null);
        specializedFirm.setApproverRemarks(null);
        specializedFirm.setApproverUserId(null);
        specializedFirm.setRegApprovedDate(null);
        specializedFirm.setFinalApproverUserId(null);
        specializedFirm.setFinalApproverRemarks(null);
        specializedFirm.setPaymentApprovedDate(null);
        specializedFirm.setPaymentReceiptDate(null);
        specializedFirm.setPaymentApproverRemark(null);
        specializedFirm.setPaymentApproverUserId(null);
        specializedFirm.setApplicationDate(loggedInUser.getServerDate());
        specializedFirm.setCreatedBy(loggedInUser.getUserID());
        specializedFirm.setCreatedOn(loggedInUser.getServerDate());
        specializedFirmRDao.saveOrUpdate(specializedFirm);

        return referenceNo;

    }

    public void saveCCUpgrade(SpecializedFirm specializedFirm,List<SpFirmCategory> categories, LoggedInUser loggedInUser){

        BigDecimal totalCCUpDownFee = BigDecimal.ZERO;
        final List<CategoryClassDTO> ccUpDown = new ArrayList<>();  //upgrade downgrade
        String specializedFirmFinalId = (String)commonService.getValue("crpspecializedtradefinal","Id","SPNo",specializedFirm.getCdbNo());

   /*     if(categories != null && !categories.isEmpty()){
            categories.stream().forEach(r->ccUpDown.add(new CategoryClassDTO(r.getAppliedCategoryId()
                    ,specializedFirmRService.getRegisteredClass(specializedFirmFinalId, r.getAppliedCategoryId()))));
            for(CategoryClassDTO classDTO : ccUpDown){
                BigDecimal fee = ((FeeStructureDTO) specializedFirmService.gFeeStructure(classDTO.getaClassId()).get(0)).getRegistrationFee();
                classDTO.setvAmount(fee);
                totalCCUpDownFee = totalCCUpDownFee.add(fee);
            }
        }*/
        BigDecimal totalRenewalFee = BigDecimal.ZERO;
        List<CategoryClassDTO> ccRenewal; //renewal

        if(categories != null && !categories.isEmpty()){
            final List<CategoryClassDTO> renewal = new ArrayList<>(); //renewal
            //upgrade or downgrade or change of category
            List<SpFirmCategory> conCategoryR = categories.stream().filter(c -> c.getAppliedCategoryId() != null).filter(c -> getRegisteredClass(specializedFirmFinalId, c.getAppliedCategoryId()).equals(c.getAppliedCategoryId())).collect(Collectors.toList());
            conCategoryR.addAll(categories.stream().filter(c -> c.getAppliedCategoryId() != null && c.getApprovedCategoryId() != null).collect(Collectors.toList()));
            List<SpFirmCategory> conCategoryUD = categories.stream().filter(c -> !conCategoryR.contains(c)).collect(Collectors.toList());

            conCategoryR.stream().forEach(r ->{
                String classId = getRegisteredClass(specializedFirmFinalId, r.getAppliedCategoryId());
                renewal.add(new CategoryClassDTO(r.getAppliedCategoryId(),classId,classId));
            });
            conCategoryUD.stream().forEach(r->ccUpDown.add(new CategoryClassDTO(r.getAppliedCategoryId(),r.getAppliedCategoryId(),getRegisteredClass(specializedFirmFinalId, r.getAppliedCategoryId()))));
            ccRenewal = renewal;
        } else{ // no upgrade or downgrade or change of category
            ccRenewal = getCategoryClassFinal(specializedFirmFinalId);
        }

        for(CategoryClassDTO classDTO : ccRenewal){
            BigDecimal renewalFee = ((FeeStructureDTO) specializedFirmService.gFeeStructure(classDTO.getaClassId()).get(0)).getRenewalFee();
            classDTO.setvAmount(BigDecimal.valueOf(00.00));
            totalRenewalFee = totalRenewalFee.add(renewalFee);
        }

        for(CategoryClassDTO classDTO : ccUpDown){
            if(classDTO.getCategoryId() != null){
                BigDecimal fee = ((FeeStructureDTO) specializedFirmService.gFeeStructure(classDTO.getaClassId()).get(0)).getRegistrationFee();
                classDTO.setvAmount(fee);
                totalCCUpDownFee = totalCCUpDownFee.add(fee);
            }
        }

        //save upgrade downgrade change of category
        if(!Objects.equals(totalCCUpDownFee, BigDecimal.ZERO) || Objects.equals(totalCCUpDownFee,BigDecimal.ZERO)){
            String appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "7"); //upgrade downgrade service id
            SpFirmAppliedS spFirmAppliedS = new SpFirmAppliedS();
            spFirmAppliedS.setSpecializedTradeId(specializedFirm.getId());
            spFirmAppliedS.setServiceTypeId(appliedServiceId);
            specializedFirmService.saveAppliedService(spFirmAppliedS, loggedInUser);

            SpFirmServicePayment servicePaymentUD = new SpFirmServicePayment();
            servicePaymentUD.setId(commonService.getRandomGeneratedId());
            servicePaymentUD.setSpecializedFirmId(specializedFirm.getId());
            servicePaymentUD.setCmnServiceTypeId(appliedServiceId); //upgrade downgrade
            servicePaymentUD.setTotalAmount(totalCCUpDownFee);
            servicePaymentUD.setPaymentAmount(totalCCUpDownFee);
            servicePaymentUD.setCreatedBy(loggedInUser.getUserID());
            servicePaymentUD.setCreatedOn(loggedInUser.getServerDate());
            specializedFirmRDao.saveUpdate(servicePaymentUD);

            specializedFirmRService.saveServicePaymentDetail(servicePaymentUD.getId(), ccUpDown,loggedInUser);

            specializedFirmRService.saveServicePaymentDetailOS(servicePaymentUD.getId(), ccRenewal,loggedInUser);
        }
    }

    public String getOngoingAppStatusMsg(String cdbNo){
        SpFirmDTOFetch ongoingApp = specializedFirmService.getSpecializedFirmOngoingApp(cdbNo);
        if(ongoingApp == null || ongoingApp.getReferenceNo() == null){
            return  null;
        }
        return "You have ongoing application with reference number: <b>"+ongoingApp.getReferenceNo()+
                "</b> submitted on "+ongoingApp.getApplicationDate()+". This application is <b>"
                +ongoingApp.getAppStatusName()
                +". Please wait until complete process for this application.";
    }

    /**
     * To check for validations for OTHER SERVICES. If any condition is fulfilled or not. and returns the message to be displayed
     * @param cdbNo  -- CBDNo
     * @return Response message
     */
    @Transactional(readOnly = true)
    public ResponseMessage check4Renewal(String cdbNo){
        responseMessage.reset();
        LateFeeDTO lateFeeDTO = new LateFeeDTO();
        SpecializedFirmFinal cFinal = specializedFirmRService.getSpecializedFirmFinal(cdbNo);
        responseMessage.setId(cFinal.getId());

        //region check for terminated or end of specializedFirm certificate
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

        //region check expiry date and calculate late fee
       /*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate curDate = LocalDate.parse(sdf.format(new Date()));
        LocalDate expiryDate = LocalDate.parse(sdf.format(cFinal.getRegExpiryDate()));
        LocalDate gracePeriodDate = expiryDate.plusDays(30);
        LocalDate renewalLinkActiveDate = expiryDate.minusDays(30);

        if(!curDate.isAfter(renewalLinkActiveDate)){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Your certificate expires on " +sdf.format(cFinal.getRegExpiryDate())+"."+
                    "The renewal link will open only before 30 days from expiry date.");
            return responseMessage;
        }
        long waiveOffLateFee = 0;
        BigDecimal lateFee = BigDecimal.ZERO;
        if(curDate.isAfter(gracePeriodDate)){
            Long noOfLateDays = ChronoUnit.DAYS.between(gracePeriodDate, curDate)-1;
            Long  acNoOfLateDays = ChronoUnit.DAYS.between(expiryDate, curDate)-1;
            lateFee = new BigDecimal((noOfLateDays*100));
            responseMessage.setText("Seems like your registration is already expired on <b>"+expiryDate+
                    "</b>. The total number of days late is <b>"+acNoOfLateDays+"</b> days." +
                    " However 30 days is considered as grace period which means the late fees that would be imposed within that period will be waived. Penalty amount is Nu. 100 per day.<br>" +
                    "Total number of days after grace period is <b>"+noOfLateDays+"</b>. Total of Nu. "+lateFee+" will be imposed as penalty for late renewal of your cdb Certificate till today. " +
                    "However your penalty will be calculated till date of approval.");
            waiveOffLateFee = (acNoOfLateDays - noOfLateDays)*100;
            lateFeeDTO.setNoOfDaysLate(acNoOfLateDays.intValue());
            lateFeeDTO.setNoOfDaysAfterGracePeriod(noOfLateDays.intValue());
            lateFeeDTO.setWaiveOffLateFee(new BigDecimal(waiveOffLateFee));
            lateFeeDTO.setPaymentAmount(lateFee);
        }else{
            responseMessage.setText("You are applying for renewal of contractor CDB certificate on time. So, no penalty will be" +
                    "charged. However, there will be renewal fee according to service you applied and your category and classes.");
        }
        */
        responseMessage.setText("You are applying for other Services of specialized Firm CDB certificate.There will be renewal fee according to service you applied and your category and classes.");
        //endregion
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setDto(cFinal);
        responseMessage.setDto1(lateFeeDTO);
        //responseMessage.setValue(String.valueOf(waiveOffLateFee));
        //responseMessage.setVal2(lateFee.toString());
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipmentFinal(String specializedFirmId){
        List<EquipmentDTO> equipmentDTOs = specializedFirmRDao.getEquipmentFinal(specializedFirmId);
        equipmentDTOs.forEach(e->e.setEqAttachments(specializedFirmRDao.getEQAttachmentsFinal(e.getId())));
        return equipmentDTOs;
    }

    @Transactional(readOnly = true)
    public String getRegisteredClass(String specializedFirmFinalId,String categoryId){
        String condition = "CrpSpecializedTradeFinalId = '"+specializedFirmFinalId+"' AND CmnAppliedCategoryId = '"+categoryId+"'";
        String classId = (String)commonService.getValue("crpspecializedtradeworkclassificationfinal", "CmnApprovedCategoryId", condition);
        return classId == null?"":classId;
    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getCategoryClassFinal(String specializedFirmId) {
        return specializedFirmRDao.getCategoryClassFinal(specializedFirmId);
    }

}
