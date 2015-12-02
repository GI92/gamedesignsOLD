<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game type list</title>
</head>
<body>
	<form:form id="Errors forms" modelAttribute="gameTypeForm">
		<form:errors id="error" name="error" />
	</form:form>
	<table>
		<c:forEach items="${gametypelist}" var="gameType">
			<tr>
				<form:form id="updateGameTypeForm" action="gametypelist"
					methop="POST" modelAttribute="gameTypeForm">
					<td><form:input id="updateName${gameType.ID}" name="name"
							path="name" value="${gameType.name}" /></td>
					<td><input type="submit" value="Update"
						onclick="updateGameType(${gameType.ID})" /></td>
				</form:form>
				<td><form:form id="deleteGameTypeForm"
						action="gametype/remove/${gameType.ID}" methop="POST"
						modelAttribute="gameTypeForm">
						<td><input type="submit" value="Delete"
							onclick="removeGameType(${gameType.ID})" /></td>
					</form:form></td>
			</tr>
		</c:forEach>
		<tr>
			<form:form id="addGameTypeForm" method="post" action="gametypelist"
				modelAttribute="gameTypeForm">
				<td><form:input id="addName" name="name" path="name" /></td>
				<td><input type="submit" value="Add" onclick="addGameType()" /></td>
			</form:form>
		</tr>
	</table>
</body>
</html>