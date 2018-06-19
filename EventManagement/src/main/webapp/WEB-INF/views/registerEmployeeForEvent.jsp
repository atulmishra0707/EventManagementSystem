<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Management System - Employee Event Registration</title>
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
<script>

</script>
</head>
<body class="body">
	<h2>Employee - Event Registration</h2>
	<h2>${msg}</h2>
	<form:form name="EventRegister" id="registerEmployeeForEvent" method="POST" action="registerEmployeeForEvent" commandName="registerEmployeeForEvent">
	<div id="error" class="error hide"></div>
	<div class="container">
		<table>
			<tr>
				<td>Employee :</td>				
				<td>
					<div>
					<select path="MId" name="MId" class="form-control" id="empList_op1">
		                <option selected="selected" value="0">Select</option>
		                <c:if test="${!empty empList}">
		                <c:forEach items="${empList}" var="employee">
		                	<option value="${employee.MId}">${employee.name} (${employee.MId})</option>
						</c:forEach>
						</c:if>	                 
	                </select>
	            	</div>					
				</td>			
			</tr>
		</table>
	</div>
	<div class="container">
		<table>
			<tr>
				<td>List of Events :</td></tr>
			<tr>		
				<td> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${!empty eventList}">
							<select class="form-control" path ="events" name="events" id="eventList_op2" multiple="true">
				                <c:forEach items="${eventList}" var="event">
				                	<option value="${event.eventId}">${event.eventTitle} (${event.eventId})</option>
								</c:forEach>             
			                </select>
					</c:if>
					<c:if test="${empty eventList}">
						<select class="form-control" id="eventList_op2">
							<option value="0">No Events are added to Register</option>
						</select>
					</c:if>
				</td>
			</tr>
			<tr> <td></td></tr>
			<tr></tr>
			<tr>
				<td><input id="submit" type="submit" value="Submit" /><input id="id" type="hidden" value="0" /></td>
				<td><a href="/EventManagement"><input id="cancel" type="button" value="Cancel" /></a></td>
			</tr>
		</table>
	</div>
	</form:form>
</body>
<footer><a href="/EventManagement">Return to Home Page</a></footer>
</html>