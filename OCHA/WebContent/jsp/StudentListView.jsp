<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="in.co.sunrays.ocha.model.StudentModel"%>
<%@page import="in.co.sunrays.ocha.controller.StudentListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.StudentCtl"%>
<%@page import="in.co.sunrays.common.controller.UserListCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<p class="st-title">Student List</p>

<form action="<%=ORSView.STUDENT_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label>First Name:</label> <input type="text"
				name="firstName"
				value="<%=ServletUtility.getParameter("firstName", request)%>">
				&nbsp; <label>Last Name :</label> <input type="text" name="lastName"
				value="<%=ServletUtility.getParameter("lastName", request)%>">&nbsp;
				<input type="submit" name="operation"
				value="<%=StudentListCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>
	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Father Name</th>
			<th>Mother Name</th>
			<th>College Id</th>
			<th>Department</th>
			<th>Semester</th>
			<th>Year</th>
			<th>Date Of Birth</th>
			<th>Gender</th>
			<th>mobileNo</th>
			<th>Address</th>
			<th>User Id</th>
			<th>Edit</th>
		</tr>
		<%
			if (HTMLUtility.getErrorMessage(request).length() > 0) {
		%>
		<tr>
			<td colspan="15"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>
		<%
			}
		%>
		<%
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<StudentModel> it = list.iterator();

			while (it.hasNext()) {

				StudentModel bean = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
			<td><%=bean.getFirstName()%></td>
			<td><%=bean.getLastName()%></td>
			<td><%=bean.getFatherName()%></td>
			<td><%=bean.getMotherName()%></td>
			<td><%=bean.getCollegeId()%></td>
			<td><%=bean.getDepartement()%></td>
			<td><%=bean.getSemester()%></td>
			<td><%=bean.getYear()%></td>
			<td><%=bean.getDob()%></td>
			<td><%=bean.getGender()%></td>
			<td><%=bean.getMobileNo()%></td>
			<td><%=bean.getAddress()%></td>
			<td><%=bean.getUserId()%></td>
			<td>
				<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%> <a href="<%=ORSView.STUDENT_CTL%>?id=<%=bean.getId()%>"><%=label%></a>

			</td>
		</tr>
		<%
			}
		%>
	</table>
	<table width="100%">
		<tr>
			<td align="left"><input type="submit" name="operation"
				value="<%=StudentListCtl.OP_PREVIOUS%>"></td>

			<td colspan="3" align="center"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
					
			<td align="right"><input type="submit" name="operation"
				value="<%=StudentListCtl.OP_NEXT%>"></td>
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

