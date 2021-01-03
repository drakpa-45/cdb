package com.ngn.spring.project.cdb.specializedFirm.renewal;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.AttachmentDTO;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.common.dto.ServiceFeeDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorFinal;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmHrDTO;
import com.ngn.spring.project.cdb.specializedFirm.model.SpFirmAttachment;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirmFinal;
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
public class SpecializedFirmRDao extends BaseDao {


    public void saveUpdate(Object object) {
        saveOrUpdate(object);
    }

    @Transactional(readOnly = true)
    public DropdownDTO getSpFirmStatus(String cdbNo) {
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.getSpFirmStatus");
        return (DropdownDTO)hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("CDBNo",cdbNo).list().get(0);
    }

    @Transactional(readOnly = true)
    public List<ServiceFeeDTO> getServicesFee(Integer refNo) {
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.getServicesFee");
        hQuery = hibernateQuery(sqlQuery, ServiceFeeDTO.class).setParameter("refNo",refNo);
        return hQuery.list();
    }

    /*public Contractor getContractor4rmFinal(String cdbNo) {
        sqlQuery = properties.getProperty("ContractorRCDao.getContractor4rmFinal");
        hQuery = hibernateQuery(sqlQuery, Contractor.class).setParameter("cdbNo",cdbNo);
        return (Contractor)hQuery.list().get(0);
    }*/

    /**
     * To get the HR detail for specializedFirm
     * @param specializedFirmId --  specializedFirm id
     * @param ownerOrPartner -- 'O' only owner, 'H' only HR, 'B' for both owner and HR
     * @return List
     */
    @Transactional(readOnly = true)
    public List<SpFirmHrDTO> getSpecializedFirmHRsFinal(String specializedFirmId, Character ownerOrPartner) {
        if(ownerOrPartner == 'B'){
            ownerOrPartner = null;
        }else if(ownerOrPartner == 'O'){
            ownerOrPartner = '1';
        }else if(ownerOrPartner == 'H'){
            ownerOrPartner = '0';
        }
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.getSpecializedFirmHRsFinal");
        return hibernateQuery(sqlQuery, SpFirmHrDTO.class).setParameter("specializedFirmId", specializedFirmId)
                .setParameter("ownerOrPartner",ownerOrPartner).list();
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipmentFinal(String specializedFirmId) {
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.getEquipmentFinal");
        return hibernateQuery(sqlQuery, EquipmentDTO.class).setParameter("specializedFirmId", specializedFirmId).list();
    }

    @Transactional(readOnly = true)
    public List<AttachmentDTO> getHRAttachmentsFinal(String hrId) {
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.getHRAttachmentsFinal");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("hrId", hrId).list();
    }

    @Transactional(readOnly = true)
    public List<AttachmentDTO> getEQAttachmentsFinal(String eqId) {
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.getEQAttachmentsFinal");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("eqId", eqId).list();
    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getCategoryClassFinal(String specializedFirmId) {
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.getCategoryClassFinal");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("specializedFirmId", specializedFirmId).list();
    }

    @Transactional(readOnly = true)
    public SpecializedFirmFinal getSpecializedFirmFinal(String cdbNo) {
        SpecializedFirmFinal specializedFirmFinal = null;
       try {
            CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<SpecializedFirmFinal> cQuery = builder.createQuery(SpecializedFirmFinal.class);
            Root<SpecializedFirmFinal> root = cQuery.from(SpecializedFirmFinal.class);
            cQuery.select(root).where(builder.equal(root.get("cdbNo"), cdbNo));
             specializedFirmFinal = getCurrentSession().createQuery(cQuery).getSingleResult();
            em.detach(specializedFirmFinal);
        }catch (Exception e){
           e.printStackTrace();
       }
        return specializedFirmFinal;
    }
    @Transactional
    public void saveDeleteHrRequest(String hrId) {
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.saveDeleteHrRequest");
        hibernateQuery(sqlQuery).setParameter("hrId", hrId).executeUpdate();
    }

    @Transactional
    public void saveDeleteEqRequest(String eqId) {
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.saveDeleteEqRequest");
        hibernateQuery(sqlQuery).setParameter("eqId", eqId).executeUpdate();
    }

    @Transactional(readOnly = true)
    public String auditMemo(String specializedFirmFinalId) {
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.auditMemo");
        Query hQuery = hibernateQuery(sqlQuery).setParameter("specializedFirmFinalId", specializedFirmFinalId);
        return hQuery.list().isEmpty()?null:hQuery.list().get(0).toString();
    }

    public List<SpFirmAttachment> getIncAttachmentFinal(String specializedFirmId) {
        sqlQuery = properties.getProperty("SpecializedFirmRCDao.getIncAttachmentFinal");
        return hibernateQuery(sqlQuery, SpFirmAttachment.class).setParameter("specializedFirmId", specializedFirmId).list();
    }
}
