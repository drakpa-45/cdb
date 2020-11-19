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
                    <h3 class="card-title font-weight-bold">News/Notices

                    </h3>
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
                            Rejected Application
                        </h4>
                            Note: You need to reapply the following services within 10 days. Your reapply link will be
                            disabled after 10 days
                            <div class="table-responsive">
                                <table class="card-table table table-bordered table-vcenter" id="architect_table">
                                    <thead>
                                    <tr>
                                        <th>Sl No.</th>
                                        <th>Application Number</th>
                                        <th>Applied Date</th>
                                        <th>Rejected Date</th>
                                        <th>Reason</th>
                                    </tr>
                                    </thead>
                                    <tbody class="text-center">
                                    <c:forEach items="${rejectedApplications}" var="rejected" varStatus="i">
                                        <tr>
                                            <td>${i.index+1}</td>
                                            <td>
                                                <a href="#" onclick="openAndClaimApplication('${rejected.mytaskCount}','open')" data-toggle="tooltip"  data-placement="top" >
                                                        ${rejected.mytaskCount}
                                                </a>
                                                <a></a>
                                            </td>
                                            <td>${rejected.appDate}</td>
                                            <td>${rejected.rejectedDate}</td>
                                            <td>${rejected.remarks} </td>
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