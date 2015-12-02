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
	<input id="categoryID" type="hidden" name="categoryID" value="0" />
	<table>
		<tr>
			<td>
				<button id="categoryButton" onclick="categorySelect(0)">HOME</button>
			</td>
			<c:forEach items="${categorylist}" var="category">
				<td>
					<button id="categoryButton"
						onclick="categorySelect(${category.ID})">${category.name}</button>
				</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>