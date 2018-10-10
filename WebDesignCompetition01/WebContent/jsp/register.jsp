<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<html>
<head>
<title>来 啊 来 啊 ！</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="../css/main.css" />
<link href="../css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap-theme.min.css" rel="stylesheet"
	type="text/css">
<link href="../css/templatemo_style.css" rel="stylesheet"
	type="text/css">
<c:if test="${message!=null}">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>

<!-- 在jsp页面中移除session的值 -->
<c:remove var="message" scope="session" />

</head>
<body class="subpage">

	<!-- Header -->
	<header id="header">
		<div class="inner">
			<a href="../index.jsp" class="logo">WeGo</a>
			<nav id="nav">
				<a href="../index.jsp">首页</a><a
					href="in_activity.jsp">校内活动</a> <a href="out_activity.jsp">社会活动</a>
					<a href="giant.jsp">优秀组织者</a>
				<a href="login.jsp">登录</a> <a href="register.jsp">注册</a>
			</nav>
			<a href="#navPanel" class="navPanelToggle"><span
				class="fa fa-bars"></span></a>
		</div>
	</header>

	<h1 class="margin-bottom-15" style="color: #5385c1;">
		<i class="fa fa-user-plus"></i>&nbsp;&nbsp;&nbsp;创 建 用 户
	</h1>
	<div class="container">
		<div class="col-md-12">
			<!-- 含有需要二进制上传的文件 -->
			<form onsubmit="return validateForm()" id="registerForm" enctype="multipart/form-data"
				class="form-horizontal templatemo-create-account templatemo-container"
				role="form"
				action="${pageContext.request.contextPath }/servlet/userServlet?method=register"
				method="post">
				<div class="form-inner">
					<div class="form-group">
						<div class="col-md-6">
							<label for="username" class="control-label">用户名</label>
							<input type="text" class="form-control" id="username"
								name="username" placeholder="">
						</div>
						<div class="col-md-6">
							<label for="nickname" class="control-label">用户昵称</label> <input
								type="text" class="form-control" id="nickname" name="nickname"
								placeholder="">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<label for="phone" class="control-label">手机号</label> <input
								type="text" class="form-control" id="phone" name="phone"
								placeholder="">
						</div>
						<br>

						<div class="col-md-6 ">
							<label class="radio-inline"> <input type="radio"
								name="sex" id="sex" value="男" checked>男
							</label> <label class="radio-inline"> <input type="radio"
								name="sex" id="sex" value="女"> 女
							</label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-6">
							<label for="password" class="control-label">密码</label> <input
								type="password" class="form-control" id="password"
								name="password" placeholder="">
						</div>
						<div class="col-md-6">
							<label for="password" class="control-label">重复密码</label> <input
								type="password" class="form-control" id="password_confirm"
								name="password_confirm" placeholder="">
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-6">
							<label for="realname" class="control-label">真实姓名</label> <input
								type="text" class="form-control" id="realname" name="realname"
								placeholder="">
						</div>
						<div class="col-md-6">
							<label for="profilepic" class="control-label">上传头像</label> <input
								type="file" class="form-control" id="profilepic"
								name="profilepic" placeholder="">
						</div>
					</div>
					<div class="form-group ">
						<div class="col-md-12 ">
							<!-- <input type="submit" value="创建用户" class="btn btn-info col-md-4 col-md-offset-4"> -->
							<button type="submit"
								class="btn btn-info col-md-4 col-md-offset-4">创建用户</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- Footer -->
	<footer id="footer">
		<div class="inner">
			<div class="flex">
				<div class="copyright">制作团队成员：徐浩军、陶雨洁、井小普</div>

			</div>
		</div>
	</footer>

	<!-- Scripts -->

	<script src="../js/jquery-1.11.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/skel.min.js"></script>
	<script src="../js/util.js"></script>
	<script src="../js/main.js"></script>
	<script src="../js/registerYZ.js"></script>

</body>
</html>