<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 25/07/2018
  Time: 04:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="border-right col-lg-8 p-0 mt-7">
                <div class="form-group">
                    <div class="col-md-12">
                        <h5><strong><i>Registration System</i></strong></h5>
                        <p>The <a href="<c:url value="/"/>">Construction Development Board</a> (CDB) has developed a Contractors Registration System based upon a classification of Contractors and Categorization of Works.</p>
                        <p>The registration of Contractors, re-registration, up-gradation, and any matters related to Contractors registration shall be carried out strictly in accordance with the specified principles and procedures.</p>
                        <p>CDB Registration requirement henceforth shall apply to JVs (amongst National Contractors/Consultants or with Foreign Contractors/Consultants) and also to independent Foreign Construction or Consultancy Firms if they wish to participate in contract/consultancy works in Bhutan.</p>
                        <p>All Ministries/Departments/Agencies (government corporate agencies) concerned in the public sector shall use these Registered Contractors/Consultants according to their classifications and categories in the execution of infrastructure projects. The private sectors &amp; the NGOs are also encouraged to use the same.</p>
                        <p>Verifier: cdechen09@gmail.com, ulham_06@yahoo.com, cwangmo@cdb.gov.bt</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 p-0">
                <form class="border-0 card" action="<c:url value="/auth"/>" method="post">
                    <div class="card-body p-6">
                        <div class="card-title">Login to your account</div>
                        <hr>
                        <div class="form-group">
                            <label class="form-label">Login Id</label>
                            <input type="email" class="form-control" name="username" aria-describedby="emailHelp" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" name="password" placeholder="Password">
                        </div>
                        <div class="form-footer">
                            <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                            <button type="button" onclick="res()" class="btn btn-warning btn-block">Resubmit</button>
                        </div>
                        <div class="form-group" style="color:red">
                            <c:if test="${not empty error}"><label class="form-control error">${error}</label></c:if>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>

</div>


</body>
</html>
