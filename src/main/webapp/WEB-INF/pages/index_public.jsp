<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            </div>
            <div class="card" id="rejectTab" style="/* display: none */">
                <div class="card-header">
                    <h3 class="card-title font-weight-bold">Notices</h3>
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <div class="col-lg-12">
                            <c:forEach items="${newsAndNotifications}" var="news" varStatus="i">
                                <ul>
                                    <li>${news.messages}</li>
                                </ul>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12"><h4>
                            Administrator on 12/10/2020
                        </h4>
                            If you are having difficulty using our online e-Zotin system. Please view the following video tutorials. 1.Creating an Account:Account: Create Account  for Renewal : Renewal: Renewal">https://www.youtube.com/watch?vC4KcwtlTF3E3.Apply for a Service: Service : Service">https://www.youtube.com/watch?vT45halQZxso
                           <br/> NOTIFICATION:(02 Aug 2017)The Construction Development Board is constantly receiving request from the registered firms for checking the status of their employees and equipments. However, CDB would like to inform that with launching of online services (e-zotin) w.e.f. 1st June 2016, all the firms registered with CDB can view such information and your track records after logging into your own profile from the CDB website: www.cdb.gov.bt Therefore, in the interest of protection of individual firm's privacy and efficient application of online services, henceforth, CDB Secretariat will discontinue providing such information. This is also to remind that non-payment of registration fees for approved online applications within period of "one month" shall result in cancellation of your application.Further, the standard "Undertaking Letter" for registration of employees in your firm can be downloaded from CDB web site:
                            FAQsQ1.What are the attachments required for ownership transfer?Ans: If you are transferring from husband to wife or vice versa attach a copy of marriage certificate. If you are transferring from Father to son/daughter or Mother to son/daughter attach a copy of census Family Tree acquired from Department of Civil Registration and Census.Q2.What are the attachments required for renewal?Ans: 1. Refresher Course Certificate2.CV, Letter of Undertaking, Academic Transcript for each Human Resource. If you want to remove the existing HR make a Delete Request.3. Bluebook,Insurance Copy for each RSTA registered equipment. For non-registered equipment a letter of verification issued by a government engineer equivalent of rank of an AE and above.If you want to remove an equipment make a Delete Request.Q3. What are the attachments required for change of firm name?Ans: A copy of the advertisement published in print media.Q4. What are the attachments required for cancellation/surrender?Ans: A copy of application stating the reason for cancellation/surrender.Q5. What do I do when my application gets rejected?Ans: Read the reasons for rejection carefully and reapply by clicking the reapply link on your dashboard or email. Then submit all the documents or attachments that are required and resubmit the application.Q6. Where do I make the payment?Ans:The payment is accepted only in the eight regional offices of the country else you make payment directly at the CDB office. The payment of fees should be made in cash if you are paying at Accounts,CDB.
                            <br/> NOTIFICATION:(02 Aug 2017)The Construction Development Board is constantly receiving request from the registered firms for checking the status of their employees and equipments. However, CDB would like to inform that with launching of online services (e-zotin) w.e.f. 1st June 2016, all the firms registered with CDB can view such information and your track records after logging into your own profile from the CDB website: www.cdb.gov.bt .
                            Dear e-Zotin Users,We would like to inform you that since the launch of our online registration we have received many request to change user name and password to login to our system and we would like to remind  all Contractor's to kindly take note of the followings :1. Your user or Login ID is your email address;2. If you forgot your password, please click on the Forgot Password? link and your new password will be sent to your e-mail address registered with CDB;3. Frequently change your password;4.  Do not change your mobile numbers, if so please update from your profile;5. Any important notice will be sent to your e-mail address and through SMS notice;6. Your application status will be notified through SMS;7.Do Not pay until you receive the final payment notice;8.Your final payment notice will be e-mailed to you once your application is APPROVED;9. For now you can make payment at the nearest Regional Revenue and Customs Office(RRCO); 10. e-mail a copy of the money receipt to our accountant at accountant@cdb.gov.bt or registration@cdb.gov.bt;11. OR  if you are in Thimphu you can make payment at our office till 3 PM;12. Applications will be processed only in working days. (Summer: 9:00 am to 5:00 pm and Winter: 9:00 am to 4:00pm)13. Any mail you receive from cdbserver2015@gmail.com is an automated email.(Do Not Reply)We are working to simplify the registration process to serve you better.Web Administrator(webmaster@cdb.gov.bt)
                            If you are having difficulty using our online e-Zotin system. Please view the following video tutorials. 1.Creating an Account:Account 3.apply for Renewal : Renewal 3.Apply for a Service: Service
                            After your application is approved for payment and if you are paying in nearest RRCO you need to send the scanned copy of receipt to accountant@cdb.gov.bt within 30 days. Otherwise your application will be cancelled.
                            <br/>This is to inform all the applicants that the system has been recently upgraded to better security and upgraded modules.  While accessing the system you may encounter some issues. In such situation kindly write to at registration@cdb.gov.bt/ webmaster@cdb.gov.bt . We will respond to you in the working hours (9:00 am to 5:00pm).Further, you may call us at 326035. Your kind cooperation is solicited and inconvenience caused is highly regretted.
                            If you are having difficulty using our online e-Zotin system. Please view the following video tutorials.  1.Creating an Account:Account  2.apply for Renewal : Renewal  3.Apply for a Service: Service
                            <br />
                           <b> Note: You need to reapply the following services within 10 days. Your reapply link will be
                            disabled after 10 days</b>
                            </br></br>
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
                                            <td>${history.appDate} </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12"><h4>
                           Rejected Application
                        </h4>
                            <b> Note: You need to reapply the following services within 10 days. Your reapply link will be
                                disabled after 10 days</b>
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
                                            <td> ${history.applicationNo}</td>
                                            <td>${history.serviceName}</td>
                                            <td>${history.appStatus}</td>
                                            <td>${history.appDate} </td>
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

<script type="text/javascript">
    $('#fees_structure').prop('class', 'tab-pane active');
    function next(val) {
        if (val == "fees_structure") {
            $('#locationcontent').prop('class', 'tab-pane active');
            $('#personalDetials').removeClass("active");
            $('#personal_tab').removeClass("active");
            $("#personalh").css("color", "white");
            $('#personalcheck').html('<i class="fa fa-check text-white"></i>');
            $("#personalh").css("background-color", "#120f65");
            $("#locationh").css("background-color", "rgb(18, 18, 19)");
            $("#locationh").css("color", "white");
        }
        else if (val == "location" && validatelocation()) {
            $('#attachmentcontent').prop('class', 'tab-pane active');
            $('#locationhead').removeClass("active");
            $('#locationcontent').removeClass("active");
            $('#locationcheck').html('<i class="fa fa-check text-white"></i>');
            $("#personalh").css("color", "white");
            $("#personalh").css("background-color", "#120f65");
            $("#locationh").css("background-color", "#120f65");
            $("#attachmenthead").css("background-color", "rgb(18, 18, 19)");
            $("#attachmenthead").css("color", "white");
        }
    }
    function pretab(val) {
        if (val == "location") {
            $('#personal_tab').prop('class', 'tab-pane active');
            $('#locationhead').removeClass("active");
            $('#locationcontent').removeClass("active");
            $("#personalh").css("background-color", "rgb(18, 18, 19)");
            $("#locationh").css("background-color", "#120f65");
        }
        else if ("attachment" == val) {
            $('#locationcontent').prop('class', 'tab-pane active');
            $('#attachmenthead').removeClass("active");
            $('#attachmentcontent').removeClass("active");
            $("#locationh").css("background-color", "rgb(18, 18, 19)");
            $("#attachmenthead").css("background-color", "#120f65");
        }
    }
</script>

</body>