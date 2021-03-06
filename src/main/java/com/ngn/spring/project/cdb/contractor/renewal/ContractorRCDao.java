package com.ngn.spring.project.cdb.contractor.renewal;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.AttachmentDTO;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.common.dto.ServiceFeeDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorAttachment;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorFinal;
import com.ngn.spring.project.lib.DropdownDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class ContractorRCDao extends BaseDao {

    public void saveUpdate(Object object) {
        saveOrUpdate(object);
    }

    @Transactional(readOnly = true)
    public DropdownDTO getContractorStatus(String cdbNo) {
        sqlQuery = properties.getProperty("ContractorRCDao.getContractorStatus");
        return (DropdownDTO) hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("CDBNo", cdbNo).list().get(0);
    }

    @Transactional(readOnly = true)
    public List<ServiceFeeDTO> getServicesFee(Integer refNo) {
        sqlQuery = properties.getProperty("ContractorRCDao.getServicesFee");
        hQuery = hibernateQuery(sqlQuery, ServiceFeeDTO.class).setParameter("refNo", refNo);
        return hQuery.list();
    }

    /*public Contractor getContractor4rmFinal(String cdbNo) {
        sqlQuery = properties.getProperty("ContractorRCDao.getContractor4rmFinal");
        hQuery = hibernateQuery(sqlQuery, Contractor.class).setParameter("cdbNo",cdbNo);
        return (Contractor)hQuery.list().get(0);
    }*/

    /**
     * To get the HR detail for contractor
     *
     * @param contractorId   --  contractor id
     * @param ownerOrPartner -- 'O' only owner, 'H' only HR, 'B' for both owner and HR
     * @return List
     */

    @Transactional(readOnly = true)
    public List<ContractorHrDTO> getContractorHRsFinal(String contractorId, Character ownerOrPartner) {
        if (ownerOrPartner == 'B') {
            ownerOrPartner = null;
        } else if (ownerOrPartner == 'O') {
            ownerOrPartner = '1';
        } else if (ownerOrPartner == 'H') {
            ownerOrPartner = '0';
        }
        sqlQuery = properties.getProperty("ContractorRCDao.getContractorHRsFinal");
        return hibernateQuery(sqlQuery, ContractorHrDTO.class).setParameter("contractorId", contractorId)
                .setParameter("ownerOrPartner", ownerOrPartner).list();
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipmentFinal(String contractorId) {
        sqlQuery = properties.getProperty("ContractorRCDao.getEquipmentFinal");
        return hibernateQuery(sqlQuery, EquipmentDTO.class).setParameter("contractorId", contractorId).list();
    }

    @Transactional(readOnly = true)
    public List<AttachmentDTO> getHRAttachmentsFinal(String hrId) {
        sqlQuery = properties.getProperty("ContractorRCDao.getHRAttachmentsFinal");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("hrId", hrId).list();
    }

    @Transactional(readOnly = true)
    public List<AttachmentDTO> getEQAttachmentsFinal(String eqId) {
        sqlQuery = properties.getProperty("ContractorRCDao.getEQAttachmentsFinal");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("eqId", eqId).list();
    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getCategoryClassFinal(String contractorId) {
        sqlQuery = properties.getProperty("ContractorRCDao.getCategoryClassFinal");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("contractorId", contractorId).list();
    }

    @Transactional(readOnly = true)
    public ContractorFinal getContractorFinal(String cdbNo) {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ContractorFinal> cQuery = builder.createQuery(ContractorFinal.class);
        Root<ContractorFinal> root = cQuery.from(ContractorFinal.class);
        cQuery.select(root).where(builder.equal(root.get("cdbNo"), cdbNo));
        ContractorFinal contractorFinal = getCurrentSession().createQuery(cQuery).getSingleResult();
        em.detach(contractorFinal);
        return contractorFinal;
    }

    @Transactional
    public void saveDeleteHrRequest(String hrId) {
        sqlQuery = properties.getProperty("ContractorRCDao.saveDeleteHrRequest");
        hibernateQuery(sqlQuery).setParameter("hrId", hrId).executeUpdate();
    }

    @Transactional
    public void saveDeleteEqRequest(String eqId) {
        sqlQuery = properties.getProperty("ContractorRCDao.saveDeleteEqRequest");
        hibernateQuery(sqlQuery).setParameter("eqId", eqId).executeUpdate();
    }

    @Transactional
    public void saveEditEqRequest(String eqId) {
        sqlQuery = properties.getProperty("ContractorRCDao.saveEditEqRequest");
        hibernateQuery(sqlQuery).setParameter("eqId", eqId).executeUpdate();
    }


    @Transactional(readOnly = true)
    public String auditMemo(String contractorFinalId) {
        sqlQuery = properties.getProperty("ContractorRCDao.auditMemo");
        Query hQuery = hibernateQuery(sqlQuery).setParameter("contractorFinalId", contractorFinalId);
        return hQuery.list().isEmpty()?null:hQuery.list().get(0).toString();
    }

    public List<ContractorAttachment> getIncAttachmentFinal(String contractorFinalId) {
        sqlQuery = properties.getProperty("ContractorRCDao.getIncAttachmentFinal");
        return hibernateQuery(sqlQuery, ContractorAttachment.class).setParameter("contractorFinalId", contractorFinalId).list();
    }

}
