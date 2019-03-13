<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String mainPage=(String)request.getAttribute("mainPage");
	if(mainPage==null||mainPage==""){
		mainPage="default.jsp";
	}
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息管理系统</title>
<link href="${pageContext.request.contextPath}/style/userInfo.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container-fluid">
<jsp:include  page="common/head.jsp"></jsp:include>
<jsp:include  page="common/menu.jsp"></jsp:include>
<jsp:include  page="<%= mainPage %>"></jsp:include>
<jsp:include  page="common/foot.jsp"></jsp:include>
</div>
</body>
</html>