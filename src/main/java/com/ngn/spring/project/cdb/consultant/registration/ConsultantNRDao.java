package com.ngn.spring.project.cdb.consultant.registration;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.consultant.model.ConsultantEQAttachment;
import com.ngn.spring.project.cdb.consultant.model.ConsultantHRAttachment;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantCategoryDTO;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTOFetch;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantTrainingDTO;
import com.ngn.spring.project.cdb.consultant.model.Consultant;
import com.ngn.spring.project.cdb.consultant.model.ConsultantFinal;
import com.ngn.spring.project.cdb.contractor.registration.dto.CategoryDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.lib.DropdownDTO;
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
public class ConsultantNRDao extends BaseDao {

    @Transactional(readOnly = true)
    public List gFeeStructure(String category) {
        sqlQuery = properties.getProperty("ConsultantDao.gFeeStructure");
        return hibernateQuery(sqlQuery, FeeStructureDTO.class).setParameter("category",category).list();
    }

    @Transactional(readOnly = true)
    public List gConsultantCategory() {
        sqlQuery = properties.getProperty("ConsultantDao.gConsultantCategory");
        return hibernateQuery(sqlQuery, ConsultantCategoryDTO.class).list();
    }

    @Transactional(readOnly = true)
    public List gClassification() {
        sqlQuery = properties.getProperty("ConsultantDao.gConsultantClassification");
        return hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }

    public void saveUpdate(Object object) {
        saveOrUpdate(object);
    }

    @Transactional(readOnly = true)
    public List gEquipment() {
        sqlQuery = properties.getProperty("ConsultantDao.gEquipment");
        return hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }

    @Transactional(readOnly = true)
    public Boolean isEmailUnique(String email) {
        sqlQuery = properties.getProperty("ConsultantDao.isEmailUnique");
        return hibernateQuery(sqlQuery).setParameter("email",email).list().isEmpty();
    }

    @Transactional(readOnly = true)
    public List getTrainingDtl(String cidNo) {
        sqlQuery = properties.getProperty("ConsultantDao.getTrainingDtl");
        return hibernateQuery(sqlQuery, ConsultantTrainingDTO.class).setParameter("cidNo",cidNo).list();
    }

    @Transactional(readOnly = true)
    public Consultant getConsultant(String cdbNo){
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Consultant> cQuery = builder.createQuery(Consultant.class);
        Root<Consultant> root = cQuery.from(Consultant.class);
        cQuery.select(root).where(builder.equal(root.get("ReferenceNo"), cdbNo));
        Consultant consultant = getCurrentSession().createQuery(cQuery).getSingleResult();
        em.detach(consultant);
        return consultant;
    }

    @Transactional(readOnly = true)
    public ConsultantFinal getConsultantFinal(String cdbNo){
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ConsultantFinal> cQuery = builder.createQuery(ConsultantFinal.class);
        Root<ConsultantFinal> root = cQuery.from(ConsultantFinal.class);
        cQuery.select(root).where(builder.equal(root.get("cdbNo"), cdbNo));
        ConsultantFinal consultantFinal = getCurrentSession().createQuery(cQuery).getSingleResult();
        em.detach(consultantFinal);
        return consultantFinal;
    }

    public ConsultantDTOFetch getConsultantOngoingApp(String cdbNo) {
        sqlQuery = properties.getProperty("ConsultantDao.getConsultantOngoingApp");
        List list = hibernateQuery(sqlQuery, ConsultantDTOFetch.class).setParameter("cdbNo", cdbNo).list();
        return (ConsultantDTOFetch)(list.isEmpty()?null:list.get(0));
    }

    public Boolean isFirmNameUnique(String firmName) {
        sqlQuery = properties.getProperty("ConsultantDao.isFirmNameUnique");
        return hibernateQuery(sqlQuery).setParameter("firmName",firmName).list().isEmpty();
    }

    public List gFeeStructureRO(String category) {
        sqlQuery = properties.getProperty("ConsultantDao.gConsultantCategoryRO");
        return hibernateQuery(sqlQuery, FeeStructureDTO.class).setParameter("category",category).list();
    }

    @Transactional(readOnly = true)
    public ConsultantHRAttachment getHRAttachmentFinal(String hraId) {
        sqlQuery = properties.getProperty("ConsultantDao.getHRAttachmentFinal");
        return (ConsultantHRAttachment)hibernateQuery(sqlQuery,ConsultantHRAttachment.class).setParameter("hraId",hraId).list().get(0);
    }

    @Transactional(readOnly = true)
    public ConsultantEQAttachment getEQAttachmentFinal(String eqaId) {
        sqlQuery = properties.getProperty("ConsultantDao.getEQAttachmentFinal");
        return (ConsultantEQAttachment)hibernateQuery(sqlQuery,ConsultantEQAttachment.class).setParameter("eqaId",eqaId).list().get(0);
    }
}
