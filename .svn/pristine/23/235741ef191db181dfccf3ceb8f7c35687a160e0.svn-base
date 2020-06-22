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
	div_obj.css("top",($(window).height()-250)/2);
	div_obj.css("left",($(window).width()-200)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
}   
function hideDiv() {   
	  $("#mask").hide();   
	  $("#pop-div").hide(); 
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
	height: 520px;
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
<!--         <div id="div1"> -->
<%--         <s:actionerror name="err" cssStyle="color:red;font-size:13"/> --%>
<!-- 		<table cellspacing="1" cellpadding="2" width="90%" border="0"> -->
<!--             <tr class="bg_tr"> -->
<!--                 <td class="td_bg">专辑名称： -->
<%--                     <s:textfield size="35" maxlength="20" name="f120101.cateName"/> --%>
<!--                 </td> -->
<!--                 <td class="td_bg">商品编号： -->
<%--                     <s:textfield size="35" maxlength="20" name="f120101.cateName"/> --%>
<!--                     <input type="button" value="检索" onclick="document.form1.searchMode.value='0';actionSubmit('A03010102')"/> -->
<!--                 </td> -->
<!--             </tr> -->
<!-- 		</table> -->
<!-- 		</div> -->
		<div id="div2">
		<table width="450px">
            <tr>
<%--                 <td>检索结果：<s:label name="f120101.resultCount" />件</td> --%>
                <td align="right"><input type="button" onclick="popupDiv();" value="添加分类"/></td>
            </tr>
        </table>

		</div>
		<div id="div3">
	    <table width="450px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="250px" align="center">类别</td>
				<td align="center">操作</td>
			</tr>
			<s:iterator value="f170101.albumList" status="status1">
            <tr class="bg_tr">
                <td width="250px" class="td_bg" align="center"><s:property value='name'/><s:hidden name="f170101.albumList[%{#status1.index}].name" value="%{name}"/></td>
                <td class="td_bg" align="center">
                    <a target="_blank" href="A17010201?shumokuid=<s:property value='shumokuId'/>">查看</a>
                </td>
            </tr>
            </s:iterator>
		</table>

</div>
        <div id='pop-div' style="width:200px;height:250px;display:none;position: fixed;" class="pop-box">   
            <div class="pop-box-body" style="height:180px">
            名称&nbsp;<s:textfield name="f170101.addName"/>
            </div>
            <table align="center" width="95%">
                <tr>
                    <td width="25%" align="right"><input type="button" onclick="actionSubmit('A17010102');" value="確定" /></td>
                    <td width="25%"></td>
                    <td width="25%"></td>
                    <td width="25%"><input type="button" onclick="hideDiv();" value="返回" /></td>
                </tr>
            </table>
        </div>
		<s:hidden name="viewId" value="V120101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="rowIndex"/>
	</s:form>
</body>
</html>