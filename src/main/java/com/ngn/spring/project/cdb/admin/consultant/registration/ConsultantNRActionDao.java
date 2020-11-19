package com.ngn.spring.project.cdb.admin.consultant.registration;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.*;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantHrDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 2/14/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Repository
public class ConsultantNRActionDao extends BaseDao {

    @Transactional(readOnly = true)
    public List gTaskList(String userId,String status,String service) {
        sqlQuery = properties.getProperty("ConsultantActionDao.gTaskList");
        return hibernateQuery(sqlQuery, TasksDTO.class).setParameter("userId", userId)
                .setParameter("status", status).setParameter("service", service).list();
    }
    @Transactional(readOnly = true)
    public List gMyTaskList(String userId) {
        sqlQuery = properties.getProperty("ConsultantActionDao.gMyTaskList");
        return hibernateQuery(sqlQuery, TasksDTO.class).setParameter("userId", userId).list();
    }

    /*@Transactional(readOnly = true)
    public List<ContractorHR> getContractorHRs(String contractorId){
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ContractorHR> cQuery = builder.createQuery(ContractorHR.class);
        Root<ContractorHR> root = cQuery.from(ContractorHR.class);
        cQuery.select(root).where(builder.equal(root.get("contractorID"), contractorId));
        return getCurrentSession().createQuery(cQuery).list();
    }*/

    @Transactional(readOnly = true)
    public List<ConsultantHrDTO> getConsultantHRs(String consultantId, Character ownerOrPartner) {
        if(ownerOrPartner == 'B'){
            ownerOrPartner = null;
        }else if(ownerOrPartner == 'O'){
            ownerOrPartner = '1';
        }else if(ownerOrPartner == 'H'){
            ownerOrPartner = '0';
        }
        sqlQuery = properties.getProperty("ConsultantActionDao.getConsultantHRs");
        return hibernateQuery(sqlQuery, ConsultantHrDTO.class).setParameter("consultantId", consultantId).list();
    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getCategoryClass(String consultantId) {
        sqlQuery = properties.getProperty("ConsultantActionDao.getCategoryClass");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("consultantId", consultantId).list();
    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getFeeCategoryClass(String consultantId) {
        sqlQuery = properties.getProperty("ConsultantActionDao.getFeeCategoryClass");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("consultantId", consultantId).list();
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipment(String consultantId) {
        sqlQuery = properties.getProperty("ConsultantActionDao.getEquipment");
        return hibernateQuery(sqlQuery, EquipmentDTO.class).setParameter("consultantId", consultantId).list();
    }

    @Transactional(readOnly = true)
    public String getNextCDBNo() {
        sqlQuery = properties.getProperty("ConsultantActionDao.getNextCDBNo");
        List resultList = hibernateQuery(sqlQuery).list();
        return resultList.isEmpty()?"1":Integer.toString(((Double) resultList.get(0)).intValue());
    }

    @Transactional(readOnly = false)
    public void verify(String consultantId, String userID, String vRemarks) {
        sqlQuery = properties.getProperty("ConsultantActionDao.verify");
        hibernateQuery(sqlQuery)
                .setParameter("consultantId", consultantId)
                .setParameter("vUserId",userID)
                .setParameter("vRemarks",vRemarks).executeUpdate();
    }

    @Transactional(readOnly = true)
    public List<ApplicationHistoryDTO> getAppHistoryDtl(String consultantId) {
        sqlQuery = properties.getProperty("ConsultantActionDao.getAppHistoryDtl");
        return (List<ApplicationHistoryDTO>)hibernateQuery(sqlQuery, ApplicationHistoryDTO.class).setParameter("consultantId", consultantId).list();
    }

    @Transactional(readOnly = false)
    public void approve(String consultantId, String userID, String aRemarks) {
        sqlQuery = properties.getProperty("ConsultantActionDao.approve");
        hibernateQuery(sqlQuery)
                .setParameter("consultantId", consultantId)
                .setParameter("aUserId",userID)
                .setParameter("aRemarks",aRemarks).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void reject(String consultantId, String userID, String remarks,String rejectedApplicationStatusId) {
        sqlQuery = properties.getProperty("ConsultantActionDao.reject");
        hibernateQuery(sqlQuery)
                .setParameter("consultantId", consultantId)
                .setParameter("userId",userID)
                .setParameter("remarks",remarks)
                .setParameter("applicationStatusId",rejectedApplicationStatusId).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void send2MyOrGroupTask(String appNo, String lockUserId) {
        sqlQuery = properties.getProperty("ConsultantActionDao.send2MyOrGroupTask");
        hibernateQuery(sqlQuery)
                .setParameter("appNo", appNo)
                .setParameter("lockUserId", lockUserId).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void paymentUpdate(String consultantId,String userId,String appStatusId,String createdBy) {
        sqlQuery = properties.getProperty("ConsultantActionDao.paymentUpdate");
        hibernateQuery(sqlQuery)
                .setParameter("consultantId", consultantId)
                .setParameter("userId",userId)
                .setParameter("appStatusId",appStatusId)
                .setParameter("createdBy",createdBy)
                .executeUpdate();
         }

    @Transactional(readOnly = false)
    public List<AttachmentDTO> getHRAttachments(String hrId) {
        sqlQuery = properties.getProperty("ConsultantActionDao.getHRAttachments");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("hrId", hrId).list();
    }

    public List<AttachmentDTO> getEQAttachments(String eqId) {
        sqlQuery = properties.getProperty("ConsultantActionDao.getEQAttachments");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("eqId", eqId).list();
    }

    @Transactional(readOnly = false)
    public void sendBack(String consultantId, String userID, String sendRemarks, String appStatus) {
        sqlQuery = properties.getProperty("ConsultantActionDao.sendBack");

        hibernateQuery(sqlQuery)
                .setParameter("consultantId", consultantId)
                .setParameter("userId", userID)
                        //.setParameter("remarks",sendRemarks)
                .setParameter("applicationStatusId", appStatus).executeUpdate();
    }
}
