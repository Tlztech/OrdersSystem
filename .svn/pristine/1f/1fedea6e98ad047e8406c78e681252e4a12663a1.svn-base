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
function popupDiv() {   
    var div_obj = $("#pop-div");    
	div_obj.css("top",($(window).height()-200)/2);
	div_obj.css("left",($(window).width()-400)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
}   
function popupDiv2() {   
    var div_obj = $("#pop-div2");    
	div_obj.css("top",($(window).height()-200)/2);
	div_obj.css("left",($(window).width()-400)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
}  
function hideDiv() {   
	  $("#mask").hide();   
	  $("#pop-div").hide(); 
	  $("#pop-div2").hide(); 
	  clearVal();
	} 
</script>
<style type="text/css">
<!--
body {
	font-family: "微锟斤拷锟脚猴拷,Verdana, 锟斤拷锟斤拷锟斤拷";
	color: #1E5494; /*锟斤拷锟斤拷锟斤拷锟斤拷色*/
	font-size: 12px;
	font-weight: bolder;
	margin-left: 4px;
	margin-top: 0px;
	margin-right: 0px;
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 0px;
	PADDING-TOP: 0px;
}
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
	height: 320px;
	width:980px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 50px;
}
#div4 {
    left: 50px;
	top: 40px;
	width:880px;
	position: relative;
}
#div5 {
	height: 320px;
	width:980px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 50px;
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
<body>
<div id='mask' class="mask" style="width:100%;height:100%;display:none"></div>
    <s:form name="form1" theme="simple">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>

		<div id="div2">
		<table width="960px">
            <tr>
                <td>账户一览</td>
                <td align="right"><input type="button" onclick="popupDiv();" value="添加账本"/></td>
            </tr>
        </table>
        <table width="960px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
				<td width="200px" align="center">账簿名称</td>
				<td width="150px" align="center">账号（卡号）</td>
				<td width="150px" align="center">类别</td>
				<td width="80px" align="center">地区</td>
				<td width="150px" align="center">备注</td>
				<td align="center"></td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="960px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
		    <s:iterator value="f150301.accountList" status="status1">
			<tr class="bg_tr">
			    <td width="200px" class="td_bg" align="center"><s:property value='name'/><s:hidden name="f150301.accountList[%{#status1.index}].name" value="%{name}"/></td>
				<td width="150px" class="td_bg" align="center"><s:property value='cardno'/><s:hidden name="f150301.accountList[%{#status1.index}].cardno" value="%{cardno}"/></td>
				<td width="150px" class="td_bg" align="center"><s:property value='type'/><s:hidden name="f150301.accountList[%{#status1.index}].type" value="%{type}"/></td>
				<td width="80px" class="td_bg" align="center"><s:property value='area'/><s:hidden name="f150301.accountList[%{#status1.index}].area" value="%{area}"/></td>
				<td width="150px" class="td_bg" align="center"><s:property value='biko'/><s:hidden name="f150301.accountList[%{#status1.index}].biko" value="%{biko}"/></td>
				<td class="td_bg" align="center"><s:if test="type != '上海现金账' && type != '日本現金帳'"><input type="button" value="自动分类设置"/></s:if></td>
			</tr>
		    </s:iterator>
		</table>
		</div>
		<hr>
		<div id="div4">
		<table width="960px">
            <tr>
                <td>分类一览</td>
                <td align="right"><input type="button" onclick="popupDiv2();" value="添加分类"/></td>
            </tr>
        </table>
        <table width="960px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
				<td width="200px" align="center">分类名称</td>
				<td width="150px" align="center">类别</td>
				<td width="80px" align="center">地区</td>
				<td align="center">备注</td>
			</tr>
		</table>
		</div>
		<div id="div5">
	    <table width="960px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
		    <s:iterator value="f150301.fenleiList" status="status1">
			<tr class="bg_tr">
			    <td width="200px" class="td_bg" align="center"><s:property value='name'/><s:hidden name="f150301.fenleiList[%{#status1.index}].name" value="%{name}"/></td>
				<td width="150px" class="td_bg" align="center"><s:property value='type'/><s:hidden name="f150301.fenleiList[%{#status1.index}].type" value="%{type}"/></td>
				<td width="80px" class="td_bg" align="center"><s:property value='area'/><s:hidden name="f150301.fenleiList[%{#status1.index}].area" value="%{area}"/></td>
				<td class="td_bg" align="center"><s:property value='biko'/><s:hidden name="f150301.fenleiList[%{#status1.index}].biko" value="%{biko}"/></td>
			</tr>
		    </s:iterator>
		</table>
		</div>

        <div id='pop-div' style="width:400px;height:220px;display:none;position: fixed;" class="pop-box">   
            <div class="pop-box-body">
            <table align="center" width="395px">
                <tr>
                    <td>账簿名称</td>
                    <td><s:textfield name="f150301.accountName" size="25"/></td>
                </tr>
                <tr>
                    <td>账号（卡号）</td>
                    <td><s:textfield name="f150301.cardNo" size="25"/></td>
                </tr>
                <tr>
                    <td>地区</td>
                    <td><s:select name="f150301.area" list="#{0:'日本',1:'中国'}"/></td>
                </tr>
                <tr>
                    <td>类别</td>
                    <td><s:select list="typeList" listValue="bankName" listKey="bankCode"  name="f150301.type"/></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td><s:textarea name="f150301.biko" style="width:300px;"/></td>
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
        <div id='pop-div2' style="width:400px;height:220px;display:none;position: fixed;" class="pop-box">   
            <div class="pop-box-body">
            <table align="center" width="395px">
                <tr>
                    <td>分类名称</td>
                    <td><s:textfield name="f150301.fenleiName" size="25"/></td>
                </tr>
                <tr>
                    <td>地区</td>
                    <td><s:select name="f150301.area2" list="#{0:'日本',1:'中国'}"/></td>
                </tr>
                <tr>
                    <td>类别</td>
                    <td><s:select list="#{0:'进账',1:'出账'}"  name="f150301.type2"/></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td><s:textarea name="f150301.biko2" style="width:300px;"/></td>
                </tr>
            </table>
            </div>
            <table align="center" width="95%">
                <tr>
                    <td width="25%" align="right"><input type="button" onclick="actionSubmit('A15030103');" value="確定" /></td>
                    <td width="25%"></td>
                    <td width="25%"></td>
                    <td width="25%"><input type="button" onclick="hideDiv();" value="返回" /></td>
                </tr>
            </table>
        </div>
		<s:hidden name="viewId" value="V150301"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="rowIndex"/>
	</s:form>
</body>
</html>