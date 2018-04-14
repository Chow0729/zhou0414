<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>访问失败</title>
<link href="css/loginAndRegister.css" rel="stylesheet">
</head>
<body>
	<header>
		<a href="http://www.tempetek.com" class="header_logo"> 公司主页 </a>
		<div class="header_link">
			<a href="${pageContext.request.contextPath}">登录</a>&nbsp;|&nbsp; <a
				href="http://office.tempetek.com:8092">jira</a>&nbsp;|&nbsp; <a
				href="http://office.tempetek.com:8090">wiki</a>&nbsp;|&nbsp; <a
				href="http://exmail.qq.com/cgi-bin/loginpage?t=logindomain&param=@tempetek.com">企业邮箱</a>
		</div>
	</header>
	<div class="container">
		<p class="val-ok-tips">对不起，您没有资格访问此资源，如有疑问请联系管理员！</p>
		<hr />
		<p class="val-ok-tips">
			<span id="countDown">10</span>秒后返回主界面，<a
				href="${pageContext.request.contextPath}/main">或点此返回</a>
		</p>
	</div>
	<hr class="footer-fixed">
	<footer>
		<a href="http://www.tempetek.com"> <img src="img/logo.png"
			alt="TempeTek" width="120">
		</a> <br> Copyright © 2017 <a href="http://www.tempetek.com"
			title="Tempetek">TempeTek</a> Co., Ltd. All rights reserved.
	</footer>

	<script type="text/javascript">
		$(function() {
			if (window != top) {
                top.location.href = location.href;
            }
			
			countDown();
		});
		function countDown() {
			var num = $("#countDown").text();
			if (num == 0) {
				window.location.href = "${pageContext.request.contextPath}/main";
				return;
			}
			num--;
			$("#countDown").text(num);
			setTimeout("countDown ()", 1000);
		}
	</script>
</body>
</html>