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
                <input type="hidden" id="consultantIdFinal" value="${appDetail.id}">
                <div class="form-group fa-pull-right">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                        <button class="btn btn-sm btn-primary" type="button" onclick="printInfo('${appDetail.cdbNo}')">
                            <i class="fa fa-print"></i> Print Information
                        </button>
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
                            <td><strong>Tpn Number</strong></td>
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
                            <td><strong>Village</strong></td>
                            <td>${appDetail.pVillageId}</td>
                        </tr>
                        <tr>
                            <td><strong>Gewog</strong></td>
                            <td>${appDetail.pGewogId}</td>
                        </tr>
                        <tr>
                            <td><strong>Address</strong></td>
                            <td>${appDetail.estAddress}</td>
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
                            <td>${appDetail.estAddress}</td>
                        </tr>
                        <tr>
                            <td><label class="control-label">Email Id</label></td>
                            <td>${appDetail.regEmail}</td>
                        </tr>
                        <tr>
                            <td> <label class="control-label">Telephone Number</label></td>
                            <td>${appDetail.regPhoneNo}</td>
                        </tr>
                        <tr>
                            <td><label class="control-label">Mobile Number</label></td>
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
                                    <a href="#ownerpartner" data-toggle="tab" class="active show">Owner or Partner</a>
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
                                    <span style="color: blue">Category Details</span>
                                    <table id="classificationTbl" class="table table-bordered table-hover">
                                        <thead style="background-color: #F2F2F2">
                                        <tr>
                                            <td>Category</td>
                                            <td>Apply for Class</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%-- TODO:: kept as static since loading dynamic hampers the performance --%>
                                        <tr>
                                            <td>
                                                <input type="hidden" id="cateId0" value="e6372584-bc15-11e4-81ac-080027dcfac6" name="categories[0].serviceCateID">Architectural Services
                                            </td>
                                            <td>
                                                <a href="javascript:void(0)" style="color: #006699" title='Architectural and Interior Design' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="asone" value="2dc059a3-bc17-11e4-81ac-080027dcfac6" style="zoom:1.4" class="categoryCheck" name="itemId" disabled>A1 &nbsp; &nbsp; &nbsp;&nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Urban Planning' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="astwo" class="categoryCheck"style="zoom:1.4"  value="378c8114-bc17-11e4-81ac-080027dcfac6" name="itemId" disabled>A2&nbsp;&nbsp; &nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Landscaping and Site Development' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="asthree" class="categoryCheck"style="zoom:1.4"  value="42914a22-bc17-11e4-81ac-080027dcfac6" name="itemId" disabled>A3
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="hidden" id="cateId1" value="f39b9245-bc15-11e4-81ac-080027dcfac6" name="categories[1].serviceCateID">Civil Engineering Services
                                            </td>
                                            <td>
                                                <a href="javascript:void(0)" style="color: #006699" title='Structural Design' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="cvsone" class="categoryCheck"style="zoom:1.4"  name="itemId" value="51f58a70-bc17-11e4-81ac-080027dcfac6" disabled>C1&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Geo-Tech Studies' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="cvstwo" class="categoryCheck"style="zoom:1.4"  name="itemId" value="5b147a4d-bc17-11e4-81ac-080027dcfac6" disabled>C2&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Social & ENviroment Studies' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="cvsthree" class="categoryCheck"style="zoom:1.4"  name="itemId" value="6516bfdd-bc17-11e4-81ac-080027dcfac6" disabled>C3&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Roads, Bridges, Buildings & Air Ports' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="cvsfour" class="categoryCheck"style="zoom:1.4"  name="itemId" value="7b84fd72-bc17-11e4-81ac-080027dcfac6" disabled>C4&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Irrigation, Hydraulics, WaterSupply, Sanitation, Sewerage & Solid Waste' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="cvsfive" class="categoryCheck"style="zoom:1.4"  name="itemId" value="a8ee79e6-bc17-11e4-81ac-080027dcfac6" disabled>C5&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Construction Management, Site Supervision & Surveying' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="cvssix" class="categoryCheck"style="zoom:1.4"  name="itemId" value="be34bd47-bc17-11e4-81ac-080027dcfac6" disabled>C6&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Water Resources & Hydro Power Projects' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="cvsseven" class="categoryCheck"style="zoom:1.4"  name="itemId" value="cc3bfc36-bc17-11e4-81ac-080027dcfac6" disabled>C7
                                            </td>
                                        </tr>

                                        <tr>
                                            <td><input type="hidden" id="cateId2" value="fb2aa1a7-bc15-11e4-81ac-080027dcfac6" name="categories[2].serviceCateID">Electrical Engineering Services</td>
                                            <td>
                                                <a href="javascript:void(0)" style="color: #006699" title='Investigation & Design of Hydro Power Projects' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="eesone" class="categoryCheck"style="zoom:1.4"  name="itemId" value="ded7b309-bc17-11e4-81ac-080027dcfac6" disabled>E1&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Operation & Maintenance of Hydro Power Projects' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="eestwo" class="categoryCheck"style="zoom:1.4"  name="itemId" value="ef1e617f-bc17-11e4-81ac-080027dcfac6" disabled>E2&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Urban & Rural Electrification, Transmission Line, Communication & Scada' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="eesthree" class="categoryCheck"style="zoom:1.4"  name="itemId" value="1a4e9b6f-bc18-11e4-81ac-080027dcfac6" disabled>E3&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Construction Management & Site Supervision' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="eesfour" class="categoryCheck"style="zoom:1.4"  name="itemId" value="271c4483-bc18-11e4-81ac-080027dcfac6" disabled>E4&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Sub-station' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="eesfive" class="categoryCheck"style="zoom:1.4"  name="itemId" value="30a3dd3c-bc18-11e4-81ac-080027dcfac6" disabled>E5&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Energy Efficiency Services' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="eessix" class="categoryCheck"style="zoom:1.4"  name="itemId" value="3ceb09ba-bc18-11e4-81ac-080027dcfac6" disabled>E6&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='House Wiring' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="eesseven" class="categoryCheck"style="zoom:1.4"  name="itemId" value="4461b1b0-bc18-11e4-81ac-080027dcfac6" disabled>E7
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="hidden" id="cateId3" value="2adfae00-be66-11e9-9ac2-0026b988eaa8" name="categories[3].serviceCateID">Surveying Services</td>
                                            <td>
                                                <a href="javascript:void(0)" style="color: #006699" title='Cadastral' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="sone" class="categoryCheck"style="zoom:1.4"  name="itemId" value="8a6ea970-be66-11e9-9ac2-0026b988eaa8" disabled>S1&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Topographic' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="stwo" class="categoryCheck"style="zoom:1.4"  name="itemId" value="b20d9185-be66-11e9-9ac2-0026b988eaa8" disabled>S2&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Geodetic & Precision' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="sthree" class="categoryCheck"style="zoom:1.4"  name="itemId" value="fb9e92cb-be66-11e9-9ac2-0026b988eaa8" disabled>S3&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Photogrammetric' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="sfour" class="categoryCheck"style="zoom:1.4"  name="itemId" value="1129c568-be67-11e9-9ac2-0026b988eaa8" disabled>S4&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Instrument Calibration, Maintenance and Certification' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="sfive" class="categoryCheck"style="zoom:1.4"  name="itemId" value="3aba7cc5-be67-11e9-9ac2-0026b988eaa8" disabled>S5&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='GIS & Remote Sensing' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="ssix" class="categoryCheck" style="zoom:1.4" name="itemId" value="5fa269a3-be67-11e9-9ac2-0026b988eaa8" disabled>S6&nbsp; &nbsp;&nbsp; &nbsp;
                                                <a href="javascript:void(0)" style="color: #006699" title='Bathymetric' class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                                <input type="checkbox" id="sseven" style="zoom:1.4" class="categoryCheck" name="itemId" value="4cd73d78-be67-11e9-9ac2-0026b988eaa8" disabled>S7
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade in" id="trackRecord">
                                    <table id="trackRecordTbl" class="table table-bordered table-hover">
                                        <thead>
                                        <tr style="background-color: #EAEDED">
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
                                    <span style="color: blue">Comments</span>
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
<script type="text/javascript" src="<c:url value="/resources/js/cdb/consultant/consultant_profile.js"/>"></script>
</div>
<script>
    function printInfo(cdbNo) {
        var url = '/cdb/public_access/profile/printInformation?CDBNo=' + cdbNo;
        $('#content_main_div_public_user').load(url);
    }
</script>
</body>

</html>