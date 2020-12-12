package com.ngn.spring.project.cdb.architect.dao;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.TasksDTO;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.architect.dto.ArchitectFeesDto;
import com.ngn.spring.project.cdb.architect.entity.ArchitectDocument;
import com.ngn.spring.project.cdb.architect.entity.CrparchitectEntity;
import com.ngn.spring.project.cdb.architect.entity.CrparchitectFinalEntity;
import com.ngn.spring.project.cdb.architect.entity.ServiceEntity;
import com.ngn.spring.project.cdb.certification.CertificateDTO;
import com.ngn.spring.project.commonDto.TasklistDto;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by USER on 3/19/2020.
 */
@Repository
public class ArchitectDao extends BaseDao {
    public List gFeeStructure(String architect) {
        sqlQuery = properties.getProperty("ArchitectDao.gFeeStructure");
        return hibernateQuery(sqlQuery, ArchitectFeesDto.class).list();
    }
    @Transactional
    public BigInteger getMaxId() {
        sqlQuery = properties.getProperty("ArchitectDao.getMaxId");
        return (BigInteger) hibernateQuery(sqlQuery).uniqueResult();
    }
    @Transactional
    public void save(CrparchitectEntity engineer) {
        saveOrUpdate(engineer);
    }

    @Transactional
    public void saveAservies(ServiceEntity engineer) {
        saveOrUpdate(engineer);
    }
    @Transactional
    public void saveAttchment(ArchitectDocument engineerAttachment) {
        saveOrUpdate(engineerAttachment);
    }

    @Transactional(readOnly = true)
    public List getTaskList(String status,String type,String userId,String servicetype) {
        if(type=="Group"){
            sqlQuery = properties.getProperty("ArchitectDao.getTaskList");
            return hibernateQuery(sqlQuery, TasksDTO.class).setParameter(1, servicetype).setParameter(2, status).list();
        }
        else{
            sqlQuery = properties.getProperty("ArchitectDao.getMyTaskList");
            return hibernateQuery(sqlQuery, TasksDTO.class).setParameter(1, userId).setParameter(2, servicetype).setParameter(3, status).list();
        }

    }

    @Transactional(readOnly = false)
    public String assignMyTask(String appNo, String lockUserId, String type) {
        String lockByUserId = "";
        if(type.equalsIgnoreCase("release")){
            lockByUserId =null;
        }else{
            lockByUserId=lockUserId;
        }
        sqlQuery = properties.getProperty("ArchitectDao.send2MyOrGroupTask");
        int save = hibernateQuery(sqlQuery).setParameter("appNo", appNo) .setParameter("lockUserId", lockByUserId).executeUpdate();
        if(save>0){
            return "Success";
        }else{
            return "Failed";
        }
    }

    @Transactional(readOnly = false)
    public ArchitectDto getArchitetDetails(String appNo) {
        ArchitectDto dto =new ArchitectDto();
        sqlQuery = properties.getProperty("ArchitectDao.getArchiectDtls");
        dto=(ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, appNo).list().get(0);
        return dto;
    }

    @Transactional(readOnly = false)
    public List<ArchitectDocument> getdocumentList(String crpArchitectId) {
        List<ArchitectDocument> doclist=new ArrayList<ArchitectDocument>();
        sqlQuery = properties.getProperty("ArchitectDao.getArchiectDoc");
        doclist= hibernateQuery(sqlQuery, ArchitectDocument.class).setParameter(1, crpArchitectId).list();
        return doclist;
    }

    @Transactional
    public ArchitectDto updateReject(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crparchitect SET RemarksByRejector=?,RejectedDate= CURRENT_TIMESTAMP,SysRejectorUserId=?,CmnApplicationRegistrationStatusId=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.REJECTED.getCode()).setParameter(4, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # updateReject: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public ArchitectDto updateVerification(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crparchitect SET RemarksByVerifier=?,VerifiedDate= CURRENT_TIMESTAMP,SysVerifierUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.VERIFIED.getCode()).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # updateVerification: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String generateArchitectNo(String countryId,String artype) {
        String architectNo="", selectquery="";
        String firstpart="",secondpart="";
        try {
            if(countryId.equalsIgnoreCase("Bhutan")){
                firstpart="BA-";
                if(artype.equalsIgnoreCase("Government")){
                    secondpart="(G)";
                    selectquery="SELECT MAX(ARNo) cdbNo FROM crparchitectfinal WHERE ARNo NOT LIKE 'N%' AND ARNo LIKE '%(G)%'";
                }
                else{
                    secondpart="(P)";
                    selectquery="SELECT MAX(ARNo) cdbNo FROM crparchitectfinal WHERE ARNo NOT LIKE 'N%' AND ARNo LIKE '%(P)%'";
                }
            }
            else{
                firstpart="NB-";
                if(artype.equalsIgnoreCase("Government")){
                    secondpart="(G)";
                    selectquery="SELECT MAX(ARNo) cdbNo FROM crparchitectfinal WHERE ARNo LIKE 'N%' AND ARNo LIKE '%(G)%'";
                }
                else{
                    secondpart="(P)";
                    selectquery="SELECT MAX(ARNo) cdbNo FROM crparchitectfinal WHERE ARNo LIKE 'N%' AND ARNo LIKE '%(P)%'";
                }
            }
            String curr_architectNo=  (String) hibernateQuery(selectquery).list().get(0);
            if(curr_architectNo==null){
                architectNo=firstpart+"001"+secondpart;
            }
            else{
                int num=Integer.parseInt(curr_architectNo.replaceAll("\\D+",""));
                num++;
                if(String.valueOf(num).length()==1){
                    architectNo=firstpart+"00"+num+secondpart;
                }
                else if(String.valueOf(num).length()==2){
                    architectNo=firstpart+"0"+num+secondpart;
                }
                else{
                    architectNo=firstpart+num+secondpart;
                }
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # approveArchitectRegistration: " + e);
            e.printStackTrace();
        }
        return architectNo;
    }

    @Transactional
    public ArchitectDto updateApplicationForPayment(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crparchitect SET RemarksByPaymentApprover=?,PaymentApprovedDate= CURRENT_TIMESTAMP,SysPaymentApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=?,PaymentReceiptNo=?,PaymentReceiptDate=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.APPROVED.getCode()).setParameter(4, null).setParameter(5, dto.getPaymentReceiptNo()).setParameter(6, dto.getPaymentReceiptDate()).setParameter(7, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # approveArchitectRegistration: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String insertInPaymentDetails(ArchitectDto dto, String userID, HttpServletRequest request) {
        String retval="";
        try {
            if(dto.getPaymentmode().equalsIgnoreCase("Not Applicable")){
                retval = "Success";
            }else {
                org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crparchitectregistrationpayment (Id,CrpArchitectFinalId,Amount,CreatedBy,Mode_Of_Payment,CreatedOn) VALUES(?,(SELECT Id FROM crparchitectfinal WHERE  ReferenceNo =?),?,?,?,CURRENT_TIMESTAMP) ");
                query1.setParameter(1, UUID.randomUUID().toString()).setParameter(2, dto.getReferenceNo()).setParameter(3, dto.getTotalAmt()).setParameter(4, userID).setParameter(5, dto.getPaymentmode());
                int save = query1.executeUpdate();
                if (save > 0) {
                    retval = "Success";
                }
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # insertInPaymentDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public ArchitectDto getarchitectapplicationdetails(ArchitectDto dto) {
       // ArchitectDto dt=new ArchitectDto();
        try {
            sqlQuery = properties.getProperty("ArchitectDao.getarchitectregDetails");
            dto=(ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, dto.getReferenceNo()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # getarchitectapplicationdetails: " + e);
            e.printStackTrace();
        }
        return dto;
    }
    @Transactional
    public String insertuserDetails(ArchitectDto dto, String userID, HttpServletRequest request) {
        String return_value="Insert_Fail";
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

            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO sysuser (ContactNo,CreatedBy,CreatedOn,Email,FullName,Id,PASSWORD,STATUS,username) VALUES(?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?) ");
            query1.setParameter(1, dto.getMobileNo()).setParameter(2, userID).setParameter(3, dto.getEmail()).setParameter(4, dto.getFullname()).setParameter(5, generateID).setParameter(6, pw_hash).setParameter(7, "1").setParameter(8, dto.getEmail());
            int save = query1.executeUpdate();
            if (save > 0) {
                System.out.print("Password: "+saltString+"("+pw_hash+") is generated against user:"+dto.getEmail());
                return_value= generateID+"/"+saltString;
            }

        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # insertuserDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public String insertArchitedtFinalDetails(ArchitectDto dto, String userID, String sysuserId) {
        String return_value="",actorId="";
        if(dto.getServiceTypeId().equalsIgnoreCase("registration")){
            actorId=sysuserId;
        }else if(dto.getServiceSectorType().equalsIgnoreCase("renewal") || dto.getServiceSectorType().equalsIgnoreCase("cancel")){
            actorId=userID;
        }
        try {
            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crparchitectfinal (ApplicationDate,ARNo,CIDNo,CmnApplicationRegistrationStatusId,CmnCountryId,CmnDzongkhagId,CmnQualificationId,CmnSalutationId,CmnServiceSectorTypeId," +
                    "CmnUniversityCountryId,CreatedBy,Email,EmployerAddress,EmployerName,Gewog,GraduationYear,Id,MobileNo,Name," +
                    "NameOfUniversity,ReferenceNo,RegistrationApprovedDate,RegistrationExpiryDate,RemarksByFinalApprover,SysUserId," +
                    "SysFinalApproverUserId,Village,CmnTradeId,InitialDate,SysFinalApprovedDate,CreatedOn) VALUES(?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE,CURRENT_DATE,CURRENT_TIMESTAMP)");
            query1.setParameter(1, dto.getApplicationDate()).setParameter(2, dto.getCdbNo()).setParameter(3, dto.getCidNo()).setParameter(4,  ApplicationStatus.APPROVED.getCode()).setParameter(5, dto.getCountryId()).setParameter(6, dto.getDzongkhagId()).setParameter(7, dto.getQualificationId()).setParameter(8, dto.getSalutation()).setParameter(9, dto.getServiceSectorTypeId())
                    .setParameter(10, dto.getUniversityCountry()).setParameter(11, userID).setParameter(12, dto.getEmail()).setParameter(13, dto.getEmployeeAddress()).setParameter(14, dto.getEmployeeName()).setParameter(15, dto.getGewog()).setParameter(16, dto.getGraduationyr().toString().substring(0,4)).setParameter(17, dto.getCrpArchitectId()).setParameter(18, dto.getMobileNo()).setParameter(19, dto.getFullname())
                    .setParameter(20, dto.getUniversityName()).setParameter(21, dto.getReferenceNo()).setParameter(22, dto.getApprovaldate()).setParameter(23,dto.getRegExpDate()).setParameter(24,dto.getRemarks()).setParameter(25, sysuserId)
                    .setParameter(26, userID).setParameter(27, dto.getVillage()).setParameter(28,dto.getTrade());
            int save = query1.executeUpdate();
            if(save>0) {
                org.hibernate.query.Query querydoc = sqlQuery("INSERT INTO `crparchitectattachmentfinal` (`Id`,`CrpArchitectFinalId`,`DocumentName`,`DocumentPath`,`FileType`,`CreatedBy`,`CreatedOn`) SELECT a.`Id`,a.`CrpArchitectId`,a.`DocumentName`,a.`DocumentPath`,a.`FileType`,?,CURRENT_TIMESTAMP FROM `crparchitectattachment` a  LEFT JOIN `crparchitect` ar ON a.`CrpArchitectId`=ar.`Id` WHERE ar.`ReferenceNo`=? ");
                querydoc.setParameter(1, userID).setParameter(2, dto.getReferenceNo());
                save = querydoc.executeUpdate();
                return_value = "Success";
            } else{
                return_value="failed";
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # insertArchitedtFinalDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public List<ArchitectDto> getPrintList() {
        sqlQuery = properties.getProperty("ArchitectDao.getPrintList");
        return hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, ApplicationStatus.APPROVED.getCode()).list();
    }

    @Transactional
    public ArchitectDto populateApplicantDetails(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery="SELECT f.ARNo cdbNo,f.ReferenceNo refNo,f.Id CrpArchitectId,f.CIDNo cidNo,f.Name fullname,f.Village village,f.Gewog gewog,f.CmnDzongkhagId cmnDzongkhagId,d.NameEn dzongkhagId,c.Name countryId,s.Name salutation,\n" +
                    "i.Name updateStatus,f.CmnSalutationId salutationId,f.Email email,f.MobileNo mobileNo,f.EmployerName employeeName,f.EmployerAddress employeeAddress,f.CmnQualificationId qualificationId, q.Name qualification,f.CmnServiceSectorTypeId serviceSectorTypeId,\n" +
                    "f.GraduationYear graduationyr,t.Name serviceSectorType,td.Name trade, f.CmnTradeId cmnTradeId,f.NameOfUniversity universityName,f.CmnUniversityCountryId cmnUniversityCountryId,uc.Name universityCountry,f.RegistrationApprovedDate registrationApproveDate,f.RegistrationExpiryDate regExpDate\n" +
                    "FROM crparchitectfinal f \n" +
                    "LEFT JOIN cmnlistitem i ON i.Id=f.CmnApplicationRegistrationStatusId \n" +
                    "LEFT JOIN cmnlistitem s ON s.Id=f.CmnSalutationId\n" +
                    "LEFT JOIN cmncountry c ON c.Id=f.CmnCountryId\n" +
                    "LEFT JOIN cmncountry uc ON uc.Id=f.CmnUniversityCountryId\n" +
                    "LEFT JOIN cmnlistitem q ON q.Id=f.CmnQualificationId LEFT JOIN cmnlistitem t ON t.Id=f.CmnServiceSectorTypeId \n" +
                    "LEFT JOIN cmndzongkhag d ON d.Id=f.CmnDzongkhagId LEFT JOIN cmnlistitem td ON td.Id=f.CmnTradeId WHERE f.ARNo = ? ";
            dto=(ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # populateApplicantDetails: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public ArchitectDto checkOngoingApplication(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery="SELECT f.`ARNo` cdbNo,i.`Name` updateStatus,f.`ApplicationDate` applicationDate,f.`ReferenceNo` ReferenceNo FROM crparchitect f LEFT JOIN cmnlistitem i ON i.`Id`=f.`CmnApplicationRegistrationStatusId` WHERE i.`Id` IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND f.`ARNo`=? ";
            //dto=(ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo).list();
            Query queryre = sqlQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo);
            if(queryre.list().size()>0){
                dto=(ArchitectDto) queryre.list().get(0);
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # checkOngoingApplication: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<ArchitectFeesDto> getundertaking(String type) {
        List<ArchitectFeesDto> dto = new ArrayList<ArchitectFeesDto>();
        try {
            sqlQuery="SELECT u.`Details` name FROM `cmnundertaking` u WHERE u.`Type`=?";
            Query queryre = sqlQuery(sqlQuery, ArchitectFeesDto.class).setParameter(1, type);
            if(queryre.list().size()>0){
                dto=queryre.list();
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # getundertaking: " + e);
            e.printStackTrace();
        }
        return dto;
    }
    @Transactional
    public ArchitectDto getarchitectDetails(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery="SELECT f.`CIDNo` cidNo,f.`ARNo` cdbNo,f.`RegistrationApprovedDate` registrationApproveDate,f.`RegistrationExpiryDate` regExpDate,f.`Name` fullname,f.`CmnSalutationId` salutation,f.`CmnDzongkhagId` dzongkhagId,f.`Village` village,f.`Gewog` gewog,f.`CmnServiceSectorTypeId` serviceSectorType,f.`CmnCountryId` countryId,f.`Email` email,f.`MobileNo` mobileNo,f.`EmployerName` employeeName,f.`EmployerAddress` employeeAddress,f.`CmnQualificationId` qualificationId,f.`GraduationYear` graduationyr,f.`CmnUniversityCountryId` universityCountry,f.`NameOfUniversity` universityName FROM crparchitectfinal f  WHERE f.`ARNo`=? ";
            Query queryre = sqlQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo);
            if(queryre.list().size()>0){
                dto=(ArchitectDto) queryre.list().get(0);
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # checkOngoingApplication: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    public ArchitectDocument getDocumentDetailsByDocId(String uploadDocId) {
        ArchitectDocument dto=new ArchitectDocument();
        try {
            String GET_DOCUMENT_DTLS_BY_UUID="SELECT a.`DocumentName` documentName,a.`DocumentPath` documentPath,a.`CrpArchitectId` architectid,a.`FileType` fileType FROM crparchitectattachment a WHERE a.`Id`=? ";
            Query query = sqlQuery(GET_DOCUMENT_DTLS_BY_UUID,ArchitectDocument.class).setParameter(1, uploadDocId);
            dto = (ArchitectDocument) query.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String updateRenewalDetails(ArchitectDto dto1, String userID, int interval) {
        String return_value="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crparchitectfinal s SET s.`RegistrationExpiryDate`=DATE_ADD(CURRENT_DATE, INTERVAL ? YEAR),s.`EditedBy`=?,s.`EditedOn`=CURRENT_TIMESTAMP,s.`ReRegistrationRemarks`=?,s.`ReRegistrationDate`=CURRENT_DATE WHERE s.`ARNo`=? ");
            query1.setParameter(1, interval).setParameter(2, userID).setParameter(3, dto1.getRemarks()).setParameter(4,  dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value="Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # approveArchitectRegistration: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public String insertInPaymentServiceDetails(ArchitectDto dto, String userID) {
        String retval="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crparchitectservicepayment (Id,CrpArchitectId,CmnServiceTypeId,NoOfDaysLate,NoOfDaysAfterGracePeriod,PenaltyPerDay,CreatedBy,PaymentAmount,TotalAmount,CreatedOn) VALUES(?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP) ");
            query1.setParameter(1, UUID.randomUUID().toString()).setParameter(2, dto.getCrpArchitectId()).setParameter(3, dto.getServiceTypeId()).setParameter(4, dto.getNoOfDaysLate()).setParameter(5, dto.getNoOfDaysAfterGracePeriod()).setParameter(6, "100").setParameter(7,userID).setParameter(8,dto.getPaymentAmt()).setParameter(9,dto.getTotalAmt());
            int save = query1.executeUpdate();
            if (save > 0) {
                retval= "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # insertInPaymentDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String updatePaymentServiceDetails(ArchitectDto dto, String userID) {
        dto.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
        String retval = "";
        try {
            Query query1 = sqlQuery("UPDATE crparchitectservicepayment SET TotalAmount = ?,PaymentAmount = ?,Mode_Of_Payment = ? WHERE CrpArchitectId = ?");
            query1.setParameter(1,dto.getTotalAmt()).setParameter(2, dto.getPaymentAmt()).setParameter(3, dto.getPaymentmode()).setParameter(4, dto.getCrpArchitectId());
            int save = query1.executeUpdate();
            if (save > 0) {
                retval = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # updatePaymentServiceDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String updateCancellationDetails(ArchitectDto dto1, String userID) {
        String return_value="";
        try {
            org.hibernate.query.Query query1 =  sqlQuery("UPDATE crparchitectfinal s SET s.`CmnApplicationRegistrationStatusId`=?,s.`EditedBy`=?,s.`EditedOn`=CURRENT_TIMESTAMP,s.`DeregisteredRemarks`=?,s.`DeRegisteredDate`=CURRENT_DATE WHERE s.`ARNo`=? ");
            query1.setParameter(1, ApplicationStatus.DEREGISTERED.getCode()).setParameter(2, userID).setParameter(3, dto1.getRemarks()).setParameter(4,  dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value="Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # approveArchitectRegistration: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String isMailUnique(HttpServletRequest request) {
        String isMailUnique="";
        try {
            sqlQuery = "SELECT a.Email FROM sysuser a WHERE a.Email = ?";
            isMailUnique = (String) hibernateQuery(sqlQuery).setParameter(1, request.getParameter("mailId")).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # isMailUnique:" + e);
            e.printStackTrace();
        }
        return isMailUnique;
    }

    @Transactional
    public ArchitectDto updateApproval(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            int validity_year=2;
            String applicationstatus="";
            if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
                applicationstatus= ApplicationStatus.APPROVED.getCode();
            }
            else if(dto.getServiceSectorType().equalsIgnoreCase("Government")){
                validity_year=5;
                applicationstatus= ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
            }
            else{
                applicationstatus= ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
            }
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crparchitect SET RemarksByApprover=?,RegistrationApprovedDate= CURRENT_TIMESTAMP,RegistrationApprovedDate=CURRENT_TIMESTAMP,RegistrationExpiryDate=DATE_ADD(CURRENT_DATE, INTERVAL "+validity_year+" YEAR),SysApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, applicationstatus).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # approveArchitectRegistration: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public CrparchitectFinalEntity getArchitectFinal(String cdbNo) {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<CrparchitectFinalEntity> cQuery = builder.createQuery(CrparchitectFinalEntity.class);
        Root<CrparchitectFinalEntity> root = cQuery.from(CrparchitectFinalEntity.class);
        cQuery.select(root).where(builder.equal(root.get("arNo"), cdbNo));
        CrparchitectFinalEntity architectFinal = getCurrentSession().createQuery(cQuery).getSingleResult();
        em.detach(architectFinal);
        return architectFinal;
    }

    public ArchitectDto getArchitectOngoingApp(String cdbNo) {
        sqlQuery = properties.getProperty("ArchitectDao.getArchitectOngoingApp");
        List list = hibernateQuery(sqlQuery, ArchitectDto.class).setParameter("cdbNo", cdbNo).list();
        return (ArchitectDto)(list.isEmpty()?null:list.get(0));
    }

    @Transactional
    public String deletePrevRecord(ArchitectDto dto1) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("DELETE FROM crparchitectfinal WHERE ARNo =?");
            query1.setParameter(1, dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # deletePrevRecord: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public CertificateDTO getArchitetPrintDetails(HttpServletRequest request, String cdbNo) {
        CertificateDTO dto =new CertificateDTO();
        sqlQuery = properties.getProperty("ArchitectDao.getArchitetPrintDetails");
        dto=(CertificateDTO) hibernateQuery(sqlQuery, CertificateDTO.class).setParameter(1, cdbNo).list().get(0);
        return dto;
    }

    public String isCIDUnique(String cidNo) {
        String isCIDUnique = "";
        try {
            sqlQuery = "SELECT c.CmnApplicationRegistrationStatusId FROM crparchitect c WHERE c.CIDNo=?";
            isCIDUnique = (String) hibernateQuery(sqlQuery).setParameter(1, cidNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # isCIDUnique:" + e);
            e.printStackTrace();
        }
        return isCIDUnique;
    }

    @Transactional
    public List<TasklistDto> populateapplicationHistoryArchitect(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT \n" +
                    "a.ReferenceNo applicationNo,\n" +
                    "a.ApplicationDate appDate,\n" +
                    "b.Name AS appStatus, \n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services'\n" +
                    "END AS serviceName\n" +
                    "FROM\n" +
                    "crparchitectfinal a \n" +
                    "INNER JOIN cmnlistitem b  \n" +
                    "ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crparchitectappliedservice s \n" +
                    "ON s.CrpArchitectId = a.Id WHERE a.ARNo =?\n" +
                    "ORDER BY a.ReferenceNo DESC;";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # populateapplicationHistoryArchitect: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplicationArchitect(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate appDate,b.Name AS appStatus, \n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services'\n" +
                    "END AS serviceName\n" +
                    "FROM\n" +
                    "crparchitect a \n" +
                    "INNER JOIN cmnlistitem b  \n" +
                    "ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crparchitectappliedservice s \n" +
                    "ON s.CrpArchitectId = a.Id WHERE a.CmnApplicationRegistrationStatusId = 'de662a61-b049-11e4-89f3-080027dcfac6' AND  s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' OR s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' AND a.ARNo = ?\n" +
                    "ORDER BY a.ReferenceNo DESC";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # populaterejectedApplicationArchitect: " + e);
            e.printStackTrace();
        }
        return dto;
    }
}

