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
                    <form action="#" id="surveyForm" enctype="multipart/form-data" >
                        <input type="hidden" id="referenceNo" value="${appDetails.referenceNo}" name="referenceNo"/>
                        <%--<form id="architectForm" action="#" method="post" enctype="multipart/form-data">--%>
                        <div class="card-header">
                            <h3 class="card-title font-weight-bold">Registration of Survey</h3>
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
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Salutation:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="hidden" readonly id="salutationid" name="salutation" value="${appDetails.cmnSalutationId}" class="form-control number">
                                                                            <input type="text" readonly id="salutation" value="${appDetails.salutation}" class="form-control number">
                                                                            <span id="salutation_err" class="text-danger"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Cid No:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly id="cidNo" value="${appDetails.cidNo}" name="cidNo" class="form-control number">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Name:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly  name="fullname" value="${appDetails.fullname}" maxlength="100" id="name" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Dzongkhag:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="hidden" readonly value="${appDetails.cmnDzongkhagId}" maxlength="100" id="dzongkhagid" name="dzongkhagId" class="form-control">
                                                                            <input type="text" readonly value="${appDetails.dzongkhagId}" maxlength="100" id="dzongkhag" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Gewog:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" value="${appDetails.gewog}" readonly maxlength="100" id="gewog" name="gewog" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Village:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" value="${appDetails.village}" readonly maxlength="100" id="village" name="village" class="form-control">
                                                                            <input type="hidden"  name="villageId" id="villageId">
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
                                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Service for:</label>
                                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <select class="form-control"  name="serviceTypeId">
                                                                            <option value="030ace8e-24af-11e6-967f-9c2a70cc8e06">Surveyor</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Country:</label>
                                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <input type="hidden" value="${appDetails.cmnCountryId}" readonly maxlength="100" id="counid" name="countryId" class="form-control">
                                                                        <input type="text" value="${appDetails.countryId}" readonly maxlength="100" id="country" class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Type: </label>
                                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <input type="hidden" value="${appDetails.cmnServiceSectorTypeId}" readonly maxlength="100" id="serviceid" name="serviceSectorType" class="form-control">
                                                                        <input type="text" value="${appDetails.serviceSectorType}" readonly maxlength="100" id="service" class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr />

                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="nextTab('personalInformation','categoryDtls')"  class="btn btn-primary">
                                                            Next  <i class="fa fa-arrow-right"></i>
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
                                                                    <input id="email" type="email" value="${appDetails.email}" class="form-control" name="email" placeholder="Type valid email">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Mobile No:</label>
                                                                    <input type="text" class=" form-control number" value="${appDetails.mobileNo}" id="mobileNo" name="mobileNo"  maxlength="8">
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Office/Employer Name:</label>
                                                                    <input type="text" class="form-control" value="${appDetails.employeeName}" name="employeeName" placeholder="Employer Name">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Office/Employer Address:</label>
                                                                    <textarea class="form-control" name="employeeAddress" maxlength="50" placeholder="Office/Employer Address">
                                                                        ${appDetails.employeeAddress}
                                                                    </textarea>
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
                                                                    <label>Qualification:</label>
                                                                    <input type="hidden" class=" form-control number" value="${appDetails.cmnQualificationId}" readonly name="qualificationId">
                                                                    <input type="text" class=" form-control number" value="${appDetails.qualificationId}" readonly>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Year of graduation:</label>
                                                                    <input type="text" class=" form-control number" value="${fn:substring(appDetails.graduationyr, 0, 4)}" readonly name="graduationYear" >
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>University:</label>
                                                                   <%-- <input type="hidden" value="${appDetails.cmnUniversityCountryId}" readonly maxlength="100" id="countryid" name="universityName" class="form-control">--%>
                                                                    <input type="text" class="form-control " value="${appDetails.universityName}" name="universityName" readonly>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Country of unversity:</label>
                                                                    <%--<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <form:select path="countryList" name="countryId" id="countryId"  class="form-control">
                                                                            <form:options items="${countryList}"  itemValue="value" itemLabel="text"/>
                                                                        </form:select>
                                                                    </div>--%>
                                                                    <input type="hidden" class=" form-control number" value="${appDetails.cmnUniversityCountryId}" readonly name="universityCountry">
                                                                    <input type="text" class=" form-control number" value="${appDetails.universityCountry}" readonly>
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
                                                                Next  <i class="fa fa-arrow-right"></i>
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
                                                                    <label><b>Attach your supoting documents:<span class="text-danger">*</span></b>( Please click on add more document button to add more attachments)</label><br />
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
                                                                    <input type="file" name="files" id="file1" class="alert badge-danger" onchange="validateAttachment(this.value,'file1','filecheck1')">
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
                                                                            I am/we are aware that any false information provided herein will result in rejection of my application and suspension of any registered granted;
                                                                        </li>
                                                                        <li>I/We shall not make refund claims of expenditure incurred in processing this application;
                                                                        </li>
                                                                        <li>I/We have read and understood the 'Code of Ethics' and shall perform in line with Code of Ethics and any other legislation in force. Failure to comply, will be subject to the penalities provided for in the applicable legislation of the country.
                                                                        </li>
                                                                        <li>I/We hereby declare that issue of CDB certificate does not in anyway constitute an obligation on the part of CDB or any other Goverment agency to provide contract works.
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-lg-12">
                                                                    <span><input type="checkbox" id="submitcheckbox" onclick="enablesubmit()" name="tnc" class="required"  style="width:15px;height:15px;"></span>
                                                                    <span class="bold"> I agree to the above Terms Conditions</span>
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
                        </div>
                        <jsp:include page="/WEB-INF/pages/architect/confirmationModal.jsp"/>
                    </form>
                    <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
                    <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
                    <script src="<c:url value="/resources/js/cdb/survey.js"/>"></script>
                </div>

            </div>
        </div>
    </div>
</div>
<script>

</script>
</body>
