<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.ChangePasswordCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<jsp:useBean id="model" class="in.co.sunrays.common.model.UserModel"
	scope="request"></jsp:useBean>

<p class="st-title">Change Password</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>


<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="POST">

	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>

		<tr>
			<th>Old Password*</th>
			<td><input type="password" name="oldPassword"
				value=<%=DataUtility
					.getString(request.getParameter("oldPassword") == null ? ""
							: DataUtility.getString(request
									.getParameter("oldPassword")))%>><font
				color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
		</tr>

		<tr>
			<th>New Password*</th>
			<td><input type="password" name="newPassword"
				value=<%=DataUtility
					.getString(request.getParameter("newPassword") == null ? ""
							: DataUtility.getString(request
									.getParameter("newPassword")))%>><font
				color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
		</tr>

		<tr>
			<th>Confirm Password*</th>
			<td><input type="password" name="confirmPassword"
				value=<%=DataUtility.getString(request
					.getParameter("confirmPassword") == null ? "" : DataUtility
					.getString(request.getParameter("confirmPassword")))%>><font
				color="red"> <%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></font></td>
		</tr>

		<tr>
			<th></th>
			<td colspan="2"><input type="submit" name="operation"
				value="<%=ChangePasswordCtl.OP_SAVE%>"> &nbsp;<input
				type="submit" name="operation"
				value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>"></td>
		</tr>

	</table>
</form>