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
var selectType = "0";
function openWin1() {
    var returnVal = openShowModalDialog("615", "600", "P01010101");
    if (returnVal) {
        $.post("getPopUpDate", null, function(result) {
            setDisplay(result);
        }, "json");
    }
}
function init(){
    for ( var i = 0; typeof (document
            .getElementsByName("f050101.commodityList[" + i + "].checkFlg")[0]) != "undefined"; i++) {
    	if(document
                .getElementsByName("f050101.commodityList[" + i + "].status")[0].value == "审核通过"){
    		document.getElementsByName("button1")[i].style.display ="none";
    		document.getElementsByName("button2")[i].style.display ="none";
    		document.getElementsByName("f050101.commodityList[" + i + "].checkFlg")[0].disabled =true;
    	}else if(document
                .getElementsByName("f050101.commodityList[" + i + "].status")[0].value == "审核未通过"){
    		document.getElementsByName("button2")[i].style.display ="none";
    	}
    	
    }
	if(document.getElementsByName("alertFlg")[0].value=="0"){
		alert("操作成功！");
		document.getElementsByName("alertFlg")[0].value="1";
		actionSubmit("A05010102");
	}
}
function setDisplay(result) {
    var object = JSON.parse(result);
    var cateName = object.cateName;
    var cateId = object.cateId;
    $("#categoryName").val(cateName);
    $("#categoryId").val(cateId);
}
	function checkAll() {
		if (selectType == "0") {
			for ( var i = 0; typeof (document
					.getElementsByName("f050101.commodityList[" + i
							+ "].checkFlg")[0]) != "undefined"; i++) {
				if (!document.getElementsByName("f050101.commodityList[" + i
                        + "].checkFlg")[0].disabled){
				document.getElementsByName("f050101.commodityList[" + i
						+ "].checkFlg")[0].checked = true;
				}
			}
			selectType = "1";
		} else {
			for ( var i = 0; typeof (document
					.getElementsByName("f050101.commodityList[" + i
							+ "].checkFlg")[0]) != "undefined"; i++) {
				if (!document.getElementsByName("f050101.commodityList[" + i
                        + "].checkFlg")[0].disabled){
				document.getElementsByName("f050101.commodityList[" + i
						+ "].checkFlg")[0].checked = false;
				}
			}
			selectType = "0";
		}
	}

	function check() {
		for ( var i = 0; typeof (document
				.getElementsByName("f050101.commodityList[" + i + "].checkFlg")[0]) != "undefined"; i++) {
			if (document.getElementsByName("f050101.commodityList[" + i
					+ "].checkFlg")[0].checked == true) {
				return true;
			}
		}
		alert("请至少选择一项");
		return false;
	}
</script>
<style type="text/css">
<!--

#div1 {
	width: 950px;
	position: relative;
	top: 20px;
	left:100px;
}

#div2 {
    left: 20px;
	top: 40px;
	width:950px;
	position: relative;
}
#div3 {
	height: 380px;
	width:970px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 20px;
}
-->
</style>
</head>
<body onload="init();">
    <s:form name="form1" theme="simple">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="80px">商品编号：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f050101.commodityId"/>
                </td>
                <td class="td_bg" width="80px">商品分类：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" id="categoryName" name="f050101.categoryName" cssClass="readonly" readOnly="true"/>&nbsp;&nbsp;<input type="button" onclick="openWin1()" value="参照"/>
                    <input type="hidden" id="categoryId" name="f050101.categoryId">
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">中文名：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f050101.chineseName"/>
                </td>
                <td class="td_bg" width="80px">日文名：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f050101.japaneseName"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td class="td_bg" colspan="3">
                    <s:radio name="f050101.type" list="#{ '1':'全部', '2':'未审核', '3':'审核通过','4':'审核未通过'}" />&nbsp;&nbsp;&nbsp;
                </td>
                <td align="right"><input type="button" onclick="actionSubmit('A05010102')" value="检索" style="width:50px;height:25px"/></td>
            </tr>
		</table>
		</div>
		<div id="div2">
		<table width="950px">
            <tr>
                <td>检索结果：<s:label name="f050101.resultCount" />件</td>
                <td align="right">
                    <input type="button" onclick="if(check()){document.form1.passFlg.value='0';actionSubmit('A05010104');}" value="批量通过" style="width:100px;height:20px"/>
                    <input type="button" onclick="if(check()){document.form1.passFlg.value='1';actionSubmit('A05010104');}" value="批量不通过" style="width:100px;height:20px"/>
                </td>
            </tr>
        </table>
        <table width="950px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="70px" align="center"><input type="button" onclick="checkAll()" value="全选/取消"></td>
			    <td width="80px" align="center">商品编号</td>
			    <td width="80px" align="center">状态</td>
			    <td width="100px" align="center">分类</td>
				<td width="200px" align="center">中文名</td>
				<td width="300px" align="center">链接</td>
				<td align="center">操作</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="950px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f050101.commodityList" status="status1">
			<tr class="bg_tr" height="60px">
			    <td width="70px" class="td_bg" align="center"><s:checkbox name="f050101.commodityList[%{#status1.index}].checkFlg"/></td>
			    <td width="80px" class="td_bg" align="center"><s:property value='commodityId'/><s:hidden name="f050101.commodityList[%{#status1.index}].commodityId" value="%{commodityId}"/></td>
				<td width="80px" class="td_bg" align="center">
				    <s:property value='status'/><s:hidden name="f050101.commodityList[%{#status1.index}].status" value="%{status}"/>
				</td>
				<td width="100px" class="td_bg" align="center"><s:property value='categoryName'/><s:hidden name="f050101.commodityList[%{#status1.index}].categoryName" value="%{categoryName}"/></td>
				<td width="200px" class="td_bg" align="left"><s:property value='chineseName'/><s:hidden name="f050101.commodityList[%{#status1.index}].chineseName" value="%{chineseName}"/></td>
				<td width="300px" class="td_bg" align="left"><a target="_blank" href="<s:property value='commodityUrl'/>"><s:property value='commodityUrl'/></a><s:hidden name="f050101.commodityList[%{#status1.index}].commodityUrl" value="%{commodityUrl}"/></td>
				<td class="td_bg" align="center">
				    <input type="button" name="button1" value="通过" onclick="document.form1.rowIndex.value=${status1.index};document.form1.passFlg.value='0';actionSubmit('A05010103');"/>&nbsp;&nbsp;&nbsp;
				    <input type="button" name="button2" value="不通过" onclick="document.form1.rowIndex.value=${status1.index};document.form1.passFlg.value='1';actionSubmit('A05010103');"/>
				</td>
			</tr>
		    </s:iterator>
		</table>
		</div>
		<s:hidden name="viewId" value="V050101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="passFlg"/>
		<s:hidden name="alertFlg"/>
	</s:form>
</body>
</html>