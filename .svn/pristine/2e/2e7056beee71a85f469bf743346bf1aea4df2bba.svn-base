<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/commonjs.js"></script>
<script type="text/javascript">
function init(){
	document.getElementById("barcode").focus();
	document.getElementById("barcode").value = "";
	if(document.getElementsByName("disabledFlg")[0].value == "true"){
		document.getElementById("confirmBtn").disabled = true;
	}
	hideDiv();
	var imgs = document.getElementsByName("detailImg");
    if (imgs) {
	       for ( var i = 0; i < imgs.length; i++) {
	           document.getElementsByName("detailImg")[i].src = document.getElementsByName("commodityList["+i+"].picUrl")[0].value;
	           
	       }
	}
	
	
}
function popupDiv() {   
    var div_obj = $("#pop-div");    
    //添加并显示遮罩层   
    $("#mask").show("slow");
    div_obj.show("slow");  
                    
}   
  
function hideDiv() {  
    $("#mask").hide("slow");
  $("#pop-div").hide("slow");   
} 
</script>

<script type="text/javascript">
</script>
<style type="text/css">
<!--
#div1 {
	width: 700px;
	position: relative;
	top: 20px;
	left:30px;
}

#div2 {
    left: 20px;
	top: 40px;
	width:780px;
	position: relative;
}
#div3 {
	height: 545px;
	width:780px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 20px;
}
#div4 {
	width:780px;
	overflow:none;
	position: absolute;
	top: 750px;
}
.pop-box {   
            z-index: 9999; /*这个数值要足够大，才能够显示在最上层*/  
            margin-bottom: 3px;   
            position: absolute;   
            background: #FFF;   
            border:solid 1px #6e8bde;   
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
            filter: Alpha(Opacity=30);
            -moz-opacity:.3;    
            opacity:0.3;   
            z-index:1000
        } 
-->
</style>
</head>
<body onload="init()">
<div id='pop-div' style="left:300px;top:200px;width:200px;height:100px;position:absolute;" class="pop-box">   
            <div class="pop-box-body" >  
                <p>
                <br/><br/>
                处理中...........
                </p>  
            </div>  
        </div>
        <div id='mask' class="mask" style="width:100%;height:100%;"></div>
    <s:form name="form1" theme="simple" action="A06010108">
        <div style="width:700px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg">运单号：<s:label name="waybillNo"/><s:hidden name="waybillNo"/></td>
            </tr>
		</table>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg">商品编号或条形码：<s:textfield id="barcode" size="30" name="barcode"/>
                <s:hidden name="inputHid"/>
                &nbsp;&nbsp;&nbsp;<input type="submit" id="button1" onclick="popupDiv();" value="确认"></td>
            </tr>
        </table>
		</div>
		<div id="div2">
		  <table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg">商品种类：<s:label name="count"/><s:hidden name="count"/>&nbsp;&nbsp;&nbsp;&nbsp;
                商品总个数：<s:label name="sum"/><s:hidden name="sum"/>&nbsp;&nbsp;&nbsp;&nbsp;
                实际签收总个数：<s:label name="getSum"/><s:hidden name="getSum"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="签收所有" onclick="actionSubmit('A06010113')">
                </td>

        </table>
        <table width="760px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
				<td width="130px" align="center">商品编号</td>
				<td width="120px" align="center">图片</td>
				<td width="80px" align="center">发货个数</td>
				<td width="80px" align="center">签收个数</td>
				<td align="center">备注</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="760px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
		    <s:iterator value="commodityList" status="status">
			<tr class="bg_tr">
			    <td width="130px" class="td_bg" align="center"  id="checktd[${status.index}]2"><s:textfield size="15" readOnly="true" name="commodityList[%{#status.index}].commodityId" value="%{commodityId}"/></td>
				<td width="120px" class="td_bg" align="center" id="checktd[${status.index}]3"><a href="${picUrl}" target="_blank"><img height="70px" name="detailImg" border="0"/></a><s:hidden name="commodityList[%{#status.index}].picUrl" value="%{picUrl}"/></td>
				<td width="80px" class="td_bg" align="center" id="checktd[${status.index}]4"><s:textfield size="2" readOnly="true"  cssClass="readonly" name="commodityList[%{#status.index}].quantity" value="%{quantity}"/></td>
				<td width="80px" class="td_bg" align="center" id="checktd[${status.index}]5"><s:textfield size="2" name="commodityList[%{#status.index}].getSu" value="%{getSu}"/></td>
				<td class="td_bg" align="center" id="checktd[${status.index}]6"><s:textarea name="commodityList[%{#status.index}].remarks" value="%{remarks}"/>
				    <s:hidden name="commodityList[%{#status.index}].delFlg" value="%{delFlg}"/>
				    <s:hidden name="commodityList[%{#status.index}].tuika" value="%{tuika}"/>
				</td>
			</tr>
		    </s:iterator>
		</table>
		<table width="760px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
		    <tr>
		        <td>
		            <a href="#" id="firstPage" onclick="actionSubmit('A06010109?page=first')">首页</a>&nbsp;<a href="#" id="lastPage" onclick="actionSubmit('A06010109?page=last')">上一页</a>&nbsp;
		            <a href="#" id="nextPage" onclick="actionSubmit('A06010109?page=next')">下一页</a>&nbsp;<a href="#" id="endPage" onclick="actionSubmit('A06010109?page=end')">尾页</a>
		            &nbsp;&nbsp;&nbsp;&nbsp;第<s:label name="nowPage"/><s:hidden id="nowPage" name="nowPage"/>页&nbsp;/&nbsp;共<s:label name="allPage"/><s:hidden id="allPage" name="allPage"/>页
		            &nbsp;&nbsp;&nbsp;&nbsp;跳转到<s:textfield cssErrorStyle="background:red;" name="goPage" size="1"/>页 <input onclick="actionSubmit('A06010109?page=go')" type="button" value="GO">
		        </td>
		    </tr>
		</table>
		</div>
		<s:hidden name="viewId" value="V060104"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="disabledFlg"/>
		<div id="div4">
		<table width="680px" align="center">
		    <tr>
		    <td align="left"><input type="button" value="返回" onclick="window.close()"></td>
		    <td align="right"><input type="button" id="confirmBtn" value="签收" onclick="popupDiv();actionSubmit('A06010106')"></td>
		    </tr>
		</table>
		</div>
	</s:form>
</body>
</html>