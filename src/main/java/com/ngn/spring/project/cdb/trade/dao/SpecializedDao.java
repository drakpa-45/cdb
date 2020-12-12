package com.ngn.spring.project.cdb.trade.dao;

import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.TasksDTO;
import com.ngn.spring.project.cdb.architect.dto.ArchitectDto;
import com.ngn.spring.project.cdb.certification.CertificateDTO;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirmFinal;
import com.ngn.spring.project.cdb.trade.dto.OwnershipDTO;
import com.ngn.spring.project.cdb.trade.dto.TradeDto;
import com.ngn.spring.project.cdb.trade.dto.TradeFeesDto;
import com.ngn.spring.project.cdb.trade.entity.*;
import com.ngn.spring.project.commonDto.TasklistDto;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.LoggedInUser;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by USER on 5/5/2020.
 */
@Repository
public class SpecializedDao extends BaseDao {

    public List gFeeStructure(String trade) {
        //sqlQuery ="SELECT CAST(IF(f.`NewRegistrationFee` IS NULL,'0',f.`NewRegistrationFee`) AS UNSIGNED) registrationFee,f.`FirstRenewalFee` renewalFee,f.`Name` name,f.`RegistrationValidity` validaty FROM`crpservicefeestructure` f WHERE f.`Name`='Specialized Trade'";
        sqlQuery = properties.getProperty("SpecializedDao.gFeeStructure");
        return hibernateQuery(sqlQuery, TradeFeesDto.class).list();
    }

    public List category() {
        List<TradeFeesDto> dtoList=new ArrayList<>();
        sqlQuery = properties.getProperty("SpecializedDao.category");
         dtoList=hibernateQuery(sqlQuery, TradeFeesDto.class).list();
        return dtoList;
    }

    @Transactional
    public BigInteger getMaxId() {
        sqlQuery = properties.getProperty("SpecializedDao.getMaxId");
        return (BigInteger) hibernateQuery(sqlQuery).uniqueResult();
    }
    @Transactional
    public void save(CrpspecializedtradeEntity specialized) {
        saveOrUpdate(specialized);
    }

    @Transactional
    public void saveAservies(ServicespecializedtradeEntity specializedtradeserviceEntity) {
        saveOrUpdate(specializedtradeserviceEntity);
    }
    @Transactional
    public void saveAttchment(TradeDocument spTradeAttachment) {
        saveOrUpdate(spTradeAttachment);
    }

    @Transactional(readOnly = true)
    public List getTaskList(String status,String type,String userId,String servicetype) {
        if(type=="Group"){
            sqlQuery = properties.getProperty("SpecializedDao.getTaskList");
            return hibernateQuery(sqlQuery, TasksDTO.class).setParameter(1, servicetype).setParameter(2, status).list();
        }
        else{
            sqlQuery = properties.getProperty("SpecializedDao.getMyTaskList");
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
        sqlQuery = properties.getProperty("SpecializedDao.send2MyOrGroupTask");
        hibernateQuery(sqlQuery).setParameter("appNo", appNo) .setParameter("lockUserId", lockByUserId).executeUpdate();
    }

    @Transactional(readOnly = false)
    public ArchitectDto getArchitetDetails(String appNo) {
        ArchitectDto dto =new ArchitectDto();
        sqlQuery = properties.getProperty("ArchitectDao.getArchiectDtls");
        dto=(ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, appNo).list().get(0);
        return dto;
    }

    @Transactional(readOnly = false)
    public List<TradeDocument> getdocumentList(String crptradeId) {
        List<TradeDocument> doclist=new ArrayList<TradeDocument>();
        sqlQuery = properties.getProperty("SpecializedDao.getTradeDoc");
        doclist= hibernateQuery(sqlQuery, TradeDocument.class).setParameter(1, crptradeId).list();
        return doclist;
    }

    @Transactional
    public TradeDto updateReject(TradeDto dto, String userID, HttpServletRequest request) {
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade SET RemarksByRejector=?,RejectedDate= CURRENT_TIMESTAMP,SysRejectorUserId=?,CmnApplicationRegistrationStatusId=? WHERE ReferenceNo =?");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.REJECTED.getCode()).setParameter(4, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedTradeDao # updateReject: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public TradeDto updateVerification(TradeDto dto, String userID, HttpServletRequest request) {
        String statuscode="";
        try {
            statuscode= ApplicationStatus.VERIFIED.getCode();
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade SET RemarksByVerifier=?,VerifiedDate= CURRENT_TIMESTAMP,SysVerifierUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =?");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, statuscode).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedTradeDao # updateVerification: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public TradeDto updateApplicationForPayment(TradeDto dto, String userID, HttpServletRequest request) {
        try {
            String sql ="UPDATE crpspecializedtrade SET RemarksByPaymentApprover=?,PaymentApprovedDate = CURRENT_TIMESTAMP,SysPaymentApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=?,PaymentReceiptNo=?,PaymentReceiptDate=? WHERE ReferenceNo =? ";
            org.hibernate.query.Query query1 =hibernateQuery(sql,TradeDto.class);
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.APPROVED.getCode()).setParameter(4, null).setParameter(5, dto.getPaymentReceiptNo()).setParameter(6, dto.getPaymentReceiptDate()).setParameter(7, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedTradeDao # updateApplicationForPayment: " + e);
            e.printStackTrace();
        }
        return dto;
    }
    @Transactional
    public String insertInPaymentDetails(ArchitectDto dto, String userID, HttpServletRequest request) {
        String retval="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crparchitectregistrationpayment (Id,CrpArchitectFinalId,Amount,CreatedBy,CreatedOn,Mode_Of_Payment) VALUES(?,(SELECT Id FROM crparchitectfinal WHERE  ReferenceNo =?),?,?,CURRENT_TIMESTAMP,?) ");
            query1.setParameter(1, UUID.randomUUID().toString()).setParameter(2, dto.getReferenceNo()).setParameter(3, dto.getTotalAmt()).setParameter(4, userID).setParameter(5, dto.getPaymentmode());
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
    public List<TradeDto> getPrintList() {
        sqlQuery = properties.getProperty("SpecializedDao.getPrintList");
        return hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, ApplicationStatus.APPROVED_FOR_PAYMENT.getCode()).list();
    }

    @Transactional
    public TradeDto checkOngoingApplication(String cdbNo) {
        TradeDto dto = new TradeDto();
        try {
            sqlQuery="SELECT f.SPNo cdbNo,i.Name updateStatus,f.ApplicationDate applicationDate,f.ReferenceNo ReferenceNo FROM crpspecializedtrade f LEFT JOIN cmnlistitem i ON i.Id=f.CmnApplicationRegistrationStatusId WHERE i.Id IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND f.SPNo=?";
            Query queryre = sqlQuery(sqlQuery, TradeDto.class).setParameter(1, cdbNo);
            if(queryre.list().size()>0){
                dto=(TradeDto) queryre.list().get(0);
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # checkOngoingApplication: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    public TradeDocument getDocumentDetailsByDocId(String uploadDocId) {
        TradeDocument dto=new TradeDocument();
        try {
            String GET_DOCUMENT_DTLS_BY_UUID="SELECT a.`DocumentName` documentName,a.`DocumentPath` documentPath,a.`CrpSpecializedTradeId` crpSpecializedTradeId,a.`FileType` fileType FROM crpspecializedtradeattachment a WHERE a.`Id`=? ";
            Query query = sqlQuery(GET_DOCUMENT_DTLS_BY_UUID,TradeDocument.class).setParameter(1, uploadDocId);
            dto = (TradeDocument) query.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String updateRenewalDetails(TradeDto dto1, String userID, int interval) {
        String return_value="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtradefinal s SET s.RegistrationExpiryDate=DATE_ADD(CURRENT_DATE, INTERVAL ? YEAR),s.EditedBy=?,s.EditedOn=CURRENT_TIMESTAMP,s.RemarksByApprover=?, CmnApplicationRegistrationStatusId=? WHERE s.SPNo=?");
            query1.setParameter(1, interval).setParameter(2, userID).setParameter(3, dto1.getRemarks()).setParameter(4, ApplicationStatus.APPROVED.getCode()).setParameter(5, dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value="Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # updateRenewalDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public String insertInPaymentServiceDetails(TradeDto dto, String userID) {
        String retval="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crpspecializedtradeservicepayment (Id,CrpSpecializedTradeId,CmnServiceTypeId,NoOfDaysLate,NoOfDaysAfterGracePeriod,PenaltyPerDay,CreatedBy,PaymentAmount,TotalAmount,CreatedOn) VALUES(?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP) ");
            query1.setParameter(1, UUID.randomUUID().toString()).setParameter(2, dto.getCrpSpecializedTradeId()).setParameter(3, dto.getServiceTypeId()).setParameter(4, dto.getNoOfDaysLate()).setParameter(5, dto.getNoOfDaysAfterGracePeriod()).setParameter(6, "100").setParameter(7, userID).setParameter(8,dto.getPaymentAmt()).setParameter(9,dto.getTotalAmt());
            int save = query1.executeUpdate();
            if (save > 0) {
                retval= "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # insertInPaymentServiceDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String updatePaymentServiceDetails(TradeDto dto, String userID) {
        dto.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
        String retval = "";
        try {
            Query query1 = sqlQuery("UPDATE crpspecializedtradeservicepayment SET TotalAmount = ?,PaymentAmount = ?,Mode_Of_Payment = ? WHERE CrpSpecializedTradeId = ?");
            query1.setParameter(1,dto.getTotalAmt()).setParameter(2, dto.getPaymentAmt()).setParameter(3, dto.getPaymentmode()).setParameter(4, dto.getCrpSpecializedTradeId());
            int save = query1.executeUpdate();
            if (save > 0) {
                retval = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # updatePaymentServiceDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String updateCancellationDetails(TradeDto dto1, String userID) {
        String return_value="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade s SET s.CmnApplicationRegistrationStatusId=?,s.EditedBy=?,s.EditedOn=CURRENT_TIMESTAMP,s.DeregisteredBlacklistedRemarks=? WHERE s.ReferenceNo=?");
            query1.setParameter(1, ApplicationStatus.DEREGISTERED.getCode()).setParameter(2, userID).setParameter(3, dto1.getRemarks()).setParameter(4,dto1.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value="Success";
            }else{
                return_value="failed";
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # updateCancellationDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String saveWrkClassification(SpecializedTradeCategory catEntity) {
       try{
           saveOrUpdate(catEntity);
           return "success";
       }catch (Exception e){
           System.out.print("exception while inserting into workflow classification"+e);
       }
        return "fail";
    }

    public TradeDto fetchdtls(TradeDto appNo) {
        TradeDto dtls=new TradeDto();
        try {
            sqlQuery = properties.getProperty("SpecializedDao.getDtls");
            dtls=(TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, appNo.getReferenceNo()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in specializedDao # getSpecialzedTradeDtls: " + e);
            e.printStackTrace();
        }
        return dtls;
    }

    public TradeDto getTradeDetails(String appNo) {
        TradeDto dto =new TradeDto();
            try {
                 sqlQuery = properties.getProperty("SpecializedDao.getSpecializedradeDtls");
                        dto=(TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, appNo).list().get(0);
                } catch (Exception e){
                e.printStackTrace();
                System.out.print("erroerrrr....SpecializedDao.getSpecializedradeDtls"+e);
            }
        return dto;
    }

    @Transactional
    public TradeDto approveSpTradeRegistrationSole(TradeDto dto, String userID, HttpServletRequest request) {
        try {
            int validity_year=3;
            String applicationstatus="";
            if(dto.getServiceTypeId().equalsIgnoreCase("cancel") || dto.getServiceTypeId().equalsIgnoreCase("registration") || dto.getServiceTypeId().equalsIgnoreCase("renewal")){
                applicationstatus= ApplicationStatus.APPROVED.getCode();
            }
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade SET RemarksByApprover=?,RegistrationApprovedDate= CURRENT_TIMESTAMP,RegistrationApprovedDate=CURRENT_TIMESTAMP,RegistrationExpiryDate=DATE_ADD(CURRENT_DATE, INTERVAL "+validity_year+" YEAR),SysApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =?");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, applicationstatus).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in specializedDao # approveSpTradeRegistration: " + e);
            e.printStackTrace();
        }
        return dto;
    }
    @Transactional
    public TradeDto getspcializedTradeapplicationdetails(TradeDto dto) {
        try {
            sqlQuery = properties.getProperty("SpecializedDao.getspcializedTradeDetails");
            dto= (TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, dto.getReferenceNo()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # getspcializedTradeapplicationdetails: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String generatespecializedTradeNo(TradeDto dto) {
        String spTradeNo="", selectquery="";
        String firstpart="";
        try {
                firstpart="SP-";
                selectquery = "SELECT MAX(SPNo) cdbNo FROM crpspecializedtradefinal";
                String curr_specializedTradeNo = (String) hibernateQuery(selectquery).list().get(0);
                if (curr_specializedTradeNo == null) {
                    curr_specializedTradeNo = firstpart + "001";
                    spTradeNo=curr_specializedTradeNo;
                } else {
                  int num = Integer.parseInt(curr_specializedTradeNo.replaceAll("\\D+", ""));
                    num++;
                    if (String.valueOf(num).length() == 1) {
                        spTradeNo = firstpart + "00" + num;
                    } else if (String.valueOf(num).length() == 2) {
                        spTradeNo = firstpart + "0" + num;
                    } else {
                        spTradeNo = firstpart + num;
                    }
                }

        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # generatespecializedTradeNo: " + e);
            e.printStackTrace();
        }
        return spTradeNo;
    }
    @Transactional
    public String insertspTradeFinalDetails(TradeDto dto, String userID, String sysuserId) {
        String return_value="",statusValue="",actorId="";
       /* if(dto.getServiceTypeId().equalsIgnoreCase("registration") || dto.getServiceTypeId().equalsIgnoreCase("cancel") ){
            statusValue= ApplicationStatus.APPROVED.getCode();
        }else if(dto.getServiceTypeId().equalsIgnoreCase("renewal")){
            statusValue= ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
        }*/
        String finalId = UUID.randomUUID().toString();

        if(dto.getServiceSectorType().equalsIgnoreCase("New Registration")){
            actorId=sysuserId;
        }else if(dto.getServiceSectorType().equalsIgnoreCase("Renewal of CDB Certificate") || dto.getServiceSectorType().equalsIgnoreCase("Cancellation of Registration")){
            actorId=userID;
        }
        try {
            if(dto.getServiceSectorType().equalsIgnoreCase("Renewal of CDB Certificate")){
                org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crpspecializedtradefinal\n" +
                        "(SysUserId,ReferenceNo,ApplicationDate,SPNo,CmnSalutationId,NAME,CmnDzongkhagId,Gewog,Village,Email,MobileNo,TelephoneNo,EmployerName,EmployerAddress,\n" +
                        "TPN,CmnApplicationRegistrationStatusId,RegistrationApprovedDate,RegistrationExpiryDate,CreatedBy,CIDNo,CrpSpecializedTradeId,Id,InitialDate,CreatedOn,EditedOn)\n" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE,CURRENT_DATE,CURRENT_TIMESTAMP)");
                query1.setParameter(1, sysuserId).setParameter(2, dto.getReferenceNo()).setParameter(3, dto.getApplicationDate()).setParameter(4, dto.getCdbNo()).setParameter(5, dto.getSalutation()).setParameter(6, dto.getFullname()).setParameter(7, dto.getDzongkhagId()).setParameter(8, dto.getGewog()).setParameter(9, dto.getVillage())
                        .setParameter(10, dto.getEmail()).setParameter(11, dto.getMobileNo()).setParameter(12, dto.getTelephoneNo()).setParameter(13, dto.getEmployeeName()).setParameter(14, dto.getEmployeeAddress()).setParameter(15, dto.getTpn()).setParameter(16,ApplicationStatus.APPROVED.getCode()).setParameter(17, dto.getApplicationDate()).setParameter(18, dto.getRegExpDate()).setParameter(19, userID)
                        .setParameter(20, dto.getCidNo()).setParameter(21,dto.getCrpSpecializedTradeId()).setParameter(22,finalId);
                int save = query1.executeUpdate();
                if (save > 0) {
                    return_value = "Success";
                } else {
                    return_value = "fail";
                }
            } else {
                org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crpspecializedtradefinal\n" +
                        "(SysUserId,ReferenceNo,ApplicationDate,SPNo,CmnSalutationId,NAME,CmnDzongkhagId,Gewog,Village,Email,MobileNo,TelephoneNo,EmployerName,EmployerAddress,\n" +
                        "TPN,CmnApplicationRegistrationStatusId,RegistrationApprovedDate,RegistrationExpiryDate,CreatedBy,CIDNo,CrpSpecializedTradeId,Id,InitialDate,CreatedOn,EditedOn)\n" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE,CURRENT_DATE,CURRENT_TIMESTAMP)");
                query1.setParameter(1, actorId).setParameter(2, dto.getReferenceNo()).setParameter(3, dto.getApplicationDate()).setParameter(4, dto.getCdbNo()).setParameter(5, dto.getSalutation()).setParameter(6, dto.getFullname()).setParameter(7, dto.getDzongkhagId()).setParameter(8, dto.getGewog()).setParameter(9, dto.getVillage())
                        .setParameter(10, dto.getEmail()).setParameter(11, dto.getMobileNo()).setParameter(12, dto.getTelephoneNo()).setParameter(13, dto.getEmployeeName()).setParameter(14, dto.getEmployeeAddress()).setParameter(15, dto.getTpn()).setParameter(16, ApplicationStatus.APPROVED.getCode()).setParameter(17, dto.getApplicationDate()).setParameter(18, dto.getRegExpDate()).setParameter(19, userID)
                        .setParameter(20, dto.getCidNo()).setParameter(21, dto.getCrpSpecializedTradeId()).setParameter(22, finalId);
                int save = query1.executeUpdate();
                if (save > 0) {
                    org.hibernate.query.Query querydoc = sqlQuery("INSERT INTO `crpspecializedtradeattachmentfinal` (`Id`,`CrpSpecializedTradeFinalId`,`DocumentName`,`DocumentPath`,`FileType`,`CreatedBy`,`CreatedOn`) SELECT a.`Id`,a.`CrpSpecializedTradeId`,a.`DocumentName`,a.`DocumentPath`,a.`FileType`,?,CURRENT_TIMESTAMP FROM `crpspecializedtradeattachment` a  LEFT JOIN `crpspecializedtrade` ar ON a.`CrpSpecializedTradeId`=ar.`Id` WHERE ar.`ReferenceNo`=? ");
                    querydoc.setParameter(1, userID).setParameter(2, dto.getReferenceNo());
                    save = querydoc.executeUpdate();
                    if (save > 0) {
                        return_value = "Success";
                    } else {
                        return_value = "fail";
                    }
                } else {
                    System.out.print("exception while inserting to crpspecializedtradeattachmentfinal");
                    return_value = "Fail";
                }
                if (save > 0) {
                    org.hibernate.query.Query querydoc = sqlQuery("INSERT INTO crpspecializedtradeworkclassificationfinal(Id,CrpSpecializedTradeFinalId,CmnAppliedCategoryId,CmnVerifiedCategoryId,CmnApprovedCategoryId,CreatedBy,EditedBy,CreatedOn,EditedOn)SELECT a.Id,a.CrpSpecializedTradeId,a.CmnAppliedCategoryId,a.CmnVerifiedCategoryId,a.CmnApprovedCategoryId,?,a.EditedBy,a.CreatedOn,a.EditedOn FROM crpspecializedtradeworkclassification a LEFT JOIN crpspecializedtrade c ON c.CrpSpecializedTradeId=a.CrpSpecializedTradeId WHERE c.`ReferenceNo`=?");
                    querydoc.setParameter(1, userID).setParameter(2, dto.getReferenceNo());
                    save = querydoc.executeUpdate();
                    if (save > 0) {
                        return_value = "Success";
                    } else {
                        return_value = "fail";
                    }
                } else {
                    System.out.print("exception while inserting to crpspecializedtradeworkclassificationfinal");
                    return_value = "Fail";
                }
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # insertspTradeFinalDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String getCrpspecializedtradeid(TradeDto dto) {
        org.hibernate.query.Query sql=sqlQuery("SELECT c.CrpSpecializedTradeId FROM crpspecializedtrade c WHERE c.ReferenceNo=?");
        return (String) sql.setParameter(1,dto.getReferenceNo()).list().get(0);
    }

    @Transactional
    public TradeDto populateSpApplicantDetails(String cdbNo) {
        TradeDto dto = new TradeDto();
        try {
            sqlQuery="SELECT \n" +
                    "  f.SPNo cdbNo,\n" +
                    "  f.CrpSpecializedTradeId crpSpecializedTradeId,\n" +
                    "  f.ReferenceNo referenceNo,\n" +
                    "  f.CIDNo cidNo,\n" +
                    "  f.Name fullname,\n" +
                    "  v.Village_Name village,\n" +
                    "  g.Gewog_Name gewog,\n" +
                    "  dd.NameEn dzongkhagId,\n" +
                    "  c.Name countryId,\n" +
                    "  d.NameEn cmnRegisteredDzongkhagId,\n" +
                    "  s.Name salutation,\n" +
                    "  f.TelephoneNo telephoneNo,\n" +
                    "  f.TPN tpn,\n" +
                    "  i.Name updateStatus,\n" +
                    "  f.Email email,\n" +
                    "  f.MobileNo mobileNo,\n" +
                    "  f.EmployerName employeeName,\n" +
                    "  f.EmployerAddress employeeAddress,\n" +
                    "  f.RegistrationApprovedDate registrationApproveDate,\n" +
                    "  f.RegistrationExpiryDate regExpDate \n" +
                    "FROM\n" +
                    "  crpspecializedtradefinal f \n" +
                    "  LEFT JOIN cmnlistitem i \n" +
                    "    ON i.Id = f.CmnApplicationRegistrationStatusId \n" +
                    "  LEFT JOIN cmnlistitem s \n" +
                    "    ON s.Id = f.CmnSalutationId \n" +
                    "  LEFT JOIN cmndzongkhag d \n" +
                    "    ON d.Dzongkhag_Serial_No = f.CmnRegisteredDzongkhagId \n" +
                    "  LEFT JOIN cmncountry c \n" +
                    "    ON c.Id = f.CmnCountryId \n" +
                    "  LEFT JOIN sysuser su \n" +
                    "    ON su.Email = f.Email \n" +
                    "  LEFT JOIN cmnvillage v \n" +
                    "    ON v.Village_Name = f.village \n" +
                    "  LEFT JOIN cmngewog g \n" +
                    "    ON g.Gewog_Serial_No = v.Gewog_Serial_No \n" +
                    "  LEFT JOIN cmndzongkhag dd \n" +
                    "    ON dd.Dzongkhag_Serial_No = g.Dzongkhag_Serial_No \n" +
                    "WHERE f.SPNo = ?";
            dto=(TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, cdbNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # populateSpApplicantDetails: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public TradeDto updatecrpspecializedTrade(String specializedTradeNo, String appNo) {
        String sqlQuery="UPDATE crpspecializedtrade SET SPNo=? WHERE ReferenceNo=?";
        org.hibernate.query.Query hQuery=hibernateQuery(sqlQuery,TradeDto.class).setParameter(1,specializedTradeNo).setParameter(2,appNo);
        hQuery.executeUpdate();
        return null;
    }

    @Transactional
    public TradeDto getspTradeDetails(String cdbNo) {
        TradeDto dto = new TradeDto();
        try {
            sqlQuery="SELECT f.CIDNo cidNo,f.SPNo cdbNo,f.RegistrationApprovedDate registrationApproveDate,f.RegistrationExpiryDate regExpDate,f.Name fullname,f.CmnSalutationId salutation,f.CmnDzongkhagId dzongkhagId,f.Village village,f.Gewog gewog,f.Email email,f.MobileNo mobileNo,f.EmployerName employeeName,f.EmployerAddress employeeAddress,f.TelephoneNo telephoneNo,f.TPN tpn FROM crpspecializedtradefinal f  WHERE f.SPNo=?";
            Query queryre = sqlQuery(sqlQuery, TradeDto.class).setParameter(1, cdbNo);
            if(queryre.list().size()>0){
                dto=(TradeDto) queryre.list().get(0);
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # getspTradeDetails: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String updateCancellationDetailsFinal(TradeDto dto1, String userID) {
        String return_value="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtradefinal s SET s.CmnApplicationRegistrationStatusId=?,s.EditedBy=?,s.EditedOn=CURRENT_TIMESTAMP,s.DeregisteredRemarks=?,s.DeRegisteredDate=CURRENT_DATE WHERE s.SPNo=? ");
            query1.setParameter(1, ApplicationStatus.DEREGISTERED.getCode()).setParameter(2, userID).setParameter(3, dto1.getRemarks()).setParameter(4,  dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value="Success";
            }else{
                return_value="failed";
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # updateCancellationDetailsFinal: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public TradeDto specializedtradeservicepayment(TradeDto dto, String userID, HttpServletRequest request, String servicepaymentID) {
        String statuscode="";

        try {
            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crpspecializedtradeservicepayment\n" +
                    "(Id,CrpSpecializedTradeId,CmnServiceTypeId,NoOfDaysLate,NoOfDaysAfterGracePeriod,PenaltyPerDay,\n" +
                    "TotalAmount,PaymentAmount,WaiveOffLateFee,NewLateFeeAmount,Mode_Of_Payment,CreatedBy,EditedBy,CreatedOn,EditedOn)\n" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)");
            query1.setParameter(1, servicepaymentID).setParameter(2, dto.getCrpSpecializedTradeId()).setParameter(3, dto.getServiceTypeId()).setParameter(4, dto.getNoOfDaysLate()).setParameter(5, dto.getNoOfDaysAfterGracePeriod()).setParameter(6, dto.getPenaltyPerDay()).setParameter(7, dto.getTotalAmt()).setParameter(8, dto.getPenaltyPerDay()).setParameter(9, dto.getPaymentAmt())
                    .setParameter(10, dto.getTotalAmt()).setParameter(11, dto.getPaymentmode()).setParameter(12,userID).setParameter(13, userID);
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedTradeDao # updateVerification: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TradeFeesDto> getAppliedServiceId(String crpSpecializedTradeId) {
        List<TradeFeesDto> appservId=new ArrayList<TradeFeesDto>();
        sqlQuery = properties.getProperty("SpecializedDao.appliedserviceid");
        appservId= hibernateQuery(sqlQuery, TradeFeesDto.class).setParameter(1, crpSpecializedTradeId).list();
        return appservId;
    }

    @Transactional(readOnly = true)
    public void saveUpdate(Object object) {
       saveOrUpdate(object);
    }

    public String saveHRdtls(SpecializedtradeHR catEntity) {
        try{
            saveOrUpdate(catEntity);
            return "success";
        }catch (Exception e){
            System.out.print("exception while inserting into saveHRdtls "+e);
        }
        return "fail";
    }

    @Transactional(readOnly = true)
    public List gEquipment() {
        sqlQuery = properties.getProperty("SpecializedDao.gEquipment");
        return hibernateQuery(sqlQuery, TradeFeesDto.class).list();
    }

    public String saveEQdtls(SpecializedtradeEQ specializedtradeEQ) {
        try{
            saveOrUpdate(specializedtradeEQ);
            return "success";
        }catch (Exception e){
            System.out.print("exception while inserting into saveEQdtls "+e);
        }
        return "fail";
    }
    @Transactional(readOnly = true)
    public TradeDto getTradeDetailsForIncop(String appNo) {
            TradeDto dto =new TradeDto();
            try {
                sqlQuery = properties.getProperty("SpecializedDao.getTradeDetailsForIncop");
                dto=(TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, appNo).list().get(0);
            }catch (Exception e){
                e.printStackTrace();
                System.out.print("erroerrrr....SpecializedDao.getTradeDetailsForIncop"+e);
            }
            return dto;
    }
    @Transactional(readOnly = true)
    public List<OwnershipDTO> getOwnerShipDtls(String crpSpecializedTradeId) {
        List<OwnershipDTO> ownershipDTOList=new ArrayList<OwnershipDTO>();
        try {
            sqlQuery = properties.getProperty("SpecializedDao.getOwnerShipDtls");
            ownershipDTOList = hibernateQuery(sqlQuery, OwnershipDTO.class).setParameter(1, crpSpecializedTradeId).list();
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("erroerrrr....SpecializedDao.getOwnerShipDtls"+e);
        }
        return ownershipDTOList;
    }

    @Transactional(readOnly = true)
    public List<SpecializedtradeHR> getSpecializedtradeHRList(String crpSpecializedTradeId) {
        List<SpecializedtradeHR> specializedtradeHRList=new ArrayList<SpecializedtradeHR>();
        try {
            sqlQuery = properties.getProperty("SpecializedDao.getSpecializedtradeHRList");
            specializedtradeHRList = hibernateQuery(sqlQuery, SpecializedtradeHR.class).setParameter(1, crpSpecializedTradeId).list();
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("erroerrrr....SpecializedDao.getSpecializedtradeHRList"+e);
        }
        return specializedtradeHRList;
    }

    @Transactional(readOnly = true)
    public List<SpecializedtradeEQ> getSpecializedtradeEQList(String crpSpecializedTradeId) {
        List<SpecializedtradeEQ> specializedtradeEQList=new ArrayList<SpecializedtradeEQ>();
        try {
            sqlQuery = properties.getProperty("SpecializedDao.getSpecializedtradeEQList");
            specializedtradeEQList = hibernateQuery(sqlQuery, SpecializedtradeEQ.class).setParameter(1, crpSpecializedTradeId).list();
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("erroerrrr....SpecializedDao.getSpecializedtradeEQList"+e);
        }
        return specializedtradeEQList;
    }

    public TradeDocument getDocumentDetailsByDocIdhrDoc(String uploadDocId) {
        TradeDocument dto=new TradeDocument();
        try {
            String GET_DOCUMENT_DTLS_BY_UUID="SELECT a.DocumentName documentName,a.DocumentPath documentPath,a.CrpSpecializedtradeHumanResourceId crpSpecializedTradeId,a.FileType fileType FROM crpspecializedtradehumanresourceattachment a WHERE a.Id = ?";
            Query query = sqlQuery(GET_DOCUMENT_DTLS_BY_UUID,TradeDocument.class).setParameter(1, uploadDocId);
            dto = (TradeDocument) query.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    public TradeDocument getDocumentDetailsByDocIdeqDoc(String uploadDocId) {
        TradeDocument dto=new TradeDocument();
        try {
            String GET_DOCUMENT_DTLS_BY_UUID="SELECT a.DocumentName documentName,a.DocumentPath documentPath,a.CrpSpecializedtradeEquipmentId crpSpecializedTradeId,a.FileType fileType FROM crpspecializedtradeequipmentattachment a WHERE a.Id = ?";
            Query query = sqlQuery(GET_DOCUMENT_DTLS_BY_UUID,TradeDocument.class).setParameter(1, uploadDocId);
            dto = (TradeDocument) query.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    public TradeDto updateApproval(TradeDto dto, String userID, HttpServletRequest request) {
        String statuscode="";
        int validity_year = 3;
        try {
            if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
                statuscode=ApplicationStatus.APPROVED.getCode();
               /* Query query1 = sqlQuery("UPDATE sysuser SET STATUS = ? WHERE SysApproverUserId = ?");
                query1.setParameter(1,0).setParameter(2, userID);
                query1.executeUpdate();*/

            }else{
                statuscode=ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
            }
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade SET RemarksByApprover=?,RegistrationApprovedDate= CURRENT_TIMESTAMP,RegistrationExpiryDate=DATE_ADD(CURRENT_DATE, INTERVAL " + validity_year + " YEAR),SysApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =?");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, statuscode).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedTradeDao # updateApproval: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public SpecializedtradeHR fetchHRdetails(SpecializedtradeHR hrEntity, TradeDto dto1) {
        try {
            sqlQuery = properties.getProperty("SpecializedDao.fetchHRdetails");
            hrEntity = (SpecializedtradeHR) hibernateQuery(sqlQuery, SpecializedtradeHR.class).setParameter(1, dto1.getCrpSpecializedTradeId()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # fetchHRdetails: " + e);
            e.printStackTrace();
        }
        return hrEntity;
    }

    @Transactional
    public SpecializedtradeHRAttachment fetchHRAttdetails(SpecializedtradeHRAttachment hrEntityAttachment, SpecializedtradeHR fetchHRdetails) {
        try {
            sqlQuery = properties.getProperty("SpecializedDao.fetchHRAttdetails");
            hrEntityAttachment = (SpecializedtradeHRAttachment) hibernateQuery(sqlQuery, SpecializedtradeHRAttachment.class).setParameter(1, fetchHRdetails.getId()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # fetchHRAttdetails: " + e);
            e.printStackTrace();
        }
        return hrEntityAttachment;
    }

    @Transactional
    public SpecializedtradeEQ fetchEQdetails(SpecializedtradeEQ eqEntity, TradeDto dto1) {
        try {
            sqlQuery = properties.getProperty("SpecializedDao.fetchEQdetails");
            eqEntity = (SpecializedtradeEQ) hibernateQuery(sqlQuery, SpecializedtradeEQ.class).setParameter(1, dto1.getCrpSpecializedTradeId()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # fetchEQdetails: " + e);
            e.printStackTrace();
        }
        return eqEntity;
    }

    @Transactional
    public SpecializedtradeEQAttachment fetchEQAttdetails(SpecializedtradeEQAttachment eqEntityAttachment, SpecializedtradeEQ fetchEQdetails) {
        try {
            sqlQuery = properties.getProperty("SpecializedDao.fetchEQAttdetails");
            eqEntityAttachment = (SpecializedtradeEQAttachment) hibernateQuery(sqlQuery, SpecializedtradeEQAttachment.class).setParameter(1, fetchEQdetails.getId()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in ConsultantDao # fetchEQAttdetails: " + e);
            e.printStackTrace();
        }
        return eqEntityAttachment;
    }

    @Transactional
    public String insertSpecializedIncoFinalDetails(SpecializedtradeHR fetchHRdetails, SpecializedtradeHRAttachment fetchHRAttdetails, SpecializedtradeEQ fetchEQdetails, SpecializedtradeEQAttachment fetchEQAttdetails, TradeDto dto, String userID, String sysuserId, HttpServletRequest request) {
        String return_value = "", statusValue = "", actorId="";
        if(request.getParameter("servicefor").equalsIgnoreCase("cancel")){
            statusValue = ApplicationStatus.DEREGISTERED.getCode();
        }else if(request.getParameter("servicefor").equalsIgnoreCase("Registration") || request.getParameter("servicefor").equalsIgnoreCase("renewal")){
            statusValue = ApplicationStatus.APPROVED.getCode();
        }
        String finalId = UUID.randomUUID().toString();

        if(dto.getServiceSectorType().equalsIgnoreCase("New Registration")){
            actorId=sysuserId;
        }else if(dto.getServiceSectorType().equalsIgnoreCase("Renewal of CDB Certificate") || dto.getServiceSectorType().equalsIgnoreCase("Cancellation of Registration")){
            actorId=userID;
        }
        try {
            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crpspecializedtradefinal\n" +
                    "(SysUserId,ReferenceNo,ApplicationDate,SPNo,CmnSalutationId,NAME,CmnDzongkhagId,Gewog,Village,Email,MobileNo,TelephoneNo,EmployerName,EmployerAddress,\n" +
                    "TPN,CmnApplicationRegistrationStatusId,RegistrationApprovedDate,RegistrationExpiryDate,CreatedBy,CIDNo,CrpSpecializedTradeId,Id,CmnCountryId,CmnServiceSectorTypeId,CmnRegisteredDzongkhagId,NameOfFirm,TradeLicenseNo,Permenant_village_serialNo,RegisteredAddress,Sex,ShowInCertificate,CmnOwnershipTypeId,\n" +
                    "NationalityId,DesignationId,FaxNo,InitialDate,CreatedOn,EditedOn)\n" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE,CURRENT_DATE,CURRENT_TIMESTAMP)");
            query1.setParameter(1,actorId).setParameter(2, dto.getReferenceNo()).setParameter(3, dto.getApplicationDate()).setParameter(4, dto.getCdbNo()).setParameter(5, dto.getSalutation()).setParameter(6, dto.getFullname()).setParameter(7, dto.getDzongkhagId()).setParameter(8, dto.getGewog()).setParameter(9, dto.getVillage())
                    .setParameter(10, dto.getEmail()).setParameter(11, dto.getMobileNo()).setParameter(12, dto.getTelephoneNo()).setParameter(13, dto.getEmployeeName()).setParameter(14, dto.getEmployeeAddress()).setParameter(15, dto.getTpn()).setParameter(16,statusValue).setParameter(17, dto.getRegistrationApproveDate()).setParameter(18, dto.getApplicationDate()).setParameter(19, userID)
                    .setParameter(20, dto.getCidNo()).setParameter(21,dto.getCrpSpecializedTradeId()).setParameter(22,finalId).setParameter(23, dto.getCountryId()).setParameter(24,dto.getServiceSectorType()).setParameter(25,dto.getCmnRegisteredDzongkhagId()).setParameter(26,dto.getFirmName()).setParameter(27, dto.getTradeLicenseNo()).setParameter(28,dto.getPervillageserialno()).setParameter(29,dto.getRegisteredAddress())
                    .setParameter(30, dto.getSex()).setParameter(31, dto.getShowInCertificate()).setParameter(32,dto.getOwnershiptype()).setParameter(33,dto.getNationality()).setParameter(34,dto.getDesignationId()).setParameter(35,dto.getRegFaxNo());
            int save = query1.executeUpdate();
            if (save >= 1) {
                String hrfinalId = UUID.randomUUID().toString();
                String sex = "";
                if (fetchHRdetails.getSex().equalsIgnoreCase("Male")) {
                    sex = "M";
                } else {
                    sex = "F";
                }
                org.hibernate.query.Query query2 = sqlQuery("INSERT INTO crpspecializedtradehumanresourcefinal(Id,CrpSpecializedTradeFinalId,CmnSalutationId,CIDNo,NAME,Sex,CmnCountryId,CmnQualificationId,CmnServiceTypeId,CmnTradeId,CmnDesignationId,ShowInCertificate,IsPartnerOrOwner,CreatedBy,CreatedOn)\n" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)");
                query2.setParameter(1, hrfinalId).setParameter(2, fetchHRdetails.getSpecializedTradeId()).setParameter(3, fetchHRdetails.getSalutationId()).setParameter(4, fetchHRdetails.getCidNo()).setParameter(5, fetchHRdetails.getName()).setParameter(6, sex).setParameter(7, fetchHRdetails.getCountryId()).setParameter(8, fetchHRdetails.getQualificationId()).setParameter(9, fetchHRdetails.getServiceTypeId()).setParameter(10, fetchHRdetails.getTradeId()).setParameter(11, fetchHRdetails.getDesignationId()).setParameter(12, fetchHRdetails.getSiCertificate()).setParameter(13, fetchHRdetails.getIsPartnerOrOwner()).setParameter(14, userID);
                save = query2.executeUpdate();
                if (save >= 1) {
                    String hrAttfinalId = UUID.randomUUID().toString();
                    org.hibernate.query.Query query3 = sqlQuery("INSERT INTO crpspecializedtradehumanresourceattachmentfinal(Id,CrpConsultantHumanResourceFinalId,DocumentName,DocumentPath,FileType,CreatedBy,CreatedOn)VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP)");
                    query3.setParameter(1, hrAttfinalId).setParameter(2,hrfinalId).setParameter(3, fetchHRAttdetails.getDocumentName()).setParameter(4, fetchHRAttdetails.getDocumentPath()).setParameter(5, fetchHRAttdetails.getFileType()).setParameter(6, userID);
                    save = query3.executeUpdate();
                    if (save >= 1) {
                        String eqfinalId = UUID.randomUUID().toString();
                        org.hibernate.query.Query query4 = sqlQuery("INSERT INTO crpspecializedtradeequipmentfinal(Id,CrpSpecializedtradeFinalId,CmnEquipmentId,RegistrationNo,SerialNo,ModelNo,Quantity,CreatedBy,CreatedOn)VALUES (?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)");
                        query4.setParameter(1, eqfinalId).setParameter(2, fetchEQdetails.getCrpSpecializedTradeId()).setParameter(3, fetchEQdetails.getEquipmentId()).setParameter(4, fetchEQdetails.getRegistrationNo()).setParameter(5, fetchEQdetails.getSerialNo()).setParameter(6, fetchEQdetails.getModelNo()).setParameter(7, fetchEQdetails.getQuantity()).setParameter(8, userID);
                        save = query4.executeUpdate();
                        if (save >= 1) {
                            String eqAttfinalId = UUID.randomUUID().toString();
                            org.hibernate.query.Query query5 = sqlQuery("INSERT INTO crpspecializedtradeequipmentattachmentfinal(Id,CrpSpecializedtradeEquipmentFinalId,DocumentName,DocumentPath,FileType,CreatedBy,CreatedOn)VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP)");
                            query5.setParameter(1, eqAttfinalId).setParameter(2, eqfinalId).setParameter(3, fetchEQAttdetails.getDocumentName()).setParameter(4, fetchEQAttdetails.getDocumentPath()).setParameter(5, fetchEQAttdetails.getFileType()).setParameter(6, userID);
                            save = query5.executeUpdate();
                            if (save >= 1) {
                                return_value = "Success";
                            } else {
                                return_value = "Failed";
                            }
                        }
                    }
                }
            } else {
                return_value = "Failed";
            }
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # insertSpecializedIncoFinalDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public TradeDto paymentApproved(TradeDto dto, String userID, HttpServletRequest request) {
        try {
            int validity_year = 3;
            String applicationstatus = "";
            applicationstatus = ApplicationStatus.APPROVED.getCode();

            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade SET RemarksByApprover=?,RegistrationApprovedDate= CURRENT_TIMESTAMP,RegistrationApprovedDate=CURRENT_TIMESTAMP,RegistrationExpiryDate=DATE_ADD(CURRENT_DATE, INTERVAL " + validity_year + " YEAR),SysApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =?");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, applicationstatus).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # paymentApproved: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String checkOwnerShipType(String cdbNo) {
        String ownerType="";
        try {
            sqlQuery = properties.getProperty("SpecializedDao.checkOwnerShipType");
            ownerType = (String) hibernateQuery(sqlQuery).setParameter(1, cdbNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # checkOwnerShipType: " + e);
            e.printStackTrace();
        }
        return ownerType;
    }

    @Transactional
    public TradeDto populateSpIncoApplicantDetails(String cdbNo) {
        TradeDto dto = new TradeDto();
        try {
            sqlQuery = "SELECT a.SPNo cdbNo,a.CrpSpecializedTradeId crpSpecializedTradeId,a.Name fullname,a.CIDNo cidNo,a.CmnSalutationId salutation,a.CmnApplicationRegistrationStatusId updateStatus,a.RegisteredAddress registeredAddress,a.Email regEmail,a.MobileNo regMobileNo,a.TelephoneNo regPhoneNo,a.FaxNo regFaxNo,a.NameOfFirm firmName,v.Village_Name village,g.Gewog_Name gewog,dd.NameEn dzongkhagId,c.Name countryId,d.NameEn cmnRegisteredDzongkhagId,a.CmnOwnershipTypeId ownershiptype,a.TradeLicenseNo tradeLicenseNo,a.TPN tpn,a.ReferenceNo,a.RegistrationApprovedDate approvaldate,a.RegistrationExpiryDate regExpDate,a.ApplicationDate applicationDate FROM crpspecializedtrade a LEFT JOIN cmnlistitem i ON i.Id = a.CmnSalutationId LEFT JOIN cmnlistitem li ON a.CmnOwnershipTypeId = li.Id LEFT JOIN cmndzongkhag d ON d.Dzongkhag_Serial_No = a.CmnRegisteredDzongkhagId LEFT JOIN cmncountry c ON c.Id = a.CmnCountryId LEFT JOIN sysuser su ON su.Email = a.Email LEFT JOIN cmnvillage v ON v.Village_Serial_No = a.Permenant_village_serialNo LEFT JOIN cmngewog g ON g.Gewog_Serial_No = v.Gewog_Serial_No LEFT JOIN cmndzongkhag dd ON dd.Dzongkhag_Serial_No = g.Dzongkhag_Serial_No WHERE a.SPNo =? GROUP BY a.SPNo ";
            dto = (TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, cdbNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # populateSpIncoApplicantDetails:" + e);
            e.printStackTrace();
        }
        return dto;
    }

    public String updateCrpspecializedtradeRenewal(TradeDto dto, MultipartFile[] fileEQ, MultipartFile[] files, MultipartFile[] fileHR, UserSessionDetailDTO userId) {
        String return_value = "";
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade s SET s.CmnApplicationRegistrationStatusId=?,s.EditedBy=?,s.EditedOn=CURRENT_TIMESTAMP,s.Email=?,s.FaxNo=?,s.TPN=?,s.TelephoneNo=?,s.MobileNo=?,s.TradeLicenseNo=?,s.ApplicationDate=CURRENT_DATE WHERE s.ReferenceNo=?");
            query1.setParameter(1, ApplicationStatus.UNDER_PROCESS.getCode()).setParameter(2,userId).setParameter(3,dto.getRegEmail()).setParameter(4,dto.getRegFaxNo()).setParameter(5,dto.getTpn()).setParameter(6,dto.getRegPhoneNo()).setParameter(7,dto.getRegMobileNo()).setParameter(8,dto.getTradeLicenseNo()).setParameter(9,dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            } else {
                return_value = "failed";
            }
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # updateCrpspecializedtradeRenewal: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String updatecrpspecializedtradeappliedservice(TradeDto dto, UserSessionDetailDTO userId) {
        String return_value = "";
        String type="45bc628b-cbbe-11e4-83fb-080027dcfac6";
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtradeappliedservice s SET s.CmnServiceTypeId=?,s.EditedOn=CURRENT_DATE,s.CreatedBy=? WHERE s.CrpSpecializedTradeId = ?");
            query1.setParameter(1, type).setParameter(2,userId).setParameter(3,dto.getCrpSpecializedTradeId());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            } else {
                return_value = "failed";
            }
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # updatecrpspecializedtradeappliedservice: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public TradeDto hrDetails(TradeDto dto) {
        try {
            sqlQuery = "SELECT c.CrpSpecializedTradeId crpSpecializedTradeId FROM crpspecializedtradehumanresource c LEFT JOIN crpspecializedtrade d ON d.CrpSpecializedTradeId = c.CrpSpecializedTradeId WHERE d.ReferenceNo = ?";
            dto = (TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, dto.getReferenceNo()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # hrDetails:" + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public TradeDto eqDetails(TradeDto dto) {
        try {
            sqlQuery = "SELECT c.CrpSpecializedTradeId crpSpecializedTradeId FROM crpspecializedtradeequipment c LEFT JOIN crpspecializedtrade d ON d.CrpSpecializedTradeId=c.CrpSpecializedTradeId WHERE d.ReferenceNo=?";
            dto = (TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, dto.getReferenceNo()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # eqDetails:" + e);
            e.printStackTrace();
        }
        return dto;
    }

    public String updateApplicationCancellationspecializedtrade(TradeDto dto, UserSessionDetailDTO user, LoggedInUser loggedInUser) {
        String return_value = "";
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade s SET s.CmnApplicationRegistrationStatusId=?,s.EditedBy=?,s.EditedOn=CURRENT_TIMESTAMP,s.ApplicationDate=CURRENT_DATE WHERE s.ReferenceNo=?");
            query1.setParameter(1, ApplicationStatus.VERIFIED.getCode()).setParameter(2, user).setParameter(3,dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            } else {
                return_value = "failed";
            }
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # updateApplicationCancellationspecializedtrade: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String updatecrpspecializedtradeappliedserviceCancellation(TradeDto dto, UserSessionDetailDTO user) {
        String return_value = "";
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtradeappliedservice s SET s.CmnServiceTypeId=?,s.EditedOn=CURRENT_DATE,s.CreatedBy=? WHERE s.CrpSpecializedTradeId = ?");
            query1.setParameter(1,  ApplicationStatus.CANCELLATION.getCode()).setParameter(2,user).setParameter(3,dto.getCrpSpecializedTradeId());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            } else {
                return_value = "failed";
            }
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # updatecrpspecializedtradeappliedserviceCancellation: " + e);
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
            System.out.print("Exception in specializedtrade # isMailUnique:" + e);
            e.printStackTrace();
        }
        return isMailUnique;
    }

    @Transactional
    public String getCancellationRemarks(String appNo) {
      String cancelReason="";
        try {
            String query="SELECT f.CancellationRemarks cancellationRemarks FROM crpspecializedtrade f WHERE f.ReferenceNo=?";
            cancelReason= (String) hibernateQuery(query, TradeDto.class).setParameter(1, appNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # getCancellationRemarks: " + e);
            e.printStackTrace();
        }
        return cancelReason;
    }

    @Transactional(readOnly = true)
    public SpecializedFirmFinal getSpecializedTradeFinal(String cdbNo) {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<SpecializedFirmFinal> cQuery = builder.createQuery(SpecializedFirmFinal.class);
        Root<SpecializedFirmFinal> root = cQuery.from(SpecializedFirmFinal.class);
        cQuery.select(root).where(builder.equal(root.get("cdbNo"), cdbNo));
        SpecializedFirmFinal specializedtradeFinal = getCurrentSession().createQuery(cQuery).getSingleResult();
        em.detach(specializedtradeFinal);
        return specializedtradeFinal;
    }

    public ArchitectDto getSpecializedtradeOngoingApp(String cdbNo) {
        sqlQuery = properties.getProperty("SpecializedDao.getSpecializedtradeOngoingApp");
        List list = hibernateQuery(sqlQuery, ArchitectDto.class).setParameter("cdbNo", cdbNo).list();
        return (ArchitectDto)(list.isEmpty()?null:list.get(0));
    }

    @Transactional
    public String deletePrevRecord(TradeDto dto1) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("DELETE FROM crpspecializedtradefinal WHERE SPNo =?");
            query1.setParameter(1, dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # deletePrevRecord: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public String deleteFromSysuser(TradeDto dto1) {
        String return_value = "";
        try {
            Query query1 = sqlQuery("DELETE FROM sysuser WHERE username =?");
            query1.setParameter(1, dto1.getEmail());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value = "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # deleteFromSysuser: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String isCIDUnique(String cidNo) {
        String isCIDUnique = "";
        try {
            sqlQuery = "SELECT c.CmnApplicationRegistrationStatusId FROM crpspecializedtrade c WHERE c.CIDNo=?";
            isCIDUnique = (String) hibernateQuery(sqlQuery).setParameter(1, cidNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in specializedtrade # isCIDUnique:" + e);
            e.printStackTrace();
        }
        return isCIDUnique;
    }

    public CertificateDTO getSpecializedTradePrintDetails(HttpServletRequest request, String cdbNo) {
        CertificateDTO dto = new CertificateDTO();
        sqlQuery = properties.getProperty("SpecializedDao.getSpecializedTradePrintDetails");
        dto = (CertificateDTO) hibernateQuery(sqlQuery, CertificateDTO.class).setParameter(1, cdbNo).list().get(0);
        return dto;
    }

    @Transactional
    public List<TasklistDto> populateapplicationHistorySpecializedFirm(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.`ReferenceNo` applicationNo, e.serviceName,\n" +
                    " b.`Name` AS appStatus,a.`ApplicationDate` appDate \n" +
                    "FROM `crpspecializedtrade` a  INNER JOIN `cmnlistitem` b ON b.`Id`  = a.`CmnApplicationRegistrationStatusId`\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.`CrpSpecializedTradeId`,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name SEPARATOR ', ')serviceName  \n" +
                    "FROM `crpspecializedtradeappliedservice` c \n" +
                    "INNER JOIN `crpservice` d ON d.`Id` = c.`CmnServiceTypeId` GROUP BY c.`CrpSpecializedTradeId`\n" +
                    ") e ON e.`CrpSpecializedTradeId` = a.`CrpSpecializedTradeId` \n" +
                    "WHERE a.SPNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # populateapplicationHistory: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplicationSpecializedFirm(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,\n" +
                    "b.Name AS appStatus,a.ApplicationDate appDate,a.RemarksByRejector remarks\n" +
                    "FROM crpspecializedtrade a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpSpecializedTradeId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name SEPARATOR ', ')serviceName  \n" +
                    "FROM crpspecializedtradeappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpSpecializedTradeId\n" +
                    ") e ON e.CrpSpecializedTradeId = a.CrpSpecializedTradeId \n" +
                    "WHERE a.CmnApplicationRegistrationStatusId = 'de662a61-b049-11e4-89f3-080027dcfac6' AND a.SPNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # populateapplicationHistory: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TasklistDto> populateapplicationHistorySptrade(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate applicationDate,a.SPNo cdbNo, \n" +
                    "b.Name AS appStatus,\n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services'\n" +
                    "END AS serviceName\n" +
                    "FROM crpspecializedtradefinal a INNER JOIN cmnlistitem b ON b.Id = a.CmnApplicationRegistrationStatusId  \n" +
                    "INNER JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId = a.CrpSpecializedTradeId WHERE  a.SPNo =? GROUP BY a.ReferenceNo  ORDER BY a.ReferenceNo DESC";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # populateapplicationHistorySptrade: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplicationSptrade(String cdbNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate applicationDate,a.SPNo cdbNo, \n" +
                    "b.Name AS appStatus,\n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services'\n" +
                    "END AS serviceName\n" +
                    "FROM crpspecializedtrade a INNER JOIN cmnlistitem b ON b.Id = a.CmnApplicationRegistrationStatusId  \n" +
                    "INNER JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId = a.CrpSpecializedTradeId WHERE a.CmnApplicationRegistrationStatusId = 'de662a61-b049-11e4-89f3-080027dcfac6' AND  s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' OR s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' AND a.SPNo =? GROUP BY a.ReferenceNo  ORDER BY a.ReferenceNo DESC";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbNo).list();
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # populaterejectedApplicationSptrade: " + e);
            e.printStackTrace();
        }
        return dto;
    }
}

