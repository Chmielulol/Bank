
<!Doctype html>
<html>

<head>
	<title>User Data</title>
</head>

<body>

<input type="button" value="Sign Out" onclick="window.location.href='signOut'; return false;" class="add-button"/>
<br>

		<h3>User Data</h3>			
			<table>
				<tbody>
					<tr>
						<td><label>Login:</label>
						<td>${user.login}</td>
					</tr>
					<tr>
						<td><label>First Name:</label>
						<td>${userData.firstName }</td>
					</tr>
					<tr>
						<td><label>Last Name:</label>
						<td>${userData.lastName }</td>
					</tr>
					<tr>
						<td><label>PESEL:</label>
						<td>${userData.pesel }</td>
					</tr>
					<tr>
						<td><label>Email:</label>
						<td>${userData.email }</td>
					</tr>
					<tr>
						<td><label>Phone Number:</label>
						<td>${userData.phoneNumber }</td>
					</tr>
					<tr>
						<td><input type="button" value="Change Password" onclick="window.location.href='changePassword'; return false;" class="add-button"/>
					</tr>
				</tbody>
			
			</table>

		<br><br><br>
		
		<input type="button" value="Home" onclick="window.location.href='home'; return false;" class="add-button"/>
		
		<input type="button" value="Transfer History" onclick="window.location.href='showTransferHistory'; return false;" class="add-button"/>
		
		<input type="button" value="Transfer" onclick="window.location.href='makeTransfer'; return false;" class="add-button"/>
		
		<input type="button" value="Personal Transfer" onclick="window.location.href='makePersonalTransfer'; return false;" class="add-button"/>
		
		<input type="button" value="Show Data" onclick="window.location.href='showUserData'; return false;" class="add-button"/>


</body>
</html>