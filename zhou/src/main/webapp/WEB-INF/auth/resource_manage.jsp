<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源管理</title>
<script src="bootstrap-3.3.7/extension/boorstrap.treegrid.extension.js"></script>
</head>
<body>

	<div class="panel-body" style="padding-bottom: 0px;">
		<div id="toolbar" class="btn-group">
			<button id="btn_add" type="button" class="btn btn-success"
				onclick="addResource1()">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
		</div>
		<table id="tb"></table>
	</div>
	<!-- 添加资源/修改模态框（Modal） -->
	<div class="modal fade" id="resource_modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form class="form-horizontal" id="resourceInfoForm"
			enctype="multipart/form-data">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加资源</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="txt_resourceName" class="col-sm-3 control-label">资源名称
								<span style="color: red;">*</span>
							</label>
							<div class="col-sm-7">
								<input type="text" name="resourceName" class="form-control"
									id="txt_resourceName" placeholder="请输入资源名称">
							</div>
						</div>
						<div class="form-group">
							<label for="txt_resourceUrl" class="col-sm-3 control-label"
								style="padding-right: 25px;">资源链接</label>
							<div class="col-sm-7">
								<input type="text" name="resourceUrl" class="form-control"
									id="txt_resourceUrl" placeholder="请输入资源链接">
							</div>
						</div>
						<div class="form-group">
							<label for="txt_location" class="col-sm-3 control-label">显示位置
								<span style="color: red;">*</span>
							</label>
							<div class="col-sm-7">
								<input type="text" name="location" class="form-control"
									id="txt_location" placeholder="请输入显示的位置">
							</div>
						</div>
						<div class="form-group">
							<label for="txt_icon" class="col-sm-3 control-label"
								style="padding-right: 25px;">资源图标</label>
							<div class="col-sm-7" style="position: relative;">
								<input type="text" name="icon" class="form-control"
									id="txt_icon" placeholder="请选择资源图标"> <a
									class="btn btn-link" target="view_window"
									href="http://fontawesome.dashgame.com/"
									style="padding-left: 12px; position: absolute; left: 470px; top: 1px;">
									<i class="glyphicon glyphicon-search"></i>
								</a>
							</div>
						</div>
						<div class="form-group">
							<label for="txt_isenable" class="col-sm-3 control-label">启用状态
								<span style="color: red;">*</span>
							</label>
							<div class="col-sm-7">
								<input name="isenable" id="txt_isenable1" type="radio" value="1">启用&nbsp;&nbsp;
								<input name="isenable" id="txt_isenable0" type="radio" value="0">禁用
							</div>
						</div>
						<div class="form-group">
							<label for="txt_remark" class="col-sm-3 control-label"
								style="padding-right: 25px;">备注</label>
							<div class="col-sm-7">
								<input type="text" name="remark" class="form-control"
									id="txt_remark" placeholder="请输入备注">
							</div>
						</div>
						<div class="form-group">
							<label for="txt_style" class="col-sm-3 control-label"
								style="padding-right: 25px;">菜单样式</label>
							<div class="col-sm-7">
								<input type="text" name="style" class="form-control"
									id="txt_style" placeholder="请输入菜单样式">
							</div>
						</div>
						<!-- 隐藏域 -->
						<input type="hidden" id="txt_pid" name="pid"> <input
							type="hidden" id="txt_resourceId" name="resourceId">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary"
							onclick="addOrUpdate()" name="submit">提交</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<script src="js/auth/resource_manage.js"></script>
    <script type="text/javascript" src="js/auth/plugins/component.js"></script>
</body>
</html>