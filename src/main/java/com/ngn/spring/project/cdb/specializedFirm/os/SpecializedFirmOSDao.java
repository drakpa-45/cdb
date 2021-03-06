package com.ngn.spring.project.cdb.specializedFirm.os;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.common.dto.ServiceFeeDTO;
import com.ngn.spring.project.lib.DropdownDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Repository
public class SpecializedFirmOSDao extends BaseDao {


    public void saveUpdate(Object object) {
        saveOrUpdate(object);
    }

    @Transactional(readOnly = true)
    public DropdownDTO getContractorStatus(String cdbNo) {
        sqlQuery = properties.getProperty("ContractorRCDao.getContractorStatus");
        return (DropdownDTO)hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("CDBNo",cdbNo).list().get(0);
    }

    @Transactional(readOnly = true)
    public List<ServiceFeeDTO> getServicesFee(Integer refNo) {
        sqlQuery = properties.getProperty("ContractorRCDao.getServicesFee");
        hQuery = hibernateQuery(sqlQuery, ServiceFeeDTO.class).setParameter("refNo",refNo);
        return hQuery.list();
    }



}
