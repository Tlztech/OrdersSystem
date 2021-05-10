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
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg">商品编号：
                    <s:textfield size="15" maxlength="50" name="f030301.shohinbango"/>&nbsp;&nbsp;&nbsp;
                    <s:select list="#{'':'▼请选择','0':'上海仓库','1':'日本仓库'}" name="f030301.kuwei"/>&nbsp;&nbsp;&nbsp;
                    <s:select list="#{'':'▼请选择','0':'加','1':'减'}" name="f030301.jiajian"/>&nbsp;&nbsp;&nbsp;
                    <s:textfield size="4" maxlength="20" name="f030301.kosu"/>
                </td>
            </tr>
        </table>
        <hr>
        <table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" valign="top">损益原因<font color="red">*</font>：</td>
                <td class="td_bg">
                    <s:textarea cssStyle="width:300px;height:100px"  name="f030301.riyu"/>
                </td>
            </tr>
		</table>
		<table cellspacing="1" cellpadding="2" width="90%"  border="0">
            <tr class="bg_tr">
                <td class="td_bg" valign="top" align="right"><input type="button" onclick="this.disabled=true;actionSubmit('A03030102')" value="確定"/>
            </tr>
		</table>
		<hr>
		<table cellspacing="1" cellpadding="2" width="90%"  border="0">
            <tr class="bg_tr">
				<td class="td_bg" valign="top">CSVから在庫数量更新</td>
			</tr>
			<tr class="bg_tr">
				<td class="td_bg">csvファイル選択：<s:file
							name="csvFile" /></td>
                <td class="td_bg" valign="top" align="right"><input type="button" onclick="actionSubmit('A03030103');" value="CSVから導入"/>
            </tr>
		</table>
		</div>
		<s:hidden name="viewId" value="V030101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="rowIndex"/>
	</s:form>
</body>
</html>