<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>V030203:商品明细</title>
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/commonjs.js"></script>
<script type="text/javascript">
function clear(){
	if("颜色，尺寸，规格等" == document.getElementsByName("f030303.describe")[0].value){
		document.getElementsByName("f030303.describe")[0].value = "";
	}
}
function setImagePreview() { 
    var docObj=document.getElementById("doc"); 
    var imgObjPreview=document.getElementById("preview"); 
    if(docObj.files && docObj.files[0]){ 
    //火狐下，直接设img属性 
    imgObjPreview.style.display = 'block'; 
    //imgObjPreview.src = docObj.files[0].getAsDataURL(); 
    //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式 
    imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]); 
    }else{ 
    //IE下，使用滤镜 
    docObj.select(); 
    docObj.blur();
    var imgSrc = document.selection.createRange().text; 
    var localImagId = document.getElementById("localImag"); 
    //必须设置初始大小 
    //图片异常的捕捉，防止用户修改后缀来伪造图片 
    localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)"; 
    localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc; 

    imgObjPreview.style.display = 'none'; 
    document.selection.empty(); 
    } 
    return true; 
    } 
    
function check() {
    if (document.getElementsByName("f030303.detailNo")[0].value == "") {
        alert("请输入明细编号");
        return false;
    }
    if (document.getElementsByName("f030303.describe")[0].value == "" || "颜色，尺寸，规格等" == document.getElementsByName("f030303.describe")[0].value) {
        alert("请输入商品描述");
        return false;
    }

clear();
    return true;
}

function init() {
   // if (document.getElementsByName("f030303.picUrl")[0].value != '') {
   //     document.getElementById("preview").src = "/OrdersSystem/"
   //             + document.getElementsByName("f030303.picUrl")[0].value;
   // }
}
</script>
<style type="text/css">
<!--
#div1 {
    width: 580px;
    position: relative;
    top: 20px;
    left:20px;
}
#div4 {
    width:580px;
    overflow:none;
    position: absolute;
    top: 550px;
}
#div5 {
    width:162px;
    width:162px;
    position: absolute;
    top: 70px;
    left: 400px;
}
-->
</style>
</head>
<body onload="init();">
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:500px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
        <table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="90px">明细编号<span style="font-color:red">(*)</span>：</td>
                <td class="td_bg">
                    <s:textfield cssErrorStyle="background:red;" name="f030303.detailNo" maxlength="8" size="10"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">图片：</td>
                 <td class="td_bg">
                    <s:file disabled="true" name="f030303.uploadFile" id="doc" onchange="javascript:setImagePreview();"></s:file>
                    <s:hidden name="f030303.picUrl" />
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">参考进价：</td>
                <td class="td_bg">
                    <s:textfield name="f030303.priceIn" size="15" maxlength="12"/>&nbsp;&nbsp;元
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">参考售价：</td>
                <td class="td_bg">
                    <s:textfield name="f030303.rePrice" size="15" maxlength="12"/>&nbsp;&nbsp;日元
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">上海库存数：</td>
                <td class="td_bg">
                    <s:textfield name="f030303.stockSh" size="8" maxlength="6"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">日本库存数：</td>
                <td class="td_bg">
                    <s:textfield name="f030303.stockJp" size="8" maxlength="6"/>
                </td>
            </tr>
            <tr class="bg_tr" style="display:none">
                <td class="td_bg">挂起库存数：</td>
                <td class="td_bg">
                    <s:textfield name="f030303.stockHandup" size="8" maxlength="6"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">条形码：</td>
                <td class="td_bg">
                    <s:textfield name="f030303.barCode" size="25" maxlength="6"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">商品描述<span style="font-color:red">(*)</span>：</td>
                <td class="td_bg">
                    <s:textarea name="f030303.describe" cssErrorStyle="background:red;" cssStyle="width:250px;height:100px;"></s:textarea>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg">备注：</td>
                <td class="td_bg">
                    <s:textarea name="f030303.remarks" cssStyle="width:250px;height:100px"></s:textarea>
                </td>
            </tr>
        </table>
        </div>
		<div id="div4">
		<table width="480px" align="center">
		    <tr>
		    <td align="left"><input type="button" value="返回" onclick="window.close()"></td>
		    <td align="right"><input type="button" id="confirm" value="确定" onclick="if(check()){actionSubmit('A03020301');}"></td>
		    </tr>
		</table>
		</div>
		<div id="localImag" style="position: absolute;left:420px;top:100px;width:150px;height:150px"><img width="140px" src="${f030203.picUrl}"/>
		</div>
		<input type="hidden" name="mode">
	</s:form>
</body>
</html>