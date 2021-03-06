<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>User page</title>
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>

	<!-- </div> -->
	<div id="wrapper">
		<div class="overlay"></div>

		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
			<ul class="nav sidebar-nav">
				<li class="sidebar-brand"><a href="/admin/admin">
						${admin.username} </a></li>
				<li><a href="/admin/changepassword">Change Password</a></li>
				<li><a href="/admin/adduser">Add user</a></li>
				<li><a href="/admin/categories">Category</a></li>
				<li>
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form> <a onclick="document.forms['logoutForm'].submit()">Logout</a>
				</li>
			</ul>
		</nav>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<button type="button" class="hamburger is-closed"
				data-toggle="offcanvas">
				<span class="hamb-top"></span> <span class="hamb-middle"></span> <span
					class="hamb-bottom"></span>
			</button>
			<div class="container">
				<form method="POST" action="/admin/adduser" modelAttribute="userForm" class="form-signin">
					<div class ="center" style="text-align: center">
						<c:if test="${error != null}">
							<div class="alert alert-danger">
								<strong>${error}</strong>
							</div>
						</c:if>
					</div>
					<h2 class="form-signin-heading">Add user</h2>
					<input type="text" class="form-control" name="username"
						id="username" placeholder="User name" value="${user.username}" />

			        <input type="password" class="form-control" name="password" id="password"
									placeholder="Password" /> 
					<input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm"
						placeholder="Confirm your password" /> 
			        
			       <input type="Email" class="form-control" name="email" id="email"
									placeholder="Email" /> 
					<input type="text"
						class="form-control" name="firstname" id="firstname"
						placeholder="First name" /> 
					<input
						type="text" class="form-control" name="lastname" id="lastname"
						placeholder="Last name" />
					<label class="checkboxcustom"> Admin Role
					  <input type="checkbox" name = "isadmin">
					  <span class="checkmark"></span>
					</label>

					<button class="btn btn-lg btn-primary btn-block" type="submit"
						value="change">Create user</button>
				</form>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/sidebar.js"></script>
</body>
</html>


