<%@page import="com.todo.model.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="windows-1255"%>
<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>

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
<!-- load the user object that's attached to this session -->
<jsp:useBean type="com.todo.model.User" id="user" scope="session" />
<!-- load the message  that's attached to this request -->
<jsp:useBean class="java.lang.String" id="msg" scope="request" />
<jsp:useBean class="java.lang.String" id="errorMsg" scope="request" />

<body>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/TasksProjectB/controller/homepage">To Do List</a>
		</div>
	</div>
	<h3>
		<span class="label label-primary"><%=msg %></span>
		<span class="label label-danger"><%=errorMsg %></span>
	</h3>
</nav>

<div class="col-md-6">
	<%@ taglib uri="WEB-INF/tagstld.tld" prefix="customtag" %>
	<customtag:printTodoItem userid="<%=user.getId() %>" />

	<ul class="nav nav-pills" role="tablist">
		<li role="presentation" class="active">
			<a href="/TasksProjectB/controller/homepage">My List</a>
		</li>
		<li role="presentation">
			<a href="/TasksProjectB/controller/addItemPage">New Task</a>
		</li>
		<li role="presentation">
			<a href="/TasksProjectB/controller/editUserPage">Edit User Information</a>
		</li>
		<li role="presentation">
			<a href="/TasksProjectB/controller/logoff">Sign Off</a>
		</li>
	</ul>
</div>

</body>
</html>
