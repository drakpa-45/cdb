<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>
<%--
  Created by IntelliJ IDEA.
  User: Dechen Wangdi
  Date: 12/17/2019
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <div class="page-header mb-0 mt-0 page-header">
                    <h1 class="page-title">
                        Construction Development Board
                    </h1>
                </div>

                <div class="card" id="registrtaionFormCard">
                    <form action="#" id="surveyForm" method="post" enctype="multipart/form-data">
                    <%--<form id="architectForm" action="#" method="post" enctype="multipart/form-data">--%>
                        <div class="card-header">
                            <h3 class="card-title font-weight-bold">Registration of Survey</h3>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="nav-tabs-custom">
                                        <ul class="m-0 nav nav-tabs">
                                            <li class="feesStructure tab-pane active">
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
                                            <div class="tab-pane active feesStructure" id="feesStructure">
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
                                                                    <td>${fee.registrationFee}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
                                                        <label>Enter your valid CID/work permit Number:<span class="text-danger">*</span></label>
                                                        <input type="text" onclick="remove_err('app_çid_err')" name="app_çid" class="form-control number" id="app_çid">
                                                        <span id="app_çid_err" class="text-danger"></span>
                                                    </div>
                                                </div>
                                                <hr />
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button"  onclick="nextTab('feesStructure','personalInformation')" class="btn btn-primary">
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
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Salutation<span class="text-danger">*</span>:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <form:select path="salutationList"  name="salutation" onclick="remove_err('salutation_err')" id="salutation" class="form-control">
                                                                                <form:option value="">Select</form:option>
                                                                                <form:options items="${salutationList}" itemValue="value" itemLabel="text"></form:options>
                                                                            </form:select>
                                                                            <span id="salutation_err" class="text-danger"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">CID Number:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly id="cidNo" name="cidNo" class="form-control number">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Name:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly  name="fullname" maxlength="100" id="name" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Dzongkhag:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly maxlength="100" id="dzongkhag" class="form-control">
                                                                            <input type="hidden"  name="dzongkhagId" id="dzongkhagId">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Gewog:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly maxlength="100" id="gewog" name="gewog" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Village:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly maxlength="100" id="village" name="village" class="form-control">
                                                                            <input type="hidden"  name="villageId" id="villageId">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <span id="imageId"></span>
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
                                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Service for <span class="text-danger">*</span></label>
                                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <select class="form-control"  name="serviceTypeId">
                                                                            <option value="030ace8e-24af-11e6-967f-9c2a70cc8e06">Surveyor</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Country <span class="text-danger">*</span></label>
                                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <form:select path="countryList" name="countryId" id="countryId"  class="form-control">
                                                                            <form:options items="${countryList}"  itemValue="value" itemLabel="text"/>
                                                                        </form:select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Type <span class="text-danger">*</span></label>
                                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <form:select path="typeList" name="serviceSectorType" onclick="remove_err('serviceSectorType_err')" id="serviceSectorType" class="form-control">
                                                                            <form:option value="">Select</form:option>
                                                                            <form:options items="${typeList}" itemValue="value" itemLabel="text"></form:options>
                                                                        </form:select>
                                                                        <span id="serviceSectorType_err" class="text-danger"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Trade <span class="text-danger">*</span></label>
                                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <form:select path="tradeList" name="trade" id="trade" onclick="remove_err('trade_err')" class="form-control">
                                                                            <form:option value="">Select</form:option>
                                                                            <form:options items="${tradeList}" itemValue="value" itemLabel="text"></form:options>
                                                                        </form:select>
                                                                        <span id="trade_err" class="text-danger"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr />
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="previousTab('feesStructure','personalInformation')"  class="btn btn-success">
                                                            <i class="fa fa-arrow-circle-left"></i>  Previous
                                                        </button>
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
                                                                    <input  type="email" class="form-control"id="regEmail" onchange="isMailUnique(this.value)" required="true" name="email" placeholder="Type valid email">
                                                                    <span id="email_err" class="text-danger"></span>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Mobile Number: <span class="text-danger">*</span></label>
                                                                    <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                                                    <span id="mobileNo_err" class="text-danger"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Office/Employer Name</label>
                                                                    <input type="text" class="form-control" name="employeeName" placeholder="Employer Name">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Office/Employer Address</label>
                                                                    <textarea class="form-control" name="employeeAddress" placeholder="Office/Employer Address"></textarea>
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
                                                                    <label>Qualification <span class="text-danger">*</span></label>
                                                                    <form:select path="qualificationList" name="qualificationId" onclick="remove_err('qualificationId_err')" id="qualificationId" class="form-control">
                                                                        <form:option value="">Select</form:option>
                                                                        <form:options items="${qualificationList}" itemValue="value" itemLabel="text"></form:options>
                                                                    </form:select>
                                                                    <span id="qualificationId_err" class="text-danger"></span>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Year of Graduation <span class="text-danger">*</span></label>
                                                                    <input type="number" class=" form-control number" maxlength="4" onclick="remove_err('graduationYear_err')" id="graduationYear" name="graduationYear" placeholder="year..">
                                                                    <span id="graduationYear_err" class="text-danger"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>University: <span class="text-danger">*</span></label>
                                                                    <input type="text" class="form-control " name="universityName" onclick="remove_err('universityName_err')" id="universityName" placeholder="university name">
                                                                    <span id="universityName_err" class="text-danger"></span>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Country of University: <span class="text-danger">*</span></label>
                                                                    <form:select path="countryList"  name="universityCountry" onclick="remove_err('universityCountry_err')" id="universityCountry" class="form-control">
                                                                        <form:option value="">Select</form:option>
                                                                        <form:options items="${countryList}" itemValue="value" itemLabel="text"></form:options>
                                                                    </form:select>
                                                                    <span id="universityCountry_err" class="text-danger"></span>
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
                                                                    <label><b>Attach your supporting documents:<span class="text-danger">*</span></b>( Please click on add more document button to add more attachments)</label><br />
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
                                                                    <input type="file" name="files" id="file1" class="alert badge-danger" accept="application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document" required onchange="validateAttachment(this.value,'file1','filecheck1')">
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
                                                                            <c:forEach var="att" items="${undertaking}" varStatus="counter">
                                                                                <li>${att.name}</li>
                                                                            </c:forEach>
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
                                                                            I am/We are aware that any false information provided herein shall result in rejection of my application and suspension of any registration granted;
                                                                        </li>
                                                                        <li>I/We shall not make refund claims of expenditure incurred in processing this application;
                                                                        </li>
                                                                        <li>I/We have read and understood the legal instruments issued by CDB (Manuals, Guidelines, Codes etc.)  and shall perform in line with these instruments and any other laws in force.
                                                                        </li>
                                                                        <li>I/We understand that the failure to comply with aforementioned legal instruments  will be subject to the penalties provided for in these instruments and applicable laws in force; and
                                                                        </li>
                                                                        <li>I/We hereby declare that issuance of CDB certificate does not in any way constitute an obligation on the part of CDB or any other government agencies to provide contract works.</li>
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
                                                        <button type="button" onclick="previousTab('categoryDtls','saveAndPreview')"  class="btn btn-success">
                                                            <i class="fa fa-arrow-left"></i>  Previous
                                                        </button>
                                                        <button type="button" onclick="submitRegistrationForm()"  class="btn btn-primary" id="submitbtn">
                                                            <i class="fa fa-save"></i> Submit
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                    </form>
                    <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
                    <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
                    <script src="<c:url value="/resources/js/cdb/survey.js"/>"></script>
                   <%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>--%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>




