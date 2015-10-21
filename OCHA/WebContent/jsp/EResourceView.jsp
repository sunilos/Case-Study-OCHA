<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.util.AccessUtility"%>

<jsp:useBean id="model" class="in.co.sunrays.ocha.model.EResourceModel"
	scope="request"></jsp:useBean>

<p class="st-title">E-Resource</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.ERESOURCE_CTL%>" method="Post">

	<input type="hidden" name="id" value="<%=model.getId()%>">

	<table>
	<tr>
			<th>Table Contain</th>
			<td><input type="text" name="tablesContains"
				value="<%=DataUtility.getStringData(model.getTablesContains())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("tablesContains", request)%></font></td>
		</tr>

		<tr>
			<th>Link</th>
			<td><input type="text" name="name"
				value="<%=DataUtility.getStringData(model.getName())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
		</tr>

		<tr>
			<th valign="top">Detail</th>
			<td><textarea rows="3" cols="40" name="detail"><%=DataUtility.getStringData(model.getDetail())%></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit"
				name="operation" value="<%=BaseCtl.OP_SAVE%>"> &nbsp; 
				
				<%
				if (model.getId() > 0) {
					 %> <input type="submit" name="operation" value="<%=BaseCtl.OP_DELETE%>">
									&nbsp; <%
					 	}
					 %> <input type="submit" name="operation" value="<%=BaseCtl.OP_CANCEL%>">
								</td>
							</tr>
						</table>
					</form>


