<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body class="">
<c:if test="${not empty res.status}">
    <div class="card" id="acknowledgment">
        <div class="card-header">
            <b>Contractor >> Other Services >> </b>Acknowledgement
        </div>
        <div class="card-body">
            <c:if test="${res.status eq 1}">
                ${res.text}
            </c:if>
            <c:if test="${res.status eq 0}">
                <span class="error"> ${res.text}</span>
            </c:if>
        </div>
    </div>
</c:if>
<c:if test="${empty res.status}">
    <div class="mt-5">
        <div class="container mb-9">
            <div class="card" id="registrtaionFormCard">
                <div class="card-header">
                    <h3 class="card-title font-weight-bold">Contractor >> Other Services>> CDB No: ${cdbNo}</h3>
                </div>
                <div class="card-body">
                    <div class="form-group row">
                        <div class="col-lg-12 col-md-12 col-sm-12 text-center">
                            <span class="error" id="err-expired-audit-memo" style="color:#ff0000; font-size: 10px"></span>
                        </div>
                    </div>
                    <c:if test="${renewalCheck.status eq 0}">
                        <div class="form-group row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 alert alert-danger text-center" style="font-size: 10px">
                                <span class="error">${renewalCheck.text}</span>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${renewalCheck.status eq 1}">
                        <div class="col-md-12 col-lg-12">
                            <input type="hidden" id="isExpired" value="<%=request.getSession().getAttribute("isExpired")%>"/>
                            <input type="hidden" id="auditMemo" value="${auditMemo}"/>
                            <form action="" method="post" class="" id="contractorOSForm" enctype="multipart/form-data">
                                <div class="nav-tabs-custom">
                                    <ul class="m-0 nav nav-tabs">
                                        <li class="feesStructure tab-pane active " id="feesStructure">
                                            <a href="#fees_structure" class="border text-white" data-toggle="tab" data-placement="top">
                                                <i class="fa fa-bookmark mr-1"></i>Fee Structure</a>
                                        </li>
                                        <li class="tab-pane services" id="services">
                                            <a href="#services" class=" border" data-toggle="tab" data-placement="top">
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
                                            <a href="#saveAndPreview" class="border" data-toggle="tab" data-placement="top">
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
                                                            <td>500.00</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Firm Name Change</td>
                                                            <td>500.00</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Change of Location</td>
                                                            <td>500.00</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Change of Owner/Partner and other Controlling interest</td>
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
                                                    <button type="button" id="btn1" onclick="nextTab('feesStructure')" class="btn btn-primary">
                                                        <i class="fa fa-arrow-circle-right mr-2"></i>Next
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane services">
                                            <div class="panel-body table-responsive div-actual">
                                                <span>
                                                 Would you like to avail listed below along with this application? Please tick on the check box if you wish to.
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
                                            <div class="col-lg-12 form-group">
                                                <button type="button" onclick="backTab('services')" id="btn2" class="btn btn-azure col-lg-offset-9">
                                                    <i class="fa fa-arrow-circle-left"></i>Back
                                                </button>
                                                <button type="button" onclick="nextTab('services')" id="btn3" class="btn btn-primary">
                                                    <i class="fa fa-arrow-circle-right"></i>Next
                                                </button>
                                            </div>
                                        </div>
                                        <div class="tab-pane generalInformation">
                                            <div class="div-actual">
                                                <div class="card" id="gInfo">
                                                    <div class="bg-blue card-status card-status-left"></div>
                                                    <div class="card-body">
                                                        <input type="hidden" id="contractorIdFinal">
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
                                                                <label class="col-lg-4 form-label">Trade License Number</label>
                                                                <input type="text" class="col-lg-7 form-control" name="contractor.tradeLicenseNo" required="true" placeholder="Text.." id="tradeLicenseNo">
                                                            </div>
                                                            <div class="col-lg-6 col-lg-6">
                                                                <label class="col-lg-4 form-label">Proposed Firm Name
                                                                    <span class="text-danger">*</span></label>
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
                                                <div class="card hidden" id="cIncorporation">
                                                    <div class="bg-blue card-status card-status-left"></div>
                                                    <div class="card-header">
                                                        <h3 class="card-title">Attach Certificates of Incorporation/Sole Proprietorship</h3>
                                                    </div>
                                                    <div class="card-body">
                                                        <div class="col-lg-12">
                                                            <div class=""><input id="addMoreCert" type="button" value="Add More Certificate" class="btn btn-primary"></div>
                                                            <table class="table table-bordered table-center table-responsive-lg" id="certificateTbl">
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
                                                <div class="card" id="ownerPartner1">
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
                                                                    <th>Delete Request?</th>
                                                                    <th>Action</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                               <%-- <tr>
                                                                    <td class="country">
                                                                        <form:select id="countryList" class="form-control" name="contractor.contractorHRs[0].countryId" data-msg-required="" data-rule-required="true" path="countryList">
                                                                            <form:option value="" label="Select Country"/>
                                                                            <form:options items="${countryList}" itemValue="value" itemLabel="text"/>
                                                                        </form:select>
                                                                    </td>
                                                                    <td>
                                                                        <input type="text" name="contractor.contractorHRs[0].cidNo" class="form-control hr-cid" placeholder="Text..">
                                                                    </td>
                                                                    <td>
                                                                        <form:select id="salutation" name="contractor.contractorHRs[0].salutationId" class="form-control input-sm" data-msg-required="true" data-rule-required="true" path="salutationList">
                                                                            <form:option value="" label="Select Salutation"/>
                                                                            <form:options items="${salutationList}" itemValue="value" itemLabel="text"/>
                                                                        </form:select>
                                                                    </td>
                                                                    <td>
                                                                        <input type="text" class="form-control name" name="contractor.contractorHRs[0].name" placeholder="Text..">
                                                                    </td>
                                                                    <td>
                                                                        <select id="gender" name="contractor.contractorHRs[0].sex" class="form-control sex">
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
                                                                            <input type="checkbox" class="custom-control-input showCert" name="contractor.contractorHRs[0].siCertificate" value="1">
                                                                            <i class="custom-control-label"></i>
                                                                        </label>
                                                                    </td>
                                                                    <td><input type='checkbox' id="deleteRequest" name='contractor.contractorHRs[0].deleteRequest' disabled value='1'></td>
                                                                    &lt;%&ndash;<td class='action'><button class='btn-sm btn-info btn-block edit-rowOW'>Edit</button></td>&ndash;%&gt;
                                                                </tr>--%>
                                                                </tbody>
                                                            </table>
                                                            <div class="col-lg-12 text-right hide" id="ownerPartner">
                                                                <%--<button type="button" class="btn btn-outline-primary btn-sm" onclick="addRow('partnerDtls')">
                                                                    <i class="fe fe-plus mr-2"></i>Add More
                                                                </button>--%>
                                                                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#addOwModal">
                                                                        <i class="fa fa-plus"></i> Add More HR
                                                                    </button>
                                                                <button type="button" class="btn btn-outline-danger btn-sm" onclick="removeRow('partnerDtls')">
                                                                    <i class="fe fe-trash mr-2"></i>Remove Last Row
                                                                </button>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row hide" id="ownerShipchangeId">
                                                            <div class="col-lg-12 col-md-12 ">
                                                                <label class="col-lg-3 col-md-3">Reason for Change of Owner:
                                                                    <span class="text-danger">*</span></label>
                                                                <input type="text" class="col-lg-6 form-control" name="contractor.ownershipChangeRemarks" id="ownershipChangeRemarks" required="" placeholder="Text..">
                                                            </div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12">
                                                                <span><h5 class="text-orange">Attach Certificates of Ownership change</h5></span>
                                                                <div class=""><input id="addMoreCertOwner" type="button" value="Add More Certificate" class="btn btn-primary"></div>
                                                                <table class="table table-bordered table-center table-responsive-lg" id="certificateTblOwner">
                                                                    <thead>
                                                                    <tr>
                                                                        <th>Sl No</th>
                                                                        <th>Document Name</th>
                                                                        <th>Document Attached</th>
                                                                        <th>File Size</th>
                                                                        <th>Delete</th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody class="files">
                                                                   <%-- <tr>
                                                                        <td><input type='text' required="" class='form-control docName' name='cAttachments[0].documentName'/></td>
                                                                        <td><input type='file' required="" class='file' name='cAttachments[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg'/></td>
                                                                        <td class='file-size'></td>
                                                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><input type='text' required="" class='form-control docName' name='cAttachments[0].documentName'/></td>
                                                                        <td><input type='file' required="" class='file' name='cAttachments[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg'/></td>
                                                                        <td class='file-size'></td>
                                                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                                                    </tr>--%>
                                                                    </tbody>
                                                                </table>
                                                            </div>
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
                                                                <input type="text" class="form-control col-lg-8 form-control" name="contractor.pGewogId" id="pGewogId" required="true" placeholder="Text..">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="col-lg-6 col-md-6 form-group">
                                                                <label class="col-lg-4 form-label">Village </label>
                                                                <input type="text" class="form-control col-lg-8 form-control" name="contractor.pVillageId" id="pVillageId" required="true" placeholder="Text..">
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
                                                                <input type="email" class=" form-control col-lg-7" name="contractor.regEmail" id="regEmail" required="true" placeholder="Text..">
                                                            </div>
                                                            <div class="col-lg-6 col-md-6 ">
                                                                <label class="col-lg-4 form-label">Mobile Number <span class="text-danger">*</span></label>
                                                                <input type="text" class=" form-control col-lg-7" name="contractor.regMobileNo" id="regMobileNo" required="true" placeholder="Text..">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <div class="col-lg-6 col-md-6">
                                                                <label class="col-lg-4 form-label">Telephone Number </label>
                                                                <input type="text" class="form-control col-lg-7" name="contractor.regPhoneNo" id="regPhoneNo" placeholder="Text..">
                                                            </div>
                                                            <div class="col-lg-6 col-md-6">
                                                                <label class="col-lg-4 form-label">Fax Number</label>
                                                                <input type="text" class="form-control col-lg-7" name="contractor.regFaxNo" id="regFaxNo" placeholder="Text..">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                           <%-- <div class="col-lg-12 form-group">
                                                <button type="button" onclick="backTab('generalInformation')" id="btn4" class="btn btn-azure col-lg-offset-9">
                                                    <i class="fa fa-arrow-circle-left"></i>Back
                                                </button>
                                                   <button type="button" onclick="nextTab('generalInformation')" id="btn5" class="btn btn-primary nextTab">
                                                       <i class="fa fa-arrow-circle-right"></i>Next &nbsp;
                                                   </button>
                                            </div>--%>
                                            <div class="col-lg-12 form-group">
                                                <div class="col-md-offset-11 col-lg-offset-10 col-xs-offset-10">
                                                    <button type="button" onclick="nextTab('generalInformation')" id="btn5" class="btn btn-primary nextTab">
                                                        <i class="fa fa-arrow-circle-right"></i>Next &nbsp;
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane category_details hide">
                                            <div class="div-actual">
                                                <div class="card">
                                                    <b class="text-orange">Note: If you are upgrading/down grade class,it is necessary to attach Letter of Undertaking</b> &nbsp; &nbsp;
                                                </div>
                                                <table id="contractorCCTbl" class="table table-bordered table-hover">
                                                    <thead style="background-color: #F2F2F2">
                                                    <tr>
                                                        <td></td>
                                                        <td>Category</td>
                                                        <td>Existing Class</td>
                                                        <td>New Class</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%-- TODO:: kept as static since loading dynamic hampers the performance --%>
                                                    <tr id="W1">
                                                        <td><input class="form-control categoryCheck" type="checkbox" name="categories[0].projectCateID" value="6cd737d4-a2b7-11e4-b4d2-080027dcfac6" style="width: 17px; height: 17px;"></td>
                                                        <td>W1-Roads and Bridges</td>
                                                        <td><input type="hidden" name="categories[0].existingClassID" class="existingClassID">
                                                            <select class="form-control existingClassID" disabled="">
                                                                <option value="">-Select-</option>
                                                                <option value="e19afe94-c3ea-11e4-af9f-080027dcfac6">L:Large</option>
                                                                <option value="003f9a02-c3eb-11e4-af9f-080027dcfac6">M:Medium</option>
                                                                <option value="ef832830-c3ea-11e4-af9f-080027dcfac6">S:Small</option>
                                                            </select></td>
                                                        <td><select name="categories[0].appliedClassID" class="form-control appliedClassID" disabled="">
                                                            <option value="">-Select-</option>
                                                            <option value="e19afe94-c3ea-11e4-af9f-080027dcfac6">L:Large</option>
                                                            <option value="003f9a02-c3eb-11e4-af9f-080027dcfac6">M:Medium</option>
                                                            <option value="ef832830-c3ea-11e4-af9f-080027dcfac6">S:Small</option>
                                                        </select></td>
                                                    </tr>
                                                    <tr id="W2">
                                                        <td><input class="form-control categoryCheck" type="checkbox" name="categories[1].projectCateID" value="8176bd2d-a2b7-11e4-b4d2-080027dcfac6" style="width: 17px; height: 17px;"></td>
                                                        <td>W2-Traditional Bhutanese Painting/Finishing Works</td>
                                                        <td><input type="hidden" name="categories[1].existingClassID" class="existingClassID">
                                                            <select class="form-control existingClassID" disabled="">
                                                                <option value="">-Select-</option>
                                                                <option value="0c14ebea-c3eb-11e4-af9f-080027dcfac6">R:Registered</option>
                                                            </select></td>
                                                        <td><select name="categories[1].appliedClassID" class="form-control appliedClassID" disabled="">
                                                            <option value="">-Select-</option>
                                                            <option value="0c14ebea-c3eb-11e4-af9f-080027dcfac6">R:Registered</option>
                                                        </select></td>
                                                    </tr>
                                                    <tr id="W3">
                                                        <td><input class="form-control categoryCheck" type="checkbox" name="categories[2].projectCateID" value="8afc0568-a2b7-11e4-b4d2-080027dcfac6" style="width: 17px; height: 17px;"></td>
                                                        <td>W3-Buildings,Irrigation,Drainage,Flood Control,Water Supply andSewerage</td>
                                                        <td><input type="hidden" name="categories[2].existingClassID" class="existingClassID">
                                                            <select class="form-control existingClassID" disabled="">
                                                                <option value="">-Select-</option>
                                                                <option value="e19afe94-c3ea-11e4-af9f-080027dcfac6">L:Large</option>
                                                                <option value="003f9a02-c3eb-11e4-af9f-080027dcfac6">M:Medium</option>
                                                                <option value="ef832830-c3ea-11e4-af9f-080027dcfac6">S:Small</option>
                                                            </select></td>
                                                        <td><select name="categories[2].appliedClassID" class="form-control appliedClassID" disabled="">
                                                            <option value="">-Select-</option>
                                                            <option value="e19afe94-c3ea-11e4-af9f-080027dcfac6">L:Large</option>
                                                            <option value="003f9a02-c3eb-11e4-af9f-080027dcfac6">M:Medium</option>
                                                            <option value="ef832830-c3ea-11e4-af9f-080027dcfac6">S:Small</option>
                                                        </select></td>
                                                    </tr>
                                                    <tr id="W4">
                                                        <td><input class="form-control categoryCheck" type="checkbox" name="categories[3].projectCateID" value="9090a82a-a2b7-11e4-b4d2-080027dcfac6" style="width: 17px; height: 17px;"></td>
                                                        <td>W4-Power and Telecommunication Works</td>
                                                        <td><input type="hidden" name="categories[3].existingClassID" class="existingClassID">
                                                            <select class="form-control existingClassID" disabled="">
                                                                <option value="">-Select-</option>
                                                                <option value="e19afe94-c3ea-11e4-af9f-080027dcfac6">L:Large</option>
                                                                <option value="003f9a02-c3eb-11e4-af9f-080027dcfac6">M:Medium</option>
                                                                <option value="ef832830-c3ea-11e4-af9f-080027dcfac6">S:Small</option>
                                                            </select></td>
                                                        <td><select name="categories[3].appliedClassID" class="form-control appliedClassID" disabled="">
                                                            <option value="">-Select-</option>
                                                            <option value="e19afe94-c3ea-11e4-af9f-080027dcfac6">L:Large</option>
                                                            <option value="003f9a02-c3eb-11e4-af9f-080027dcfac6">M:Medium</option>
                                                            <option value="ef832830-c3ea-11e4-af9f-080027dcfac6">S:Small</option>
                                                        </select></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <b class="text-orange">Attach your UnderTaking Below</b> &nbsp;
                                                    &nbsp;
                                                    <br/>
                                                    <input type="button" id="addMoreCertCategory" value="Add More File" class="btn btn-primary eqFile">
                                                    <div class="table-responsive">
                                                        <table class="table table-bordered table-center table-responsive-lg auto-index" id="certificateTblCategory">
                                                            <thead>
                                                            <tr>
                                                                <th>Sl No</th>
                                                                <th>Document Name</th>
                                                                <th>Document Attached</th>
                                                                <th>File Size</th>
                                                                <th>Delete</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody class="files">
                                                           <%-- <tr>
                                                                <td>
                                                                    <input type='hidden' class='form-control aFor' name='cAttachments[0].attachmentFor' value='C'/>
                                                                    <input type='text' required="" class='form-control docName' name='cAttachments[0].documentName'/></td>
                                                                <td><input type='file' required="" class='file' name='cAttachments[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg'/></td>
                                                                <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                                            </tr>--%>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-12 form-group nextBackBtn">
                                                <button type="button" onclick="backTab('category_details')" id="btn6" class="btn btn-azure col-lg-offset-9">
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
                                                                    <th style="width: 10%">Gender</th>
                                                                    <th style="width: 10%">Country</th>
                                                                    <th style="width: 10%">Designation</th>
                                                                    <th style="width: 10%">Qualification</th>
                                                                    <th style="width: 10%">Trade / Fields</th>
                                                                    <th style="width: 10%">Service Type</th>
                                                                    <th style="width: 20%">Attachments (CV/UT/AT)</th>
                                                                    <th style="width: 20%">Delete Request</th>
                                                                    <th style="width: 5%">Action</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                </tbody>
                                                            </table>
                                                            <div class="col-lg-12 text-right">
                                                                <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#addHRModal"><i class="fa fa-plus"></i> Add More HR</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-12 form-group nextBackBtn">
                                                <button type="button" onclick="backTab('human_resource_criteria')" id="btn7" class="btn btn-azure col-lg-offset-9 backTab">
                                                    <i class="fa fa-arrow-circle-left"></i> &nbsp;Back
                                                </button>
                                                <button type="button" id="btnValHRNext" onclick="nextTab('human_resource_criteria')" class="btn btn-primary nextTab">Next &nbsp;
                                                    <i class="fa fa-arrow-circle-right"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="tab-pane equipment_details hide">
                                            <div class="div-actual">
                                                <i><strong>Contractor Equipment Details</strong></i>
                                                <div class="table-responsive">
                                                    <table class="table table-bordered table-striped" id="equipmentTbl">
                                                        <thead>
                                                        <tr>
                                                            <th>Equipment Name</th>
                                                            <th>Registration Number</th>
                                                            <th>Quantity</th>
                                                            <th>Attachment</th>
                                                            <th>Delete Request</th>
                                                            <th>Action</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        </tbody>
                                                    </table>
                                                    <div class="col-lg-12 text-right">
                                                        <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#eqModal"><i class="fa fa-plus"></i> Add More Equipment</button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-12 form-group nextBackBtn">
                                                <button type="button" onclick="backTab('equipment_details')" id="btn8" class="btn btn-azure col-lg-offset-9">
                                                    <i class="fa fa-arrow-circle-left"></i>&nbsp; Back
                                                </button>
                                                <button type="button" class="btn btn-primary" id="btnValEqNext" onclick="nextTab('equipment_details')">Next &nbsp;
                                                    <i class="fa fa-life-saver"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="tab-pane saveAndPreview">
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
                                                                            verification process (at the discretion of CDB);
                                                                            <br> c. Entity has obtained the same due to
                                                                            false submissions;
                                                                            <br> d. Entity becomes bankrupt or winds up; or
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
                                                                <label class="col-lg-12">I/We declare and confirm that:- </label>
                                                                <div id="termsAndCon">
                                                                    <ul>
                                                                        <li>All information and attachments with this application are true and correct;</li>
                                                                        <li>I am/we are aware that any false information
                                                                            provided herein will result in rejection of my application and suspension of any registered granted;
                                                                        </li>
                                                                        <li>I/We shall not make refund claims of expenditure incurred in processing this application;</li>
                                                                        <li>I/We have read and understood the 'Code of Ethics' and shall perform in line with Code
                                                                            of Ethics and any other legislation in force. Failure to comply, will be subject to
                                                                            the penalties provided for in the applicable legislation of the country.
                                                                        </li>
                                                                        <li>I/We hereby declare that issue of CDB certificate does not in anyway constitute an
                                                                            obligation on the part of CDB or any other Government agency to provide contract works.
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-lg-12">
                                                                    <span><input type="checkbox" id="agreeCheck" name="tnc" class="required" onclick="enableSubmit()" style="width:15px;height:15px;"></span>
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
                                                <button class="btn btn-primary col-lg-2 mr-2" type="button" id="btnSubmit" onclick="showConfirmation()" disabled>Submit</button>
                                                <button class="btn btn-danger col-lg-2" type="button">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
            <form id="modalForm">
                    <%--HR add model--%>
                <div aria-hidden="true" aria-labelledby="hrModalLabel" role="dialog" class="modal fade in"
                     id="addHRModal">
                    <div class="modal-dialog modal-lg" id="hrModal">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 id="hrModalLabel" class="modal-title">Add Human Resource</h4>
                                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
                            </div>
                            <div class="modal-body form-horizontal">
                                <div class="modal-div">
                                    <div class="form-group">
                                        <input type="hidden" class="id4Edit" name="contractorHRs[0].id">
                                        <label class="col-lg-2">Nationality
                                            <span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <select name="contractorHRs[0].countryId" id="hr5" required="" class="form-control custom-select text-left select-beast country">
                                                <option value="">Select Country</option>
                                                <c:forEach var="item" items="${countryList}">
                                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-md-2 col-lg-2">CID/Work Permit No <span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <div class="input-icon">
                                               <span class="input-icon-addon">
                                                 <i class="fa fa-address-card-o"></i>
                                               </span>
                                                <input type="text" name="contractorHRs[0].cidNo" class="form-control hr-cid" id="hr3" required="" placeholder="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2">Title
                                            <span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <select name="contractorHRs[0].salutationId" id="hr1" required="" class="form-control custom-select text-left select-beast">
                                                <option value="">Select Title</option>
                                                <c:forEach var="item" items="${salutationList}">
                                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-lg-2">Name
                                            <span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <div class="input-icon">
                                                <span class="input-icon-addon"><i class="fe fe-user"></i></span>
                                                <input type="text" name="contractorHRs[0].name" id="hr2" class="form-control name" required="" placeholder="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2">Gender<span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <select name="contractorHRs[0].sex" id="hr4" required="" class="form-control custom-select text-left select-beast sex">
                                                <option value="">Select Gender</option>
                                                <option value="M">Male</option>
                                                <option value="F">Female</option>
                                            </select>
                                        </div>
                                        <label class="col-lg-2">Designation
                                            <span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <select name="contractorHRs[0].designationId" id="hr6" required="" class="form-control custom-select text-left select-beast">
                                                <option value="">Select Designation</option>
                                                <c:forEach var="item" items="${designationList}">
                                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2">Qualification
                                            <span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <select name="contractorHRs[0].qualificationId" id="hr7" required="" class="form-control custom-select text-left select-beast">
                                                <option value="">Select Qualification</option>
                                                <c:forEach var="item" items="${qualificationList}">
                                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-lg-2">Trade
                                            <span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <select name="contractorHRs[0].tradeId" id="hr8" required="" class="form-control custom-select text-left select-beast">
                                                <option value="">Select Trade</option>
                                                <c:forEach var="item" items="${tradeList}">
                                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2">Service Type<span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <select name="contractorHRs[0].serviceTypeId" id="hr9" required="true" class="form-control custom-select text-left select-beast">
                                                <option value="">Select Type</option>
                                                <c:forEach var="item" items="${serviceTypeList}">
                                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-lg-2"> Joining Date<span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <div class="input-group margin-bottom-sm">
                                                <span class="input-group-addon pr-5"><i class="fa fa-calendar"></i></span>
                                                <input type="date" name="contractorHRs[0].joiningDate" id="joiningDate" class="form-control datepicker">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2">CDB Reg No :</label>
                                        <div class="col-lg-4">
                                            <div class="input-icon">
                                                <span class="input-icon-addon"><i class="fe fe-user"></i></span>
                                                <input type="text" name="contractorHRs[0].cdbNo" id="hr11" class="form-control name" placeholder="cdbNo">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 col-lg-12">
                                            <input type="button" id="addMoreHr" value="Add More File" class="btn btn-primary hrFile">
                                            <div class="table-responsive">
                                                <table class="table table-bordered table-hover" id="hrUploadTbl">
                                                    <thead>
                                                    <tr>
                                                        <th>Document Name</th>
                                                        <th>Document Attached</th>
                                                        <th>File Size</th>
                                                        <th>Delete</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="fileListhrsection" class="files">
                                                    <tr>
                                                        <td><input type="hidden" class="hraId">
                                                            <input type='text' required="" value="Certificate" class='form-control docName' name='contractorHRs[0].contractorHRAs[0].documentName'/>
                                                        </td>
                                                        <td><input type='file' required="" class='file' name='contractorHRs[0].contractorHRAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/></td>
                                                        <td class='file-size'></td>
                                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input type="hidden" class="hraId">
                                                            <input type='text' required="" value="Undertaking" class='form-control docName' name='contractorHRs[0].contractorHRAs[0].documentName'/>
                                                        </td>
                                                        <td><input type='file' required="" class='file' name='contractorHRs[0].contractorHRAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/></td>
                                                        <td class='file-size'></td>
                                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input type="hidden" class="hraId">
                                                            <input type='text' required="" value="Others" class='form-control docName' name='contractorHRs[0].contractorHRAs[0].documentName'/>
                                                        </td>
                                                        <td><input type='file' required="" class='file' name='contractorHRs[0].contractorHRAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/></td>
                                                        <td class='file-size'></td>
                                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary" onclick="getModalData('hrDtlsTable','hr',9)" type="button">OK</button>
                                <button data-dismiss="modal" class="btn btn-warning" type="button">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                          <%--Owner add model--%>
                        <div aria-hidden="true" aria-labelledby="hrModalLabel" role="dialog" class="modal fade in"
                             id="addOwModal">
                            <div class="modal-dialog modal-lg" id="ownerModal">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 id="ownerModalLabel" class="modal-title">Add Human Resource</h4>
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
                                    </div>
                                    <div class="modal-body form-horizontal">
                                        <div class="modal-div">
                                            <div class="form-group">
                                                <input type="hidden" id="id5Edit" name="contractorOWs[0].id">
                                                <label class="col-lg-2">Nationality
                                                    <span class="text-danger">*</span>:</label>
                                                <div class="col-lg-4">
                                                    <select name="contractorOWs[0].countryId" id="ow1" required="" class="form-control custom-select text-left select-beast country">
                                                        <c:forEach var="item" items="${countryList}">
                                                            <option value="${item.value}"><c:out value="${item.text}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <label class="col-md-2 col-lg-2">CID/Work Permit No.<span class="text-danger">*</span>:</label>
                                                <div class="col-lg-4">
                                                    <div class="input-icon">
                                                      <span class="input-icon-addon">
                                                         <i class="fa fa-address-card-o"></i>
                                                      </span>
                                                        <input type="text" name="contractorOWs[0].cidNo" class="form-control hr-cid" id="ow2" required="" placeholder="">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2">Title
                                                    <span class="text-danger">*</span>:</label>
                                                <div class="col-lg-4">
                                                    <select name="contractorOWs[0].salutationId" id="ow3" required="" class="form-control custom-select text-left select-beast">
                                                        <option value="">Select Salutation</option>
                                                        <c:forEach var="item" items="${salutationList}">
                                                            <option value="${item.value}"><c:out value="${item.text}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <label class="col-lg-2">Name
                                                    <span class="text-danger">*</span>:</label>
                                                <div class="col-lg-4">
                                                    <div class="input-icon">
                                                        <span class="input-icon-addon"><i class="fe fe-user"></i></span>
                                                        <input type="text" name="contractorOWs[0].name" id="ow4" class="form-control name" required="" placeholder="">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2">Gender<span class="text-danger">*</span>:</label>
                                                <div class="col-lg-4">
                                                    <select name="contractorOWs[0].sex" id="ow5" required="" class="form-control custom-select text-left select-beast sex">
                                                        <option value="">Select Gender</option>
                                                        <option value="M">Male</option>
                                                        <option value="F">Female</option>
                                                    </select>
                                                </div>
                                                <label class="col-lg-2">Designation
                                                    <span class="text-danger">*</span>:</label>
                                                <div class="col-lg-4">
                                                    <select name="contractorOWs[0].designationId" id="ow6" required="" class="form-control custom-select text-left select-beast">
                                                        <option value="">Select Designation</option>
                                                        <c:forEach var="item" items="${designationList}">
                                                            <option value="${item.value}"><c:out value="${item.text}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-primary" onclick="getOwnerModalData('partnerDtls','ow',6)" type="button">OK</button>
                                        <button data-dismiss="modal" class="btn btn-warning" target="#addOwModal" type="button">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <%--Equipment addmore model--%>
                <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" class="modal fade in" id="eqModal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 id="myModalLabel" class="modal-title">Add Equipment</h4>
                                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">
                                    <span></span>
                                </button>
                            </div>
                            <div class="modal-body form-horizontal">
                                <div class="">
                                    <div class="form-group">
                                        <label class="col-lg-2">Equipment <span class="text-danger">*</span>:</label>
                                        <input type="hidden" class="id4Edit" name="equipments[0].id"/> <!-- for edit-->
                                        <div class="col-lg-4">
                                            <select name="equipments[0].equipmentId" class="form-control custom-select text-left equipmentId" required="" onchange="enableRegistrationNo()" id="eq1">
                                                <option value="">Select Equipment</option>
                                                <c:forEach var="item" items="${equipmentList}">
                                                    <option value="${item.value}" class="${item.obj1}"><c:out value="${item.text}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-lg-3">Registration No. <span class="text-danger">*</span>:</label>
                                        <div class="col-lg-3">
                                            <input type="text" name="equipments[0].registrationNo" class="form-control registrationNo" onchange="validateNo(this.value)"<%--required="true"--%> disabled id="eq2" placeholder="BP-1-A1234">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2">Quantity <span class="text-danger">*</span>:</label>
                                        <div class="col-lg-4">
                                            <div class="input-icon">
                                                <input type="text" name="equipments[0].quantity" class="form-control" required="" id="eq3">

                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <input type="button" id="addMoreEq" value="Add More File" class="btn btn-primary eqFile">
                                            <div class="table-responsive">
                                                <table class="table table-bordered table-hover" id="eqUploadTbl">
                                                    <thead>
                                                    <tr>
                                                        <th>Document Name</th>
                                                        <th>Document Attached</th>
                                                        <th>Delete</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="files">
                                                    <tr>
                                                        <td><input type='text' required="" class='form-control docName' value="Bluebook" name='equipments[0].contractorEQAs[0].documentName'/>
                                                        </td>
                                                        <td><input type='file' required="" class='file' name='equipments[0].contractorEQAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/></td>
                                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                                    </tr>
                                                    <tr>
                                                        <td><input type='text' required="" class='form-control docName' value="Insurance Certificate" name='equipments[0].contractorEQAs[0].documentName'/>
                                                        </td>
                                                        <td><input type='file' required="" class='file' name='equipments[0].contractorEQAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/></td>
                                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button data-dismiss="modal" class="btn btn-primary" onclick="getModalData('equipmentTbl','eq',3)" type="button">OK</button>
                                <button data-dismiss="modal" class="btn btn-warning" type="button">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                    <%--Confirmation Modal--%>
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
                                <button type="button" class="btn btn-warning" onclick="closemodel('confirmationModel')">
                                    <span class="fa fa-times"></span> No
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</c:if>

<script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/cdb/contractor/contractorOS.js"/>"></script>

<script>
    function validateNo(vNo) {
        if (validateVehNo(vNo)) {
            return true;
        } else {
            $('#eq2').val('');
            errorMsg('oops!! please check your vehicle Number format');
            return false;
        }
    }
    function validateVehNo() {
        var vNo = $('#eq2').val();
        const re = /\w[BPG]-\d-\w\d{4}$/;
        return re.test(vNo);
    }
    function checkDuplicateEQ() {
        var $this = $(this);
        var isEqExist = false;
        $('#eqdatatable').find('tbody tr td:nth-child(3)').each(function () {
            if ($this.val() == $(this).text()) {
                warningMsg("This Registration Number is already exists in your list!!!");
                $this.val('');
                isEqExist = true;
                return isEqExist;
            }
        });
    }

    function enableRegistrationNo() {
        $('.equipmentId').on('change', function (e) {
            var isRegistration = $(this).find("option:selected").hasClass("1");
            if (isRegistration == true) {
                $('#eq2').prop('disabled', false).prop('required', true);
                $('#eq3').val(1).prop('disabled', true);
            } else {
                $('#eq2').val('').prop('disabled', true);
                $('#eq3').val('').prop('disabled', false);
            }
        })
    }
</script>
</body>
</html>