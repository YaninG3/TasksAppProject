<%@page import="java.util.List"%>
<%@page import="java.util.concurrent.CountDownLatch"%>
<%@page import="com.todo.model.User"%>
<%@page import="com.todo.model.HibernateToDoListDAO,com.todo.model.TodoItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();

out.println("<p>current table status:<p>");

//User userb = new User("adni","Yadin","ydning","psds");
TodoItem task = new TodoItem("Buy mouse", "afff", 1);
TodoItem task1 = new TodoItem("Buy mouse", "wee", 2);
TodoItem task2 = new TodoItem("Buy mouse", "dff", 1);
TodoItem task3 = new TodoItem("Buy mouse", "xcv", 2);
TodoItem task4 = new TodoItem("Buy mouse", "r422", 3);

dao.createItem(task);
dao.createItem(task2);
dao.createItem(task3);
dao.createItem(task4);
dao.createItem(task1);

List<TodoItem> items = dao.readItemList();

out.println(printItemsTable(items));

out.println("<p> now only user 1 <p>");

items = dao.getItemListByUserId(1);

out.println(printItemsTable(items));



/* TodoItem task2 = new TodoItem("Throw the trash", "2344", 4555);
out.println("<p> New item (" + newID.toString() + ") will be replaced with " + task2.toString() + "<p>");
dao.replaceItem(task2, newID);


out.println("<p>Table after item update:<p>");
items = dao.readItemList();
out.println(printItemsTable(items));

dao.deleteAllItems();

out.println("<p>items table after Delete All:");
items = dao.readItemList();
out.println(printItemsTable(items));


User user = new User("tri","bot","anti","hiIamtri");
dao.createUser(user);
List<User> userDB = dao.readUserList();
out.println(printUsersTable(userDB)); */
%>

<%!
String printItemsTable(List<TodoItem> items){
	
	String str = "";
	str += "<table border='1'>";
	str += "<tr> <th>id</th> <th>subject</th> <th>date</th> <th>user id</th> </tr>";
	for(int i = 0 ; i < items.size(); i++){
		str += "<tr>";
		str += "<th>" + items.get(i).getId() + "</th>";
		str += "<th>" + items.get(i).getSubject() + "</th>";
		str += "<th>" + items.get(i).getDate() + "</th>";
		str += "<th>" + items.get(i).getUserId() + "</th>";
		str += "</tr>";
	}
	str += "</table>";
	
	return str;
}
%>

<%!
String printUsersTable(List<User> users){
	
	String str = "";
	str += "<table border='1'>";
	str += "<tr> <th>id</th> <th>First</th> <th>Last</th> <th>Username</th> <th>Password</th></tr>";
	for(int i = 0 ; i < users.size(); i++){
		str += "<tr>";
		str += "<th>" + users.get(i).getId() + "</th>";
		str += "<th>" + users.get(i).getFirstName() + "</th>";
		str += "<th>" + users.get(i).getLastName() + "</th>";
		str += "<th>" + users.get(i).getUserName() + "</th>";
		str += "<th>" + users.get(i).getPassword() + "</th>";
		str += "</tr>";
	}
	str += "</table>";
	
	return str;
}
%>

</body>
</html>