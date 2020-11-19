<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<body class="">
<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-12">
                <div class="page-header mb-0 mt-0 page-header">
                    <h1 class="page-title">
                        Construction Development Board
                    </h1>
                </div>

                <input type="hidden" id="appNoVA" value="${appNo}" name="applicationNo"/>

                <div class="card" id="registrtaionFormCard">
                    <div class="card-header">
                        <h3 class="card-title font-weight-bold" style="color: #002752">SpecializedFirm >> Renewal Application >>
                            <security:authorize access="hasRole('ROLE_VERIFIER')">Verification</security:authorize>
                            <security:authorize access="hasRole('ROLE_APPROVER')">Approval</security:authorize>
                            <security:authorize access="hasRole('ROLE_PAYMENT')">Payment Approval</security:authorize>
                        </h3>
                        <span style="font-size: small;color: #444444"> >> CDB No: ${cdbNo} >> Application Number : ${appNo}</span>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="tab-content border p-3 col-lg-12">
                                <form action="" method="post" id="specializedFirmPaymentForm">
                                    <input type="hidden" id="appNoPayment"  value="${appNo}" name="appNo"/>

                                    <div class="">
                                        <div class="card tab2">
                                            <div class="bg-blue card-status card-status-left"></div>
                                            <div class="card-body">
                                                <div class="col-lg-12">
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Ownership Type <span
                                                                class="text-danger">*</span></label>

                                                        <label class="col-lg-8 form-label form-control" id="ownershipType"></label>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Country <span
                                                                class="text-danger">*</span></label>
                                                        <label class="col-lg-8 form-label form-control" id="country"></label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Trade License No</label>
                                                        <label class="col-lg-8 form-label form-control" id="tradeLicenseNo"></label>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Proposed Firm Name <span
                                                                class="text-danger">*</span></label>
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

                                            <%--<div class="card-header">
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

                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>--%>
                                        </div>
                                        <div class="tab-pane category_details ">
                                            <div class="card tab2">
                                                <div class="bg-blue card-status card-status-left"></div>
                                                <div class="card-header">Category Details</div>
                                            <div class="div-actual">
                                                <table id="specializedFirmCCTbl" class="table table-bordered table-hover">
                                                    <thead style="background-color: #F2F2F2">
                                                    <tr>
                                                        <th></th>
                                                        <th>Service Type</th>
                                                        <th>Category</th>
                                                        <th>Existing Class</th>
                                                        <th>Apply for Class</th>
                                                        <th>Fee</th>
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
                                            <div class="card-header">Service Applied and Fee</div>
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
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" style="zoom:1.6" name="incorporation"
                                                                   value="1" id="Incorporation" class="service_check">
                                                        </td>
                                                        <td>
                                                            <span>Incorporation</span>
                                                        </td>
                                                        <td>500.00</td>
                                                    </tr>
                                                    </tbody>
                                                    <tfoot></tfoot>
                                                </table>
                                            </div>
                                        </div>

                                        <div class="card tab2">
                                            <div class="bg-blue card-status card-status-left"></div>
                                            <div class="card-header">
                                                <h3 class="card-title">Fees Applied</h3>
                                            </div>
                                            <div class="card-body">

                                                <div class="col-lg-12">
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">
                                                                CDB Number :<font color="red">*</font>
                                                            </label>

                                                            <div class="col-lg-4" id="CDBNumberclass">
                                                                <input type="text" name="cdbNo"
                                                                        id="cdbNo" class="form-control"
                                                                        readonly="readonly" value="${cdbNo}">
                                                            </div>
                                                            <label class="col-lg-2 control-label">
                                                                Payment Date :<font color="red">*</font>
                                                            </label>

                                                            <div class="col-lg-4" id="dateclass">

                                                                <div class="input-group margin-bottom-sm">
                                                                                <span class="input-group-addon pr-5"><i
                                                                                        class="fa fa-calendar"></i></span>
                                                                    <input type="date" name="paymentDate"
                                                                           value="" id="paymentDate"
                                                                           class="form-control datepicker">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">
                                                                Receipt No :<font color="red">*</font>
                                                            </label>

                                                            <div class="col-lg-4 ">
                                                                <input type="text" name="paymentReceiptNo"
                                                                        id="paymentReceiptNo"
                                                                       class="form-control">
                                                            </div>
                                                            <label class="col-lg-2 control-label">
                                                                Remarks :
                                                            </label>

                                                            <div class="col-lg-4">
                                                                <input type="text" name="paymentRemarks"
                                                                       value="" id="paymentRemarks"
                                                                       class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">
                                                                Amount Paid:
                                                            </label>

                                                            <div class="col-lg-4" >
                                                                <input type="text" name="paymentAmount"
                                                                       value="" readonly="readonly"
                                                                       id="paymentAmount" class="form-control">
                                                            </div>
                                                            <label class="col-lg-2 control-label">
                                                                Mode of Payment:
                                                            </label>

                                                            <div class="col-lg-4">
                                                                <select name="modeOfPayment"
                                                                        id="contractorType"
                                                                        class="form-control chzn-select">
                                                                    <option value="select">-Select-</option>

                                                                    <option value="CDb-Cash">CDB-Cash
                                                                    </option>

                                                                    <option value="RRCO-Thimphu">
                                                                        RRCO-Thimphu
                                                                    </option>

                                                                    <option value="RRCO- Phuntsholing">RRCO-
                                                                        Phuntsholing
                                                                    </option>

                                                                    <option value="RRCO-Gelephu">
                                                                        RRCO-Gelephu
                                                                    </option>

                                                                    <option value="RRCO-Mongar">
                                                                        RRCO-Mongar
                                                                    </option>

                                                                    <option value="RRCO-Wangdiphodrang">
                                                                        RRCO-Wangdiphodrang
                                                                    </option>

                                                                    <option value="RRCO-Samdrupjongkhar">
                                                                        RRCO-Samdrupjongkhar
                                                                    </option>

                                                                    <option value="RRCO-Samdrupjongkhar">
                                                                        Not Applicable
                                                                    </option>
                                                                </select>
                                                                <span class="help-block" id=""></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Box Close -->
                                    </div>
                                    <div class="col-lg-12 form-group">
                                        <button type="submit" class="btn btn-primary" id="btnSave">
                                            <i class="fa fa-file-text mr-1"></i>Save and Generate Certificate</button>
                                        <button type="button" class="btn btn-primary" id="btnSendBack"><i
                                                class="fa fa-backward"></i>&nbsp;&nbsp;Send back
                                        </button>
                                        <a href="<c:url value="/admin/specializedFirm"/>" class="btn btn-orange">
                                            <i class="fa fa-ban"></i>Cancel
                                        </a>
                                    </div>

                                </form>

                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <script src="<c:url value="/resources/js/cdb/specializedFirm/specializedFirmRCAction.js"/>"></script>
</div>

</body>
</html>