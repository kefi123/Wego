function validateForm() {
	// 验证标题
	var x = document.forms["releaseForm"]["title"].value;
	if (x == null || x == "") {
		alert("活动标题必须填写");
		document.forms["releaseForm"]["title"].focus();
		return false;
	}

	// 验证活动地点
	x = document.forms["releaseForm"]["place"].value;
	if (x == null || x == "") {
		alert("活动地点必须填写");
		document.forms["releaseForm"]["place"].focus();
		return false;
	}

	// 验证开始时间
	x = document.forms["releaseForm"]["startdate"].value;
	if (x == null || x == "") {
		alert("开始日期必须填写");
		document.forms["releaseForm"]["startdate"].focus();
		return false;
	}
	// 验证结束时间
	x = document.forms["releaseForm"]["enddate"].value;
	if (x == null || x == "") {
		alert("结束日期必须填写");
		document.forms["releaseForm"]["enddate"].focus();
		return false;
	}
	// 验证人数限制为大于0的整数
	x = document.forms["releaseForm"]["limitsize"].value;
	if (x == null || x == "" || x < 0 || isNaN(x)) {
		alert("人数限制必须为大于0的整数");
		document.forms["releaseForm"]["limitsize"].focus();
		return false;
	}
	
	// 验证上传图片是否为空
	x = document.forms["releaseForm"]["picpath"].value;
	if (x == null || x == "") {
		alert("请上传图片");
		document.forms["releaseForm"]["picpath"].focus();
		return false;
	}
	
	// 验证活动详情
	x = document.forms["releaseForm"]["description"].value;
	if (x == null || x == "") {
		alert("请填写活动的详细信息");
		document.forms["releaseForm"]["description"].focus();
		return false;
	}

}