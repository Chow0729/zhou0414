<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>菜单管理</title>
</head>
<body>
    <div class="panel-body" style="padding-bottom: 0px;">
        <div class="panel panel-default">
            <div class="panel-heading">查询条件</div>
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal"
                    onsubmit="return false;">
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
                                    onclick="selectedFiled(this)">菜名</a></li>
                                <li><a href="javascript:void(0);"
                                    style="padding-left: 15px; font-weight: 700"
                                    onclick="selectedFiled(this)">种类</a></li>
                                <li><a href="javascript:void(0);"
                                    style="padding-left: 15px; font-weight: 700"
                                    onclick="selectedFiled(this)">菜价</a></li>
                                <li><a href="javascript:void(0);"
                                    style="padding-left: 15px; font-weight: 700"
                                    onclick="selectedFiled(this)">库存</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="javascript:void(0);"
                                    style="padding-left: 15px; font-weight: 700"
                                    onclick="selectedFiled(this)">全部</a></li>
                            </ul>
                        </div>
                        <div class="col-sm-3" style="position: relative;">
                            <input type="text" class="form-control" id="txt_search_condition"
                                placeholder="请输入查询内容">
                        </div>
                        <div class="col-sm-3">
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
            <button id="btn_delete" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
            </button>
        </div>
        <table id="table"></table>
    </div>
    
    <!-- 添加菜单模态框 开始 -->
    <div class="modal fade" id="food_modal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <form class="form-horizontal" id="foodInfoForm">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">添加菜单</h4>
                    </div>
                    <div class="modal-body">
                        <div class="panel-body" style="padding-bottom: 0px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">请填写菜品信息</div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-6 form-group">
                                            <label class="col-sm-4 control-label" for="txt_name">菜名</label>
                                            <div class="col-sm-8">
                                                <input class="form-control"
                                                    id="txt_name" name="name" type="text"
                                                    placeholder="请输入菜名" />
                                            </div>
                                          </div>
                                          <div class="col-sm-6 form-group">
                                            <label class="col-sm-4 control-label" for="txt_imageFile">显示图片</label>
                                            <div class="col-sm-8">
                                                <input id="txt_imageFile" name="file" type="file" />
                                            </div>
                                          </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6 form-group">
                                            <label class="col-sm-4 control-label" for="txt_originalPrice">原价</label>
                                            <div class="col-sm-8">
                                                <input class="form-control"
                                                    id="txt_originalPrice" name="originalPrice" type="text"
                                                    placeholder="请输入原价" oninput="if(isNaN(value))execCommand('undo')"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 form-group">
                                            <label class="col-sm-4 control-label" for="txt_price">售价</label>
                                            <div class="col-sm-8">
                                                    <input class="form-control" id="txt_price" oninput="if(isNaN(value))execCommand('undo')"
                                                        name="price" type="text" placeholder="请输入售价" />
                                                </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6 form-group">
                                            <label class="col-sm-4 control-label" for="txt_stock">库存</label>
                                            <div class="col-sm-8">
                                                <input class="form-control" oninput="if(isNaN(value))execCommand('undo')"
                                                    id="txt_stock" name="stock" type="text"
                                                    placeholder="请输入库存" />
                                            </div>
                                        </div>
                                        <div class="col-sm-6 form-group">
                                            <label class="col-sm-4 control-label" for="txt_cateId">种类</label>
                                            <div class="col-sm-8">
                                                    <select class="form-control" id="txt_cateId" name="categoryId"> 
                                                        <option value="0" data-pinyin="">请选择种类</option>
                                                        <c:forEach items="${allCates }" var="cate" >
                                                        <option value="${cate.categoryId }" data-pinyin="${cate.pinyinName }">${cate.categoryName }</option>
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12 form-group">
                                            <label class="col-sm-2 control-label" for="txt_remark">备注</label>
                                            <div class="col-sm-10" style="padding-left: 10px;padding-right: 45px">
                                                <input class="form-control"
                                                    id="txt_remark" name="remark" type="text"
                                                    placeholder="请输入备注" />
                                            </div>
                                        </div>
                                    </div>   
                                   <!--  隐藏域    --> 
                                   <input type="hidden" name="pinyinName" id="hidden_pinyin"/>
                                   <input type="hidden" name="foodId" id="hidden_foodId"/>
                                   <input type="hidden" name="imageUrl" id="hidden_imageUrl"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                        </button>
                        <button type="submit" class="btn btn-primary" id="btn_confirm"
                            name="submit">
                            <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确认
                        </button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal -->
        </form>
    </div>
    <!-- 添加菜单模态框 结束 -->
    
    <script type="text/javascript">
    var requestUrl = "${requestUrl}";
    </script>
    <script type="text/javascript" src="js/business/food_manage.js"></script>
    <script type="text/javascript" src="js/auth/plugins/component.js"></script>
</body>
</html>