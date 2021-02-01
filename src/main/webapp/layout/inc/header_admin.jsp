<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/9/2020
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 16/05/2019
  Time: 06:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="auth" property="principal"/>

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
        top: 20px;
        right: 10px;
        padding: 5px 8px;
        border-radius: 50%;
        background-color: red;
        color: white;
    }
</style>

<div class="header py-4" style="background: #120f65;">
    <div class="container ">
        <div class="d-flex">
            <a class="header-brand" href="<c:url value="/index"/>">
                <img src="<c:url value="/resources/img/logo.png"/>" class="mt-2 header-brand-img" alt="tabler logo" style="height:60px;width:60px">
            </a>
            <h3 class="text-white"><br/>Government to citizen service delivery initiative</h3>


            <div class="d-flex order-lg-2 ml-auto mt-5">
                <div class="dropdown">
                    <a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown">
                        <span class="avatar" style=""></span>
                    <span class="ml-2 d-none d-lg-block">
                      <span class="text-muted">${auth.fullName}</span>
                      <small class="text-muted d-block mt-1">Administrator</small>
                    </span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
                        <a class="dropdown-item" href="#">
                            <i class="dropdown-icon fe fe-help-circle"></i> Need help?
                        </a>
                        <a class="dropdown-item" href="<c:url value="/logout"/>">
                            <i class="dropdown-icon fe fe-log-out"></i> Sign out
                        </a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="collapse d-lg-flex header p-0 shadow-sm" id="headerMenuCollapse">
    <div class="container">
        <div class="row col-lg-12 col-md-12 col-sm-12" >
            <div class="col-sm-12 col-md-2 col-lg-2"></div>
                <div class="col-sm-12 col-md-4 col-lg-4">
                <strong>
                    <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-home"></i> Home</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-print"></i> Print Certificate <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="<c:url value="/admin_architect/architect_print_Certificate"/>" class="nav-link pl-4" style="color:black"> <i class="fa fa-check"></i>Architect</a>
                                <a href="<c:url value="/admin_engineer/engineer_print_Certificate"/>" class="nav-link pl-4" style="color:black"> <i class="fa fa-check"></i>Engineer</a>
                                <a href="<c:url value="#"/>" class="nav-link pl-4" style="color:black"> <i class="fa fa-check"></i>Contractor</a>
                                <a href="<c:url value="#"/>" class="nav-link pl-4" style="color:black"> <i class="fa fa-check"></i>Consultant</a>
                                <a href="<c:url value="/admin_survey/survey_print_Certificate"/>" class="nav-link pl-4" style="color:black"> <i class="fa fa-check"></i>Surveyor</a>
                                <a href="<c:url value="/admin_specializedTrade/specializedTrade_print_Certificate"/>" class="nav-link pl-4" style="color:black"> <i class="fa fa-check"></i>Specialized Trader</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="notification">
                                <security:authorize access="hasRole('ROLE_APPROVER')"><i class="notification fa fa-bell-o mt-3" style="zoom: 1.4"></i><span class="badge">0</span></security:authorize>
                            </a>
                        </li>
                    </ul>
                </strong>
            </div>
            <div class="col-sm-12 col-md-4 col-lg-4 mt-3 pull-right">
                <input type="text" class="form-control number" placeholder="Track application status with your application number" onchange="checkAppStatus(this.value)">
            </div>
            <div class="col-sm-12 col-md-2 col-lg-2 mt-3 pull-right">
                <button class="form-control number bg btn-success fa fa-search">Search</button>
            </div>
        </div>
    </div>
</div>

<script>
function checkAppStatus(applicationNo){
    window.open('/cdb/trackApp?applicationNo=' + applicationNo);
}
</script>
