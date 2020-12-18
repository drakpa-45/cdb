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
                <div class="card" id="acknowledgementCard" style="display: none">
                    <div class="card-header">
                        <h3 class="card-title font-weight-bold">Application Acknowledgement</h3>
                    </div>
                    <div class="card-body" style="">
                        <div class="form-group">
                            <div class="col-lg-12">
                                This application is verified and forwarded for approval,.<br>
                                <p>Click on tasklist button to go back to tasklist.</p>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            Thanks You.
                            <div class="col-lg-12 mt-3">
                                <input type="button" class="btn btn-primary" id="btnLogin1" onclick="window.print();" value="Print">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card" id="registrtaionFormCard">
                    <form action="#" id="surveyverificationForm">
                        <input type="hidden" id="referenceNo" value="${appDetails.referenceNo}" name="referenceNo"/>
                        <%--<form id="architectForm" action="#" method="post" enctype="multipart/form-data">--%>
                        <div class="card-header">
                            <h3 class="card-title font-weight-bold" style="color: #002752">Surveyor >> New Registration >>
                                <%-- <security:authorize access="hasRole('ROLE_VERIFIER')">Verification</security:authorize>
                                            <security:authorize access="hasRole('ROLE_APPROVER')">Approval</security:authorize> --%>
                                <c:if test="${appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6'}">Payment Approver</c:if>
                            </h3>
                            <span class="font-weight-bold" style="font-size: small;color: #444444"> >> Application Number : ${appDetails.referenceNo}</span>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="card tab2">
                                        <div class="bg-blue card-status card-status-left"></div>
                                        <div class="card-header">
                                            <h3 class="card-title">Personal Details</h3>
                                        </div>
                                        <div class="card-body">
                                            <div class="col-lg-12 col-lg-md col-sm-12 col-xs-12">
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Salutation:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" readonly id="salutation" name="salutation" value="${appDetails.salutation}" class="form-control number">${appDetails.salutation}
                                                            <span id="salutation_err" class="text-danger"></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">CID Number:</label>
                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                        <input type="hidden" readonly id="cidNo" value="${appDetails.cidNo}" name="cidNo" class="form-control number">${appDetails.cidNo}
                                                    </div>
                                                </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Name:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" readonly name="fullname" value="${appDetails.fullname}" maxlength="100" id="name" class="form-control">${appDetails.fullname}
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Dzongkhag:</label>
                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                            <input type="hidden" readonly value="${appDetails.dzongkhagId}" maxlength="100" id="dzongkhag" class="form-control">${appDetails.dzongkhagId}
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Gewog:</label>
                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                            <input type="hidden" value="${appDetails.gewog}" readonly maxlength="100" id="gewog" name="gewog" class="form-control">${appDetails.gewog}
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Village:</label>
                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                            <input type="hidden" value="${appDetails.village}" readonly maxlength="100" id="village" name="village" class="form-control">${appDetails.village}
                                                            <input type="hidden"  name="villageId" id="villageId">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Email:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" readonly id="email" value="${appDetails.email}" name="email" class="form-control number">${appDetails.email}
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Mobile No:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" class=" form-control number" value="${appDetails.mobileNo}" readonly id="mobileNo" name="mobileNo" maxlength="8">${appDetails.mobileNo}
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                    <img src='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo=${appDetails.cidNo}' width='200px' height='200px' class='pull-right'/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card tab2">
                                <div class="bg-blue card-status card-status-left"></div>
                                <div class="card-header">
                                    <h3 class="card-title">Qualification & other details</h3>
                                </div>
                                <div class="card-body">
                                    <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label>Qualification:</label>
                                            <input type="hidden" class=" form-control number" value="${appDetails.qualificationId}" readonly name="ualification">${appDetails.qualificationId}
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label>Year of graduation:</label>
                                            <input type="hidden" class=" form-control number" value="${fn:substring(appDetails.graduationyr, 0, 4)}" readonly name="graduationYear" >${fn:substring(appDetails.graduationyr, 0, 4)}
                                        </div>
                                    </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label>University:</label>
                                            <input type="hidden" class="form-control " name="universityName"value="${appDetails.universityName}" readonly>${appDetails.universityName}
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label>Country of University:</label>
                                            <input type="hidden" class=" form-control number" value="${appDetails.universityCountry}" readonly name="universityCountry">${appDetails.universityCountry}
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label>Service for:</label>
                                                <input type="hidden" value="030ace8e-24af-11e6-967f-9c2a70cc8e06" readonly maxlength="100" id="serviceTypeId" name="serviceTypeId" class="form-control">Surveyor
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label>Type: </label>
                                                <input type="hidden" value="${appDetails.serviceSectorType}" readonly maxlength="100" id="service" name="serviceSectorType" class="form-control">${appDetails.serviceSectorType}
                                        </div>
                                    </div>

                                    <div class="col-lg-12">
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label>Office/Employer Address:</label>
                                                <input type="hidden" value="${appDetails.employeeAddress}" readonly maxlength="100" id="country" name="country" class="form-control">${appDetails.employeeAddress}
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label>Office/Employer Name:</label>
                                                <input type="hidden" value="${appDetails.employeeName}" readonly maxlength="100" id="country" name="country" class="form-control">${appDetails.employeeName}
                                        </div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label>Trade:</label>
                                            <input type="hidden" value="${appDetails.cmnTradeId}" readonly maxlength="100" id="trade" name="trade" class="form-control">${appDetails.trade}
                                        </div>
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
                                                            <label>CDB Number:</label>
                                                            <input type="text" class=" form-control number" value="${appDetails.cdbNo}" readonly name="cdbNotodisplay">
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                            <label>Payment Receipt Date:</label>
                                                           <%-- <input type="text" onclick="remove_err('paymentReceiptDate_err')" class=" form-control datepicker"
                                                                   readonly name="paymentReceiptDate" id="paymentReceiptDate">--%>
                                                            <div class="input-group margin-bottom-sm">
                                                                                <span class="input-group-addon pr-5"><i
                                                                                        class="fa fa-calendar"></i></span>
                                                                <input type="date" name="paymentReceiptDate"
                                                                       value="" id="paymentReceiptDate"
                                                                       class="form-control">
                                                            </div>
                                                            <span id="paymentReceiptDate_err" class="text-danger"></span>
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
                                                            <c:if test="${appDetails.serviceSectorType=='Government'}">
                                                                <input type="text" class=" form-control number" value="0.00" readonly name="totalAmt">
                                                            </c:if>
                                                            <c:if test="${appDetails.serviceSectorType=='Private'}">
                                                                <input type="text" class=" form-control number" value="2000.0" readonly name="totalAmt">
                                                            </c:if>
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
                                    <%--${appDetails.updateStatus}--%>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="table-responsive">
                                    <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6' || appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6' || appDetails.updateStatus=='262a3f11-adbd-11e4-99d7-080027dcfac6'}">
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
                                                        <tr>
                                                            <td><strong>Submitted By: </strong></td>
                                                            <td>${appDetails.createdBy}<b style="color: blueviolet">(${appDetails.fullname})</b></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </c:if>
                                    <div class="col-md-6 table-responsive">
                                        <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6' || appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6'}">
                                            <table class="table">
                                                <tbody>
                                                <td colspan="1" class="font-blue-madison bold warning">Verifier Information</td>
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
                                    <div class="col-md-6 table-responsive">
                                        <table class="table">
                                            <c:if test="${appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6'}">
                                            <tbody>
                                            <td colspan="1" class="font-blue-madison bold warning">Approver Information</td>
                                            <tr>
                                                <td colspan="1">
                                                    <table class="table table-condensed">
                                                        <tr>
                                                            <td><strong>Date of Approval:</strong></td>
                                                            <td>${appDetails.approvaldate}</td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Approval Remarks: </strong></td>
                                                            <td>${appDetails.approiverremarks}</td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Approved By: </strong></td>
                                                            <td>${appDetails.approverUser}</td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                                        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                                            <label class="form-label">Your Remarks:</label>
                                            <textarea class="form-control" onclick="remove_err('remarks_err')" name="remarks" id="remarks"></textarea>
                                            <span id="remarks_err" class="text-danger"></span>
                                        </div>
                                <br>
                            <div class="form-group row pull-right">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                   <%-- <button class="btn btn-warning" type="button" id="showrejectsection" onclick="updateReject()"><span class="fa fa-times"></span> Reject
                                    </button>--%>
                                    <c:if test="${appDetails.updateStatus=='262a3f11-adbd-11e4-99d7-080027dcfac6'}">
                                        <button type="button" onclick="previousTab('categoryDtls','saveAndPreview')" class="btn btn-success">
                                            <i class="fa fa-arrow-circle-left"></i> Previous
                                        </button>
                                        <button type="button" onclick="verifyApplication()" class="btn btn-primary">
                                            <i class="fa fa-save"></i> Verify
                                        </button>
                                    </c:if>
                                    <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6'}">
                                        <button type="button" onclick="previousTab('categoryDtls','saveAndPreview')" class="btn btn-success">
                                            <i class="fa fa-arrow-circle-left"></i> Previous
                                        </button>
                                        <button type="button" onclick="approveApplication('registration')" class="btn btn-primary">
                                            <i class="fa fa-save"></i> Approve
                                        </button>
                                    </c:if>
                                    <c:if test="${appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6'}">
                                        <button type="button" onclick="approveAndGenerateCertificate('registration')" class="btn btn-primary">
                                            <i class="fa fa-save"></i> Approve & Generate Certificate
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
    <script src="<c:url value="/resources/js/cdb/survey.js"/>"></script>
</div>
<script>

</script>
</body>
