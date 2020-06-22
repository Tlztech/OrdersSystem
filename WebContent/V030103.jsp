<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/commonjs.js"></script>
<style type="text/css">
<!--


#div1 {
    left: 50px;
	top: 85px;
	position: absolute;
}
#div2 {
    left: 50px;
	top: 55px;
	position: absolute;
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
        <div id="div2">
            <h4 style="color:black">操作成功！</h4>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="450px" align="left" border="0">
		    <tr class="bg_tr">
                <td width="60px" class="td_bg">分类ID：</td>
                <td class="td_bg">
                    <s:textfield readonly="true" cssClass="readonly" size="7" name="f030103.cateId"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="60px" class="td_bg">分类名称：</td>
                <td class="td_bg">
                    <s:textfield readonly="true" cssClass="readonly" size="35" maxlength="20" cssErrorStyle="background:red;" name="f030103.cateName"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">父类：</td>
                <td class="td_bg">
                    <s:textfield readonly="true" cssClass="readonly" size="35" maxlength="20" cssErrorStyle="background:red;" name="f030103.fatherCateName"/>
                    <input type="button" disabled value="参照"/>
                </td>
            </tr>
            <tr class="bg_tr">
					<td class="td_bg">名称（日文）：</td>
					<td class="td_bg"><s:textfield readonly="true" cssClass="readonly" size="35" maxlength="20"
							id="fatherCateName" cssErrorStyle="background:red;"
							name="f030103.nameJpn" /></td>
				</tr>
				<tr class="bg_tr">
					<td class="td_bg">素材（中文）：</td>
					<td class="td_bg"><s:textfield readonly="true" cssClass="readonly" size="35" maxlength="20"
							id="fatherCateName" cssErrorStyle="background:red;"
							name="f030103.sozaiChn" /></td>
				</tr>
				<tr class="bg_tr">
					<td class="td_bg">素材（日文）：</td>
					<td class="td_bg"><s:textfield readonly="true" cssClass="readonly" size="35" maxlength="20"
							id="sozaiJpn" cssErrorStyle="background:red;"
							name="f030103.sozaiJpn" /></td>
				</tr>
				<tr class="bg_tr">
					<td class="td_bg">默认报关价格：</td>
					<td class="td_bg"><s:textfield readonly="true" cssClass="readonly" size="35" maxlength="20"
							id="kakaku" cssErrorStyle="background:red;"
							name="f030103.kakaku" /></td>
				</tr>
            <tr class="bg_tr">
                <td class="td_bg">&nbsp;</td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">&nbsp;</td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" align=left colspan="2">
                    <input type="button" value="返回" onclick="actionSubmit('A03010101')"/>&nbsp;&nbsp;
                </td>
            </tr>
		</table>
		</div>
		<s:hidden name="viewId" value="V030103"/>
	    <s:hidden name="mode" id="mode"/>
    </s:form>
</body>
</html>