<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>注册失败</title>
<link href="css/loginAndRegister.css" rel="stylesheet">
</head>
<body>
	<header>
		<a href="http://www.zcmu.edu.cn" class="header_logo"> 公司主页 </a>
		<div class="header_link">
			<a href="${pageContext.request.contextPath}">登录</a>&nbsp;|&nbsp; 
				<a href="http://exmail.qq.com/cgi-bin/loginpage?t=logindomain&param=@tempetek.com">企业邮箱</a>
		</div>
	</header>
	<div class="container">
		<c:if test="${isAssignRolesError }">
			<p class="val-ok-tips">注册失败！错误信息：${error } </p>
			<hr/>
			<p class="val-ok-tips"><span id="countDown">10</span>秒后返回登录界面，<a href="${pageContext.request.contextPath}">或点此返回</a></p>
		</c:if>
		<c:if test="${!isAssignRolesError }">
			<p class="val-ok-tips">邮件发送失败！错误信息：${error } </p>
			<hr/>
			<p class="val-ok-tips"><span id="countDown">10</span>秒后返回登录界面，<a href="${pageContext.request.contextPath}">或点此返回</a></p>
		</c:if>
	</div>
	<hr class="footer-fixed">
	<footer>
		</a> <br> Copyright © 2018 <a href="http://www.longyinju.cn"
			title="SwanGarden">SwanGarden</a> Co., Ltd. All rights reserved.
	</footer>

	<script type="text/javascript">
	$(function () {
		countDown();
	});
	function countDown() {
		var num = $("#countDown").text();
		if (num == 0) {
			 window.location.href = "${pageContext.request.contextPath}";
			 return;
		}
		num--;
		$("#countDown").text(num);
		setTimeout("countDown ()",1000);
	}
	</script>
</body>
</html>