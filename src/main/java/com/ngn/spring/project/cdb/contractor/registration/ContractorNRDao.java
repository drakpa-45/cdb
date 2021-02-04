package com.ngn.spring.project.cdb.contractor.registration;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.contractor.registration.dto.CategoryDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorDTOFetch;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorTrainingDTO;
import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;
import com.ngn.spring.project.cdb.contractor.registration.model.Contractor;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorEQAttachment;
import com.ngn.spring.project.cdb.contractor.registration.model.ContractorHRAttachment;
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
public class ContractorNRDao extends BaseDao {


    @Transactional(readOnly = true)
    public List gFeeStructure(String category) {
        sqlQuery = properties.getProperty("ContractorDao.gFeeStructure");
        return hibernateQuery(sqlQuery, FeeStructureDTO.class).setParameter("category",category).list();
    }

    @Transactional(readOnly = true)
    public List gContractCategory() {
        sqlQuery = properties.getProperty("ContractorDao.gContractCategory");
        return hibernateQuery(sqlQuery, CategoryDTO.class).list();
    }

    @Transactional(readOnly = true)
    public List gClassification() {
        sqlQuery = properties.getProperty("ContractorDao.gClassification");
        return hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }

    public void saveUpdate(Object object) {
        saveOrUpdate(object);
    }

    @Transactional(readOnly = true)
    public List gEquipment() {
        sqlQuery = properties.getProperty("ContractorDao.gEquipment");
        return hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }

    @Transactional(readOnly = true)
    public Boolean isEmailUnique(String email) {
        sqlQuery = properties.getProperty("ContractorDao.isEmailUnique");
        return hibernateQuery(sqlQuery).setParameter("email",email).list().isEmpty();
    }

    @Transactional(readOnly = true)
    public List getTrainingDtl(String cidNo) {
        sqlQuery = properties.getProperty("ContractorDao.getTrainingDtl");
        return hibernateQuery(sqlQuery, ContractorTrainingDTO.class).setParameter("cidNo",cidNo).list();
    }

    @Transactional(readOnly = true)
    public Contractor getContractor(String cdbNo){
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Contractor> cQuery = builder.createQuery(Contractor.class);
        Root<Contractor> root = cQuery.from(Contractor.class);
        cQuery.select(root).where(builder.equal(root.get("ReferenceNo"), cdbNo));
        Contractor contractor = getCurrentSession().createQuery(cQuery).getSingleResult();
        em.detach(contractor);
        return contractor;
    }


    public ContractorDTOFetch getContractorOngoingApp(String cdbNo) {
        sqlQuery = properties.getProperty("ContractorDao.getContractorOngoingApp");
        List list = hibernateQuery(sqlQuery, ContractorDTOFetch.class).setParameter("cdbNo", cdbNo).list();
        return (ContractorDTOFetch)(list.isEmpty()?null:list.get(0));
    }

    public Boolean isFirmNameUnique(String firmName) {
        sqlQuery = properties.getProperty("ContractorDao.isFirmNameUnique");
        return hibernateQuery(sqlQuery).setParameter("firmName",firmName).list().isEmpty();

    }

    @Transactional(readOnly = true)
    public ContractorHRAttachment getHRAttachmentFinal(String hraId) {
        sqlQuery = properties.getProperty("ContractorDao.getHRAttachmentFinal");
        return (ContractorHRAttachment)hibernateQuery(sqlQuery,ContractorHRAttachment.class).setParameter("hraId",hraId).list().get(0);
    }

    @Transactional(readOnly = true)
    public ContractorEQAttachment getEQAttachmentFinal(String eqaId) {
        sqlQuery = properties.getProperty("ContractorDao.getEQAttachmentFinal");
        return (ContractorEQAttachment)hibernateQuery(sqlQuery,ContractorEQAttachment.class).setParameter("eqaId",eqaId).list().get(0);
    }
}
