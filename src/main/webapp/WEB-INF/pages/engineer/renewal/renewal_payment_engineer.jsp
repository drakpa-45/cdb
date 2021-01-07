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
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
                    <form action="#" id="engineerverificationForm">
                        <%--<form id="architectForm" action="#" method="post" enctype="multipart/form-data">--%>
                        <div class="card-header">
                            <h3 class="card-title font-weight-bold" style="color: #002752">Engineer >> Renewal >>
                                <security:authorize access="hasRole('ROLE_PAYMENT')">Payment Approver</security:authorize>
                            </h3>
                            <span class="font-weight-bold" style="font-size: small;color: #444444"> >> Application Number : ${appDetails.referenceNo} || CDB Number : ${appDetails.cdbNo}     Application Date: ${appDetails.applicationDate} </span>
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
                                                            <input type="hidden" readonly id="salutation" name="salutation" value="${appDetails.salutation}" class="form-control number">
                                                            <label class="col-lg-8 form-label form-control">${appDetails.salutation}</label>
                                                            <span id="salutation_err" class="text-danger"></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">CID Number:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" readonly id="cidNo" value="${appDetails.cidNo}" name="cidNo" class="form-control number">
                                                            <label class="col-lg-8 form-label form-control">${appDetails.cidNo}</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Name:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" readonly name="fullname" value="${appDetails.fullname}" maxlength="100" id="name" class="form-control">
                                                            <label class="col-lg-8 form-label form-control">${appDetails.fullname}</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Dzongkhag:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" readonly value="${appDetails.dzongkhagId}" maxlength="100" id="dzongkhag" class="form-control">
                                                            <label class="col-lg-8 form-label form-control">${appDetails.dzongkhagId}</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Gewog:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" value="${appDetails.gewog}" readonly maxlength="100" id="gewog" name="gewog" class="form-control">
                                                            <label class="col-lg-8 form-label form-control">${appDetails.gewog}</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Village:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" value="${appDetails.village}" readonly maxlength="100" id="village" name="village" class="form-control">
                                                            <label class="col-lg-8 form-label form-control">${appDetails.village}</label>
                                                            <input type="hidden"  name="villageId" id="villageId">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Email:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" readonly id="email" value="${appDetails.email}" name="email" class="form-control number">
                                                            <label class="col-lg-8 form-label form-control">${appDetails.email}</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Mobile Number:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" class=" form-control number" value="${appDetails.mobileNo}" readonly id="mobileNo" name="mobileNo" maxlength="8">
                                                            <label class="col-lg-8 form-label form-control">${appDetails.mobileNo}</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Trade:</label>
                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                            <input type="hidden" class=" form-control number" value="${appDetails.cmnTradeId}" readonly id="trade" name="trade" maxlength="8">
                                                            <label class="col-lg-8 form-label form-control">${appDetails.trade}</label>
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
                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Qualification:</label>
                                            <input type="hidden" class=" form-control number" value="${appDetails.qualificationId}" readonly name="ualification">
                                            <label class="col-lg-8 form-label form-control">${appDetails.qualificationId}</label>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Graduation Year:</label>
                                            <input type="hidden" class=" form-control number" value="${fn:substring(appDetails.graduationyr, 0, 4)}" readonly name="graduationYear">
                                            <label class="col-lg-8 form-label form-control">${fn:substring(appDetails.graduationyr, 0, 4)}</label>
                                        </div>
                                    </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">University:</label>
                                            <input type="hidden" class="form-control " name="universityName"value="${appDetails.universityName}" readonly>
                                            <label class="col-lg-8 form-label form-control">${appDetails.universityName}</label>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Country of University:</label>
                                            <input type="hidden" class=" form-control number" value="${appDetails.universityCountry}" readonly name="universityCountry">
                                            <label class="col-lg-8 form-label form-control">${appDetails.universityCountry}</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Service for:</label>
                                            <input type="hidden" value="030ace8e-24af-11e6-967f-9c2a70cc8e06" readonly maxlength="100" id="serviceTypeId" name="serviceTypeId" class="form-control">
                                            <label class="col-lg-8 form-label form-control">Engineer</label>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Type: </label>
                                            <input type="hidden" value="${appDetails.serviceSectorType}" readonly maxlength="100" id="service" name="serviceSectorType" class="form-control">
                                            <label class="col-lg-8 form-label form-control">${appDetails.serviceSectorType}</label>
                                            <input type="hidden" value="${appDetails.serviceSectorTypeId}" readonly maxlength="100" id="service" name="serviceSectorTypeId" class="form-control">
                                        </div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Office/Employer Name:</label>
                                            <input type="hidden" value="${appDetails.employeeName}" readonly maxlength="100" id="country" name="country" class="form-control">
                                            <label class="col-lg-8 form-label form-control">${appDetails.employeeName}</label>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Office/Employer Address:</label>
                                            <input type="hidden" value="${appDetails.employeeAddress}" readonly maxlength="100" id="country" name="country" class="form-control">
                                            <label class="col-lg-8 form-label form-control">${appDetails.employeeAddress}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" class=" form-control number" value="${appDetails.cdbNo}" readonly name="cdbNo">
                            <input type="hidden" value="${appDetails.referenceNo}" readonly id="referenceNo" name="referenceNo" class="form-control">
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
                                                    <div class="input-group margin-bottom-sm">
                                                        <span class="input-group-addon pr-5"><i class="fa fa-calendar"></i></span>
                                                        <input type="date" name="paymentReceiptDate" value="" id="paymentReceiptDate" class="form-control">
                                                    </div>
                                                    <span id="paymentReceiptDate_err" class="text-danger"></span>
                                                </div>
                                            </div>
                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                    <label>Total Number Of Late Days:</label>
                                                    <input type="number" class="form-control " name="noOfDaysLate" id="noOfDaysLate" value="${appDetails.noOfDaysLate}" readonly>
                                                </div>
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                    <label>No of Days after Grace Period:</label>
                                                    <input type="number" class="form-control " name="noOfDaysAfterGracePeriod" id="noOfDaysAfterGracePeriod" value="${appDetails.noOfDaysAfterGracePeriod}" readonly>
                                                </div>
                                            </div>
                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                    <label>Penalty Amount:</label>
                                                    <input type="text" class="form-control number" name="paymentAmt" id="paymentAmt" value="${appDetails.paymentAmt}" readonly>
                                                </div>
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                    <label>Renewal Amount:</label>
                                                    <c:if test="${appDetails.serviceSectorType=='Government'}">
                                                        <input type="text" class=" form-control number" value="0.00" readonly name="totalAmt">
                                                    </c:if>
                                                    <c:if test="${appDetails.serviceSectorType=='Private'}">
                                                        <input type="text" class=" form-control number" value="1000.0" readonly name="totalAmt">
                                                    </c:if>
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
                                                    <input type="text" class=" form-control number" value="${appDetails.totalAmt}" readonly id="totalAmt" name="totalAmt">
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
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                    <label>Expiry Date: </label>
                                                    <input type="text" value="${appDetails.regExpDate}" readonly id="regExpDate" class="form-control">
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
                                    <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6' || appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6' || appDetails.updateStatus=='262a3f11-adbd-11e4-99d7-080027dcfac6'}">
                                        <table class="table">
                                            <tbody>
                                            <tr>
                                                <td colspan="2" class="font-blue-madison bold warning">Applicant Details
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <table class="table table-condensed">
                                                        <tr>
                                                            <td><strong>Date of application:</strong></td>
                                                            <td>${appDetails.applicationDate}</td>
                                                        </tr>
                                                        <%--<tr>--%>
                                                            <%--<td><strong>Remarks: </strong></td>--%>
                                                            <%--<td>${appDetails.remarks}</td>--%>
                                                        <%--</tr>--%>
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
                                    <div class="col-md-6 table-responsive">
                                        <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6' || appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6'}">
                                            <table class="table">
                                                <tbody>
                                                <td colspan="1" class="font-blue-madison bold warning">Verifier
                                                    Information
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
                            <br><br><br>

                            <div class="form-group row pull-right">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                    <%--<button class="btn btn-warning" type="button" id="showrejectsection" onclick="updateReject()"><span class="fa fa-times"></span> Reject--%>
                                    <%--</button>--%>
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
<script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
<script src="<c:url value="/resources/js/cdb/engineer.js"/>"></script>
<script>

    function calculateTotalAmount(){
        // alert();
        var payamt = $('#paymentAmt').val();
        var renewamt = $('#renewalAmt').val();

        var totamt = parseFloat(payamt + renewamt);
        $('#totalAmt').val(totamt);
        $('#totalAmt').prop('readOnly', true);
    }

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
