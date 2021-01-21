package com.ngn.spring.project.cdb.contractor.renewal;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.admin.dto.ContractorInfoDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.common.dto.ServiceFeeDTO;
import com.ngn.spring.project.cdb.contractor.registration.ContractorNRService;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorDTOFetch;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.*;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.DropdownDTO;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
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
public class ContractorRCService extends BaseService {
    private String UPLOAD_LOC;

    @Autowired
    private ContractorRCDao contractorRCDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ContractorNRService contractorNRService;

    @Autowired
    private ContractorRCService contractorRCService;


    public DropdownDTO getContractorStatus(String cdbNo){
        return contractorRCDao.getContractorStatus(cdbNo);
    }

    /**
     * The main save method for contractor which calls specific save methods
     * @param contractorDTO   --  ContractorDTO
     * @param loggedInUser    --  Current logged in user
     * @return ResponseMessage  -- response message
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public ResponseMessage save(ContractorDTO contractorDTO,RenewalServiceType renewalServiceType, LoggedInUser loggedInUser) throws Exception {

        if(renewalServiceType == null){
            return responseMessage;
        }
        String cdbNo = loggedInUser.getCdbNo().split("999")[1];
        List<String> appliedServicesList = new ArrayList<>();
        responseMessage = check4Renewal(cdbNo,true);
        if(responseMessage.getStatus() == UNSUCCESSFUL_STATUS){
            return responseMessage;
        }
        ContractorFinal contractorFinal = (ContractorFinal)responseMessage.getDto();

        Contractor contractor = new Contractor();
        contractor.setpGewog(contractorFinal.getpGewogId());
        contractor.setpVillage(contractorFinal.getpVillageId());
        BeanUtils.copyProperties(contractorFinal,contractor);

        String contractorId = commonService.getRandomGeneratedId();
        String appliedService;
        contractor.setContractorId(contractorId);
        //insert undertaking letter
        if(contractorDTO.getcAttachments() != null && !contractorDTO.getcAttachments().isEmpty())
            contractorRCService.updateIncorporation(contractorDTO.getcAttachments(), loggedInUser, contractorFinal.getId());

        //region incorporation (Name are also allowed to change)
        if(renewalServiceType.getIncorporation() != null){
            String ownershipTypeId = contractorDTO.getContractor().getOwnershipTypeId();
          //  updateIncorporation(contractorDTO.getcAttachments(), loggedInUser, contractor.getContractorId());
            contractor.setOwnershipTypeId(ownershipTypeId);
            contractor.setFirmName(contractorDTO.getContractor().getFirmName());

            List<ContractorHR> ownerList = contractorDTO.getContractor().getContractorHRs();
            contractor.setOwnershipChangeRemarks(contractorDTO.getContractor().getOwnershipChangeRemarks());
            for(ContractorHR contractorHR:ownerList){
                String hrId = commonService.getRandomGeneratedId();
                contractorHR.setId(hrId);
                contractorHR.setContractorID(contractorId);
                contractorHR.setIsPartnerOrOwner(TRUE_INT);
                contractorNRService.saveHR(contractorHR, loggedInUser);
            }

            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "12");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region change of location
        if(renewalServiceType.getChangeOfLocation() != null){
            contractor.setEstAddress(contractorDTO.getContractor().getEstAddress());
            contractor.setRegDzongkhagId(contractorDTO.getContractor().getRegDzongkhagId());
            contractor.setRegFaxNo(contractorDTO.getContractor().getRegFaxNo());
            contractor.setRegPhoneNo(contractorDTO.getContractor().getRegPhoneNo());
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "6");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region change of firm name
        if(renewalServiceType.getChangeOfFirmName() != null){
            contractor.setFirmName(contractorDTO.getContractor().getFirmName());
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "10");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region edit in contractor information
        contractor.setId(contractorId);
        String referenceNo = saveRC(contractor,loggedInUser);
        List<CategoryClassDTO> classDTOs = saveRCFeeCCUpgrade(contractor,contractorDTO.getCategories(),loggedInUser);
        //endregion

        //region Upgrade or downgrade
        classDTOs.stream().filter(c->c.getCategoryId() != null).forEach(c -> {
            ConCategory conCategory = new ConCategory();
            conCategory.setContractorID(contractorId);
            conCategory.setProjectCateID(c.getCategoryId());
            conCategory.setAppliedClassID(c.getaClassId());
            contractorNRService.saveCC(conCategory, loggedInUser);
        });
        //endregion

        //region change of owner or partner
        if(renewalServiceType.getChangeOfOwner() != null){
            List<ContractorHR> ownerList = contractorDTO.getContractor().getContractorHRs();

            for(ContractorHR contractorHR:ownerList){
                String hrId = commonService.getRandomGeneratedId();
                contractorHR.setId(hrId);
                contractorHR.setContractorID(contractorId);
                contractorHR.setIsPartnerOrOwner(TRUE_INT);
                contractorNRService.saveHR(contractorHR, loggedInUser);
            }
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "4");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region update HR
        if(renewalServiceType.getUpdateHR() != null){
            List<ContractorHR> hrList = contractorDTO.getContractorHRs();
            for(ContractorHR contractorHR:hrList){
                if(contractorHR.getDeleteRequest() != null && contractorHR.getDeleteRequest() == 1){
                    //to save deleted hr
                    contractorRCDao.saveDeleteHrRequest(contractorHR.getId());
                }else{
                    if(emptyNullCheck(contractorHR.getId())){
                        contractorHR.setId(commonService.getRandomGeneratedId());
                    }
                    //currentHRs.add(contractorHR.getId());
                    if(emptyNullCheck(contractorHR.getCidNo())){
                        continue;
                    }
                    contractorHR.setContractorID(contractorId);
                    contractorHR.setIsPartnerOrOwner(FALSE_INT);
                    contractorNRService.saveHR(contractorHR, loggedInUser);
                    //Save Human resource attachment
                    for (ContractorHRAttachment contractorHRA : contractorHR.getContractorHRAs()) {
                        if(contractorHRA.getAttachment() == null){ //No changes, so no need to save
                            continue;
                        }
                        contractorHRA.setContractorHrId(contractorHR.getId());
                        contractorNRService.saveHRA(contractorHRA, loggedInUser);
                    }
                }
            }
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "8");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region update EQ
        if(renewalServiceType.getUpdateEq() != null) {
            List<ContractorEQ> eqList = contractorDTO.getEquipments();
            for (ContractorEQ contractorEQ : eqList) {
                if (contractorEQ.getDeleteRequest() != null && contractorEQ.getDeleteRequest() == 1) {
                    //to save deleted hr
                    contractorRCDao.saveDeleteEqRequest(contractorEQ.getId());
                }
                if (emptyNullCheck(contractorEQ.getId())) {
                    contractorEQ.setId(commonService.getRandomGeneratedId());
                }
                if (emptyNullCheck(contractorEQ.getEquipmentId())) {
                    continue;
                }
                contractorEQ.setContractorId(contractorId);
                contractorNRService.saveEQ(contractorEQ, loggedInUser);
                //Save Human resource attachment
                for (ContractorEQAttachment contractorEQA : contractorEQ.getContractorEQAs()) {
                    if (contractorEQA.getAttachment() == null) { //No changes, so no need to save
                        continue;
                    }
                    contractorEQA.setEquipmentId(contractorEQ.getId());
                    contractorNRService.saveEQA(contractorEQA, loggedInUser);
                }
            }
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "9");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region to save owner details if both of incoporation & change of owner service is not availed
        if(renewalServiceType.getIncorporation() == null || renewalServiceType.getChangeOfOwner() == null){
            List<ContractorHR> ownerList = contractorDTO.getContractor().getContractorHRs();
            contractor.setOwnershipChangeRemarks(contractorDTO.getContractor().getOwnershipChangeRemarks());
            for(ContractorHR contractorHR:ownerList){
                String hrId = commonService.getRandomGeneratedId();
                contractorHR.setId(hrId);
                contractorHR.setContractorID(contractorId);
                contractorHR.setIsPartnerOrOwner(TRUE_INT);
                contractorNRService.saveHR(contractorHR, loggedInUser);
            }
        }
        //end region

        //region late fee service id
        BigDecimal lateFee = new BigDecimal(responseMessage.getVal2());
        if(lateFee.compareTo(BigDecimal.ZERO)  != 0){
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "11");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region save applied service and payment
        appliedServicesList.stream().filter(c-> !c.isEmpty()).forEach(
                c->saveAppliedS(contractorId,c,loggedInUser)
        );
        //endregion
        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your application for Renewal Of Contractor has been submitted and your application number is "+referenceNo+"<br>" +
                "You will receive an email with the Application summary.<br><br>" +
                "You can track your application using above Application Number. <br>" +
                "Thanks You.");
        return responseMessage;
    }

    @Transactional
    public String saveRC(Contractor contractor,LoggedInUser loggedInUser){
        String referenceNo = commonService.getNextID("crpcontractor", "ReferenceNo").toString();
        contractor.setReferenceNo(referenceNo);
        if(contractor.getContractorId() == null){
            contractor.setContractorId(contractor.getId());
        }
        contractor.setLockedByUserId(null);
        LateFeeDTO lateFeeDTO = (LateFeeDTO)responseMessage.getDto1();
        contractor.setWaiveOffLateFee(lateFeeDTO.getWaiveOffLateFee()==null?
                BigDecimal.ZERO:lateFeeDTO.getWaiveOffLateFee());
        contractor.setAppStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        contractor.setHasNotification("0");
        contractor.setVerifierRemarks(null);
        contractor.setSysVerifierUserId(null);
        contractor.setRegVerifiedDate(null);
        contractor.setApproverRemarks(null);
        contractor.setApproverUserId(null);
        contractor.setRegApprovedDate(null);
        contractor.setFinalApproverUserId(null);
        contractor.setFinalApproverRemarks(null);
        contractor.setPaymentApprovedDate(null);
        contractor.setPaymentReceiptDate(null);
        contractor.setPaymentApproverRemark(null);
        contractor.setPaymentApproverUserId(null);
        contractor.setApplicationDate(loggedInUser.getServerDate());
        contractor.setCreatedBy(loggedInUser.getUserID());
        contractor.setCreatedOn(loggedInUser.getServerDate());
        contractorRCDao.saveOrUpdate(contractor);
        return referenceNo;
    }

    @Transactional
    public List<CategoryClassDTO> saveRCFeeCCUpgrade(Contractor contractor,List<ConCategory> categories, LoggedInUser loggedInUser){
        //since project category is cannot be null
        categories = categories.stream().filter(c-> c.getProjectCateID() != null).collect(Collectors.toList());

        BigDecimal totalRenewalFee = BigDecimal.ZERO;
        BigDecimal totalCCUpDownFee = BigDecimal.ZERO;
        List<CategoryClassDTO> ccRenewal; //renewal
        final List<CategoryClassDTO> ccUpDown = new ArrayList<>();  //upgrade downgrade
        String contractorFinalId = (String)commonService.getValue("crpcontractorfinal","Id","CDBNo",contractor.getCdbNo());

        if(categories != null && !categories.isEmpty()){
            final List<CategoryClassDTO> renewal = new ArrayList<>(); //renewal
            //upgrade or downgrade or change of category
            List<ConCategory> conCategoryR = categories.stream().filter(c-> c.getAppliedClassID() != null).filter(c -> getRegisteredClass(contractorFinalId, c.getProjectCateID()).equals(c.getAppliedClassID())).collect(Collectors.toList());
            conCategoryR.addAll(categories.stream().filter(c -> c.getProjectCateID() != null && c.getAppliedClassID() == null).collect(Collectors.toList()));
            List<ConCategory> conCategoryUD = categories.stream().filter(c -> !conCategoryR.contains(c)).collect(Collectors.toList());

            conCategoryR.stream().forEach(r->{
                String classId = getRegisteredClass(contractorFinalId, r.getProjectCateID());
                renewal.add(new CategoryClassDTO(r.getProjectCateID(),classId,classId));
            });
            conCategoryUD.stream().forEach(r->ccUpDown.add(new CategoryClassDTO(r.getProjectCateID(),r.getAppliedClassID(),getRegisteredClass(contractorFinalId, r.getProjectCateID()))));
            ccRenewal = renewal;
        }else{ // no upgrade or downgrade or change of category
            ccRenewal = getCategoryClassFinal(contractorFinalId);
        }
        for(CategoryClassDTO classDTO : ccRenewal){
            BigDecimal renewalFee = ((FeeStructureDTO) contractorNRService.gFeeStructure(classDTO.getaClassId()).get(0)).getRenewalFee();
            classDTO.setvAmount(renewalFee);
            totalRenewalFee = totalRenewalFee.add(renewalFee);
        }
        for(CategoryClassDTO classDTO : ccUpDown){
            BigDecimal fee = ((FeeStructureDTO) contractorNRService.gFeeStructure(classDTO.getaClassId()).get(0)).getRegistrationFee();
            classDTO.setvAmount(fee);
            totalCCUpDownFee = totalCCUpDownFee.add(fee);
        }

        //save renewal
        LateFeeDTO lateFeeDTO = (LateFeeDTO)responseMessage.getDto1();
        if(!Objects.equals(totalRenewalFee, BigDecimal.ZERO)){
            String appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "2"); //renewal service id
            ContractorAppliedS contractorAppliedS = new ContractorAppliedS();
            contractorAppliedS.setContractorId(contractor.getId());
            contractorAppliedS.setServiceTypeId(appliedServiceId);
            contractorNRService.saveAppliedService(contractorAppliedS, loggedInUser);

            ContractorServicePayment servicePaymentR = new ContractorServicePayment();
            servicePaymentR.setId(commonService.getRandomGeneratedId());
            servicePaymentR.setContractorId(contractor.getId());
            servicePaymentR.setCmnServiceTypeId(appliedServiceId); //renewal
            servicePaymentR.setNoOfDaysLate(lateFeeDTO.getNoOfDaysLate());
            servicePaymentR.setNoOfDaysAfterGracePeriod(lateFeeDTO.getNoOfDaysAfterGracePeriod());
            servicePaymentR.setWaiveOffLateFee(lateFeeDTO.getWaiveOffLateFee());
            servicePaymentR.setTotalAmount(totalRenewalFee);
            servicePaymentR.setPaymentAmount(totalRenewalFee);
            servicePaymentR.setCreatedBy(loggedInUser.getUserID());
            servicePaymentR.setCreatedOn(loggedInUser.getServerDate());
            contractorRCDao.saveUpdate(servicePaymentR);
            saveServicePaymentDetail(servicePaymentR.getId(),ccRenewal,loggedInUser);
        }

        //save upgrade downgrade change of category
        if(!Objects.equals(totalCCUpDownFee, BigDecimal.ZERO)){
            String appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "7"); //upgrade downgrade service id
            ContractorAppliedS contractorAppliedS = new ContractorAppliedS();
            contractorAppliedS.setContractorId(contractor.getId());
            contractorAppliedS.setServiceTypeId(appliedServiceId);
            contractorNRService.saveAppliedService(contractorAppliedS, loggedInUser);

            ContractorServicePayment servicePaymentUD = new ContractorServicePayment();
            servicePaymentUD.setId(commonService.getRandomGeneratedId());
            servicePaymentUD.setContractorId(contractor.getId());
            servicePaymentUD.setCmnServiceTypeId(appliedServiceId); //upgrade downgrade
            servicePaymentUD.setTotalAmount(totalCCUpDownFee);
            servicePaymentUD.setPaymentAmount(totalCCUpDownFee);
            servicePaymentUD.setCreatedBy(loggedInUser.getUserID());
            servicePaymentUD.setCreatedOn(loggedInUser.getServerDate());
            contractorRCDao.saveUpdate(servicePaymentUD);

            saveServicePaymentDetail(servicePaymentUD.getId(),ccUpDown,loggedInUser);
        }
        ccUpDown.addAll(ccRenewal);
        return ccUpDown;
 }

    @Transactional
    public void saveServicePaymentDetail(String servicePaymentId,List<CategoryClassDTO> classDTOs,LoggedInUser loggedInUser){
        for(CategoryClassDTO classDTO : classDTOs){
            ContractorServicePaymentDetail spDetail = new ContractorServicePaymentDetail();
            spDetail.setId(commonService.getRandomGeneratedId());
            spDetail.setServicePaymentId(servicePaymentId);
            spDetail.setAmount(classDTO.getvAmount());
            spDetail.setCategoryId(classDTO.getCategoryId());
            if(classDTO.getExClassId() != null && !classDTO.getExClassId().isEmpty()){
                spDetail.setExistingClassId(classDTO.getExClassId());
            }
            spDetail.setAppliedClassId(classDTO.getaClassId());
            spDetail.setCreatedBy(loggedInUser.getUserID());
            spDetail.setCreatedOn(loggedInUser.getServerDate());
            contractorRCDao.saveUpdate(spDetail);
        }
    }

    @Transactional
    public void saveAppliedS(String contractorId,String appliedServiceId,LoggedInUser loggedInUser){
        ContractorAppliedS contractorAppliedS = new ContractorAppliedS();
        contractorAppliedS.setContractorId(contractorId);
        contractorAppliedS.setServiceTypeId(appliedServiceId);
        contractorNRService.saveAppliedService(contractorAppliedS,loggedInUser);

        BigDecimal fee;
        //get fee for service
        Object feeObj = commonService.getValue("crpservice", "ContractorAmount","Id",appliedServiceId);
        fee = (feeObj == null)?BigDecimal.ZERO:new BigDecimal(feeObj.toString());

        ContractorServicePayment servicePayment = new ContractorServicePayment();
        servicePayment.setId(commonService.getRandomGeneratedId());
        servicePayment.setContractorId(contractorId);
        servicePayment.setCmnServiceTypeId(appliedServiceId);
        servicePayment.setTotalAmount(fee);
        servicePayment.setPaymentAmount(fee);
        servicePayment.setCreatedBy(loggedInUser.getUserID());
        servicePayment.setCreatedOn(loggedInUser.getServerDate());
        contractorRCDao.saveUpdate(servicePayment);
    }

    @Transactional
    public void updateIncorporation(List<ContractorAttachment> cAttachments,
                                    LoggedInUser loggedInUser,String contractorId) throws Exception{
        if(cAttachments != null && cAttachments.size() >= 1) {
                for(ContractorAttachment cAttachment:cAttachments) {
                cAttachment.setContractorId(contractorId);
                contractorNRService.saveAttachment(cAttachment, loggedInUser);
            }
        }
    }

    public String getOngoingAppStatusMsg(String cdbNo){
        ContractorDTOFetch ongoingApp = contractorNRService.getContractorOngoingApp(cdbNo);
        if(ongoingApp == null || ongoingApp.getReferenceNo() == null){
            return  null;
        }
        return "You have ongoing application with reference number: <b>"+ongoingApp.getReferenceNo()+
                "</b> submitted on "+ongoingApp.getApplicationDate()+". This application is <b>"
                +ongoingApp.getAppStatusName()
                +"</b>. Please wait until complete process for this application.";
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
        ContractorFinal cFinal = getContractorFinal(cdbNo);
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
                    "<b> "+ ApplicationStatus.valueOf(cFinal.getAppStatusId()).getName()+"</b>");
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
        } else{
            responseMessage.setText("You are applying for renewal of contractor CDB certificate on time. So, no penalty will be" +
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

    @Transactional(readOnly = true)
    public ContractorFinal getContractorFinal(String cdbNo){
        ContractorFinal contractorFinal =  contractorRCDao.getContractorFinal(cdbNo);
        if(!emptyNullCheck(contractorFinal.getpDzongkhagId())) {
            contractorFinal.setDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", contractorFinal.getpDzongkhagId()).toString());
        }
        contractorFinal.setRegDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", contractorFinal.getRegDzongkhagId()).toString());
        contractorFinal.setOldDzongkhag(commonService.getValue("cmndzongkhag", "NameEn", "Id", contractorFinal.getRegDzongkhagId()).toString());
        return contractorFinal;
    }

    /**
     * To get the services fee of other services if refNo is null or fee of that refNo
     * @param refNo  - reference No
     * @return list
     */
    @Transactional(readOnly = true)
    public List<ServiceFeeDTO> getServicesFee(Integer refNo) {
        return contractorRCDao.getServicesFee(refNo);
    }

    @Transactional(readOnly = true)
    public List<ContractorHrDTO> getContractorHRsFinal(String contractorId,Character ownerOrPartner){
        List<ContractorHrDTO> contractorHRs = contractorRCDao.getContractorHRsFinal(contractorId,ownerOrPartner); //B for both owner and hr
        contractorHRs.forEach(h->h.setHrAttachments(contractorRCDao.getHRAttachmentsFinal(h.getId())));
        return  contractorHRs;
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipmentFinal(String contractorId){
        List<EquipmentDTO> equipmentDTOs = contractorRCDao.getEquipmentFinal(contractorId);
        equipmentDTOs.forEach(e->e.setEqAttachments(contractorRCDao.getEQAttachmentsFinal(e.getId())));
        return equipmentDTOs;
    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getCategoryClassFinal(String contractorId) {
        return contractorRCDao.getCategoryClassFinal(contractorId);
    }

    @Transactional(readOnly = true)
    public String getRegisteredClass(String contractorFinalId,String categoryId){
        String condition = "CrpContractorFinalId = '"+contractorFinalId+"' AND CmnProjectCategoryId = '"+categoryId+"'";
        String classId = (String)commonService.getValue("crpcontractorworkclassificationfinal", "CmnApprovedClassificationId", condition);
        return classId == null?"":classId;
    }

    @Transactional(readOnly = true)
    public String auditMemo(String contractorFinalId){
        return contractorRCDao.auditMemo(contractorFinalId);
    }

    @Transactional(readOnly = true)
    public List<ContractorAttachment> getIncAttachmentFinal(String contractorFinalId) {
        return  contractorRCDao.getIncAttachmentFinal(contractorFinalId);
    }

    @Transactional
    public void saveServicePaymentDetailOS(String servicePaymentId,List<CategoryClassDTO> classDTOs,LoggedInUser loggedInUser){
        for(CategoryClassDTO classDTO : classDTOs){
            ContractorServicePaymentDetail spDetail = new ContractorServicePaymentDetail();
            spDetail.setId(commonService.getRandomGeneratedId());
            spDetail.setServicePaymentId(servicePaymentId);
            spDetail.setAmount(classDTO.getvAmount());
            spDetail.setCategoryId(classDTO.getCategoryId());
            if(classDTO.getExClassId() != null && !classDTO.getExClassId().isEmpty()){
                spDetail.setExistingClassId(classDTO.getExClassId());
            }
            spDetail.setAppliedClassId(classDTO.getaClassId());
            spDetail.setCreatedBy(loggedInUser.getUserID());
            spDetail.setCreatedOn(loggedInUser.getServerDate());
            contractorRCDao.saveUpdate(spDetail);
        }
    }
}
