<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="windows-1255"%>
<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/.11.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="../../assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="starter-template.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<!-- load the message  that's attached to this request -->
<jsp:useBean class="java.lang.String" id="msg" scope="request" />
<jsp:useBean class="java.lang.String" id="errorMsg" scope="request" />
<body>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/TasksProjectB/controller/loginPage">To Do List</a>
		</div>
	</div>
	<h3>
		<span class="label label-primary"><%=msg %></span>
		<span class="label label-danger"><%=errorMsg %></span>
	</h3>
</nav>
	<div class="container">
	<h2 class="form-signin-heading">Registration Form</h2>
		<form action="/TasksProjectB/controller/registrationAction" method="post">
			<label >First Name:</label> 
			<input type="text" class="form-control" name="firstname" placeholder="Enter first name"> 
			<label for="usr">Last Name:</label> 
			<input type="text" class="form-control" name="lastname" placeholder="Enter last name">
			<label for="usr">User Name:</label> 
			<input type="text" class="form-control" name="username" placeholder="Enter user name"> 
			<label for="usr">Password:</label>
			<input type="password" class="form-control" name="password" placeholder="Enter password">
			<BR>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
		</form>
		<br>
	</div>
</body>
</html>