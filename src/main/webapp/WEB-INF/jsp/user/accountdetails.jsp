<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<form:form id="updateUserForm" method="POST" action="accountdetails"
	modelAttribute="userForm">
	<table>
		<form:errors id="error" name="error"/>
		<tr>
			<td><form:label path="username">Username: </form:label></td>
			<td><form:input id="username" name="username" path="username" /></td>
			<td><form:errors path="username" /></td>
		</tr>
		<tr>
			<td><form:label path="password">Password: </form:label></td>
			<td><form:password id="password" name="password" path="password" /></td>
			<td><form:errors path="password" /></td>
		</tr>
		<tr>
			<td><form:label path="email">Email: </form:label></td>
			<td><form:input id="email" name="email" path="email" /></td>
			<td><form:errors path="email" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Update" onclick="updateUser()" /></td>
		</tr>
	</table>
</form:form>