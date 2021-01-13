<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>

<html>

<head>
	<title>Add Client</title>
</head>

<body>

<input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
<br>
Add Client:
<form:form action="addClientPost" modelAttribute="data" method="Post">
		<table>
		<tr>
			<td><label>First Name</label></td>
			<td><form:input path="firstName"/></td>
		</tr>
		<tr>
			<td><label>Last Name</label></td>
			<td><form:input path="lastName"/></td>
		</tr>
		<tr>
			<td><label>Pesel</label></td>
			<td><form:input path="pesel"/></td>
		</tr>
		<tr>
			<td><label>Email</label></td>
			<td><form:input path="email"/></td>
		</tr>
		<tr>
			<td><label>Phone Number</label></td>
			<td><form:input path="phoneNumber"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Add" class="save"/></td>
		</tr>
		</table>

</form:form>
	

</body>

</html>