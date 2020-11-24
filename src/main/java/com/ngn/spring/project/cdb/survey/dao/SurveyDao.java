package com.ngn.spring.project.cdb.survey.dao;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.TasksDTO;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.architect.dto.ArchitectFeesDto;
import com.ngn.spring.project.cdb.architect.entity.CrparchitectEntity;
import com.ngn.spring.project.cdb.architect.entity.CrparchitectFinalEntity;
import com.ngn.spring.project.cdb.certification.CertificateDTO;
import com.ngn.spring.project.cdb.survey.entity.CrpsurveyFinalEntity;
import com.ngn.spring.project.cdb.survey.entity.SurveyDocument;
import com.ngn.spring.project.cdb.survey.entity.SurveyServiceEntity;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.LoggedInUser;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by USER on 3/19/2020.
 */
@Repository
public class SurveyDao extends BaseDao {
    public List gFeeStructure(String survey) {
        sqlQuery = properties.getProperty("SurveyDao.gFeeStructure");
        return hibernateQuery(sqlQuery, ArchitectFeesDto.class).list();
    }
    @Transactional
    public BigInteger getMaxId() {
        sqlQuery = properties.getProperty("SurveyDao.getMaxId");
        return (BigInteger) hibernateQuery(sqlQuery).uniqueResult();
    }

    @Transactional
    public void save(CrparchitectEntity engineer) {
        saveOrUpdate(engineer);
    }

    @Transactional
    public void saveSservies(SurveyServiceEntity surveyor) {
        saveOrUpdate(surveyor);
    }

    @Transactional
    public void saveAttchment(SurveyDocument surveyDocument) {
        saveOrUpdate(surveyDocument);
    }

    @Transactional(readOnly = true)
    public List getTaskList(String status, String type, String userId, String servicetype) {
        if (type == "Group") {
            sqlQuery = properties.getProperty("SurveyDao.getTaskList");
            return hibernateQuery(sqlQuery, TasksDTO.class).setParameter(1, servicetype).setParameter(2, status).list();
        } else {
            sqlQuery = properties.getProperty("SurveyDao.getMyTaskList");
            return hibernateQuery(sqlQuery, TasksDTO.class).setParameter(1, userId).setParameter(2, servicetype).setParameter(3, status).list();
        }
    }

    @Transactional(readOnly = false)
    public void assignMyTask(String appNo, String lockUserId, String type) {
        String lockByUserId = "";
        if(type.equalsIgnoreCase("release")){
             lockByUserId ="null";
        }else{
            lockByUserId=lockUserId;
        }
        sqlQuery = properties.getProperty("SurveyDao.send2MyOrGroupTask");
        hibernateQuery(sqlQuery).setParameter("appNo", appNo).setParameter("lockUserId", lockByUserId).executeUpdate();
    }

    @Transactional(readOnly = false)
    public ArchitectDto getSurveyDetails(String appNo) {
        ArchitectDto dto = new ArchitectDto();
        sqlQuery = properties.getProperty("SurveyDao.getSurveyDtls");
        dto = (ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, appNo).list().get(0);
        return dto;
    }

    @Transactional(readOnly = false)
    public List<SurveyDocument> getdocumentList(String crpSurveyId) {
        List<SurveyDocument> doclist = new ArrayList<SurveyDocument>();
        sqlQuery = properties.getProperty("SurveyDao.getSurveyDoc");
        doclist = hibernateQuery(sqlQuery, SurveyDocument.class).setParameter(1, crpSurveyId).list();
        return doclist;
    }

    @Transactional
    public ArchitectDto updateReject(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            Query query1 = sqlQuery("UPDATE crpsurvey SET RemarksByRejector=?,RejectedDate= CURRENT_TIMESTAMP,SysRejectorUserId=?,CmnApplicationRegistrationStatusId=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.REJECTED.getCode()).setParameter(4, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in surveyDao # updateReject: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public ArchitectDto updateVerification(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            Query query1 = sqlQuery("UPDATE crpsurvey SET RemarksByVerifier=?,VerifiedDate= CURRENT_TIMESTAMP,SysVerifierUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.VERIFIED.getCode()).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # updateVerification: " + e);
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
                applicationStatus=ApplicationStatus.APPROVED.getCode();
               /* Query query1 = sqlQuery("UPDATE sysuser SET STATUS = ? WHERE SysApproverUserId = ?");
                query1.setParameter(1,0).setParameter(2, userID);
                query1.executeUpdate();*/
            }else{
                applicationStatus=ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
            }
            Query query1 = sqlQuery("UPDATE crpsurvey SET RemarksByApprover=?,RegistrationApprovedDate= CURRENT_TIMESTAMP,RegistrationExpiryDate=DATE_ADD(CURRENT_DATE, INTERVAL " + validity_year + " YEAR),SysApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =?");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, applicationStatus).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
        }
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # updateVerification: " + e);
            e.printStackTrace();
        }
        return dto;
    }

   /* @Transactional
    public ArchitectDto approveSurveyRegistration(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            int validity_year=2;
            String applicationstatus="";
            if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
                applicationstatus= ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
            }else if(dto.getServiceTypeId().equalsIgnoreCase("Registration")){
                if(dto.getServiceSectorType().equalsIgnoreCase("Goverment")){
                    validity_year=5;
                    applicationstatus= ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
                }
                else{
                    applicationstatus= ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
                }
            }else if(dto.getServiceTypeId().equalsIgnoreCase("renewal")){
                if(dto.getServiceSectorType().equalsIgnoreCase("Goverment")){
                    validity_year=5;
                }
                applicationstatus= ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
            }
            Query query1 = sqlQuery("UPDATE crpsurvey SET RemarksByApprover=?,RegistrationApprovedDate= CURRENT_TIMESTAMP,RegistrationApprovedDate=CURRENT_TIMESTAMP,RegistrationExpiryDate=DATE_ADD(CURRENT_DATE, INTERVAL "+validity_year+" YEAR),SysApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, applicationstatus).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in surveyDao # approveSurveyRegistration: " + e);
            e.printStackTrace();
        }
        return dto;
    }*/

    @Transactional
    public String generateSurveyNo(String countryId, String surtype) {
        String surveyNo = "", selectquery = "";
        String firstpart = "", secondpart = "";
        try {
            if (countryId.equalsIgnoreCase("Bhutan")) {
                firstpart = "BS-";
                if (surtype.equalsIgnoreCase("Government")) {
                    secondpart = "(G)";
                    selectquery = "SELECT MAX(ARNo) cdbNo FROM crpsurveyfinal WHERE ARNo NOT LIKE 'N%' AND ARNo LIKE '%(G)%'";
                } else {
                    secondpart = "(P)";
                    selectquery = "SELECT MAX(ARNo) cdbNo FROM crpsurveyfinal WHERE ARNo NOT LIKE 'N%' AND ARNo LIKE '%(P)%'";
                }
            } else {
                firstpart = "NB-";
                if (surtype.equalsIgnoreCase("Government")) {
                    secondpart = "(G)";
                    selectquery = "SELECT MAX(ARNo) cdbNo FROM crpsurveyfinal WHERE ARNo LIKE 'N%' AND ARNo LIKE '%(G)%'";
                } else {
                    secondpart = "(P)";
                    selectquery = "SELECT MAX(ARNo) cdbNo FROM crpsurveyfinal WHERE ARNo LIKE 'N%' AND ARNo LIKE '%(P)%'";
                }
            }
            String curr_survertNo = (String) hibernateQuery(selectquery).list().get(0);
            if (curr_survertNo == null) {
                surveyNo = firstpart + "001" + secondpart;
            } else {
                int num = Integer.parseInt(curr_survertNo.replaceAll("\\D+", ""));
                num++;
                if (String.valueOf(num).length() == 1) {
                    surveyNo = firstpart + "00" + num + secondpart;
                } else if (String.valueOf(num).length() == 2) {
                    surveyNo = firstpart + "0" + num + secondpart;
                } else {
                    surveyNo = firstpart + num + secondpart;
                }
            }
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao : " + e);
            e.printStackTrace();
        }
        return surveyNo;
    }

    @Transactional
    public ArchitectDto updateApplicationForPayment(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            Query query1 = sqlQuery("UPDATE crpsurvey SET RemarksByPaymentApprover=?,PaymentApprovedDate= CURRENT_TIMESTAMP,SysPaymentApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=?,PaymentReceiptNo=?,PaymentReceiptDate=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.APPROVED.getCode()).setParameter(4, null).setParameter(5, dto.getPaymentReceiptNo()).setParameter(6, dto.getPaymentReceiptDate()).setParameter(7, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # approveArchitectRegistration: " + e);
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
                Query query1 = sqlQuery("INSERT INTO crpsurveyregistrationpayment (Id,CrpSurveyFinalId,Amount,CreatedBy,CreatedOn,Mode_Of_Payment) VALUES(?,(SELECT Id FROM crpsurveyfinal WHERE  ReferenceNo =?),?,?,CURRENT_TIMESTAMP,?) ");
                query1.setParameter(1, UUID.randomUUID().toString()).setParameter(2, dto.getReferenceNo()).setParameter(3, dto.getTotalAmt()).setParameter(4, userID).setParameter(5, dto.getPaymentmode());
                int save = query1.executeUpdate();
                if (save > 0) {
                    retval = "Success";
                }
            }
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # insertInPaymentDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public ArchitectDto getsurveyapplicationdetails(ArchitectDto dto) {
        // ArchitectDto dt=new ArchitectDto();
        try {
            sqlQuery = properties.getProperty("SurveyDao.getsurveyregDetails");
            dto = (ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, dto.getReferenceNo()).list().get(0);
            return dto;
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # getsurveyapplicationdetails: " + e);
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
            System.out.print("Exception in SurveyDao # insertuserDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public String insertSurveyFinalDetails(ArchitectDto dto, String userID, String sysuserId) {
        String return_value = "";
        /*
        SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
        Date current_date = new Date();
        boolean expired = current_date.before(new Date());*/
        try {
            Query query1 = sqlQuery("INSERT INTO crpsurveyfinal (ApplicationDate,ARNo,CIDNo,CmnApplicationRegistrationStatusId,CmnCountryId,CmnDzongkhagId,CmnQualificationId,CmnSalutationId,CmnServiceSectorTypeId," +
                    "CmnUniversityCountryId,CreatedBy,Email,EmployerAddress,EmployerName,Gewog,GraduationYear,Id,MobileNo,Name," +
                    "NameOfUniversity,ReferenceNo,RegistrationExpiryDate,RemarksByFinalApprover,SysUserId," +
                    "SysFinalApproverUserId,Village,InitialDate,SysFinalApprovedDate,CreatedOn,RegistrationApprovedDate) VALUES(?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?," +
                    "?,CURRENT_DATE,CURRENT_DATE,CURRENT_TIMESTAMP,CURRENT_DATE)");
            query1.setParameter(1, dto.getApplicationDate()).setParameter(2, dto.getCdbNo()).setParameter(3, dto.getCidNo()).setParameter(4, ApplicationStatus.APPROVED.getCode()).setParameter(5, dto.getCountryId()).setParameter(6, dto.getDzongkhagId()).setParameter(7, dto.getQualificationId()).setParameter(8, dto.getSalutation()).setParameter(9, dto.getServiceSectorType())
                    .setParameter(10, dto.getUniversityCountry()).setParameter(11, userID).setParameter(12, dto.getEmail()).setParameter(13, dto.getEmployeeAddress()).setParameter(14, dto.getEmployeeName()).setParameter(15, dto.getGewog()).setParameter(16, dto.getGraduationyr().toString().substring(0, 4)).setParameter(17, dto.getCrpSurveyId()).setParameter(18, dto.getMobileNo()).setParameter(19, dto.getFullname())
                    .setParameter(20, dto.getUniversityName()).setParameter(21, dto.getReferenceNo()).setParameter(22, dto.getRegExpDate()).setParameter(23, dto.getRemarks()).setParameter(24, sysuserId)
                    .setParameter(25, userID).setParameter(26, dto.getVillage());
            int save = query1.executeUpdate();

            Query querydoc = sqlQuery("INSERT INTO `crpsurveyattachmentfinal` (`Id`,`CrpSurveyFinalId`,`DocumentName`,`DocumentPath`,`FileType`,`CreatedBy`,`CreatedOn`) SELECT a.`Id`,a.`CrpSurveyId`,a.`DocumentName`,a.`DocumentPath`,a.`FileType`,?,CURRENT_TIMESTAMP FROM `crpsurveyattachment` a  LEFT JOIN `crpsurvey` ar ON a.`CrpSurveyId`=ar.`Id` WHERE ar.`ReferenceNo`=? ");
            querydoc.setParameter(1, userID).setParameter(2, dto.getReferenceNo());
            save = querydoc.executeUpdate();
            return_value = "Success";
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # insertSurveyFinalDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public List<ArchitectDto> getPrintList() {
        sqlQuery = properties.getProperty("SurveyDao.getPrintList");
        return hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, ApplicationStatus.APPROVED.getCode()).list();
    }

    @Transactional
    public ArchitectDto checkOngoingApplication(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery = "SELECT f.ARNo cdbNo,i.Name updateStatus,f.ApplicationDate applicationDate,f.ReferenceNo ReferenceNo FROM crpsurvey f LEFT JOIN cmnlistitem i ON i.Id=f.CmnApplicationRegistrationStatusId WHERE i.Id IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND f.ARNo=? ";
            Query queryre = sqlQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo);
            if (queryre.list().size() > 0) {
                dto = (ArchitectDto) queryre.list().get(0);
            }
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # checkOngoingApplication: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public ArchitectDto getsurveyDetails(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery = "SELECT f.`CIDNo` cidNo,f.`ARNo` cdbNo,f.`RegistrationApprovedDate` registrationApproveDate,f.`RegistrationExpiryDate` regExpDate,f.`Name` fullname,f.`CmnSalutationId` salutation,f.`CmnDzongkhagId` dzongkhagId,f.`Village` village,f.`Gewog` gewog,f.`CmnServiceSectorTypeId` serviceSectorType,f.`CmnCountryId` countryId,f.`Email` email,f.`MobileNo` mobileNo,f.`EmployerName` employeeName,f.`EmployerAddress` employeeAddress,f.`CmnQualificationId` qualificationId,f.`GraduationYear` graduationyr,f.`CmnUniversityCountryId` universityCountry,f.`NameOfUniversity` universityName FROM crpsurveyfinal f  WHERE f.`ARNo`=? ";
            Query queryre = sqlQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo);
            if (queryre.list().size() > 0) {
                dto = (ArchitectDto) queryre.list().get(0);
            }
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # getsurveyDetails: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    public SurveyDocument getDocumentDetailsByDocId(String uploadDocId) {
        SurveyDocument dto = new SurveyDocument();
        try {
            String GET_DOCUMENT_DTLS_BY_UUID = "SELECT a.`DocumentName` documentName,a.`DocumentPath` documentPath,a.`CrpSurveyId` surveyid,a.`FileType` fileType FROM crpsurveyattachment a WHERE a.`Id`=? ";
            Query query = sqlQuery(GET_DOCUMENT_DTLS_BY_UUID, SurveyDocument.class).setParameter(1, uploadDocId);
            dto = (SurveyDocument) query.list().get(0);
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
            System.out.print("Exception in SurveyDao # updateRenewalDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public String insertInPaymentServiceDetails(ArchitectDto dto, String userID) {
        String retval = "";
        try {
            Query query1 = sqlQuery("INSERT INTO crpsurveyservicepayment (Id,CrpSurveyId,CmnServiceTypeId,NoOfDaysLate,NoOfDaysAfterGracePeriod,PenaltyPerDay,CreatedBy,PaymentAmount,TotalAmount,CreatedOn) VALUES(?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP) ");
            query1.setParameter(1, UUID.randomUUID().toString()).setParameter(2, dto.getCrpSurveyId()).setParameter(3, dto.getServiceTypeId()).setParameter(4, dto.getNoOfDaysLate()).setParameter(5, dto.getNoOfDaysAfterGracePeriod()).setParameter(6, "100").setParameter(7, userID).setParameter(8,dto.getPaymentAmt()).setParameter(9,dto.getTotalAmt());
            int save = query1.executeUpdate();
            if (save > 0) {
                retval = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # insertInPaymentDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String updatePaymentServiceDetails(ArchitectDto dto, String userID) {
        dto.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
        String retval = "";
        try {
            Query query1 = sqlQuery("UPDATE crpsurveyservicepayment SET TotalAmount = ?,PaymentAmount = ?,Mode_Of_Payment = ? WHERE CrpSurveyId = ?");
            query1.setParameter(1,dto.getTotalAmt()).setParameter(2, dto.getPaymentAmt()).setParameter(3, dto.getPaymentmode()).setParameter(4, dto.getCrpSurveyId());
            int save = query1.executeUpdate();
            if (save > 0) {
                retval = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # updatePaymentServiceDetails: " + e);
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
            System.out.print("Exception in SurveyDao # updateCancellationDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public ArchitectDto populateSurveyApplicantDetails(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery = "SELECT f.ARNo cdbNo,f.ReferenceNo refNo,f.Id CrpSurveyId,f.CIDNo cidNo,f.Name fullname,f.Village village,f.Gewog gewog,d.NameEn dzongkhagId,c.Name countryId,s.Name salutation,\n" +
                    "i.Name updateStatus,f.CmnSalutationId salutationId,f.Email email,f.MobileNo mobileNo,f.EmployerName employeeName,f.EmployerAddress employeeAddress,f.CmnQualificationId qualificationId, q.Name qualification,f.CmnServiceSectorTypeId serviceSectorTypeId,\n" +
                    "f.GraduationYear graduationyr,t.Name serviceSectorType,f.NameOfUniversity universityName,f.CmnUniversityCountryId cmnUniversityCountryId,uc.Name universityCountry,f.RegistrationApprovedDate registrationApproveDate,f.RegistrationExpiryDate regExpDate\n" +
                    "FROM crpsurveyfinal f \n" +
                    "LEFT JOIN cmnlistitem i ON i.Id=f.CmnApplicationRegistrationStatusId \n" +
                    "LEFT JOIN cmnlistitem s ON s.Id=f.CmnSalutationId\n" +
                    "LEFT JOIN cmncountry c ON c.Id=f.CmnCountryId\n" +
                    "LEFT JOIN cmncountry uc ON uc.Id=f.CmnUniversityCountryId\n" +
                    "LEFT JOIN cmnlistitem q ON q.Id=f.CmnQualificationId LEFT JOIN cmnlistitem t ON t.Id=f.CmnServiceSectorTypeId \n" +
                    "LEFT JOIN cmndzongkhag d ON d.Id=f.CmnDzongkhagId WHERE f.ARNo=?";
            dto = (ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # populateSurveyApplicantDetails: " + e);
            e.printStackTrace();
        }
        return dto;
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
            System.out.print("Exception in SurveyDao # isMailUnique:" + e);
            e.printStackTrace();
        }
        return isMailUnique;
    }

    @Transactional(readOnly = true)
    public CrpsurveyFinalEntity getSurveyFinal(String cdbNo) {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<CrpsurveyFinalEntity> cQuery = builder.createQuery(CrpsurveyFinalEntity.class);
        Root<CrpsurveyFinalEntity> root = cQuery.from(CrpsurveyFinalEntity.class);
        cQuery.select(root).where(builder.equal(root.get("arNo"), cdbNo));
        CrpsurveyFinalEntity surveyFinal = getCurrentSession().createQuery(cQuery).getSingleResult();
        em.detach(surveyFinal);
        return surveyFinal;
    }

    public ArchitectDto getSurveyorOngoingApp(String cdbNo) {
            sqlQuery = properties.getProperty("SurveyorDao.getSurveyorOngoingApp");
            List list = hibernateQuery(sqlQuery, ArchitectDto.class).setParameter("cdbNo", cdbNo).list();
            return (ArchitectDto)(list.isEmpty()?null:list.get(0));
    }

    @Transactional
    public String deletePrevRecord(ArchitectDto dto1) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("DELETE FROM crpsurveyfinal WHERE ARNo =?");
            query1.setParameter(1, dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in SurveyDao # updateCancellationDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public CertificateDTO getSurveyPrintDetails(HttpServletRequest request, String cdbNo) {
        CertificateDTO dto =new CertificateDTO();
        sqlQuery = properties.getProperty("SurveyorDao.getSurveyPrintDetails");
        dto=(CertificateDTO) hibernateQuery(sqlQuery, CertificateDTO.class).setParameter(1, cdbNo).list().get(0);
        return dto;
    }
}

