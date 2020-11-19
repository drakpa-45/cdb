package com.ngn.spring.project.cdb.admin.specializedFirm;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.*;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmHrDTO;
import com.ngn.spring.project.cdb.specializedFirm.model.SpFirmAttachment;
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
public class SpecializedFirmActionDao extends BaseDao {

    @Transactional(readOnly = true)
    public List gTaskList(String userId,String status,String service) {
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.gTaskList");
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
     * @param specializedFirmId --  contractor id
     * @param ownerOrPartner -- 'O' only owner, 'H' only HR, 'B' for both owner and HR
     * @return
     */
    @Transactional(readOnly = true)
    public List<SpFirmHrDTO> getSpecializedFirmHRs(String specializedFirmId,Character ownerOrPartner) {
        if(ownerOrPartner == 'B'){
            ownerOrPartner = null;
        }else if(ownerOrPartner == 'O'){
            ownerOrPartner = '1';
        }else if(ownerOrPartner == 'H'){
            ownerOrPartner = '0';
        }
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.getSpecializedFirmHRs");
        return hibernateQuery(sqlQuery, SpFirmHrDTO.class).setParameter("specializedFirmId", specializedFirmId)
                .setParameter("ownerOrPartner",ownerOrPartner).list();

    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getFeeCategoryClass(String specializedFirmId) {
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.getFeeCategoryClass");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("specializedFirmId", specializedFirmId).list();
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipment(String specializedFirmId) {
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.getEquipment");
        return hibernateQuery(sqlQuery, EquipmentDTO.class).setParameter("specializedFirmId", specializedFirmId).list();
    }

    @Transactional(readOnly = true)
    public String getNextCDBNo() {
     /*   sqlQuery = properties.getProperty("SpecializedFirmActionDao.getNextCDBNo");
        List resultList = hibernateQuery(sqlQuery).list();
        return resultList.isEmpty()?"1":Integer.toString(((Double) resultList.get(0)).intValue());
        */
        String spTradeNo="", selectquery="";
        String firstpart="";
        try {
            firstpart="SF-";
            selectquery = "SELECT MAX(a.SPNo) cdbNo FROM crpspecializedtradefinal a WHERE a.SPNo LIKE 'SF-%'";
            String curr_specializedTradeNo = (String) hibernateQuery(selectquery).list().get(0);
            if (curr_specializedTradeNo == null) {
                curr_specializedTradeNo = firstpart + "001";
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
            System.out.print("Exception in SpecializedDao # generatespecializedFirmNo: " + e);
            e.printStackTrace();
        }
        return spTradeNo;
    }

    @Transactional(readOnly = false)
    public void verify(String specializedFirmId, String userID, String vRemarks) {
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.verify");
        hibernateQuery(sqlQuery)
                .setParameter("specializedFirmId", specializedFirmId)
                .setParameter("vUserId",userID)
                .setParameter("vRemarks",vRemarks).executeUpdate();
    }

    @Transactional(readOnly = true)
    public List<ApplicationHistoryDTO> getAppHistoryDtl(String specializedFirmId) {
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.getAppHistoryDtl");
        return (List<ApplicationHistoryDTO>)hibernateQuery(sqlQuery, ApplicationHistoryDTO.class).setParameter("specializedFirmId", specializedFirmId).list();
    }

    @Transactional(readOnly = false)
    public void approve(String specializedFirmId, String userID, String aRemarks) {
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.approve");
        hibernateQuery(sqlQuery)
                .setParameter("specializedFirmId", specializedFirmId)
                .setParameter("aUserId",userID)
                .setParameter("aRemarks",aRemarks).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void reject(String specializedFirmId, String userID, String remarks,String rejectedApplicationStatusId) {
        sqlQuery = properties.getProperty("specializedFirmActionDao.reject");
        hibernateQuery(sqlQuery)
                .setParameter("specializedFirmId", specializedFirmId)
                .setParameter("userId",userID)
                .setParameter("remarks",remarks)
                .setParameter("applicationStatusId",rejectedApplicationStatusId).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void sendBack(String specializedFirmId, String userID, String sendRemarks, String appStatus) {
        sqlQuery = properties.getProperty("specializedFirmActionDao.sendBack");
        hibernateQuery(sqlQuery)
                .setParameter("specializedFirmId", specializedFirmId)
                .setParameter("userId", userID)
                //.setParameter("remarks",sendRemarks)
                .setParameter("applicationStatusId", appStatus).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void send2MyOrGroupTask(String appNo, String lockUserId) {
        sqlQuery = properties.getProperty("SpecializedDao.send2MyOrGroupTask");
        hibernateQuery(sqlQuery)
                .setParameter("appNo", appNo)
                .setParameter("lockUserId", lockUserId).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void paymentUpdate(String specializedFirmId,String userId,String appStatusId,String createdBy) {
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.paymentUpdate");
        if (createdBy ==  null){
            createdBy = userId;
        }
        hibernateQuery(sqlQuery)
                .setParameter("specializedFirmId", specializedFirmId)
                .setParameter("userId",userId)
                .setParameter("appStatusId",appStatusId)
                .setParameter("createdBy",createdBy)
                .executeUpdate();
         }

    @Transactional(readOnly = false)
    public List<AttachmentDTO> getHRAttachments(String hrId) {
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.getHRAttachments");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("hrId", hrId).list();
}

    public List<AttachmentDTO> getEQAttachments(String eqId) {
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.getEQAttachments");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("eqId", eqId).list();
    }

    public List<SpFirmHrDTO> getSpFirmHRs(String contractorId, Character ownerOrPartner) {
        return null;
    }

    @Transactional(readOnly = false)
    public List<SpFirmAttachment> getIncAttachment(String crpSpecializedTradeId) {
        sqlQuery = properties.getProperty("SpecializedFirmActionDao.getIncAttachment");
        return hibernateQuery(sqlQuery, SpFirmAttachment.class).setParameter("crpSpecializedTradeId", crpSpecializedTradeId).list();
    }
}
