<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 4/17/2020
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF/jspdf.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF-AutoTable/jspdf.plugin.autotable.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/tableExport.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/tableExport.min.js"/>"></script>
<body>
<div class="card">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
            <strong>
                Registration Information
            </strong>
        </div>
    </div>
    <hr/>
        <input type="hidden" id="isExpired" value="<%=request.getSession().getAttribute("isExpired")%>"/>
    <div class="row form-group">
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
            <img src='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo=${App_Details.cidNo}'  width='200px'  height='200px' class='pull-right'/>
        </div>
        <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
            <div class="table-responsive" id="printdiv">
                <table class="table table-condensed" id="spdf">
                    <tbody>
                    <tr>
                        <td colspan="2" class="font-blue-madison bold warning"><b>Personal Information</b></td>
                    </tr>
                    <tr>
                        <td>
                    <tr>
                        <td><strong>Full Name: </strong></td>
                        <td>${App_Details.fullname}</td>
                    </tr>
                    <tr>
                        <td><strong>CID: </strong></td>
                        <td>${App_Details.cidNo}</td>
                    </tr>
                    <tr>
                        <td><strong>Contact Number: </strong></td>
                        <td>${App_Details.mobileNo}</td>
                    </tr>
                    <tr>
                        <td><strong>Email Address: </strong></td>
                        <td>${App_Details.email}</td>
                    </tr>
                    <tr>
                        <td><strong>Telephone Number: </strong></td>
                        <td>${App_Details.telephoneNo}</td>
                    </tr>

                    <tr>
                        <td><strong>TPN Number: </strong></td>
                        <td>${App_Details.tpn}</td>
                    </tr>
                    </td>
                    </tr>
                    </tbody>

                    <tbody>
                    <tr>
                        <td colspan="2" class="font-blue-madison bold warning"><b>Permanent Address</b></td>
                    </tr>
                    <tr>
                        <td>
                    <tr>
                        <td><strong>Dzongkhag: </strong></td>
                        <td>${App_Details.dzongkhagId}</td>
                        <input type="hidden" name="dzongkhagId" id="dzongkhagId">
                    </tr>
                    <tr>
                        <td><strong>Gewog: </strong></td>
                        <td>${App_Details.gewog}</td>
                    </tr>
                    <tr>
                        <td><strong>Village: </strong></td>
                        <td>${App_Details.village}</td>
                    </tr>
                    </td>
                    </tr>
                    </tbody>
                    <div>
                        <tbody>
                        <tr>
                            <td colspan="2" class="font-blue-madison bold warning"><b>Other Information</b></td>
                        </tr>
                        <tr>
                            <td>
                        <tr>
                            <td><strong>CDB Number:</strong></td>
                            <td>${App_Details.cdbNo}</td>
                        </tr>
                        <tr>
                            <td><strong>Registration Approval Date:</strong></td>
                            <td>${App_Details.registrationApproveDate}</td>
                        </tr>
                        <tr>
                            <td><strong>Registration Expiry Date:</strong></td>
                            <% Boolean isExpired = (Boolean)request.getSession().getAttribute("isExpired");
                                if(isExpired){%>
                            <td style="color: #ff0000">${App_Details.regExpDate} <i>(Expired)</i></td>
                            <%}else{%>
                            <td>${App_Details.regExpDate}</td>
                            <%}%>
                        </tr>
                        </td>
                        </tr>
                        </tbody>
                    </div>
                </table>
            </div>
        </div>
    </div>
</div>
<br><br>
<div>
    <button class="btn btn-outline-info" type="button" id="prntid" onclick="printAndDowoload()"><i class="fa fa-download"></i>Print</button>
    <a href="#" onClick="doExportItem('#spdf',
                                        {type: 'pdf',
                                         jspdf: {orientation: 'l',
                                         margins: {right: 20, left: 20, top: 15, bottom: 15},
                                         autotable: {styles: {rowHeight: 16, halign: 'left'},
                                         tableWidth: 'auto'}
                                         }});">
        <button type="button" class="btn btn-outline-danger"><i class="fa fa-save"></i>Generate PDF</button></a>
</div>

<script>
    $(document).ready(function() {
        $('#spdf').DataTable({
            responsive: true
        });

    });
    function doExportItem(selector, params){
        var options={
            tableName: 'khklh',
            worksheetName: 'TradeRegistration',
            fileName: 'TradeRegistration'
        };
        $.extend(true, options, params);
        $(selector).tableExport(options);
    }
</script>
<script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/cdb/survey.js"/>"></script>
</body>
