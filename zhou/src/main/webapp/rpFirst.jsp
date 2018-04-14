<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>找回密码</title>
<link href="css/loginAndRegister.css" rel="stylesheet">
<style type="text/css">


</style>
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
                            <li class="active">
                                <div>
                                    <i>1</i>
                                    <p>填写帐号</p>
                                </div>
                                <i></i>
                            </li>
                            <li >
                                <div>
                                    <i>2</i>
                                    <p>身份验证</p>
                                </div>
                                <i></i>
                            </li>
                            <li>
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
			<form id="input_account_form" action="${pageContext.request.contextPath}/identityVerification" method="post" onsubmit="return checkPost();">
				<div class="form-group">
					<label for="account">账号</label> <input type="text" name="account" id="account"
						class="form-control" placeholder="用户名或公司邮箱"></input>
				</div>
				<div id="verificationCode" class="form-group">
					<label for="checkcode">验证码</label> 
					<input type="text" name="checkcode" class="form-control"  placeholder="请输入验证码"></input> 
					<small id="verificationCodeVd"></small>
				</div>
				<div class="form-group">
					<img src="${pageContext.request.contextPath}/verificationPicture"
						alt="验证码" id="image" /> 
						<a href="javascript:reload();" style="margin: 10px 10px"><label>换一张</label></a>
				</div>
				
				<div class="form-group">
					<input id="nextSubBtn" type="submit" class="btn btn-success" value="下一步 " />
				</div>
			</form>
		</div>

	</div>
	<hr class="footer-fixed">
	<footer>
		</a> <br> Copyright © 2018 <a href="http://www.longyinju.com"
			title="SwanGarden">SwanGarden</a> Co., Ltd. All rights reserved.
	</footer>

	<script type="text/javascript">
		$(function() {
			//初始化验证插件
			formValidator();
			if ("${param.account }" != "") {
				$("#account").val("${param.account }");
			}
			
			$("#verificationCode > input").bind("input propertychange",function(){
				$("#verificationCodeVd").html("");
			});
			
			

			// 阻止默认事件提交，防止重复提交
		      /* $('#input_account_form').bootstrapValidator().on('success.form.bv', function(e) {
		          // 阻止默认事件提交
		        	e.preventDefault();
		      });  */
		});

		function reload() {
			$("#image").attr(
					"src",
					"${pageContext.request.contextPath}/verificationPicture?date="
							+ new Date().getTime());
			$("#verificationCode > input").val(""); // 将验证码清空
		}

		var check_count = 0;
		var check_result = false;
		function checkPost() {	
			if (check_count == 1) {
				check_count++;
				return check_result;
			}			
			$.ajax({
						url : "${pageContext.request.contextPath}/validateCode?sessionName=verificationCode",
						type : "post",
						dataType : "json",
						async : false,
						data : {
							checkCode : this.input_account_form.checkcode.value,
						},
						success : function(data) {
							if (!data) {
								$("#verificationCode").attr("class", "form-group has-feedback has-error");
								$("#verificationCode > i.form-control-feedback.glyphicon.glyphicon-ok").attr("class", "form-control-feedback glyphicon glyphicon-remove");
								$("#verificationCodeVd").html("<small class='help-block' data-bv-validator='remote' data-bv-for='checkCode' data-bv-result='INVALID'>验证码输入错误</small>");
							}
							reload();
							check_result = data;
							check_count++;
						}
					});
			return check_result;
		}

		function formValidator() {
			$('#input_account_form').bootstrapValidator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					account : {
						message : '账号验证失败',
						validators : {
							notEmpty : {
								message : '账号不能为空'
							}
						}
					},
					checkcode : {
						message : '验证码验证失败',
						validators : {
							notEmpty : {
								message : '验证码不能为空'
							},
						}
					},
				}
			});
		}
	</script>
</body>
</html>