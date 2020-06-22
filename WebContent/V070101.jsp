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
	if(document.getElementsByName("successFlg")[0].value == "1"){
		document.getElementsByName("successFlg")[0].value = "0";
		alert("操作成功");
	}
	hideDiv();
}
function popupDiv() {   
    var div_obj = $("#pop-div");    
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
    setTimeout("hideDiv()",4000);
                    
}   
  
function hideDiv() {   
  $("#mask").hide();   
  $("#pop-div").hide();   
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
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:500px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <h3>STEP1:生成进货单（一个为123mart一个为3eshop，顺序无所谓）</h3>
         <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
        <table>
        <tr>
            <td><input type="file" name="inputPath1" /></td>
            <td><input type="file" name="inputPath3" /></td>
        </tr>
        <tr>
            <td><input type="button" value="生成" onclick="popupDiv();actionSubmit('A07010102')"></td>
        </tr>
        </table>
		</div>
		<div id="div1">
        <h3>STEP2:上传进货单</h3>
        <table>
        <tr>
            <td><input type="file" name="inputPath2" /></td>
        </tr>
        <tr>
            <td><input type="button" value="上传" onclick="popupDiv();actionSubmit('A07010103')"></td>
        </tr>
        </table>
        </div>
        <s:hidden name="successFlg" />
    </s:form>
               <div id='pop-div' style="left:300px;top:200px;width:200px;height:100px;position:absolute;" class="pop-box">   
            <div class="pop-box-body" >  
                <p>
                <br/><br/>
                处理中...........
                </p>  
            </div>  
        </div>
    <div id='mask' class="mask" style="width:100%;height:100%;"></div>
</body>
</html>