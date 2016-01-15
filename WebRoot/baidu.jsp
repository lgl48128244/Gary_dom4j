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

<title>My JSP 'baidu.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

a {
	text-decoration: none;
}

ul,li {
	list-style: none;
}

body {
	font-size: 14px;
	font-family: "微软雅黑"
}

/*设置背景*/
.bg {
	/* background:
		url("http://imgstore.cdn.sogou.com/app/a/100540002/714894.jpg")
		no-repeat; */
	background: url("image/bg.jpg") no-repeat;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-size: cover;
	z-index: -1;
}

#container {
	width: 640px;
	height: 70px;
	margin: 260px auto;
}

.inputs {
	width: 560px;
	height: 45px;
	border: none;
	float: left;
	text-indent: 1em;
	font-family: "微软雅黑";
	box-shadow: inset 0 0 1em #ccc
}

.searchBtn {
	height: 45px;
	background: #fff;
	display: inline-block;
	float: left;
	width: 77px;
	text-align: center;
	line-height: 45px;
	border-left: 1px solid #ccc;
	font-size: 16px;
}

.searchBox {
	border: 1px solid #ccc;
	height: 45px;
}

#autoBox {
	display: none;
}

#autoBox li {
	height: 35px;
	border-bottom: 1px solid #ccc;
	line-height: 35px;
	padding-left: 10px;
	font-size: 12px;
}

#autoBox li:HOVER {
	background: #eaeaea;
}

.itemBox {
	width: 300px;
	background: #fff;
	position: absolute;
	height: 100%;
	top: 0;
	left: 0;
}

.itemBox li {
	overflow: hidden;
}

.items {
	height: 32px;
	border-bottom: 1px solid #ccc;
	line-height: 32px;
	transition: all 1s ease;
	padding-left: 20px;
	position: relative;
	padding-left: 20px;
	position: relative;
}

.showpan {
	position: absolute;
	top: 0;
	left: 260px;
	width: 100%;
	height: 100%;
	background: red;
}
</style>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript">
	$(function() {
		$(".items").append("<div class='showpan'></div>");
		$(".items").hover(function() {
			$(this).find(".showpan").css("background", randomColor()).stop(true, true).animate({
				left : 0
			});
		}, function() {
			$(this).find(".showpan").css("background", randomColor()).stop(true, true).animate({
				left : 260
			});
		});
		function randomColor() {
			var r = Math.floor(Math.random() * 256);
			var g = Math.floor(Math.random() * 256);
			var b = Math.floor(Math.random() * 256);
			return "rgb(" + r + "," + g + "," + b + ")";
		}
	});
	window.onload = function() {
		//实现jquery中的css方法
		var keyBoxDOM = function(id) {
			var dom = document.getElementById(id);
			return {
				css : function(opts) {
					for ( var key in opts) {
						var v = opts[key];
						if (typeof v == "number") {
							v += "px";
						}
						dom["style"][key] = v;
					}
				}
			}
		}
		window.keyBoxDOM = keyBoxDOM;
		//获取input输入框
		var keybox = document.getElementById("keyBox");
		//获取自动提示框
		var autobox = document.getElementById("autoBox");
		//获取下拉框
		var ulbox = document.getElementById("ulBox");
		//获取按钮ID
		var btnChang = document.getElementById("btnChang");
		var index = -1;
		//var index = 0;
		//重新绑定事件
		keybox.onkeyup = autoComplete;
		keybox.onkeydown = autoComplete;
		function autoComplete() {
			//拿到input 的宽度和高度
			var inw = this.offsetWidth;
			var inh = this.offsetHeight;
			//输入框值
			var value = this.value;
			//验证
			var check = value.match(/@/ig);
			if (check && check.length > 1) {
				this.value = value.substring(0, value.length - 1);
				return;
			}
			if (value.indexOf("@") != -1) {
				keyBoxDOM("autoBox").css({
					display : "block",
					width : inw,
					height : "215",
					marginTop : 45,
					background : "#f9f9f9"
				})
				//拼接数据
				var html = getMailHTML(value);
				ulbox.innerHTML = html;
				//动态绑定事件
				var lis = ulbox.children;
				for (var i = 0; i < lis.length; i++) {
					lis[i].onclick = function() {
						keybox.value = this.innerHTML;
						keybox.onkeyup = autoComplete;
						keybox.onkeydown = autoComplete;
					}
				}
			}
		}
		//通过键盘控制
		document.onkeyup = function(e) {
			//键码38 40
			var ev = e || window.event;
			var code = ev.keyCode || ev.which;
			//获取所有的li
			var lis = ulbox.children;
			/* if (code == 40) {//向下
				keybox.onkeyup = null;
				keybox.onkeydown = null;
				if (index == lis.length) {
					index = 0;
				}
				for (var i = 0; i < lis.length; i++) {
					lis[i].style.background = "none";
				}
				lis[index].style.background = "#ccc";
				keybox.value = lis[index].innerHTML;
				index++;

			}
			if (code == 38) {//向上
				
			} */
			if (code == 40) {//向下
				keybox.onkeyup = null;
				keybox.onkeydown = null;
				if (index == lis.length - 1) {
					index = 0;
				} else {
					index++;
					if (index > lis.length - 1) {
						index = 0;
					}
				}
				if (index != -1) {
					for (var i = 0; i < lis.length; i++) {
						lis[i].style.background = "none";
					}
					lis[index].style.background = "#ccc";
					keybox.value = lis[index].innerHTML;
				}
			}
			if (code == 38) {//向上
				if (index == -1) {
					index = lis.length - 1;
				} else {
					index--;
					if (index < 0) {
						index = lis.length - 1;
					}
				}
				if (index != -1) {
					for (var i = 0; i < lis.length; i++) {
						lis[i].style.background = "none";
					}
					lis[index].style.background = "#ccc";
					keybox.value = lis[index].innerHTML;
				}
			}
			if (code == 13) {//回车键
				autobox.style.display = "";
			}
		}
		function getMailHTML(value) {
			var arr = [ "163.com", "126.com", "qq.com", "gmail.com", "sohu.com", "sina.com" ];
			var html = "";
			for (var i = 0; i < arr.length; i++) {
				//html += "<li onclick='selectMe(this);'>" + value + arr[i] + "</li>";
				html += "<li>" + value + arr[i] + "</li>";
			}
			return html;
		}
		//静态绑定
		function selectMe(obj) {
			document.getElementById("keybox") = obj.innerHTML;
		}
		autobox.onmouseleave = function() {
			this.style.display = "none";
		}
		btnChang.onmouseover = function() {
			this.style.background = "#eaeaea";
		}
		btnChang.onmouseleave = function() {
			this.style.background = "#f9f9f9";
		}
	}
</script>
</head>

<body>
	<!-- 设置背景 -->
	<div class="bg"></div>
	<div id="container">
		<div class="searchBox" style="">
			<input type="text" class="inputs" id="keyBox" placeholder="请输入邮箱" /><a href="" class="searchBtn" id="btnChang">搜索</a>
			<div id="autoBox">
				<ul id="ulBox">
				</ul>
			</div>
		</div>
	</div>
	<div>
		<ul class="itemBox">
			<li class="items">0</li>
			<li class="items">1</li>
			<li class="items">2</li>
			<li class="items">3</li>
			<li class="items">4</li>
			<li class="items">5</li>
			<li class="items">6</li>
			<li class="items">7</li>
			<li class="items">8</li>
			<li class="items">9</li>
			<li class="items">0</li>
			<li class="items">1</li>
			<li class="items">2</li>
			<li class="items">3</li>
			<li class="items">4</li>
			<li class="items">5</li>
			<li class="items">6</li>
			<li class="items">7</li>
			<li class="items">8</li>
			<li class="items">9</li>
			<li class="items">0</li>
			<li class="items">1</li>
			<li class="items">2</li>
		</ul>
	</div>
</body>
</html>
