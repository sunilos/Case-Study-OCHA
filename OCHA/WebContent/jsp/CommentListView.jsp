<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.model.CommentModel"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<p class="st-title">Comment List</p>

<form action="<%=ORSView.COMMENT_LIST_CTL%>">
	<table width="100%">
		<tr>
			<td align="center"><label> Name :</label> <input type="text"
				name="subject"
				value="<%=ServletUtility.getParameter("subject", request)%>">
				&emsp; <input type="submit" name="operation"
				value="<%=BaseCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>EResource Name</th>
			<th>Text</th>
			<th>Edit</th>
		</tr>
		<%
			if (HTMLUtility.getErrorMessage(request).length() > 0) {
		%>
		<tr>
			<td colspan="4"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>
		<%
			}
		%>
		<%
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<CommentModel> it = list.iterator();
			while (it.hasNext()) {
				CommentModel model = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=model.getId()%>"></td>
			<td><%=model.getName()%></td>
			<td><%=model.getText()%></td>
			<td>
				<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%> <a href="<%=ORSView.COMMENT_CTL%>?id=<%=model.getId()%>"><%=label%></a>

		</tr>
		<%
			}
		%>
	</table>
	<table width="100%">
		<tr>
			<td align="left"><input type="submit" name="operation"
				value="<%=BaseCtl.OP_PREVIOUS%>"></td>
			<td><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
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