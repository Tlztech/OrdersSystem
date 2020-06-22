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
var scrollxVal = document.getElementsByName("scrollx")[0].value;
	var scrollyVal = document.getElementsByName("scrolly")[0].value;
	if(scrollxVal != ""){
		$(document).scrollLeft(scrollxVal);
	}
	if(scrollyVal != ""){
		$(document).scrollTop(scrollyVal);
	}
}
</script>
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
	width:580px;
	position: relative;
}
#div3 {
	height: 520px;
	width:580px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 50px;
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
		<div id="div2">
		<s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table>
		    <tr>
		        <td><s:file name="uploadFile"/>&nbsp;&nbsp;<input type="button" value="上传翻译文档" onclick="actionSubmit('A11010106')"/></td>
		    </tr>
		</table>
        <table width="460px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="100px" align="center">类别</td>
				<td align="center">操作</td>
			</tr>
 			<tr class="bg_tr">
			    <td width="100px" class="td_bg" align="left">素材</td>
				<td class="td_bg" align="center">
				    <input type="button" value="查看" onclick="document.form1.mode.value='1';actionSubmit('A11010102')"/>
			</td>
			</tr>
<!-- 			<tr class="bg_tr"> -->
<!--                 <td width="100px" class="td_bg" align="left">颜色</td> -->
<!--                 <td class="td_bg" align="center"> -->
<!--                     <input type="button" value="查看" onclick="actionSubmit('A11010102?mode=2')"/> -->
<!--                 </td> -->
<!--             </tr> -->
            <tr class="bg_tr">
                <td width="100px" class="td_bg" align="left">详细信息（名称）</td>
                <td class="td_bg" align="center">
                    <input type="button" value="查看" onclick="document.form1.mode.value='3';actionSubmit('A11010102')"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="100px" class="td_bg" align="left">详细信息（内容）</td>
                <td class="td_bg" align="center">
                    <input type="button" value="查看" onclick="document.form1.mode.value='4';actionSubmit('A11010102')"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="100px" class="td_bg" align="left">横纵轴(名称)</td>
                <td class="td_bg" align="center">
                    <input type="button" value="查看" onclick="document.form1.mode.value='5';actionSubmit('A11010102')"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="100px" class="td_bg" align="left">横纵轴（内容）</td>
                <td class="td_bg" align="center">
                    <input type="button" value="查看" onclick="document.form1.mode.value='2';actionSubmit('A11010102')"/>
                </td>
            </tr>
            
<!--             <tr class="bg_tr"> -->
<!--                 <td width="100px" class="td_bg" align="left">尺寸说明</td> -->
<!--                 <td class="td_bg" align="center"> -->
<!--                     <input type="button" value="查看" onclick="actionSubmit('A11010102?mode=6')"/> -->
<!--                 </td> -->
<!--             </tr> -->
            <tr class="bg_tr">
                <td width="100px" class="td_bg" align="left">尺码</td>
                <td class="td_bg" align="center">
                    <input type="button" value="查看" onclick="document.form1.mode.value='7';actionSubmit('A11010102')"/>
                </td>
            </tr>
		</table>
		</div>
		<s:hidden name="viewId" value="V110101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="scrollx"/>
        <s:hidden name="scrolly"/>
	</s:form>
</body>
<script type="text/javascript">
window.onscroll = function(){
	
	document.getElementsByName("scrolly")[0].value = $(document).scrollTop();
	document.getElementsByName("scrollx")[0].value = $(document).scrollLeft();
}
</script>
</html>