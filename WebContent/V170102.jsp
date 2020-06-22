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
        <div id="div2">
        <s:actionerror name="err" cssStyle="color:red;font-size:16"/>
        <h2>
            <s:label name="name"></s:label>
        </h2>
        <table width="650px">
            <tr>
                <td align="left">中文&nbsp;&nbsp;<s:textfield name="f170102.chinese" /></td>
                <td align="left">日文&nbsp;&nbsp;<s:textfield name="f170102.japanese" /></td>
                <td align="left"><input type="button" onclick="actionSubmit('A17010202')" value="添加"/></td>
            </tr>
        </table>
        
        </div>
        <div id="div3">
        <table width="660px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
            <tr class="bg_tr">
                <td width="160px" align="center">ID</td>
                <td width="200px" align="center">中文名</td>
                <td width="200px" align="center">日文名</td>
                <td align="center">操作</td>
            </tr>
        </table>
        <table width="660px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
            <s:iterator value="f170102.dataList" status="status">
            <tr class="bg_tr">
                <td width="160px" class="td_bg" align="left"><s:property value='id'/></td>
                <td width="200px" class="td_bg" align="left"><s:property value='chinese'/></td>
                <td width="200px" class="td_bg" align="left"><s:property value='japanese'/></td>
                <td class="td_bg" align="center">
                    <input type="button" onclick="actionSubmit('A17010203?id=<s:property value='id'/>')" value="删除"/>&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
            </s:iterator>
        </table>
        </div>
        <s:hidden name="viewId" value="V170102"/>
        <s:hidden name="searchMode"/>
        <s:hidden name="mode"/>
        <s:hidden name="rowIndex"/>
        <s:hidden name="shumokuid"/>
    </s:form>
</body>
</html>