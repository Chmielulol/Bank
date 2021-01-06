<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>

<head>
	<title>Login</title>
</head>

<body>

Login:
<form:form action="loginUser" modelAttribute="user" method="Post">
		
		<table>
		<tr>
			<td><label>Login</label></td>
			<td><form:input path="login"/></td>
		</tr>
		<tr>
			<td><label>Password</label></td>
			<td><form:password path="password"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Login" class="save"/></td>
		</tr>
		</table>

</form:form>
	

</body>

</html>