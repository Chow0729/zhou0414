<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<link rel="stylesheet" href="css/bootstrapStyle/bootstrapStyle.css"
	type="text/css">
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal"
					onsubmit="return false;">
					<div class="form-group" style="margin-top: 15px">
						<div class="col-sm-1" style="padding-top: 0">
							<button class="btn btn-default dropdown-toggle" type="button"
								id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="true">
								<span id="serchFiledSelceted" style="font-weight: 700;">全部</span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1"
								style="min-width: 100px; width: 80px; left: 13px;">
								<li><a href="javascript:void(0);"
									style="padding-left: 15px; font-weight: 700"
									onclick="selectedFiled(this)">角色名称</a></li>
								<li><a href="javascript:void(0);"
									style="padding-left: 15px; font-weight: 700"
									onclick="selectedFiled(this)">角色代码</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="javascript:void(0);"
									style="padding-left: 15px; font-weight: 700"
									onclick="selectedFiled(this)">全部</a></li>
							</ul>
						</div>
						<div class="col-sm-3" style="position: relative;">
							<input type="text" class="form-control" id="txt_search_condition"
								placeholder="请输入查询内容">
						</div>
						<div class="col-sm-3">
							<button type="button" style="margin-left: 50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div>

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
			<button id="btn_reenable" type="button" class="btn btn-default"
				disabled="disabled">
				<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>启用
			</button>
			<button id="btn_dispatch" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>分配资源
			</button>
		</div>
		<table id="table"></table>
	</div>
	
	<!-- 添加角色模态框 开始-->
    <div class="modal fade" id="role_modal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <form class="form-horizontal" id="roleInfoForm">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">添加/修改角色</h4>
                    </div>
                    <div class="modal-body">
                        <div class="panel-body" style="padding-bottom: 0px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">请填写角色信息</div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label for="roleNameInput" class="col-sm-3 control-label">角色名称
                                            <span style="color: red;">*</span>
                                        </label>
                                        <div class="col-sm-7">
                                            <input type="text" name="roleName" class="form-control"
                                                id="roleNameInput" placeholder="请输入角色名称">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="roleCodeInput" class="col-sm-3 control-label">角色代码
                                            <span style="color: red;">*</span>
                                        </label>
                                        <div class="col-sm-7">
                                            <input type="text" name="roleCode" class="form-control"
                                                id="roleCodeInput" placeholder="请输入角色代码">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="remarkInput" class="col-sm-3 control-label"
                                            style="padding-right: 25px;">备注</label>
                                        <div class="col-sm-7">
                                            <input type="text" name="remark" class="form-control"
                                                id="remarkInput" placeholder="请输入备注">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="isenableInput isenableInput0"
                                            class="col-sm-3 control-label">是否可用 <span
                                            style="color: red;">*</span>
                                        </label>
                                        <div class="col-sm-7" style="padding-top: 5px">
                                            <input name="isenable" id="isenableInput" type="radio"
                                                value="1">是&nbsp;&nbsp; <input name="isenable"
                                                type="radio" id="isenableInput0" value="0">否
                                        </div>
                                    </div>
                                    <input type="hidden" id="createdTimeInput" name="createdTime"/>
                                    <input type="hidden" id="roleIdInput" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                        </button>
                        <button type="submit" class="btn btn-primary" id="btn_confirm"
                            name="submit">
                            <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确认
                        </button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal -->
        </form>
    </div>
	<!-- 添加角色模态框结束-->
	
	<!-- 分配资源模态框 开始-->
    <div class="modal fade" id="dispatchModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">分配资源</h4>
                </div>
                <div class="modal-body">
                    <div class="left" style="margin-left: 50px;">
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="btn_submitRes">确认</button>
                    <button type="button" class="btn btn-success" data-dismiss="modal"
                        id="btn_closeModal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>
	<!-- 分配资源模态框 结束-->
	
	<script type="text/javascript" src="js/auth/role_manage.js"></script>
	<script type="text/javascript" src="js/auth/plugins/component.js"></script>
	<script type="text/javascript" src="js/plugins/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.ztree.exedit.js"></script>
</body>
</html>