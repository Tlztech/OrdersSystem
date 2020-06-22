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
	setDisplay();
    hideDiv();
}
function doAction(actionId){
	popupDiv();
    var logKey = "";
    logKey = _getRandomString(10);
    actionSubmit(actionId+'?logKey='+logKey);
    setInterval(function(){
        getLog(logKey);
    },2000);
}
function popupDiv() {   
    var div_obj = $("#pop-div");    
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}   

function hideDiv() {   
  $("#mask").hide();   
  $("#pop-div").hide();   
} 

function updateSts(sts) {
	var index = document.form1.rowIndex.value;
	var bango = document.getElementsByName("f090101.meisaiList[" + index
			+ "].kanribango")[0].value;
	$.post("A09010103", {
		kanribango : bango,
		status : sts,
	}, function(result) {
		if (result != "true") {
			alert("操作失败");
		}else{
			document.getElementsByName("f090101.meisaiList[" + index + "].kakuninSts")[0].value = sts;
			setDisplay();
		}
	}, "json");
}
function updateMemo(memoValue) {

	var index = document.form1.rowIndex.value;
	var bango = document.getElementsByName("f090101.meisaiList[" + index
			+ "].kanribango")[0].value;
	var motoMemo = document.getElementsByName("f090101.meisaiList[" + index
			+ "].memo")[0].value;
	if(motoMemo!=memoValue){	
	$.post("A09010104", {
		kanribango : bango,
		memo : memoValue,
	}, function(result) {
		if (result != "true") {
			alert("メモアップデート失敗！");
		}else{
			document.getElementsByName("f090101.meisaiList[" + index
					+ "].memo")[0].value = memoValue;
		}
	}, "json");
	}
}


function setDisplay(){
	var kakuninsts = null;
    for ( var i = 0; typeof (document
            .getElementsByName("f090101.meisaiList[" + i
                    + "].kanribango")[0]) != "undefined"; i++) {
    	
    	kakuninsts = document.getElementsByName("f090101.meisaiList[" + i + "].kakuninSts")[0].value;
    	
    	if(kakuninsts == "0"){
            document.getElementById("button1["+i+"]").style.display = '';
            document.getElementById("button2["+i+"]").style.display = 'none';
            document.getElementById("td8["+i+"]").innerHTML = document.getElementById("td8["+i+"]").innerHTML.replace("確認済み","");
            setTdColor(i,"");
    	}else if(kakuninsts == "1"){
    		document.getElementById("button1["+i+"]").style.display = 'none';
            document.getElementById("button2["+i+"]").style.display = '';
            document.getElementById("td8["+i+"]").innerHTML = document.getElementById("td8["+i+"]").innerHTML.replace("確認済み","");
            document.getElementById("td8["+i+"]").innerHTML = "確認済み" + document.getElementById("td8["+i+"]").innerHTML;
            setTdColor(i,"#A9A9A9");
    	}
    }
    	
}
function setTdColor(i,color){
	document.getElementById("td1["+i+"]").style.backgroundColor=color;
	document.getElementById("td2["+i+"]").style.backgroundColor=color;
	document.getElementById("td3["+i+"]").style.backgroundColor=color;
	document.getElementById("td4["+i+"]").style.backgroundColor=color;
	document.getElementById("td5["+i+"]").style.backgroundColor=color;
	document.getElementById("td6["+i+"]").style.backgroundColor=color;
	document.getElementById("td7["+i+"]").style.backgroundColor=color;
	document.getElementById("td8["+i+"]").style.backgroundColor=color;
}
</script>
<style type="text/css">
<!--

#div1 {
	width: 900px;
	position: relative;
	top: 20px;
	left:100px;
}

#div2 {
    left: 20px;
	top: 40px;
	width:1100px;
	position: relative;
}
#div3 {
	height: 400px;
	width:1120px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 20px;
}
#div4 {
	height: 40px;
	width:1120px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 40px;
	left: 20px;
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
<div id='mask' class="mask" style="width:100%;height:100%;"></div>
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
    
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="100px">取引年月日：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f090101.torihikinengappiStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f090101.torihikinengappiEnd"/>
                </td>
                <td class="td_bg" width="100px">取引区分：</td>
                <td class="td_bg">
                    <s:select list="#{'':' ', '入金':'入金', '出金':'出金'}" name="f090101.torihikikubun"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="100px">摘要：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f090101.tekiyou"/>
                </td>
                <td class="td_bg" width="100px">確認ステータス：</td>
                <td class="td_bg">
                    <s:select list="#{'':' ', '1':'確認済み', '0':'未確認'}" name="f090101.kakuninsts"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="100px">お支払金額：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f090101.oshiharaikingakuStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f090101.oshiharaikingakuEnd"/>
                </td>
                <td class="td_bg" width="100px">お預り金額：</td>
                <td class="td_bg">
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f090101.otukarikingakuStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f090101.otukarikingakuEnd"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="100px">メモ：</td>
                <td class="td_bg"  colspan="3">
                    <s:textfield size="50" maxlength="100" name="f090101.memo "/>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="right"><input type="button" onclick="actionSubmit('A09010102')" value="检索" style="width:50px;height:25px"/></td>
            </tr>
		</table>
		</div>
		<div id="div2">
		<table width="1100px">
            <tr>
                <td>検索結果：<s:label name="f090101.resultCount" />件<s:hidden name="f090101.resultCount" /></td>
                <td align="right"><input type="button" onclick="doAction('A09010105');" value="最新情報を取得する"/></td>
            </tr>
        </table>
        <table width="1100px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="120px" align="center">取引年月日</td>
			    <td width="120px" align="center">お支払金額</td>
				<td width="120px" align="center">お預り金額</td>
				<td width="80px" align="center">取引区分</td>
				<td width="120px" align="center">差引残高</td>
				<td width="180px" align="center">摘要</td>
				<td width="220px" align="center">メモ</td>
				<td align="center">ステータス</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="1100px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f090101.meisaiList" status="status">
			<tr class="bg_tr" height="40px">
			    <td width="120px" class="td_bg" id="td1[${status.index}]" align="center"><s:property value='torihikinengappi'/><s:hidden name="f090101.meisaiList[%{#status.index}].torihikinengappi" value="%{torihikinengappi}"/></td>
			    <td width="120px" class="td_bg" id="td2[${status.index}]" align="right"><s:property value='oshiharaikingaku'/><s:hidden name="f090101.meisaiList[%{#status.index}].oshiharaikingaku" value="%{oshiharaikingaku}"/></td>
			    <td width="120px" class="td_bg" id="td3[${status.index}]" align="right"><s:property value='otukarikingaku'/><s:hidden name="f090101.meisaiList[%{#status.index}].otukarikingaku" value="%{otukarikingaku}"/></td>
			    <td width="80px" class="td_bg" id="td4[${status.index}]" align="center"><s:property value='torihikikubun'/><s:hidden name="f090101.meisaiList[%{#status.index}].torihikikubun" value="%{torihikikubun}"/></td>
			    <td width="120px" class="td_bg" id="td5[${status.index}]" align="right"><s:property value='sashihikisanko'/><s:hidden name="f090101.meisaiList[%{#status.index}].sashihikisanko" value="%{sashihikisanko}"/></td>
			    <td width="180px" class="td_bg" id="td6[${status.index}]" align="center"><s:property value='tekiyou'/><s:hidden name="f090101.meisaiList[%{#status.index}].tekiyou" value="%{tekiyou}"/></td>
			    <td width="220px" class="td_bg" id="td7[${status.index}]" align="center"><s:hidden name="f090101.meisaiList[%{#status.index}].memo"/><s:textarea style="height:30px" name="f090101.meisaiList[%{#status.index}].memo" onclick="document.form1.rowIndex.value=%{#status.index};" onblur="updateMemo(this.value)"/> </td>
				<td class="td_bg" align="center" id="td8[${status.index}]">
				    <s:hidden name="f090101.meisaiList[%{#status.index}].kanribango" value="%{kanribango}"/>
				    <s:hidden name="f090101.meisaiList[%{#status.index}].kakuninSts" value="%{kakuninSts}"/>
				    <input type="button" id="button1[${status.index}]" onclick="document.form1.rowIndex.value=${status.index};updateSts('1')" value="確認"/>
				    <input type="button" id="button2[${status.index}]" onclick="document.form1.rowIndex.value=${status.index};updateSts('0')" value="取消"/>
				</td>
			</tr>
		    </s:iterator>
		</table>
		</div>
		<div id="div4">
		<table width="1120px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
		    <tr>
		        <td>
		            <a href="#" id="firstPage" onclick="actionSubmit('A03020106?page=first')">首页</a>&nbsp;<a href="#" id="lastPage" onclick="actionSubmit('A03020106?page=last')">上一页</a>&nbsp;
		            <a href="#" id="nextPage" onclick="actionSubmit('A03020106?page=next')">下一页</a>&nbsp;<a href="#" id="endPage" onclick="actionSubmit('A03020106?page=end')">尾页</a>
		            &nbsp;&nbsp;&nbsp;&nbsp;第<s:label name="nowPage"/><s:hidden id="nowPage" name="nowPage"/>页&nbsp;/&nbsp;共<s:label name="allPage"/><s:hidden id="allPage" name="allPage"/>页
		            &nbsp;&nbsp;&nbsp;&nbsp;跳转到<s:textfield cssErrorStyle="background:red;" name="goPage" size="1"/>页 <input onclick="actionSubmit('A03020106?page=go')" type="button" value="GO">
		        </td>
		    </tr>
		</table>
		</div>
		<s:hidden name="viewId" value="V030201"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		<div id='pop-div' style="left:350px;top:100px;width:450px;height:260px;position:absolute;" class="pop-box">   
            <div class="pop-box-body" >
            <table>
                <tr>
                    <td colspan="2" align="center"><textarea id="logArea" style="width:400px;height:200px" readOnly></textarea></td>
                </tr>
            </table>
            </div>
        </div>
    
	</s:form>
</body>
</html>