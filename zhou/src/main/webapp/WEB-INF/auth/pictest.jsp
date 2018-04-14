<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图片测试</title>
<link rel="stylesheet" type="text/css" href="css/personalCenter/cropper.min.css">
<link rel="stylesheet" type="text/css" href="css/personalCenter/ImgCropping.css">
</head>
<body>
<body style="background: rgb(238, 238, 238);">

<button id="replaceImg" class="l-btn">更换图片</button>
<div style="width: 320px;height: 320px;border: solid 1px #555;padding: 5px;margin-top: 10px">
    <img id="finalImg" src="" width="100%">
</div>


<!--图片裁剪框 start-->
<div style="display: none" class="tailoring-container">
    <div class="black-cloth" onclick="closeTailor(this)"></div>
    <div class="tailoring-content" style="top: -197.5px; left: 375.5px;">
            <div class="tailoring-content-one">
                <label title="上传图片" for="chooseImg" class="l-btn choose-btn">
                    <input type="file" accept="image/jpg,image/jpeg,image/png" name="file" id="chooseImg" class="hidden" onchange="selectImg(this)">
                    选择图片
                </label>
                <div class="close-tailoring" onclick="closeTailor(this)">×</div>
            </div>
            <div class="tailoring-content-two">
                <div class="tailoring-box-parcel">
                    <img id="tailoringImg">
                </div>
                <div class="preview-box-parcel">
                    <p>图片预览：</p>
                    <div class="square previewImg"></div>
                    <div class="circular previewImg"></div>
                </div>
            </div>
            <div class="tailoring-content-three">
                <button class="l-btn cropper-reset-btn">复位</button>
                <button class="l-btn cropper-rotate-btn">旋转</button>
                <button class="l-btn cropper-scaleX-btn">换向</button>
                <button class="l-btn sureCut" id="sureCut">确定</button>
            </div>
        </div>
</div>
<!--图片裁剪框 end-->

<script type="text/javascript" src="js/personalCenter/cropper.min.js"></script>
<script>

    //弹出框水平垂直居中
    (window.onresize = function () {
        var win_height = $(window).height();
        var win_width = $(window).width();
        if (win_width <= 768){
            $(".tailoring-content").css({
                "top": (win_height - $(".tailoring-content").outerHeight())/2,
                "left": 0
            });
        }else{
            $(".tailoring-content").css({
                "top": (win_height - $(".tailoring-content").outerHeight())/2,
                "left": (win_width - $(".tailoring-content").outerWidth())/2
            });
        }
    })();

    //弹出图片裁剪框
    $("#replaceImg").on("click",function () {
        $(".tailoring-container").toggle();
    });
    //图像上传
    function selectImg(file) {
        if (!file.files || !file.files[0]){
            return;
        }
        var imgBase64 = '';
        //调用函数，对图片进行压缩 
        compress(file.files[0], function(imgBase64){
        	imgBase64 = imgBase64;    //存储转换的base64编码  
        	$('#tailoringImg').cropper('replace', imgBase64,false);//默认false，适应高度，不失真
        });
        /* var reader = new FileReader();
        reader.onload = function (evt) {
            var replaceSrc = evt.target.result;
            
            //更换cropper的图片
            $('#tailoringImg').cropper('replace', replaceSrc,false);//默认false，适应高度，不失真
        }
        reader.readAsDataURL(file.files[0]); */
    }
    //cropper图片裁剪
    $('#tailoringImg').cropper({
        aspectRatio: '1/1',//默认比例
        preview: '.previewImg',//预览视图
        guides: false,  //裁剪框的虚线(九宫格)
        autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
        movable: false, //是否允许移动图片
        dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
        movable: true,  //是否允许移动剪裁框
        resizable: true,  //是否允许改变裁剪框的大小
        zoomable: false,  //是否允许缩放图片大小
        mouseWheelZoom: false,  //是否允许通过鼠标滚轮来缩放图片
        touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
        rotatable: true,  //是否允许旋转图片
        crop: function(e) {
            // 输出结果数据裁剪图像。
        }
    });
    //旋转
    $(".cropper-rotate-btn").on("click",function () {
        $('#tailoringImg').cropper("rotate", 45);
    });
    //复位
    $(".cropper-reset-btn").on("click",function () {
        $('#tailoringImg').cropper("reset");
    });
    //换向
    var flagX = true;
    $(".cropper-scaleX-btn").on("click",function () {
        if(flagX){
            $('#tailoringImg').cropper("scaleX", -1);
            flagX = false;
        }else{
            $('#tailoringImg').cropper("scaleX", 1);
            flagX = true;
        }
        flagX != flagX;
    });

    //裁剪后的处理
    $("#sureCut").on("click",function () {
        if ($("#tailoringImg").attr("src") == null ){
            return false;
        }else{
            var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
            var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
            $("#finalImg").prop("src",base64url);//显示为图片的形式

            //关闭裁剪框
            closeTailor();
        }
    });
    //关闭裁剪框
    function closeTailor() {
        $(".tailoring-container").toggle();
    }


    
	// 不对图片进行压缩，直接转成base64  
	function directTurnIntoBase64(fileObj, callback) {
		var r = new FileReader();
		// 转成base64  
		r.onload = function() {
			//变成字符串  
			imgBase64 = r.result;
			console.log(imgBase64);
			callback(imgBase64);
		}
		r.readAsDataURL(fileObj); //转成Base64格式  
	}

	// 对图片进行压缩  
	function compress(fileObj, callback) {
		if (typeof (FileReader) === 'undefined') {
			console.log("当前浏览器内核不支持base64图标压缩");
			//调用上传方式不压缩    
			directTurnIntoBase64(fileObj, callback);
		} else {
			try {
				var reader = new FileReader();
				alert("onload前");
				//调用reader.readAsDataURL方法时触发的load方法
				reader.onload = function(e) {
					alert("onload后");
					
					var image = $('<img/>');
					
					alert("image load 前");
					//image.load(function() {
					image.on('load',function() {
						alert("image load 后");
						square = 700, //定义画布的大小，也就是图片压缩之后的像素  
						canvas = document.createElement('canvas'),
								context = canvas.getContext('2d'),
								imageWidth = 0, //压缩图片的大小  
								imageHeight = 0, offsetX = 0, offsetY = 0,
								data = '';

						canvas.width = square;
						canvas.height = square;
						context.clearRect(0, 0, square, square);

						if (this.width > this.height) {
							imageWidth = Math.round(square * this.width
									/ this.height);
							imageHeight = square;
							offsetX = -Math.round((imageWidth - square) / 2);
						} else {
							imageHeight = Math.round(square * this.height
									/ this.width);
							imageWidth = square;
							offsetY = -Math.round((imageHeight - square) / 2);
						}
						context.drawImage(this, offsetX, offsetY, imageWidth,
								imageHeight);
						var data = canvas.toDataURL('image/jpeg');
						//压缩完成执行回调    
						callback(data);
					});
					image.attr('src', e.target.result);
				};
				reader.readAsDataURL(fileObj);
			} catch (e) {
				console.log("压缩失败!");
				//调用直接上传方式  不压缩   
				directTurnIntoBase64(fileObj, callback);
			}
		}
	}
</script>


</body>
</html>