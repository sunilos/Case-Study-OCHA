<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.ocha.controller.NoticeCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.NoticeModel"
	scope="request"></jsp:useBean>
	
<p class="st-title">Add Notice</p>
 
<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.NOTICE_CTL%>" method="post">
<input type="hidden" name="id" value="<%=model.getId()%>">
	
	<table>
		<tr>
			<th>Subject*</th>
			<td><input type="text" name="subject"
				value="<%=DataUtility.getStringData(model.getSubject())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("subject", request)%></font></td>
		</tr>
		<tr>
			<th>Details*</th>
			<td><textarea name="details" cols="22"><%=DataUtility.getStringData(model.getDetails())%></textarea>
			</td>
		</tr>
		<tr>
			<th>Create On*</th>
			<td><input type="text" name="createdOn" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getCreatedOn())%>"> <a
				href="javascript:getCalendar(document.forms[0].createdOn);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("createdOn", request)%></font></td>
			<td></td>
			<th>Expire On*</th>
			<td><input type="text" name="expireDate" readonly="readonly"
				value="<%=DataUtility.getDateString(model.getExpireDate())%>"> <a
				href="javascript:getCalendar(document.forms[0].expireDate);"> <img
					src="<%=ORSView.IMG_FOLDER%>/cal.jpg" width="16" height="15" border="0"
					alt="Calender">
			</a><font color="red"> <%=ServletUtility.getErrorMessage("expireDate", request)%></font></td>
		</tr>
		
		<tr>
			<th></th>
			<td colspan="2"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%> &nbsp; 
					<input
				type="submit" name="operation" value="<%=BaseCtl.OP_DELETE%>">
				&nbsp;</td>
		</tr>																	
	</table>
</form>
