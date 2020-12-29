<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/3/2020
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${not empty res.status}">
    <div class="card" id="acknowledgment">
        <div class="card-header">
            <b>Contractor >> Cancellation of Certificate >> </b>Acknowledgement
        </div>
        <div class="card-body">
            <c:if test="${res.status eq 1}">
                ${res.text}
            </c:if>
            <c:if test="${res.status eq 0}">
                <span class="error"> ${res.text}</span>
            </c:if>
        </div>
    </div>
</c:if>
<c:if test="${empty res.status}">
    <div class="mt-5">
        <div class="container mb-9">
            <div class="card" id="registrtaionFormCard">
                <div class="card-header">
                    <h3 class="card-title font-weight-bold">Contractor >> Cancellation of Certificate >> CDB No: ${cdbNo}</h3>
                </div>
                <div class="card-body">
                    <div class="form-group row col-lg-12 col-md-12 col-sm-12 col-xs-12 alert alert-danger text-center" style="font-size: 10px">
                        <c:if test="${renewalCheck.status eq 1}">
                            <span class="error">${renewalCheck.text}</span>
                        </c:if>
                        <c:if test="${renewalCheck.status eq 0}">
                            <span class="error">${renewalCheck.text}</span>
                        </c:if>
                    </div>
                </div>
                <form method="post" class="card-body" id="contractorCCForm">
                    <input type="hidden" name="cdbNo" value="${cdbNo}"/>
                    <div class="form-group row">
                        <label class="col-lg-4 form-label" for="cancellationReason">Cancellation Reason
                            <span class="text-danger">*</span></label>
                        <textarea name="cancellationReason" class="form-control col-lg-6" id="cancellationReason" rows="6" required="required"></textarea>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-4 form-label" for="cancelConfirm"> Are you sure you want to cancel the certificate?</label>
                        <input type="checkbox" class="checkbox" id="cancelConfirm" value="C" required="" name="cancelConfirm"/>
                    </div>
                    <div class="form-group row">
                        <input type="submit" class="btn btn-primary" id="btnSubmit" value="Submit"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</c:if>
<script type="text/javascript" src="<c:url value="/resources/js/cdb/contractor/contractorCC.js"/>"></script>
</body>
</html>
