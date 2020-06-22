<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>V040101:上架代码生成</title>
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/commonjs.js"></script>
<script type="text/javascript">

	function getPath(obj) {
		if (obj) {
			if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
				obj.select();
				return document.selection.createRange().text;
			} else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
				if (obj.files) {
					return obj.files.item(0).getAsDataURL();
				}
				return obj.value;
			}
			return obj.value;
		}
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
<body>
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:500px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <table>
        <tr>
            <td><input type="file" name="inputPath1"  onchange="document.form1.inputPath.value=getPath(document.form1.inputPath1)"/>
                <input type="hidden" name="inputPath"></td>
                <td>
                <input type="radio" name="exetype" value="1"/>女装
                <input type="radio" name="exetype" value="2"/>手机壳
                <input type="radio" name="exetype" value="3"/>其他
                <input type="radio" name="exetype" value="4"/>xfpy
                <input type="radio" name="exetype" value="5"/>nzpy
                <s:select list="#{ '0':'123mart', '1':'3eshop'}"   name="tenpobetu"/>
                <s:checkbox name="tuiguang"/>123推广3e
                </td>
        </tr>
        <tr>
            <td><input type="button" value="读取Excel" onclick="actionSubmit('A04010102')"></td>
        </tr>
        </table>
		</div>
	</s:form>
</body>
</html>