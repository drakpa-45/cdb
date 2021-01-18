package com.ngn.spring.project.cdb.admin.contractor.renewal;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
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
public class ContractorRCActionDao extends BaseDao {

    @Transactional(readOnly = false)
    public void paymentUpdate(String contractorId,String userId,String appStatusId,String createdBy) {
        sqlQuery = properties.getProperty("ContractorRCActionDao.paymentUpdate");
        if (createdBy ==  null){
            createdBy = userId;
        }
        hibernateQuery(sqlQuery).setParameter("contractorId", contractorId).setParameter("userId",userId)
                .setParameter("appStatusId",appStatusId).setParameter("createdBy",createdBy).executeUpdate();
    }

    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo) {
        sqlQuery = properties.getProperty("ContractorRCActionDao.getAppliedServices");
        return hibernateQuery(sqlQuery, AppliedServiceFeeDTO.class).setParameter("applicationNo", applicationNo).list();
    }

    public List<ContractorHrDTO> getDeleteHrRequest(String cdbNo) {
        sqlQuery = properties.getProperty("ContractorRCActionDao.getDeleteHrRequest");
        return hibernateQuery(sqlQuery, ContractorHrDTO.class).setParameter("cdbNo", cdbNo).list();
    }

    public List getProposedCategories(String appNo) {
        sqlQuery = properties.getProperty("ContractorRCActionDao.getProposedCategories");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("appNo", appNo).list();
    }
}
