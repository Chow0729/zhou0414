var isEdit = false;

//提示框toastr的显示控制
toastr.options = {
	"positionClass" : "toast-top-center",
	"timeOut" : "1500"
}

//初始化日期插件
$('.date-picker').datepicker({
  autoclose: true,
	clearBtn: true,
	pickerPosition : 'top-right'//日期插件弹出的位置
}); 

//初始化页面
$(function() {
	//初始化table
	var oTable = new TableInit();
	oTable.Init();
	
	//初始化button
	var oButton = new ButtonInit();
	oButton.Init();
	
	//初始化表单验证
	formValidator();
	
	//初始化时间控件
	$('.date-picker').datepicker();
});

var TableInit = function() {
	var oTableInit = new Object();
	//初始化Table
	oTableInit.Init = function() {
		$('#table').bootstrapTable({
			url : './user/getAllUsersByPage', //请求后台的URL（*）
			contentType : "application/x-www-form-urlencoded", // 必须要有！！！！
			method : 'post', //请求方式（*）
			toolbar : '#toolbar', //工具按钮用哪个容器
			striped : true, //是否显示行间隔色
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, //是否显示分页（*）
			sortable : true, //是否启用排序
			sortOrder : "asc", //排序方式
			queryParams : oTableInit.queryParams,//传递参数（*）
			sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, //初始化加载第一页，默认第一页
			pageSize : 10, //每页的记录行数（*）
			pageList : [ 10, 15, 20, 100 ], //可供选择的每页的行数（*）
			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			showColumns : true, //是否显示所有的列
			showRefresh : true, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : true, //是否启用点击选中行
			/*height : 500,*/ //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "userId", //每一行的唯一标识，一般为主键列
			showToggle : true, //是否显示详细视图和列表视图的切换按钮
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			columns : [ {
				checkbox : true
			}, {
				field : 'userId',
				title : '编号',
				sortable : true
			},{
				field : 'userName',
				title : '用户名称'
				/*clickToSelect: false*/
			}, {
				field : 'realName',
				title : '真实姓名'
			}, {
				field : 'sex',
				title : '性别',
				formatter : function(value, row, index) {
					if (value == 1) {
						return '男'
					} else if (value == 2) {
						return '女'
					} else {
						return '-'
					}
				}
			}, {
				field : 'phone',
				title : '电话'
			}, 
			 {
				field : 'mail',
				title : '邮箱'
			}, {
				field : 'isenable',
				title : '状态',
				formatter : function(value, row, index) {
					if (value == 1) {
						return "<span style='color:green;'>可用</span>";
					} else {
						return "<span style='color:red'>禁用</span>";
					}
				}
			},
			{
				field : 'depName',
				title : '部门'
			}, 
			{
				field : 'position',
				title : '职位'
			}, {
				field : 'createdTime',
				title : '创建日期'
			}]
		});
	};

	//得到查询的参数
	oTableInit.queryParams = function(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			start : params.offset, //页码
			pageSize : params.limit, //页面大小
			selectedFiled : $("#serchFiledSelceted").text(),
			condition : $("#txt_search_condition").val(),
			isenable:$("#txt_isenable").val(),
            sortOrder:this.sortOrder //排序方式（desc，asc）
		};
		return temp;
	};
	return oTableInit;
};

//按钮
var ButtonInit = function() {
	var oInit = new Object();
	oInit.Init = function() {
		// 点击查询按钮
		$("#btn_query").click(function() {
			$('#table').bootstrapTable("refresh");
		});

		//点击新增按钮
		$('#btn_add').click(function() {
			$('#myTaba a:first').tab('show');
			isEdit = false;
			$("#myModalLabel").text("");
			$("#myModalLabel").text("添加用户基本信息");
			$("#otherTab").text("");
			$("#otherTab").text("添加用户部门信息");
			inputValue('');
			//开启模态框
			$('#user_modal').modal('show');
		});
		

	    // 点击修改按钮
		$('#btn_edit').click(function() {
			$('#myTaba a:first').tab('show')
			isEdit = true;
			$("#myModalLabel").text("修改用户基本信息");
			$("#otherTab").text("修改用户部门信息");
			var ids = getIdSelections(); // 获取选中行的id
			if (ids == "") {
				toastr.warning("请选择要修改的数据");
				return;
			}
			if (ids.length > 1) {
				toastr.info("选择了多条数据,请选择修改一条数据!");
			} else {
				//打开模态框
				$('#user_modal').modal("show");
				$.ajax({
					url : "./user/getUserWithDeptByUserId?userId=" + ids[0],
					type : "post",
					dataType : "json",
					success : function(result) {
						inputValue(result); // 打开模态框的时候显示要修改的user数据
					}
				});
			}
		});
		
		// 提交按钮点击事件
		$('#btn_submit').click(function() {
			if (isEdit) {
				userEdit();
			} else {
				userAdd(); //调用添加的方法
			}
		});
		
		// 点击停用按钮
		$('#btn_disable').click(function() {
			var ids = getIdSelections().toString();
			if (ids == "") {
				toastr.warning("请选择要停用的用户");
			} else {
				Ewin.confirm({
					message : "确认要删除选择的数据吗？"
				}).on(function(e) {
					if (!e) {
						return;
					}
					editUsersStatus(ids, 0);
				});
			}
		});

		// 点击彻底删除按钮
		$('#btn_delete').click(function() {
			var ids = getIdSelections().toString();
			if (ids == "") {
				toastr.warning("请选择要删除的用户");
			} else {
				Ewin.confirm({
					message : "确认要删除选择的用户吗？"
				}).on(function(e) {
					if (!e) {
						return;
					}
					delUsers(ids);
				});
			}
		});

		// 点击启用按钮
		$('#btn_enable').click(function() {
			var ids = getIdSelections().toString();
			if (ids == "") {
				toastr.warning("请选择要启用的用户");
			} else {
				var a = confirm("确定要启用用户？");
				if (a) {
					editUsersStatus(ids, 1);
				}
			}
		});
		
		//点击用户部门信息标签
		$('#otherTab').click(function() {
			if (isEdit == false) {
				$('#userInfo').data('bootstrapValidator').validate();
				if (!$('#userInfo').data('bootstrapValidator').isValid()) {
					$("#otherTab").attr("href", "#");
					toastr.warning("请先添加用户必填信息");
				} else {
					$("#btn_submit").attr("disabled", false);
					$("#otherTab").attr("href", "#addOthers");
				}
			} else {
				$("#otherTab").attr("href", "#addOthers");
			}
		});
	};
	return oInit;
};

// 模态框
var ModalInit = function() {
	var oModalInit = new Object();
	oModalInit.Init = function() {
		$('#user_modal').modal({
			keyboard : true
		});

	}
	return oModalInit;
}

//获取选中的查询条件
function selectedFiled(obj) {
	var thisObj = $(obj);
	var selectedFiled = thisObj.text();
	$('#serchFiledSelceted').text(selectedFiled);
}

//Modal验证销毁重构
$('#user_modal').on('hidden.bs.modal', function() {
    $("#userInfo").data('bootstrapValidator').destroy();
    $('#userInfo').data('bootstrapValidator', null);
    formValidator();
});

//获取选中行的id
function getIdSelections() {
	return $.map($('#table').bootstrapTable('getSelections'),
			function(row) {
				return row.userId;
			});
}

//添加时的方法
function userAdd() {
	// 开启验证
	$('#userInfo').data('bootstrapValidator').validate();
	if (!$('#userInfo').data('bootstrapValidator').isValid()) {
		return;
	}
	
	$.ajax({
		url : "./user/addUser",
		type : "post",
		data : new FormData($("#userInfo")[0]),
		dataType : "json",
		contentType: false,
        processData: false,
		success : function(jsonresult) {
			if (jsonresult.data > 0) {
				toastr.success(jsonresult.message);
				$('#user_modal').modal('hide');
				$('#table').bootstrapTable('refresh');
			} else {
				toastr.error(jsonresult.message);
			}
		}
	});
}

function userEdit() {
	// 开启验证
	$('#userInfo').data('bootstrapValidator').validate();
	if (!$('#userInfo').data('bootstrapValidator').isValid()) {
		return;
	}
	
	$.ajax({
		url : "./user/editUser",
		type : "post",
		data : new FormData($("#userInfo")[0]),
		dataType : "json",
		contentType: false,
        processData: false,
		success : function(jsonresult) {
			if (jsonresult.data > 0) {
				toastr.success(jsonresult.message);
				$('#user_modal').modal('hide');
				$('#table').bootstrapTable('refresh');
			} else {
				toastr.error(jsonresult.message);
			}
		}
	});
}

//批量修改用户
function editUsersStatus(ids, status) {
	$.ajax({
		type : "post",
		url : "./user/editUsersStatus",
		data : {
			"ids" : ids,
			"status" : status
			},
		dataType : 'JSON',
		//contentType : "application/json",
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
				$('#table').bootstrapTable('refresh');
			} else {
				toastr.error(result.message);
				$('#table').bootstrapTable('refresh');
			}
		}
	});
}

//批量删除用户
function delUsers(ids) {
	$.ajax({
		type : "post",
		url : "./user/delUsers",
		data : {
			"ids" : ids,
			},
		dataType : 'JSON',
		success : function(result) {
			if (result.data > 0) {
				toastr.success(result.message);
			} else {
				toastr.error(result.message);
			}
			$('#table').bootstrapTable('refresh');
		}
	});
}

function isEnable() {
	var status=$("#txt_isenable").val();
	if(status==0){
		$("#btn_delete").attr("disabled", false); 
		$("#btn_disable").attr("disabled", true); 
		$("#btn_enable").attr("disabled",false);
		 $('#table').bootstrapTable('refresh');
	}else if(status==1){
		$("#btn_delete").attr("disabled", true); 
		$("#btn_disable").attr("disabled",false); 
		$("#btn_enable").attr("disabled",true);
	    $('#table').bootstrapTable('refresh');
	}
}

// 弹出模态框的时候给模态框里的表单赋初值
function inputValue(user) {
	$("#txt_username").val(user.user_name);
	$("#txt_password").val(user.password);
	$("#txt_realname").val(user.real_name);
	$("#txt_native").val(user.native_place);
	$("#txt_live").val(user.living_place);
	$("#txt_phone").val(user.phone);
	$("#txt_mail").val(user.mail);
	$("#txt_birthday").val(user.birth_date);
	var date = new Date();
	if (user.created_time != undefined) {
		date.setTime(user.created_time);
		$("#createdTime").val(date.Format("yyyy-MM-dd hh:mm:ss"));
	}
	
	var picUrl=user.pic_url;
	
	$("#txt_remark").val(user.remark);
	$("#hidden_pic").val(picUrl);  //隐藏域，用于保存用户的头像地址
	if (user == "") {
		$('#isenableInput').prop("checked", true);
		$('#isenableInput0').prop("checked", false);
	} else {
		if (user.isenable == 1) {
			$('#isenableInput').prop("checked", "checked");
			$('#isenableInput0').prop("checked", false);
		} else if (user.isenable == 0) {
			$('#isenableInput0').prop("checked", "checked");
			$('#isenableInput').prop("checked", false);
		}
		if(user.sex==1){
			$('#sexInput').prop("checked", "checked");
			$('#sexInput0').prop("checked", false);
		}else if(user.sex==2){
			$('#sexInput').prop("checked", false);
			$('#sexInput0').prop("checked", "checked");
		}else{
			$('#sexInput').prop("checked", false);
			$('#sexInput0').prop("checked", false);
		}
		if(user.marriage==1){
			$('#ismarrInput').prop("checked", true);
			$('#ismarrInput0').prop("checked", false);
		}else if(user.marriage==2){
			$('#ismarrInput').prop("checked", false);
			$('#ismarrInput0').prop("checked", true);
		}else{
			$('#ismarrInput').prop("checked", false);
			$('#ismarrInput0').prop("checked", false);
		}
	}
	//用户部门信息中的部门下拉框
	if (user!="" && user.dep_id != undefined && user.dep_id != null) {
		$("#dep").val(user.dep_id);  //部门
	}
	$("#txt_empNo").val(user.emp_no);  //工号
	$("#txt_position").val(user.position);  //职位
	$("#txt_title").val(user.job_title);  //职称
	$("#txt_salary").val(user.salary);  //薪资
	$("#txt_worked").val(user.worked_time);  //工作时长
	if (user.entry_time != undefined && user.entry_time != null) {
		date.setTime(user.entry_time);
		$("#txt_entry").val(date.Format("yyyy-MM-dd"));  //入职时间
	}
	if (user.regular_time != undefined && user.regular_time != null) {
		date.setTime(user.regular_time);
		$("#txt_regular").val(date.Format("yyyy-MM-dd"));  //转正时间
	}
	
	//隐藏域的值
	$('#userId').val(user.user_id);  //用户id
	$('#ucId').val(user.uc_id);  // userCompany的id
}


//校验字段
function formValidator(){
$("#userInfo").bootstrapValidator({
	message: 'This value is not valid',
	feedbackIcons: { /*输入框不同状态，显示图片的样式*/
		valid: 'glyphicon glyphicon-ok',
		invalid: 'glyphicon glyphicon-remove',
		validating: 'glyphicon glyphicon-refresh'
	},
	fields: {
		userName: {
			message: '用户名验证失败',
			validators: {
				notEmpty: {
					message: '用户名不能为空'
				}/*,stringLength: {
                    min: 5,
                    max: 30,
                    message: '用户名长度必须在5到18位之间'
                }*//*,
                regexp: {
                    regexp: /^[a-zA-Z0-9_]+$/,
                    message: '用户名只能包含大写、小写、数字和下划线'
                }*/
            }
        },
	
		phone: {
			message: 'The phone is not valid',
			validators: {
				notEmpty: {
					message: '用户手机不能为空'
				},
				regexp: {
					regexp: /^1[3|5|8]{1}[0-9]{9}$/,
					message: '请输入正确的手机号码'
				}
			}
		},
		password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                }
            }
        },
		realName: {
                validators: {
                    notEmpty: {
                        message: '真实姓名不能为空'
                    }
                }
            },
        mail: {
            validators: {
                notEmpty: {
                    message: '邮箱不能为空'
                },
                emailAddress: {
                    message: '邮箱地址格式有误'
                }
            }
        },
        idcard: {
            validators: {
              /*  notEmpty: {
                    message: '身份证号不能为空'
                },*/
                regexp: {
					regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
					message: '请输入正确的身份证号码'
				}
            }
        }
		
	}
});
}

function operateTMSRPFormatter2(value, row, index) {
	var date = new Date();
	date.setTime(row.updatedTime);
	var html = ('<span>' + date.Format("yyyy-MM-dd") + '</span>');
	return html;
}

Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1,
					(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
							.substr(("" + o[k]).length)));
	return fmt;
}