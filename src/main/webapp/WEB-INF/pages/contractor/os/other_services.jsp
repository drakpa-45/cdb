<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body class="">
<c:if test="${not empty res}">

<div class="card hide" id="acknowledgment">
    <div class="card-header">
        <b>Contractor >> Renewal Application >> </b>Acknowledgement
    </div>
    <div class="card-body">
        <c:if test="${res.status eq 1}">
            ${res.text}
        </c:if>
        <c:if test="${res.status eq 0}">
            <label class="form-control error"><spring:message code="${res.text}"/></label>
        </c:if>

    </div>
</div>
</c:if>
<div class="mt-5">
    <div class="container mb-9">
        <div class="row">

                <div class="card" id="registrtaionFormCard">
                    <div class="card-header">
                        <h3 class="card-title font-weight-bold">Other Services</h3>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12 col-lg-12">
                                <form action="" method="post" class="" id="contractorRenewalForm">
                                <div class="nav-tabs-custom">
                                    <ul class="m-0 nav nav-tabs">
                                        <li class="feesStructure tab-pane active ">
                                            <a href="#fees_structure" class="border text-white" data-toggle="tab"
                                               data-placement="top">
                                                <i class="fa fa-bookmark mr-1"></i>Fee Structure</a>
                                        </li>
                                        <li class="tab-pane services">
                                            <a href="#services" class=" border" data-toggle="tab" data-placement="top">
                                                <i class="fa fa-exclamation-circle mr-1"></i>Services</a>
                                        </li>
                                        <li class="tab-pane generalInformation">
                                            <a href="#general_Information" class=" border" data-toggle="tab"
                                               data-placement="top">
                                                <i class="fa fa-exclamation-circle mr-1"></i>General Information</a>
                                        </li>


                                        <li class="tab-pane saveAndPreview">
                                            <a href="#saveAndPreview" class="border" data-toggle="tab"
                                               data-placement="top">
                                                <i class="fa fa-file mr-1"></i>Preview</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content border p-3 col-lg-12">
                                        <div class="active feesStructure mt-5 tab-pane">
                                            <div class="div-actual">
                                                <div class="form-group ">
                                                    <h5 class="text-danger">Note</h5>

                                                    <p class="mb-5 text-justify">
                                                        You can apply for multiple services in this section of your
                                                        application together. Relevant fees will be applicable. You can
                                                        skip services if you wish to apply for only one service. Below
                                                        is the list of all the services that you can avail with this
                                                        application.
                                                    </p>
                                                    <table class="table table-bordered table-condensed table-striped">
                                                        <thead>
                                                        <tr>
                                                            <th>Service Name</th>
                                                            <th>Fees (Nu.)</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>Incorporation</td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Firm Name Change</td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Change of Location</td>
                                                            <td>500.00</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Change of Owner/Partner and other Controlling interest
                                                            </td>
                                                            <td>1000.00</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Upgrade/Down grade/Add Category or Classification</td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Update Human Resource</td>
                                                            <td>Not Applicable</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Update Equipment</td>
                                                            <td>Not Applicable</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>

                                                </div>
                                            </div>
                                            <div class="col-lg-12 form-group">
                                                <div class="col-md-offset-11 col-lg-offset-10 col-xs-offset-10">
                                                    <button type="button" onclick="nextTab('feesStructure','services')"
                                                            class="btn btn-primary">
                                                        <i class="fa fa-arrow-right mr-2"></i>Next
                                                    </button>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="tab-pane services">
                                            <div class="panel-body table-responsive div-actual" >
                                  <span>
                                    Would you like to avail listed below along with this application? Please tick on the check box if you wish to.
                                  </span>
                                                <table >
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
                                                                   id="changeOfLocation" checked="checked">
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
                                                        onclick="nextTab('feeStructure','services')"
                                                        class="btn btn-azure col-lg-offset-9">
                                                    <i class="fa fa-arrow-left"></i>
                                                    Back
                                                </button>
                                                <button type="button" onclick="nextTab('services','generalInformation')"
                                                        class="btn btn-primary">
                                                    <i class="fa fa-arrow-right"></i>
                                                    Save &amp; Next
                                                </button>
                                            </div>
                                        </div>

                                        <div class="tab-pane generalInformation">

                                                    <div class="div-actual">
                                                        <div class="card" id="gInfo">
                                                            <div class="bg-blue card-status card-status-left"></div>
                                                            <div class="card-body">

                                                                <div class="form-group row">
                                                                    <div class="col-lg-6 col-md-6 ">
                                                                        <label class="col-lg-4 form-label">Ownership Type
                                                                            <span class="text-danger">*</span></label>

                                                                        <form:select id="ownershipList" class="form-control col-lg-7"
                                                                                     required="true" path="ownershipList" name="contractor.ownershipTypeId">
                                                                            <form:option value="" label="Select Ownership Type"/>
                                                                            <form:options items="${ownershipList}" itemValue="value"
                                                                                          itemLabel="text"/>
                                                                        </form:select>
                                                                    </div>

                                                                    <div class="col-lg-6 col-md-6">
                                                                        <label class="col-lg-4 form-label">Country <span
                                                                                class="text-danger">*</span></label>

                                                                        <select class="form-control col-lg-7" required="true"
                                                                                name="contractor.pCountryId" id="pCountryId">
                                                                            <c:forEach var="item" items="${countryList}">
                                                                                <option value="${item.value}"><c:out value="${item.text}"/></option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                </div>

                                                                <div class="form-group row">
                                                                    <div class="col-lg-6 col-lg-6">
                                                                        <label class="col-lg-4 form-label">Trade License
                                                                            No</label>
                                                                        <input type="text" class="col-lg-7 form-control"
                                                                               name="contractor.tradeLicenseNo" required="true"
                                                                               placeholder="Text.." id="tradeLicenseNo">
                                                                    </div>
                                                                    <div class="col-lg-6 col-lg-6">
                                                                        <label class="col-lg-4 form-label">Proposed Firm
                                                                            Name <span class="text-danger">*</span></label>
                                                                        <input type="text" class="col-lg-7 form-control" id="firmName"
                                                                               name="contractor.firmName" required="true" placeholder="Text..">
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <div class="col-lg-6 col-md-6">
                                                                        <label class="col-lg-4 form-label">TPN Number</label>
                                                                        <input type="text" class="col-lg-7 form-control"
                                                                               name="contractor.tpn" id="tpn"
                                                                               placeholder="Text..">
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
                                                                    <div class=""><input id="addMoreCert" type="button" value="Add More Certificate" class="btn btn-primary"> </div>
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
                                                            <div class="card-body" >
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
                                                                                <form:select id="countryList" class="form-control"
                                                                                             name="contractor.contractorHRs[0].countryId"
                                                                                             data-msg-required="" data-rule-required="true" path="countryList">
                                                                                    <form:option value="" label="Select Country"/>
                                                                                    <form:options items="${countryList}" itemValue="value"
                                                                                                  itemLabel="text"/>
                                                                                </form:select>
                                                                            </td>
                                                                            <td>
                                                                                <input type="text" name="contractor.contractorHRs[0].cidNo" class="form-control hr-cid"
                                                                                       placeholder="Text..">
                                                                            </td>
                                                                            <td>
                                                                                <form:select id="salutation" name="contractor.contractorHRs[0].salutationId"
                                                                                             class="form-control input-sm"
                                                                                             data-msg-required="true" data-rule-required="true"
                                                                                             path="salutationList">
                                                                                    <form:option value="" label="Select Salutation"/>
                                                                                    <form:options items="${salutationList}" itemValue="value"
                                                                                                  itemLabel="text"/>
                                                                                </form:select>
                                                                            </td>
                                                                            <td>
                                                                                <input type="text" class="form-control name" name="contractor.contractorHRs[0].name"
                                                                                       placeholder="Text..">
                                                                            </td>
                                                                            <td>
                                                                                <select id="gender" name="contractor.contractorHRs[0].sex" class="form-control sex">
                                                                                    <option value="">Select Gender</option>
                                                                                    <option value="M">Male</option>
                                                                                    <option value="F">Female</option>
                                                                                </select>

                                                                            </td>
                                                                            <td>
                                                                                <form:select id="designation" name="contractor.contractorHRs[0].designationId"
                                                                                             class="form-control input-sm"
                                                                                             data-msg-required="" data-rule-required="true"
                                                                                             path="designationList">
                                                                                    <form:option value="" label="Select Designation"/>
                                                                                    <form:options items="${designationList}" itemValue="value" itemLabel="text"/>
                                                                                </form:select>

                                                                            </td>
                                                                            <td>
                                                                                <label class="custom-control custom-checkbox ml-6">
                                                                                    <input type="checkbox" class="custom-control-input showCert"
                                                                                           name="contractor.contractorHRs[0].siCertificate" value="1">
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

                                                        <div class="card ">
                                                            <div class="bg-blue card-status card-status-left"></div>
                                                            <div class="card-header">
                                                                <h3 class="card-title">Permanent Address</h3>
                                                            </div>
                                                            <div class="card-body">
                                                                <div class="col-lg-12">
                                                                    <div class="col-lg-6 col-md-6 form-group">
                                                                        <label class="col-lg-4 form-label">Dzongkhag</label>
                                                                        <select id="pDzongkhagId"
                                                                                class="form-control input-sm col-lg-8"
                                                                                name="contractor.pDzongkhagId">
                                                                            <option value="">Select Dzongkhag</option>
                                                                            <c:forEach var="item" items="${dzongkhagList}">
                                                                                <option value="${item.value}"><c:out
                                                                                        value="${item.text}"/></option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-lg-6 col-md-6 form-group">
                                                                        <label class="col-lg-4 form-label">Gewog </label>
                                                                        <select name="contractor.pGewogId" id="pGewogId" class="form-control col-lg-8">
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-12">
                                                                    <div class="col-lg-6 col-md-6 form-group">
                                                                        <label class="col-lg-4 form-label">Village </label>
                                                                        <select name="contractor.pVillageId" id="pVillageId" class="form-control col-lg-8">

                                                                        </select>
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
                                                                <div class="form-group row">
                                                                    <div class="col-lg-6 col-md-6 ">
                                                                        <label class="col-lg-4 form-label" for="estAddress">Establishment
                                                                            Address <span
                                                                                    class="text-danger">*</span></label>
                                                                        <input type="text" class="col-lg-7 form-control"
                                                                               name="contractor.estAddress" id="estAddress" required="true"
                                                                               placeholder="Text..">
                                                                    </div>
                                                                    <div class="col-lg-6 col-md-6">
                                                                        <label class="col-lg-4 form-label">Dzongkhag <span
                                                                                class="text-danger">*</span></label>
                                                                        <form:select id="regDzongkhagId"
                                                                                     class="form-control input-sm col-lg-7" required="true"
                                                                                     path="dzongkhagList" name="contractor.regDzongkhagId">
                                                                            <form:option value="" label="Select Dzongkhag"/>
                                                                            <form:options items="${dzongkhagList}" itemValue="value"
                                                                                          itemLabel="text"/>
                                                                        </form:select>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <div class="col-lg-6 col-md-6">
                                                                        <label class="col-lg-4 form-label">Email <span
                                                                                class="text-danger">*</span></label>
                                                                        <input type="email" class=" form-control col-lg-7"
                                                                               name="contractor.regEmail" id="regEmail" required="true"
                                                                               placeholder="Text..">
                                                                    </div>
                                                                    <div class="col-lg-6 col-md-6 ">
                                                                        <label class="col-lg-4 form-label">Mobile No <span
                                                                                class="text-danger">*</span></label>
                                                                        <input type="text" class=" form-control col-lg-7"
                                                                               name="contractor.regMobileNo" id="regMobileNo" required="true"
                                                                               placeholder="Text..">
                                                                    </div>
                                                                </div>
                                                                <div class="form-group row">
                                                                    <div class="col-lg-6 col-md-6">
                                                                        <label class="col-lg-4 form-label">Telephone No </label>
                                                                        <input type="text" class="form-control col-lg-7"
                                                                               name="contractor.regPhoneNo" id="regPhoneNo"
                                                                               placeholder="Text..">
                                                                    </div>
                                                                    <div class="col-lg-6 col-md-6">
                                                                        <label class="col-lg-4 form-label">Fax No <span
                                                                                class="text-danger">*</span></label>
                                                                        <input type="text" class=" form-control col-lg-7"
                                                                               name="contractor.regFaxNo" required="true"
                                                                               placeholder="Text..">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                               <%-- <div class="">
                                                    <!-- Box Open -->
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12">
                                                                &lt;%&ndash;<div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Ownership Type
                                                                        <span class="text-danger">*</span></label>
                                                                    <label class="col-lg-8 form-label">Sole
                                                                        Proprietorship</label>
                                                                </div>&ndash;%&gt;
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Ownership Type
                                                                        <span class="text-danger">*</span></label>

                                                                    <form:select id="ownershipList" class="form-control col-lg-7"
                                                                                 required="true" path="ownershipList" name="contractor.ownershipTypeId">
                                                                        <form:option value="" label="Select Ownership Type"/>
                                                                        <form:options items="${ownershipList}" itemValue="value"
                                                                                      itemLabel="text"/>
                                                                    </form:select>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Country <span
                                                                            class="text-danger">*</span></label>
                                                                    <label class="col-lg-8 form-label">Belgium</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Trade License
                                                                        No</label>
                                                                    <label class="col-lg-8 form-label">1060001</label>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Proposed Firm
                                                                        Name <span class="text-danger">*</span></label>
                                                                    <label class="col-lg-8 form-label">ABC Firm
                                                                        Name</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">TPN
                                                                        Number</label>
                                                                    <label class="col-lg-8 form-label">BIT123123</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Box Close -->
                                                    <div class="card hide" id="cIncorporation">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Attach Certificates of Incorporation</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12">
                                                                <div class=""><input id="addMoreCert" type="button" value="Add More Certificate" class="btn btn-primary"> </div>
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

                                                    <div id="CheckModal" class="modal fade in " tabindex="-1"
                                                         role="dialog" aria-labelledby="myModalLabel"
                                                         style="display: none; padding-right: 6px;">
                                                        <div class="modal-dialog" style=" max-width: 900px;">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h4 id="myModalLabel" class="modal-title"><i
                                                                            class="fa fa-info-circle fa-lg"></i>
                                                                        Personal Check<span id="cid"></span></h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="panel panel-default">
                                                                        <div class="panel-body">
                                                                            <div id="modal-print">
                                                                                <div class="form-group">
                                                                                    <p align="center">
                                                                                        <strong><u>Caution</u></strong>
                                                                                    </p>

                                                                                    <p align="center"><strong>An
                                                                                        engineer is allowed to execute
                                                                                        only two work at a time for that
                                                                                        particular firm.</strong></p>

                                                                                    <p align="center"><strong>For any
                                                                                        other Human Resource they are
                                                                                        allowed to involve only in a
                                                                                        single project of work</strong>
                                                                                    </p>

                                                                                    <p align="center"><font size="5px;">Details
                                                                                        of CID No: <span
                                                                                                id="cidchecked">123213</span></font>
                                                                                    </p>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <div class="col-lg-9 mt-8">
                                                                                        <span class=""><b>From DCRC
                                                                                            database</b></span>

                                                                                        <div class="col-lg-12 form-group mb-0 pt-4">
                                                                                            <label class="col-lg-3 form-label">Sex</label>
                                                                                            <label class="col-lg-8 form-label">Male</label>
                                                                                        </div>
                                                                                        <div class="col-lg-12 form-group mb-0">
                                                                                            <label class="col-lg-3 form-label">Name</label>
                                                                                            <label class="col-lg-8 form-label">Tshewan
                                                                                                Tenzin</label>
                                                                                        </div>
                                                                                        <div class="col-lg-12 form-group mb-0">
                                                                                            <label class="col-lg-3 form-label">Dzongkhag</label>
                                                                                            <label class="col-lg-8 form-label">Samtse</label>
                                                                                        </div>
                                                                                        <div class="col-lg-12 form-group mb-0">
                                                                                            <label class="col-lg-3 form-label">Gewog</label>
                                                                                            <label class="col-lg-8 form-label">Samtse</label>
                                                                                        </div>
                                                                                        <div class="col-lg-12 form-group mb-0">
                                                                                            <label class="col-lg-3 form-label">Village</label>
                                                                                            <label class="col-lg-8 form-label">Samtse</label>
                                                                                        </div>
                                                                                        <div class="col-lg-12 form-group mb-0">
                                                                                            <label class="col-lg-3 form-label">DOB</label>
                                                                                            <label class="col-lg-8 form-label">1991-01-01</label>
                                                                                        </div>

                                                                                    </div>
                                                                                    <div class="col-lg-3"
                                                                                         align="center">
                                                                                        <br><br><span id="photoSpan"
                                                                                                      style=""><img
                                                                                            src="../assets/images/ip.jpeg"></span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <div class="col-lg-12"
                                                                                         align="center">
                                                                                        <p><span id="dcbinfo"><b>Human
                                                                                            Resource is not registered
                                                                                            in any of the CDB
                                                                                            firm</b><br><br> This person is not engaged in any work or project<br>This personis not a civil servant</span>
                                                                                        </p>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <p align="center">With regard to
                                                                                        Corporate Employee please verify
                                                                                        with the concern agencies. There
                                                                                        may be certain
                                                                                        inconsistency.</p>
                                                                                    <hr>
                                                                                    <p align="center"><strong>Print this
                                                                                        page as an evidence to prove
                                                                                        that particular HR is engaged or
                                                                                        not in a work or
                                                                                        project</strong></p>

                                                                                    <p align="center">
                                                                                        Printed on: <span id="dateSpan"
                                                                                                          style="display: none;"></span>

                                                                                        By:Ugyen Doelma
                                                                                    </p>
                                                                                </div>
                                                                            </div>
                                                                            <button type="button"
                                                                                    class="btn btn-primary"
                                                                                    onclick="javascript:printDiv('modal-print')">
                                                                                Print
                                                                            </button>
                                                                            <button type="button"
                                                                                    class="btn btn-success"
                                                                                    id="closeModal"
                                                                                    onclick="checkBtn('checkver1')"
                                                                                    data-dismiss="modal">OK
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </div>
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
                                                                    <label class="col-lg-8 form-label">Bumthang</label>

                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Gewog </label>
                                                                    <label class="col-lg-8 form-label">Chokhor</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Village </label>
                                                                    <label class="col-lg-8 form-label">Chamkhar</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Box Close -->
                                                    <!-- Box Open -->

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
                                                                    <label class="col-lg-5 form-label">Establishment
                                                                        Address <span
                                                                                class="text-danger">*</span></label>
                                                                    <label class="col-lg-7 form-label">Olakha,
                                                                        Thimphu</label>

                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Dzongkhag <span
                                                                            class="text-danger">*</span></label>
                                                                    <label class="col-lg-7 form-label">Samtse</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Email <span
                                                                            class="text-danger">*</span></label>
                                                                    <label class="col-lg-7 form-label">abc@gmail.com</label>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Mobile No <span
                                                                            class="text-danger">*</span></label>
                                                                    <label class="col-lg-7 form-label">17897654</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Telephone
                                                                        No </label>
                                                                    <label class="col-lg-7 form-label">365543</label>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Fax No <span
                                                                            class="text-danger">*</span></label>
                                                                    <label class="col-lg-7 form-label">365789</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Box Close -->
                                                    <!-- Box Open -->
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Proposed Establishment Addresses</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Establishment
                                                                        Address <span
                                                                                class="text-danger">*</span></label>
                                                                    <input type="text" class="col-lg-7 form-control"
                                                                           name="example-text-input"
                                                                           placeholder="Text..">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Dzongkhag <span
                                                                            class="text-danger">*</span></label>
                                                                    <select name="beast" id="select-beast"
                                                                            class="form-control custom-select col-lg-7">
                                                                        <option value="1">Samtse</option>
                                                                        <option value="4">Thimphu</option>
                                                                        <option value="3">Paro</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Email <span
                                                                            class="text-danger">*</span></label>
                                                                    <input type="text" class=" form-control col-lg-7"
                                                                           name="example-text-input"
                                                                           placeholder="Text..">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Mobile No <span
                                                                            class="text-danger">*</span></label>
                                                                    <input type="text" class=" form-control col-lg-7"
                                                                           name="example-text-input"
                                                                           placeholder="Text..">
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Telephone
                                                                        No </label>
                                                                    <input type="text" class="form-control col-lg-7"
                                                                           name="example-text-input"
                                                                           placeholder="Text..">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Fax No <span
                                                                            class="text-danger">*</span></label>
                                                                    <input type="text" class=" form-control col-lg-7"
                                                                           name="example-text-input"
                                                                           placeholder="Text..">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Box Close -->
                                                </div>--%>
                                            <div class="col-lg-12 form-group">
                                                <button type="button" onclick="nextTab('feesStructure','services')"
                                                        class="btn btn-azure col-lg-offset-9">
                                                    <i class="fa fa-arrow-left"></i>
                                                    Back
                                                </button>
                                                <button type="button"
                                                        onclick="saveAndPreview('generalInformation','saveAndPreview')"
                                                        class="btn btn-primary">
                                                    <i class="fa fa-arrow-right"></i>
                                                    Save & Preview
                                                </button>
                                            </div>
                                        </div>

                                        <div class="tab-pane saveAndPreview">

                                            <div id="saveAndPreview">
                                            </div>
                                            <div>
                                                <div id="submitSection" style="">
                                                    <div class="panel panel-default">
                                                        <div class="panel-body">
                                                            <div class="form-froup">
                                                                <div class="col-lg-12">
                                                                    <strong>Terms and Condition</strong>

                                                                    <div class="form-group">
                                                                        <div class="col-lg-12" id="">
                                                                            1. As provided in clause 2.1.1.2 and 2.3.1
                                                                            of Procurement Rules and Regulations 2009,
                                                                            the holder of this Certificate is qualified
                                                                            to participate in public procurement
                                                                            procedure.

                                                                            <br> 2. The issuance of CDB Registration
                                                                            Certificate will be based largely on the
                                                                            fulfillment of the minimum criteria set
                                                                            against classification of
                                                                            Contractor/Consultant and Categorization of
                                                                            Works and upon certification by competent
                                                                            authority for construction professionals.

                                                                            <br> 3. All the registered contractors
                                                                            should comply with 'Code of ethics for
                                                                            Contractors'.

                                                                            <br> 4. CDB will not be accountable for any
                                                                            false/fabricated submission that could have
                                                                            led to the fulfillment of the criteria and
                                                                            subsequent issue of CDB Registration
                                                                            Certificate.

                                                                            <br> 5. CDB Registration Certificate once
                                                                            issued would not relieve the certificate
                                                                            holder of any relaxation on the minimum
                                                                            requirements for registration.

                                                                            <br> 6. Notwithstanding the provisions of
                                                                            Companies Act of Bhutan, the certificate
                                                                            issued is non-transferable even if the
                                                                            promoters separate and establish similar
                                                                            companies.

                                                                            <br> 7. CDB Certificate cannot be leased or
                                                                            subleased to any individual or another firm.

                                                                            <br> 8. Certificate is valid during the
                                                                            period for which it was issued provided it
                                                                            has not been cancelled, suspended or revoked
                                                                            by CDB or any other competent authority.

                                                                            <br> 9. Failing to renew within the expiry
                                                                            date will lead to penalty of Nu.100 per day.

                                                                            <br> 10. Failing to pay the fees for
                                                                            approved online application within 30 days
                                                                            will lead to cancellation of the
                                                                            application.

                                                                            <br> 11. All registered construction firm
                                                                            must attend the mandatory refresher course
                                                                            in order to apply for renewal.

                                                                            <br> 12. No Contractors can submit bid,
                                                                            participate in bidding or be on the
                                                                            contention for award if the registration has
                                                                            expired.

                                                                            <br> 13. No Contractors can
                                                                            undertake/implement works which is not
                                                                            within the scope of the registration.

                                                                            <br> 14. CDB may verify the resources
                                                                            committed for the projects as and when
                                                                            desires.

                                                                            <br> 15. The registration is subject to
                                                                            verification whenever the CDB so desires.
                                                                            CDB will inspect the minimum mandatory
                                                                            requirement of manpower and equipment of
                                                                            Large and Medium contractors and the during
                                                                            the time of monitoring, every firm must
                                                                            extend necessary support and cooperation to
                                                                            CDB Officials.

                                                                            <br> 16. Large and Medium Contractors must
                                                                            have Office established with Signboard
                                                                            (requirements of office and signboard as
                                                                            determined by CDB)

                                                                            <br> 17. Registered firms are required to
                                                                            inform the CDB of any changes in their
                                                                            address, contact details or any pertinent
                                                                            particulars within one month.

                                                                            <br> 18. The CDB Registration Certificate
                                                                            can be revoked, downgraded, suspended or
                                                                            cancelled at any given time if the:
                                                                            <br> a. Holder undertakes unlawful
                                                                            participation in the procurement process;
                                                                            <br> b. Entity does not possess the minimum
                                                                            requirements during the physical
                                                                            verification process (at the discretion of
                                                                            CDB);
                                                                            <br> c. Entity has obtained the same due to
                                                                            false submissions;
                                                                            <br> d. Entity becomes bankrupt or winds up;
                                                                            or
                                                                            <br> e. Entity has been charged by the court
                                                                            for penal offence.
                                                                            <br> This Terms of Condition is hereby
                                                                            endorsed and enforced with immediate effect.

                                                                            <br> (Phub Rinzin)
                                                                            <br> Director
                                                                        </div>
                                                                    </div>


                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-lg-12">I/We declare and confirm
                                                                    that:- </label>

                                                                <div id="termsAndCon">
                                                                    <ul>
                                                                        <li>All information and attachments with this
                                                                            application are true and correct;
                                                                        </li>
                                                                        <li>I am/we are aware that any false information
                                                                            provided herein will result in rejection of
                                                                            my application and suspension of any
                                                                            registered granted;
                                                                        </li>
                                                                        <li>I/We shall not make refund claims of
                                                                            expenditure incurred in processing this
                                                                            application;
                                                                        </li>
                                                                        <li>I/We have read and understood the 'Code of
                                                                            Ethics' and shall perform in line with Code
                                                                            of Ethics and any other legislation in
                                                                            force. Failure to comply, will be subject to
                                                                            the penalities provided for in the
                                                                            applicable legislation of the country.
                                                                        </li>
                                                                        <li>I/We hereby declare that issue of CDB
                                                                            certificate does not in anyway constitute an
                                                                            obligation on the part of CDB or any other
                                                                            Goverment agency to provide contract works.
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-lg-12">
                                                                    <span><input type="checkbox" id="submitcheckbox"
                                                                                 name="tnc" class="required"
                                                                                 style="width:15px;height:15px;"></span>
                                                                    <span class="bold"> I agree to the above Terms Conditions</span>
                                                                </label>
                                                                <!-- <br /><span class="text-danger" id="termAndConErrorClass"></span> -->
                                                            </div>
                                                            <input type="hidden" name="addedeqCount" id="addedeqCount">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-offset-5 text-center">
                                                <button class="btn btn-primary col-lg-2 mr-2" type="button"
                                                        onclick="showConfirmation()">Submit
                                                </button>
                                                <button class="btn btn-danger col-lg-2" type="button">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </form>

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

            </div>
        </div>
    </div>

<script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/cdb/contractor_renewal.js"/>"></script>
</body>
</html>