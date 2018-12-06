<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
    	<div class ="center" style="text-align: center">
				<c:if test="${success != null}">
					<div class="alert alert-success">
						<strong>${success}</strong>
					</div>
					<h4 class="form-signin-heading"><a href="/login">Login</a></h4>
				</c:if>
				<c:if test="${error != null}">
					<div class="alert alert-danger">
						<strong>${error}</strong>
					</div>
				</c:if>
			</div>
        <h2 class="form-signin-heading">Create your account</h2>
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

        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
