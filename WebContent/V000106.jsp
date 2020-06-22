<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link href="Images/css1/left_css.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
function showmenu(obj){
    if(obj.value == ">"){
        window.parent.document.getElementById("frame").cols="180,25,*";
        document.getElementById("chgBtn").value = "<";
    }else{
        window.parent.document.getElementById("frame").cols="0,25,*";
        document.getElementById("chgBtn").value = ">";
    }
}
</script>
<style>
.btn {
 BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 11px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7b9ebd 1px solid;width=13px
}
</style>
<body>
<div style="position:absolute;top:45%">
    <input type="button" id="chgBtn" value="<" onclick="showmenu(this)" class="btn"/>
</div>
</body>
</html>
