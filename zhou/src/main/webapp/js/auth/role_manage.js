//全局变量，表示当前时修改还是添加角色
var isEdit = false;

//表示选中行的数量
var a = 0;

//ztree参数配置
var setting = {
	check : {
		enable : true
	},
	data : {
		key : {
			name : "resourceName"
		},
		simpleData : {
			enable : true,
			idKey : "resourceId",
			pIdKey : "pid",
			rootPId : 0
		}
	}
};

//提示框toastr的显示控制
toastr.options = {
	"positionClass" : "toast-top-center",
	"timeOut" : "1500"
}

$(function() {
	//初始化表格
	var oTable = new TableInit();
	oTable.Init();
	
	//初始化按钮
	var oButtonInit = new ButtonInit();
	oButtonInit.Init();
	
	// 校验字段
	formValidator();

	// Modal验证销毁重构
	$('#role_modal').on('hidden.bs.modal', function() {
		$("#roleInfoForm").data('bootstrapValidator').destroy();
		$('#roleInfoForm').data('bootstrapValidator', null);
		formValidator();
	});
});

// 表格
var TableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#table')
				.bootstrapTable(
						{
							url : './role/getRolesByPage',
							contentType : "application/x-www-form-urlencoded", // 必须要有！！！！
							method : 'post', // 请求方式（*）
							striped : true, // 是否显示行间隔色
							cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
							pagination : true,
							sortable : false, // 是否启用排序
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
							uniqueId : "roleId", // 每一行的唯一标识，一般为主键列
							toolbar : "#toolbar", // 工具按钮用哪个容器
							columns : [
									{
										checkbox : true
									},
									{
										field : 'roleId',
										title : '角色编号',
										align : 'center',
										visible : false
									},
									{
										field : 'roleName',
										title : '角色名称',
										align : 'center'
									},
									{
										field : 'roleCode',
										title : '角色代码',
										align : 'center'
									},
									{
										field : 'remark',
										title : '备注',
										align : 'center'
									},
									{
										field : 'isenable',
										title : '启用状态',
										align : 'center',
										width : '5%',
										formatter : function(value, row, index) {
											if (value == 1) {
												return "<a class='btn btn-link' id='btn_enableRole' data-content='点击禁用角色' data-toggle='popover' style='color:green;cursor:pointer;' onclick='enableRole("
														+ row.roleId
														+ ","
														+ value
														+ ")'>可用</a>";
											} else if (value == 0) {
												return "<a class='btn btn-link' id='btn_enableRole' data-content='点击启用角色' data-toggle='popover' style='color:red;cursor:pointer;' onclick='enableRole("
														+ row.roleId
														+ ","
														+ value
														+ ")'>禁用</a>";
											} else {
												return '-';
											}
										}
									}, {
										field : 'editTime',
										title : '修改时间',
										align : 'center'
									}, {
										field : 'createdTime',
										title : '创建时间',
										align : 'center'
									}/*,
									{
										field : 'roleId',
										title : '操作',
										align : 'center'
									}*/],
							onCheck : function() {
								a += 1;
								alterBtnReenable(a);
							},
							onUncheck : function() {
								a = a - 1;
								alterBtnReenable(a);
							},
							onCheckAll : function() {
								a = getIdSelections().length;
								alterBtnReenable(a);
							},
							onUncheckAll : function() {
								a = 0;
								alterBtnReenable(a);
							},
							onPostBody : function() {
								$("[data-toggle='popover']").popover({
									trigger : 'hover',
									container : 'body',
									placement : 'left'
								});
							}
						});
	};

	// 得到查询的参数
	oTableInit.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			start : params.offset, // 从数据库第几条记录开始,页码
			pageSize : params.limit,
			/*status : $('#').val(),*/
			selectedFiled : $("#serchFiledSelceted").text(),
			condition : $("#txt_search_condition").val()
		}
		return temp;
	}
	return oTableInit;
}

var ModalInit = function() {
	var oModalInit = new Object();
	oModalInit.Init = function() {
		$('#role_modal').modal({
			keyboard : true,
			backdrop : "static",
		});
	}
	return oModalInit;
}

var ButtonInit = function() {
	var oButtonInit = new Object();
	oButtonInit.Init = function() {
		//点击查询按钮
		$('#btn_query').click(function() {
			$('#table').bootstrapTable("refresh");
		});
		
		//点击新增按钮
		$('#btn_add').click(function() {
			$("#myModalLabel").text("添加角色");
			isEdit = false;
			inputValue('');
			//打开模态框
			$('#role_modal').modal("show");
		});
		
		//点击修改按钮
		$('#btn_edit').click(function() {
			$("#myModalLabel").text("修改角色");
			isEdit = true;
			var ids = getIdSelections(); // 获取选中行的id
			if (ids == "") {
				toastr.warning("请选择要修改的数据");
				return;
			}
			if (ids.length > 1) {
				toastr.warning("选择了多条数据,请选择修改一条数据!");
			} else {
				$('#role_modal').modal('show');
				$.ajax({
					url : "./role/getRoleById",
					type : "post",
					dataType : "json",
					data : {
						"roleId" : ids[0],
					},
					success : function(role) {
						//bu能修改代码为0002的角色
						if (role.roleCode == "0002") {
							$('#roleCodeInput').attr("readonly", "readonly");
						} else {
							$('#roleCodeInput').attr("readonly", false);
						}
						inputValue(role); // 打开模态框的时候显示要修改的role数据
					}
				});
			}
		});
		
		// 点击删除按钮
		$('#btn_delete').click(function() {
			var ids = getIdSelections();
			var a = ids.toString().indexOf("1")>=0;
			if (ids == "") {
				toastr.warning("请选择要删除的数据");
				return;
			} else if(a) {
				//不能删除roleCode为0002的角色
				toastr.warning("角色\"0002\"不能删除");
			} else {
				Ewin.confirm({
					message : "确认要删除选择的数据吗？"
				}).on(function(e) {
					if (!e) {
						return;
					}
					roleDelete(ids);
				});
			}
		});
		
		// 点击分配资源按钮
		$('#btn_dispatch').click(function() {
			var ids = getIdSelections(); // 获取选中行的id
			if (ids == "") {
				toastr.warning("请选择要分配的角色");

				return;
			}
			if (ids.length > 1) {
				toastr.warning("选择了多条数据,请选择修改一条数据!");
			} else {
				$("#dispatchModal").modal("show");
				$.ajax({
					url : "./resource/getResources",
					type : "post",
					dataType : "json",
					data : {
						"roleId" : ids[0],
					},
					success : function(zNodes) {
						a = getIdSelections().length;
						alterBtnReenable(a);
						$.fn.zTree.init($("#treeDemo"), setting, zNodes);
						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
						treeObj.expandAll(true); // 打开模态框的时候显示要修改的role数据
					}
				});
			}
		});
		
		//新增或修改时点击确定按钮
		$('#btn_confirm').click(function() {
			if (isEdit) {
				roleEdit();
			} else {
				roleAdd();
			}
		});
		
		// 点击分配资源确认按钮
		$('#btn_submitRes').click(function() {
			var ids = getIdSelections();
			var roleId = ids[0];
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			//资源id
			var resourceIds = "";
			if (nodes != "") {
				for (var i = 0; i < nodes.length; i++) {
					resourceIds += "," + nodes[i].resourceId;
				}
			}
			if (resourceIds.length > 0) {
				resourceIds = resourceIds.substring(1)
			}
			$.ajax({
				type : "post",
				url : "./resource/dispatchResources",
				data : {
					"resourceIds" : resourceIds,
					"roleId" : roleId
				},
				dataType : "json",
				success : function(result) {
					if (result.data > 1) {
						$('#table').bootstrapTable("refresh");
						$("#dispatchModal").modal("hide");
						toastr.success(result.message);
					} else {
						toastr.error(result.message);
						$("#dispatchModal").modal("hide");
					}
				}
			});

		});
	}
	return oButtonInit;
}

//获取所有选中的行的主键
function getIdSelections() {
	return $.map($('#table').bootstrapTable('getSelections'), function(row) {
		return row.roleId;
	});
}

//获取选中行的isenable值
function getIsenableSelections() {
	return $.map($('#table').bootstrapTable('getSelections'), function(row) {
		return row.isenable;
	});
}

//获取选中的查询条件
function selectedFiled(obj) {
	var thisObj = $(obj);
	var selectedFiled = thisObj.text();
	$('#serchFiledSelceted').text(selectedFiled);
}

//弹出模态框的时候给模态框里的表单赋初值
function inputValue(role) {
	$("#roleIdInput").val(role.roleId);
	$("#roleNameInput").val(role.roleName);
	$("#roleCodeInput").val(role.roleCode);
	$("#remarkInput").val(role.remark);
	if (role.createdTime!=undefined) {
		$("#createdTimeInput").val(role.createdTime);
	}
	if (role == "") {
		$('#isenableInput').prop("checked", "checked");
		$('#isenableInput0').prop("checked", false);
	} else {
		if (role.isenable == 1) {
			$('#isenableInput').prop("checked", "checked");
			$('#isenableInput0').prop("checked", false);
		} else if (role.isenable == 0) {
			$('#isenableInput0').prop("checked", "checked");
			$('#isenableInput').prop("checked", false);
		}
	}
}

//添加时的方法
function roleAdd() {
	// 开启验证
	$('#roleInfoForm').data('bootstrapValidator').validate();
	if (!$('#roleInfoForm').data('bootstrapValidator').isValid()) {
		return;
	}
	//表单参数
	var params = $('#roleInfoForm').serialize();
	$.ajax({
		type : "post",
		url : "./role/addRole",
		data : params,
		dataType : "json",
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
				$('#table').bootstrapTable("refresh"); // 添加成功之后刷新表格
				$('#role_modal').modal('hide'); // 隐藏模态框
			} else {
				toastr.error(result.message);
			}
		}
	});
}

//修改时的方法
function roleEdit() {
	var ids = getIdSelections(); // 获取选中的行的id值
	params = $('#roleInfoForm').serialize() + "&roleId=" + ids[0];
	$.ajax({
		type : "post",
		url : "./role/editRole",
		data : params,
		dataType : "json",
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
				$('#table').bootstrapTable("refresh"); // 添加成功之后刷新表格
				$('#role_modal').modal('hide'); // 隐藏模态框
			} else {
				toastr.error(result.message);
			}
		}
	});
}

//删除时的方法
function roleDelete(ids) {
	ids = ids.toString();
	$.ajax({
		type : "post",
		url : "./role/delRoles",
		data : {'ids': ids},
		dataType : 'JSON',
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
			} else {
				toastr.error(result.message);
			}
			$('#table').bootstrapTable("refresh");
		}
	});
}

// 校检字段
function formValidator() {
	$('#roleInfoForm').bootstrapValidator({
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			roleName : {
				validators : {
					notEmpty : {
						message : '角色名称不能为空'
					},
					stringLength : {
						/* 长度提示 */
						min : 2,
						max : 10,
						message : '角色名称长度必须在2到10之间'
					},
					/*threshold : 2,*/// 只有2个字符以上才发送ajax请求
					/*remote : {
						url : "./auth/checkRoleNameUnique.do",
						type : 'POST',
						data : function(validator) {
							return {
								id : $("#roleIdInput").val(),
								roleName : $("#roleNameInput").val(),
								isEdit : isEdit
							};
						},
						message : '该名称已存在，请输入其他角色名称',
						delay : 100
					}*/
				}
			},
			roleCode : {
				validators : {
					notEmpty : {
						message : '角色代码不能为空'
					},
					stringLength : {
						/* 长度提示 */
						min : 4,
						max : 10,
						message : '用户名长度必须在4到10之间'
					},
					/*threshold : 4,// 只有4个字符以上才发送ajax请求
					remote : {
						url : "./auth/checkRoleCodeUnique.do",
						type : 'POST',
						data : function(validator) {
							return {
								id : $("#roleIdInput").val(),
								roleCode : $("#roleCodeInput").val(),
								isEdit : isEdit
							};
						},
						message : '该角色已存在，请重新输入',
						delay : 100
					}*/
				}
			}
		}
	});
}

//修改重新启用/禁用按钮
function alterBtnReenable(selectionslength) {
	var isenables = getIsenableSelections(); // 获取选中行的isenable值
	if (selectionslength==0) { //没有选中行
		$('#btn_reenable').attr("disabled", true);
	} else if (selectionslength==1) {  //选中行为1
		$('#btn_reenable').attr("disabled", false);
		if (isenables[0]==0) {
			$('#btn_reenable')
			.html(
					"<span class='glyphicon glyphicon-ok-sign' aria-hidden='true'></span>启用");
		} else {
			$('#btn_reenable')
			.html(
					"<span class='glyphicon glyphicon-remove-sign' aria-hidden='true'></span>禁用");
		}
	} else {  //选中多行
		for (var i = 1; i < isenables.length; i++) {
			if (isenables[i]!=isenables[0]) {
				$('#btn_reenable').attr("disabled", true);
				break;
			} else {
				$('#btn_reenable').attr("disabled", false);
				if (isenables[0]==1) {
					$('#btn_reenable')
					.html(
							"<span class='glyphicon glyphicon-remove-sign' aria-hidden='true'></span>禁用");
				} else {
					$('#btn_reenable')
					.html(
							"<span class='glyphicon glyphicon-ok-sign' aria-hidden='true'></span>启用");
				}
			}
		}
	}
}