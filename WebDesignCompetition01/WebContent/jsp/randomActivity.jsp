<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<html>
<head>
<title>随机一个</title>
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

	<div class="container">
		<div class="row " style="margin-top: 50px;">
			<!-- 占十格，并且居中 -->
			<div class="col-md-10 col-md-offset-1">
				<!-- 让活动详情显示在一个圆边矩形框内 -->
				<div class="thumbnail row" id="content">
					<h3 class="text-info text-center" id="title"></h3>
					<table class="table">
						<tr>
							<td colspan="" rowspan="10" headers="" id="picpath">
							</td>
							<td>活动类型：</td>
							<td id="type"></td>
						</tr>
						<tr>
							<td>时间：</td>
							<td id="time"></td>
						</tr>
						<tr>
							<td>地点：</td>
							<td id="place"></td>
						</tr>
						<tr>
							<td>人数上限：</td>
							<td id="limitsize"></td>
						</tr>
						<tr>
							<td>已报名人数：</td>
							<td id="currentsize"></td>
						</tr>
						<tr>
							<td>当前状态：</td>
							<td id="status"></td>
						</tr>
						<tr>
							<td>是否收费：</td>
							<td id="ispay"></td>
						</tr>
						<tr>
							<td>联系方式：</td>
							<td id="phone"></td>
						</tr>
						<tr>
							<td>点击量：</td>
							<td id="clicknum"></td>
						</tr>
					</table>
					<div class="row" style="margin-top: 50px;">
						<div class="col-md-12">
							<p id="description" class="bg-info"
								style="text-indent: 2em; line-height: 2.5em; padding: 20px; margin: 0;"></p>
							<br>
							<div class="row">
								<div class="text-right col-md-6 col-md-offset-3" id="sign_up">
									
								</div>
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

	<!-- Scripts -->

	<script src="../js/jquery-1.11.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/skel.min.js"></script>
	<script src="../js/util.js"></script>
	<script src="../js/main.js"></script>

	<script type="text/javascript">
		$.get("${pageContext.request.contextPath}/"+
				"servlet/activityServlet?method=randomActivity",
						function(data, status) {
							if (data!=null) {
								var sign_up1="<a href='${pageContext.request.contextPath }/servlet/joinActivityServlet?method=add_joinActivity&activityid="+data.activityid+"&phone=${user.phone}' class='btn btn-success col-md-12'"+
								"style='height: 40px; font-size: 22px;margin-bottom:15px;'>马上报名</a>";
							
								$("#title").append(data.title);
								var picpath="<img class='img-responsive img-rounded' src="+data.picpath+
								" alt='活动海报' style='width: 420px; height: 277px; margin-left: auto; margin-right: auto;'>";
								$("#picpath").append(picpath);
								if(data.type==1)
									$("#type").append("校内活动");
								else
									$("#type").append("社会活动");
								$("#time").append(data.starttime+" ~ "+data.endtime);
								$("#place").append(data.place);
								$("#limitsize").append(data.limitsize+" 人");
								$("#currentsize").append(data.currentsize+" 人");
								if (data.status == 0
										&& data.currentsize < data.limitsize) {
									$("#status").append("报名中~~");
									$("#sign_up").append(sign_up1);
								} else if (data.status == 1)
									$("#status").append("已开始~~");
								else if (data.status == 2)
									$("#status").append("已结束~~");
								else if (data.status == 3)
									$("#status").append("活动已取消~~");
								else
									$("#status").append("报名人数已达上限");
								
								if(data.ispay==1)
									$("#ispay").append("收费 "+data.money+" 元");
								else
									$("#ispay").append("免费");
								$("#phone").append(data.phone);
								$("#clicknum").append(data.clicknum);
								$("#description").append(data.description);
								
								
							}
						}, "json");
	</script>
</body>
</html>