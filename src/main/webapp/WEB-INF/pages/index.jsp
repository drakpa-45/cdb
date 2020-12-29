<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<div class="card">
    <div class="card-header col-sm-12 col-md-12 col-lg-12">
        <div class="col-sm-12 col-md-7 col-lg-7"><h3 class="card-title font-weight-bold">Construction Development Board Services </h3></div>
            <div class="col-sm-12 col-md-4 col-lg-4 pull-right">
                <input type="text" class="form-control number" placeholder="Track application status with your application number" onchange="checkAppStatus(this.value)">
            </div>
        <div class="col-sm-12 col-md-1 col-lg-1 pull-right">
            <button class="form-control number bg btn-success fa fa-search">Search</button>
        </div>
    </div>
    <div class="card-body">
        <div class="row">
            <div class="col-md-7 col-lg-7">
                <div class="form-group">
                    <h5> Registration System</h5>
                    <p>
                        The<a href="https://www.citizenservices.gov.bt/CDBPUBLIC/#"> Construction Development Board</a>(CDB)
                        has developed a Contractors Registration System based upon a classification of Contractors and
                        Categorization of Works.
                    </p>
                    <p>
                        <span>
                              The registration of Contractors, re-registration, up-gradation, and any matters related to Contractors registration shall be carried out strictly in accordance with the specified principles and procedures.
                        </span>
                    </p>
                    <p>
                        CDB Registration requirement henceforth shall apply to JVs (amongst National
                        Contractors/Consultants or with Foreign Contractors/Consultants) and also to independent Foreign
                        Construction or Consultancy Firms if they wish to participate in contract/consultancy works in
                        Bhutan.
                    </p>
                    <p>
                        All Ministries/Departments/Agencies (government corporate agencies) concerned in the public
                        sector shall use these Registered Contractors/Consultants according to their classifications and
                        categories in the execution of infrastructure projects. The private sectors the NGOs are also
                        encouraged to use the same.
                    </p>
                </div>
            </div>
            <div class="col-lg-5 col-md-5 p-0">
                <form class="border-0 card" action="<c:url value="/auth"/>" method="post">
                    <div class="card-body p-6">
                        <div class="card-title text-lime"><u><b>Login to your account</b></u></div>
                        <hr>
                        <%--<div class="form-group">
                            <label class="form-label">Login Id</label>
                            <input type="email" class="form-control" name="username" aria-describedby="emailHelp" placeholder="Enter email">
                        </div>--%>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-user-circle"></i></span>
                            </div>
                            <input type="email" class="form-control" name="username" placeholder="Enter your email">
                        </div>
                       <%-- <div class="form-group">
                            <label class="form-label">
                                Password
                            </label>
                           <input type="password" class="form-control" name="password" placeholder="Password">
                        </div>--%>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-lock"></i></span>
                            </div>
                            <input type="text" class="form-control" name="password" placeholder="Enter your Password">
                        </div>
                        <div>
                            <label>
                                <div class="form-group">
                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                        <span><input type="checkbox" id="submitcheckbox" onclick="" name="tnc" class="required"  style="width:15px;height:15px;"></span>
                                        <span class="bold text-info">Remember me</span>
                                    </div>
                                </div>
                            </label>
                            <label>
                                <div class="form-group">
                                    <div class="col-lg-12 col-lg-12 col-md-12 col-sm-12">
                                        <a href="<c:url value="/forgetPassword"/>">
                                            <span class="bold text-yellow"><u><i>Forgot Password?</i></u></span>
                                        </a>
                                    </div>
                                </div>
                            </label>
                        </div>
                        <div class="form-footer col-md-3">
                            <button type="submit" class="btn btn-indigo align-content-center">Sign in</button>
                           <%-- <button type="button" onclick="res()" class="btn btn-warning btn-block">Resubmit</button>--%>
                        </div>
                        <div class="form-group" style="color:red">
                            <c:if test="${not empty error}">
                                <label class="form-control error">${error}</label>
                            </c:if>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-12 col-lg-12">
                <div class="form-group">
                   <center> <h3 class="align-items-center text-orange" style="font-family: themify"><u><b>New Registration</b></u></h3></center>
                    <div class="col-md-12 col-lg-14 col-sm-14">
                        <div class="col-md-2 col-lg-2 col-sm-12">
                            <a href="<c:url value="/contractorNR"/>"> <img src="<c:url value="/resources/img/contractor.jpg"/>" class="mt-2 ml-4 header-brand-img"  data-toggle="tooltip" data-original-title="Contractor"  class="tooltipCSSSelector" alt="tabler logo" style="height:60px;width:60px"></a>
                            <div class="text-block">
                                <h4><a href="<c:url value="/contractorNR"/>">Contractor</a></h4>
                            </div>
                            </div>
                            <div class="col-md-2 col-lg-2 col-sm-12">
                                <a href="<c:url value="/consultantNR"/>"><img src="<c:url value="/resources/img/consultant.png"/>" class="mt-2 ml-4 header-brand-img" data-toggle="tooltip"  title='Consultant' class="tooltipCSSSelector" alt="tabler logo"style="height:60px;width:60px"></a>
                            <div class="text-block">
                                <h4><a href="<c:url value="/consultantNR"/>">Consultant</a></h4>
                            </div>
                        </div>
                        <div class="col-md-2 col-lg-2 col-sm-12">
                            <a href="<c:url value="/specializedFirm"/>"> <img src="<c:url value="/resources/img/specializedfirm.png"/>" class="mt-2 ml-6 header-brand-img" title='Specialized Firm' data-toggle="tooltip"  class="tooltipCSSSelector" alt="tabler logo" style="height:60px;width:60px"></a>
                            <div class="text-block">
                                <h4><a href="<c:url value="/specializedFirm"/>">Specialized Firm</a></h4>
                            </div>
                        </div>
                        <div class="col-md-2 col-lg-2 col-sm-12">
                            <a href="<c:url value="/architectIndex"/>"> <img src="<c:url value="/resources/img/architect.jpg"/>" class="mt-2 header-brand-img" title='Architect'data-toggle="tooltip"  class="tooltipCSSSelector" alt="tabler logo" style="height:60px;width:60px"></a>
                            <div class="text-block">
                                <h4><a href="<c:url value="/architectIndex"/>">Architect</a></h4>
                            </div>
                        </div>
                        <div class="col-md-2 col-lg-2 col-sm-12">
                            <a href="<c:url value="/tradeIndex"/>"> <img src="<c:url value="/resources/img/specializedtreade.jpg"/>" class="mt-2  ml-6 header-brand-img"data-toggle="tooltip"   title='Specialized Trade' class="tooltipCSSSelector"alt="tabler logo" style="height:60px;width:60px"></a>
                            <div class="text-block">
                                <h4><a href="<c:url value="/tradeIndex"/>">Specialized Trade</a></h4>
                            </div>
                        </div>
                        <div class="col-md-2 col-lg-2 col-sm-12">
                            <a href="<c:url value="/surveyIndex"/>"> <img src="<c:url value="/resources/img/surveyor.jpg"/>" class="mt-2 ml-2 header-brand-img" title='Surveyor'data-toggle="tooltip"  class="tooltipCSSSelector" alt="tabler logo" style="height:60px;width:60px"></a>
                            <div class="text-block">
                                <h4><a href="<c:url value="/surveyIndex"/>">Surveyor</a></h4>
                            </div>
                        </div>
                        <div class="col-md-2 col-lg-2 col-sm-12">
                            <a href="<c:url value="/engineerNR"/>"> <img src="<c:url value="/resources/img/engineer.jpg"/>" class="mt-2 ml-2 header-brand-img" title='Engineer'data-toggle="tooltip"  class="tooltipCSSSelector" alt="tabler logo" style="height:60px;width:60px"></a>
                            <div class="text-block">
                                <h4><a href="<c:url value="/engineerNR"/>">Engineer</a></h4>
                            </div>
                        </div>
                    </div>
                 </div>
            </div>
        </div>
    </div>
</div>

<script>$('[data-toggle="tooltip"]').tooltip();
    function checkAppStatus(applicationNo){
        window.open('/cdb/trackApp?applicationNo=' + applicationNo);
    }
</script>
</body>
</html>
