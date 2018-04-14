$(function(){
    //初始化Table
    TableInit().Init();
    
    //提示框js初始化
    toastr.options.positionClass = 'toast-top-center';
    
    //初始化表单验证
    verification();
})

//增删改查请求地址
var companyUrl="dept/";
var insertUrl=companyUrl+"insertDepartment";
var deleteUrl=companyUrl+"deleteDepartments";
var updateUrl=companyUrl+"updateDepartment";
var thisUrl;
var selectRows;


//添加按钮
$("#btn_add").click(function(){
    $("#myModalLabel").html("新增部门信息");
    $("#myModal").modal('show');
    thisUrl = "./dept/addDept";
})

//更新按钮
$("#btn_edit").click(function(){
    selectRows = $("#tb_departments").bootstrapTable('getSelections');
    if(selectRows.length!=1){
        toastr.warning("请选择一条要修改的部门信息");
        return;
    }
    fillModal(selectRows);
    $("#myModalLabel").html("更新部门信息");
    $("#myModal").modal('show');
    thisUrl = "./dept/editDept";
})

//删除按钮
$("#btn_delete").click(function(){
    selectRows = $.map($("#tb_departments").bootstrapTable('getSelections'), function(row) {
        return row.depId;
    });
    if(selectRows.length<1){
        toastr.warning("请选择要删除的部门");
        return;
    }
    $("#delcfmModel").modal('show');
})

//删除确认按钮
function delUrlSubmit(){
	var ids = selectRows.toString();
    $.ajax({
        type:"post",
        url:"./dept/delDept",
        data:{
        	"deptIds" : ids
        },
        async:true,
        success:function(result){
        	if (result > 0) {
        		toastr.success("删除成功");
        	} else {
        		toastr.warning("删除失败");
        	}
            $("#tb_departments").bootstrapTable('refresh');
        },
        error:function(){
            toastr.error("删除失败")
        }
    });
}

//提交按钮
$(".btn-primary").click(function(){
    var bootstrapValidator = $("#deptInfo").data('bootstrapValidator');
    bootstrapValidator.validate();
    if(!bootstrapValidator.isValid()){return;}
    $.ajax({
        type:"post",
        url:thisUrl,
        data:$("#deptInfo").serialize(),
        async:true,
        success:function(result){
            $("#tb_departments").bootstrapTable('refresh');
            $("#myModal").modal('hide');
            toastr.success("操作成功") 
        },
        error:function(){
           toastr.error("操作失败") 
        }
    });
})

//模态框隐藏事件绑定
$("#myModal").on('hidden.bs.modal', function(){
    $("#deptInfo")[0].reset();
    $("#deptInfo").data('bootstrapValidator').destroy();
    verification();
})

//更新时，模态框数据填充
function fillModal (selectRows){
    $("input[name='depId']").val(selectRows[0].depId);
    $("input[name='depNo']").val(selectRows[0].depNo);
    $("input[name='depName']").val(selectRows[0].depName);
    $("textarea[name='remark']").val(selectRows[0].remark);
    $("input[name='createdTime']").val(selectRows[0].createdTime);
}
// 表单验证
var verification = function() {
    $('#deptInfo').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            depNo:{
                validators:{
                    notEmpty:{
                        message:'部门编号不能为空'
                    },
                    stringLength:{
                        max:32,
                        message:'部门编号最长为32个字符'
                    }
                }
            },
            depName:{
                validators:{
                    notEmpty:{
                        message:"部门名称不能为空"
                    },
                    stringLength:{
                        max:32,
                        message:'部门名称最长为32个字符'
                    }
                }
            },
            remark:{
                validators:{
                    stringLength:{
                        max:200,
                        message:'备注最长为200个字符'
                    }
                }
            }
            
        }
    });
}

//表格的属性配置
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_departments').bootstrapTable({
            url: './dept/getAllDeptsPage',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'depNo',
                title: '部门编号',
                align: 'center',
            }, {
                field: 'depName',
                title: '部门名称',
                align: 'center',
            }, {
                field: 'remark',
                title: '备注',
                align: 'center',
            }, {
                field: 'editTime',
                title: '更新时间',
                align: 'center',
            }, {
                field: 'createdTime',
                title: '创建时间',
                align: 'center',
            }, ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            search : params.search,//模糊查询的条件
        };
        return temp;
    };
    return oTableInit;
};

