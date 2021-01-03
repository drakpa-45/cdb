package com.ngn.spring.project.cdb.consultant.renewal;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.common.dto.ServiceFeeDTO;
import com.ngn.spring.project.cdb.consultant.model.*;
import com.ngn.spring.project.cdb.consultant.registration.ConsultantNRService;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTO;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTOFetch;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantHrDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.*;
import com.ngn.spring.project.cdb.contractor.renewal.LateFeeDTO;
import com.ngn.spring.project.cdb.contractor.renewal.RenewalServiceType;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.DropdownDTO;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
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
public class ConsultantRCService extends BaseService {
    private String UPLOAD_LOC;

    @Autowired
    private ConsultantRCDao consultantRCDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ConsultantNRService consultantNRService;


    public DropdownDTO getConsultantStatus(String cdbNo){
        return consultantRCDao.getConsultantStatus(cdbNo);
    }

    /**
     * The main save method for consultant which calls specific save methods
     * @param consultantDTO   --  consultantDTO
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
        responseMessage = check4Renewal(cdbNo,true);
        if(responseMessage.getStatus() == UNSUCCESSFUL_STATUS){
            return responseMessage;
        }
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
            updateIncorporation(consultantDTO.getcAttachments(), loggedInUser, consultantFinal.getId());


        //region incorporation (Name are also allowed to change)
        if(renewalServiceType.getIncorporation() != null){
            String ownershipTypeId = consultantDTO.getConsultant().getOwnershipTypeId();
          //  updateIncorporation(consultantDTO.getcAttachments(), loggedInUser, consultant.getConsultantId());
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

        //region edit in crpconsultant information
        consultant.setId(consultantId);
        String referenceNo = saveRC(consultant,loggedInUser);
      //  List<CategoryClassDTO> classDTOs =  saveRCFeeCCUpgrade(consultant,consultantDTO.getCategories(),loggedInUser,request);
        //endregion

        //region Upgrade or downgrade
       /* if(renewalServiceType.getUpgradeDowngrade() != null){
            List<ConsultantCategory> categories = consultantDTO.getCategories();
            //region save consultant category
            categories.stream().filter(c->c.getProjectCateID() != null).forEach(c -> {
                c.setConsultantID(consultantId);
                consultantNRService.saveCC(c, loggedInUser);
            });
        }*/
        //endregion

/*
        //region Upgrade or downgrade
        classDTOs.stream().filter(c->c.getCategoryId() != null).forEach(c -> {
            ConsultantCategory conCategory = new ConsultantCategory();
            conCategory.setConsultantID(consultantId);
            conCategory.setServiceCateID(c.getCategoryId());
            conCategory.setAppliedServiceID(c.getaClassId());
            consultantNRService.saveCC(conCategory, loggedInUser,request);
        });
        //endregion
        */

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

        //late fee service id
        BigDecimal lateFee = new BigDecimal(responseMessage.getVal2());
        if(lateFee.compareTo(BigDecimal.ZERO) == 0){
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "11");
            appliedServicesList.add(appliedService);
        }

        //region save applied service and payment
        appliedServicesList.stream().filter(c-> !c.isEmpty()).forEach(
                c->saveAppliedS(consultantId,c,loggedInUser)
        );

        //endregion

        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your application for Renewal Of Consultant has been submitted and your application number is "+referenceNo+"<br>" +
                "You will receive an email with the Application summary.<br><br>" +
                "You can track your application using above Application Number. <br>" +
                "Thanks You.");
        return responseMessage;
    }


    public String saveRC(Consultant consultant,LoggedInUser loggedInUser){
        String referenceNo = commonService.getNextID("crpconsultant", "ReferenceNo").toString();
        consultant.setReferenceNo(referenceNo);
        if(consultant.getConsultantId() == null){
            consultant.setConsultantId(consultant.getId());
        }
        LateFeeDTO lateFeeDTO = (LateFeeDTO)responseMessage.getDto1();
        consultant.setWaiveOffLateFee(lateFeeDTO.getWaiveOffLateFee()==null? BigDecimal.ZERO:lateFeeDTO.getWaiveOffLateFee());
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


    public List<CategoryClassDTO> saveRCFeeCCUpgrade(Consultant consultant, List<ConsultantCategory> categories, LoggedInUser loggedInUser, HttpServletRequest request){

        BigDecimal totalRenewalFee = BigDecimal.ZERO;
        BigDecimal totalCCUpDownFee = BigDecimal.ZERO;
        List<CategoryClassDTO> ccRenewal; //renewal
        final List<CategoryClassDTO> ccUpDown = new ArrayList<>();  //upgrade downgrade
        String consultantFinalId = (String)commonService.getValue("crpconsultantfinal","Id","CDBNo",consultant.getCdbNo());

        String itemId[] = request.getParameterValues("itemId");

        List<String> appliedServices;
            appliedServices = Arrays.asList(itemId); // request is made changes here

            if(categories != null && !categories.isEmpty()){
            final List<CategoryClassDTO> renewal = new ArrayList<>(); //renewal
            //upgrade or downgrade or change of category
                final List<String> finalAppliedServices = appliedServices;
                List<ConsultantCategory> conCategoryR = categories.stream().filter(c -> finalAppliedServices != null).filter(c->getRegisteredClass(consultantFinalId,c.getServiceCateID()).equals(appliedServices.toString())).collect(Collectors.toList());
                conCategoryR.addAll(categories.stream().filter(c -> c.getServiceCateID() != null && appliedServices != null).collect(Collectors.toList()));
            List<ConsultantCategory> conCategoryUD = categories.stream().filter(c -> !conCategoryR.contains(c)).collect(Collectors.toList());

               /* List<ConsultantCategory> conCategoryR = categories.stream().filter(c-> c.getServiceCateID() != null).filter(c -> getRegisteredClass(consultantFinalId, c.getServiceCateID()).equals(c.getServiceCateID())).collect(Collectors.toList());
                conCategoryR.addAll(categories.stream().filter(c -> c.getServiceCateID() != null && c.getAppliedServiceID() == null).collect(Collectors.toList()));
                List<ConsultantCategory> conCategoryUD = categories.stream().filter(c -> !conCategoryR.contains(c)).collect(Collectors.toList());
                */

                conCategoryR.stream().forEach(r -> {
                String classId = getRegisteredClass(consultantFinalId, r.getServiceCateID());
                renewal.add(new CategoryClassDTO(r.getServiceCateID(), classId, classId));
            });

            conCategoryUD.stream().forEach(r->ccUpDown.add(new CategoryClassDTO(r.getServiceCateID(),getRegisteredClass(consultantFinalId, r.getServiceCateID()))));
            ccRenewal = renewal;
        } else { // no upgrade or downgrade or change of category
            ccRenewal = getCategoryClassFinal(consultantFinalId);
        }
        for(CategoryClassDTO classDTO : ccRenewal){
            BigDecimal renewalFee = ((FeeStructureDTO) consultantNRService.gFeeStructure(classDTO.getaClassId()).get(0)).getRenewalFee();
            classDTO.setvAmount(renewalFee);
            totalRenewalFee = totalRenewalFee.add(renewalFee);
        }
        for(CategoryClassDTO classDTO : ccUpDown){
            BigDecimal fee = ((FeeStructureDTO) consultantNRService.gFeeStructure(classDTO.getaClassId()).get(0)).getRegistrationFee();
            classDTO.setvAmount(fee);
            totalCCUpDownFee = totalCCUpDownFee.add(fee);
        }

        //save renewal
        LateFeeDTO lateFeeDTO = (LateFeeDTO)responseMessage.getDto1();
        if(!Objects.equals(totalRenewalFee, BigDecimal.ZERO)){
            String appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "2"); //renewal service id
            ConsultantAppliedS consultantAppliedS = new ConsultantAppliedS();
            consultantAppliedS.setConsultantId(consultant.getId());
            consultantAppliedS.setServiceTypeId(appliedServiceId);
            consultantNRService.saveAppliedService(consultantAppliedS, loggedInUser);

            ConsultantServicePayment servicePaymentR = new ConsultantServicePayment();
            servicePaymentR.setId(commonService.getRandomGeneratedId());
            servicePaymentR.setConsultantId(consultant.getId());
            servicePaymentR.setCmnServiceTypeId(appliedServiceId); //renewal
            servicePaymentR.setNoOfDaysLate(lateFeeDTO.getNoOfDaysLate());
            servicePaymentR.setNoOfDaysAfterGracePeriod(lateFeeDTO.getNoOfDaysAfterGracePeriod());
            servicePaymentR.setWaiveOffLateFee(lateFeeDTO.getWaiveOffLateFee());
            servicePaymentR.setTotalAmount(totalRenewalFee);
            servicePaymentR.setPaymentAmount(totalRenewalFee);
            servicePaymentR.setCreatedBy(loggedInUser.getUserID());
            servicePaymentR.setCreatedOn(loggedInUser.getServerDate());
            consultantRCDao.saveUpdate(servicePaymentR);

            saveServicePaymentDetail(servicePaymentR.getId(),ccRenewal,loggedInUser);
        }

        //save upgrade downgrade change of category
        if(!Objects.equals(totalCCUpDownFee, BigDecimal.ZERO)){
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

            saveServicePaymentDetail(servicePaymentUD.getId(),ccRenewal,loggedInUser);
        }
        ccUpDown.addAll(ccRenewal);
        return ccUpDown;
    }

    @Transactional
    public void saveServicePaymentDetail(String servicePaymentId,List<CategoryClassDTO> classDTOs,LoggedInUser loggedInUser){
        for(CategoryClassDTO classDTO : classDTOs){
            ConsultantServicePaymentDetail spDetail = new ConsultantServicePaymentDetail();
            spDetail.setId(commonService.getRandomGeneratedId());
            spDetail.setServicePaymentId(servicePaymentId);
            spDetail.setAmount(classDTO.getvAmount());
            spDetail.setCategoryId(classDTO.getCategoryId());
           // spDetail.setExistingClassId(classDTO.getApClassId());
            spDetail.setAppliedClassId(classDTO.getApClassId());
        }
    }

    @Transactional
    public void saveAppliedS(String consultantId, String appliedServiceId, LoggedInUser loggedInUser){
        ConsultantAppliedS consultantAppliedS = new ConsultantAppliedS();
        consultantAppliedS.setConsultantId(consultantId);
        consultantAppliedS.setServiceTypeId(appliedServiceId);
        consultantNRService.saveAppliedService(consultantAppliedS,loggedInUser);

        BigDecimal fee;
        //get fee for service
        Object feeObj = commonService.getValue("crpservice", "ConsultantAmount","Id",appliedServiceId);
        fee = (feeObj == null)?BigDecimal.ZERO:new BigDecimal(feeObj.toString());

        ConsultantServicePayment servicePayment = new ConsultantServicePayment();
        servicePayment.setId(commonService.getRandomGeneratedId());
        servicePayment.setConsultantId(consultantId);
        servicePayment.setCmnServiceTypeId(appliedServiceId);
        servicePayment.setTotalAmount(fee);
        servicePayment.setPaymentAmount(fee);
        servicePayment.setCreatedBy(loggedInUser.getUserID());
        servicePayment.setCreatedOn(loggedInUser.getServerDate());
        consultantRCDao.saveUpdate(servicePayment);
    }

    @Transactional
    public void updateIncorporation(List<ConsultantAttachment> cAttachments, LoggedInUser loggedInUser,String consultantId) throws Exception{
        if(cAttachments.size() > 1) {
            for(ConsultantAttachment cAttachment:cAttachments) {
                cAttachment.setConsultantId(consultantId);
                consultantNRService.saveAttachment(cAttachment, loggedInUser);
            }
        }
    }

    public String getOngoingAppStatusMsg(String cdbNo){
        ConsultantDTOFetch ongoingApp = consultantNRService.getConsultantOngoingApp(cdbNo);
        if(ongoingApp == null || ongoingApp.getReferenceNo() == null){
            return  null;
        }
        return "You have ongoing application with reference number: <b>"+ongoingApp.getReferenceNo()+
                "</b> submitted on "+ongoingApp.getApplicationDate()+". This application is <b>"
                +ongoingApp.getAppStatusName()
                +" Please wait until complete process for this application.";
    }
    /**
     * To check for validations for renewal. If any condition is fulfilled or not. and returns the message to be displayed
     * @param cdbNo  -- CBDNo
     * @return Response message
     */
    @Transactional(readOnly = true)
    public ResponseMessage check4Renewal(String cdbNo,Boolean isRenewal){
        responseMessage.reset();
        LateFeeDTO lateFeeDTO = new LateFeeDTO();
        ConsultantFinal cFinal = consultantNRService.getConsultantFinal(cdbNo);
        responseMessage.setId(cFinal.getId());

        //region check for terminated or end of consultant certificate
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

        if(!isRenewal){
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("");
            return responseMessage;
        }

        //region check expiry date and calculate late fee
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
            responseMessage.setText("You are applying for renewal of consultant CDB certificate on time. So, no penalty will be" +
                    "charged. However, there will be renewal fee according to service you applied and your category and classes.");
        }
        //endregion
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setDto(cFinal);
        responseMessage.setDto1(lateFeeDTO);
        responseMessage.setValue(String.valueOf(waiveOffLateFee));
        responseMessage.setVal2(lateFee.toString());
        return responseMessage;
    }


    /**
     * To get the services fee of other services if refNo is null or fee of that refNo
     * @param refNo  - reference No
     * @return list
     */
    @Transactional(readOnly = true)
    public List<ServiceFeeDTO> getServicesFee(Integer refNo) {
        return consultantRCDao.getServicesFee(refNo);
    }

    @Transactional(readOnly = true)
    public List<ConsultantHrDTO> getConsultantHRsFinal(String consultantId,Character ownerOrPartner){
        List<ConsultantHrDTO> consultantHRs = consultantRCDao.getConsultantHRsFinal(consultantId, ownerOrPartner); //B for both owner and hr
        consultantHRs.forEach(h->h.setHrAttachments(consultantRCDao.getHRAttachmentsFinal(h.getId())));
        return  consultantHRs;
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipmentFinal(String consultantId){
        List<EquipmentDTO> equipmentDTOs = consultantRCDao.getEquipmentFinal(consultantId);
        equipmentDTOs.forEach(e->e.setEqAttachments(consultantRCDao.getEQAttachmentsFinal(e.getId())));
        return equipmentDTOs;
    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getCategoryClassFinal(String consultantId) {
        return consultantRCDao.getCategoryClassFinal(consultantId);
    }

    @Transactional(readOnly = true)
    public ConsultantFinal getConsultantFinal(String cdbNo){
        ConsultantFinal consultantFinal =  consultantRCDao.getConsultantFinal(cdbNo);
        if(!emptyNullCheck(consultantFinal.getpDzongkhagId())) {
            consultantFinal.setDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", consultantFinal.getpDzongkhagId()).toString());
        }
        consultantFinal.setRegDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", consultantFinal.getRegDzongkhagId()).toString());
        consultantFinal.setOldDzongkhag(commonService.getValue("cmndzongkhag", "NameEn", "Id", consultantFinal.getRegDzongkhagId()).toString());
        return consultantFinal;
    }


    public String getRegisteredClass(String consultantFinalId,String categoryId){
        String condition = "CrpConsultantFinalId = '"+consultantFinalId+"' AND CmnServiceCategoryId = '"+categoryId+"'";
        String classId = (String)commonService.getValue("crpconsultantworkclassificationfinal", "CmnApprovedServiceId", condition);
        return classId == null?"":classId;
    }

    @Transactional(readOnly = true)
    public String auditMemo(String consultantFinalId){
        return consultantRCDao.auditMemo(consultantFinalId);
    }

    @Transactional
    public void saveServicePaymentDetailOS(String servicePaymentId,List<CategoryClassDTO> classDTOs,LoggedInUser loggedInUser){
        for(CategoryClassDTO classDTO : classDTOs){
            ConsultantServicePaymentDetail spDetail = new ConsultantServicePaymentDetail();
            spDetail.setId(commonService.getRandomGeneratedId());
            spDetail.setServicePaymentId(servicePaymentId);
            spDetail.setAmount(classDTO.getvAmount());
            spDetail.setCategoryId(classDTO.getCategoryId());
            // spDetail.setExistingClassId(classDTO.getApClassId());
            spDetail.setAppliedClassId(classDTO.getApClassId());
        }
    }

    @Transactional(readOnly = true)
    public List<ConsultantAttachment> getIncAttachmentFinal(String consultantIdFinal) {
        return  consultantRCDao.getIncAttachmentFinal(consultantIdFinal);
    }
}
