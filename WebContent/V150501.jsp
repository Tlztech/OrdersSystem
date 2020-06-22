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
	if(document.getElementsByName("mode")[0].value == "shinki"){
	    getDayStart();
	}
}

function changed(){
	document.getElementsByName("f150501.hozozumiFlg")[0].value = '0';
	document.getElementById("xiazai").disabled = true;
}
function getDayStart(){
	var hiseikyu = document.getElementsByName("f150501.hiseikyusha")[0].value;
	$.post("A15050102", {
		hiseikyusha : hiseikyu,
	}, function(result) {
		document.getElementsByName("f150501.startDay")[0].value = result;
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
	width: 97%;
	position: relative;
	height:10%;
	margin-left:auto;
	margin-right:auto;
}

#div2 {
	width:97%;
	overflow-x:hidden;
	position: relative;
	margin-left:auto;
	margin-right:auto;
}
#div3 {
	width:97%;
	position: relative;
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
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:90%;margin-top: 5px;margin-left: 10px">
        <h2> <s:label name="title"/></h2>
                <input type="button" value="重新设置" onclick="actionSubmit('A15050101?mode='+document.getElementsByName('mode')[0].value)">&nbsp;&nbsp;
		        <input type="button" value="保存" onclick="actionSubmit('A15050104')">&nbsp;&nbsp;
		        <s:if test="f150501.hozozumiFlg == 1">
		            <input type="button" id="xiazai" value="下载请求书" onclick="actionSubmit('A15050105')">
		        </s:if>
		        <s:else>
		            <input type="button" id="xiazai" value="下载请求书" onclick="actionSubmit('A15050105')" disabled>
		        </s:else>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:16"/>
        <table cellspacing="1" cellpadding="2" width="700px" border="0" >
            <tr class="bg_tr">
                <td class="td_bg" width="100px">请求开始日:</td>
                <td class="td_bg" width="150px"><s:textfield onchange="changed()" cssClass="readonly" readonly="true" size="15" maxlength="8" name="f150501.startDay"/></td>
                <td class="td_bg" width="100px">请求截止日:
                <td class="td_bg"><s:textfield onchange="changed()" cssClass="Wdate" onClick="WdatePicker()" size="15" maxlength="8" name="f150501.endDay"/>
                 &nbsp;&nbsp;价格基数：<s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.jishu" size="5"/>
                &nbsp;&nbsp;<input type="button" id="xiazai" value="计算" onclick="actionSubmit('A15050103')"></td>
            </tr>
        </table>
        <table cellspacing="1" cellpadding="2" width="90%" border="0" >
            <tr>
                <td align="center"><h2>見　積　書</h2></td>
            </tr>
        </table>
        <table width="90%" cellspacing="1" cellpadding="1" border="0">
        <tr>
        <td align="left" width="60%">
        <table  cellspacing="1" cellpadding="1" border="0">
            <tr>
                <td align="left">
                <s:if test="mode == 'shinki'"><s:select list="#{ 'トレンド最前線':'トレンド最前線','勝意有限会社':'勝意有限会社'}" onchange="getDayStart();changed();" name="f150501.hiseikyusha"/></s:if>
                <s:else><s:label name="f150501.hiseikyusha"/><s:hidden name="f150501.hiseikyusha"/></s:else>
                御中</td>
            </tr>
            <tr height="100px">
                <td>下記の通りお見積いたしますのでご査収下さい。</td>
            </tr>
            <tr>
                <td><h3>お見積金額&nbsp;&nbsp;&nbsp;¥<s:label name="f150501.seikyukingaku"/></h3></td>
            </tr>
        </table>
        </td>
        <td align="right"  width="40%">
        <table cellspacing="1" cellpadding="1" border="0">
            <tr>
                <td align="right"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.seikyusha1" size="25" /></td>
            </tr>
            <tr>
                <td align="right">〒<s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.seikyusha2" size="25" /></td>
            </tr>
            <tr>
                <td align="right"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.seikyusha3" size="25"/></td>
            </tr>
            <tr>
                <td align="right">TEL:<s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.seikyusha4" size="25" /></td>
            </tr>
            <tr>
                <td align="right">FAX:<s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.seikyusha5" size="25" /></td>
            </tr>
            <tr>
                <td align="right">担当:<s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.seikyusha6" size="25"/></td>
            </tr>
        </table>
        </td>
        </tr>
        </table>
        </div>
        <div id="div2">
        <table width="90%" class="table" cellspacing="1" cellpadding="1" border="0">
			<tr class="bg_tr">
			    <td width="200px" align="center">注文番号</td>
			    <td width="150px" align="center">費目(商品番号)</td>
				<td width="80px" align="center">単価</td>
				<td width="80px" align="center">数量</td>
				<td width="120px" align="center">金額</td>
				<td align="center">補足</td>
			</tr>
		</table>
        </div>
        <div id="div3">
        <table width="90%" class="table" cellspacing="1" cellpadding="1" border="0" style="word-break:break-all">
            <tr class="bg_tr">
			    <td width="200px" class="td_bg" align="center"></td>
			    <td width="150px" class="td_bg" align="center">メール便代理発送料金</td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.merubintanka" size="5"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.merubinkosu" size="5" cssClass="readonly" readonly="true"/></td>
			    <td width="120px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.merubinkingaku" cssClass="readonly" readonly="true" size="7"/></td>
			    <td class="td_bg" align="center"></td>
			</tr>
			<tr class="bg_tr">
			    <td width="200px" class="td_bg" align="center"></td>
			    <td width="150px" class="td_bg" align="center">宅急便代理発送料金</td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.takyubintanka" size="5"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.takyubinkosu" size="5" cssClass="readonly" readonly="true"/></td>
			    <td width="120px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.takyubinkingaku" cssClass="readonly" readonly="true" size="7"/></td>
			    <td class="td_bg" align="center"></td>
			</tr>
			<tr class="bg_tr">
			    <td width="200px" class="td_bg" align="center"></td>
			    <td width="150px" class="td_bg" align="center"><s:checkbox onchange="changed()" name="f150501.tesuryoChk"/>促進販売手数料</td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.tesuryotanka" size="5"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.tesuryokosu" cssClass="readonly" readonly="true" size="5" value="1"/></td>
			    <td width="120px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.tesuryokingaku" cssClass="readonly" readonly="true" size="7"/></td>
			    <td class="td_bg" align="center"></td>
			</tr>
			<tr class="bg_tr">
			    <td width="200px" class="td_bg" align="center"></td>
			    <td width="150px" class="td_bg" align="center"><s:checkbox onchange="changed()" name="f150501.daibikiChk"/>代金引換預かり金</td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" cssClass="readonly" readonly="true"  name="f150501.daibikitanka" size="5"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.daibikikosu" cssClass="readonly" readonly="true" value="1" size="5"/></td>
			    <td width="120px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.daibikikingaku" cssClass="readonly" readonly="true" size="7"/></td>
			    <td class="td_bg" align="center"></td>
			</tr>
            <tr class="bg_tr">
			    <td width="200px" class="td_bg" align="center"></td>
			    <td width="150px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.himoku1" size="15"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.tanka1" size="5"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.kosu1" size="5"/></td>
			    <td width="120px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.kingaku1" cssClass="readonly" readonly="true" size="7"/></td>
			    <td class="td_bg" align="center"></td>
			</tr>
			<tr class="bg_tr">
			    <td width="200px" class="td_bg" align="center"></td>
			    <td width="150px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.himoku2" size="15"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.tanka2" size="5"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.kosu2" size="5"/></td>
			    <td width="120px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.kingaku2" cssClass="readonly" readonly="true" size="7"/></td>
			    <td class="td_bg" align="center"></td>
			</tr>
			<tr class="bg_tr">
			    <td width="200px" class="td_bg" align="center"></td>
			    <td width="150px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.himoku3" size="15"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.tanka3" size="5"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.kosu3" size="5"/></td>
			    <td width="120px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.kingaku3" cssClass="readonly" readonly="true" size="7"/></td>
			    <td class="td_bg" align="center"></td>
			</tr>
			<tr class="bg_tr">
			    <td width="200px" class="td_bg" align="center"></td>
			    <td width="150px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.himoku4" size="15"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.tanka4" size="5"/></td>
			    <td width="80px" class="td_bg" align="center"><s:textfield onchange="changed()" name="f150501.kosu4" size="5"/></td>
			    <td width="120px" class="td_bg" align="center"><s:textfield onchange="changed()" cssStyle="text-align:right" name="f150501.kingaku4" cssClass="readonly" readonly="true" size="7"/></td>
			    <td class="td_bg" align="center"></td>
			</tr>
		    <s:iterator value="f150501.meisaiList" status="status">
			<tr class="bg_tr">
			    <td width="200px" class="td_bg" id="td1[${status.index}]" align="center"><s:property value='juchubango'/><s:hidden name="f150501.meisaiList[%{#status1.index}].juchubango" value="%{juchubango}"/></td>
			    <td width="150px" class="td_bg" id="td2[${status.index}]" align="center"><s:property value='shohinbango'/><s:hidden name="f150501.meisaiList[%{#status1.index}].shohinbango" value="%{shohinbango}"/></td>
			    <td width="80px" class="td_bg" id="td3[${status.index}]" align="right"><s:property value='tanka'/><s:hidden name="f150501.meisaiList[%{#status1.index}].tanka" value="%{tanka}"/></td>
			    <td width="80px" class="td_bg" id="td4[${status.index}]" align="right"><s:property value='kosu'/><s:hidden name="f150501.meisaiList[%{#status1.index}].kosu" value="%{kosu}"/></td>
			    <td width="120px" class="td_bg" id="td5[${status.index}]" align="right"><s:property value='kingaku'/><s:hidden name="f150501.meisaiList[%{#status1.index}].kingaku" value="%{kingaku}"/></td>
			    <td class="td_bg" id="td5[${status.index}]" align="center"></td>
			</tr>
		    </s:iterator>
		    <tr class="bg_tr">
			    <td align="right" colspan="4">小計</td>
			    <td align="right"><s:label name="f150501.kokei"/></td>
			    <td>&nbsp;</td>
			</tr>
			<tr class="bg_tr">
			    <td align="right" colspan="4">調整金額</td>
			    <td align="right">0</td>
			    <td></td>
			</tr>
			<tr class="bg_tr">
			    <td align="right" colspan="4">消費税</td>
			    <td align="right">0</td>
			    <td></td>
			</tr>
			<tr class="bg_tr">
			    <td align="right" colspan="4">合計金額</td>
			    <td align="right"><s:label name="f150501.gokei"/></td>
			    <td></td>
			</tr>
		</table>
		</div>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
        <s:hidden name="f150501.hozozumiFlg"/>
		<s:hidden name="scrollx"/>
        <s:hidden name="scrolly"/>
        <s:hidden name="f150501.seikyukingaku"/>
        <s:hidden name="f150501.createDay"/>
	</s:form>
</body>
<script type="text/javascript">
window.onscroll = function(){
	
	document.getElementsByName("scrolly")[0].value = $(document).scrollTop();
	document.getElementsByName("scrollx")[0].value = $(document).scrollLeft();
}
</script>
</html>