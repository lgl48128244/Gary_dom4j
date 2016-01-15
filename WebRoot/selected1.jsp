<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'selected1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
ul,li {
	list-style-type: none;
}
</style>
<script type="text/javascript" language="javascript">
	var currentSelIndex = -1;
	var oldSelIndex = -1;

	function selectItem(keyword, event) {
		if (keyword == "") {
			document.getElementById("ulItems").style.display = "none";
			return;
		} else {
			var liLength = document.getElementById("ulItems").getElementsByTagName("li").length; //获取列表数量
			if ((event.keyCode == 38 || event.keyCode == 40) && document.getElementById("ulItems").style.display != "none") {
				if (liLength > 0) {
					oldSelIndex = currentSelIndex;
					//上移
					if (event.keyCode == 38) {
						if (currentSelIndex == -1) {
							currentSelIndex = liLength - 1;
						} else {
							currentSelIndex = currentSelIndex - 1;
							if (currentSelIndex < 0) {
								currentSelIndex = liLength - 1;
							}
						}
						if (currentSelIndex != -1) {
							document.getElementById("li_" + currentSelIndex).style.backgroundColor = "#cbf3fd";
							document.getElementById("txtKeyword").value = document.getElementById("li_" + currentSelIndex).innerText;
						}
						if (oldSelIndex != -1) {
							document.getElementById("li_" + oldSelIndex).style.backgroundColor = "#ffffff";
						}
					}
					//下移
					if (event.keyCode == 40) {
						if (currentSelIndex == liLength - 1) {
							currentSelIndex = 0;
						} else {
							currentSelIndex = currentSelIndex + 1;
							if (currentSelIndex > liLength - 1) {
								currentSelIndex = 0;
							}
						}
						if (currentSelIndex != -1) {
							document.getElementById("li_" + currentSelIndex).style.backgroundColor = "#cbf3fd";
							document.getElementById("txtKeyword").value = document.getElementById("li_" + currentSelIndex).innerText;
						}
						if (oldSelIndex != -1) {
							document.getElementById("li_" + oldSelIndex).style.backgroundColor = "#ffffff";
						}
					}
				}
			} else if (event.keyCode == 13) {
				if (document.getElementById("ulItems").style.display != "none" && liLength > 0 && currentSelIndex != -1) {
					document.getElementById("txtKeyword").value = document.getElementById("li_" + currentSelIndex).innerText;
					document.getElementById("ulItems").style.display = "none";
					currentSelIndex = -1;
					oldSelIndex = -1;
				}
			} else {
				autoComplete(keyword);
				document.getElementById("ulItems").style.display = "";
				currentSelIndex = -1;
				oldSelIndex = -1;
			}
		}
	}

	function autoComplete(keyword) {
		var liHtml0 = "<li id=\"li_0\">1</li><li id=\"li_1\">12</li><li id=\"li_2\">123</li><li id=\"li_3\">1234</li>";
		var liHtml1 = "<li id=\"li_0\">12</li><li id=\"li_1\">123</li><li id=\"li_2\">1234</li>";
		var liHtml2 = "<li id=\"li_0\">123</li><li id=\"li_1\">1234</li>";
		var liHtml3 = "<li id=\"li_0\">1234</li>";
		if (keyword == "1234") {
			document.getElementById("ulItems").innerHTML = liHtml3;
		} else if (keyword == "123") {
			document.getElementById("ulItems").innerHTML = liHtml2;
		} else if (keyword == "12") {
			document.getElementById("ulItems").innerHTML = liHtml1;
		} else if (keyword == "1") {
			document.getElementById("ulItems").innerHTML = liHtml0;
		} else {
			document.getElementById("ulItems").innerHTML = "";
		}
	}
</script>
</head>
<body>
	<input id="txtKeyword" type="text" onkeyup="selectItem(this.value, event)" style="width:200px;" />
	<ul id="ulItems" style="display: none; border:1px solid #cecece; border-top:none; width:200px; padding:2px; margin:0px;">
	</ul>
</body>
</html>