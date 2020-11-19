<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 4/15/2020
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<body>
    <div class="mt-5">
        <div class="container mb-9">
            <div class="row">
                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                    <div class="page-header mb-0 mt-0 page-header">
                        <h1 class="page-title">
                            Construction Development Board
                        </h1>
                    </div>
                </div>
                <div class="card" id="registrtaionFormCard">
                    <div class="card-header">
                        <h3 class="card-title font-weight-bold">Print List for SpecializedTrade</h3>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                <div class="table-responsive">
                                    <table class="card-table table table-bordered table-vcenter" id="architect_table">
                                        <thead>
                                        <tr>
                                            <th>Sl No.</th>
                                            <th>CDB Number</th>
                                            <th>CID Number</th>
                                            <th>Applicant Name</th>
                                            <th>Contact Number</th>
                                            <th>Email</th>
                                            <th>Approved Date</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody class="text-center">
                                        <c:forEach items="${printList}" var="task" varStatus="i">
                                            <tr>
                                                <td>${i.index+1}</td>
                                                <td>${task.cdbNo}</td>
                                                <td>${task.cidNo}</td>
                                                <td>${task.fullname}</td>
                                                <td>${task.mobileNo}</td>
                                                <td>${task.email}</td>
                                                <td>${task.approvaldate}</td>
                                                <td>
                                                    <button class="btn btn-primary" type="button" onclick="printCertificate('${task.cdbNo}')">
                                                        <i class="fa fa-print"></i>Print
                                                    </button>
                                                </td>
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
    <script src="<c:url value="/resources/js/cdb/specialized.js"/>"></script>
</body>
