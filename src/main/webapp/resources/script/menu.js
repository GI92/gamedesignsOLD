jQuery(document).ready(function($) {
	$('#menu-code').load("menu");
	$('#user-date').load("logged");
});

function categorySelect(id) {
	if (id == 0) {
		document.getElementById("body-code").innerHTML = "<h3>Project was created for learning, this is why this site don't have contain any design templates</h3>";
		document.getElementById("categoryID").value = 0;
	} else {
		$('#body-code').load("categorydetails");
		document.getElementById("categoryID").value = id;
		filterChangeContnent();
	}
}

function filterChangeContnent() {
	var method = "GET";
	var categoryID = document.getElementById("categoryID").value;
	var url = "designlist" + "/" + categoryID;
	callServlet(url, method, "designsview");
}

function filterChanged() {
	var method = "POST";
	var categoryID = document.getElementById("categoryID").value;
	var gametypes = [];
	$("input:checkbox[name=configselect]:checked").each(function() {
		gametypes.push($(this).val());
	});
	var url = "designlist" + "/" + categoryID + "/" + gametypes.toString();
	if (gametypes.length == 0) {
		url = "designlist" + "/" + categoryID;
		method = "GET";
	}
	callServlet(url, method, "designsview");
}

function callServlet(url, method, divID) {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById(divID).innerHTML = xmlhttp.responseText;
		}
	}

	xmlhttp.open(method, url, true);
	xmlhttp.send();
}

function loginUser() {
	$('#body-code').load("login");
}

function registerUser() {
	$('#body-code').load("register");
}

function loginUserSubmit() {
	changeSubmit("#loginForm");
}

function registerUserSubmit() {

	changeSubmit("#registerForm");
}

function changeSubmit(formName) {
	$(formName).submit(function(e) {
		e.preventDefault();
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			crossDomain : true,
			data : postData,
			dataType : "html",
			success : function(data, textStatus, jqXHR) {
				document.getElementById("body-code").innerHTML = data;
			}
		});
	});

	$(formName).submit(); // Submit the FORM
}

function userchanges() {
	var selected = document.getElementById("userbar").value;
	if (selected == "portofolio") {
		$('#body-code').load("userdesignlist");
	}
	if (selected == "account") {
		$('#body-code').load("accountdetails");
	}
	if (selected == "create") {
		window.location.replace("createdesign");
	}
	if (selected == "category") {
		$('#body-code').load("categorylist");
	}
	if (selected == "gametype") {
		$('#body-code').load("gametypelist");
	}
	if (selected == "logout") {
		$('#body-code').load("logout");
	}
}

function createdesign() {

}

function updateUser() {
	changeSubmit(updateUserForm);
}

function updateCategory(id) {
	var url = "categorylist";
	var varid = "updateName" + id;
	var name = document.getElementById(varid).value;
	callServlet(url + "/" + name + "/" + id, "POST", "body-code");
}

function removeCategory(id) {
	var url = "category/remove/";
	callServlet(url + "/" + id, "POST", "body-code");
}

function addCategory() {
	var url = "categorylist";
	var name = document.getElementById("addName").value;
	callServlet(url + "/" + name + "/0", "POST", "body-code");
}

function updateGameType(id) {
	var url = "gametypelist";
	var varid = "updateName" + id;
	var name = document.getElementById(varid).value;
	callServlet(url + "/" + name + "/" + id, "POST", "body-code");
}

function removeGameType(id) {
	var url = "gametype/remove/";
	callServlet(url + "/" + id, "POST", "body-code");
}

function addGameType() {
	var url = "gametypelist";
	var name = document.getElementById("addName").value;
	callServlet(url + "/" + name + "/0", "POST", "body-code");
}

function viewdesign(id){
	$('#body-code').load("viewdesign/"+id);
}