$(function() {
	//初始化表格
	var oTableInit1 = new TableInit1();
	oTableInit1.Init();
	
	//初始化按钮
	var oButton1 = new ButtonInit1();
	oButton1.Init();
});

// 表格
var TableInit1 = function() {
	var oTableInit1 = new Object();
	oTableInit1.Init = function() {
		$('#roomTable').bootstrapTable(
						{
							url : './room/getAllRoomByPage',
							contentType : "application/x-www-form-urlencoded", // 必须要有！！！！
							method : 'post', // 请求方式（*）
							striped : true, // 是否显示行间隔色
							cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
							pagination : true,
							sortable : false, // 是否启用排序
							sortOrder : "asc", // 排序方式
							queryParams : oTableInit1.queryParams, // 传递参数（*）
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
							uniqueId : "roomId", // 每一行的唯一标识，一般为主键列
							toolbar : "#RoomToolbar", // 工具按钮用哪个容器
							columns : [
									{
										checkbox : true
									},
									{
										field : 'roomId',
										title : '房间编号',
										align : 'center',
										visible : false
									},
									{
										field : 'roomName',
										title : '房间名称',
										align : 'center'
									},
									{
										field : 'status',
										title : '房间状态',
										align : 'center'
									},
									{
										field : 'roomType',
										title : '房间类型',
										align : 'center'
									},
									{
										field : 'remark',
										title : '备注',
										align : 'center',
										width : '5%'
									}, /*{
										field : 'status',
										title : '状态',
										align : 'center'
									}, {
										field : 'remark',
										title : '备注',
										align : 'center'
									},*/
									{
										field : 'roleId',
										title : '操作',
										align : 'center'/*,
										formatter :showDetail*/
									}]
						});
	};

	// 得到查询的参数
	oTableInit1.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			start : params.offset, // 从数据库第几条记录开始,页码
			pageSize : params.limit,
			//status : $("#txt_search_roomTypeStatus").val(),  //是否有房
			selectedFiled : $("#serchFiledSelceted").text(),  //查询条件
			condition : $("#txt_search_condition").val()  //查询内容
		}
		console.log(temp);
		return temp;
	}
	return oTableInit1;
}

var ButtonInit1 = function() {
	var oButtonInit1 = new Object();
	oButtonInit1.Init = function() {
		//点击新增按钮
		$('#btn_add_room').click(function() {
			$('#room_modal').modal("show");
		});
		
		//点击修改按钮
		$('#btn_edit_room').click(function() {
			
		});
		
		// 点击删除按钮
		$('#btn_delete_room').click(function() {
			doDeleteroom();
		});
		
		//新增或修改时点击确定按钮
		$('#btn_confirm_room').click(function() {
			doAddroom();
		});
		
		//点击查询按钮
		$('#btn_room_query').click(function() {
			$('#roomTable').bootstrapTable("refresh");
		});
	}
	return oButtonInit1;
}

//添加房间
function doAddroom(){
	alert("hahahha");
	$.ajax({
		url:"./room/saveRoom",
		data:$("#roomInfoForm").serialize(),
    	type:"post",
    	dataType : 'JSON',
		success : function(map) {
            if (map.result>0) {
            	toastr.success(map.message);
            	$('#roomTable').bootstrapTable("refresh"); // 添加成功之后刷新表格
            	$('#room_modal').modal('hide'); // 隐藏模态框
            } else {
            	toastr.error(map.message);
            	$('#room_modal').modal('hide');
            }
		}
    });
}
function doDeleteroom(){
	var ids=[];
	var rows = $('#roomTable').bootstrapTable('getSelections');
	for(var i=0; i<rows.length; i++) {
		ids[i] = rows[i].roomId;
	}
	$.ajax({
		url:"./room/deleteRoom",
		//data:$("#roomInfoForm").serialize(),
		data:{"ids":ids.toString()},
    	type:"post",
    	//dataType : 'JSON',
		success : function(map) {
            if (map.result>0) {
            	toastr.success(map.message);
            	$('#roomTable').bootstrapTable("refresh"); // 添加成功之后刷新表格
            	$('#room_modal').modal('hide'); // 隐藏模态框
            } else {
            	toastr.error(map.message);
            	$('#room_modal').modal('hide');
            }
		}
    });
}