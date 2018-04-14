<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../taglibs.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>天鹅养生苑</title>
<link href="css/auth/style.css" rel="stylesheet">
<link href="css/auth/main.css" rel="stylesheet">
<style type="text/css">
a:hover {
    text-decoration: none;
}

a:focus {
    text-decoration: none;
}

a.dropdown-toggle {
    height: 53px;
}

.nav>li>a.dropdown-toggle, .nav>li>a.dropdown-toggle:focus, .nav>li>a.dropdown-toggle:hover
    {
    background-color: #333333 !important;
    color: #f5f5f5;
    bottom: 10px;
    right: 55px;
}

img#userPic {
    vertical-align: middle;
    width: 32px;
    height: 32px;
    border-radius: 50%;
    margin-right: 10px;
}
</style>
</head>
<body>
    <div id="container" class="container-fluid">
        <header class="container-fluid mainContainer"
            style="border-bottom: solid 2px #080808;overflow: visible;">
            <div class="row">
                <div id="top-title" class="col-xs-4 col-sm-6 col-md-2 col-md-offset-5">
                    <b>天鹅养生苑</b>
                </div>
                <div id="top-loginMessage" class="col-xs-8 col-md-5 col-sm-6 pull-right" style="text-align: right;">
                    <ul class="nav navbar-right">
                        <!--Speech Icon-->
                        <li class="dropdown user-box show-on-hover">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"> 
                                <img id="userPic" alt="头像" src="./img/timg.jpg" class="headImage">
                                <label style="margin: 0px; padding-right: 5px;"> ${sessionScope.User.realName }</label>
                                <span class="caret" style="margin-left: 0px;"></span>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" style="right: 40px;">
                                <li><a href="./login_out.do"> <i
                                        class="fa fa-power-off"></i> 注销
                                </a> <a href="javascript:void(0)" onclick="showInIframe(this,'./pc/personalData')"> <i class="fa fa-gear"></i>
                                        账号设置
                                </a> <a href="javascript:void(0)" onclick="showInIframe(this,'./changePass/changePassword')"> <i class="fa fa-edit"></i>修改密码
                                </a></li>
                            </ul></li>
                    </ul>
                    
                </div>
            </div>
        </header>
        <div id="page" class="clearfix container-fluid mainContainer">
            <div class="row">
				<!-- 左侧导航菜单 开始 -->
				<div id="navMenu" class="col-xs-3 col-sm-3 col-md-2 sidebar"
                    style="padding: 0px;">
                    <ul class="nav nav-sidebar">
                        <c:forEach items="${menus}" var="menu">
							<li class="menu-parent">
							    <a href="#${menu.resourceName }" class="nav-header menu-first collapsed" data-toggle="collapse" onclick="changeIcon(this)">
							        <i class="${menu.icon }"></i>&nbsp;${menu.resourceName}<i class="fa fa-plus-circle pull-right" style="padding-top: 10px"></i>
							    </a>
							    <c:forEach items="${menu.children }" var="m">
							        <ul id="${menu.resourceName }" class="nav nav-list collapse menu-second">
							            <li><a href="${m.resourceUrl }" class="menuItem" onclick="menuItem(this)"><i class="${m.icon }"></i>${m.resourceName}</a>
							            </li>
							        </ul>
							    </c:forEach>
							</li>
						</c:forEach>
                    </ul>
                </div>
				<!-- 左侧导航菜单 结束 -->

				<!-- 右侧iframe 开始 -->
                <div class="col-xs-9 col-sm-9 col-md-10 main" style="padding: 0px;">
                    <!-- 导航路径 开始 -->
                    <!-- <ol class="breadcrumb" id="navPath" style="margin-bottom: 0">                      
                    </ol>  -->
                    <div class="row content-tabs">
                        <button class="roll-nav roll-left tabLeft">
                            <i class="fa fa-backward"></i>
                        </button>
                        <nav class="page-tabs menuTabs">
                            <div class="page-tabs-content">
                                <a href="javascript:;" class="active menuTab">首页</a>
                            </div>
                            
                        </nav>
                        <button class="roll-nav roll-right tabRight">
                            <i class="fa fa-forward"></i>
                        </button>
                        <div class="btn-group roll-nav roll-right">
                            <button class="dropdown J_tabClose" data-toggle="dropdown">
                                页签操作<span class="caret"></span>
                            </button>
                            <ul role="menu" class="dropdown-menu dropdown-menu-right">
                                <li class="tabReload"><a>刷新</a></li>
                                <li class="tabCloseAll"><a>全部关闭</a></li>
                                <li class="tabCloseOther"><a>关闭其他</a></li>
                            </ul>
                        </div>
                        <a href="./login_out.do" class="roll-nav roll-right J_tabExit"><i
                            class="fa fa fa-sign-out"></i> 退出</a>
                    </div>
                    <!-- 导航路径 结束 -->
                    <!-- iframe开始 -->
                    <iframe style="height: 95%; width: 100%; border: 0" id="mainFrame" name="mainFrame"></iframe>
                    <!-- iframe结束 -->
                </div>
                <!-- 右侧iframe 结束 -->
            </div>
        </div>
        <footer class="container-fluid mainContainer"> Copyright ©
            2017杭州坦佩科技有限公司 | 浙ICP备15002550 </footer>
    </div>
    <script type="text/javascript">
        $(function() {
            if (window != top) {
                top.location.href = location.href;
            }
            
            //获取iframe  在iframe中点击时关闭下拉框
            var fr = document.getElementById("mainFrame");  
            //iframe单独加载，需要在加载时创建监听点击事件  
            fr.onload = function(e){  
                //点击iframe事件  
                fr.contentDocument.onclick = function(){ 
                    var menu = $('.dropdown-toggle')[0];
                    var userbox = $('.user-box');
                    var ariaexpanded = menu.getAttribute("aria-expanded");
                    if(ariaexpanded == 'true') {
                        userbox.attr("class", "dropdown user-box show-on-hover");
                        menu.setAttribute('aria-expanded', "false");
                    }
                }
                e.preventDefault();
            }  
        });
        
        //
        function showInIframe(obj,url) {
            $("#mainFrame").attr("src", url);
        }
        
        
        //切换图标
        function changeIcon(obj) {
            //获取所有的目录节点
            $first = $('.menu-parent');

            var count = 0;
            $first.on('click', function(e) {
                if (++count == 1) {
                    //将其他的图标都改为"+"
                    $first.not(this).find('.menu-first i.pull-right').attr("class",
                            "fa fa-plus-circle pull-right");
                    //切换自己的图标
                    $(this).find('.menu-first i.pull-right').toggleClass('fa fa-plus-circle fa fa-minus-circle');
                }
                e.preventDefault();
            });
            
            //菜单栏点开一个，关闭其他
            var $children = $('.menu-second'), //获取所有的“子”
            $parent = $('.menu-parent'); // 获取所有的"父”
            $parent.on('click', function(e) {// 当“父”发生点击事件
                    if ($(this).find('.menu-second').is(':hidden')) {
                        $parent.find('.menu-second').collapse("hide");// 关闭其他
                        $(this).find('.menu-second').collapse("show");// 显示自己
                    }
                    e.preventDefault();
                });
        }

        //点击导航条内容时清空元素后面的兄弟元素
        function emptyNav(obj) {
            //获取当前的li元素
            var $obj = $(obj).parent().parent();
            //清空当前li元素后面的兄弟元素
            $obj.nextAll().remove();
        }
    </script>
    <script type="text/javascript" src="js/auth/main.js"></script>
    <!-- <script type="text/javascript" src="/js/auth/plugins/jquery.slimscroll.min.js"></script> -->
</body>
</html>