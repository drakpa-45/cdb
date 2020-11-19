package com.ngn.spring.project.cdb.admin.consultant.renewal;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ====================================================================
 * Created by Pema Drakpa on 8/3/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
@Repository
public class ConsultantRCActionDao extends BaseDao{

    @Transactional(readOnly = false)
    public void paymentUpdate(String consultantId,String userId,String appStatusId,String createdBy) {
        sqlQuery = properties.getProperty("ConsultantRCActionDao.paymentUpdate");
        if (createdBy ==  null){
            createdBy = userId;
        }
        hibernateQuery(sqlQuery)
                .setParameter("consultantId", consultantId)
                .setParameter("userId",userId)
                .setParameter("appStatusId",appStatusId)
                .setParameter("createdBy",createdBy)
                .executeUpdate();
    }

    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo) {
        sqlQuery = properties.getProperty("ConsultantRCActionDao.getAppliedServices");
        return hibernateQuery(sqlQuery, AppliedServiceFeeDTO.class)
                .setParameter("applicationNo", applicationNo).list();
    }
}
