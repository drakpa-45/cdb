<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 3/29/2020
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<body>
<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-12">
                <div class="page-header mb-0 mt-0 page-header">
                    <h1 class="page-title">
                        Construction Development Board
                    </h1>
                </div>
                <!-- tab open -->
                <div class="card" id="registrtaionFormCard">
                    <form action="#" id="surveyverificationForm">
                        <%--<form id="architectForm" action="#" method="post" enctype="multipart/form-data">--%>
                        <div class="card-header">
                            <h3 class="card-title font-weight-bold" style="color: #002752">Surveyor >> Renewal >>
                                <security:authorize access="hasRole('ROLE_VERIFIER')">Verification/</security:authorize>
                                <security:authorize access="hasRole('ROLE_APPROVER')">Approval</security:authorize>
                            </h3> <input type="hidden" readonly id="referenceNo" value="${appDetails.referenceNo}" name="referenceNo" class="form-control number">
                            <span class="font-weight-bold" style="font-size: small;color: #444444"> >> Application Number : ${appDetails.referenceNo} || CDB Number : ${appDetails.cdbNo}     Application Date: ${appDetails.applicationDate} </span>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="nav-tabs-custom">
                                        <ul class="m-0 nav nav-tabs">
                                            <li class="tab-pane personalInformation active">
                                                <a href="#" class=" border" data-toggle="tab" data-placement="top">
                                                    <i class="fa fa-users mr-1"></i>Personal Information</a>
                                            </li>
                                            <li class="tab-pane categoryDtls">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top">
                                                    <i class="fa fa-mobile mr-1"></i>Contact Details</a>
                                            </li>
                                            <li class="tab-pane saveAndPreview">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top">
                                                    <i class="fa fa-file mr-1"></i>Attachments</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content border p-3 col-lg-12">
                                            <div class="tab-pane active personalInformation" id="personalInformation">
                                                <div class="">
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Personal Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12 col-lg-md col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Salutation:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" readonly id="salutation" name="salutation" value="${appDetails.salutation}" class="form-control number">
                                                                            <label class="col-lg-8 form-label form-control">${appDetails.salutation}</label>
                                                                            <span id="salutation_err" class="text-danger"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">CID Number:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" readonly id="cidNo" value="${appDetails.cidNo}" name="cidNo" class="form-control number">
                                                                            <label class="col-lg-8 form-label form-control">${appDetails.cidNo}</label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Name:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" readonly name="fullname" value="${appDetails.fullname}" maxlength="100" id="name" class="form-control">
                                                                            <label class="col-lg-8 form-label form-control">${appDetails.fullname}</label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Dzongkhag:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" readonly value="${appDetails.dzongkhagId}" maxlength="100" id="dzongkhag" class="form-control">
                                                                            <label class="col-lg-8 form-label form-control">${appDetails.dzongkhagId}</label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Gewog:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" value="${appDetails.gewog}" readonly maxlength="100" id="gewog" name="gewog" class="form-control">
                                                                            <label class="col-lg-8 form-label form-control">${appDetails.gewog}</label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Village:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" value="${appDetails.village}" readonly maxlength="100" id="village" name="village" class="form-control">
                                                                            <label class="col-lg-8 form-label form-control">${appDetails.village}</label>
                                                                            <input type="hidden" name="villageId" id="villageId">
                                                                        </div>
                                                                    </div>
                                                                    <input type='button'  value='Check for this CID' class='checkCid btn btn-success'>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <img src='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo=${appDetails.cidNo}' width='200px' height='200px' class='pull-right'/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Box Open -->
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Service for:</label>
                                                                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                        <input type="hidden" value="030ace8e-24af-11e6-967f-9c2a70cc8e06" readonly maxlength="100" id="serviceTypeId" name="serviceTypeId" class="form-control">
                                                                        <label class="col-lg-8 form-label form-control">Surveyor</label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Country:</label>
                                                                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                        <input type="hidden" value="${appDetails.countryId}" readonly maxlength="100" id="country" name="country" class="form-control">
                                                                        <label class="col-lg-8 form-label form-control">${appDetails.countryId}</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Type: </label>
                                                                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                        <input type="hidden" value="${appDetails.serviceSectorType}" readonly maxlength="100" id="service" name="serviceSectorType" class="form-control">
                                                                        <label class="col-lg-8 form-label form-control">${appDetails.serviceSectorType}</label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Trade: </label>
                                                                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                        <input type="hidden" value="${appDetails.trade}" readonly maxlength="100" id="trade" name="trade" class="form-control">
                                                                        <label class="col-lg-8 form-label form-control">${appDetails.trade}</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="nextTab('personalInformation','categoryDtls')" class="btn btn-primary">
                                                            Next <i class="fa fa-arrow-circle-right"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane categoryDtls" id="contact_detail">
                                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 form-group">
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Contact Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Email:</label>
                                                                    <input id="email" type="hidden" value="${appDetails.email}" readonly class="form-control" name="email" placeholder="Type valid email">
                                                                    <label class="col-lg-8 form-label form-control">${appDetails.email}</label>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Mobile Number:</label>
                                                                    <input type="hidden" class=" form-control number" value="${appDetails.mobileNo}" readonly id="mobileNo" name="mobileNo" maxlength="8">
                                                                    <label class="col-lg-8 form-label form-control">${appDetails.mobileNo}</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Office/Employer Name:</label>
                                                                    <input type="hidden" class="form-control" value="${appDetails.employeeName}" readonly name="employeeName" placeholder="Employer Name">
                                                                    <label class="col-lg-8 form-label form-control">${appDetails.employeeName}</label>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Office/Employer Address:</label>
                                                                    <input type="hidden" class="form-control" value="${appDetails.employeeAddress}" readonly name="employeeAddress" placeholder="Employer employeeAddress">
                                                                    <label class="col-lg-8 form-label form-control">${appDetails.employeeAddress}</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Qualification Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Qualification:</label>
                                                                    <input type="hidden" class=" form-control number" value="${appDetails.qualificationId}" readonly name="ualification">
                                                                    <label class="col-lg-8 form-label form-control">${appDetails.qualificationId}</label>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Year of Graduation:</label>
                                                                    <input type="hidden" class=" form-control number" value="${fn:substring(appDetails.graduationyr, 0, 4)}" readonly name="graduationYear">
                                                                    <label class="col-lg-8 form-label form-control">${fn:substring(appDetails.graduationyr, 0, 4)}</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">University:</label>
                                                                    <input type="hidden" class="form-control " name="universityName" value="${appDetails.universityName}" readonly>
                                                                    <label class="col-lg-8 form-label form-control">${appDetails.universityName}</label>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Country of University:</label>
                                                                    <input type="hidden" class=" form-control number" value="${appDetails.universityCountry}" readonly name="graduationYear">
                                                                    <label class="col-lg-8 form-label form-control">${appDetails.universityCountry}</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <hr/>
                                                    <div class="form-group row pull-right">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                            <button type="button" onclick="previousTab('personalInformation','categoryDtls')" class="btn btn-success">
                                                                <i class="fa fa-arrow-circle-left"></i> Previous
                                                            </button>
                                                            <button type="button" onclick="nextTab('categoryDtls','saveAndPreview')" class="btn btn-primary">
                                                                Next <i class="fa fa-arrow-circle-right"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane saveAndPreview" id="saveAndPreview">
                                                <div id="submitSection" style="">
                                                    <div class="panel panel-default">
                                                        <div class="panel-body">
                                                            <div class="form-group row">
                                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                    <label><b>Documents:</b></label>
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                    <div class="table-responsive">
                                                                        <table id="attachment"
                                                                               class="table table-bordered table-hover">
                                                                            <thead>
                                                                            <tr>
                                                                                <td>Sl No#</td>
                                                                                <td>Attachment</td>
                                                                                <td colspan="2">Action</td>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                            <c:forEach var="att" items="${appDetails.surveyDocuments}" varStatus="counter">
                                                                                <option value="${dzo.header_id}_${dzo.department}_${dzo.type}">${dzo.header_name}</option>
                                                                                <tr>
                                                                                    <td>${counter.index+1}</td>
                                                                                    <td>${att.documentName}</td>
                                                                                    <td>
                                                                                        <button class="btn btn-primary" type="button" onclick="viewAttachment('${att.id}','view')" target="_blank"><i class="fa fa-eye"></i>View</button>
                                                                                    </td>
                                                                                    <td>
                                                                                        <button class="btn btn-primary" type="button" onclick="viewAttachment('${att.id}','download')">
                                                                                            <i class="fa fa-download"></i>Download
                                                                                        </button>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <input type="hidden" class=" form-control number" value="${appDetails.cdbNo}" readonly name="cdbNo">
                                                            <c:if test="${appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6'}">
                                                                <div class="card tab2">
                                                                    <div class="bg-blue card-status card-status-left"></div>
                                                                    <div class="card-header">
                                                                        <h3 class="card-title">Payment Details</h3>
                                                                    </div>
                                                                    <div class="card-body">
                                                                        <div id="paymentForm">
                                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                    <label>Cdb Number:</label>
                                                                                    <input type="text" class=" form-control number" value="${appDetails.cdbNo}" readonly name="cdbNotodisplay">
                                                                                </div>
                                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                    <label>Payment Receipt Date:</label>
                                                                                    <div class="input-group margin-bottom-sm">
                                                                                        <span class="input-group-addon pr-5"><i class="fa fa-calendar"></i></span>
                                                                                        <input type="date" name="paymentReceiptDate" value="" id="paymentReceiptDate" class="form-control datepicker">
                                                                                    </div>
                                                                                    <span id="paymentReceiptDate_err" class="text-danger"></span>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                    <label>Total Number Of Late Days:</label>
                                                                                    <input type="number" class="form-control " name="noOfDaysLate" id="noOfDaysLate" value="0" readonly>
                                                                                </div>
                                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                    <label>No of Days after Grace Period:</label>
                                                                                    <input type="number" class="form-control " name="noOfDaysAfterGracePeriod" id="noOfDaysAfterGracePeriod" value="0" readonly>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                    <label>Penalty Amount:</label>
                                                                                    <input type="number" class="form-control " name="panalty" id="panalty" value="0" readonly>
                                                                                </div>
                                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                    <label>Renewal Amount:</label>
                                                                                    <input type="text" class=" form-control number" value="2000.0" readonly name="paymentAmt" id="paymentAmt">
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                    <label>Receipt Number:</label>
                                                                                    <input type="text" onclick="remove_err('paymentReceiptNo_err')" class="form-control " name="paymentReceiptNo" id="paymentReceiptNo">
                                                                                    <span id="paymentReceiptNo_err" class="text-danger"></span>
                                                                                </div>
                                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                    <label>Total Amount:</label>
                                                                                    <input type="text" class=" form-control number" value="2000.0" readonly id="totalAmt" name="totalAmt">
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                    <label>Mode of Payment:</label>
                                                                                    <select name="paymentmode" class="chosen-select form-control" onchange="checkForApplicable(this.value)" id="paymentmode" required>
                                                                                        <option value="">--Select--</option>
                                                                                        <c:forEach var="plist" items="${modeOfPayment}" varStatus="counter">
                                                                                            <option value="${plist.name}">${plist.name}</option>
                                                                                        </c:forEach>
                                                                                    </select>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                            <input type="hidden" id="currentStatus" value="${appDetails.updateStatus}">
                                                            <%--${appDetails.updateStatus}--%>
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                <div class="table-responsive">
                                                                    <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6' || appDetails.updateStatus=='262a3f11-adbd-11e4-99d7-080027dcfac6'}">
                                                                        <table class="table">
                                                                            <tbody>
                                                                            <tr>
                                                                                <td colspan="2" class="font-blue-madison bold warning">Applicant Details</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table class="table table-condensed">
                                                                                        <tr>
                                                                                            <td><strong>Date of application:</strong></td>
                                                                                            <td>${appDetails.applicationDate}</td>
                                                                                        </tr>
                                                                                            <%-- <tr>
                                                                                                 <td>
                                                                                                     <strong>Remarks: </strong>
                                                                                                 </td>
                                                                                                 <td>${appDetails.remarks}</td>
                                                                                             </tr>--%>
                                                                                        <tr>
                                                                                            <td><strong>Submitted By: </strong></td>
                                                                                            <td>${appDetails.createdBy} <b style="color: blueviolet">(${appDetails.fullname})</b></td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </c:if>
                                                                    <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6'}">
                                                                        <table class="table">
                                                                            <tbody>
                                                                            <td colspan="1" class="font-blue-madison bold warning">
                                                                                Verifier Information
                                                                            </td>
                                                                            <tr>
                                                                                <td>
                                                                                    <table class="table table-condensed">
                                                                                        <tr>
                                                                                            <td><strong>Date of Verification:</strong></td>
                                                                                            <td>${appDetails.applicationDate}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><strong>Verifier Remarks: </strong></td>
                                                                                            <td>${appDetails.verifierremarks}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><strong>Verified By: </strong></td>
                                                                                            <td>${appDetails.verifierUser}</td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                                                                <label class="form-label">Your Remarks:</label>
                                                                <textarea class="form-control" onclick="remove_err('remarks_err')" name="remarks" id="remarks"></textarea>
                                                                <span id="remarks_err" class="text-danger"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr/>
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="previousTab('categoryDtls','saveAndPreview')" class="btn btn-success">
                                                            <i class="fa fa-arrow-circle-left"></i> Previous
                                                        </button>
                                                        <button class="btn btn-warning" type="button" id="showrejectsection" onclick="updateReject()"><span class="fa fa-times"></span> Reject
                                                        </button>
                                                        <c:if test="${appDetails.updateStatus=='262a3f11-adbd-11e4-99d7-080027dcfac6'}">
                                                            <button type="button" onclick="verifyApplication()" class="btn btn-primary">
                                                                <i class="fa fa-save"></i> Verify
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6'}">
                                                            <button type="button" onclick="approveApplication('renewal')" class="btn btn-primary">
                                                                <i class="fa fa-save"></i> Approve
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6'}">
                                                            <button type="button" onclick="approveAndGenerateCertificate('renewal')" class="btn btn-primary">
                                                                <i class="fa fa-save"></i> Approve & Update Certificate
                                                            </button>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <jsp:include page="/WEB-INF/pages/architect/confirmationModal.jsp"/>
                    </form>
                    <%--HR Modal--%>
                    <div id="hrModal" class="modal fade in " tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" style=" max-width: 900px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 id="myModalLabel" class="modal-title"><i class="fa fa-info-circle fa-lg"></i> Personal Check<span id="cid"></span></h4>
                                </div>
                                <div class="modal-body">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div id="modal-print">
                                                <div class="form-group">
                                                    <p align="center"><strong><u>Caution</u></strong></p>
                                                    <p align="center"><strong>An surveyor is allowed to execute only two work at
                                                        a time for that particular firm.</strong></p>
                                                    <p align="center"><strong>For any other Human Resource they are allowed to
                                                        involve only in a single project of work</strong></p>
                                                    <p align="center"><font size="5px;">Details of CID No: <span id="cidchecked"></span></font></p>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-9 mt-8">
                                                        <span class=""><b>From DCRC database</b></span>
                                                        <div class="col-lg-12 form-group mb-0 pt-4">
                                                            <label class="col-lg-3 form-label">Sex</label>
                                                            <label class="col-lg-8 form-label" id="sexM"></label>
                                                        </div>
                                                        <div class="col-lg-12 form-group mb-0">
                                                            <label class="col-lg-3 form-label">Name</label>
                                                            <label class="col-lg-8 form-label" id="nameM"></label>
                                                        </div>
                                                        <div class="col-lg-12 form-group mb-0">
                                                            <label class="col-lg-3 form-label">Dzongkhag</label>
                                                            <label class="col-lg-8 form-label" id="dzongkhagM"></label>
                                                        </div>
                                                        <div class="col-lg-12 form-group mb-0">
                                                            <label class="col-lg-3 form-label">Gewog</label>
                                                            <label class="col-lg-8 form-label" id="gewogM"></label>
                                                        </div>
                                                        <div class="col-lg-12 form-group mb-0">
                                                            <label class="col-lg-3 form-label">Village</label>
                                                            <label class="col-lg-8 form-label" id="villageM"></label>
                                                        </div>
                                                        <div class="col-lg-12 form-group mb-0">
                                                            <label class="col-lg-3 form-label">DOB</label>
                                                            <label class="col-lg-8 form-label" id="dobM"></label>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3" align="center">
                                                        <br><br><span id="photoM"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-12" align="center">
                                                        <div class="tab-pane employeeDetails" style="display: none" id="engagedId">
                                                            <div class="form-group">
                                                                <div class="col-lg-12" align="center">
                                                                    <p ><span id="dcbinfo"><b>The Individual holding CID/permit no.<label id="cidNumber"></label>(<label id="hrName"></label>) is engaged in following project(s):</b></span></p>
                                                                    <p><span id="dcbinfonotEngaged"></span></p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <p align="center">With regard to Corporate Employee please verify with the
                                                        concern agencies. There may be certain inconsistency.</p>
                                                    <hr>
                                                    <p align="center"><strong>Print this page as an evidence to prove that
                                                        particular HR is engaged or not in a work or project</strong></p>
                                                    <p align="center">
                                                        Printed on:  <%=new Date()%>
                                                        By: ${auth.fullName}
                                                    </p>
                                                </div>
                                            </div>
                                            <button type="button" class="btn btn-primary" onclick="javascript:printDiv('modal-print')">Print</button>
                                            <button type="button" class="btn btn-success" id="closeModal" onclick="checkBtn('checkver1')" data-dismiss="modal">OK</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
                    <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
                    <script src="<c:url value="/resources/js/cdb/survey.js"/>"></script>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var day = currentTime.getDate();
    if (day < 10) {
        day = "0" + day;
    }
    var year = currentTime.getFullYear();
    var date1 = new Date(year + "-" + month + "-" + day);
    var date2 = new Date('${appDetails.regExpDate}');
    if ($('#currentStatus').val() == "6195664d-c3c5-11e4-af9f-080027dcfac6") {//approved for payment
        date1 = new Date($('#approvalDate').val());
    }
    var Difference_In_Time = date2.getTime() - date1.getTime();
    var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
    if (Difference_In_Days < 0 && '${appDetails.serviceSectorType}' == "Private") {
        $('#noOfDaysLate').val(Math.ceil(Difference_In_Days * -1));
        if (Math.ceil(Difference_In_Days + 30) < 0) {
            var totaldays = Math.ceil((Difference_In_Days + 30) * -1);
            var totalpenalty = totaldays * 100;
            if (totalpenalty > 3000) {
                totalpenalty = 3000;
            }
            $('#noOfDaysAfterGracePeriod').val(totaldays);
            $('#panalty').val(totalpenalty);
            $('#totalAmt').val(totalpenalty + 2000);
        }
        else {
            $('#noteforgraceperiod').html('You are in Grace Period and no penalty is applied to you.');
        }
        $('#expdetails').show();
    }
</script>
</body>
