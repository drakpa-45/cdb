
/*Consultant Dao*/
ConsultantDao.gFeeStructure = SELECT cc.Id AS id,cc.NewRegistrationFee AS registrationFee,cc.FirstRenewalFee AS renewalFee FROM crpservicefeestructure cc WHERE cc.Name=:category  ORDER BY referenceNo ASC
ConsultantDao.gConsultantCategory = SELECT wc.Id AS id, wc.`Name` AS name,wc.`Code` AS code FROM `cmnconsultantservicecategory` wc ORDER BY Code ASC
ConsultantDao.gConsultantClassification = SELECT a.CmnConsultantServiceCategoryId AS value, a.Code AS text,a.Name AS obj1, a.Id id FROM `cmnconsultantservice` a ORDER BY Code ASC;
ConsultantDao.gConsultantCategoryRO = SELECT cc.`Id` AS id,cc.`Name` AS name,cc.`Code` AS code,cc.`RegistrationFee` AS registrationFee,cc.`RenewalFee` AS renewalFee FROM `cmnconsultantclassification` cc where (:category is null or cc.Id =:category) ORDER BY referenceNo ASC
/*SELECT a.CmnConsultantServiceCategoryId AS value, CONCAT(a.Code,':',a.Name) text FROM `cmnconsultantservice` a;*/

ConsultantDao.gEquipment = SELECT a.`Id` AS value,a.`Name` AS text,a.IsRegistered as obj1  FROM `cmnequipment` a
ConsultantDao.isEmailUnique = SELECT a.Email FROM `sysuser` a WHERE a.Email =:email
ConsultantDao.getTrainingDtl = SELECT a.`CmnTrainingTypeId` tTypeId,a.`CmnTrainingModuleId` tModuleId,tt.`Name` tType,tm.`Name` tModule,a.`TrainingFromDate` fromDate,a.`TrainingToDate` toDate,b.`CIDNo` cidNo,b.`Participant` participant FROM `crpcontractortraining` a INNER JOIN `crpcontractortrainingdetail` b ON a.`Id` = b.`CrpContractorTrainingId` INNER JOIN `cmnlistitem` tt ON tt.Id = a.`CmnTrainingTypeId` INNER JOIN `cmnlistitem` tm ON tm.Id = a.`CmnTrainingModuleId` WHERE b.`CIDNo` =:cidNo
ConsultantDao.isFirmNameUnique= SELECT a.NameOfFirm FROM `crpconsultant` a WHERE a.NameOfFirm =:firmName
ConsultantDao.getHRAttachmentFinal = SELECT Id id,CrpConsultantHumanResourceFinalId consultantHrId,DocumentName documentName,DocumentPath documentPath,FileType fileType,CreatedBy createdBy,CreatedOn createdOn  FROM crpconsultanthumanresourceattachmentfinal WHERE Id = :hraId

/** ConsultantActionDao */

ConsultantActionDao.gTaskList=CALL ProCrpConsultantTaskList (:userId,:status,:service);

ConsultantActionDao.gGroupTaskList = SELECT a.`ReferenceNo` applicationNo, CONCAT(d.Name,' (Consultant)') serviceName, a.`NameOfFirm` firmName,a.`MobileNo` contactNo, b.`Name` AS appStatus,a.`ApplicationDate` applicationDate FROM `crpconsultant` a INNER JOIN `cmnlistitem` b ON b.`Id`  = a.`CmnApplicationRegistrationStatusId` INNER JOIN `crpconsultantappliedservice` c ON c.`CrpConsultantId` = a.`CrpConsultantId` INNER JOIN `crpservice` d ON d.`Id` = c.`CmnServiceTypeId` WHERE a.`SysLockedByUserId` IS NULL and a.`CmnApplicationRegistrationStatusId` = :status ORDER BY a.`ReferenceNo` DESC

ConsultantActionDao.gMyTaskList = SELECT a.`ReferenceNo` applicationNo, CONCAT(d.Name,' (Consultant)') serviceName,a.`NameOfFirm` firmName,a.`MobileNo` contactNo, b.`Name` AS appStatus,a.`ApplicationDate` applicationDate FROM `crpconsultant` a INNER JOIN `cmnlistitem` b ON b.`Id`  = a.`CmnApplicationRegistrationStatusId` INNER JOIN `crpconsultantappliedservice` c ON c.`CrpConsultantId` = a.`CrpConsultantId` INNER JOIN `crpservice` d ON d.`Id` = c.`CmnServiceTypeId` WHERE a.`SysLockedByUserId`=:userId ORDER BY a.`ReferenceNo` DESC

ConsultantActionDao.send2MyOrGroupTask=UPDATE crpconsultant a SET a.`SysLockedByUserId` =:lockUserId  WHERE a.ReferenceNo =:appNo

ConsultantDao.getConsultantOngoingApp = SELECT aa.`CrpConsultantId` consultantId,aa.`ReferenceNo` AS referenceNo, aa.ApplicationDate AS applicationDate, aa.CDBNo AS cdbNo,aa.Id AS appStatusId,bb.Name AS appStatusName FROM `crpconsultant` aa INNER JOIN cmnlistitem bb ON aa.`CmnApplicationRegistrationStatusId`=bb.Id WHERE  bb.Id IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND aa.CDBNo =:cdbNo

ConsultantActionDao.getCDBNoFromAppNo = SELECT b.cdbno FROM  crpconsultant a INNER JOIN crpconsultantfinal b ON a.email = b.email WHERE a.referenceNo =:appNo

ConsultantActionDao.getConsultantHRs = SELECT hr.Id AS id,hr.CrpConsultantId consultantID,hr.CIDNo cidNo,hr.Name AS name, hr.Sex sex,hr.ShowInCertificate AS siCertificate, hr.Verified as verified,hr.Approved \
,hr.IsPartnerOrOwner isPartnerOrOwner,hr.DeleteRequest AS deleteRequest,DATE_FORMAT(hr.JoiningDate, '%d-%m-%Y') joinDate,sa.Name salutationName,co.Name countryName,qu.Name qualificationName,st.Name serviceTypeName,td.Name tradeName,de.Name designationName \
FROM crpconsultanthumanresource hr INNER JOIN cmnlistitem sa ON sa.Id = hr.CmnSalutationId INNER JOIN cmncountry co ON co.Id = hr.CmnCountryId LEFT JOIN cmnlistitem qu ON qu.Id = hr.CmnQualificationId \
LEFT JOIN cmnlistitem st ON st.Id = hr.CmnServiceTypeId LEFT JOIN cmnlistitem td ON td.Id = hr.CmnTradeId INNER JOIN cmnlistitem de ON de.Id = hr.CmnDesignationId WHERE hr.CrpConsultantId =:consultantId

ConsultantActionDao.getEquipment = SELECT ce.Id id, ce.CrpConsultantId consultantId, eq.Name equipmentName,ce.RegistrationNo registrationNo,ce.CmnEquipmentId equipmentId ,ce.SerialNo serialNo,ce.Quantity quantity,ce.ModelNo modelNo \
FROM crpconsultantequipment ce INNER JOIN cmnequipment  eq ON ce.CmnEquipmentId = eq.Id WHERE ce.CrpConsultantId = :consultantId \
INNER JOIN crpconsultantworkclassification cl ON cl.Id = ce.CmnEquipmentId WHERE ce.CrpConsultantId =:consultantId

ConsultantActionDao.getAppHistoryDtl = SELECT c.*,CASE v.FullName WHEN NULL THEN '(Citizen)' ELSE v.FullName END userName FROM(SELECT 'Submitted' appStatus,c.CrpConsultantId consultantId,c.CreatedBy userId,c.CreatedOn actionDate,'' remarks FROM crpconsultant c UNION ALL SELECT 'Verified',c.CrpConsultantId,c.SysVerifierUserId,c.RegistrationVerifiedDate,c.RemarksByVerifier FROM crpconsultant c UNION ALL SELECT 'Approved for Payment',c.CrpConsultantId,c.SysApproverUserId,c.RegistrationApprovedDate,c.RemarksByApprover FROM crpconsultant c UNION ALL SELECT 'Rejected',c.CrpConsultantId,c.SysRejecterUserId,c.RejectedDate,c.RemarksByRejector FROM crpconsultant c) c LEFT JOIN sysuser v ON c.userId = v.Id WHERE c.CrpConsultantId = :consultantId AND c.actionDate IS NOT NULL

ConsultantActionDao.getCategoryClass=SELECT cs.Id id,cs.Code code,cs.Name name,rp.CmnServiceCategoryId categoryId,rp.Amount aAmount,rp.AppliedService value FROM  crpconsultantregistrationpayment rp LEFT JOIN cmnconsultantservicecategory cs ON cs.Id =  rp.CmnServiceCategoryId WHERE CrpConsultantFinalId = :consultantId ORDER BY cs.Code ASC

ConsultantActionDao.getConsuntantHRs=SELECT hr.Id AS id,hr.CrpConsultantId AS consultantID,hr.CIDNo AS cidNo,hr.Name AS name, hr.Sex AS sex,hr.ShowInCertificate AS siCertificate,hr.IsPartnerOrOwner AS isPartnerOrOwner,hr.JoiningDate AS joiningDate,sa.Name AS salutationName,co.Name AS countryName,qu.Name AS qualificationName,st.Name AS serviceTypeName,td.Name AS tradeName,de.Name AS designationName FROM crpconsultanthumanresource hr INNER JOIN cmnlistitem sa ON sa.Id = hr.CmnSalutationId INNER JOIN cmncountry co ON co.Id = hr.CmnCountryId LEFT JOIN cmnlistitem qu ON qu.Id = hr.CmnQualificationId \
LEFT JOIN cmnlistitem st ON st.Id = hr.CmnServiceTypeId LEFT JOIN cmnlistitem td ON td.Id = hr.CmnTradeId INNER JOIN cmnlistitem de ON de.Id = hr.CmnDesignationId WHERE hr.CrpConsultantId = :consultantId

ConsultantActionDao.getEquipment=SELECT ce.Id id, ce.CrpConsultantId consultantId, eq.Name equipmentName,ce.RegistrationNo registrationNo,ce.SerialNo serialNo,ce.Quantity quantity,ce.ModelNo modelNo,ce.Verified as verified, ce.Approved as approved,ce.CmnEquipmentId equipmentId  \
FROM crpconsultantequipment ce INNER JOIN cmnequipment  eq ON ce.CmnEquipmentId = eq.Id WHERE ce.CrpConsultantId =:consultantId

ConsultantActionDao.getFeeCategoryClass= SELECT cs.Id id,cs.Code code,cs.Name name,CONCAT(cs.Code, '-', cs.Name) categoryName,cw.CmnAppliedServiceId categoryId,s.Name obj1,GROUP_CONCAT(s.Code) aClassName,GROUP_CONCAT(s.Code) vClassName,GROUP_CONCAT(s.Code) apClassName,s.CmnConsultantServiceCategoryId text FROM crpconsultantworkclassification cw LEFT JOIN cmnconsultantservicecategory cs ON cs.Id = cw.CmnServiceCategoryId LEFT JOIN cmnconsultantservice s ON s.Id = cw.CmnAppliedServiceId WHERE cw.CrpConsultantId =:consultantId GROUP BY cw.CmnServiceCategoryId ASC ;

ConsultantActionDao.getAppHistoryDtl=SELECT c.* , CASE v.FullName WHEN NULL THEN '(Citizen)' ELSE v.FullName END userName FROM ( SELECT 'Submitted' appStatus,c.CrpConsultantId consultantId,c.CreatedBy userId,c.CreatedOn actionDate,''remarks FROM crpconsultant c UNION ALL SELECT 'Verified',c.CrpConsultantId,c.SysVerifierUserId,c.VerifiedDate,c.RemarksByVerifier FROM crpconsultant c UNION ALL SELECT 'Approved for Payment',c.CrpConsultantId,c.SysApproverUserId,c.RegistrationApprovedDate,c.RemarksByApprover FROM crpconsultant c UNION ALL SELECT 'Rejected',c.CrpConsultantId,c.SysRejecterUserId,c.RejectedDate,c.RemarksByRejector FROM crpconsultant c ) c LEFT JOIN sysuser v ON c.userId = v.Id WHERE c.consultantId =:consultantId AND c.actionDate IS NOT NULL

ConsultantActionDao.verify=CALL ProCrpConsultantApplicationVerify(:consultantId,:vUserId,:vRemarks)

ConsultantActionDao.approve=CALL ProCrpConsultantApplicationApprove(:consultantId,:aUserId,:aRemarks)

ConsultantActionDao.reject = UPDATE crpconsultant c SET c.SysRejecterUserId =:userId, \
		                          c.RejectedDate = CURDATE(), \
		                          c.RemarksByRejector = :remarks, \
                              c.RegistrationStatus = 3, \
		                          c.CmnApplicationRegistrationStatusId =:applicationStatusId \
	                          WHERE c.CrpConsultantId=:consultantId

ConsultantActionDao.sendBack=UPDATE crpconsultant c SET c.EditedBy =:userId, \
		                          c.EditedOn = CURDATE(), c.SysLockedByUserId = null \
                              c.RegistrationStatus = 7, \
		                          c.CmnApplicationRegistrationStatusId =:applicationStatusId \
	                          WHERE c.CrpConsultantId=:consultantId

ConsultantActionDao.paymentUpdate = CALL ProCrpConsultantNewRegistrationFinalData(:consultantId,:userId,:appStatusId,:createdBy)

ConsultantActionDao.getHRAttachments=SELECT a.DocumentName documentName,DocumentPath documentPath, FileType fileType FROM crpconsultanthumanresourceattachment a WHERE a.CrpConsultantHumanResourceId = :hrId
ConsultantActionDao.getEQAttachments= SELECT a.DocumentName documentName,DocumentPath documentPath, FileType fileType FROM crpconsultantequipmentattachment a WHERE a.CrpConsultantEquipmentId = :eqId
ConsultantActionDao.getIncAttachment = SELECT DocumentName AS documentName, DocumentPath documentPath,FileType AS fileType,AttachmentFor AS attachmentFor FROM crpconsultantattachment WHERE CrpConsultantId =:consultantId

/*New Queries*/
ConsultantActionDao.getNextCDBNo=SELECT DISTINCT a.`CDBNo`+1 AS cdbNo  FROM crpconsultantfinal a ORDER BY CONVERT(a.`CDBNo`, DECIMAL) DESC  LIMIT 1

ConsultantDao.getMaxId= SELECT  A.ReferenceNo FROM crpconsultant A ORDER BY A.ReferenceNo DESC LIMIT 1

ConsultantDao.getFirmName=SELECT a.NameOfFirm FROM crpconsultant a WHERE a.NameOfFirm =?

ConsultantDao.getTaskList=SELECT a.Name serviceName,a.ReferenceNo applicationNo,a.MobileNo contactNo,a.ApplicationDate applicationDate, a.CIDNo serviceNo,b.Name AS appStatus FROM crpconsultant a INNER JOIN cmnlistitem b  ON b.Id = a.CmnApplicationRegistrationStatusId  INNER JOIN crpconsultantappliedservice s ON s.CrpConsultantId = a.CrpConsultantId WHERE a.SysLockedByUserId IS NULL AND s.CmnServiceTypeId = ? AND a.CmnApplicationRegistrationStatusId = ? GROUP BY a.ReferenceNo  ORDER BY a.ReferenceNo DESC

ConsultantDao.getMyTaskList=SELECT a.Name serviceName,a.ReferenceNo applicationNo,a.MobileNo contactNo,a.ApplicationDate applicationDate,a.CIDNo serviceNo, b.Name AS appStatus FROM crpconsultant a INNER JOIN cmnlistitem b ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crpconsultantappliedservice s ON s.CrpConsultantId = a.CrpConsultantId WHERE a.SysLockedByUserId = ? AND s.CmnServiceTypeId = ? AND a.CmnApplicationRegistrationStatusId = ? GROUP BY a.ReferenceNo  ORDER BY a.ReferenceNo DESC

ConsultantDao.send2MyOrGroupTask=UPDATE crpconsultant a SET a.`SysLockedByUserId` =:lockUserId  WHERE a.ReferenceNo =:appNo

ConsultantDao.getOwnerShipDtls=SELECT  c.CIDNo oCid, b.Name salutationId, cc.Name nationality, c.ShowInCertificate showInCertificate, a.Name designationId, c.Name oname,IF(c.Sex='M','Male','Female') sex FROM crpconsultant c LEFT JOIN cmnlistitem b ON b.Id =c.CmnSalutationId LEFT JOIN cmncountry cc ON cc.Id = c.NationalityId LEFT JOIN cmnlistitem a ON a.Id=c.DesignationId WHERE c.CrpConsultantId =?

ConsultantDao.getconsultantHRs=SELECT hr.Id AS id,hr.CrpConsultantId consultantID,hr.CIDNo cidNo,hr.Name AS name,hr.Sex sex,hr.ShowInCertificate AS siCertificate,hr.Verified AS verified,hr.Approved,hr.IsPartnerOrOwner isPartnerOrOwner,hr.JoiningDate joiningDate, \
sa.Name salutationName,co.Name countryName,qu.Name qualificationName,st.Name serviceTypeName,td.Name tradeName,de.Name designationName FROM crpconsultanthumanresource hr INNER JOIN cmnlistitem sa ON sa.Id = hr.CmnSalutationId INNER JOIN cmncountry co ON co.Id = hr.CmnCountryId \
LEFT JOIN cmnlistitem qu ON qu.Id = hr.CmnQualificationId LEFT JOIN cmnlistitem st ON st.Id = hr.CmnServiceTypeId LEFT JOIN cmnlistitem td ON td.Id = hr.CmnTradeId INNER JOIN cmnlistitem de ON de.Id = hr.CmnDesignationId WHERE hr.CrpConsultantId = :consultantId AND (:ownerOrPartner IS NULL OR hr.IsPartnerOrOwner = :ownerOrPartner)

ConsultantDao.getConsultantEQList=SELECT cme.Name equipmentId,e.RegistrationNo registrationNo,e.Quantity quantity,a.DocumentName documentName,a.FileType fileType,a.Id id FROM crpconsultantequipment e LEFT JOIN cmnequipment cme ON cme.Id=e.CmnEquipmentId LEFT JOIN crpconsultantequipmentattachment a ON a.CrpConsultantEquipmentId=e.Id WHERE e.CrpConsultantId=?

ConsultantDao.getConsultantDetails=SELECT cf.Email id,cc.Name pCountryId,v.Village_Name pVillageId,g.Gewog_Name pGewogId,dd.NameEn pDzongkhagId,a.CDBNo cdbNo,a.Name fullname,a.CmnSalutationId cmnSalutationId,a.CmnApplicationRegistrationStatusId updateStatus,a.Address estAddress,a.Email regEmail,a.MobileNo regMobileNo,a.TelephoneNo regPhoneNo,a.FaxNo regFaxNo,a.NameOfFirm firmName,d.NameEn cmnRegisteredDzongkhagId,li.Name ownershiptype,a.TradeLicenseNo tradeLicenseNo,a.TPN tpn,a.CrpConsultantId consultantId,a.ReferenceNo,a.VerifiedDate verifcationdate,a.RemarksByVerifier verifierremarks,a.RegistrationApprovedDate approvaldate,a.RemarksByApprover approiverremarks,ss.Name serviceTypeId,a.RegistrationExpiryDate regExpDate,a.ApplicationDate applicationDate FROM crpconsultant a LEFT JOIN cmnlistitem i ON i.Id = a.CmnSalutationId LEFT JOIN cmnlistitem li ON a.CmnOwnershipTypeId=li.Id LEFT JOIN cmndzongkhag d ON d.Dzongkhag_Serial_No = a.CmnRegisteredDzongkhagId LEFT JOIN crpconsultantappliedservice s ON s.CrpConsultantId = a.CrpConsultantId LEFT JOIN crpservice ss ON ss.Id = s.CmnServiceTypeId LEFT JOIN crpconsultantfinal cf ON cf.ReferenceNo=a.ReferenceNo LEFT JOIN cmncountry cc ON cc.Id = a.CmnCountryId LEFT JOIN cmnvillage v ON v.Village_Serial_No=a.Permenant_village_serialNo LEFT JOIN cmngewog g ON g.Gewog_Serial_No =v.Gewog_Serial_No LEFT JOIN cmndzongkhag dd ON dd.Dzongkhag_Serial_No = g.Dzongkhag_Serial_No WHERE a.ReferenceNo =? GROUP BY a.ReferenceNo

ConsultantDao.getconsultantapplicationdetails=SELECT c.CrpConsultantId consultantId,c.ReferenceNo ReferenceNo,c.InitialDate initialDate,c.ApplicationDate applicationDate,c.CDBNo cdbNo,c.CIDNo cidNo, c.CmnSalutationId cmnSalutationId, c.NationalityId nationality,c.DesignationId designationId,c.Name fullname,c.Sex sex,c.CmnOwnershipTypeId cmnOwnershipTypeId,c.NameOfFirm firmName,c.CmnRegisteredDzongkhagId cmnRegisteredDzongkhagId,c.Address estAddress,c.Village pVillageId,c.Gewog pGewogId,c.CmnCountryId pCountryId,c.CmnDzongkhagId pDzongkhagId,c.Email regEmail,c.TelephoneNo regPhoneNo,c.MobileNo regMobileNo,c.FaxNo regFaxNo,c.TradeLicenseNo tradeLicenseNo,c.TPN tpn,c.CmnApplicationRegistrationStatusId updateStatus,c.VerifiedDate verifcationdate, c.RemarksByVerifier remarks,c.CreatedOn createdOn,c.CreatedBy createdBy,c.Permenant_village_serialNo pervillageserialno,c.ShowInCertificate showInCertificate,ss.Name serviceTypeId FROM crpconsultant c LEFT JOIN crpconsultantappliedservice s ON s.CrpConsultantId = c.CrpConsultantId LEFT JOIN crpservice ss ON ss.Id = s.CmnServiceTypeId WHERE c.ReferenceNo=?

ConsultantDao.fetchHRdetails=SELECT c.Id id,c.CrpConsultantId consultantID,c.CmnSalutationId salutationId,c.CIDNo cidNo,c.Name name,IF(c.Sex='M','Male','Female') sex,c.CmnCountryId countryId,c.CmnQualificationId qualificationId,c.CmnServiceTypeId serviceTypeId,c.CmnTradeId tradeId,c.CmnDesignationId designationId,c.ShowInCertificate siCertificate,c.IsPartnerOrOwner isPartnerOrOwner FROM crpconsultanthumanresource c WHERE c.CrpConsultantId=?

ConsultantDao.fetchHRAttdetails=SELECT c.CrpConsultantHumanResourceId consultantHrId,c.Id id,c.DocumentName documentName,c.DocumentPath documentPath,c.FileType fileType FROM crpconsultanthumanresourceattachment c WHERE c.CrpConsultantHumanResourceId=?

ConsultantDao.fetchEQdetails=SELECT e.CmnEquipmentId equipmentId,e.Id id,e.RegistrationNo registrationNo,e.Quantity quantity,e.SerialNo serialNo,e.ModelNo modelNo,e.CrpConsultantId consultantId FROM crpconsultantequipment e WHERE e.CrpConsultantId=?

ConsultantDao.fetchEQAttdetails=SELECT e.Id id, e.CrpConsultantEquipmentId equipmentId,e.DocumentName documentName,e.DocumentPath documentPath,e.FileType fileType FROM crpconsultantequipmentattachment e WHERE e.CrpConsultantEquipmentId=?

ConsultantDao.getCDBNo=SELECT f.CDBNo cdbNo FROM sysuser s LEFT JOIN crpconsultantfinal f ON f.SysUserId=s.Id WHERE s.username=?

ConsultantDao.getCategoryClass=SELECT cs.Id id,cs.Code code,cs.Name name,cw.CmnAppliedCategoryId categoryId,s.Name obj1,s.Code value  FROM crpconsultantworkclassification cw LEFT JOIN cmnconsultantservicecategory cs ON cs.Id=cw.CmnServiceCategoryId LEFT JOIN cmnconsultantservice s ON s.Code=cw.CmnAppliedCategoryId WHERE cw.CrpConsultantId =:consultantId

ConsultantDao.checkIfMailExists=SELECT s.username FROM sysuser s WHERE s.username=?

ConsultantDao.getConsultantAttachments=SELECT d.CrpConsultantId consultantId,d.DocumentName documentName,d.DocumentPath documentPath,d.FileType fileType,d.Id id FROM crpconsultantattachment d WHERE d.CrpConsultantId =?

/* Consultant renewal dao*/
ConsultantRCDao.getConsultantStatus = select bb.Id value,bb.Name text,bb.ReferenceNo obj1 from `crpconsultantfinal` aa inner join cmnlistitem bb on aa.`CmnApplicationRegistrationStatusId`=bb.Id where cdbNo = :cdbNo

ConsultantRCDao.getConsultantHRsFinal = SELECT hr.Id AS id,hr.CrpConsultantFinalId consultantID,hr.CIDNo cidNo,hr.Name AS name,hr.Sex sex,hr.ShowInCertificate AS siCertificate,hr.DeleteRequest AS deleteRequest,hr.IsPartnerOrOwner isPartnerOrOwner,DATE_FORMAT(hr.JoiningDate, '%d-%m-%Y') joinDate,sa.Name salutationName,co.Name countryName,qu.Name qualificationName,st.Name serviceTypeName,td.Name tradeName,de.Name designationName FROM crpconsultanthumanresourcefinal hr INNER JOIN cmnlistitem sa ON sa.Id = hr.CmnSalutationId INNER JOIN cmncountry co ON co.Id = hr.CmnCountryId LEFT JOIN cmnlistitem qu ON qu.Id = hr.CmnQualificationId LEFT JOIN cmnlistitem st ON st.Id = hr.CmnServiceTypeId LEFT JOIN cmnlistitem td ON td.Id = hr.CmnTradeId INNER JOIN cmnlistitem de ON de.Id = hr.CmnDesignationId WHERE hr.CrpConsultantFinalId = :consultantId AND (:ownerOrPartner IS NULL OR hr.IsPartnerOrOwner = :ownerOrPartner)
ConsultantRCDao.getHRAttachmentsFinal=SELECT a.DocumentName documentName,DocumentPath documentPath, FileType fileType FROM crpconsultanthumanresourceattachmentfinal a WHERE a.CrpConsultantHumanResourceFinalId = :hrId

ConsultantRCDao.getEquipmentFinal = SELECT ce.`Id` id, eq.`Name` equipmentName,ce.`RegistrationNo` registrationNo,ce.`SerialNo` serialNo,ce.`Quantity` quantity,ce.`ModelNo` modelNo ,ce.DeleteRequest AS deleteRequest,ce.CmnEquipmentId equipmentId FROM `crpconsultantequipmentfinal` ce INNER JOIN `cmnequipment`  eq ON ce.`CmnEquipmentId` = eq.`Id` WHERE ce.`CrpConsultantFinalId` =:consultantId
ConsultantRCDao.getEQAttachmentsFinal=SELECT a.DocumentName documentName,DocumentPath documentPath, FileType fileType FROM crpconsultantequipmentattachmentfinal a WHERE a.CrpConsultantEquipmentFinalId  = :eqId

ConsultantRCDao.saveDeleteHrRequest = Update crpconsultanthumanresourcefinal set DeleteRequest = 1 where Id =:hrId
ConsultantRCDao.saveDeleteEqRequest = Update crpconsultantequipmentfinal set DeleteRequest = 1 where Id =:eqId

ConsultantRCDao.getServicesFee= SELECT Id,ReferenceNo, Name AS serviceName, ConsultantAmount AS feeAmount  FROM `crpservice` WHERE ((:refNo is null and ReferenceNo IN (4,6,7,8,9,10,12)) or ReferenceNo = :refNo)

ConsultantRCDao.getIncAttachmentFinal = SELECT Id id, DocumentName AS documentName, DocumentPath documentPath,FileType AS fileType FROM crpconsultantattachmentfinal WHERE CrpConsultantFinalId =:consultantIdFinal
/*Consultant Renewal Action dao*/
ConsultantRCActionDao.getAppliedServices=SELECT a.ReferenceNo applicationNo,c.CmnServiceTypeId serviceId,d.referenceNo serviceRefNo,d.Name serviceName,e.PaymentAmount,a.NameOfFirm firmName,b.Name AS appStatus,a.ApplicationDate applicationDate FROM crpconsultant a INNER JOIN cmnlistitem b ON b.Id = a.CmnApplicationRegistrationStatusId INNER JOIN crpconsultantappliedservice c ON c.CrpConsultantId = a.CrpConsultantId INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId INNER JOIN crpconsultantservicepayment e ON e.CrpConsultantId = c.CrpConsultantId AND e.CmnServiceTypeId = c.CmnServiceTypeId WHERE a.ReferenceNo =:applicationNo

ConsultantRCActionDao.getDeleteHrRequest =SELECT hr.Id AS id,hr.CrpConsultantFinalId consultantID,hr.CIDNocidNo,hr.Name AS NAME,hr.Sex sex,hr.ShowInCertificate AS siCertificate,hr.DeleteRequest AS deleteRequest,hr.IsPartnerOrOwner isPartnerOrOwner,hr.JoiningDate joiningDate,sa.Name salutationName,co.Name countryName,qu.Name qualificationName,st.Name serviceTypeName,td.Name tradeName,de.Name designationName FROM crpconsultanthumanresourcefinal hr INNER JOIN cmnlistitem sa ON sa.Id = hr.CmnSalutationId INNER JOIN cmncountry co ON co.Id = hr.CmnCountryId LEFT JOIN cmnlistitem qu ON qu.Id = hr.CmnQualificationId LEFT JOIN cmnlistitem st ON st.Id = hr.CmnServiceTypeId LEFT JOIN cmnlistitem td ON td.Id = hr.CmnTradeId INNER JOIN cmnlistitem de ON de.Id = hr.CmnDesignationId WHERE hr.CrpConsultantFinalId = :consultantId AND (:ownerOrPartner IS NULL OR hr.IsPartnerOrOwner = :ownerOrPartner)

ConsultantRCDao.getCategoryClassFinal=SELECT Id AS id,CrpConsultantFinalId AS consultantId,CmnServiceCategoryId AS categoryId,CmnAppliedServiceId AS aClassId,CmnVerifiedServiceId AS vClassId,CmnApprovedServiceId AS apClassId FROM crpconsultantworkclassificationfinal WHERE CrpConsultantFinalId = :consultantId

ConsultantRCActionDao.paymentUpdate = CALL ProCrpConsultantRenewalPaymentApproval(:consultantId,:userId,:appStatusId,:createdBy)

ConsultantRCActionDao.getEmployeeDetailsFromCDB = SELECT con.NameOfFirm consultantFirmname,con.CDBNo consultantCDBNo,co.NameOfFirm contractorFirmname,co.CDBNo contractorCDBNo,s.NameOfFirm spFirmname, s.SPNo spCDBNo FROM crpcontractorhumanresourcefinal c LEFT JOIN crpconsultanthumanresourcefinal crp ON c.CIDNo = crp.CIDNo LEFT JOIN crpspecializedtradehumanresourcefinal sf ON sf.CIDNo = c.CIDNo LEFT JOIN crpspecializedtradefinal s ON s.Id = sf.CrpSpecializedTradeFinalId LEFT JOIN crpconsultantfinal con ON con.Id = crp.CrpConsultantFinalId LEFT JOIN crpcontractorfinal co ON co.Id = c.CrpContractorFinalId WHERE c.CIDNo =:cidNo

ConsultantRCDao.getRegisteredService=SELECT c.AppliedService serviceName FROM crpconsultantregistrationpayment c WHERE c.CrpConsultantFinalId=:consultantFinalId GROUP BY AppliedService;

ConsultantRCActionDao.getProposedCategories=