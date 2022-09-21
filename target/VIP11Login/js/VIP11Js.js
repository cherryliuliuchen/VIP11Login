function nowInput(content){
	console.log('正在向输入框中输入内容'+content);
}

//通过ajax方法来请求Login接口，并对返回进行处理。
//通过ajax方法来请求Login接口，并对返回进行处理。
function ajaxlogin() {
	$.ajax(
		{
			//设置请求内容，并且发送请求
			//url地址，接口的请求地址。
			url: "./login",
			//http方法
			type: "post",
			//请求参数
			// data:{"user":"roy","password":"123456"},
			data: $("form").serialize(),
			//声明返回结果格式为json。如果返回格式不是标准json，则会走到error回调函数。
			dataType: "json",
			//处理返回结果，也叫回调函数
			//表示的是，收到了返回报文，HTTP协议状态码是2开头的
			//()中的参数，其实指的是返回结果的内容
			success: function (result) {
				//用alert显示返回结果
				// alert(result);
				//js中的json解析
				//登录成功的时候显示蓝色，失败显示红色
				if (result.status == "200") {
					$("h3")[0].style = "color:#2b2bbd";
					window.location.href = "./user.html";
				} else {
					$("h3")[0].style = "color:#c9362b";
				}
				$("h3")[0].innerText = result.msg;
			},
			error: function () {
				alert("请求参数错误，请检查")
			}
		}
	)
}

function getUserInfo() {
	$.ajax({
		//请求的url地址
		url: "./GetUser",
		//http方法
		type: "post",
		// 指定接口返回数据的类型。默认是text。
		dataType: "json",
		//执行完之后的处理操作
		success: function b(result) {
			$("#userid")[0].innerText = result["data"]["id"];
			$("#nickname")[0].innerText = result.data["nickname"];
			$("#describe")[0].innerText = result.data["describe"];
		},
		//error的含义是请求失败，跟业务逻辑没有关系
		error: function (result) {
			alert("查询接口调用失败，请检查日志。");
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
			alert("调用登录接口失败，请检查服务端日志。");
		}
	})
}