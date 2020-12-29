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
            <!-- tab open -->
            <div class="card" id="verifyTab" style="display: none">
                <div class="card-header">
                    <h3 class="card-title font-weight-bold">Application Acknowledgement</h3>
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <div class="col-lg-12">
                            This application is verified and forwarded for approval,<br>
                            Click on tasklist button to go back to tasklist
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="col-lg-12 mt-3">
                            <a href="<c:url value="/admin/"/>" class="btn btn-blue">TaskList</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card" id="registrtaionFormCard">
            <div class="card-header">
                <h3 class="card-title font-weight-bold" style="color: #002752">Consultnat >> Cancellation of Certificate >>
                    <security:authorize access="hasRole('ROLE_VERIFIER')">Verification</security:authorize>
                    <security:authorize access="hasRole('ROLE_APPROVER')">Approval</security:authorize>
                </h3>
                <span style="font-size: small;color: #444444"> >> Application Number : ${appNo}</span>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12 col-lg-12">
                        <div class="generalInformation ">
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
                                    <!-- Box Close -->
                                    <!-- Box Open -->
                                    <%--<div class="card tab2">
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
                                    </div>--%>
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
                                                <table class="table table-bordered table-center table-responsive-lg"
                                                       id="partnerDtls1">
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
                                                    <tr>
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
                                                    <label class="col-lg-5 form-label">Telephone Number</label>
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

                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="table-responsive">
                                        <fieldset><h3>Application Status</h3>
                                            <table id="appStatusTbl" width="1000px" cellpadding="1" cellspacing="1" border="1" style="border-collapse: collapse" class="table table-bordered">
                                                <thead>
                                                <tr class="Caption">
                                                    <td width="20%" align="center" valign="top"><strong>Status</strong></td>
                                                    <td width="20%" align="center" valign="top"><strong>Action By</strong></td>
                                                    <td width="20%" align="center" valign="top"><strong>Action Date</strong></td>
                                                    <td width="20%" align="center" valign="top"><strong>Remarks</strong></td>
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
                                        <textarea name="vRemarks" id="vRemarks" class="form-control"></textarea>
                                        <span class="help-block" id="remarkErrorId"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <security:authorize access="hasRole('ROLE_VERIFIER')">
                                    <button type="button" class="btn btn-primary" id="btnVerify"><i class="fa fa-check"></i>&nbsp;&nbsp;Verify</button>
                                </security:authorize>
                                <security:authorize access="hasRole('ROLE_APPROVER')">
                                    <button type="button" class="btn btn-primary" id="btnApprove"><i class="fa fa-check"></i>&nbsp;&nbsp;Approve</button>
                                    <button type="button" class="btn btn-primary" id="btnSendBack"><i class="fa fa-backward"></i>&nbsp;&nbsp;Send back</button>
                                </security:authorize>
                                <button type="button" class="btn btn-danger " id="btnReject"><i class="fa fa-times mr-1"></i>Reject</button>
                                <a href="<c:url value="/admin/consultant"/>">
                                    <button type="button" class="btn btn-warning"><i class="fa fa-ban"></i>Cancel</button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<c:url value="/resources/js/cdb/consultant/consultantNRAction.js"/>"></script>
</div>
</body>
</html>
