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
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript">
function openMeisaiWin(accountid,type) {
	var actionId = "";
	if("003" == type || "008" == type){
		//现金账
		actionId = "A15010201";
	}else if("001" == type){
		//中国银行
		actionId = "A15010301";
	}else if("002" == type){
		//支付宝
		actionId = "A15010401";
	}else if("004" == type || "005" == type || "006" == type || "007" == type){
		//支付宝
		actionId = "A15010501";
	}
	
	actionId = actionId + "?accountId="+accountid;
	
    openShowModalDialog("1024", "700", actionId);

}
function popupDiv(index) { 

    var div_obj = $("#pop-div");    
	div_obj.css("top",($(window).height()-250)/2);
	div_obj.css("left",($(window).width()-250)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
    $("#rowIndex").val(index); 
}   
function hideDiv() {   
	  $("#mask").hide();   
	  $("#pop-div").hide(); 
	  clearVal();
	} 

</script>
<style type="text/css">
<!--

#div1 {
	width: 900px;
	position: relative;
	top: 20px;
	left:20px;
}

#div2 {
    left: 20px;
	top: 40px;
	width:1100px;
	position: relative;
}
#div3 {
	height: 400px;
	width:1120px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 20px;
}
#div4 {
	height: 40px;
	width:1120px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 40px;
	left: 20px;
}
  .pop-box {   
            z-index: 9999; /*这个数值要足够大，才能够显示在最上层*/  
            margin-bottom: 3px;   
            position: absolute;   
            background: #FFF;   
            border:solid 1px #6e8bde;   
        }   
          
        .pop-box h4 {   
            color: #FFF;   
            cursor:default;   
            height: 18px;   
            font-size: 14px;   
            font-weight:bold;   
            text-align: left;   
            padding-left: 8px;   
            padding-top: 4px;   
            padding-bottom: 2px;   
            background: url("../images/header_bg.gif") repeat-x 0 0;   
        }   
          
        .pop-box-body {   
            clear: both;   
            margin: 4px;   
            padding: 2px;   
        } 
        
        
        .mask {   
            color:#C7EDCC;
            background-color:#C7EDCC;
            position:absolute;
            top:0px;
            left:0px;
            filter: Alpha(Opacity=60);
            -moz-opacity:.6;    
            opacity:0.6;
            z-index:1000;   
        } 
-->
</style>
</head>
<body onload="init();">
<div id='mask' class="mask" style="width:100%;height:100%;display:none"></div>
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="2" cellpadding="2" class="table" width="300px"  border="0">
            <tr>
                <td colspan="2">账户总览</td>
            </tr>
            <tr class="bg_tr">
                <td width="210px">总余额</td>
                <td class="td_bg" align="right"><s:property value="f150101.totalBalance"/><s:if test ='area == 1'>元</s:if><s:else>円</s:else></td>
            </tr>
            <tr class="bg_tr">
                <td width="210px">账簿数</td>
                <td class="td_bg" align="right"><s:property value="f150101.accountCount"/></td>
            </tr>
            <tr class="bg_tr">
                <td width="210px">未处理归类</td>
                <td class="td_bg" align="right">
                    <s:if test ='f150101.totalUndidCount > 0'><font color="red"><s:property value="f150101.totalUndidCount"/></font></s:if>
                    <s:else><s:property value="f150101.totalUndidCount"/></s:else>
                </td>
            </tr>
		</table>
		</div>
		<div id="div2">
        <table width="960px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
				<td width="150px" align="center">账簿名称</td>
				<td width="150px" align="center">账号（卡号）</td>
				<td width="80px" align="center">类别</td>
				<td width="80px" align="center">余额</td>
				<td width="100px" align="center">未处理归类</td>
				<td width="150px" align="center">最后更新日</td>
				<td align="center"></td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="960px" class="table" cellspacing="1" cellpadding="1"  align="left" border="0">
		    <s:iterator value="f150101.accountList" status="status1">
			<tr class="bg_tr" height="50px">
			    <td width="150px" class="td_bg" align="center"><s:property value='name'/><s:hidden name="f150101.accountList[%{#status1.index}].name" value="%{name}"/></td>
				<td width="150px" class="td_bg" align="center"><s:property value='cardNo'/><s:hidden name="f150101.accountList[%{#status1.index}].cardNo" value="%{cardNo}"/></td>
				<td width="80px" class="td_bg" align="center"><s:property value='typeName'/><s:hidden name="f150101.accountList[%{#status1.index}].typeName" value="%{typeName}"/></td>
				<td width="80px" class="td_bg" align="right"><s:property value='balance'/><s:hidden name="f150101.accountList[%{#status1.index}].balance" value="%{balance}"/>&nbsp;&nbsp;</td>
				<td width="100px" class="td_bg" align="right">
				    <s:if test ='undidCount > 0'><font color="red"><s:property value="undidCount"/></font></s:if>
				    <s:else><s:property value="undidCount"/></s:else>
				    <s:hidden name="f150101.accountList[%{#status1.index}].undidCount" value="%{undidCount}"/>&nbsp;&nbsp;
				</td>
				<td width="150px" class="td_bg" align="center"><s:property value='updateTime'/><s:hidden name="f150101.accountList[%{#status1.index}].updateTime" value="%{updateTime}"/></td>
				<td class="td_bg" align="center"><input type="button" onclick="openMeisaiWin(<s:property value='accountId'/>,<s:property value='type'/>);" value="查看详情"></td>
				<s:hidden name="f150101.accountList[%{#status1.index}].type" value="%{type}"/>
				<s:hidden name="f150101.accountList[%{#status1.index}].accountId" value="%{accountId}"/>
			</tr>
		    </s:iterator>
		</table>
		</div>
		<div id='pop-div' style="width:250px;height:150px;display:none;position: fixed;" class="pop-box">   
            <div class="pop-box-body">
            <table align="center" width="245px" height="100px">
                <tr>
                    <td><s:file name="f150301.csvFile"/></td>
                </tr>
            </table>
            </div>
            <table align="center" width="95%">
                <tr>
                    <td width="25%" align="right"><input type="button" onclick="actionSubmit('A15030102');" value="確定" /></td>
                    <td width="25%"></td>
                    <td width="25%"></td>
                    <td width="25%"><input type="button" onclick="hideDiv();" value="返回" /></td>
                </tr>
            </table>
        </div>
		<s:hidden name="viewId" value="V150101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex" id ='rowIndex'/>
		<s:hidden name="area"/>
	</s:form>
</body>
</html>