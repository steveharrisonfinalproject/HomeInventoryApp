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
<title>Category page</title>
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
				<h1 style="text-align: center;">List All Categories</h1>
				<button type="button" class="btn btn-info btn-lg btnAdd"
						data-toggle="modal" data-target="#AddModal">Add Category</button>
				<div id="categories">
					<table id="tablecategory"
						class="table table-striped table-bordered table-sm"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th class="th-sm">Id <i class="fa fa-sort float-right"
									aria-hidden="true"></i>
								</th>
								<th class="th-sm">Category name <i
									class="fa fa-sort float-right" aria-hidden="true"></i>
								</th>
								<th class="th-sm">Action <i class="fa fa-sort float-right"
									aria-hidden="true"></i>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty lsCategory}">
								<c:forEach items="${lsCategory}" var="category">
									<tr>
										<td>${category.categoryid}</td>
										<td>${category.categoryname}</td>
										<td>
											<button class="btn btn-success" data-toggle="modal"
												data-target="#editCategoryModal">Edit</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					
					<div class="modal fade" id="editCategoryModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
									<h4 class="modal-title" id="myModalLabel">Update Category</h4>
								</div>
								<div class="modal-body">
									<form name="modalFormCategory" action="/admin/updatecategory"
									method="POST">
									<input type = "hidden" class="form-control inputtext" name="categoryidedit"
										id="categoryidedit" />
									<input type="text" class="form-control inputtext" name="categorynameedit"
										id="categorynameedit" placeholder="Category name" required /> 
									</form>
								</div>
								<div class="modal-footer category">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary" name="edit">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="AddModal" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Add category</h4>
								</div>
								<div class="modal-body">
								<form name="modalFormAddCategory" action="/admin/addcategory" method="POST">
									<input type="text" class="form-control" name="categoryname"
										id="categoryname" placeholder="Category name" required/>
								</form>
								</div>
								<div class="modal-footer add">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary" name="Add">Add</button>
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
