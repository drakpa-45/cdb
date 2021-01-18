package com.ngn.spring.project.cdb.admin.specializedFirm.renewal;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmHrDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ====================================================================
 * Created by Nima Yoezer on 8/3/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
@Repository
public class SpecializedFirmRCActionDao extends BaseDao {

    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo) {
        sqlQuery = properties.getProperty("SpecializedFirmRCActionDao.getAppliedServices");
        return hibernateQuery(sqlQuery, AppliedServiceFeeDTO.class)
                .setParameter("applicationNo", applicationNo).list();
    }

    public List<ContractorHrDTO> getDeleteHrRequest(String cdbNo) {
        sqlQuery = properties.getProperty("SpecializedFirmRCActionDao.getDeleteHrRequest");
        return hibernateQuery(sqlQuery, SpFirmHrDTO.class).setParameter("cdbNo", cdbNo).list();
    }

    public List getProposedCategories(String appNo) {
        sqlQuery = properties.getProperty("SpecializedFirmRCActionDao.getProposedCategories");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("appNo", appNo).list();
    }

    @Transactional(readOnly = false)
    public void paymentUpdate(String specializedFirmId,String userId,String appStatusId,String createdBy) {
        sqlQuery = properties.getProperty("SpecializedFirmRCActionDao.paymentUpdate");
        if (createdBy ==  null){
            createdBy = userId;
        }
        hibernateQuery(sqlQuery).setParameter("specializedFirmId", specializedFirmId).setParameter("userId",userId)
                .setParameter("appStatusId",appStatusId).setParameter("createdBy",createdBy).executeUpdate();
    }
}
