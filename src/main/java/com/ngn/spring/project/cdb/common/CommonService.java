package com.ngn.spring.project.cdb.common;

import com.ngn.spring.project.auth.LoginDTO;
import com.ngn.spring.project.base.BaseService;
import com.ngn.spring.project.cdb.architect.dao.ArchitectDao;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.architect.dto.ArchitectFeesDto;
import com.ngn.spring.project.cdb.common.dto.FileDetailDTO;
import com.ngn.spring.project.cdb.common.dto.PersonalInfoDTO;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTO;
import com.ngn.spring.project.cdb.engineer.dao.EngineerDao;
import com.ngn.spring.project.cdb.survey.dao.SurveyDao;
import com.ngn.spring.project.cdb.trade.dao.SpecializedDao;
import com.ngn.spring.project.cdb.trade.dto.TradeDto;
import com.ngn.spring.project.cdb.trade.dto.TradeFeesDto;
import com.ngn.spring.project.commonDto.TasklistDto;
import com.ngn.spring.project.lib.DropdownDTO;
import com.ngn.spring.project.lib.ResponseMessage;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.wso2.client.api.DCRC_CitizenDetailsAPI.DefaultApi;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizenDetailsResponse;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizendetailsObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Service
public class CommonService extends BaseService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private ArchitectDao arDao;

    @Autowired
    private SpecializedDao spDao;

    @Autowired
    private SurveyDao suDao;

    @Autowired
    private EngineerDao engineerDao;
    /**
     * To get the country list
     * @return List
     */

    @Transactional(readOnly = true)
    public List gCountryList(){
        return commonDao.gCountryList();
    }

    /**
     * To get the dzongkhag list
     * @return List
     */

    @Transactional(readOnly = true)
    public List gDzongkhagList(){
        return commonDao.gDzongkhagList();
    }

    /**
     * To get the dzongkhag list
     * @return List
     */

    @Transactional(readOnly = true)
    public List gCmnListItem(String cmnListId){
        return commonDao.gCmnListItem(cmnListId);
    }

    @Transactional(readOnly = true)
    public Object getNextID(String tblName, String colName){
        return commonDao.getNextID(tblName, colName);
    }

    /**
     * To get the a column value from certain table with filter
     * @param tblName -- table name to fetch data from
     * @param colName -- column name
     * @param filterCol -- filtering column
     * @param filterVal -- filtering value
     * @return data
     */
    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String filterCol, String filterVal ){
        return commonDao.getValue(tblName, colName,filterCol,filterVal);
    }
    /**
     * To get the a column value from certain table with multiple filter condition
     * @param tblName -- table name to fetch data from
     * @param colName -- column name
     * @param condition -- filtering condition, can include multiple condition with and or operator
     * @return data
     */
    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String condition ){
        return commonDao.getValue(tblName, colName,condition);
    }

    @Transactional(readOnly = true)
    public ResponseMessage getGewogList(String dzongkhagId){
        List gewogList = commonDao.getGewogList(dzongkhagId);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setDto(gewogList);
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public ResponseMessage getVillageList(String gewogId){
        List villageList = commonDao.getVillageList(gewogId);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setDto(villageList);
        return responseMessage;
    }

    public String getRandomGeneratedId(){
        return UUID.randomUUID().toString();
    }

    public String uploadDocument(MultipartFile attachment,String loc, String fileName) throws Exception{
        String rootPath = null;
        if (attachment != null && !attachment.isEmpty()) {
            //get file upload location from properties file
            Resource resource = new ClassPathResource("/properties/fileUpload.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            rootPath= props.getProperty("fileUpload.loc");

            rootPath = rootPath + loc + "//"+fileName;

            byte[] bytes = attachment.getBytes();
            Path path = Paths.get(rootPath);

            Path parentDir = path.getParent();
            if (!Files.exists(parentDir))
                Files.createDirectories(parentDir);
            Files.write(path, bytes);

        }
        return rootPath;
    }

    @Transactional(readOnly = true)
    public void viewDownloadFile(String tableName,String filterCol,String filterVal,HttpServletResponse response) throws Exception{
        FileDetailDTO fileDetailDTO = commonDao.getAttachmentDetail(tableName,filterCol,filterVal);
        if(fileDetailDTO != null) {

            File file = new File(fileDetailDTO.getFilePath());
            byte[] bFile = Files.readAllBytes(file.toPath());
            if(file.exists())
                try {
                    downloadFile(bFile,fileDetailDTO.getFileName(),response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

    }

    public void viewDownloadFile(String documentPath,HttpServletResponse response) throws Exception{
        if(documentPath != null) {
            //documentPath = documentPath.replaceAll("///","\\");
            File file = new File(documentPath);
            byte[] bFile = Files.readAllBytes(file.toPath());
            if(file.exists())
                try {
                    downloadFile(bFile,file.getName(),response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

    }

    public ResponseMessage downloadFile(byte[] file, String fileName, HttpServletResponse response) throws IOException {
        ResponseMessage responseMessage = new ResponseMessage();
        if (fileName == null || fileName.isEmpty()) {
            responseMessage.setStatus(0);
            responseMessage.setText("File Name is empty");
            return responseMessage;
        }
        if (file == null) {
            responseMessage.setStatus(0);
            responseMessage.setText("No file to download");
            return responseMessage;
        }

        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (fileExt) {
            case "xlsx":
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "xls":
                response.setContentType("application/vnd.ms-excel");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "docx":
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "doc":
                response.setContentType("application/msword");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "pdf":
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "inline:filename=" + fileName);
                break;
            case "png":
                response.setContentType("image/png");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "jpg":
                response.setContentType("image/jpeg");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "jpeg":
                response.setContentType("image/pjpeg");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "txt":
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
            case "csv":
                response.setContentType("text/csv");
                response.addHeader("Content-Disposition", "attachment:filename=" + fileName);
                break;
        }
        response.setContentLength(file.length);
        FileCopyUtils.copy(file, response.getOutputStream());
        responseMessage.setStatus(1);
        responseMessage.setText("File downloaded successfully.");

        return responseMessage;

    }


    public String getFileEXT(MultipartFile attachment){
        String originalFN = attachment.getOriginalFilename();
        return originalFN.substring(originalFN.lastIndexOf("."));
    }


    //TODO-- fetch from API DCR
    public ResponseMessage getPersonalInfo(String cid){
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String dcrcCitizenEndPointUrl =resourceBundle1.getString("getCitizenDetails.endPointURL");
        String dcrcCitizenaccessToken =resourceBundle1.getString("getCitizenDetails.accessToken");
        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO();

        String cdbNo=commonDao.getCDBNo(cid);
        if(!cdbNo.isEmpty()) {
            personalInfoDTO.setCdbNo(cdbNo);
            personalInfoDTO.setRemarks("This CID is already registered in one of the services.");
        }else{
            personalInfoDTO.setCdbNo("Not Registered");
        }
        try {
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
            httpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
            org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
            apiClient.setHttpClient(httpClient);
            apiClient.setBasePath(dcrcCitizenEndPointUrl);
            apiClient.setAccessToken(dcrcCitizenaccessToken);

            DefaultApi api = new DefaultApi(apiClient);
            CitizenDetailsResponse citizenDetailsResponse = api.citizendetailsCidGet(cid);
            CitizendetailsObj citizendetailsObj = citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().get(0);
            String dzongkhagIdDCRC = citizendetailsObj.getDzongkhagId();
            if(dzongkhagIdDCRC.length() == 1){
                dzongkhagIdDCRC = '0'+dzongkhagIdDCRC;
            }
            String dzonghagId=commonDao.getValue("cmndzongkhag","Id","Code",dzongkhagIdDCRC).toString();

            personalInfoDTO.setCidNo(cid);

            personalInfoDTO.setFullName(citizendetailsObj.getFirstName() + " " + citizendetailsObj.getMiddleName() + " " + citizendetailsObj.getLastName());
            personalInfoDTO.setFullName(personalInfoDTO.getFullName().replaceAll("null", ""));
            personalInfoDTO.setSex(citizendetailsObj.getGender());
            personalInfoDTO.setDzongkhagId(dzonghagId);
            personalInfoDTO.setDzongkhagNmae(citizendetailsObj.getDzongkhagName());
            personalInfoDTO.setGowegId(citizendetailsObj.getGewogId());
            personalInfoDTO.setGowegName(citizendetailsObj.getGewogName());
            personalInfoDTO.setVillageId(citizendetailsObj.getVillageSerialNo());
            personalInfoDTO.setVillageName(citizendetailsObj.getVillageName());
            personalInfoDTO.setDob(citizendetailsObj.getDob());

            responseMessage = new ResponseMessage();
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setDto(personalInfoDTO);
            return responseMessage;
        }catch(Exception e){
            personalInfoDTO.setFullName(" ");
            responseMessage = new ResponseMessage();
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Could not connect to DCRC API. Please wait for the connection OR enter the information correctly.");
            responseMessage.setDto(personalInfoDTO);
            return responseMessage;

           /* personalInfoDTO.setFullName("Full Name");
            personalInfoDTO.setSex("M");
            personalInfoDTO.setDzongkhagNmae("Thimphu");
            personalInfoDTO.setGowegId("3004");
            personalInfoDTO.setGowegName("Thimthrom");
            personalInfoDTO.setVillageId("4003");
            personalInfoDTO.setVillageName("Thimthrom");
            String dzong1=commonDao.getValue("cmndzongkhag","Id","NameEn","Thimphu").toString();
            personalInfoDTO.setDzongkhagId(dzong1);
            // System.out.print("Exception in CommonDaoImpl # getPersonalDetails: "+e);
            e.printStackTrace();*/

        }
    }

    public TasklistDto populateCount(String type,String registrationtype) {
        return commonDao.populateCount(type,registrationtype);
    }

    public String getCdbNo(LoginDTO loginDTO) {
        String app_type=commonDao.getAppType(loginDTO);
        return app_type;
    }

    public List<ArchitectFeesDto> getundertaking(String type){
        return arDao.getundertaking(type);
    }
    public ArchitectDto populateApplicantDetails(String cdbNo) {
        ArchitectDto dto=new ArchitectDto();
        dto=arDao.populateApplicantDetails(cdbNo);
        List<ArchitectFeesDto> terms=arDao.getundertaking("Architect_Renewal");
        dto.setTerms(terms);
        return dto;
    }

    public List<TasklistDto> getdashboardDetails(String type) {
        List<TasklistDto> dashboards=commonDao.getdashboardDetails(type);
        return dashboards;
    }

    public List<TasklistDto> populaterejectedApplications(String getCdbNoForSurvey, String getCdbNoForSp, String cdbdet) {
        List<TasklistDto> rejectedapplist=commonDao.populaterejectedApplications(cdbdet,getCdbNoForSurvey,getCdbNoForSp);
        return rejectedapplist;
    }

    public String getSpCdbNo(LoginDTO loginDTO) {
        String app_type=commonDao.getSpAppType(loginDTO);
        return app_type;
    }

        public TradeDto populateSpApplicantDetails(String cdbNo) {
            TradeDto dto=new TradeDto();
            dto=spDao.populateSpApplicantDetails(cdbNo);

           // List<TradeFeesDto> terms=spDao.getundertaking("Architect_Renewal");
            //dto.setTerms(terms);
            List<TradeFeesDto> terms=spDao.getAppliedServiceId(dto.getCrpSpecializedTradeId());
            dto.setTerms(terms);
            return dto;
    }

    public String getCdbNoForSp(LoginDTO loginDTO) {
        String app_type=commonDao.getCdbNoForSp(loginDTO);
        return app_type;
    }

    public String getCdbNoForSurvey(LoginDTO loginDTO) {
        String app_type=commonDao.getCdbNoForSurvey(loginDTO);
        return app_type;
    }

    public String getSurveyCdbNo(LoginDTO loginDTO) {
        String app_type=commonDao.getSurveyCdbNo(loginDTO);
        return app_type;
    }

    public ArchitectDto populateSurveyApplicantDetails(String cdbNo) {
        ArchitectDto dto=new ArchitectDto();
        dto=suDao.populateSurveyApplicantDetails(cdbNo);
        List<ArchitectFeesDto> terms=arDao.getundertaking("Survey_Renewal");
        dto.setTerms(terms);
        return dto;
    }

    @Transactional
    public Boolean isEmailUnique(String email) {
       return commonDao.isEmailUnique(email);
    }

    @Transactional
    public Boolean getFirmName(HttpServletRequest request) {
        return commonDao.getFirmName(request);
    }
    @Transactional
    public Boolean getFirmNameConsultant(HttpServletRequest request) {
        return commonDao.getFirmNameConsultant(request);
    }
    public String getOwnershipType(String appNo) {
        return commonDao.getOwnershipType(appNo);
    }

    @Transactional
    public List getModePayment() {
        return commonDao.getModePayment();
    }

    @Transactional
    public String insertuserDetails(PersonalInfoDTO dto1, String userID, HttpServletRequest request) {
        return commonDao.insertuserDetails(dto1,userID,request);
    }

    public BigInteger getCdbNoForConsultant(LoginDTO loginDTO) {
        BigInteger app_type=commonDao.getCdbNoForConsultant(loginDTO);
        return app_type;
    }

    public String getConsultantCdbNo(LoginDTO loginDTO) {
        String app_type=commonDao.getConsultantCdbNo(loginDTO);
        return app_type;
    }

    public ConsultantDTO populateConsultantApplicantDetails(String cdbNo) {
        ConsultantDTO dto=new ConsultantDTO();
       /* dto=consultantDao.populateConsultantApplicantDetails(cdbNo);
        *//*List<ArchitectFeesDto> terms=arDao.getundertaking("Survey_Renewal");
        dto.setTerms(terms);*//*
        List<OwnershipDTO> ownershipDTOs =consultantDao.getOwnerShipDtls(dto.getConsultantId());
        dto.setOwnershipDTOs(ownershipDTOs);

        List<ConsultantCategoryClassDTO> terms=consultantDao.getAppliedServiceId(dto.getConsultantId());
        dto.setTerms(terms);

        List<ConsultantAttachment> cAttachments = consultantDao.getConsultantAttachments(dto.getConsultantId());
        dto.setcAttachments(cAttachments);*/

        return dto;
    }

    @Transactional
    public Boolean checkIfMailExists(PersonalInfoDTO dto1, String userID, HttpServletRequest request) {
        return commonDao.checkIfMailExists(dto1);
    }

    @Transactional
    public List<DropdownDTO> getDropdownDetails(String sl_no, String type) {
        return commonDao.getDropdownDetails(sl_no, type);
    }

    @Transactional
    public String updatesysuser(PersonalInfoDTO dto1, String userID, String getfullname, HttpServletRequest request) {
        return commonDao.updatesysuser(dto1, userID, getfullname, request);
    }

    @Transactional
    public String getPreviousMail(PersonalInfoDTO dto1, String userID, HttpServletRequest request) {
        return commonDao.getPreviousMail(dto1,userID,request);
    }

    public ArchitectDto populateEngineerApplicantDetails(String cdbNo) {
        ArchitectDto dto=new ArchitectDto();
        dto=engineerDao.populateEngineerApplicantDetails(cdbNo);
        List<ArchitectFeesDto> terms=arDao.getundertaking("Architect_Renewal");
        dto.setTerms(terms);
        return dto;
    }

    @Transactional(readOnly = true)
    public List getTrackRecord(String cdbNo) {
        return commonDao.getTrackRecord(cdbNo);
    }

    @Transactional(readOnly = true)
    public boolean isExpiredApplication(String cdbNo){
        return cdbNo == null?false:commonDao.isExpiredApplication(cdbNo);

    }
}
