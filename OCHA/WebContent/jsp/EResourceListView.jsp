<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.model.EResourceModel"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>

<p class="st-title">ERsource List</p>

<form action="<%=ORSView.ERESOURCE_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label> Link :</label> <input type="text"
				name="name"
				value="<%=ServletUtility.getParameter("name", request)%>">
				&nbsp; <label>Detail :</label> <input type="text" name="detail"
				value="<%=ServletUtility.getParameter("detail", request)%>">&nbsp;
				<input type="submit" name="operation" value="<%=BaseCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="0" width="100%">
		<tr>
			<th>Select</th>
			<th>Table Contains</th>
			<th>Link</th>
			<th>Details</th>
			<th>Edit</th>
		</tr>

		<%
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<EResourceModel> it = list.iterator();
			while (it.hasNext()) {
				EResourceModel model = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=model.getId()%>"></td>
			<td><%=model.getTablesContains()%></td>
			<td><%=model.getName()%></td>
			<td><%=model.getDetail()%></td>
			<td>
			<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
			%> <a href="<%=ORSView.ERESOURCE_CTL%>?id=<%=model.getId()%>"><%=label%></a>
		</tr>
		<%
			}
		%>
	</table>

	<table width="100%">
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
