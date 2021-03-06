/*Common Dao*/
CommonDao.gCountryList = SELECT a.`Id`AS value, a.`Name` AS text FROM `cmncountry` a ORDER BY a.`Code` DESC, a.`Name` ASC
CommonDao.gDzongkhagList = SELECT a.`Id` AS value,CONCAT(a.`NameEn`,COALESCE(a.NameDz,'')) AS text FROM `cmndzongkhag` a
CommonDao.gCmnListItem = SELECT a.`Id` value,a.`Name` text FROM `cmnlistitem` a WHERE cmnlistid =:cmnListId  order by a.Name ASC
CommonDao.getGewogList = SELECT a.Gewog_Id as id, a.`Gewog_Name` AS value, CONCAT(a.`Gewog_Name` , '(',a.`Gewog_Name_Bh`,')') text FROM `cmngewog` a INNER JOIN `cmndzongkhag` b ON a.`Dzongkhag_Serial_No` = b.`Code`  WHERE b.`Id` =:dzongkhagId
CommonDao.getVillageList = SELECT a.`Village_Name` AS value, CONCAT(a.`Village_Name` , '(',a.`Village_Name_Bh`,')') text FROM `cmnvillage` a INNER JOIN `cmngewog` b ON a.`Gewog_Serial_No` = b.`Gewog_Serial_No`  WHERE b.`Gewog_Id` =:gewogId

CommonDao.getAttachmentDetail = SELECT a.`DocumentName` fileName,a.`DocumentPath` filePath,a.`FileType` fileType FROM :tableName a WHERE Id =:id

CommonDao.isExpiredContractor = SELECT `RegistrationExpiryDate` < NOW() FROM`crpcontractorfinal` where cdbNo =:cdbNo
CommonDao.getTrackRecord = SELECT 	`Year1` years,`CDBNo` cdbNo,`firmName` firmName,`PAgency` pAgency,`Nworks` nWorks,`WClassification` wClassification,`BidAmount` bidAmount,`EvalAmount` evalAmount,`Dzongkhag` dzongkhag,`Status` status,`Startdt` startDate,`CompleDt` completeDate,`Rating` rating,`Scoring` scoring,`Recordid` recordId,`WorkId` workId,`EvaluationOntime` evaluationOnTime,`EvaluationQuality` evaluationQty,`Remarks` remarks FROM trackrecord WHERE CDBNo =:cdbNo ORDER BY Year1 desc

CommonDao.isExpiredConsultant = SELECT `RegistrationExpiryDate` < NOW() FROM`crpconsultantfinal` where cdbNo =:cdbNo

CommonDao.isExpiredSpecializedFirm = SELECT `RegistrationExpiryDate` < NOW() FROM`crpspecializedtradefinal` where SPNo =:cdbNo

CommonDao.isExpiredEngineer = SELECT `RegistrationExpiryDate` < NOW() FROM`crpengineerfinal` WHERE CDBNo =:cdbNo

CommonDao.isExpiredSurveyor =  SELECT `RegistrationExpiryDate` < NOW() FROM `crpsurveyfinal` WHERE ARNo =:cdbNo

CommonDao.isExpiredArchitect =  SELECT `RegistrationExpiryDate` < NOW() FROM `crparchitectfinal` WHERE ARNo =:cdbNo

CommonDao.isExpiredSpecializedTrade = SELECT `RegistrationExpiryDate` < NOW() FROM`crpspecializedtradefinal` where SPNo =:cdbNo

CommonDao.validateWorkEngagementCidNo = SELECT DISTINCT(t5.Id) id, GROUP_CONCAT(t4.CDBNo SEPARATOR ', ' ) cdbNo, \
CASE WHEN t5.migratedworkid IS NULL THEN CONCAT(t6.Code,'/',YEAR(t5.UploadedDate),'/',t5.WorkId) ELSE t5.migratedworkid END AS workId, \
t6.Name AS procuringAgency \
FROM etlcontractorhumanresource t1 \
LEFT JOIN etltenderbiddercontractor t2 ON t1.EtlTenderBidderContractorId=t2.Id \
LEFT JOIN etltenderbiddercontractordetail t3 ON t3.EtlTenderBidderContractorId=t2.Id \
LEFT JOIN crpcontractorfinal t4 ON t4.Id=t3.CrpContractorFinalId LEFT JOIN etltender t5 ON t5.Id=t2.EtlTenderId \
LEFT JOIN cmnprocuringagency t6 ON t6.Id=t5.CmnProcuringAgencyId \
WHERE t2.ActualStartDate IS NOT NULL AND t5.CmnWorkExecutionStatusId ='1ec69344-a256-11e4-b4d2-080027dcfac6' AND t1.CIDNo =:cidNo GROUP BY id

CommonDao.validateCorporateCidNo =SELECT c.PositionTitle positionTitle,c.Agency agency,c.CIDNo cidNo FROM crpgovermentengineer c WHERE c.CIDNo =:cidNo AND Releaved=0

CommonDao.isUsenameExist = SELECT a.username FROM sysuser a WHERE a.username =:username

CommonDao.consultantFetchEqDtlsFromCDB = SELECT c.NameOfFirm consultantFirmname,c.CDBNo consultantCDBNo FROM crpconsultantfinal c WHERE c.Id IN (SELECT e.CrpConsultantFinalId FROM crpconsultantequipmentfinal e WHERE e.RegistrationNo =:regNo)
CommonDao.contractorFetchEqDtlsFromCDB = SELECT c.NameOfFirm consultantFirmname,c.CDBNo consultantCDBNo FROM crpcontractorfinal c WHERE c.Id IN (SELECT e.CrpContractorFinalId FROM crpcontractorequipmentfinal e WHERE e.RegistrationNo =:regNo)
CommonDao.specializedFirmFetchEqDtlsFromCDB = SELECT c.NameOfFirm consultantFirmname,c.SPNo consultantCDBNo FROM crpspecializedtradefinal c WHERE c.Id IN (SELECT e.CrpSpecializedtradeFinalId FROM crpspecializedtradeequipmentfinal e WHERE e.RegistrationNo =:regNo)