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
                        <h3 class="card-title font-weight-bold" style="color: #002752">Contractor >> Renewal Application >>
                            <security:authorize access="hasRole('ROLE_VERIFIER')">Verification</security:authorize>
                            <security:authorize access="hasRole('ROLE_APPROVER')">Approval</security:authorize>
                            <security:authorize access="hasRole('ROLE_PAYMENT')">Payment Approval</security:authorize>
                        </h3>
                        <span style="font-size: small;color: #444444"> >> CDB No: ${cdbNo} >> Application Number : ${appNo}</span>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="tab-content border p-3 col-lg-12">
                                <form action="" method="post" id="contractorPaymentForm">
                                    <input type="hidden" id="appNoPayment"  value="${appNo}" name="appNo"/>

                                    <div class="">
                                        <div class="card tab2">
                                            <div class="bg-blue card-status card-status-left"></div>
                                            <div class="card-body">
                                                <div class="col-lg-12">
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Ownership Type</label>
                                                        <label class="col-lg-8 form-label form-control" id="ownershipType"></label>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Country </label>
                                                        <label class="col-lg-8 form-label form-control" id="country"></label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Trade License Number</label>
                                                        <label class="col-lg-8 form-label form-control" id="tradeLicenseNo"></label>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Proposed Firm Name </label>
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
                                                        <label class="col-lg-7 form-label form-control" id="pDzongkhag"></label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-5 form-label">Email</label>
                                                        <label class="col-lg-7 form-label form-control" id="regEmailExist"></label>
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
                                            <div class="card-header bg-indigo-light text-white">Category</div>
                                            <div class="div-actual">
                                                <table id="contractorCCTbl1" class="table table-bordered table-hover">
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
                                        </div>
                                        <div class="card tab2">
                                            <div class="bg-blue card-status card-status-left"></div>
                                            <div class="card-header bg-indigo-light text-white">Service Applied and Fee</div>
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

                                        <div class="card tab2">
                                            <div class="bg-blue card-status card-status-left"></div>
                                            <div class="card-header">
                                                <div class="card-header bg-indigo-light text-white">Fees Applied</h3>
                                            </div>
                                            <div class="card-body">

                                                <div class="col-lg-12">
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">CDB Number :<font color="red">*</font></label>
                                                            <div class="col-lg-4" id="CDBNumberclass">
                                                                <input type="text" name="cdbNo" id="cdbNo" class="form-control" readonly="readonly" value="${cdbNo}">
                                                            </div>
                                                            <label class="col-lg-2 control-label">Payment Date :<font color="red">*</font></label>
                                                            <div class="col-lg-4" id="dateclass">
                                                                <div class="input-group margin-bottom-sm">
                                                                    <span class="input-group-addon pr-5"><i class="fa fa-calendar"></i></span>
                                                                    <input type="date" name="paymentDate" value="" id="paymentDate" class="form-control datepicker">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Receipt No :<font color="red">*</font></label>
                                                            <div class="col-lg-4 ">
                                                                <input type="text" name="paymentReceiptNo" id="paymentReceiptNo" class="form-control">
                                                            </div>
                                                            <label class="col-lg-2 control-label">Remarks :</label>
                                                            <div class="col-lg-4">
                                                                <input type="text" name="paymentRemarks" value="" id="paymentRemarks" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Amount Paid:</label>
                                                            <div class="col-lg-4" >
                                                                <input type="text" name="paymentAmount" value="" readonly="readonly" id="paymentAmount" class="form-control">
                                                            </div>
                                                            <label class="col-lg-2 control-label">Mode of Payment:</label>
                                                            <div class="col-lg-4">
                                                                <select name="modeOfPayment" class="chosen-select form-control" onchange="checkForApplicable(this.value)" id="paymentmode" required>
                                                                    <option value="">--Select--</option>
                                                                    <c:forEach var="plist" items="${modeOfPayment}" varStatus="counter">
                                                                        <option value="${plist.name}">${plist.name}</option>
                                                                    </c:forEach>
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
                                    <div class="col-lg-12 form-group">
                                        <button type="submit" class="btn btn-primary" id="btnSave">
                                            <i class="fa fa-file-text mr-1"></i>Save and Generate Certificate</button>
                                       <%-- <button type="button" class="btn btn-primary" id="btnSendBack"><i
                                                class="fa fa-backward"></i>&nbsp;&nbsp;Send back
                                        </button>--%>
                                        <a href="<c:url value="/admin/contractor"/>" class="btn btn-orange">
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
    <script src="<c:url value="/resources/js/cdb/contractor/contractorRCAction.js"/>"></script>
</div>
</body>
</html>