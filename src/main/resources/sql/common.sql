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

CommonDao.validateWorkEngagementCidNo = SELECT DISTINCT(t5.Id) id, GROUP_CONCAT(t4.CDBNo SEPARATOR ', ' ) cdbNo,
CASE WHEN T5.migratedworkid IS NULL THEN CONCAT(T6.Code,'/',YEAR(T5.UploadedDate),'/',T5.WorkId) ELSE T5.migratedworkid END AS workId,
T6.Name AS procuringAgency
FROM etlcontractorhumanresource t1
LEFT JOIN etltenderbiddercontractor t2 ON t1.EtlTenderBidderContractorId=t2.Id
LEFT JOIN etltenderbiddercontractordetail t3 ON t3.EtlTenderBidderContractorId=t2.Id
LEFT JOIN crpcontractorfinal t4 ON t4.Id=t3.CrpContractorFinalId
LEFT JOIN etltender t5 ON t5.Id=t2.EtlTenderId
LEFT JOIN cmnprocuringagency t6 ON t6.Id=t5.CmnProcuringAgencyId
WHERE t2.ActualStartDate IS NOT NULL AND t5.CmnWorkExecutionStatusId ='1ec69344-a256-11e4-b4d2-080027dcfac6' AND t1.CIDNo =:cidNo
GROUP BY id
