<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/commonjs.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function openWin1() {
   window.open("A06010104?waybillNo="+document.getElementsByName("f060101.wayBillList["+document.form1.rowIndex.value+"].waybillNo")[0].value);
    //if (returnVal) {
     //   alert("签收完成");
       // document.getElementById("kensaku").click();
    //}
}
function openBaoguanWin() {
	var waybillno = document.getElementsByName("f060101.wayBillList["+document.form1.rowIndex.value+"].waybillNo")[0].value
    window.open("A06010501?waybillNo="+waybillno);
   // if (returnVal) {
    //	actionSubmit("A06010501");
    //}
}
function init(){
	var companyId = "<%=session.getAttribute("comp")%>";
    var imgs = document.getElementsByName("button1");
    listLength = imgs.length;
    for(var i=0;i<listLength;i++){
    	if(document.getElementsByName("f060101.wayBillList["+i+"].status")[0].value == "已签收"){
    		document.getElementsByName("button1")[i].value="查看";
    		document.getElementsByName("button1")[i].disabled = false;
    	}
    	
   		if(document.getElementsByName("f060101.wayBillList["+i+"].status")[0].value == "未签收"){
   			if(companyId == 0 || companyId == 1) {
   			document.getElementsByName("button1")[i].disabled = false;
   			}
            document.getElementsByName("button5")[i].disabled = false;
        }
    	if(document.getElementsByName("f060101.wayBillList["+i+"].status")[0].value == "一时保存"){
            document.getElementsByName("button2")[i].disabled = false;
            document.getElementsByName("button6")[i].disabled = false;
        }
    }
    if(document.getElementsByName("f060101.passFlg")[0].value == "1"){
    	document.getElementsByName("f060101.passFlg")[0].value = "0";
    	alert("删除成功！");
    	document.getElementById("kensaku").click();
    }
    if(document.getElementsByName("f060101.passFlg")[0].value == "2"){
        document.getElementsByName("f060101.passFlg")[0].value = "0";
        alert("取消成功！");
        document.getElementById("kensaku").click();
    }
	
}

function updateCustoms(value) {
	var index = document.form1.rowIndex.value;
	var bango = document.getElementsByName("f060101.wayBillList[" + index
			+ "].waybillNo")[0].value;
	var motoCustoms = document.getElementsByName("f060101.wayBillList[" + index
			+ "].customs")[0].value;
	
	if(motoCustoms!=value){	
	$.post("A06010112", {
		waybillNo : bango,
		customs : value,
	}, function(result) {
		if (result != "true") {
			alert("アップデート失敗！");
		}else{
			document.getElementsByName("f060101.wayBillList[" + index
					+ "].customs")[0].value = value;
		}
	}, "json");
	}
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
<body onload="init()">
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
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
                    <s:textfield cssClass="Wdate" onClick="WdatePicker()" size="20" maxlength="8" name="f060101.deliverDay"/>
                </td>
                <td class="td_bg" width="80px">签收日：</td>
                <td class="td_bg">
                    <s:textfield cssClass="Wdate" onClick="WdatePicker()" size="20" maxlength="8" id="categoryName" name="f060101.receiveDay"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">运单号：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f060101.waybillNo"/>
                </td>
                <td class="td_bg" width="80px">关税：</td>
                <td class="td_bg">
                    <s:select list="#{ '':'--','1':'未缴纳关税', '2':'缴纳关税'}"   name="f060101.customs"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">物流公司：</td>
                <td class="td_bg" width="280px">
                    <s:select list="#{ '01':'ZCE', '02':'EMS', '03':'其他','04':'全部'}"   name="f060101.logistics"/>
                </td>
                <s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
                <td class="td_bg" width="80px">发货公司：</td>
                <td class="td_bg">
                    <select id="f060101.companyId" name="f060101.companyId">
                    	<option value="0">请选择</option>
                        <c:forEach items="${f060101.companyList}" var="company" >
                        	<c:if test="${company.companyId==f060101.companyId}">
                              <option value="${company.companyId}" selected="selected"> ${company.companyName} </option>
                            </c:if>
                            <c:if test="${company.companyId!=f060101.companyId}">
                            	<option value="${company.companyId}"> ${company.companyName} </option>
                            </c:if>
                        </c:forEach>  
                    </select>
                </td>
                </s:if>
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
                <td align="right"><input type="button" onclick="actionSubmit('A06010103')" value="检索" id="kensaku" style="width:50px;height:25px"/></td>
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
                <td width="60px" class="td_bg" align="right"><s:hidden name="f060101.wayBillList[%{#status1.index}].customs"/><s:textfield size="5" style="text-align:right" name="f060101.wayBillList[%{#status1.index}].customs"  onblur="document.form1.rowIndex.value=%{#status1.index};updateCustoms(this.value)"/></td>
                <td class="td_bg" align="center">
                            <input type="button" name="button1" value="签收" disabled onclick="document.form1.rowIndex.value=${status1.index};openWin1();"/>
                            <input type="button" name="button2" value="修改" disabled onclick="document.form1.rowIndex.value=${status1.index};actionSubmit('A06010105');"/>
                            <input type="button" name="button3" value="下载发货单" onclick="document.form1.rowIndex.value=${status1.index};actionSubmit('A06010107');"/>
                            <input type="button" name="button4" value="报关单" onclick="document.form1.rowIndex.value=${status1.index};openBaoguanWin();"/>
                            <input type="button" name="button5" value="取消发货" disabled onclick="document.form1.rowIndex.value=${status1.index};if(confirm('确认取消发货？')){actionSubmit('A06010111');}else{return flase;}"/>
                            <input type="button" name="button6" value="删除" disabled onclick="document.form1.rowIndex.value=${status1.index};if(confirm('确认删除？')){actionSubmit('A06010110');}else{return flase;}"/>
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
        <s:hidden name="f060101.passFlg"/>
    </s:form>
</body>
</html>