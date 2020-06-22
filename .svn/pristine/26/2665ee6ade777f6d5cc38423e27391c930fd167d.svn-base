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
<style type="text/css">
<!--

#div1 {
	width: 500px;
	position: relative;
	top: 20px;
	left:100px;
}

#div2 {
    left: 50px;
	top: 40px;
	width:880px;
	position: relative;
}
#div3 {
	height: 520px;
	width:980px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 50px;
}
-->
</style>
</head>
<body>
    <s:form name="form1" theme="simple">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg">分类名称：
                    <s:textfield size="35" maxlength="20" name="f030101.cateName"/>
                    <input type="button" value="检索" onclick="document.form1.searchMode.value='0';actionSubmit('A03010102')"/>
                </td>
            </tr>
		</table>
		</div>
		<div id="div2">
		<table width="960px">
            <tr>
                <td>检索结果：<s:label name="f030101.resultCount" />件</td>
                <td align="right"><input type="button" onclick="document.form1.mode.value='0';actionSubmit('A03010201')" value="添加分类"/></td>
            </tr>
        </table>
        <table width="960px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="60px" align="center">ID</td>
				<td width="100px" align="center">分类名称</td>
				<td width="100px" align="center">父类名称</td>
				<td width="70px" align="center">子类个数</td>
				<td width="70px" align="center">名称（日文）</td>
				<td width="70px" align="center">素材（中文）</td>
				<td width="70px" align="center">素材（日文）</td>
				<td width="100px" align="center">默认报关价格</td>
				<td align="center">操作</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="960px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
		    <s:iterator value="f030101.categoryList" status="status">
			<tr class="bg_tr">
			    <td width="60px" class="td_bg" align="left"><s:property value='cateId'/><s:hidden name="f030101.categoryList[%{#status.index}].cateId" value="%{cateId}"/></td>
				<td width="100px" class="td_bg" align="left"><s:property value='cateName'/><s:hidden name="f030101.categoryList[%{#status.index}].cateName" value="%{cateName}"/></td>
				<td width="100px" class="td_bg" align="left"><s:property value='fatherCateName'/><s:hidden name="f030101.categoryList[%{#status.index}].fatherCateName" value="%{fatherCateName}"/></td>
				<td width="70px" class="td_bg" align="left"><s:property value='cateCount'/><s:hidden name="f030101.categoryList[%{#status.index}].cateCount" value="%{cateCount}"/></td>
				<td width="70px" class="td_bg" align="left"><s:property value='nameJpn'/><s:hidden name="f030101.categoryList[%{#status.index}].nameJpn" value="%{nameJpn}"/></td>
				<td width="70px" class="td_bg" align="left"><s:property value='sozaiChn'/><s:hidden name="f030101.categoryList[%{#status.index}].sozaiChn" value="%{sozaiChn}"/></td>
				<td width="70px" class="td_bg" align="left"><s:property value='sozaiJpn'/><s:hidden name="f030101.categoryList[%{#status.index}].sozaiJpn" value="%{sozaiJpn}"/></td>
				<td width="100px" class="td_bg" align="left"><s:property value='kakaku'/><s:hidden name="f030101.categoryList[%{#status.index}].kakaku" value="%{kakaku}"/></td>
				<td class="td_bg" align="center">
				    <s:if test="%{fatherCateName == null}">
				        <input type="button" value="查看父类" disabled onclick="document.form1.rowIndex.value=${status.index};document.form1.searchMode.value='2';actionSubmit('A03010102')"/>&nbsp;&nbsp;&nbsp;
				    </s:if>
				    <s:if test="%{fatherCateName != null}">
				        <input type="button" value="查看父类" onclick="document.form1.rowIndex.value=${status.index};document.form1.searchMode.value='2';actionSubmit('A03010102')"/>&nbsp;&nbsp;&nbsp;
				    </s:if>
				    <s:if test="%{cateCount == 0}">
				        <input type="button" value="查看子类" disabled onclick="document.form1.rowIndex.value=${status.index};document.form1.searchMode.value='1';actionSubmit('A03010102')"/>&nbsp;&nbsp;&nbsp;
				    </s:if>
				    <s:if test="%{cateCount > 0}">
				        <input type="button" value="查看子类" onclick="document.form1.rowIndex.value=${status.index};document.form1.searchMode.value='1';actionSubmit('A03010102')"/>&nbsp;&nbsp;&nbsp;
				    </s:if>
				    <input type="button" value="添加子类" onclick="document.form1.rowIndex.value=${status.index};document.form1.mode.value='3';actionSubmit('A03010201')"/>&nbsp;&nbsp;&nbsp;
				    <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.mode.value='1';actionSubmit('A03010201')" value="修改"/>&nbsp;&nbsp;&nbsp;
				    <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.mode.value='2';actionSubmit('A03010201')" value="删除"/>&nbsp;&nbsp;&nbsp;
				    <s:hidden name="f030101.categoryList[%{#status.index}].fatherCateId" value="%{fatherCateId}"/>
				</td>
			</tr>
		    </s:iterator>
		</table>
		</div>
		<s:hidden name="viewId" value="V030101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="rowIndex"/>
	</s:form>
</body>
</html>