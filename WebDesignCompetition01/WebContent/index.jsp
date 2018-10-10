<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<html>
<head>
<title>WeGo网</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/main.css" />


<c:if test="${message!=null}">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>

<!-- 在jsp页面中移除session的值 -->
<c:remove var="message" scope="session" />

</head>
<body>

	<!-- Header -->
	<header id="header">
		<div class="inner">
			<a href="index.jsp" class="logo">WeGo</a>
			<nav id="nav">
				<a href="index.jsp">首页</a>  <a
					href="jsp/in_activity.jsp">校内活动</a> <a href="jsp/out_activity.jsp">社会活动</a>
					<a href="jsp/giant.jsp">优秀组织者</a>
				<c:if test="${user==null }">
					<a href="jsp/login.jsp">登录</a>
					<a href="jsp/register.jsp">注册</a>
				</c:if>
				<c:if test="${user!=null }">
					<a href="jsp/user_center.jsp">个人中心</a>
					<a href="jsp/randomActivity.jsp">随机一个</a>
					<a href="jsp/release_activity.jsp">发布活动</a>
					<a
						href="${pageContext.request.contextPath }/servlet/userServlet?method=logoff">注销</a>
				</c:if>
			</nav>
			<a href="#navPanel" class="navPanelToggle"><span
				class="fa fa-bars"></span></a>
		</div>
	</header>

	<!-- Banner -->
	<section id="banner">
		<h1>欢迎来到 WeGo 网</h1>
		<p>一个让你娱乐、交友的平台~~~~</p>
	</section>

	<!-- Three -->
	<section id="three" class="wrapper special">
		<div class="inner">
			<header class="align-center">
				<h2><a href="#three" >JOIN US</a></h2>
				<p>本 月 活 动 推 荐</p>
			</header>
			<div class="flex flex-2" >
				<article>
					<div class="image fit">
						<img src="images/pic01.jpg" alt="Pic 01" />
					</div>
					<header>
						<c:if test="${user!=null }">
							<a href="jsp/detail_activity.jsp?activityid=6ab62b2a-0aee-4426-afa1-92fc8edc7c8f" style="text-decoration: none;"><h3>封龙山一日游</h3></a>
						</c:if>
						<c:if test="${user==null }">
						
							<a href="#" onclick="alert('请先登录，再查看');" style="text-decoration: none;"><h3>封龙山一日游</h3></a>
						</c:if>
						
					</header>
					<p>背起你的行囊，和小伙伴们一起迈开双腿，走出校园，和我们一起去参观，攀爬美丽，陡峭的封龙山吧！</p>

				</article>
				<article>
					<div class="image fit">
						<img src="images/pic02.jpg" alt="Pic 02" />
					</div>
					<header>
					<c:if test="${user!=null }">
							<a href="jsp/detail_activity.jsp?activityid=5e319aa6-ad2f-4a05-bb42-3d583188e6db" style="text-decoration: none;"><h3>校园摄影比赛</h3></a>
						</c:if>
						<c:if test="${user==null }">
						
							<a href="#" onclick="alert('请先登录，再查看');" style="text-decoration: none;"><h3>校园摄影比赛</h3></a>
						</c:if>
						
					</header>
					<p>
						拿起你的相机或手机。 <br>和美丽动人的小伙伴们，一起拍下美丽的瞬间吧。
					</p>
				</article>
			</div>
		</div>
	</section>

	<!-- Two -->
	<section id="two" class="wrapper style1 special">
		<div class="inner">
			<header>
				<h2><a href="#two">hello man / woman !</a></h2>
				<p>优秀组织者 推 荐</p>
			</header>
			<div class="flex flex-4">
				<div class="box person" >
					<div class="image round">
						<img class="img-responsive img-circle" src="images/tx02.jpg"
					style=" width: 168px; height: 168px;">
					</div>
					<h3>井小普</h3>
					<p>积极组织校园活动组织。</p>
				</div>
				<div class="box person" >
					<div class="image round">
						<img class="img-responsive img-circle" src="images/tyj01.jpg"
					style=" width: 168px; height: 168px;">
					</div>
					<h3>陶雨洁</h3>
					<p>团日活动之星，多次组织团日活动</p>
				</div>
				<div class="box person">
					<div class="image round">
						<img class="img-responsive img-circle" src="images/xhj01.jpg"
					style=" width: 168px; height: 168px;">
					</div>
					<h3>徐浩军</h3>
					<p>社会活动发起人，多次带着同学走出校园。</p>
				</div>
				<div class="box person">
					<div class="image round">
						<img class="img-responsive img-circle" src="images/xl01.jpg"
					style=" width: 168px; height: 168px;">
					</div>
					<h3>许磊</h3>
					<p>公益活动之星，多次同学参观养老院。</p>
				</div>
			</div>
		</div>
	</section>



	<!-- Footer -->
	<footer id="footer">
		<div class="inner">
			<div class="flex">
				<div class="copyright">制作团队成员：徐浩军、陶雨洁、井小普</div>

			</div>
		</div>
	</footer>

	<!-- Scripts -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/skel.min.js"></script>
	<script src="js/util.js"></script>
	<script src="js/main.js"></script>

</body>
</html>