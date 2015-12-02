<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="http://localhost:8080/gamedesigns/resources/script/jquery-1.11.3.js"></script>
<script type="text/javascript" src="http://localhost:8080/gamedesigns/resources/script/updatedesign.js"></script>
</head>
<body>
	<form:form id="updatedesignForm" modelAttribute="designForm"
		enctype="multipart/form-data">
		<form:errors id="error" name="error" />
		<br />
		<table>
			<tr>
				<td><form:label path="name">Name: </form:label></td>
				<td><form:input id="name" name="name" path="name" /></td>
				<td><form:errors path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description: </form:label></td>
				<td><form:textarea id="description" name="description"
						path="description" /></td>
			</tr>
			<tr>
				<td>Default image:</td>
				<td><img alt="" src="../../image/${designForm.ID}" height="200"
					width="200"></td>
				<td><input type="file" id="fileUpload" name="fileUpload"
					size="50" accept="image/*" /></td>
			</tr>
			<tr>
				<td>Change source code archive:</td>
				<td><input type="file" id="archiveUpload" name="archiveUpload"
					size="500" accept=".zip" /></td>

			</tr>
			<tr>
				<td><input type="submit" value="Update Design" /></td>
			</tr>
			<tr>
				<td>Preview images:</td>
			</tr>
			<c:forEach items="${imageList}" var="preview">
				<tr>
					<td><img alt="" src="../../image/preview/${preview.ID}"
						height="200" width="200"></td>
					<td>
					<input type="submit" onclick="javascript:deletePreview(${preview.ID})" value="Delete"/></td>
				</tr>
			</c:forEach>
			<tr>
				<td>Add new picture for preview:</td>
				<td><input type="file" id="previewUpload" name="previewUpload"
					size="500" accept="image/*" /></td>
				<td><input type="submit" value="Add New preview" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>