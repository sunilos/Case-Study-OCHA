<%@page import="in.co.sunrays.ocha.model.DepartementModel"%>
<%@page import="in.co.sunrays.ocha.model.CollegeModel"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.DepartementCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>
<jsp:useBean id="model"
	class="in.co.sunrays.ocha.model.DepartementModel" scope="request"></jsp:useBean>

<jsp:useBean id="collegeList" class="java.util.ArrayList"
	scope="request" />

<p class="st-title">Add Department</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.DEPARTEMENT_CTL%>" method="POST">
	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>
		<tr>
			<th>Code*</th>
			<td><input type="text" name="code"
				value="<%=DataUtility.getStringData(model.getCode())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("code", request)%></font></td>
		</tr>
		<tr>
			<th>Department Name*</th>
			<td><input type="text" name="name"
				value="<%=DataUtility.getStringData(model.getName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
		</tr>
		<tr>
			<th>Description*</th>
			<td><input type="text" name="decription"
				value="<%=DataUtility.getStringData(model.getDecription())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("decription", request)%></font></td>
		</tr>
		<tr>
			<th>College *</th>
             <td><%=HTMLUtility.getList("collegeId", model.getCollegeId()
					+ "", collegeList)%></td>
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

