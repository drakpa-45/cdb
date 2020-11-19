package com.ngn.spring.project.cdb.admin.contractor.os;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
import org.springframework.stereotype.Repository;

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
public class ContractorOSActionDao extends BaseDao {

    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo) {
        sqlQuery = properties.getProperty("ContractorRCActionDao.getAppliedServices");
        return hibernateQuery(sqlQuery, AppliedServiceFeeDTO.class)
                .setParameter("applicationNo", applicationNo).list();

    }

    public List<ContractorHrDTO> getDeleteHrRequest(String cdbNo) {
        sqlQuery = properties.getProperty("ContractorRCActionDao.getDeleteHrRequest");
        return hibernateQuery(sqlQuery, ContractorHrDTO.class)
                .setParameter("cdbNo", cdbNo).list();
    }
}
