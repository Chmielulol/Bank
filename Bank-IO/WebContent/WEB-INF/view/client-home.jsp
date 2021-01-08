<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Client Home Page</title>	
</head>
<body>
<h3>Home Page</h3>

	Client: ${user.login} 		<input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
		


<br><br><br>

	<h3>Accounts:</h3>
		<table>
			<tr>
				<th>Name</th>
				<th>Money</th>
				<th>Currency</th>
				<th>Details</th>
			</tr>
			
			<c:forEach var ="tempAccount" items="${user_accounts}" varStatus="loop">
			
			<c:url var="detailsLink" value="/client/showAccountDetails">
				<c:param name="accountId" value = "${tempAccount.accountNumber}"></c:param>
			</c:url>
				
			<tr>
				<td>${tempAccount.name}</td>
				<td>${tempAccount.money}</td>
				<td>${tempAccount.currency}</td>
				<td>
					<a href="${detailsLink}">details</a>
				</td>
			</tr>
			</c:forEach>
		
		</table>
		
		<br><br><br>
		
		<input type="button" value="Home" onclick="window.location.href='home'; return false;" class="add-button"/>
		
		<input type="button" value="Transfer History" onclick="window.location.href='showTransferHistory'; return false;" class="add-button"/>
		
		<input type="button" value="Transfer" onclick="window.location.href='makeTransfer'; return false;" class="add-button"/>
		
		<input type="button" value="Personal Transfer" onclick="window.location.href='makePersonalTransfer'; return false;" class="add-button"/>
		
		<input type="button" value="Show Data" onclick="window.location.href='showUserData'; return false;" class="add-button"/>
		

</body>
</html>