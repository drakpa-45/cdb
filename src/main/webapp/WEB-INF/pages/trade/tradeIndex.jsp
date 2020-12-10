<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <div class="page-header mb-0 mt-0 page-header">
                    <h1 class="page-title">Construction Development Board</h1>
                </div>
                <div class="card" id="registrtaionFormCard">
                    <form action="#" id="specializedTradeForm" method="post" enctype="multipart/form-data">
                        <%--<form id="architectForm" action="#" method="post" enctype="multipart/form-data">--%>
                        <div class="card-header">
                            <h3 class="card-title font-weight-bold">Registration of Specialized Trade</h3>
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
                                                    <i class="fa fa-mobile mr-1"></i>Category Details</a>
                                            </li>
                                            <li class="tab-pane saveAndPreview">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top">
                                                    <i class="fa fa-file mr-1"></i>Attachments</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content border p-3 col-lg-12">

                                            <div class="tab-pane active feesStructure" id="feesStructure" >
                                                <div class="form-group" id="individualFeestructure">
                                                    <table id="csa" class="table table-striped table-bordered table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th>Validity (yrs)</th>
                                                            <th>Registration Fees(Nu.)</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach var="fee" items="${fee_details}" varStatus="count">
                                                            <tr>
                                                                <td>${fee.validaty}</td>
                                                                <td>${fee.registrationFee}</td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="form-group row" id="individualcondition">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <label>The validity of the registration certificate will be for
                                                            3 years and the registration fee is not applicable for first
                                                            time applicant. However, the first renewal fee is Nu. 1000/-
                                                            and Nu. 500/- thereafter.</label>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
                                                        <label>Enter your valid CID Number:<span class="text-danger">*</span></label>
                                                        <input type="number" name="app_çid" class="form-control number" onchange="checkStatus(this.value)" id="app_çid" min="0" onkeypress="return preventDot(event);"/>
                                                        <span id="app_çid_err" class="text-danger"></span>
                                                    </div>
                                                </div>
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
                                                    <div id="pInfoSpId">
                                                        <jsp:include page="pInfoSp.jsp"/>
                                                    </div>
                                                </div>
                                                <hr/>
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="previousTab('feesStructure','personalInformation')" class="btn btn-success">
                                                            <i class="fa fa-arrow-circle-left"></i> Previous
                                                        </button>
                                                        <button type="button" onclick="nextTab('personalInformation','categoryDtls')" class="btn btn-primary" id="sole">
                                                            Next <i class="fa fa-arrow-circle-right"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane categoryDtls" id="contact_detail">
                                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 form-group">
                                                    <div class="card tab2" id="individualcate">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Category Information</h3> >> <i>Please tick the radio button to select a category</i>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <table id="specializedTradeInd" class="table table-bordered table-hover">
                                                                    <thead style="background-color: #0077aa">
                                                                    <tr>
                                                                        <th></th>
                                                                        <th>Category</th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                    <c:forEach items="${categoryList}" var="category" varStatus="i">
                                                                        <tr>
                                                                            <td><input class="form-control categoryCheck" type="radio" name="itemId" value="${category.id}" style="width: 17px; height: 17px;" required="true"></td>
                                                                            <td>${category.code}${category.name}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group row pull-right">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                            <label class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                                                <button type="button" onclick="previousTab('personalInformation','categoryDtls')" class="btn btn-success">
                                                                    <i class="fa fa-arrow-circle-left"></i> Previous
                                                                </button>
                                                            </label>
                                                            <label class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                                                <button type="button" onclick="nextTab('categoryDtls','saveAndPreview')" class="btn btn-primary" id="soleee">
                                                                    Next <i class="fa fa-arrow-circle-right"></i>
                                                                </button>
                                                            </label>
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
                                                                    <label><b>Attachment >> (Attach Academic Certificate and other relevant documents if any.)<span class="text-danger">*</span></b>( Please click on add more document button to add more attachments)</label><br/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
                                                                    <input type="file" name="files" id="file1" class="alert badge-danger"required="true" accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document' onchange="validateAttachment(this.value,'file1','filecheck1')">
                                                                </div>
                                                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
                                                                    <button class="btn btn-success fa-pull-right" type="button" onclick="addmoreattachemnts()"><i class="fa fa-plus"> Add More Documents</i></button>
                                                                </div>
                                                            </div>
                                                            <span id="fileadd"></span>
                                                            <br/>
                                                            <input type="hidden" name="addedeqCount" id="addedeqCount">
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr/>
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="previousTab('categoryDtls','saveAndPreview')" class="btn btn-success">
                                                            <i class="fa fa-arrow-circle-left"></i> Previous
                                                        </button>
                                                        <button type="button" onclick="submitRegistrationForm()" class="btn btn-primary" id="submitbtn">
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
                        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                             class="modal in" id="concirmationModel">
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
                                        <button type="button" class="btn btn-success" onclick="SubmitApplicationDetials()" id="submitbtnsole">Yes
                                        </button>
                                        <button type="button" class="btn btn-warning" onclick="closemodel('concirmationModel')"><span class="fa fa-times"></span> No
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
                    <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
                    <script src="<c:url value="/resources/js/cdb/specialized.js"/>"></script>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>




