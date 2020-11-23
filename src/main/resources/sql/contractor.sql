
/*Contractor Dao*/
ContractorDao.gFeeStructure = SELECT cc.`Id` AS id,cc.`Name` AS name,cc.`Code` AS code,cc.`RegistrationFee` AS registrationFee,cc.`RenewalFee` AS renewalFee FROM `cmncontractorclassification` cc where (:category is null or cc.Id =:category) ORDER BY referenceNo ASC
ContractorDao.gContractCategory = SELECT wc.Id AS id, wc.`Name` AS name,wc.`Code` AS code FROM `cmncontractorworkcategory` wc ORDER BY referenceNo ASC
ContractorDao.gClassification = SELECT a.Id AS value, CONCAT(a.Code,':',a.Name) text FROM `cmncontractorclassification` a ORDER BY a.code ASC
ContractorDao.gEquipment = SELECT a.`Id` AS value,a.`Name` AS text,a.IsRegistered as obj1  FROM `cmnequipment` a ORDER BY a.Name ASC
ContractorDao.isEmailUnique = SELECT a.Email FROM `crpcontractor` a WHERE a.Email =:email
ContractorDao.isFirmNameUnique = SELECT a.NameOfFirm FROM `crpcontractor` a WHERE a.NameOfFirm =:firmName
ContractorDao.getTrainingDtl = SELECT a.`CmnTrainingTypeId` tTypeId,a.`CmnTrainingModuleId` tModuleId,tt.`Name` tType,tm.`Name` tModule,a.`TrainingFromDate` fromDate,a.`TrainingToDate` toDate,b.`CIDNo` cidNo,b.`Participant` participant FROM `crpcontractortraining` a INNER JOIN `crpcontractortrainingdetail` b ON a.`Id` = b.`CrpContractorTrainingId` INNER JOIN `cmnlistitem` tt ON tt.Id = a.`CmnTrainingTypeId` INNER JOIN `cmnlistitem` tm ON tm.Id = a.`CmnTrainingModuleId` WHERE b.`CIDNo` =:cidNo
ContractorDao.getContractorOngoingApp = SELECT aa.`CrpContractorId` contractorId,aa.`ReferenceNo` AS referenceNo, aa.ApplicationDate AS applicationDate, aa.CDBNo AS cdbNo,aa.Id AS appStatusId,bb.Name AS appStatusName FROM `crpcontractor` aa INNER JOIN cmnlistitem bb ON aa.`CmnApplicationRegistrationStatusId`=bb.Id WHERE  bb.Id IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND aa.CDBNo =:cdbNo



/** ContractorActionDao */
ContractorActionDao.gTaskList = CALL ProCrpContractorTaskList (:userId,:status,:service);

ContractorActionDao.getContractorHRs = SELECT hr.`Id` AS id,hr.`CrpContractorId` contractorID,hr.`CIDNo`cidNo,hr.`Name` AS name, hr.`Sex` sex,hr.`ShowInCertificate` AS siCertificate, hr.Verified as verified,hr.Approved \
,hr.`IsPartnerOrOwner` isPartnerOrOwner,hr.`JoiningDate` joiningDate,sa.`Name` salutationName,co.`Name` countryName,qu.`Name` qualificationName,st.`Name` serviceTypeName,td.`Name` tradeName,de.`Name` designationName \
FROM `crpcontractorhumanresource` hr INNER JOIN `cmnlistitem` sa ON sa.`Id` = hr.`CmnSalutationId` INNER JOIN `cmncountry` co ON co.`Id` = hr.`CmnCountryId` LEFT JOIN `cmnlistitem` qu ON qu.`Id` = hr.`CmnQualificationId` \
LEFT JOIN `cmnlistitem` st ON st.`Id` = hr.`CmnServiceTypeId` LEFT JOIN `cmnlistitem` td ON td.`Id` = hr.`CmnTradeId` INNER JOIN `cmnlistitem` de ON de.`Id` = hr.`CmnDesignationId` WHERE hr.`CrpContractorId` =:contractorId and (:ownerOrPartner is null or hr.`IsPartnerOrOwner` =:ownerOrPartner)

ContractorActionDao.getFeeCategoryClass = SELECT cc.`Id` id,cc.`CmnCategoryId` categoryId,CONCAT(wc.`Code`,'-',wc.`Name`) categoryName, a.`Name` aClassName,cc.`AppliedAmount` AS aAmount,v.`Name` vClassName,cc.`VerifiedAmount` AS vAmount,ap.`Name` apClassName,cc.ApprovedAmount apAmount \
FROM `crpcontractorregistrationpayment` cc INNER JOIN `cmncontractorworkcategory` wc ON wc.`Id` = cc.`CmnCategoryId` LEFT JOIN `cmncontractorclassification` a ON a.`Id` = cc.`CmnAppliedClassificationId` LEFT JOIN `cmncontractorclassification` v ON v.`Id` = cc.`CmnVerifiedClassificationId` \
LEFT JOIN `cmncontractorclassification` ap ON ap.`Id` = cc.`CmnApprovedClassificationId`  WHERE cc.`CrpContractorFinalId` =:contractorId

ContractorActionDao.getEquipment = SELECT ce.`Id` id, ce.`CrpContractorId` contractorId, eq.`Name` equipmentName,ce.`RegistrationNo` registrationNo,ce.`SerialNo` serialNo,ce.`Quantity` quantity,ce.`ModelNo` modelNo \
,ce.Verified as verified, ce.Approved as approved \
FROM `crpcontractorequipment` ce INNER JOIN `cmnequipment`  eq ON ce.`CmnEquipmentId` = eq.`Id`WHERE ce.`CrpContractorId` = :contractorId

ContractorActionDao.verify = CALL ProCrpContractorApplicationVerify(:contractorId,:vUserId,:vRemarks)
ContractorActionDao.approve = CALL ProCrpContractorApplicationApprove(:contractorId,:aUserId,:aRemarks)
ContractorActionDao.reject = UPDATE crpcontractor c SET c.SysRejecterUserId =:userId, \
		                          c.RejectedDate = CURDATE(), \
		                          c.RemarksByRejector = :remarks, \
                              c.RegistrationStatus = 3, \
		                          c.CmnApplicationRegistrationStatusId =:applicationStatusId \
	                          WHERE c.CrpContractorId=:contractorId

ContractorActionDao.sendBack=UPDATE crpcontractor c SET c.EditedBy =:userId, \
		                          c.EditedOn = CURDATE(), c.SysLockedByUserId = null, \
                              c.RegistrationStatus = 7, \
		                          c.CmnApplicationRegistrationStatusId =:applicationStatusId \
	                          WHERE c.CrpContractorId=:contractorId

ContractorActionDao.paymentUpdate = CALL ProCrpContractorNewRegistrationFinalData(:contractorId,:userId,:appStatusId,:createdBy)

ContractorActionDao.getAppHistoryDtl = SELECT c.* , case v.`FullName` when NULL then '(Citizen)' else v.`FullName` end userName FROM ( SELECT 'Submitted' appStatus,c.`CrpContractorId` contractorId,c.`CreatedBy` userId,c.`CreatedOn` actionDate,''remarks FROM `crpcontractor` c UNION ALL SELECT 'Verified',c.`CrpContractorId`,c.`SysVerifierUserId`,c.`RegistrationVerifiedDate`,c.`RemarksByVerifier`FROM `crpcontractor` c UNION ALL SELECT 'Approved for Payment',c.`CrpContractorId`,c.`SysApproverUserId`,c.`RegistrationApprovedDate`,c.`RemarksByApprover` FROM `crpcontractor` c UNION ALL SELECT 'Rejected',c.`CrpContractorId`,c.`SysRejecterUserId`,c.`RejectedDate`,c.`RemarksByRejector` FROM `crpcontractor` c ) c LEFT JOIN `sysuser` v ON c.`userId` = v.`Id` WHERE c.`contractorId` =:contractorId AND c.actionDate IS NOT NULL
ContractorActionDao.send2MyOrGroupTask = UPDATE crpcontractor a SET a.`SysLockedByUserId` =:lockUserId  WHERE a.ReferenceNo =:appNo
ContractorActionDao.getNextCDBNo = SELECT DISTINCT a.`CDBNo`+1 AS cdbNo  FROM crpcontractorfinal a ORDER BY CONVERT(a.`CDBNo`, DECIMAL) DESC  LIMIT 1
ContractorActionDao.getHRAttachments = SELECT a.`DocumentName` documentName,`DocumentPath` documentPath, `FileType` fileType FROM `crpcontractorhumanresourceattachment` a WHERE a.`CrpContractorHumanResourceId` = :hrId
ContractorActionDao.getEQAttachments = SELECT a.`DocumentName` documentName,`DocumentPath` documentPath, `FileType` fileType FROM `crpcontractorequipmentattachment` a WHERE a.`CrpContractorEquipmentId`  = :eqId
ContractorActionDao.getIncAttachment = SELECT DocumentName AS documentName, DocumentPath documentPath,FileType AS fileType FROM crpcontractorattachment WHERE CrpContractorId =:contractorId
/* Contractor renewal dao*/
ContractorRCDao.getContractorStatus =select bb.Id value,bb.Name text,bb.ReferenceNo obj1 from `crpcontractorfinal` aa inner join cmnlistitem bb on aa.`CmnApplicationRegistrationStatusId`=bb.Id where cdbNo = :cdbNo
ContractorRCDao.getServicesFee = SELECT Id,ReferenceNo, Name AS serviceName, ContractorAmount AS feeAmount  FROM `crpservice` WHERE ((:refNo is null and ReferenceNo IN (4,6,7,8,9,10,12)) or ReferenceNo = :refNo)

ContractorRCDao.getContractorHRsFinal = SELECT hr.`Id` AS id,hr.`CrpContractorFinalId` contractorID,hr.`CIDNo`cidNo,hr.`Name` AS name, hr.`Sex` sex,hr.`ShowInCertificate` AS siCertificate, hr.DeleteRequest AS deleteRequest \
,hr.`IsPartnerOrOwner` isPartnerOrOwner,DATE_FORMAT(hr.`JoiningDate`,'%d-%m-%Y') joiningDate,sa.`Name` salutationName,co.`Name` countryName,qu.`Name` qualificationName,st.`Name` serviceTypeName,td.`Name` tradeName,de.`Name` designationName \
FROM `crpcontractorhumanresourcefinal` hr INNER JOIN `cmnlistitem` sa ON sa.`Id` = hr.`CmnSalutationId` INNER JOIN `cmncountry` co ON co.`Id` = hr.`CmnCountryId` LEFT JOIN `cmnlistitem` qu ON qu.`Id` = hr.`CmnQualificationId` \
LEFT JOIN `cmnlistitem` st ON st.`Id` = hr.`CmnServiceTypeId` LEFT JOIN `cmnlistitem` td ON td.`Id` = hr.`CmnTradeId` INNER JOIN `cmnlistitem` de ON de.`Id` = hr.`CmnDesignationId` WHERE hr.`CrpContractorFinalId` =:contractorId and (:ownerOrPartner is null or hr.`IsPartnerOrOwner` =:ownerOrPartner)

ContractorRCDao.getEquipmentFinal = SELECT ce.`Id` id, eq.`Name` equipmentName,ce.`RegistrationNo` registrationNo,ce.`SerialNo` serialNo,ce.`Quantity` quantity,ce.`ModelNo` modelNo ,ce.DeleteRequest AS deleteRequest FROM `crpcontractorequipmentfinal` ce INNER JOIN `cmnequipment`  eq ON ce.`CmnEquipmentId` = eq.`Id` WHERE ce.`CrpContractorFinalId` =:contractorId
ContractorRCDao.getHRAttachmentsFinal = SELECT a.Id as id,a.`DocumentName` documentName,`DocumentPath` documentPath, `FileType` fileType FROM `crpcontractorhumanresourceattachmentfinal` a WHERE a.`CrpContractorHumanResourceFinalId` = :hrId
ContractorRCDao.getEQAttachmentsFinal = SELECT a.Id as id,a.`DocumentName` documentName,`DocumentPath` documentPath, `FileType` fileType FROM `crpcontractorequipmentattachmentfinal` a WHERE a.`CrpContractorEquipmentFinalId`  = :eqId
ContractorRCDao.getCategoryClassFinal = SELECT 	`Id` AS id, `CrpContractorFinalId` AS contractorId,`CmnProjectCategoryId` AS categoryId,`CmnAppliedClassificationId` AS aClassId, `CmnVerifiedClassificationId` AS vClassId, `CmnApprovedClassificationId` AS apClassId FROM `crpcontractorworkclassificationfinal` WHERE CrpContractorFinalId =:contractorId
ContractorRCDao.saveDeleteHrRequest = Update crpcontractorhumanresourcefinal set DeleteRequest = 1 where Id =:hrId
ContractorRCDao.saveDeleteEqRequest = Update crpcontractorequipmentfinal set DeleteRequest = 1 where Id =:eqId
ContractorRCDao.auditMemo = SELECT CONCAT('You have following audit memo:<br>',AIN,' : ',`AuditObservation`) AS auditObservation FROM `crpcontractorauditclearance` WHERE `CrpContractorConsultantId` =:contractorFinalId AND  `Dropped` = '0'

/*Contractor Renewal Action Dao*/

ContractorRCActionDao.getAppliedServices = SELECT a.`ReferenceNo` applicationNo,c.CmnServiceTypeId serviceId,d.referenceNo serviceRefNo, d.Name serviceName, e.PaymentAmount ,a.`NameOfFirm` firmName,b.`Name` AS appStatus,a.`ApplicationDate` applicationDate FROM `crpcontractor` a  INNER JOIN `cmnlistitem` b ON b.`Id`  = a.`CmnApplicationRegistrationStatusId` INNER JOIN `crpcontractorappliedservice` c ON c.`CrpContractorId` = a.`CrpContractorId` INNER JOIN `crpservice` d ON d.`Id` = c.`CmnServiceTypeId` INNER JOIN `crpcontractorservicepayment`  e ON e.`CrpContractorId` = c.CrpContractorId AND e.`CmnServiceTypeId` = c.CmnServiceTypeId WHERE a.`ReferenceNo` = :applicationNo

/*ContractorRCActionDao.getAppliedServices = SELECT CAST(a.`ReferenceNo` as char ) applicationNo,c.CmnServiceTypeId serviceId,CAST(d.referenceNo as char) serviceRefNo, d.Name serviceName, e.PaymentAmount ,a.`NameOfFirm` firmName,b.`Name` AS appStatus,a.`ApplicationDate` applicationDate FROM `crpcontractor` a  INNER JOIN `cmnlistitem` b ON b.`Id`  = a.`CmnApplicationRegistrationStatusId` INNER JOIN `crpcontractorappliedservice` c ON c.`CrpContractorId` = a.`CrpContractorId` INNER JOIN `crpservice` d ON d.`Id` = c.`CmnServiceTypeId` INNER JOIN `crpcontractorservicepayment`  e ON e.`CrpContractorId` = c.CrpContractorId AND e.`CmnServiceTypeId` = c.CmnServiceTypeId WHERE a.`ReferenceNo` = :applicationNo */
ContractorRCActionDao.getDeleteHrRequest = SELECT hr.`Id` AS id,hr.`CrpContractorFinalId` contractorID,hr.`CIDNo`cidNo,hr.`Name` AS name, hr.`Sex` sex,hr.`ShowInCertificate` AS siCertificate, hr.DeleteRequest AS deleteRequest \
,hr.`IsPartnerOrOwner` isPartnerOrOwner,hr.`JoiningDate` joiningDate,sa.`Name` salutationName,co.`Name` countryName,qu.`Name` qualificationName,st.`Name` serviceTypeName,td.`Name` tradeName,de.`Name` designationName \
FROM `crpcontractorhumanresourcefinal` hr INNER JOIN `cmnlistitem` sa ON sa.`Id` = hr.`CmnSalutationId` INNER JOIN `cmncountry` co ON co.`Id` = hr.`CmnCountryId` LEFT JOIN `cmnlistitem` qu ON qu.`Id` = hr.`CmnQualificationId` \
LEFT JOIN `cmnlistitem` st ON st.`Id` = hr.`CmnServiceTypeId` LEFT JOIN `cmnlistitem` td ON td.`Id` = hr.`CmnTradeId` INNER JOIN `cmnlistitem` de ON de.`Id` = hr.`CmnDesignationId` WHERE hr.`CrpContractorFinalId` =:contractorId and (:ownerOrPartner is null or hr.`IsPartnerOrOwner` =:ownerOrPartner)

ContractorRCActionDao.getProposedCategories = SELECT s.`CrpContractorId` contractorId,ss.Name serviceName,ss.ReferenceNo serviceRefNo,ca.Code categoryName,ex.Name exClassName,ap.Name aClassName ,d.Amount aAmount, d.`CmnCategoryId` categoryId,d.`CmnExistingClassificationId` exClassId,d.`CmnAppliedClassificationId` aClassId \
FROM `crpcontractorservicepayment` s INNER JOIN `crpcontractorservicepaymentdetail` d ON s.`Id` = `CrpContractorServicePaymentId` INNER JOIN `crpcontractor` c ON s.`CrpContractorId` = c.Id INNER JOIN `crpservice` ss ON ss.Id = s.`CmnServiceTypeId` \
LEFT JOIN `cmncontractorclassification` ap ON ap.Id = d.`CmnAppliedClassificationId` LEFT JOIN `cmncontractorclassification` ex ON ex.Id = d.`CmnExistingClassificationId` \
LEFT JOIN `cmncontractorworkcategory` ca ON ca.Id = d.`CmnCategoryId` WHERE c.`ReferenceNo` = :appNo ORDER BY ca.ReferenceNo ASC
ContractorRCActionDao.paymentUpdate = CALL ProCrpContractorRenewalPaymentApproval(:contractorId,:userId,:appStatusId,:createdBy)