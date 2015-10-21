<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<jsp:useBean id="model" class="in.co.sunrays.ocha.model.TimeTableModel"
	scope="request"></jsp:useBean>
<%
	LinkedHashMap<String, String> facMap = new LinkedHashMap<String, String>();
	facMap.put("", "--Select--");
	facMap.put("Ram Sharma", "Ram Sharma");
	facMap.put("Yash Sharama", "Yash Sharama");
	facMap.put("Manoj Sharama", " Manoj Sharma");
	facMap.put("Ramesh Sharama", "Yash Sharama");
	facMap.put("Suresh Sharama", "Yash Sharama");
	facMap.put("Mahendra Sharama", "Mahendra Sharama");
	facMap.put("Rajesh Sharama", "Rajesh Sharama");
	facMap.put("Mahesh Sharama", "Mahesh Sharama");

	LinkedHashMap<String, String> semesterMap = new LinkedHashMap<String, String>();
	semesterMap.put("", "--Select--");
	semesterMap.put("1", "1");
	semesterMap.put("2", "2");
	semesterMap.put("3", "3");
	semesterMap.put("4", "4");
	semesterMap.put("5", "5");
	semesterMap.put("6", "6");
	semesterMap.put("7", "7");
	semesterMap.put("8", "8");

	LinkedHashMap<String, String> branchMap = new LinkedHashMap<String, String>();
	branchMap.put("", "--Select--");
	branchMap.put("IT", "IT");
	branchMap.put("CS", "CS");
	branchMap.put("EC", "EC");
	branchMap.put("CE", "CE");
	branchMap.put("ME", "ME");

	LinkedHashMap<String, String> yearMap = new LinkedHashMap<String, String>();
	yearMap.put("", "--Select--");
	yearMap.put("1", "1");
	yearMap.put("2", "2");
	yearMap.put("3", "3");
	yearMap.put("4", "4");

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
%>

<p class="st-title">Add Timetable</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>


<form action="<%=ORSView.TIMETABLE_CTL%>" method="POST">
	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>
		<tr>
			<th>Branch*</th>
			<td><%=HTMLUtility.getList("branch", model.getBranch(),
					branchMap)%></td>
			<td></td>
		</tr>
		<tr>
			<th>Semester*</th>
			<td><%=HTMLUtility.getList("semester", model.getSemester() + "",
					semesterMap)%></td>
			</td>
			<td></td>
			<th>Year*</th>
			<td><%=HTMLUtility.getList("year", model.getYear() + "", yearMap)%></td>
			</td>
		</tr>
		<tr>
			<th>Date(mm/dd/yyyy)*</th>
			<td><input type="text" name="date1" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDate())%>"> <a
				href="javascript:getCalendar(document.forms[0].date1);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15"
					border="0" alt="Calender">
			</a></td>
			<td></td>
			<th>Time*</th>
			<td><input type="text" name="time"
				value="<%=DataUtility.getStringData(model.getTime())%>"></td>
			<th>Subject*</th>
			<td><%=HTMLUtility.getList("subject", model.getSubject(),
					subjectMap)%></td>
			<td></td>
			<th>Faculty*</th>
			<td><%=HTMLUtility.getList("faculty", model.getFaculty(), facMap)%></td>
		</tr>
		<tr>
			<th>Date(mm/dd/yyyy)*</th>
			<td><input type="text" name="date2" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDate())%>"> <a
				href="javascript:getCalendar(document.forms[0].date2);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15"
					border="0" alt="Calender">
			</a></td>
			<td></td>
			<th>Time*</th>
			<td><input type="text" name="time"
				value="<%=DataUtility.getStringData(model.getTime())%>"></td>
			<th>Subject*</th>
			<td><%=HTMLUtility.getList("subject", model.getSubject(),
					subjectMap)%></td>
			<td></td>
			<th>Faculty*</th>
			<td><%=HTMLUtility.getList("faculty", model.getFaculty(), facMap)%></td>
		</tr>
		<tr>
			<th>Date(mm/dd/yyyy)*</th>
			<td><input type="text" name="date3" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDate())%>"> <a
				href="javascript:getCalendar(document.forms[0].date3);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15"
					border="0" alt="Calender">
			</a></td>
			<td></td>
			<th>Time*</th>
			<td><input type="text" name="time"
				value="<%=DataUtility.getStringData(model.getTime())%>"></td>
			<th>Subject*</th>
			<td><%=HTMLUtility.getList("subject", model.getSubject(),
					subjectMap)%></td>
			<td></td>
			<th>Faculty*</th>
			<td><%=HTMLUtility.getList("faculty", model.getFaculty(), facMap)%></td>
		</tr>
		<tr>
			<th>Date(mm/dd/yyyy)*</th>
			<td><input type="text" name="date4" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDate())%>"> <a
				href="javascript:getCalendar(document.forms[0].date4);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15"
					border="0" alt="Calender">
			</a></td>
			<td></td>
			<th>Time*</th>
			<td><input type="text" name="time"
				value="<%=DataUtility.getStringData(model.getTime())%>"></td>
			<th>Subject*</th>
			<td><%=HTMLUtility.getList("subject", model.getSubject(),
					subjectMap)%></td>
			<td></td>
			<th>Faculty*</th>
			<td><%=HTMLUtility.getList("faculty", model.getFaculty(), facMap)%></td>
		</tr>
		<tr>
			<th>Date(mm/dd/yyyy)*</th>
			<td><input type="text" name="date5" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDate())%>"> <a
				href="javascript:getCalendar(document.forms[0].date5);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15"
					border="0" alt="Calender">
			</a></td>
			<td></td>
			<th>Time*</th>
			<td><input type="text" name="time"
				value="<%=DataUtility.getStringData(model.getTime())%>"></td>
			<th>Subject*</th>
			<td><%=HTMLUtility.getList("subject", model.getSubject(),
					subjectMap)%></td>
			<td></td>
			<th>Faculty*</th>
			<td><%=HTMLUtility.getList("faculty", model.getFaculty(), facMap)%></td>
		</tr>
		<tr>
			<th>Date(mm/dd/yyyy)*</th>
			<td><input type="text" name="date6" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDate())%>"> <a
				href="javascript:getCalendar(document.forms[0].date6);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15"
					border="0" alt="Calender">
			</a></td>
			<td></td>
			<th>Time*</th>
			<td><input type="text" name="time"
				value="<%=DataUtility.getStringData(model.getTime())%>"></td>
			<th>Subject*</th>
			<td><%=HTMLUtility.getList("subject", model.getSubject(),
					subjectMap)%></td>
			<td></td>
			<th>Faculty*</th>
			<td><%=HTMLUtility.getList("faculty", model.getFaculty(), facMap)%></td>
		</tr>
		<tr>
			<td align="center" colspan="6"><input type="submit"
				name="operation" value="<%=BaseCtl.OP_SAVE%>"></td>
		</tr>
	</table>
</form>

