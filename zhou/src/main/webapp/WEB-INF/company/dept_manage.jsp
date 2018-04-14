<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门管理</title>
</head>
<body>

	<div class="panel-body" style="padding-bottom: 0px;">

		<div id="toolbar" class="btn-group">
			<button id="btn_add" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
			<button id="btn_edit" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button>
			<button id="btn_delete" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>
		</div>

		<table id="tb_departments"></table>


		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel"></h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="deptInfo">
							
							<div class="form-group">
								<label for="inputDepNo" class="col-sm-3 control-label">部门编号</label>
								<div class="col-sm-7">
									<input name="depNo" type="text" class="form-control"
										id="inputDepNo" placeholder="部门编号">
								</div>
								<span style="color: red;">*</span>
							</div>
							<div class="form-group">
								<label for="inputDepName" class="col-sm-3 control-label">部门名称</label>
								<div class="col-sm-7">
									<input name="depName" type="text" class="form-control"
										id="inputDepName" placeholder="部门名称">
								</div>
								<span style="color: red;">*</span>
							</div>
							<div class="form-group">
								<label for="inputRemark" class="col-sm-3 control-label">备注</label>
								<div class="col-sm-7">
									<textarea name="remark" class="form-control" rows="3"
										placeholder="备注" id="inputRemark"></textarea>
								</div>
							</div>
							<!-- 隐藏域 -->
							<input type="hidden" name="depId" />
							<input type="hidden" name="createdTime">
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary">提交更改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

	</div>

	<!-- 信息删除确认 -->
	<div class="modal fade" id="delcfmModel">
		<div class="modal-dialog">
			<div class="modal-content message_align">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">提示信息</h4>
				</div>
				<div class="modal-body">
					<p>您确认要删除吗？</p>
				</div>
				<div class="modal-footer">
					<input type="hidden" id="url" />
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<a onclick="delUrlSubmit()" class="btn btn-success"
						data-dismiss="modal">确定</a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>


<script type="text/javascript" src="js/company/dept_manage.js"></script>
</body>
</html>