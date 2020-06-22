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

function checkAll(){
	var allcheck = true;
	
	for (var i = 0; typeof (document.getElementsByName("f130102.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
		if(document.getElementsByName("f130102.orderList[" + i + "].ischecked")[0].checked == false){
			allcheck = false;
			break;
		}
    }
	
	if(allcheck){
		for (var i = 0; typeof (document.getElementsByName("f130102.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
			document.getElementsByName("f130102.orderList[" + i + "].ischecked")[0].checked = false;
	    }
	}else{
		for (var i = 0; typeof (document.getElementsByName("f130102.orderList[" + i + "].ischecked")[0]) != "undefined"; i++) {
			document.getElementsByName("f130102.orderList[" + i + "].ischecked")[0].checked = true;
	    }
	}
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
<div id='mask' class="mask" style="width:100%;height:100%;display:none"></div>
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		</div>
        <div id="div2">
        <table width="99%">
            <tr>
                <td align="right">
                    <input type="button" style="height:30px" onclick="actionSubmit('A13010202');" value="反映待ちに設定"/>
                </td>
            </tr>
        </table>
        <table cellspacing="1" cellpadding="2" width="99%" border="0" class="table">
            <tr class="bg_tr" height="30px">
                <s:if test="f130102.hasoshubetsu == 1"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=1')">発送待ち-発送可(<s:if test ='f130102.hasomachihasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130102.hasomachihasokaCount"/><s:if test ='f130102.hasomachihasokaCount>0'></font></s:if><s:hidden name="f130102.hasomachihasokaCount"/>)</a></td>
                <s:if test="f130102.hasoshubetsu == 2"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=2')">追加発送待ち-発送可(<s:if test ='f130102.tuikahasomachihasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130102.tuikahasomachihasokaCount"/><s:if test ='f130102.tuikahasomachihasokaCount>0'></font></s:if><s:hidden name="f130102.tuikahasomachihasokaCount"/>)</a></td>
                <s:if test="f130102.hasoshubetsu == 3"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=3')">分納発送待ち-発送可(<s:if test ='f130102.bunnohasomachihasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130102.bunnohasomachihasokaCount"/><s:if test ='f130102.bunnohasomachihasokaCount>0'></font></s:if><s:hidden name="f130102.bunnohasomachihasokaCount"/>)</a></td>
                <s:if test="f130102.hasoshubetsu == 4"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=4')">発送待ち-至急-発送可(<s:if test ='f130102.hasomachishikyuhasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130102.hasomachishikyuhasokaCount"/><s:if test ='f130102.hasomachishikyuhasokaCount>0'></font></s:if><s:hidden name="f130102.hasomachishikyuhasokaCount"/>)</a></td>
                <s:if test="f130102.hasoshubetsu == 5"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=5')">追加発送待ち-至急-発送可(<s:if test ='f130102.tuikahasomachishikyuhasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130102.tuikahasomachishikyuhasokaCount"/><s:if test ='f130102.tuikahasomachishikyuhasokaCount>0'></font></s:if><s:hidden name="f130102.tuikahasomachishikyuhasokaCount"/>)</a></td>
                <s:if test="f130102.hasoshubetsu == 6"><td class="td_bg" style="font-size:13px;background-color: lightblue"></s:if><s:else><td class="td_bg" style="font-size:13px"></s:else><a href="javascript:actionSubmit('A13010106?type=6')">分納発送待ち-至急-発送可(<s:if test ='f130102.bunnohasomachishikyuhasokaCount>0'><font color="red" size="4"></s:if><s:text name="f130102.bunnohasomachishikyuhasokaCount"/><s:if test ='f130102.bunnohasomachishikyuhasokaCount>0'></font></s:if><s:hidden name="f130102.bunnohasomachishikyuhasokaCount"/>)</a></td>
                <s:hidden name="f130102.hasoshubetsu"/>
            </tr>
		</table>
        <table cellspacing="1" cellpadding="2" width="600px" border="0" class="table">
            <tr class="bg_tr">
                <s:if test="f130102.tenposhubetsu == 1"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=1')">楽天(<s:text name="f130102.rakutenCount"/>)<s:hidden name="f130102.rakutenCount"/></a></td>
                <s:if test="f130102.tenposhubetsu == 2"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=2')">Yahooショッピング(<s:text name="f130102.yahooShoppingCount"/>)<s:hidden name="f130102.yahooShoppingCount"/></a></td>
                <s:if test="f130102.tenposhubetsu == 3"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=3')">DENA(<s:text name="f130102.denaCount"/>)</a><s:hidden name="f130102.denaCount"/></td>
                <s:if test="f130102.tenposhubetsu == 4"><td width="150px" class="td_bg" style="font-size:13px;background-color: lightblue" align="center"></s:if><s:else><td width="150px" class="td_bg" style="font-size:13px" align="center"></s:else><a href="javascript:actionSubmit('A13010105?type=4')">ヤフオク(<s:text name="f130102.yafuokuCount"/>)</a><s:hidden name="f130102.yafuokuCount"/></td>
                <s:hidden name="f130102.tenposhubetsu"/>
            </tr>
		</table>
        <table width="99%" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
                <td width="3%" align="center"><input type="checkbox" onchange="checkAll()"></td>
			    <td width="6%" align="center">サイト/<br/>店舗</td>
			    <td width="15%" align="center">受注番号</td>
			    <td width="10%" align="center">注文日時</td>
			    <td width="10%" align="center">お支払い方法</td>
			    <td width="6%" align="center">合計金額&nbsp;/<br/>&nbsp;請求額</td>
			    <td width="9%" align="center">送付先名前</td>
			    <td width="5%" align="center">配送方法 </td>
			    <td width="12%" align="center">コメント</td>
			    <td width="15%" align="center">届け時間帯</td>
				<td align="center">備考</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="99%" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f130102.orderList" status="status">
			<tr class="bg_tr" height="50px">
			    <td width="3%" class="td_bg" align="center" valign="middle">
			        <s:checkbox name="f130102.orderList[%{#status.index}].ischecked"/>
			    </td>
			    <td width="6%" class="td_bg" align="center" valign="middle">
			        <s:property value='site'/>/<br/><s:property value='tenpo'/>
			        <s:hidden name="f130102.orderList[%{#status.index}].site" value="%{site}"/>
			        <s:hidden name="f130102.orderList[%{#status.index}].tenpo" value="%{tenpo}"/>
			    </td>
			    <td width="15%" class="td_bg" align="center" valign="middle">
			        <a target="_blank" href="A10010201?OrderNo=<s:property value='chumonbango'/>"><s:property value='chumonbango'/></a>
			        <s:hidden name="f130102.orderList[%{#status.index}].chumonbango" value="%{chumonbango}"/>
			    </td>
				<td width="10%" class="td_bg" align="center" valign="middle"><s:property value='chumonichiji'/><s:hidden name="f130102.orderList[%{#status.index}].chumonichiji" value="%{chumonichiji}"/></td>
				<td width="10%" class="td_bg" align="center" valign="middle"><s:property value='oshiharaihoho'/><s:hidden name="f130102.orderList[%{#status.index}].oshiharaihoho" value="%{oshiharaihoho}"/></td>
				<td width="6%" class="td_bg" align="right" valign="middle">
				    <s:property value='gokeikingaku'/>&nbsp;/<br/>&nbsp;<s:property value='seikyujingaku'/>
				    <s:hidden name="f130102.orderList[%{#status.index}].gokeikingaku" value="%{gokeikingaku}"/>
				    <s:hidden name="f130102.orderList[%{#status.index}].seikyujingaku" value="%{seikyujingaku}"/>
				</td>
				<td width="9%" class="td_bg" align="center" valign="middle" id="td0[${status.index}]">
				    <s:property value='sofusakinamae'/>
				    <s:hidden name="f130102.orderList[%{#status.index}].sofusakinamae" value="%{sofusakinamae}"/>
				</td>
				<td width="5%" class="td_bg" align="center" valign="middle"><s:property value='haisohoho'/><s:hidden name="f130102.orderList[%{#status.index}].haisohoho" value="%{haisohoho}"/></td>
				<td width="12%" class="td_bg" align="center" valign="middle">
				    <s:property value='comento'/>
				    <s:hidden name="f130102.orderList[%{#status.index}].comento" value="%{comento}"/>
				</td>
				<td width="15%" class="td_bg" align="center">
				    <span id="shiteibi${status.index}"><s:label name="f130102.orderList[%{#status.index}].otodokeshiteibi" value="%{otodokeshiteibi}"/></span>
				    <s:hidden name="f130102.orderList[%{#status.index}].otodokeshiteibi" value="%{otodokeshiteibi}"/>
				    <br/>
				    <input type="button" value="設定" name="otodokeSetei" onclick="showTodoke(${status.index})"/>
				    <input type="button" value="取消" name="otodokeTorikeshi" style="display:none" onclick="hideTodoke(${status.index})"/>
				    <input type="button" value="確定" name="otodokeKakutei" style="display:none" onclick="setTodoke(${status.index})"/><br/>
				    <s:textfield style="display:none" size="13" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="otodokebishitei"/>
				    <s:select style="display:none" list="#{ '':'▼選択してください','01':'午前中','12':'12:00~14:00','14':'14:00~16:00','16':'16:00~18:00','04':'18:00~21:00'}" name="otodokejikantai1" />&nbsp;
				</td>
				<td class="td_bg" align="center">
				    <s:label name="f130102.orderList[%{#status.index}].biko" value="%{biko}"/>
				    <s:hidden name="f130102.orderList[%{#status.index}].biko" value="%{biko}"/>
				    <s:hidden name="f130102.orderList[%{#status.index}].sofusakidenwabango" value="%{sofusakidenwabango}"/>
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
		        <td  align="center"><input type="button" value="発送済、確認待ち" style="height:25px;width:200px;font-size: 20px;font-weight:700" onclick="popupDiv()"></td>
		    </tr>
		</table>
		</div>
		<div id='pop-div' style="left:30%;width:350px;height:160px;display:none" class="pop-box">   
            <div class="pop-box-body" >
            <table align="center">
                <tr height="40px">
                    <td align="center">受注番号：</td>
                    <td align="center"><s:textfield name="f130102.inputJuchubango" onkeydown="keyboardEvent(event);"/></td>
                </tr>
                <tr height="60px">
                    <td align="center">伝票番号：</td>
                    <td align="center"><s:textfield name="f130102.inputDenpyobango"  onkeydown="keyboardEvent2(event);"/></td>
                </tr>
            </table>
            <table align="center" width="200px">
                <tr>
                    <td align="center"><input type="button" onclick="hideDiv();" value="戻る" /></td>
                    <td align="center"><input type="button" id="submitbtn" onclick="actionSubmit('A13010104')" value="確定" /></td>
                </tr>
            </table>
            </div>
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