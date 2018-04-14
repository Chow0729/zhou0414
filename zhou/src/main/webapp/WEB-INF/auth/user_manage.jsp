<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal">
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
									onclick="selectedFiled(this)">员工名称</a></li>
								<li><a href="javascript:void(0);"
									style="padding-left: 15px; font-weight: 700"
									onclick="selectedFiled(this)">部门名称</a></li>
								<li><a href="javascript:void(0);"
									style="padding-left: 15px; font-weight: 700"
									onclick="selectedFiled(this)">真实姓名</a></li>
								<li><a href="javascript:void(0);"
									style="padding-left: 15px; font-weight: 700"
									onclick="selectedFiled(this)">电话</a></li>
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
								placeholder="请输入搜索关键词">
						</div>
						<label class="control-label col-sm-1" for="txt_search_userinfo">状态</label>
						<div class="col-sm-2">
							<select class="form-control" id="txt_isenable"
								onchange="isEnable()">
								<option value="1" selected>启用</option>
								<option value="0">禁用</option>
							</select>
						</div>
						<div class="col-sm-3" style="text-align: left;">
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
			<button id="btn_delete" type="button" class="btn btn-default"
				disabled>
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>彻底删除
			</button>
			<button id="btn_disable" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>停用
			</button>
			<button id="btn_enable" type="button" class="btn btn-default"
				disabled>
				<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>启用
			</button>
		</div>
		<table id="table"></table>
	</div>
	
	<!-- 添加用户模态框（Modal） -->
    <div class="modal fade" id="user_modal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <form class="form-horizontal" id="userInfo"
            enctype="multipart/form-data">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                        <!-- tab标签导航 -->
                        <ul id="myTaba" class="nav nav-tabs">
                        <li class="active"><a href="#addBasic" data-toggle="tab"
                            class="modal-title" id="myModalLabel"> 添加用户基本信息</a></li>
                        <li><a href="#addOthers" data-toggle="tab" class="modal-title" id="otherTab">添加用户部门信息</a></li>
                    </ul>
                    </div>
                    <div class="modal-body">
                        <!-- 用户基本信息/用户学历信息tab标签切换 -->
                        <div id="myContent" class="tab-content">
                            <!-- 添加/修改用户基本信息 -->
                            <div class="tab-pane fade in active" id="addBasic">
                                <div class="form-group">
                                    <label for="txt_username" class="col-sm-3 control-label">用户名</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="userName" class="form-control"
                                            id="txt_username" placeholder="用户名称">
                                    </div>
                                    <span style="color: red;">*</span>
                                </div>
                                <div class="form-group">
                                    <label for="txt_password" class="col-sm-3 control-label">密码</label>
                                    <div class="col-sm-7">
                                        <input type="password" name="password" class="form-control"
                                            id="txt_password" placeholder="密码">
                                    </div>
                                    <span style="color: red;">*</span>
                                </div>
                                <div class="form-group">
                                    <label for="txt_realname" class="col-sm-3 control-label">真实姓名</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="realName" class="form-control"
                                            id="txt_realname" placeholder="真实姓名">
                                    </div>
                                    <span style="color: red;">*</span>
                                </div>
                                <div class="form-group">
                                    <label for="txt_sex" class="col-sm-3 control-label">性别</label>
                                    <div class="col-sm-7">
                                        <input name="sex" id="sexInput" type="radio" value="1">男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input name="sex" type="radio" id="sexInput0" value="2">女
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_marr" class="col-sm-3 control-label">婚姻状态</label>
                                    <div class="col-sm-7">
                                        <input name="marriage" id="ismarrInput" type="radio" value="1">已婚&nbsp;&nbsp;
                                        <input name="marriage" type="radio" id="isemarrInput0"
                                            value="2">未婚
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_live" class="col-sm-3 control-label">籍贯</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="nativePlace" class="form-control"
                                            id="txt_native" placeholder="籍贯">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_statu" class="col-sm-3 control-label">现居住地</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="livingPlace" class="form-control"
                                            id="txt_live" placeholder="现居住地">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_phone" class="col-sm-3 control-label">手机号码</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="phone" class="form-control"
                                            id="txt_phone" placeholder="手机号码">
                                    </div>
                                    <span style="color: red;">*</span>
                                </div>
                                <div class="form-group">
                                    <label for="txt_mail" class="col-sm-3 control-label">邮箱</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="mail" class="form-control"
                                            id="txt_mail" placeholder="邮箱">
                                    </div>
                                    <span style="color: red">*</span>
                                </div>
                                <div class="form-group">
                                    <label for="txt_birth" class="col-sm-3 control-label">出生日期</label>
                                    <div class="col-sm-7">
                                        <input type="text" value="" name="birthDate"
                                            class="form-control date-picker" id="txt_birthday"
                                            data-date-format="yyyy-mm-dd" placeholder="出生日期">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_remark" class="col-sm-3 control-label">备注</label>
                                    <div class="col-sm-7">
                                        <textarea name="remark" class="form-control" rows="3"
                                            id="txt_remark"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="isenableInput" class="col-sm-3 control-label">是否可用</label>
                                    <div class="col-sm-7">
                                        <input name="isenable" id="isenableInput" type="radio"
                                            value="1" checked>是&nbsp;&nbsp; <input
                                            name="isenable" type="radio" id="isenableInput0" value="0">否
                                        <span style="color: red;">*</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-7">
                                        <!-- 用户的id -->
                                        <input type="hidden" name="userId" id="userId">
                                        <!-- userCompany的id -->
                                        <input type="hidden" name="ucId" id="ucId">
                                        <!-- 用户的创建时间 -->
                                        <input type="hidden" name="createdTime" id="createdTime">
                                        <!-- 用户的头像地址 -->
                                        <input type="hidden" name="picUrl" id="hidden_pic">
                                    </div>
                                </div>
                            </div>
                            <!-- 添加用户部门信息 -->
                            <div class="tab-pane fade" id="addOthers">
                                   <div class="form-group">
                                    <label class="control-label col-sm-3" for="role_select"
                                        style="padding-top: 10px;">部门</label>
                                    <div class="col-sm-7">
                                        <select id="dep" class="form-control" name="depId">
                                            <option value="0">请选择部门</option>
                                            <c:forEach items="${departments}" var="department">
                                                <option value="${department.depId }">${department.depName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_position" class="col-sm-3 control-label">工号</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="empNo" class="form-control"
                                            id="txt_empNo" placeholder="工号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_position" class="col-sm-3 control-label">职位</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="position" class="form-control"
                                            id="txt_position" placeholder="职位">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_title" class="col-sm-3 control-label">职称</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="jobTitle" class="form-control"
                                            id="txt_title" placeholder="职称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_salary" class="col-sm-3 control-label">薪资</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="salary" class="form-control"
                                            id="txt_salary" placeholder="单位（元/月）">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_worked" class="col-sm-3 control-label">已经工作时长</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="workedTime" class="form-control"
                                            id="txt_worked" placeholder="单位（年）">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="txt_entry" class="col-sm-3 control-label">入职时间</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="entryTime" value=""
                                            class="form-control date-picker "
                                            data-date-format="yyyy-mm-dd" id="txt_entry"
                                            placeholder="入职时间">
                                    </div>
                                    
                                </div>
                                <div class="form-group">
                                    <label for="txt_regular" class="col-sm-3 control-label">转正时间</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="regularTime" value=""
                                            class="form-control date-picker "
                                            data-date-format="yyyy-mm-dd" id="txt_regular"
                                            placeholder="转正时间">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                        </button>
                        <button type="submit" id="btn_submit" class="btn btn-primary"
                            name="submit">
                            <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>提交更改
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
	
	
	
	<script type="text/javascript" src="js/auth/user_manage.js"></script>
	<script type="text/javascript" src="js/auth/plugins/component.js"></script>
</body>
</html>