<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<html>
<head>
<title>登 录</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link href="../css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap-theme.min.css" rel="stylesheet"
	type="text/css">
<link href="../css/templatemo_style.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="../css/main.css" />

<c:if test="${message!=null}">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>

<!-- 在jsp页面中移除session的值 -->
<c:remove  var="message"  scope="session"  />

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

	<div class="container">
		<div class="col-md-12">
			<h1 class="margin-bottom-15 h1" style="color: #5385c1;margin-top:50px;margin-bottom:80px;">都来了，你还不登录？</h1>
			<form
				class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30"
				role="form"
				action="${pageContext.request.contextPath }/servlet/userServlet?method=login"
				method="post">
				<div class="form-group">
					<div class="col-xs-12">
						<div class="control-wrapper">
							<label for="username" class="control-label fa-label"> <i
								class="fa fa-user"></i></label> <input type="text" class="form-control"
								id="username" name="username" placeholder="用户名">
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">
						<div class="control-wrapper">
							<label for="password" class="control-label fa-label"><i
								class="fa fa-lock"></i></label> <input type="password"
								class="form-control" id="password" name="password"
								placeholder="密码">
						</div>
					</div>
				</div>
				<br>
				<div class="form-group">
					<div class="col-md-12">
						<div class="control-wrapper col-md-6">
							<input type="submit" value=" 登 录 " class="btn btn-info">

						</div>
						<div class="text-center col-md-6">
							<a href="register.jsp" class="templatemo-create-new"> 创 建 用 户
								<i class="fa fa-arrow-circle-o-right"></i>
							</a>
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

</body>
</html>