
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.ForgetPasswordCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.common.model.UserModel"
	scope="request"></jsp:useBean>

<p class="st-title">Forget Password</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="Post">

	<table>
		<tr>
			<td>Email ID *</td>
			<td><input type="text" name="login" size="50"
				value=<%=DataUtility.getStringData(request.getParameter("login"))%>>
				<span class="error"> <%=ServletUtility.getErrorMessage("login", request)%>
			</span></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" name="operation"
				value="<%=BaseCtl.OP_GO%>"></td>
		</tr>

	</table>

</form>
