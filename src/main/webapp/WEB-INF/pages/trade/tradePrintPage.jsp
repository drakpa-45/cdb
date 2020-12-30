<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>
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
<html>
<div>
<div class="mt-5">
    <div class="container mb-12">
        <div class="card" id="registrtaionFormCard">
            <form action="#" id="printingForm" method="post" enctype="form-data">
                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                    <br />
                        <div class="card tab2" id="print">
                            <div class="card-header">
                                <label class=>${acknowledgement_message}</label>
                            </div>
                            <div class="card-body">
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                        <label>Applicant Name: <i>${cidDetails.fullname}</i></label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                        <label>Mobile Number: <i>${cidDetails.mobileNo}</i></label>
                                    </div>
                                </div>
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                        <label>CID Number: <i>${cidDetails.cidNo}</i></label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                        <label>Date of Submission: <i>${cidDetails.initialDate}</i></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <div class="text-center">
                        <button type="button" class="btn btn-primary" onclick="PrintDiv()">Print</button>
                    </div>
                </div>
            </form>
            <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
            <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
            <script src="<c:url value="/resources/js/cdb/specialized.js"/>"></script>
        </div>
    </div>
</div>
</div>
</html>

