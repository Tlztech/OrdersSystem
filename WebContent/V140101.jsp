<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/commonjs.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	function doAction() {
		var div_obj = $("#pop-div");
		//添加并显示遮罩层   
		$("#mask").show("slow");
		div_obj.show("slow");
		var logKey = "";
		logKey = _getRandomString(10);
		actionSubmit('A14010104' + '?logKey=' + logKey);
		setInterval(function() {
			getLog(logKey);
		}, 2000);
	}
	function hideDiv() {
		$("#mask").hide();
		$("#pop-div").hide();
	}

	function init() {
		hideDiv();
	}
	function doAction2() {
		var div_obj = $("#pop-div");
		//添加并显示遮罩层   
		$("#mask").show("slow");
		div_obj.show("slow");
		var logKey = "";
		logKey = _getRandomString(10);
		actionSubmit('A14010105' + '?logKey=' + logKey);
		setInterval(function() {
			getLog(logKey);
		}, 2000);
	}

	function doAction3() {
		actionSubmit('A14010106');
	}
	function doAction4() {
		var div_obj = $("#pop-div");
		//添加并显示遮罩层   
		$("#mask").show("slow");
		div_obj.show("slow");
		var logKey = "";
		logKey = _getRandomString(10);
		actionSubmit('A14010107' + '?logKey=' + logKey);
		setInterval(function() {
			getLog(logKey);
		}, 2000);
	}
</script>
<style type="text/css">
<!--
body {
	font-family: "微锟斤拷锟脚猴拷,Verdana, 锟斤拷锟斤拷锟斤拷";
	color: #1E5494; /*锟斤拷锟斤拷锟斤拷锟斤拷色*/
	font-size: 12px;
	font-weight: bolder;
	margin-left: 4px;
	margin-top: 0px;
	margin-right: 0px;
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 0px;
	PADDING-TOP: 0px;
}

td {
	color: #1E5494;
	font-size: 12px;
	line-height: 18px;
}

select {
	font-size: 12px;
}

a {
	text-decoration: none
}

#div1 {
	width: 99%;
	position: relative;
	top: 0px;
	height: 10%;
	margin-left: auto;
	margin-right: auto;
}

#div2 {
	top: 20px;
	width: 99%;
	overflow-x: hidden;
	position: relative;
	margin-left: auto;
	margin-right: auto;
}

#div3 {
	width: 99%;
	position: relative;
	top: 19px;
	margin-left: auto;
	margin-right: auto;
}

#div5 {
	top: 20px;
	width: 99%;
	overflow-x: hidden;
	position: relative;
	margin-left: auto;
	margin-right: auto;
}

#div4 {
	height: 70px;
	width: 99%;
	overflow-y: auto;
	overflow-x: hidden;
	position: relative;
	top: 20px;
	margin-left: auto;
	margin-right: auto;
}

.pop-box {
	z-index: 9998; /*这个数值要足够大，才能够显示在最上层*/
	margin-bottom: 3px;
	position: absolute;
	background: #FFF;
	border: solid 1px #6e8bde;
}

.pop-box h4 {
	color: #FFF;
	cursor: default;
	height: 18px;
	font-size: 14px;
	font-weight: bold;
	text-align: left;
	padding-left: 8px;
	padding-top: 4px;
	padding-bottom: 2px;
	background: url("../images/header_bg.gif") repeat-x 0 0;
}

.pop-box-body {
	clear: both;
	margin: 4px;
	padding: 2px;
}

.mask {
	color: #C7EDCC;
	background-color: #C7EDCC;
	position: absolute;
	top: 0px;
	left: 0px;
	filter: Alpha(Opacity = 60);
	-moz-opacity: .6;
	opacity: 0.6;
	z-index: 1000;
}
-->
</style>
</head>
<body onload="init();">
	<%
		Connection conn = null;
	List<String> siteList = new ArrayList<String>();
	List<String> shopList = new ArrayList<String>();
	try {
		conn = com.rakuten.util.JdbcConnection.getConnection();
		String sql_site = "SELECT distinct SITE FROM rakuten.shop";
		PreparedStatement ps = conn.prepareStatement(sql_site);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			siteList.add(rs.getString("SITE").replace(" ", "+"));
		}
		String sql = "SELECT distinct SHOP_ID FROM rakuten.shop";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			shopList.add(rs.getString("SHOP_ID"));
		}

	} catch (Exception e) {
		out.println(e);
	} finally {
		conn.close();
	}
	%>
	<div id='mask' class="mask" style="width: 100%; height: 100%;"></div>
	<s:form name="form1" theme="simple" method="post"
		enctype="multipart/form-data">
		<br />
		<br />
		<select name="site">
			<%
				for (int i = 0; i < siteList.size(); i++) {
			%>
			<option value=<%=siteList.get(i)%>><%=siteList.get(i)%></option>
			<%
				}
			%>
		</select>
		<select name="shop">
			<%
				for (int i = 0; i < shopList.size(); i++) {
			%>
			<option value=<%=shopList.get(i)%>><%=shopList.get(i)%></option>
			<%
				}
			%>
		</select>
		<input type="button" onclick="actionSubmit('A14010102')"
			value="下载最新预警列表">
		<br />
		<br />
		<br />
		<br />
		<s:file name="xlsFile" />
		<input type="button" onclick="actionSubmit('A14010103')"
			value="上传最新预警列表">
		<!--<input type="button" onclick="doAction()" value="同步库存">-->
		<input type="button" onclick="doAction4()" value="同步全部楽天、Yahoo、AU库存">
		<input type="button" onclick="doAction4()" value="同步全部楽天、Yahoo、AU库存（每周一次）">
		<br />
		<br />
		<br />
		<br />
		<input type="button" onclick="doAction3()" value="下载库存csv">
		<s:file name="file" />
		<input type="button" onclick="doAction2()" value="上传库存csv">
		<s:actionerror name="err" cssStyle="color:red;font-size:13" />
		<div id='pop-div'
			style="left: 350px; top: 100px; width: 450px; height: 260px; position: absolute;"
			class="pop-box">
			<div class="pop-box-body">
				<table>
					<tr>
						<td align="center"><textarea id="logArea"
								style="width: 400px; height: 200px" readOnly></textarea></td>
					</tr>
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="关闭"></td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
</body>

</html>