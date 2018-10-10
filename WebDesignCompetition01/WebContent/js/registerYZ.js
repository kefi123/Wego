function validateForm() {
	// 验证用户名不能为空
	var x = document.forms["registerForm"]["username"].value;
	if (x == null || x == "") {
		alert("请填写用户名");
		document.forms["registerForm"]["username"].focus();
		return false;
	}

	// 验证用户昵称不能为空
	x = document.forms["registerForm"]["nickname"].value;
	if (x == null || x == "") {
		alert("请填写用户昵称");
		document.forms["registerForm"]["nickname"].focus();
		return false;
	}

	// 验证手机号不能为空并且必须是11位的整数
	x = document.forms["registerForm"]["phone"].value;
	if (x == null || x == "" || isNaN(x)) {
		alert("请输入11位的手机号");
		document.forms["registerForm"]["phone"].focus();
		return false;
	}
	// 验证密码不能为空
	x = document.forms["registerForm"]["password"].value;
	if (x == null || x == "") {
		alert("请设置登录密码");
		document.forms["registerForm"]["password"].focus();
		return false;
	}
	// 验证重复密码不能为空，并且重复密码要和密码相等
	var y = document.forms["registerForm"]["password_confirm"].value;
	if (y == null || y == "" || y != x) {
		alert("请输入与密码相同的重复密码");
		document.forms["registerForm"]["password_confirm"].focus();
		return false;
	}

	// 验证上传图片是否为空
	x = document.forms["registerForm"]["profilepic"].value;
	if (x == null || x == "") {
		alert("请上传头像");
		document.forms["registerForm"]["profilepic"].focus();
		return false;
	}

	// 验证真实姓名不能为空并且不是数字
	x = document.forms["registerForm"]["realname"].value;
	if (x == null || x == "" || !isNaN(x)) {
		alert("请输入正确的真实姓名");
		document.forms["registerForm"]["realname"].focus();
		return false;
	}

}