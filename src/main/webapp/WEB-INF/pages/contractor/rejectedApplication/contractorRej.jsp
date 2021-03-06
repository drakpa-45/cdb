<%@ page import="java.util.Date" %>
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
            <input type="hidden" name="cdbNo" value="${cdbNo}" id="cdbNo" >
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
                                        <td><input type="checkbox" style="zoom:1.6" name="incorporation" value="1" id="Incorporation" class="service_check"></td>
                                        <td><span>Incorporation</span></td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" style="zoom:1.6" name="changeOfFirmName" value="1" id="changeOfFirmName" class="service_check"></td>
                                        <td><span>Change of Firm Name</span></td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" style="zoom:1.6" name="changeOfLocation" value="1" class="service_check" id="changeOfLocation"></td>
                                        <td><span>Change of Location</span></td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" style="zoom:1.6" name="changeOfOwner" value="1" id="changeOfOwnerId" class="service_check"></td>
                                        <td><span>Change of Owner</span></td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" style="zoom:1.6" name="upgradeDowngrade" value="1" id="upgradeDowngrade" class="service_check"></td>
                                        <td><span>Upgrade/Downgrade/Add Category/Classification</span></td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" style="zoom:1.6" name="updateHR" value="1" id="updateHR" class="service_check"></td>
                                        <td><span>Update Human Resource</span></td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" style="zoom:1.6" name="updateEq" value="1" id="updateEq" class="service_check"></td>
                                        <td><span>Update Equipment</span></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-12 form-group pull-right">
                               <%-- <button type="button" onclick="backTab('services')" class="btn btn-azure col-lg-offset-9">
                                    <i class="fa fa-arrow-left"></i>Back
                                </button>--%>
                                <button type="button" onclick="nextTab('services')" class="btn btn-primary">
                                    <i class="fa fa-arrow-circle-right"></i>Next
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
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-4 form-label">Old Proposed Firm Name</label>
                                                <label class="col-lg-8 form-label form-control" id="oldfirmName"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card hide" id="cIncorporation">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header bg-indigo-light">
                                        <h3 class="card-title text-white">Attach Certificates of Incorporation</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-lg-12">
                                            <table class="table table-bordered table-center table-responsive-lg auto-index" id="IncCertificateTbl">
                                                <thead>
                                                <tr>
                                                    <th>Sl no</th>
                                                    <th>Document Attached</th>
                                                    <th>View/Download</th>
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
                                    <div class="card-header bg-indigo-light">
                                        <h3 class="card-title  text-white">Permanent Address</h3>
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

                                <div class="card tab2">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header bg-indigo-light">
                                        <h3 class="card-title  text-white">Name of Owner, Partners and/or others with Controlling Interest</h3>
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
                                                    <th>Title</th>
                                                    <th>Name</th>
                                                    <th>Gender</th>
                                                    <th>Designation</th>
                                                    <th>Show in certificate</th>
                                                    <th>Action</th>
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
                                                <tbody class="existing-ow">
                                                <tr><th colspan="11"> Existing Owner List</th></tr>
                                                </tbody>
                                                <tbody class="newly-added-ow">
                                                <tr><th colspan="11"> Newly Added Owner Resource List</th></tr>
                                                </tbody>
                                                <tbody class="edited-ow">
                                                <tr><th colspan="11"> Edited Owner Resource List</th></tr>
                                                </tbody>
                                                <tbody class="deleted-ow">
                                                <tr><th colspan="11"> Deleted Owner Resource List</th></tr>
                                                </tbody>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="card hide" id="oIncorporation">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header bg-indigo-light">
                                        <h3 class="card-title  text-white">Attach Certificates of ownership change</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-lg-12">
                                            <table class="table table-bordered table-center table-responsive-lg auto-index" id="OcCertificateTbl">
                                                <thead>
                                                <tr>
                                                    <th>Sl no</th>
                                                    <th>Document Attached</th>
                                                    <th>View/Download</th>
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
                                    <div class="card-header bg-indigo-light">
                                        <h3 class="card-title text-white">Existing Establishment Addresses</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Establishment Address</label>
                                                <label class="col-lg-7 form-label form-control" id="estAddressExist"></label>
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Dzongkhag</label>
                                               <%-- <form:select id="estDzongkhagExist"
                                                             class="form-control input-sm col-lg-7" required="true"
                                                             path="dzongkhagList">
                                                    <form:option value="" label="Select Dzongkhag"/>
                                                    <form:options items="${dzongkhagList}" itemValue="value"
                                                                  itemLabel="text"/>
                                                </form:select>--%>
                                                <label class="col-lg-7 form-label form-control" id="estDzongkhagExist"></label>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Email</label>
                                                <label class="col-lg-7 form-label form-control" id="regEmailExist"></label>
                                                <input type="hidden" id="regEmail">
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Mobile Number</label>
                                                <label class="col-lg-7 form-label form-control" id="regMobileNoExist"></label>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Telephone Number </label>
                                                <label class="col-lg-7 form-label form-control" id="regPhoneNoExist"></label>
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">
                                                <label class="col-lg-5 form-label">Fax Number </label>
                                                <label class="col-lg-7 form-label form-control" id="regFaxNoExist"></label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="card-header">
                                        <h3 class="card-title text-danger">Proposed Establishment Addresses</h3>
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

                                <div class="card tab2">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header bg-indigo-light">
                                        <h3 class="card-title  text-white">Induction course Details</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-lg-12">
                                            <table class="table table-bordered table-center table-responsive-lg auto-index" id="inductionCourseDtl">
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
                            </div>
                            <div class="col-lg-12 form-group">
                                <button type="button" onclick="backTab('generalInformation')" class="btn btn-azure col-lg-offset-9">
                                    <i class="fa fa-arrow-circle-left"></i> &nbsp;Back
                                </button>
                                <button type="button" onclick="nextTab('generalInformation')" disabled id="nextGIBtn" class="btn btn-primary">
                                    <i class="fa fa-arrow-circle-right"></i>Next
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
                                <table class="table table-bordered table-center table-responsive-lg auto-index" id="certificateTblCategory">
                                    <thead>
                                    <tr>
                                        <th>Sl No</th>
                                        <th>Document Attached</th>
                                        <th>View/Download</th>
                                    </tr>
                                    </thead>
                                    <tbody class="files">
                                   <%-- <tr><td>
                                        <input type='hidden' class='form-control aFor' name='cAttachments[0].attachmentFor' value='C'/>
                                        <input type='text' required="" class='form-control docName' name='cAttachments[0].documentName'/> </td>
                                        <td><input type='file' required="" class='file' name='cAttachments[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg'/> </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'> <a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                    </tr>--%>
                                    </tbody>
                                </table>
                            </div>
                            <div class="card tab2">
                                <div class="bg-blue card-status card-status-left"></div>
                                <div class="card-header bg-indigo-light">Service Applied and Fee</div>
                                <div class="card-body">
                                    <table class="table table-bordered table-condensed table-striped" id="serviceTbl">
                                        <thead>
                                        <tr>
                                            <th style="width: 5%"></th>
                                            <th style="width: 60%">Service Name</th>
                                            <th style="width: 25%">Fees (Nu.)</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                        <tfoot>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                            <div class="col-lg-12 form-group nextBackBtn">
                                <button type="button" onclick="backTab('category_details')" class="btn btn-azure col-lg-offset-9">
                                    <i class="fa fa-arrow-circle-left"></i> &nbsp; Back
                                </button>
                                <button type="button" id="btnValCCNext" class="btn btn-primary" onclick="nextTab('category_details')">Next &nbsp;
                                    <i class="fa fa-arrow-circle-right"></i>
                                </button>
                            </div>
                        </div>

                        <div class="tab-pane human_resource_criteria hide">
                            <div class="div-actual">
                                <div class="card tab4">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header">
                                        <h3 class="card-title"><div class="card-header bg-indigo-light">Human Resource Form</h3>
                                    </div>
                                    <div class="card-body">
                                        <div style="overflow-x: auto;white-space: nowrap;">
                                            <table class="table table-bordered" id="hrDtlsTable">
                                                <thead>
                                                <tr class="table-dark">
                                                    <th style="width: 5%">Title</th>
                                                    <th style="width: 15%">Name</th>
                                                    <th style="width: 15%">ID/Work Permit No.</th>
                                                    <th style="width: 10%">Gender</th>
                                                    <th style="width: 10%">Country</th>
                                                    <th style="width: 10%">Designation</th>
                                                    <th style="width: 10%">Qualification</th>
                                                    <th style="width: 10%">Trade / Fields</th>
                                                    <th style="width: 10%">Service Type</th>
                                                    <th style="width: 10%">Joining Date</th>
                                                    <th style="width: 20%">Attachments (CV/UT/AT)</th>
                                                    <th style="width: 5%">Action</th>
                                                    <security:authorize access="hasRole('ROLE_VERIFIER')">
                                                        <th style="width: 10%">Verify</th>
                                                    </security:authorize>
                                                    <security:authorize access="hasRole('ROLE_APPROVER')">
                                                        <th style="width: 10%">Verify</th>
                                                        <th style="width: 10%">Approve</th>
                                                    </security:authorize>
                                                </tr>
                                                </thead>
                                                <tbody class="existing-hr">
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
                                        <security:authorize access="hasRole('ROLE_APPROVER')">
                                            <span class="text-danger"> Would you like to send Hr replacement notification on approval? Please tick if you wish to:<input type='checkbox' name='contractorHRs[0].sendNotification' id= "sendNotification" style='zoom:1.6' value='1'></span>
                                        </security:authorize>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12 form-group nextBackBtn">
                                <button type="button" onclick="backTab('human_resource_criteria')" class="btn btn-azure col-lg-offset-9 backTab">
                                    <i class="fa fa-arrow-circle-left"></i> &nbsp;Back
                                </button>
                                <button type="button" id="nextHRBtn" disabled onclick="nextTab('human_resource_criteria')" class="btn btn-primary nextTab">Next &nbsp;
                                    <i class="fa fa-arrow-circle-right"></i>
                                </button>
                            </div>
                        </div>

                        <div class="tab-pane equipment_details hide">
                            <div class="div-actual">
                                <i><strong> Equipment Details</strong></i>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped"
                                           id="equipmentTbl">
                                        <thead>
                                        <tr>
                                            <th>Equipment Name</th>
                                            <th>Registration Number</th>
                                            <th>Quantity</th>
                                            <th>Attachment</th>
                                            <th>Action</th>
                                            <security:authorize access="hasRole('ROLE_VERIFIER')">
                                                <th style="width: 10%">Verify</th>
                                            </security:authorize>
                                            <security:authorize access="hasRole('ROLE_APPROVER')">
                                                <th style="width: 10%">Verify</th>
                                                <th style="width: 10%">Approve</th>
                                            </security:authorize>
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

                                </div>
                            </div>
                            <div class="col-lg-12 form-group nextBackBtn">
                                <button type="button" onclick="backTab('equipment_details')" class="btn btn-azure col-lg-offset-9">
                                    <i class="fa fa-arrow-circle-left"></i>&nbsp; Back
                                </button>
                                <button type="button" class="btn btn-primary" id="btnValEqNext" disabled onclick="nextTab('equipment_details')">Next &nbsp;
                                    <i class="fa fa-life-saver"></i>
                                </button>
                            </div>
                        </div>
                        <div class="tab-pane saveAndPreview">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="table-responsive">
                                        <fieldset><h3>Application Status</h3>
                                            <table id="appStatusTbl" width="1000px" cellpadding="1" cellspacing="1" border="1" style="border-collapse: collapse" class="table table-bordered table-dark">
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
                                   <%-- <button type="button" class="btn btn-primary" id="btnSendBack"><i
                                            class="fa fa-backward"></i>&nbsp;&nbsp;Send back
                                    </button>--%>
                                </security:authorize>
                                <button type="button" class="btn btn-danger " id="btnReject"><i class="fa fa-times mr-1"></i>Reject</button>
                                <a href="<c:url value="/admin/contractor"/>">
                                    <button type="button" class="btn btn-warning"><i class="fa fa-ban"></i>Cancel</button>
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

    <%--HR Modal--%>
    <div id="hrModal" class="modal fade in " tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" style=" max-width: 900px;">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 id="myModalLabel" class="modal-title"><i class="fa fa-info-circle fa-lg"></i> Personal Check<span id="cid"></span></h4>
                </div>
                <div class="modal-body">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div id="modal-print">
                                <div class="form-group">
                                    <p align="center"><strong><u>Caution</u></strong></p>

                                    <p align="center"><strong>An engineer is allowed to execute only two work at a time for that particular firm.</strong></p>
                                    <p align="center"><strong>For any other Human Resource they are allowed to involve only in a single project of work</strong></p>
                                    <p align="center"><font size="5px;">Details of CID No: <span id="cidchecked"></span></font></p>
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
                                        <%--<p id="notEngagedId" style="display: none"><span id="dcbinfo"><b>Human Resource is not registered in any of the
                                            CDB firm</b><br><br> This person is not engaged in any work or project<br>This person is not a civil servant</span>
                                        </p>--%>
                                        <div class="tab-pane employeeDetails" id="engagedId">
                                            <div class="form-group">
                                                <div class="col-lg-12" align="center">
                                                    <p><span id="dcbinfo"><b>The Individual holding CID/permit no.<label id="cidNumber"></label>(<label id="hrName"></label>) is engaged in following project(s):</b></span></p>
                                                    <p><span id="dcbinfonotEngaged"></span></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <hr />
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
                                    <p align="center"><strong><u><font size="3px;">Caution</font></u></strong></p>
                                    <p align="center"><strong>An equipment is allowed to execute only one work at a time for the perticular firm.</strong></p>
                                    <p align="center"><font size="5px;">Details of Registration No: <span id="regchecked"></span></font></p>
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
    <script src="<c:url value="/resources/js/cdb/contractor/contractorRCAction.js"/>"></script>
</div>

</body>

