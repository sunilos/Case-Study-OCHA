<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.model.RoleModel"%>
<%@page import="in.co.sunrays.common.controller.RoleListCtl"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<p class="st-title">Role List</p>

<form action="<%=ORSView.ROLE_LIST_CTL%>">
	<table width="80%">
		<tr>
			<td align="center"><label> Role Name :</label> <input
				type="text" name="name"
				value="<%=ServletUtility.getParameter("name", request)%>">
				&emsp; <input type="submit" name="operation"
				value="<%=BaseCtl.OP_SEARCH%>"></td>
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
		<%
			if (HTMLUtility.getErrorMessage(request).length() > 0) {
		%>
		<tr>
			<td colspan="5"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>
		<%
			}
		%>
		<%
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<RoleModel> it = list.iterator();
			while (it.hasNext()) {
				RoleModel model = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=model.getId()%>"></td>
			<td><%=model.getName()%></td>
			<td><%=model.getDescription()%></td>
			<td>
			<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
			
			%> <a href="<%=ORSView.ROLE_CTL%>?id=<%=model.getId()%>"><%=label%></a>

		</tr>
		<%
			}
		%>
	</table>
	<table width="80%">
		<tr>
			<td align="left"><input type="submit" name="operation"
				value="<%=BaseCtl.OP_PREVIOUS%>"></td>
				
			<td><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
			
			<td align="right"><input type="submit" name="operation"
				value="<%=BaseCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<%
		int pageNo = ServletUtility.getPageNo(request);
		int pageSize = ServletUtility.getPageSize(request);
		int index = ((pageNo - 1) * pageSize) + 1;
	%>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">

</form>