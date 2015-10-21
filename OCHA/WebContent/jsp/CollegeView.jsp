<%@page import="in.co.sunrays.ocha.model.CollegeModel"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.CollegeCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.CollegeModel"
	scope="request"></jsp:useBean>

<p class="st-title">College</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.COLLEGE_CTL%>" method="POST">
	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>
		<tr>
			<th>Name*</th>
			<td><input type="text" name="name"
				value="<%=DataUtility.getStringData(model.getName())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
		</tr>
		<tr>
			<th>Description*</th>
			<td><input type="text" name="description"
				value="<%=DataUtility.getStringData(model.getDescription())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
		</tr>
		<tr>
			<th></th>
			<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%>
			</td>
		</tr>
	</table>
</form>