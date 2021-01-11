<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Transfer History</title>	
</head>
<body>

<input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
<br>

<h3>Transfer History</h3>

<br><br><br>


		<table>
			<tr>
				<th>Title</th>
				<th>Money</th>
				<th>Currency</th>
				<th>Date</th>
				<th>From</th>
				<th>To</th>
			</tr>
			
			<c:forEach var ="tempTransfer" items="${transferHistory}">				
			<tr>
				<td>${tempTransfer.title}</td>
				<td>${tempTransfer.money}</td>
				<td>${tempTransfer.currency}</td>
				<td>${tempTransfer.date}</td>
				<td>${tempTransfer.senderAccountId}</td>
				<td>${tempTransfer.recieverAccountId}</td>
				
			</tr>
			</c:forEach>
		
		</table>
		
		<br><br><br>
		
		<c:set var="tempUser" value="${employee}" />
		
		<c:if test="${tempUser != null}">
			<input type="button" value="Back" onclick="window.location.href='showClientDetails?clientId=${user.id}'; return false;" class="add-button"/>
		</c:if>
		<c:if test="${tempUser == null}">
		<input type="button" value="Home" onclick="window.location.href='home'; return false;" class="add-button"/>
		
		<input type="button" value="Transfer History" onclick="window.location.href='showTransferHistory'; return false;" class="add-button"/>
		
		<input type="button" value="Transfer" onclick="window.location.href='makeTransfer'; return false;" class="add-button"/>
		
		<input type="button" value="Personal Transfer" onclick="window.location.href='makePersonalTransfer'; return false;" class="add-button"/>
		
		<input type="button" value="Show Data" onclick="window.location.href='showUserData'; return false;" class="add-button"/>
		</c:if>
</body>
</html>

<c:if test=""></c:if>