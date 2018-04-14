<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色分配</title>
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
									onclick="selectedFiled(this)">用户名称</a></li>
								<li><a href="javascript:void(0);"
									style="padding-left: 15px; font-weight: 700"
									onclick="selectedFiled(this)">部门名称</a></li>
								<li><a href="javascript:void(0);"
									style="padding-left: 15px; font-weight: 700"
									onclick="selectedFiled(this)">角色名称</a></li>
								<li><a href="javascript:void(0);"
									style="padding-left: 15px; font-weight: 700"
									onclick="selectedFiled(this)">职位</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="javascript:void(0);"
									style="padding-left: 15px; font-weight: 700"
									onclick="selectedFiled(this)">全部</a></li>
							</ul>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="txt_search_condition"
								placeholder="请输入查询内容">
						</div>
						<!-- <label class="control-label col-sm-1" for="txt_search_isRole">角色状态</label> -->
						<!-- <div class="col-sm-2">
							<select class="form-control" id="txt_search_isRole"
								onchange="isEnable()">
								<option value="1" selected>已有角色</option>
								<option value="0">没有角色</option>
							</select>
						</div> -->
						<div class="col-sm-3">
							<button type="button" style="margin-left: 50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="toolbar" class="btn-group">
			<button id="btn_addMoreUserRole" type="button"
				class="btn btn-default">
				<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>批量修改角色
			</button>
		</div>
		<table id="table"></table>
	</div>
	
	<!-- 单个分配角色的模态框 开始 -->
	<div class="modal fade" id="role_assignment_modal" tabindex="-1"
        role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">角色分配</h4>
                </div>
                <div class="modal-body" style="padding-bottom: 35px;">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="role_grant_select"
                            style="padding-top: 10px;">角色</label>
                        <div class="col-sm-4">
                            <select id="role_grant_select" class="form-control">
                            </select>
                        </div>
                    </div>
                </div>
                <!-- 隐藏域 -->
                <input type="hidden" id="userid_select"> 
                <input type="hidden" id="old_roleid_select">
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="btn_confirm">确定</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- 单个分配角色的模态框 结束 -->
	
	<!-- 批量分配模态框 开始 -->
    <div class="modal fade" id="role_assignment_more_modal" tabindex="-1"
        role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">角色分配</h4>
                </div>
                <div class="modal-body" style="padding-bottom: 35px;">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="role_select"
                            style="padding-top: 10px;">角色</label>
                        <div class="col-sm-4">
                            <select id="role_select" class="form-control">
                            </select>
                        </div>
                    </div>
                </div>
                <!-- 隐藏域 -->
                <input type="hidden" id="hidden_userIds"/>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="btn_more_conform">确定</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
	<!-- 批量分配模态框 结束 -->
	
<script type="text/javascript" src="js/auth/user_role.js"></script>
<script type="text/javascript" src="js/auth/plugins/component.js"></script>
</body>
</html>