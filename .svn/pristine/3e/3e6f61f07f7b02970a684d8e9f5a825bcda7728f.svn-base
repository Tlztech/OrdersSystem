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
function showAddedFile(file1,file2,file3,file4,file5){
	
	document.getElementById("added1").href="javascript: openShowModalDialog('1024', '700', '/OrdersSystem/"+file1+"')";
	document.getElementById("added1").innerHTML = file1;
	document.getElementById("added2").href="/OrdersSystem/"+file2;
	document.getElementById("added2").innerHTML = file2;
	document.getElementById("added3").href="/OrdersSystem/"+file3;
	document.getElementById("added3").innerHTML = file3;
	document.getElementById("added4").href="/OrdersSystem/"+file4;
	document.getElementById("added4").innerHTML = file4;
	document.getElementById("added5").href="/OrdersSystem/"+file5;
	document.getElementById("added5").innerHTML = file5;
	popupDiv2();
	
}
function init(){
	getFenleiList();
}
function pushDate(){
	var file1 = document.getElementsByName("f150102.addedFile1")[0].value;
	var file2 = document.getElementsByName("f150102.addedFile2")[0].value;
	var file3 = document.getElementsByName("f150102.addedFile3")[0].value;
	var file4 = document.getElementsByName("f150102.addedFile4")[0].value;
	var file5 = document.getElementsByName("f150102.addedFile5")[0].value;


	actionSubmit('A15010203?kachoshi1='+file1+"&"+"kachoshi2="+file2+"&"+"kachoshi3="+file3+"&"+"kachoshi4="+file4+"&"+"kachoshi5="+file5);
}
function getFenleiList(){
	var selVal = document.getElementsByName("f150102.leibie_hid")[0].value;
	document.getElementsByName("f150102.leibie")[0].options.length = 0; 
	var selObj = document.getElementsByName("f150102.leibie")[0];
	$.post("getJiaoyiFenleiList", 
			{
			area:document.getElementsByName("f150102.addArea")[0].value,
			kubun:document.getElementsByName("f150102.kubun")[0].value,
			}, 
			function(result) {
				var typeList = result.split("&");
				selObj.options.add(new Option("全部",""));
				for(var i=0;i<typeList.length;i++){
					var valList = typeList[i].split("%");
					selObj.options.add(new Option(valList[1],valList[0]));
				}	
				document.getElementsByName("f150102.leibie")[0].value  = selVal;
		    }
		,"json");
}

function setLeibiehid(){
	document.getElementsByName("f150102.leibie_hid")[0].value = document.getElementsByName("f150102.leibie")[0].value;
}

function clearData(){
	$("#jiaoyiTr").hide();
	
	$("input[name^='f150102.addJiaoyiri']").val("");
	$("input[name^='f150102.addJine']").val("");
	document.getElementsByName("f150102.addKubun")[0].value="";
	$("textarea[name^='f150102.addBiko']").val("");
	$("input[name^='f150102.addedFile1']").val("");
	$("input[name^='f150102.addedFile2']").val("");
	$("input[name^='f150102.addedFile3']").val("");
	$("input[name^='f150102.addedFile4']").val("");
	$("input[name^='f150102.addedFile5']").val("");
	
}

function checkFields(){

	if(document.getElementsByName("f150102.addJiaoyiri")[0].value == ""){
		alert("请输入交易日");
		return false;
	}else if(document.getElementsByName("f150102.addJine")[0].value == ""){
		alert("请输入金额");
		return false;
	}else if(document.getElementsByName("f150102.addKubun")[0].value == ""){
		alert("请选择进出帐区分");
		return false;
	}else if(document.getElementsByName("f150102.addJiaoyiKubun")[0].value == ""){
		alert("请选择交易区分");
		return false;
	}else if(document.getElementsByName("f150102.addedFile1")[0].value == ""){
		alert("请上传附件");
		return false;
	}else if(!IsNum(document.getElementsByName("f150102.addJine")[0].value)){
		alert("金额输入有误");
		return false;
	}else{
		return true;
	}
}

function IsNum(s){
   if (s!=null && s!=""){
	   return !isNaN(s);
   }
   return false;
}

function getJiaoyifenleiList(){
	document.getElementsByName("f150102.addJiaoyiKubun")[0].options.length = 0; 
	var selObj = document.getElementsByName("f150102.addJiaoyiKubun")[0];
	$.post("getJiaoyiFenleiList", 
		{
		area:document.getElementsByName("f150102.addArea")[0].value,
		kubun:document.getElementsByName("f150102.addKubun")[0].value
		}, 
		function(result) {
			var typeList = result.split("&");
			
			for(var i=0;i<typeList.length;i++){
				var valList = typeList[i].split("%");
				selObj.options.add(new Option(valList[1],valList[0]));
			}
			
			$("#jiaoyiTr").show("slow");
			
	    }
	,"json");
}


function popupDiv() { 

    var div_obj = $("#pop-div");    
	div_obj.css("top",($(window).height()-420)/2);
	div_obj.css("left",($(window).width()-400)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
    $("#rowIndex").val(index); 
}   
function popupDiv2() { 

    var div_obj = $("#pop-div2");    
	div_obj.css("top",($(window).height()-420)/2);
	div_obj.css("left",($(window).width()-400)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
    $("#rowIndex").val(index); 
}   
function hideDiv() {   
	  $("#mask").hide();   
	  $("#pop-div").hide(); 
	  $("#pop-div2").hide(); 
	  clearVal();
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
	width:900px;
	position: relative;
}
#div3 {
	height: 390px;
	width:980px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 20px;
}
#div4 {
	height: 40px;
	width:980px;
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
<div id='mask' class="mask" style="width:100%;height:100%;display:none"></div>
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
    
        <div style="width:800px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>

        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="100px">交易日</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f150102.dayStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f150102.dayEnd"/>
                </td>
                <td class="td_bg" width="100px">区分</td>
                <td class="td_bg">
                    <s:select list="#{'':'全部', '0':'进账', '1':'出账'}" name="f150102.kubun" onchange="getFenleiList();"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="100px">备注</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f150102.biko"/>
                </td>
                <td class="td_bg" width="100px">类别</td>
                <td class="td_bg">
                    <s:select list="#{'':' '}" name="f150102.leibie" onchange="setLeibiehid();"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="100px">出账金额</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f150102.outStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f150102.outEnd"/>
                </td>
                <td class="td_bg" width="100px">进账金额</td>
                <td class="td_bg">
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f150102.inStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f150102.intEnd"/>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="right"><input type="button" onclick="actionSubmit('A15010202')" value="检索" style="width:50px;height:25px"/></td>
            </tr>
		</table>
		</div>
		<div id="div2">
		<table width="900px">
            <tr>
                <td>検索結果：<s:label name="f150102.resultCount" />件<s:hidden name="f150102.resultCount" /></td>
                <td align="right"><input type="button" onclick="clearData();popupDiv();" value="添加条目"></td>
            </tr>
        </table>
        <table width="950px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="100px" align="center">交易日</td>
			    <td width="100px" align="center">进账金额<s:if test ='f150102.addArea == 0'>(円)</s:if><s:else>(元)</s:else></td>
				<td width="100px" align="center">出账金额<s:if test ='f150102.addArea == 0'>(円)</s:if><s:else>(元)</s:else></td>
				<td width="80px" align="center">区分</td>
				<td width="100px" align="center">余额<s:if test ='f150102.addArea == 0'>(円)</s:if><s:else>(元)</s:else></td>
				<td width="100px" align="center">类别</td>
				<td width="180px" align="center">备注</td>
				<td  align="center">附件</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="950px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f150102.meisaiList" status="status">
			<tr class="bg_tr" height="40px">
			    <td width="100px" class="td_bg" id="td1[${status.index}]" align="center"><s:property value='jiaoyiri'/><s:hidden name="f150102.meisaiList[%{#status.index}].jiaoyiri" value="%{jiaoyiri}"/></td>
			    <td width="100px" class="td_bg" id="td2[${status.index}]" align="right"><s:property value='incoming'/><s:hidden name="f150102.meisaiList[%{#status.index}].incoming" value="%{incoming}"/></td>
			    <td width="100px" class="td_bg" id="td3[${status.index}]" align="right"><s:property value='out'/><s:hidden name="f150102.meisaiList[%{#status.index}].out" value="%{out}"/></td>
			    <td width="80px" class="td_bg" id="td4[${status.index}]" align="center"><s:property value='kubun'/><s:hidden name="f150102.meisaiList[%{#status.index}].kubun" value="%{kubun}"/></td>
			    <td width="100px" class="td_bg" id="td5[${status.index}]" align="right"><s:property value='balance'/><s:hidden name="f150102.meisaiList[%{#status.index}].balance" value="%{balance}"/></td>
			    <td width="100px" class="td_bg" id="td6[${status.index}]" align="center"><s:property value='leibie'/><s:hidden name="f150102.meisaiList[%{#status.index}].leibie" value="%{leibie}"/></td>
			    <td width="180px" class="td_bg" id="td7[${status.index}]" align="center"><s:property value='biko'/><s:hidden name="f150102.meisaiList[%{#status.index}].biko" value="%{biko}"/></td>
				<td class="td_bg" align="center" id="td8[${status.index}]">
				    <input type="button"  value="查看附件" onclick="showAddedFile('<s:property value='addedFile1'/>','<s:property value='addedFile2'/>','<s:property value='addedFile3'/>','<s:property value='addedFile4'/>','<s:property value='addedFile5'/>');"/>
				</td>
				<s:hidden name="f150102.meisaiList[%{#status.index}].addedFile1" value="%{addedFile1}"/>
				<s:hidden name="f150102.meisaiList[%{#status.index}].addedFile1" value="%{addedFile2}"/>
				<s:hidden name="f150102.meisaiList[%{#status.index}].addedFile1" value="%{addedFile3}"/>
				<s:hidden name="f150102.meisaiList[%{#status.index}].addedFile1" value="%{addedFile4}"/>
				<s:hidden name="f150102.meisaiList[%{#status.index}].addedFile1" value="%{addedFile5}"/>
			</tr>
		    </s:iterator>
		</table>
		</div>
		<div id="div4">
		<table width="950px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
		    <tr>
		        <td>
		            <a href="#" id="firstPage" onclick="actionSubmit('A03020106?page=first')">首页</a>&nbsp;<a href="#" id="lastPage" onclick="actionSubmit('A03020106?page=last')">上一页</a>&nbsp;
		            <a href="#" id="nextPage" onclick="actionSubmit('A03020106?page=next')">下一页</a>&nbsp;<a href="#" id="endPage" onclick="actionSubmit('A03020106?page=end')">尾页</a>
		            &nbsp;&nbsp;&nbsp;&nbsp;第<s:label name="nowPage"/><s:hidden id="nowPage" name="nowPage"/>页&nbsp;/&nbsp;共<s:label name="allPage"/><s:hidden id="allPage" name="allPage"/>页
		            &nbsp;&nbsp;&nbsp;&nbsp;跳转到<s:textfield cssErrorStyle="background:red;" name="goPage" size="1"/>页 <input onclick="actionSubmit('A03020106?page=go')" type="button" value="GO">
		        </td>
		    </tr>
		</table>
		</div>
		<div id='pop-div' style="width:400px;height:420px;display:none;position: fixed;" class="pop-box">   
            <div class="pop-box-body" style="height:350px">
            <table align="center" width="395px">
                <tr>
                    <td>交易日<font color="red">*</font></td>
                    <td><s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f150102.addJiaoyiri"/></td>
                </tr>
                <tr>
                    <td>进出帐区分<font color="red">*</font></td>
                    <td><s:select list="#{'':'请选择','0':'进账', '1':'出账'}" name="f150102.addKubun" onchange="getJiaoyifenleiList();"/></td>
                </tr>
                <tr style="display:none" id="jiaoyiTr">
                    <td>交易区分<font color="red">*</font></td>
                    <td><s:select list="#{'':'  '}" name="f150102.addJiaoyiKubun"/></td>
                </tr>
                <tr>
                    <td>金额<font color="red">*</font></td>
                    <td><s:textfield size="10" maxlength="10" style="text-align:right" name="f150102.addJine"/><s:if test ='area = 0'>円</s:if><s:else>元</s:else></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td><s:textarea name="f150102.addBiko" style="width:300px;"/></td>
                </tr>
                <tr height="35px">
                    <td>附件1<font color="red">*</font></td>
                    <td><s:file name="f150102.addedFile1" /></td>
                </tr>
                <tr height="35px">
                    <td>附件2</td>
                    <td><s:file name="f150102.addedFile2" /></td>
                </tr>
                <tr height="35px">
                    <td>附件3</td>
                    <td><s:file name="f150102.addedFile3" /></td>
                </tr>
                <tr height="35px">
                    <td>附件4</td>
                    <td><s:file name="f150102.addedFile4" /></td>
                </tr>
                <tr height="35px">
                    <td>附件5</td>
                    <td><s:file name="f150102.addedFile5" /></td>
                </tr>
                <s:hidden name="f150102.addArea"/>
            </table>
            </div>
            <table align="center" width="95%">
                <tr>
                    <td width="25%" align="right"><input type="button" onclick="if(checkFields()){pushDate();}" value="確定" /></td>
                    <td width="25%"></td>
                    <td width="25%"></td>
                    <td width="25%"><input type="button" onclick="clearData();hideDiv();" value="返回" /></td>
                </tr>
            </table>
        </div>
        <div id='pop-div2' style="width:400px;height:420px;display:none;position: fixed;" class="pop-box">   
            <div class="pop-box-body" style="height:350px">
            <table align="center" width="395px">
                <tr>
                    <td>附件1：</td>
                    <td><a href="" id="added1"></a></td>
                </tr>
                <tr>
                    <td>附件2：</td>
                    <td><a href="" id="added2"></a></td>
                </tr>
                <tr>
                    <td>附件3：</td>
                    <td><a href="" id="added3"></a></td>
                </tr>
                <tr>
                    <td>附件4：</td>
                    <td><a href="" id="added4"></a></td>
                </tr>
                <tr>
                    <td>附件5：</td>
                    <td><a href="" id="added5"></a></td>
                </tr>
            </table>
            </div>
            <table align="center" width="95%">
                <tr>
                    <td width="25%"><input type="button" onclick="hideDiv();" value="返回" /></td>
                </tr>
            </table>
        </div>
		<s:hidden name="viewId" value="V030201"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="accountId"/>
		<s:hidden name="area"/>
		<s:hidden name="f150102.leibie_hid"/>
	</s:form>
</body>
</html>