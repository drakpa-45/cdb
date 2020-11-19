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
                            <table class="card-table table table-bordered table-vcenter" id="group_task">
                                <thead>
                                <tr>
                                    <th>Sl No.</th>
                                    <th>Application Number</th>
                                    <th>Service Name</th>
                                    <th>Firm Name</th>
                                    <th>Contact Number</th>
                                    <th>Status</th>
                                    <th>Action Date</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody class="text-center">
                                <c:forEach items="${groupTaskList}" var="task" varStatus="i">
                                    <tr>
                                        <td>${i.index+1}</td>
                                        <%--<td><a href="<c:url value="/admin/contractor/${task.applicationNo}"/>" class="font-weight-bold">${task.applicationNo}</a></td>--%>
                                        <td><a>${task.applicationNo}</a></td>
                                        <td>${task.serviceName}</td>
                                        <td>${task.firmName} </td>
                                        <td>${task.contactNo}</td>
                                        <td><span class="status-icon bg-success"></span> ${task.appStatus} </td>
                                        <td>${task.applicationDate}</td>
                                        <td><input type="button" class="btn btn-info gMToggle groupTask" value="Take Action"> </td>
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
                            <table class="card-table table table-bordered table-vcenter" id="my_task">
                                <thead>
                                <tr>
                                    <th>Sl No.</th>
                                    <th>Application Number</th>
                                    <th>Service Name</th>
                                    <th>Firm Name</th>
                                    <th>Contact Number</th>
                                    <th>Status</th>
                                    <th>Action Date</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody class="text-center">

                                <c:forEach items="${myTaskList}" var="task" varStatus="i">
                                    <tr>
                                        <td>${i.index+1}</td>
                                        <td><a href="<c:url value="/admin/consultant/${task.applicationNo}"/>" class="font-weight-bold">${task.applicationNo}</a></td>
                                        <td>${task.serviceName}</td>
                                        <td>${task.firmName} </td>
                                        <td>${task.contactNo}</td>
                                        <td><span class="status-icon bg-success"></span> ${task.appStatus} </td>
                                        <td>${task.applicationDate}</td>
                                        <td><div class="btn btn-info gMToggle myTask"> Send back</div> </td>
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
    $('#group_task').DataTable({
        responsive: true
    });
    $('#my_task').DataTable({
        responsive: true
    });
    function gMToggle(){
        $('body').on('click','.gMToggle',function(){
            var flag;
            $(this).hasClass('groupTask')==true?flag='G':flag='M';
            var appNo = $(this).closest('tr').find('a').text();
            $.ajax({
                url: cdbGlobal.baseURL() + '/admin/consultant/send2MyOrGroupTask',
                type: 'POST',
                data: {appNo:appNo,flag:flag},
                success: function (res) {
                    window.location = cdbGlobal.baseURL() + '/admin/consultant';
                }
            });
        });
    }
    gMToggle();
</script>

</body>
</html>
