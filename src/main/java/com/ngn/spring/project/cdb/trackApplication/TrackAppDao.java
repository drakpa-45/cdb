package com.ngn.spring.project.cdb.trackApplication;

import com.ngn.spring.project.base.BaseDao;
import com.ngn.spring.project.commonDto.TasklistDto;
import groovy.transform.TailRecursive;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 18-Dec-20.
 */
@Repository
public class TrackAppDao extends BaseDao{

    @Transactional
    public List<TasklistDto> populateTrackApp(String applicationNo) {
        List<TasklistDto> dto=new ArrayList<TasklistDto>();
        try {
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate appDate,b.Name AS appStatus, \n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services' END AS serviceName FROM crpsurvey a INNER JOIN cmnlistitem b  \n" +
                    "ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crpsurveyappliedservice s \n" +
                    "ON s.CrpSurveyId = a.CrpSurveyId WHERE a.ReferenceNo =?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate appDate,b.Name AS appStatus,\n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services' END AS serviceName\n" +
                    "FROM crpspecializedtrade a INNER JOIN cmnlistitem b ON b.Id = a.CmnApplicationRegistrationStatusId  \n" +
                    "INNER JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId = a.CrpSpecializedTradeId WHERE  \n" +
                    "a.ReferenceNo =?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate appDate,b.Name AS appStatus, \n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services' END AS serviceName\n" +
                    "FROM crparchitect a INNER JOIN cmnlistitem b  ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crparchitectappliedservice s \n" +
                    "ON s.CrpArchitectId = a.CrpArchitectId WHERE a.ReferenceNo =?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo,a.ApplicationDate appDate,b.Name AS appStatus, \n" +
                    "CASE\n" +
                    "WHEN s.CmnServiceTypeId = '55a922e1-cbbf-11e4-83fb-080027dcfac6' THEN 'New Registration'\n" +
                    "WHEN s.CmnServiceTypeId ='45bc628b-cbbe-11e4-83fb-080027dcfac6' THEN 'Renewal'\n" +
                    "WHEN s.CmnServiceTypeId = 'acf4b324-cbbe-11e4-83fb-080027dcfac6' THEN 'Cancellation'\n" +
                    "ELSE 'No Services' END AS serviceName\n" +
                    "FROM crpengineer a INNER JOIN cmnlistitem b  ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crpengineerappliedservice s \n" +
                    "ON s.CrpEngineerId = a.CrpEngineerId WHERE a.ReferenceNo =?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,\n" +
                    "b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpconsultant a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpConsultantId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpconsultantappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpConsultantId\n" +
                    ") e ON e.CrpConsultantId = a.CrpConsultantId \n" +
                    "WHERE a.ReferenceNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpcontractor a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpContractorId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name ORDER BY d.referenceno ASC)serviceName  \n" +
                    "FROM crpcontractorappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpContractorId) e ON e.CrpContractorId = a.CrpContractorId \n" +
                    "WHERE a.ReferenceNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
            sqlQuery = "SELECT a.ReferenceNo applicationNo, e.serviceName,b.Name AS appStatus,a.ApplicationDate appDate \n" +
                    "FROM crpspecializedtrade a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId\n" +
                    "INNER JOIN (\n" +
                    "SELECT c.CrpSpecializedTradeId,MIN(d.referenceNo)minRef, GROUP_CONCAT(d.Name SEPARATOR ', ')serviceName  \n" +
                    "FROM crpspecializedtradeappliedservice c \n" +
                    "INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId GROUP BY c.CrpSpecializedTradeId\n" +
                    ") e ON e.CrpSpecializedTradeId = a.CrpSpecializedTradeId \n" +
                    "WHERE a.ReferenceNo=?";
            dto = (List<TasklistDto>) hibernateQuery(sqlQuery, TasklistDto.class).setParameter(1, applicationNo).list();
            if(dto.size()>0){
                return dto;
            }
        } catch (Exception e) {
            System.out.print("Exception in TrackAppDao # populateTrackApp: " + e);
            e.printStackTrace();
        }
        return dto;
    }
}
