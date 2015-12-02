<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<form:form id="createdesignForm" modelAttribute="designForm"
		enctype="multipart/form-data">
		<br />
		Select category: 
		<select name="categoryID">
			<c:forEach items="${categorylist}" var="category">
				<option value="${category.ID}">${category.name}</option>
			</c:forEach>
		</select>
		<br />
		<form:errors id="gametypeError" name="gametypeError" />
		Select game type:
		<br />
		<c:forEach items="${gametypelist}" var="gametype">
			${gametype.name}<input type="checkbox" name="gametype"
				value="${gametype.ID}">
			<BR>
		</c:forEach>
		<table>
			<tr>
				<td><form:label path="name">Name: </form:label></td>
				<td><form:input id="name" name="name" path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description: </form:label></td>
				<td><form:textarea id="description" name="description"
						path="description" /></td>
			</tr>
			<tr>
				<td>Pick default image:</td>
				<td><input type="file" id="fileUpload" name="fileUpload"
					size="50" accept="image/*" /></td>
			</tr>
			<tr>
				<td>Pick source code archive:</td>
				<td><input type="file" id="archiveUpload" name="archiveUpload"
					size="500" accept=".zip" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add Design" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>