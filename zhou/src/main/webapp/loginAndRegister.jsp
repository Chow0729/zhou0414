<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>注册/登录</title>
<link href="css/loginAndRegister.css" rel="stylesheet">
</head>
<body>
	<header>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-sm-4 pull-right">
				<div class="tabbable">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#login-pane" data-toggle="tab">登录</a></li>
						<li><a href="#register-pane" data-toggle="tab">注册</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="login-pane" role="tabpanel">
							<!-- 登录表单 -->
							<div class="login_form">
								<form id="login_form" action="${pageContext.request.contextPath}/login.do" method="post">
									<!-- 用戶名输入框 -->
									<div id="usernameStatus" class="form-group">
										<i class="myIcon"><span class="glyphicon glyphicon-user"></span></i>
										<label class="forInput" for="username">用户名或邮箱</label> <input
											type="text" class="form-control" id="login_username"
											name="username" placeholder="Username" autofocus="autofocus"
											aria-describedby="helpUserName userNameInputErrorStatus">
										<span id="userNameErrorInputIcon" aria-hidden="true"></span> <span
											id="userNameInputErrorStatus" class="sr-only">(error)</span>
										<span id="helpUserName" class="help-block"></span>
									</div>
									<!-- 密码输入框 -->
									<div id="passwordStatus" class="form-group">
										<i class="myIcon"><span class="glyphicon glyphicon-lock"></span></i>
										<label class="forInput" for="password">密码</label> <input
											type="password" class="form-control" id="login_password"
											name="password" placeholder="Password" maxlength="16"
											aria-describedby="helpPassword passwordInputErrorStatus">
										<span id="passwordErrorInputIcon" aria-hidden="true"></span> <span
											id="passwordInputErrorStatus" class="sr-only">(error)</span>
										<span id="helpPassword" class="help-block"></span>
									</div>
									<!-- checkBox -->
									<div class="form-group">
										<div class="row">
											<div class="col-sm-3">
												<label> <input name="remember" id="remember"
													type="checkbox"> 记住我
												</label>
											</div>
											<div class="col-sm-4 pull-right">
												<a href="${pageContext.request.contextPath}/rpFirst.jsp">忘记密码？</a>
											</div>
										</div>
										<hr />
									</div>
									<div class="form-group">
										<input type="submit" class="btn btn-primary" value="登录 " />
									</div>
								</form>
							</div>
						</div>
						<div class="tab-pane" id="register-pane" role="tabpanel">
							<!-- 注册表单 -->
							<div class="register_form">
								<form id="register_form" action="./register.do" method="post"
									onsubmit="return CheckPost();">
									<!-- 邮箱输入框 -->
									<div id="emailValidate" class="form-group">
										<i class="myIcon glyphicon glyphicon-envelope"></i> <label
											class="forInput" for="email">邮箱<span>*</span></label> 	
										<input type="text" class="form-control" id="email"
                                            name="mail" aria-describedby="email-addon">
										<!-- <div class="input-group">
											<input type="text" class="form-control required" id="email"
											name="mail" aria-describedby="email-addon"> 
										    <span class="input-group-addon" id="email-addon">@tempetek.com</span>
										</div> -->
										<small id="emailVd"></small>
									</div>
									<!-- 密码输入框 -->
									<div class="form-group">
										<i class="myIcon glyphicon glyphicon-lock"></i> <label
											class="forInput" for="register_password">密码<span>*</span></label>
										<input type="password" class="form-control"
											id="register_password" name="password" maxlength="16">
									</div>
									<!-- 确认密码输入框 -->
									<div class="form-group">
										<i class="myIcon glyphicon glyphicon-lock"></i> <label
											class="forInput" for="rpassword">确认密码<span>*</span></label> <input
											class="form-control" type="password" id="rpassword"
											name="rpassword" maxlength="16" />
									</div>
									<!-- 用戶名输入框 -->
									<div id="userNameValidate" class="form-group">
										<i class="myIcon glyphicon glyphicon-user"></i> <label
											class="forInput" for="register_username">用户名<span>*</span></label>
										<input type="text" class="form-control" id="register_username"
											name="userName"> <small id="userNameVd"></small>
									</div>
									<!-- 姓名输入框 -->
									<div class="form-group">
										<i class="myIcon glyphicon glyphicon-user"></i> <label
											class="forInput" for="realName">真实姓名<span>*</span></label> <input
											type="text" class="form-control" id="realName"
											name="realName">
									</div>
									<div class="form-group">
										<input type="submit" class="btn btn-success" value="注册 " />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-7 brand-holder pull-left">
				<h1>
				坦佩 OA系统
				</h1>
				<h3>轻松办公&nbsp;&nbsp;高效工作</h3>
				<p>
				记录每一天的工作成果和经验分享，可透视整个组织每位成员的工作状态和成效，洞察每件事项的进度。
				每周、每月、每年达成了哪些工作成效、总结成长的心得，并简明扼要的汇报下一步的总体计划。
				随时记录需要做的事情或备忘想法，定期安排哪一天进行，及时与其他同事共同协作并与上下级指导沟通。
				</p>
			</div>
		</div>
	</div>
	<hr class="footer-fixed">
	<footer>
		<a href="http://www.tempetek.com">
			<!-- <img src="img/logo.png" alt="TempeTek" width="120"> -->
		</a>
		<br>
		Copyright © 2018 
		<a href="http://www.longyinju.cn" title="SwanGarden">SwanGarden</a>
		 Co., Ltd. All rights reserved.
	</footer>
	<script>
		$(function() {
			//跳出frame
			if (window != top) {
				top.location.href = location.href;
			}
			var error = decodeURI("${param.error }");
			//<!-- 判断是否记住登录信息 -->
			if ("${cookie.username.value }" != null) {
				$("#login_username").val("${cookie.username.value }");
				$("#login_password").val("${cookie.password.value }");
			}
			if ("${cookie.remember.value }" == "on") {
				$("#remember").prop("checked", true);
			} else {
				$("#remember").prop("checked", false);
			}
			
			console.log("fsdcjklhskdlhf");
			console.log(error);
			
			//<!-- 登录失败情况 -->
			if (error != "") {
				if (error == "密码错误") {
					$("#username").val("${username }");
					$("#helpPassword").html(error);
					$("#passwordStatus").addClass("has-error has-feedback");
					$("#passwordErrorInputIcon").addClass(
							"glyphicon glyphicon-remove form-control-feedback");
				} else {
					$("#helpUserName").html(error);
					$("#usernameStatus").addClass("has-error has-feedback");
					$("#userNameErrorInputIcon").addClass(
							"glyphicon glyphicon-remove form-control-feedback");
				}
			} else {
				console.log("error" + error+ "error");
			}
			//初始化验证插件
			formValidator();
		});
		//绑定a标签点击事件，销毁验证重新 初始化
		$("a[href='#\login-pane'],a[href='#\register-pane']").bind(
				'click',
				function() {
					$("#register_form").data('bootstrapValidator').destroy();
					$("#register_form").data('bootstrapValidator', null);
					formValidator();
					$(':input', '#register_form').not(
							':button,:submit,:reset,:hidden').val('');
				});
		//初始化验证插件方法
		function formValidator() {
			$('#register_form').bootstrapValidator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					userName : {
						message : '用户名验证失败',
						validators : {
							notEmpty : {
								message : '用户名不能为空'
							},
							stringLength : {
								min : 4,
								max : 18,
								message : '用户名长度必须在4到18位之间'
							},
							regexp : {
								regexp : /^[a-zA-Z0-9_]+$/,
								message : '用户名只能包含大写、小写、数字和下划线'
							}
						}
					},
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
					rpassword : {
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
					},
					realName : {
						message : '真实姓名验证失败',
						validators : {
							notEmpty : {
								message : '姓名不能为空'
							}
						}
					},
					mail : {
						validators : {
							notEmpty : {
								message : '邮箱不能为空'
							},
							emailAddress: {
			                    message: '邮箱地址格式有误'
			                }
						}
					}
				}
			});
		}

		//用户名和邮箱是否已注册校验
		function CheckPost() {
			var register = false;
			$
					.ajax({
						url : "./registerValidate",
						type : "post",
						dataType : "json",
						async : false,   //同步
						data : {
							"userName" : this.register_form.userName.value,
							"email" : this.register_form.mail.value,
						},
						success : function(result) {
							if (result.message != "用户名已存在"
									&& result.message != "") {
								$("#emailValidate").attr("class",
										"form-group has-feedback has-error");
								$(
										"#emailValidate > i.form-control-feedback.glyphicon.glyphicon-ok")
										.attr("class",
												"form-control-feedback glyphicon glyphicon-remove");
								$("#emailVd")
										.html(
												"<small class='help-block' data-bv-validator='remote' data-bv-for='mail' data-bv-result='VALID'>"
														+ result.message
														+ "</small>");
							} else if (result.message == "用户名已存在") {
								$("#userNameValidate").attr("class",
										"form-group has-feedback has-error");
								$(
										"#userNameValidate > i.form-control-feedback.glyphicon.glyphicon-ok")
										.attr("class",
												"form-control-feedback glyphicon glyphicon-remove");
								$("#userNameVd")
										.html(
												"<small class='help-block' data-bv-validator='remote' data-bv-for='mail' data-bv-result='VALID'>用户名已存在</small>");
							} else {
								register = true;
							}
						}
					});
			return register;
		}
	</script>
</body>
</html>