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
<sec:authentication var="auth" property="principal"/>
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
                        <span class="avatar" ></span>
                    <span class="ml-2 d-none d-lg-block">
                      <%--<span class="text-muted">${auth.fullName}</span>--%>
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
        <div class="row pull-right">
            <div class="col-lg order-lg-first">
                <strong>
                    <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                        <li class="nav-item">
                            <a href="<c:url value="/public/"/>" class="nav-link active" style="color:black"><i
                                    class="fa fa-home"></i> Dashboard</a>
                        </li>

                        <li class="nav-item ">
                            <a href="<c:url value="/public/locationChange"/>" class="nav-link" style="color:black"> <i
                                    class="fa fa-inbox"></i>Other Services</a>
                        </li>
                        <li class="nav-item ">
                            <a href="/trackApplication" class="nav-link" style="color:black"> <i
                                    class="fa fa-search"></i>Track Your Application</a>
                        </li>

                    </ul>
                </strong>
            </div>
        </div>
    </div>
</div>

