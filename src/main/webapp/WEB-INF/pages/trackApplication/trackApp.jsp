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
                <div class="card-body">

                    <div class="form-group">
                        <div class="col-lg-12"><h4>
                            Application Records
                        </h4>
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
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
</script>

</body>