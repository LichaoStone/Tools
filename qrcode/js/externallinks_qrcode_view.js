// 初始化页面
$(document).ready(function() {
	//初始化二维码
	$('#qrcodeCanvas').qrcode({
          text:$("#url").val(),//二维码代表的字符串（本页面的URL）
          width:300,//二维码宽度
          height:300//二维码高度
     });
});