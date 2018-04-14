var isEdit = false;

//提示框toastr的显示控制
toastr.options = {
	"positionClass" : "toast-top-center",
	"timeOut" : "1500"
}

$(function() {
	// 校验字段
	formValidator();

	// Modal验证销毁重构
	$('#resource_modal').on('hidden.bs.modal', function() {
		$("#resourceInfoForm").data('bootstrapValidator').destroy();
		$('#resourceInfoForm').data('bootstrapValidator', null);
		formValidator();
	});

	// 初始化表格
	$('#tb')
			.bootstrapTable(
					{
						url : './resource/getResourcesTree',
						toolbar : '#toolbar',
						sidePagination : 'client',
						pagination : false,
						treeView : true,
						treeId : "resourceId",
						treeField : "resourceName",
						treeRootLevel : 1,
						clickToSelect : true,
						treeCollapseAll : false,
						striped : true,
						collapseIcon : "glyphicon glyphicon-plus icon-plus",// 折叠样式
						expandIcon : "glyphicon glyphicon-minus icon-minus",// 展开样式
						columns : [
								{
									title : '#',
									formatter : function(value, row, index) {
										if (row.children == null || row.children.length == 0) {
											if (row.ParentId != null) {
												return "<span class='noChildren'>"
														+ (index + 1)
														+ "</span>";
											} else {
												return index + 1;
											}
										} else {
											return index + 1;
										}
									},
									width : '5%'
								},
								{
									field : 'resourceId',
									visible : false,
									switchable : false
								},
								{
									field : 'resourceName',
									title : '资源名称',
									width : '15%'
								},
								{
									field : 'resourceUrl',
									title : 'Url',
									width : '25%'
								},
								{
									field : 'icon',
									title : '图标',
									formatter : function(value, row, index) {
										var span = "<span style='color:#999' class= '"
												+ value + "'></span>"
										return span;
									},
									width : '5%'
								},
								{
									field : 'location',
									title : '位置',
								},
								{
									field : 'remark',
									title : '备注',
									width : '15%'
								},
								{
									field : 'ParentId',
									visible : false,
									switchable : false
								},
								{
									field : 'children',
									visible : false,
									switchable : false,
								},
								{
									field : 'isenable',
									title : '状态',
									formatter : function(value, row, index) {
										var status = "";
										if (row.isenable == 1) {
											status = "checked";
										}
										var button = "<div class='switch'><input data-id='"
												+ row.resourceId
												+ "'"
												+ "type='checkbox' "
												+ status
												+ " /></div>"
										return button;
									},
									
								},
								{
									title : '操作',
									formatter : function(value, row, index) {
								        if (row.ParentId == 0) {
								        	var button = '<div class="btn-group">'+'<button data-pid="'
								        	+ row.resourceId
								        	+ '" type="button" class="btn btn-default" onclick="addResource2(this)">'
								        	+ '<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>增加'+'</button>'+'<button data-resourceId="'
								        	+ row.resourceId
								        	+ '" type="button" class="btn btn-default" onclick="editResource(this)">'
								        	+ '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改'+'</button>'+'<button data-children="'
								        	+ row.children.length
								        	+ '"  data-resourceId="'
								        	+ row.resourceId
								        	+ '" type="button" class="btn btn-danger" onclick="deleteResource(this)">'
								        	+ '<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除'
								        	+ '</button>'+'</div>';
								        	return button;
								        } else {
								        	var button = '<div class="btn-group">'+'<button data-resourceId="'
								        	+ row.resourceId
								        	+ '" type="button" class="btn btn-default" onclick="editResource(this)">'
								        	+ '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改'+'</button>'+'<button data-children="'
								        	+ row.children.length
								        	+ '"  data-resourceId="'
								        	+ row.resourceId
								        	+ '" type="button" class="btn btn-danger" onclick="deleteResource(this)">'
								        	+ '<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除'
								        	+ '</button>'+'</div>';
								        	return button;
								        }
									},
									cellStyle : function cellStyle(value, row,
											index) {
										return {
											css : {
												"min-width" : "230px",
											}
										};
									}
								} ],
						onPostBody : function() {
							$('span.noChildren').parent().parent()
									.find('td:nth-child(2) > span.tree-icon.glyphicon.glyphicon-plus.icon-plus')
									.removeClass().attr("style", "margin:0 13px");
							$('div.switch input').bootstrapSwitch({
								onText : '启用',
								offText : '停用',
								onColor : "success",
								offColor : "danger",
								onSwitchChange : function(event, state) {
									var resourceId = $(this).attr("data-id");
									var isenable;
									if (state == true) {
										isenable = 1;
									} else {
										isenable = 0;
									}
									$.ajax({
										type : "post",
										url : "./resource/changeIsenable",
										data : {
											"resourceId" : resourceId,
											"isenable" : isenable
										},
										dataType : 'JSON',
										success : function(data) {
											if (data.success != undefined) {
												$('#tb').bootstrapTable("refresh");
												toastr.success(data.success);
											} else {
												toastr.error(data.error);
											}
										}
									});
								},
								labelText : "状态"
							});
						}
					});
});

// 模态框
var ModalInit = function() {
	var oModalInit = new Object();
	oModalInit.Init = function() {
		$('#resource_modal').modal({
			keyboard : true,
			backdrop : "static",
		});
	}
	return oModalInit;
}

// 弹出模态框的时候给模态框里的表单赋初值
function inputValue(resource) {
	$("#txt_resourceName").val(resource.resourceName);
	$("#txt_resourceUrl").val(resource.resourceUrl);
	$("#txt_location").val(resource.location);
	$("#txt_icon").val(resource.icon);
	if (resource == "") {
		$('#txt_isenable0').prop("checked", false);
		$('#txt_isenable1').prop("checked", "checked");
	} else {
		if (resource.isenable == 1) {
			$('#txt_isenable1').prop("checked", "checked");
			$('#txt_isenable0').prop("checked", false);
		} else {
			$('#txt_isenable0').prop("checked", "checked");
			$('#txt_isenable1').prop("checked", false);
		}
	}
	$("#txt_remark").val(resource.remark);
	$("#txt_style").val(resource.style);
	$("#txt_pid").val(resource.pid);
	$("#txt_resourceId").val(resource.resourceId);
}

// 点击新增根节点
function addResource1() {
	$("#myModalLabel").text("添加根资源");
	isEdit = false;
	$('#resource_modal').modal('show'); // 显示模态框
	inputValue('');
}

// 点击新增子节点
function addResource2(obj) {
	$("#myModalLabel").text("添加子资源");
	var thisObj = $(obj);
	var pid = thisObj.attr("data-pid"); // 获取parentId
	isEdit = false;
	$('#resource_modal').modal('show'); // 显示模态框
	inputValue('');
	$('#txt_pid').val(pid);
}

//修改资源
function editResource(obj) {
	$("#myModalLabel").text("修改资源");
	isEdit = true;
	var thisObj = $(obj);
	var resourceId = thisObj.attr("data-resourceId"); // 获取要修改的资源id
	//alert(resourceId);
	$('#resource_modal').modal('show'); // 显示模态框
	$.ajax({
		type : "post",
		url : "./resource/getResourceById",
		data : {
			"resourceId" : resourceId
		},
		dataJson : "json",
		success : function(result) {
			inputValue(result);
		}
	});
}

// 添加资源
function resourceAdd() {
	// 开启验证
	$('#resourceInfoForm').data('bootstrapValidator').validate();
	if (!$('#resourceInfoForm').data('bootstrapValidator').isValid()) {
		return;
	}
	var params = $('#resourceInfoForm').serialize();
	$.ajax({
		type : "post",
		url : "./resource/addResource",
		data : params,
		dataType : "json",
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
				$('#tb').bootstrapTable("refresh"); // 添加成功之后刷新表格
				$('#resource_modal').modal('hide');
			} else {
				toastr.error(result.message);
				$('#resource_modal').modal('hide');
			}
		}
	});
}

// 修改资源
function resourceEdit() {
	var params = $('#resourceInfoForm').serialize();
	$.ajax({
		type : "post",
		url : "./resource/editResource",
		data : params,
		dataType : "json",
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
				$('#tb').bootstrapTable("refresh"); // 添加成功之后刷新表格
				$('#resource_modal').modal('hide');
			} else {
				toastr.error(result.message);
				$('#resource_modal').modal('hide');
			}
		}
	});
}

function addOrUpdate() {
	if (isEdit) {
		resourceEdit();
	} else {
		resourceAdd();
	}
}

// 删除资源
function deleteResource(obj) {
	var thisObj = $(obj);
	var resourceId = thisObj.attr("data-resourceId");
	if (thisObj.attr("data-children") != 0) {
		toastr.warning("该节点下还有其他子节点，请先删除所有子节点后再删除该节点！");
		return false;
	} else {
		Ewin.confirm({
			message : "删除该资源后将无法使用，是否删除该资源？"
		}).on(function(e) {
			if (!e) {
				return;
			}
			$.ajax({
				type : "post",
				url : "./resource/deleteLeafResource",
				data : {
					"resourceId" : resourceId,
				},
				dataType : 'JSON',
				success : function(result) {
					$('#tb').bootstrapTable("refresh");
					toastr.success(result.message);
				}
			});
		});
	}
}

// 校检字段
function formValidator() {
	$('#resourceInfoForm').bootstrapValidator({
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			resourceName : {
				validators : {
					notEmpty : {
						message : '资源名称不能为空'
					}
				}
			},
			location : {
				validators : {
					notEmpty : {
						message : '显示的位置不能为空'
					},
					stringLength : {
						/* 长度提示 */
						max : 8,
						message : '最大长度为8'
					}
				}
			}
		}
	});
}