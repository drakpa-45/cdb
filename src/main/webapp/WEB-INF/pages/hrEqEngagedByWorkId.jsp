<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<style>
    .notification {
        text-decoration: none;
        position: relative;
        display: inline-block;
        border-radius: 1px;
    }
    /*  .notification:hover {
          background: red;
      }*/
    .notification .badge {
        position: absolute;
        top: 12px;
        right: 0.25px;
        padding: 2px 4px;
        border-radius: 50%;
        background-color: red;
        color: white;
    }
</style>
<div class="mt-2">
    <div class="container mb-9">
        <div class="row">
            <div class="col-12">
                <div class="page-header mb-0 mt-0 page-header">
                    <h1 class="page-title">Construction Development Board</h1>
                </div>
            </div>
            <div class="card" id="rejectTab" style="/* display: none */">
                <div class="card-header">
                    <h2 class="card-title font-weight-bold">Notice:</h2>
                    <%--<span class="center"><a href="<c:url value="/admin/getContractorHrDeleteDtls"/>" class="notification"><i class="notification fa fa-bell-o mt-2" style="zoom: 1.4"></i><span class="badge">${contractor_Ncount.notificationCount}</span></a></span>--%>
                </div>
                <div class="card-body">
                    <%-- <div class="form-group">
                         <div class="col-lg-12">
                             <c:forEach items="${newsAndNotifications}" var="news" varStatus="i">
                                 <ul><li>${news.messages}</li></ul>
                             </c:forEach>
                         </div>
                     </div>--%>
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#Messages" style="color: #000099"><u>Messages</u> <i class="fa fa-arrow-circle-o-down"></i></a>
                        </h4>
                        <div id="Messages" class="panel-collapse collapse">
                            <div class="form-group">
                                <div class="col-lg-12"><h4>Administrator on 12/10/2020</h4><br><br>
                                    NOTIFICATION:(02 Aug 2017)The Construction Development Board is constantly
                                    receiving request from the registered firms for checking the status of their
                                    employees and equipments. <br>
                                    However, CDB would like to inform that with launching of
                                    online services (e-zotin) w.e.f. 1st June 2016, all the firms registered with CDB
                                    can view such information and your track records after logging into your own profile
                                    from the CDB website: www.cdb.gov.bt <br>
                                    Therefore, in the interest of protection of
                                    individual firm's privacy and efficient application of online services, henceforth,
                                    CDB Secretariat will discontinue providing such information. This is also to remind
                                    that non-payment of registration fees for approved online applications within period
                                    of "one month" shall result in cancellation of your application.<br><br>
                                    Further, the standard "Undertaking Letter" for registration of employees in your firm can be
                                    downloaded from CDB web site: <br>FAQsQ 1.What are the attachments required for ownership transfer? <br>
                                    Ans: If you are transferring from husband to wife or vice versa attach a copy of marriage
                                    certificate. If you are transferring from Father to son/daughter or Mother to
                                    son/daughter attach a copy of census Family Tree acquired from Department of Civil
                                    Registration and Census.<br>
                                    Q2.What are the attachments required for renewal?Ans: 1.
                                    Refresher Course Certificate2.CV, Letter of Undertaking, Academic Transcript for
                                    each Human Resource. If you want to remove the existing HR make a Delete Request.<br>
                                    Q3.Bluebook,Insurance Copy for each RSTA registered equipment. For non-registered
                                    equipment a letter of verification issued by a government engineer equivalent of
                                    rank of an AE and above.If you want to remove an equipment make a Delete Request.Q3.
                                    What are the attachments required for change of firm name?Ans: A copy of the
                                    advertisement published in print media.<br>
                                    Q4. What are the attachments required for
                                    cancellation/surrender? <br>
                                    Ans: A copy of application stating the reason for
                                    cancellation/surrender.<br>
                                    Q5. What do I do when my application gets rejected?<br>
                                    Ans: Read the reasons for rejection carefully and reapply by clicking the reapply link on your
                                    dashboard or email. Then submit all the documents or attachments that are required
                                    and resubmit the application.<br>
                                    Q6. Where do I make the payment? <br>
                                    Ans:The payment is accepted only in the eight regional offices of the country else you make payment
                                    directly at the CDB office. The payment of fees should be made in cash if you are
                                    paying at Accounts,CDB. <br><br>
                                    NOTIFICATION:(02 Aug 2017)The Construction Development Board is constantly
                                    receiving request from the registered firms for checking the status of their
                                    employees and equipments. However, CDB would like to inform that with launching of
                                    online services (e-zotin) w.e.f. 1st June 2016, all the firms registered with CDB
                                    can view such information and your track records after logging into your own profile
                                    from the CDB website: www.cdb.gov.bt . <br><br>
                                    Dear e-Zotin Users,We would like to inform you that since the launch of our online
                                    registration we have received many request to change user name and password to login
                                    to our system and we would like to remind all Contractor's to kindly take note of
                                    the followings :<br>
                                    1. Your user or Login ID is your email address;<br>
                                    2. If you forgot your password, please click on the Forgot Password? link and your new password will be
                                    sent to your e-mail address registered with CDB;<br>
                                    3. Frequently change your password;<br>
                                    4. Do not change your mobile numbers, if so please update from your profile;<br>
                                    5. Any important notice will be sent to your e-mail address and through SMS notice;<br>
                                    6. Your application status will be notified through SMS;<br>
                                    7.Do Not pay until you receive the final payment notice;<br>
                                    8.Your final payment notice will be e-mailed to you once your application is APPROVED;<br>
                                    9. For now you can make payment at the nearest Regional Revenue and Customs Office(RRCO); <br>
                                    10. e-mail a copy of the money receipt to our accountant at accountant@cdb.gov.bt or registration@cdb.gov.bt;<br>
                                    11. OR if you are in Thimphu you can make payment at our office till 3 PM;<br>
                                    12. Applications will be processed only in working days. (Summer: 9:00 am to 5:00 pm and Winter: 9:00 am to 4:00pm)<br>
                                    13. Any mail you receive from cdbserver2015@gmail.com is an automated email.(Do Not Reply)We are working to simplify the registration process to serve you
                                    better.Web Administrator(webmaster@cdb.gov.bt) <br><br>
                                    If you are having difficulty using our online e-Zotin system. Please view the following video tutorials.<br>
                                    1.Creating an Account:Account <br>
                                    2.apply for Renewal : Renewal <br>
                                    3.Apply for a Service: Service <br><br>
                                    After your application is approved for payment and if you are paying in nearest RRCO
                                    you need to send the scanned copy of receipt to accountant@cdb.gov.bt within 30
                                    days. Otherwise your application will be cancelled. <br>
                                    <br/>This is to inform all the applicants that the system has been recently upgraded
                                    to better security and upgraded modules. While accessing the system you may
                                    encounter some issues.<br> In such situation kindly write to at registration@cdb.gov.bt/
                                    webmaster@cdb.gov.bt . <br>We will respond to you in the working hours (9:00 am to
                                    5:00pm).Further, you may call us at 326035. Your kind cooperation is solicited and
                                    inconvenience caused is highly regretted. <br><br>
                                    If you are having difficulty using our online e-Zotin system. Please view the
                                    following video tutorials. <br>
                                    1.Creating an Account:Account <br>
                                    2.apply for Renewal :Renewal <br>
                                    3.Apply for a Service: Service
                                    <br/>
                                </div>
                            </div>
                        </div>
                        <br/><br/>
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <input type="hidden" id="auditMemo" value="${auditMemo}"/>
                            <h4><span class="text-azure"><u>Audit Memo</u></span></h4>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-responsive-lg auto-index" id="auditMemoTbl">
                                        <thead>
                                        <tr>
                                            <th>SL No.</th>
                                            <th>Agency</th>
                                            <th>AIN</th>
                                            <th>Audit observation</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${auditMemo}" var="audit">
                                            <tr>
                                                <td></td>
                                                <td>${audit.serviceName}</td>
                                                <td>${audit.messages}</td>
                                                <td>${audit.remarks}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <input type="hidden" id="seekClearification" value="${seekClearification}"/>
                            <h4><span class="text-azure"><u>Seek Clearification List</u></span></h4>
                            <div class="table-responsive">
                                <table class="table table-bordered table-responsive-lg auto-index" id="seekClearificationTbl">
                                    <thead>
                                    <tr>
                                        <th>SL No.</th>
                                        <th>Tender Id</th>
                                        <th>Enquiry</th>
                                        <th>Respond</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${seekClearance}" var="clearance">
                                        <tr>
                                            <td></td>
                                            <td>${clearance.messages}</td>
                                            <td>${clearance.remarks}</td>
                                            <td>${clearance.serviceName}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <h4><span class="text-azure"><u>Application History</u></span></h4>
                            <div class="table-responsive">
                                <table class="card-table table table-bordered table-vcenter" id="architect_table">
                                    <thead>
                                    <tr>
                                        <th>Sl No.</th>
                                        <th>Application Number</th>
                                        <th>Service Name</th>
                                        <th>Application Status</th>
                                        <th>Applied Date</th>
                                    </tr>
                                    </thead>
                                    <tbody class="text-center">
                                    <c:forEach items="${applicationHistory}" var="history" varStatus="i">
                                        <tr>
                                            <td>${i.index+1}</td>
                                            <td> ${history.applicationNo}</td>
                                            <td>${history.serviceName}</td>
                                            <td>${history.appStatus}</td>
                                            <td>${history.action_Date} </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            </br>
                            <h4><span class="text-danger pl-3"><u>Rejected Application</u></span></h4>
                            <b> Note: You need to reapply the following services within 10 days. Your reapply link will be disabled after 10 days.</b>
                            </br></br>
                            <div class="table-responsive">
                                <table class="card-table table table-bordered table-vcenter" id="rejectedTblId">
                                    <thead>
                                    <tr>
                                        <th>Sl No.</th>
                                        <th>Application Number</th>
                                        <th>Service Name</th>
                                        <th>Application Status</th>
                                        <th>Applied Date</th>
                                    </tr>
                                    </thead>
                                    <tbody class="text-center">
                                    <c:forEach items="${rejectedApplications}" var="history" varStatus="i">
                                        <tr>
                                            <td>${i.index+1}</td>
                                            <td>
                                                <a href="<c:url value="/public_access/contractorRC?appNo="/>${history.applicationNo}" onclick="openAndClaimRejectedApplication2('${history.applicationNo}')" data-toggle="tooltip" data-placement="top"> ${history.applicationNo}</a></td>
                                            <td>${history.serviceName}</td>
                                            <td>${history.appStatus}</td>
                                            <td>${history.action_Date} </td>
                                        </tr>
                                    </c:forEach>
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
<script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
<script>
    $('#rejectedTblId').DataTable({
        responsive: true
    });
    $('#architect_table').DataTable({
        responsive: true
    });
    $('#auditMemoTbl').DataTable({
        responsive: true
    });

    $('#seekClearificationTbl').DataTable({
        responsive: true
    });

</script>
</body>
</html>