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
function popupDiv() {   
    var div_obj = $("#pop-div"); 
	div_obj.css("top",($(window).height()-550)/2);
	div_obj.css("left",($(window).width()-500)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
    
    document.getElementsByName("f130101.inputJuchubango")[0].value="";
    document.getElementsByName("f130101.inputDenpyobango")[0].value="";
    document.getElementsByName("receive")[0].value="";
    document.getElementById("bikoArea").innerHTML = "";
	document.getElementById("infoArea").innerHTML = "";
	document.getElementById("cmtkakuninTd").style.display = "none";
	document.getElementById("cmtkakunin").checekd = false;
    document.getElementsByName("receive")[0].focus();
} 
function popupDiv3() {   
    var div_obj = $("#pop-div2"); 
	div_obj.css("top",($(window).height()-350)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
} 

function popupDiv2() {   
    var div_obj = $("#kakuninDiv"); 
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
    
    $("#kakunin").attr("src","A13020101");
}

function checkReq(){
	var val = document.getElementsByName("pass_val")[0].value;
	
	if(val == "0"){
		return false;
	}else{
		return true;
	}
	
}

function getReq(){
	var no = document.getElementsByName("f130101.inputJuchubango")[0].value;
	$.post("A13020110", {orderNo:no}, function(result) {
		document.getElementsByName("pass_val")[0].value = result;
		if("0" == result){
			document.getElementsByName("f130101.checkhitiyo")[0].checked = false;
		}else{
			document.getElementsByName("f130101.checkhitiyo")[0].checked = true;
		}
	},"json");
	
}

function checkHasokano(){
	if(document.getElementsByName("f130101.inputDenpyobango")[0].value == ""){
		alert("伝票番号を入力してください");
		return false;
	}
	if(document.getElementsByName("f130101.inputJuchubango")[0].value == ""){
		alert("受注番号を入力してください");
		return false;
	}

	if(checkReq()){
		var ariFlg = true;
		for (var i = 0; typeof (document.getElementsByName("hasokosu")[i]) != "undefined"; i++) {
			if(document.getElementsByName("hasokosu")[i].value != document.getElementsByName("kosuhid")[i].value){
				ariFlg = false;
				break;
			}
			
		}
		if(!ariFlg){
			alert("発送情報誤り！");
			return false;
		}
		if(document.getElementById("cmtkakuninTd").style.display !="none"){
			if(document.getElementById("cmtkakunin").checked == false){
				alert("備考欄をチェックしてください");
				return false;
			}
		}
		
	}
	return true;
	
}

function setInfoVal(infoVal){
	
	var Regx = /^[A-Za-z]*$/;
	
	infoVal = infoVal.replace(/\s+/g,"");
	// 是快递单号
	//if((infoVal.charAt(0) == 3 || infoVal.charAt(0) == 4 || infoVal.charAt(0) == 1 || infoVal.charAt(0) == 5 || (infoVal.charAt(0) == 2 && infoVal.charAt(1) == 8)) && infoVal.length == 12){
	if((/^\d+$/.test(infoVal)) && infoVal.length == 12) {
		document.getElementsByName("f130101.inputDenpyobango")[0].value=infoVal;
		
	    if(checkHasokano()){
			actionSubmit('A13010104');
		}
	}else{
		clearInfo();
		// 订单号
		document.getElementsByName("f130101.inputJuchubango")[0].value=infoVal;
		checkBiko();
	}
/* 	}else if((infoVal.charAt(0) == 1 && infoVal.length != 13) || infoVal.charAt(0) == 2 || infoVal.charAt(0) == 3 || infoVal.charAt(0) == 'O'|| infoVal.charAt(0) == 'P' || infoVal.charAt(0) == 'Y' || Regx.test(infoVal.charAt(0)) ){
		clearInfo();
		// 订单号
		document.getElementsByName("f130101.inputJuchubango")[0].value=infoVal;
		checkBiko();
		
	}else if((infoVal.charAt(0) == 1 && infoVal.length == 13) || infoVal.length == 6){
		if(document.getElementById("infoArea").innerHTML == ""){
			alert("先ずは受注番号を入力してください！");
		}else{
			var ariFlg = false;
			
			if(infoVal.indexOf("1000000")!=0){
				infoVal = "1000000"+infoVal;
			}
			for (var i = 0; typeof (document.getElementsByName("hasokosu")[i]) != "undefined"; i++) {
				if(document.getElementsByName("barcodehid")[i].value == infoVal){
					ariFlg = true;
					document.getElementsByName("hasokosu")[i].value = (parseInt(document.getElementsByName("hasokosu")[i].value)+1);
					break;
				}
			}
			if(!ariFlg){
				alert("該当荷物は発送すべきありません！");
			}
		}
		document.getElementsByName("receive")[0].value = "";
		setColor();
	} */
}

function setColor(){
	for (var i = 0; typeof (document.getElementsByName("hasokosu")[i]) != "undefined"; i++) {
		
		if(document.getElementsByName("hasokosu")[i].value!=0 && document.getElementsByName("hasokosu")[i].value>document.getElementsByName("kosuhid")[i].value){
			document.getElementById(i+"1").style.backgroundColor = "red";
			document.getElementById(i+"2").style.backgroundColor = "red";
			document.getElementById(i+"3").style.backgroundColor = "red";
			document.getElementById(i+"4").style.backgroundColor = "red";
		}else if(document.getElementsByName("hasokosu")[i].value!=0 && document.getElementsByName("hasokosu")[i].value == document.getElementsByName("kosuhid")[i].value){
			document.getElementById(i+"1").style.backgroundColor = "green";
			document.getElementById(i+"2").style.backgroundColor = "green";
			document.getElementById(i+"3").style.backgroundColor = "green";
			document.getElementById(i+"4").style.backgroundColor = "green";
		}else if(document.getElementsByName("hasokosu")[i].value!=0 && document.getElementsByName("hasokosu")[i].value < document.getElementsByName("kosuhid")[i].value){
			document.getElementById(i+"1").style.backgroundColor = "yellow";
			document.getElementById(i+"2").style.backgroundColor = "yellow";
			document.getElementById(i+"3").style.backgroundColor = "yellow";
			document.getElementById(i+"4").style.backgroundColor = "yellow";
		}
	}
}

function clearInfo(){
	document.getElementById("cmtkakuninTd").style.display = "none";
	document.getElementById("cmtkakunin").checked=false;
	document.getElementById("bikoArea").innerHTML = "";
	document.getElementById("bikoArea").innerHTML = "";
	
}

//监听Enter键自动提交事件 
function keyboardEvent(event){ 
var keyCode=event.keyCode ? event.keyCode:event.which?event.which:event.charCode;//解决浏览器之间的差异问题 
if(keyCode==13){ 
	document.getElementsByName("receive")[0].focus();
	setInfoVal(document.getElementsByName("receive")[0].value);
	document.getElementsByName("receive")[0].value="";
} 
} 

function keyboardEvent2(event){ 
	var keyCode=event.keyCode ? event.keyCode:event.which?event.which:event.charCode;//解决浏览器之间的差异问题 
	if(keyCode==13){ 
		document.getElementById("submitbtn").click();
	} 
} 

function hideDiv() {   
	  $("#mask").hide();   
	  $("#pop-div").hide(); 
	  $("#pop-div2").hide();
	  $("#kakuninDiv").hide(); 
}
function init() {
	if(document.getElementsByName("successFlg")[0].value != ''){
		var no = document.getElementsByName("successFlg")[0].value;
		document.getElementsByName("successFlg")[0].value = '';
		$.post("A13010109", {orderNo:no}, function(result) {
			if(result == "1"){
				popupDiv();
			}else{
				alert("发送失败！请重试");
			}
		},"json");
		
	}
	replaceBr();
	getReq();
	document.getElementsByName("f130101.checkPass")[0].value = "";
	
}
function replaceBr(){
	for (var i = 0; typeof (document
            .getElementsByName("f130101.orderList[" + i
                    + "].sofusakinamae")[0]) != "undefined"; i++) {
            document.getElementById("td0["+i+"]").innerHTML = document.getElementById("td0["+i+"]").innerHTML.replace(new RegExp("&lt;br&gt;", 'g'),"<br/>");
    }
}

function checkAll(){
	var allcheck = true;
	
	for (var i = 0; typeof (document.getElementsByName("f130101.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
		if(document.getElementsByName("f130101.orderList[" + i + "].ischecked")[0].checked == false){
			allcheck = false;
			break;
		}
    }
	
	if(allcheck){
		for (var i = 0; typeof (document.getElementsByName("f130101.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
			document.getElementsByName("f130101.orderList[" + i + "].ischecked")[0].checked = false;
	    }
	}else{
		for (var i = 0; typeof (document.getElementsByName("f130101.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
			document.getElementsByName("f130101.orderList[" + i + "].ischecked")[0].checked = true;
	    }
	}
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
	var no = document.getElementsByName("f130101.orderList["+index+"].chumonbango")[0].value;
	
	
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
		//if(otodoke3!=""){
		//	innerText+=otodoke3 + "時&nbsp;～";
		//}
		//if(otodoke4!=""){
		//	innerText+=otodoke4 + "時&nbsp;";
		//}
		document.getElementById("shiteibi"+index).innerHTML = innerText;
		hideTodoke(index);
		alert("指定成功");
    }, "json");
	
}

function checkBiko(){
	var no = document.getElementsByName("f130101.inputJuchubango")[0].value;
	$.post("A13020109", {orderNo:no}, function(result) {
		
		var dataArr = result.split("&&");
		
		
		document.getElementById("bikoArea").innerHTML = "<font color='red'>"+dataArr[0]+"</font>";
		if(dataArr[0]!=''){
			document.getElementById("cmtkakuninTd").style.display = "";
		}
		var html="";
		var j=0;
		for(var i=1;i<dataArr.length;i++){
			var shouhinbango = dataArr[i];
			var kosu = dataArr[++i];
			var barcode = dataArr[++i];
			html +=("<tr class='bg_tr'><td class='td_bg' id='"+j+"1'>"+shouhinbango+
					"</td><td class='td_bg' id='"+j+"2'>"+kosu+"</td><td class='td_bg' id='"+j+"3'>"+
					barcode+"</td><td class='td_bg' id='"+j+"4'><input type='text' size='5' value='0' name='hasokosu' onkeyup='setColor();'><input type='hidden' name='kosuhid' value='"
					+kosu+"'><input type='hidden' name='barcodehid' value='"+barcode+"'></td></tr>");
			j++;
			
		}
		
		document.getElementById("infoArea").innerHTML = ("<table cellspacing='1' align='center' border='1' cellpadding='2' width='500px'><tr class='bg_tr'><td>商品番号</td><td>個数</td><td>バーコード</td><td>発送個数</td></tr>"+html+"</table>");
		
		//	actionSubmit('A13010104');
		
	},"json");
	
	
		
	}
	
function isNumber(val){
    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    //var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val)){
        return true;
    }else{
        return false;
    }
}

function updateSize(bango,size1,thisIdx){
	if(isNumber(size1)){
		if((parseFloat(size1)>=parseFloat("0.1")) && (parseFloat(size1)<parseFloat("9"))){
			$.post("A13010111", {orderNo:bango,thisSize:size1}, function(result) {
				document.getElementsByName("f130101.orderList["+thisIdx+"].unsokaisha")[0].value=result;
				alert("成功しました！");
			},"json");
		}else{
			alert("尺寸输入不正确，请重新输入0.1~9之间！");
		}
	}else{
		alert("尺寸输入不正确，请重新输入！");
	}
}

function updateKaisha(bango,idx){
	var size1 = document.getElementsByName("f130101.orderList["+idx+"].size")[0].value;
	var kaisha = document.getElementsByName("f130101.orderList["+idx+"].unsokaisha")[0].value;
	  $.post("A13010112", {orderNo:bango,thisSize:size1,unsokaisha:kaisha}, function(result) {
		},"json");
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
	height:10%;
	margin-left:auto;
	margin-right:auto;
}

#div2 {
	top: 20px;
	width:99%;
	overflow-x:hidden;
	position: relative;
	margin-left:auto;
	margin-right:auto;
}
#div3 {
	width:99%;
	position: relative;
	top: 19px;
	margin-left:auto;
	margin-right:auto;
}
#div5 {
	top: 20px;
	width:99%;
	overflow-x:hidden;
	position: relative;
	margin-left:auto;
	margin-right:auto;
}
#div4 {
	height: 70px;
	width:99%;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 20px;
	margin-left:auto;
	margin-right:auto;
}
  .pop-box {   
            z-index: 9998; /*这个数值要足够大，才能够显示在最上层*/  
            margin-bottom: 3px;   
            position: absolute;   
            background: #FFF;   
            border:solid 1px #6e8bde;   
        }   
          
        .pop-box h4 {   
            color: #FFF;   
            cursor:default;   
            height: 18px;   
            font-size: 14px;   
            font-weight:bold;   
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
            color:#C7EDCC;
            background-color:#C7EDCC;
            position:absolute;
            top:0px;
            left:0px;
            filter: Alpha(Opacity=60);
            -moz-opacity:.6;    
            opacity:0.6;
            z-index:1000;
        } 
-->
</style>
</head>
<body onload="init();">
<%
		Connection conn = null;
	try {
		Map<String, String> siteMap = new HashMap<String, String>();
		siteMap.put("","--");
		conn = com.rakuten.util.JdbcConnection.getConnection();
		String sql = "SELECT distinct SITE FROM rakuten.shop";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			siteMap.put(rs.getString("SITE"), rs.getString("SITE"));
		}
		sql = "SELECT distinct SHOP_ID FROM rakuten.shop where DELETE_FLG is null";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		Map<String, String> shopMap = new HashMap<String, String>();
		shopMap.put("","--");
		while (rs.next()) {
			shopMap.put(rs.getString("SHOP_ID"), rs.getString("SHOP_ID"));
		}
		request.setAttribute("sitemap", siteMap);
		request.setAttribute("shopmap", shopMap);

	} catch (Exception e) {
		out.println(e);
	} finally {
		conn.close();
	}
	%>
<div id='mask' class="mask" style="width:100%;height:100%;display:none"></div>
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="70%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="140px">注文日時：</td>
                <td class="td_bg" width="300px">
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f130101.kikanStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f130101.kikanEnd"/>
                </td>
                <td class="td_bg">受注番号：</td>
                <td class="td_bg" >
                    <s:textfield size="35" maxlength="50" name="f130101.chumonbango"/>
                </td>
            </tr>
            <tr>
                <td class="td_bg">送付先名前：</td>
                <td class="td_bg" >
                    <s:textfield size="25" maxlength="50" name="f130101.sofusakinamae"/>
                </td>
                <td class="td_bg">送付先電話番号：</td>
                <td class="td_bg">
                    <s:textfield size="15" maxlength="50" name="f130101.sofusakidenwabango"/>
                </td>
             </tr>
             <tr>
                <td class="td_bg" >お支払い方法</td>
                <td class="td_bg" >
                    <s:select list="#{'':'--','クレジットカード':'クレジットカード','代金引換':'代金引換','銀行振込 ':'銀行振込 ','楽天バンク決済':'楽天バンク決済','Edy決済':'楽天Edy決済','その他':'その他'}" name="f130101.oshiharaihoho"/>
                </td>
                <td class="td_bg" >配送方法</td>
                <td class="td_bg" >
                    <s:select list="#{'':'--','DM便':'DM便','メール便':'メール便','宅配便':'宅配便'}" name="f130101.haisohoho"/>
                </td>
            </tr>
            <tr>
                <td class="td_bg">平台別</td>
                <td class="td_bg">
                    <s:select list="#request.sitemap" name="f130101.site"/>
                </td>
                <td class="td_bg">店舗別</td>
                <td class="td_bg">
                    <s:select list="#request.shopmap" name="f130101.tenpo"/>
                </td>
            </tr>
            <tr>
                <td class="td_bg" >コメント注意</td>
                <td class="td_bg" >
                    <s:select list="#{'':'--','1':'○'}" name="f130101.chuyi"/>
                </td>
				<td class="td_bg">時間帯設定</td>
                <td class="td_bg">
                    <s:select list="#{'':'--','1':'あり','0':'なし'}" name="f130101.jikantaiset"/>
                </td>
                
            </tr>
            <tr>
				<td class="td_bg">運送会社</td>
                <td class="td_bg">
                    <s:select list="#{'':'--','1001':'ヤマト運輸','1002':'佐川急便','1003':'郵便局'}" name="f130101.unyokaisha"/>
                </td>
                <td class="td_bg">商品番号</td>
                <td class="td_bg">
                    <s:textfield size="15" maxlength="50" name="f130101.shohinbango"/>
                    <s:checkbox name="f130101.searchKeywordCondition"/>商品のみを含め
                </td>
                <td class="td_bg"></td>
                <td class="td_bg">
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A13010102')" value="検索" style="width:50px;height:25px"/></td>
                </td>
            </tr>
		</table>
		<div style="width:900px;height:20px;margin-top: 5px;">
            <hr>
        </div>
		</div>
        <div id="div2">
        <table width="99%">
<!--         　　　　　　　　<tr> -->
<!--                 <td align="left" colspan="2"> -->
<%--                     送り状設定：　宅配便：<s:select list="#{'1002':'佐川急便','1001':'ヤマト運輸'}" name="f130101.takuhaibinkaisha"/> --%>
<%--                     メール便：<s:select list="#{'1001':'ヤマト運輸'}" name="f130101.merubinkaisha"/> --%>
<!--                 </td> -->
<!--             </tr> -->
            <tr>
                <td align="left">
                    <s:select list="#{'0':'選択した注文','1':'すべての注文'}" name="f130101.outtype"/>
                    <s:if test="f130101.hasoshubetsu == 1"><input type="button" style="height:30px" onclick="actionSubmit('A13010103');" value="発送の明細書を生成する"/></s:if>
                    <s:else><input type="button" style="height:30px" disabled value="発送の明細書を生成する"/></s:else>
                    <input type="button" style="height:30px" onclick="actionSubmit('A13010103?okurijonomi=true');" value="送り状のみ生成する"/>
                    <s:checkbox name="f130101.checkFlg" checked="true"/>打印前确认是否已经打印&nbsp;&nbsp;
                    ページの高さ：<s:textfield size="4" name="f130101.pageHeight"/>px
                    <input type="button" onclick="popupDiv3()" value="設定">
                </td>
                <td align="right">
                    <input type="button" style="height:30px" onclick="actionSubmit('A13010101');" value="最新の情報に更新"/>
                </td>
            </tr>
        </table>
        <table cellspacing="1" cellpadding="2" width="99%" border="0" class="table">
            <tr class="bg_tr" height="30px">
                <s:if test="f130101.hasoshubetsu == 1"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=1')">発送待ち-発送可(<s:if test ='f130101.hasomachihasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130101.hasomachihasokaCount"/><s:if test ='f130101.hasomachihasokaCount>0'></font></s:if><s:hidden name="f130101.hasomachihasokaCount"/>)</a></td>
                <s:if test="f130101.hasoshubetsu == 2"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=2')">追加発送待ち-発送可(<s:if test ='f130101.tuikahasomachihasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130101.tuikahasomachihasokaCount"/><s:if test ='f130101.tuikahasomachihasokaCount>0'></font></s:if><s:hidden name="f130101.tuikahasomachihasokaCount"/>)</a></td>
                <s:if test="f130101.hasoshubetsu == 3"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=3')">分納発送待ち-発送可(<s:if test ='f130101.bunnohasomachihasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130101.bunnohasomachihasokaCount"/><s:if test ='f130101.bunnohasomachihasokaCount>0'></font></s:if><s:hidden name="f130101.bunnohasomachihasokaCount"/>)</a></td>
                <s:if test="f130101.hasoshubetsu == 4"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=4')">発送待ち-至急-発送可(<s:if test ='f130101.hasomachishikyuhasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130101.hasomachishikyuhasokaCount"/><s:if test ='f130101.hasomachishikyuhasokaCount>0'></font></s:if><s:hidden name="f130101.hasomachishikyuhasokaCount"/>)</a></td>
                <s:if test="f130101.hasoshubetsu == 5"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=5')">追加発送待ち-至急-発送可(<s:if test ='f130101.tuikahasomachishikyuhasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130101.tuikahasomachishikyuhasokaCount"/><s:if test ='f130101.tuikahasomachishikyuhasokaCount>0'></font></s:if><s:hidden name="f130101.tuikahasomachishikyuhasokaCount"/>)</a></td>
                <s:if test="f130101.hasoshubetsu == 6"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=6')">分納発送待ち-至急-発送可(<s:if test ='f130101.bunnohasomachishikyuhasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130101.bunnohasomachishikyuhasokaCount"/><s:if test ='f130101.bunnohasomachishikyuhasokaCount>0'></font></s:if><s:hidden name="f130101.bunnohasomachishikyuhasokaCount"/>)</a></td>
                <s:hidden name="f130101.hasoshubetsu"/>
            </tr>
		</table>
        <table cellspacing="1" cellpadding="2" width="1150px" border="0" class="table">
            <tr class="bg_tr">
                <s:if test="f130101.tenposhubetsu == 1"><td width="100px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="100px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=1')">楽天(<s:text name="f130101.rakutenCount"/>)<s:hidden name="f130101.rakutenCount"/></a></td>
                <s:if test="f130101.tenposhubetsu == 2"><td width="200px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="200px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=2')">Yahooショッピング(<s:text name="f130101.yahooShoppingCount"/>)<s:hidden name="f130101.yahooShoppingCount"/></a></td>
                <s:if test="f130101.tenposhubetsu == 3"><td width="100px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="100px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=3')">DENA(<s:text name="f130101.denaCount"/>)</a><s:hidden name="f130101.denaCount"/></td>
                <s:if test="f130101.tenposhubetsu == 4"><td width="100px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="100px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=4')">ヤフオク(<s:text name="f130101.yafuokuCount"/>)</a><s:hidden name="f130101.yafuokuCount"/></td>
                <s:if test="f130101.tenposhubetsu == 5"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=5')">ポンパレモール(<s:text name="f130101.ponpareCount"/>)</a><s:hidden name="f130101.ponpareCount"/></td>
                <s:if test="f130101.tenposhubetsu == 6"><td width="100px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="100px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=6')">qoo10(<s:text name="f130101.qoo10Count"/>)</a><s:hidden name="f130101.qoo10Count"/></td>
                <s:if test="f130101.tenposhubetsu == 7"><td width="100px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="100px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=7')">AU(<s:text name="f130101.auCount"/>)</a><s:hidden name="f130101.auCount"/></td>
                <s:if test="f130101.tenposhubetsu == 8"><td width="100px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="100px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=8')">その他(<s:text name="f130101.otherCount"/>)</a><s:hidden name="f130101.otherCount"/></td>
                <s:hidden name="f130101.tenposhubetsu"/>
            </tr>
		</table>
        <table width="99%" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
                <td width="3%" align="center"><input type="checkbox" onchange="checkAll()"></td>
			    <td width="6%" align="center">サイト/<br/>店舗</td>
			    <td width="12%" align="center">受注番号</td>
			    <td width="6%" align="center">注文日時</td>
			    <td width="8%" align="center">お支払い方法</td>
			    <td width="5%" align="center">合計金額&nbsp;/<br/>&nbsp;請求額</td>
			    <td width="7%" align="center">送付先名前</td>
			    <td width="5%" align="center">配送方法 </td>
			    <td width="5%" align="center">サイズ </td>
			    <td width="9%" align="center">運送会社 </td>
			    <td width="8%" align="center">コメント</td>
			    <td width="15%" align="center">届け時間帯</td>
				<td align="center">備考</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="99%" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f130101.orderList" status="status">
			<tr class="bg_tr" height="50px">
			    <td width="3%" class="td_bg" align="center" valign="middle">
			        <s:checkbox name="f130101.orderList[%{#status.index}].ischecked"/>
			    </td>
			    <td width="6%" class="td_bg" align="center" valign="middle">
			        <s:property value='site'/>/<br/><s:property value='tenpo'/>
			        <s:hidden name="f130101.orderList[%{#status.index}].site" value="%{site}"/>
			        <s:hidden name="f130101.orderList[%{#status.index}].tenpo" value="%{tenpo}"/>
			    </td>
			    <td width="12%" class="td_bg" align="center" valign="middle">
			        <a target="_blank" href="A10010201?OrderNo=<s:property value='chumonbango'/>"><s:if test ='red_flg == "1"'><font color="red"><s:property value='chumonbango'/></font></s:if><s:else><s:property value='chumonbango'/></s:else></a>
			        <s:hidden name="f130101.orderList[%{#status.index}].chumonbango" value="%{chumonbango}"/>
			    </td>
				<td width="6%" class="td_bg" align="center" valign="middle"><s:property value='chumonichiji'/><s:hidden name="f130101.orderList[%{#status.index}].chumonichiji" value="%{chumonichiji}"/></td>
				<td width="8%" class="td_bg" align="center" valign="middle"><s:property value='oshiharaihoho'/><s:hidden name="f130101.orderList[%{#status.index}].oshiharaihoho" value="%{oshiharaihoho}"/></td>
				<td width="5%" class="td_bg" align="right" valign="middle">
				    <s:property value='gokeikingaku'/>&nbsp;/<br/>&nbsp;<s:property value='seikyujingaku'/>
				    <s:hidden name="f130101.orderList[%{#status.index}].gokeikingaku" value="%{gokeikingaku}"/>
				    <s:hidden name="f130101.orderList[%{#status.index}].seikyujingaku" value="%{seikyujingaku}"/>
				</td>
				<td width="7%" class="td_bg" align="center" valign="middle" id="td0[${status.index}]">
				    <s:property value='sofusakinamae'/>
				    <s:hidden name="f130101.orderList[%{#status.index}].sofusakinamae" value="%{sofusakinamae}"/>
				</td>
				<td width="5%" class="td_bg" align="center" valign="middle"><s:property value='haisohoho'/><s:hidden name="f130101.orderList[%{#status.index}].haisohoho" value="%{haisohoho}"/></td>
				<td width="5%" class="td_bg" align="center" valign="middle">
				    <s:if test ='size>1'>
				        <s:textfield cssStyle="background-color:red" size="2" name="f130101.orderList[%{#status.index}].size" value="%{size}"/><br/>
				    </s:if>
				    <s:else>
				    </s:else>
				        <s:textfield size="2" name="f130101.orderList[%{#status.index}].size" value="%{size}"/><br/>
				    <input type="button" value="設定" name="" onclick="updateSize('<s:property value='chumonbango'/>',document.getElementsByName('f130101.orderList[${status.index}].size')[0].value,'${status.index}')"/>
				</td>
				<td width="9%" class="td_bg" align="center" valign="middle">
				


				        <s:select list="#{'':'--','1001':'ヤマト運輸','1002':'佐川急便','1003':'郵便局'}" name="f130101.orderList[%{#status.index}].unsokaisha" value="%{unsokaisha}" onchange="updateKaisha('%{chumonbango}',%{#status.index})"/>
<%-- 				        <input type="button" value="設定" name="" onclick="updateKaisha('<s:property value='chumonbango'/>',document.getElementsByName('f130101.orderList[${status.index}].size')[0].value,document.getElementsByName('f130101.orderList[${status.index}].unsokaisha')[0].value)"/> --%>

				    
				</td>
				<td width="8%" class="td_bg" align="center" valign="middle">
				    <s:property value='comento'/>
				    <s:hidden name="f130101.orderList[%{#status.index}].comento" value="%{comento}"/>
				</td>
				<td width="15%" class="td_bg" align="center">
				    <span id="shiteibi${status.index}"><s:label name="f130101.orderList[%{#status.index}].otodokeshiteibi" value="%{otodokeshiteibi}"/></span>
				    <s:hidden name="f130101.orderList[%{#status.index}].otodokeshiteibi" value="%{otodokeshiteibi}"/>
				    <br/>
				    <input type="button" value="設定" name="otodokeSetei" onclick="showTodoke(${status.index})"/>
				    <input type="button" value="取消" name="otodokeTorikeshi" style="display:none" onclick="hideTodoke(${status.index})"/>
				    <input type="button" value="確定" name="otodokeKakutei" style="display:none" onclick="setTodoke(${status.index})"/><br/>
				    <s:textfield style="display:none" size="13" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="otodokebishitei"/>
				    <s:select style="display:none" list="#{ '':'▼選択してください','01':'午前中','12':'12:00~14:00','14':'14:00~16:00','16':'16:00~18:00','04':'18:00~21:00'}" name="otodokejikantai1" />&nbsp;
				</td>
				<td class="td_bg" align="center">
				    <s:label name="f130101.orderList[%{#status.index}].biko" value="%{biko}"/>
				    <s:hidden name="f130101.orderList[%{#status.index}].biko" value="%{biko}"/>
				    <s:hidden name="f130101.orderList[%{#status.index}].sofusakidenwabango" value="%{sofusakidenwabango}"/>
				</td>
			</tr>
		    </s:iterator>
		</table>
		<s:hidden name="viewId" value="V100101"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="successFlg"/>
		<s:hidden name="scrollx"/>
        <s:hidden name="scrolly"/>
		</div>
        <div id="div4">
		<table width="99%" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
		    <tr>
		        <td>
		            <a href="#" id="firstPage" onclick="actionSubmit('A13010107?page=first')">首页</a>&nbsp;<a href="#" id="lastPage" onclick="actionSubmit('A13010107?page=last')">上一页</a>&nbsp;
		            <a href="#" id="nextPage" onclick="actionSubmit('A13010107?page=next')">下一页</a>&nbsp;<a href="#" id="endPage" onclick="actionSubmit('A13010107?page=end')">尾页</a>
		            &nbsp;&nbsp;&nbsp;&nbsp;第<s:label name="nowPage"/><s:hidden id="nowPage" name="nowPage"/>页&nbsp;/&nbsp;共<s:label name="allPage"/><s:hidden id="allPage" name="allPage"/>页
		            &nbsp;&nbsp;&nbsp;&nbsp;跳转到<s:textfield cssErrorStyle="background:red;" name="goPage" size="1"/>页 <input onclick="actionSubmit('A13010107?page=go')" type="button" value="GO">
		        </td>
		    </tr>
		</table>
		</div>
		<div id="fudong" style="position: absolute;right:20px;top:10px;">
		<table width="99%" cellspacing="1" cellpadding="1" align="center" border="0">
		    <tr>
		        <td  align="center"><input type="button" value="発　送　開　始" style="height:50px;width:200px;font-size: 20px;font-weight:700" onclick="popupDiv()"></td>
		    </tr>
		    <tr>
		        <td  align="center"><input type="button" value="発送済、反映待ち" style="height:25px;width:200px;font-size: 20px;font-weight:700" onclick="popupDiv2()"></td>
		    </tr>
		</table>
		
		</div>
		<div id='pop-div' style="left:30%;width:550px;height:500px;display:none" class="pop-box">   
            <div class="pop-box-body" >
            <table align="center" width="500px">
                <tr>
                    <td align="center"><input type="button" onclick="hideDiv();" value="戻る" /></td>
                    <td align="center"><input type="button" id="submitbtn" onclick="if(checkHasokano())actionSubmit('A13010104')" value="確定" /></td>
                    <td align="center" id="cmtkakuninTd" style="display:none">コメント確認済み<input type="checkbox" id="cmtkakunin" ></td>
                </tr>
            </table>
            <table align="center">
                <tr height="40px">
                    <td colspan="4"><input type="text" style="ime-mode:disabled" name="receive"  id="receive" onkeydown="keyboardEvent(event);"></td>
                </tr>
                <tr height="40px">
                    <td align="center">受注番号：</td>
                    <td align="center"><s:textfield cssClass="readonly" name="f130101.inputJuchubango"/></td>
                    <td align="center">伝票番号：</td>
                    <td align="center"><s:textfield cssClass="readonly" name="f130101.inputDenpyobango" /></td>
                </tr>
            </table>
            <div style="width:500;height:80px;overflow: scroll" id="bikoArea">
            </div>
            <div style="width:500;height:300px;overflow: scroll" id="infoArea">
            </div>
            </div>
        </div>
        <div id="kakuninDiv" style="display:none;width:98%;position: absolute;top:0px;z-index:9999;background-color: white;overflow:auto">
            <table align="right" width="200px">
                <tr>
                    <td align="center"><input type="button" style="height:25px;width:200px;font-size: 20px;font-weight:700" onclick="hideDiv();" value="閉じる" /></td>
                </tr>
            </table>   
            <table align="center" width="100%">
                <tr>
                    <td align="center"><iframe id="kakunin" src="" frameborder="0" width="99%" height="600px"></iframe></td>
                </tr>
            </table>
            
        </div>
        <div id='pop-div2' style="left:30%;width:350px;height:200px;display:none" class="pop-box">   
            <div class="pop-box-body" >
            <table align="center" width="300px">
                <tr>
                    <td align="center"><input type="button" onclick="hideDiv();" value="戻る" /></td>
                    <td align="center"><input type="button" id="submitbtn" onclick="actionSubmit('A13020111')" value="確定" /></td>
                    <td align="center" id="cmtkakuninTd" style="display:none">コメント確認済み<input type="checkbox" id="cmtkakunin" ></td>
                </tr>
            </table>
            <table align="center">
                <tr height="40px">
                    <td align="center">チェック必要：</td>
                    <td align="center"><s:checkbox name="f130101.checkhitiyo"/></td>
                </tr>
                <tr height="40px">
                    <td align="center">パースワード：</td>
                    <td align="center"><s:password name="f130101.checkPass" /></td>
                </tr>
            </table>
            </div>
        </div>

        <s:hidden name="successFlg"/>
        <s:hidden name="f130101.userId"/>
        <s:hidden name="pass_val"/>
	</s:form>
</body>
<script type="text/javascript">
window.onscroll = function(){
	
	document.getElementsByName("scrolly")[0].value = $(document).scrollTop();
	document.getElementsByName("scrollx")[0].value = $(document).scrollLeft();
}
</script>
</html>