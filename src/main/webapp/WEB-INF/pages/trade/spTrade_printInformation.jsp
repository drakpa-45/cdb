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
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <strong>
                        Registration Information
                    </strong>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
                    <button class="btn btn-sm btn-primary pull-right" type="button" onclick="printInfo('${App_Details.cdbNo}')"><i class="fa fa-print"></i> Print Information</button> &nbsp;&nbsp;&nbsp;
                    <button class="btn btn-sm btn-success pull-right" type="button" onclick="printCertificate('${App_Details.cdbNo}')"><i class="fa fa-edit"></i> Print Certificate</button>&nbsp;&nbsp;&nbsp;
                </div>
            </div>
            <hr />
            <input type="hidden" id="isExpired" value="<%=request.getSession().getAttribute("isExpired")%>"/>
            <div class="row form-froup">
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
                    <img src='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo=${App_Details.cidNo}'  width='200px'  height='200px' class='pull-right'/>
                </div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
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
                                            <tr>
                                                <td><strong>Telephone Number: </strong></td>
                                                <td>${App_Details.telephoneNo}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>TPN Number: </strong></td>
                                                <td>${App_Details.tpn}</td>
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
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="table-responsive">
                            <table class="table">
                                <tbody>
                                <%--<tr>
                                    <td>
                                        <table class="table table-condensed">
                                            <tr>
                                                <td><strong>Qualification: </strong></td>
                                                <td>${App_Details.qualificationId}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Year of Graduation: </strong></td>
                                                <td>${fn:substring(App_Details.graduationyr, 0, 4)}</td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td>
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
                                    </td>
                                </tr>--%>
                                <tr>
                                    <td colspan="2" class="font-blue-madison bold warning">Other Information</td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <table class="table table-condensed">
                                            <tr>
                                                <td><strong>CDB Number: </strong></td>
                                                <td>${App_Details.cdbNo}</td>
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
                                           <%-- <tr>
                                                <td><strong>Last Renewal Date: </strong></td>
                                                <td>${App_Details.registrationApproveDate}</td>
                                            </tr>--%>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                        <button class="btn btn-info" type="button" id="prntid" onclick="printAndDowoload()"><i class="fa fa-download"></i>Print</button><br /><br />
                </div>
            </div>
        </div>
    </div>
   </div>
    <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/cdb/survey.js"/>"></script>
</body>
