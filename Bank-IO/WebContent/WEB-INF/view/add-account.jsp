<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>

<head>
	<title>Add Account</title>
</head>

<body>

Add Account to Client: ${client.login}  <input type="button" value="Back" onclick="window.location.href='showClientDetails?clientId=${client.id}'; return false;" class="add-button"/>
<input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
<form:form action="addClientAccountPost" modelAttribute="account" method="Post">
		
		<table>
		<tr>
			<td><label>Name:</label></td>
			<td><form:input path="name"/></td>
		</tr>
		<tr>
			<td><label>Percentage:</label></td>
			<td><form:input path="percentage"/></td>
		</tr>
		<tr>
			<td><label>Currency:</label></td>
			<td>
				<form:select path="currency">
					<form:option value="PLN" />
					<form:option value="USD" />
					<form:option value="EUR" />
				</form:select>
			</td>
		</tr>
		<tr>
			<td><label>Type:</label></td>
			<td>
				<form:select path="type">
					<form:option value="Current"/>
					<form:option value="Savings"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Add" class="save"/></td>
		</tr>
		</table>

</form:form>
	

</body>

</html>