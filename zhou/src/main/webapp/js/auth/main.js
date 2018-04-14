$(function() {
	//点击标签时跳转到该页面
	$('.menuTabs').on('click', '.menuTab', activeTab);
	
	//点击关闭按钮
	$('.menuTabs').on('click', '.menuTab i', closeTab);
	
	//点击向左侧移动按钮
	$('.tabLeft').on('click', scrollTabLeft);
	
	//点击向右侧移动按钮
	$('.tabRight').on('click', scrollTabRight);
	
	//点击刷新按钮
	$('.tabReload').on('click', refreshTab);
	
    //点击全部关闭按钮
	$('.tabCloseAll').on('click', closeAllTab);
	
	//点击关闭其他按钮
	$('.tabCloseOther').on('click', closeOtherTab);
});

function menuItem(obj) {
	//获取标志数据
	var dataUrl = $(obj).attr('href'), 
	    menuName = $.trim($(obj).text()), 
	    flag = true;

	if (dataUrl == undefined || $.trim(dataUrl).length == 0) {
		return false;
	}

	// 选项卡菜单已存在
	$('.menuTab').each(function() {
		if ($(this).data('id') == dataUrl) {
			//如果不是选中当前选项卡
			if (!$(this).hasClass('active')) {
				//找到其他的选项卡移除active的类
				$(this).addClass('active').siblings('.menuTab').removeClass('active');
				//滚动到当前选项卡
				scrollToTab(this);
				// 显示tab对应的内容区
				$('#mainFrame').attr('src', dataUrl);
			}
			flag = false;
			return false;
		}
	});
	// 选项卡菜单不存在
	if (flag) {
		var str = '<a href="javascript:;" class="active menuTab" data-id="'
				+ dataUrl + '">' + menuName
				+ ' <i class="fa fa-times-circle"></i></a>';
		//将当前的menuTab移除active类
		$('.menuTab').removeClass('active');

		// 添加选项卡对应的iframe
		$("#mainFrame").attr("src", dataUrl);

		// 添加选项卡
		$('.menuTabs .page-tabs-content').append(str);
		scrollToTab($('.menuTab.active'));
	}
	return false;
}

//计算元素集合的总宽度
function calSumWidth(elements) {
    var width = 0;
    $(elements).each(function() {
    	//返回元素的宽度
        width += $(this).outerWidth(true);
    });
    return width;
}

//滚动到指定选项卡
function scrollToTab(element) {
    var marginLeftVal = calSumWidth($(element).prevAll()),
    marginRightVal = calSumWidth($(element).nextAll());
    // 可视区域非tab宽度
    var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
    //可视区域tab宽度
    var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
    //实际滚动宽度
    var scrollVal = 0;
    
    var prevElementWidth = 0;
    var nextElementWidth = 0;
    if($(element).prev()[0] != undefined) {
    	prevElementWidth = $(element).prev().outerWidth(true);
    }
    
    if($(element).next()[0] != undefined) {
    	nextElementWidth = $(element).next().outerWidth(true);
    }
    
    
    if ($(".page-tabs-content").outerWidth() < visibleWidth) {
        console.log("if");
    	scrollVal = 0;
    } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - nextElementWidth)) {
        console.log("else if1");
    	if ((visibleWidth - nextElementWidth) > marginRightVal) {
            scrollVal = marginLeftVal;
            var tabElement = element;
            while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                scrollVal -= $(tabElement).prev().outerWidth();
                tabElement = $(tabElement).prev();
            }
        }
    } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - prevElementWidth)) {
    	console.log("else if2");
    	scrollVal = marginLeftVal - prevElementWidth;
    }
    $('.page-tabs-content').animate({
        marginLeft: 0 - scrollVal + 'px'
    },
    "fast");
}



//点击选项卡菜单
function activeTab() {
    if (!$(this).hasClass('active')) {
        var currentId = $(this).data('id');
        // 显示tab对应的内容区
        $('#mainFrame').attr("src", currentId);
        $(this).addClass('active').siblings('.menuTab').removeClass('active');
        scrollToTab(this);
    }
}

//关闭选项卡菜单
function closeTab() {
    var closeTabId = $(this).parents('.menuTab').data('id');
    var currentWidth = $(this).parents('.menuTab').width();
    // 当前元素处于活动状态
    if ($(this).parents('.menuTab').hasClass('active')) {
        // 当前元素后面有同辈元素，使后面的一个元素处于活动状态
        if ($(this).parents('.menuTab').next('.menuTab').length) {

            var activeId = $(this).parents('.menuTab').next('.menuTab:eq(0)').data('id');
            $(this).parents('.menuTab').next('.menuTab:eq(0)').addClass('active');

            /*$('#mianFrame').each(function() {
                if ($(this).data('id') == activeId) {
                    $(this).show().siblings('.RuoYi_iframe').hide();
                    return false;
                }
            });*/

            var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
            if (marginLeftVal < 0) {
                $('.page-tabs-content').animate({
                    marginLeft: (marginLeftVal + currentWidth) + 'px'
                },
                "fast");
            }

            //  移除当前选项卡
            $(this).parents('.menuTab').remove();

            $('#mainFrame').attr('src', activeId);
            // 移除tab对应的内容区
            /*$('#mianFrame').each(function() {
                if ($(this).data('id') == closeTabId) {
                    $(this).remove();
                    return false;
                }
            });*/
        }

        // 当前元素后面没有同辈元素，使当前元素的上一个元素处于活动状态
        if ($(this).parents('.menuTab').prev('.menuTab').length) {
            var activeId = $(this).parents('.menuTab').prev('.menuTab:last').data('id');
            $(this).parents('.menuTab').prev('.menuTab:last').addClass('active');
            /*$('#mianFrame').each(function() {
                if ($(this).data('id') == activeId) {
                    $(this).show().siblings('.RuoYi_iframe').hide();
                    return false;
                }
            });*/

            //  移除当前选项卡
            $(this).parents('.menuTab').remove();

            $('#mainFrame').attr('src', activeId);
            // 移除tab对应的内容区
            /*$('#mianFrame').each(function() {
                if ($(this).data('id') == closeTabId) {
                    $(this).remove();
                    return false;
                }
            });*/
        }
    }
    // 当前元素不处于活动状态
    else {
        //  移除当前选项卡
        $(this).parents('.menuTab').remove();

        // 移除相应tab对应的内容区
        /*$('#mainFrame').each(function() {
            if ($(this).data('id') == closeTabId) {
                $(this).remove();
                return false;
            }
        });*/
        scrollToTab($('.menuTab.active'));
    }
    return false;
}

//标签栏向左移动
function scrollTabLeft() {
	//判断当前元素前面有没有同辈元素，有就使上一个元素处于活动状态
	if ($(".active").prev().length) {
		 var activeId = $('.active').prev('.menuTab').data('id');
		 //将前一个的标签添加active样式
         $('.active').prev('.menuTab').addClass('active');
         //移除自己的active样式
         $('.active:last').removeClass('active');
         //滚动到指定的标签页
         scrollToTab($('.menuTab .active'));
         //显示相应的页面
         $('#mainFrame').attr("src", activeId);
	}
}

//标签栏向右移动
function scrollTabRight() {
	//判断当前元素前面有没有同辈元素，有就使下一个元素处于活动状态
	if ($(".active").next().length) {
		 var activeId = $('.active').next('.menuTab').data('id');
		 //将下一个的标签添加active样式
        $('.active').next('.menuTab').addClass('active');
        //移除自己的active样式
        $('.active:first').removeClass('active');
        //滚动到指定的标签页
        scrollToTab($('.menuTab .active'));
        //显示相应的页面
        $('#mainFrame').attr("src", activeId);
	}
}

//点击刷新按钮
function refreshTab() {
	var activeId = $('.active').data('id');
	$('#mainFrame').attr('src', activeId);
}

//点击全部关闭按钮
function closeAllTab() {
	//移除除首页以外的标签，并选中首页
	$('.menuTab:gt(0)').remove();
	$('.menuTab').addClass('active');
	
	//显示首页内容
	
}

//点击关闭其他按钮
function closeOtherTab() {
	//移除 除首页和自己以外的所有标签
	$('.menuTab').not(':first').not('.active').remove();
}
