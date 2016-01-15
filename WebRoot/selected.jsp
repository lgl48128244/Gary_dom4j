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

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<title>JS使用方向键控制选择项目的文本框</title>
<style type="text/css">
#divSelect {
	border: 1px solid red;
	width: 208px !important;
	width: 210px;
}

#divSelect ul {
	width: 200px;
	margin: 3px;
	margin-left: -35px;
	*margin-left: 3px;
	overflow: hidden;
}

#divSelect ul li {
	float: left;
	list-style-type: none;
	width: 45px;
	height: 14px;
	line-height: 20px;
	font: 14px arial;
	text-align: center;
	padding: 2px;
}

#divSelect li:hover {
	background: green;
	cursor: pointer;
}

#txtInput {
	width: 205px;
}
</style>
</head>

<body>
	<form method="post" action="##">
		<input type="text" id="txtInput" value="" autocomplete="off" onkeydown="if(event.keyCode==13)return false;">
		<!-- 防止回车键触发表单提交onKeyPress -->
		<div id="divSelect"></div>
		<script type="text/javascript">
			var list = "<ul>"
			list += "<li>123a</li><li>战争片</li><li>动作片</li><li>爱情片</li><li>剧情片</li><li>记录片</li><li>fbac</li><li>bbca</li><li>defe</li><li>gzza</li><li>恐怖片</li><li>古装片</li><li>电视剧</li><li>读书</li><li >小说</li><li>作品集</li><li>历史</li><li>诗歌</li><li >散文</li><li>军事</li>";
			list += "</ul>"
			document.getElementById('divSelect').innerHTML = list;
		</script>
	</form>
	<script type="text/javascript">
		function $(sId) {
			return document.getElementById(sId);
		}
		function clearSelectedOptBgColor(target) {
			if (target.seletedIndex >= 0)
				target.options[target.seletedIndex].style.background = "";
		}
		function setSelectedOptBgColor(target) {
			target.options[target.seletedIndex].style.background = "green";
		}
		var upKeyCode = 38;
		var downKeyCode = 40;
		var enterKeyCode = 13;
		var oInput = $("txtInput");
		oInput.options = $("divSelect").getElementsByTagName("li");
		oInput.seletedIndex = -1;
		oInput.focus();
		//oInput.onKeyPress{}
		oInput.onkeyup = function(event) {
			if (event == undefined)
				event = window.event;
			switch (event.keyCode) {
			case 37://向左
				clearSelectedOptBgColor(this);
				this.seletedIndex--;
				if (this.seletedIndex < 0)
					this.seletedIndex = this.options.length - 1;
				this.value = this.options[this.seletedIndex].innerHTML;
				setSelectedOptBgColor(this);
				break;
			case 38://向上
				clearSelectedOptBgColor(this);
				this.seletedIndex = this.seletedIndex - 4;
				if (this.seletedIndex < 0)
					this.seletedIndex = this.options.length - 1;
				this.value = this.options[this.seletedIndex].innerHTML;
				setSelectedOptBgColor(this);
				break;
			case 39://向右
				clearSelectedOptBgColor(this);
				this.seletedIndex++;
				if (this.seletedIndex >= this.options.length)
					this.seletedIndex = 0;
				this.value = this.options[this.seletedIndex].innerHTML;
				setSelectedOptBgColor(this);
				break;
			case 40://向下
				clearSelectedOptBgColor(this);
				this.seletedIndex = this.seletedIndex + 4;
				if (this.seletedIndex >= this.options.length)
					this.seletedIndex = 0;
				this.value = this.options[this.seletedIndex].innerHTML;
				setSelectedOptBgColor(this);
				break;
			case enterKeyCode:
				this.value = this.options[this.seletedIndex].innerHTML;
				//alert('aa')
				break;
			}
		};
		oInput.onblur = function() {
			clearSelectedOptBgColor(this);
			this.seletedIndex = 1;
		};
	</script>
</body>
</html>
