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

var htmlText;
var frm;
function shori(orderNo,expNo,kaisha,sts){
    loadContent("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="+orderNo);
	window.frames["main"].document.body.innerHTML = htmlText;
	frm = window.frames["main"];
	
	var stsSel = frm.document.getElementsByName("status_id_"+orderNo)[0];
	
	for(var i=0;i<stsSel.options.length;i++){
		
	    if(stsSel.options[i].text == sts){
		    stsSel.options[i].selected=true;
			break;
		}	
	}
	var objs = frm.document.getElementsByTagName("input");
	
	for(var i=0;i<objs.length;i++){
		
		if(objs[i].name.indexOf("shipping_number_")==0){
		    objs[i].value = expNo;	
		}
		if(objs[i].name.indexOf("deliver_company_")==0){
		    objs[i].value = kaisha;
		}
	}
	
	
	frm.document.getElementsByName("B016")[0].click();	
}
function check(){
	loadContent("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="+orderNo);
	window.frames["main"].document.body.innerHTML = htmlText;
	frm = window.frames["main"];
	
	var stsSel = frm.document.getElementsByName("status_id_"+orderNo)[0].value;
	var kaishaSel = frm.document.getElementsByName("deliver_company_"+orderNo)[0].value;
	var denpyo = frm.document.getElementsByName("shipping_number_"+orderNo)[0].value;

}

function loadContent(strURL){ 
   if (typeof strURL != "string" || strURL == "") return;
   var xml = new ActiveXObject("Microsoft.XMLHTTP");
   xml.open("get",strURL,false);
   xml.send();
   if(xml.status==200) htmlText = xml.responseText;
   xml=null;
}



function popupDiv() {   
    var div_obj = $("#pop-div"); 
	div_obj.css("top",($(window).height()-260)/2);
	div_obj.css("left",($(window).width()-450)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  

}   

function doAction(orderNoList,expNoList,kaisha,sts){
    popupDiv();
    var logDisplay = document.getElementById("logArea");
    logDisplay.value = "処理開始。。。。";
    for(var i=0;i<orderNoList.length;i++){
    	logDisplay.value = (i+1 + "/" + orderNoList.length + " " + orderNoList[i]);
    	shori(orderNoList[i],expNoList[i],kaisha[i],sts);
    }
    
	//setTimeout(function(){
	//	check();
	//},3000);
    //var logKey = "";
    //logKey = _getRandomString(10);
    //actionSubmit('A13020103'+'?logKey='+logKey);
    //setInterval(function(){
    //    getLog(logKey);
    //},2000);
}
function hideDiv() {   
	  $("#mask").hide();   
	  $("#pop-div").hide(); 
}
function init() {
	if(document.getElementsByName("successFlg")[0].value == '1'){
		document.getElementsByName("successFlg")[0].value = '0';
		alert("反映済みでした！");
	}
	replaceBr();
	var result = "";
	if(document.getElementsByName("shoriFlg")[0].value == "1"){
		result = document.getElementsByName("result")[0].value;
		document.getElementsByName("result")[0].value = "";
		document.getElementsByName("shoriFlg")[0].value = "0";
		var orderNoList = result.split("%%")[0].split("&");
		var expNoList = result.split("%%")[1].split("&");
		var kaisha = result.split("%%")[2].split("&");
		var sts = "temp";
		doAction(orderNoList,expNoList,kaisha,sts);
	}
	

}
function replaceBr(){
	for (var i = 0; typeof (document
            .getElementsByName("f130201.orderList[" + i
                    + "].sofusakinamae")[0]) != "undefined"; i++) {
            document.getElementById("td0["+i+"]").innerHTML = document.getElementById("td0["+i+"]").innerHTML.replace(new RegExp("&lt;br&gt;", 'g'),"<br/>");
    }
}

function checkAll(){
	var allcheck = true;
	
	for (var i = 0; typeof (document.getElementsByName("f130201.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
		if(document.getElementsByName("f130201.orderList[" + i + "].ischecked")[0].checked == false){
			allcheck = false;
			break;
		}
    }
	
	if(allcheck){
		for (var i = 0; typeof (document.getElementsByName("f130201.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
			document.getElementsByName("f130201.orderList[" + i + "].ischecked")[0].checked = false;
	    }
	}else{
		for (var i = 0; typeof (document.getElementsByName("f130201.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
			document.getElementsByName("f130201.orderList[" + i + "].ischecked")[0].checked = true;
	    }
	}
}

function henko(bango,kaisha,denpyo){
	$.post("A13010110", {unsokaisha:kaisha,denpyobango:denpyo,orderNo:bango}, function(result) {
		if("true" == result){
		    alert("変更しました");
		}else{
			alert("失敗しました")
		}
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
            z-index: 9999; /*这个数值要足够大，才能够显示在最上层*/  
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
<iframe id="main" src="" style="display:none"></iframe>
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
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f130201.kikanStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f130201.kikanEnd"/>
                </td>
                <td class="td_bg">受注番号：</td>
                <td class="td_bg" >
                    <s:textfield size="35" maxlength="50" name="f130201.chumonbango"/>
                </td>
            </tr>
            <tr>
                <td class="td_bg">送付先名前：</td>
                <td class="td_bg" >
                    <s:textfield size="25" maxlength="50" name="f130201.sofusakinamae"/>
                </td>
                <td class="td_bg">送付先電話番号：</td>
                <td class="td_bg">
                    <s:textfield size="15" maxlength="50" name="f130201.sofusakidenwabango"/>
                </td>
             </tr>
             <tr>
                <td class="td_bg" >お支払い方法</td>
                <td class="td_bg" >
                    <s:select list="#{'':'--','クレジットカード':'クレジットカード','代金引換':'代金引換','銀行振込 ':'銀行振込 ','楽天バンク決済':'楽天バンク決済','Edy決済':'楽天Edy決済','その他':'その他'}" name="f130201.oshiharaihoho"/>
                </td>
                <td class="td_bg" >配送方法</td>
                <td class="td_bg" >
                    <s:select list="#{'':'--','ネコポス':'ネコポス','宅配便':'宅配便'}" name="f130201.haisohoho"/>
                </td>
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A13020102')" value="検索" style="width:50px;height:25px"/></td>
            </tr>
            <tr>
                <td class="td_bg">店舗別</td>
                <td class="td_bg" colspan="3">
                    <s:select list="#{'':'--','coverforefront':'coverforefront','WhiteSweet':'WhiteSweet'}" name="f130201.tenpo"/>
                </td>
            </tr>
        </table>
		</div>
        <div id="div2">
        <table width="99%">
            <tr>
                <td align="left">
                    <s:select list="#{'0':'選択した注文','1':'すべての注文'}" name="f130201.outtype"/>
                   
                    <input type="button" style="height:30px" onclick="if(confirm('反映済みに設定しますか')){actionSubmit('A13020106')};" value="反映済みに設定"/>
                    <input type="button" style="height:30px" onclick="actionSubmit('A13020112')" value="下载雅虎未反应csv"/>
                    <input type="button" style="height:30px" onclick="actionSubmit('A13020113')" value="下载乐天未反应csv"/>
                    <input type="button" style="height:30px" onclick="actionSubmit('A13020114')" value="设置反应完毕"/>
                </td>
            </tr>
        </table>
        <table cellspacing="1" cellpadding="2" width="600px" border="0" class="table">
            <tr class="bg_tr">
                <s:if test="f130201.tenposhubetsu == 1"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13020104?type=1')">楽天(<s:text name="f130201.rakutenCount"/>)<s:hidden name="f130201.rakutenCount"/></a></td>
                <s:if test="f130201.tenposhubetsu == 2"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13020104?type=2')">Yahooショッピング(<s:text name="f130201.yahooShoppingCount"/>)<s:hidden name="f130201.yahooShoppingCount"/></a></td>
                <s:if test="f130201.tenposhubetsu == 3"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13020104?type=3')">DENA(<s:text name="f130201.denaCount"/>)</a><s:hidden name="f130201.denaCount"/></td>
                <s:if test="f130201.tenposhubetsu == 4"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13020104?type=4')">ヤフオク(<s:text name="f130201.yafuokuCount"/>)</a><s:hidden name="f130201.yafuokuCount"/></td>
                <s:if test="f130201.tenposhubetsu == 5"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13020104?type=5')">ポンパレモール(<s:text name="f130201.ponpareCount"/>)</a><s:hidden name="f130201.ponpareCount"/></td>
                <s:if test="f130201.tenposhubetsu == 6"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13020104?type=6')">qoo10(<s:text name="f130201.qoo10Count"/>)</a><s:hidden name="f130201.qoo10Count"/></td>
                <s:hidden name="f130201.tenposhubetsu"/>
            </tr>
		</table>
        <table width="99%" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
                <td width="3%" align="center"><input type="checkbox" onchange="checkAll()"></td>
			    <td width="8%" align="center">サイト/<br/>店舗</td>
			    <td width="15%" align="center">受注番号</td>
			    <td width="16%" align="center">発送情報</td>
			    <td width="10%" align="center">お支払い方法</td>
			    <td width="6%" align="center">合計金額&nbsp;/<br/>&nbsp;請求額</td>
			    <td width="9%" align="center">送付先名前</td>
			    <td width="5%" align="center">配送方法 </td>
			    <td width="12%" align="center">コメント</td>
				<td align="center">備考</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="99%" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f130201.orderList" status="status">
			<tr class="bg_tr" height="50px">
			    <td width="3%" class="td_bg" align="center" valign="middle">
			        <s:checkbox name="f130201.orderList[%{#status.index}].ischecked"/>
			    </td>
			    <td width="8%" class="td_bg" align="center" valign="middle">
			        <s:property value='site'/>/<br/><s:property value='tenpo'/>
			        <s:hidden name="f130201.orderList[%{#status.index}].site" value="%{site}"/>
			        <s:hidden name="f130201.orderList[%{#status.index}].tenpo" value="%{tenpo}"/>
			    </td>
			    <td width="15%" class="td_bg" align="center" valign="middle">
			        <s:if test="f130201.tenposhubetsu == 3">
			            <a target="_blank" href="https://pki.bidders.co.jp/dap/sv/mycb272?seq_suc_id=<s:property value='chumonbango'/>"><s:property value='chumonbango'/></a>
			        </s:if>
			        <s:else>
			            <a target="_blank" href="A10010201?OrderNo=<s:property value='chumonbango'/>"><s:property value='chumonbango'/></a>
			        </s:else>
			        <s:hidden name="f130201.orderList[%{#status.index}].chumonbango" value="%{chumonbango}"/>
			    </td>
				<td width="16%" class="td_bg" align="center" valign="middle">
				　　　　<s:select list="#{'1001':'ヤマト運輸','1002':'佐川急便'}" name="f130201.orderList[%{#status.index}].haisokaisha" />
				    <s:textfield size="15" name="f130201.orderList[%{#status.index}].denpyobango" value="%{denpyobango}"/>
				    <input type="button" value="変更" onclick="henko('<s:property value='chumonbango'/>',document.getElementsByName('f130201.orderList[${status.index}].haisokaisha')[0].value,document.getElementsByName('f130201.orderList[${status.index}].denpyobango')[0].value)">
				</td>
				<td width="10%" class="td_bg" align="center" valign="middle"><s:property value='oshiharaihoho'/><s:hidden name="f130201.orderList[%{#status.index}].oshiharaihoho" value="%{oshiharaihoho}"/></td>
				<td width="6%" class="td_bg" align="right" valign="middle">
				    <s:property value='gokeikingaku'/>&nbsp;/<br/>&nbsp;<s:property value='seikyujingaku'/>
				    <s:hidden name="f130201.orderList[%{#status.index}].gokeikingaku" value="%{gokeikingaku}"/>
				    <s:hidden name="f130201.orderList[%{#status.index}].seikyujingaku" value="%{seikyujingaku}"/>
				</td>
				<td width="9%" class="td_bg" align="center" valign="middle" id="td0[${status.index}]">
				    <s:property value='sofusakinamae'/>
				    <s:hidden name="f130201.orderList[%{#status.index}].sofusakinamae" value="%{sofusakinamae}"/>
				</td>
				<td width="5%" class="td_bg" align="center" valign="middle"><s:property value='haisohoho'/><s:hidden name="f130201.orderList[%{#status.index}].haisohoho" value="%{haisohoho}"/></td>
				<td width="12%" class="td_bg" align="center" valign="middle">
				    <s:property value='comento'/>
				    <s:hidden name="f130201.orderList[%{#status.index}].comento" value="%{comento}"/>
				</td>
				<td class="td_bg" align="center">
				    <s:label name="f130201.orderList[%{#status.index}].biko" value="%{biko}"/>
				    <s:hidden name="f130201.orderList[%{#status.index}].biko" value="%{biko}"/>
				    <s:hidden name="f130201.orderList[%{#status.index}].sofusakidenwabango" value="%{sofusakidenwabango}"/>
				</td>
			</tr>
		    </s:iterator>
		</table>
		<s:hidden name="viewId" value="V130201"/>
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
		            <a href="#" id="firstPage" onclick="actionSubmit('A13020105?page=first')">首页</a>&nbsp;<a href="#" id="lastPage" onclick="actionSubmit('A13020105?page=last')">上一页</a>&nbsp;
		            <a href="#" id="nextPage" onclick="actionSubmit('A13020105?page=next')">下一页</a>&nbsp;<a href="#" id="endPage" onclick="actionSubmit('A13020105?page=end')">尾页</a>
		            &nbsp;&nbsp;&nbsp;&nbsp;第<s:label name="nowPage"/><s:hidden id="nowPage" name="nowPage"/>页&nbsp;/&nbsp;共<s:label name="allPage"/><s:hidden id="allPage" name="allPage"/>页
		            &nbsp;&nbsp;&nbsp;&nbsp;跳转到<s:textfield cssErrorStyle="background:red;" name="goPage" size="1"/>页 <input onclick="actionSubmit('A13020105?page=go')" type="button" value="GO">
		        </td>
		    </tr>
		</table>
		</div>
		<div id='pop-div' style="left:350px;top:100px;width:450px;height:260px;position:absolute;display:none" class="pop-box">   
            <div class="pop-box-body" >
            <table>
                <tr>
                    <td colspan="2" align="center"><textarea id="logArea" style="width:400px;height:200px" readOnly></textarea></td>
                </tr>
            </table>
            </div>
        </div>
        <s:hidden name="successFlg"/>
        <s:hidden name="result"/>
        <s:hidden name="shoriFlg"/>
	</s:form>
</body>
<script type="text/javascript">
window.onscroll = function(){
	
	document.getElementsByName("scrolly")[0].value = $(document).scrollTop();
	document.getElementsByName("scrollx")[0].value = $(document).scrollLeft();
}
</script>
</html>