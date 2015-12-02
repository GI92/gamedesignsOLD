<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<form:form id="registerForm" modelAttribute="userForm">
		<table>
			<tr>
				<td><form:label path="username">Username: </form:label></td>
				<td><form:input id="username" name="username" path="username" /></td>
				<td><form:errors path="username" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password: </form:label></td>
				<td><form:password id="password" name="password"
						path="password" /></td>
				<td><form:errors path="password" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email: </form:label></td>
				<td><form:input id="email" name="email" path="email" /></td>
				<td><form:errors path="email" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"
					onclick="registerUserSubmit()" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>