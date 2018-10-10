<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>

<html>
<head>
<title>快来发布活动吧！</title>
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
				<c:if test="${user==null }">
					<a href="login.jsp">登录</a>
					<a href="register.jsp">注册</a>
				</c:if>
				<c:if test="${user!=null }">
					<a href="user_center.jsp">个人中心</a>
					<a href="randomActivity.jsp">随机一个</a>
					<a href="release_activity.jsp">发布活动</a>
					<a
						href="${pageContext.request.contextPath }/servlet/userServlet?method=logoff">注销</a>
				</c:if>
			</nav>
			<a href="#navPanel" class="navPanelToggle"><span
				class="fa fa-bars"></span></a>
		</div>
	</header>

	<h1 class="margin-bottom-15" style="color: #5385c1;">
		发布活动
	</h1>
	<div class="container">
		<div class="col-md-12">
			<!-- 含有需要二进制上传的文件 -->
			<form onsubmit="return validateForm()" id="releaseForm" enctype="multipart/form-data"
				class="form-horizontal templatemo-create-account templatemo-container"
				role="form"
				action="${pageContext.request.contextPath }/servlet/activityServlet?method=release_activity"
				method="post">
				<div class="form-inner">
					<div class="form-group">
						<div class="col-md-6">
							<label for="title" class="control-label">活动标题</label> <input
								type="text" class="form-control" id="title" name="title"
								placeholder="">
						</div>
						<div class="col-md-6">
							<label for="place" class="control-label">活动地点</label> <input
								type="text" class="form-control" id="place" name="place"
								placeholder="">
						</div>

					</div>
					<div class="form-group">
						<div class="col-md-6">
							<label for="startdate" class="control-label">开始日期、时间</label>

							<div class="row">
									<div class="col-md-7">
										<input type="date" class="form-control " id="startdate"
											name="startdate">
									</div>
									<div class="col-md-5">
										<input type="time" class="form-control" id="starttime"
											name="starttime">
									</div>
							</div>

						</div>
						<div class="col-md-6">
							<label for="enddate" class="control-label">结束日期、时间</label>



							<div class="row">
									<div class="col-md-7">
										<input type="date" class="form-control " id="enddate"
											name="enddate">
									</div>
									<div class="col-md-5">
										<input type="time" class="form-control" id="endtime"
											name="endtime">
									</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<input
								type="hidden" class="form-control" id="phone" name="phone"
								placeholder="" value="${user.phone}">
						<div class="col-md-6 ">
						<br>
							<label class="radio-inline"> <input type="radio"
								name="type" id="type" value="1" checked>校内活动
							</label> <label class="radio-inline"> <input type="radio"
								name="type" id="type" value="0"> 社会活动
							</label>
						</div>
						<div class="col-md-6">
							<label for="limitsize" class="control-label">人数限制</label> <input
								type="text" class="form-control" id="limitsize" name="limitsize">
						</div>

					</div>

					<div class="form-group">
						<div class="col-md-6 ">
						<br>
							<label class="radio-inline"> <input type="radio"
								name="ispay" id="ispay" value="1" onclick="this.form.money.disabled='';" checked>收费
							</label> <label class="radio-inline"> <input type="radio"
								name="ispay" id="ispay" value="0" onclick="this.form.money.disabled='disabled';this.form.money.value='';"> 不收费
							</label>
						</div>

						<div class="col-md-6">
							<label for="money" class="control-label">收费价格</label> <input
								type="text" class="form-control " id="money" name="money"
								placeholder="">
						</div>
					</div>


					<div class="form-group">

						<div class="col-md-12">
							<label for="picpath" class="control-label">上传活动海报<small style="color:red;">（尺寸最好为420px*230px）</small></label>
						<input
								type="file"  id="picpath"
								name="picpath" class="form-control" >

						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							<label for="description" class="control-label">活动的详情</label>
							<textarea name="description" id="description" class="textarea form-control" style="height:200px;"></textarea>
						</div>

					</div>
					<div class="form-group ">
						<div class="col-md-12 ">
							<!-- <input type="submit" value="创建用户" class="btn btn-info col-md-4 col-md-offset-4"> -->
							<button type="submit"
								class="btn btn-info col-md-4 col-md-offset-4">上传活动</button>
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
	<script src="../js/releaseYZ.js"></script>

</body>
</html>