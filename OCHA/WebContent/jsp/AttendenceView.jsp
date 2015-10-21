<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>
<jsp:useBean id="model" class="in.co.sunrays.ocha.model.AttendenceModel"
	scope="request" />
	
<p class="st-title">Attendance</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>
<%
	LinkedHashMap<String, String> branchMap = new LinkedHashMap<String, String>();
	branchMap.put("", "--Select--");
	branchMap.put("IT", "IT");
	branchMap.put("CS", "CS");
	branchMap.put("EC", "EC");
	branchMap.put("CE", "CE");
	branchMap.put("ME", "ME");

	LinkedHashMap<String, String> monthMap = new LinkedHashMap<String, String>();
	monthMap.put("", "--Select--");
	monthMap.put("January", "January");
	monthMap.put("February", "February");
	monthMap.put("March", "March");
	monthMap.put("April", "April");
	monthMap.put("May", "May");
	monthMap.put("June", "June");
	monthMap.put("July", "July");
	monthMap.put("August", "August");
	monthMap.put("September", "September");
	monthMap.put("October", "October");
	monthMap.put("November", "November");
	monthMap.put("December", "December");

	LinkedHashMap<String, String> yearMap = new LinkedHashMap<String, String>();
	yearMap.put("", "--Select--");
	yearMap.put("11", "11");
	yearMap.put("12", "12");
	yearMap.put("13", "13");
	yearMap.put("14", "14");
	yearMap.put("15", "15");
	yearMap.put("16", "16");

	LinkedHashMap<String, String> subjectMap = new LinkedHashMap<String, String>();
	subjectMap.put("", "--Select--");
	subjectMap.put("OOT", "OOT");
	subjectMap.put("BME", "BME");
	subjectMap.put("M1", "M1");
	subjectMap.put("ADA", "ADA");
	subjectMap.put("ACA", "ACA");
	subjectMap.put("TOC", "TOC");
	subjectMap.put("DS", "DS");
	subjectMap.put("OS", "OS");
	subjectMap.put("JAVA", "JAVA");
	subjectMap.put("SEPM", "SEPM");

	List userList = (List) request.getAttribute("userList");
%>

<form action="<%=ORSView.ATTENDENCE_CTL%>" method="post">
<input type="hidden" name="id" value="<%=model.getId()%>">
	<table>
		<tr>
			<th>Branch*</th>
			<td><%=HTMLUtility.getList("branch", model.getBranchName(),
					branchMap)%></td>
			<td></td>
		</tr>
		<tr>
			<th>Month*</th>
			<td><%=HTMLUtility.getList("month", model.getMonth() + "",
					monthMap)%></td>
			<td></td>
			<th>Year*</th>
			<td><%=HTMLUtility.getList("year", model.getYear() + "", yearMap)%></td>
		</tr>
		<tr>
			<th>Student Id*</th>
			<td><input type="text" name="studentId"
				value="<%=DataUtility.getStringData(model.getStudentId())%>"></td>
			<td></td>
			<th>Student Name*</th>
			<td><%=HTMLUtility.getList("studentName",
					model.getStudentName(), userList)%></td>
		</tr>
		<tr>
			<th>Subject 1*</th>
			<td><%=HTMLUtility.getList("subject1", model.getSubject1(),
					subjectMap)%></td>
			<td></td>
			<th>Attendance*</th>
			<td><input type="text" name="attendance1"
				value="<%=model.getAttendence1()%>"></td>
		</tr>
		<tr>
			<th>Subject 2*</th>
			<td><%=HTMLUtility.getList("subject2", model.getSubject2(),
					subjectMap)%></td>
			<td></td>
			<th>Attendance*</th>
			<td><input type="text" name="attendance2"
				value="<%=model.getAttendence2()%>"></td>
		</tr>
		<tr>
			<th>Subject 3*</th>
			<td><%=HTMLUtility.getList("subject3", model.getSubject3(),
					subjectMap)%></td>
			<td></td>
			<th>Attendance*</th>
			<td><input type="text" name="attendance3"
				value="<%=model.getAttendence3()%>"></td>
		</tr>
		<tr>
			<th>Subject 4*</th>
			<td><%=HTMLUtility.getList("subject4", model.getSubject4(),
					subjectMap)%></td>
			<td></td>
			<th>Attendance*</th>
			<td><input type="text" name="attendance4"
				value="<%=model.getAttendence4()%>"></td>
		</tr>
		<tr>
			<th>Subject 5*</th>
			<td><%=HTMLUtility.getList("subject5", model.getSubject5(),
					subjectMap)%></td>
			<td></td>
			<th>Attendance*</th>
			<td><input type="text" name="attendance5"
				value="<%=model.getAttendence5()%>"></td>
		</tr>
		<tr>
			<th>Subject 6*</th>
			<td><%=HTMLUtility.getList("subject6", model.getSubject6(),
					subjectMap)%></td>
			<td></td>
			<th>Attendance*</th>
			<td><input type="text" name="attendance6"
				value="<%=model.getAttendence6()%>"></td>
		</tr>
		<tr>
			<th>Subject 7*</th>
			<td><%=HTMLUtility.getList("subject7", model.getSubject7(),
					subjectMap)%></td>
			<td></td>
			<th>Attendance*</th>
			<td><input type="text" name="attendance7"
				value="<%=model.getAttendence7()%>"></td>
		</tr>
		<tr>
			<th>Subject 8*</th>
			<td><%=HTMLUtility.getList("subject8", model.getSubject8(),
					subjectMap)%></td>
			<td></td>
			<th>Attendance*</th>
			<td><input type="text" name="attendance8"
				value="<%=model.getAttendence8()%>"></td>
		</tr>
		<tr>
			<th>Subject 9*</th>
			<td><%=HTMLUtility.getList("subject9", model.getSubject9(),
					subjectMap)%></td>
			<td></td>
			<th>Attendance*</th>
			<td><input type="text" name="attendance9"
				value="<%=model.getAttendence9()%>"></td>
		</tr>
		<tr>
			<th>Subject 10*</th>
			<td><%=HTMLUtility.getList("subject10", model.getSubject10(),
					subjectMap)%></td>
			<td></td>
			<th>Attendance*</th>
			<td><input type="text" name="attendance10"
				value="<%=model.getAttendence10()%>"></td>
		</tr>
		<tr>
			<td align="center" colspan="4"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%></td>
		</tr>
	</table>
</form>