<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Management System - Confirmation</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

	});
</script>
<style>
body {
    margin: 0;
    padding: 0;
    text-align:center;
    margin-top:5%;
}

.container {
    position: relative;
    left:42%;
    width: 100%;
    display:inline-block;
    margin-top:5%;
}
</style>
</head>
<body class="body">
	<h2>
		<b><font color="green">${message}</font>	</b>
	</h2>

	<div class="container">
		<table>
			<tr>
				<td></td>
				<td width="120"><a href="/EventManagement"><input type="button"
							value="Home" /></a></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
</body>
</html>