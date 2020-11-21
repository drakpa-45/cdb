SpecializedDao.gFeeStructure = SELECT CAST(IF(f.`NewRegistrationFee` IS NULL,'0',f.`NewRegistrationFee`) AS UNSIGNED) \
registrationFee,f.`FirstRenewalFee` renewalFee,f.SecondRenewalFee secondrenewalFee,f.`Name` name,f.`RegistrationValidity` validaty FROM`crpservicefeestructure` \
f WHERE f.`Name`='Specialized Trade'

SpecializedDao.gEquipment= SELECT a.`Id` AS value,a.`Name` AS text,a.IsRegistered AS obj1  FROM `cmnequipment` a

SpecializedDao.category=SELECT a.Id AS id, CONCAT(a.Code,'-',a.Name) code FROM cmnspecializedtradecategory a WHERE a.Code LIKE '%SP%';

SpecializedDao.categoryFirm=SELECT a.Id AS id, CONCAT(a.Code,'-',a.Name) code FROM cmnspecializedtradecategory a WHERE a.Code LIKE '%SF%';

SpecializedDao.getDtls=SELECT a.CIDNo cidNo, a.Name fullname, a.MobileNo mobileNo,  \
a.ApplicationDate initialDate,a.ReferenceNo referenceNo FROM crpspecializedtrade a WHERE a.ReferenceNo=?

SpecializedDao.getMaxId = SELECT  a.ReferenceNo FROM crpspecializedtrade a ORDER BY a.ReferenceNo DESC LIMIT 1

SpecializedDao.getTaskList=SELECT a.Name serviceName,a.ReferenceNo applicationNo,a.MobileNo contactNo,a.ApplicationDate applicationDate, \
a.CIDNo serviceNo,b.Name AS appStatus FROM crpspecializedtrade a INNER JOIN cmnlistitem b  ON b.Id = a.CmnApplicationRegistrationStatusId  \
INNER JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId = a.CrpSpecializedTradeId WHERE a.SysLockedByUserId IS NULL AND s.CmnServiceTypeId = ?  \
AND a.CmnApplicationRegistrationStatusId = ? GROUP BY a.ReferenceNo  ORDER BY a.ReferenceNo DESC

SpecializedDao.getMyTaskList=SELECT a.Name serviceName,a.ReferenceNo applicationNo,a.MobileNo contactNo,a.ApplicationDate applicationDate, \
 a.CIDNo serviceNo, b.Name AS appStatus FROM crpspecializedtrade a INNER JOIN cmnlistitem b ON b.Id = a.CmnApplicationRegistrationStatusId  \
 INNER JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId = a.CrpSpecializedTradeId WHERE a.SysLockedByUserId = ? AND s.CmnServiceTypeId = ?  \
 AND a.CmnApplicationRegistrationStatusId = ? GROUP BY a.ReferenceNo  ORDER BY a.ReferenceNo DESC

SpecializedDao.send2MyOrGroupTask = UPDATE crpspecializedtrade a SET a.`SysLockedByUserId` =:lockUserId  WHERE a.ReferenceNo =:appNo

SpecializedDao.getSpecializedradeDtls = SELECT a.SPNo cdbNo,a.CmnApplicationRegistrationStatusId updateStatus,a.TelephoneNo telephoneNo,a.TPN tpn,a.CrpSpecializedTradeId crpSpecializedTradeId,a.ReferenceNo,w.CmnAppliedCategoryId id,cat.Name name,a.CIDNo cidNo,a.Name fullname,i.Name salutation,d.NameEn dzongkhagId,a.Gewog gewog,a.Village village,a.Email email,a.MobileNo mobileNo,a.EmployerName employeeName,a.EmployerAddress employeeAddress,a.VerifiedDate verifcationdate,a.RemarksByVerifier verifierremarks,a.RegistrationApprovedDate approvaldate,a.RemarksByApprover approiverremarks,ss.Name serviceTypeId,a.RegistrationExpiryDate regExpDate,a.ApplicationDate applicationDate,a.CIDNo createdBy, o.FullName verifierUser,z.FullName approverUser,IF(css.NoOfDaysLate IS NULL, 0,  css.NoOfDaysLate) noOfDaysLate,IF(css.noOfDaysAfterGracePeriod IS NULL, 0,  css.noOfDaysAfterGracePeriod ) noOfDaysAfterGracePeriod, css.PaymentAmount paymentAmt,css.PenaltyPerDay penaltyPerDay,css.TotalAmount totalAmt  FROM crpspecializedtrade a LEFT JOIN sysuser o ON o.Id = a.SysVerifierUserId LEFT JOIN sysuser z ON z.Id = a.SysApproverUserId LEFT JOIN cmnlistitem i ON i.Id = a.CmnSalutationId LEFT JOIN cmndzongkhag d ON d.Id = a.CmnDzongkhagId LEFT JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId = a.CrpSpecializedTradeId LEFT JOIN crpservice ss ON ss.Id = s.CmnServiceTypeId  LEFT JOIN crpspecializedtradeworkclassification  w  ON w.CrpSpecializedTradeId=a.CrpSpecializedTradeId LEFT JOIN cmnspecializedtradecategory cat ON w.CmnAppliedCategoryId = cat.Id LEFT JOIN  crpspecializedtradeservicepayment css ON css.CrpSpecializedTradeId = a.CrpSpecializedTradeId WHERE a.ReferenceNo =? GROUP BY a.ReferenceNo
SpecializedDao.getTradeDoc=SELECT d.Id id,d.DocumentName,d.DocumentPath,d.CreatedOn,d.FileType FROM crpspecializedtradeattachment d WHERE d.CrpSpecializedTradeId=?

SpecializedDao.getspcializedTradeDetails=SELECT ApplicationDate applicationDate,CmnApplicationRegistrationStatusId updateStatus,CrpSpecializedTradeId,ReferenceNo,CIDNo cidNo,NAME fullname,CmnSalutationId salutation,CmnDzongkhagId dzongkhagId,Gewog gewog,Village village,Email email,MobileNo mobileNo,TelephoneNo telephoneNo,TPN tpn,EmployerName employeeName,EmployerAddress employeeAddress,RegistrationApprovedDate approvaldate,RemarksByApprover approiverremarks,RegistrationExpiryDate regExpDate, CmnCountryId countryId,CmnServiceSectorTypeId serviceSectorType,CmnRegisteredDzongkhagId cmnRegisteredDzongkhagId,NameOfFirm firmName,TradeLicenseNo tradeLicenseNo,Permenant_village_serialNo pervillageserialno,RegisteredAddress registeredAddress,Sex sex,ShowInCertificate showInCertificate,CmnOwnershipTypeId cmnOwnershipTypeId,NationalityId nationality,DesignationId designationId,FaxNo regFaxNo FROM crpspecializedtrade WHERE ReferenceNo = ?
SpecializedDao.getPrintList=SELECT f.SPNo cdbNo,f.ApplicationDate applicationDate,f.CIDNo cidNo,f.Email email,f.Name fullname,f.MobileNo mobileNo,f.EditedOn approvaldate FROM crpspecializedtradefinal f WHERE f.CmnApplicationRegistrationStatusId=? group BY f.SPNo;

SpecializedDao.getFirmName=SELECT a.NameOfFirm FROM crpspecializedtrade a WHERE a.NameOfFirm =?

SpecializedDao.appliedserviceid=SELECT w.Id AS id, w.CmnAppliedCategoryId AS appliedCategoryId,CONCAT(c.Code,'-',c.Name) code FROM crpspecializedtradeworkclassification w LEFT JOIN cmnspecializedtradecategory c ON w.CmnAppliedCategoryId = c.Id WHERE w.CrpSpecializedTradeId=?;

/*Queries for Incorporated goes here....*/

SpecializedDao.getTradeDetailsForIncop=SELECT a.SPNo cdbNo,cc.Name countryId,v.Village_Name village,g.Gewog_Name gewog,dd.NameEn dzongkhagId,cf.Email id,a.CmnApplicationRegistrationStatusId updateStatus,a.RegisteredAddress registeredAddress,a.Email regEmail,a.MobileNo regMobileNo,a.TelephoneNo regPhoneNo,a.FaxNo regFaxNo,a.NameOfFirm firmName,d.NameEn cmnRegisteredDzongkhagId,a.CmnOwnershipTypeId ownershiptype,a.TradeLicenseNo tradeLicenseNo,a.TPN tpn,a.CrpSpecializedTradeId crpSpecializedTradeId,a.ReferenceNo,a.VerifiedDate verifcationdate,a.RemarksByVerifier verifierremarks,a.RegistrationApprovedDate approvaldate,a.RemarksByApprover approiverremarks,ss.Name serviceTypeId,a.RegistrationExpiryDate regExpDate,a.ApplicationDate applicationDate FROM crpspecializedtrade a LEFT JOIN cmnlistitem i ON i.Id = a.CmnSalutationId LEFT JOIN cmndzongkhag d ON d.Dzongkhag_Serial_No = a.CmnRegisteredDzongkhagId LEFT JOIN crpspecializedtradeappliedservice s ON s.CrpSpecializedTradeId = a.CrpSpecializedTradeId LEFT JOIN crpservice ss ON ss.Id = s.CmnServiceTypeId LEFT JOIN crpspecializedtradefinal cf ON cf.ReferenceNo = a.ReferenceNo LEFT JOIN cmncountry cc ON cc.Id = a.CmnCountryId LEFT JOIN cmnvillage v ON v.Village_Serial_No = a.Permenant_village_serialNo LEFT JOIN cmngewog g ON g.Gewog_Serial_No = v.Gewog_Serial_No LEFT JOIN cmndzongkhag dd ON dd.Dzongkhag_Serial_No = g.Dzongkhag_Serial_No WHERE a.ReferenceNo = ? GROUP BY a.ReferenceNo

SpecializedDao.getOwnerShipDtls=SELECT  c.CIDNo oCid, b.Name salutationId, cc.Name nationality, c.ShowInCertificate showInCertificate, a.Name designationId, c.Name oname,IF(c.Sex='M','Male','Female') sex FROM crpspecializedtrade c LEFT JOIN cmnlistitem b ON b.Id =c.CmnSalutationId LEFT JOIN cmncountry cc ON cc.Id = c.NationalityId LEFT JOIN cmnlistitem a ON a.Id=c.DesignationId WHERE c.CrpSpecializedTradeId =?

SpecializedDao.getSpecializedtradeHRList=SELECT a.CIDNo cidNo,a.Name name,ccccc.Name serviceTypeId,IF(a.Sex ='M', 'Male', 'Female') sex, cccc.Name tradeId,b.Name countryId,c.Name designationId,cc.Name qualificationId,ccc.Name salutationId,d.DocumentName documentName,d.DocumentPath documentPath,d.FileType fileType,d.Id id FROM crpspecializedtradehumanresource a LEFT JOIN cmncountry b ON b.Id=a.CmnCountryId LEFT JOIN cmnlistitem c ON c.Id = a.CmnDesignationId LEFT JOIN cmnlistitem cc ON cc.Id = a.CmnQualificationId LEFT JOIN cmnlistitem ccc ON ccc.Id = a.CmnSalutationId LEFT JOIN cmnlistitem cccc ON cccc.Id = a.CmnTradeId LEFT JOIN cmnlistitem ccccc ON ccccc.Id = a.CmnServiceTypeId LEFT JOIN crpspecializedtradehumanresourceattachment d ON d.CrpSpecializedtradeHumanResourceId = a.Id WHERE a.CrpSpecializedTradeId=?

SpecializedDao.getSpecializedtradeEQList=SELECT cme.Name equipmentId,e.RegistrationNo registrationNo,e.Quantity quantity,a.DocumentName documentName,a.FileType fileType,a.Id id FROM crpspecializedtradeequipment e LEFT JOIN cmnequipment cme ON cme.Id=e.CmnEquipmentId LEFT JOIN crpspecializedtradeequipmentattachment a ON a.CrpSpecializedtradeEquipmentId=e.CmnEquipmentId WHERE e.CrpSpecializedTradeId=?

SpecializedDao.fetchHRdetails=SELECT c.Id id,c.CrpSpecializedTradeId specializedTradeId,c.CmnSalutationId salutationId,c.CIDNo cidNo,c.Name name,IF(c.Sex = 'M', 'Male', 'Female') sex,c.CmnCountryId countryId,c.CmnQualificationId qualificationId,c.CmnServiceTypeId serviceTypeId,c.CmnTradeId tradeId,c.CmnDesignationId designationId,c.ShowInCertificate siCertificate,c.IsPartnerOrOwner isPartnerOrOwner FROM crpspecializedtradehumanresource c WHERE c.CrpSpecializedTradeId = ?

SpecializedDao.fetchHRAttdetails=SELECT c.CrpSpecializedtradeHumanResourceId specializedtradeHumanResourceId,c.Id id,c.DocumentName documentName,c.DocumentPath documentPath,c.FileType fileType FROM crpspecializedtradehumanresourceattachment c WHERE c.CrpSpecializedtradeHumanResourceId = ?

SpecializedDao.fetchEQdetails=SELECT e.CmnEquipmentId equipmentId,e.Id id,e.RegistrationNo registrationNo,e.Quantity quantity,e.SerialNo serialNo,e.ModelNo modelNo,e.CrpSpecializedTradeId crpSpecializedTradeId FROM crpspecializedtradeequipment e WHERE e.CrpSpecializedTradeId = ?

SpecializedDao.fetchEQAttdetails=SELECT e.Id id,e.CrpSpecializedtradeEquipmentId equipmentId,e.DocumentName documentName,e.DocumentPath documentPath,e.FileType fileType FROM crpspecializedtradeequipmentattachment e WHERE e.CrpSpecializedtradeEquipmentId = ?

SpecializedDao.checkOwnerShipType=SELECT CmnOwnershipTypeId FROM crpspecializedtrade WHERE SPNo=?

/** Firm Queries here*/

SpecializedDao.getFirmFee=SELECT f.NewRegistrationFee registrationFee,f.FirstRenewalFee renewalFee,f.Name name,f.RegistrationValidity validaty FROM crpservicefeestructure f WHERE f.Name ='Specialized Trade Firm';

SpecializedDao.isFirmNameUnique=SELECT a.NameOfFirm FROM crpspecializedtrade a WHERE a.NameOfFirm =:firmName

SpecializedDao.isEmailUnique=SELECT a.Email FROM sysuser a WHERE a.Email =:email

SpecializedFirmActionDao.gTaskList=CALL ProCrpSpecializedFirmTaskList (:userId,:status,:service);

SpecializedDao.getSpecializedFirmOngoingApp=SELECT aa.CrpSpecializedTradeId specializedTradeId,aa.ReferenceNo AS referenceNo,aa.ApplicationDate AS applicationDate,aa.SPNo AS cdbNo,aa.Id AS appStatusId,bb.Name AS appStatusName FROM crpspecializedtrade aa INNER JOIN cmnlistitem bb ON aa.CmnApplicationRegistrationStatusId = bb.Id WHERE bb.Id IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND aa.SPNo = :cdbNo

SpecializedFirmActionDao.getSpecializedFirmHRs=SELECT hr.Id AS id,hr.CrpSpecializedTradeId specializedFirmID,hr.CIDNo cidNo,hr.Name AS name,hr.Sex sex,hr.ShowInCertificate AS siCertificate,hr.Verified AS verified,hr.Approved,hr.IsPartnerOrOwner isPartnerOrOwner,DATE_FORMAT(hr.JoiningDate, '%d-%m-%Y') joinDate,sa.Name salutationName,co.Name countryName,qu.Name qualificationName,st.Name serviceTypeName,td.Name tradeName,de.Name designationName FROM crpspecializedtradehumanresource hr \
INNER JOIN cmnlistitem sa ON sa.Id = hr.CmnSalutationId INNER JOIN cmncountry co ON co.Id = hr.CmnCountryId LEFT JOIN cmnlistitem qu ON qu.Id = hr.CmnQualificationId LEFT JOIN cmnlistitem st ON st.Id = hr.CmnServiceTypeId LEFT JOIN cmnlistitem td ON td.Id = hr.CmnTradeId INNER JOIN cmnlistitem de ON de.Id = hr.CmnDesignationId WHERE hr.CrpSpecializedTradeId = :specializedFirmId AND (:ownerOrPartner IS NULL OR hr.IsPartnerOrOwner = :ownerOrPartner)

SpecializedFirmActionDao.getHRAttachments=SELECT a.DocumentName documentName,DocumentPath documentPath, FileType fileType FROM crpspecializedtradehumanresourceattachment a WHERE a.CrpSpecializedtradeHumanResourceId = :hrId

SpecializedFirmActionDao.getEquipment=SELECT ce.Id id, ce.CrpSpecializedTradeId specializedFirmId, eq.Name equipmentName,ce.RegistrationNo registrationNo,ce.SerialNo serialNo,ce.Quantity quantity,ce.ModelNo modelNo ,ce.Verified AS verified, ce.Approved AS approved \
FROM crpspecializedtradeequipment ce LEFT JOIN cmnequipment  eq ON ce.CmnEquipmentId = eq.Id LEFT JOIN cmnspecializedtradecategory cl ON cl.Id = ce.CmnEquipmentId WHERE ce.CrpSpecializedTradeId =:specializedFirmId

SpecializedFirmActionDao.getEQAttachments=SELECT a.DocumentName documentName,DocumentPath documentPath, FileType fileType FROM crpspecializedtradeequipmentattachment a WHERE a.CrpSpecializedtradeEquipmentId  = :eqId

SpecializedFirmActionDao.getAppHistoryDtl=SELECT c.* , CASE v.FullName WHEN NULL THEN '(Citizen)' ELSE v.FullName END userName FROM ( SELECT 'Submitted' appStatus,c.CrpSpecializedTradeId specializedFirmId,c.CreatedBy userId,c.CreatedOn actionDate,''remarks FROM crpspecializedtrade c UNION ALL SELECT 'Verified',c.CrpSpecializedTradeId,c.SysVerifierUserId,c.VerifiedDate,c.RemarksByVerifier FROM crpspecializedtrade c UNION ALL SELECT 'Approved for Payment',c.CrpSpecializedTradeId,c.SysApproverUserId,c.RegistrationApprovedDate,c.RemarksByApprover FROM crpspecializedtrade c UNION ALL SELECT 'Rejected',c.CrpSpecializedTradeId,c.SysRejecterUserId,c.RejectedDate,c.RemarksByRejector FROM crpspecializedtrade c ) c LEFT JOIN sysuser v ON c.userId = v.Id WHERE c.specializedFirmId =:specializedFirmId AND c.actionDate IS NOT NULL

SpecializedFirmActionDao.getFeeCategoryClass=SELECT cc.Id id,cc.CmnCategoryId categoryId,CONCAT(wc.Code, '-', wc.Name) categoryName,wc.Code aClassId,cc.AppliedAmount AS aAmount,v.Code vClassName,cc.VerifiedAmount AS vAmount,ap.Code apClassName,cc.ApprovedAmount apAmount FROM crpspecializedtraderegistrationpayment cc INNER JOIN cmnspecializedtradecategory wc ON wc.Id = cc.CmnCategoryId LEFT JOIN crpspecializedtradeworkclassification a ON a.Id = cc.CmnAppliedCategoryId LEFT JOIN cmnspecializedtradecategory v ON v.Id = cc.CmnVerifiedCategoryId LEFT JOIN cmnspecializedtradecategory ap ON ap.Id = cc.CmnApprovedCategoryId WHERE cc.CrpSpecializedTradeFinalId =:specializedFirmId ORDER BY wc.Code ASC

SpecializedFirmActionDao.verify=CALL ProCrpSpecializedFirmApplicationVerify(:specializedFirmId,:vUserId,:vRemarks)

SpecializedFirmActionDao.approve=CALL ProCrpSpecializedFirmApplicationApprove(:specializedFirmId,:aUserId,:aRemarks)

SpecializedFirmActionDao.getNextCDBNo = SELECT a.SPNo AS cdbNo  FROM crpspecializedtradefinal a WHERE a.SPNo LIKE 'SF-%'ORDER BY CONVERT(a.SPNo, DECIMAL) DESC  LIMIT 1

SpecializedFirmActionDao.paymentUpdate=CALL ProCrpSpecializedFirmNewRegistrationFinalData(:specializedFirmId,:userId,:appStatusId,:createdBy)

specializedFirmActionDao.sendBack=UPDATE crpspecializedtrade c SET c.EditedBy =:userId, c.EditedOn = CURDATE(), c.SysLockedByUserId = NULL, c.RegistrationStatus = 7, c.CmnApplicationRegistrationStatusId =:applicationStatusId WHERE c.CrpSpecializedTradeId=:specializedFirmId

specializedFirmActionDao.reject=UPDATE crpspecializedtrade c SET c.SysRejecterUserId =:userId, c.RejectedDate = CURDATE(), c.RemarksByRejector = :remarks, c.RegistrationStatus = 3, c.CmnApplicationRegistrationStatusId =:applicationStatusId WHERE c.CrpSpecializedTradeId=:specializedFirmId

SpecializedFirmActionDao.getIncAttachment = SELECT DocumentName AS documentName, DocumentPath documentPath,FileType AS fileType FROM crpspecializedtradeattachment WHERE CrpSpecializedTradeId =:crpSpecializedTradeId

/*Renewal for specializedFirm*/

SpecializedFirmRCDao.getSpecializedFirmHRsFinal=SELECT hr.Id AS id,hr.CrpSpecializedTradeFinalId specializedFirmID,hr.CIDNo cidNo,hr.Name AS name,hr.Sex sex,hr.ShowInCertificate AS siCertificate,hr.DeleteRequest AS deleteRequest,hr.IsPartnerOrOwner isPartnerOrOwner,hr.JoiningDate joiningDate,sa.Name salutationName,co.Name countryName,qu.Name qualificationName,st.Name serviceTypeName,td.Name tradeName,de.Name designationName \
FROM crpspecializedtradehumanresourcefinal hr INNER JOIN cmnlistitem sa ON sa.Id = hr.CmnSalutationId INNER JOIN cmncountry co ON co.Id = hr.CmnCountryId LEFT JOIN cmnlistitem qu ON qu.Id = hr.CmnQualificationId LEFT JOIN cmnlistitem st ON st.Id = hr.CmnServiceTypeId LEFT JOIN cmnlistitem td ON td.Id = hr.CmnTradeId INNER JOIN cmnlistitem de ON de.Id = hr.CmnDesignationId WHERE hr.CrpSpecializedTradeFinalId = :specializedFirmId AND (:ownerOrPartner IS NULL OR hr.IsPartnerOrOwner = :ownerOrPartner)

SpecializedFirmRCDao.getHRAttachmentsFinal=SELECT a.Id AS id,a.DocumentName documentName,DocumentPath documentPath, FileType fileType FROM crpspecializedtradehumanresourceattachmentfinal a WHERE a.CrpSpecializedTradeHumanResourceFinalId = :hrId

SpecializedFirmRCDao.getEquipmentFinal=SELECT ce.Id id, eq.Name equipmentName,ce.RegistrationNo registrationNo,ce.SerialNo serialNo,ce.Quantity quantity,ce.ModelNo modelNo ,ce.DeleteRequest AS deleteRequest FROM crpspecializedtradeequipmentfinal ce INNER JOIN cmnequipment  eq ON ce.CmnEquipmentId = eq.Id WHERE ce.CrpSpecializedtradeFinalId =:specializedFirmId

SpecializedFirmRCDao.getEQAttachmentsFinal=SELECT a.Id AS id,a.DocumentName documentName,DocumentPath documentPath, FileType fileType FROM crpspecializedtradeequipmentattachmentfinal a WHERE a.CrpSpecializedtradeEquipmentFinalId  = :eqId

SpecializedFirmRCDao.getCategoryClassFinal=SELECT Id AS id,CrpSpecializedTradeFinalId AS specializedFirmId,CmnAppliedCategoryId AS categoryId,CmnVerifiedCategoryId AS aClassId,CmnApprovedCategoryId AS vClassId FROM crpspecializedtradeworkclassificationfinal WHERE CrpSpecializedTradeFinalId = :specializedFirmId ORDER BY CmnAppliedCategoryId

SpecializedFirmRCDao.getSpFirmStatus=SELECT bb.Id value,bb.Name text,bb.ReferenceNo obj1 FROM crpspecializedtradefinal aa INNER JOIN cmnlistitem bb ON aa.CmnApplicationRegistrationStatusId=bb.Id WHERE cdbNo = :cdbNo

SpecializedFirmRCDao.getServicesFee = SELECT Id,ReferenceNo, Name AS serviceName, ContractorAmount AS feeAmount  FROM `crpservice` WHERE ((:refNo is null and ReferenceNo IN (4,6,7,8,9,10,12)) or ReferenceNo = :refNo)

SpecializedFirmRCActionDao.getAppliedServices=SELECT a.ReferenceNo applicationNo,c.CmnServiceTypeId serviceId,d.referenceNo serviceRefNo, d.Name serviceName, e.PaymentAmount ,a.NameOfFirm firmName,b.Name AS appStatus,a.ApplicationDate applicationDate FROM crpspecializedtrade a  INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId INNER JOIN crpspecializedtradeappliedservice c ON c.CrpSpecializedTradeId = a.CrpSpecializedTradeId INNER JOIN crpservice d ON d.Id = c.CmnServiceTypeId INNER JOIN crpspecializedtradeservicepayment  e ON e.CrpSpecializedTradeId = c.CrpSpecializedTradeId AND e.CmnServiceTypeId = c.CmnServiceTypeId WHERE a.ReferenceNo = :applicationNo

SpecializedFirmRCActionDao.getDeleteHrRequest = SELECT hr.Id AS id,hr.CrpSpecializedTradeFinalId specializedFirmID,hr.CIDNo cidNo,hr.Name AS NAME, hr.Sex sex,hr.ShowInCertificate AS siCertificate, hr.DeleteRequest AS deleteRequest \
,hr.IsPartnerOrOwner isPartnerOrOwner,hr.JoiningDate joiningDate,sa.Name salutationName,co.Name countryName,qu.Name qualificationName,st.Name serviceTypeName,td.Name tradeName,de.Name designationName \
FROM crpspecializedtradehumanresourcefinal hr INNER JOIN cmnlistitem sa ON sa.Id = hr.CmnSalutationId INNER JOIN cmncountry co ON co.Id = hr.CmnCountryId LEFT JOIN cmnlistitem qu ON qu.Id = hr.CmnQualificationId \
LEFT JOIN cmnlistitem st ON st.Id = hr.CmnServiceTypeId LEFT JOIN cmnlistitem td ON td.Id = hr.CmnTradeId INNER JOIN cmnlistitem de ON de.Id = hr.CmnDesignationId WHERE hr.CrpSpecializedTradeFinalId =:specializedFirmId AND (:ownerOrPartner IS NULL OR hr.IsPartnerOrOwner =:ownerOrPartner)

SpecializedFirmRCActionDao.paymentUpdate= CALL ProCrpSpecializedFirmRenewalPaymentApproval(:specializedFirmId,:userId,:appStatusId,:createdBy)

SpecializedFirmRCActionDao.getProposedCategories = SELECT s.CrpSpecializedTradeId specializedFirmId,ss.Name serviceName,ss.ReferenceNo serviceRefNo,CONCAT(ca.Code,'-',ca.Name) categoryName,CONCAT(ex.Code,'-',ex.Name) exClassName,CONCAT(acl.Code,'-',acl.Name) aClassName,d.Amount aAmount,d.CmnCategoryId categoryId,d.CmnExistingClassificationId exClassId,d.CmnAppliedClassificationId aClassId FROM crpspecializedtradeservicepayment s INNER JOIN crpspecializedtradeservicepaymentdetail d ON s.Id = CrpSpecializedFirmServicePaymentId INNER JOIN crpspecializedtrade c ON s.CrpSpecializedTradeId = c.CrpSpecializedTradeId INNER JOIN crpservice ss ON ss.Id = s.CmnServiceTypeId LEFT JOIN cmnspecializedtradecategory ca ON d.CmnCategoryId = ca.Id LEFT JOIN cmnspecializedtradecategory ex ON d.CmnExistingClassificationId = ex.Id LEFT JOIN cmnspecializedtradecategory acl ON d.CmnAppliedClassificationId = acl.Id WHERE c.ReferenceNo = :appNo  AND d.CmnCategoryId IS NOT NULL

SpecializedFirmRCDao.saveDeleteHrRequest = Update crpspecializedtradehumanresourcefinal set DeleteRequest = 1 where Id =:hrId ;

SpecializedFirmRCDao.saveDeleteEqRequest=Update crpspecializedtradeequipmentfinal set DeleteRequest = 1 where Id =:eqId

SpecializedFirmRCDao.auditMemo=SELECT CONCAT('You have following audit memo:<br>',AIN,' : ',`AuditObservation`) AS auditObservation FROM `crpcontractorauditclearance` WHERE `CrpContractorConsultantId` =:specializedFirmFinalId AND  `Dropped` = '0';

SpecializedDao.getSpecializedtradeOngoingApp = SELECT f.SPNo cdbNo,i.Name updateStatus,f.ApplicationDate applicationDate,f.ReferenceNo ReferenceNo FROM crpspecializedtrade f LEFT JOIN cmnlistitem i ON i.Id=f.CmnApplicationRegistrationStatusId WHERE i.Id IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND f.SPNo=:cdbNo