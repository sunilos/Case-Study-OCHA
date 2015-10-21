<%@page import="java.util.ResourceBundle"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.model.NoticeModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.common.controller.LoginCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.common.model.UserModel"
	scope="request"></jsp:useBean>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<table >
	<TR>
		<td> <h2>Login</h2></td>
		<td ><h2>Notice board</h2></td>
	</TR>
	<tr>
		<td width="30%" style="border-style: none;">

			<form action="<%=ORSView.LOGIN_CTL%>" method="POST">

				<table >
					<tr>
						<th>LoginId*</th>
						<td><input type="text" name="login" size=30
							value="<%=DataUtility.getStringData(model.getLogin())%>"><font
							color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
					</tr>
					<tr>
						<th>Password*</th>
						<td><input type="password" name="password" size=30
							value="<%=DataUtility.getStringData(model.getPassword())%>"><font
							color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2"><input type="submit" name="operation"
							value="<%=LoginCtl.OP_SIGN_IN%>"></td>
					</tr>
					<tr>
						<th></th>
						<td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>">Forget my
								password</a>&nbsp;</td>
					</tr>
				</table>
			</form>
		</td>
		<td width="30%" valign="top">
			<table border="1" > 
				<tr>
					<th>#ABC</th>
					<th>Subject</th>
					<th>Details</th>
					<th>Create Date</th>
					<th>Expire Date</th>
				</tr>
				<%
					int i = 1;
					List list = (List) request.getAttribute("noticeList");
					Iterator it = list.iterator();
					while (it.hasNext()) {
						NoticeModel noticeModel = (NoticeModel) it.next();
				%>
				<tr>
					<td><%=i++%></td>
					<td><%=noticeModel.getSubject()%></td>
					<td><%=noticeModel.getDetails()%></td>
					<td><%=noticeModel.getCreatedOn()%></td>
					<td><%=noticeModel.getExpireDate()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</td>
	</tr>
</table>
