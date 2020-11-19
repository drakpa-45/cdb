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
