package com.ngn.spring.project.cdb.trade.service;

import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.common.CommonDao;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.common.dto.PersonalInfoDTO;
import com.ngn.spring.project.cdb.contractor.renewal.LateFeeDTO;
import com.ngn.spring.project.cdb.engineer.dao.EngineerDao;
import com.ngn.spring.project.cdb.engineer.model.Engineer;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirmFinal;
import com.ngn.spring.project.cdb.trade.dao.SpecializedDao;
import com.ngn.spring.project.cdb.trade.dto.OwnershipDTO;
import com.ngn.spring.project.cdb.trade.dto.TradeDto;
import com.ngn.spring.project.cdb.trade.dto.TradeFeesDto;
import com.ngn.spring.project.cdb.trade.entity.*;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 5/5/2020.
 */
@Service
public class SpecializedService extends BaseService{
    private String UPLOAD_LOC;

    @Autowired
    SpecializedDao dao;

    @Autowired
    EngineerDao engineerDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private CommonDao commonDao;

    @Transactional(readOnly = true)
    public List getFeesDetals(String trade) {
        return dao.gFeeStructure(trade);
    }

    @Transactional(readOnly = true)
    public List category() {
        return dao.category();
    }

    @Transactional(readOnly = false)
    public ResponseMessage saveSpecializedTrade(TradeDto dto, LoggedInUser loggedInUser) {
       String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            CrpspecializedtradeEntity specialized = convertDtoToEntity(dto, loggedInUser);
            specialized.setId(generateID);
            specialized.setCrpSpecializedTradeId(generateID);
            specialized.setCmnOwnershipTypeId("Individual");
            dao.save(specialized);

            ServicespecializedtradeEntity specializedtradeserviceEntity = convertToServicespecializedtradeEntity(loggedInUser);
            specializedtradeserviceEntity.setCrpSpecializedTradeId(generateID);
            dao.saveAservies(specializedtradeserviceEntity);
            responseMessage.setStatus(1);
            dto.setReferenceNo(new BigInteger(specialized.getReferenceNo()));
            dto.setCrpSpecializedTradeId(generateID);
            responseMessage.setDto(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }
    private CrpspecializedtradeEntity convertDtoToEntity(TradeDto dto, LoggedInUser loggedInUser){
        CrpspecializedtradeEntity entity=new CrpspecializedtradeEntity();
        entity.setApplicationDate(new Date());
        entity.setInitialDate(new Date());
        entity.setReferenceNo(getMaxId().toString());
        entity.setCIDNo(dto.getCidNo());
        entity.setName(dto.getFullname());
        entity.setCmnSalutationId(dto.getSalutation());
        entity.setCmnDzongkhagId(dto.getDzongkhagId());
        entity.setGewog(dto.getGewog());
        entity.setVillage(dto.getVillage());
        entity.setHasNotification("0");
        entity.setCmnServiceSectorTypeId(dto.getServiceSectorType());
        entity.setCmnCountryId(dto.getCountryId());
        //entity.setTPN(dto.getTrade());//trade field is not there in db
        entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.UNDER_PROCESS.getCode());

        entity.setEmail(dto.getEmail());
        entity.setMobileNo(dto.getMobileNo());
        entity.setTelephoneNo(dto.getTelephoneNo());
        entity.setTPN(dto.getTpn());
        entity.setEmployerName(dto.getEmployeeName());
        entity.setEmployerAddress(dto.getEmployeeAddress());
        entity.setCreatedBy(loggedInUser.getUserID());
        entity.setCreatedOn(loggedInUser.getServerDate());
        return  entity;
    }
    private ServicespecializedtradeEntity convertToServicespecializedtradeEntity(LoggedInUser loggedInUser) {
        String generateID = commonService.getRandomGeneratedId();
        ServicespecializedtradeEntity specializedService = new ServicespecializedtradeEntity();
        specializedService.setId(generateID);
        specializedService.setCmnServiceTypeId("55a922e1-cbbf-11e4-83fb-080027dcfac6");//new registration
        specializedService.setCreatedBy(loggedInUser.getUserID());
        specializedService.setEditedBy(loggedInUser.getUserID());
        specializedService.setCreatedOn(loggedInUser.getServerDate());
        specializedService.setEditedOn(loggedInUser.getServerDate());
        return specializedService;
    }

    @Transactional(readOnly = false)
    private BigInteger getMaxId() {
        BigInteger referenceNo = dao.getMaxId();
        return (referenceNo.intValue() > 0) ? referenceNo.add(BigInteger.ONE) : BigInteger.ONE;
    }

    @Transactional
    public void saveDoc(MultipartFile[] files, String referenceNo, String service,String loggedInUser) {

        try{
            for (int i=0;i<files.length;i++){
                String generateID = commonService.getRandomGeneratedId();
                MultipartFile file= files[i];
                String docPath = commonService.uploadDocument(file,service,file.getOriginalFilename());
                TradeDocument docdet= new TradeDocument();
                docdet.setId(generateID);
                docdet.setCrpSpecializedTradeId(referenceNo);
                docdet.setDocumentName(file.getOriginalFilename());
                docdet.setFileType(file.getContentType());
                docdet.setDocumentPath(docPath);
                docdet.setCreatedBy(loggedInUser);
                docdet.setEditedBy(loggedInUser);
                docdet.setCreatedOn(new Date());
                docdet.setEditedOn(new Date());
                dao.saveAttchment(docdet);
            }
        }
        catch (Exception e){
            System.out.print(e);
        }
    }

    @Transactional(readOnly = true)
    public List getTaskList(String status,String type,String userId,String servicetype) {
        return dao.getTaskList(status, type, userId, servicetype);
    }
    public void assignMyTask(String appNo, String userID, String type) {
        dao.assignMyTask(appNo, userID,type);
    }

    public List<TradeDto> getPrintList() {
        return dao.getPrintList();
    }

    @Transactional
    public TradeDocument getDocumentDetailsByDocId(String uploadDocId) {
        TradeDocument dto=dao.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }

    @Transactional
    public String saveWrkClassification(TradeDto dto, TradeFeesDto tradeDto, String loggedInUser, HttpServletRequest request) {
        String  status="";
        String itemId[]=request.getParameterValues("itemId");
        //String randomSpecializedTradeId = commonService.getRandomGeneratedId();
        String crpspecializedtradeid=dao.getCrpspecializedtradeid(dto);
       // if (!CollectionUtils.isEmpty(dto.getTerms())) {
            for (int i =0; i<itemId.length-1;i++) {
                String randomID = commonService.getRandomGeneratedId();
                SpecializedTradeCategory catEntity = convertToTradeEntity(tradeDto);
                catEntity.setAppliedCategoryId(itemId[i]);
                catEntity.setId(randomID);
                catEntity.setCrpSpecializedTradeId(crpspecializedtradeid);
                catEntity.setCreatedBy(loggedInUser);
                catEntity.setEditedBy(loggedInUser);
                catEntity.setCreatedOn(new Date());
                status = dao.saveWrkClassification(catEntity);
            }
            //status = dao.saveWrkClassification(catEntity);

       /* }else {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        }*/
            return status;
    }

    @Transactional
         private SpecializedTradeCategory convertToTradeEntity(TradeFeesDto tradeDto) {
        SpecializedTradeCategory en = new SpecializedTradeCategory();
        BeanUtils.copyProperties(tradeDto, en);
        return en;
    }
    @Transactional
    public TradeDto fetchdtls(TradeDto appNo) {
        return dao.fetchdtls(appNo);
    }

    @Transactional
    public TradeDto getTradeDetails(String appNo) {
        String specializedTradeNo = "";
        TradeDto dto = dao.getTradeDetails(appNo);
        if (dto.getServiceTypeId().equalsIgnoreCase("New Registration")) {
            //generate cdb nunber
            specializedTradeNo = dao.generatespecializedTradeNo(dto);
            dto.setCdbNo(specializedTradeNo);
        }
    if(dto.getServiceTypeId().equalsIgnoreCase("Cancellation of Registration")) {
        String reasonCancel=dao.getCancellationRemarks(appNo);
        dto.setCancellationRemarks(reasonCancel);
    }else{
        List<TradeFeesDto> terms = dao.getAppliedServiceId(dto.getCrpSpecializedTradeId());
        dto.setTerms(terms);
        if (terms.size() > 0) {
            List<TradeDocument> doc = dao.getdocumentList(dto.getCrpSpecializedTradeId());
            dto.setDoc(doc);
          /*  if (doc.size() > 0) {
                TradeDto updatecrpspecializedTrade = dao.updatecrpspecializedTrade(specializedTradeNo, appNo);
            } else {
                System.out.print("unable to update updatecrpspecializedTrade because doc size is zero.");
            }*/
        } else {
            System.out.print("unable to fetch appliedserviceId.");
        }
    }
        return dto;
        }

    public TradeDto updateVerification(TradeDto dto, String userID, HttpServletRequest request) {
        dto= dao.updateVerification(dto, userID,request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
                //send sms and email notification
            }else{
                dto.setUpdateStatus("Failed to update workflow table for verification. ");}
        } else{
            dto.setUpdateStatus("Failed to update the application table for verification. ");}
        return dto;
    }

    public TradeDto updateReject(TradeDto dto, String userID, HttpServletRequest request) {
        dto= dao.updateReject(dto, userID,request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
                //send sms and email notification
                String mailContent = "Dear User,<br>Your application for application number : " + dto.getReferenceNo() + " is Rejected because of" + dto.getRemarks() + "." +
                        "<br>You may re-apply through following link:<br>" +
                        "<a target='_blank' href='/cdb/public_access/renewal'>Click here for resubmission of an application</a>" ;
                try {
                    MailSender.sendMail(dto.getEmail(), "cdb@gov.bt", null, mailContent, "Application Rejected");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                dto.setUpdateStatus("Failed to update workflow table for rejecting. ");}
        } else{
            dto.setUpdateStatus("Failed to update the application table for rejecting. ");
        }
        return dto;
    }

    public TradeDto approveSpTradeRegistrationSole(TradeDto dto, String userID, HttpServletRequest request, PersonalInfoDTO commonDto) {
        String insert="", stuts="",deletePrevRecord="",deleteFromSysuser="";
      TradeDto dto1=dao.getspcializedTradeapplicationdetails(dto);
        dto1.setServiceSectorType(dto.getServiceSectorType());

        commonDto.setRegMobileNo(dto1.getRegMobileNo());
        commonDto.setFullname(dto1.getFullname());
        commonDto.setRegEmail(dto1.getEmail());
        commonDto.setId(dto.getId());

        if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
            dto1.setServiceTypeId(dto.getServiceTypeId());
            //update cancellation
            dto1.setCdbNo(dto.getCdbNo());
            dto1.setRemarks(dto.getRemarks());

            if(dto1.getUpdateStatus().equalsIgnoreCase("6195664d-c3c5-11e4-af9f-080027dcfac6")) {
                 stuts = dao.updateCancellationDetails(dto1, userID);
                if (stuts.equalsIgnoreCase("Success")) {
                    dto.setUpdateStatus("Success");
                } else {
                    System.out.print("unable to update application for cancellation crpspecializedtrade");
                    dto.setUpdateStatus("fail");
                }
            }else{
                stuts= dao.updateCancellationDetailsFinal(dto1, userID);
                if (stuts.equalsIgnoreCase("Success")) {
                    dto.setUpdateStatus("Success");
                } else {
                    System.out.print("unable to update application for cancellation crpspecializedtradefinal");
                    dto.setUpdateStatus("fail");
                }
            }
            if(stuts.equalsIgnoreCase("success")){
                insert=dao.insertspTradeFinalDetails(dto1, userID, insert.split("/")[0]);
            }
            dto1.setRemarks(dto.getRemarks());
            } else {
                if(dto.getServiceTypeId().equalsIgnoreCase("registration")){
                    dto1.setServiceTypeId(dto.getServiceTypeId());
                    insert=commonService.insertuserDetails(commonDto, userID, request);
                    if(!insert.equalsIgnoreCase("Insert_Fail")){
                        dto1.setCdbNo(dto.getCdbNo());
                        dto1.setRemarks(dto.getRemarks());
                        String password=insert.split("/")[1];
                        insert=dao.insertspTradeFinalDetails(dto1, userID, insert.split("/")[0]);
                        dto.setUpdateStatus(insert);
                    }
                } else if(dto.getServiceTypeId().equalsIgnoreCase("renewal")){
                    dto1.setServiceTypeId(dto.getServiceTypeId());
                    dto1.setCdbNo(dto.getCdbNo());
                    //insert=dao.updateRenewalDetails(dto1, userID, 5);

                    deletePrevRecord = dao.deletePrevRecord(dto1);
                    // if(deletePrevRecord.equalsIgnoreCase("Success")) {
                    deleteFromSysuser = dao.deleteFromSysuser(dto1);
                    insert = commonService.insertuserDetails(commonDto, userID, request);

                    insert=dao.insertspTradeFinalDetails(dto1, userID, insert.split("/")[0]);
                        if(insert.equalsIgnoreCase("Success")){
                            String servicepaymentID = commonService.getRandomGeneratedId();
                       //     dto= dao.specializedtradeservicepayment(dto, userID, request,servicepaymentID);
                            dao.updatePaymentServiceDetails(dto1, userID);
                            if(dto1.getUpdateStatus().equalsIgnoreCase("Success")){
                                dto= dao.approveSpTradeRegistrationSole(dto, userID, request);
                                dto.setUpdateStatus(insert);
                            }
                            //send sms and email notification
                        } else{
                            dto.setUpdateStatus("Failed to update crpspecializedtrade table. ");}
                    }
                  if(insert.equalsIgnoreCase("Success")){
                   dto= dao.approveSpTradeRegistrationSole(dto, userID, request);
                      dto.setUpdateStatus("success");
                //send notification
                      String mailContent = "Dear User,Your application for application number : "+dto1.getReferenceNo()+" is approved."+
                              "You can login to the system for renewal and other services using following credential:" +
                              "Username : your registered email" +
                              "Password : 123" +
                              "Please change your default password after login.";
                      try {
                          MailSender.sendMail(dto1.getEmail(), "cdb@gov.bt", null, mailContent, "Application approved successfully");
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                }
        }
        return dto;
    }

    public TradeDto checkOngoingApplication(String cdbNo) {
        TradeDto dto=dao.checkOngoingApplication(cdbNo);
        return dto;
    }
    @Transactional
    public ResponseMessage saveSpTradeRenenwal(TradeDto dto, String userID) {
        String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            CrpspecializedtradeEntity tradeEntity = convertDtoToEntityForRenewal(dto, userID);
            tradeEntity.setId(generateID);
            tradeEntity.setCrpSpecializedTradeId(generateID);
            dto.setCrpSpecializedTradeId(generateID);
            dto.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
            dao.save(tradeEntity);


            String generateID1= commonService.getRandomGeneratedId();
            ServicespecializedtradeEntity appliedServiceEntity = new ServicespecializedtradeEntity();
            appliedServiceEntity.setId(generateID1);
            appliedServiceEntity.setCmnServiceTypeId("45bc628b-cbbe-11e4-83fb-080027dcfac6");//Renewal of CDB Certificate
            appliedServiceEntity.setCreatedBy(userID);
            appliedServiceEntity.setEditedBy(userID);
            appliedServiceEntity.setCreatedOn(new Date());
            appliedServiceEntity.setEditedOn(new Date());
            appliedServiceEntity.setCrpSpecializedTradeId(generateID);
            dao.saveAservies(appliedServiceEntity);

            BigDecimal payAmount = dto.getPaymentAmt();
            BigDecimal totalAmount;

            if(payAmount.doubleValue() > 3000.00){
                dto.setPaymentAmt(BigDecimal.valueOf(3000.00));
                totalAmount = BigDecimal.valueOf(dto.getPaymentAmt().floatValue() + 1000);
                dto.setTotalAmt(totalAmount);
            }else{
                dto.setPaymentAmt(dto.getPaymentAmt());
                totalAmount = BigDecimal.valueOf(dto.getPaymentAmt().floatValue() + 1000);
                dto.setTotalAmt(totalAmount);
            }

            dao.insertInPaymentServiceDetails(dto, userID);

            responseMessage.setStatus(1);
            dto.setReferenceNo(new BigInteger(tradeEntity.getReferenceNo()));
            dto.setCrpSpecializedTradeId(generateID);
            responseMessage.setDto(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }

    private CrpspecializedtradeEntity convertDtoToEntityForRenewal(TradeDto dto1, String user){
        TradeDto dto=dao.getspTradeDetails(dto1.getCdbNo());
        CrpspecializedtradeEntity entity=new CrpspecializedtradeEntity();
        entity.setApplicationDate(new Date());
        entity.setInitialDate(new Date());
        entity.setReferenceNo(getMaxId().toString());
        entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        entity.setSPNo(dto.getCdbNo());
        entity.setRegistrationExpiryDate(dto.getRegExpDate());
        entity.setCIDNo(dto.getCidNo());
        entity.setName(dto.getFullname());
        entity.setCmnSalutationId(dto.getSalutation());
        entity.setCmnDzongkhagId(dto.getDzongkhagId());
        entity.setGewog(dto.getGewog());
        entity.setVillage(dto.getVillage());
        entity.setTPN(dto.getTpn());
        entity.setHasNotification("0");
        entity.setEmail(dto.getEmail());
        entity.setMobileNo(dto.getMobileNo());
        entity.setTelephoneNo(dto.getTelephoneNo());
        entity.setEmployerName(dto.getEmployeeName());
        entity.setEmployerAddress(dto.getEmployeeAddress());
        entity.setCmnOwnershipTypeId("Individual");
        entity.setCreatedOn(new Date());
        entity.setCreatedBy(user);
        return  entity;
    }

    public ResponseMessage saveSpTradecancellation(TradeDto dto1, String userID) {
        String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            TradeDto dto=dao.getspTradeDetails(dto1.getCdbNo());
            CrpspecializedtradeEntity entity=new CrpspecializedtradeEntity();
            entity.setId(generateID);
            entity.setCrpSpecializedTradeId(generateID);
            entity.setApplicationDate(new Date());
            entity.setInitialDate(new Date());
            entity.setReferenceNo(getMaxId().toString());
            entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.VERIFIED.getCode());
            entity.setSPNo(dto.getCdbNo());
            entity.setRegistrationExpiryDate(dto.getRegExpDate());
            entity.setCIDNo(dto.getCidNo());
            entity.setName(dto.getFullname());
            entity.setCmnSalutationId(dto.getSalutation());
            entity.setCmnDzongkhagId(dto.getDzongkhagId());
            entity.setGewog(dto.getGewog());
            entity.setVillage(dto.getVillage());
            entity.setTPN(dto.getTpn());
            entity.setHasNotification("0");
            entity.setEmail(dto.getEmail());
            entity.setMobileNo(dto.getMobileNo());
            entity.setTelephoneNo(dto.getTelephoneNo());
            entity.setEmployerName(dto.getEmployeeName());
            entity.setEmployerAddress(dto.getEmployeeAddress());
            entity.setCmnOwnershipTypeId("Individual");
            entity.setCreatedOn(new Date());
            entity.setCreatedBy(userID);
            dao.save(entity);

            String generateID1= commonService.getRandomGeneratedId();
            ServicespecializedtradeEntity serviceEntity = new ServicespecializedtradeEntity();
            serviceEntity.setId(generateID1);
            serviceEntity.setCmnServiceTypeId(ApplicationStatus.CANCELLATION.getCode());
            serviceEntity.setCreatedBy(userID);
            serviceEntity.setEditedBy(userID);
            serviceEntity.setCreatedOn(new Date());
            serviceEntity.setEditedOn(new Date());
            serviceEntity.setCrpSpecializedTradeId(generateID);
            dao.saveAservies(serviceEntity);

            engineerDao.updateSysuser(dto.getEmail());

            responseMessage.setStatus(1);
            dto.setReferenceNo(new BigInteger(entity.getReferenceNo()));
            dto.setCrpSpecializedTradeId(generateID);
            responseMessage.setDto(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }

    @Transactional
    public List getModePayment() {
            return commonService.getModePayment();
    }

    @Transactional
    public TradeDto approveAndGenerateCertificate(TradeDto dto, String userID, HttpServletRequest request) {
        dto = dao.updateApplicationForPayment(dto, userID, request);
        String insert = "", deletePrevRecord = "";
        if (dto.getUpdateStatus().equalsIgnoreCase("Success")) {
            TradeDto dto1 = dao.getspcializedTradeapplicationdetails(dto);
            dto1.setRemarks(dto.getRemarks());
            dto1.setPaymentmode(dto.getPaymentmode());
            dto1.setPaymentAmt(dto.getPaymentAmt());
            dto1.setTotalAmt(dto.getTotalAmt());
            dto1.setNoOfDaysAfterGracePeriod(dto.getNoOfDaysAfterGracePeriod());
            dto1.setNoOfDaysLate(dto.getNoOfDaysLate());

            if (dto.getServiceTypeId().equalsIgnoreCase("renewal")) {
                dto1.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
                dto1.setCdbNo(dto.getCdbNo());
                deletePrevRecord = dao.deletePrevRecord(dto1);
                insert = dao.insertspTradeFinalDetails(dto1, userID, insert.split("/")[0]);
                if (insert.equalsIgnoreCase("success")) {
                    String paymentSuccess = dao.updatePaymentServiceDetails(dto1, userID);
                    if (paymentSuccess.equalsIgnoreCase("success")) {
                        insert = dao.updateRenewalDetails(dto1, userID, 3);
                    } else {
                        dto.setUpdateStatus("fail");
                    }
                } else {
                    dto.setUpdateStatus("fail");
                }
            }
            if (insert.equalsIgnoreCase("Success")) {
                dto.setUpdateStatus("Success");
                //send notification
            }
        }
            return dto;

    }

    @Transactional
    public String saveWrkClassificationrenewal(TradeDto dto, TradeFeesDto tradeDto, String loggedInUser, HttpServletRequest request) {
        String  status="";
        String itemId[]=request.getParameterValues("itemId");
        //String randomSpecializedTradeId = commonService.getRandomGeneratedId();
        String crpspecializedtradeid=dao.getCrpspecializedtradeid(dto);
        // if (!CollectionUtils.isEmpty(dto.getTerms())) {
        for (int i =0; i<itemId.length;i++) {
            String randomID = commonService.getRandomGeneratedId();
            SpecializedTradeCategory catEntity = convertToTradeEntity(tradeDto);
            catEntity.setAppliedCategoryId(itemId[i]);
            catEntity.setId(randomID);
            catEntity.setCrpSpecializedTradeId(crpspecializedtradeid);
            catEntity.setCreatedBy(loggedInUser);
            catEntity.setEditedBy(loggedInUser);
            catEntity.setCreatedOn(new Date());
            status = dao.saveWrkClassification(catEntity);
        }
        //status = dao.saveWrkClassification(catEntity);

       /* }else {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        }*/
        return status;
    }
    public String getOwnershipType(String appNo) {
        return commonService.getOwnershipType(appNo);
    }

    @Transactional
    public TradeDto updateApproval(TradeDto dto, String userID, HttpServletRequest request) {
        dto= dao.updateApproval(dto, userID, request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
                //send sms and email notification
                String mailContent = "Dear User,<br>Your application for application number : "+dto.getReferenceNo()+" is approved."+
                        "<br>You may pay the required fee online through following link:<br>"  +
                        "<a target='_blank' href='https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg'>https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg</a>" +
                        "<br>Or You may visit our CDB counters to pay the fee." +
                        "<br><br>Note: Only after payment confirmation, your application will be done final approval. And you will get the login credential to log into system.";
                try {
                    MailSender.sendMail(dto.getEmail(), "cdb@gov.bt", null, mailContent, "Application approved for payment");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                dto.setUpdateStatus("Failed to update workflow table for updateApproval. ");}
        } else{
            dto.setUpdateStatus("Failed to update the application table for updateApproval. ");}
        return dto;
    }

    public String checkOwnerShipType(String cdbNo) {
        return dao.checkOwnerShipType(cdbNo);
    }

  @Transactional
    public ResponseMessage updateApplicationCancellation(TradeDto dto, UserSessionDetailDTO user, LoggedInUser loggedInUser, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        String updateCrpspecializedtrade = dao.updateApplicationCancellationspecializedtrade(dto, user, loggedInUser);
        if (updateCrpspecializedtrade.equalsIgnoreCase("success")) {
            String updatecrpspecializedtradeappliedservice = dao.updatecrpspecializedtradeappliedserviceCancellation(dto, user);
            responseMessage.setStatus(1);
        } else {
            responseMessage.setStatus(0);
        }
        return responseMessage;
    }

    @Transactional
    public ResponseMessage isMailUnique(HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        String isMailUnique=dao.isMailUnique(request);
        if(isMailUnique.isEmpty()){
            responseMessage.setStatus(0);
        }else{
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public ResponseMessage check4Renewal(String cdbNo, Boolean isRenewal) {
        responseMessage.reset();
        LateFeeDTO lateFeeDTO = new LateFeeDTO();
        SpecializedFirmFinal cFinal = getSpecializedTradeFinal(cdbNo);
        responseMessage.setId(cFinal.getId());

        //region check for terminated or end of surveyor certificate
        List<String> statusList = new ArrayList<>();
        statusList.add(ApplicationStatus.BLACKLISTED.getCode());
        statusList.add(ApplicationStatus.DEBARRED.getCode());
        statusList.add(ApplicationStatus.DEREGISTERED.getCode());
        statusList.add(ApplicationStatus.REJECTED.getCode());
        statusList.add(ApplicationStatus.REVOKED.getCode());
        statusList.add(ApplicationStatus.SUSPENDED.getCode());
        statusList.add(ApplicationStatus.SURRENDERED.getCode());
        statusList.add(ApplicationStatus.CANCELLATION.getCode());
        if (statusList.contains(cFinal.getAppStatusId())) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("You are not allow to avail this service as your certificate is " +
                    "<b> " + ApplicationStatus.valueOf(cFinal.getAppStatusId()).getName() + "</b>");
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
                responseMessage.setText("You are applying for renewal of Specialized Trade CDB certificate on time. So, no penalty will be" +
                    "charged. However, there will be renewal fee.");
        }
        //endregion
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setDto(cFinal);
        responseMessage.setDto1(lateFeeDTO);
        responseMessage.setValue(String.valueOf(waiveOffLateFee));
        responseMessage.setVal2(lateFee.toString());
        return responseMessage;

    }

    private String getOngoingAppStatusMsg(String cdbNo) {
        ArchitectDto ongoingApp = dao.getSpecializedtradeOngoingApp(cdbNo);
        if(ongoingApp == null || ongoingApp.getReferenceNo() == null){
            return  null;
        }
        return "You have ongoing application with reference number: <b>"+ongoingApp.getReferenceNo()+
                "</b> submitted on "+ongoingApp.getApplicationDate()+". This application is <b>"
                +ongoingApp.getUpdateStatus()
                +"</b>. Please wait until complete process for this application.";
    }

    @Transactional(readOnly = true)
    private SpecializedFirmFinal getSpecializedTradeFinal(String cdbNo) {
        return dao.getSpecializedTradeFinal(cdbNo);
    }
}