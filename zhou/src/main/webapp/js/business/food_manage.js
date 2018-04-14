//用于判断当前是新增还是修改
var isEdit = false;

//最大的图片大小200k
var MaxSize = 100*1024;

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
	$('#food_modal').on('hidden.bs.modal', function() {
		$("#foodInfoForm").data('bootstrapValidator').destroy();
		$('#foodInfoForm').data('bootstrapValidator', null);
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
							url : './food/getFoodsByPage',
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
							uniqueId : "foodId", // 每一行的唯一标识，一般为主键列
							toolbar : "#toolbar", // 工具按钮用哪个容器
							columns : [
									{
										checkbox : true
									},
									{
										field : 'foodId',
										title : '编号',
										align : 'center',
										visible : false
									},
									{
										field : 'name',
										title : '菜名',
										align : 'center'
									},
									{
										field : 'imageUrl',
										title : '图片',
										align : 'center',
										formatter:function(value,row,index){
						                    var s = '<a href="javascript:void(0)"><img style="width:50px;height:30px;border-radius: 8px;"  src="' + requestUrl + row.imageUrl+'" /></a>';
						                    return s;
						                }
									},
									{
										field : 'categoryName',
										title : '种类',
										align : 'center'
									},
									{
										field : 'originalPrice',
										title : '原价',
										align : 'center'
									},
									{
										field : 'price',
										title : '菜价',
										align : 'center'
									},
									{
										field : 'stock',
										title : '库存（份）',
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
			selectedFiled : $("#serchFiledSelceted").text(),
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
			$('#myModalLabel').text('添加菜单');
			//将下拉框启用
			$('#txt_cateId').attr("disabled", false);
			isEdit = false;
			$('#food_modal').modal("show");
			inputValue("");
		});
		
		//点击修改按钮(不允许修改种类)
		$('#btn_edit').click(function() {
			$('#myModalLabel').text('修改菜单');
			//将下拉框禁用
			$('#txt_cateId').attr("disabled", "disabled");
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
					url : "./food/getFoodById",
					type : "post",
					dataType : "json",
					data : {
						"foodId" : ids[0],
					},
					success : function(food) {
						$('#food_modal').modal('show');
						inputValue(food); // 打开模态框的时候显示要修改的role数据
					}
				});
			}
		});
		
		// 点击删除按钮
		$('#btn_delete').click(function() {
			var ids = getIdSelections();
			if(ids == "") {
				toastr.warning("请选择要删除的数据");
			} else if(ids.length > 1) {
				toastr.warning("只能删除一条数据");
			} else {
				Ewin.confirm({
					message : "确认要删除选择的数据吗？"
				}).on(function(e) {
					if (!e) {
						return;
					}
					foodDel(ids);
				});
				
			}
		});
		
		//新增或修改时点击确定按钮
		$('#btn_confirm').click(function() {
			var file = $('#txt_imageFile').val();
			//修改时可以不上传图片
			if (isEdit) {
				//修改菜单
				foodEdit();
			} else {
				if(file == "" || file == null) {
					toastr.warning("请选择图片");
					return false;
				} else {
					var photo = document.getElementById("txt_imageFile");
					var size = photo.files[0].size;
					if (size > MaxSize) {
						toastr.warning("请选择100k以下的图片");
						return false;
					}
					//添加菜单
					foodAdd();
				}
			}
			
		});
		
		
	}
	return oButtonInit;
}

//获取所有选中的行的主键
function getIdSelections() {
	return $.map($('#table').bootstrapTable('getSelections'), function(row) {
		return row.foodId;
	});
}

//获取选中的查询条件
function selectedFiled(obj) {
	var thisObj = $(obj);
	var selectedFiled = thisObj.text();
	$('#serchFiledSelceted').text(selectedFiled);
}

//弹出模态框的时候给模态框里的表单赋初值
function inputValue(food) {
	$('#txt_name').val(food.name);
	$('#txt_imageFile').val("");
	$('#txt_originalPrice').val(food.originalPrice);
	$('#txt_price').val(food.price);
	$('#txt_stock').val(food.stock);
	if(food != "") {
		$('#txt_cateId').val(food.categoryId);
	} else {
		$('#txt_cateId').val(0);
	}
	$('#txt_remark').val(food.remark);
	
	if (food != "") {
		$('#hidden_foodId').val(food.foodId);
	} else {
		$('#hidden_foodId').val(null);
	}
	$('#hidden_imageUrl').val(food.imageUrl);
}

//新增菜单
function foodAdd() {
	// 开启验证
	$('#foodInfoForm').data('bootstrapValidator').validate();
	if (!$('#foodInfoForm').data('bootstrapValidator').isValid()) {
		return;
	}
	//获取菜单种类的拼音名
	var pinyin = $("#txt_cateId > option:selected ").data("pinyin");
	//给隐藏域赋值
	$('#hidden_pinyin').val(pinyin);
	
	$.ajax({
		type : "post",
		url : "./food/addFood",
		data : new FormData($("#foodInfoForm")[0]),
		dataType : "json",
		contentType: false,
        processData: false,
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
			} else {
				toastr.error(result.message);
			}
			$('#table').bootstrapTable("refresh"); // 添加成功之后刷新表格
			$('#food_modal').modal('hide'); // 隐藏模态框
		}
	});
}

//修改菜单
function foodEdit() {
	//获取菜单种类的拼音名
	var pinyin = $("#txt_cateId > option:selected ").data("pinyin");
	//给隐藏域赋值
	$('#hidden_pinyin').val(pinyin);
	
	$.ajax({
		type : "post",
		url : "./food/editFood",
		data : new FormData($("#foodInfoForm")[0]),
		dataType : "json",
		contentType: false,
        processData: false,
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
			} else {
				toastr.error(result.message);
			}
			$('#table').bootstrapTable("refresh"); // 添加成功之后刷新表格
			$('#food_modal').modal('hide'); // 隐藏模态框
		}
	});
}

//删除菜单
function foodDel(ids) {
	$.ajax({
		type : "post",
		url : "./food/delFood",
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
	$('#foodInfoForm').bootstrapValidator({
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			name : {
				validators : {
					notEmpty : {
						message : '菜名不能为空'
					}
				},
			},
			originalPrice : {
				validators : {
					notEmpty : {
						message : '原价不能为空'
					}
				}
			},
			price : {
				validators : {
					notEmpty : {
						message : '售价不能为空'
					}
				}
			},
			stock : {
				validators : {
					notEmpty : {
						message : '库存不能为空'
					}
				}
			},
			file : {
				validators : {
					file: {
                        extension: 'png,jpg,jpeg',
                        type: 'image/png,image/jpg,image/jpeg',
                        message: '请重新选择图片'
                    }
				}
			},
			categoryId : {
				validators : {
					notEmpty : {
						message : '请选择种类'
					},
					callback: {
						message: '必须选择一个种类',
						callback: function(value, validator) {
							if(value == 0) {
								return false;
							} else {
								return true;
							}
						}
					}
				}
			},
		}
	});
}