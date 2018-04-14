<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>找回密码</title>
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
	<div class="container rp">
		<div class="section section-subnav">
	                    <div class="section_subnav">
	                        <ul class="subnav_process">
	                            <li class="passed">
	                                <div>
	                                    <i>1</i>
	                                    <p>填写帐号</p>
	                                </div>
	                                <i></i>
	                            </li>
	                            <li class="passed">
	                                <div>
	                                    <i>2</i>
	                                    <p>身份验证</p>
	                                </div>
	                                <i></i>
	                            </li>
	                            <li class="passed">
	                                <div>
	                                    <i>3</i>
	                                    <p>设置新密码</p>
	                                </div>
	                                <i></i>
	                            </li>
	                            <li class="last passed">
	                                <div>
	                                    <i>4</i>
	                                    <p>完成</p>
	                                </div>
	                                <i></i>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	                
		<div class="val-ok">
			<c:if test="${success }">
				<p><i></i></p>
			    <p class="val-ok-tips">恭喜您，登录密码找回成功！</p>
			    <p class="val-ok-tips2"></p>
			    <div class="val-ok-btn">
	    			<input type="button" onclick="goMain()" style="width:180px" class="btn btn-success btn-lg" value="确认" />
	    		</div>
			</c:if>			
			<c:if test="${!success }">
				<p class="val-ok-tips">很抱歉，密码更改失败，请重试！</p>
				<div class="val-ok-btn">
	    			<input type="button" onclick="goBack()" style="width:180px" class="btn btn-danger btn-lg" value="返回" />
	    		</div>
			</c:if>	    
		</div>
	</div>
	<hr class="footer-fixed">
	<footer>
		<a href="http://www.tempetek.com"> <img src="img/logo.png"
			alt="TempeTek" width="120">
		</a> <br> Copyright © 2017 <a href="http://www.tempetek.com"
			title="Tempetek">TempeTek</a> Co., Ltd. All rights reserved.
	</footer>

	<script type="text/javascript">
	
		function goMain(){
			window.location.href = "${pageContext.request.contextPath }"
		}
		
		function goBack(){
			window.location.href = "${pageContext.request.contextPath }/rpFirst.jsp"
		}
	
	</script>
</body>
</html>