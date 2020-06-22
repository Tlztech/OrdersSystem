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
function doAction(actionid){
	var div_obj = $("#pop-div");    
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
    var logKey = "";
    logKey = _getRandomString(10);
    actionSubmit(actionid+'?logKey='+logKey);
    setInterval(function(){
        getLog(logKey);
    },500);
}


function hideDiv() {   
  $("#mask").hide();   
  $("#pop-div").hide();   
} 

function init(){
	hideDiv();
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
		        <td>
		        货号：<s:textfield name="f160101.shohinbango"/><s:select list="#{'coverforefront':'coverforefront','xandw':'xandw'}" name="f160101.shop"/>
		        <input type="button" value="同步库存" onclick="actionSubmit('A16010102')">
		        </td>
		    </tr>
		    <tr>
		        <td>
		        订单编号：<s:textfield name="f160101.chumonbango"/>
		        <input type="button" value="删除" onclick="actionSubmit('A16010103')">
		        </td>
		    </tr>
		</table>
        <s:select list="#{'coverforefront':'coverforefront','xandw':'xandw'}" name="shop2"/>
        <input type="button" value="下载商品" onclick="doAction('A16010104')">
        <input type="button" value="改网页" onclick="doAction('A16010105')">
        <input type="button" value="处理订单" onclick="doAction('A16010106')"><br/>
        <input type="file" name="inputPath" /><input type="button" value="获取最新在库情报" onclick="actionSubmit('A16010107')">
        </br><input type="button" value="qoo10在库情报" onclick="actionSubmit('A16010108')">
		</div>
		<s:hidden name="viewId" value="V160101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="scrollx"/>
        <s:hidden name="scrolly"/>
        <div id='pop-div' style="left:350px;top:100px;width:450px;height:260px;position:absolute;" class="pop-box">   
            <div class="pop-box-body" >
            <table>
                <tr>
                    <td align="center"><textarea id="logArea" style="width:400px;height:200px" readOnly></textarea></td>
                </tr>
                <tr>
                    <td align="center"><input type="button" onclick="hideDiv();" value="关闭"></td>
                </tr>
            </table>
            </div>
        </div>
	</s:form>
</body>

</html>