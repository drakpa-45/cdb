package com.ngn.spring.project.cdb.consultant.renewal;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.admin.dto.AttachmentDTO;
import com.ngn.spring.project.cdb.admin.dto.CategoryClassDTO;
import com.ngn.spring.project.cdb.admin.dto.EquipmentDTO;
import com.ngn.spring.project.cdb.common.dto.EmployeeDetailsDTO;
import com.ngn.spring.project.cdb.common.dto.PersonalInfoDTO;
import com.ngn.spring.project.cdb.common.dto.ServiceFeeDTO;
import com.ngn.spring.project.cdb.consultant.model.Consultant;
import com.ngn.spring.project.cdb.consultant.model.ConsultantAttachment;
import com.ngn.spring.project.cdb.consultant.model.ConsultantFinal;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantHrDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorHrDTO;
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
public class ConsultantRCDao extends BaseDao {


    public void saveUpdate(Object object) {
        saveOrUpdate(object);
    }

    @Transactional(readOnly = true)
    public DropdownDTO getConsultantStatus(String cdbNo) {
        sqlQuery = properties.getProperty("ConsultantRCDao.getConsultantStatus");
        return (DropdownDTO)hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("CDBNo",cdbNo).list().get(0);
    }

    @Transactional(readOnly = true)
    public List<ServiceFeeDTO> getServicesFee(Integer refNo) {
        sqlQuery = properties.getProperty("ConsultantRCDao.getServicesFee");
        hQuery = hibernateQuery(sqlQuery, ServiceFeeDTO.class).setParameter("refNo",refNo);
        return hQuery.list();
    }

    /*public Contractor getContractor4rmFinal(String cdbNo) {
        sqlQuery = properties.getProperty("ContractorRCDao.getContractor4rmFinal");
        hQuery = hibernateQuery(sqlQuery, Contractor.class).setParameter("cdbNo",cdbNo);
        return (Contractor)hQuery.list().get(0);
    }*/

    /**
     * To get the HR detail for consultant
     * @param consultantId --  consultant id
     * @param ownerOrPartner -- 'O' only owner, 'H' only HR, 'B' for both owner and HR
     * @return List
     */
    @Transactional(readOnly = true)
    public List<ConsultantHrDTO> getConsultantHRsFinal(String consultantId, Character ownerOrPartner) {
        if(ownerOrPartner == 'B'){
            ownerOrPartner = null;
        }else if(ownerOrPartner == 'O'){
            ownerOrPartner = '1';
        }else if(ownerOrPartner == 'H'){
            ownerOrPartner = '0';
        }
        sqlQuery = properties.getProperty("ConsultantRCDao.getConsultantHRsFinal");
        return hibernateQuery(sqlQuery, ConsultantHrDTO.class).setParameter("consultantId", consultantId)
                .setParameter("ownerOrPartner",ownerOrPartner).list();
    }

    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipmentFinal(String consultantId) {
        sqlQuery = properties.getProperty("ConsultantRCDao.getEquipmentFinal");
        return hibernateQuery(sqlQuery, EquipmentDTO.class).setParameter("consultantId", consultantId).list();
    }

    @Transactional(readOnly = true)
    public List<AttachmentDTO> getHRAttachmentsFinal(String hrId) {
        sqlQuery = properties.getProperty("ConsultantRCDao.getHRAttachmentsFinal");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("hrId", hrId).list();
    }

    @Transactional(readOnly = true)
    public List<AttachmentDTO> getEQAttachmentsFinal(String eqId) {
        sqlQuery = properties.getProperty("ConsultantRCDao.getEQAttachmentsFinal");
        return hibernateQuery(sqlQuery, AttachmentDTO.class).setParameter("eqId", eqId).list();
    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getCategoryClassFinal(String consultantId) {
        sqlQuery = properties.getProperty("ConsultantRCDao.getCategoryClassFinal");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("consultantId", consultantId).list();
    }

    public ConsultantFinal getConsultantFinal(String cdbNo) {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ConsultantFinal> cQuery = builder.createQuery(ConsultantFinal.class);
        Root<ConsultantFinal> root = cQuery.from(ConsultantFinal.class);
        cQuery.select(root).where(builder.equal(root.get("cdbNo"), cdbNo));
        ConsultantFinal consultantFinal = getCurrentSession().createQuery(cQuery).getSingleResult();
        em.detach(consultantFinal);
        return consultantFinal;
    }

    @Transactional
    public void saveDeleteHrRequest(String hrId) {
        sqlQuery = properties.getProperty("ConsultantRCDao.saveDeleteHrRequest");
        hibernateQuery(sqlQuery).setParameter("hrId", hrId).executeUpdate();
    }

    @Transactional
    public void saveDeleteEqRequest(String eqId) {
        sqlQuery = properties.getProperty("ConsultantRCDao.saveDeleteEqRequest");
        hibernateQuery(sqlQuery).setParameter("eqId", eqId).executeUpdate();
    }

    @Transactional(readOnly = true)
    public String auditMemo(String consultantFinalId) {
        sqlQuery = properties.getProperty("ContractorRCDao.auditMemo");
        Query hQuery = hibernateQuery(sqlQuery).setParameter("contractorFinalId", consultantFinalId);
        return hQuery.list().isEmpty()?null:hQuery.list().get(0).toString();
    }

    public List getProposedCategories(String appNo) {
        sqlQuery = properties.getProperty("ConsultantRCActionDao.getProposedCategories");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class)
                .setParameter("appNo", appNo).list();
    }

    public List<ConsultantAttachment> getIncAttachmentFinal(String consultantIdFinal) {
        sqlQuery = properties.getProperty("ConsultantRCDao.getIncAttachmentFinal");
        return hibernateQuery(sqlQuery, ConsultantAttachment.class).setParameter("consultantIdFinal", consultantIdFinal).list();
    }
}
