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
	<table>
		<tr>
			<td style="border-right-style: solid; vertical-align: text-top;">
				<div id="searchfilter">
					<c:forEach items="${gametypelist}" var="gametype">
						${gametype.name}<input type="checkbox" onchange="filterChanged()"
							id="configselect" name="configselect" value="${gametype.ID}">
						<BR>
					</c:forEach>
				</div>
			</td>
			<td>
				<div id="designsview">
				</div>
			</td>
		</tr>
	</table>
</body>
</html>