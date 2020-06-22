<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>P010101:商品分类检索</title>
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/commonjs.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var val = $("input[name$=cateId]").val();
		if(val == null){
			$("#confirm").attr("disabled",true);
		}else{
			$("#confirm").attr("disabled",false);
			$("input[name^=index]:eq(0)").attr("checked",true);
			$("input[name=rowIndex]").val("0");
		}
	})
</script>
<style type="text/css">
<!--
#div1 {
	width: 500px;
	position: relative;
	top: 20px;
	left:30px;
}

#div2 {
    left: 20px;
	top: 40px;
	width:580px;
	position: relative;
}
#div3 {
	height: 365px;
	width:580px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 20px;
}
#div4 {
	width:580px;
	overflow:none;
	position: absolute;
	top: 550px;
}
-->
</style>
</head>
<body>
    <s:form name="form1" theme="simple">
        <div style="width:500px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg">分类名称：
                    <s:textfield size="35" maxlength="20" name="p010101.cateName"/>
                    <input type="button" value="检索" onclick="document.form1.searchMode.value='0';actionSubmit('P01010102')"/>
                </td>
            </tr>
		</table>
		</div>
		<div id="div2">
		<table width="540px">
            <tr>
                <td>检索结果：<s:label name="p010101.resultCount" />件</td>
            </tr>
        </table>
        <table width="560px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="20px" align="center"></td>
				<td width="140px" align="center">分类名称</td>
				<td width="140px" align="center">父类名称</td>
				<td width="70px" align="center">子类个数</td>
				<td width="190px" align="center">操作</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="560px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
		    <s:iterator value="p010101.categoryList" status="status">
			<tr class="bg_tr">
			    <td width="20px" align="center"><input type="radio" name="index" onclick="document.form1.rowIndex.value=${status.index}"></td>
				<td width="140px" class="td_bg" align="left"><s:property value='cateName'/><s:hidden name="p010101.categoryList[%{#status.index}].cateName" value="%{cateName}"/></td>
				<td width="140px" class="td_bg" align="left"><s:property value='fatherCateName'/><s:hidden name="p010101.categoryList[%{#status.index}].fatherCateName" value="%{fatherCateName}"/></td>
				<td width="70px" class="td_bg" align="left"><s:property value='cateCount'/><s:hidden name="p010101.categoryList[%{#status.index}].cateCount" value="%{cateCount}"/></td>
				<td width="190px" class="td_bg" align="center">
				    <s:if test="%{fatherCateName == null}">
				        <input type="button" value="查看父类" disabled onclick="document.form1.rowIndex.value=${status.index};document.form1.searchMode.value='2';actionSubmit('P01010102')"/>&nbsp;&nbsp;&nbsp;
				    </s:if>
				    <s:if test="%{fatherCateName != null}">
				        <input type="button" value="查看父类" onclick="document.form1.rowIndex.value=${status.index};document.form1.searchMode.value='2';actionSubmit('P01010102')"/>&nbsp;&nbsp;&nbsp;
				    </s:if>
				    <s:if test="%{cateCount == 0}">
				        <input type="button" value="查看子类" disabled onclick="document.form1.rowIndex.value=${status.index};document.form1.searchMode.value='1';actionSubmit('P01010102')"/>&nbsp;&nbsp;&nbsp;
				    </s:if>
				    <s:if test="%{cateCount > 0}">
				        <input type="button" value="查看子类" onclick="document.form1.rowIndex.value=${status.index};document.form1.searchMode.value='1';actionSubmit('P01010102')"/>&nbsp;&nbsp;&nbsp;
				    </s:if>
				    <s:hidden name="p010101.categoryList[%{#status.index}].fatherCateId" value="%{fatherCateId}"/>
				    <s:hidden name="p010101.categoryList[%{#status.index}].cateId" value="%{cateId}"/>
				</td>
			</tr>
		    </s:iterator>
		</table>
		</div>
		<s:hidden name="viewId" value="P010101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="rowIndex"/>
		<div id="div4">
		<table width="480px" align="center">
		    <tr>
		    <td align="left"><input type="button" value="返回" onclick="window.close()"></td>
		    <td align="right"><input type="button" id="confirm" value="确定" onclick="actionSubmit('P01010103')"></td>
		    </tr>
		</table>
		</div>
	</s:form>
</body>
</html>