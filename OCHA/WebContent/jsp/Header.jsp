<%@page import="java.io.Console"%>
<%@page import="in.co.sunrays.util.MenuBuilder"%>
<%@page import="in.co.sunrays.common.model.UserModel"%>
<%@page import="in.co.sunrays.util.PropertyReader"%>
<%@page import="in.co.sunrays.common.model.RoleModel"%>
<%@page import="in.co.sunrays.common.controller.LoginCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%
	UserModel userModel = (UserModel) session.getAttribute("user");
	boolean userLoggedIn = userModel != null;
	String welcomeMsg = "Hi, ";

	if (userLoggedIn) {
		String role = (String) session.getAttribute("role");
		welcomeMsg = welcomeMsg + userModel.getFirstName() + " ("
				+ role + ")";
	} else {
		welcomeMsg = welcomeMsg + "Guest";
	}
%>
<img src="<%=ORSView.IMG_FOLDER%>/logo.png" height="100px">
<span style="font-size: 40px"><%=PropertyReader.getValue("app.title")%></span>
<HR>
<table width="80%">
	<tr>
		<%
			if (userLoggedIn) {
		%>
		<td width="80%" align="left"><%=MenuBuilder.getMenu(userModel.getRoleId())%></td>
		<%
			} else {
		%>
		<a href="<%=ORSView.LOGIN_CTL%>">Home</a>
		<%
			}
		%></td>
		<p align="right"><%=welcomeMsg%></p>
	</tr>
</table>
<HR>