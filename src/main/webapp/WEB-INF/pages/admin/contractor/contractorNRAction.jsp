<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<security:authentication var="auth" property="principal"/>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/9/2020
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<input type="hidden" id="appNoVA" value="${appNo}" name="applcationNo"/>

<div class="container mb-9">
    <div class="row">
        <div class="col-12">
            <div class="page-header mb-0 mt-0 page-header">
                <h1 class="page-title">
                    Construction Development Board
                </h1>
            </div>
        </div>
        <div class="card" id="registrtaionFormCard">
            <div class="card-header">
                <h3 class="card-title font-weight-bold" style="color: #002752">Contractor >> New Registration >>
                    <security:authorize access="hasRole('ROLE_VERIFIER')">Verification</security:authorize>
                    <security:authorize access="hasRole('ROLE_APPROVER')">Approval</security:authorize>
                </h3>
                <span style="font-size: small;color: #444444"> >> Application Number : ${appNo}</span>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12 col-lg-12">
                        <div class="nav-tabs-custom">
                            <ul class="m-0 nav nav-tabs">
                                <li class="tab-pane generalInformation active">
                                    <a href="#general_Information" class=" border" data-toggle="tab"
                                       data-placement="top">
                                        <i class="fa fa-exclamation-circle mr-1"></i>General Information</a>
                                </li>
                                <li class="tab-pane categoryDtls">
                                    <a href="#category_details" class="border" data-toggle="tab" data-placement="top">
                                        <i class="fa fa-sitemap mr-1"></i>Category Details</a>
                                </li>
                                <li class="humanResourceCriteria tab-pane">
                                    <a href="#human_resource_criteria" class="border" data-toggle="tab"
                                       data-placement="top">
                                        <i class="fa fa-users mr-1"></i>Human Resource Criteria</a>
                                </li>
                                <li class="tab-pane contractorEquipmentDtls">
                                    <a href="#consultant_equipment_details" class="border" data-toggle="tab"
                                       data-placement="top">
                                        <i class="fa fa-truck mr-1"></i>Contractor Equipment Details</a>
                                </li>
                                <li class="tab-pane saveAndPreview">
                                    <a href="#saveAndPreview" class="border" data-toggle="tab"
                                       data-placement="top">
                                        <i class="fa fa-truck mr-1"></i>Preview</a>
                                </li>
                            </ul>
                            <div class="tab-content border p-3 col-lg-12">
                                <div class="tab-pane generalInformation active">
                                    <form action="" method="post" class="">
                                        <div class="">
                                            <!-- Box Open -->
                                            <div class="card tab2">
                                                <div class="bg-blue card-status card-status-left"></div>
                                                <div class="card-body">
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-4 form-label">Ownership Type</label>
                                                            <label class="col-lg-8 form-label form-control" id="ownershipType"></label>
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-4 form-label">Country</label>
                                                            <label class="col-lg-8 form-label form-control" id="country"></label>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-4 form-label">Trade License Number</label>
                                                            <label class="col-lg-8 form-label form-control" id="tradeLicenseNo"></label>
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-4 form-label">Proposed Firm Name</label>
                                                            <label class="col-lg-8 form-label form-control" id="firmName"></label>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-4 form-label">TPN Number</label>
                                                            <label class="col-lg-8 form-label form-control" id="tpn"></label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="card hide" id="cIncorporation">
                                                <div class="bg-blue card-status card-status-left"></div>
                                                <div class="card-header">
                                                    <h3 class="card-title">Attach Certificates of Incorporation</h3>
                                                </div>
                                                <div class="card-body">
                                                    <div class="col-lg-12">
                                                        <%--<div class=""><input id="addMoreCert" type="button"
                                                                             value="Add More Certificate"
                                                                             class="btn btn-primary"></div>--%>
                                                        <table class="table table-bordered table-center table-responsive-lg auto-index"
                                                               id="IncCertificateTbl">
                                                            <thead>
                                                            <tr>
                                                                <th>Sl no</th>
                                                                <th>Document Name</th>
                                                                <th>Document Attached</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="card tab2">
                                                <div class="bg-blue card-status card-status-left"></div>
                                                <div class="card-header">
                                                    <h3 class="card-title">Name of Owner, Partners and/or others with
                                                        Controlling Interest</h3>
                                                </div>
                                                <div class="card-body">
                                                    <div class="col-lg-12">
                                                        <table class="table table-bordered table-center table-responsive-lg"
                                                               id="partnerDtls">
                                                            <thead>
                                                            <tr>
                                                                <th>Sl No</th>
                                                                <th>Nationality</th>
                                                                <th>CID/Work Permit No.</th>
                                                                <th>Salutation</th>
                                                                <th>Name</th>
                                                                <th>Gender</th>
                                                                <th>Designation</th>
                                                                <th>Show in certificate</th>
                                                                <th>CHECK FOR THE CID</th>
                                                                <security:authorize access="hasRole('ROLE_VERIFIER')">
                                                                    <th style="width: 10%">Verify</th>
                                                                </security:authorize>
                                                                <security:authorize access="hasRole('ROLE_APPROVER')">
                                                                    <th style="width: 10%">Verify</th>
                                                                    <th style="width: 10%">Approve</th>
                                                                </security:authorize>
                                                            </tr>
                                                            </thead>
                                                            <tbody>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Box Close -->
                                            <!-- Box Open -->
                                            <div class="card tab2">
                                                <div class="bg-blue card-status card-status-left"></div>
                                                <div class="card-header">
                                                    <h3 class="card-title">Permanent Address</h3>
                                                </div>
                                                <div class="card-body">
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-4 form-label">Dzongkhag</label>
                                                            <label class="col-lg-8 form-label form-control" id="pDzongkhag"></label>
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-4 form-label">Gewog </label>
                                                            <label class="col-lg-8 form-label form-control" id="pGewog"></label>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-4 form-label">Village </label>
                                                            <label class="col-lg-8 form-label form-control" id="pVillage"></label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Box Close -->
                                            <!-- Box Open -->
                                            <div class="card tab2">
                                                <div class="bg-blue card-status card-status-left"></div>
                                                <div class="card-header">
                                                    <h3 class="card-title">Induction course Details</h3>
                                                </div>
                                                <div class="card-body">
                                                    <div class="col-lg-12">
                                                        <table class="table table-bordered table-center table-responsive-lg auto-index"
                                                               id="inductionCourseDtl">
                                                            <thead>
                                                            <tr>
                                                                <th>Sl No</th>
                                                                <th>Training type</th>
                                                                <th>Training Dates</th>
                                                                <th>Module</th>
                                                                <th>Participant</th>
                                                                <th>CID No</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <tr class="noRecord">
                                                                <td colspan="6">No Record Found</td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Box Close -->
                                            <!-- Box Open -->
                                            <div class="card tab2">
                                                <div class="bg-blue card-status card-status-left"></div>
                                                <div class="card-header">
                                                    <h3 class="card-title">Establishment Addresses</h3>
                                                </div>
                                                <div class="card-body">
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-5 form-label">Establishment Address</label>
                                                            <label class="col-lg-7 form-label form-control" id="estAddress"></label>
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-5 form-label">Dzongkhag</label>
                                                            <label class="col-lg-7 form-label form-control" id="estDzongkhag"></label>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-5 form-label">Email</label>
                                                            <label class="col-lg-7 form-label form-control" id="regEmail"></label>
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-5 form-label">Mobile Number</label>
                                                            <label class="col-lg-7 form-label form-control" id="regMobileNo"></label>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-5 form-label">Telephone Number </label>
                                                            <label class="col-lg-7 form-label form-control" id="regPhoneNo"></label>
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 form-group">
                                                            <label class="col-lg-5 form-label">Fax Number </label>
                                                            <label class="col-lg-7 form-label form-control" id="regFaxNo"></label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Box Close -->
                                        </div>
                                    </form>
                                    <div class="col-lg-12 form-group">
                                        <button type="button" onclick="nextTab('feesStructure','generalInformation')" class="btn btn-azure col-lg-offset-9" id="btn1">
                                            <i class="fa fa-arrow-circle-left"></i>Back
                                        </button>
                                        <button type="button" disabled onclick="nextTab('generalInformation','categoryDtls')" class="btn btn-primary" id="nextGIBtn">
                                            <i class="fa fa-arrow-circle-right"></i>Next
                                        </button>
                                    </div>
                                </div>
                                <div class="tab-pane categoryDtls">
                                    <form action="" method="post" class="">
                                        <table id="contractorCCTbl" class="table table-bordered table-hover">
                                            <thead style="background-color: #F2F2F2">
                                            <tr>
                                                <td></td>
                                                <td>Category</td>
                                                <td>Apply for Class</td>
                                                <td>Fees</td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                            <tfoot>

                                            </tfoot>
                                        </table>
                                    </form>
                                    <div class="col-lg-12 form-group">
                                        <button type="button" onclick="backTab('categoryDtls','generalInformation')"
                                                class="btn btn-azure col-lg-offset-9" id="btn2">
                                            <i class="fa fa-arrow-circle-left"></i>Back
                                        </button>
                                        <button type="button" onclick="nextTab('categoryDtls','humanResourceCriteria')"
                                                class="btn btn-primary">
                                            <i class="fa fa-arrow-circle-right" id="btn3"></i>Next
                                        </button>
                                    </div>
                                </div>
                                <div class="tab-pane humanResourceCriteria">
                                    <form action="" method="post" class="">
                                        <div class="">
                                            <div class="row">
                                                <div class="col-md-12 col-lg-12">
                                                    <!-- Box Open -->
                                                    <div class="card tab4">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Human Resource Form</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-sm-12 table-responsive">
                                                                <table class="table table-bordered table-hover"
                                                                       id="hrTbl">
                                                                    <thead>
                                                                    <tr style="background-color: #e6f9ff">
                                                                        <th>SLNo</th>
                                                                        <th>Nationality</th>
                                                                        <th>CID/Work Permit No</th>
                                                                        <th>Salutation</th>
                                                                        <th>Name</th>
                                                                        <th>Gender</th>
                                                                        <th>Designation</th>
                                                                        <th>Qualification</th>
                                                                        <th>Trade</th>
                                                                        <th>Join date</th>
                                                                        <th>Service Type</th>
                                                                        <th>Attachment</th>
                                                                        <th>Check for the CID</th>
                                                                        <security:authorize
                                                                                access="hasRole('ROLE_VERIFIER')">
                                                                            <th style="width: 10%">Verify</th>
                                                                        </security:authorize>
                                                                        <security:authorize
                                                                                access="hasRole('ROLE_APPROVER')">
                                                                            <th style="width: 10%">Verify</th>
                                                                            <th style="width: 10%">Approve</th>
                                                                        </security:authorize>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <!-- Box Close -->
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="col-lg-12 form-group">
                                        <button type="button" onclick="backTab('humanResourceCriteria','categoryDtls')" class="btn btn-azure col-lg-offset-9" id="btn4">
                                            <i class="fa fa-arrow-circle-left"></i>Back
                                        </button>
                                        <button type="button" id="nextHRBtn" disabled onclick="nextTab('humanResourceCriteria','contractorEquipmentDtls')" class="btn btn-primary">
                                            <i class="fa fa-arrow-circle-right"></i>Next
                                        </button>
                                    </div>
                                </div>
                                <div class="tab-pane contractorEquipmentDtls">
                                    <form action="" method="post" class="">
                                        <div id="contractorEquipment" style="">
                                            <i><strong>Contractor Equipment Details</strong></i>

                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <div class="table-responsive">
                                                        <table class="table table-bordered table-hover"
                                                               id="equipmentTbl">
                                                            <thead>
                                                            <tr style="background-color: #e6f9ff">
                                                                <th>SLNo</th>
                                                                <th>Equipment Name</th>
                                                                <th>Registration Type</th>
                                                                <th>Registration Number</th>
                                                                <th>Owner Name</th>
                                                                <th>Equipment Number</th>
                                                                <th>Attachment</th>
                                                                <th>Equipment Check</th>
                                                                <security:authorize access="hasRole('ROLE_VERIFIER')">
                                                                    <th style="width: 10%">Verify</th>
                                                                </security:authorize>
                                                                <security:authorize access="hasRole('ROLE_APPROVER')">
                                                                    <th style="width: 10%">Verify</th>
                                                                    <th style="width: 10%">Approve</th>
                                                                </security:authorize>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                        <!-- Equipment modal open -->
                                                        <!-- Equipment modal close -->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        </form>
                                    <div class="col-lg-12 form-group">
                                        <button type="button" onclick="backTab('contractorEquipmentDtls','humanResourceCriteria')"id="btn5" class="btn btn-azure col-lg-offset-9">
                                            <i class="fa fa-arrow-circle-left"></i>&nbsp; Back
                                        </button>
                                        <button type="button" disabled class="btn btn-primary" id="btnValEqNext" onclick="saveAndPreview('contractorEquipmentDtls', 'saveAndPreview')">
                                            Save & Preview &nbsp;
                                            <i class="fa fa-life-saver"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="tab-pane saveAndPreview" id="saveAndPreview">
                                    <div id="submitSection" style="">
                                        <div class="panel-body">
                                            <div class="form-group">
                                                <div class="table-responsive">
                                                    <fieldset><h3>Application Status</h3>
                                                        <table id="appStatusTbl" width="1000px" cellpadding="1"
                                                               cellspacing="1" border="1"
                                                               style="border-collapse: collapse"
                                                               class="table table-bordered">
                                                            <thead>
                                                            <tr class="Caption">
                                                                <td width="20%" align="center" valign="top">
                                                                    <strong>Status</strong>
                                                                </td>
                                                                <td width="20%" align="center" valign="top">
                                                                    <strong>Action By</strong>
                                                                </td>
                                                                <td width="20%" align="center" valign="top">
                                                                    <strong>Action Date</strong>
                                                                </td>
                                                                <td width="20%" align="center" valign="top">
                                                                    <strong>Remarks</strong>
                                                                </td>
                                                            </tr>
                                                            </thead>
                                                            <tbody>

                                                            </tbody>
                                                        </table>
                                                        <br>
                                                    </fieldset>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2" for="vRemarks">Remarks:</label>

                                                <div class="col-md-10" id="remarkclass">
                                                    <textarea name="vRemarks" id="vRemarks"
                                                              class="form-control"></textarea>
                                                    <span class="help-block" id="remarkErrorId"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="col-md-12">
                                        <security:authorize access="hasRole('ROLE_VERIFIER')">
                                            <button type="button" class="btn btn-primary" id="btnVerify"><i
                                                    class="fa fa-check"></i>&nbsp;&nbsp;Verify
                                            </button>
                                        </security:authorize>
                                        <security:authorize access="hasRole('ROLE_APPROVER')">
                                            <button type="button" class="btn btn-primary" id="btnApprove"><i
                                                    class="fa fa-check"></i>&nbsp;&nbsp;Approve
                                            </button>
                                          <%--  <button type="button" class="btn btn-primary" id="btnSendBack"><i
                                                    class="fa fa-backward"></i>&nbsp;&nbsp;Send back
                                            </button>--%>
                                        </security:authorize>
                                        <button type="button" class="btn btn-danger " id="btnReject"><i
                                                class="fa fa-times mr-1"></i>Reject
                                        </button>
                                        <a href="<c:url value="/admin/contractor"/>">
                                            <button type="button" class="btn btn-warning"><i class="fa fa-ban"></i>
                                                Cancel
                                            </button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--HR Modal--%>
            <div id="hrModal" class="modal fade in " tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" style=" max-width: 900px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 id="myModalLabel" class="modal-title"><i class="fa fa-info-circle fa-lg"></i> Personal
                                Check<span id="cid"></span></h4>
                        </div>
                        <div class="modal-body">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div id="modal-print">
                                        <div class="form-group">
                                            <p align="center"><strong><u>Caution</u></strong></p>

                                            <p align="center"><strong>An engineer is allowed to execute only two work at
                                                a time for that particular firm.</strong></p>

                                            <p align="center"><strong>For any other Human Resource they are allowed to
                                                involve only in a single project of work</strong></p>

                                            <p align="center"><font size="5px;">Details of CID No: <span
                                                    id="cidchecked"></span></font></p>
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
                                                <p id="notEngagedId" style="display: none"><span id="dcbinfo"><b>Human Resource is not registered in any of the
                                                    CDB firm</b><br><br> This person is not engaged in any work or project<br>This person is not a civil servant</span>
                                                </p>
                                                <div class="tab-pane employeeDetails" style="display: none" id="engagedId">
                                                    <p ><span><b>The Individual holding CID/permit no.<label id="cidNumber"></label>(<label id="hrName"></label>) is engaged in following project(s):</b></span>
                                                    </p>
                                                    <form action="" method="post" class="">
                                                        <div id="employeeDetails" style="">
                                                            <i><strong>Employee Engagement Details</strong></i>
                                                            <div class="panel panel-default">
                                                                <div class="panel-body">
                                                                    <span>Contractor</span>
                                                                    <div class="table-responsive">
                                                                        <table class="table table-bordered table-hover"
                                                                               id="employeeDTLS">
                                                                            <thead>
                                                                            <tr style="background-color: #e6f9ff">
                                                                                <th>SLNo</th>
                                                                                <th>Work </th>
                                                                                <th>Procuring Agency</th>
                                                                                <th>CDB Number</th>
                                                                                <th>Firm Name</th>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody>

                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="panel panel-default">
                                                                <div class="panel-body">
                                                                    <span>Consultant</span>
                                                                    <div class="table-responsive">
                                                                        <table class="table table-bordered table-hover"
                                                                               id="employeeDTLS1">
                                                                            <thead>
                                                                            <tr style="background-color: #e6f9ff">
                                                                                <th>SLNo</th>
                                                                                <th>Work </th>
                                                                                <th>Procuring Agency</th>
                                                                                <th>CDB Number</th>
                                                                                <th>Firm Name</th>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody>

                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="panel panel-default">
                                                                <div class="panel-body">
                                                                    <span>Specialized Firm</span>
                                                                    <div class="table-responsive">
                                                                        <table class="table table-bordered table-hover"
                                                                               id="employeeDTLS2">
                                                                            <thead>
                                                                            <tr style="background-color: #e6f9ff">
                                                                                <th>SLNo</th>
                                                                                <th>Work </th>
                                                                                <th>Procuring Agency</th>
                                                                                <th>CDB Number</th>
                                                                                <th>Firm Name</th>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody>

                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
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
                                    <div class="col-md-12 col-lg-12 col-sm-12">
                                        <div class="col-md-1 col-lg-1 col-sm-2">
                                            <button type="button" class="btn btn-primary"
                                                    onclick="javascript:printDiv('modal-print')">Print
                                            </button>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-4">
                                            <button type="button" class="btn btn-success" id="closeModal" style="display: none"
                                                    onclick="checkBtn('owner')" data-dismiss="modal">OK
                                            </button>
                                            <button type="button" class="btn btn-success " id="closeModal1"  style="display: none"
                                                    onclick="checkBtn('Hr')" data-dismiss="modal">OK
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%--Equipment Modal--%>
            <div id="CheckModalEquipment" class="modal fade in" tabindex="-1" role="dialog"
                 aria-labelledby="myModalLabel" aria-hidden="false">
                <div class="modal-dialog" style=" max-width: 900px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 id="myModalLabel1" class="modal-title"><i class="fa fa-info-circle fa-lg"></i> Equipment
                                check<span id="cid2"></span></h4>
                        </div>
                        <div class="modal-body">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div id="modal-print-equipment">
                                        <div class="form-group">
                                            <p align="center"><strong><u><font size="3px;">Caution</font></u></strong>
                                            </p>

                                            <p align="center"><strong>An equipment is allowed to execute only one work
                                                at a time for the perticular firm.</strong></p>

                                            <p align="center"><font size="5px;">Details of Registration No: <span
                                                    id="regchecked"></span></font></p>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-12">
                                                <p align="center"><strong>From RSTA database</strong></p>

                                                <div class="col-lg-12">
                                                    <div class="col-lg-12 form-group mb-0 pt-4">
                                                        <label class="col-lg-3 form-label">Registered No.</label>
                                                        <label class="col-lg-8 form-label" id="regNo">BP-1-D1234</label>
                                                    </div>
                                                    <div class="col-lg-12 form-group mb-0">
                                                        <label class="col-lg-3 form-label">Owner Name</label>
                                                        <label class="col-lg-8 form-label" id="ownerName">Tshewan Tenzin</label>
                                                    </div>
                                                    <div class="col-lg-12 form-group mb-0">
                                                        <label class="col-lg-3 form-label">Registered Region</label>
                                                        <label class="col-lg-8 form-label">Samtse</label>
                                                    </div>
                                                    <div class="col-lg-12 form-group mb-0">
                                                        <label class="col-lg-3 form-label">Vehicle Type</label>
                                                        <label class="col-lg-8 form-label">Tractor</label>
                                                    </div>
                                                </div>
                                                <p align="center">
                                                    <span id="regcheckerrorspa" class="has-error"></span>

                                                <p align="center"></p>

                                                <p align="center"><strong><span id="engagementStatus"></span></strong>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <p align="center"><strong>Print this page as an evidence to prove that
                                                particular HR is engaged or not in a work or project</strong></p>

                                            <p align="center">
                                                Printed on: 28-05-2019 <span id="dateSpan1"
                                                                             style="display: none;"></span>
                                                By:Tshewang Tenzin
                                            </p>
                                        </div>
                                    </div>
                                    <button type="button" class="btn btn-primary"
                                            onclick="javascript:printDiv('modal-print-equipment')">Print
                                    </button>
                                    <button type="button" class="btn btn-success" onclick="checkBtn('equipment')"
                                            id="closeModalEquipment" data-dismiss="modal">OK
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function showAcknowledgement() {
                $("#registrtaionFormCard").hide();
                $("#acknowledgementCard").show();
            }
           /* function saveAndPreview(presentClass, nextClass) {
                var content = '<h3 class="pt-3 text-center">Fee Structure</h3>' + $(".feesStructure > form").html() +
                        '<h3 class="pt-3 text-center">General Information</h3>' + $(".generalInformation > form").html() +
                        '<h3 class="pt-3 text-center">Category Details</h3>' + $(".categoryDtls > form").html()
                        + '<h3 class="pt-3 text-center">Human Resource</h3>' + $(".humanResourceCriteria > form").html() +
                        '<h3 class="pt-3 text-center">Consultant Equipment Details</h3>' + $(".consultantEquipmentDtls > form").html();

                $("." + presentClass + ">a").addClass('bg-blue text-white');
                $('.tab-pane').removeClass("active");
                $('.tab-content').removeClass("active");
                $("." + nextClass).addClass("active");
                $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");
                $("#" + nextClass).html(content);
            }*/
            function getModalData(tableId, prefix, totalCol) {
                var td = "";
                for (var i = 1; i <= totalCol; i++) {
                    var rowVal = $("#" + prefix + i).val();
                    td = td + "<td>" + rowVal + "</td>";
                }
                var tr = "<tr>" + td + "<td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2'><i class='fa fa-trash text-danger'></i></a></td></tr>";
                $("#" + tableId + ">.noRecord").hide();
                $('#' + tableId).append(tr);
            }
        </script>

        <script type="text/javascript">
            function addRow(tableId) {
                var $tableBody = $('#' + tableId).find("tbody"),
                        $trLast = $tableBody.find("tr:last"),
                        $trNew = $trLast.clone();
                $trLast.after($trNew);
            }
            function removeRow(tableId) {
                $('#' + tableId + ' tr:last').remove();
            }
        </script>
        <script type="text/javascript">
            function nextTab(presentClass, nextClass) {
              //  alert($('.'+presentClass).find('.check').is(':checked'));
            //    if($('.'+presentClass).find('.check').is(':checked') == true){
                    $("." + presentClass + ">a").addClass('bg-blue text-white');
                    $('.tab-pane').removeClass("active");
                    $('.tab-content').removeClass("active");
                    $("." + nextClass).addClass("active");
                    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");
               /* } else{
                    alert('Please click on CHECK FOR THIS CID')
                }*/

            }
            function backTab(presentClass, prevClass) {
                $("." + presentClass + ">a").removeClass('bg-blue text-white');
                $('.tab-pane').removeClass("active");
                $('.tab-content').removeClass("active");
                $("." + prevClass).addClass("active");
                $("." + presentClass + ">a").find(".fa-check").remove();
                $("." + prevClass + ">a").find(".fa-check").remove();
            }
        </script>
    </div>
    <script src="<c:url value="/resources/js/cdb/contractor/contractorNRAction.js"/>"></script>
</div>
</div>
</body>
</html>
