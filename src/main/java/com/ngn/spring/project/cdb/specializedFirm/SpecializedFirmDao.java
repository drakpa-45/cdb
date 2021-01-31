package com.ngn.spring.project.cdb.specializedFirm;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantTrainingDTO;

import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmDTOFetch;
import com.ngn.spring.project.cdb.specializedFirm.model.SpFirmtHRAttachment;
import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirm;

import com.ngn.spring.project.cdb.contractor.registration.dto.FeeStructureDTO;

import com.ngn.spring.project.cdb.trade.dto.TradeFeesDto;
import com.ngn.spring.project.lib.DropdownDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
public class SpecializedFirmDao extends BaseDao {

    @Transactional(readOnly = true)
    public List gFeeStructure(String category) {
        sqlQuery = properties.getProperty("SpecializedDao.getFirmFee");
        return hibernateQuery(sqlQuery, FeeStructureDTO.class).list();
    }

    @Transactional(readOnly = true)
    public List gClassification() {
        sqlQuery = properties.getProperty("ConsultantDao.gConsultantClassification");
        return hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }
    public List categoryFirm() {
        List<TradeFeesDto> dtoList=new ArrayList<>();
        sqlQuery = properties.getProperty("SpecializedDao.categoryFirm");
        dtoList=hibernateQuery(sqlQuery, TradeFeesDto.class).list();
        return dtoList;
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
        sqlQuery = properties.getProperty("SpecializedDao.isEmailUnique");
        return hibernateQuery(sqlQuery).setParameter("email",email).list().isEmpty();
    }

    @Transactional(readOnly = true)
    public List getTrainingDtl(String cidNo) {
        sqlQuery = properties.getProperty("ConsultantDao.getTrainingDtl");
        return hibernateQuery(sqlQuery, ConsultantTrainingDTO.class).setParameter("cidNo",cidNo).list();
    }

    public Boolean isFirmNameUnique(String firmName) {
        sqlQuery = properties.getProperty("SpecializedDao.isFirmNameUnique");
        return hibernateQuery(sqlQuery).setParameter("firmName",firmName).list().isEmpty();
    }

    @Transactional(readOnly = true)
    public SpecializedFirm getSpecializedFirm(String cdbNo){
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<SpecializedFirm> cQuery = builder.createQuery(SpecializedFirm.class);
        Root<SpecializedFirm> root = cQuery.from(SpecializedFirm.class);
        cQuery.select(root).where(builder.equal(root.get("ReferenceNo"), cdbNo));
        SpecializedFirm specializedFirm = getCurrentSession().createQuery(cQuery).getSingleResult();
        em.detach(specializedFirm);
        return specializedFirm;
    }

    @Transactional
    public SpFirmDTOFetch getSpecializedFirmOngoingApp(String cdbNo) {
        sqlQuery = properties.getProperty("SpecializedDao.getSpecializedFirmOngoingApp");
        List list = hibernateQuery(sqlQuery, SpFirmDTOFetch.class).setParameter("cdbNo", cdbNo).list();
        return (SpFirmDTOFetch)(list.isEmpty()?null:list.get(0));
    }

    @Transactional(readOnly = true)
    public SpFirmtHRAttachment getHRAttachmentFinal(String hraId) {
        sqlQuery = properties.getProperty("SpecializedDao.getHRAttachmentFinal");
        return (SpFirmtHRAttachment)hibernateQuery(sqlQuery,SpFirmtHRAttachment.class).setParameter("hraId",hraId).list().get(0);
    }
}
