<%@page import="in.co.sunrays.ocha.model.AttendenceModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<p class="st-title">Attendance List</p>

<form action="<%=ORSView.ATTENDENCE_LIST_CTL%>">

	<table width="100%">
		<tr>
			<td align="center"><label> Student Name :</label> <input
				type="text" name="subject"
				value="<%=ServletUtility.getParameter("subject", request)%>">
				&emsp; <input type="submit" name="operation"
				value="<%=BaseCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
	<br>

	<table border="1" width="100%">
		<tr>
			<th>Select</th>
			<th>Month</th>
			<th>Year</th>
			<th>Student Id</th>
			<th>Student Name</th>
			<th>Branch Name</th>
			<th>Subject - Attendance (1)</th>
			<th>Subject - Attendance (2)</th>
			<th>Subject - Attendance (3)</th>
			<th>Subject - Attendance (4)</th>
			<th>Subject - Attendance (5)</th>
			<th>Subject - Attendance (6)</th>
			<th>Subject - Attendance (7)</th>
			<th>Subject - Attendance (8)</th>
			<th>Subject - Attendance (9)</th>
			<th>Subject - Attendance (10)</th>
			<th>Edit</th>
		</tr>
		<%
			if (HTMLUtility.getErrorMessage(request).length() > 0) {
		%>
		<tr>
			<td colspan="16"><%=HTMLUtility.getErrorMessage(request)%></td>
		</tr>
		<%
			}
		%>
		<%
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<AttendenceModel> it = list.iterator();
			while (it.hasNext()) {
				AttendenceModel model = it.next();
		%>
		<tr>
			<td><input type="checkbox" name="ids" value="<%=model.getId()%>"></td>
			<td><%=model.getMonth()%></td>
			<td><%=model.getYear()%></td>
			<td><%=model.getStudentId()%></td>
			<td><%=model.getStudentName()%></td>
			<td><%=model.getBranchName()%></td>
			<td><%=model.getSubject1() + " - " + model.getAttendence1()%></td>
			<td><%=model.getSubject2() + " - " + model.getAttendence2()%></td>
			<td><%=model.getSubject3() + " - " + model.getAttendence3()%></td>
			<td><%=model.getSubject4() + " - " + model.getAttendence4()%></td>
			<td><%=model.getSubject5() + " - " + model.getAttendence5()%></td>
			<td><%=model.getSubject6() + " - " + model.getAttendence6()%></td>
			<td><%=model.getSubject7() + " - " + model.getAttendence7()%></td>
			<td><%=model.getSubject8() + " - " + model.getAttendence8()%></td>
			<td><%=model.getSubject9() + " - " + model.getAttendence9()%></td>
			<td><%=model.getSubject10() + " - "
						+ model.getAttendence10()%></td>
			<td>
			<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
				%> <a href="<%=ORSView.ATTENDENCE_CTL%>?id=<%=model.getId()%>"><%=label%></a>

			
			</td>
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