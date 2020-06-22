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
function init(){

	var flg = document.form1.passFlg.value;
	if(flg == "1"){
		alert("添加成功");
		actionSubmit('A06010101');
	}else if(flg == "2"){
		alert("保存成功");
	}
	hideDiv();
	document.getElementsByName("f060102.commodityIdInput")[0].value="";
	document.getElementsByName("f060102.commodityIdInput")[0].focus();
	var imgs = document.getElementsByName("detailImg");
    listLength = imgs.length;
    if (imgs) {
	       for ( var i = 0; i < imgs.length; i++) {
	           document.getElementsByName("detailImg")[i].src = document.getElementsByName("f060102.commodityList["+i+"].picUrl")[0].value;
	       }
	}

}


//监听Enter键自动提交事件 
function keyboardEvent(event){ 
var keyCode=event.keyCode ? event.keyCode:event.which?event.which:event.charCode;//解决浏览器之间的差异问题 
if(keyCode==13){ 
	pushData();
} 
} 

function pushData(){
	popupDiv();
	$.post("A06010206", {
		shouhinbango:document.getElementsByName("f060102.commodityIdInput")[0].value,
		kosu:document.getElementsByName("f060102.quantityInput")[0].value,
		biko:document.getElementsByName("f060102.remarksInput")[0].value,
	}, function(result) {
		hideDiv();
		
		document.getElementsByName("f060102.commodityIdInput")[0].value="";
		document.getElementsByName("f060102.quantityInput")[0].value="";
		document.getElementsByName("f060102.remarksInput")[0].value="";
		var dataArr = result.split("&%&");
		if(dataArr[0] !="false"){
		    setData(dataArr[0],dataArr[1],dataArr[2],dataArr[3]);
		}else{
			alert(dataArr[1]);
		}
		
		
		document.getElementsByName("f060102.commodityIdInput")[0].focus();
	},"json");
}

function changeKosu(id,row,kosu,shouhinbango){
	if(document.getElementById("lastshohin").value!=""){
    	document.getElementById(document.getElementById("lastshohin").value+"kosutxt").style.backgroundColor="";
    }
	var kosuhtml = "";
	kosuhtml+=("<input type=\"text\" onkeyup=\"changeKosu(this.id,'"+row+"',this.value,'"+shouhinbango+"');\" name=\"f060102.commodityList["+row+"].quantity\" size=\"3\" maxlength=\"5\" value=\""+kosu+"\" id=\""+shouhinbango+"kosutxt\"/>");
    document.getElementById(shouhinbango+"kosutd").innerHTML = kosuhtml;
    document.getElementById(shouhinbango+"kosutxt").style.backgroundColor="lightpink";
    document.getElementById("lastshohin").value=shouhinbango;
}

function changeBiko(id,row,biko,shouhinbango){
	var bikohtml = "";
	bikohtml+=("<input type=\"text\" onkeyup=\"changeKosu(this.id,'"+row+"',this.value,'"+shouhinbango+"');\" name=\"f060102.commodityList["+row+"].remarks\" size=\"40\" maxlength=\"50\" value=\""+biko+"\" id=\""+shouhinbango+"bikotxt\"/>");
    document.getElementById(shouhinbango+"bikotd").innerHTML = bikohtml;
}

function setData(shouhinbango,kosu,biko,picURL){
	var html="";
    var row = "";
    
    if(document.getElementById("lastshohin").value!=""){
    	document.getElementById(document.getElementById("lastshohin").value+"kosutxt").style.backgroundColor="";
    }
    
    
    
    if(document.getElementById(shouhinbango+"idtd") != null){
    	document.getElementById(shouhinbango+"kosutxt").style.backgroundColor="lightpink";
    	var numcount = (parseInt(document.getElementById(shouhinbango+"kosutxt").value) + parseInt(kosu));
    	$("#"+shouhinbango+"kosutxt").val(numcount);
    	document.getElementById(shouhinbango+"bikotxt").value = biko;
    	
    	for (var i = 0; typeof (document.getElementsByName("rowNum")[i]) != "undefined"; i++) {
			if(document.getElementsByName("rowNum")[i].value == shouhinbango){
				row = i;
				break;
			}
		}
    	var kosuhtml = "";
    	var bikohtml = "";
    	kosuhtml+=("<input type=\"text\" onkeyup=\"changeKosu(this.id,'"+row+"',this.value,'"+shouhinbango+"');\" name=\"f060102.commodityList["+row+"].quantity\" size=\"3\" maxlength=\"5\" value=\""+numcount+"\" id=\""+shouhinbango+"kosutxt\"/>");
    	bikohtml+=("<input type=\"text\" onkeyup=\"changeBiko(this.id,'"+row+"',this.value,'"+shouhinbango+"');\" name=\"f060102.commodityList["+row+"].remarks\" size=\"40\" maxlength=\"50\" value=\""+biko+"\" id=\""+shouhinbango+"bikotxt\"/>");
	    document.getElementById(shouhinbango+"kosutd").innerHTML = kosuhtml;
	    document.getElementById(shouhinbango+"bikotd").innerHTML = bikohtml;
	    
	    document.getElementById(shouhinbango+"kosutxt").style.backgroundColor="lightpink";
	    
    	if((row*60-40)>div3.scrollHeight){
    		div3.scrollTop = div3.scrollHeight; 
    	}else{
    		div3.scrollTop = (row*60-40);
    	}
    	
    }else{    
    	
        if(document.getElementsByName("rowNum")!=null){
        	row = document.getElementsByName("rowNum").length;
        }else{
        	row = "0";
        }
    	html+="<tr height=\"60px\" class=\"bg_tr\" >";
	    html+=("<td width=\"120px\" class=\"td_bg\" align=\"center\" id=\""+shouhinbango+"idtd\">"+shouhinbango+"</td>");
	    html+="<td width=\"80px\" class=\"td_bg\" align=\"center\">";
	    html+=("<a href=\""+picURL+"\" target=\"_blank\"><img id=\""+shouhinbango+"imgtd\" height=\"50px\" name=\"detailImg\" width=\"60px\" border=\"0\" src=\""+picURL+"\"/></a>");
	    html+="</td>";
	    html+=("<td width=\"60px\" class=\"td_bg\" align=\"center\" id=\""+shouhinbango+"kosutd\"><input onkeyup=\"changeKosu(this.id,'"+row+"',this.value,'"+shouhinbango+"');\" type=\"text\" name=\"f060102.commodityList["+row+"].quantity\" size=\"3\" maxlength=\"5\" value=\""+kosu+"\" id=\""+shouhinbango+"kosutxt\"/></td>");
	    html+=("<td width=\"340px\" class=\"td_bg\" align=\"center\" id=\""+shouhinbango+"bikotd\"><input onkeyup=\"changeBiko(this.id,'"+row+"',this.value,'"+shouhinbango+"');\" type=\"text\" name=\"f060102.commodityList["+row+"].remarks\" size=\"40\" maxlength=\"50\" value=\""+biko+"\" id=\""+shouhinbango+"bikotxt\"/></td>");
	    html+=("<td class=\"td_bg\" align=\"center\">");
	    html+=("<input type=\"button\" onclick=\"document.form1.rowIndex.value=0;actionSubmit('A06010203')\" value=\"删除\"/>");
	    html+="</td>";
	    html+=("<input type=\"hidden\" name=\"f060102.commodityList["+row+"].commodityId\" value=\""+shouhinbango+"\" id=\"A06010201_f060102_commodityList_"+row+"__commodityId\"/>");
	    html+=("<input type=\"hidden\" name=\"f060102.commodityList["+row+"].picUrl\" value=\""+picURL+"\" id=\"A06010201_f060102_commodityList_"+row+"__picUrl\"/>");
	    html+=("<input type=\"hidden\" name=\"rowNum\" value=\""+shouhinbango+"\"/>");
	    html+="</tr>";
	
	    document.getElementById("maintable").innerHTML += html;
	    div3.scrollTop = div3.scrollHeight; 
	    document.getElementById(shouhinbango+"kosutxt").style.backgroundColor="lightpink";
	    document.getElementById("lastshohin").value=shouhinbango;
    }
}

function popupDiv() {   
    var div_obj = $("#pop-div");    
    //添加并显示遮罩层   
    $("#mask").show();
    div_obj.show();  
                    
}   
  
function hideDiv() {   
  $("#mask").hide("slow"); 
  $("#pop-div").hide("slow");   
}  


</script>
<style type="text/css">
<!--

#div1 {
    width: 650px;
    position: relative;
    top: 20px;
    left:100px;
}

#div2 {
    left: 20px;
    top: 40px;
    width:500px;
    position: relative;
}
#div3 {
    height: 300px;
    width:718px;
    overflow-y:scroll;
    overflow-x:hidden;
    position: relative;
    top: 39px;
    left: 20px;
}
#div4 {
    width:1100px;
    overflow-y:auto;
    overflow-x:hidden;
    position: absolute;
    bottom:25px;
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
    <s:form name="form1" action="A06010201" theme="simple"  enctype="multipart/form-data">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id='mask' class="mask" style="width:100%;height:100%;display:none"></div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
        <table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="130px">发货日&nbsp;&nbsp;(例:20130606)：</td>
                <td class="td_bg">
                    <s:textfield size="20" cssErrorStyle="background:red;" maxlength="8" name="f060102.deliverDay"/>
                </td>
                <td class="td_bg" width="50px">运单号：</td>
                <td class="td_bg">
                    <s:textfield size="20" cssErrorStyle="background:red;" maxlength="20" name="f060102.waybillNo"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="130px">物流公司：</td>
                <td class="td_bg">
                    <s:select name="f060102.logistics" list="#{ '01':'ZCE', '02':'EMS', '03':'其他'}" />
                </td>
                <td class="td_bg" width="50px">运费：</td>
                <td class="td_bg">
                    <s:textfield size="10" maxlength="8" name="f060102.freight"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="130px" >重量：</td>
                <td class="td_bg" colspan="3">
                    <s:textfield size="10" maxlength="4" name="f060102.weight"/>
                </td>
            </tr>
        </table>
        </div>
        <div id="div2">
                <table width="680px">
            <tr>
                <td align="left">
                    <s:file name="filepath"/>
                    <input type="button" value="批量添加" onclick="popupDiv();actionSubmit('A06010204')" style="width:80px;height:25px">
                </td>
            </tr>
        </table>
        <table class="table" width="600px" cellspacing="1" cellpadding="1">
            <tr class="bg_tr" >
                <td width="70px" align="center">商品编号</td>
                <td width="150px" class="td_bg" >
                    <s:textfield name="f060102.commodityIdInput" cssErrorStyle="background:red;" onkeydown="keyboardEvent(event);" size="18" maxlength="20"/>
                </td>
                <td width="50px" align="center">数量</td>
                <td width="60px" class="td_bg" >
                    <s:textfield name="f060102.quantityInput" onkeydown="keyboardEvent(event);" size="3" maxlength="5"/>
                </td>
                <td width="50px" align="center">备注</td>
                <td class="td_bg" >
                    <s:textfield name="f060102.remarksInput" onkeydown="keyboardEvent(event);" size="27" maxlength="50"/>
                </td>
            </tr>
        </table>
        <table width="680px" style="margin-top:-29px">
            <tr>
                <td align="right">
                    <input type="button" onclick="pushData();" id="button1" value="添加" style="width:50px;height:25px">
                </td>
            </tr>
        </table>

        <br/><br/>
        <table width="700px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
            <tr class="bg_tr">
                <td width="120px" align="center">商品编号</td>
                <td width="80px" align="center">图片</td>
                <td width="60px" align="center">数量</td>
                <td width="340px" align="center">备注</td>
                <td align="center">操作</td>
            </tr>
        </table>
        </div>
        <div id="div3">
        <table width="700px" id="maintable" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
            <s:iterator value="f060102.commodityList" status="status1">
            <tr class="bg_tr" height="60px">
                <td width="120px" class="td_bg" align="center" id="<s:property value='commodityId'/>idtd"><s:property value='commodityId'/></td>
                <td width="80px" class="td_bg" align="center">
                    <a href="${picUrl}" target="_blank"><img id="<s:property value='commodityId'/>imgtd" height="50px" name="detailImg" width="60px" border="0"/></a>
                </td>
                <td width="60px" class="td_bg" align="center" id="<s:property value='commodityId'/>kosutd"><s:textfield id="%{commodityId}kosutxt" onkeyup="changeKosu(this.id,'%{#status1.index}',this.value,'%{commodityId}');" name="f060102.commodityList[%{#status1.index}].quantity" value="%{quantity}" size="3" maxlength="5"/></td>
                <td width="340px" class="td_bg" align="center" id="<s:property value='commodityId'/>bikotd"><s:textfield id="%{commodityId}bikotxt" onkeyup="changeBiko(this.id,'%{#status1.index}',this.value,'%{commodityId}');" name="f060102.commodityList[%{#status1.index}].remarks" value="%{remarks}" size="40" maxlength="50"/></td>
                <td class="td_bg" align="center">
                    <input type="button" onclick="document.form1.rowIndex.value=${status1.index};actionSubmit('A06010203')" value="删除"/>
                </td>
                <s:hidden name="f060102.commodityList[%{#status1.index}].commodityId" value="%{commodityId}"/>
                <s:hidden name="f060102.commodityList[%{#status1.index}].picUrl" value="%{picUrl}"/>
                <input type="hidden" name="rowNum" value="<s:property value='commodityId'/>"/>
            </tr>
            </s:iterator>
        </table>
        </div>
        <div id="div4">
        <table width="700px">
            <tr>
                <td class="td_bg" align="left"><input type="button" value="返回" style="width:50px;height:25px" onclick="actionSubmit('A06010101')">
                <input type="button" value="确定" style="width:50px;height:25px" onclick="popupDiv();actionSubmit('A06010202')">
                </td>
                <td class="td_bg" align="right"><input type="button" value="一时保存" style="width:90px;height:25px" onclick="popupDiv();actionSubmit('A06010205')"></td>
            </tr>
        </table>
        </div>
        <s:hidden name="viewId" value="V060101"/>
        <s:hidden name="searchMode"/>
        <s:hidden name="mode"/>
        <s:hidden name="shoriMode"/>
        <s:hidden name="rowIndex"/>
        <s:hidden name="passFlg"/>
        <s:hidden name="alertFlg"/>
        <s:hidden name="f060102.hid_waybillNo"/>
        <input type="hidden" id="lastshohin">
    </s:form>
       <div id='pop-div' style="left:300px;top:200px;width:200px;height:100px;position:absolute;display:none" class="pop-box">   
            <div class="pop-box-body" >  
                <p>
                <br/><br/>
                处理中...........
                </p>  
            </div>  
        </div>
</body>
</html>