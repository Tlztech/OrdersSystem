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
function openWin1() {
    var returnVal = openShowModalDialog("615", "600", "P01010101");
    if (returnVal) {
        $.post("getPopUpDate", null, function(result) {
            setDisplay(result);
        }, "json");
    }
}
function setDisplay(result) {
    var object = JSON.parse(result);
    var cateName = object.cateName;
    var cateId = object.cateId;
    $("#categoryName").val(cateName);
    $("#categoryId").val(cateId);
}
function actionConfirm(){
    if(window.confirm("确认删除？")){
        actionSubmit('A03020103');
    }
}

function init() {
    //var imgs = document.getElementsByName("detailImg");
   // if (imgs) {
  //      for ( var i = 0; i < imgs.length; i++) {
        	
  //          document.getElementsByName("detailImg")[i].src = "/OrdersSystem/"
   //                 + document
   //                         .getElementsByName("f180101.commodityList["
   //                                 + i + "].picUrl")[0].value;
   //     }
   // }
    hideDiv();
}
function popupDiv() {   
    var div_obj = $("#pop-div");    
    //添加并显示遮罩层    
    div_obj.show("slow");  
}   
function popupDiv2() {   
    var div_obj = $("#pop-div2");    
    //添加并显示遮罩层   
    div_obj.show("slow");  
}  
  
function hideDiv() {   
  $("#mask").hide();   
  $("#pop-div").hide();   
  $("#pop-div2").hide(); 
} 
function hideDiv2() {   
	  $("#mask").hide("slow");   
	  $("#pop-div").hide("slow");   
	  $("#pop-div2").hide("slow");  
	} 


</script>
<style type="text/css">
<!--

#div1 {
	width: 900px;
	position: relative;
	top: 20px;
	left:100px;
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
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
    
        <div style="width:900px;margin-top: 5px;margin-left: 10px;">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0" >
            <tr class="bg_tr">
                <td class="td_bg" width="80px">平台：</td>
                <td class="td_bg">
                    <s:select list="#{'':'全部','123mart':'123mart','3eshop':'3eshop','citycat':'citycat','trend777':'trend777'}" name="f180101.PlatformSelect"/>
                </td>
                <td class="td_bg" width="80px">店铺：</td>
                <td class="td_bg">
                    <s:select list="#{'':'全部','123mart':'123mart','3eshop':'3eshop','citycat':'citycat','trend777':'trend777'}" name="f180101.ShopSelect"/>
                    
                </td>
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A18010101')" value="查找" style="width:80px;height:25px"/></td>
            </tr>
		</table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
		</div>	
        
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
        <h3>店铺</h3>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="80px">平台：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f180101.Platform"/>
                </td>
                <td class="td_bg" width="80px">店铺ID：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f180101.ShopId"/>
                </td>
                <td class="td_bg" width="80px">店铺名：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f180101.ShopName"/>
                </td>
            </tr>
            <tr class="bg_tr">
            	<td class="td_bg" width="80px">店铺番号：</td>
                <td class="td_bg" width="180px">
                    <s:textfield size="20" maxlength="20" name="f180101.ShopNumber" placeholder="注：订单前六位"/>
                </td>
                <td class="td_bg" width="80px">电话番号：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f180101.PhoneNumber"/>
                </td>
                <td class="td_bg" width="80px">FAX：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f180101.Fax"/>
                </td>
            </tr>
           
  		</table>
  		
		</div>
		<div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
  		 	<tr class="bg_tr">
                <td class="td_bg" width="80px">店铺URL：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f180101.ShopUrl" style="width:450px;"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">店铺地址：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f180101.ShopAddress" style="width:450px;"/>
                </td>
            </tr>
        </table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
        </div>
		
		<div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
        <h3>乐天信息</h3>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="80px">ServiceKey：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="40" name="f180101.ServiceKey" style="width:700px"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">LicenseKey：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="40" name="f180101.LicenseKey" style="width:700px"/>
                </td>
            </tr>
		</table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
		</div>
		
		<div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
        <h3>Yahoo信息</h3>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
			<tr class="bg_tr">
                <td class="td_bg" width="80px">ApplicationID：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="40" name="f180101.ApplicationID" style="width:700px"/>
                </td>
            </tr>
			<tr class="bg_tr">
                <td class="td_bg" width="80px">AccessToken：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="40" name="f180101.AccessToken" style="width:700px" readonly="true"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">RefreshToken：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="40" name="f180101.RefreshToken" style="width:700px" readonly="true"/>
                </td>
            </tr>
            <tr class="bg_tr">
	            <td class="td_bg" width="80px">登录时日：</td>
                <td class="td_bg" width="180px">
                    <s:textfield size="20" maxlength="20" name="f180101.LoginTime" readonly="true"/>
                </td>
            </tr>
            <!--  
            <tr class="bg_tr">
                <td class="td_bg" width="80px">平台：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f180101.commodityId"/>
                </td>
                <td class="td_bg" width="80px">店铺ID：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" id="categoryName" name="f180101.categoryName" cssClass="readonly" readOnly="true"/>&nbsp;&nbsp;<input type="button" onclick="openWin1()" value="参照"/>
                </td>
                <td class="td_bg" width="80px">店铺名：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" id="categoryName" name="f180101.categoryName" cssClass="readonly" readOnly="true"/>&nbsp;&nbsp;<input type="button" onclick="openWin1()" value="参照"/>
                </td>
            </tr>
            -->
		</table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
		</div>
		
		<div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A18010101')" value="追加" style="width:100px;height:45px"/></td>
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A18010101')" value="更新" style="width:100px;height:45px"/></td>
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A18010101')" value="删除" style="width:100px;height:45px"/></td>
            </tr>
            
		</table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
		</div>
		
		<s:hidden name="viewId" value="V180101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		
		<s:hidden name="f180101.hid_PlatformSelect"/>
		<s:hidden name="f180101.hid_ShopSelect"/>
		<s:hidden name="f180101.hid_Platform"/>
		<s:hidden name="f180101.hid_ShopId"/>
		<s:hidden name="f180101.hid_ShopName"/>
		<s:hidden name="f180101.hid_ShopNumber"/>
		<s:hidden name="f180101.hid_PhoneNumber"/>
		<s:hidden name="f180101.hid_Fax"/>
		<s:hidden name="f180101.hid_ShopUrl"/>
		<s:hidden name="f180101.hid_ShopAddress"/>
		<s:hidden name="f180101.hid_ServiceKey"/>
		<s:hidden name="f180101.hid_LicenseKey"/>
		<s:hidden name="f180101.hid_ApplicationID"/>
		<s:hidden name="f180101.hid_AccessToken"/>
		<s:hidden name="f180101.hid_RefreshToken"/>
		<s:hidden name="f180101.hid_LoginTime"/>
		
	</s:form>
</body>
</html>