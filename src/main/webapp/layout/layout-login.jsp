<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 16/05/2019
  Time: 04:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <jsp:include page="inc/js.jsp"/>
    <jsp:include page="inc/css.jsp"/>
</head>
<body>
<jsp:include page="inc/header.jsp"/>

<div class="mt-5">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12">
                <sitemesh:write property='body'/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
