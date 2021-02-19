package com.ngn.spring.project.cdb.survey.service;

import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import bt.gov.g2c.aggregator.business.InvokePaymentWS;
import bt.gov.g2c.aggregator.dto.PaymentDTO;
import bt.gov.g2c.aggregator.dto.RequestDTO;
import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.architect.entity.CrparchitectFinalEntity;
import com.ngn.spring.project.cdb.certification.CertificateDTO;
import com.ngn.spring.project.cdb.common.CommonDao;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.renewal.LateFeeDTO;
import com.ngn.spring.project.cdb.engineer.dao.EngineerDao;
import com.ngn.spring.project.cdb.survey.dao.SurveyDao;
import com.ngn.spring.project.cdb.survey.entity.CrpsurveyEntity;
import com.ngn.spring.project.cdb.survey.entity.CrpsurveyFinalEntity;
import com.ngn.spring.project.cdb.survey.entity.SurveyDocument;
import com.ngn.spring.project.cdb.survey.entity.SurveyServiceEntity;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.global.global.SmsSender;
import com.ngn.spring.project.lib.LoggedInUser;
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
import java.util.ResourceBundle;

/**
 * Created by USER on 3/19/2020.
 */
@Service
public class SurveyServices extends BaseService {
    @Autowired
    private SurveyDao dao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private EngineerDao engineerDao;

    @Autowired
    private CommonDao commonDao;

    @Transactional(readOnly = true)
    public List getFeesDetals(String survey) {
        return dao.gFeeStructure(survey);
    }

    @Transactional(readOnly = false)
    public ResponseMessage saveSurvey(ArchitectDto dto, UserSessionDetailDTO user) {
        String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            CrpsurveyEntity survey = convertDtoToEntity(dto, user);
            survey.setId(generateID);
            survey.setCrpSurveyId(generateID);
            dao.save(survey);

            SurveyServiceEntity serviceEntity = convertSurveyorToServiceEntity(user);
            serviceEntity.setSurveyId(generateID);
            dao.saveSservies(serviceEntity);
            responseMessage.setStatus(1);
            dto.setReferenceNo(new BigInteger(survey.getReferenceNo()));
            dto.setCrpSurveyId(generateID);
            responseMessage.setDto(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseMessage;
    }

    private CrpsurveyEntity convertDtoToEntity(ArchitectDto dto, UserSessionDetailDTO user) {
        CrpsurveyEntity entity = new CrpsurveyEntity();
        entity.setApplicationDate(new Date());
        entity.setInitialDate(new Date());
        entity.setReferenceNo(getMaxId().toString());
        entity.setCIDNo(dto.getCidNo());
        entity.setName(dto.getFullname());
        entity.setCmnSalutationId(dto.getSalutation());
        entity.setCmnDzongkhagId(dto.getDzongkhagId());
        entity.setGewog(dto.getGewog());
        entity.setVillage(dto.getVillage());
        entity.setCmnServiceSectorTypeId(dto.getServiceSectorType());
        entity.setCmnCountryId(dto.getCountryId());
        entity.setCmnTradeId(dto.getTrade());
        entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.UNDER_PROCESS.getCode());

        entity.setEmail(dto.getEmail());
        entity.setMobileNo(dto.getMobileNo());
        entity.setEmployerName(dto.getEmployeeName());
        entity.setEmployerAddress(dto.getEmployeeAddress());
        entity.setCmnQualificationId(dto.getQualificationId());
        entity.setGraduationYear(dto.getGraduationYear());
        entity.setCmnUniversityCountryId(dto.getUniversityCountry());
        entity.setNameOfUniversity(dto.getUniversityName());
        if (user == null) {
            entity.setCreatedBy("CITIZEN");
        } else {
            entity.setCreatedBy(user.getCid());
        }
        entity.setCreatedOn(new Date());
        return entity;
    }

    private SurveyServiceEntity convertSurveyorToServiceEntity(UserSessionDetailDTO user) {
        String generateID = commonService.getRandomGeneratedId();
        SurveyServiceEntity surveyAppliedServiceEntity = new SurveyServiceEntity();
        surveyAppliedServiceEntity.setId(generateID);
        surveyAppliedServiceEntity.setCmnServiceTypeId("55a922e1-cbbf-11e4-83fb-080027dcfac6");//new registration
        surveyAppliedServiceEntity.setCreatedBy(null);
        if (user == null) {
            surveyAppliedServiceEntity.setCreatedBy("CITIZEN");
        } else {
            surveyAppliedServiceEntity.setCreatedBy(user.getCid());
        }
        surveyAppliedServiceEntity.setCreatedOn(new Date());
        surveyAppliedServiceEntity.setEditedOn(new Date());
        return surveyAppliedServiceEntity;
    }

    @Transactional(readOnly = false)
    private BigInteger getMaxId() {
        BigInteger referenceNo = dao.getMaxId();
        return (referenceNo.intValue() > 0) ? referenceNo.add(BigInteger.ONE) : BigInteger.ONE;
    }

    @Transactional
    public void saveDoc(MultipartFile[] files, String referenceNo, String service, String loggedInUser) {

        try {
            for (int i = 0; i < files.length; i++) {
                String generateID = commonService.getRandomGeneratedId();
                MultipartFile file = files[i];
                String docPath = commonService.uploadDocument(file, service, file.getOriginalFilename());
                SurveyDocument docdet = new SurveyDocument();
                docdet.setId(generateID);
                docdet.setSurveyid(referenceNo);
                docdet.setDocumentName(file.getOriginalFilename());
                docdet.setFileType(file.getContentType());
                docdet.setDocumentPath(docPath);
                docdet.setCreatedBy(loggedInUser);
                docdet.setEditedBy(loggedInUser);
                docdet.setCreatedOn(new Date());
                docdet.setEditedOn(new Date());
                dao.saveAttchment(docdet);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @Transactional(readOnly = true)
    public List getTaskList(String status, String type, String userId, String servicetype) {
        return dao.getTaskList(status, type, userId, servicetype);
    }

    @Transactional
    public List getModePayment() {
        return commonService.getModePayment();
    }

    public String assignMyTask(String appNo, String userID, String type) {
        String assignMyTask = dao.assignMyTask(appNo, userID,type);
        return assignMyTask;
    }

    public ArchitectDto getSurveyDetails(String appNo) {
        ArchitectDto dto = dao.getSurveyDetails(appNo);
        if (dto.getServiceTypeId().equalsIgnoreCase("New Registration")) {
            if (dto.getServiceSectorType().equalsIgnoreCase("Government") || dto.getServiceSectorType().equalsIgnoreCase("Private") && dto.getUpdateStatus().equalsIgnoreCase("6195664d-c3c5-11e4-af9f-080027dcfac6")) {
                //generate cdb nunber
             //   String surveyNo = dao.generateSurveyNo(dto.getCountryId(), dto.getServiceSectorType());
                dto.setCdbNo("NULL");
            }
         /*   else
            if(dto.getUpdateStatus().equalsIgnoreCase("6195664d-c3c5-11e4-af9f-080027dcfac6")){
                String surveyNo=dao.generateSurveyNo(dto.getCountryId(), dto.getServiceSectorType());
                dto.setCdbNo(surveyNo);
            }*/
        }
        List<SurveyDocument> surveyDocuments = dao.getdocumentList(dto.getCrpSurveyId());
        dto.setSurveyDocuments(surveyDocuments);
        return dto;
    }

    public ArchitectDto updateReject(ArchitectDto dto, String userID, HttpServletRequest request) {
        dto = dao.updateReject(dto, userID, request);
        if (dto.getUpdateStatus().equalsIgnoreCase("Success")) {
            if (dto.getUpdateStatus().equalsIgnoreCase("Success")) {
                //send sms and email notification
                String mailContent = "Dear User,<br>Your application for application number : " + dto.getReferenceNo() + " is Rejected because of" + dto.getRemarks() + "." +
                        "<br>You may re-apply through following link:<br>" +
                        "<a target='_blank' href='/119.2.120.14:8181/cdb/public_access/renewal'>Click here for resubmission of an application</a>";
                try {
                    MailSender.sendMail(dto.getEmail(), "cdb@gov.bt", null, mailContent, "Application Rejected");
                    SmsSender.smsSender(dto.getEmail(), "cdb@gov.bt", null, mailContent, "Application Rejected");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                dto.setUpdateStatus("Failed to update workflow table for rejecting. ");
            }
        } else {
            dto.setUpdateStatus("Failed to update the application table for rejecting. ");
        }
        return dto;
    }

    public ArchitectDto updateVerification(ArchitectDto dto, String userID, HttpServletRequest request) {
        dto = dao.updateVerification(dto, userID, request);
        if (dto.getUpdateStatus().equalsIgnoreCase("Success")) {
            if (dto.getUpdateStatus().equalsIgnoreCase("Success")) {
                //send sms and email notification
            } else {
                dto.setUpdateStatus("Failed to update workflow table for verification. ");
            }
        } else {
            dto.setUpdateStatus("Failed to update the application table for verification. ");
        }
        return dto;
    }

    public ArchitectDto approveSurveyRegistration(ArchitectDto dto, String userID, HttpServletRequest request) {
        dto = dao.updateApproval(dto, userID, request);
        if (dto.getUpdateStatus().equalsIgnoreCase("Success")) {
            if(request.getParameter("servicefor").equalsIgnoreCase("cancel")){
                dto=dao.updateFinalTable(dto,userID,request);
                engineerDao.updateSysuser(dto.getEmail());
                //send sms and email notification
                String mailContent = "Dear User,<br>Your application for  Cancellation of Certificate is approved with application number : " + dto.getReferenceNo();
                try {
                    MailSender.sendMail(dto.getEmail(), "cdb@gov.bt", null, mailContent, "CDB certificate Cancelled");
                    SmsSender.smsSender(dto.getMobileNo(), "cdb@gov.bt", null, mailContent, "CDB certificate Cancelled");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                //send sms and email notification
                RequestDTO requestDTO = new RequestDTO();
                requestDTO.setApplicationNo(String.valueOf(dto.getReferenceNo()));
                requestDTO.setAgencyCode("CDB");
                requestDTO.setServiceName("New Registration of Survey");
                requestDTO.setExpiryDate(null);
                ArrayList<PaymentDTO> paymentList = new ArrayList<PaymentDTO>();
                PaymentDTO paymentdto = new PaymentDTO();
                //Integer amount = passportUploadDAO.getServiceFees(applicationNo);
                BigDecimal amount = (BigDecimal) commonService.getValue("crpsurveyregistrationpayment","Amount","CrpSurveyFinalId",dto.getCrpSurveyId());
                paymentdto.setServiceFee(String.valueOf(amount));

                paymentdto.setAccountHeadId("131310001");
                paymentList.add(paymentdto);
                requestDTO.setPaymentList(paymentList.toArray(new PaymentDTO[paymentList.size()]));
                System.out.println("Response from Aggregator: "+paymentdto.getServiceFee());
                ResourceBundle bundle = ResourceBundle.getBundle("wsEndPointURL_en_US");
                InvokePaymentWS invokews = new InvokePaymentWS(bundle.getString("getPayment.endPointURL"));
                boolean isSaved = invokews.insertPaymentDetailsOnApproval(requestDTO);
                System.out.println("Response from Aggregator: "+isSaved);

                String mailContent = "Dear User,<br>Your application for application number : " + dto.getReferenceNo() + " is approved." +
                        "<br>You may pay the required fee online through following link:<br>" +
                        "<a target='_blank' href='https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg'>https://www.citizenservices.gov.bt/G2CPaymentAggregatorStg</a>" +
                        "<br>Or You may visit our CDB counters to pay the fee. " +
                        "<br><br>Note: Only after payment confirmation, your application will be done final approval. And you will get the login credential to log into system. ";
                try {
                    MailSender.sendMail(dto.getEmail(), "cdb@gov.bt", null, mailContent, "Application Payment approved");
                    SmsSender.smsSender(dto.getMobileNo(), "cdb@gov.bt", null, mailContent, "Application approved for Payment");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            } else {
                dto.setUpdateStatus("Failed to update workflow table for verification. ");
            }
        return dto;
        /*ArchitectDto dto1=dao.getsurveyapplicationdetails(dto);
        if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
            //update cancellation
            dto1.setCdbNo(dto.getCdbNo());
            dto1.setRemarks(dto.getRemarks());
            dao.updateCancellationDetails(dto1, userID);
            dto.setUpdateStatus("Success");
        }
        else{
            if(dto.getServiceSectorType().equalsIgnoreCase("Goverment")){
                dto1.setRemarks(dto.getRemarks());
                String insert="";
                if(dto.getServiceTypeId().equalsIgnoreCase("registration")){
                    insert=dao.insertuserDetails(dto1, userID, request);
                    if(!insert.equalsIgnoreCase("Insert_Fail")){
                        dto1.setCdbNo(dto.getCdbNo());
                        dto1.setRemarks(dto.getRemarks());
                        String password=insert.split("/")[1];
                        insert=dao.insertSurveyFinalDetails(dto1, userID, insert.split("/")[0]);
                    }
                }
                else if(dto.getServiceTypeId().equalsIgnoreCase("renewal")){
                    dto1.setCdbNo(dto.getCdbNo());
                   // insert=dao.updateRenewalDetails(dto1, userID, 5);
                }
                if(insert.equalsIgnoreCase("Success")){
                    dto.setUpdateStatus("Success");
                    //send notification
                }
            }
        }
        dto= dao.approveSurveyRegistration(dto, userID, request);

        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
                //send sms and email notification for application approval
            }
            else{
                dto.setUpdateStatus("Failed to update workflow table for approval. ");
            }
        }
        else{
            dto.setUpdateStatus("Fai0.led to update the application table for approval. ");
        }
        return dto;*/
    }

    public ArchitectDto approveAndGenerateCertificate(ArchitectDto dto, String userID, HttpServletRequest request) {
        String insert = "",deletePrevRecord="",deleteFromSysuser="";
        // if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
        ArchitectDto dto1 = dao.getsurveyapplicationdetails(dto);
        dto1.setRemarks(dto.getRemarks());
        dto1.setPaymentmode(dto.getPaymentmode());
        dto1.setPaymentAmt(dto.getPaymentAmt());
        dto1.setTotalAmt(dto.getTotalAmt());
        dto1.setNoOfDaysAfterGracePeriod(dto.getNoOfDaysAfterGracePeriod());
        dto1.setNoOfDaysLate(dto.getNoOfDaysLate());
        dto1.setTrade(dto.getTrade());
        //  if(dto.getServiceSectorType().equalsIgnoreCase("Goverment")) {
        if (dto.getServiceTypeId().equalsIgnoreCase("registration")) {
            insert = dao.insertuserDetails(dto1, userID, request);
            String surveyNo = dao.generateSurveyNo(dto1.getCmnCountryId(), dto.getServiceSectorType());
            dto.setCdbNo(surveyNo);
            dto1.setCdbNo(surveyNo);
            if (!insert.equalsIgnoreCase("Insert_Fail")) {
                dto1.setCdbNo(dto.getCdbNo());
                String password = insert.split("/")[1];
                insert = dao.insertSurveyFinalDetails(dto1, userID, insert.split("/")[0]);
                if (insert.equalsIgnoreCase("Success")) {
                    insert = dao.insertInPaymentDetails(dto, userID, request);
                }
            }
        } else if (dto.getServiceTypeId().equalsIgnoreCase("renewal")) {
            dto1.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
            dto1.setCdbNo(dto.getCdbNo());
            deletePrevRecord = dao.deletePrevRecord(dto1);
                deleteFromSysuser = commonDao.deleteFromSysuser(dto1);
                insert = dao.insertuserDetails(dto1, userID, request);
                insert = dao.insertSurveyFinalDetails(dto1, userID, insert.split("/")[0]);
                if (insert.equalsIgnoreCase("Success")) {
                    String update = dao.updateRenewalDetails(dto1, userID, 2);
                    if (update.equalsIgnoreCase("Success")) {
                        dao.updatePaymentServiceDetails(dto1, userID);
                    } else {
                        System.out.print("unable to updatePaymentServiceDetails");
                    }
                } else {
                    System.out.print("unable to update crpsurveyfinal");
                }
        }
        //}
        if (insert.equalsIgnoreCase("Success")) {
            dto = dao.updateApplicationForPayment(dto, userID, request);
            dto.setUpdateStatus("Success");
            //send notification
            String mailContent = "Dear User,Your application for application number : " + dto.getReferenceNo() + " is approved." +
                    "You can login to the system for renewal other services using following credential:" +
                    "Username : your registered email" +
                    "Password : 123" +
                    "Please change your default password after login.";
            try {
                MailSender.sendMail(dto.getEmail(), "cdb@gov.bt", null, mailContent, "Application Payment approved");
                SmsSender.smsSender(dto.getEmail(), "cdb@gov.bt", null, mailContent, "Application Payment approved");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("not able to send notification for this email" + dto.getEmail() + "with reason:" + e);
            }
            // }
        }
        return dto;
    }

    public List<ArchitectDto> getPrintList() {
        return dao.getPrintList();
    }

    public ArchitectDto checkOngoingApplication(String cdbNo) {
        return dao.checkOngoingApplication(cdbNo);
    }

    @Transactional
    public ResponseMessage saveSurveyRenenwal(ArchitectDto dto, String userID) {
        String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            CrpsurveyEntity survey = convertDtoToEntityForRenewal(dto, userID);
            survey.setId(generateID);
            survey.setCrpSurveyId(generateID);
            dto.setCrpSurveyId(generateID);
            dto.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
            if (survey.getGraduationYear() != null) {
                dao.save(survey);
            } else {
                responseMessage.setStatus(0);
                return responseMessage;
            }

            String generateID1 = commonService.getRandomGeneratedId();
            SurveyServiceEntity surveyAppliedServiceEntity = new SurveyServiceEntity();
            surveyAppliedServiceEntity.setId(generateID1);
            surveyAppliedServiceEntity.setCmnServiceTypeId("45bc628b-cbbe-11e4-83fb-080027dcfac6");//Renewal of CDB Certificate
            surveyAppliedServiceEntity.setCreatedBy(userID);
            surveyAppliedServiceEntity.setEditedBy(userID);
            surveyAppliedServiceEntity.setCreatedOn(new Date());
            surveyAppliedServiceEntity.setEditedOn(new Date());
            surveyAppliedServiceEntity.setSurveyId(generateID);
            dao.saveSservies(surveyAppliedServiceEntity);

            BigDecimal payAmount = dto.getPaymentAmt();
            if(payAmount == null || dto.getServiceSectorType().equalsIgnoreCase("Government")){
                payAmount = BigDecimal.valueOf(00.00);
            }
            BigDecimal totalAmount;
            if(payAmount.doubleValue() > 3000.00){
                dto.setPaymentAmt(BigDecimal.valueOf(3000.00));
                totalAmount = BigDecimal.valueOf(payAmount.floatValue() + 1000);
                dto.setTotalAmt(totalAmount);
            }else{
                if(dto.getServiceSectorType().equalsIgnoreCase("Government")){
                    totalAmount = BigDecimal.valueOf(payAmount.floatValue());
                    dto.setPaymentAmt(BigDecimal.valueOf(00.00));
                }else{
                    totalAmount = BigDecimal.valueOf(payAmount.floatValue() + 1000);
                    dto.setPaymentAmt(dto.getPaymentAmt());
                }
                dto.setTotalAmt(totalAmount);
            }
            dao.insertInPaymentServiceDetails(dto, userID);

            responseMessage.setStatus(1);
            dto.setReferenceNo(new BigInteger(survey.getReferenceNo()));
            dto.setCrpSurveyId(generateID);
            responseMessage.setDto(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    private CrpsurveyEntity convertDtoToEntityForRenewal(ArchitectDto dto, String user) {
        // ArchitectDto dto1=dao.getsurveyDetails(dto1.getCdbNo());
        CrpsurveyEntity entity = new CrpsurveyEntity();
        entity.setApplicationDate(new Date());
        entity.setInitialDate(new Date());
        entity.setReferenceNo(getMaxId().toString());
        entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        entity.setARNo(dto.getCdbNo());
        entity.setRegistrationExpiryDate(dto.getRegExpDate());
        entity.setCIDNo(dto.getCidNo());
        entity.setName(dto.getFullname());
        entity.setCmnSalutationId(dto.getSalutation());
        entity.setCmnDzongkhagId(dto.getDzongkhagId());
        entity.setGewog(dto.getGewog());
        entity.setVillage(dto.getVillage());
        entity.setCmnServiceSectorTypeId(dto.getServiceSectorTypeId());
        entity.setCmnCountryId(dto.getCountryId());
        entity.setCmnTradeId(dto.getTrade());
        entity.setEmail(dto.getEmail());
        entity.setMobileNo(dto.getMobileNo());
        entity.setEmployerName(dto.getEmployeeName());
        entity.setEmployerAddress(dto.getEmployeeAddress());
        entity.setCmnQualificationId(dto.getQualificationId());
        entity.setGraduationYear(dto.getGraduationYear());
        entity.setCmnUniversityCountryId(dto.getUniversityCountry());
        entity.setNameOfUniversity(dto.getUniversityName());
        entity.setCreatedOn(new Date());
        entity.setCreatedBy(user);
        return entity;
    }

    @Transactional
    public SurveyDocument getDocumentDetailsByDocId(String uploadDocId) {
        SurveyDocument dto = dao.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }

    @Transactional
    public ResponseMessage saveSurveycancellation(ArchitectDto dto1, String userID) {
        String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            ArchitectDto dto = dao.getsurveyDetails(dto1.getCdbNo());

            CrpsurveyEntity entity = new CrpsurveyEntity();

            entity.setId(generateID);
            entity.setCrpSurveyId(generateID);
            entity.setApplicationDate(new Date());
            entity.setInitialDate(new Date());
            entity.setReferenceNo(getMaxId().toString());
            entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.VERIFIED.getCode());
            entity.setARNo(dto.getCdbNo());
            entity.setRegistrationExpiryDate(dto.getRegExpDate());
            entity.setCIDNo(dto.getCidNo());
            entity.setName(dto.getFullname());
            entity.setCmnSalutationId(dto.getSalutation());
            entity.setCmnDzongkhagId(dto.getDzongkhagId());
            entity.setGewog(dto.getGewog());
            entity.setVillage(dto.getVillage());
            entity.setCmnServiceSectorTypeId(dto.getServiceSectorType());
            entity.setCmnCountryId(dto.getCountryId());
            entity.setEmail(dto.getEmail());
            entity.setMobileNo(dto.getMobileNo());
            entity.setEmployerName(dto.getEmployeeName());
            entity.setEmployerAddress(dto.getEmployeeAddress());
            entity.setCmnQualificationId(dto.getQualificationId());
            entity.setGraduationYear(dto.getGraduationYear());
            entity.setCmnUniversityCountryId(dto.getUniversityCountry());
            entity.setNameOfUniversity(dto.getUniversityName());
            entity.setCreatedOn(new Date());
            entity.setCreatedBy(userID);
            dao.save(entity);

            String generateID1 = commonService.getRandomGeneratedId();
            SurveyServiceEntity surveyrAppliedServiceEntity = new SurveyServiceEntity();
            surveyrAppliedServiceEntity.setId(generateID1);
            surveyrAppliedServiceEntity.setCmnServiceTypeId(ApplicationStatus.CANCELLATION.getCode());//Renewal of CDB Certificate
            surveyrAppliedServiceEntity.setCreatedBy(userID);
            surveyrAppliedServiceEntity.setEditedBy(userID);
            surveyrAppliedServiceEntity.setCreatedOn(new Date());
            surveyrAppliedServiceEntity.setEditedOn(new Date());
            surveyrAppliedServiceEntity.setSurveyId(generateID);
            dao.saveSservies(surveyrAppliedServiceEntity);

            responseMessage.setStatus(1);
            dto.setReferenceNo(new BigInteger(entity.getReferenceNo()));
            dto.setCrpSurveyId(generateID);
            responseMessage.setDto(dto);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    public ArchitectDto getDetails(String appNo) {
        ArchitectDto dto = dao.getDetails(appNo);
        List<SurveyDocument> surveyDocuments = dao.getdocumentList(dto.getCrpSurveyId());
        dto.setSurveyDocuments(surveyDocuments);
        return dto;
    }

    @Transactional
    public ResponseMessage isMailUnique(HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        String isMailUnique = dao.isMailUnique(request);
        if (isMailUnique.isEmpty()) {
            responseMessage.setStatus(0);
        } else {
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }

    /**
     * To check for validations for renewal. If any condition is fulfilled or not. and returns the message to be displayed
     *
     * @param cdbNo -- CBDNo
     * @return Response message
     */
    @Transactional(readOnly = true)
    public ResponseMessage check4Renewal(String cdbNo, Boolean isRenewal) {
        responseMessage.reset();
        LateFeeDTO lateFeeDTO = new LateFeeDTO();
        CrpsurveyFinalEntity cFinal = getSurveyFinal(cdbNo);
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
            responseMessage.setText("You are not allow to avail this service as your certificate is " +
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
                lateFee= BigDecimal.valueOf(00.00);
            }
            if(cFinal.getCmnServiceSectorTypeId().equalsIgnoreCase("6e1cd096-bea8-11e4-9757-080027dcfac6")){
                responseMessage.setText("Seems like your registration is already expired on <b>"+expiryDate+
                        "</b>. The total number of days late is <b>"+acNoOfLateDays+"</b> days." +
                        " However 30 days is considered as grace period which means the late fees that would be imposed within that period will be waived. Penalty amount is Nu. 100 per day.<br>" +
                        "Total number of days after grace period is <b>"+noOfLateDays+"</b>. <h4>Since you are government employee renewal and late fees will not be imposed.</h4>");
            }else {
                responseMessage.setText("Seems like your registration is already expired on <b>" + expiryDate +
                        "</b>. The total number of days late is <b>" + acNoOfLateDays + "</b> days." +
                        " However 30 days is considered as grace period which means the late fees that would be imposed within that period will be waived. Penalty amount is Nu. 100 per day.<br>" +
                        "Total number of days after grace period is <b>" + noOfLateDays + "</b>. Total of Nu. " + lateFee + " will be imposed as penalty for late renewal of your cdb Certificate till today. " +
                        "However your penalty will be calculated till date of approval.");
            }
            waiveOffLateFee = (acNoOfLateDays - noOfLateDays)*100;
            lateFeeDTO.setNoOfDaysLate(acNoOfLateDays.intValue());
            lateFeeDTO.setNoOfDaysAfterGracePeriod(noOfLateDays.intValue());
            lateFeeDTO.setWaiveOffLateFee(new BigDecimal(waiveOffLateFee));
            lateFeeDTO.setPaymentAmount(lateFee);
        } else{
            responseMessage.setText("You are applying for renewal of Surveyor CDB certificate on time. So, no penalty will be" +
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
        ArchitectDto ongoingApp = dao.getSurveyorOngoingApp(cdbNo);
        if(ongoingApp == null || ongoingApp.getReferenceNo() == null){
            return  null;
        }
        return "You have ongoing application with reference number: <b>"+ongoingApp.getReferenceNo()+
                "</b> submitted on "+ongoingApp.getApplicationDate()+". This application is <b>"
                +ongoingApp.getUpdateStatus()
                +"</b>. Please wait until complete process for this application.";
    }


    @Transactional(readOnly = true)
    private CrpsurveyFinalEntity getSurveyFinal(String cdbNo) {
        return dao.getSurveyFinal(cdbNo);
    }

    @Transactional
    public CertificateDTO getSurveyPrintDetails(HttpServletRequest request, String cdbNo) {
        return dao.getSurveyPrintDetails(request,cdbNo);
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
}
