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
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
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
function openwin1() {
    var returnVal = openShowModalDialog("615", "600", "P01010101");
    if (returnVal) {
        $.post("getPopUpDate", null, function(result) {
            setDisplay(result);
        }, "json");
    }
}

	function setDisplay(result) {
		var object = JSON.parse(result);
		var cateId = object.cateId;

		actionSubmit('A06010503?cateId='+cateId);

	}
function changed(){
	document.getElementsByName("f060105.hozozumiFlg")[0].value = '0';
	document.getElementById("xiazai").disabled = true;
}
function caculate(){
	changed();
	var totalPcsVal = 0;
	var totalVal = 0.00;
	for ( var i = 0; typeof (document.getElementsByName("f060105.meisaiList[" + i + "].description")[0]) != "undefined"; i++) {
		var pcsVal = document.getElementsByName("f060105.meisaiList[" + i + "].pcs")[0].value;
		var untVal = document.getElementsByName("f060105.meisaiList[" + i + "].unitValue")[0].value;
        if( pcsVal != null && pcsVal != "" && untVal != null && untVal != ""){
        	totalPcsVal = totalPcsVal + parseInt(pcsVal);
        	totalVal = totalVal + untVal*pcsVal;
        	document.getElementsByName("f060105.meisaiList[" + i + "].totalValue")[0].value = (untVal*pcsVal).toFixed(2);
        	document.getElementsByName("f060105.meisaiList[" + i + "].unitValue")[0].value = (untVal*1).toFixed(2);
        }else{
        	document.getElementsByName("f060105.meisaiList[" + i + "].totalValue")[0].value = "";
        }
    }
	document.getElementById("totalPcsTd").innerHTML = totalPcsVal;
    document.getElementById("totalValueTd").innerHTML = ("US$"+(totalVal*1).toFixed(2));
    document.getElementsByName("f060105.totalPcs")[0].value = totalPcsVal;
    document.getElementsByName("f060105.totalValue")[0].value = ("US$"+(totalVal*1).toFixed(2));
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
                <input type="button" value="重新设置" onclick="actionSubmit('A06010504?waybillNo='+document.getElementsByName('f060105.wayBillNo')[0].value);">&nbsp;&nbsp;
		        <input type="button" value="保存" onclick="actionSubmit('A06010505')">&nbsp;&nbsp;
		        <s:if test="f060105.hozozumiFlg == 1">
		            <input type="button" id="xiazai" value="下载报关单" onclick="actionSubmit('A06010506')">
		        </s:if>
		        <s:else>
		            <input type="button" id="xiazai" value="下载报关单" disabled>
		        </s:else>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:16"/>
        <table cellspacing="1" cellpadding="2" width="400px" border="0" >
            <tr class="bg_tr">
                <td class="td_bg" width="200px">WAYBILL NO.:</td>
                <td class="td_bg"><h3><s:label name="f060105.wayBillNo"/><s:hidden name="f060105.wayBillNo"/></h3></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="200px">WEIGHT:
                <td class="td_bg"><s:textfield onchange="changed();" name="f060105.weight" size="5"/>&nbsp;KG</td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="200px">DIMENSIONS:
                <td class="td_bg"><s:textfield onchange="changed();" name="f060105.dimensions" size="5"/></td>        
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="200px">DATE OF EXPORTATION:</td>
                <td class="td_bg"><s:textfield onchange="changed();" name="f060105.dateOfExportation" cssClass="Wdate" onClick="WdatePicker()" size="15"/></td>
        </table>
        <table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="50%"><h2>RECEIVER  DETAILS:</h2></td>
                <td class="td_bg" width="50%"><h2>SHIPPER DETAILS:</h2></td>
            </tr>
        </table>
		<table cellspacing="1" cellpadding="2" class="table" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="50%">COMPANY NAME:<s:textfield onchange="changed();" name="f060105.rcompanyName" size="40"/></td>
                <td class="td_bg" width="50%">COMPANY NAME:<s:textfield onchange="changed();" name="f060105.scompanyName" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="50%">ADDRESS:<s:textfield onchange="changed();" name="f060105.raddress" size="40"/></td>
                <td class="td_bg" width="50%">ADDRESS:<s:textfield onchange="changed();" name="f060105.saddress" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="50%">CITY:<s:textfield onchange="changed();" name="f060105.rcity" size="10"/></td>
                <td class="td_bg" width="50%">CITY:<s:textfield onchange="changed();" name="f060105.scity" size="10"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="50%">PROVINCE:<s:textfield onchange="changed();" name="f060105.rprovince" size="10"/></td>
                <td class="td_bg" width="50%">PROVINCE:<s:textfield onchange="changed();" name="f060105.sprovince" size="10"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="50%">COUNTRY:<s:textfield onchange="changed();" name="f060105.rcountry" size="10"/></td>
                <td class="td_bg" width="50%">COUNTRY:<s:textfield onchange="changed();" name="f060105.scountry" size="10"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="50%">POSTCODE:<s:textfield onchange="changed();" name="f060105.rpostcode" size="10"/></td>
                <td class="td_bg" width="50%">POSTCODE:<s:textfield onchange="changed();" name="f060105.spostcode" size="10"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="50%">CONTACT NAME:<s:textfield onchange="changed();" name="f060105.rcontactName" size="30"/></td>
                <td class="td_bg" width="50%">CONTACT NAME:<s:textfield onchange="changed();" name="f060105.scontactName" size="30"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="50%">TELEPHONE:<s:textfield onchange="changed();" name="f060105.rtelephone" size="20"/></td>
                <td class="td_bg" width="50%">TELEPHONE:<s:textfield onchange="changed();" name="f060105.stelephone" size="20"/></td>
            </tr>
        </table>
        <table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" ><h2>SHIPMENT  DETAILS:</h2></td>
                <td class="td_bg" align="right">
                    <input type="button" value="加一行" onclick="actionSubmit('A06010502')">&nbsp;&nbsp;
		            <input type="button" value="添加分类" onclick="openwin1();">
		        </td>
            </tr>
        </table>
        </div>
        <div id="div2">
        <table width="90%" class="table" cellspacing="1" cellpadding="1" border="0">
			<tr class="bg_tr">
			    <td align="center">DESCRIPTION</td>
			    <td width="50px" align="center">PCS</td>
				<td width="50px" align="center">ORIGIN</td>
				<td width="100px" align="center">UNIT VALUE</td>
				<td width="100px" align="center">TOTAL VALUE</td>
			</tr>
		</table>
        </div>
        <div id="div3">
        <table width="90%" class="table" cellspacing="1" cellpadding="1" border="0" style="word-break:break-all">
		    <s:iterator value="f060105.meisaiList" status="status">
			<tr class="bg_tr">
			    <td class="td_bg" id="td1[${status.index}]" align="center"><s:textfield cssErrorStyle="background:red;" name="f060105.meisaiList[%{#status.index}].description" onchange="changed();" size="75"/></td>
			    <td width="50px" class="td_bg" id="td2[${status.index}]" align="center"><s:textfield cssErrorStyle="background:red;" name="f060105.meisaiList[%{#status.index}].pcs" size="3" onchange="caculate();"/></td>
			    <td width="50px" class="td_bg" id="td3[${status.index}]" align="center"><s:textfield cssErrorStyle="background:red;" name="f060105.meisaiList[%{#status.index}].origin" onchange="changed();" size="3"/></td>
			    <td width="100px" class="td_bg" id="td4[${status.index}]" align="right">US$<s:textfield cssErrorStyle="background:red;" name="f060105.meisaiList[%{#status.index}].unitValue" onchange="caculate();" size="5"/></td>
			    <td width="100px" class="td_bg" id="td4[${status.index}]" align="right">US$<s:textfield cssErrorStyle="background:red;" readonly="true" name="f060105.meisaiList[%{#status.index}].totalValue" size="5"/></td>
			</tr>
		    </s:iterator>
		    <tr class="bg_tr">
			    <td align="right"><b>TOTAL:</b></td>
			    <td width="50px" align="center" id="totalPcsTd"><s:label name="f060105.totalPcs"/></td>
			    <td width="50px" align="center"></td>
			    <td width="100px" align="right"><b>TOTAL:</b></td>
			    <td width="100px" align="right" id="totalValueTd">US$<s:label name="f060105.totalValue"/></td>
			</tr>
		</table>
		<br/><br/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" ><h3>SHIPPER'S SIGNTURE&STAMP:</h3></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" >————————————————————————</td>
            </tr>
        </table>
		</div>
        <s:hidden name="f060105.totalPcs"/>
        <s:hidden name="f060105.totalValue"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
        <s:hidden name="f060105.hozozumiFlg"/>
		<s:hidden name="scrollx"/>
        <s:hidden name="scrolly"/>
	</s:form>
</body>
<script type="text/javascript">
window.onscroll = function(){
	
	document.getElementsByName("scrolly")[0].value = $(document).scrollTop();
	document.getElementsByName("scrollx")[0].value = $(document).scrollLeft();
}
</script>
</html>