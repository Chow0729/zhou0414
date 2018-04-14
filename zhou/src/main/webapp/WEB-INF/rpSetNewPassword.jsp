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
		<a href="http://www.zcmu.edu.cn" class="header_logo"> 公司主页 </a>
		<div class="header_link">
			<a href="${pageContext.request.contextPath}">登录</a>&nbsp;|&nbsp;
			<a href="http://exmail.qq.com/cgi-bin/loginpage?t=logindomain&param=@tempetek.com">企业邮箱</a>
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
                            <li class="active">
                                <div>
                                    <i>3</i>
                                    <p>设置新密码</p>
                                </div>
                                <i></i>
                            </li>
                            <li class="last">
                                <div>
                                    <i>4</i>
                                    <p>完成</p>
                                </div>
                                <i></i>
                            </li>
                        </ul>
                    </div>
                </div>
		<div class="rp_form">
			<form id="set_newPassword_form" action="${pageContext.request.contextPath}/updatePassword?email=${param.email }" method="post">
				<div class="form-group">
					<label for="password">新密码<span style="color:red">*</span>：</label> <input id="password" type="password" name="password"
						class="form-control"></input>
				</div>
				<div class="form-group">
					<label for="rPassword">确认新密码<span style="color:red">*</span>：</label> <input id="rPassword" type="password" name="rPassword"
						class="form-control"></input>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-success"  value="确认" />
				</div>
			</form>
		</div>
	</div>
	<hr class="footer-fixed">
	<footer>
	<br> Copyright © 2018 <a href="http://www.longyinju.cn"
			title="SwanGarden">SwanGarden</a> Co., Ltd. All rights reserved.
	</footer>

	<script type="text/javascript">
	
	$(function(){
		formValidator();
	});
	
	function formValidator() {
		$('#set_newPassword_form').bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				password : {
					message : '密码验证失败',
					validators : {
						notEmpty : {
							message : '密码不能为空'
						},
						stringLength : {
							min : 6,
							max : 18,
							message : '密码长度必须在6到18位之间'
						},
						regexp : {
							regexp : /^[^\u4e00-\u9fa5]{0,}$/,
							message : '只能输入除中文外的任意字符'
						}
					}
				},
				rPassword : {
					message : '密码确认验证失败',
					validators : {
						notEmpty : {
							message : '密码确认不能为空'
						},
						identical : {
							field : 'password',
							message : '两次密码不相同'
						}
					}
				}
			}
		});
	}
	
	</script>
</body>
</html>