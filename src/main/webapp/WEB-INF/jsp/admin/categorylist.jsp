<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Category</title>
</head>
<body>
	<form:form id="updateCategoryForm" action="categorylist" methop="POST"
		modelAttribute="categoryForm">
		<form:errors id="error" name="error" />
	</form:form>
	<table>
		<c:forEach items="${categorylist}" var="category">
			<tr>
				<form:form id="updateCategoryForm" action="categorylist"
					methop="POST" modelAttribute="categoryForm">
					<td><form:input id="updateName${category.ID}" name="name"
							path="name" value="${category.name}" /></td>
					<td><input type="submit" value="Update"
						onclick="updateCategory(${category.ID})" /></td>
				</form:form>
				<td><form:form id="deleteCategoryForm"
						action="category/remove/${category.ID}" methop="POST"
						modelAttribute="categoryForm">
						<td><input type="submit" value="Delete"
							onclick="removeCategory(${category.ID})" /></td>
					</form:form></td>
			</tr>
		</c:forEach>
		<tr>
			<form:form id="addCategoryForm" action="categorylist" methop="POST"
				modelAttribute="categoryForm">
				<td><form:input id="addName" name="name" path="name" /></td>
				<td><input type="submit" value="Add" onclick="addCategory()" /></td>
			</form:form>
		</tr>
	</table>
</body>
</html>