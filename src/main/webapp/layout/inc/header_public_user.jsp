<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="auth" property="principal"/>
<%String cdbNo=(String) session.getAttribute("App_Details"); %>

<html>
<head>
    <link href ="css/cdb.css" type ="text/html/css" rel ="stylesheet"/>
</head>
<body>
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
                      <span class="text-muted">${auth.fullName}</span>
                      <small class="text-muted d-block mt-1">Users</small>
                    </span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
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
                    <%if (cdbNo.startsWith("SpecializedTrade")){%>
                    <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link" style="color:black"><i class="fa fa-Dashboard"></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/renewal?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Ceritifcate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/cancelspTrade?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="<c:url value="/public_access/update?param=<%=cdbNo%>"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/changepassowrd?param=${App_Details}"/>" class="nav-link active" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%}else if (cdbNo.startsWith("Architect")){%>
                    <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard"></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/renewal?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Ceritifcate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/cancel?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit "></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="<c:url value="/public_access/update?param=<%=cdbNo%>"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                                <a href="<c:url value="/public_access/update?param=<%=cdbNo%>"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-envelope"></i> Update Email</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/changepassowrd?param=${App_Details}"/>" class="nav-link active" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%}else if (cdbNo.startsWith("Survey")){%>
                    <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard "></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/renewal?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Ceritifcate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/cancel?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="<c:url value="/public_access/update?param=<%=cdbNo%>"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                                <a href="<c:url value="/public_access/update?param=<%=cdbNo%>"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-envelope"></i> Update Email</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/changepassowrd?param=${App_Details}"/>" class="nav-link active" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%} else if (cdbNo.startsWith("Engineer")){%>
                    <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard "></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/renewal?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Ceritifcate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/cancel?param="/><%=cdbNo%>" class="nav-link active" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="<c:url value="/public_access/update?param=<%=cdbNo%>"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                                <a href="<c:url value="/public_access/update?param=<%=cdbNo%>"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-envelope"></i> Update Email</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/changepassowrd?param=${App_Details}"/>" class="nav-link active" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%}else if (cdbNo.startsWith("Consultant")){%>
                    <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard "></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile"/>" class="nav-link active" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/consultantRC"/>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Ceritifcate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/consultantOS"/>" class="nav-link active" style="color:black"><i class="fa fa-edit"></i> Other Services</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/consultantCC"/>" class="nav-link active" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="<c:url value="/public_access/update"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                                <a href="<c:url value="/public_access/update"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-envelope"></i> Update Email</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/changepassword"/>" class="nav-link active" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                            <%}else if (cdbNo.startsWith("Contractor")){%>
                        <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                            <li class="nav-item">
                                <a href="<c:url value="/admin/"/>" class="nav-link" style="color:black"><i class="fa fa-Dashboard"></i>Dashboard</a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/public_access/profile"/>" class="nav-link" style="color:black;"><i class="fa fa-user"></i> Profile</a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/public_access/contractorRC"/>" class="nav-link hcolor" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Certificate</a>
                            </li>
                            <li class="nav-item" >
                                <a href="<c:url value="/public_access/contractorOS"/>" class="nav-link hcolor" style="color:black"><i class="fa fa-edit"></i> Other Services</a>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/public_access/contractorCC"/>" class="nav-link hcolor" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                            </li>
                            <li class="nav-item" style="color:black">
                                <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                                <div class="dropdown-menu dropdown-menu-arrow">
                                    <a href="<c:url value="/public_access/update"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                                    <a href="<c:url value="/public_access/update"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-envelope"></i> Update Email</a>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a href="<c:url value="/public_access/changepassword"/>" class="nav-link" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                            </li>
                        </ul>
                    <%}else if (cdbNo.startsWith("SpecializedFirm")){%>
                    <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                        <li class="nav-item">
                            <a href="<c:url value="/admin/"/>" class="nav-link active" style="color:black"><i class="fa fa-Dashboard"></i> Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/profile"/>" class="nav-link" style="color:black"><i class="fa fa-user"></i> Profile</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/specializedFirmRC"/>" class="nav-link" style="color:black"><i class="fa fa-edit"></i> Renewal of CDB Certificate</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/specializedFirmOS"/>" class="nav-link" style="color:black"><i class="fa fa-edit"></i> Other Services</a>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/specializedFirmCC"/>" class="nav-link" style="color:black"><i class="fa fa-ban"></i> Cancellation of CDB Certificate</a>
                        </li>
                        <li class="nav-item" style="color:black">
                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fa fa-edit"></i> Update <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="<c:url value="/public_access/update"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-phone"></i> Update Phone Number</a>
                                <a href="<c:url value="/public_access/update"/>" class="nav-link active pl-2" style="color:black"><i class="fa fa-envelope"></i> Update Email</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a href="<c:url value="/public_access/changepassword"/>" class="nav-link" style="color:black"><i class="fa fa-lock"></i> Change Password</a>
                        </li>
                    </ul>
                    <%}%>
                </strong>
            </div>
        </div>
    </div>
</div>
<style>
    /* unvisited link */
    .hcolor:link {
        color: black;
    }
    /* selected link */
    .hcolor:active {
        color: blue;
    }
</style>
</body>
</html>
