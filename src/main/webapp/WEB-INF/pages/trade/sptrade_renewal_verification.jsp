<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 3/29/2020
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
                    <form action="#" id="tradeverificationForm" >
                        <%--<form id="architectForm" action="#" method="post" enctype="multipart/form-data">--%>
                            <div class="card-header">
                                <h3 class="card-title font-weight-bold" style="color: #002752">Specialized Trade>> Renewal >>
                                    <security:authorize access="hasRole('ROLE_VERIFIER')">Verification/</security:authorize>
                                    <security:authorize access="hasRole('ROLE_APPROVER')">Approval</security:authorize>
                                </h3><input type="hidden" readonly id="referenceNo" value="${appDetails.referenceNo}" name="referenceNo" class="form-control number">
                                <span style="font-size: small;color: #444444"> >> Application Number : ${appDetails.referenceNo} || CDB Number : ${appDetails.cdbNo}     Application Date: ${appDetails.applicationDate} </span>
                            </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="nav-tabs-custom">
                                        <ul class="m-0 nav nav-tabs">
                                            <%--<li class="feesStructure tab-pane ">
                                                <a href="#" class="border text-white" data-toggle="tab" data-placement="top">
                                                    <i class="fa fa-bookmark mr-1"></i>Fee Structure</a>
                                            </li>--%>
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
                                                                            <input type="hidden" readonly id="salutation" name="salutation" value="${appDetails.salutation}" class="form-control number">${appDetails.salutation}
                                                                            <span id="salutation_err" class="text-danger"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">CID Number:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" readonly id="cidNo" value="${appDetails.cidNo}" name="cidNo" class="form-control number">${appDetails.cidNo}
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Name:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" readonly name="fullname" value="${appDetails.fullname}" maxlength="100" id="name" class="form-control">${appDetails.fullname}
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Dzongkhag:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" readonly value="${appDetails.dzongkhagId}" maxlength="100" id="dzongkhag" class="form-control">${appDetails.dzongkhagId}
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Gewog:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" value="${appDetails.gewog}" readonly maxlength="100" id="gewog" name="gewog" class="form-control">${appDetails.gewog}
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Village:</label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="hidden" value="${appDetails.village}" readonly maxlength="100" id="village" name="village" class="form-control">${appDetails.village}
                                                                            <input type="hidden" name="villageId" id="villageId">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <img src='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo=${appDetails.cidNo}'  width='200px'  height='200px' class='pull-right'/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Box Open -->

                                                </div>
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="nextTab('personalInformation','categoryDtls')"  class="btn btn-primary">
                                                            Next  <i class="fa fa-arrow-circle-right"></i>
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
                                                                    <label>Email:</label>
                                                                    <input id="email" type="hidden" value="${appDetails.email}" readonly class="form-control" name="email" placeholder="Type valid email">${appDetails.email}
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Mobile No:</label>
                                                                    <input type="hidden" class=" form-control number" value="${appDetails.mobileNo}" readonly id="mobileNo" name="mobileNo"  maxlength="8">${appDetails.mobileNo}
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Telephone Number:</label>
                                                                    <input id="telephoneNo" type="hidden" value="${appDetails.telephoneNo}" readonly class="form-control" name="telephoneNo"
                                                                           placeholder="provide your telephone number">${appDetails.telephoneNo}
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>TPN No:</label>
                                                                    <input type="hidden" class=" form-control number" value="${appDetails.tpn}" readonly id="tpn" name="tpn">${appDetails.tpn}
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Office/Employer Name:</label>
                                                                    <input type="hidden" class="form-control" value="${appDetails.employeeName}" readonly name="employeeName" placeholder="Employer Name">${appDetails.employeeName}
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Office/Employer Address:</label>
                                                                    <input type="hidden" class="form-control" value="${appDetails.employeeAddress}" readonly name="employeeAddress" placeholder="Employer employeeAddress">${appDetails.employeeAddress}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <hr />
                                                    <div class="form-group row pull-right">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                            <button type="button" onclick="previousTab('personalInformation','categoryDtls')"  class="btn btn-success">
                                                                <i class="fa fa-arrow-circle-left"></i>  Previous
                                                            </button>
                                                            <button type="button" onclick="nextTab('categoryDtls','saveAndPreview')"  class="btn btn-primary">
                                                                Next  <i class="fa fa-arrow-circle-right"></i>
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
                                                                        <table id="attachment" class="table table-bordered table-hover">
                                                                            <thead>
                                                                            <tr>
                                                                                <td>Sl No#</td>
                                                                                <td>Attachment</td>
                                                                                <td colspan="2">Action</td>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                            <c:forEach var="att" items="${appDetails.doc}" varStatus="counter">
                                                                                <option value="${dzo.header_id}_${dzo.department}_${dzo.type}">${dzo.header_name}</option>
                                                                                <tr>
                                                                                    <td>${counter.index+1}</td>
                                                                                    <td>${att.documentName}</td>
                                                                                    <td>  <button class="btn btn-primary" type="button" onclick="viewAttachment('${att.id}','view')" target="_blank"><i class="fa fa-eye"></i> View </button> </td>
                                                                                    <td>  <button class="btn btn-primary" type="button" onclick="viewAttachment('${att.id}','download')"><i class="fa fa-download"></i> Download </button> </td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                    <label><b>Category Details:</b></label><br />
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                    <div class="table-responsive">
                                                                        <table id="category" class="table table-bordered table-hover">
                                                                            <thead style="background-color: #F2F2F2">
                                                                            <tr>
                                                                                <th></th>
                                                                                <th>Category</th>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                            <c:forEach items="${appDetails.terms}" var="category" varStatus="i">
                                                                                <tr>
                                                                                        <%--${category.arrayId}--%>
                                                                                        <%--${i.index}--%>
                                                                                    <td><input class="form-control categoryCheck" type="checkbox"
                                                                                               name="itemId"  value="${category.appliedCategoryId}"
                                                                                               style="width: 17px; height: 17px;"disabled checked></td>
                                                                                    <td>${category.code}${category.name}</td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <input type="hidden" id="currentStatus" value="${appDetails.updateStatus}">
                                                            <%--${appDetails.updateStatus}--%>
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                <div class="table-responsive">
                                                                    <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6' || appDetails.updateStatus=='262a3f11-adbd-11e4-99d7-080027dcfac6'}">
                                                                        <table class="table">
                                                                            <tbody>
                                                                            <tr>
                                                                                <td colspan="2"
                                                                                    class="font-blue-madison bold warning">Applicant Details
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <table class="table table-condensed">
                                                                                        <tr>
                                                                                            <td><strong>Date of application:</strong>
                                                                                            </td>
                                                                                            <td>${appDetails.applicationDate}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td>
                                                                                                <strong>Submitted By: </strong>
                                                                                            </td>
                                                                                            <td>${appDetails.createdBy}<b style="color: blueviolet">(${appDetails.fullname})</b></td>
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
                                                                                            <td><strong>Date of
                                                                                                Verification:</strong>
                                                                                            </td>
                                                                                            <td>${appDetails.applicationDate}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><strong>Verifier
                                                                                                Remarks: </strong></td>
                                                                                            <td>${appDetails.verifierremarks}</td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><strong>Verified
                                                                                                By: </strong></td>
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
                                                            <input type="hidden" id="serviceSectorType" value="${appDetails.serviceTypeId}" name="serviceSectorType"/>
                                                            <input type="hidden" class=" form-control number" value="${appDetails.cdbNo}" readonly name="cdbNo">
                                                           <%-- <input type="hidden" class=" form-control number" value="${appDetails.id}" readonly name="id">--%>
                                                            <input type="hidden" class=" form-control number" value="${appDetails.crpSpecializedTradeId}" readonly name="crpSpecializedTradeId">
                                                            <c:if test="${appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6'}">
                                                                <div class="card tab2">
                                                                    <div class="bg-blue card-status card-status-left"></div>
                                                                    <div class="card-header">
                                                                        <h3 class="card-title">Payment Details</h3>
                                                                    </div>
                                                                    <div class="card-body">
                                                                        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                <label>Cdb Number:</label>
                                                                                <input type="text" class=" form-control number" value="${appDetails.cdbNo}" readonly name="cdbNotodisplay">
                                                                            </div>
                                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                                <label>Payment Receipt Date:</label>
                                                                                <div class="input-group margin-bottom-sm">
                                                                                <span class="input-group-addon pr-5"><i
                                                                                        class="fa fa-calendar"></i></span>
                                                                                    <input type="date" name="paymentReceiptDate"
                                                                                           value="" id="paymentReceiptDate"
                                                                                           class="form-control datepicker">
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
                                                                                <label>Reneal Amount:</label>
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
                                                                                <select name="paymentmode"
                                                                                        class="chosen-select form-control"
                                                                                        id="paymentmode" required>
                                                                                    <option value="">--Select--</option>
                                                                                    <c:forEach var="plist" items="${modeOfPayment}" varStatus="counter">
                                                                                        <option value="${plist.arrayId}">${plist.name}</option>
                                                                                    </c:forEach>
                                                                                </select>
                                                                                <span id="paymentmode_err" class="text-danger"></span>
                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                            <%--${appDetails.updateStatus}--%>
                                                            <div class="row pt-4">
                                                                <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                                                                    <label class="form-label">Your Remarks:</label>
                                                                    <textarea class="form-control" onclick="remove_err('remarks_err')" name="remarks" id="remarks"></textarea>
                                                                    <span id="remarks_err" class="text-danger"></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr />
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="previousTab('categoryDtls','saveAndPreview')"  class="btn btn-success">
                                                            <i class="fa fa-arrow-circle-left"></i>  Previous
                                                        </button>
                                                        <button class="btn btn-warning" type="button" id="showrejectsection" onclick="updateReject()"><span class="fa fa-times"></span> Reject </button>
                                                        <c:if test="${appDetails.updateStatus=='262a3f11-adbd-11e4-99d7-080027dcfac6'}">
                                                            <button type="button" onclick="verifyApplication()"  class="btn btn-primary">
                                                                <i class="fa fa-save"></i> Verify
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6'}">
                                                            <button type="button" onclick="approveApplication('renewal')"  class="btn btn-primary">
                                                                <i class="fa fa-save"></i> Approve
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6'}">
                                                            <button type="button" onclick="approveAndGenerateCertificate('renewal')"  class="btn btn-primary">
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
                    <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
                    <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
                    <script src="<c:url value="/resources/js/cdb/specialized.js"/>"></script>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
    if(month<10){
        month="0"+month;
    }
    var day = currentTime.getDate();
    if(day<10){
        day="0"+day;
    }
    var year = currentTime.getFullYear();
    var date1 = new Date(year + "-" + month + "-" + day);
    var date2 = new Date('${appDetails.regExpDate}');
    if($('#currentStatus').val()=="6195664d-c3c5-11e4-af9f-080027dcfac6"){//approved for payment
        date1 = new Date($('#approvalDate').val());
    }
    var Difference_In_Time = date2.getTime() - date1.getTime();
    var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
    if(Difference_In_Days < 0 && '${appDetails.serviceSectorType}'=="Private"){
        $('#noOfDaysLate').val(Math.ceil(Difference_In_Days*-1));
        if(Math.ceil(Difference_In_Days+30)<0){
            var totaldays=Math.ceil((Difference_In_Days+30)*-1);
            var totalpenalty=totaldays*100;
            if(totalpenalty>3000){
                totalpenalty=3000;
            }
            $('#noOfDaysAfterGracePeriod').val(totaldays);
            $('#panalty').val(totalpenalty);
            $('#totalAmt').val(totalpenalty+2000);
        }
        else{
            $('#noteforgraceperiod').html('You are in Grace Period and no penalty is applied to you.');
        }
        $('#expdetails').show();
    }
</script>
</body>
