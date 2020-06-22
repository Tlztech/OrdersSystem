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
    width:1000px;
    position: relative;
}
#div3 {
    height: 280px;
    width:1120px;
    overflow-y:auto;
    overflow-x:hidden;
    position: relative;
    top: 39px;
    left: 20px;
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
                <td class="td_bg" width="80px">发货日：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f060101.deliverDay"/>
                </td>
                <td class="td_bg" width="80px">签收日：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" id="categoryName" name="f060101.receiveDay"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">运单号：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f060101.waybillNo"/>
                </td>
                <td class="td_bg" width="80px">关税：</td>
                <td class="td_bg">
                    <s:select list="#{ '1':'未缴纳关税', '2':'缴纳关税'}"   name="f060101.customs"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">物流公司：</td>
                <td class="td_bg" width="280px">
                    <s:select list="#{ '01':'ZCE', '02':'EMS', '03':'其他','04':'全部'}"   name="f060101.logistics"/>
                </td>
                <td class="td_bg" width="80px">&nbsp;</td>
                <td class="td_bg">
                   &nbsp;
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td class="td_bg" colspan="3">
                    <s:radio name="f060101.status" list="#{ '1':'全部', '2':'已签收', '3':'未签收'}" />&nbsp;&nbsp;&nbsp;
                </td>
                <td align="right"><input type="button" onclick="actionSubmit('A06010103')" value="检索" style="width:50px;height:25px"/></td>
            </tr>
        </table>
        </div>
        <div id="div2">
        <table width="1000px">
            <tr>
                <td>检索结果：<s:label name="f060101.resultCount" />件</td>
                <td align="right">
                    <input type="button" onclick="actionSubmit('A06010102');" value="添加发货单" style="width:100px;height:20px"/>
                </td>
            </tr>
        </table>
        <table width="1000px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
            <tr class="bg_tr">
                <td width="100px" align="center">运单号</td>
                <td width="100px" align="center">物流公司</td>
                <td width="80px" align="center">状态</td>
                <td width="100px" align="center">发货日</td>
                <td width="100px" align="center">签收日</td>
                <td width="60px" align="center">重量(KG)</td>
                <td width="60px" align="center">运费(CNY)</td>
                <td width="60px" align="center">关税(JPY)</td>
                <td align="center">操作</td>
            </tr>
        </table>
        </div>
        <div id="div3">
        <table width="1000px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
            <s:iterator value="f060101.wayBillList" status="status1">
            <tr class="bg_tr" >
                <td width="100px" class="td_bg" align="center"><s:property value='waybillNo'/><s:hidden name="f060101.wayBillList[%{#status1.index}].waybillNo" value="%{waybillNo}"/></td>
                <td width="100px" class="td_bg" align="center"><s:property value='logistics'/><s:hidden name="f060101.wayBillList[%{#status1.index}].logistics" value="%{logistics}"/>
                </td>
                <td width="80px" class="td_bg" align="center"><s:property value='status'/><s:hidden name="f060101.wayBillList[%{#status1.index}].status" value="%{status}"/></td>
                <td width="100px" class="td_bg" align="center"><s:property value='deliverDay'/><s:hidden name="f060101.wayBillList[%{#status1.index}].deliverDay" value="%{deliverDay}"/></td>
                <td width="100px" class="td_bg" align="center"><s:property value='receiveDay'/><s:hidden name="f060101.wayBillList[%{#status1.index}].receiveDay" value="%{receiveDay}"/></td>
                <td width="60px" class="td_bg" align="center"><s:property value='weight'/><s:hidden name="f060101.wayBillList[%{#status1.index}].weight" value="%{weight}"/></td>
                <td width="60px" class="td_bg" align="right"><s:property value='freight'/><s:hidden name="f060101.wayBillList[%{#status1.index}].freight" value="%{freight}"/></td>
                <td width="60px" class="td_bg" align="right"><s:property value='customs'/><s:hidden name="f060101.wayBillList[%{#status1.index}].customs" value="%{customs}"/></td>
                <td class="td_bg" align="center">
                            <input type="button" name="button1" value="签收" onclick="document.form1.rowIndex.value=${status1.index};actionSubmit('A06010104');"/>
                            <input type="button" name="button2" value="查看/修改" onclick="document.form1.rowIndex.value=${status1.index};actionSubmit('A06010105');"/>
                            <input type="button" name="button2" value="删除" onclick="document.form1.rowIndex.value=${status1.index};actionSubmit('A06010106');"/>
                </td>
            </tr>
            </s:iterator>
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