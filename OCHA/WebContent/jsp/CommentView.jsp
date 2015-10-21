<%@page import="in.co.sunrays.util.AccessUtility"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./js/calendar.js"></script>

<title>Comment Management</title>
</head>
<body>
	<form action="<%=ORSView.COMMENT_CTL%>">


		<jsp:useBean id="model" class="in.co.sunrays.ocha.model.CommentModel"
			scope="request" />

		<p class="st-title">Comment</p>

		<%=HTMLUtility.getSuccessMessage(request)%>
		<%=HTMLUtility.getErrorMessage(request)%>

		<input type="hidden" name="id" value="<%=model.getId()%>"> <input
			type="hidden" name="createdDatetime"
			value="<%=DataUtility.getTimestamp(model.getCreatedOn())%>">
		<input type="hidden" name="resourceId"
			value="<%=model.getResourceId()%>">


		<table>

			<%-- 	 <tr>
					<th>Resource Name*</th>
					<td><%=HTMLUtility.getList("resourceId",
					String.valueOf(bean.getResourceId()), l) %></td>
				</tr>  --%>
			<tr>
				<th>Resource Name</th>
				<td><input type="text" name="name" readonly="readonly"
					value="<%=DataUtility.getStringData(model.getName())%>"></td>
			</tr>

			<tr>
				<th>Text*</th>
				<td><input type="text" name="text"
					value="<%=DataUtility.getStringData(model.getText())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("text", request)%></font></td>
			</tr>
			<tr>
				<th></th>
				<td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; <%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%>
					<%
						if (model.getId() > 0) {
					%> &emsp;<input type="submit" name="operation"
						value="<%=BaseCtl.OP_DELETE%>"> 
					<%
						}
					%>&emsp; <input type="submit" name="operation"
					value="<%=BaseCtl.OP_CANCEL%>">
				</td>
			</tr>
		</table>
	</form>