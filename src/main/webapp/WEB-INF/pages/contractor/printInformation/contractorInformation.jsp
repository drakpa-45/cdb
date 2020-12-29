<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div class="card" id="printdiv">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <input type="hidden" id="isExpired" value="<%=request.getSession().getAttribute("isExpired")%>"/>
                <input type="hidden" id="contractorIdFinal" value="${appDetail.id}">
                <div class="form-group fa-pull-right">
                    <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 ">
                        <button class="btn btn-sm btn-primary " type="button" onclick="printAndDowoload()"><i class="fa fa-download"></i>Print</button>
                        &nbsp;&nbsp;&nbsp;
                        <button class="btn btn-sm btn-danger " type="button" onclick="doExportItem()"><i class="fa fa-edit"></i>Generate PFD</button>
                        &nbsp;&nbsp;&nbsp;
                    </div>
                </div>
                <div><span style="color: orangered"><center><u>CONTRACTOR DETAILS</u></center></span></div>
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
                            <td></td>
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
                            <td></td>
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
                            <td><label class="control-label">Telephone Number</label></td>
                            <td>${appDetail.regPhoneNo}</td>
                        </tr>
                        <tr>
                            <td><label class="control-label">Mobile Number</label></td>
                            <td>${appDetail.regMobileNo}</td>
                        </tr>
                        <tr>
                            <td> <label class="control-label">Fax Number</label></td>
                            <td>${appDetail.regFaxNo}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12">
                    <div class="panel with-nav-tabs">
                        <div class="panel-body">
                            <div class="tab-content">
                                <div class="active show" id="ownerpartner">
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
                                <div id="humanresourceSection">
                                    <span style="color: blue">Human Resource</span>
                                    <table width="100%" class="table table-bordered table-hover " id="hrDtlsTable">
                                        <thead>
                                        <tr style="background-color: #e6f9ff">
                                            <th>Sl No</th>
                                            <th>Salutation</th>
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
                                <div id="equipmentSection">
                                    <span style="color: blue">Equipment Details</span>
                                    <table class="table table-bordered table-hover" id="equipmentTbl">
                                        <thead>
                                        <tr style="background-color: #e6f9ff">
                                            <th>Sl No</th>
                                            <th>Equipment Name</th>
                                            <th>Equipment Type</th>
                                            <th>Registration Number</th>
                                            <th>Registration To</th>
                                            <th>Quantity</th>
                                            <th>Attachment</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <div id="classification">
                                    <span style="color: blue">Category Details</span>
                                    <table id="classificationTbl" class="table table-bordered table-hover">
                                        <thead>
                                        <tr style="background-color: #EAEDED">
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
                                <div id="trackRecord">
                                    <span style="color: blue"> Track Record Details</span>
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
                                <div id="command">
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
											<span>
												Adverse Record
											</span>
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
    <button class="btn btn-info" type="button" id="prntid" onclick="printAndDowoload()"><i class="fa fa-download"></i>Print
    </button>
    <hr/>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/cdb/contractor/contractor_profile.js"/>"></script>
</div>
<script>
    function printInfo(cdbNo) {
        var url = '/cdb/public_access/profile?CDBNo=' + cdbNo;
        $('#content_main_div_public_user').load(url);
    }
    function printAndDowoload() {
        $('#prntid').hide();
        var divToPrint = document.getElementById('printdiv');
        var popupWin = window.open('', '_blank', 'width=600,height=400');
        popupWin.document.open();
        popupWin.document.write('<html><body onload="window.print()">' + divToPrint.innerHTML + '</body></html>');
        popupWin.document.write('<link rel="stylesheet" href="http://www.dynamicdrive.com/ddincludes/mainstyle.css" type="text/css" />');
        popupWin.document.close();
    }
    function doExportItem() {
        var HTML_Width = $("#printdiv").width();
        var HTML_Height = $("#printdiv").height();
        var top_left_margin = 15;
        var PDF_Width = HTML_Width + (top_left_margin * 2);
        var PDF_Height = (PDF_Width * 1.5) + (top_left_margin * 2);
        var canvas_image_width = HTML_Width;
        var canvas_image_height = HTML_Height;
        var totalPDFPages = Math.ceil(HTML_Height / PDF_Height) - 1;
        html2canvas($("#printdiv")[0], {allowTaint: true}).then(function (canvas) {
            canvas.getContext('2d');
            console.log(canvas.height + "  " + canvas.width);
            var imgData = canvas.toDataURL("image/jpeg", 1.0);
            var pdf = new jsPDF('p', 'pt', [PDF_Width, PDF_Height]);
            pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin, canvas_image_width, 0);
            for (var i = 1; i <= totalPDFPages; i++) {
                pdf.addPage(PDF_Width, PDF_Height);
                pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height * i) + (top_left_margin * 4), canvas_image_width, canvas_image_height);
            }
            pdf.save("form.pdf");
        });
    }
</script>
</body>

</html>