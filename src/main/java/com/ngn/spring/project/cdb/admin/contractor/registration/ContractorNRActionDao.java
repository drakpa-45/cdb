package com.ngn.spring.project.cdb.admin.contractor.registration;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.*;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorAttachment;
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
public class ContractorNRActionDao extends BaseDao {

    @Transactional(readOnly = true)
    public List gTaskList(String userId,String status,String service) {
        sqlQuery = properties.getProperty("ContractorActionDao.gTaskList");
        return hibernateQuery(sqlQuery, TasksDTO.class).setParameter("userId", userId)
                .setParameter("status", status).setParameter("service", service).list();
    }
    /*@Transactional(readOnly = true)
    public List<ContractorHR> getContractorHRs(String contractorId){
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ContractorHR> cQuery = builder.createQuery(ContractorHR.class);
        Root<ContractorHR> root = cQuery.from(ContractorHR.class);
        cQuery.select(root).where(builder.equal(root.get("contractorID"), contractorId));
        return getCurrentSession().createQuery(cQuery).list();
    }*/

    /**
     * To get the HR detail for contractor
     * @param contractorId --  contractor id
     * @param ownerOrPartner -- 'O' only owner, 'H' only HR, 'B' for both owner and HR
     * @return
     */
    @Transactional(readOnly = true)
    public List<ContractorHrDTO> getContractorHRs(String contractorId,Character ownerOrPartner) {
        if(ownerOrPartner == 'B'){
            ownerOrPartner = null;
        } else if(ownerOrPartner == 'O'){
            ownerOrPartner = '1';
        } else if(ownerOrPartner == 'H'){
            ownerOrPartner = '0';
        }
        sqlQuery = properties.getProperty("ContractorActionDao.getContractorHRs");
        return hibernateQuery(sqlQuery, ContractorHrDTO.class).setParameter("contractorId", contractorId)
                .setParameter("ownerOrPartner",ownerOrPartner).list();
    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getFeeCategoryClass(String contractorId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getFeeCategoryClass");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("contractorId", contractorId).list();
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipment(String contractorId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getEquipment");
        return hibernateQuery(sqlQuery, EquipmentDTO.class).setParameter("contractorId", contractorId).list();
    }

    @Transactional(readOnly = true)
    public String getNextCDBNo() {
        sqlQuery = properties.getProperty("ContractorActionDao.getNextCDBNo");
        List resultList = hibernateQuery(sqlQuery).list();
        return resultList.isEmpty()?"1":Integer.toString(((Double) resultList.get(0)).intValue());
    }

    @Transactional(readOnly = false)
    public void verify(String contractorId, String userID, String vRemarks) {
        sqlQuery = properties.getProperty("ContractorActionDao.verify");
        hibernateQuery(sqlQuery).setParameter("contractorId", contractorId)
                .setParameter("vUserId",userID).setParameter("vRemarks",vRemarks).executeUpdate();
    }

    @Transactional(readOnly = true)
    public List<ApplicationHistoryDTO> getAppHistoryDtl(String contractorId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getAppHistoryDtl");
        return (List<ApplicationHistoryDTO>)hibernateQuery(sqlQuery, ApplicationHistoryDTO.class).setParameter("contractorId", contractorId).list();
    }

    @Transactional(readOnly = false)
    public void approve(String contractorId, String userID, String aRemarks) {
        sqlQuery = properties.getProperty("ContractorActionDao.approve");
        hibernateQuery(sqlQuery).setParameter("contractorId", contractorId)
                .setParameter("aUserId",userID).setParameter("aRemarks",aRemarks).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void reject(String contractorId, String userID, String remarks,String rejectedApplicationStatusId) {
        sqlQuery = properties.getProperty("ContractorActionDao.reject");
        hibernateQuery(sqlQuery).setParameter("contractorId", contractorId).setParameter("userId",userID)
                .setParameter("remarks",remarks).setParameter("applicationStatusId",rejectedApplicationStatusId).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void sendBack(String contractorId, String userID, String sendRemarks, String appStatus) {
        sqlQuery = properties.getProperty("ContractorActionDao.sendBack");
        hibernateQuery(sqlQuery).setParameter("contractorId", contractorId)
                .setParameter("userId", userID)
                //.setParameter("remarks",sendRemarks)
                .setParameter("applicationStatusId", appStatus).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void send2MyOrGroupTask(String appNo, String lockUserId) {
        sqlQuery = properties.getProperty("ContractorActionDao.send2MyOrGroupTask");
        hibernateQuery(sqlQuery).setParameter("appNo", appNo)
                .setParameter("lockUserId", lockUserId).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void paymentUpdate(String contractorId,String userId,String appStatusId,String createdBy) {
        sqlQuery = properties.getProperty("ContractorActionDao.paymentUpdate");
        if (createdBy ==  null){
            createdBy = userId;
        }
        hibernateQuery(sqlQuery).setParameter("contractorId", contractorId).setParameter("userId",userId)
                .setParameter("appStatusId",appStatusId).setParameter("createdBy",createdBy).executeUpdate();
         }

    @Transactional(readOnly = false)
    public List<AttachmentDTO> getHRAttachments(String hrId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getHRAttachments");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("hrId", hrId).list();
    }

    @Transactional(readOnly = false)
    public List<AttachmentDTO> getEQAttachments(String eqId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getEQAttachments");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("eqId", eqId).list();
    }

    @Transactional(readOnly = false)
    public List<ContractorAttachment> getIncAttachment(String contractorId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getIncAttachment");
        return hibernateQuery(sqlQuery, ContractorAttachment.class).setParameter("contractorId", contractorId).list();
    }

    public String getCDBNoFromAppNo(String appNo) {
        sqlQuery = properties.getProperty("ContractorActionDao.getCDBNoFromAppNo");
        return hibernateQuery(sqlQuery).setParameter("appNo", appNo).list().get(0).toString();
    }
}
