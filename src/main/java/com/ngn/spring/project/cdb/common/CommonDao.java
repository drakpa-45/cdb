package com.ngn.spring.project.cdb.common;

import com.ngn.spring.project.auth.LoginDTO;
import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.common.dto.*;
import com.ngn.spring.project.cdb.trade.dto.TradeFeesDto;
import com.ngn.spring.project.commonDto.TasklistDto;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.DropdownDTO;
import com.ngn.spring.project.lib.ResponseMessage;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Repository
public class CommonDao extends BaseDao {
    @Transactional(readOnly = true)
    public List gCountryList() {
        sqlQuery = properties.getProperty("CommonDao.gCountryList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List gDzongkhagList() {
        sqlQuery = properties.getProperty("CommonDao.gDzongkhagList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List gCmnListItem(String cmnListId) {
        sqlQuery = properties.getProperty("CommonDao.gCmnListItem");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("cmnListId", cmnListId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List getGewogList(String dzongkhagId) {
        sqlQuery = properties.getProperty("CommonDao.getGewogList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("dzongkhagId",dzongkhagId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List getVillageList(String gewogId) {
        sqlQuery = properties.getProperty("CommonDao.getVillageList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("gewogId",gewogId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public Object getNextID(String tblName, String colName) {
        sqlQuery = "select ("+colName+")+1 from "+tblName+" order by "+colName + " desc";
        hQuery = hibernateQuery(sqlQuery);
        return hQuery.list() == null?1:hQuery.list().get(0);
    }

    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String filterCol, String filterVal) {
        sqlQuery = "select "+colName+" from "+tblName+" where "+filterCol+ "='"+filterVal+"'";
        hQuery = hibernateQuery(sqlQuery);List obj = hQuery.list();
        return obj.isEmpty()?null:obj.get(0);
    }

    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String condition) {
        sqlQuery = "select "+colName+" from "+tblName+" where "+condition;
        hQuery = hibernateQuery(sqlQuery);List obj = hQuery.list();
        return obj.isEmpty()?null:obj.get(0);
    }

    @Transactional(readOnly = true)
    public FileDetailDTO getAttachmentDetail(String tableName,String filterCol,String filterVal) {
        //sqlQuery = properties.getProperty("CommonDao.getAttachmentDetail");
        sqlQuery = "SELECT a.DocumentName fileName,a.DocumentPath filePath,a.FileType fileType FROM "+tableName+" a WHERE "+filterCol+"=:filterVal";
        hQuery = hibernateQuery(sqlQuery,FileDetailDTO.class)
                //.setParameter("tableName",tableName)
                .setParameter("filterVal", filterVal);
        List obj = hQuery.list();
        return obj.isEmpty()?null:(FileDetailDTO)obj.get(0);
    }

    @Transactional(readOnly = true)
    public TasklistDto populateCount(String type,String registrationtype) {
        TasklistDto dto = new TasklistDto();
        if(type.equalsIgnoreCase("crparchitect")){
            String resubmitquery=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
            Query resubmitgroup = sqlQuery(resubmitquery).setParameter(1, registrationtype);
            if(resubmitgroup.list().size()>0)
                dto.setGroupTaskCount((BigInteger) resubmitgroup.list().get(0));
            String registrationquery=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a LEFT JOIN crparchitectappliedservice s ON s.`CrpArchitectId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
            Query registrationqueryes = sqlQuery(registrationquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
            if(registrationqueryes.list().size()>0)
                dto.setRegistration((BigInteger) registrationqueryes.list().get(0));

            String renwalquery=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a LEFT JOIN crparchitectappliedservice s ON s.`CrpArchitectId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
            Query renwalquerye = sqlQuery(renwalquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
            if(renwalquerye.list().size()>0)
                dto.setRenewal((BigInteger) renwalquerye.list().get(0));

            String cancellaitonquery=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a LEFT JOIN crparchitectappliedservice s ON s.`CrpArchitectId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
            Query cancellaitonquerye = sqlQuery(cancellaitonquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
            if(cancellaitonquerye.list().size()>0)
                dto.setCancellation((BigInteger) cancellaitonquerye.list().get(0));

        }else if(type.equalsIgnoreCase("crpspecializedTrade")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpspecializedtrade a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
            Query resubmitgroup = sqlQuery(resubmitquery).setParameter(1, registrationtype);
            if(resubmitgroup.list().size()>0)
                dto.setGroupTaskCount((BigInteger) resubmitgroup.list().get(0));

            String registrationquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpspecializedtrade a LEFT JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query registrationqueryes = sqlQuery(registrationquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
            if(registrationqueryes.list().size()>0)
                dto.setRegistration((BigInteger) registrationqueryes.list().get(0));

            String renwalquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpspecializedtrade a LEFT JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query renwalquerye = sqlQuery(renwalquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
            if(renwalquerye.list().size()>0)
                dto.setRenewal((BigInteger) renwalquerye.list().get(0));

            String cancellaitonquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpspecializedtrade a LEFT JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query cancellaitonquerye = sqlQuery(cancellaitonquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
            if(cancellaitonquerye.list().size()>0)
                dto.setCancellation((BigInteger) cancellaitonquerye.list().get(0));
        }else if(type.equalsIgnoreCase("crpconsultant")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpconsultant a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
            Query resubmitgroup = sqlQuery(resubmitquery).setParameter(1, registrationtype);
            if(resubmitgroup.list().size()>0)
                dto.setGroupTaskCount((BigInteger) resubmitgroup.list().get(0));

            String registrationquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpconsultant a LEFT JOIN crpconsultantappliedservice s ON s.CrpConsultantId=a.CrpConsultantId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query registrationqueryes = sqlQuery(registrationquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
            if(registrationqueryes.list().size()>0)
                dto.setRegistration((BigInteger) registrationqueryes.list().get(0));

            String renwalquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpconsultant a LEFT JOIN crpconsultantappliedservice s ON s.CrpConsultantId=a.CrpConsultantId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query renwalquerye = sqlQuery(renwalquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
            if(renwalquerye.list().size()>0)
                dto.setRenewal((BigInteger) renwalquerye.list().get(0));

            String cancellaitonquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpconsultant a LEFT JOIN crpconsultantappliedservice s ON s.CrpConsultantId=a.CrpConsultantId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query cancellaitonquerye = sqlQuery(cancellaitonquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
            if(cancellaitonquerye.list().size()>0)
                dto.setCancellation((BigInteger) cancellaitonquerye.list().get(0));
        }else if(type.equalsIgnoreCase("crpsurvey")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpsurvey a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
            Query resubmitgroup = sqlQuery(resubmitquery).setParameter(1, registrationtype);
            if(resubmitgroup.list().size()>0)
                dto.setGroupTaskCount((BigInteger) resubmitgroup.list().get(0));

            String registrationquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpsurvey a LEFT JOIN crpsurveyappliedservice s ON s.CrpSurveyId=a.CrpSurveyId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query registrationqueryes = sqlQuery(registrationquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
            if(registrationqueryes.list().size()>0)
                dto.setRegistration((BigInteger) registrationqueryes.list().get(0));

            String renwalquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpsurvey a LEFT JOIN crpsurveyappliedservice s ON s.CrpSurveyId=a.CrpSurveyId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query renwalquerye = sqlQuery(renwalquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
            if(renwalquerye.list().size()>0)
                dto.setRenewal((BigInteger) renwalquerye.list().get(0));

            String cancellaitonquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpsurvey a LEFT JOIN crpsurveyappliedservice s ON s.CrpSurveyId=a.CrpSurveyId WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query cancellaitonquerye = sqlQuery(cancellaitonquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
            if(cancellaitonquerye.list().size()>0)
                dto.setCancellation((BigInteger) cancellaitonquerye.list().get(0));
        }else if(type.equalsIgnoreCase("crpcontractor")){
            String resubmitquery="SELECT COUNT(a.`ReferenceNo`)appcount FROM crpcontractor a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
            Query resubmitgroup = sqlQuery(resubmitquery).setParameter(1, registrationtype);
            if(resubmitgroup.list().size()>0)
                dto.setGroupTaskCount((BigInteger) resubmitgroup.list().get(0));

            String registrationquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpcontractor a LEFT JOIN crpcontractorappliedservice s ON s.CrpContractorId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query registrationqueryes = sqlQuery(registrationquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
            if(registrationqueryes.list().size()>0)
                dto.setRegistration((BigInteger) registrationqueryes.list().get(0));

            String renwalquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpcontractor a LEFT JOIN crpcontractorappliedservice s ON s.CrpContractorId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query renwalquerye = sqlQuery(renwalquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
            if(renwalquerye.list().size()>0)
                dto.setRenewal((BigInteger) renwalquerye.list().get(0));

            String cancellaitonquery="SELECT COUNT(a.ReferenceNo)appcount FROM crpcontractor a LEFT JOIN crpcontractorappliedservice s ON s.CrpContractorId=a.Id WHERE a.CmnApplicationRegistrationStatusId=? AND s.CmnServiceTypeId=?";
            Query cancellaitonquerye = sqlQuery(cancellaitonquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
            if(cancellaitonquerye.list().size()>0)
                dto.setCancellation((BigInteger) cancellaitonquerye.list().get(0));
        }
        return dto;
    }

    @Transactional
    public String getAppType(LoginDTO loginDTO) {
        String cdbNo="";
        String query="SELECT COALESCE(CONCAT('Architect999', f.ARNo),CONCAT('Contractor999', c.CDBNo),CONCAT('Consultant999', cons.CDBNo),CONCAT('SpecializedFirm999', sp.SPNo),CONCAT('Engineer999',ef.CDBNo)) cdbNo FROM sysuser s LEFT JOIN crparchitectfinal f ON f.SysUserId = s.Id LEFT JOIN crpcontractorfinal c ON c.SysUserId = s.Id LEFT JOIN crpconsultantfinal cons ON cons.SysUserId = s.Id LEFT JOIN crpspecializedtradefinal sp ON sp.SysUserId = s.Id LEFT JOIN crpengineerfinal ef ON ef.SysUserId=s.Id  WHERE s.username =?";
        cdbNo = (String)sqlQuery(query).setParameter(1, loginDTO.getUsername()).uniqueResult();
        return cdbNo;
    }

    @Transactional
    public List<TasklistDto> getdashboardDetails(String type) {
        /*MessageFor field
				 1=CRPS,2=Etool,3=CiNet,4=Contractors,5=Consultant,6=Architects,7=Engineers,8=Specilazed Trade Users*/
        if(type.equalsIgnoreCase("Architect")){
            type="6";
        }
        List<TasklistDto> retval=new ArrayList<TasklistDto>();
        try {
            sqlQuery="SELECT Message messages FROM `sysnewsandnotification` WHERE MessageFor=? ";
            Query queryre = sqlQuery(sqlQuery, TasklistDto.class).setParameter(1, type);
            if(queryre.list().size()>0){
                retval=queryre.list();
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # getdashboardDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplications(String getCdbNoForSp, String getCdbNoForSurvey, String cdbdet) {
        List<TasklistDto> retval=new ArrayList<TasklistDto>();
        try {
            if(cdbdet.split("999")[0].equalsIgnoreCase("Architect")){
                sqlQuery="SELECT a.`ReferenceNo` Application_Number,a.`ApplicationDate` appDate,a.`ARNo` cdbNo,a.`RejectedDate` rejectedDate,a.`RemarksByRejector` remarks FROM crparchitect a WHERE a.`CmnApplicationRegistrationStatusId`='de662a61-b049-11e4-89f3-080027dcfac6' AND a.`ARNo`=? ";
            }
            if(cdbdet.split("999")[0].equalsIgnoreCase("Consultant")){
                sqlQuery="SELECT a.ReferenceNo Application_Number,a.ApplicationDate appDate,a.CDBNo cdbNo,a.RejectedDate rejectedDate,a.RemarksByRejector remarks FROM crpconsultant a WHERE a.CmnApplicationRegistrationStatusId = 'de662a61-b049-11e4-89f3-080027dcfac6' AND a.CDBNo =?";
            }
            Query queryre = sqlQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbdet.split("999")[1]);
            if(queryre.list().size()>0){
                retval=queryre.list();
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # getdashboardDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String getSpAppType(LoginDTO loginDTO) {
        String cdbNo="";
        String specializedquery=" SELECT f.SPNo cdbNo FROM sysuser s LEFT JOIN crpspecializedtradefinal f ON f.SysUserId=s.Id WHERE s.username=? ";
        Query spNo = sqlQuery(specializedquery).setParameter(1, loginDTO.getUsername());
        if(spNo.list().size()>0)
            cdbNo="SpecializedTrade999"+ spNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    @Transactional
    public String getCdbNoForSp(LoginDTO loginDTO) {
        String cdbNo="";
        String specializedquery="SELECT f.SPNo cdbNo FROM sysuser s LEFT JOIN crpspecializedtradefinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query spNo = sqlQuery(specializedquery).setParameter(1, loginDTO.getUsername());
                            return (String) spNo.list().get(0);
    }

    @Transactional
    public String getCdbNoForSurvey(LoginDTO loginDTO) {
        String cdbNo="";
        String surveyquery="SELECT f.ARNo cdbNo FROM sysuser s LEFT JOIN crpsurveyfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query spNo = sqlQuery(surveyquery).setParameter(1, loginDTO.getUsername());
        return (String) spNo.list().get(0);
    }

    @Transactional
    public String getSurveyCdbNo(LoginDTO loginDTO) {
        String cdbNo="";
        String architectquery="SELECT f.ARNo cdbNo FROM sysuser s LEFT JOIN crpsurveyfinal f ON f.SysUserId=s.Id WHERE s.username=? ";
        Query arNo = sqlQuery(architectquery).setParameter(1, loginDTO.getUsername());
        if(arNo.list().size()>0)
            cdbNo="Survey999"+ arNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    public Boolean isEmailUnique(String email) {
        sqlQuery = properties.getProperty("SurveyDao.isEmailUnique");
        return hibernateQuery(sqlQuery).setParameter("email",email).list().isEmpty();
    }

    public Boolean getFirmName(HttpServletRequest request) {
        sqlQuery = properties.getProperty("SpecializedDao.getFirmName");
        return hibernateQuery(sqlQuery).setParameter(1,request.getParameter("firmName")).list().isEmpty();
    }

    public Boolean getFirmNameConsultant(HttpServletRequest request) {
        sqlQuery = properties.getProperty("ConsultantDao.getFirmName");
        return hibernateQuery(sqlQuery).setParameter(1,request.getParameter("firmName")).list().isEmpty();
    }

    @Transactional
    public String getOwnershipType(String appNo) {
        String surveyquery="SELECT s.CmnOwnershipTypeId FROM crpspecializedtrade s WHERE s.ReferenceNo=?";
        Query ownertype = sqlQuery(surveyquery).setParameter(1, appNo);
        return (String) ownertype.list().get(0);
    }

    public List getModePayment() {
        String sqlQuery = "SELECT pt.SlNo arrayId, pt.Mode_Of_Payment name FROM payment_table pt";
        Query hquery=hibernateQuery(sqlQuery,TradeFeesDto.class);
        return hquery.list();
    }

    public String insertuserDetails(PersonalInfoDTO dto, String userID, HttpServletRequest request) {
        String return_value="Insert_Fail",actorId="";
        try {
            String generateID = UUID.randomUUID().toString();
            String digit="1234567890";
            StringBuilder salt=new StringBuilder();
            Random rnd=new Random();
            while (salt.length()<4){
                int index=(int) (rnd.nextFloat() * digit.length());
                salt.append(digit.charAt(index));
            }
            String saltString=salt.toString();
            String pw_hash= BCrypt.hashpw(saltString, BCrypt.gensalt());

            Query query1 = sqlQuery("INSERT INTO sysuser (ContactNo,CreatedBy,CreatedOn,Email,FullName,Id,PASSWORD,STATUS,username) VALUES(?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?) ");
            query1.setParameter(1, dto.getRegMobileNo()).setParameter(2, userID).setParameter(3, dto.getRegEmail()).setParameter(4, dto.getFullname()).setParameter(5, generateID).setParameter(6, pw_hash).setParameter(7, "1").setParameter(8, dto.getRegEmail());
            int save = query1.executeUpdate();
            if (save > 0) {
                System.out.print("Password: "+saltString+"("+pw_hash+") is generated against user:"+dto.getRegEmail());
                return_value= generateID+"/"+saltString;
            }

        } catch (Exception e) {
            System.out.print("Exception in commonDao # insertuserDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public BigInteger getCdbNoForConsultant(LoginDTO loginDTO) {
        sqlQuery = properties.getProperty("ConsultantDao.getCDBNo");
        List resultList = hibernateQuery(sqlQuery).setParameter(1, loginDTO.getUsername()).list();
        return (BigInteger) (resultList.isEmpty() ? 1 : resultList.get(0));
    }

    @Transactional
    public String getConsultantCdbNo(LoginDTO loginDTO) {
        String cdbNo="";
        String consquery="SELECT f.CDBNo cdbNo FROM sysuser s LEFT JOIN crpconsultantfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query arNo = sqlQuery(consquery).setParameter(1, loginDTO.getUsername());
        if(arNo.list().size()>0)
            cdbNo="Consultant999"+ arNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    public Boolean checkIfMailExists(PersonalInfoDTO dto1) {
        sqlQuery = properties.getProperty("ConsultantDao.checkIfMailExists");
        return hibernateQuery(sqlQuery).setParameter(1,dto1.getRegEmail()).list().isEmpty();
    }

    public List<DropdownDTO> getDropdownDetails(String sl_no, String type) {
        List<DropdownDTO> dropDownList = new ArrayList<DropdownDTO>();
        try {
            if (type.equalsIgnoreCase("gewog_list")) {
                String sqlQuery = "SELECT g.Gewog_Serial_No AS value, CONCAT(g.Gewog_Name,'/',g.Gewog_Name_Bh) AS text FROM cmngewog g WHERE Dzongkhag_Serial_No=:Type_Id";
                Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("Type_Id", sl_no);
                dropDownList = hQuery.list();
            } else if (type.equalsIgnoreCase("village_list")) {
                String sqlQuery = "SELECT v.Village_Serial_No AS value, CONCAT(v.Village_Name,'/',v.Village_Name_Bh)AS text FROM cmnvillage v WHERE Gewog_Serial_No=:Type_Id";
                Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("Type_Id", sl_no);
                dropDownList = hQuery.list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dropDownList;
    }

    public String updatesysuser(PersonalInfoDTO dto1, String userID, String getfullname, HttpServletRequest request) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("UPDATE sysuser s SET s.EditedOn=CURRENT_DATE,s.EditedBy=?,s.Email=?,s.FullName=?,s.username=? WHERE s.username = ?");
            query1.setParameter(1,userID).setParameter(2,dto1.getRegEmail()).setParameter(3,getfullname).setParameter(4,dto1.getRegEmail()).setParameter(5,dto1.getId());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }else {
                return_value = "failed";
            }
        }catch (Exception e) {
            System.out.print("Exception in ConsultantDao # updatesysuser: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String getPreviousMail(PersonalInfoDTO dto1, String userID, HttpServletRequest request) {
        String previousMail="";
        try {
            sqlQuery = "SELECT s.FullName FROM sysuser s WHERE s.Email=?";
            previousMail = (String) hibernateQuery(sqlQuery).setParameter(1, dto1.getId()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in ConsultantDao # getPreviousMail:" + e);
            e.printStackTrace();
            return previousMail="Failed";
        }
        return previousMail;
    }

    @Transactional
    public String getCDBNo(String cid) {
        String cdbNo="", consquery="",existingCDBNo="";
try {
    consquery = "SELECT ARNo FROM crparchitectfinal WHERE CIDNo =?";
    existingCDBNo = (String)hibernateQuery(consquery).setParameter(1, cid).uniqueResult();
    if(existingCDBNo !=null) {
        cdbNo = existingCDBNo;
    }
    consquery = "SELECT CDBNo FROM crpengineerfinal WHERE CIDNo =?";
    existingCDBNo = (String)hibernateQuery(consquery).setParameter(1, cid).uniqueResult();
    if(existingCDBNo !=null) {
        cdbNo = existingCDBNo;
    }

    consquery = "SELECT SPNo FROM crpspecializedtradefinal WHERE CIDNo =?";
    existingCDBNo = (String)hibernateQuery(consquery).setParameter(1, cid).uniqueResult();
    if(existingCDBNo !=null) {
        cdbNo = existingCDBNo;
    }
    consquery = "SELECT ARNo FROM crpsurveyfinal WHERE CIDNo =?";
    existingCDBNo = (String)hibernateQuery(consquery).setParameter(1, cid).uniqueResult();
    if(existingCDBNo !=null) {
        cdbNo = existingCDBNo;
    }

    }catch (Exception e){
    e.printStackTrace();
    }
        return cdbNo;
    }

    public List getTrackRecord(String cdbNo) {
        sqlQuery = properties.getProperty("CommonDao.getTrackRecord");
        hQuery = hibernateQuery(sqlQuery, TrackRecordDTO.class).setParameter("cdbNo",cdbNo);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public boolean isExpiredApplication(String cdbNo) {
        String type = cdbNo.split("999")[0];
        cdbNo = cdbNo.split("999")[1];
        if(type.equalsIgnoreCase("Contractor")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredContractor");
        }else if(type.equalsIgnoreCase("Consultant")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredConsultant");
        }else if(type.equalsIgnoreCase("SpecializedFirm")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredSpecializedFirm");
        }else if(type.equalsIgnoreCase("Engineer")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredEngineer");
        } else if(type.equalsIgnoreCase("Survey")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredSurveyor");
        }else if(type.equalsIgnoreCase("Architect")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredArchitect");
        }else if(type.equalsIgnoreCase("SpecializedTrade")){
            sqlQuery = properties.getProperty("CommonDao.isExpiredSpecializedTrade");
        }
       // BigInteger bigIntValue = (BigInteger)hibernateQuery(sqlQuery).setParameter("cdbNo", cdbNo).list().get(0);
       Integer bigIntValue = (Integer) hibernateQuery(sqlQuery).setParameter("cdbNo", cdbNo).list().get(0);

        return (bigIntValue.intValue() == 1);
    }

    @Transactional
    public String deleteFromSysuser(ArchitectDto dto1) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("DELETE FROM sysuser WHERE username =?");
            query1.setParameter(1, dto1.getEmail());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # deleteFromSysuser: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public List<EmployeeDetailsDTO> validateWorkEngagementCidNo(String cidNo) {
       // sqlQuery = properties.getProperty("ConsultantRCActionDao.getEmployeeDetailsFromCDB");
        List<EmployeeDetailsDTO> employeeDetailsDTOs = new ArrayList<>();
        sqlQuery = properties.getProperty("CommonDao.validateWorkEngagementCidNo");
         employeeDetailsDTOs =(List<EmployeeDetailsDTO>) hibernateQuery(sqlQuery, EmployeeDetailsDTO.class).setParameter("cidNo", cidNo).list();

        return employeeDetailsDTOs;
    }

    @Transactional
    public String getCdbNoForContractor(LoginDTO loginDTO) {
        String cdbNo="";
        String consquery="SELECT f.CDBNo cdbNo FROM sysuser s LEFT JOIN crpcontractorfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query arNo = sqlQuery(consquery).setParameter(1, loginDTO.getUsername());
        if(arNo.list().size()>0)
            cdbNo="Contractor999"+ arNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    @Transactional
    public List<TasklistDto> populateapplicationHistoryConsultant(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,\n" +
                    "b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpconsultant a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpConsultantId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpconsultantappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpConsultantId\n" +
                    ") e ON e.CrpConsultantId = a.CrpConsultantId \n" +
                    "WHERE a.CDBNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # populateapplicationHistoryConsultant: " + e);
            e.printStackTrace();
        }
        return dto;
    }
    @Transactional
    public List<TasklistDto> populateapplicationHistoryContractor(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,\n" +
                    "b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpcontractor a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpContractorId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpcontractorappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpContractorId\n" +
                    ") e ON e.CrpContractorId = a.CrpContractorId \n" +
                    "WHERE a.CDBNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # populateapplicationHistoryContractor: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplicationConsultant(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,a.RemarksByRejector remarks,\n" +
                    "b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpconsultant a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpConsultantId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpconsultantappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpConsultantId\n" +
                    ") e ON e.CrpConsultantId = a.CrpConsultantId \n" +
                    "WHERE a.CmnApplicationRegistrationStatusId = 'de662a61-b049-11e4-89f3-080027dcfac6' AND a.CDBNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # populaterejectedApplicationConsultant: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplicationContractor(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,a.RemarksByRejector remarks,\n" +
                    "b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpcontractor a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpContractorId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpcontractorappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpContractorId\n" +
                    ") e ON e.CrpContractorId = a.CrpContractorId \n" +
                    "WHERE a.CmnApplicationRegistrationStatusId = 'de662a61-b049-11e4-89f3-080027dcfac6' AND a.CDBNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # populaterejectedApplicationContractor: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String getCdbNoForArchitect(LoginDTO loginDTO) {
        String cdbNo="";
        String surveyquery="SELECT f.ARNo cdbNo FROM sysuser s LEFT JOIN crparchitectfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query spNo = sqlQuery(surveyquery).setParameter(1, loginDTO.getUsername());
        return (String) spNo.list().get(0);
    }

    @Transactional
    public String getArchitectCDBNo(LoginDTO loginDTO) {
        String cdbNo="";
        String architectquery="SELECT f.ARNo cdbNo FROM sysuser s LEFT JOIN crparchitectfinal f ON f.SysUserId=s.Id WHERE s.username=? ";
        Query arNo = sqlQuery(architectquery).setParameter(1, loginDTO.getUsername());
        if(arNo.list().size()>0)
            cdbNo="Architect999"+ arNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    @Transactional
    public String getCdbNoForEngineer(LoginDTO loginDTO) {
        String cdbNo="";
        String surveyquery="SELECT f.CDBNo cdbNo FROM sysuser s LEFT JOIN crpengineerfinal f ON f.SysUserId=s.Id WHERE s.username=?";
        Query spNo = sqlQuery(surveyquery).setParameter(1, loginDTO.getUsername());
        return (String) spNo.list().get(0);
    }

    @Transactional
    public String getEngineerCDBNo(LoginDTO loginDTO) {
        String cdbNo="";
        String architectquery="SELECT f.CDBNo cdbNo FROM sysuser s LEFT JOIN crpengineerfinal f ON f.SysUserId=s.Id WHERE s.username=? ";
        Query arNo = sqlQuery(architectquery).setParameter(1, loginDTO.getUsername());
        if(arNo.list().size()>0)
            cdbNo="Engineer999"+ arNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    @Transactional
    public List<GovCopDTO> validateCorporateCidNo(String cidNo) {
        List<GovCopDTO> dto=new ArrayList<GovCopDTO>();
        sqlQuery = properties.getProperty("CommonDao.validateCorporateCidNo");
        dto =(List<GovCopDTO>) hibernateQuery(sqlQuery, GovCopDTO.class).setParameter("cidNo", cidNo).list();
        return dto;
    }

    public List<CdbDTO> validatePartnerCidNoFromCDBdatabase(String cid) {
        return null;
    }

    @Transactional(readOnly = true)
    public Boolean isUsenameExist(String username) {
        sqlQuery = properties.getProperty("CommonDao.isUsenameExist");
        return hibernateQuery(sqlQuery).setParameter("username", username).list().isEmpty();
    }

    public ResponseMessage updatePhoneNumber(LoginDTO loginDTO, String phoneNumber) {
        ResponseMessage responseMessage = null;

        String getCdbNoForSp = getCdbNoForSp(loginDTO);
        String getCdbNoForSurvey = getCdbNoForSurvey(loginDTO);
        String getCdbNoForArchitect = getCdbNoForArchitect(loginDTO);
        String getCdbNoForEngineer= getCdbNoForEngineer(loginDTO);
        String getConsultantCdbNo = getConsultantCdbNo(loginDTO);
        String getCdbNoForContractor = getCdbNoForContractor(loginDTO);
        String cdbdet="";int save=0;
        if(getCdbNoForSp != null){
           // if(getCdbNoForSp.contains("SP-") || getCdbNoForSp.contains("SF-")) {
                org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtradefinal SET MobileNo = ? WHERE Email = ?");
                query1.setParameter(1, phoneNumber).setParameter(2, loginDTO.getUsername());
                 save = query1.executeUpdate();
           // }
        } else if(getCdbNoForSurvey != null) {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpsurveyfinal SET MobileNo = ? WHERE Email = ?");
            query1.setParameter(1, phoneNumber).setParameter(2, loginDTO.getUsername());
             save = query1.executeUpdate();
        }else if(getCdbNoForEngineer != null) {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpengineerfinal SET MobileNo = ? WHERE Email = ?");
            query1.setParameter(1, phoneNumber).setParameter(2, loginDTO.getUsername());
             save = query1.executeUpdate();
        }else if(getCdbNoForArchitect != null) {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crparchitectfinal SET MobileNo = ? WHERE Email = ?");
            query1.setParameter(1, phoneNumber).setParameter(2, loginDTO.getUsername());
             save = query1.executeUpdate();
        }else if(getConsultantCdbNo!=null){
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpconsultantfinal SET MobileNo = ? WHERE Email = ?");
            query1.setParameter(1, phoneNumber).setParameter(2, loginDTO.getUsername());
             save = query1.executeUpdate();
        }else if(getCdbNoForContractor!=null){
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpcontractorfinal SET MobileNo = ? WHERE Email = ?");
            query1.setParameter(1, phoneNumber).setParameter(2, loginDTO.getUsername());
             save = query1.executeUpdate();
        }
        if (save > 0) {
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }

    public ResponseMessage updatePassword(LoginDTO loginDTO, String username, String newPwd) {
      ResponseMessage responseMessage=null;
        String digit=newPwd;
        StringBuilder salt=new StringBuilder();
        Random rnd=new Random();
        while (salt.length()<4){
            int index=(int) (rnd.nextFloat() * digit.length());
            salt.append(digit.charAt(index));
        }
        String saltString=salt.toString();
        String pw_hash= BCrypt.hashpw(saltString, BCrypt.gensalt());
        try {
            Query query1 = sqlQuery("UPDATE sysuser s SET s.password=? WHERE s.username = ?");
            query1.setParameter(1, pw_hash).setParameter(2,loginDTO.getUsername());
             query1.executeUpdate();
        }catch (Exception e) {
            System.out.print("Exception in ConsultantDao # updatesysuser: " + e);
            e.printStackTrace();
        }
        return responseMessage;
    }
}


