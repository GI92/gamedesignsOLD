function deletePreview(id){
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			//window.location.replace("http://localhost:8080/gamedesigns/design/update/3");
		}
	}

	xmlhttp.open("GET", "deletePreview/"+id, true);
	xmlhttp.send();
}