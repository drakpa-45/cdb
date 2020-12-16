<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 4/19/2020
  Time: 9:33 AM
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<c:if test="${not empty res.status}">
    <div class="card" id="acknowledgment">
        <div class="card-header">

        </div>
        <div class="card-body">
            <c:if test="${res.status eq 1}">
                ${res.text}
            </c:if>
            <c:if test="${res.status eq 0}">
                <span class="error"> ${res.text}</span>
            </c:if>

        </div>
    </div>
</c:if>
<c:if test="${empty res.status}">
    <div class="mt-5">
        <div class="container mb-9">
            <div class="row">
                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                    <div class="page-header mb-0 mt-0 page-header">
                        <h1 class="page-title">
                            Construction Development Board
                        </h1>
                    </div>
                        <div class="form-group row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 alert alert-danger text-center"
                                 style="font-size: 10px">
                                <c:if test="${renewalCheck.status eq 1}">
                                    <span class="error">${renewalCheck.text}</span>
                                </c:if>
                                <c:if test="${renewalCheck.status eq 0}">
                                    <span class="error">${renewalCheck.text}</span>
                                </c:if>
                            </div>
                        </div>
                    <c:if test="${renewalCheck.status eq 1}">
                          <p class="text-justify">
                          <span id="message">While renewing your certificate, you can also upgrade/downgrade work classification or change other information. Relevant fees will be applicable.</span>
                         </p>
                    <div class="card" id="registrtaionFormCard">
                        <form action="#" id="engineerrenewalForm" method="post" enctype="multipart/form-data">
                            <div class="card-header">
                                <h3 class="card-title font-weight-bold">Renewal of Engineer</h3>
                                <span class="pl-9">CDB Number: ${registrationDetails.cdbNo}</span>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                        <div class="nav-tabs-custom">
                                            <ul class="m-0 nav nav-tabs">
                                                <li class="feesStructurerenewal tab-pane active">
                                                    <a href="#" class="border text-white" data-toggle="tab" data-placement="top">
                                                    <i class="fa fa-bookmark mr-1"></i>Fee Structure</a>
                                                </li>
                                                <li class="tab-pane personalInformation">
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
                                                <div class="tab-pane active feesStructurerenewal" id="feesStructure">
                                                    <div class="form-group ">
                                                        <table id="csa"
                                                               class="table table-striped table-bordered table-hover">
                                                            <thead>
                                                            <tr>
                                                                <th>Type</th>
                                                                <th>Validity (yrs)</th>
                                                                <th>Amount(Nu.)</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach var="fee" items="${fee_details}" varStatus="count">
                                                                <tr>
                                                                    <td>${fee.name}</td>
                                                                    <td>${fee.validaty}</td>
                                                                    <td>${fee.renewalFee}</td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                   <%-- <c:if test = "${fn:contains('Deregistered,Blacklisted,Revoked,Suspended', registrationDetails.updateStatus)}">
                                                        <div class="form-group">
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 alert alert-danger text-center">
                                                                You are not allow to avail this service as your certificate is <b> ${registrationDetails.updateStatus}</b>.
                                                                <% nextbtn="false2";%>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <div class="form-group" id="expdetails" style="display: none">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 alert alert-danger text-center">
                                                            Seems like your registration is already expired on <b>${registrationDetails.regExpDate}</b>.The total number of days late is <b><span id="totalDaysLate"></span></b> days.
                                                            However 30 days is considered as grace period which means the late fees that would be imposed within that period will be waived. Penalty amount is Nu. 100 per day.
                                                            <span id="noteforgraceperiod"></span>
                                                        </div>
                                                    </div>--%>
                                                    <hr />
                                                    <input type="hidden" id="expdate" value="${registrationDetails.regExpDate}">
                                                    <input type="hidden" name="noOfDaysLate" id="totalNoDaysLate1" value="${renewalCheck.dto1.noOfDaysLate}">
                                                    <input type="hidden" name="paymentAmt" id="totalpenaltyamount1" value="${renewalCheck.dto1.paymentAmount}">
                                                    <input type="hidden" name="noOfDaysAfterGracePeriod" id="noOfDaysAfterGracePeriod1" value="${renewalCheck.dto1.noOfDaysAfterGracePeriod}">

                                                    <div class="form-group row pull-right">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                            <button type="button"  onclick="nextTab('feesStructurerenewal','personalInformation')" class="btn btn-primary">
                                                                Next  <i class="fa fa-arrow-circle-right"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="tab-pane personalInformation" id="personalInformation">
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
                                                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Salutation<span class="text-danger">*</span>:</label>
                                                                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                                <form:select path="salutationList"  name="salutation" onclick="remove_err('salutation_err')" id="salutation" class="form-control">
                                                                                    <form:option value="${registrationDetails.salutationId}">${registrationDetails.salutation}</form:option>
                                                                                    <form:options items="${salutationList}" itemValue="value" itemLabel="text"></form:options>
                                                                                </form:select>
                                                                               <%-- <input type="text" id="salutation" name="salutation" class="form-control" value="${registrationDetails.salutation}" readonly>--%>
                                                                            </div>
                                                                        </div>
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">CID Number:</label>
                                                                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                                <input type="number" class="form-control" name="cidNo" value="${registrationDetails.cidNo}" readonly>
                                                                            </div>
                                                                        </div>
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Name:</label>
                                                                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                                <input type="text" value="${registrationDetails.fullname}" name="fullname" class="form-control" readonly>
                                                                            </div>
                                                                        </div>
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Dzongkhag:</label>
                                                                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                                <input type="hidden" maxlength="100" id="dzongkhagId" name="dzongkhagId" class="form-control"  value="${registrationDetails.cmnDzongkhagId}" readonly>
                                                                                <input type="text" maxlength="100" id="cmndzongkhag" class="form-control"  value="${registrationDetails.dzongkhagId}" readonly>
                                                                            </div>
                                                                        </div>
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Gewog:</label>
                                                                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                                <input type="text" maxlength="100" id="gewog" name="gewog" class="form-control" value="${registrationDetails.gewog}" readonly>
                                                                            </div>
                                                                        </div>
                                                                        <div class="form-group row">
                                                                            <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Village:</label>
                                                                            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                                <input type="text" maxlength="100" id="village" name="village" class="form-control" value="${registrationDetails.village}" readonly>
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
                                                        <div class="card tab2">
                                                            <div class="bg-blue card-status card-status-left"></div>
                                                            <div class="card-body">
                                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Country <span class="text-danger">*</span></label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                           <%-- <input type="text" maxlength="100" id="countryList" readonly name="countryList" class="form-control" value="${registrationDetails.countryId}">--%>
                                                                            <form:select path="countryList" name="countryId" id="countryId"  class="form-control">
                                                                                <form:options items="${countryList}"  itemValue="value" itemLabel="text"/>
                                                                            </form:select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Type <span class="text-danger">*</span></label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="text" maxlength="100" id="serviceSectorType" name="serviceSector" readonly class="form-control" value="${registrationDetails.serviceSectorType}">
                                                                            <input type="hidden" maxlength="100" id="serviceSectorType" name="serviceSectorType" class="form-control" value="${registrationDetails.serviceSectorTypeId}">
                                                                              <%-- <form:select path="typeList" name="serviceSectorType" onclick="remove_err('serviceSectorType_err')" id="serviceSectorType" class="form-control">
                                                                                   <form:option value="${registrationDetails.serviceSectorTypeId}">${registrationDetails.serviceSectorType}</form:option>
                                                                                   <form:options items="${typeList}" itemValue="value" itemLabel="text"></form:options>
                                                                               </form:select>--%>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12">Trade <span class="text-danger">*</span></label>
                                                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                                                                            <input type="text" maxlength="100" id="trade" name="cmnTradeId" readonly class="form-control" value="${registrationDetails.trade}">
                                                                            <input type="hidden" maxlength="100" id="cmnTradeId" name="trade" class="form-control" value="${registrationDetails.cmnTradeId}">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-12">

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <hr />
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
                                                                        <label>Email: <span class="text-danger">*</span></label>
                                                                        <input id="email" type="email" class="form-control" value="${registrationDetails.email}" name="email" readonly>
                                                                    </div>
                                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                        <label>Mobile No: <span class="text-danger">*</span></label>
                                                                        <input type="number" class="form-control number sp-character" value="${registrationDetails.mobileNo}" id="mobileNo" name="mobileNo" onKeyPress="if(this.value.length==8) return false;" min="0">
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-12">
                                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                        <label>Office/Employer Name:</label>
                                                                        <input type="text" class="form-control" name="employeeName"  value="${registrationDetails.employeeName}">
                                                                    </div>
                                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                        <label>Office/Employer Address:</label>
                                                                        <input type="text" class="form-control" name="employeeAddress"  value="${registrationDetails.employeeAddress}">
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
                                                                        <label>Qualification: <span class="text-danger">*</span></label>
                                                                        <form:select path="qualificationList" name="qualificationId" onclick="remove_err('qualificationId_err')" id="qualificationId" class="form-control">
                                                                            <form:option value="${registrationDetails.qualificationId}">${registrationDetails.qualification}</form:option>
                                                                            <form:options items="${qualificationList}" itemValue="value" itemLabel="text"></form:options>
                                                                        </form:select>
                                                                       <%-- <input type="text" class=" form-control number"  value="${registrationDetails.qualificationId}" name="qualificationId" readonly>--%>
                                                                    </div>
                                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                        <label>Year of graduation: <span class="text-danger">*</span></label>
                                                                        <%--<input type="number" class=" form-control number" value="${fn:substring(registrationDetails.graduationyr, 0, 4)}" id="graduationYear" name="graduationYear" onKeyPress="if(this.value.length==4) return false;" min="0">--%>
                                                                        <input type="number" class=" form-control number" value="${fn:substring(registrationDetails.graduationyr, 0, 4)}" id="graduationYear" name="graduationYear"  onKeyPress="if(this.value.length==4) return false;" min="0">
                                                                        <span id="graduationYear_err" class="text-danger"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                        <label>University: <span class="text-danger">*</span></label>
                                                                        <input type="text" class="form-control " name="universityName" value="${registrationDetails.universityName}" id="universityName">
                                                                    </div>
                                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                        <label>Country of University: <span class="text-danger">*</span></label>
                                                                        <form:select path="countryList"  name="universityCountry" onclick="remove_err('universityCountry_err')" id="universityCountry" class="form-control">
                                                                            <form:option value="${registrationDetails.cmnUniversityCountryId}">${registrationDetails.universityCountry}</form:option>
                                                                            <form:options items="${countryList}" itemValue="value" itemLabel="text"></form:options>
                                                                        </form:select>
                                                                       <%-- <input type="text" class="form-control " name="universityCountry" value="${registrationDetails.universityCountry}" id="universityCountry" readonly>--%>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <hr />
                                                        <div class="form-group row pull-right">
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                                <button type="button" onclick="previousTab('personalInformation','categoryDtls')"  class="btn btn-success">
                                                                    <i class="fa fa-arrow-left"></i>  Previous
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
                                                                        <label><b>Attach your supporting documents:<span class="text-danger">*</span></b>( Please click on add more document button to add more attachments)</label><br />
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
                                                                        <input type="file" name="files" id="file1" class="alert badge-danger" required="true" accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document' onchange="validateAttachment(this.value,'file1','filecheck1')">
                                                                    </div>
                                                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
                                                                        <button class="btn btn-success fa-pull-right" type="button" onclick="addmoreattachemnts()"><i class="fa fa-plus"> Add More Documents</i></button>
                                                                    </div>
                                                                </div>
                                                                <span id="fileadd"></span>
                                                                <br/>
                                                                <div class="form-froup">
                                                                    <div class="col-lg-12">
                                                                        <strong>Terms and Condition</strong>
                                                                        <div class="form-group">
                                                                            <div class="col-lg-12" id="">
                                                                                <ol>
                                                                                    <c:forEach var="att" items="${registrationDetails.terms}" varStatus="counter">
                                                                                        <li>${att.name}</li>
                                                                                    </c:forEach>
                                                                                </ol>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-lg-12"> I/We declare and confirm that: </label>
                                                                    <div id="termsAndCon">
                                                                        <ul>
                                                                            <li>
                                                                                All information and attachments with this application are true and correct;
                                                                            </li>
                                                                            <li>
                                                                                I am/we are aware that any false information provided herein will result in rejection of my application and suspension of any registered granted;
                                                                            </li>
                                                                            <li>I/We shall not make refund claims of expenditure incurred in processing this application;
                                                                            </li>
                                                                            <li>I/We have read and understood the 'Code of Ethics' and shall perform in line with Code of Ethics and any other legislation in force. Failure to comply, will be subject to the penalities provided for in the applicable legislation of the country.
                                                                            </li>
                                                                            <li>I/We hereby declare that issue of CDB certificate does not in anyway constitute an obligation on the part of CDB or any other Government agency to provide contract works.
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-lg-12">
                                                                        <span><input type="checkbox" id="submitcheckbox" onclick="enablesubmit()" name="tnc" class="required"  style="width:15px;height:15px;"></span>
                                                                        <span class="bold"> I/We agree to the above Terms & Conditions.</span>
                                                                    </label>
                                                                </div>
                                                                <input type="hidden" name="addedeqCount" id="addedeqCount">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <hr />
                                                    <div class="form-group row pull-right">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                            <input type="hidden" name="cdbNo" value="${registrationDetails.cdbNo}">
                                                            <input type="hidden" name="CrpEngineerId" value="${registrationDetails.crpEngineerId}">
                                                            <button type="button" onclick="previousTab('categoryDtls','saveAndPreview')"  class="btn btn-success">
                                                                <i class="fa fa-arrow-left"></i> Previous
                                                            </button>
                                                            <button type="button" disabled onclick="submitRegistrationForm()" class="btn btn-primary" id="submitbtn">
                                                                <i class="fa fa-save"></i> Submit
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                </c:if>
                                                <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"  class="modal in" id="concirmationModel">
                                                    <div class="modal-dialog modal-lg">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <span><b>Confirmation!</b></span>
                                                            </div>
                                                            <div class="modal-body form-horizontal">
                                                                <div class="alert alert-info">
                                                                    <div class="row">
                                                                        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                                                                            <span id="messages"></span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-success" onclick="SubmitApplicationDetials()">Yes</button>
                                                                <button type="button" class="btn btn-warning" onclick="closemodel('concirmationModel')"><span class="fa fa-times"></span> No</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
    <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
    <script src="<c:url value="/resources/js/cdb/engineer.js"/>"></script>
  <script>
        //checkvalidity('','${registrationDetails.serviceSectorType}');
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
        var applicationDate = (year + "/" + month + "/" + day);
        var date1 = new Date(year + "-" + month + "-" + day);
        var date2 = new Date('${registrationDetails.regExpDate}');

        var Difference_In_Time = date2.getTime() - date1.getTime();
        var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
        if(Difference_In_Days < 0 && '${registrationDetails.serviceSectorType}'=="Private"){
            $('#totalDaysLate').html(Math.ceil(Difference_In_Days*-1));
            if(Math.ceil(Difference_In_Days+30)<0){
                var totaldays=Math.ceil((Difference_In_Days+30)*-1);
                var totalpenalty=totaldays*100;
                if(totalpenalty>3000){
                    totalpenalty=3000;
                }
                $('#feenextbtn').hide();
                $('#totalNoDaysLate').val(totaldays);
                $('#totalpenaltyamount').val(totalpenalty);
                $('#noteforgraceperiod').html('Total number of days after grace period is <b>'+totaldays+"</b>. Total of Nu. "+totalpenalty+" will be imposed as penalty for late renewal of your cdb Certificate till today. However your penalty will be calculated till date of approval.");
            }
            else{
                $('#noteforgraceperiod').html('You are in Grace Period and no penalty is applied to you.');
            }
            $('#expdetails').show();
        }

    </script>
</body>
