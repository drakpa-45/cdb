SurveyDao.gFeeStructure = SELECT CAST(IF(f.`NewRegistrationFee` IS NULL,'0',f.`NewRegistrationFee`) AS UNSIGNED) registrationFee,f.`FirstRenewalFee` renewalFee,f.`Name` name,f.`RegistrationValidity` validaty FROM `crpservicefeestructure` f WHERE f.`Name` LIKE '%Survey%'

SurveyDao.getMaxId = SELECT  A.ReferenceNo FROM crpsurvey A ORDER BY A.ReferenceNo DESC LIMIT 1
SurveyDao.getTaskList=SELECT a.Name serviceName,a.ReferenceNo applicationNo,a.MobileNo contactNo,a.ApplicationDate applicationDate,a.CIDNo serviceNo,cm.Name serviceSectorType,b.Name AS appStatus FROM crpsurvey a INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId INNER JOIN crpsurveyappliedservice s ON s.CrpSurveyId=a.Id LEFT JOIN cmnlistitem cm ON cm.Id = a.CmnServiceSectorTypeId WHERE a.SysLockedByUserId IS NULL AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId=? ORDER BY a.ReferenceNo DESC;
SurveyDao.getMyTaskList=SELECT a.Name serviceName,a.ReferenceNo applicationNo,a.MobileNo contactNo,a.ApplicationDate applicationDate,a.CIDNo serviceNo,cm.Name serviceSectorType,b.Name AS appStatus FROM crpsurvey a INNER JOIN cmnlistitem b ON b.Id  = a.CmnApplicationRegistrationStatusId INNER JOIN crpsurveyappliedservice s ON s.CrpSurveyId=a.Id LEFT JOIN cmnlistitem cm ON cm.Id = a.CmnServiceSectorTypeId WHERE a.SysLockedByUserId=? AND s.CmnServiceTypeId=? AND a.CmnApplicationRegistrationStatusId=? ORDER BY a.ReferenceNo DESC;

SurveyDao.send2MyOrGroupTask = UPDATE crpsurvey a SET a.`SysLockedByUserId` =:lockUserId  WHERE a.ReferenceNo =:appNo

SurveyDao.getSurveyDtls=SELECT a.CmnApplicationRegistrationStatusId updateStatus,a.CrpSurveyId,a.ReferenceNo,a.CIDNo cidNo,t.Name trade,a.CmnTradeId cmnTradeId,a.Name fullname,i.Name salutation,d.NameEn dzongkhagId,a.Gewog gewog,a.Village village,c.Name countryId,ser.Name serviceSectorType,a.Email email,a.MobileNo mobileNo,a.EmployerName employeeName,a.EmployerAddress employeeAddress,q.Name qualificationId,a.GraduationYear graduationyr,a.NameOfUniversity universityName,uc.Name universityCountry,a.VerifiedDate verifcationdate,a.RemarksByVerifier verifierremarks,a.RegistrationApprovedDate approvaldate, a.RemarksByApprover approiverremarks,ss.Name serviceTypeId,a.ARNo cdbNo,a.RegistrationExpiryDate regExpDate,a.ApplicationDate applicationDate,a.CIDNo createdBy,o.FullName verifierUser,z.FullName approverUser,IF(css.NoOfDaysLate IS NULL, 0,  css.NoOfDaysLate) noOfDaysLate,IF(css.noOfDaysAfterGracePeriod IS NULL, 0,  css.noOfDaysAfterGracePeriod ) noOfDaysAfterGracePeriod, css.PaymentAmount paymentAmt,css.PenaltyPerDay penaltyPerDay,css.TotalAmount totalAmt FROM crpsurvey a LEFT JOIN sysuser o ON o.Id=a.SysVerifierUserId LEFT JOIN sysuser z ON z.Id = a.SysApproverUserId LEFT JOIN cmnlistitem i ON i.Id=a.CmnSalutationId LEFT JOIN cmndzongkhag d ON d.Id=a.CmnDzongkhagId LEFT JOIN cmncountry c ON c.Id=a.CmnCountryId LEFT JOIN cmnlistitem ser ON ser.Id=a.CmnServiceSectorTypeId LEFT JOIN cmnlistitem q ON q.Id=a.CmnQualificationId LEFT JOIN cmncountry uc ON uc.Id=a.CmnUniversityCountryId LEFT JOIN crpsurveyappliedservice s ON s.CrpSurveyId=a.Id LEFT JOIN crpservice ss ON ss.Id=s.CmnServiceTypeId LEFT JOIN cmnlistitem t ON t.Id = a.CmnTradeId LEFT JOIN  crpsurveyservicepayment css ON css.CrpSurveyId = a.CrpSurveyId WHERE a.ReferenceNo=?
SurveyDao.getSurveyDoc=SELECT d.Id id,d.DocumentName,d.DocumentPath,d.CreatedOn,d.FileType FROM crpsurveyattachment d WHERE d.CrpSurveyId=?

SurveyDao.getsurveyregDetails=SELECT ApplicationDate applicationDate,CmnApplicationRegistrationStatusId updateStatus, CrpSurveyId,ReferenceNo,CIDNo cidNo,NAME fullname,CmnSalutationId salutation,CmnDzongkhagId dzongkhagId, Gewog gewog,Village village,CmnCountryId countryId,CmnServiceSectorTypeId serviceSectorType,Email email,MobileNo mobileNo,EmployerName employeeName,EmployerAddress employeeAddress,CmnQualificationId qualificationId,GraduationYear graduationyr,NameOfUniversity universityName,CmnUniversityCountryId universityCountry,RegistrationApprovedDate approvaldate,RemarksByApprover approiverremarks,RegistrationExpiryDate regExpDate FROM crpsurvey WHERE ReferenceNo=?
SurveyDao.getPrintList=SELECT f.`ARNo` cdbNo,f.`ApplicationDate` applicationDate,f.`CIDNo` cidNo,f.`Email` email,f.`Name` fullname,f.`RegistrationApprovedDate` paymentReceiptDate,f.`MobileNo` mobileNo FROM crpsurveyfinal f WHERE f.`CmnApplicationRegistrationStatusId`=? GROUP BY f.Email

SurveyDao.isEmailUnique=SELECT a.Email FROM sysuser a WHERE a.Email =:email
/*Resubmission query here*/
SurveyDao.getDetails=SELECT a.CmnApplicationRegistrationStatusId updateStatus,a.CrpSurveyId,a.ReferenceNo,a.CIDNo cidNo,a.Name fullname,i.Name salutation,d.NameEn dzongkhagId,a.Gewog gewog,a.Village village,c.Name countryId,ser.Name serviceSectorType,a.Email email,a.MobileNo mobileNo,a.EmployerName employeeName,a.EmployerAddress employeeAddress,q.Name qualificationId,a.GraduationYear graduationyr,a.NameOfUniversity universityName,uc.Name universityCountry,a.VerifiedDate verifcationdate,a.RemarksByVerifier verifierremarks,a.RegistrationApprovedDate approvaldate,a.RemarksByApprover approiverremarks,ss.Name serviceTypeId,a.ARNo cdbNo,a.RegistrationExpiryDate regExpDate,a.ApplicationDate applicationDate,a.CmnServiceSectorTypeId cmnServiceSectorTypeId,a.CmnQualificationId cmnQualificationId,a.CmnUniversityCountryId cmnUniversityCountryId,a.CmnSalutationId cmnSalutationId,a.CmnDzongkhagId cmnDzongkhagId,a.CmnCountryId cmnCountryId FROM crpsurvey a LEFT JOIN cmnlistitem i ON i.Id = a.CmnSalutationId LEFT JOIN cmndzongkhag d ON d.Id = a.CmnDzongkhagId LEFT JOIN cmncountry c ON c.Id = a.CmnCountryId LEFT JOIN cmnlistitem ser ON ser.Id = a.CmnServiceSectorTypeId LEFT JOIN cmnlistitem q ON q.Id = a.CmnQualificationId LEFT JOIN cmncountry uc ON uc.Id = a.CmnUniversityCountryId LEFT JOIN crpsurveyappliedservice s ON s.CrpSurveyId = a.Id LEFT JOIN crpservice ss ON ss.Id = s.CmnServiceTypeId WHERE a.ReferenceNo = ?

SurveyorDao.getSurveyorOngoingApp = SELECT f.ARNo cdbNo,i.Name updateStatus,f.ApplicationDate applicationDate,f.ReferenceNo ReferenceNo FROM crpsurvey f LEFT JOIN cmnlistitem i ON i.Id=f.CmnApplicationRegistrationStatusId WHERE i.Id IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND f.ARNo=:cdbNo