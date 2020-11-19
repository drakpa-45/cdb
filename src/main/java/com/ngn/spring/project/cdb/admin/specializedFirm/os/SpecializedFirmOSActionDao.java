package com.ngn.spring.project.cdb.admin.specializedFirm.os;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.AppliedServiceFeeDTO;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmHrDTO;
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
public class SpecializedFirmOSActionDao extends BaseDao {

    public List<AppliedServiceFeeDTO> getAppliedServices(String applicationNo) {
        sqlQuery = properties.getProperty("ContractorRCActionDao.getAppliedServices");
        return hibernateQuery(sqlQuery, AppliedServiceFeeDTO.class)
                .setParameter("applicationNo", applicationNo).list();
    }

    public List<SpFirmHrDTO> getDeleteHrRequest(String cdbNo) {
        sqlQuery = properties.getProperty("ContractorRCActionDao.getDeleteHrRequest");
        return hibernateQuery(sqlQuery, SpFirmHrDTO.class)
                .setParameter("cdbNo", cdbNo).list();
    }
}
