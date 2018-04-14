<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored ="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	response.setHeader("Cache-Control","no-store");   
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader("Expires",0);
	String ip=null;
	if (request.getHeader("x-forwarded-for") == null) { 
		ip= request.getRemoteAddr(); 
	}else{ 
		ip= request.getHeader("x-forwarded-for"); 
	}
	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = request.getHeader("Proxy-Client-IP"); 
	} 
	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = request.getHeader("WL-Proxy-Client-IP"); 
	} 
	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = request.getRemoteAddr(); 
	} 
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link href="bootstrap-3.3.7/css/bootstrap-switch.css" rel="stylesheet">
	<link href="css/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap-3.3.7/css/toastr.min.css" rel="stylesheet" />
    <link href="bootstrap-3.3.7/css/bootstrap-table.css" rel="stylesheet">
    <link href="bootstrap-3.3.7/css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="bootstrap-3.3.7/css/bootstrap-datepicker.css" rel="stylesheet">
    <link href="icon/grad.ico" rel="shortcut icon" type="image/x-icon">  
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7/js/toastr.min.js"></script>
    <script src="bootstrap-3.3.7/js/bootstrap-table.js"></script>
    <script src="bootstrap-3.3.7/js/bootstrap-table-zh-CN.js"></script>
    <script src="bootstrap-3.3.7/js/bootstrap-table-export.js"></script>
	<script src="bootstrap-3.3.7/js/tableExport.js"></script>
	<script src="bootstrap-3.3.7/js/bootstrapValidator.min.js"></script>
	<script src="bootstrap-3.3.7/js/bootstrap-datepicker.js"></script>	
	<script src="bootstrap-3.3.7/js/notify.min.js"></script>
	<script src="bootstrap-3.3.7/js/bootstrap-switch.js"></script>
</head>
</html>