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
                            <li class="active">
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
			<form id="input_account_form" action="${pageContext.request.contextPath}/setNewPassword?email=${email }" method="post" onsubmit="return checkPost();">
				<div class="form-group">
					<label for="email">邮箱</label> <input id="email" type="email" name="email"
						class="form-control" value="" readonly></input>
					<input id="getCheckCodeBtn" type="button" onclick="getValidateCode()" class="btn btn-default" style="margin-top:10px" value="获取验证码" />
				</div>
				<div id="verificationCode" class="form-group">
					<label for="checkcode">验证码</label> 
					<input id="inputCode" type="text" name="checkcode" class="form-control" placeholder="请输入4位数验证码"></input> 
					<i class="" data-bv-icon-for="checkcode"></i>
					<small id="verificationCodeVd"></small>
				</div>
				<div class="form-group">
					<input type="submit" style="width:49%" class="btn btn-success" value="下一步" />
					<input type="button" style="width:49%" class="btn btn-default" value="返回" onClick="goBack();" />
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
		//提示框toastr的显示控制
		toastr.options = {
		    "positionClass" : "toast-top-center",
		    "timeOut" : "1500"
		}
		
		if ("${email }" == "") {
			$("#email").attr("value", "邮箱不存在,请返回确认");
		} else {
			$("#email").attr("value", "${email }");
		}
		
		$("#verificationCode > input").bind("input propertychange",function(){
			$("#verificationCode").attr("class", "form-group");
			$("#verificationCode > i").attr("class", "");
			$("#verificationCodeVd").html("");
		});
	});
		
	function getValidateCode(){	
		if ("${email }" == "") {
			oa_alert("邮箱不存在！请输入正确的账号", "error");
		} else {
			$.ajax({
				url : "${pageContext.request.contextPath}/getValidateCode",
				type : "post",
				dataType : "json",
				data : {
					email : this.input_account_form.email.value,
				},
				success : function(data) {
					if (data == 0) {
						oa_alert("账号未激活无法更改密码，请前去邮箱激活！", "error");
					} else {
						oa_alert("邮件已经发送，请注意查收！", "success");
						$("#getCheckCodeBtn").attr("disabled","disabled");
						$("#getCheckCodeBtn").addClass("active");
						reGetCheckCode(60);
					}			
				}				
			});				
		}
	}
	
	function reGetCheckCode(num) {
		if (num == 0) {		
			toastr.warning("请查看您的邮箱是否正确或联系管理员");
			$("#getCheckCodeBtn").removeClass("active");
			$("#getCheckCodeBtn").removeAttr("disabled"); 
			$("#getCheckCodeBtn").attr("value", "重获验证码");
			return;
		}
		$("#getCheckCodeBtn").attr("value", num + "s后可重新获取");
		num--;		
		setTimeout("reGetCheckCode (" + num + ")",1000);
	}

	
	function goBack(){
		window.location.href = "${pageContext.request.contextPath}/rpFirst.jsp?account=${account }"
	}
		
	function oa_alert(message, style) {
		$.notify(message, {
			globalPosition : 'top center',
			arrowShow : true,
			className : style,
			autoHide : true
		});
	}
	
	function checkPost() {
		
		var result = false;
		var inputCode = $("#inputCode").val();
		if (inputCode == "") {
			$("#verificationCode").attr("class", "form-group has-feedback has-error");
			$("#verificationCode > i").attr("class", "form-control-feedback glyphicon glyphicon-remove");
			$("#verificationCodeVd").html("<small class='help-block' data-bv-validator='remote' data-bv-for='checkCode' data-bv-result='INVALID'>验证码不能为空</small>");
			return false;
		}
		$.ajax({
					url : "${pageContext.request.contextPath}/validateCode?sessionName=checkCode",
					type : "post",
					dataType : "json",
					async : false,
					data : {
						checkCode : this.input_account_form.checkcode.value,
					},
					success : function(data) {
						console.log(data);
						if (!data) {
							$("#verificationCode").attr("class", "form-group has-feedback has-error");
							$("#verificationCode > i").attr("class", "form-control-feedback glyphicon glyphicon-remove");
							$("#verificationCodeVd").html("<small class='help-block' data-bv-validator='remote' data-bv-for='checkCode' data-bv-result='INVALID'>验证码错误</small>");
						}
						result = data;
					}
				});
		return result;
	}
	</script>
</body>
</html>