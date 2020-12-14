package com.ngn.spring.project.cdb.engineer.service;

import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.certification.CertificateDTO;
import com.ngn.spring.project.cdb.common.CommonDao;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.renewal.LateFeeDTO;
import com.ngn.spring.project.cdb.engineer.dao.EngineerDao;
import com.ngn.spring.project.cdb.engineer.model.CrpengineerFinalEntity;
import com.ngn.spring.project.cdb.engineer.model.Engineer;
import com.ngn.spring.project.cdb.engineer.model.EngineerAppliedServiceEntity;
import com.ngn.spring.project.cdb.engineer.model.EngineerAttachment;
import com.ngn.spring.project.cdb.survey.dao.SurveyDao;
import com.ngn.spring.project.cdb.survey.entity.CrpsurveyEntity;
import com.ngn.spring.project.cdb.survey.entity.SurveyDocument;
import com.ngn.spring.project.cdb.survey.entity.SurveyServiceEntity;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.ResponseMessage;
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
 * Created by USER on 3/19/2020.
 */
@Service
public class EngineerServices extends BaseService{
    @Autowired
    private EngineerDao dao;
    @Autowired
    private CommonService commonService;

    @Autowired
    private CommonDao commonDao;

    @Transactional(readOnly = true)
    public List getFeesDetals(String Engineer) {
        return dao.gFeeStructure(Engineer);
    }
    @Transactional(readOnly = false)
    public ResponseMessage saveEngineer(ArchitectDto dto, UserSessionDetailDTO user) {
        String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            Engineer engineer = convertDtoToEntity(dto, user);
            engineer.setId(generateID);
            engineer.setEngineerId(generateID);
            dao.save(engineer);

            EngineerAppliedServiceEntity serviceEntity = convertEngineerToServiceEntity(user);
            serviceEntity.setEngineerId(generateID);
            dao.saveSservies(serviceEntity);
            responseMessage.setStatus(1);
            dto.setReferenceNo(engineer.getReferenceNo());
            dto.setCrpEngineerId(generateID);
            responseMessage.setDto(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }
    private Engineer convertDtoToEntity(ArchitectDto dto, UserSessionDetailDTO user){
        Engineer entity=new Engineer();
        entity.setApplicationDate(new Date());
        entity.setInitialDate(new Date());
        entity.setReferenceNo(getMaxId());
        entity.setCID(dto.getCidNo());
        entity.setName(dto.getFullname());
        entity.setSalutationId(dto.getSalutation());
        entity.setDzongkhagId(dto.getDzongkhagId());
        entity.setGewog(dto.getGewog());
        entity.setVillage(dto.getVillage());
        entity.setCmnServiceSectorTypeId(dto.getServiceSectorType());
        entity.setCountryId(dto.getCountryId());
        entity.setTradeId(dto.getTrade());
        entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.UNDER_PROCESS.getCode());

        entity.setEmail(dto.getEmail());
        entity.setMobileNo(dto.getMobileNo());
        entity.setEmployerName(dto.getEmployeeName());
        entity.setEmployerAddress(dto.getEmployeeAddress());
        entity.setQualification(dto.getQualificationId());
        entity.setGraduationYear(dto.getGraduationYear());
        entity.setUniversityCountry(dto.getUniversityCountry());
        entity.setUniversityName(dto.getUniversityName());
        entity.setWaiveOffLateFee(0);
        if(user==null){
            entity.setCreatedBy("CITIZEN");
        }
        else{
            entity.setCreatedBy(user.getCid());
        }
        entity.setCreatedOn(new Date());
        return  entity;
    }

    private EngineerAppliedServiceEntity convertEngineerToServiceEntity(UserSessionDetailDTO user) {
        String generateID = commonService.getRandomGeneratedId();
        EngineerAppliedServiceEntity engineerAppliedServiceEntity = new EngineerAppliedServiceEntity();
        engineerAppliedServiceEntity.setId(generateID);
        engineerAppliedServiceEntity.setCmnServiceTypeId("55a922e1-cbbf-11e4-83fb-080027dcfac6");//new registration
        engineerAppliedServiceEntity.setCreatedBy(null);
        if(user==null){
            engineerAppliedServiceEntity.setCreatedBy("CITIZEN");
        }
        else{
            engineerAppliedServiceEntity.setCreatedBy(user.getCid());
        }
        engineerAppliedServiceEntity.setCreatedOn(new Date());
        engineerAppliedServiceEntity.setEditedOn(new Date());
        return engineerAppliedServiceEntity;
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
                EngineerAttachment docdet= new EngineerAttachment();
                docdet.setId(generateID);
                docdet.setEngineerId(referenceNo);
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
    public List getTaskList(String status,String type,String userId,String servicetype){
        return dao.getTaskList(status,type,userId,servicetype);
    }

    @Transactional
    public List getModePayment() {
        return commonService.getModePayment();
    }

    public String assignMyTask(String appNo, String userID, String type) {
        String assignMyTask = dao.assignMyTask(appNo, userID,type);
        return assignMyTask;
    }

    public ArchitectDto getEngineerDetails(String appNo) {
        ArchitectDto dto=dao.getEngineerDetails(appNo);
        if(dto.getServiceTypeId().equalsIgnoreCase("New Registration") ){
            if(dto.getServiceSectorType().equalsIgnoreCase("Government") || dto.getServiceSectorType().equalsIgnoreCase("Private") && dto.getUpdateStatus().equalsIgnoreCase("6195664d-c3c5-11e4-af9f-080027dcfac6")){
                //generate cdb nunber
               // String engineerNo=dao.generateEngineerNo(dto.getCountryId(), dto.getServiceSectorType());
                dto.setCdbNo("NULL");
            }
           /* else if(dto.getUpdateStatus().equalsIgnoreCase("6195664d-c3c5-11e4-af9f-080027dcfac6")){
                String engineerNo=dao.generateEngineerNo(dto.getCountryId(), dto.getServiceSectorType());
                dto.setCdbNo(engineerNo);
            }*/
        }
        List<EngineerAttachment> engineerAttachments =dao.getdocumentList(dto.getCrpEngineerId());
        dto.setEngineerAttachments(engineerAttachments);
       return dto;
    }

    public ArchitectDto updateReject(ArchitectDto dto, String userID, HttpServletRequest request) {
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
                dto.setUpdateStatus("Failed to update workflow table for rejecting. ");
            }
        }
        else {
            dto.setUpdateStatus("Failed to update the application table for rejecting. ");
        }
        return dto;
    }

    public ArchitectDto updateVerification(ArchitectDto dto, String userID, HttpServletRequest request) {
        dto= dao.updateVerification(dto, userID,request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
                //send sms and email notification
            }
            else{
                dto.setUpdateStatus("Failed to update workflow table for verification. ");
            }
        }
        else{
            dto.setUpdateStatus("Failed to update the application table for verification. ");
        }
        return dto;
    }

    public ArchitectDto approveEngineerRegistration(ArchitectDto dto, String userID, HttpServletRequest request) {
        dto= dao.updateApproval(dto, userID,request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
                 if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
                    dto=dao.updateFinalTable(dto,userID,request);
                     if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
                         dao.updateSysuser(dto.getEmail());
                         String mailContent = "Dear User,<br>Your application for Cancellation of CDB certificate is cancelled with application number : "+dto.getReferenceNo();
                         try {
                             MailSender.sendMail(dto.getEmail(),"cdb@gov.bt",null,mailContent,"Application Cancelled");
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                }else {
                     //send sms and email notification
                     String mailContent = "Dear User,<br>Your application for application number : " + dto.getReferenceNo() + " is approved." +
                             "<br>You may pay the required fee online through following link:<br>" +
                             "<a target='_blank' href='https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg'>https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg</a>" +
                             "<br>Or You may visit our CDB counters to pay the fee. " +
                             "<br><br>Note: Only after payment confirmation, your application will be done final approval. And you will get the login credential to log into system. ";
                     try {
                         MailSender.sendMail(dto.getEmail(), "cdb@gov.bt", null, mailContent, "Application Payment approved");
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }

            }
            else{
                dto.setUpdateStatus("Failed to update workflow table for verification. ");
            }
        return dto;
    }

    @Transactional
    public ArchitectDto approveAndGenerateCertificate(ArchitectDto dto, String userID, HttpServletRequest request) {
        String insert="",deletePrevRecord="",deleteFromSysuser="";
       // if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            ArchitectDto dto1=dao.getengineerregDetails(dto);

        dto1.setServiceSectorType(dto.getServiceSectorType());
        dto1.setServiceTypeId(dto.getServiceTypeId());
        dto1.setServiceSectorTypeId(dto.getServiceSectorTypeId());
        dto1.setCdbNo(dto.getCdbNo());
        dto1.setTrade(dto.getTrade());

            dto1.setRemarks(dto.getRemarks());
            dto1.setPaymentmode(dto.getPaymentmode());
            dto1.setPaymentAmt(dto.getPaymentAmt());
            dto1.setTotalAmt(dto.getTotalAmt());
            dto1.setNoOfDaysAfterGracePeriod(dto.getNoOfDaysAfterGracePeriod());
            dto1.setNoOfDaysLate(dto.getNoOfDaysLate());
      //  if(dto.getServiceSectorType().equalsIgnoreCase("Goverment")) {
            if (dto.getServiceTypeId().equalsIgnoreCase("registration")) {
                insert = dao.insertuserDetails(dto1, userID, request);
                String engineerNo=dao.generateEngineerNo(dto1.getCountryId(), dto.getServiceSectorType());
                dto1.setCdbNo(engineerNo);
                dto.setCdbNo(engineerNo);
                if (!insert.equalsIgnoreCase("Insert_Fail")) {
                    dto1.setCdbNo(dto.getCdbNo());
                    String password = insert.split("/")[1];
                    insert = dao.insertEngineerFinalDetails(dto1, userID, insert.split("/")[0]);
                    if (insert.equalsIgnoreCase("Success")) {
                        insert = dao.insertInPaymentDetails(dto, userID, request);
                    }
                }
            } else if (dto.getServiceTypeId().equalsIgnoreCase("renewal")) {
               // dto1.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
                dto1.setCdbNo(dto.getCdbNo());
               deletePrevRecord = dao.deletePrevRecord(dto1);
               // if(deletePrevRecord.equalsIgnoreCase("Success")) {
                    deleteFromSysuser = commonDao.deleteFromSysuser(dto1);
                insert = dao.insertuserDetails(dto1, userID, request);
                    insert = dao.insertEngineerFinalDetails(dto1, userID, insert.split("/")[0]);
                    if (insert.equalsIgnoreCase("Success")) {
                        String update = dao.updateRenewalDetails(dto1, userID, 2);
                        if (update.equalsIgnoreCase("Success")) {
                            dao.updatePaymentServiceDetails(dto1, userID);
                        } else {
                            System.out.print("unable to updatePaymentServiceDetails");
                        }
                    } else {
                        System.out.print("unable to update crpengineerfinal");
                    }
               /* } else {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                }*/
            }
        //}
            if(insert.equalsIgnoreCase("Success")){
                dto= dao.updateApplicationForPayment(dto, userID, request);
                dto.setUpdateStatus("Success");
                //send notification
                String mailContent = "Dear User,Your application for application number : "+ dto.getReferenceNo()+" is approved."+
                        "You can login to the system for renewal other services using following credential:" +
                        "Username : your registered email" +
                        "Password : 123" +
                        "Please change your default password after login.";
                try {
                    MailSender.sendMail(dto.getEmail(),"cdb@gov.bt",null,mailContent,"Application Payment approved");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print("not able to send notification for this email"+dto.getEmail()+ "with reason:"+e);
                }
           // }
        }
        return dto;
    }

    public List<ArchitectDto> getPrintList() {
        return dao.getPrintList();
    }

    public ArchitectDto checkOngoingApplication(String cdbNo) {
        ArchitectDto dto=dao.checkOngoingApplication(cdbNo);
        return dto;
    }

    @Transactional
    public ResponseMessage saveEngineerRenenwal(ArchitectDto dto, String userID) {
        String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            Engineer engineer = convertDtoToEntityForRenewal(dto, userID);
            engineer.setId(generateID);
            engineer.setEngineerId(generateID);
            dto.setCrpEngineerId(generateID);
            dto.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
            if(engineer.getGraduationYear()!=null) {
                dao.save(engineer);
            } else {
                responseMessage.setStatus(0);
                return responseMessage;
            }
            String generateID1= commonService.getRandomGeneratedId();
            EngineerAppliedServiceEntity engineerAppliedServiceEntity = new EngineerAppliedServiceEntity();
            engineerAppliedServiceEntity.setId(generateID1);
            engineerAppliedServiceEntity.setCmnServiceTypeId("45bc628b-cbbe-11e4-83fb-080027dcfac6");//Renewal of CDB Certificate
            engineerAppliedServiceEntity.setCreatedBy(userID);
            engineerAppliedServiceEntity.setEditedBy(userID);
            engineerAppliedServiceEntity.setCreatedOn(new Date());
            engineerAppliedServiceEntity.setEditedOn(new Date());
            engineerAppliedServiceEntity.setEngineerId(generateID);
            dao.saveSservies(engineerAppliedServiceEntity);

            BigDecimal payAmount = dto.getPaymentAmt();
            if(payAmount == null || dto.getServiceSectorType().equalsIgnoreCase("6e1cd096-bea8-11e4-9757-080027dcfac6")){
                payAmount = BigDecimal.valueOf(00.00);
            }
            BigDecimal totalAmount;

            if(payAmount.doubleValue() > 3000.00){
                dto.setPaymentAmt(BigDecimal.valueOf(3000.00));
                 totalAmount = BigDecimal.valueOf(payAmount.floatValue() + 1000);
                dto.setTotalAmt(totalAmount);
            }else{
                dto.setPaymentAmt(dto.getPaymentAmt());
                if(dto.getServiceSectorType().equalsIgnoreCase("6e1cd096-bea8-11e4-9757-080027dcfac6")){
                    totalAmount = BigDecimal.valueOf(payAmount.floatValue());
                }else{
                    totalAmount = BigDecimal.valueOf(payAmount.floatValue() + 1000);
                }
                dto.setTotalAmt(totalAmount);
            }
            dao.insertInPaymentServiceDetails(dto, userID);

            responseMessage.setStatus(1);
            dto.setReferenceNo(engineer.getReferenceNo());
            dto.setCrpEngineerId(generateID);
            responseMessage.setDto(dto);
        } catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }

    private Engineer convertDtoToEntityForRenewal(ArchitectDto dto, String user){
      //  ArchitectDto dto=dao.getengineerDetails(dto1.getCdbNo());
        Engineer entity=new Engineer();
        entity.setApplicationDate(new Date());
        entity.setInitialDate(new Date());
        entity.setReferenceNo(getMaxId());
        entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        entity.setCdbNo(dto.getCdbNo());
        entity.setRegistrationExpiryDate(dto.getRegExpDate());
        entity.setCID(dto.getCidNo());
        entity.setName(dto.getFullname());
        entity.setSalutationId(dto.getSalutation());
        entity.setDzongkhagId(dto.getDzongkhagId());
        entity.setGewog(dto.getGewog());
        entity.setVillage(dto.getVillage());
        entity.setEngineerId(dto.getCrpEngineerId());
        entity.setCmnServiceSectorTypeId(dto.getServiceSectorType());
        entity.setCountryId(dto.getCountryId());
        entity.setEmail(dto.getEmail());
        entity.setTradeId(dto.getTrade());
        entity.setMobileNo(dto.getMobileNo());
        entity.setEmployerName(dto.getEmployeeName());
        entity.setEmployerAddress(dto.getEmployeeAddress());
        entity.setQualification(dto.getQualificationId());
        entity.setGraduationYear(dto.getGraduationYear());
        entity.setUniversityCountry(dto.getUniversityCountry());
        entity.setUniversityName(dto.getUniversityName());
        entity.setWaiveOffLateFee(0);
        entity.setCreatedOn(new Date());
        entity.setCreatedBy(user);
        return  entity;
    }

    @Transactional
    public EngineerAttachment getDocumentDetailsByDocId(String uploadDocId) {
        EngineerAttachment dto=dao.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }

    @Transactional
    public ResponseMessage saveEngineercancellation(ArchitectDto dto1, String userID) {

        ResponseMessage responseMessage = new ResponseMessage();
        try {
            ArchitectDto dto=dao.getengineerDetails(dto1.getCdbNo());

            Engineer entity=new Engineer();

            String generateID = commonService.getRandomGeneratedId();
            entity.setId(generateID);
            entity.setEngineerId(generateID);
            entity.setApplicationDate(new Date());
            entity.setCancellationReason(dto1.getCancellationRemarks());
            entity.setInitialDate(new Date());
            entity.setReferenceNo(getMaxId());
            entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.VERIFIED.getCode());
            entity.setCdbNo(dto.getCdbNo());
            entity.setRegistrationExpiryDate(dto.getRegExpDate());
            entity.setCID(dto.getCidNo());
            entity.setName(dto.getFullname());
            entity.setSalutationId(dto.getSalutation());
            entity.setDzongkhagId(dto.getDzongkhagId());
            entity.setGewog(dto.getGewog());
            entity.setVillage(dto.getVillage());
            entity.setCmnServiceSectorTypeId(dto.getServiceSectorType());
            entity.setCountryId(dto.getCountryId());
            entity.setEmail(dto.getEmail());
            entity.setTradeId(dto1.getTrade());
            entity.setMobileNo(dto.getMobileNo());
            entity.setEmployerName(dto.getEmployeeName());
            entity.setEmployerAddress(dto.getEmployeeAddress());
            entity.setQualification(dto.getQualificationId());
            entity.setGraduationYear(dto1.getGraduationYear());
            entity.setUniversityCountry(dto.getUniversityCountry());
            entity.setUniversityName(dto.getUniversityName());
            entity.setWaiveOffLateFee(0);
            entity.setCreatedOn(new Date());
            entity.setCreatedBy(userID);
            dao.save(entity);

            String generateID1= commonService.getRandomGeneratedId();
            EngineerAppliedServiceEntity engineerAppliedServiceEntity = new EngineerAppliedServiceEntity();
            engineerAppliedServiceEntity.setId(generateID1);
            engineerAppliedServiceEntity.setCmnServiceTypeId(ApplicationStatus.CANCELLATION.getCode());//Renewal of CDB Certificate
            engineerAppliedServiceEntity.setCreatedBy(userID);
            engineerAppliedServiceEntity.setEditedBy(userID);
            engineerAppliedServiceEntity.setCreatedOn(new Date());
            engineerAppliedServiceEntity.setEditedOn(new Date());
            engineerAppliedServiceEntity.setEngineerId(generateID);
            dao.saveSservies(engineerAppliedServiceEntity);

            responseMessage.setStatus(1);
            dto.setReferenceNo(entity.getReferenceNo());
            dto.setCrpEngineerId(generateID);
            responseMessage.setDto(dto);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }

    public ArchitectDto getDetails(String appNo) {
        ArchitectDto dto=dao.getDetails(appNo);
        List<EngineerAttachment> engineerAttachments =dao.getdocumentList(dto.getCrpSurveyId());
        dto.setEngineerAttachments(engineerAttachments);
        return dto;
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

    /**
     *
     * @param cdbNo
     * @param isRenewal
     * @return
     */

    @Transactional(readOnly = true)
    public ResponseMessage check4Renewal(String cdbNo, Boolean isRenewal) {
        responseMessage.reset();
        LateFeeDTO lateFeeDTO = new LateFeeDTO();
        CrpengineerFinalEntity cFinal = getEngineerFinal(cdbNo);
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
        if (statusList.contains(cFinal.getCmnApplicationRegistrationStatusId())) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("You are not allowed to avail this service as your certificate is " +
                    "<b> " + ApplicationStatus.valueOf(cFinal.getCmnApplicationRegistrationStatusId()).getName() + "</b>");
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
        LocalDate expiryDate = LocalDate.parse(sdf.format(cFinal.getRegistrationExpiryDate()));
        LocalDate gracePeriodDate = expiryDate.plusDays(30);
        LocalDate renewalLinkActiveDate = expiryDate.minusDays(30);

        if(!curDate.isAfter(renewalLinkActiveDate)){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Your certificate expires on " +sdf.format(cFinal.getRegistrationExpiryDate())+"."+
                    "The renewal link will open only before 30 days from expiry date.");
            return responseMessage;
        }
        long waiveOffLateFee = 0;
        BigDecimal lateFee = BigDecimal.ZERO;
        if(curDate.isAfter(gracePeriodDate)){
            Long noOfLateDays = ChronoUnit.DAYS.between(gracePeriodDate, curDate)-1;
            Long  acNoOfLateDays = ChronoUnit.DAYS.between(expiryDate, curDate)-1;
            lateFee = new BigDecimal((noOfLateDays*100));
            if(lateFee.doubleValue()>3000){
                lateFee= BigDecimal.valueOf(3000);
            }
            if(cFinal.getCmnServiceSectorTypeId().equalsIgnoreCase("6e1cd096-bea8-11e4-9757-080027dcfac6")){
                responseMessage.setText("Seems like your registration is already expired on <b>"+expiryDate+
                        "</b>. The total number of days late is <b>"+acNoOfLateDays+"</b> days." +
                        " However 30 days is considered as grace period which means the late fees that would be imposed within that period will be waived. Penalty amount is Nu. 100 per day.<br>" +
                        "Total number of days after grace period is <b>"+noOfLateDays+"</b>. <h4>Since you are government employee renewal and late fees will not be imposed.</h4>");
            }else{
                responseMessage.setText("Seems like your registration is already expired on <b>"+expiryDate+
                        "</b>. The total number of days late is <b>"+acNoOfLateDays+"</b> days." +
                        " However 30 days is considered as grace period which means the late fees that would be imposed within that period will be waived. Penalty amount is Nu. 100 per day.<br>" +
                        "Total number of days after grace period is <b>"+noOfLateDays+"</b>. Total of Nu. "+lateFee+" will be imposed penalty for late renewal of your CDB Certificate till today. " +
                        "However your penalty will be calculated till date of approval.");
            }
            waiveOffLateFee = (acNoOfLateDays - noOfLateDays)*100;
            lateFeeDTO.setNoOfDaysLate(acNoOfLateDays.intValue());
            lateFeeDTO.setNoOfDaysAfterGracePeriod(noOfLateDays.intValue());
            lateFeeDTO.setWaiveOffLateFee(new BigDecimal(waiveOffLateFee));
            lateFeeDTO.setPaymentAmount(lateFee);
        } else{
            responseMessage.setText("You are applying for renewal of Engineer CDB certificate on time. So, no penalty will be" +
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
        ArchitectDto ongoingApp = dao.getEngineerOngoingApp(cdbNo);
        if(ongoingApp == null || ongoingApp.getReferenceNo() == null){
            return  null;
        }
        return "You have ongoing application with reference number: <b>"+ongoingApp.getReferenceNo()+
                "</b> submitted on "+ongoingApp.getApplicationDate()+". This application is <b>"
                +ongoingApp.getUpdateStatus()
                +"</b>. Please wait until complete process for this application.";
    }

    @Transactional(readOnly = true)
    private CrpengineerFinalEntity getEngineerFinal(String cdbNo) {
        return dao.getEngineerFinal(cdbNo);
    }


    @Transactional(readOnly = true)
    public ResponseMessage isCIDUnique(String cidNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        String cidStatus = "";
        String isCIDUnique = dao.isCIDUnique(cidNo);
        if (isCIDUnique.equalsIgnoreCase("262a3f11-adbd-11e4-99d7-080027dcfac6") || isCIDUnique.equalsIgnoreCase("36f9627a-adbd-11e4-99d7-080027dcfac6") || isCIDUnique.equalsIgnoreCase("463c2d4c-adbd-11e4-99d7-080027dcfac6") || isCIDUnique.equalsIgnoreCase("6195664d-c3c5-11e4-af9f-080027dcfac6")) {
            if (isCIDUnique.equalsIgnoreCase("262a3f11-adbd-11e4-99d7-080027dcfac6")) {
                cidStatus = ApplicationStatus.UNDER_PROCESS.getName();
            }
            if (isCIDUnique.equalsIgnoreCase("36f9627a-adbd-11e4-99d7-080027dcfac6")) {
                cidStatus = ApplicationStatus.VERIFIED.getName();
            }
            if (isCIDUnique.equalsIgnoreCase("463c2d4c-adbd-11e4-99d7-080027dcfac6")) {
                cidStatus = ApplicationStatus.APPROVED.getName();
            }
            if (isCIDUnique.equalsIgnoreCase("6195664d-c3c5-11e4-af9f-080027dcfac6")) {
                cidStatus = ApplicationStatus.APPROVED_FOR_PAYMENT.getName();
            }
            responseMessage.setText("Application for this CID is " + cidStatus + "." + " Please wait until the process is complete.");
            responseMessage.setStatus(1);
        } else {
            responseMessage.setStatus(0);
        }
        return responseMessage;
    }


    @Transactional
    public CertificateDTO getEngineerPrintDetails(HttpServletRequest request, String cdbNo) {
        return dao.getEngineerPrintDetails(request,cdbNo);
    }
}
