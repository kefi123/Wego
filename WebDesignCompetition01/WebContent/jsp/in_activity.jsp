<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<html>
<head>
<title>来啊！快活啊</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../css/main.css" />
</head>
<body class="subpage">

	<!-- Header -->
	<header id="header">
		<div class="inner">
			<a href="../index.jsp" class="logo">WeGo</a>
			<nav id="nav">
				<a href="../index.jsp">首页</a> <a
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

	<div class="container">
		<header class="align-center">
			<embed src="../swf/school.swf"width="1100" height="80"style="margin-top:20px;margin-bottom:50px;"></embed>
		</header>
		<!-- 校内活动展示 -->
		<div class="row" id="display_in_activity"></div>

		<!-- 分页 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
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

	<script>
		$
				.get(
						"${pageContext.request.contextPath}/"
								+ "servlet/activityServlet?method=display_activity&type=1",
						function(data, status) {
							if (data != null) {
								//遍历参加的活动
								for ( var id in data) {
									var content = "<div  class='col-lg-4 col-md-4 col-sm-6 col-xs-12'>"
											+ "<div style='height:454.8px;' class='thumbnail'>"
											+ "<img style='width:420px;height:230px;' src="+data[id].picpath+" alt='' class='img-responsive img-rounded' />"
											+ "<div style='margin-top:20px;' class='caption'>"
											+ "<a href='detail_activity.jsp?activityid="+data[id].activityid+"' style='text-decoration: none;'><h3>"
											+ data[id].title
											+ "</h3></a>"
											+ "<p class='text-muted' style='text-indent: 2em; line-height: 2.0em; padding: 10px; margin: 0;'>"
											+ data[id].description
											+ "</p>"
											+ "</div></div></div>";

									$("#display_in_activity").append(content);
								}

							}
						}, "json");
	</script>

</body>
</html>