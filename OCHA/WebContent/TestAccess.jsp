<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.ocha.model.AppRole"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String roles =  "" + AppRole.ADMIN;
AccessUtility.setWriteAccess(roles, request);

roles = AppRole.STAFF+ "" + AppRole.ADMIN;
AccessUtility.setDeleteAccess(roles, request);

%>
<H1>Write</H1>
Staff  : <%=AccessUtility.canWrite(request) %><BR>


<H1>Delete</H1>
Staff Can Write : <%=AccessUtility.canDelete(request) %>

</body>
</html>