package com.ngn.spring.project.cdb.specializedFirm.renewal;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.admin.dto.NewDeleteExistDTO;
import com.ngn.spring.project.cdb.admin.specializedFirm.SpecializedFirmActionService;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.common.dto.ServiceFeeDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.cdb.contractor.renewal.LateFeeDTO;
import com.ngn.spring.project.cdb.contractor.renewal.RenewalServiceType;
import com.ngn.spring.project.cdb.specializedFirm.SpecializedFirmDao;
import com.ngn.spring.project.cdb.specializedFirm.SpecializedFirmService;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmDTO;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmDTOFetch;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmHrDTO;
import com.ngn.spring.project.cdb.specializedFirm.model.*;
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
public class SpecializedFirmRService extends BaseService {
    private String UPLOAD_LOC;

    @Autowired
    private SpecializedFirmRDao specializedFirmRDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SpecializedFirmService specializedFirmService;

    @Autowired
    private SpecializedFirmRService specializedFirmRService;

    @Autowired
    SpecializedFirmActionService nrActionService;

    @Autowired
    private SpecializedFirmDao dao;

    public DropdownDTO getSpFirmStatus(String cdbNo){
        return specializedFirmRDao.getSpFirmStatus(cdbNo);
    }

    /**
     * The main save method for spFirm which calls specific save methods
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
        if(responseMessage.getStatus() == UNSUCCESSFUL_STATUS){
            return responseMessage;
        }
        SpecializedFirmFinal specializedFirmFinal = (SpecializedFirmFinal)responseMessage.getDto();

        SpecializedFirm specializedFirm = new SpecializedFirm();
        specializedFirm.setpGewogId(specializedFirmFinal.getpGewogId());
        specializedFirm.setpVillageId(specializedFirmFinal.getpVillageId());
       // specializedFirm.setCrpSpecializedTradeId(specializedFirmFinal.getId());
        BeanUtils.copyProperties(specializedFirmFinal,specializedFirm);

        String specializedFirmId = commonService.getRandomGeneratedId();
        String appliedService;

        specializedFirm.setCrpSpecializedTradeId(specializedFirmId);
        //insert undertaking letter
        if(spFirmDTO.getcAttachments() != null && !spFirmDTO.getcAttachments().isEmpty())
            specializedFirmRService.updateIncorporation(spFirmDTO.getcAttachments(), loggedInUser, specializedFirmId);

        //region incorporation (Name are also allowed to change)
        if(renewalServiceType.getIncorporation() != null){
            String ownershipTypeId = spFirmDTO.getSpecializedFirm().getOwnershipTypeId();
         //  updateIncorporation(spFirmDTO.getcAttachments(), loggedInUser, specializedFirmId);
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
        if(renewalServiceType.getChangeOfFirmName() != null){
            specializedFirm.setFirmName(spFirmDTO.getSpecializedFirm().getFirmName());
            specializedFirm.setNewFirmName(spFirmDTO.getSpecializedFirm().getNewFirmName());
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "10");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region edit in spFirm information
        specializedFirm.setId(specializedFirmId);
        String referenceNo = saveRC(specializedFirm,loggedInUser);

        List<CategoryClassDTO> classDTOs = saveRCFeeCCUpgrade(specializedFirm,spFirmDTO.getCategories(),loggedInUser);
        //endregion

        //region Upgrade or downgrade
        classDTOs.stream().filter(c->c.getCategoryId() != null).forEach(c -> {
            SpFirmCategory conCategory = new SpFirmCategory();
            conCategory.setSpecializedID(specializedFirmId);
            conCategory.setAppliedCategoryId(c.getCategoryId());
            conCategory.setAppliedCategoryId(c.getaClassId());
            specializedFirmService.saveCC(conCategory, loggedInUser);
        });
        //endregion

        //region change of owner or partner
        if(renewalServiceType.getChangeOfOwner() != null){
            List<SpFirmHR> ownerList = spFirmDTO.getSpecializedFirm().getSpFirmHRs();
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

        //region to save owner when both incoporation & ownerchanged is not availed
        if(renewalServiceType.getChangeOfOwner() == null || renewalServiceType.getIncorporation() ==  null){
            List<SpFirmHR> ownerList = spFirmDTO.getSpecializedFirm().getSpFirmHRs();
            specializedFirm.setOwnershipChangeRemarks(spFirmDTO.getSpecializedFirm().getOwnershipChangeRemarks());
            for(SpFirmHR spFirmHR:ownerList){
                String hrId = commonService.getRandomGeneratedId();
                spFirmHR.setId(hrId);
                spFirmHR.setSpecializedID(specializedFirmId);
                spFirmHR.setIsPartnerOrOwner(TRUE_INT);
                specializedFirmService.saveHR(spFirmHR, loggedInUser);
            }
        }
        //endregion

        //region late fee service id
        BigDecimal lateFee = new BigDecimal(responseMessage.getVal2());
        if(lateFee.compareTo(BigDecimal.ZERO) != 0){
            appliedService = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "11");
            appliedServicesList.add(appliedService);
        }
        //endregion

        //region save applied service and payment
        appliedServicesList.stream().filter(c-> !c.isEmpty()).forEach(
                c->saveAppliedS(specializedFirmId,c,loggedInUser)
        );
        //endregion
        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your application for Renewal Of Specialized Firm has been submitted and your application number is "+referenceNo+"<br>" +
                "You will receive an email with the Application summary.<br><br>" +
                "You can track your application using above Application Number. <br>" +
                "Thanks You.");
        return responseMessage;
    }

    private List<CategoryClassDTO> saveRCFeeCCUpgrade(SpecializedFirm specializedFirm, List<SpFirmCategory> categories, LoggedInUser loggedInUser) {
            BigDecimal totalRenewalFee = BigDecimal.ZERO;
            BigDecimal totalCCUpDownFee = BigDecimal.ZERO;
            List<CategoryClassDTO> ccRenewal; //renewal
            final List<CategoryClassDTO> ccUpDown = new ArrayList<>();  //upgrade downgrade
            String specializedFirmFinalId = (String)commonService.getValue("crpspecializedtradefinal","Id","SPNo",specializedFirm.getCdbNo());

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
            classDTO.setvAmount(renewalFee);
            totalRenewalFee = totalRenewalFee.add(renewalFee);
        }

       // for(CategoryClassDTO classDTO : ccUpDown){
            for(int i=0; i<ccUpDown.size();i++){
            if(ccUpDown.get(i).getCategoryId() != null){
                BigDecimal fee = ((FeeStructureDTO) specializedFirmService.gFeeStructure( ccUpDown.get(i).getaClassId()).get(0)).getRegistrationFee();
                ccUpDown.get(i).setvAmount(fee);
                totalCCUpDownFee = totalCCUpDownFee.add(fee);
            }
        }

        //save renewal
            LateFeeDTO lateFeeDTO = (LateFeeDTO)responseMessage.getDto1();
            if(!Objects.equals(totalRenewalFee, BigDecimal.ZERO)){
               String appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "2"); //renewal service id
                SpFirmAppliedS spFirmAppliedS = new SpFirmAppliedS();
                spFirmAppliedS.setSpecializedTradeId(specializedFirm.getId());
                spFirmAppliedS.setServiceTypeId(appliedServiceId);
                specializedFirmService.saveAppliedService(spFirmAppliedS,loggedInUser);

                SpFirmServicePayment servicePaymentR = new SpFirmServicePayment();
                servicePaymentR.setId(commonService.getRandomGeneratedId());
                servicePaymentR.setSpecializedFirmId(specializedFirm.getId());
                servicePaymentR.setCmnServiceTypeId(appliedServiceId); //renewal
                servicePaymentR.setNoOfDaysLate(lateFeeDTO.getNoOfDaysLate());
                servicePaymentR.setNoOfDaysAfterGracePeriod(lateFeeDTO.getNoOfDaysAfterGracePeriod());
                servicePaymentR.setWaiveOffLateFee(lateFeeDTO.getWaiveOffLateFee());
                servicePaymentR.setTotalAmount(totalRenewalFee);
                servicePaymentR.setPaymentAmount(totalRenewalFee);
                servicePaymentR.setCreatedBy(loggedInUser.getUserID());
                servicePaymentR.setCreatedOn(loggedInUser.getServerDate());
                specializedFirmRDao.saveUpdate(servicePaymentR);
                saveServicePaymentDetail(servicePaymentR.getId(),ccRenewal,loggedInUser);
            }

            //save upgrade downgrade change of category
            if(!Objects.equals(totalCCUpDownFee, BigDecimal.ZERO) || Objects.equals(totalCCUpDownFee, BigDecimal.ZERO)){
                String appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "7"); //upgrade downgrade service id
                SpFirmAppliedS spFirmAppliedS = new SpFirmAppliedS();
                spFirmAppliedS.setSpecializedTradeId(specializedFirm.getId());
                spFirmAppliedS.setServiceTypeId(appliedServiceId);
                specializedFirmService.saveAppliedService(spFirmAppliedS,loggedInUser);

                SpFirmServicePayment servicePaymentUD = new SpFirmServicePayment();
                servicePaymentUD.setId(commonService.getRandomGeneratedId());
                servicePaymentUD.setSpecializedFirmId(specializedFirm.getId());
                servicePaymentUD.setCmnServiceTypeId(appliedServiceId); //upgrade downgrade
                servicePaymentUD.setTotalAmount(totalCCUpDownFee);
                servicePaymentUD.setPaymentAmount(totalCCUpDownFee);
                servicePaymentUD.setCreatedBy(loggedInUser.getUserID());
                servicePaymentUD.setCreatedOn(loggedInUser.getServerDate());
                specializedFirmRDao.saveUpdate(servicePaymentUD);

                saveServicePaymentDetail(servicePaymentUD.getId(),ccUpDown,loggedInUser);
            }
        ccUpDown.addAll(ccRenewal);
        return ccRenewal;
    }

    @Transactional
    public void saveServicePaymentDetail(String servicePaymentId,List<CategoryClassDTO> classDTOs,LoggedInUser loggedInUser){
        for(CategoryClassDTO classDTO : classDTOs){
            SpFirmServicePaymentDetail spDetail = new SpFirmServicePaymentDetail();
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
            specializedFirmRDao.saveUpdate(spDetail);
        }
    }

    @Transactional
    public void saveServicePaymentDetailOS(String servicePaymentId,List<CategoryClassDTO> classDTOs,LoggedInUser loggedInUser){
        for(CategoryClassDTO classDTO : classDTOs){
            SpFirmServicePaymentDetail spDetail = new SpFirmServicePaymentDetail();
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
            specializedFirmRDao.saveUpdate(spDetail);
        }
    }
    @Transactional(readOnly = false)
    public void savePayment(SpFirmRegPayment spFirmRegPayment, LoggedInUser loggedInUser) {
        FeeStructureDTO feeDTO = (FeeStructureDTO)dao.gFeeStructure(spFirmRegPayment.getAppliedCategoryId()).get(0);
        String id = commonService.getRandomGeneratedId();
        spFirmRegPayment.setId(id);
        spFirmRegPayment.setAmount(feeDTO.getRenewalFee());
        spFirmRegPayment.setCreatedBy(loggedInUser.getUserID());
        spFirmRegPayment.setCreatedOn(loggedInUser.getServerDate());
        dao.saveUpdate(spFirmRegPayment);
    }

    public String saveRC(SpecializedFirm specializedFirm,LoggedInUser loggedInUser){
        String referenceNo = commonService.getNextID("crpspecializedtrade", "ReferenceNo").toString();
        specializedFirm.setReferenceNo(referenceNo);
        if(specializedFirm.getCrpSpecializedTradeId() == null){
            specializedFirm.setCrpSpecializedTradeId(specializedFirm.getId());
        }
        LateFeeDTO lateFeeDTO = (LateFeeDTO)responseMessage.getDto1();
        specializedFirm.setWaiveOffLateFee(lateFeeDTO.getWaiveOffLateFee()==null?
                BigDecimal.ZERO:lateFeeDTO.getWaiveOffLateFee());
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

    @Transactional
    public void saveAppliedS(String specializedFirmId, String appliedServiceId, LoggedInUser loggedInUser){
        SpFirmAppliedS spFirmAppliedS = new SpFirmAppliedS();
        spFirmAppliedS.setSpecializedTradeId(specializedFirmId);
        spFirmAppliedS.setServiceTypeId(appliedServiceId);
       specializedFirmService.saveAppliedService(spFirmAppliedS,loggedInUser);

        BigDecimal fee;
        //get fee for service
        Object feeObj = commonService.getValue("crpservice", "SpecializedTradeFirstRenewAmount","Id",appliedServiceId);
        fee = (feeObj == null)?BigDecimal.ZERO:new BigDecimal(feeObj.toString());

        SpFirmServicePayment servicePayment = new SpFirmServicePayment();
        servicePayment.setId(commonService.getRandomGeneratedId());
        servicePayment.setSpecializedFirmId(specializedFirmId);
        servicePayment.setCmnServiceTypeId(appliedServiceId);
        servicePayment.setTotalAmount(fee);
        servicePayment.setPaymentAmount(fee);
        servicePayment.setCreatedBy(loggedInUser.getUserID());
        servicePayment.setCreatedOn(loggedInUser.getServerDate());
        specializedFirmRDao.saveUpdate(servicePayment);
    }

    @Transactional
    public void updateIncorporation(List<SpFirmAttachment> cAttachments,LoggedInUser loggedInUser,String specializedFirmId) throws Exception{
        if(cAttachments != null && cAttachments.size() >= 1) {
            for(SpFirmAttachment cAttachment:cAttachments) {
                cAttachment.setSpecializedTradeId(specializedFirmId);
                specializedFirmService.saveAttachment(cAttachment, loggedInUser);
            }
        }
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
        SpecializedFirmFinal cFinal = getSpecializedFirmFinal(cdbNo);
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
            responseMessage.setText("You are applying for renewal of SpecializedFirm CDB certificate on time. So, no penalty will be" +
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

    public String getOngoingAppStatusMsg(String cdbNo){
        SpFirmDTOFetch ongoingApp = specializedFirmService.getSpecializedFirmOngoingApp(cdbNo);
        if(ongoingApp == null || ongoingApp.getReferenceNo() == null){
            return  null;
        }
        return "You have ongoing application with reference number: <b>"+ongoingApp.getReferenceNo()+
                "</b> submitted on "+ongoingApp.getApplicationDate()+". This application is <b>"
                +ongoingApp.getAppStatusName()
                +" Please wait until complete process for this application.";
    }

    @Transactional(readOnly = true)
    public SpecializedFirmFinal getSpecializedFirmFinal(String cdbNo){
        SpecializedFirmFinal specializedFirmFinal = specializedFirmRDao.getSpecializedFirmFinal(cdbNo);
        if(!emptyNullCheck(specializedFirmFinal.getpDzongkhagId())) {
            specializedFirmFinal.setDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", specializedFirmFinal.getpDzongkhagId()).toString());
        }
        specializedFirmFinal.setRegDzongkhagName(commonService.getValue("cmndzongkhag", "NameEn", "Id", specializedFirmFinal.getRegDzongkhagId()).toString());
        specializedFirmFinal.setOldDzongkhag(commonService.getValue("cmndzongkhag", "NameEn", "Id", specializedFirmFinal.getRegDzongkhagId()).toString());
        return specializedFirmFinal;}

    /**
     * To get the services fee of other services if refNo is null or fee of that refNo
     * @param refNo  - reference No
     * @return list
     */
    @Transactional(readOnly = true)
    public List<ServiceFeeDTO> getServicesFee(Integer refNo) {
        return specializedFirmRDao.getServicesFee(refNo);
    }

    @Transactional(readOnly = true)
    public List<SpFirmHrDTO> getSpecializedFirmHRsFinal(String specializedFirmId,Character ownerOrPartner){
        List<SpFirmHrDTO> spFirmHRs = specializedFirmRDao.getSpecializedFirmHRsFinal(specializedFirmId, ownerOrPartner); //B for both owner and hr
        spFirmHRs.forEach(h->h.setHrAttachments(specializedFirmRDao.getHRAttachmentsFinal(h.getId())));
        return  spFirmHRs;
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipmentFinal(String specializedFirmId){
        List<EquipmentDTO> equipmentDTOs = specializedFirmRDao.getEquipmentFinal(specializedFirmId);
        equipmentDTOs.forEach(e->e.setEqAttachments(specializedFirmRDao.getEQAttachmentsFinal(e.getId())));
        return equipmentDTOs;
    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getCategoryClassFinal(String specializedFirmId) {
        return specializedFirmRDao.getCategoryClassFinal(specializedFirmId);
    }

    @Transactional(readOnly = true)
    public String getRegisteredClass(String specializedFirmFinalId,String categoryId){
        String condition = "CrpSpecializedTradeFinalId = '"+specializedFirmFinalId+"' AND CmnAppliedCategoryId = '"+categoryId+"'";
        String classId = (String)commonService.getValue("crpspecializedtradeworkclassificationfinal", "CmnApprovedCategoryId", condition);
        return classId == null?"":classId;
    }

    @Transactional(readOnly = true)
    public String auditMemo(String specializedFirmFinalId){
        return specializedFirmRDao.auditMemo(specializedFirmFinalId);
    }

    @Transactional(readOnly = true)
    public List<SpFirmAttachment> getIncAttachmentFinal(String specializedFirmId) {
        return  specializedFirmRDao.getIncAttachmentFinal(specializedFirmId);
    }

}
