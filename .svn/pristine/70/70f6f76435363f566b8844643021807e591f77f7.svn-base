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
<script src="js/jquery.js"></script>
<script type="text/javascript">
function actionConfirm(){
    if(window.confirm("确认删除？")){
        actionSubmit('A08010103');
    }
}

function doAction(){
	var logKey = "";
	document.getElementById("button11").disabled=true;
	document.getElementById("button22").disabled=true;
	logKey = _getRandomString(10);
	actionSubmit('A08010102?logKey='+logKey);
	setInterval(function(){
	    getLog(logKey);
	},2000);
}

function init() {
	hideDiv2();
	if(document.getElementsByName("f080101.passFlg")[0].value == "1"){
		document.getElementsByName("f080101.passFlg")[0].value = "0";
		alert("删除成功！");
		document.getElementById("kensaku").click();
	}
}
  
function popupDiv2() {   
    var div_obj = $("#pop-div2");    
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}  
  

function hideDiv2() {   
	  $("#mask").hide();   
	  $("#pop-div2").hide();  
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
		<table cellspacing="1" cellpadding="2" width="700px" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="40px">種目：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f080101.shumoku"/>
                </td>
                <td class="td_bg" colspan="4" align="left"><input type="button" id="kensaku" onclick="actionSubmit('A08010104')" value="检索" style="width:50px;height:25px"/></td>
            </tr>
		</table>
		</div>
		<div id="div2">
		<table width="700px">
            <tr>
                <td>検索結果：<s:label name="f080101.resultCount" />件<s:hidden name="f080101.resultCount" /></td>
                <td align="right"><input type="button" onclick="popupDiv2();" value="新規"/>&nbsp;&nbsp;&nbsp;
                <input type="button" onclick="popupDiv2();" value="サイトから新規"/></td>
            </tr>
        </table>
        <table width="700px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="120px" align="center">種目番号</td>
			    <td width="200px" align="center">種目名称</td>
			    <td width="150px" align="center">最終更新日</td>
				<td align="center">&nbsp;</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="700px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f080101.shumokuList" status="status">
			<tr class="bg_tr">
			    <td width="120px" class="td_bg" align="center"><s:property value='shumokubango'/><s:hidden name="f080101.shumokuList[%{#status.index}].shumokubango" value="%{shumokubango}"/></td>
			    <td width="200px" class="td_bg" align="center"><s:property value='shumokuMeisho'/><s:hidden name="f080101.shumokuList[%{#status.index}].shumokuMeisho" value="%{shumokuMeisho}"/></td>
			    <td width="150px" class="td_bg" align="center"><s:property value='koshinbi'/><s:hidden name="f080101.shumokuList[%{#status.index}].koshinbi" value="%{koshinbi}"/></td>
				<td class="td_bg" align="center">
				    <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.shoriMode.value='2';actionSubmit('A08010201')" value="照会"/>&nbsp;&nbsp;&nbsp;
				    <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.shoriMode.value='2';actionSubmit('A03020201')" value="更新"/>&nbsp;&nbsp;&nbsp;
				    <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.shoriMode.value='2';if(confirm('确认删除？删除后数据无法恢复')){actionSubmit('A08010103');}else{return flase;}" value="削除"/>&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		    </s:iterator>
		</table>
		</div>>
		<s:hidden name="viewId" value="V080101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="f080101.passFlg"/>
        
        <div id='pop-div2' style="left:350px;top:70px;width:500px;height:470px;position:absolute;" class="pop-box">   
            <div class="pop-box-body" >
            <table>
                <tr height="50px">
                    <td width="80px">種目名称：&nbsp;&nbsp;</td><td><s:textfield size="50" name="f080101.shumokumeishou"/></td>
                </tr>
                <tr height="50px">
                    <td width="80px">リンク：&nbsp;&nbsp;</td><td><s:textfield size="50" name="f080101.rinku"/></td>
                </tr>
                <tr height="50px">
                    <td width="80px">サイト：&nbsp;&nbsp;</td><td><s:select list="#{ '01':'netvivi'}" name="f080101.ptype"/></td>
                </tr>
                <tr height="50px">
                    <td width="80px">ログ：&nbsp;&nbsp;</td><td>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><textarea id="logArea" style="width:400px;height:200px" readOnly></textarea></td>
                </tr>
            </table>
            <table align="center">
                <tr height="50px">
                    <td><input type="button" id="button11" onclick="doAction()" value="確定"></td>
                    <td><input type="button" id="button22" onclick="hideDiv2();" value="戻る"></td>
                </tr>
            </table>
            </div>
        </div>
	</s:form>
	<script type="text/javascript">
	</script>
</body>
</html>