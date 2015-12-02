<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="get" action="download/${design.ID}">
	<button type="submit">Downloan design</button>
</form>
<form method="GET" action="design/update/${design.ID}">
	<button type="submit">Update design</button>
</form>
<form method="get" action="design/delete/${design.ID}">
	<button type="submit">Delete design</button>
</form>
<BR>
Design name: ${design.name}
<BR>
Description: ${design.description}
<BR>
Images:
<BR>
<img alt="" src="image/${design.ID}" height="200" width="200">
<BR>
<c:forEach items="${designpreview}" var="preview">
	<img alt="" src="image/preview/${preview.ID}" height="200" width="200">
</c:forEach>
<BR>