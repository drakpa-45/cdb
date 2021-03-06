<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div class="content-wrapper">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <input type="hidden" id="isExpired" value="<%=request.getSession().getAttribute("isExpired")%>"/>
                <input type="hidden" id="spFirmHRidIdFinal" value="${appDetail.id}">

                <div class="form-group fa-pull-right">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                        <button class="btn btn-sm btn-primary" type="button" onclick="printInfo('${appDetail.cdbNo}')"><i class="fa fa-print"></i> Print Information</button>
                        &nbsp;&nbsp;&nbsp;
                        <button class="btn btn-sm btn-success" type="button" onclick="printCertificate()"><i class="fa fa-edit"></i> Print Certificate</button>
                        &nbsp;&nbsp;&nbsp;
                    </div>
                </div>
                <hr/>
                <div class="col-md-6 table-responsive">
                    <table class="table table-condensed">
                        <tbody>
                        <tr>
                            <td colspan="2" class="font-blue-madison bold warning">General Info</td>
                        </tr>
                        <tr>
                            <td><strong>CDB Number.</strong></td>
                            <td><input type="hidden" value="${appDetail.cdbNo}" id="cdbNo" class="form-control" readonly>${appDetail.cdbNo}</td>
                        </tr>
                        <tr>
                            <td><strong>Ownership Type</strong></td>
                            <td>${appDetail.ownershipName}</td>
                        </tr>
                        <tr>
                            <td><strong>Company Name</strong></td>
                            <td>${appDetail.firmName}</td>
                        </tr>
                        <tr>
                            <td><strong>Trade License Number</strong></td>
                            <td>${appDetail.tradeLicenseNo}</td>
                        </tr>
                        <tr>
                            <td><strong>TPN Number</strong></td>
                            <td>${appDetail.tpn}</td>
                        </tr>
                        <tr>
                            <td><strong>Country</strong></td>
                            <td>${appDetail.countryName}</td>
                        </tr>
                        <tr>
                            <td><strong>Application Date</strong></td>
                            <td>${appDetail.applicationDate}</td>
                        </tr>
                        <tr>
                            <td><strong>Registration Expiry Date</strong></td>
                            <% Boolean isExpired = (Boolean) request.getSession().getAttribute("isExpired");
                                if (isExpired) {%>
                            <td style="color: #ff0000">${appDetail.regExpiryDate} <i>(Expired)</i></td>
                            <%} else {%>
                            <td>${appDetail.regExpiryDate}</td>
                            <%}%>
                        </tr>
                        <tr>
                            <td colspan="2" class="font-blue-madison bold warning">Registered Address</td>
                        </tr>
                        <tr>
                            <td><strong>Dzongkhag</strong></td>
                            <td>${appDetail.dzongkhagName}</td>
                        </tr>
                        <tr>
                            <td><strong>Gewog</strong></td>
                            <td>${appDetail.pGewogId}</td>
                        </tr>
                        <tr>
                            <td><strong>Village</strong></td>
                            <td>${appDetail.pVillageId}</td>
                        </tr>
                        <tr>
                            <td><strong>Address</strong></td>
                            <td>${appDetail.regAddress}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-6 table-responsive">
                    <table class="table table-condensed">
                        <tbody>
                        <tr>
                            <td colspan="2" style="background-color: #FFF2F2">Establishment Address</td>
                        </tr>
                        <tr>
                            <td><label class="control-label">Dzongkhag</label></td>
                            <td>${appDetail.regDzongkhagName}</td>
                        </tr>
                        <tr>
                            <td><label class="control-label">Address</label></td>
                            <td>${appDetail.regAddress}</td>
                        </tr>
                        <tr>
                            <td>
                                <label class="control-label">
                                    Email Id
                                </label>
                            </td>
                            <td>${appDetail.regEmail}</td>
                        </tr>
                        <tr>
                            <td><label class="control-label">Telephone Number</label></td>
                            <td>${appDetail.regPhoneNo}</td>
                        </tr>
                        <tr>
                            <td>
                                <label class="control-label">Mobile Number</label>
                            </td>
                            <td>${appDetail.regMobileNo}</td>
                        </tr>
                        <tr>
                            <td><label class="control-label">Fax Number</label></td>
                            <td>${appDetail.regFaxNo}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12">
                    <div class="panel with-nav-tabs">
                        <div class="panel-heading">
                            <ul class="nav nav-tabs">
                                <li class="tab-pane active">
                                    <a href="#ownerpartner" data-toggle="tab" class="show text-info">Owner or Partner</a>
                                </li>
                                <li>
                                    <a href="#humanresourceSection" data-toggle="tab" class="text-info"> Human Resource</a>
                                </li>
                                <li>
                                    <a href="#equipmentSection" data-toggle="tab" class="text-info"> Equipment</a>
                                </li>
                                <li>
                                    <a href="#classification" data-toggle="tab" class="text-info">Classification Details</a>
                                </li>
                                <li>
                                    <a href="#trackRecord" data-toggle="tab" class="text-info">Track Records</a>
                                </li>
                                <li>
                                    <a href="#command" data-toggle="tab" class="text-info">Comments/Adverse Record/Monitoring report</a>
                                </li>
                            </ul>
                        </div>
                        <div class="panel-body">
                            <div class="tab-content">
                                <div class="tab-pane fade in active show" id="ownerpartner">
									<span style="color: blue">
                                        Name of owner/partners/others with controlling interest
                                    </span>
                                    <table width="100%" class="table table-bordered table-hover" id="partnerDtls">
                                        <thead>
                                        <tr style="background-color: #e6f9ff">
                                            <th>Sl No</th>
                                            <th>Name</th>
                                            <th>CID Number</th>
                                            <th>Gender</th>
                                            <th>Country</th>
                                            <th>Designation</th>
                                            <th>Show In Certificate</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade in" id="humanresourceSection">
                                    <span style="color: blue">Human Resource</span>
                                    <table width="100%" class="table table-bordered table-hover " id="hrDtlsTable">
                                        <thead>
                                        <tr style="background-color: #e6f9ff">
                                            <th>Sl No</th>
                                            <th>Title</th>
                                            <th>Name</th>
                                            <th>CID Number</th>
                                            <th>Gender</th>
                                            <th>Country</th>
                                            <th>Designation</th>
                                            <th>Trade</th>
                                            <th>Qualification</th>
                                            <th>Service Type</th>
                                            <th>Join Date</th>
                                            <th>Attachment</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade in" id="equipmentSection">
                                    <table class="table table-bordered table-hover" id="equipmentTbl">
                                        <thead>
                                        <tr style="background-color: #e6f9ff">
                                            <th>Sl No</th>
                                            <th>Equipment Name</th>
                                            <th>Equipment Type</th>
                                            <th>Registration Number</th>
                                            <th>Quantity</th>
                                            <th>Attachment</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade in" id="classification">
                                    <table id="classificationTbl" class="table table-bordered table-hover">
                                        <thead>
                                        <tr style="background-color: #e6f9ff">
                                            <th colspan="2" align="center">Category</th>
                                            <th align="center">Applied</th>
                                            <th align="center">Verified</th>
                                            <th align="center">Approved</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade in" id="trackRecord">
                                    <table id="trackRecordTbl" class="table table-bordered table-hover">
                                        <thead>
                                        <tr style="background-color: #e6f9ff">
                                            <th>Sl No</th>
                                            <th>Agency</th>
                                            <th>Work Id</th>
                                            <th>Work Name</th>
                                            <th>Category</th>
                                            <th>Award Amount</th>
                                            <th>Final Amount</th>
                                            <th>Dzongkhag</th>
                                            <th>Work Start Date</th>
                                            <th>Work Complete Date</th>
                                            <th>Remarks</th>
                                            <th>APS</th>
                                            <th>Work Status</th>
                                        </tr>
                                        </thead>
                                        <tbody style="font-size: small !important;">
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade in" id="command">
                                    <span>Comments</span>
                                    <table width="100%">
                                        <thead>
                                        <tr>
                                            <th>Date</th>
                                            <th>Remarks</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <span>Adverse Record</span>
                                    <table width="100%">
                                        <thead>
                                        <tr>
                                            <th>Date</th>
                                            <th>Remarks</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <span>Monitoring Reports</span>
                                    <table width="100%">
                                        <thead>
                                        <tr>
                                            <th>Date</th>
                                            <th>Remarks</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="<c:url value="/resources/js/cdb/specializedFirm/specializedFirm_profile.js"/>"></script>
</div>
<script>
    function printInfo(cdbNo) {
        var url = '/cdb/public_access/profile/printInformation?cdbNo=' + cdbNo;
        $('#content_main_div_public_user').load(url);
    }
</script>
</body>

</html>