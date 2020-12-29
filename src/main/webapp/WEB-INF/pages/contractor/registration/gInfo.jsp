<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11/22/2019
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<form name="contractorGIForm" contractor="contractor/saveGI" method="POST" class="globalForm"
      id="contractorGIForm" enctype="multipart/form-data" role="form">--%>
<div class="div-actual">
    <div class="card" id="gInfo">
        <div class="bg-blue card-status card-status-left"></div>
        <div class="card-body">
            <div class="form-group row">
                <div class="col-lg-6 col-md-6 ">
                    <label class="col-lg-4 form-label">Ownership Type
                        <span class="text-danger">*</span></label>
                    <form:select id="ownershipList" class="form-control col-lg-7" required="true" path="ownershipList" name="contractor.ownershipTypeId">
                        <form:option value="" label="Select Ownership Type"/>
                        <form:options items="${ownershipList}" itemValue="value" itemLabel="text"/>
                    </form:select>
                </div>
                <div class="col-lg-6 col-md-6">
                    <label class="col-lg-4 form-label">Country <span class="text-danger">*</span></label>
                    <select class="form-control col-lg-7" required="true" name="contractor.pCountryId" id="pCountryId">
                        <c:forEach var="item" items="${countryList}">
                            <option value="${item.value}"><c:out value="${item.text}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6 col-lg-6">
                    <label class="col-lg-4 form-label">Trade License No</label>
                    <input type="text" class="col-lg-7 form-control" name="contractor.tradeLicenseNo" placeholder="Text.." id="tradeLicenseNo">
                </div>
                <div class="col-lg-6 col-lg-6">
                    <label class="col-lg-4 form-label">Proposed Firm Name <span class="text-danger">*</span></label>
                    <input type="text" class="col-lg-7 form-control" id="firmName" name="contractor.firmName" required="true" placeholder="Text..">
                    <br/><br/><i class="afterNameMsg text-blue"></i>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6 col-md-6">
                    <label class="col-lg-4 form-label">TPN Number</label>
                    <input type="text" class="col-lg-7 form-control" name="contractor.tpn" id="tpn" placeholder="Text..">
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
                <div class=""><input id="addMoreCert" type="button" value="Add More Certificate" class="btn btn-primary"></div>
                <table class="table table-bordered table-center table-responsive-lg auto-index" id="certificateTbl">
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
    <div class="card">
        <div class="bg-blue card-status card-status-left"></div>
        <div class="card-header">
            <h3 class="card-title">Name of Owner, Partners and/or others with Controlling Interest</h3>
        </div>
        <div class="card-body">
            <div class="col-lg-12">
                <table class="table table-bordered table-center table-responsive-lg" id="partnerDtls">
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
                            <form:select id="countryList" class="form-control country" name="contractor.contractorHRs[0].countryId" data-rule-required="true" path="countryList">
                                <form:options items="${countryList}" itemValue="value" itemLabel="text"/>
                            </form:select>
                        </td>
                        <td>
                            <input type="text" name="contractor.contractorHRs[0].cidNo" class="form-control hr-cid ownerCidNo" placeholder="Text.." required="true">
                        </td>
                        <td>
                            <form:select id="salutation" name="contractor.contractorHRs[0].salutationId" class="form-control input-sm" data-msg-required="true" data-rule-required="true" path="salutationList">
                                <form:option value="" label="Select Salutation"/>
                                <form:options items="${salutationList}" itemValue="value" itemLabel="text"/>
                            </form:select>
                        </td>
                        <td>
                            <input type="text" class="form-control name" id="name" name="contractor.contractorHRs[0].name" placeholder="Text.." required="true">
                        </td>
                        <td>
                            <select id="gender" name="contractor.contractorHRs[0].sex" class="form-control sex" required="true">
                                <option value="">Select Gender</option>
                                <option value="M">Male</option>
                                <option value="F">Female</option>
                            </select>
                        </td>
                        <td>
                            <form:select id="designation" name="contractor.contractorHRs[0].designationId" class="form-control input-sm" data-msg-required="" data-rule-required="true" path="designationList">
                                <form:option value="" label="Select Designation"/>
                                <form:options items="${designationList}" itemValue="value" itemLabel="text"/>
                            </form:select>
                        </td>
                        <td>
                            <label class="custom-control custom-checkbox ml-6">
                                <input type="checkbox" class="custom-control-input showCert" id="siCertificate" name="contractor.contractorHRs[0].siCertificate" value="1">
                                <i class="custom-control-label"></i>
                            </label>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="col-lg-12 text-right">
                    <%-- <b class="text-orange"><marque>Note:Please Tick(✔) in "Show in Certificate" field to display your name in Certificate.</marque></b> &nbsp; &nbsp;--%>
                    <button type="button" class="btn btn-outline-primary btn-sm" onclick="addRow('partnerDtls')">
                        <i class="fe fe-plus mr-2"></i>Add More
                    </button>
                    <button type="button" class="btn btn-outline-danger btn-sm" onclick="removeRow('partnerDtls')">
                        <i class="fe fe-trash mr-2"></i>Remove Last Row
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="card ">
        <div class="bg-blue card-status card-status-left"></div>
        <div class="card-header">
            <h3 class="card-title">Permanent Address</h3>
        </div>
        <div class="card-body">
            <div class="col-lg-12">
                <div class="col-lg-6 col-md-6 form-group">
                    <label class="col-lg-4 form-label">Dzongkhag</label>
                    <select id="pDzongkhagId" class="form-control input-sm col-lg-8" name="contractor.pDzongkhagId">
                        <option value="">Select Dzongkhag</option>
                        <c:forEach var="item" items="${dzongkhagList}">
                            <option value="${item.value}"><c:out value="${item.text}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-lg-6 col-md-6 form-group">
                    <label class="col-lg-4 form-label">Gewog </label>
                    <select name="contractor.pGewog" id="pGewogId" class="form-control col-lg-8"></select>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="col-lg-6 col-md-6 form-group">
                    <label class="col-lg-4 form-label">Village </label>
                    <select name="contractor.pVillage" id="pVillageId" class="form-control col-lg-8"></select>
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
                <table class="table table-bordered table-center table-responsive-lg" id="inductionCourseDtl">
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
            <div class="form-group row">
                <div class="col-lg-6 col-md-6 ">
                    <label class="col-lg-4 form-label" for="estAddress">Establishment Address <span class="text-danger">*</span></label>
                    <input type="text" class="col-lg-7 form-control" name="contractor.estAddress" id="estAddress" required="true" placeholder="Text..">
                </div>
                <div class="col-lg-6 col-md-6">
                    <label class="col-lg-4 form-label">Dzongkhag <span class="text-danger">*</span></label>
                    <form:select id="regDzongkhagId" class="form-control input-sm col-lg-7" required="true" path="dzongkhagList" name="contractor.regDzongkhagId">
                        <form:option value="" label="Select Dzongkhag"/>
                        <form:options items="${dzongkhagList}" itemValue="value" itemLabel="text"/>
                    </form:select>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6 col-md-6">
                    <label class="col-lg-4 form-label">Email <span class="text-danger">*</span></label>
                    <input type="email" class=" form-control col-lg-7" name="contractor.regEmail" id="regEmail" required="true" placeholder="Email address">
                </div>
                <div class="col-lg-6 col-md-6">
                    <label class="col-lg-4 form-label">Confirm Email <span class="text-danger">*</span></label>
                    <input type="email" class=" form-control col-lg-7" name="contractor.confirmEmail" id="confirmEmail" required="true" placeholder="Confirm Email address">
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6 col-md-6 ">
                    <label class="col-lg-4 form-label">Mobile No <span class="text-danger">*</span></label>
                    <input type="text" class=" form-control col-lg-7 number" name="contractor.regMobileNo" id="regMobileNo" required="true" placeholder="Mobile Number" maxlength="13">
                </div>
                <div class="col-lg-6 col-md-6">
                    <label class="col-lg-4 form-label">Telephone No </label>
                    <input type="text" class="form-control col-lg-7 number" name="contractor.regPhoneNo" id="regPhoneNo" placeholder="Phone Number" maxlength="13">
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6 col-md-6">
                    <label class="col-lg-4 form-label">Fax No <span class="text-danger"></span></label>
                    <input type="text" class=" form-control col-lg-7 number" name="contractor.regFaxNo" placeholder="Fax No" maxlength="12">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-12 form-group">
    <button type="button" onclick="backTab('generalInformation','fees_structure')" class="btn btn-azure col-lg-offset-9">
        <i class="fa fa-arrow-circle-left"></i> &nbsp;Back
    </button>
    <button type="button" id="btnValGINext" class="btn btn-primary">Next &nbsp;<i class="fa fa-arrow-circle-right"></i></button>
</div>
<%--</form>--%>

