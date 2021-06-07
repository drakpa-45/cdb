<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body class="">
<div class="mt-5">
    <div class="container mb-9">
        <form id="modalForm"> <%--Confirmation Modal--%>
            <div id="confirmationModel">
                <div class=""><span><b>Reminders for replacement of deleted Human Resource!</b></span></div>
                <div class="modal-body form-horizontal">
                    <div class="alert alert-avatar">
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                                <ol>
                                    <c:forEach var="ap" items="${hrList}">
                                        <li><span class="text-orange">${ap.firmName} (${ap.cdbNo})</span> has deleted Human Resource: <span class="text-orange">${ap.regAddress}</span> bearing CID No./Work Permit <span class="text-orange">${ap.tradeLicenseNo}</span> on <span class="text-orange">${ap.appDate}</span>.<a
                                            href="#" class="text-danger"><b>Click here if Replaced</b></a>
                                        </li>
                                    </c:forEach>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
</body>
</html>