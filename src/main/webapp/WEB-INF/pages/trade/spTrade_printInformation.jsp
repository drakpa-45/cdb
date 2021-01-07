<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 4/17/2020
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>--%>
<%--<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF/jspdf.min.js"/>"></script>--%>
<%--<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF-AutoTable/jspdf.plugin.autotable.js"/>"></script>--%>
<%--<script type="text/javascript" src="<c:url value="/resources/dataTable/tableExport.js"/>"></script>--%>
<%--<script type="text/javascript" src="<c:url value="/resources/dataTable/tableExport.min.js"/>"></script>--%>
<body>
<div class="card">
    <div class="card-body" id="printdiv">
        <div class="form-group fa-pull-right">
            <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 ">
                <button class="btn btn-sm btn-primary" type="button" onclick="printAndDowoload()"><i class="fa fa-download"></i>Print</button> &nbsp;&nbsp;&nbsp;
                <button class="btn btn-sm btn-danger" type="button" onclick="doExportItem()"><i class="fa fa-edit"></i>Generate PFD</button> &nbsp;&nbsp;&nbsp;
            </div>
        </div>
       <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <strong>
                <center>Specialized Trade Details</center>
            </strong>
        </div>
       </div>
      <hr/>
        <input type="hidden" id="isExpired" value="<%=request.getSession().getAttribute("isExpired")%>"/>
       <div class="row form-group">
        <%--<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">--%>
            <%--<img src='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo=${App_Details.cidNo}'  width='200px'  height='200px' class='pull-right'/>--%>
        <%--</div>--%>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="table-responsive">
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
                            <td><strong>CID Number: </strong></td>
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
</div>
<script>
    function doExportItem(){
        var HTML_Width = $("#printdiv").width();
        var HTML_Height = $("#printdiv").height();
        var top_left_margin = 15;
        var PDF_Width = HTML_Width+(top_left_margin*2);
        var PDF_Height = (PDF_Width*1.5)+(top_left_margin*2);
        var canvas_image_width = HTML_Width;
        var canvas_image_height = HTML_Height;
        var totalPDFPages = Math.ceil(HTML_Height / PDF_Height)-1;
        html2canvas($("#printdiv")[0],{allowTaint:true}).then(function(canvas) {
            canvas.getContext('2d');
            console.log(canvas.height+"  "+canvas.width);
            var imgData = canvas.toDataURL("image/jpeg", 1.0);
            var pdf = new jsPDF('p', 'pt',  [PDF_Width, PDF_Height]);
            pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin,canvas_image_width,0);
            for (var i = 1; i <= totalPDFPages; i++) {
                pdf.addPage(PDF_Width, PDF_Height);
                pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4),canvas_image_width,canvas_image_height);
            }
            pdf.save("form.pdf");
        });
    }
</script>
<script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/cdb/survey.js"/>"></script>
</body>
