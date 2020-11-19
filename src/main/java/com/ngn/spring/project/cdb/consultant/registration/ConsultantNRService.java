package com.ngn.spring.project.cdb.consultant.registration;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTO;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTOFetch;
import com.ngn.spring.project.cdb.consultant.model.*;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class ConsultantNRService extends BaseService {
    private String UPLOAD_LOC;
    private String REFERENCE_NO;

    @Autowired
    private ConsultantNRDao consultantDao;

    @Autowired
    private CommonService commonService;

    /**
     * To get the fee structure of the consultant based on category.
     * @param category -- consultant category.
     * @return List
     */

    @Transactional(readOnly = true)
    public List gFeeStructure(String category){
        return consultantDao.gFeeStructure(category);
    }

    /**
     * To get the fee structure of the consultant based on category.
     * @return List
     */

    @Transactional(readOnly = true)
    public List gConsultantCategory(){
        return consultantDao.gConsultantCategory();
    }

    @Transactional(readOnly = true)
    public List gClassification(){
        return consultantDao.gClassification();
    }

    @Transactional(readOnly = true)
    public List gEquipment(){
        return consultantDao.gEquipment();
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
    public ResponseMessage save(ConsultantDTO consultantDTO, LoggedInUser loggedInUser, HttpServletRequest request) throws Exception {
        Consultant consultant = consultantDTO.getConsultant();
        List<ConsultantHR> consultantHRs = consultantDTO.getConsultantHRs();
        List<ConsultantCategory> categories = consultantDTO.getCategories();
        List<ConsultantEQ> equipments = consultantDTO.getEquipments();

        responseMessage = validation(consultantDTO);
        if(responseMessage.getStatus() != SUCCESSFUL_STATUS){
            return responseMessage;
        }

        String consultantID = saveGI(consultant, loggedInUser);
        //region save attachment
        if(consultantDTO.getcAttachments() != null) {
            for (ConsultantAttachment attachment : consultantDTO.getcAttachments()){
                attachment.setConsultantId(consultantID);
                saveAttachment(attachment,loggedInUser);
            }
        }
        //endregion
        //region Save Consultant HR
        for(ConsultantHR consultantHR:consultantHRs){
            String hrId = commonService.getRandomGeneratedId();
            consultantHR.setId(hrId);
            consultantHR.setConsultantID(consultantID);
            consultantHR.setIsPartnerOrOwner(FALSE_INT);
           // consultantHR.setJoiningDate(new Date());
            saveHR(consultantHR, loggedInUser);
            //Save Human resource attachment
            for(ConsultantHRAttachment consultantHRA:consultantHR.getConsultantHRAs()){
                consultantHRA.setConsultantHrId(hrId);
                saveHRA(consultantHRA,loggedInUser);
            }
        }
        //endregion

        //region save consultant category
        categories.stream().filter(c->c.getServiceCateID()!= null).forEach(c -> {
            c.setConsultantID(consultantID);
            saveCC(c, loggedInUser,request);
        });
        //endregion
        Integer i = 0;
        if(equipments != null && !equipments.isEmpty()) {
            for (ConsultantEQ consultantEQ : equipments) {
                i++;
                String consultantEQId = commonService.getRandomGeneratedId();
                consultantEQ.setId(consultantEQId);
                // consultantEQ.setId(consultantEQ.getEquipmentId());
                consultantEQ.setConsultantId(consultantID);
                consultantEQ.setSerialNo(i.toString());
                String itemId = consultantEQ.getEquipmentId();
                String itemIdArray[] = itemId.toString().split(",");
                String part1 = itemIdArray[0];
                consultantEQ.setEquipmentId(part1);
                saveEQ(consultantEQ, loggedInUser);

                for (ConsultantEQAttachment consultantEQA : consultantEQ.getConsultantEQAs()) {
                    consultantEQA.setEquipmentId(consultantEQId);
                    saveEQA(consultantEQA, loggedInUser);
                }
            }
        }
        //region save applied service
        ConsultantAppliedS consultantAS = new ConsultantAppliedS();
        consultantAS.setConsultantId(consultantID);
        saveAppliedService(consultantAS,loggedInUser);
        //endregion
        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your application for Registration Of Consultant has been submitted and your application number is <b>"+REFERENCE_NO+"</b><br>" +
                "You will receive an email with the Application summary.<br><br>" +
                "<b>You can track your application using above Application Number.</b> <br>" +
                "Thanks You.");
        String mailContent = "Dear User," +
                "Your application for New Registration of Consultant is submitted with an application number : "+REFERENCE_NO+
                "You can check your application status using above application number."+
                "Thank You.";
        MailSender.sendMail(consultant.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application Submitted");
        return responseMessage;
    }

    public ResponseMessage validation(ConsultantDTO consultantDTO){
        Consultant consultant = consultantDTO.getConsultant();
        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        Boolean isEmailUnique = isEmailUnique(consultant.getRegEmail());
        if(!isEmailUnique){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("This email has been already registered.");
            return responseMessage;
        }

        Boolean isFirmNameUnique = isFirmNameUnique(consultant.getFirmName());
        if(!isFirmNameUnique){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("This firm name been already taken. Please choose another name.");
            return responseMessage;
        }

        if(!consultant.getRegEmail().equals(consultant.getConfirmEmail())){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Confirmation email does not match.");
            return responseMessage;
        }
        return responseMessage;
    }

    /**
     * To save consultant general information
     * @param consultant    --  Consultant Entity
     * @param loggedInUser  --  Current logged in user
     * @return String  -- consultantID
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public String saveGI(Consultant consultant, LoggedInUser loggedInUser) throws Exception {
        //region Save consultant
        String consultantID = commonService.getRandomGeneratedId();
        String referenceNo = commonService.getNextID("crpconsultant", "ReferenceNo").toString();
        REFERENCE_NO = referenceNo;
        UPLOAD_LOC = "Consultant//"+referenceNo;
        consultant.setApplicationDate(loggedInUser.getServerDate());
        consultant.setInitialDate(loggedInUser.getServerDate());
        consultant.setWaiveOffLateFee(BigDecimal.ZERO);
        consultant.setHasNotification("0");
        consultant.setRegStatus("0");
        consultant.setAppStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        consultant.setCreatedBy(loggedInUser.getUserID());
        consultant.setCreatedOn(loggedInUser.getServerDate());
        consultant.setId(consultantID);
        consultant.setConsultantId(consultantID);
        consultant.setReferenceNo(referenceNo);
        if(consultant.getpDzongkhagId()== null || consultant.getpDzongkhagId().isEmpty()){
            consultant.setpDzongkhagId(null);
        }
        consultantDao.saveUpdate(consultant);
        //endregion

        //region Save Consultant HR
        for(ConsultantHR consultantHR:consultant.getConsultantHRs()){
            String hrId = commonService.getRandomGeneratedId();
            consultantHR.setId(hrId);
            consultantHR.setConsultantID(consultantID);
            consultantHR.setIsPartnerOrOwner(TRUE_INT);
            saveHR(consultantHR, loggedInUser);
        }
        //endregion

        return consultantID;
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

    /**
     * To save consultant human resource
     * @param consultantHR  -- ConsultantHR entity
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveHR(ConsultantHR consultantHR,LoggedInUser loggedInUser) throws Exception {
        if(consultantHR.getSiCertificate() == null){
            consultantHR.setSiCertificate(0);
        }
        consultantHR.setVerified(FALSE_INT);
        consultantHR.setApproved(FALSE_INT);
        consultantHR.setCreatedBy(loggedInUser.getUserID());
        consultantHR.setCreatedOn(loggedInUser.getServerDate());
        consultantDao.saveUpdate(consultantHR);
    }
    /**
     * to save consultant human resource attachment
     * @param consultantHRA -- ConsultantHRAttachment
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */

    @Transactional(readOnly = false)
    public void saveHRA(ConsultantHRAttachment consultantHRA, LoggedInUser loggedInUser) throws Exception{
        MultipartFile attachment = consultantHRA.getAttachment();
        /*String documentName = contractorHRA.getDocumentName();
        if(documentName == null || documentName.isEmpty()){
            contractorHRA.setDocumentName("CV_UT_AT_"+i);
        }*/
        String docNameUpload = consultantHRA.getDocumentName()+commonService.getFileEXT(attachment);
        String specificLoc = UPLOAD_LOC+"//HR";
        String docPath = commonService.uploadDocument(attachment, specificLoc, docNameUpload);
        String hrAttachmentID = commonService.getRandomGeneratedId();
        consultantHRA.setId(hrAttachmentID);
        consultantHRA.setDocumentPath(docPath);
        consultantHRA.setDocumentName(docNameUpload);
        consultantHRA.setFileType(attachment.getContentType());
        consultantHRA.setCreatedBy(loggedInUser.getUserID());
        consultantHRA.setCreatedOn(loggedInUser.getServerDate());
        consultantDao.saveUpdate(consultantHRA);
    }

    /**
     * To save consultant category and classification
     * @param consultantCategory  --  ConsultantCategory entity
     * @param loggedInUser  -- Current Logged in user
     * @param request
     */

    @Transactional(readOnly = false)
    public void saveCC(ConsultantCategory consultantCategory, LoggedInUser loggedInUser, HttpServletRequest request) {
        String aServices[] = consultantCategory.getAppliedServiceID().split(",");
        List<String> aServiceList = Arrays.asList(aServices);

        aServiceList.stream().filter(s -> !s.isEmpty()).forEach(s -> {
                    ConsultantCategory conCategory = new ConsultantCategory();
                    conCategory.setId(commonService.getRandomGeneratedId());
                    conCategory.setConsultantID(consultantCategory.getConsultantID());
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
            } else {
                serviceCodes = serviceCodes.concat(",").concat(serviceCode);
            }
            noOfServices++;
        }

            //set Payment values
            ConsultantRegPayment consultantPayment = new ConsultantRegPayment();
            consultantPayment.setConsultantId(consultantCategory.getConsultantID());
            consultantPayment.setCategoryId(consultantCategory.getServiceCateID());
            consultantPayment.setAppliedServices(serviceCodes);
            consultantPayment.setServiceXFee(String.valueOf(noOfServices +"*"+ "3000"));
            consultantPayment.setAmount(BigDecimal.valueOf(noOfServices * 3000));

            consultantPayment.setCreatedBy(loggedInUser.getUserID());
            consultantPayment.setCreatedOn(loggedInUser.getServerDate());
            savePayment(consultantPayment, loggedInUser);


      }

    @Transactional(readOnly = false)
    public void savePayment(ConsultantRegPayment consultantRegPayment, LoggedInUser loggedInUser) {
       // FeeStructureDTO feeDTO = (FeeStructureDTO)consultantDao.gFeeStructure(consultantRegPayment.getAppliedServices()).get(0);
        String id = commonService.getRandomGeneratedId();
        consultantRegPayment.setId(id);
       // consultantRegPayment.setAmount(feeDTO.getRegistrationFee());
        consultantRegPayment.setCreatedBy(loggedInUser.getUserID());
        consultantRegPayment.setCreatedOn(loggedInUser.getServerDate());
        consultantDao.saveUpdate(consultantRegPayment);
    }
    /**
     * To save consultant equipment details
     * @param consultantEQ  -- ConsultantEQ entity
     * @param loggedInUser  -- Current Logged in user
     */
    @Transactional(readOnly = false)
    public void saveEQ(ConsultantEQ consultantEQ, LoggedInUser loggedInUser) {
        if(consultantEQ.getApproved() == null){
            consultantEQ.setApproved(FALSE_INT);
        }
        if(consultantEQ.getVerified() == null){
            consultantEQ.setVerified(FALSE_INT);
        }
        consultantEQ.setCreatedBy(loggedInUser.getUserID());
        consultantEQ.setCreatedOn(loggedInUser.getServerDate());
        consultantDao.saveUpdate(consultantEQ);
    }

    @Transactional(readOnly = false)
    public void saveAppliedService(ConsultantAppliedS consultantAppliedS, LoggedInUser loggedInUser) {
        String id = commonService.getRandomGeneratedId();
        String appliedServiceId = null;
        if(consultantAppliedS.getServiceTypeId() == null){
            appliedServiceId = (String)commonService.getValue("crpservice","Id","ReferenceNo","1");
            consultantAppliedS.setServiceTypeId(appliedServiceId);
        }
        consultantAppliedS.setId(id);
        consultantAppliedS.setCreatedBy(loggedInUser.getUserID());
        consultantAppliedS.setCreatedOn(loggedInUser.getServerDate());

        consultantDao.saveUpdate(consultantAppliedS);
    }

    /**
     * to save consultant human resource attachment
     * @param consultantEQA -- ConsultantEQAttachment
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveEQA(ConsultantEQAttachment consultantEQA, LoggedInUser loggedInUser) throws Exception{
        MultipartFile attachment = consultantEQA.getAttachment();

        String docName = consultantEQA.getDocumentName()+commonService.getFileEXT(attachment);
        String specificLoc = UPLOAD_LOC+"//EQUIPMENT";
        String docPath = commonService.uploadDocument(attachment,specificLoc,docName);
        String eqAttachmentID = commonService.getRandomGeneratedId();
        consultantEQA.setId(eqAttachmentID);
        consultantEQA.setDocumentPath(docPath);
        consultantEQA.setDocumentName(docName);
        consultantEQA.setFileType(attachment.getContentType());
        consultantEQA.setCreatedBy(loggedInUser.getUserID());
        consultantEQA.setCreatedOn(loggedInUser.getServerDate());
        consultantDao.saveUpdate(consultantEQA);
    }

    @Transactional(readOnly = true)
    public Consultant getConsultant(String referenceNo){
        return consultantDao.getConsultant(referenceNo);
    }

    @Transactional(readOnly = true)
    public ConsultantFinal getConsultantFinal(String cdbNo){
        return consultantDao.getConsultantFinal(cdbNo);
    }

    public ConsultantDTOFetch getConsultantOngoingApp(String cdbNo){
        return consultantDao.getConsultantOngoingApp(cdbNo);
    }

    @Transactional(readOnly = true)
    public List getTrainingDtl(String cidNo){
        return consultantDao.getTrainingDtl(cidNo);
    }

    @Transactional(readOnly = true)
    public Boolean isEmailUnique(String email) {
        return consultantDao.isEmailUnique(email);
    }

    @Transactional(readOnly = true)
    public Boolean isFirmNameUnique(String firmName) {
        return consultantDao.isFirmNameUnique(firmName);
    }
}
