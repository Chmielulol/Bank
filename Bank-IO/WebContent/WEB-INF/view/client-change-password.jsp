<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>

<head>
	<title>Change Password</title>
</head>

<body>

<input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
<br>
<h3>Change Password</h3>
<form:form action="changePasswordPost" modelAttribute="tempUser" method="Post">
	<form:hidden path="id"/>
	<table>
		<tr>
			<td><label>New Password:</label></td>
			<td><form:password path="password"/></td>
		</tr>
		<tr>
			<td><label>Submit:</label>
			<td><input type="submit" value="Change" class="save"/></td>
		</tr>
	</table>
</form:form>
	

</body>

</html>