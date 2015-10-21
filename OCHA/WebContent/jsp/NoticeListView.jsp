<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="in.co.sunrays.ocha.model.NoticeModel"%>
<%@page import="in.co.sunrays.ocha.controller.NoticeListCtl"%>
<%@page import="in.co.sunrays.common.controller.UserListCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<p class="st-title">Notice List</p>

<form action="<%=ORSView.NOTICE_LIST_CTL%>">
	<table width="100%">
		<tr>
			<td align="center"><label>Subject :</label> <input type="text"
				name="subject"
				value="<%=ServletUtility.getParameter("subject", request)%>">
				&emsp; <label>Detail :</label> <input type="text" name="details"
				value="<%=ServletUtility.getParameter("details", request)%>">&emsp;
				<input type="submit" name="operation" value="<%=BaseCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="0" width="100%">
		<tr>
			<th>Select</th>
			<th>Subject</th>
			<th>Details</th>
			<th>Create Date</th>
			<th>Expire Date</th>
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
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<NoticeModel> it = list.iterator();
			while (it.hasNext()) {
				NoticeModel model = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=model.getId()%>"></td>
			<td><%=model.getSubject()%></td>
			<td><%=model.getDetails()%></td>
			<td><%=model.getCreatedOn()%></td>
			<td><%=model.getExpireDate()%></td>
			<td>
			<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%> <a href="<%=ORSView.NOTICE_CTL%>?id=<%=model.getId()%>"><%=label%></a>
		</tr>
			
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