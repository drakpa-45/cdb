package com.ngn.spring.project.cdb.contractor.registration;

import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorDTOFetch;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.*;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.global.global.MailSender;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
public class ContractorNRService extends BaseService {
    private String UPLOAD_LOC;
    private String REFERENCE_NO;

    @Autowired
    private ContractorNRDao contractorNRDao;

    @Autowired
    private CommonService commonService;


    /**
     * To get the fee structure of the contractor based on category.
     * @param category -- contractor category. If passed null would get fee structure of all category
     * @return List
     */
    @Transactional(readOnly = true)
    public List gFeeStructure(String category){
        return contractorNRDao.gFeeStructure(category);
    }


    /**
     * To get the fee structure of the contractor based on category.
     * @return List
     */
    @Transactional(readOnly = true)
    public List gContractCategory(){
        return contractorNRDao.gContractCategory();
    }

    @Transactional(readOnly = true)
    public List gClassification(){
        return contractorNRDao.gClassification();
    }

    @Transactional(readOnly = true)
    public List gEquipment(){
        return contractorNRDao.gEquipment();
    }

    public ResponseMessage validation(ContractorDTO contractorDTO){
        Contractor contractor = contractorDTO.getContractor();
        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        Boolean isEmailUnique = isEmailUnique(contractor.getRegEmail());
        if(!isEmailUnique){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("This email has been already registered.");
            return responseMessage;
        }

        Boolean isFirmNameUnique = isFirmNameUnique(contractor.getFirmName());
        if(!isFirmNameUnique){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("This firm name been already taken. Please choose another name.");
            return responseMessage;
        }

        if(!contractor.getRegEmail().equals(contractor.getConfirmEmail())){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Confirmation email does not match.");
            return responseMessage;
        }

        return responseMessage;
    }


    /**
     * The main save method for contractor which calls specific save methods
     * @param contractorDTO   --  ContractorDTO
     * @param loggedInUser    --  Current logged in user
     * @return ResponseMessage  -- response message
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public ResponseMessage save(ContractorDTO contractorDTO, LoggedInUser loggedInUser) throws Exception {
        Contractor contractor = contractorDTO.getContractor();
        List<ContractorHR> contractorHRs = contractorDTO.getContractorHRs();
        List<ConCategory> categories = contractorDTO.getCategories();
        List<ContractorEQ> equipments = contractorDTO.getEquipments();

        responseMessage = validation(contractorDTO);
        if(responseMessage.getStatus() != SUCCESSFUL_STATUS){
            return responseMessage;
        }

        String contractorID = saveGI(contractor, loggedInUser);
        //region save attachment
        if(contractorDTO.getcAttachments() != null) {
            for (ContractorAttachment attachment : contractorDTO.getcAttachments()){
                attachment.setContractorId(contractorID);
                saveAttachment(attachment,loggedInUser);
            }

        }
        //endregion
        //region Save Contractor HR
        if(contractorHRs != null && !contractorHRs.isEmpty()) {
            for (ContractorHR contractorHR : contractorHRs) {
                String hrId = commonService.getRandomGeneratedId();
                contractorHR.setId(hrId);
                contractorHR.setContractorID(contractorID);
                contractorHR.setIsPartnerOrOwner(FALSE_INT);
                saveHR(contractorHR, loggedInUser);
                //Save Human resource attachment
                for (ContractorHRAttachment contractorHRA : contractorHR.getContractorHRAs()) {
                    contractorHRA.setContractorHrId(hrId);
                    saveHRA(contractorHRA, loggedInUser);
                }
            }
        }

        //endregion
        //region save contractor category
        categories.stream().filter(c->c.getProjectCateID()!= null).forEach(c->{
                    c.setContractorID(contractorID);
                    saveCC(c,loggedInUser);
                    //set Payment values
                    ContractorRegPayment contractorPayment = new ContractorRegPayment();
                    contractorPayment.setContractorId(contractorID);
                    contractorPayment.setCategoryId(c.getProjectCateID());
                    contractorPayment.setAppliedClassId(c.getAppliedClassID());
                    savePayment(contractorPayment, loggedInUser);
                }
        );
        //endregion
        Integer i = 0;
        if(equipments != null && !equipments.isEmpty()){
            for(ContractorEQ contractorEQ : equipments) {
                i++;
                String contractorEQId = commonService.getRandomGeneratedId();
                contractorEQ.setId(contractorEQId);
                contractorEQ.setContractorId(contractorID);
                contractorEQ.setSerialNo(i.toString());
                saveEQ(contractorEQ, loggedInUser);

                //Save Human resource attachment
                //Save equipment attachment
                for (ContractorEQAttachment contractorEQA : contractorEQ.getContractorEQAs()) {
                    contractorEQA.setEquipmentId(contractorEQId);
                    saveEQA(contractorEQA, loggedInUser);
                }
            }
        }
        //region save applied service
        ContractorAppliedS contractorAS = new ContractorAppliedS();
        contractorAS.setContractorId(contractorID);
        saveAppliedService(contractorAS,loggedInUser);
        //endregion
        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Your application for Registration Of Contractor has been submitted and your application number is <b>" + REFERENCE_NO + "</b><br>" +
                "You will receive an email with the Application summary.<br><br>" +
                "You can track your application using above Application Number. <br>" +
                "Thanks You.");
        String mailContent = "<b>Application No: "+REFERENCE_NO+" is submitted sucessfully on "+new Date()+" with Construction Development Board (CDB)." +
                "Applied Firm name : "+contractor.getFirmName()
                +"This is to acknowledge for the registration of the Contractor with Construction Development Board (CDB)." +
                " Your application will processed in due course. You can check the status of the application using CID no or Application number provided." +
                " You will also be notified via email when your application is approved." +
                "Thank You," +
                "(CDB)";
        MailSender.sendMail(contractor.getRegEmail(), "cdb@gov.bt", null, mailContent, "Application Registered Success");
        return responseMessage;
    }

    /**
     * To save contractor general information
     * @param contractor    --  Contractor Entity
     * @param loggedInUser  --  Current logged in user
     * @return String  -- contractorID
     * @throws Exception
     */

    @Transactional(readOnly = false)
    public String saveGI(Contractor contractor, LoggedInUser loggedInUser) throws Exception {
        //region Save contractor
        String contractorID = commonService.getRandomGeneratedId();
        String referenceNo = commonService.getNextID("crpcontractor", "ReferenceNo").toString();
        REFERENCE_NO = referenceNo;
        UPLOAD_LOC = "Contractor//"+referenceNo;
        contractor.setApplicationDate(loggedInUser.getServerDate());
        contractor.setWaiveOffLateFee(BigDecimal.ZERO);
        contractor.setHasNotification("0");
        contractor.setRegStatus("0");
        contractor.setAppStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        contractor.setCreatedBy(loggedInUser.getUserID());
        contractor.setCreatedOn(loggedInUser.getServerDate());
        contractor.setId(contractorID);
        contractor.setContractorId(contractorID);
        contractor.setReferenceNo(referenceNo);
        contractor.setInitialDate(loggedInUser.getServerDate());
        if(contractor.getpDzongkhagId()== null || contractor.getpDzongkhagId().isEmpty()){
            contractor.setpDzongkhagId(null);
        }

        contractorNRDao.saveUpdate(contractor);
        //endregion

        //region Save Contractor HR (Owner and Partner)
        for(ContractorHR contractorHR:contractor.getContractorHRs()){
            String hrId = commonService.getRandomGeneratedId();
            contractorHR.setId(hrId);
            contractorHR.setContractorID(contractorID);
            contractorHR.setIsPartnerOrOwner(TRUE_INT);
            saveHR(contractorHR, loggedInUser);
        }
        //endregion
        return contractorID;
    }

    @Transactional(readOnly = false)
    public void saveAttachment(ContractorAttachment cAttachment,LoggedInUser loggedInUser) throws Exception {
        MultipartFile attachment = cAttachment.getAttachment();
        String docName = cAttachment.getDocumentName()+commonService.getFileEXT(attachment);
        String specificLoc = UPLOAD_LOC+"//IncorporationCertificate";
        String docPath = commonService.uploadDocument(attachment, specificLoc, docName);
        String attachmentId = commonService.getRandomGeneratedId();
        cAttachment.setId(attachmentId);
        cAttachment.setDocumentPath(docPath);
        cAttachment.setDocumentName(docName);
        cAttachment.setFileType(attachment.getContentType());
        cAttachment.setCreatedBy(loggedInUser.getUserID());
        cAttachment.setCreatedOn(loggedInUser.getServerDate());
        contractorNRDao.saveUpdate(cAttachment);
    }

    /**
     * To save contractor human resource
     * @param contractorHR  -- ContractorHR entity
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveHR(ContractorHR contractorHR,LoggedInUser loggedInUser) throws Exception {
        if(contractorHR.getSiCertificate() == null){
            contractorHR.setSiCertificate(0);
        }
        contractorHR.setVerified(FALSE_INT);
        contractorHR.setApproved(FALSE_INT);
        contractorHR.setCreatedBy(loggedInUser.getUserID());
        contractorHR.setCreatedOn(loggedInUser.getServerDate());
        contractorNRDao.saveUpdate(contractorHR);
    }

    /**
     * to save contractor human resource attachment
     * @param contractorHRA -- ContractorHRAttachment
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveHRA(ContractorHRAttachment contractorHRA, LoggedInUser loggedInUser) throws Exception{
        MultipartFile attachment = contractorHRA.getAttachment();
        /*String documentName = contractorHRA.getDocumentName();
        if(documentName == null || documentName.isEmpty()){
            contractorHRA.setDocumentName("CV_UT_AT_"+i);
        }*/
        String docNameUpload = contractorHRA.getDocumentName()+commonService.getFileEXT(attachment);
        String specificLoc = UPLOAD_LOC+"//HR";
        String docPath = commonService.uploadDocument(attachment, specificLoc, docNameUpload);

        if(emptyNullCheck(contractorHRA.getId())){
            String hrAttachmentID = commonService.getRandomGeneratedId();
            contractorHRA.setId(hrAttachmentID);
        }

        contractorHRA.setDocumentPath(docPath);
        contractorHRA.setDocumentName(docNameUpload);
        contractorHRA.setFileType(attachment.getContentType());
        contractorHRA.setCreatedBy(loggedInUser.getUserID());
        contractorHRA.setCreatedOn(loggedInUser.getServerDate());
        contractorNRDao.saveUpdate(contractorHRA);
    }

    /**
     * To save contractor category and classification
     * @param conCategory  --  ConCategory entity
     * @param loggedInUser  -- Current Logged in user
     */
    @Transactional(readOnly = false)
    public void saveCC(ConCategory conCategory, LoggedInUser loggedInUser) {
        String contractorCCId = commonService.getRandomGeneratedId();
        conCategory.setId(contractorCCId);
        conCategory.setCreatedBy(loggedInUser.getUserID());
        conCategory.setCreatedOn(loggedInUser.getServerDate());
        contractorNRDao.saveUpdate(conCategory);

    }

    @Transactional(readOnly = false)
    public void savePayment(ContractorRegPayment contractorRegPayment, LoggedInUser loggedInUser) {
        FeeStructureDTO feeDTO = (FeeStructureDTO) contractorNRDao.gFeeStructure(contractorRegPayment.getAppliedClassId()).get(0);
        String id = commonService.getRandomGeneratedId();
        contractorRegPayment.setId(id);
        contractorRegPayment.setAppliedAmount(feeDTO.getRegistrationFee());
        contractorRegPayment.setCreatedBy(loggedInUser.getUserID());
        contractorRegPayment.setCreatedOn(loggedInUser.getServerDate());
        contractorNRDao.saveUpdate(contractorRegPayment);
    }
    /**
     * To save contractor equipment details
     * @param contractorEQ  -- ContractorEQ entity
     * @param loggedInUser  -- Current Logged in user
     */
    @Transactional(readOnly = false)
    public void saveEQ(ContractorEQ contractorEQ, LoggedInUser loggedInUser) {
        if(contractorEQ.getApproved() == null){
            contractorEQ.setApproved(FALSE_INT);
        }
        if(contractorEQ.getVerified() == null){
            contractorEQ.setVerified(FALSE_INT);
        }

        contractorEQ.setCreatedBy(loggedInUser.getUserID());
        contractorEQ.setCreatedOn(loggedInUser.getServerDate());
        contractorNRDao.saveUpdate(contractorEQ);
    }

    @Transactional(readOnly = false)
    public void saveAppliedService(ContractorAppliedS contractorAppliedS, LoggedInUser loggedInUser) {
        String id = commonService.getRandomGeneratedId();
        String appliedServiceId = null;
        if(contractorAppliedS.getServiceTypeId() == null) {
            appliedServiceId = (String) commonService.getValue("crpservice", "Id", "ReferenceNo", "1");
            contractorAppliedS.setServiceTypeId(appliedServiceId);
        }
        contractorAppliedS.setId(id);
        contractorAppliedS.setCreatedBy(loggedInUser.getUserID());
        contractorAppliedS.setCreatedOn(loggedInUser.getServerDate());

        contractorNRDao.saveUpdate(contractorAppliedS);
    }




    /**
     * to save contractor human resource attachment
     * @param contractorEQA -- ContractorHRAttachment
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveEQA(ContractorEQAttachment contractorEQA, LoggedInUser loggedInUser) throws Exception{
        MultipartFile attachment = contractorEQA.getAttachment();

        String docName = contractorEQA.getDocumentName()+commonService.getFileEXT(attachment);
        String specificLoc = UPLOAD_LOC+"//EQUIPMENT";
        String docPath = commonService.uploadDocument(attachment,specificLoc,docName);
        String eqAttachmentID = commonService.getRandomGeneratedId();
        contractorEQA.setId(eqAttachmentID);
        contractorEQA.setDocumentPath(docPath);
        contractorEQA.setDocumentName(docName);
        contractorEQA.setFileType(attachment.getContentType());
        contractorEQA.setCreatedBy(loggedInUser.getUserID());
        contractorEQA.setCreatedOn(loggedInUser.getServerDate());
        contractorNRDao.saveUpdate(contractorEQA);
    }

    @Transactional(readOnly = true)
    public Contractor getContractor(String referenceNo){
        return contractorNRDao.getContractor(referenceNo);
    }

    public ContractorDTOFetch getContractorOngoingApp(String cdbNo){
        return contractorNRDao.getContractorOngoingApp(cdbNo);
    }

    @Transactional(readOnly = true)
    public List getTrainingDtl(String cidNo){
        return contractorNRDao.getTrainingDtl(cidNo);
    }

    @Transactional(readOnly = true)
    public Boolean isEmailUnique(String email) {
        return contractorNRDao.isEmailUnique(email);
    }

    @Transactional(readOnly = true)
    public Boolean isFirmNameUnique(String firmName) {
        return contractorNRDao.isFirmNameUnique(firmName);
    }
}
