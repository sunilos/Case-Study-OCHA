<!DOCTYPE html>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.ocha.model.AppRole"%>
<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
<html>
<head>
<meta charset="UTF-8">
<title>OCHA</title>
<meta name="description" content="A description of your website">
<meta name="keywords" content="keyword1, keyword2, keyword3">

<link href="<%=ORSView.CSS_FOLDER%>/style.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="<%=ORSView.CSS_FOLDER%>/sunrays.css">
<script type='text/javascript' src='<%=ORSView.JS_FOLDER%>/calendar.js'></script>

</head>
<body>
	<%@page import="java.io.Console"%>
	<%@page import="in.co.sunrays.util.MenuBuilder"%>
	<%@page import="in.co.sunrays.common.model.UserModel"%>
	<%@page import="in.co.sunrays.util.PropertyReader"%>
	<%@page import="in.co.sunrays.common.model.RoleModel"%>
	<%@page import="in.co.sunrays.common.controller.LoginCtl"%>
	<%@page import="in.co.sunrays.ocha.controller.ORSView"%>
	<%
		UserModel userModel = ServletUtility.getUserModel(request);

		long roleId = ServletUtility.getRole(request);

		String welcomeMsg = "Hi, Guest ";

		if (ServletUtility.isLoggedIn(request)) {
			RoleModel roleModel = new RoleModel().findByPK(roleId);
			welcomeMsg = "Hi, " + userModel.getFirstName() + " ("
					+ roleModel.getName() + ")";
		}
	%>

	<div id="wrapper">

		<div id="header">

			<div class="top_banner">
				<h1>Online College Helpdesk Application</h1>
				<p>Your College Expert</p>
			</div>

		</div>

		<div id="page_content">

			<div class="navigation">
				<ul>
					<li><a href="<%=ORSView.LOGIN_CTL%>?op=Home">Home</a></li>
			
					<li><a href="http://www.sunrays.co.in/about">About Us</a></li>
					<li><a href="http://www.sunrays.co.in/contact-us">ContactUs</a></li>
					<%
						if (ServletUtility.isLoggedIn(request)) {
					%>
					<li><a href="<%=ORSView.LOGIN_CTL%>">Logout</a></li>
					<%
						} else {
					%>
					<li><a href="<%=ORSView.USER_REGISTRATION_CTL%>?roleId=2">Staff
							Sign UP</a></li>
					<li><a href="<%=ORSView.USER_REGISTRATION_CTL%>?roleId=1">Student
							Sign UP</a></li>
					<%
						}
					%>
					
				</ul>
				
			</div>

			<div class="left_side_bar">

				<div class="col_1">
					<h1>Main Menu</h1>
					<H3><%=welcomeMsg%></H3>
					<div class="box">
						<%=MenuBuilder.getMenu(roleId, MenuBuilder.VERTICAL)%>
					</div>
				</div>

				<div class="col_1">
					<h1>Block</h1>
					<div class="box">
						<p>Enter Block content here...</p>
						<br>
						<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
							Aenean commodo Lorem ipsum dolor sit amet, consectetuer
							adipiscing elit. Aenean commodo</p>
					</div>
				</div>

			</div>

			<div class="right_section">
				<div class="common_content">

					<%
						String bodyPage = null;

						bodyPage = (String) request.getAttribute("bodyPage");
					%>

					<jsp:include page="<%=bodyPage%>"></jsp:include>
				</div>

			</div>

			<div class="clear"></div>

			<!--start footer from here-->
			<div id="footer">
				Copyright &copy; 2014. Design by <a href="http://www.casino.biz"
					target="_blank">casino.biz</a><br>

				<!--DO NOT remove footer link-->
				<!--Template designed by-->
				<a href="http://www.casino.biz"><img
					src="<%=ORSView.APP_CONTEXT%>/images/footer.gif" class="copyright"
					alt="http://www.casino.biz"></a>
			</div>

			<!--/. end footer from here-->
		</div>

	</div>
</body>
</html>