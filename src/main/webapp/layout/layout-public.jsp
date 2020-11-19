<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 16/05/2019
  Time: 04:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta name="msapplication-TileColor" content="#2d89ef">
    <meta name="theme-color" content="#4188c9">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <%--<link rel="icon" href="../favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" type="image/x-icon" href="../favicon.ico">--%>
    <title><sitemesh:write property='title'/></title>

    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext">
--%>
    <jsp:include page="inc/js.jsp"/>
    <jsp:include page="inc/css.jsp"/>
    <jsp:include page="inc/context.jsp"/>

</head>
<body class="">

<jsp:include page="inc/header_public.jsp"/>
<jsp:include page="/WEB-INF/pages/rpIFrame.jsp"/>
<%
    UserSessionDetailDTO userSessionDetailDTO =null;
    if((UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY)!=null){
        userSessionDetailDTO = (UserSessionDetailDTO) request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);
        /*System.out.println("CID: "+ userSessionDetailDTO.getCid());
        System.out.println("First Name: "+ userSessionDetailDTO.getFirstName());
        System.out.println("Access Token: "+userSessionDetailDTO.getAccessToken());*/
    }

%>
<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " id="public_layout_div">
                <sitemesh:write property='body'/>
            </div>
        </div>
    </div>
</div>

<jsp:include page="inc/footer.jsp"/>

</body>
</html>
