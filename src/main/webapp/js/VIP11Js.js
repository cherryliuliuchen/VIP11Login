function nowInput(content){
	console.log('Typing into the input box'+content);
}


function ajaxlogin() {
	$.ajax(
		{

			url: "./login",
			//http
			type: "post",
			// data:{"user":"roy","password":"123456"},
			data: $("form").serialize(),
			dataType: "json",

			success: function (result) {

				if (result.status == "200") {
					$("h3")[0].style = "color:#2b2bbd";
					window.location.href = "./user.html";
				} else {
					$("h3")[0].style = "color:#c9362b";
				}
				$("h3")[0].innerText = result.msg;
			},
			error: function () {
				alert("Request parameter error, please check")
			}
		}
	)
}

function getUserInfo() {
	$.ajax({

		url: "./GetUser",
		type: "post",
		dataType: "json",
		success: function b(result) {
			$("#userid")[0].innerText = result["data"]["id"];
			$("#nickname")[0].innerText = result.data["nickname"];
			$("#describe")[0].innerText = result.data["describe"];
		},

		error: function (result) {
			alert("The query interface call failed. Please check the log.");
		}
	})
}

function logout() {
	$.ajax({
		url: "./Logout",
		type: "post",
		dataType: "json",
		success: function a(result) {
			alert(result["msg"]);
			window.location.href = "./index.html";
		},
		error: function () {
			alert("Failed to call the login interface, please check the server log.");
		}
	})
}