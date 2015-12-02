<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${designlist}" var="design">
	Design name: ${design.name}
	<BR>
	<img alt="" src="image/${design.ID}" height="200" width="200" onclick="javascript:viewdesign(${design.ID})">
	<BR>
	============================================================
	<BR>
</c:forEach>