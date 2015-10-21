<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.ocha.model.CollegeModel"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.StudentCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="java.util.LinkedHashMap"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.StudentModel"
	scope="request" />

<jsp:useBean id="collegeList" class="java.util.ArrayList"
	scope="request" />

<jsp:useBean id="semesterMap" class="java.util.LinkedHashMap"
	scope="request" />

<jsp:useBean id="yearMap" class="java.util.LinkedHashMap"
	scope="request" />

<jsp:useBean id="departementMap" class="java.util.LinkedHashMap"
	scope="request" />

<p class="st-title">Add Student</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.STUDENT_CTL%>" method="POST">
	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>
		<tr>
			<th>First Name*</th>
			<td><input type="text" name="firstName"
				value="<%=DataUtility.getStringData(model.getFirstName())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
		</tr>
		<tr>
			<th>Last Name*</th>
			<td><input type="text" name="lastName"
				value="<%=DataUtility.getStringData(model.getLastName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
		</tr>
		<tr>
			<th>Father Name*</th>
			<td><input type="text" name="fatherName"
				value="<%=DataUtility.getStringData(model.getFatherName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("fatherName", request)%></font></td>
		</tr>
		<tr>
			<th>Mother Name*</th>
			<td><input type="text" name="motherName"
				value="<%=DataUtility.getStringData(model.getMotherName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("motherName", request)%></font></td>
		</tr>
		<tr>
			<th>College *</th>

			<td><%=HTMLUtility.getList("collegeId", model.getCollegeId(),
					collegeList)%></td>

		</tr>
		<tr>
			<th>Department *</th>
			<td><%=HTMLUtility.getList("departement",
					model.getDepartement(), departementMap)%></td>
		</tr>
		<tr>
			<th>Semester*</th>
			<td><%=HTMLUtility.getList("semester", model.getSemester() + "",
					semesterMap)%></td>
		</tr>
		<tr>
			<th>Year*</th>
			<td><%=HTMLUtility.getList("year", model.getYear() + "", yearMap)%></td>
			</td>
		</tr>
		<tr>
			<th>Date Of Birth(mm/dd/yyyy)*</th>
			<td><input type="text" name="dob" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getDob())%>"> <a
				href="javascript:getCalendar(document.forms[0].dob);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
		</tr>


		<tr>
			<th>Gender *</th>
			<td>
				<%
					HashMap map = new HashMap();
					map.put("M", "Male");
					map.put("F", "Female");

					String htmlList = HTMLUtility.getList("gender", model.getGender(),
							map);
					
				%> <%=htmlList%>
		</tr>
		<tr>
			<th>Mobile No*</th>
			<td><input type="text" name="mobileNo"
				value="<%=DataUtility.getStringData(model.getMobileNo())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
		</tr>

		<tr>
			<th>Address*</th>
			<td><textarea name="address" cols="22"><%=DataUtility.getStringData(model.getAddress())%></textarea>
		</tr>

		<tr>
			<th></th>
			<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%>
			</td>
		</tr>
	</table>
</form>

