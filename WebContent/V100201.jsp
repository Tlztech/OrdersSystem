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
#div4 {
    left: 20px;
    top: 40px;
    width:1000px;
    position: relative;
}
#div5 {
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
        <table cellspacing="1" cellpadding="2" width="50%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="80px">受注番号：</td>
                <td class="td_bg" width="180px">
                    <s:textfield  size="40" name="f100201.juchubango"/>
                </td>
                <td class="td_bg">
                    <input type="button" onclick="actionSubmit('A10020102');" value="検索"/>
                </td>
            </tr>
           
        </table>
        </div>
        <div id="div2">
        <table width="1000px">
            <tr>
                <td>
                <h2>注文状態</h2>
                </td>
                <td align="right">
                    <input type="button" onclick="actionSubmit('A10020103');" value="追加" style="width:100px;height:30px"/>&nbsp;&nbsp;&nbsp;
                    <input type="button" value="保存"  onclick="actionSubmit('A10020104');" style="width:100px;height:30px"/>
                </td>
            </tr>
        </table>
        
        <table width="1000px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
            <tr class="bg_tr">
                <td width="150px" align="center">日期</td>
                <td width="650px" align="center">状态</td>
                <td align="center">操作</td>
            </tr>
        </table>
        </div>
        <div id="div3">
        <table width="1000px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
            <s:iterator value="f100201.juchujoutaiList" status="status1">
            <tr class="bg_tr" >
                <td width="150px" class="td_bg" align="center"><s:property value='stsdata'/><s:hidden name="f100201.juchujoutaiList[%{#status1.index}].stsdata" value="%{stsdata}"/></td>
                <td width="650px" class="td_bg" align="left">
                <s:if test="editable == false"><s:property value='joutai'/><s:hidden name="f100201.juchujoutaiList[%{#status1.index}].joutai" value="%{joutai}"/></s:if>
                <s:else><s:textfield size="100" name="f100201.juchujoutaiList[%{#status1.index}].joutai" /></s:else>
                </td>
                <td class="td_bg" align="center">
               <s:if test="editable == true"><input type="button" value="削除"  onclick="document.form1.rowIndex.value=${status1.index};if(confirm('削除しますか')){actionSubmit('A10020105');}else{return flase;}"/></s:if>
               <s:hidden name="f100201.juchujoutaiList[%{#status1.index}].stsid" value="%{stsid}"/>
               <s:hidden name="f100201.juchujoutaiList[%{#status1.index}].editable" value="%{editable}"/></td>
            
               
            </tr>
            </s:iterator>
        </table>
        </div>
        <div id="div4">
        <h2>注文詳細リスト</h2>
        <table width="1000px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
            <tr class="bg_tr">
                <td width="150px" align="center">画像</td>
                <td width="180px" align="center">商品番号</td>
                <td width="420px" align="center">商品名</td>
                <td width="50px" align="center">個数</td>
                <td align="center">商品状態</td>
            </tr>
        </table>
        </div>
        <div id="div5">
        <table width="1000px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
            <s:iterator value="f100201.juchushousaiiList" status="status1">
            <tr class="bg_tr" >
                <td width="150px" class="td_bg" align="center"><img width="120px" src="<s:property value='picurl'/>"/><s:hidden name="f100201.juchushousaiiList[%{#status1.index}].picurl" value="%{picurl}"/></td>
                <td width="180px" class="td_bg" align="center"><s:property value='shohinbango'/><s:hidden name="f100201.juchushousaiiList[%{#status1.index}].shohinbango" value="%{shohinbango}"/></td>
                <td width="420px" class="td_bg" align="center"><s:property value='shohinmei'/><s:hidden name="f100201.juchushousaiiList[%{#status1.index}].shohinmei" value="%{shohinmei}"/></td>
                <td width="50px" class="td_bg" align="center"><s:property value='kosu'/><s:hidden name="f100201.juchushousaiiList[%{#status1.index}].kosu" value="%{kosu}"/></td>
                <td class="td_bg" align="center"><s:property value='shohinjoutai'/><s:hidden name="f100201.juchushousaiiList[%{#status1.index}].shohinjoutai" value="%{shohinjoutai}"/></td>
            </tr>
            </s:iterator>
        </table>
        </div>
        <s:hidden name="viewId" value="V100201"/>
        <s:hidden name="searchMode"/>
        <s:hidden name="mode"/>
        <s:hidden name="shoriMode"/>
        <s:hidden name="rowIndex"/>
        <s:hidden name="passFlg"/>
        <s:hidden name="alertFlg"/>
        <s:hidden name="f100201.passFlg"/>
        <s:hidden name="f100201.juchubango_hid"/>
    </s:form>
</body>
</html>