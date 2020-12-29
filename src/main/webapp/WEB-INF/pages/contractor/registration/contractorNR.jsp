<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/22/2019
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<body>
<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <div class="page-header mb-0 mt-0 page-header">
                    <h1 class="page-title">Construction Development Board</h1>
                </div>
                <c:if test="${not empty error}">
                    <div class="card hidden" id="acknowledgement">
                        <div class="card-header">
                            <b>Application / </b>Acknowledgement
                        </div>
                        <div class="card-body" style="color:red"> ${error}</div>
                    </div>
                </c:if>
                <div class="card" id="registrtaionFormCard">
                    <form id="contractorForm" name="contractorForm" action="<c:url value="contractor/save"/>" method="POST" class="globalForm" enctype="multipart/form-data">
                        <div class="card-header">
                            <h3 class="card-title font-weight-bold">Registration of Contractor</h3>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="nav-tabs-custom">
                                        <ul class="m-0 nav nav-tabs">
                                            <li class="fees_structure tab-pane active">
                                                <a href="#" class="border text-white" data-toggle="tab" data-placement="top"><i class="fa fa-bookmark mr-1"></i>Fee Structure</a>
                                            </li>
                                            <li class="tab-pane general_Information">
                                                <a href="#" class=" border" data-toggle="tab" data-placement="top"><i class="fa fa-exclamation-circle mr-1"></i>General Information</a>
                                            </li>
                                            <li class="tab-pane category_details">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top"><i class="fa fa-sitemap mr-1"></i>Category Details</a>
                                            </li>
                                            <li class="tab-pane human_resource_criteria">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top"><i class="fa fa-users mr-1"></i>Human Resource Criteria</a>
                                            </li>
                                            <li class="tab-pane equipment_details">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top"><i class="fa fa-truck mr-1"></i>Equipment Details</a>
                                            </li>
                                            <li class="tab-pane saveAndPreview">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top"><i class="fa fa-file mr-1"></i>Preview</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content border p-3 col-lg-12">
                                            <div role="tabpanel" class="tab-pane active fees_structure" id="fees_structure">
                                                <h3 class="pt-3 text-center hide">Fee Structure</h3>
                                                <jsp:include page="fee.jsp"/>
                                            </div>
                                            <div role="tabpanel" class="tab-pane general_Information" id="general_Information">
                                                <h3 class="pt-3 text-center hide">General Information</h3>
                                                <jsp:include page="gInfo.jsp"/>
                                            </div>
                                            <div role="tabpanel" class="tab-pane category_details" id="category_details">
                                                <h3 class="pt-3 text-center hide">Category Details</h3>
                                                <jsp:include page="category.jsp"/>
                                            </div>
                                            <div role="tabpanel" class="tab-pane human_resource_criteria" id="human_resource_criteria">
                                                <h3 class="pt-3 text-center hide">Human Resource Criteria</h3>
                                                <jsp:include page="hr.jsp"/>
                                            </div>
                                            <div role="tabpanel" class="tab-pane equipment_details" id="equipment_details">
                                                <h3 class="pt-3 text-center hide"> Equipment Details</h3>
                                                <jsp:include page="equipment.jsp"/>
                                            </div>
                                            <div class="tab-pane saveAndPreview" id="saveAndPreview">
                                                <div id="submitSection" style="">
                                                    <div class="panel panel-default">
                                                        <div class="panel-body">
                                                            <div class="form-group">
                                                                <label class="col-lg-12">I/We declare and confirm that:- </label>
                                                                <div id="termsAndCon">
                                                                    <ul>
                                                                        <li> All information and attachments with this
                                                                            application are true and correct;
                                                                        </li>
                                                                        <li>I am/We are aware that any false information
                                                                            provided herein shall result in rejection of
                                                                            my application and suspension of any
                                                                            registration granted;
                                                                        </li>
                                                                        <li>I/We shall not make refund claims of
                                                                            expenditure incurred in processing this
                                                                            application;
                                                                        </li>
                                                                        <li>I/We have read and understood the legal
                                                                            instruments issued by CDB (Manuals,
                                                                            Guidelines, Codes etc.) and shall perform in
                                                                            line with these instruments and
                                                                            any other laws in force.
                                                                        </li>
                                                                        <li>I/We understand that the failure to comply
                                                                            with aforementioned legal instruments will
                                                                            be subject to the penalties provided for in
                                                                            these instruments and
                                                                            applicable laws in force; and
                                                                        </li>
                                                                        <li>I/We hereby declare that issuance of CDB
                                                                            certificate does not in any way constitute
                                                                            an obligation on the part of CDB or any
                                                                            other government agencies to
                                                                            provide contract works.
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-lg-12">
                                                                    <span><input type="checkbox" id="submitcheckbox" onclick="enableSubmit()" name="tnc" required="true" style="width:15px;height:15px;"></span>
                                                                    <span class="bold"> I/We agree to the above Terms & Conditions.</span>
                                                                </label>
                                                                <!-- <br /><span class="text-danger" id="termAndConErrorClass"></span> -->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="previousTab('equipment_details','saveAndPreview')" class="btn btn-success">
                                                            <i class="fa fa-arrow-circle-left"></i> Previous
                                                        </button>
                                                        <button type="button" onclick="showConfirmation()" class="btn btn-primary" id="btnSubmit" disabled>
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
                             class="modal in" id="confirmationModel">
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
                                        <button type="button" class="btn btn-success" onclick="submitApplication()">Yes</button>
                                        <button type="button" class="btn btn-warning" onclick="closemodel('confirmationModel')"><span class="fa fa-times"></span> No</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
                    <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
                    <script src="<c:url value="/resources/js/cdb/contractor/contractorNR.js"/>"></script>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

