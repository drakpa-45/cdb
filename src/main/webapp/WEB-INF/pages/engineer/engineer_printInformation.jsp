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

<body>
<div class="card" id="printdiv">
    <div class="card-body">
        <div class="form-group fa-pull-right">
            <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12 ">
                <button class="btn btn-sm btn-primary" type="button" onclick="printAndDowoload()"><i class="fa fa-download"></i>Print</button> &nbsp;&nbsp;&nbsp;
                <button class="btn btn-sm btn-danger" type="button" onclick="doExportItem()"><i class="fa fa-edit"></i>Generate PFD</button> &nbsp;&nbsp;&nbsp;
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <strong>
                    <center>ENGINEER DETAILS</center>
                </strong>
            </div>
        </div>
        <hr />
        <input type="hidden" id="isExpired" value="<%=request.getSession().getAttribute("isExpired")%>"/>
        <div class="row form-froup">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="table-responsive">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td colspan="2" class="font-blue-madison bold warning">Personal Information</td>
                        </tr>
                        <tr>
                            <td>
                                <table class="table table-condensed">
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
                                </table>
                            </td>
                            <td>
                                <table class="table table-condensed">
                                    <tr>
                                        <td><strong>Village: </strong></td>
                                        <td>${App_Details.village}</td>
                                    </tr>
                                    <tr>
                                        <td><strong>Gewog: </strong></td>
                                        <td>${App_Details.gewog}</td>
                                    </tr>
                                    <tr>
                                        <td><strong>Dzongkhag: </strong></td>
                                        <td>${App_Details.dzongkhagId}</td>
                                    </tr>
                                    <tr>
                                        <td><strong>Country: </strong></td>
                                        <td>${App_Details.countryId}</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <hr />
                    <div class="col-md-6 table-responsive">
                        <table class="table">
                            <tbody>
                            <td colspan="1" class="font-blue-madison bold warning">Qualification Information</td>
                            <tr>
                                <td>
                                    <table class="table table-condensed">
                                        <tr>
                                            <td><strong>Qualification: </strong></td>
                                            <td>${App_Details.qualification}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>Year of Graduation: </strong></td>
                                            <td>${fn:substring(App_Details.graduationyr, 0, 4)}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>Country of Study: </strong></td>
                                            <td>${App_Details.universityCountry}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>University Name: </strong></td>
                                            <td>${App_Details.universityName}</td>
                                        </tr>
                                    </table>
                                </td>
                                <%-- <td>
                                     <table class="table table-condensed">
                                         <tr>
                                             <td><strong>Country of Study: </strong></td>
                                             <td>${App_Details.universityCountry}</td>
                                         </tr>
                                         <tr>
                                             <td><strong>University Name: </strong></td>
                                             <td>${App_Details.universityName}</td>
                                         </tr>
                                     </table>
                                 </td>--%>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6 table-responsive">
                        <table class="table">
                            <tbody>
                            <td colspan="1" class="font-blue-madison bold warning">CDB Certificate Information</td>
                            <tr>
                                <td colspan="1">
                                    <table class="table table-condensed">
                                        <tr>
                                            <td><strong>CDB Number: </strong></td>
                                            <td>${App_Details.cdbNo}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>Type: </strong></td>
                                            <td>${App_Details.serviceSectorType}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>Trade: </strong></td>
                                            <td>${App_Details.trade}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>Registration Approved Date: </strong></td>
                                            <td>${App_Details.registrationApproveDate}</td>
                                        </tr>
                                        <%-- <tr>
                                             <td><strong>Registration Expiry Date: </strong></td>
                                             <td>${App_Details.regExpDate}</td>
                                         </tr>--%>
                                        <tr>
                                            <td><strong>Registration Expiry Date</strong></td>
                                            <% Boolean isExpired = (Boolean)request.getSession().getAttribute("isExpired");
                                                if(isExpired){%>
                                            <td style="color: #ff0000">${App_Details.regExpDate} <i>(Expired)</i></td>
                                            <%}else{%>
                                            <td>${App_Details.regExpDate}</td>
                                            <%}%>
                                        </tr>
                                        <%--  <tr>
                                              <td><strong>Last Renewal Date: </strong></td>
                                              <td>${App_Details.registrationApproveDate}</td>
                                          </tr>--%>
                                    </table>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div>
</div>
<script>
    function doExportItem(){
        var HTML_Width = $("#printdiv").width();
        var HTML_Height = $("#printdiv").height();
        var top_left_margin = 12;
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

