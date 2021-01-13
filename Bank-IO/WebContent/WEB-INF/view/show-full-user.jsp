<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Client Page</title>	
</head>
<body>
<h3>Client : ${client.login}</h3>

	Employee: ${employee.login} <input type="button" value="Home" onclick="window.location.href='home'; return false;" class="add-button"/>
	<input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
		
<br><br>

<h3>Personal Data:</h3>
<table>
	
	<tr>
		<td>First Name:</td>
		<td>${clientData.firstName}</td>
	</tr>
	<tr>
		<td>Last Name:</td>
		<td>${clientData.lastName}</td>
	</tr>
	<tr>
		<td>Pesel:</td>
		<td>${clientData.pesel}</td>
	</tr>
	<tr>
		<td>Email:</td>
		<td>${clientData.email}</td>
	</tr>
	<tr>
		<td>Phone Number:</td>
		<td>${clientData.phoneNumber}</td>
	</tr>
		
</table>

<input type="button" value="Transfer History" onclick="window.location.href='showTransferHistory'; return false;" class="add-button"/>

<br><br>

	<h3>Accounts:</h3> 
	<input type="button" value="Add Account" onclick="window.location.href='addClientAccount'; return false;" class="add-button"/>
		<table>
			<tr>
				<th>Name</th>
				<th>Currency</th>
				<th>Show Details</th>
			</tr>
			
			<c:forEach var ="tempAccount" items="${clientAccounts}" varStatus="loop">
			
			<c:url var="detailsLink" value="/employee/showClientAccount">
				<c:param name="accountId" value = "${tempAccount.accountNumber}"></c:param>
			</c:url>
				
			<tr>
				<td>${tempAccount.name}</td>
				<td>${tempAccount.currency}</td>
				<td>
					<a href="${detailsLink}">details</a>
				</td>
			</tr>
			</c:forEach>
		
		</table>
		
</body>
</html>