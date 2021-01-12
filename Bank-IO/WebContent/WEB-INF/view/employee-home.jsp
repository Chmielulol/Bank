<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Employee Home Page</title>	
</head>
<body>
<h3>Home Page</h3>

	Employee: ${employee.login} <input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
		


<br><br><br>

	<h3>Clients:</h3>
	<form:form action="searchClient" modelAttribute="client" method="Post">
		<table>
			<tr>
				<td><form:input path="login"/></td>
				<td><input type="submit" value="Search" class="save"/></td>
				<td><input type="button" value="Add Client" onclick="window.location.href='addClient'; return false;" class="add-button"/></td>
			</tr>
			<tr>
				<th>Login</th>
				<th>Type</th>
				<th>Details</th>
			</tr>
			
			<c:forEach var ="tempClient" items="${clients}" varStatus="loop">
			
			<c:url var="detailsLink" value="/employee/showClientDetails">
				<c:param name="clientId" value = "${tempClient.id}"></c:param>
			</c:url>
				
			<tr>
				<td>${tempClient.login}</td>
				<td>${tempClient.type}</td>
				<td>
					<a href="${detailsLink}">details</a>
				</td>
			</tr>
			</c:forEach>
		
		</table>
		</form:form>
		
</body>
</html>