package com.ngn.spring.project.cdb.engineer.dao;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.TasksDTO;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.architect.dto.ArchitectFeesDto;
import com.ngn.spring.project.cdb.architect.entity.CrparchitectEntity;
import com.ngn.spring.project.cdb.certification.CertificateDTO;
import com.ngn.spring.project.cdb.engineer.model.CrpengineerFinalEntity;
import com.ngn.spring.project.cdb.engineer.model.Engineer;
import com.ngn.spring.project.cdb.engineer.model.EngineerAppliedServiceEntity;
import com.ngn.spring.project.cdb.engineer.model.EngineerAttachment;
import com.ngn.spring.project.cdb.survey.entity.SurveyDocument;
import com.ngn.spring.project.cdb.survey.entity.SurveyServiceEntity;
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by USER on 3/19/2020.
 */
@Repository
public class EngineerDao extends BaseDao {
    public List gFeeStructure(String Engineer) {
        sqlQuery = properties.getProperty("EngineerDao.gFeeStructure");
        return hibernateQuery(sqlQuery, ArchitectFeesDto.class).list();
    }
    @Transactional
    public BigInteger getMaxId() {
        sqlQuery = properties.getProperty("EngineerDao.getMaxId");
        return (BigInteger) hibernateQuery(sqlQuery).uniqueResult();
    }

    @Transactional
    public void save(Engineer engineer) {
        saveOrUpdate(engineer);
    }

    @Transactional
    public void saveSservies(EngineerAppliedServiceEntity engineer) {
        saveOrUpdate(engineer);
    }

    @Transactional
    public void saveAttchment(EngineerAttachment engineerAttachment) {
        saveOrUpdate(engineerAttachment);
    }

    @Transactional(readOnly = true)
    public List getTaskList(String status, String type, String userId, String servicetype) {
        if (type == "Group") {
            sqlQuery = properties.getProperty("EngineerDao.getTaskList");
            return hibernateQuery(sqlQuery, TasksDTO.class).setParameter(1, servicetype).setParameter(2, status).list();
        } else {
            sqlQuery = properties.getProperty("EngineerDao.getMyTaskList");
            return hibernateQuery(sqlQuery, TasksDTO.class).setParameter(1, userId).setParameter(2, servicetype).setParameter(3, status).list();
        }
    }

    @Transactional(readOnly = false)
    public String assignMyTask(String appNo, String lockUserId, String type) {
        String lockByUserId = "",assignMyTask="";
        if(type.equalsIgnoreCase("release")){

            lockByUserId =null;
        }else{
            lockByUserId=lockUserId;
        }
        sqlQuery = properties.getProperty("EngineerDao.send2MyOrGroupTask");
         int save = hibernateQuery(sqlQuery).setParameter("appNo", appNo).setParameter("lockUserId", lockByUserId).executeUpdate();
        if(save>0){
            return "Success";
        }else{
            return "Failed";
        }
    }

    @Transactional(readOnly = false)
    public ArchitectDto getEngineerDetails(String appNo) {
        ArchitectDto dto = new ArchitectDto();
        sqlQuery = properties.getProperty("EngineerDao.getEngineerDetails");
        dto = (ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, appNo).list().get(0);
        return dto;
    }

    @Transactional(readOnly = false)
    public List<EngineerAttachment> getdocumentList(String crpEngineerId) {
        List<EngineerAttachment> doclist = new ArrayList<EngineerAttachment>();
        sqlQuery = properties.getProperty("EngineerDao.getEngineerDoc");
        doclist = hibernateQuery(sqlQuery, SurveyDocument.class).setParameter(1, crpEngineerId).list();
        return doclist;
    }

    @Transactional
    public ArchitectDto updateReject(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            Query query1 = sqlQuery("UPDATE crpengineer SET RemarksByRejector=?,RejectedDate= CURRENT_TIMESTAMP,SysRejectorUserId=?,CmnApplicationRegistrationStatusId=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.REJECTED.getCode()).setParameter(4, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # updateReject: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public ArchitectDto updateVerification(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            Query query1 = sqlQuery("UPDATE crpengineer SET RemarksByVerifier=?,VerifiedDate= CURRENT_TIMESTAMP,SysVerifierUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.VERIFIED.getCode()).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # updateVerification: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public ArchitectDto updateApproval(ArchitectDto dto, String userID, HttpServletRequest request) {
        int validity_year = 0;
        String applicationStatus="";
        try {
            if(dto.getServiceSectorType().equalsIgnoreCase("Government")){
                 validity_year = 5;
            }else {
                 validity_year = 2;
            }
            if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
                applicationStatus=ApplicationStatus.DEREGISTERED.getCode();
               /* Query query1 = sqlQuery("UPDATE sysuser SET STATUS = ? WHERE SysApproverUserId = ?");
                query1.setParameter(1,0).setParameter(2, userID);
                query1.executeUpdate();*/
            }else{
                applicationStatus=ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
            }
            Query query1 = sqlQuery("UPDATE crpengineer SET RemarksByApprover=?,RegistrationApprovedDate= CURRENT_TIMESTAMP,RegistrationExpiryDate=DATE_ADD(CURRENT_DATE, INTERVAL " + validity_year + " YEAR),SysApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =?");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, applicationStatus).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
        }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # updateVerification: " + e);
            e.printStackTrace();
        }
        return dto;
    }
   
    @Transactional
    public String generateEngineerNo(String countryId, String surtype) {
        String engineerNo = "", selectquery = "";
        String firstpart = "", secondpart = "";
        try {
            if (countryId.equalsIgnoreCase("Bhutan")) {
                firstpart = "BE-";
                if (surtype.equalsIgnoreCase("Government")) {
                    secondpart = "(G)";
                    selectquery = "SELECT MAX(CDBNo) cdbNo FROM crpengineerfinal WHERE CDBNo NOT LIKE 'N%' AND CDBNo LIKE '%(G)%'";
                } else {
                    secondpart = "(P)";
                    selectquery = "SELECT MAX(CDBNo) cdbNo FROM crpengineerfinal WHERE CDBNo NOT LIKE 'N%' AND CDBNo LIKE '%(P)%'";
                }
            } else {
                firstpart = "NBE-";
                if (surtype.equalsIgnoreCase("Government")) {
                    secondpart = "(G)";
                    selectquery = "SELECT MAX(CDBNo) cdbNo FROM crpengineerfinal WHERE CDBNo LIKE 'N%' AND CDBNo LIKE '%(G)%'";
                } else {
                    secondpart = "(P)";
                    selectquery = "SELECT MAX(CDBNo) cdbNo FROM crpengineerfinal WHERE CDBNo LIKE 'N%' AND CDBNo LIKE '%(P)%'";
                }
            }
            String curr_engineerNo = (String) hibernateQuery(selectquery).list().get(0);
            if (curr_engineerNo == null) {
                engineerNo = firstpart + "001" + secondpart;
            } else {
                int num = Integer.parseInt(curr_engineerNo.replaceAll("\\D+", ""));
                num++;
                if (String.valueOf(num).length() == 1) {
                    engineerNo = firstpart + "00" + num + secondpart;
                } else if (String.valueOf(num).length() == 2) {
                    engineerNo = firstpart + "0" + num + secondpart;
                } else {
                    engineerNo = firstpart + num + secondpart;
                }
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao : " + e);
            e.printStackTrace();
        }
        return engineerNo;
    }

    @Transactional
    public ArchitectDto updateApplicationForPayment(ArchitectDto dto, String userID, HttpServletRequest request) {
        String staut_code ="";
        if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
            staut_code=ApplicationStatus.CANCELLATION.getCode();
        }else{
            staut_code=ApplicationStatus.APPROVED.getCode();
        }
        try {
            Query query1 = sqlQuery("UPDATE crpengineer SET RemarksByPaymentApprover=?,PaymentApprovedDate= CURRENT_TIMESTAMP,SysPaymentApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=?,PaymentReceiptNo=?,PaymentReceiptDate=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3,staut_code).setParameter(4, null).setParameter(5, dto.getPaymentReceiptNo()).setParameter(6, dto.getPaymentReceiptDate()).setParameter(7, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # approveArchitectRegistration: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String insertInPaymentDetails(ArchitectDto dto, String userID, HttpServletRequest request) {
        String retval = "";
        try {
            if(dto.getPaymentmode().equalsIgnoreCase("Not Applicable")){
                retval = "Success";
            }else {
                Query query1 = sqlQuery("INSERT INTO crpengineerregistrationpayment (Id,CrpEngineerFinalId,Amount,CreatedBy,CreatedOn,Mode_Of_Payment) VALUES(?,(SELECT Id FROM crpengineerfinal WHERE  ReferenceNo =?),?,?,CURRENT_TIMESTAMP,?) ");
                query1.setParameter(1, UUID.randomUUID().toString()).setParameter(2, dto.getReferenceNo()).setParameter(3, dto.getTotalAmt()).setParameter(4, userID).setParameter(5, dto.getPaymentmode());
                int save = query1.executeUpdate();
                if (save > 0) {
                    retval = "Success";
                }
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # insertInPaymentDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public ArchitectDto getengineerregDetails(ArchitectDto dto) {
        // ArchitectDto dt=new ArchitectDto();
        try {
            sqlQuery = properties.getProperty("EngineerDao.getengineerregDetails");
            dto = (ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, dto.getReferenceNo()).list().get(0);
            return dto;
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # getsurveyapplicationdetails: " + e);
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public String insertuserDetails(ArchitectDto dto, String userID, HttpServletRequest request) {
        String return_value = "Insert_Fail";
        try {
            String generateID = UUID.randomUUID().toString();
            String digit = "1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 4) {
                int index = (int) (rnd.nextFloat() * digit.length());
                salt.append(digit.charAt(index));
            }
            String saltString = salt.toString();
            String pw_hash = BCrypt.hashpw(saltString, BCrypt.gensalt());

            Query query1 = sqlQuery("INSERT INTO sysuser (ContactNo,CreatedBy,CreatedOn,Email,FullName,Id,PASSWORD,STATUS,username) VALUES(?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?) ");
            query1.setParameter(1, dto.getMobileNo()).setParameter(2, userID).setParameter(3, dto.getEmail()).setParameter(4, dto.getFullname()).setParameter(5, generateID).setParameter(6, pw_hash).setParameter(7, "1").setParameter(8, dto.getEmail());
            int save = query1.executeUpdate();
            if (save > 0) {
                System.out.print("Password: " + saltString + "(" + pw_hash + ") is generated against user:" + dto.getEmail());
                return_value = generateID + "/" + saltString;
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # insertuserDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public String insertEngineerFinalDetails(ArchitectDto dto, String userID, String sysuserId) {
        String return_value = "", actorId="";
        /*
        SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
        Date current_date = new Date();
        boolean expired = current_date.before(new Date());*/
        if(dto.getServiceTypeId().equalsIgnoreCase("registration")){
            actorId=sysuserId;
        }else if(dto.getServiceTypeId().equalsIgnoreCase("renewal") || dto.getServiceTypeId().equalsIgnoreCase("cancel")){
            actorId=userID;
        }
        try {
            Query query1 = sqlQuery("INSERT INTO crpengineerfinal (ApplicationDate,CDBNo,CIDNo,CmnApplicationRegistrationStatusId,CmnCountryId,CmnDzongkhagId,CmnQualificationId,CmnSalutationId,CmnServiceSectorTypeId," +
                    "CmnUniversityCountryId,CreatedBy,Email,EmployerAddress,EmployerName,Gewog,GraduationYear,Id,MobileNo,Name," +
                    "NameOfUniversity,ReferenceNo,RegistrationExpiryDate,RemarksByFinalApprover,SysUserId," +
                    "SysFinalApproverUserId,Village,CmnTradeId,InitialDate,SysFinalApprovedDate,CreatedOn,RegistrationApprovedDate) VALUES(?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE,CURRENT_DATE,CURRENT_TIMESTAMP,CURRENT_DATE)");
            query1.setParameter(1, dto.getApplicationDate()).setParameter(2, dto.getCdbNo()).setParameter(3, dto.getCidNo()).setParameter(4, ApplicationStatus.APPROVED.getCode()).setParameter(5, dto.getCountryId()).setParameter(6, dto.getDzongkhagId()).setParameter(7, dto.getQualificationId()).setParameter(8, dto.getSalutation()).setParameter(9, dto.getServiceSectorTypeId())
                    .setParameter(10, dto.getUniversityCountry()).setParameter(11, userID).setParameter(12, dto.getEmail()).setParameter(13, dto.getEmployeeAddress()).setParameter(14, dto.getEmployeeName()).setParameter(15, dto.getGewog()).setParameter(16, dto.getGraduationyr().toString().substring(0, 4)).setParameter(17, dto.getCrpEngineerId()).setParameter(18, dto.getMobileNo()).setParameter(19, dto.getFullname())
                    .setParameter(20, dto.getUniversityName()).setParameter(21, dto.getReferenceNo()).setParameter(22, dto.getRegExpDate()).setParameter(23, dto.getRemarks()).setParameter(24, sysuserId)
                    .setParameter(25, userID).setParameter(26, dto.getVillage()).setParameter(27,dto.getTrade());
            int save = query1.executeUpdate();
            if(save>0) {
                Query querydoc = sqlQuery("INSERT INTO `crpengineerattachmentfinal` (`Id`,`CrpEngineerFinalId`,`DocumentName`,`DocumentPath`,`FileType`,`CreatedBy`,`CreatedOn`) SELECT a.`Id`,a.`CrpEngineerId`,a.`DocumentName`,a.`DocumentPath`,a.`FileType`,?,CURRENT_TIMESTAMP FROM `crpengineerattachment` a  LEFT JOIN `crpengineer` ar ON a.`CrpEngineerId`=ar.`Id` WHERE ar.`ReferenceNo`=? ");
                querydoc.setParameter(1, userID).setParameter(2, dto.getReferenceNo());
                save = querydoc.executeUpdate();
                return_value = "Success";
            }else {
                return_value="Fail";
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # insertEngineerFinalDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public List<ArchitectDto> getPrintList() {
        sqlQuery = properties.getProperty("EngineerDao.getPrintList");
        return hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, ApplicationStatus.APPROVED.getCode()).list();
    }

    @Transactional
    public ArchitectDto checkOngoingApplication(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery = "SELECT f.CDBNo cdbNo,i.Name updateStatus,f.ApplicationDate applicationDate,f.ReferenceNo ReferenceNo FROM crpengineer f LEFT JOIN cmnlistitem i ON i.Id=f.CmnApplicationRegistrationStatusId WHERE i.Id IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND f.CDBNo=? ";
            Query queryre = sqlQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo);
          //  if (queryre.list().size() > 0) {
                dto = (ArchitectDto) queryre.list().get(0);
          //  }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # checkOngoingApplication: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public ArchitectDto getengineerDetails(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery = "SELECT f.`CIDNo` cidNo,f.`CDBNo` cdbNo,f.`RegistrationApprovedDate` registrationApproveDate,f.`RegistrationExpiryDate` regExpDate,f.`Name` fullname,f.`CmnSalutationId` salutation,f.`CmnDzongkhagId` dzongkhagId,f.`Village` village,f.`Gewog` gewog,f.`CmnServiceSectorTypeId` serviceSectorType,f.`CmnCountryId` countryId,f.`Email` email,f.`MobileNo` mobileNo,f.`EmployerName` employeeName,f.`EmployerAddress` employeeAddress,f.`CmnQualificationId` qualificationId,f.`GraduationYear` graduationyr,f.`CmnUniversityCountryId` universityCountry,f.`NameOfUniversity` universityName FROM crpengineerfinal f  WHERE f.`CDBNo`=? ";
            Query queryre = sqlQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo);
            if(queryre.list().size()>0){
                dto=(ArchitectDto) queryre.list().get(0);
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # getengineerDetails: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    public EngineerAttachment getDocumentDetailsByDocId(String uploadDocId) {
        EngineerAttachment dto = new EngineerAttachment();
        try {
            String GET_DOCUMENT_DTLS_BY_UUID = "SELECT a.`DocumentName` documentName,a.`DocumentPath` documentPath,a.`CrpEngineerId` engineerId,a.`FileType` fileType FROM crpengineerattachment a WHERE a.`Id`=? ";
            Query query = sqlQuery(GET_DOCUMENT_DTLS_BY_UUID, EngineerAttachment.class).setParameter(1, uploadDocId);
            dto = (EngineerAttachment) query.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String updateRenewalDetails(ArchitectDto dto1, String userID, int interval) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("UPDATE crpsurveyfinal s SET s.RegistrationExpiryDate=DATE_ADD(CURRENT_DATE, INTERVAL ? YEAR),s.EditedBy=?,s.EditedOn=CURRENT_TIMESTAMP,s.ReRegistrationRemarks=?,s.ReRegistrationDate=CURRENT_DATE WHERE s.ARNo=?");
            query1.setParameter(1, interval).setParameter(2, userID).setParameter(3, dto1.getRemarks()).setParameter(4, dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # updateRenewalDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public String insertInPaymentServiceDetails(ArchitectDto dto, String userID) {
        String retval = "";
        try {
            Query query1 = sqlQuery("INSERT INTO crpengineerservicepayment (Id,CrpEngineerId,CmnServiceTypeId,NoOfDaysLate,NoOfDaysAfterGracePeriod,PenaltyPerDay,CreatedBy,PaymentAmount,TotalAmount,CreatedOn) VALUES(?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)");
            query1.setParameter(1, UUID.randomUUID().toString()).setParameter(2, dto.getCrpEngineerId()).setParameter(3, dto.getServiceTypeId()).setParameter(4, dto.getNoOfDaysLate()).setParameter(5, dto.getNoOfDaysAfterGracePeriod()).setParameter(6, "100").setParameter(7, userID).setParameter(8,dto.getPaymentAmt()).setParameter(9,dto.getTotalAmt());
            int save = query1.executeUpdate();
            if (save > 0) {
                retval = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # insertInPaymentDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String updatePaymentServiceDetails(ArchitectDto dto, String userID) {
        dto.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
        String retval = "";
        try {
            Query query1 = sqlQuery("UPDATE crpengineerservicepayment SET TotalAmount = ?,PaymentAmount = ?,Mode_Of_Payment = ? WHERE CrpEngineerId = ?");
            query1.setParameter(1,dto.getTotalAmt()).setParameter(2, dto.getPaymentAmt()).setParameter(3, dto.getPaymentmode()).setParameter(4, dto.getCrpEngineerId());
            int save = query1.executeUpdate();
            if (save > 0) {
                retval = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # updatePaymentServiceDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String updateCancellationDetails(ArchitectDto dto1, String userID) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("UPDATE crpsurveyfinal s SET s.`CmnApplicationRegistrationStatusId`=?,s.`EditedBy`=?,s.`EditedOn`=CURRENT_TIMESTAMP,s.`DeregisteredRemarks`=?,s.`DeRegisteredDate`=CURRENT_DATE WHERE s.`ARNo`=? ");
            query1.setParameter(1, ApplicationStatus.DEREGISTERED.getCode()).setParameter(2, userID).setParameter(3, dto1.getRemarks()).setParameter(4, dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # updateCancellationDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public ArchitectDto getDetails(String appNo) {
        ArchitectDto dto = new ArchitectDto();
        sqlQuery = properties.getProperty("SurveyDao.getDetails");
        dto = (ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, appNo).list().get(0);
        return dto;
    }

    public String isMailUnique(HttpServletRequest request) {
        String isMailUnique = "";
        try {
            sqlQuery = "SELECT a.Email FROM sysuser a WHERE a.Email = ?";
            isMailUnique = (String) hibernateQuery(sqlQuery).setParameter(1, request.getParameter("mailId")).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # isMailUnique:" + e);
            e.printStackTrace();
        }
        return isMailUnique;
    }

    @Transactional
    public ArchitectDto populateEngineerApplicantDetails(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery = "SELECT f.CDBNo cdbNo,f.ReferenceNo refNo,f.Id CrpEngineerId,f.CIDNo cidNo,f.Name fullname,f.Village village,f.Gewog gewog,f.CmnDzongkhagId cmnDzongkhagId,d.NameEn dzongkhagId,c.Name countryId,s.Name salutation,\n" +
                    "i.Name updateStatus,f.CmnSalutationId salutationId,f.Email email,f.MobileNo mobileNo,f.EmployerName employeeName,f.EmployerAddress employeeAddress,f.CmnQualificationId qualificationId, q.Name qualification,f.CmnServiceSectorTypeId serviceSectorTypeId,\n" +
                    "f.GraduationYear graduationyr,t.Name serviceSectorType,td.Name trade, f.CmnTradeId cmnTradeId,f.NameOfUniversity universityName,f.CmnUniversityCountryId cmnUniversityCountryId,uc.Name universityCountry,f.RegistrationApprovedDate registrationApproveDate,f.RegistrationExpiryDate regExpDate\n" +
                    "FROM crpengineerfinal f \n" +
                    "LEFT JOIN cmnlistitem i ON i.Id=f.CmnApplicationRegistrationStatusId \n" +
                    "LEFT JOIN cmnlistitem s ON s.Id=f.CmnSalutationId\n" +
                    "LEFT JOIN cmncountry c ON c.Id=f.CmnCountryId\n" +
                    "LEFT JOIN cmncountry uc ON uc.Id=f.CmnUniversityCountryId\n" +
                    "LEFT JOIN cmnlistitem q ON q.Id=f.CmnQualificationId LEFT JOIN cmnlistitem t ON t.Id=f.CmnServiceSectorTypeId \n" +
                    "LEFT JOIN cmnlistitem td ON td.Id=f.CmnTradeId LEFT JOIN cmndzongkhag d ON d.Id=f.CmnDzongkhagId WHERE f.CDBNo = ?";
            dto = (ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # populateEngineerApplicantDetails: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public ArchitectDto updateFinalTable(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            Query query1 = sqlQuery("UPDATE crpengineerfinal SET  CmnApplicationRegistrationStatusId = ?,DeregisteredRemarks = ?,EditedBy = ?,CreatedOn = CURRENT_TIMESTAMP,EditedOn = CURRENT_TIMESTAMP,DeRegisteredDate = CURRENT_DATE WHERE ReferenceNo = ?");
            query1.setParameter(1,  ApplicationStatus.DEREGISTERED.getCode()).setParameter(2, dto.getRemarks()).setParameter(3,userID).setParameter(4, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # updateFinalTable: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public ArchitectDto updateSysuser(String emailId) {
        ArchitectDto dto = new ArchitectDto();
        try {
            Query query1 = sqlQuery("UPDATE sysuser SET Status = ? WHERE username =?");
            query1.setParameter(1, "0").setParameter(2,emailId);
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # updateSysuser: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public CrpengineerFinalEntity getEngineerFinal(String cdbNo) {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<CrpengineerFinalEntity> cQuery = builder.createQuery(CrpengineerFinalEntity.class);
        Root<CrpengineerFinalEntity> root = cQuery.from(CrpengineerFinalEntity.class);
        cQuery.select(root).where(builder.equal(root.get("cdbNo"), cdbNo));
        CrpengineerFinalEntity engineerFinal = getCurrentSession().createQuery(cQuery).getSingleResult();
        em.detach(engineerFinal);
        return engineerFinal;
    }
    public ArchitectDto getEngineerOngoingApp(String cdbNo) {
        sqlQuery = properties.getProperty("EngineerDao.getEngineerOngoingApp");
        List list = hibernateQuery(sqlQuery, ArchitectDto.class).setParameter("cdbNo", cdbNo).list();
        return (ArchitectDto)(list.isEmpty()?null:list.get(0));
    }

    @Transactional
    public String deletePrevRecord(ArchitectDto dto1) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("DELETE FROM crpengineerfinal WHERE CDBNo = ?");
            query1.setParameter(1, dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # deletePrevRecord: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public CertificateDTO getEngineerPrintDetails(HttpServletRequest request, String cdbNo) {
        CertificateDTO dto =new CertificateDTO();
        sqlQuery = properties.getProperty("EngineerDao.getEngineerPrintDetails");
        dto=(CertificateDTO) hibernateQuery(sqlQuery, CertificateDTO.class).setParameter(1, cdbNo).list().get(0);
        return dto;
    }

    public String isCIDUnique(String cidNo) {
        String isCIDUnique = "";
        try {
            sqlQuery = "SELECT c.CmnApplicationRegistrationStatusId FROM crpengineer c WHERE c.CIDNo=?";
            isCIDUnique = (String) hibernateQuery(sqlQuery).setParameter(1, cidNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # isCIDUnique:" + e);
            e.printStackTrace();
        }
        return isCIDUnique;
    }

    @Transactional
    public List<TasklistDto> populateapplicationHistoryEngineer(String cdbNo) {
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
                    "crpengineerfinal a \n" +
                    "INNER JOIN cmnlistitem b  \n" +
                    "ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crpengineerappliedservice s \n" +
                    "ON s.CrpEngineerId = a.Id WHERE a.CDBNo =?\n" +
                    "ORDER BY a.ReferenceNo DESC;";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # populateapplicationHistoryEngineer: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplicationEngineer(String cdbNo) {
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
                    "crpengineer a \n" +
                    "INNER JOIN cmnlistitem b  \n" +
                    "ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crpengineerappliedservice s \n" +
                    "ON s.CrpEngineerId = a.Id WHERE a.CmnApplicationRegistrationStatusId = 'de662a61-b049-11e4-89f3-080027dcfac6' AND  s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' OR s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' AND a.CDBNo =?\n" +
                    "ORDER BY a.ReferenceNo DESC";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in EngineerDao # populaterejectedApplicationEngineer: " + e);
            e.printStackTrace();
        }
        return dto;
    }
}

