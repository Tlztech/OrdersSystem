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
function init(){
	var flg = document.form1.passFlg.value;
	if(flg == "1"){
		alert("操作成功");
		actionSubmit('A06010101');
	}
}
</script>
<style type="text/css">
<!--

#div1 {
    width: 650px;
    position: relative;
    top: 20px;
    left:100px;
}

#div2 {
    left: 20px;
    top: 40px;
    width:500px;
    position: relative;
}
#div3 {
    height: 300px;
    width:718px;
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
    bottom:25px;
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
                <td class="td_bg" width="130px">发货日&nbsp;&nbsp;(例:20130606)：</td>
                <td class="td_bg">
                    <s:textfield size="20" cssErrorStyle="background:red;" cssClass="readonly" readonly="true" maxlength="8" name="f060102.deliverDay"/>
                </td>
                <td class="td_bg" width="50px">运单号：</td>
                <td class="td_bg">
                    <s:textfield size="20" cssErrorStyle="background:red;" cssClass="readonly" readonly="true" maxlength="20" name="f060102.waybillNo"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="130px">物流公司：</td>
                <td class="td_bg">
                    <s:select name="f060102.logistics" list="#{ '01':'ZCE', '02':'EMS', '03':'其他'}" />
                </td>
                <td class="td_bg" width="50px">运费：</td>
                <td class="td_bg">
                    <s:textfield size="10" maxlength="8" name="f060102.freight"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="130px" >重量：</td>
                <td class="td_bg" colspan="3">
                    <s:textfield size="10" maxlength="4" name="f060102.weight"/>
                </td>
            </tr>
        </table>
        </div>
        <div id="div2">
        <br/>
        <table class="table" width="600px" cellspacing="1" cellpadding="1">
            <tr class="bg_tr" >
                <td width="70px" align="center">商品编号</td>
                <td width="150px" class="td_bg" >
                    <s:textfield name="f060102.commodityIdInput" cssErrorStyle="background:red;" size="18" maxlength="20"/>
                </td>
                <td width="50px" align="center">数量</td>
                <td width="60px" class="td_bg" >
                    <s:textfield name="f060102.quantityInput"  size="3" maxlength="5"/>
                </td>
                <td width="50px" align="center">备注</td>
                <td class="td_bg" >
                    <s:textfield name="f060102.remarksInput"  size="27" maxlength="50"/>
                </td>
            </tr>
        </table>
        <table width="680px" style="margin-top:-29px">
            <tr>
                <td align="right">
                    <input type="button" value="添加" onclick="actionSubmit('A06010401')" style="width:50px;height:25px">
                </td>
            </tr>
        </table>
        <br/><br/>
        <table width="700px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
            <tr class="bg_tr">
                <td width="120px" align="center">商品编号</td>
                <td width="80px" align="center">图片</td>
                <td width="60px" align="center">数量</td>
                <td width="340px" align="center">备注</td>
                <td align="center">操作</td>
            </tr>
        </table>
        </div>
        <div id="div3">
        <table width="700px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
            <s:iterator value="f060102.commodityList" status="status1">
            <tr class="bg_tr" >
                <td width="120px" class="td_bg" align="center"><s:property value='commodityId'/><s:hidden name="f060102.commodityList[%{#status1.index}].commodityId" value="%{commodityId}"/></td>
                <td width="80px" class="td_bg" align="center">
                    <a href="/OrdersSystem/<s:property value='picUrl'/>" target="_blank"><img height="50px" width="60px" src="/OrdersSystem/<s:property value='picUrl'/>" border="0"/></a><s:hidden name="f060102.commodityList[%{#status1.index}].picUrl" value="%{picUrl}"/>
                </td>
                <td width="60px" class="td_bg" align="center"><s:textfield name="f060102.commodityList[%{#status1.index}].quantity" value="%{quantity}" size="3" maxlength="5"/></td>
                <td width="340px" class="td_bg" align="center"><s:textfield name="f060102.commodityList[%{#status1.index}].remarks" value="%{remarks}" size="40" maxlength="50"/></td>
                <td class="td_bg" align="center">
                    <input type="button" onclick="document.form1.rowIndex.value=${status1.index};actionSubmit('A06010403')" value="删除"/>
                </td>
            </tr>
            </s:iterator>
        </table>
        </div>
        <div id="div4">
        <table>
            <tr>
                <td class="td_bg" align="left"><input type="button" value="返回" style="width:50px;height:25px" onclick="actionSubmit('A06010101')"></td>
                <td class="td_bg" align="right"><input type="button" value="确定" style="width:50px;height:25px" onclick="actionSubmit('A06010402')"></td>
            </tr>
        </table>
        </div>
        <s:hidden name="viewId" value="V060101"/>
        <s:hidden name="searchMode"/>
        <s:hidden name="mode"/>
        <s:hidden name="shoriMode"/>
        <s:hidden name="rowIndex"/>
        <s:hidden name="passFlg"/>
        <s:hidden name="alertFlg"/>
    </s:form>
</body>
</html>