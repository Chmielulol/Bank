<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Account Detail</title>	
</head>
<body>

<input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
<br>

<h3>Account: ${account.name}</h3>

<c:set var="user" value="${employee}" />

<br>

		<table>
			<tr>
				<th>Number:</th>
				<th>${account.accountNumber}</th>
			</tr>
			<tr>
				<th>Type:</th>
				<th>${account.type}</th>
			</tr>
			<c:if test="${user == null}">
			<tr>
				<th>Money:</th>
				<th>${account.money}</th>
			</tr>
			</c:if>
			<tr>
				<th>Currency:</th>
				<th>${account.currency}</th>
			</tr>
			<tr>
				<th>Percentage:</th>
				<th>${account.percentage}</th>
			</tr>
			<tr>
				<th>Limit:</th>
				<th>${account.limit}</th>
				<th><input type="button" value="Change Limit" onclick="window.location.href='setAccountLimit?accountNumber=${account.accountNumber}'; return false;" class="add-button"/></th>
			</tr>
		</table>
		
		<br><br>
		
		<c:if test="${user!=null }">
			<input type="button" value="Add card" onclick="window.location.href='addCard?accountId=${account.accountNumber}'; return false;" class="add-button"/>
			<br>
		</c:if>
		
		<c:set var="tempCards" value="${cards}"/>
		<c:if test="${tempCards != null}">
		<table>
			<tr>
				<th>Card Number</th>
				<th>Expiery Date</th>
				<th>Limit</th>
			</tr>
			
			<c:forEach var ="tempCard" items="${cards}">	
			
			<c:if test="${user!=null }">
			<c:url var="detailsLink" value="/employee/setCardLimit">
				<c:param name="cardId" value = "${tempCard.id}"></c:param>
			</c:url>
			</c:if>
			
			<c:if test="${user==null }">
			<c:url var="detailsLink" value="/client/setCardLimit">
				<c:param name="cardId" value = "${tempCard.id}"></c:param>
			</c:url>
			</c:if>
						
			<tr>
				<td>${tempCard.id}</td>
				<td>${tempCard.expDate}</td>
				<td>${tempCard.cardLimit}</td>
				<td><input type="button" value="Change Limit" onclick="window.location.href='${detailsLink}'; return false;" class="add-button"/></td>
				
			</tr>
			</c:forEach>
		
		</table>
		
		<br><br><br>
		</c:if>
		
		<input type="button" value="Back" onclick="window.location.href='home'; return false;" class="add-button"/>
		
		
</body>
</html>