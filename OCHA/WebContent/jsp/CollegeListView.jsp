<%@page import="in.co.sunrays.ocha.controller.CollegeCtl"%>
<%@page import="in.co.sunrays.ocha.controller.CollegeListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.ocha.model.CollegeModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.CollegeModel"
	scope="request"/>

<p class="st-title">College List</p>

<form action="<%=ORSView.COLLEGE_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label>Name:</label> <input type="text"
				name="name"
				value="<%=ServletUtility.getParameter("name", request)%>">&emsp;
				<input type="submit" name="operation"
				value="<%=CollegeListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="1" width="80%">
		<tr>
			<th>Select</th>
			<th>Name</th>
			<th>Description</th>
			<th>Edit</th>
		</tr>
		<tr>
			<td colspan="4"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>
		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			List list = ServletUtility.getList(request);
			Iterator<CollegeModel> it = list.iterator();

			while (it.hasNext()) {

				CollegeModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescription()%></td>
			<td>
			<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
			%><a href="<%=ORSView.COLLEGE_CTL%>?id=<%=bean.getId()%>"><%=label%></a>
			</td>
		</tr>

		<%
			}
		%>
	</table>
	<table width="80%">
		<tr>
			<td><input type="submit" name="operation"
				value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>
				
				
			<td><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
				<td align="right"><input type="submit" name="operation"
				value="<%=CollegeCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
</center>
