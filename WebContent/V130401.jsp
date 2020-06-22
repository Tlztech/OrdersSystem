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
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript">
function init(){
	
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
td {
	color: #1E5494;
	font-size: 12px;
	line-height: 18px;
}
select {
	font-size: 12px;
}
a {
  text-decoration: none
}
#div1 {
	width: 99%;
	position: relative;
	top: 0px;
	margin-left:auto;
	margin-right:auto;
	overflow: auto
}
#div2 {
	width: 99%;
	position: relative;
	top: 0px;
	margin-left:auto;
	margin-right:auto;
	overflow: auto
}

-->
</style>
</head>
<body onload="init();">
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
        <div id="div1">
        <h2>RECEIVER  DETAILS:</h2>
        <table class="table" cellspacing="1" cellpadding="2" width="500px" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="120px">COMPANY NAME:</td>
                <td class="td_bg"><s:textfield name="f130401.rcompanyname" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">ADDRESS:</td>
                <td class="td_bg"><s:textfield name="f130401.raddress" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">CITY:</td>
                <td class="td_bg"><s:textfield name="f130401.rcity" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">PROVINCE:</td>
                <td class="td_bg"><s:textfield name="f130401.rprovince" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">COUNTRY:</td>
                <td class="td_bg"><s:textfield name="f130401.rcountry" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">POSTCODE:</td>
                <td class="td_bg"><s:textfield name="f130401.rpostcode" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">CONTACT NAME:</td>
                <td class="td_bg"><s:textfield name="f130401.rcontactname" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">TELEPHONE:</td>
                <td class="td_bg"><s:textfield name="f130401.rtelephone" size="40"/></td>
            </tr>
        </table>
        <h2>SHIPPER DETAILS:</h2>
        <table class="table" cellspacing="1" cellpadding="2" width="500px" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="120px">COMPANY NAME:</td>
                <td class="td_bg"><s:textfield name="f130401.scompanyname" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">ADDRESS:</td>
                <td class="td_bg"><s:textfield name="f130401.saddress" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">CITY:</td>
                <td class="td_bg"><s:textfield name="f130401.scity" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">PROVINCE:</td>
                <td class="td_bg"><s:textfield name="f130401.sprovince" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">COUNTRY:</td>
                <td class="td_bg"><s:textfield name="f130401.scountry" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">POSTCODE:</td>
                <td class="td_bg"><s:textfield name="f130401.spostcode" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">CONTACT NAME:</td>
                <td class="td_bg"><s:textfield name="f130401.scontactname" size="40"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="120px">TELEPHONE:</td>
                <td class="td_bg"><s:textfield name="f130401.stelephone" size="40"/></td>
            </tr>
        </table>
		</div>
		<br/><br/>
		<table width="100px">
            <tr>
                <td><input type="button" value="保存" onclick="actionSubmit('A13040102')"></td>
            </tr>
        </table>
	</s:form>
</body>

</html>