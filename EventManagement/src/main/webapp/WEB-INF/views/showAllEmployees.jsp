<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Management System - Employee List</title>
<style>
body {
	margin: 0;
	padding: 0;
}


.container {
	position: relative;
	left: 2%;
	width: 95%;
	display: inline-block;
	margin-top: 5%;
	text-align: left;	
}

.hide {
	display: none;
}
.error{
	color:red
}

footer{
margin-top: 10%;
text-align: center;	
}
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}


</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body class="body">
	<h2 align="center">Employees</h2>
	<div id="error" class="error hide"></div>
	<div class="container">
		<table>
		    <tr>
		    	<th>ID </th>
			    <th>Name </th>
			    <th>Join Date</th>
			    <th>Email_Id</th>
			</tr>
			<c:forEach items="${employees}" var="employee">
				<tr>
					<td>${employee.MId}</td>
					<td>${employee.name}</td>
					<td>${employee.joinDate}</td>
					<td>${employee.emailId}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	
</body>
<footer><a href="/EventManagement">Return to Home Page</a></footer>
</html>