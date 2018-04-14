//提示框toastr的显示控制
toastr.options = {
	"positionClass" : "toast-top-center",
	"timeOut" : "1500"
}

//初始化页面
$(function() {
	//初始化表格
	var oTable = new TableInit();
	oTable.Init();
	
	//初始化按钮
	var oButton = new ButtonInit();
	oButton.Init();
	
});

//表格
var TableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#table')
				.bootstrapTable(
						{
							url : './userRole/getUserRoleByPage',
							contentType : "application/x-www-form-urlencoded", // 必须要有！！！！
							method : 'post', // 请求方式（*）
							striped : true, // 是否显示行间隔色
							cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
							pagination : true,
							sortable : true, // 是否启用排序
							editable : false,// 开启编辑模式
							sortOrder : "asc", // 排序方式
							queryParams : oTableInit.queryParams, // 传递参数（*）
							sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
							singleSelect : false, // 设置True 将禁止多选
							clickToSelect : false, // 设置true
							// 将在点击行时，自动选择rediobox 和
							// checkbox
							pageNumber : 1, // 初始化加载第一页，默认第一页
							pageSize : 10, // 每页的记录行数（*）
							pageList : [ 10, 15, 20, 100 ], // 可供选择的每页的行数（*）
							strictSearch : true,
							showColumns : true, // 是否显示所有的列
							showRefresh : true, // 是否显示刷新按钮
							showToggle : true, // 是否显示详细视图和列表视图的切换按钮
							uniqueId : "userId", // 每一行的唯一标识，一般为主键列
							toolbar : "#toolbar", // 工具按钮用哪个容器
							columns : [
									{
										checkbox : true
									},
									{
										field : 'userId',
										title : '用户编号',
										align : 'center',
										visible : false
									},
									{
										field : 'realName',
										title : '用户名称',
										align : 'center',
										formatter : function(value, row, index) {
											return '<div  data-target="#detailModal" data-toggle="modal"  onclick="javascript:load('
													+ row.userId
													+ ')" style="color:#563d7c;cursor:pointer;">'
													+ value + '</div>';
										}
									}, {
										field : 'roleId',
										title : '角色编号',
										align : 'center',
										visible : false
									// 该列隐藏
									}, {
										field : 'roleName',
										title : '角色名称',
										align : 'center'
									}, {
										field : 'deptName',
										title : '部门名称',
										align : 'center'
									}, {
										field : 'position',
										title : '职位',
										align : 'center'
									}, {
										field : 'userId',
										title : '操作',
										align : 'center',
										width : '200px',
										formatter : function(value, row, index) {
											//有角色时两个按钮（分配按钮、删除按钮）
											if(row.roleId > 0) {
												var actions = [];
												actions.push('<button data-target="#role_assignment_modal" data-toggle="modal" class="btn btn-primary btn-sm" title="编辑" onclick="btnEdit(\'' + row.roleId + '\',\''+value+'\')"><i class="fa fa-edit"></i></button> ');
												return actions.join('');
											}
										}

									} ]
						});
	};

	// 得到查询的参数
	oTableInit.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			start : params.offset, // 从数据库第几条记录开始,页码
			pageSize : params.limit,
			//status : $("#txt_search_isRole").val(),
			selectedFiled : $("#serchFiledSelceted").text(),
			condition : $("#txt_search_condition").val()
		}
		return temp;
	}
	return oTableInit;
}

//按钮
var ButtonInit = function() {
	var oInit = new Object();
	oInit.Init = function() {
		//点击查询按钮
		$('#btn_query').click(function() {
			$('#table').bootstrapTable("refresh");
		});
		
		//点击批量分配按钮
		$('#btn_addMoreUserRole').click(function() {
			var ids = getIdSelections();
			if (ids == "") {
				toastr.warning("请选择数据");
				return;
			} else {
				$('#role_assignment_more_modal').modal('show');
				$("#role_select").empty(); // 清空角色的下拉框内容
				$.ajax({
					url : "./role/getAllRoles", // 所需要的列表接口地址
					type : "post",
					dataType : "json",
					success : function(data) {
						var h = "";
						$.each(data.roles, function(key, value) {// 拼接option 
							h += "<option value=" + value.roleId + ">" + value.roleName
									+ "</option>";
							
						})
						$("#role_select").append(h); // 添加选项下拉框
						// 隐藏域
						$("#hidden_userIds").val(ids.toString());
					}
				});
				
			}
		});
		
		//单个修改时点击确定按钮
		$('#btn_confirm').click(function() {
			var userId = $('#userid_select').val();
			var roleId = $('#role_grant_select').val();
			//修改用户角色
			userRoleEdit(userId, roleId);
		});
		
		//批量修改时点击确定按钮
		$('#btn_more_conform').click(function() {
			var userIds = $('#hidden_userIds').val();
			var roleId = $('#role_select').val();
			userRoleEditBatch(userIds, roleId);
		});
	}
	return oInit;
}

//获取所有选中的行的主键
function getIdSelections() {
	return $.map($('#table').bootstrapTable('getSelections'), function(row) {
		return row.userId;
	});
}

//获取选中的查询条件
function selectedFiled(obj) {
	var thisObj = $(obj);
	var selectedFiled = thisObj.text();
	$('#serchFiledSelceted').text(selectedFiled);
}


//点击修改用户角色
function btnEdit(roleId, userId) {
	$("#role_grant_select").empty(); // 清空角色的下拉框内容
	$.ajax({
		url : "./role/getAllRoles", // 所需要的列表接口地址
		type : "post",
		dataType : "json",
		success : function(data) {
			var h = "";
			if (roleId == 0 || roleId == undefined) {
				h += "<option value='0'>请选择角色</option>";
			}
			$.each(data.roles, function(key, value) {// 拼接option 
				if (roleId == value.roleId) {
					// 默认选中当前的角色
					h += "<option value=" + value.roleId + " selected='true'>"
							+ value.roleName + "</option>";
				} else {
					h += "<option value=" + value.roleId + ">" + value.roleName
							+ "</option>";
				}
			})
			$("#role_grant_select").append(h); // 添加选项下拉框
			// 隐藏域
			$("#userid_select").val(userId);
			$("#old_roleid_select").val(roleId);
		}
	});
}

//修改单个用户的角色
function userRoleEdit (userId, roleId) {
	$.ajax({
		url : "./userRole/editUserRoleByUserId",
		type : "post",
		data : {
			"userId" : userId,
			"roleId" : roleId
		},
		dataType : "json",
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
			} else {
				toastr.error(result.message);
			}
			$('#role_assignment_modal').modal("hide");
			$('#table').bootstrapTable("refresh");
		}
	});
}

function userRoleEditBatch(userIds, roleId) {
	$.ajax({
		url : "./userRole/editUserRoleBatch",
		type : "post",
		data : {
			"userIds" : userIds,
			"roleId" : roleId
		},
		dataType : "json",
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
			} else {
				toastr.error(result.message);
			}
			$('#role_assignment_more_modal').modal("hide");
			$('#table').bootstrapTable("refresh");
		}
	});
}