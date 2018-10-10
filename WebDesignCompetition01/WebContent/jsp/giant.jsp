<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<html>
<head>
<title>哇 ！ 大 神</title>
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
				<a href="../index.jsp">首页</a>  <a
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

	<!-- Three -->
	<section id="three" class="wrapper">
		<div class="inner">
			<header class="align-center">
				<embed src="../swf/consititutor.swf"width="1100" height="80" style="margin-top:-100px;margin-bottom:-50px;"></embed>
			</header>
		</div>
		<div class="inner">
			<div class="flex flex-4">
				<div class="box person" >
					<div class="image round">
						<img class="img-responsive img-circle" src="../images/tx02.jpg"
					style=" width: 168px; height: 168px;">
					</div>
					<h3>井小普</h3>
					<p>积极组织校园活动组织。</p>
				</div>
				<div class="box person" >
					<div class="image round">
						<img class="img-responsive img-circle" src="../images/tyj01.jpg"
					style=" width: 168px; height: 168px;">
					</div>
					<h3>陶雨洁</h3>
					<p>团日活动之星，多次组织团日活动</p>
				</div>
				<div class="box person">
					<div class="image round">
						<img class="img-responsive img-circle" src="../images/xhj01.jpg"
					style=" width: 168px; height: 168px;">
					</div>
					<h3>徐浩军</h3>
					<p>社会活动发起人，多次带着同学走出校园。</p>
				</div>
				<div class="box person">
					<div class="image round">
						<img class="img-responsive img-circle" src="../images/xl01.jpg"
					style=" width: 168px; height: 168px;">
					</div>
					<h3>许磊</h3>
					<p>公益活动之星，多次同学参观养老院。</p>
				</div>
			</div>
		</div>
	</section>
	<!-- 分页 -->
	<div class="container">

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

</body>
</html>