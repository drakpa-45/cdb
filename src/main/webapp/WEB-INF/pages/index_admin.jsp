<%@ page import="com.ngn.spring.project.global.enu.UserRole" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/21/2020
  Time: 1:06 PM
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
            <div id="filterTasklistId">
                <div class="row">
                    <div class="col-12">
                        <div class="col-lg-4">
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Services</h3>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <%--<sec:authorize access="hasRole('ROLE_000<%=UserRole.CONTRACTOR_VERIFIER.getRefNo()%>')">--%>
                                        <li class="nav-item" style="color:black">
                                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Contractor  <i class="fa fa-chevron-down"></i></a>
                                            <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a href="<c:url value="/admin/contractor?service=NR"/>"  class="dropdown-item ">New Contractor Application</a>
                                                <a href="<c:url value="/admin/contractor?service=RC"/>"  class="dropdown-item ">Renewal of Contractor </a>
                                                <a href="<c:url value="/admin/contractor?service=OS"/>"  class="dropdown-item ">Other Services</a>
                                                <a href="<c:url value="/admin/contractor?service=CC"/>"  class="dropdown-item ">Cancellation of contractor certificate</a>
                                            </div>
                                        </li>
                                      <%--  </sec:authorize>--%>
                                        <br />
                                        <li class="nav-item" style="color:black">
                                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Consultant  <i class="fa fa-chevron-down"></i></a>
                                            <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a href="<c:url value="/admin/consultant?service=NR"/>" class="dropdown-item ">New Consultant Application</a>
                                                <a href="<c:url value="/admin/consultant?service=RC"/>"class="dropdown-item ">Renewal of Consultant </a>
                                                <a href="<c:url value="/admin/consultant?service=OS"/>"class="dropdown-item ">Other Services</a>
                                                <a href="<c:url value="/admin/consultant?service=CC"/>"class="dropdown-item ">Cancellation of Consultant certificate</a>
                                            </div>
                                        </li>
                                        <br />
                                        <li class="nav-item" style="color:black">
                                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Engineer  <i class="fa fa-chevron-down"></i></a>
                                            <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a href="<c:url value="/admin_engineer/engineer_tasklist?param=new"/>" class="dropdown-item ">New Engineer Application</a>
                                                <a href="<c:url value="/admin_engineer/engineer_tasklist?param=renew"/>" class="dropdown-item ">Renewal of Engineer </a>
                                                <a href="<c:url value="/admin_engineer/engineer_tasklist?param=cancellation"/>" class="dropdown-item ">Cancellation of Engineer certificate</a>
                                            </div>
                                        </li>
                                        <br />
                                        <li class="nav-item" style="color:black">
                                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Architect  <i class="fa fa-chevron-down"></i></a>
                                            <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a href="<c:url value="/admin_architect/architect_tasklist?param=new"/>" class="dropdown-item ">New Architect Application</a>
                                                <a href="<c:url value="/admin_architect/architect_tasklist?param=renew"/>" class="dropdown-item ">Renewal of Architect</a>
                                                <a href="<c:url value="/admin_architect/architect_tasklist?param=cancellation"/>" class="dropdown-item ">Cancellation of Architect certificate</a>
                                            </div>
                                        </li>
                                        <br />
                                        <li class="nav-item" style="color:black">
                                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Surveyor<i class="fa fa-chevron-down"></i></a>
                                            <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a href="<c:url value="/admin_survey/survey_tasklist?param=new"/>" class="dropdown-item ">New Surveyor Application</a>
                                                <a href="<c:url value="/admin_survey/survey_tasklist?param=renew"/>" class="dropdown-item ">Renewal of Surveyor</a>
                                                <a href="<c:url value="/admin_survey/survey_tasklist?param=cancellation"/>" class="dropdown-item ">Cancellation of Surveyor certificate</a>
                                            </div>
                                        </li>
                                            <br />
                                            <li class="nav-item" style="color:black">
                                                <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Specialized Firm <i class="fa fa-chevron-down"></i></a>
                                                <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                    <a href="<c:url value="/admin/specializedFirm?service=NR"/>" class="dropdown-item ">New Specialized Firm Application</a>
                                                    <a href="<c:url value="/admin/specializedFirm?service=RC"/>"class="dropdown-item ">Renewal of Specialized Firm </a>
                                                    <a href="<c:url value="/admin/specializedFirm?service=OS"/>"class="dropdown-item ">Other Services</a>
                                                    <a href="<c:url value="/admin/specializedFirm?service=CC"/>"class="dropdown-item ">Cancellation of Specialized Firm certificate</a>
                                                </div>
                                            </li>
                                        <br />
                                        <li class="nav-item" style="color:black">
                                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Specialized Trade  <i class="fa fa-chevron-down"></i></a>
                                            <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a href="<c:url value="/admin_specializedTrade/specializedTrade_tasklist?param=new"/>" class="dropdown-item ">New Specialized Trade Application</a>
                                                <a href="<c:url value="/admin_specializedTrade/specializedTrade_tasklist?param=renew"/>" class="dropdown-item ">Renewal of Specialiaed Trade </a>
                                                <a href="<c:url value="/admin_specializedTrade/specializedTrade_tasklist?param=cancellation"/>" class="dropdown-item ">Cancellation of Specialiaed Trade certificate</a>
                                            </div>
                                        </li>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div class="row">
                                <div class="col-6">
                                    <div class="card bg-blue-lighter">
                                        <div class="card-body text-center">
                                            <div class="h5"><u>Total <b>Contractor</b> Application</u></div>
                                            <div class="display-4 font-weight-bold mb-4">${contractor_Count.groupTaskCount}</div>
                                            <div class="row bg-azure-light">
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Registration</div>
                                                    <div class="display-4 font-weight-bold mb-4">${contractor_Count.registration}</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Renewal</div>
                                                    <div class="display-4 font-weight-bold mb-4">${contractor_Count.renewal}</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Cancellation </div>
                                                    <div class="display-4 font-weight-bold mb-4">${contractor_Count.cancellation}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--<div class="col-6">
                                    <div class="card">
                                        <div class="card-body text-center">
                                            <div class="h5">Consultant Application</div>
                                            <div class="display-4 font-weight-bold mb-4">80</div>
                                            <div class="progress progress-sm">
                                                <div class="progress-bar bg-red" style="width: 80%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>--%>
                                <div class="col-6">
                                    <div class="card bg-yellow-lighter">
                                        <div class="card-body text-center">
                                            <div class="h5"><u>Total <b>Consultant</b> Application</u></div>
                                            <div class="display-4 font-weight-bold mb-4">${consultant_Count.groupTaskCount}</div>
                                            <div class="row bg-azure-light">
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Registration</div>
                                                    <div class="display-4 font-weight-bold mb-4">${consultant_Count.registration}</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Renewal</div>
                                                    <div class="display-4 font-weight-bold mb-4">${consultant_Count.renewal}</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Cancellation </div>
                                                    <div class="display-4 font-weight-bold mb-4">${consultant_Count.cancellation}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <div class="card bg-cyan-lighter">
                                        <div class="card-body text-center">
                                            <div class="h5"><u>Total <b>Architect</b> Application</u></div>
                                            <div class="display-4 font-weight-bold mb-4">${architect_Count.groupTaskCount}</div>
                                            <div class="row bg-azure-light">
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Registration</div>
                                                    <div class="display-4 font-weight-bold mb-4">${architect_Count.registration}</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Renewal</div>
                                                    <div class="display-4 font-weight-bold mb-4">${architect_Count.renewal}</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Cancellation </div>
                                                    <div class="display-4 font-weight-bold mb-4">${architect_Count.cancellation}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="card bg-red-lighter">
                                        <div class="card-body text-center">
                                            <div class="h5"><u>Total <b>Engineer</b> Application</u></div>
                                            <div class="display-4 font-weight-bold mb-4">0</div>
                                            <div class="row bg-azure-light">
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Registration</div>
                                                    <div class="display-4 font-weight-bold mb-4">0</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Renewal</div>
                                                    <div class="display-4 font-weight-bold mb-4">0</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Cancellation </div>
                                                    <div class="display-4 font-weight-bold mb-4">0</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="card bg-gray-dark-lightest">
                                        <div class="card-body text-center">
                                            <div class="h5"><u>Total <b>Specialized</b> Application</u></div>
                                            <div class="display-4 font-weight-bold mb-4">${sptrade_Count.groupTaskCount}</div>
                                            <div class="row bg-azure-light">
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Registration</div>
                                                    <div class="display-4 font-weight-bold mb-4">${sptrade_Count.registration}</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Renewal</div>
                                                    <div class="display-4 font-weight-bold mb-4">${sptrade_Count.renewal}</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Cancellation </div>
                                                    <div class="display-4 font-weight-bold mb-4">${sptrade_Count.cancellation}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                <div class="col-6">
                                    <div class="card bg-green-lighter">
                                        <div class="card-body text-center">
                                            <div class="h5"><u>Total <b>Survey</b> Application</u></div>
                                            <div class="display-4 font-weight-bold mb-4">0</div>
                                            <div class="row bg-azure-light">
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Registration</div>
                                                    <div class="display-4 font-weight-bold mb-4">0</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Renewal</div>
                                                    <div class="display-4 font-weight-bold mb-4">0</div>
                                                </div>
                                                <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                    <div class="h5">Cancellation </div>
                                                    <div class="display-4 font-weight-bold mb-4">0</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            </div>
        </div>
    <div id="content_main_div"></div>
        </div>
        <script>
            function _baseURL() {
                return cdbGlobal.baseURL() + "/cdb";
            }
            function loadapplication(type,service){
                var url=_baseURL()+'/admin/architect';
               // alert(url);
                $('#content_main_div').load();
            }
        </script>
</body>

</html>
