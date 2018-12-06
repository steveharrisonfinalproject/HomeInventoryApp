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
<title>Admin page</title>
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${contextPath}/resources/css/bootstrap/dataTables.bootstrap.min.css"
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
				<h1 style="text-align: center;">List All Users</h1>
				<div id="users">
					<table id="tableuser"
						class="table table-striped table-bordered table-sm"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th class="th-sm">User name <i
									class="fa fa-sort float-right" aria-hidden="true"></i>
								</th>
								<th class="th-sm">Email <i class="fa fa-sort float-right"
									aria-hidden="true"></i>
								</th>
								<th class="th-sm">First name <i
									class="fa fa-sort float-right" aria-hidden="true"></i>
								</th>
								<th class="th-sm">Last name <i
									class="fa fa-sort float-right" aria-hidden="true"></i>
								</th>
								<th class="th-sm">Status <i class="fa fa-sort float-right"
									aria-hidden="true"></i>
								</th>
								<th class="th-sm">Role <i class="fa fa-sort float-right"
									aria-hidden="true"></i>
								</th>
								<th class="th-sm">Action <i class="fa fa-sort float-right"
									aria-hidden="true"></i>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty listUsers}">
								<c:forEach items="${listUsers}" var="user">

									<tr>
										<td>${user.username}</td>
										<td>${user.email}</td>
										<td>${user.firstname}</td>
										<td>${user.lastname}</td>
										<c:if test="${user.active == 1}">
											<td>Active</td>
										</c:if>
										<c:if test="${user.active == 0}">
											<td>Deactive</td>
										</c:if>
										<c:if test="${user.isadmin == 1}">
											<td>Admin</td>
										</c:if>
										<c:if test="${user.isadmin == 0}">
											<td>User</td>
										</c:if>
										<td>
											<form id="formuser" name="formuser"
												action="/admin/updateuser" method="POST">
												<input type="hidden" name="username" id="username"
													value="${user.username}">
												<c:if test="${user.active == 1}">
													<button class="btn btn-warning" type="submit"
														value="deactive" name="deactive">Deactive</button>
												</c:if>
												<c:if test="${user.active == 0}">
													<button class="btn btn-primary" type="submit"
														value="active" name="active">Active</button>
												</c:if>
												<c:if test="${user.isadmin == 1}">
													<button class="btn btn-warning" type="submit"
														value="demote" name="demote">Demote</button>
												</c:if>
												<c:if test="${user.isadmin == 0}">
													<button class="btn btn-primary" type="submit"
														value="promote" name="promote">Promote</button>
												</c:if>
												<button class="btn btn-danger" type="submit"
														value="delete" name="delete">Delete</button>
											</form>
											<button class="btn btn-success" data-toggle="modal" data-target="#myUserModal">Edit</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					<div class="modal fade" id="myUserModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content"></div>
						</div>
						<div class="modal-dialog">
							<div class="modal-content"></div>
						</div>
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true" class="">× </span><span
											class="sr-only">Close</span>

									</button>
									<h4 class="modal-title" id="myModalLabel">Update User</h4>

								</div>
								<div class="modal-body"></div>
								<div class="modal-footer user">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary" name ="edit">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>
				</div>
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
	<script src="${contextPath}/resources/js/table.js"></script>
	<script src="${contextPath}/resources/js/bootstable.js"></script>
	<script
		src="${contextPath}/resources/js/bootstrap/jquery.dataTables.min.js"></script>
	<script
		src="${contextPath}/resources/js/bootstrap/dataTables.bootstrap.min.js"></script>
</body>
</html>
