//用于判断当前是新增还是修改
var isEdit = false;

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
	var oButton = new ButtonInit();
	oButton.Init();
	
	// 校验字段
	formValidator();

	// Modal验证销毁重构
	$('#category_modal').on('hidden.bs.modal', function() {
		$("#categoryInfoForm").data('bootstrapValidator').destroy();
		$('#categoryInfoForm').data('bootstrapValidator', null);
		formValidator();
	});
});

//表格
var TableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#table')
				.bootstrapTable(
						{
							url : './category/getCatesByPage',
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
							uniqueId : "categoryId", // 每一行的唯一标识，一般为主键列
							toolbar : "#toolbar", // 工具按钮用哪个容器
							columns : [
									{
										checkbox : true
									},
									{
										field : 'categoryId',
										title : '编号',
										align : 'center',
										visible : false
									},
									{
										field : 'categoryName',
										title : '名称',
										align : 'center'
									},
									{
										field : 'pinyinName',
										title : '拼音名称',
										align : 'center'
									},
									{
										field : 'remark',
										title : '备注',
										align : 'center'
									}],
						});
	};

	// 得到查询的参数
	oTableInit.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			start : params.offset, // 从数据库第几条记录开始,页码
			pageSize : params.limit,
			/*status : $('#').val(),*/
			//selectedFiled : $("#serchFiledSelceted").text(),
			condition : $("#txt_search_condition").val()
		}
		return temp;
	}
	return oTableInit;
}

//按钮
var ButtonInit = function() {
	var oButtonInit = new Object();
	oButtonInit.Init = function() {
		//点击查询按钮
		$('#btn_query').click(function() {
			$('#table').bootstrapTable("refresh");
		});
		
		//点击新增按钮
		$('#btn_add').click(function() {
			$('#myModalLabel').text('添加种类');
			$('#category_modal').modal('show');
			isEdit = false;
			inputValue("");
		});
		
		//点击修改按钮(不允许修改种类)
		$('#btn_edit').click(function() {
			$('#myModalLabel').text('修改种类');
			isEdit = true;
			var ids = getIdSelections();
			if (ids == "") {
				toastr.warning("请选择要修改的数据");
				return;
			} else if(ids.length > 1) {
				toastr.warning("选择了多条数据,请选择修改一条数据!");
				return;
			} else {
				$.ajax({
					url : "./category/getCateById",
					type : "post",
					dataType : "json",
					data : {
						"id" : ids[0],
					},
					success : function(cate) {
						$('#category_modal').modal('show');
						inputValue(cate); // 打开模态框的时候显示要修改的role数据
					}
				});
			}
		});
		
		// 点击删除按钮
		$('#btn_delete').click(function() {
			var ids = getIdSelections();
			if(ids == "") {
				toastr.warning("请选择要删除的数据");
			} else {
				Ewin.confirm({
					message : "确认要删除选择的数据吗？"
				}).on(function(e) {
					if (!e) {
						return;
					}
					cateDel(ids);
				});
				
			}
		});
		
		//新增或修改时点击确定按钮
		$('#btn_confirm').click(function() {
			if (isEdit) {
				cateEdit();
			} else {
				cateAdd();
			}
		});
		
		
	}
	return oButtonInit;
}

//获取所有选中的行的主键
function getIdSelections() {
	return $.map($('#table').bootstrapTable('getSelections'), function(row) {
		return row.categoryId;
	});
}

//弹出模态框的时候给模态框里的表单赋初值
function inputValue(cate) {
	$('#txt_categoryName').val(cate.categoryName);
	$('#txt_pinyinName').val(cate.pinyinName);
	$('#txt_remark').val(cate.remark);
	if(cate != '') {
		$('#hidden_categoryId').val(cate.categoryId);
	} else{
		$('#hidden_categoryId').val(null);
	}
}

//新增种类
function cateAdd() {
	// 开启验证
	$('#categoryInfoForm').data('bootstrapValidator').validate();
	if (!$('#categoryInfoForm').data('bootstrapValidator').isValid()) {
		return;
	}
	$.ajax({
		url : "./category/addCate",
		type : "post",
		dataType : "json",
		data : $('#categoryInfoForm').serialize(),
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
			} else {
				toastr.error(result.message);
			}
			$('#table').bootstrapTable("refresh"); // 添加成功之后刷新表格
			$('#category_modal').modal('hide'); // 隐藏模态框
		}
	});
}

//修改种类
function cateEdit() {
	$.ajax({
		type : "post",
		url : "./category/editCate",
		data : $('#categoryInfoForm').serialize(),
		dataType : "json",
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
			} else {
				toastr.error(result.message);
			}
			$('#table').bootstrapTable("refresh"); // 添加成功之后刷新表格
			$('#category_modal').modal('hide'); // 隐藏模态框
		}
	});
}

//删除
function cateDel(ids) {
	$.ajax({
		type : "post",
		url : "./category/delBatch",
		data : {
			"ids": ids.toString() 
		},
		dataType : "json",
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
			} else {
				toastr.error(result.message);
			}
			$('#table').bootstrapTable("refresh"); // 添加成功之后刷新表格
		}
	});
}

//校检字段
function formValidator() {
	$('#categoryInfoForm').bootstrapValidator({
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			categoryName : {
				validators : {
					notEmpty : {
						message : '名称不能为空'
					},
					regexp: {
                        regexp: /^[\u4e00-\u9fa5]+$/,
                        message: '只能输入中文'
                    }
				},
			},
			pinyinName : {
				validators : {
					notEmpty : {
						message : '拼音名称不能为空'
					},
					regexp: {
                        regexp: /^[a-zA-Z]+$/,
                        message: '只能输入字母'
                    }
				}
			}
		}
	});
}