<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Set Limit</title>	
</head>
<body>

<input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
<br>

<h3>Account: ${account.name}</h3>

<br>
<form:form action="setAccountLimitPost" modelAttribute="account" method="Post">
	<form:hidden path="accountNumber" value="${account.accountNumber}"/>
		<table>
			<tr>
				<th>Current Limit:</th>
				<th>${account.limit}</th>
			</tr>
			<tr>
				<th>New Limit:</th>
				<th><form:input path="limit"/></th>
			</tr>
			<tr>
			<td></td>
			<td><input type="submit" value="Set" class="save"/></td>
		</tr>
		</table>
</form:form>		
		<br><br>
		
		<c:set var="user" value="${employee}"/>
		
		<c:if test="${user==null}">
		<input type="button" value="Back" onclick="window.location.href='showAccountDetails?accountId=${account.accountNumber}'; return false;" class="add-button"/>
		</c:if>
		<c:if test="${user!=null}">
		<input type="button" value="Back" onclick="window.location.href='showClientAccount?accountId=${account.accountNumber}'; return false;" class="add-button"/>
		</c:if>
</body>
</html>