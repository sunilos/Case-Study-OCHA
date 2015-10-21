<%@page import="in.co.sunrays.common.model.UserModel"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.UserListCtl"%>
<%@page import="in.co.sunrays.common.controller.UserCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<p class="st-title">User List</p>

<form action="<%=ORSView.USER_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label>First Name:</label> <input type="text"
				name="firstName"
				value="<%=ServletUtility.getParameter("firstName", request)%>">&emsp;
				<td align="center"><label>Login Id:</label> <input type="text"
				name="login"
				value="<%=ServletUtility.getParameter("login", request)%>">&emsp;
				<input type="submit" name="operation"
				value="<%=UserListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table  width="100%">
		<tr>
			<th>Select</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>LoginId</th>
			<th>Gender</th>
			<th>DOB</th>
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
			Iterator<UserModel> it = list.iterator();

			while (it.hasNext()) {

				UserModel model = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=model.getId()%>"></td>
			<td><%=model.getFirstName()%></td>
			<td><%=model.getLastName()%></td>
			<td><%=model.getLogin()%></td>
			<td><%=model.getGender()%></td>
			<td><%=model.getDob()%></td>
			<td>
			<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%> <a href="<%=ORSView.USER_CTL%>?id=<%=model.getId()%>"><%=label%></a>
		</tr>
		<%
			}
		%>
	</table>
	<table width="100%">
		<tr>
			<td><input type="submit" name="operation"
				value="<%=UserListCtl.OP_PREVIOUS%>"></td>
				
			<td colspan="3" align="center"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
				
			<td align="right"><input type="submit" name="operation"
				value="<%=UserCtl.OP_NEXT%>"></td>
		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>

