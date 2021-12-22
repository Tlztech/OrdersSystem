<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
//    var company = document.getElementsByName("f180101.companyId");
//    if (company) {
// 	   for ( var i = 0; i < imgs.length; i++) {
//    			document.getElementsByName("f180101.companyId").options[i]
// 	   }
//    }
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
            	<td class="td_bg" width="80px">公司：</td>
                <td class="td_bg">
                    <select id="f180101.CompanyIdSelect" name="f180101.CompanyIdSelect">
                    	<option value="0">请选择</option>
                        <c:forEach items="${f180101.companyList}" var="company" >  
                              <option value="${company.companyId}"> ${company.companyName} </option>
                        </c:forEach>  
                    </select> 
                </td>
                <td class="td_bg" width="80px">平台：</td>
                <td class="td_bg">
                    <select id="f180101.PlatformSelect" name="f180101.PlatformSelect">
                    	<option value="0">请选择</option>
                        <c:forEach items="${f180101.siteList}" var="site" >  
                              <option value="${site}"> ${site} </option>  
                        </c:forEach>  
                    </select> 
                </td>
                <td class="td_bg" width="80px">店铺：</td>
                <td class="td_bg">
                	<select id="f180101.ShopSelect" name="f180101.ShopSelect">
                    	<option value="0">请选择</option>
                        <c:forEach items="${f180101.shopList}" var="shopID" >  
                              <option value="${shopID}"> ${shopID} </option>  
                        </c:forEach>  
                    </select> 
                </td>
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A18010102')" value="查找" style="width:80px;height:25px"/></td>
            </tr>
		</table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
		</div>	
        
        <div id="div1">
        <h3>店铺</h3>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
			<tr class="bg_tr">
				<td class="td_bg" width="80px">公司：</td>
                <td class="td_bg">
                    <select id="f180101.companyId" name="f180101.companyId">
                    	<option value="0">请选择</option>
                        <c:forEach items="${f180101.companyList}" var="company" >
                        	<c:if test="${company.companyId==f180101.companyId}">
                              <option value="${company.companyId}" selected="selected"> ${company.companyName} </option>
                            </c:if>
                            <c:if test="${company.companyId!=f180101.companyId}">
                            	<option value="${company.companyId}"> ${company.companyName} </option>
                            </c:if>
                        </c:forEach>  
                    </select>
                </td>
                <td height="30" colspan="2"><s:fielderror fieldName="error"/></td>
			</tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">平台：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="50" name="f180101.platform"/>
                </td>
                <td class="td_bg" width="80px">店铺ID：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="50" name="f180101.shopId"/>
                </td>
                <td class="td_bg" width="80px">店铺名：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="250" name="f180101.shopName"/>
                </td>
            </tr>
            <tr class="bg_tr">
            	<td class="td_bg" width="80px">店铺番号：</td>
                <td class="td_bg" width="180px">
                    <s:textfield size="20" maxlength="20" name="f180101.shopNumber" placeholder="注：订单前六位"/>
                </td>
                <td class="td_bg" width="80px">电话番号：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f180101.phoneNumber"/>
                </td>
                <td class="td_bg" width="80px">FAX：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f180101.fax"/>
                </td>
            </tr>
           
  		</table>
  		
		
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
  		 	<tr class="bg_tr">
                <td class="td_bg" width="80px">店铺URL：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="300" name="f180101.shopUrl" style="width:450px;"/>
                </td>
                <td class="td_bg" width="95px">郵便番号：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="8" name="f180101.shopPost"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">店铺地址：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="300" name="f180101.shopAddress" style="width:450px;"/>
                </td>
            </tr>
        </table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
        </div>
		
		<div id="div1">
        <h3>乐天信息</h3>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="80px">ServiceKey：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="200" name="f180101.serviceKey" style="width:700px"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">LicenseKey：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="200" name="f180101.licenseKey" style="width:700px"/>
                </td>
            </tr>
		</table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
		</div>
		
		<div id="div1">
        <h3>Yahoo信息</h3>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
			<tr class="bg_tr">
                <td class="td_bg" width="80px">ApplicationID：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="100" name="f180101.applicationID" style="width:700px"/>
                </td>
            </tr>
			<tr class="bg_tr">
                <td class="td_bg" width="80px">AccessToken：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="5000" name="f180101.accessToken" style="width:700px" readonly="true"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">RefreshToken：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="200" name="f180101.refreshToken" style="width:700px" readonly="true"/>
                </td>
            </tr>
            <tr class="bg_tr">
	            <td class="td_bg" width="80px">登录时日：</td>
                <td class="td_bg" width="180px">
                    <s:textfield size="20" maxlength="50" name="f180101.loginTime" readonly="true"/>
                </td>
            </tr>
		</table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
		</div>
		
		<div id="div1">
        <h3>Amazon信息</h3>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
			<tr class="bg_tr">
                <td class="td_bg" width="80px">AccessKeyId：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="100" name="f180101.awsAccessKeyId" style="width:700px"/>
                </td>
            </tr>
			<tr class="bg_tr">
                <td class="td_bg" width="80px">SecretKey：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="100" name="f180101.awsSecretKey" style="width:700px"/>
                </td>
            </tr>
			<tr class="bg_tr">
                <td class="td_bg" width="80px">ARN：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="100" name="f180101.awsARN" style="width:700px"/>
                </td>
            </tr>
			<tr class="bg_tr">
                <td class="td_bg" width="80px">ClientId：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="5000" name="f180101.awsClientId" style="width:700px" />
                </td>
            </tr>
			<tr class="bg_tr">
                <td class="td_bg" width="80px">ClientSecret：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="5000" name="f180101.awsClientSecret" style="width:700px" />
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">RefreshToken：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="200" name="f180101.awsRefreshToken" style="width:700px" />
                </td>
            </tr>
		</table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
		</div>
		
		<div id="div1">
        <h3>AU信息</h3>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
			<tr class="bg_tr">
                <td class="td_bg" width="80px">ApiKey：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="100" name="f180101.auApiKey" style="width:700px"/>
                </td>
            </tr>
		</table>
		<div style="width:811px;height:20px;margin-top: 10px;">
            <hr>
        </div>
		</div>
		
		<div id="div1">
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A18010103')" value="追加" style="width:100px;height:45px"/></td>
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A18010104')" value="更新" style="width:100px;height:45px"/></td>
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A18010105')" value="删除" style="width:100px;height:45px"/></td>
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
		
	</s:form>
</body>
</html>