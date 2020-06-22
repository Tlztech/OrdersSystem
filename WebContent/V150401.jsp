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
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

function init(){
	var scrollxVal = document.getElementsByName("scrollx")[0].value;
	var scrollyVal = document.getElementsByName("scrolly")[0].value;
	if(scrollxVal != ""){
		$(document).scrollLeft(scrollxVal);
	}
	if(scrollyVal != ""){
		$(document).scrollTop(scrollyVal);
	}
}

function openwin1() {
    openShowModalDialog("1024", "700", "A15050101?mode=shinki");
}

function openwin2(seikyuid) {
    openShowModalDialog("1024", "700", "A15050101?mode="+seikyuid);
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
        </div>
        <div id="div2">
        <table width="1000px">
            <tr>
                <td align="right">
                    <input type="button" onclick="openwin1();" value="添加请求书" style="width:100px;height:20px"/>
                </td>
            </tr>
        </table>
        <table width="1000px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
            <tr class="bg_tr">
                <td width="150px" align="center">请求开始日</td>
                <td width="150px" align="center">请求截止日</td>
                <td width="150px" align="center">请求者</td>
                <td width="150px" align="center">被请求者</td>
                <td width="150px" align="center">请求金额</td>
                <td width="150px" align="center">做成日</td>
                <td align="center">操作</td>
            </tr>
        </table>
        </div>
        <div id="div3">
        <table width="1000px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
            <s:iterator value="f150401.seikyushoList" status="status1">
            <tr class="bg_tr" >
                <td width="150px" class="td_bg" align="center"><s:property value='startDay'/><s:hidden name="f150401.seikyushoList[%{#status1.index}].startDay" value="%{startDay}"/></td>
                <td width="150px" class="td_bg" align="center"><s:property value='endDay'/><s:hidden name="f150401.seikyushoList[%{#status1.index}].endDay" value="%{endDay}"/></td>
                <td width="150px" class="td_bg" align="center"><s:property value='seikyusha'/><s:hidden name="f150401.seikyushoList[%{#status1.index}].seikyusha" value="%{seikyusha}"/></td>
                <td width="150px" class="td_bg" align="center"><s:property value='hiseikyusha'/><s:hidden name="f150401.seikyushoList[%{#status1.index}].hiseikyusha" value="%{hiseikyusha}"/></td>
                <td width="150px" class="td_bg" align="center"><s:property value='seikyukingaku'/><s:hidden name="f150401.seikyushoList[%{#status1.index}].seikyukingaku" value="%{seikyukingaku}"/></td>
                <td width="150px" class="td_bg" align="center"><s:property value='createDay'/><s:hidden name="f150401.seikyushoList[%{#status1.index}].createDay" value="%{createDay}"/></td>
                <td class="td_bg" align="center">
                    <s:hidden name="f150401.seikyushoList[%{#status1.index}].seikyuid" value="%{seikyuid}"/>
                    <input type="button" name="button2" value="查看"  onclick="openwin2(<s:property value='seikyuid'/>);"/>
                </td>
            </tr>
            </s:iterator>
        </table>
        </div>
        <s:hidden name="searchMode"/>
        <s:hidden name="mode"/>
        <s:hidden name="shoriMode"/>
        <s:hidden name="rowIndex"/>
        <s:hidden name="passFlg"/>
        <s:hidden name="alertFlg"/>
    </s:form>
</body>
<script type="text/javascript">
window.onscroll = function(){
	
	document.getElementsByName("scrolly")[0].value = $(document).scrollTop();
	document.getElementsByName("scrollx")[0].value = $(document).scrollLeft();
}
</script>
</html>