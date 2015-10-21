<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.model.DepartementModel"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.ocha.controller.DepartementCtl"%>
<%@page import="in.co.sunrays.ocha.controller.DepartementListCtl"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<p class="st-title">Department List</p>

<form action="<%=ORSView.DEPARTEMENT_LIST_CTL%>">
	<table width="80%">
		<tr>
			<td align="center"><label>Name:</label> <input type="text"
				name="name"
				value="<%=ServletUtility.getParameter("name", request)%>">&emsp;
				<input type="submit" name="operation"
				value="<%=DepartementListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="1" width="80%">
		<tr>
			<th>Select</th>
			<th>Code</th>
			<th>Name</th>
			<th>Description</th>
			<th>College Id</th>
			<th>Edit</th>
		</tr>
		<%
			if (HTMLUtility.getErrorMessage(request).length() > 0) {
		%>
		<tr>
			<td colspan="6"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>
		<%
			}
		%>
		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			List list = ServletUtility.getList(request);
			Iterator<DepartementModel> it = list.iterator();

			while (it.hasNext()) {

				DepartementModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getCode()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDecription()%></td>
			<td><%=bean.getCollegeId()%></td>
            <td>
			<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%><a href="<%=ORSView.DEPARTEMENT_CTL%>?id=<%=bean.getId()%>"><%=label%></a>
				</td>
			
		</tr>

		<%
			}
		%>
	</table>
	<table width="80%">
		<tr>
			<td><input type="submit" name="operation"
				value="<%=DepartementListCtl.OP_PREVIOUS%>"></td>
				
			<td><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
			
			<td align="right"><input type="submit" name="operation"
				value="<%=DepartementCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
</center>
