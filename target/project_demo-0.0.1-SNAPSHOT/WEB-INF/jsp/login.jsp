<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Login</title>
</head>
<body onLoad='document.f.j_username.focus();'>
<c:if test="${not empty error}">
	<div class="errorblock" style="color:RED">
		Your login attempt was not successful, try again.<br/>
		Caused: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	</div>
</c:if>
<form method="POST" action="<c:url value="/j_spring_security_check"/>">
<table>
	<tr>
		<td align="right">Username</td>
		<td><input type="text" name="j_username" value=''/></td>
	</tr>
	<tr>
		<td align="right">Password</td>
		<td><input type="password" name="j_password"/></td>
	</tr>
	<tr>
		<td align="right">Remember Me</td>
		<td><input type="checkbox" name="_spring_security_remember_me"/></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="submit" value="Login"/>
			<input type="reset" value="Reset"/>
		</td>
	</tr>
</table>
</form>
</body>

</html>