<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	div_obj.css("top",($(window).height()-160)/2);
	div_obj.css("left",($(window).width()-350)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
    
    document.getElementsByName("f140101.inputJuchubango")[0].value="";
    document.getElementsByName("f140101.inputDenpyobango")[0].value="";
    
    document.getElementsByName("f140101.inputJuchubango")[0].focus();
} 

function popupDiv2() {   
    var div_obj = $("#kakuninDiv"); 
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
    
    $("#kakunin").attr("src","A13020101");
}

//监听Enter键自动提交事件 
function keyboardEvent(event){ 
var keyCode=event.keyCode ? event.keyCode:event.which?event.which:event.charCode;//解决浏览器之间的差异问题 
if(keyCode==13){ 
	document.getElementsByName("f140101.inputDenpyobango")[0].focus();
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
}
function replaceBr(){
	for (var i = 0; typeof (document
            .getElementsByName("f140101.orderList[" + i
                    + "].sofusakinamae")[0]) != "undefined"; i++) {
            document.getElementById("td0["+i+"]").innerHTML = document.getElementById("td0["+i+"]").innerHTML.replace(new RegExp("&lt;br&gt;", 'g'),"<br/>");
    }
}

function checkAll(){
	var allcheck = true;
	
	for (var i = 0; typeof (document.getElementsByName("f140101.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
		if(document.getElementsByName("f140101.orderList[" + i + "].ischecked")[0].checked == false){
			allcheck = false;
			break;
		}
    }
	
	if(allcheck){
		for (var i = 0; typeof (document.getElementsByName("f140101.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
			document.getElementsByName("f140101.orderList[" + i + "].ischecked")[0].checked = false;
	    }
	}else{
		for (var i = 0; typeof (document.getElementsByName("f140101.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
			document.getElementsByName("f140101.orderList[" + i + "].ischecked")[0].checked = true;
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
	var no = document.getElementsByName("f140101.orderList["+index+"].chumonbango")[0].value;
	
	
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
	var no = document.getElementsByName("f140101.inputJuchubango")[0].value;
	$.post("A13020109", {orderNo:no}, function(result) {
		if(result != ""){
			if(confirm(result)){
				if(confirm("備考欄確認できましたか？")){
					actionSubmit('A13010104');
				}
			}
		}else{
			actionSubmit('A13010104');
		}
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
                <td class="td_bg" width="40px">联络：</td>
                <td class="td_bg" width="50px">
                    <s:select list="#{'':'全部','1':'已联络','0':'未联络'}" name="f140101.takuhaibinkaisha"/>
                </td>
                <td class="td_bg" width="40px">店铺：</td>
                <td class="td_bg">
                    <s:select list="#{'':'全部','123mart':'123mart','3eshop':'3eshop','citycat':'citycat','trend777':'trend777'}" name="f140101.takuhaibinkaisha"/>
                </td>
            </tr>
		</table>
		</div>
        <div id="div2">
        <table cellspacing="1" cellpadding="2" width="99%" border="0" class="table">
            <tr class="bg_tr" height="30px">
                <s:if test="f140101.hasoshubetsu == 1"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=1')">距下单日7日以上未发送(<s:if test ='f140101.chumonbikara7Count>0'><font color="red" size="4"></s:if><s:text name="f140101.chumonbikara7Count"/><s:if test ='f140101.chumonbikara7Count>0'></font></s:if><s:hidden name="f140101.chumonbikara7Count"/>)</a></td>
                <s:if test="f140101.hasoshubetsu == 2"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=2')">距下单日6日未发送(<s:if test ='f140101.chumonbikara6Count>0'><font color="red" size="4"></s:if><s:text name="f140101.chumonbikara6Count"/><s:if test ='f140101.chumonbikara6Count>0'></font></s:if><s:hidden name="f140101.chumonbikara6Count"/>)</a></td>
                <s:if test="f140101.hasoshubetsu == 3"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=3')">距下单日5日未发送(<s:if test ='f140101.chumonbikara5Count>0'><font color="red" size="4"></s:if><s:text name="f140101.chumonbikara5Count"/><s:if test ='f140101.chumonbikara5Count>0'></font></s:if><s:hidden name="f140101.chumonbikara5Count"/>)</a></td>
                <s:if test="f140101.hasoshubetsu == 4"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=4')">距下单日4日未发送(<s:if test ='f140101.chumonbikara4Count>0'><font color="red" size="4"></s:if><s:text name="f140101.chumonbikara4Count"/><s:if test ='f140101.chumonbikara4Count>0'></font></s:if><s:hidden name="f140101.chumonbikara4Count"/>)</a></td>
                <s:if test="f140101.hasoshubetsu == 5"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=5')">距下单日3日未发送(<s:if test ='f140101.chumonbikara3Count>0'><font color="red" size="4"></s:if><s:text name="f140101.chumonbikara3Count"/><s:if test ='f140101.chumonbikara3Count>0'></font></s:if><s:hidden name="f140101.chumonbikara3Count"/>)</a></td>
                <s:if test="f140101.hasoshubetsu == 6"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=6')">距下单日2日未发送(<s:if test ='f140101.chumonbikara2Count>0'><font color="red" size="4"></s:if><s:text name="f140101.chumonbikara2Count"/><s:if test ='f140101.chumonbikara2Count>0'></font></s:if><s:hidden name="f140101.chumonbikara2Count"/>)</a></td>
            </tr>
            <tr class="bg_tr" height="30px">
                <s:if test="f140101.hasoshubetsu == 7"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=1')">已过发送约定日(<s:if test ='f140101.yakusokubisugiCount>0'><font color="red" size="4"></s:if><s:text name="f140101.yakusokubisugiCount"/><s:if test ='f140101.yakusokubisugiCount>0'></font></s:if><s:hidden name="f140101.yakusokubisugiCount"/>)</a></td>
                <s:if test="f140101.hasoshubetsu == 8"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=2')">距发送约定日还有0天(<s:if test ='f140101.yakusokubimade0Count>0'><font color="red" size="4"></s:if><s:text name="f140101.yakusokubimade0Count"/><s:if test ='f140101.yakusokubimade0Count>0'></font></s:if><s:hidden name="f140101.yakusokubimade0Count"/>)</a></td>
                <s:if test="f140101.hasoshubetsu == 9"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=3')">距发送约定日还有1天(<s:if test ='f140101.yakusokubimade1Count>0'><font color="red" size="4"></s:if><s:text name="f140101.yakusokubimade1Count"/><s:if test ='f140101.yakusokubimade1Count>0'></font></s:if><s:hidden name="f140101.yakusokubimade1Count"/>)</a></td>
                <s:if test="f140101.hasoshubetsu == 10"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=4')">距发送约定日还有2天(<s:if test ='f140101.yakusokubimade2Count>0'><font color="red" size="4"></s:if><s:text name="f140101.yakusokubimade2Count"/><s:if test ='f140101.yakusokubimade2Count>0'></font></s:if><s:hidden name="f140101.yakusokubimade2Count"/>)</a></td>
                <s:if test="f140101.hasoshubetsu == 11"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=5')">距发送约定日还有3天(<s:if test ='f140101.yakusokubimade3Count>0'><font color="red" size="4"></s:if><s:text name="f140101.yakusokubimade3Count"/><s:if test ='f140101.yakusokubimade3Count>0'></font></s:if><s:hidden name="f140101.yakusokubimade3Count"/>)</a></td>
                <s:if test="f140101.hasoshubetsu == 12"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=6')">距发送约定日还有4天(<s:if test ='f140101.yakusokubimade4Count>0'><font color="red" size="4"></s:if><s:text name="f140101.yakusokubimade4Count"/><s:if test ='f140101.yakusokubimade4Count>0'></font></s:if><s:hidden name="f140101.yakusokubimade4Count"/>)</a></td>
                <s:hidden name="f140101.hasoshubetsu"/>
            </tr>
		</table>
        <table cellspacing="1" cellpadding="2" width="600px" border="0" class="table">
            <tr class="bg_tr">
                <s:if test="f140101.tenposhubetsu == 1"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=1')">楽天(<s:text name="f140101.rakutenCount"/>)<s:hidden name="f140101.rakutenCount"/></a></td>
                <s:if test="f140101.tenposhubetsu == 2"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=2')">Yahooショッピング(<s:text name="f140101.yahooShoppingCount"/>)<s:hidden name="f140101.yahooShoppingCount"/></a></td>
                <s:if test="f140101.tenposhubetsu == 3"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=3')">DENA(<s:text name="f140101.denaCount"/>)</a><s:hidden name="f140101.denaCount"/></td>
                <s:if test="f140101.tenposhubetsu == 4"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=4')">ヤフオク(<s:text name="f140101.yafuokuCount"/>)</a><s:hidden name="f140101.yafuokuCount"/></td>
                <s:hidden name="f140101.tenposhubetsu"/>
            </tr>
            <tr class="bg_tr">
                <s:if test="f140101.tenposhubetsu == 1"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=1')">全部(<s:text name="f140101.yoteibiAll"/>)<s:hidden name="f140101.yoteibiAll"/></a></td>
                <s:if test="f140101.tenposhubetsu == 2"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=2')">已出预订日(<s:text name="f140101.yoteibiAri"/>)<s:hidden name="f140101.yoteibiAri"/></a></td>
                <s:if test="f140101.tenposhubetsu == 3"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=3')">未出预订日(<s:text name="f140101.yoteibiNashi"/>)</a><s:hidden name="f140101.yoteibiNashi"/></td>
                <td width="150px" class="td_bg" style="font-size:13px;" align="center"></td>
                <s:hidden name="f140101.tenposhubetsu"/>
            </tr>
		</table>
        <table width="99%" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
                <td width="3%" align="center"><input type="checkbox" onchange="checkAll()"></td>
			    <td width="6%" align="center">平台/<br/>店铺</td>
			    <td width="15%" align="center">订单号</td>
			    <td width="7%" align="center">订单日时</td>
			    <td width="7%" align="center">发送约定日</td>
			    <td width="7%" align="center">发送预订日</td>
			    <td width="6%" align="center">发送约定日联络</td>
			    <td width="6%" align="center">发送预订日联络</td>
			    <td width="12%" align="center">客人备注 </td>
			    <td width="12%" align="center">客服备注 </td>
			    <td width="12%" align="center">发送预警备注 </td>
			    <td align="center"></td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="99%" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f140101.orderList" status="status">
			<tr class="bg_tr" height="50px">
			    <td width="3%" class="td_bg" align="center" valign="middle">
			        <s:checkbox name="f140101.orderList[%{#status.index}].ischecked"/>
			    </td>
			    <td width="6%" class="td_bg" align="center" valign="middle">
			        <s:property value='site'/>/<br/><s:property value='tenpo'/>
			        <s:hidden name="f140101.orderList[%{#status.index}].site" value="%{site}"/>
			        <s:hidden name="f140101.orderList[%{#status.index}].tenpo" value="%{tenpo}"/>
			    </td>
			    <td width="15%" class="td_bg" align="center" valign="middle">
			        <a target="_blank" href="A10010201?OrderNo=<s:property value='chumonbango'/>"><s:property value='chumonbango'/></a>
			        <s:hidden name="f140101.orderList[%{#status.index}].chumonbango" value="%{chumonbango}"/>
			    </td>
				<td width="7%" class="td_bg" align="center" valign="middle"><s:property value='chumonichiji'/><s:hidden name="f140101.orderList[%{#status.index}].chumonichiji" value="%{chumonichiji}"/></td>
				<td width="7%" class="td_bg" align="center" valign="middle"><s:property value='hasoyakusokubi'/><s:hidden name="f140101.orderList[%{#status.index}].hasoyakusokubi" value="%{hasoyakusokubi}"/></td>
				<td width="7%" class="td_bg" align="center" valign="middle"><s:property value='hasoyoteibi'/><s:hidden name="f140101.orderList[%{#status.index}].hasoyoteibi" value="%{hasoyoteibi}"/></td>
				<td width="6%" class="td_bg" align="center" valign="middle"><s:property value='renrakusts'/><s:hidden name="f140101.orderList[%{#status.index}].renrakusts" value="%{renrakusts}"/></td>
				<td width="12%" class="td_bg" align="center" valign="middle">
				    <s:property value='comento'/>
				    <s:hidden name="f140101.orderList[%{#status.index}].comento" value="%{comento}"/>
				</td>
				<td width="12%" class="td_bg" align="center" valign="middle">
				    <s:property value='biko'/>
				    <s:hidden name="f140101.orderList[%{#status.index}].biko" value="%{biko}"/>
				</td>
				<td width="12%" class="td_bg" align="center" valign="middle">
				    <s:property value='biko2'/>
				    <s:hidden name="f140101.orderList[%{#status.index}].biko2" value="%{biko2}"/>
				</td>
				<td class="td_bg" align="center">
				    <input type="button" value="复制信件模板" name="copyMailModel" onclick=""/><br/>
				    <input type="button" value="发信" name="soushin"  onclick=""/><br/>
				    <input type="button" value="设置为联络完毕" name="setRenrakuzumi"  onclick=""/>
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
        <s:hidden name="successFlg"/>
	</s:form>
</body>
<script type="text/javascript">
window.onscroll = function(){
	
	document.getElementsByName("scrolly")[0].value = $(document).scrollTop();
	document.getElementsByName("scrollx")[0].value = $(document).scrollLeft();
}
</script>
</html>