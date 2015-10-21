
<%@page import="java.util.ArrayList"%>
<%@page import="in.co.sunrays.ocha.model.CommentModel"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<%@page import="in.co.sunrays.common.controller.BaseCtl"%>
<%@page import="in.co.sunrays.ocha.model.EResourceModel"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<p class="st-title">E-Resource List</p>

<form action="<%=ORSView.ERESOURCE_LINK_CTL%>">

	<table width="80%">
		<tr>
			<td align="left"><label> Link :</label> <input type="text"
				name="name"
				value="<%=ServletUtility.getParameter("name", request)%>">
				&nbsp; <label>Detail :</label> <input type="text" name="detail"
				value="<%=ServletUtility.getParameter("detail", request)%>">&nbsp;
				<input type="submit" name="operation" value="<%=BaseCtl.OP_SEARCH%>"></td>
		</tr>
	</table>
</form>

<br>

<table border="0" width="80%">

	<tr>
		<th align="left">Link Resource</th>
		<th align="left">Comments</th>
	</tr>

	<%
		int i = 1;
		List list = ServletUtility.getList(request);
		Iterator<EResourceModel> it = list.iterator();
		while (it.hasNext()) {
			EResourceModel model = it.next();
	%>
	<tr>
		<td valign="top">[<%=i++%>] <a href="<%=model.getName()%>"><%=model.getName()%></a><br><%=model.getDetail()%></td>
		<td>
			<%
				CommentModel comModel = new CommentModel();
					comModel.setResourceId(model.getId());
					List l = comModel.search();
					if (l == null) {
						l = new ArrayList();
					}
					Iterator<CommentModel> comIt = l.iterator();
					
					for( int j = 1; comIt.hasNext(); j++) {
						CommentModel cbj = comIt.next();
					%>
						<span style="font-size:10px">	<%=j %> : <%= cbj.getText() %> by <%= cbj.getName()%> </span><br>
					<%
					}
			%>
			<form action="<%=ORSView.ERESOURCE_LINK_CTL%>">
				<input type="hidden" name="linkId" value="<%=model.getId()%>">
				<textarea name="text" rows="3" cols="40"></textarea>
				<BR> <input type="submit" name="operation" value="Add Comment">
			</form>
		</td>
	</tr>
	<%
		}
	%>
</table>

<form action="<%=ORSView.ERESOURCE_LINK_CTL%>">

	<table width="80%">
		<tr>
			<td align="left"><input type="submit" name="operation"
				value="<%=BaseCtl.OP_PREVIOUS%>"></td>
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