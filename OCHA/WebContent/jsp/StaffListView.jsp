<%@page import="in.co.sunrays.ocha.controller.StaffCtl"%>
<%@page import="in.co.sunrays.ocha.controller.StaffListCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.ocha.model.StaffModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.StaffModel"
	scope="request"/>

<p class="st-title">Staff List</p>

	<form action="<%=ORSView.STAFF_LIST_CTL%>">

		<table width="100%">
			<tr>
				<td align="center"><label>First Name:</label> <input
					type="text" name="firstName"
					value="<%=ServletUtility.getParameter("firstName", request)%>">&emsp;
					<label>Last Name:</label> <input type="text" name="lastName"
					value="<%=ServletUtility.getParameter("lastName", request)%>">&emsp;
					<input type="submit" name="operation"
					value="<%=StaffListCtl.OP_SEARCH%>"></td>
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
			<tr>
				<td colspan="15"><%=HTMLUtility.getErrorMessage(request)%></td>
			</tr>
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<StaffModel> it = list.iterator();

				while (it.hasNext()) {

					StaffModel bean = it.next();
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
				%> <a href="<%=ORSView.STAFF_CTL%>?id=<%=bean.getId()%>"><%=label%></a>

			</tr>

			<%
				}
			%>
		</table>
		<table width="100%">
			<tr>
				<td><input type="submit" name="operation"
					value="<%=StaffListCtl.OP_PREVIOUS%>"></td>
					
				<td><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>
			
					
				<td align="right"><input type="submit" name="operation"
					value="<%=StaffCtl.OP_NEXT%>"></td>
			</tr>
		</table>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
</center>
