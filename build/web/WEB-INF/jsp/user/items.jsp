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
<title>Items page</title>
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
				<li class="sidebar-brand"><a href="/user/user">
						${user.username} </a></li>
				<li><a href="/user/changepassword">Change Password</a></li>
				<li><a href="/user/items">Inventory</a></li>
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
				<h1 style="text-align: center;">List All Inventories</h1>
				<button type="button" class="btn btn-info btn-lg btnAdd"
					data-toggle="modal" data-target="#AddItem">Add Item</button>
				<table id="tablecategory"
					class="table table-striped table-bordered table-sm" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th class="th-sm">Item Id <i class="fa fa-sort float-right"
								aria-hidden="true"></i>
							</th>
							<th class="th-sm">Category <i class="fa fa-sort float-right"
								aria-hidden="true"></i>
							</th>
							<th class="th-sm">Item Name <i
								class="fa fa-sort float-right" aria-hidden="true"></i>
							</th>
							<th class="th-sm">Price <i class="fa fa-sort float-right"
								aria-hidden="true"></i>
							</th>
							<th class="th-sm">Action <i class="fa fa-sort float-right"
								aria-hidden="true"></i>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty listItems}">
							<c:forEach items="${listItems}" var="item">
								<tr>
									<td>${item.itemid}</td>
									<td>${item.category.categoryname}</td>
									<td>${item.itemname}</td>
									<td>${item.price}</td>
									<td>
										<button class="btn btn-success" data-toggle="modal"
											data-target="#myItemModal">Edit</button>
											<form name="modalFormAddItem" action="/user/deleteitem"
											method="POST">
											<input type ="hidden" id ="itemid" name ="itemid" value ="${item.itemid}"/>
											<button class="btn btn-danger" type="submit"
														value="delete" name="delete">Delete</button>
											</form>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<div class="modal fade" id="myItemModal" tabindex="-1"
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
									<h4 class="modal-title" id="myModalLabel">Update Item</h4>
								</div>
								<div class="modal-body">
									<form name="modalFormAddItem" action="/user/updateitem"
									method="POST">
									<input type = "hidden" class="form-control inputtext" name="itemid"
										id="itemidedit" />
									<select class="form-control" id="sel1" name = "categoryid">
										<c:if test="${not empty listCategory}">
										<c:forEach items="${listCategory}" var="category">
											<option value ="${category.categoryid}">${category.categoryname}</option>
										</c:forEach>
										</c:if>
									</select> 
										<input type="text" class="form-control inputtext" name="itemname"
										id="itemnameedit" placeholder="Item name" required /> 
										<input
										type="number" step="0.01" class="form-control inputtext" name="price" id="priceedit"
										placeholder="price" required />
									
									<div class="modal-footer item">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary" name="edit">Save
											changes</button>
									</div>
								</form>
								</div>
							</div>
						</div>
					</div>
				<div class="modal fade" id="AddItem" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add item</h4>
							</div>
							<div class="modal-body">
								<form name="modalFormAddItem" action="/user/additem"
									method="POST">
									<select class="form-control" id="sel1" name = "categoryid">
										<c:if test="${not empty listCategory}">
										<c:forEach items="${listCategory}" var="category">
											<option value ="${category.categoryid}">${category.categoryname}</option>
										</c:forEach>
										</c:if>
									</select> 
										<input type="text" class="form-control inputtext" name="itemname"
										id="itemname" placeholder="Item name" required /> 
										<input
										type="number" step="0.01" class="form-control inputtext" name="price" id="price"
										placeholder="price" required />
									
									<div class="modal-footer additem">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary" name="Add">Add</button>
									</div>
								</form>
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


