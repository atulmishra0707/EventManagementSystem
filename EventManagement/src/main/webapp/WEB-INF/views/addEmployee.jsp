<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Management System - Add Employee</title>
<style>
body {
	margin: 0;
	padding: 0;
	text-align: center;
}

.container {
	position: relative;
	left: 42%;
	width: 100%;
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
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body class="body">
	<h2>Addition of New Event</h2>
	<form:form name="employee" id="employee" method="POST" action="addEmployee" commandName="employee">
	<div id="error" class="error hide"></div>
	<div class="container">
		<table>
			<tr>
			<td>Employee ID</td>
			<td><form:input path="MId"  id="MId" name="MId" type="number"/></td>
			</tr> 
			
			<tr>
			<td>Name</td>
			<td><form:input path="name"  id="name" name="name"/></td>
			</tr>
			 <tr> 
			<%--><td>Join Date</td>
			<td><input type="date" id="joinDate" name="joinDate"/>
			<form:input type="hidden" id="hjoinDate" path="joinDate"/></td>
			</tr>	 --%>
			
			<tr>
				<td>Joining Date</td>
				<td><form:input path="date" type="date" id="joindate" name="joindate"/></td>
			</tr>
					
			<tr>
			<td>Email ID</td>
			<td><form:input path="emailId"  id="emailId" name="emailId"/></td>
		</tr> 
			<tr>
				<td><input id="submit" type="submit" value="Submit" /><input id="id" type="hidden" value="0" /></td>
				<td><a href="/EventManagement"><input id="cancel" type="button"
						value="Cancel" /></a></td>

			</tr>
		</table>
		</div>
	</form:form>
</body>
<footer><a href="/EventManagement">Return to Home Page</a></footer>
</html>