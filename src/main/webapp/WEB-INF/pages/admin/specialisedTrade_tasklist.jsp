<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/7/2020
  Time: 11:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<div class="row">
    <div class="col-12">
        <div class="mb-0 page-header">
            <h1 class="page-title">
                Construction Development Board
            </h1>
        </div>

        <div class="card">
            <div class="card-header">
                <h3 class="card-title font-weight-bold">Task List</h3>
            </div>

            <div id="tasklistId">
                <div class="card-body">
                    <div class="card">
                        <div class="bg-azure card-header" style="background: #898da0;">
                            <h3 class="card-title text-white">Group Task</h3>
                        </div>
                        <div class="table-responsive">
                            <table class="card-table table table-bordered table-vcenter" id="trade_table">
                                <thead>
                                <tr>
                                    <th>Sl No.</th>
                                    <th>Application Number</th>
                                    <th>Applicant Name</th>
                                    <th>CDB Number</th>
                                    <th>Contact Number</th>
                                    <th>Status</th>
                                    <th>Action Date</th>
                                </tr>
                                </thead>
                                <tbody class="text-center">
                                <c:forEach items="${groupTaskList}" var="task" varStatus="i">
                                    <tr>
                                        <td>${i.index+1}</td>
                                        <td>
                                            <a href="#" onclick="openAndClaimApplication('${task.applicationNo}','open')" data-toggle="tooltip"  data-placement="top" >
                                                ${task.applicationNo}
                                            </a>
                                            <a></a>
                                        </td>
                                        <td>${task.serviceName}</td>
                                        <td>${task.cdbNo}</td>
                                        <td>${task.contactNo}</td>
                                        <td><span class="status-icon bg-success"></span> ${task.appStatus} </td>
                                        <td>${task.applicationDate}</td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="card">
                        <div class="bg-azure-dark card-header" style="background: #5369d4;">
                            <h3 class="card-title text-white">My Task</h3>
                        </div>
                        <div class="table-responsive" >
                            <table class="card-table table table-bordered table-vcenter" id="trade_my_table">
                                <thead>
                                <tr>
                                    <th>Sl No.</th>
                                    <th>Application Number</th>
                                    <th>Applicant Name</th>
                                    <th>CDB Number</th>
                                    <th>Contact Number</th>
                                    <th>Status</th>
                                    <th>Action Date</th>
                                </tr>
                                </thead>
                                <tbody class="text-center">
                                <c:forEach items="${myTaskList}" var="task" varStatus="i">
                                    <tr>
                                        <td>${i.index+1}
                                            <a href="#" onclick="openAndClaimApplication('${task.applicationNo}','release')"
                                               data-toggle="tooltip" data-original-title="Release to group task">
                                            <span class="text-danger badge-pill">
                                                <i class="fa fa-times"></i>
                                            </span>
                                            </a></td>
                                        <td>
                                            <a href="#" onclick="openAndClaimApplication('${task.applicationNo}','open')" data-toggle="tooltip"  data-placement="top" >
                                                    ${task.applicationNo}
                                            </a>
                                            <a></a>
                                        </td>
                                        <td>${task.serviceName}</td>
                                        <td>${task.cdbNo}</td>
                                        <td>${task.contactNo}</td>
                                        <td><span class="status-icon bg-success"></span> ${task.appStatus} </td>
                                        <td>${task.applicationDate}</td>
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
<script>
    $('#trade_table').DataTable({
        responsive: true
    });
    $('#trade_my_table').DataTable({
        responsive: true
    });
    function openAndClaimApplication(appNo,type){
        var url= '${pageContext.request.contextPath}/admin_specializedTrade/emptylayout/openApplication?appNo='+appNo+'&type='+type;
       // var url='${pageContext.request.contextPath}/loadpagetoemptylayout/openAndClaimApplication?page_type='+page_type+'&serviceId='+serviceId+'&Application_Number='+appNo+'&action_type='+actiontype;
        $('#content_main_div').load(url);
        /*$.ajax({
            url: cdbGlobal.baseURL() + '/admin_architect/openApplication/',
            type: 'POST',
            data: {appNo:appNo,type:type},
            success: function (res) {
                alert(res);
                //window.location = cdbGlobal.baseURL() + '/admin/contractor';
            }
        });*/
    }
</script>

</body>
</html>
