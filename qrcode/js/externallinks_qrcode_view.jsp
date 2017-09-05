<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/framework/commons/page_init.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>二维码</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="keywords" content="轻快云开放平台管理后台">
	<meta http-equiv="description" content="轻快云开放平台管理后台">
	<link href="<%=basePath%>/framework/css/page_list.css" rel="stylesheet">
	<link href="<%=basePath%>/framework/js/bootstrap/css/bootstrap-table.css" rel="stylesheet">

	<script type="text/javascript" src="<%=basePath%>/framework/js/bootstrap/js/bootstrap-table.js"></script>
	<script type="text/javascript" src="<%=basePath%>/framework/js/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>/app/appservice/externallinks/js/externallinks_qrcode_view.js"></script>
	<script type="text/javascript" src="<%=basePath%>/app/appservice/externallinks/js/jquery.qrcode.min.js"></script>
<body>
<form id="form" name="form" method="post" action="">
    <input type="hidden"  id="url" name="url"  value="${bean.url}" /> 
    <!-- 用于展示二维码 -->
    <div id="qrcodeCanvas" style="text-align: center;margin-top: 30px;"></div>
</form>
</body>
</html>
