<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/commonjs.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript">
function init() {
	var scrollxVal = document.getElementsByName("scrollx")[0].value;
	var scrollyVal = document.getElementsByName("scrolly")[0].value;
	if(scrollxVal != ""){
		$(document).scrollLeft(scrollxVal);
	}
	if(scrollyVal != ""){
		$(document).scrollTop(scrollyVal);
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
#div1 {
	width: 97%;
	position: relative;
	height:10%;
	margin-left:auto;
	margin-right:auto;
}

#div2 {
	top: 20px;
	width:97%;
	overflow-x:hidden;
	position: relative;
	margin-left:auto;
	margin-right:auto;
}
#div3 {
	width:97%;
	position: relative;
	top: 39px;
	margin-left:auto;
	margin-right:auto;
}
#div4 {
	width:97%;
	position: relative;
	top: 50px;
	margin-left:auto;
	margin-right:auto;
}
#div5 {
	top: 40px;
	width:97%;
	overflow-x:hidden;
	position: relative;
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
<div id='mask' class="mask" style="width:100%;height:500%;display:none"></div>
    <s:form name="form1" theme="simple">
        <div style="width:90%;margin-top: 5px;margin-left: 10px">
        <h2> <s:label name="title"/></h2>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:16"/>
		<table cellspacing="1" cellpadding="2" width="97%" class="table">
		    <tr>
                <td colspan="2">注文者情報の修正</td>
            </tr>
		    <tr class="bg_tr">
                <td width="200px">お名前：</td>
                <td class="td_bg">
                    <s:textfield name="f100103.chumonshameiji" size="10"/>&nbsp;&nbsp;<s:textfield name="f100103.chumonshanamae" size="10"/>
                </td>
            </tr>
		    <tr class="bg_tr">
                <td width="200px">フリガナ：</td>
                <td class="td_bg">
                    <s:textfield name="f100103.chumonshameijifurigana" size="10"/>&nbsp;&nbsp;<s:textfield name="f100103.chumonshanamaefurigana" size="10"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="200px">ご住所 ：</td>
                <td class="td_bg" id="td01">
                    <font color="red">〒</font>
                    <s:textfield name="f100103.chumonshayubinbango1" size="4"/>&nbsp;-&nbsp;<s:textfield name="f100103.chumonshayubinbango2" size="5"/><br/>
                    <s:textfield name="f100103.chumonshajushotodofuken" size="7"/>&nbsp;&nbsp;<s:textfield name="f100103.chumonshajushotoshiku" size="9"/>&nbsp;&nbsp;<s:textfield name="f100103.chumonshajushochojijo" size="15"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="200px">電話番号：</td>
                <td class="td_bg">
                    <s:textfield name="f100103.chumonshadenwabango1" size="7"/>&nbsp;-&nbsp;<s:textfield name="f100103.chumonshadenwabango2" size="8"/>&nbsp;-&nbsp;<s:textfield name="f100103.chumonshadenwabango3" size="8"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="200px">メールアドレス：</td>
                <td class="td_bg">
                    <s:textfield name="f100103.meruadoresu" size="35"/>
                </td>
            </tr>
        </table>
        <br/>
        <table cellspacing="1" cellpadding="2" width="97%" class="table">
		    <tr>
                <td colspan="2">お支払い・配送方法の修正</td>
            </tr>
		    <tr class="bg_tr">
                <td width="200px">お支払い方法：</td>
                <td class="td_bg">
                    <s:select list="#{ '銀行振込':'銀行振込','代金引換':'代金引換','クレジットカード':'クレジットカード','楽天バンク決済':'楽天バンク決済','Edy決済':'Edy決済','ポイントで全額支払う':'ポイントで全額支払う','ATM決済':'ATM決済','auかんたん決済':'auかんたん決済','ドコモ ケータイ払い':'ドコモ ケータイ払い','ソフトバンクまとめて支払い ':'ソフトバンクまとめて支払い '}" name="f100103.oshiharaihoho" />
                </td>
            </tr>
		    <tr class="bg_tr">
                <td width="200px">配送方法：</td>
                <td class="td_bg">
                    <s:select list="#{ '宅配便':'宅配便','メール便':'メール便'}" name="f100103.haisohoho" />
                </td>
            </tr>
        </table>
        <br/>
        <table cellspacing="1" cellpadding="2" width="97%" class="table">
            <tr>
                <td colspan="4">送付先の修正</td>
            </tr>
            <tr class="bg_tr">
                <td width="20%">名前</td>
                <td width="20%">フリガナ</td>
                <td width="35%">住所</td>
                <td width="25%">電話番号</td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg"><s:textfield name="f100103.sofusakimeiji" size="7"/>&nbsp;&nbsp;<s:textfield name="f100103.sofusakinamae" size="7"/></td>
                <td class="td_bg"><s:textfield name="f100103.sofusakimeijifurigana" size="7"/>&nbsp;&nbsp;<s:textfield name="f100103.sofusakinamaefurigana" size="7"/></td>
                <td class="td_bg">
                    <font color="red">〒</font>
                    <s:textfield name="f100103.sofusakiyubinbango1" size="4"/>&nbsp;-&nbsp;<s:textfield name="f100103.sofusakiyubinbango2" size="5"/><br/>
                    <s:textfield name="f100103.sofusakijushotodofuken" size="7"/>&nbsp;&nbsp;<s:textfield name="f100103.sofusakijushotoshiku" size="9"/>&nbsp;&nbsp;<s:textfield name="f100103.sofusakijushochoijo" size="15"/>
                </td>
                <td class="td_bg"><s:textfield name="f100103.sofusakidenwabango1" size="5"/>&nbsp;-&nbsp;<s:textfield name="f100103.sofusakidenwabango2" size="6"/>&nbsp;-&nbsp;<s:textfield name="f100103.sofusakidenwabango3" size="6"/></td>
            </tr>
        </table>
        </div>
        <div id="div2">
        <table width="97%" class="table" align="left" cellspacing="1" cellpadding="2">
            <tr>
                <td colspan="4">注文商品・金額の修正</td>
                <td colspan="5" align="right">商品番号&nbsp;
                    <s:textfield name="f100103.tuikashohinbango" size="13"/>
                    <input type="button" value="追加" onclick="actionSubmit('A10010304')">
                </td>
            </tr>
            <tr class="bg_tr">
			                <td width="250px" align="center">商品</td>
				            <td width="170px" align="center">項目・選択肢</td>
			                <td width="70px" align="center">単価</td>
			                <td width="70px" align="center">個数</td>
				            <td width="70px" align="center">小計</td>
				            <td width="70px" align="center">税</td>
				            <td width="70px" align="center">送料</td>
				            <td width="70px" align="center">代引</td>
				            <td align="center">&nbsp;</td>
			</tr>
	    </table>
		<table width="97%" class="table"  align="left" cellspacing="1" cellpadding="2">
		        <s:iterator value="f100103.shohinList" status="status">
			    <tr class="bg_tr" height="90px">
				<td width="250px" class="td_bg" align="center" valign="middle">
				    <s:property value='shouhinmei'/>
				    <s:hidden name="f100103.shohinList[%{#status.index}].shohinbango" value="%{shohinbango}"/>
				    <s:hidden name="f100103.shohinList[%{#status.index}].shouhinmei" value="%{shouhinmei}"/>
				</td>
				<td width="170px" class="td_bg" align="left" valign="middle"><s:textarea cssStyle="width:150px;height:50px" name="f100103.shohinList[%{#status.index}].komokusentakushi" value="%{komokusentakushi}"/></td>
				<td width="70px" class="td_bg" align="right" valign="middle"><s:textfield cssStyle="text-align:right" size="3" name="f100103.shohinList[%{#status.index}].tankaku" value="%{tankaku}"/>円</td>
				<td width="70px" class="td_bg" align="center" valign="middle"><s:textfield size="3" name="f100103.shohinList[%{#status.index}].kosu" value="%{kosu}"/></td>
				<td width="70px" class="td_bg" align="right" valign="middle"><s:property value='shoukei'/><s:hidden name="f100103.shohinList[%{#status.index}].shoukei" value="%{shoukei}"/>円</td>
				<td width="70px" class="td_bg" align="center" valign="middle"><s:select name="f100103.shohinList[%{#status.index}].zei" list="#{'別':'別','込':'込'}" value="%{zei}"/></td>
				<td width="70px" class="td_bg" align="center" valign="middle"><s:select name="f100103.shohinList[%{#status.index}].sourou" list="#{'別':'別','込':'込'}" value="%{sourou}"/></td>
				<td width="70px" class="td_bg" align="center"><s:select name="f100103.shohinList[%{#status.index}].daibiki" list="#{'別':'別','込':'込'}" value="%{daibiki}"/></td>
				<td class="td_bg" align="center" valign="middle"><input type="button" onclick="document.form1.rowIndex.value=${status.index};actionSubmit('A10010305')" value="削除"></td>
			    </tr>
		        </s:iterator>
		</table>
	    <table class="table" width="97%" align="left" cellspacing="1" cellpadding="2">
            <tr>
                <td align="right" style="background-color: lightgrey">合計</td>
                <td class="td_bg" align="right" width="70px"><s:label name="f100103.gokeishouhin"/><s:hidden name="f100103.gokeishouhin"/>円</td>
                <td class="td_bg" align="right" width="70px"><s:label name="f100103.gokeizei"/><s:hidden name="f100103.gokeizei"/>円</td>
                <td class="td_bg" align="right" width="70px"><s:label name="f100103.gokeisouryou"/><s:hidden name="f100103.gokeisouryou"/>円</td>
                <td class="td_bg" align="right" width="70px"><s:label name="f100103.gokeidaibikiryou"/><s:hidden name="f100103.gokeidaibikiryou"/>円</td>
                <td class="td_bg" align="center" width="70px" rowspan="3" valign="middle"><input type="button" value="再計算" onclick="actionSubmit('A10010303')"></td>
            </tr>
            <tr>
                <td align="right" style="background-color: lightgrey">ポイント利用</td>
                <td class="td_bg" align="right" colspan="4"><s:textfield name="f100103.pointriyo" cssStyle="text-align:right" size="10"/>&nbsp;&nbsp;円</td>
            </tr>
            <tr>
                <td align="right" style="background-color: lightgrey">その他利用額</td>
                <td class="td_bg" align="right" colspan="4"><s:textfield name="f100103.sonotariyogaku" cssStyle="text-align:right" size="10"/>&nbsp;&nbsp;円</td>
            </tr>
            <tr>
                <td align="right" style="background-color: lightgrey">請求金額</td>
                <td class="td_bg" align="right" colspan="4"><s:label name="f100103.seikyukingaku"/><s:hidden name="f100103.seikyukingaku"/>円</td>
            </tr>
        </table>
		</div>
        <div id="div4">
		<table width="50%" align="center">
		    <tr>
		    <td align="left"><input type="button" value="戻る" onclick="javascript:history.go(-1)"></td>
		    <td align="right"><input type="button" value="确定" onclick="actionSubmit('A10010302')"></td>
		    </tr>
		</table>
		</div>
		<s:hidden name="viewId" value="V100101"/>
		<s:hidden name="f100103.resultcount"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
        <s:hidden name="scrollx"/>
        <s:hidden name="scrolly"/>
        <s:hidden name="orderNo"/>
	</s:form>
</body>
<script type="text/javascript">
window.onscroll = function(){
	
	document.getElementsByName("scrolly")[0].value = $(document).scrollTop();
	document.getElementsByName("scrollx")[0].value = $(document).scrollLeft();
}
</script>
</html>