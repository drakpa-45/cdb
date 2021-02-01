package com.ngn.spring.project.cdb.specializedFirm;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmDTO;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmDTOFetch;
import com.ngn.spring.project.cdb.specializedFirm.model.*;
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
import java.util.Date;
import java.util.List;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Service
public class SpecializedFirmService extends BaseService {
    private String UPLOAD_LOC;
    private String REFERENCE_NO;

    @Autowired
    private SpecializedFirmDao dao;

    @Autowired
    private CommonService commonService;

    /**
     * To get the fee structure of the specialized based on category.
     * @param category -- specialized category.
     * @return List
     */

    @Transactional(readOnly = true)
    public List gFeeStructure(String category){
        return dao.gFeeStructure(category);
    }

    /**
     * To get the fee structure of the specialized based on category.
     * @return List
     */


    @Transactional(readOnly = true)
    public List gClassification(){
        return dao.gClassification();
    }

    @Transactional(readOnly = true)
    public List categoryFirm() {
        return dao.categoryFirm();
    }

    @Transactional(readOnly = true)
    public List gEquipment(){
        return dao.gEquipment();
    }

    /**
     * The main save method for specialized which calls specific save methods
     * @param spFirmDTO   --  SpFirmDTO
     * @param loggedInUser    --  Current logged in user
     * @param request
     * @return ResponseMessage  -- response message
     * @throws Exception
     */

    @Transactional(readOnly = false)
    public ResponseMessage save(SpFirmDTO spFirmDTO, LoggedInUser loggedInUser, HttpServletRequest request) throws Exception {
        SpecializedFirm specializedFirm = spFirmDTO.getSpecializedFirm();
        List<SpFirmHR> spFirmHRs = spFirmDTO.getSpFirmHRs();
        List<SpFirmCategory> categories = spFirmDTO.getCategories();
        List<SpFirmEQ> equipments = spFirmDTO.getEquipments();

        responseMessage = validation(spFirmDTO);
        if(responseMessage.getStatus() != SUCCESSFUL_STATUS){
            return responseMessage;
        }

        String specializedID = saveGI(specializedFirm, loggedInUser);
        //region save attachment
        if(spFirmDTO.getcAttachments() != null) {
            for (SpFirmAttachment attachment : spFirmDTO.getcAttachments()){
                attachment.setSpecializedTradeId(specializedID);
                saveAttachment(attachment,loggedInUser);
            }
        }
        //endregion
        //region Save specialized HR
        for(SpFirmHR spFirmHR:spFirmHRs){
            String hrId = commonService.getRandomGeneratedId();
            spFirmHR.setId(hrId);
            spFirmHR.setSpecializedID(specializedID);
            spFirmHR.setIsPartnerOrOwner(FALSE_INT);
            saveHR(spFirmHR,loggedInUser);
            //Save Human resource attachment
            for(SpFirmtHRAttachment spFirmtHRA:spFirmHR.getSpFirmHRAs()){
                spFirmtHRA.setSpecializedHrId(hrId);
                saveHRA(spFirmtHRA,loggedInUser);
            }
        }
        //endregion
        //region save specialized category
        categories.stream().filter(c->c.getAppliedCategoryId()!= null).forEach(c->{
                    c.setSpecializedID(specializedID);
                    saveCC(c,loggedInUser);
                    //set Payment values
                    SpFirmRegPayment spFirmRegPayment = new SpFirmRegPayment();
                    spFirmRegPayment.setSpecializedTradeFinalId(specializedID);
                    spFirmRegPayment.setCategoryId(c.getAppliedCategoryId());
                    spFirmRegPayment.setAppliedCategoryId(c.getAppliedCategoryId());
                    spFirmRegPayment.setAppliedAmount(BigDecimal.valueOf(3000.00));
                    savePayment(spFirmRegPayment, loggedInUser);
                }
        );
        //endregion
        Integer i = 0;
        if(equipments != null && !equipments.isEmpty()) {
            for (SpFirmEQ spFirmEQ : equipments) {
                i++;
                String spFirmEQId = commonService.getRandomGeneratedId();
                spFirmEQ.setId(spFirmEQId);
                // spFirmEQ.setId(spFirmEQ.getEquipmentId());
                spFirmEQ.setSpecializedTradeId(specializedID);
                spFirmEQ.setSerialNo(i.toString());
                saveEQ(spFirmEQ, loggedInUser);
                //Save equipment attachment
                for (SpFirmEQAttachment spFirmEQA : spFirmEQ.getSpFirmEQAs()) {
                    spFirmEQA.setEquipmentId(spFirmEQId);
                    saveEQA(spFirmEQA, loggedInUser);
                }
            }
        }
        //region save applied service
        SpFirmAppliedS spFirmAS = new SpFirmAppliedS();
        spFirmAS.setSpecializedTradeId(specializedID);
        saveAppliedService(spFirmAS,loggedInUser);
        //endregion

        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your application for Registration Of Specialized Firm has been submitted and your application number is "+REFERENCE_NO+"<br>" +
                "You will receive an email with the Application summary.<br><br>" +
                "<b>You can track your application using above Application Number.</b> <br>" +
                "Thanks You.");

        String mailContent = "<b>Application No: "+REFERENCE_NO+" is submitted sucessfully on "+new Date()+" with Construction Development Board (CDB)." +
                "Applied Firm name : "+specializedFirm.getFirmName()
                +"This is to acknowledge for the registration of the Specialized Firm with Construction Development Board (CDB)." +
                " Your application will processed in due course. You can check the status of the application using CID no or Application number provided." +
                " You will also be notified via email when your application is approved." +
                "Thank You," +
                "(CDB)";
        MailSender.sendMail(specializedFirm.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application Registered Success");
        return responseMessage;
    }

    public ResponseMessage validation(SpFirmDTO spFirmDTO){
        SpecializedFirm specializedFirm = spFirmDTO.getSpecializedFirm();
        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        Boolean isEmailUnique = isEmailUnique(specializedFirm.getRegEmail());
        if(!isEmailUnique){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("This email has been already registered.");
            return responseMessage;
        }

        Boolean isFirmNameUnique = isFirmNameUnique(specializedFirm.getFirmName());
        if(!isFirmNameUnique){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("This firm name been already taken. Please choose another name.");
            return responseMessage;
        }

        if(!specializedFirm.getRegEmail().equals(specializedFirm.getConfirmEmail())){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Confirmation email does not match.");
            return responseMessage;
        }

        return responseMessage;
    }

    /**
     * To save specialized general information
     * @param specializedFirm    --  SpecializedFirm Entity
     * @param loggedInUser  --  Current logged in user
     * @return String  -- specializedID
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public String saveGI(SpecializedFirm specializedFirm, LoggedInUser loggedInUser) throws Exception {
        //region Save specialized
        String specializedID = commonService.getRandomGeneratedId();
        String referenceNo = commonService.getNextID("crpspecializedtrade", "ReferenceNo").toString();
        REFERENCE_NO = referenceNo;
        UPLOAD_LOC = "SpecializedFirm//"+referenceNo;
        specializedFirm.setApplicationDate(loggedInUser.getServerDate());
        specializedFirm.setWaiveOffLateFee(BigDecimal.ZERO);
        specializedFirm.setHasNotification("0");
        specializedFirm.setRegStatus("0");
        specializedFirm.setAppStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        specializedFirm.setCreatedBy(loggedInUser.getUserID());
        specializedFirm.setCreatedOn(loggedInUser.getServerDate());
        specializedFirm.setId(specializedID);
        specializedFirm.setCrpSpecializedTradeId(specializedID);
        specializedFirm.setReferenceNo(referenceNo);
        specializedFirm.setInitialDate(loggedInUser.getServerDate());
        if(specializedFirm.getpDzongkhagId()== null || specializedFirm.getpDzongkhagId().isEmpty()){
            specializedFirm.setpDzongkhagId(null);
        }
        dao.saveUpdate(specializedFirm);
        //endregion

        //region Save specialized HR
        for(SpFirmHR spFirmHR:specializedFirm.getSpFirmHRs()){
            String hrId = commonService.getRandomGeneratedId();
            spFirmHR.setId(hrId);
            spFirmHR.setSpecializedID(specializedID);
            spFirmHR.setIsPartnerOrOwner(TRUE_INT);
            saveHR(spFirmHR, loggedInUser);
        }
        //endregion
        return specializedID;
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
        dao.saveUpdate(cAttachment);
    }

    /**
     * To save specialized human resource
     * @param spFirmHR  -- SpFirmHR entity
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveHR(SpFirmHR spFirmHR,LoggedInUser loggedInUser) throws Exception {
        if(spFirmHR.getSiCertificate() == null){
            spFirmHR.setSiCertificate(0);
        }
        spFirmHR.setVerified(FALSE_INT);
        spFirmHR.setApproved(FALSE_INT);
        spFirmHR.setCreatedBy(loggedInUser.getUserID());
        spFirmHR.setCreatedOn(loggedInUser.getServerDate());
        dao.saveUpdate(spFirmHR);
    }

    /**
     * to save specialized human resource attachment
     * @param spFirmtHRA -- SpFirmtHRAttachment
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */

    @Transactional(readOnly = false)
    public void saveHRA(SpFirmtHRAttachment spFirmtHRA, LoggedInUser loggedInUser) throws Exception{
        MultipartFile attachment = spFirmtHRA.getAttachment();
        /*String documentName = contractorHRA.getDocumentName();
        if(documentName == null || documentName.isEmpty()){
            contractorHRA.setDocumentName("CV_UT_AT_"+i);
        }*/

        if(attachment != null) {
            String docNameUpload = spFirmtHRA.getDocumentName() + commonService.getFileEXT(attachment);
            String specificLoc = UPLOAD_LOC + "//HR";
            String docPath = commonService.uploadDocument(attachment, specificLoc, docNameUpload);
            spFirmtHRA.setDocumentPath(docPath);
            spFirmtHRA.setDocumentName(docNameUpload);
            spFirmtHRA.setFileType(attachment.getContentType());
        }
        if(emptyNullCheck(spFirmtHRA.getId())){
            String hrAttachmentID = commonService.getRandomGeneratedId();
            spFirmtHRA.setId(hrAttachmentID);
        }
        spFirmtHRA.setCreatedBy(loggedInUser.getUserID());
        spFirmtHRA.setCreatedOn(loggedInUser.getServerDate());
        dao.saveUpdate(spFirmtHRA);
    }

    /**
     * To save specialized category and classification
     * @param conCategory  --  SpFirmCategory entity
     * @param loggedInUser  -- Current Logged in user
     */

    @Transactional(readOnly = false)
    public void saveCC(SpFirmCategory conCategory, LoggedInUser loggedInUser) {
        String spFirmsultantCCId = commonService.getRandomGeneratedId();
        conCategory.setId(spFirmsultantCCId);
        conCategory.setCreatedBy(loggedInUser.getUserID());
        conCategory.setCreatedOn(loggedInUser.getServerDate());
        dao.saveUpdate(conCategory);
    }

    @Transactional(readOnly = false)
    public void savePayment(SpFirmRegPayment spFirmRegPayment, LoggedInUser loggedInUser) {
        FeeStructureDTO feeDTO = (FeeStructureDTO)dao.gFeeStructure(spFirmRegPayment.getAppliedCategoryId()).get(0);
        String id = commonService.getRandomGeneratedId();
        spFirmRegPayment.setId(id);
        spFirmRegPayment.setAmount(feeDTO.getRegistrationFee());
        spFirmRegPayment.setCreatedBy(loggedInUser.getUserID());
        spFirmRegPayment.setCreatedOn(loggedInUser.getServerDate());
        dao.saveUpdate(spFirmRegPayment);
    }
    /**
     * To save specialized equipment details
     * @param spFirmEQ  -- SpFirmEQ entity
     * @param loggedInUser  -- Current Logged in user
     */
    @Transactional(readOnly = false)
    public void saveEQ(SpFirmEQ spFirmEQ, LoggedInUser loggedInUser) {
        if(spFirmEQ.getApproved() == null) {
            spFirmEQ.setApproved(FALSE_INT);
        }
        if(spFirmEQ.getVerified() == null) {
            spFirmEQ.setVerified(FALSE_INT);
        }
        spFirmEQ.setCreatedBy(loggedInUser.getUserID());
        spFirmEQ.setCreatedOn(loggedInUser.getServerDate());
        dao.saveUpdate(spFirmEQ);
    }

    @Transactional(readOnly = false)
    public void saveAppliedService(SpFirmAppliedS spFirmAppliedS, LoggedInUser loggedInUser) {
        String id = commonService.getRandomGeneratedId();
        String appliedServiceId = null;
        if(spFirmAppliedS.getServiceTypeId() == null) {
            appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "1");
            spFirmAppliedS.setServiceTypeId(appliedServiceId);
        }
        spFirmAppliedS.setId(id);
        spFirmAppliedS.setCreatedBy(loggedInUser.getUserID());
        spFirmAppliedS.setCreatedOn(loggedInUser.getServerDate());
        dao.saveUpdate(spFirmAppliedS);
    }

    /**
     * to save specialized human resource attachment
     * @param spFirmEQA -- SpFirmEQAttachment
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveEQA(SpFirmEQAttachment spFirmEQA, LoggedInUser loggedInUser) throws Exception{
        MultipartFile attachment = spFirmEQA.getAttachment();

        String docName = spFirmEQA.getDocumentName()+commonService.getFileEXT(attachment);
        String specificLoc = UPLOAD_LOC+"//EQUIPMENT";
        String docPath = commonService.uploadDocument(attachment,specificLoc,docName);
        String eqAttachmentID = commonService.getRandomGeneratedId();
        spFirmEQA.setId(eqAttachmentID);
        spFirmEQA.setDocumentPath(docPath);
        spFirmEQA.setDocumentName(docName);
        spFirmEQA.setFileType(attachment.getContentType());
        spFirmEQA.setCreatedBy(loggedInUser.getUserID());
        spFirmEQA.setCreatedOn(loggedInUser.getServerDate());
        dao.saveUpdate(spFirmEQA);
    }
    public SpFirmDTOFetch getSpecializedFirmOngoingApp(String cdbNo){
        return dao.getSpecializedFirmOngoingApp(cdbNo);
    }

    @Transactional(readOnly = true)
    public SpecializedFirm getSpecializedFirm(String referenceNo){
        return dao.getSpecializedFirm(referenceNo);
    }

    @Transactional(readOnly = true)
    public List getTrainingDtl(String cidNo){
        return dao.getTrainingDtl(cidNo);
    }

    @Transactional(readOnly = true)
    public Boolean isEmailUnique(String email) {
        return dao.isEmailUnique(email);
    }

    @Transactional(readOnly = true)
    public Boolean isFirmNameUnique(String firmName) {
        return dao.isFirmNameUnique(firmName);
    }

    public SpFirmtHRAttachment getHRAttachmentFinal(String hraId) {
        return dao.getHRAttachmentFinal(hraId);
    }
}
