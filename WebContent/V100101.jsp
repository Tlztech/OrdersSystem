<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
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
function openShinkiWin() {
    var returnVal = openShowModalDialog("1024", "700", "A10010401");
    if (returnVal) {
    	actionSubmit("A10010101");
    }
}
function init() {
    hideDiv();
	var scrollxVal = document.getElementsByName("scrollx")[0].value;
	var scrollyVal = document.getElementsByName("scrolly")[0].value;
	if(scrollxVal != ""){
		$(document).scrollLeft(scrollxVal);
	}
	if(scrollyVal != ""){
		$(document).scrollTop(scrollyVal);
	}
	if(document.getElementsByName("successFlg")[0].value == "1"){
		alert("成功しました！");
		document.getElementsByName("successFlg")[0].value = "0";
	}
	
	if(document.getElementsByName("f100101.todokeset")[0].value == "1"){
		document.getElementsByName("f100101.todokeset")[0].value = "0";
	}
}
function popupDiv() {   
    var div_obj = $("#pop-div"); 
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}   
function popupDiv2() {   
    var div_obj = $("#pop-div2"); 
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}   
function popupDiv3() {   
    var div_obj = $("#pop-div3"); 
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}   
function popupDiv4() {   
    var div_obj = $("#pop-div4"); 
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}
function popupDiv5() {   
    var div_obj = $("#pop-div5"); 
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}
function popupDiv6() {   
    var div_obj = $("#pop-div6"); 
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}
function popupDiv7() {   
    var div_obj = $("#pop-div7"); 
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}
function popupDiv8() {   
    var div_obj = $("#pop-div8"); 
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}
function popupDivGetOrderInfoYahoo() {
    var div_obj = $("#pop-divGetOrderInfoYahoo");
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层 
    $("#mask").show("slow");
    div_obj.show("slow");
}
function popupDivGetOrderInfoAU() {
    var div_obj = $("#pop-divGetOrderInfoAU");
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层 
    $("#mask").show("slow");
    div_obj.show("slow");
}
function popupDiv9() {   
    var div_obj = $("#pop-div9"); 
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}
function popupDiv10() {   
    var div_obj = $("#pop-div10"); 
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}
function hideDiv() {   
  $("#mask").hide();   
  $("#pop-div").hide(); 
  $("#pop-div2").hide();
  $("#pop-div3").hide();
  $("#pop-div4").hide();
  $("#pop-div5").hide();
  $("#pop-div7").hide();
  $("#pop-div8").hide();
  $("#pop-divGetOrderInfoYahoo").hide();
  $("#pop-divGetOrderInfoAU").hide();
  $("#pop-div9").hide();
  $("#pop-div10").hide();
} 

function showTodoke(index){
	document.getElementsByName("otodokeSetei")[index].style.display = "none";
	document.getElementsByName("otodokeTorikeshi")[index].style.display = "";
	document.getElementsByName("otodokeKakutei")[index].style.display = "";
	document.getElementsByName("otodokebishitei")[index].style.display = "";
	document.getElementsByName("otodokejikantai1")[index].style.display = "";
	//document.getElementsByName("otodokejikantai2")[index].style.display = "";
	//document.getElementsByName("otodokejikantai3")[index].style.display = "";
	//document.getElementById("haifun" + index).style.display = "";
}

function hideTodoke(index){
	document.getElementsByName("otodokeSetei")[index].style.display = "";
	document.getElementsByName("otodokeTorikeshi")[index].style.display = "none";
	document.getElementsByName("otodokeKakutei")[index].style.display = "none";
	document.getElementsByName("otodokebishitei")[index].style.display = "none";
	document.getElementsByName("otodokejikantai1")[index].style.display = "none";
	//document.getElementsByName("otodokejikantai2")[index].style.display = "none";
	//document.getElementsByName("otodokejikantai3")[index].style.display = "none";
	//document.getElementById("haifun" + index).style.display = "none";
}

function setTodoke(index){
	var otodoke1 = document.getElementsByName("otodokebishitei")[index].value;
	var otodoke2 = document.getElementsByName("otodokejikantai1")[index].value;
	//var otodoke3 = document.getElementsByName("otodokejikantai2")[index].value;
	//var otodoke4 = document.getElementsByName("otodokejikantai3")[index].value;
	var no = document.getElementsByName("f100101.orderList["+index+"].chumonbango")[0].value;
	
	
	if(otodoke1 == "" && otodoke2 == "" && otodoke3 == "" && otodoke4 == ""){
		alert("请指定时间");
		return;
	}
	$.post("A13010108", {data1:otodoke1,data2:otodoke2,orderNo:no}, function(result) {
		var innerText = "";
		if(otodoke1!=""){
			innerText+=otodoke1 + "&nbsp;&nbsp;"
		}
		if(otodoke2!=""){
			if(otodoke2 == "01"){
				innerText+="午前中" + "&nbsp;&nbsp;";
			}else if(otodoke2 == "12"){
				innerText+="12:00~14:00" + "&nbsp;&nbsp;";
			}else if(otodoke2 == "14"){
				innerText+="14:00~16:00" + "&nbsp;&nbsp;";
			}else if(otodoke2 == "16"){
				innerText+="16:00~18:00" + "&nbsp;&nbsp;";
			}else if(otodoke2 == "04"){
				innerText+="18:00~21:00" + "&nbsp;&nbsp;";
			}
			
		}
		document.getElementById("shiteibi"+index).innerHTML = innerText;
		hideTodoke(index);
    }, "json");
	
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

#div1 {
	width: 99%;
	position: relative;
	top: 10px;
	height: 10%;
	margin-left: auto;
	margin-right: auto;
}

#div2 {
	top: 40px;
	width: 99%;
	overflow-x: hidden;
	position: relative;
	margin-left: auto;
	margin-right: auto;
}

#div3 {
	width: 99%;
	position: relative;
	top: 39px;
	margin-left: auto;
	margin-right: auto;
}

#div5 {
	top: 40px;
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
	top: 40px;
	margin-left: auto;
	margin-right: auto;
}

.pop-box {
	z-index: 9999; /*这个数值要足够大，才能够显示在最上层*/
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
	Map<String, String> rakutenMap = new HashMap<String, String>();
	Map<String, String> yahooMap = new HashMap<String, String>();
	Map<String, String> amazonMap = new HashMap<String, String>();
	Map<String, String> auMap = new HashMap<String, String>();
	Map<String, String> shopMap = new HashMap<String, String>();
	Map<String, String> siteMap = new HashMap<String, String>();
	shopMap.put("","--");
	siteMap.put("","--");
	try {
		Integer companyId = (Integer)session.getAttribute("comp");
		System.out.println("COMPANY_ID"+companyId);
		conn = com.rakuten.util.JdbcConnection.getConnection();
		String sql= "SELECT SITE, SHOP_ID FROM rakuten.shop where DELETE_FLG is null and (COMPANY_ID = " + companyId.intValue() + " OR " + companyId.intValue() + " = 0)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if ("楽天".equals(rs.getString("SITE"))) {
				rakutenMap.put(rs.getString("SHOP_ID"), rs.getString("SHOP_ID"));
			} else if ("Yahoo".equals(rs.getString("SITE"))) {
				yahooMap.put(rs.getString("SHOP_ID"), rs.getString("SHOP_ID"));
			} else if ("Amazon".equals(rs.getString("SITE"))) {
				amazonMap.put(rs.getString("SHOP_ID"), rs.getString("SHOP_ID"));
			} else if ("AU".equals(rs.getString("SITE"))) {
				auMap.put(rs.getString("SHOP_ID"), rs.getString("SHOP_ID"));
			}
			shopMap.put(rs.getString("SHOP_ID"), rs.getString("SHOP_ID"));
			siteMap.put(rs.getString("SITE"),rs.getString("SITE"));
		}
		
		request.setAttribute("rakutenmap",rakutenMap);
		request.setAttribute("yahoomap",yahooMap);
		request.setAttribute("amazonmap",amazonMap);
		request.setAttribute("aumap",auMap);
		request.setAttribute("shopmap",shopMap);
		request.setAttribute("siteMap",siteMap);

	} catch (Exception e) {
		out.println(e);
	} finally {
		conn.close();
	}
	%>
	<div id='mask' class="mask" style="width: 100%; height: 100%;"></div>
	<s:form name="form1" theme="simple" enctype="multipart/form-data">

		<div style="width: 900px; margin-top: 5px; margin-left: 10px">
			<b><s:label name="title" /></b>
			<hr>
		</div>
		<div id="div1">
			<s:actionerror name="err" cssStyle="color:red;font-size:13" />
			<table cellspacing="1" cellpadding="2" width="70%" border="0">
				<tr class="bg_tr">
					<td class="td_bg" width="140px">注文日時：</td>
					<td class="td_bg" width="300px"><s:textfield size="15"
							cssClass="Wdate" onClick="WdatePicker()" maxlength="10"
							name="f100101.kikanStart" />&nbsp;&nbsp;～&nbsp; <s:textfield
							size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10"
							name="f100101.kikanEnd" /></td>
					<td class="td_bg" width="140px">キーワード：</td>
					<td class="td_bg"><s:textfield size="15" maxlength="150"
							name="f100101.keyword" />&nbsp;&nbsp; <s:radio
							list="#{ '0':'商品名&nbsp;&nbsp;&nbsp;','1':'商品番号&nbsp;&nbsp;&nbsp;', '2':'備考'}"
							name="f100101.kewordType" /> <s:checkbox
							name="f100101.searchKeywordCondition" />商品のみを含め</td>
				</tr>
				<tr>
					<td class="td_bg">受注番号：</td>
					<td class="td_bg"><s:textfield size="35" maxlength="50"
							name="f100101.chumonbango" /></td>
					<td class="td_bg">注文メールアドレス：</td>
					<td class="td_bg"><s:textfield size="25" maxlength="250"
							name="f100101.chumonEmail" /></td>
				</tr>
				<tr>
					<td class="td_bg">注文者名前：</td>
					<td class="td_bg"><s:textfield size="25" maxlength="50"
							name="f100101.chumonshanamae" /></td>
					<td class="td_bg">送付先名前：</td>
					<td class="td_bg"><s:textfield size="25" maxlength="50"
							name="f100101.sofusakinamae" /></td>
				</tr>
				<tr>
					<td class="td_bg">店舗別：</td>
					<td class="td_bg"><s:select
							list="#request.shopmap"
							name="f100101.tenpobetsu" /></td>
					<td class="td_bg">サイト：</td>
					<td class="td_bg"><s:select
							list="#request.siteMap"
							name="f100101.site" /></td>
				<tr>
					<td class="td_bg">電話番号：</td>
					<td class="td_bg"><s:textfield size="15" maxlength="50"
							name="f100101.denwabango" />&nbsp;&nbsp;<s:select
							list="#{ '0':'注文者','1':'送付先'}" name="f100101.denwabangoType" /></td>
					<td class="td_bg">お支払い方法</td>
					<td class="td_bg"><s:select
							list="#{'':'--','クレジットカード':'クレジットカード','代金引換':'代金引換','銀行振込 ':'銀行振込 ','楽天バンク決済':'楽天バンク決済','Edy決済':'楽天Edy決済'}"
							name="f100101.oshiharaihoho" /></td>
				<tr>
					<td class="td_bg">注文ステータス：</td>
					<td class="td_bg" colspan="2">
						<table class="table" cellspacing="1" cellpadding="2" width="100%"
							border="0">
							<tr class="bg_tr">
								<td class="td_bg"><s:checkbox
										name="f100101.chumonStsSearch0"></s:checkbox>発送前確認</td>
								<td class="td_bg"><s:checkbox
										name="f100101.chumonStsSearch1"></s:checkbox>発送待ち</td>
								<td class="td_bg"><s:checkbox
										name="f100101.chumonStsSearch2"></s:checkbox>処理済</td>
								<td class="td_bg"><s:checkbox
										name="f100101.chumonStsSearch3"></s:checkbox>至急</td>
								<td class="td_bg"><s:checkbox
										name="f100101.chumonStsSearch9"></s:checkbox>保留</td>
							<tr class="bg_tr">
								<td class="td_bg"><s:checkbox
										name="f100101.chumonStsSearch4"></s:checkbox>分納</td>
								<td class="td_bg"><s:checkbox
										name="f100101.chumonStsSearch5"></s:checkbox>返品</td>
								<td class="td_bg"><s:checkbox
										name="f100101.chumonStsSearch6"></s:checkbox>追加</td>
								<td class="td_bg"><s:checkbox
										name="f100101.chumonStsSearch7"></s:checkbox>キャンセル</td>
								<td class="td_bg"><s:checkbox
										name="f100101.chumonStsSearch8"></s:checkbox>その他</td>
							</tr>
						</table>
					</td>
					<td class="td_bg" align="right"><input type="button"
						onclick="actionSubmit('A10010115?type=search')" value="下载注文csv"
						style="width: 100px; height: 25px" /></td>
					<td class="td_bg" align="right"><input type="button"
						onclick="actionSubmit('A10010101?type=search')" value="検索"
						style="width: 50px; height: 25px" /></td>
				</tr>
			</table>
			<div style="width: 900px; height: 20px; margin-top: 5px;">
				<hr>
			</div>
			<table cellspacing="1" cellpadding="2" width="95%" border="0"
				class="table">
				<tr class="bg_tr">
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=1')">発送待ち(<s:text
								name="f100101.hasomachiCount" />
							<s:hidden name="f100101.hasomachiCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=2')">追加発送待ち(<s:text
								name="f100101.tuikahasomachiCount" />
							<s:hidden name="f100101.tuikahasomachiCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=3')">分納発送待ち(<s:text
								name="f100101.bunnohasomachiCount" />
							<s:hidden name="f100101.bunnohasomachiCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=4')">発送待ち-至急(<s:text
								name="f100101.hasomachishikyuCount" />
							<s:hidden name="f100101.hasomachishikyuCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=5')">追加発送待ち-至急(<s:text
								name="f100101.tuikahasomachishikyuCount" />
							<s:hidden name="f100101.tuikahasomachishikyuCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=6')">分納発送待ち-至急(<s:text
								name="f100101.bunnohasomachishikyuCount" />
							<s:hidden name="f100101.bunnohasomachishikyuCount" />)
					</a></td>
				</tr>
				<tr class="bg_tr">
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=7')">発送待ち-発送可(<s:if
								test='f100101.hasomachihasokaCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.hasomachihasokaCount" />
							<s:if test='f100101.hasomachihasokaCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.hasomachihasokaCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=8')">追加発送待ち-発送可(<s:if
								test='f100101.tuikahasomachihasokaCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.tuikahasomachihasokaCount" />
							<s:if test='f100101.tuikahasomachihasokaCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.tuikahasomachihasokaCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=9')">分納発送待ち-発送可(<s:if
								test='f100101.bunnohasomachihasokaCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.bunnohasomachihasokaCount" />
							<s:if test='f100101.bunnohasomachihasokaCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.bunnohasomachihasokaCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=10')">発送待ち-至急-発送可(<s:if
								test='f100101.hasomachishikyuhasokaCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.hasomachishikyuhasokaCount" />
							<s:if test='f100101.hasomachishikyuhasokaCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.hasomachishikyuhasokaCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=11')">追加発送待ち-至急-発送可(<s:if
								test='f100101.tuikahasomachishikyuhasokaCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.tuikahasomachishikyuhasokaCount" />
							<s:if test='f100101.tuikahasomachishikyuhasokaCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.tuikahasomachishikyuhasokaCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=12')">分納発送待ち-至急-発送可(<s:if
								test='f100101.bunnohasomachishikyuhasokaCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.bunnohasomachishikyuhasokaCount" />
							<s:if test='f100101.bunnohasomachishikyuhasokaCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.bunnohasomachishikyuhasokaCount" />)
					</a></td>
				</tr>
				<tr class="bg_tr">
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=13')">返品待ち(<s:text
								name="f100101.henpinmachiCount" />
							<s:hidden name="f100101.henpinmachiCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=14')">返品中(<s:text
								name="f100101.henpinchuCount" />
							<s:hidden name="f100101.henpinchuCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=15')">返金待ち(<s:if
								test='f100101.henkinmachiCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.henkinmachiCount" />
							<s:if test='f100101.henkinmachiCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.henkinmachiCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=16')">送信待ち(<s:if
								test='f100101.soshinmachiCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.soshinmachiCount" />
							<s:if test='f100101.soshinmachiCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.soshinmachiCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=17')">お届け時間帯設定待ち(<s:if
								test='f100101.todokeseteimachiCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.todokeseteimachiCount" />
							<s:if test='f100101.todokeseteimachiCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.todokeseteimachiCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=18')">発送前確認(<s:if
								test='f100101.hasomaekakuninCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.hasomaekakuninCount" />
							<s:if test='f100101.hasomaekakuninCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.hasomaekakuninCount" />)
					</a></td>
				</tr>
				<tr class="bg_tr">
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=19')">入荷不可-連絡待ち(<s:if
								test='f100101.nyukafukarenrakumachiCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.nyukafukarenrakumachiCount" />
							<s:if test='f100101.nyukafukarenrakumachiCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.nyukafukarenrakumachiCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=20')">入荷不可-連絡済み(<s:if
								test='f100101.nyukafukarenrakuzumiCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.nyukafukarenrakuzumiCount" />
							<s:if test='f100101.nyukafukarenrakuzumiCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.nyukafukarenrakuzumiCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=21')">ステータス異常の注文(<s:if
								test='f100101.ijoCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.ijoCount" />
							<s:if test='f100101.ijoCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.ijoCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=22')">納期遅れ(<s:if
								test='f100101.okureCount>0'>
								<font color="red" size="4">
							</s:if>
							<s:text name="f100101.okureCount" />
							<s:if test='f100101.okureCount>0'>
								</font>
							</s:if>
							<s:hidden name="f100101.okureCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px">
						<%--                 <a href="javascript:actionSubmit('A10010103?type=26')">問題あり(<s:if test ='f100101.mondaiariCount>0'><font color="red" size="4"></s:if><s:text name="f100101.mondaiariCount"/><s:if test ='f100101.mondaiariCount>0'></font></s:if><s:hidden name="f100101.mondaiariCount"/>)</a> --%>
					</td>
					<td class="td_bg" style="font-size: 13px"></td>
				</tr>
				<tr class="bg_tr">
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=23')">入荷待ち(<s:text
								name="f100101.nyukamachiCount" />
							<s:hidden name="f100101.nyukamachiCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=24')">入荷中(<s:text
								name="f100101.nyukachuCount" />
							<s:hidden name="f100101.nyukachuCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"><a
						href="javascript:actionSubmit('A10010103?type=25')">運送中(<s:text
								name="f100101.unsochuCount" />
							<s:hidden name="f100101.unsochuCount" />)
					</a></td>
					<td class="td_bg" style="font-size: 13px"></td>
					<td class="td_bg" style="font-size: 13px"></td>
					<td class="td_bg" style="font-size: 13px"></td>
				</tr>
			</table>
		</div>
		<div id="div5">
			<table width="99%">
				<tr>
					<td>検索結果：<s:label name="f100101.resultCount" />件<s:hidden
							name="f100101.resultCount" /></td>
					<td align="right">
					<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)"><input type="button"
						onclick="actionSubmit('A10010401')" value="新規" />&nbsp;&nbsp; <input
						type="button" onclick="popupDiv2();" value="楽天注文取得" />&nbsp;&nbsp;
						<input type="button" onclick="popupDivGetOrderInfoYahoo();"
						value="Yahoo注文取得" />&nbsp;&nbsp;
						<input type="button" onclick="popupDivGetOrderInfoAU();"
						value="AU注文取得" />&nbsp;&nbsp; <input type="button"
						onclick="popupDiv7();" value="CSVから取得(Rakuten)" />&nbsp;&nbsp; <input
						type="button" onclick="popupDiv8();" value="CSVから取得(qoo10)" />&nbsp;&nbsp;
						<input type="button" onclick="popupDiv3();" value="CSVから取得(DENA)" />&nbsp;&nbsp;
						<input type="button" onclick="popupDiv4();" value="CSVから取得(Yahoo)" />&nbsp;&nbsp;
						<input type="button" onclick="popupDiv5();" value="CSVから取得(ポンパレ)" />&nbsp;&nbsp;
						<input type="button" onclick="popupDiv10();" value="CSVから取得(Amazon)" />&nbsp;&nbsp;
						</s:if>
						<input type="button" onclick="popupDiv9();" value="CSVから取得(代理発送)" />&nbsp;&nbsp;
						<!--                     <input type="button" onclick="actionSubmit('A10010105');" value="発送可リスト生成"/>&nbsp;&nbsp; -->
						<!--                     <input type="button" onclick="popupDiv();" value="発送情報をシステムに反映"/>&nbsp;&nbsp; -->
					</td>
				</tr>
			</table>
			‘
		</div>
		<div id="div2">
			<table width="99%" class="table" cellspacing="1" cellpadding="1"
				align="left" border="0">
				<tr class="bg_tr">
					<td width="5%" align="center">サイト/<br />店舗
					</td>
					<td width="15%" align="center">受注番号</td>
					<td width="13%" align="center">注文者</td>
					<td width="11%" align="center">注文日時</td>
					<td width="10%" align="center">お支払い方法</td>
					<td width="6%" align="center">合計金額&nbsp;/<br />&nbsp;請求額
					</td>
					<td width="5%" align="center">配送方法</td>
					<s:if test="f100101.todokeset == 0">
						<td width="7%" align="center">注文ステータス１</td>
						<td width="6%" align="center">注文ステータス２</td>
					</s:if>
					<s:else>
						<td width="13%" align="center">お届け時間帯</td>
					</s:else>
					<td width="10%" align="center">コメント</td>
					<td align="center">備考</td>
				</tr>
			</table>
		</div>
		<div id="div3">
			<table width="99%" class="table" cellspacing="1" cellpadding="1"
				align="left" border="0" style="word-break: break-all">
				<s:iterator value="f100101.orderList" status="status">
					<tr class="bg_tr" height="70px">
						<td width="5%" class="td_bg" align="center" valign="middle">
							<s:property value='site' />/<br />
						<s:property value='tenpo' /> <s:hidden
								name="f100101.orderList[%{#status.index}].site" value="%{site}" />
							<s:hidden name="f100101.orderList[%{#status.index}].tenpo"
								value="%{tenpo}" />
						</td>
						<td width="15%" class="td_bg" align="center" valign="middle">
							<a target="_blank"
							href="A10010201?OrderNo=<s:property value='chumonbango'/>"><s:property
									value='chumonbango' /></a> <s:hidden
								name="f100101.orderList[%{#status.index}].chumonbango"
								value="%{chumonbango}" />
						</td>
						<td width="13%" class="td_bg" align="center" valign="middle"><s:property
								value='chumonsha' />
							<s:hidden name="f100101.orderList[%{#status.index}].chumonsha"
								value="%{chumonsha}" /></td>
						<td width="11%" class="td_bg" align="center" valign="middle"><s:property
								value='chumonichiji' />
							<s:hidden name="f100101.orderList[%{#status.index}].chumonichiji"
								value="%{chumonichiji}" /></td>
						<td width="10%" class="td_bg" align="center" valign="middle">
							<s:property value='oshiharaihoho' /> <s:hidden
								name="f100101.orderList[%{#status.index}].oshiharaihoho"
								value="%{oshiharaihoho}" />
						</td>
						<td width="6%" class="td_bg" align="right" valign="middle"><s:property
								value='gokeikingaku' />&nbsp;/<br />&nbsp;<s:property
								value='seikyujingaku' /> <s:hidden
								name="f100101.orderList[%{#status.index}].gokeikingaku"
								value="%{gokeikingaku}" /> <s:hidden
								name="f100101.orderList[%{#status.index}].seikyujingaku"
								value="%{seikyujingaku}" /></td>
						<td width="5%" class="td_bg" align="center" valign="middle"><s:property
								value='haisohoho' />
							<s:hidden name="f100101.orderList[%{#status.index}].haisohoho"
								value="%{haisohoho}" /></td>
						<s:if test="f100101.todokeset == 0">
							<td width="7%" class="td_bg" align="center" valign="middle">
								<s:property value='chumonsts1' /> <s:hidden
									name="f100101.orderList[%{#status.index}].chumonsts1"
									value="%{chumonsts1}" />
							</td>
							<td width="6%" class="td_bg" align="center" valign="middle">
								<s:if test="chumonsts2 != ''">
									<font color="red"><s:property value='chumonsts2' /></font>
									<br>
								</s:if> <s:if test="chumonsts3 != ''">
									<font color="blue"><s:property value='chumonsts3' /></font>
									<br>
								</s:if> <s:if test="chumonsts4 != ''">
									<font color="grey"><s:property value='chumonsts4' /></font>
									<br>
								</s:if> <s:if test="chumonsts5 != ''">
									<s:property value='chumonsts5' />
									<br>
								</s:if> <s:if test="chumonsts6 != ''">
									<font color="green"><s:property value='chumonsts6' /><br></font>
								</s:if> <s:hidden name="f100101.orderList[%{#status.index}].chumonsts2"
									value="%{chumonsts2}" />
							</td>
						</s:if>
						<s:else>
							<td width="13%" class="td_bg" align="center"><span
								id="shiteibi${status.index}"></span><br /> <input type="button"
								value="設定" name="otodokeSetei"
								onclick="showTodoke(${status.index})" /> <input type="button"
								value="取消" name="otodokeTorikeshi" style="display: none"
								onclick="hideTodoke(${status.index})" /> <input type="button"
								value="確定" name="otodokeKakutei" style="display: none"
								onclick="setTodoke(${status.index})" /><br /> <s:textfield
									style="display:none" size="13" cssClass="Wdate"
									onClick="WdatePicker()" maxlength="10" name="otodokebishitei" />
								<s:select style="display:none"
									list="#{ '':'▼選択してください','01':'午前中','12':'12:00~14:00','14':'14:00~16:00','16':'16:00~18:00','04':'18:00~21:00'}"
									name="otodokejikantai1" />&nbsp;</td>
						</s:else>
						<td width="10%" class="td_bg" align="center" valign="middle">
							<s:property value='comento' /> <s:hidden
								name="f100101.orderList[%{#status.index}].comento"
								value="%{comento}" />
						</td>
						<td class="td_bg" align="center"><s:label
								name="f100101.orderList[%{#status.index}].biko" value="%{biko}" />
							<s:hidden name="f100101.orderList[%{#status.index}].biko"
								value="%{biko}" /></td>
					</tr>
				</s:iterator>
			</table>
			<s:hidden name="viewId" value="V100101" />
			<s:hidden name="mode" />
			<s:hidden name="shoriMode" />
			<s:hidden name="rowIndex" />
			<s:hidden name="successFlg" />
			<s:hidden name="scrollx" />
			<s:hidden name="scrolly" />
			<s:hidden name="f100101.todokeset" />
		</div>
		<div id='pop-div' style="left: 30%; width: 350px; height: 160px;"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">楽天発送情報をシステムに反映<br />csvファイル選択：<s:file
								name="csvFile" /></td>
					</tr>

				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010106')" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div3' style="left: 30%; width: 350px; height: 160px;"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">DENA CSVから最新情報取得<br />csvファイル選択：<s:file
								name="csvFile2" /></td>
					</tr>
				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010108')" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div4' style="left: 30%; width: 350px; height: 160px;"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">Yahoo CSVから最新情報取得<br />注文系データ：<s:file
								name="csvFile3" /><br />商品系データ：<s:file name="csvFile4" /></td>
					</tr>
				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010109')" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div5' style="left: 30%; width: 350px; height: 160px;"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">ポンパレCSVから最新情報取得<br />
						<s:file name="csvFile5" /></td>
					</tr>
				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010110')" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div7' style="left: 30%; width: 350px; height: 160px;"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">Rakuten CSVから最新情報取得<br />csvファイル選択：<s:file
								name="csvFile6" /></td>
					</tr>
				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010111')" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div8' style="left: 30%; width: 350px; height: 160px;"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">qoo10 CSVから最新情報取得<br />csvファイル選択：<s:file
								name="csvFile7" /></td>
					</tr>
				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010112')" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div9' style="left: 30%; width: 350px; height: 160px;"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">代理発送CSV<br />csvファイル選択：<s:file
								name="csvFile8" /></td>
					</tr>
				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010113')" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div10' style="left: 30%; width: 350px; height: 160px;"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">Amazon CSVから最新情報取得<br />店舗：<s:select
								list="#request.amazonmap"
								name="f100101.shutokuamazonshop" /><br />csvファイル選択：<s:file
								name="csvFile10" /></td>
					</tr>
				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010114?shop='+escape(document.getElementsByName('f100101.shutokuamazonshop')[0].value))" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div2' style="left: 30%; width: 350px; height: 160px;"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">楽天注文情報を取得する<br />店舗：<s:select
								list="#request.rakutenmap"
								name="f100101.shutokushop" />
						</td>
					</tr>

				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010104?shop='+document.getElementsByName('f100101.shutokushop')[0].value+'&platform=Rakuten')"
							value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-divGetOrderInfoYahoo'
			style="left: 30%; width: 350px; height: 160px;" class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">Yahoo 注文情報を取得する<br />店舗：<s:select
								list="#request.yahoomap"
								name="f100101.shutokuyahooshop" />
						</td>
					</tr>

				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010104?shop='+document.getElementsByName('f100101.shutokuyahooshop')[0].value+'&platform=Yahoo')"
							value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-divGetOrderInfoAU'
			style="left: 30%; width: 350px; height: 160px;" class="pop-box">
			<div class="pop-box-body">
				<table align="center">
					<tr height="80px">
						<td align="center" colspan="2">AU 注文情報を取得する<br />店舗：<s:select
								list="#request.aumap"
								name="f100101.shutokuaushop" />
						</td>
					</tr>

				</table>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010104?shop='+document.getElementsByName('f100101.shutokuaushop')[0].value+'&platform=AU')"
							value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="div4">
			<table width="99%" class="table" cellspacing="1" cellpadding="1"
				align="left" border="0">
				<tr>
					<td><a href="#" id="firstPage"
						onclick="actionSubmit('A03020106?page=first')">首页</a>&nbsp;<a
						href="#" id="lastPage"
						onclick="actionSubmit('A03020106?page=last')">上一页</a>&nbsp; <a
						href="#" id="nextPage"
						onclick="actionSubmit('A03020106?page=next')">下一页</a>&nbsp;<a
						href="#" id="endPage" onclick="actionSubmit('A03020106?page=end')">尾页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;第<s:label name="nowPage" />
						<s:hidden id="nowPage" name="nowPage" />页&nbsp;/&nbsp;共<s:label
							name="allPage" />
						<s:hidden id="allPage" name="allPage" />页
						&nbsp;&nbsp;&nbsp;&nbsp;跳转到<s:textfield
							cssErrorStyle="background:red;" name="goPage" size="1" />页 <input
						onclick="actionSubmit('A03020106?page=go')" type="button"
						value="GO"></td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
<script type="text/javascript">
window.onscroll = function(){
	
	document.getElementsByName("scrolly")[0].value = $(document).scrollTop();
	document.getElementsByName("scrollx")[0].value = $(document).scrollLeft();
}
</script>
</html>