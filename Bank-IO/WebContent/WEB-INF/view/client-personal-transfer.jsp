<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!Doctype html>
<html>

<head>
	<title>Personal Transfer</title>
</head>

<input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
<br>

<body>

		<h3>Personal Transfer</h3>
		
		<form:form action="makeTransferPost" modelAttribute="transfer" method="POST">
				
			<table>
				<tbody>
					<tr>
						<td><label>From:</label>
						<td>
          					<form:select path="senderAccountId">
          						<form:options items="${accountList}" itemValue="accountNumber" itemLabel="name"/>
          					</form:select>
      					</td>
					</tr>
					<tr>
						<td><label>To:</label>
						<td><form:select path="recieverAccountId">
          						<form:options items="${accountList}" itemValue="accountNumber" itemLabel="name"/>
          					</form:select>
          				</td>
					</tr>
					<tr>
						<td><label>Title:</label>
						<td><form:input path="title"/></td>
					</tr>
					<tr>
						<td><label>Money:</label>
						<td><form:input path="money"/></td>
					</tr>
					<tr>
						<td><label>Submit:</label>
						<td><input type="submit" value="Make Transfer" class="save"/></td>
					</tr>
				</tbody>
			
			</table>
			
		</form:form>	

		<br><br><br>
		
		<input type="button" value="Home" onclick="window.location.href='home'; return false;" class="add-button"/>
		
		<input type="button" value="Transfer History" onclick="window.location.href='showTransferHistory'; return false;" class="add-button"/>
		
		<input type="button" value="Transfer" onclick="window.location.href='makeTransfer'; return false;" class="add-button"/>
		
		<input type="button" value="Personal Transfer" onclick="window.location.href='makePersonalTransfer'; return false;" class="add-button"/>
		
		<input type="button" value="Show Data" onclick="window.location.href='showUserData'; return false;" class="add-button"/>

</body>
</html>