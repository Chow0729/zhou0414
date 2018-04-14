//提示框toastr的显示控制
toastr.options = {
	"positionClass" : "toast-top-center",
	"timeOut" : "1500"
}
var isEdit=false;
$(function() {
	//初始化表格
	var oTableInit = new TableInit();
	oTableInit.Init();
	
	//初始化按钮
	var oButton = new ButtonInit();
	oButton.Init();
});

// 表格
var TableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#roomTypeTable')
				.bootstrapTable(
						{
							url : './roomType/getAllRoomTypeByPage',
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
							uniqueId : "roomTypeId", // 每一行的唯一标识，一般为主键列
							toolbar : "#RoomTypeToolbar", // 工具按钮用哪个容器
							columns : [
									{
										checkbox : true
									},
									{
										field : 'roomTypeId',
										title : '类型编号',
										align : 'center',
										visible : false
									},
									{
										field : 'roomTypeName',
										title : '类型',
										align : 'center'
									},
									{
										field : 'roomNum',
										title : '房间数量',
										align : 'center'
									},
									{
										field : 'bedsNum',
										title : '床位数',
										align : 'center'
									},
									{
										field : 'roomPrice',
										title : '房价',
										align : 'center',
										width : '5%'
									}, {
										field : 'status',
										title : '状态',
										align : 'center'
									}, {
										field : 'remark',
										title : '备注',
										align : 'center'
									},
									{
										field : 'roleId',
										title : '操作',
										align : 'center'/*,
										formatter :showDetail*/
									}]
						});
	};

	// 得到查询的参数
	oTableInit.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			start : params.offset, // 从数据库第几条记录开始,页码
			pageSize : params.limit,
			status : $("#txt_search_roomTypeStatus").val(),  //是否有房
			selectedFiled : $("#typeSerchFiledSelceted").text(),  //查询条件
			condition : $("#txt_type_search_condition").val()  //查询内容
		}
		console.log(temp);
		return temp;
	}
	return oTableInit;
}

var ButtonInit = function() {
	var oButtonInit = new Object();
	oButtonInit.Init = function() {
		//点击新增按钮
		$('#btn_add_room_type').click(function() {
			$('#room_type_modal').modal("show");
		});
		
		//点击修改按钮
		$('#btn_edit').click(function() {
			
		});
		
		// 点击删除按钮
		$('#btn_delete_room_type').click(function() {
			var ids = getIdSelections();

			if (ids == "") {
				 toastr.warning('请选择要删除的数据数据');
				return;
			}

            Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
                if (!e) {
                    return;
                }
			$.ajax({
				type : "post",
				url :  "./roomType/delRoomType",
				data : JSON.stringify(ids),
				dataType : 'JSON',
				contentType : "application/json",
				success : function(map) {
					if (map.result > 0) {
						toastr.success(map.message);
						$('#roomTypeTable').bootstrapTable('refresh');
					} else {
						toastr.error(map.message);
					}
				}
			});
			});
		});
		
		//新增或修改时点击确定按钮
		$('#btn_confirm').click(function() {
			  //  console.log("hhahh");
			    doAddroomType();
		});
		
		//点击查询按钮
		$('#btn_room_type_query').click(function() {
			$('#roomTypeTable').bootstrapTable("refresh");
		});
	}
	return oButtonInit;
}

//该变房源状态
function changeRoomTypeStatus() {
	$('#roomTypeTable').bootstrapTable("refresh");
}

//添加房间类型
function doAddroomType(){
	$.ajax({
		url:"./roomType/addRoomType",
		data:$("#roomTypeInfoForm").serialize(),
    	type:"post",
    	dataType : 'JSON',
		success : function(map) {
            if (map.result>0) {
            	toastr.success(map.message);
            	$('#roomTypeTable').bootstrapTable("refresh"); // 添加成功之后刷新表格
            	$('#room_type_modal').modal('hide'); // 隐藏模态框
            } else {
            	toastr.error(map.message);
            	$('#room_type_modal').modal('hide');
            }
		}
    });
}

//获取选中行的id
function getIdSelections() {
	return $.map($('#roomTypeTable').bootstrapTable('getSelections'),
			function(row) {
				return row.roomTypeId;
			});
}
