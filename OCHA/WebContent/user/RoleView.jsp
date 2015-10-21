<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.RoleCtl"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.common.model.RoleModel"
	scope="request"></jsp:useBean>

<p class="st-title">Add Role</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.ROLE_CTL%>" method="post">

	<%=HTMLUtility.getCommonFields(request) %>

	<table>
		<tr>
			<th>Name*</th>
			<td><input type="text" name="name"
				value="<%=DataUtility.getStringData(model.getName())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
		</tr>
		<tr>
			<th>Description *</th>
			<td><textarea name="description" cols="22"><%=DataUtility.getStringData(model.getDescription())%></textarea>
		<tr>
			<td align="center" colspan="2"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%></td>
		</tr>
	</table>
</form>