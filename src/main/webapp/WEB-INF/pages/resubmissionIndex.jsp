<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>
<%@ page import="com.ngn.spring.project.commonDto.TasklistDto" %>
<%--
  Created by IntelliJ IDEA.
  User: Dechen Wangdi
  Date: 12/17/2019
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% TasklistDto dto = new TasklistDto();%>
<html>
<div id="loadhere">
    <div class="mt-5">
        <div class="container mb-9">
            <div class="row">
                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                    <div class="page-header mb-0 mt-0 page-header">
                        <h1 class="page-title">
                            Construction Development Board
                        </h1>
                    </div>
                    <div class="card" id="registrtaionFormCard">
                        <form action="#" id="surveyResubmissionForm" method="post" enctype="multipart/form-data">
                            <%--<form id="architectForm" action="#" method="post" enctype="multipart/form-data">--%>
                            <div class="card-header">
                                <h3 class="card-title font-weight-bold">Resubmission of an application</h3>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                        <div class="tab-content border p-3 col-lg-12">
                                            <div class="form-group row">
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
                                                    <label>Enter your Application Number:<span class="text-danger">*</span></label>
                                                    <input type="number" onclick="remove_err('app_çid_err')" name="app_çid" class="form-control" id="app_no">
                                                    <span id="app_çid_err" class="text-danger"></span>
                                                </div>
                                            </div>
                                            <hr/>
                                            <div class="form-group row pull-right">
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                    <button type="button" onclick="resubmission(dto.get(0).getMe)" class="btn btn-primary">
                                                        Next <i class="fa fa-arrow-right"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
                        <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
                        <script src="<c:url value="/resources/js/cdb/survey.js"/>"></script>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>




