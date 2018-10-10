<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<html>
<head>
<title>用户中心</title>
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
				<a href="../index.jsp">首页</a><a href="in_activity.jsp">校内活动</a> <a
					href="out_activity.jsp">社会活动</a> <a href="giant.jsp">优秀组织者</a>
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

	<div class="container">
		<div class="row" style="margin-top: 50px;">

			<div class="col-md-2"
				style="height: 600px; background-color: #c3e0eb; margin-left: 50px;">
				<img class="img-responsive img-circle" src="${user.profilepic }"
					style="margin-top: 30px; width: 168px; height: 168px;"> <br>
				<h5 class="text-center text-muted">欢迎你！${user.nickname }</h5>
				<br>
				<ul id="myTab" class="nav nav-tabs nav-stacked">
					<li class="active"><a href="#user-info" data-toggle="tab">
							个人资料 </a></li>
					<li><a href="#joined" data-toggle="tab">参加的活动</a></li>
					<li><a href="#released" data-toggle="tab">发布的活动</a></li>
				</ul>
			</div>
			<div class="col-md-9"
				style="height: 600px; background-color: #fff; margin-left: 25px;">
				<div id="myTabContent" class="tab-content row">
					<div class="tab-pane fade active in col-md-8 col-md-offset-2"
						id="user-info">
						<h3 class="text-muted text-center" style="margin-top: 50px;">个人资料</h3>
						<table class="table" style="margin-top: 50px;">
							<tr>
								<td>手机号码</td>
								<td>${user.phone }</td>
							</tr>
							<tr>
								<td>用户名</td>
								<td>${user.username }</td>
							</tr>
							<tr>
								<td>昵称</td>
								<td><input class="form-control" type="text" name="nickname"
									value="${user.nickname }"></td>
							</tr>
							<tr>
								<td>真实姓名</td>
								<td>${user.realname }</td>
							</tr>
							<tr>
								<td>性别</td>
								<td>${user.sex }</td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="submit" class="btn btn-primary pull-right"
										style="width: 100px; font-size: 16px;">保存</button>
								</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane fade col-md-10 col-md-offset-1" id="joined">
						<h3 class="text-info text-center" style="margin-top: 50px;">参加的活动</h3>
						<table class="table text-info" style="margin-top: 50px;">
							<thead>
								<tr>
									<th>活动海报</th>
									<th>活动标题</th>
									<th>活动状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="joinedActivity">
							</tbody>

						</table>
						<div class="row" style="margin-top: 50px; display: none;"
							id="cjfy">
							<div class="col-md-7 col-md-offset-3">
								<ul class="pagination" style="padding-left: 2em;">
									<li><a href="#">&laquo;</a></li>
									<li class="active"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">....</a></li>
									<li><a href="#">&raquo;</a></li>

								</ul>

							</div>
						</div>
					</div>
					<div class="tab-pane fade  col-md-10 col-md-offset-1" id="released">
						<h3 class="text-info text-center" style="margin-top: 50px;">发布的活动</h3>
						<table class="table text-info" style="margin-top: 50px;">
							<thead>
								<tr>
									<th>活动海报</th>
									<th>活动标题</th>
									<th>当前人数</th>
									<th>活动状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="userReleased">
							</tbody>

						</table>
						<div class="row" style="margin-top: 50px; display:none;"
							id="fbfy">
							<div class="col-md-7 col-md-offset-3">
								<ul class="pagination" style="padding-left: 2em;">
									<li><a href="#">&laquo;</a></li>
									<li class="active"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">....</a></li>
									<li><a href="#">&raquo;</a></li>

								</ul>

							</div>
						</div>
					</div>
				</div>
			</div>
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
</body>

<!-- Scripts -->

<script src="../js/jquery-1.11.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/skel.min.js"></script>
<script src="../js/util.js"></script>
<script src="../js/main.js"></script>

<script>
	$
			.get(
					"${pageContext.request.contextPath}/"
							+ "servlet/activityServlet?method=userReleased&phone=${user.phone}",
					function(data, status) {
						if (data != null && data != "") {
							//遍历发布的活动
							for ( var id in data) {
								var status;

								var cancel = "";

								if (data[id].status == 0) {
									status = "报名中~~";
									cancel = "<td><a href='${pageContext.request.contextPath}/"
											+ "servlet/activityServlet?method=cancelActivity&activityid="
											+ data[id].activityid
											+ "&startTime="+data[id].starttime+"' class='btn btn-info'>取消活动</a></td></tr>";
								} else if (data[id].status == 1)
									status = "已开始~~";
								else if (data[id].status == 2)
									status = "已结束~~";
								else
									status = "活动已取消~~";

								var content = "<tr>"
										+ "<td><img src="+data[id].picpath+" style='width: 250px; height: 120px;' alt='' class='img-responsive img-rounded' /></td>"
										+ "<td><a href='detail_activity.jsp?activityid="+data[id].activityid+"' style='text-decoration: none;'>" + data[id].title
										+ "</a></td>"
										+ "<td>" + data[id].currentsize
										+ " 人</td>" + "<td>" + status + "</td>"
										+ cancel;

								$("#userReleased").append(content);
							}
							document.getElementById("fbfy").style.display = "";
						}
					}, "json");
</script>
<script>
	$
			.get(
					"${pageContext.request.contextPath}/"
							+ "servlet/joinActivityServlet?method=display_joinActivity&phone=${user.phone}",
					function(data, status) {
						if (data != null && data != "") {
							//遍历参加的活动
							for ( var id in data) {
								var status;

								var cancel = "";

								if (data[id].activity.status == 0
										&& data[id].joinstatus == 0) {
									status = "报名中~~";
									cancel = "<td><a href='${pageContext.request.contextPath}/"
											+ "servlet/joinActivityServlet?method=quit_activity&activityid="
											+ data[id].activity.activityid
											+ "&phone=${user.phone}' class='btn btn-info'>退出</a></td></tr>";
								} else if (data[id].activity.status == 0
										&& data[id].joinstatus == 1) {
									status = "已退出";
								} else if (data[id].activity.status == 1)
									status = "已开始~~";
								else if (data[id].activity.status == 2)
									status = "已结束~~";
								else
									status = "活动已取消~~";

								var content = "<tr>"
										+ "<td><img src="+data[id].activity.picpath+" style='width: 280px; height: 120px;' alt='' class='img-responsive img-rounded' /></td>"
										+ "<td><a href='detail_activity.jsp?activityid="+data[id].activity.activityid+"' style='text-decoration: none;'>" + data[id].activity.title
										+ "</a></td>" + "<td>" + status + "</td>"
										+ cancel;

								$("#joinedActivity").append(content);
							}
							document.getElementById("cjfy").style.display = "";
						}
					}, "json");
</script>
</html>