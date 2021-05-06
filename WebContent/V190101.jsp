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

function init() {
	
}
function check(form) {
	if(form.elements["f190101.newpassword"].value == ""){
		alert("新しいパスワードを空にすることはできません");
		return false;
	}
	if(form.elements["f190101.newpasswordconfirmation"].value == ""){
		alert("新しいパスワード確認用を空にすることはできません");
		return false;
	}
	if(form.elements["f190101.newpassword"].value != form.elements["f190101.newpasswordconfirmation"].value){
		alert("2つのパスワードは同じではありません");
		return false;
	}
	return true;
}
</script>
<style type="text/css">
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
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="105px">新しいパスワード：</td>
                <td class="td_bg">
                    <s:password showPassword="true" size="20" maxlength="50" name="f190101.newpassword"/>
                </td>
            </tr>
            <tr class="bg_tr">
            	<td class="td_bg" width="150px">新しいパスワード確認用：</td>
                <td class="td_bg">
                    <s:password showPassword="true" size="20" maxlength="50" name="f190101.newpasswordconfirmation"/>
                </td>
            </tr>
  		</table>
		</div>
		
		<div id="div1">
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg"><input type="button" onclick="if(check(form)){actionSubmit('A19010102')}" value="変更" style="width:100px;height:45px"/></td>
            </tr>
            
		</table>
		<div style="width:900px;height:20px;margin-top: 10px;;margin-left: 10px;">
            <hr>
        </div>
		</div>
		
		<s:hidden name="viewId" value="V190101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		
	</s:form>
</body>
</html>