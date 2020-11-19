<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/1/2020
  Time: 11:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
<input type="hidden" id="appNoVA" value="${appNo}" name="applicationNo"/>

<div class="mt-5 container mb-9">
    <div class="card" id="registrtaionFormCard">
        <div class="card-header">
            <h3 class="card-title font-weight-bold" style="color: #002752">Contractor >> Renewal Application >>
                <security:authorize access="hasRole('ROLE_VERIFIER')">Verification</security:authorize>
                <security:authorize access="hasRole('ROLE_APPROVER')">Approval</security:authorize>
            </h3>
            <span style="font-size: small;color: #444444"> >> CDB No: ${cdbNo} >> Application Number : ${appNo}</span>
        </div>

        <div class="card-body">
            <form action="" method="post" class="" id="contractorRenewalForm" enctype="multipart/form-data">
                <div class="nav-tabs-custom">
                    <ul class="m-0 nav nav-tabs">
                        <li class="tab-pane services active" id="services">
                            <a href="#" class=" border" data-toggle="tab" data-placement="top">
                                <i class="fa fa-exclamation-circle mr-1"></i>Services</a>
                        </li>
                        <li class="tab-pane generalInformation" id="generalInformation">
                            <a href="#" class=" border" data-toggle="tab"
                               data-placement="top">
                                <i class="fa fa-exclamation-circle mr-1"></i>General Information</a>
                        </li>

                        <li class="tab-pane category_details hide" id="category_details">
                            <a href="#" class="border" data-toggle="tab" data-placement="top">
                                <i class="fa fa-sitemap mr-1"></i>Category Details</a>
                        </li>
                        <li class="tab-pane human_resource_criteria hide" id="human_resource_criteria">
                            <a href="#" class="border" data-toggle="tab" data-placement="top">
                                <i class="fa fa-users mr-1"></i>Human Resource Criteria</a>
                        </li>
                        <li class="tab-pane equipment_details hide" id="equipment_details">
                            <a href="#" class="border" data-toggle="tab" data-placement="top">
                                <i class="fa fa-truck mr-1"></i>Equipment Details</a>
                        </li>

                        <li class="tab-pane saveAndPreview" id="saveAndPreview">
                            <a href="#" class="border" data-toggle="tab"
                               data-placement="top">
                                <i class="fa fa-file mr-1"></i>Preview</a>
                        </li>
                    </ul>
                    <div class="tab-content border p-3 col-lg-12">

                        <div class="active tab-pane services">
                            <div class="panel-body table-responsive div-actual">
                              <span>
                                Services availed along with renewal of certificate
                              </span>
                                <table>
                                    <tbody>
                                    <tr>
                                        <td>
                                            <input type="checkbox" style="zoom:1.6" name="incorporation"
                                                   value="1" id="Incorporation" class="service_check">
                                        </td>
                                        <td>
                                            <span>Incorporation</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" style="zoom:1.6"
                                                   name="changeOfFirmName" value="1"
                                                   id="changeOfFirmName" class="service_check">
                                        </td>
                                        <td>
                                            <span>Change of Firm Name</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" style="zoom:1.6"
                                                   name="changeOfLocation" value="1" class="service_check"
                                                   id="changeOfLocation">
                                        </td>
                                        <td>
                                            <span>Change of Location</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" style="zoom:1.6" name="changeOfOwner"
                                                   value="1" id="changeOfOwnerId" class="service_check">
                                        </td>
                                        <td>
                                            <span>Change of Owner</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" style="zoom:1.6"
                                                   name="upgradeDowngrade" value="1"
                                                   id="upgradeDowngrade" class="service_check">
                                        </td>
                                        <td>
                                            <span>Upgrade/Downgrade/Add Category/Classification</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" style="zoom:1.6" name="updateHR"
                                                   value="1" id="updateHR" class="service_check">
                                        </td>
                                        <td>
                                            <span>Update Human Resource</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="checkbox" style="zoom:1.6"
                                                   name="updateEq" value="1"
                                                   id="updateEq" class="service_check">
                                        </td>
                                        <td>
                                            <span>Update Equipment</span>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-12 form-group">
                                <button type="button"
                                        onclick="backTab('services')"
                                        class="btn btn-azure col-lg-offset-9">
                                    <i class="fa fa-arrow-left"></i>
                                    Back
                                </button>
                                <button type="button" onclick="nextTab('services')"
                                        class="btn btn-primary">
                                    <i class="fa fa-arrow-right"></i>
                                    Next
                                </button>
                            </div>
                        </div>

                        <div class="tab-pane generalInformation" >

                            <%--<jsp:include page="../contractor/gInfo.jsp"/>--%>
                            <div class="div-actual">
                                <div class="card ">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-body">
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-4 form-label">Ownership Type</label>
                                                <label class="col-lg-8 form-label"
                                                       id="ownershipType"></label>
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-4 form-label">Country</label>
                                                <label class="col-lg-8 form-label" id="country"></label>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-4 form-label">Trade License No</label>
                                                <label class="col-lg-8 form-label"
                                                       id="tradeLicenseNo"></label>
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-4 form-label">Proposed Firm
                                                    Name</label>
                                                <label class="col-lg-8 form-label" id="firmName"></label>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-4 form-label">TPN Number</label>
                                                <label class="col-lg-8 form-label" id="tpn"></label>
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
                                            <div class=""><input id="addMoreCert" type="button"
                                                                 value="Add More Certificate"
                                                                 class="btn btn-primary"></div>
                                            <table class="table table-bordered table-center table-responsive-lg auto-index"
                                                   id="certificateTbl">
                                                <thead>
                                                <tr>
                                                    <th>Sl no</th>
                                                    <th>Document Name</th>
                                                    <th>Document Attached</th>
                                                    <th>File Size</th>
                                                    <th>Action</th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="card hide" id="ownerPartner">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header">
                                        <h3 class="card-title">Name of Owner, Partners and/or others
                                            with Controlling Interest</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-lg-12">
                                            <table class="table table-bordered table-center table-responsive-lg"
                                                   id="partnerDtls">
                                                <thead>
                                                <tr>
                                                    <th>Nationality</th>
                                                    <th>CID/Work Permit No.</th>
                                                    <th>Salutation</th>
                                                    <th>Name</th>
                                                    <th>Gender</th>
                                                    <th>Designation</th>
                                                    <th>Show<br>in<br>certificate</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td class="country">
                                                        <select id="countryList" class="form-control"
                                                                     name="contractor.contractorHRs[0].countryId"
                                                                     required="true" >

                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text"
                                                               name="contractor.contractorHRs[0].cidNo"
                                                               class="form-control hr-cid"
                                                               placeholder="Text..">
                                                    </td>
                                                    <td>
                                                        <select id="salutation"
                                                                     name="contractor.contractorHRs[0].salutationId"
                                                                     class="form-control input-sm"
                                                                     required="true">

                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control name"
                                                               name="contractor.contractorHRs[0].name"
                                                               placeholder="Text..">
                                                    </td>
                                                    <td>
                                                        <select id="gender"
                                                                name="contractor.contractorHRs[0].sex"
                                                                class="form-control sex">
                                                            <option value="">Select Gender</option>
                                                            <option value="M">Male</option>
                                                            <option value="F">Female</option>
                                                        </select>

                                                    </td>
                                                    <td>
                                                        <select id="designation"
                                                                     name="contractor.contractorHRs[0].designationId"
                                                                     class="form-control input-sm"
                                                                     required="true">

                                                        </select>

                                                    </td>
                                                    <td>
                                                        <label class="custom-control custom-checkbox ml-6">
                                                            <input type="checkbox"
                                                                   class="custom-control-input showCert"
                                                                   name="contractor.contractorHRs[0].siCertificate"
                                                                   value="1">
                                                            <i class="custom-control-label"></i>
                                                        </label>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <div class="col-lg-12 text-right">
                                                <button type="button"
                                                        class="btn btn-outline-primary btn-sm"
                                                        onclick="addRow('partnerDtls')">
                                                    <i class="fe fe-plus mr-2"></i>Add More
                                                </button>
                                                <button type="button"
                                                        class="btn btn-outline-danger btn-sm"
                                                        onclick="removeRow('partnerDtls')">
                                                    <i class="fe fe-trash mr-2"></i>Remove Last Row
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card tab2">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header">
                                        <h3 class="card-title">Permanent Address</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-4 form-label">Dzongkhag</label>
                                                <label class="col-lg-8 form-label" id="pDzongkhag"></label>
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-4 form-label">Gewog </label>
                                                <label class="col-lg-8 form-label" id="pGewog"></label>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-4 form-label">Village </label>
                                                <label class="col-lg-8 form-label" id="pVillage"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card tab2">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header">
                                        <h3 class="card-title">Establishment Addresses</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Establishment
                                                    Address</label>
                                                <label class="col-lg-7 form-label" id="estAddress"></label>
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Dzongkhag</label>
                                                <label class="col-lg-7 form-label"
                                                       id="estDzongkhag"></label>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Email</label>
                                                <label class="col-lg-7 form-label" id="regEmail"></label>
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Mobile No</label>
                                                <label class="col-lg-7 form-label" id="regMobileNo"></label>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Telephone No </label>
                                                <label class="col-lg-7 form-label" id="regPhoneNo"></label>
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Fax No </label>
                                                <label class="col-lg-7 form-label" id="regFaxNo"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-lg-12 form-group">
                                <button type="button" onclick="backTab('generalInformation')"
                                        class="btn btn-azure col-lg-offset-9">
                                    <i class="fa fa-arrow-left"></i>
                                    Back
                                </button>
                                <button type="button"
                                        onclick="nextTab('generalInformation')"
                                        class="btn btn-primary">
                                    <i class="fa fa-arrow-right"></i>
                                    Next
                                </button>
                            </div>
                        </div>

                        <div class="tab-pane category_details hide">
                            <div class="div-actual">
                                <table id="contractorCCTbl" class="table table-bordered table-hover">
                                    <thead style="background-color: #F2F2F2">
                                    <tr>
                                        <th></th>
                                        <th>Service Type</th>
                                        <th>Category</th>
                                        <th>Existing Class</th>
                                        <th>New Class</th>
                                        <th>Fee</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-12 form-group nextBackBtn">
                                <button type="button"
                                        onclick="backTab('category_details')"
                                        class="btn btn-azure col-lg-offset-9">
                                    <i class="fa fa-arrow-circle-left"></i> &nbsp; Back
                                </button>
                                <button type="button" id="btnValCCNext" class="btn btn-primary"
                                        onclick="nextTab('category_details')">Next &nbsp;
                                    <i class="fa fa-arrow-circle-right"></i>
                                </button>
                            </div>
                        </div>

                        <div class="tab-pane human_resource_criteria hide">
                            <div class="div-actual">
                                <div class="card tab4">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header">
                                        <h3 class="card-title">Human Resource Form</h3>
                                    </div>
                                    <div class="card-body">
                                        <div style="overflow-x: auto;white-space: nowrap;">
                                            <table class="table table-bordered" id="hrDtlsTable">
                                                <thead>
                                                <tr class="table-dark">
                                                    <th style="width: 5%">Title</th>
                                                    <th style="width: 15%">Name</th>
                                                    <th style="width: 15%">ID/Work Permit No.</th>
                                                    <th style="width: 10%">Sex</th>
                                                    <th style="width: 10%">Country</th>
                                                    <th style="width: 10%">Designation</th>
                                                    <th style="width: 10%">Qualification</th>
                                                    <th style="width: 10%">Trade / Fields</th>
                                                    <th style="width: 10%">Service Type</th>
                                                    <th style="width: 20%">Attachments (CV/UT/AT)</th>
                                                    <%--<th style="width: 5%">Action</th>--%>
                                                </tr>
                                                </thead>
                                                <tbody class="existing-hr">
                                                <%--<tr>
                                                    <td>
                                                        <select name="contractorHRs[0].salutationId" required=""
                                                                class="form-control-sm custom-select-sm salutationId">
                                                            <option value="">SELECT</option>
                                                            <c:forEach var="item" items="${salutationList}">
                                                                <option value="${item.value}"><c:out
                                                                        value="${item.text}"/></option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>

                                                    <td>
                                                        <input type="text" name="contractorHRs[0].name"
                                                               size="10" class="form-control-sm name"
                                                               placeholder="Name">
                                                    </td>
                                                    <td>
                                                        <input type="text" name="contractorHRs[0].cidNo"
                                                               class="form-control-sm cidNo" required=""
                                                               placeholder="CID/Work Permit No">
                                                    </td>
                                                    <td><select name="contractorHRs[0].sex"
                                                                class="form-control-sm custom-select-sm sex">
                                                        <option value="">SELECT</option>
                                                        <option value="M">Male</option>
                                                        <option value="F">Female</option>
                                                    </select>
                                                    </td>
                                                    <td><select name="contractorHRs[0].countryId" required=""
                                                                class="form-control-sm custom-select-sm countryId">
                                                        <option value="">SELECT</option>
                                                        <c:forEach var="item" items="${countryList}">
                                                            <option value="${item.value}"><c:out
                                                                    value="${item.text}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                    </td>
                                                    <td>
                                                        <select name="contractorHRs[0].designationId"
                                                                class="form-control-sm designationId custom-select-sm">
                                                            <option value="">SELECT</option>
                                                            <c:forEach var="item" items="${designationList}">
                                                                <option value="${item.value}"><c:out
                                                                        value="${item.text}"/></option>
                                                            </c:forEach>

                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select name="contractorHRs[0].qualificationId"
                                                                 class="form-control-sm custom-select-sm qualificationId">
                                                            <option value="">SELECT</option>
                                                            <c:forEach var="item" items="${qualificationList}">
                                                                <option value="${item.value}"><c:out
                                                                        value="${item.text}"/></option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select name="contractorHRs[0].tradeId"
                                                                class="form-control-sm custom-select-sm tradeId">
                                                            <option value="">SELECT</option>
                                                            <c:forEach var="item" items="${tradeList}">
                                                                <option value="${item.value}"><c:out
                                                                        value="${item.text}"/></option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select name="contractorHRs[0].serviceTypeId"
                                                                class="form-control-sm custom-select-sm serviceTypeId">
                                                            <option value="">SELECT</option>
                                                            <c:forEach var="item" items="${serviceTypeList}">
                                                                <option value="${item.value}"><c:out
                                                                        value="${item.text}"/></option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td>

                                                    </td>
                                                    <td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2'><i class='fa fa-trash text-danger'></i></a></td>
                                                </tr>--%>
                                                <tr><th colspan="11"> Existing Human Resource List</th></tr>
                                                </tbody>
                                                <tbody class="newly-added-hr">
                                                    <tr><th colspan="11"> Newly Added Human Resource List</th></tr>
                                                </tbody>
                                                <tbody class="edited-hr">
                                                    <tr><th colspan="11"> Edited Human Resource List</th></tr>
                                                </tbody>
                                                <tbody class="deleted-hr">
                                                    <tr><th colspan="11"> Deleted Human Resource List</th></tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12 form-group nextBackBtn">
                                <button type="button"
                                        onclick="backTab('human_resource_criteria')"
                                        class="btn btn-azure col-lg-offset-9 backTab">
                                    <i class="fa fa-arrow-circle-left"></i> &nbsp;Back
                                </button>
                                <button type="button" id="btnValHRNext"
                                        onclick="nextTab('human_resource_criteria')"
                                        class="btn btn-primary nextTab">Next &nbsp;
                                    <i class="fa fa-arrow-circle-right"></i>
                                </button>
                            </div>
                        </div>

                        <div class="tab-pane equipment_details hide">
                            <div class="div-actual">
                                <i><strong>Contractor Equipment Details</strong></i>

                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped"
                                           id="equipmentTbl">
                                        <thead>
                                        <tr>
                                            <th>Equipment Name</th>
                                            <th>Registration Number</th>
                                            <th>Quantity</th>
                                            <th>Attachment</th>
                                            <%--<th>Action</th>--%>
                                        </tr>
                                        </thead>
                                        <tbody class="existing-eq">
                                        <tr><th colspan="4"> Existing Equipment List</th></tr>
                                        </tbody>
                                        <tbody class="edited-eq">
                                        <tr><th colspan="141"> Edited Equipment List</th></tr>
                                        </tbody>
                                        <tbody class="newly-added-eq">
                                        <tr><th colspan="4"> Newly Added Equipment List</th></tr>
                                        </tbody>
                                        <tbody class="deleted-eq">
                                        <tr><th colspan="4"> Deleted Equipment List</th></tr>
                                        </tbody>
                                    </table>

                                    <div class="col-lg-12 text-right">
                                        <button type="button" class="btn btn-info btn-sm"
                                                data-toggle="modal" data-target="#eqModal"><i
                                                class="fa fa-plus"></i> Add More Equipment
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12 form-group nextBackBtn">
                                <button type="button" onclick="backTab('equipment_details')" class="btn btn-azure col-lg-offset-9">
                                    <i class="fa fa-arrow-circle-left"></i>&nbsp; Back
                                </button>
                                <button type="button" class="btn btn-primary" id="btnValEqNext"
                                        onclick="nextTab('equipment_details')">
                                    Next &nbsp;
                                    <i class="fa fa-life-saver"></i>
                                </button>
                            </div>
                        </div>

                        <div class="tab-pane saveAndPreview">
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
                                    <button type="button" class="btn btn-primary" id="btnSendBack"><i
                                            class="fa fa-backward"></i>&nbsp;&nbsp;Send back
                                    </button>
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
            </form>
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
                    <button type="button" class="btn btn-warning" onclick="closemodel('confirmationModel')"><span class="fa fa-times"></span> No
                    </button>
                </div>
            </div>
        </div>
    </div>
    <script src="<c:url value="/resources/js/cdb/contractor/contractorRCAction.js"/>"></script>
</div>


</body>

