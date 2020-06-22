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
<script src="js/json2.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript">
var imgSrc;
$(document).ready(function(){
	init();
	if(form1.mode.value == "2"){
		form1.mode.value = "";
		openWin();
	}
	var shomode = document.getElementsByName("shoriMode")[0].value;
	if(shomode == "2"){
		document.getElementsByName("f030202.commodityId")[0].className += "readonly";
		document.getElementsByName("f030202.commodityId")[0].readOnly = true;
	}
	});

function getShohinStsInfo(bango){
	openShowModalDialogShuseika("1024", "700", "P01020101?shohinbango="+bango);
}

function openWin() {
    var returnVal = openShowModalDialog("715", "700", "A03020202");
    if (returnVal) {
    	actionSubmit('A03020201');
    }
}
function actionConfirm(){
	if(window.confirm("确认删除？")){
		actionSubmit('A03020201');
	}
}
function openWin1() {
    var returnVal = openShowModalDialog("615", "600", "P01010101?shohinbango="+document.getElementsByName("f030202.commodityId")[0].value);
    if (returnVal) {
        $.post("getPopUpDate", null, function(result) {
            setDisplay(result);
        }, "json");
    }
}

	function setDisplay(result) {
		var object = JSON.parse(result);
		var cateName = object.cateName;
		var cateId = object.cateId;
		$("#categoryName").val(cateName);
		$("#categoryId").val(cateId);
	}


	

	function check() {
		if (document.getElementsByName("f030202.commodityId")[0].value == "") {
			alert("请输入商品编号");
			return false;
		}
		if (document.getElementsByName("f030202.categoryName")[0].value == "") {
			alert("请输入商品分类");
			return false;
		}
		if (document.getElementsByName("f030202.chineseName")[0].value == "") {
			alert("请输入中文名");
			return false;
		}

		return true;
	}

	function init() {

	}
	
	function updateDelFlg(commId11,checkedFlg){
		$.post("A03020204", {data1:commId11,data2:checkedFlg}, function(result) {
			if("1" == result){
			    alert("指定成功");
			}else{
				alert("指定失败");
			}
	    }, "json");
	}
	function setSource(){
		
		var commId11 = document.getElementsByName("f030202.commodityId")[0].value;
		var size11 = document.getElementsByName("f030202.source")[0].value;
		$.post("A03020205", {data1:commId11,data2:size11}, function(result) {
			if("1" == result){
			    alert("成功");
			}else{
				alert("失败");
			}
	    }, "json");
	}
</script>
<style type="text/css">
<!--

#div1 {
	width: 700px;
	position: relative;
	top: 20px;
	left:20px;
}

#div2 {
    left: 20px;
	top: 40px;
	width:1100px;
	position: relative;
}
#div3 {
	height: 190px;
	width:1120px;
	overflow-y:scroll;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 20px;
}
#div4 {
    width:1100px;
    overflow-y:auto;
    overflow-x:hidden;
    position: absolute;
    bottom:10px;
    left: 20px;
}
-->
</style>
</head>
<body onload="init()">
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div id="localImag" style="position: absolute;left:700px;top:80px;width:200px;height:200px;"><img style="border:1px solid" width="190px" height="190px" name="f030202.picUrl" src="${f030202.picUrl}"/><s:hidden name="f030202.picUrl"/></div>
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
        <b>基本信息</b>
        <hr width="500px" align="left">
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg">商品编号：<span style="font-color:red">(*)</span></td>
                <td class="td_bg">
                    <s:textfield size="10" name="f030202.commodityId" cssErrorStyle="background:red;" maxlength="12"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">商品分类：<span style="font-color:red">(*)</span>：</td>
                <td class="td_bg">
                    <s:textfield size="20" id="categoryName" name="f030202.categoryName" cssErrorStyle="background:red;" readOnly="true" cssClass="readonly" maxlength="20"/>
                    <s:hidden id="categoryId" name="f030202.categoryId" />
                    <input type="button" onclick="openWin1()" value="修改">
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">中文名：<span style="font-color:red">(*)</span>：</td>
                 <td class="td_bg">
                    <s:textfield size="15" cssErrorStyle="background:red;" name="f030202.chineseName" maxlength="130"/>&nbsp;&nbsp;&nbsp;&nbsp;日文名：
                    <s:textfield size="15" name="f030202.japaneseName" maxlength="130"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">商品链接：</td>
                <td class="td_bg">
                    <s:textfield size="35" name="f030202.commodityUrl" maxlength="200"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">进价：</td>
                <td class="td_bg">
                    <s:textfield size="5" name="f030202.priceIn" maxlength="50"/>&nbsp;&nbsp;&nbsp;&nbsp;售价：
                    <s:textfield size="5" name="f030202.priceOut" maxlength="50"/>
                </td>
            </tr>
<!--             <tr class="bg_tr"> -->
<!--                 <td class="td_bg">：</td> -->
<!--                 <td class="td_bg"> -->
<%--                     <s:file size="35" disabled="true" name="f030202.uploadFile" id="doc" onchange="form1.mode.value='99';javascript:setImagePreview();actionSubmit('A03020201');"/> --%>
<!--                 </td> -->
<!--             </tr> -->
            <tr class="bg_tr">
                <td class="td_bg">尺寸：</td>
                <td class="td_bg">
                    <s:textfield size="5" name="f030202.source"/>
                    <input type="button" value="修改" onclick="setSource();">
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">备注：</td>
                <td class="td_bg">
                    <s:textarea cssStyle="height:40px;width:250px" name="f030202.remarks" />
                </td>
            </tr>
            
         
		</table>
		</div>
		<div id="div2">
        <b>明细信息</b>
        <hr width="500px" align="left">
        <table>
            <tr><td colspan="9"><input type="button" value="添加明细" onclick="form1.mode.value='1';openWin()" /></td></tr>
        </table>
        <table width="1100px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
            <tr class="bg_tr">
                <td width="80px" align="center">明细编号</td>
                <td width="160px" align="center">商品描述</td>
                <td width="140px" align="center">图片</td>
                <td width="100px" align="center" style="display:none">条形码</td>
                <td width="320px" align="center">库存信息</td>
        
                <td align="center">操作</td>
            </tr>
        </table>
        </div>
        <div id="div3">
        <table width="1100px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
            <s:iterator value="f030202.commodityDetailList" status="status">
            <tr class="bg_tr">
                <td width="80px" class="td_bg" align="center"><s:property value='detailNo'/><s:hidden name="f030202.commodityDetailList[%{#status.index}].detailNo" value="%{detailNo}"/></td>
                <td width="160px" class="td_bg" align="left"><s:property value='describe'/><s:hidden name="f030202.commodityDetailList[%{#status.index}].describe" value="%{describe}"/></td>
                <td width="140px" class="td_bg" align="center"><img src="${f030202.picUrl}" width="80px" height="80px"><s:hidden name="f030202.commodityDetailList[%{#status.index}].picUrl" value="%{picUrl}"/></td>
                <td width="100px" class="td_bg" align="center" style="display:none"><s:property value='barCode'/><s:hidden name="f030202.commodityDetailList[%{#status.index}].barCode" value="%{barCode}"/></td>
                <td width="320px" class="td_bg" align="left">
                    上海库存：<s:property value='stockSh'/><s:hidden name="f030202.commodityDetailList[%{#status.index}].stockSh" value="%{stockSh}"/>&nbsp;&nbsp;
                    日本库存：<s:property value='stockJp'/><s:hidden name="f030202.commodityDetailList[%{#status.index}].stockJp" value="%{stockJp}"/>&nbsp;&nbsp;
                   运输途中：<s:property value='stockHandup'/><s:hidden name="f030202.commodityDetailList[%{#status.index}].stockHandup" value="%{stockHandup}"/>&nbsp;&nbsp;
                   进货途中：<s:property value='nyuka'/><s:hidden name="f030202.commodityDetailList[%{#status.index}].nyuka" value="%{nyuka}"/></td>
                
                <td class="td_bg" align="center">
<%--                     <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.mode.value='2';actionSubmit('A03020201')" disabled value="修改"/>&nbsp;&nbsp;&nbsp; --%>
<%--                     <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.mode.value='3';actionConfirm()" disabled value="删除"/>&nbsp;&nbsp;&nbsp; --%>
                    <input type="button" onclick="getShohinStsInfo('<s:property value='f030202.commodityId'/><s:property value='detailNo'/>')" value="详细"/><br/>
                    <s:checkbox name="f030202.commodityDetailList[%{#status.index}].delFlg" onclick="updateDelFlg('%{f030202.commodityId}%{detailNo}',this.checked)"/>入荷不可
                </td>
            </tr>
            </s:iterator>
        </table>
        </div>
        <div id="div4">
        <table>
            <tr>
                <td class="td_bg" align="left"><input type="button" value="返回" onclick="actionSubmit('A03020101')">&nbsp;&nbsp;&nbsp;</td>
                <td class="td_bg" align="right"><input type="button" value="确定" onclick="if(check()){actionSubmit('A03020203')}">
            </tr>
        </table>
        </div>
		<s:hidden name="viewId" value="V030102"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="f030202.picUrl" />
	</s:form>
</body>
</html>