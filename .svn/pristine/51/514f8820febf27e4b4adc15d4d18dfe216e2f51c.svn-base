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
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var listLength;
function init() {
   var imgs = document.getElementsByName("detailImg");
   listLength = imgs.length;
   if (imgs) {
       for ( var i = 0; i < imgs.length; i++) {
           document.getElementsByName("detailImg")[i].src = document.getElementsByName("f070201.nyukaList["+i+"].picUrl")[0].value;
       }
   }
    
    for ( var i = 0; typeof (document
            .getElementsByName("f070201.nyukaList[" + i
                    + "].checkFlg")[0]) != "undefined"; i++){
    	
    }
    setDisplay();
    hideDiv();
}

function showImg(imgurl){
	
	popupDiv2();
	document.getElementById("showimgurl").src = "";
	document.getElementById("showimgurl").src = imgurl.replace("200x200","650x650");
}
function popupDiv2() {   
    var div_obj = $("#pop-div2"); 
	div_obj.css("top",($(window).height()-700)/2);
	div_obj.css("left",($(window).width()-700)/2);
    //添加并显示遮罩层   
   // $("#mask").show("slow");  
    div_obj.show("slow");  
}   

function checkIt(name){
    var checkflg = document.getElementsByName(name)[0].checked;
    var index = name.substring(name.indexOf("[")+1);
    index = index.substring(0,index.indexOf("]"));
    if(checkflg){
        document.getElementById("checktd["+index+"]1").className="";  
        document.getElementById("checktd["+index+"]2").className="";  
        document.getElementById("checktd["+index+"]3").className="";  
        document.getElementById("checktd["+index+"]4").className=""; 
        document.getElementById("checktd["+index+"]5").className=""; 
        document.getElementById("checktd["+index+"]6").className=""; 
        document.getElementById("checktd["+index+"]7").className=""; 
        document.getElementById("checktd["+index+"]8").className=""; 
        document.getElementById("checktd["+index+"]9").className=""; 
        document.getElementById("checktd["+index+"]10").className=""; 
        document.getElementById("checktd["+index+"]11").className=""; 
        document.getElementById("checktd["+index+"]12").className=""; 
    }else{
        document.getElementById("checktd["+index+"]1").className="td_bg";  
        document.getElementById("checktd["+index+"]2").className="td_bg";  
        document.getElementById("checktd["+index+"]3").className="td_bg";  
        document.getElementById("checktd["+index+"]4").className="td_bg"; 
        document.getElementById("checktd["+index+"]5").className="td_bg"; 
        document.getElementById("checktd["+index+"]6").className="td_bg"; 
        document.getElementById("checktd["+index+"]7").className="td_bg"; 
        document.getElementById("checktd["+index+"]8").className="td_bg"; 
        document.getElementById("checktd["+index+"]9").className="td_bg"; 
        document.getElementById("checktd["+index+"]10").className="td_bg"; 
        document.getElementById("checktd["+index+"]11").className="td_bg"; 
        document.getElementById("checktd["+index+"]12").className="td_bg"; 
    }
}

function checkAll(){
    for ( var i = 0; typeof (document
            .getElementsByName("f070201.nyukaList[" + i
                    + "].checkFlg")[0]) != "undefined"; i++) {
        if (!document.getElementsByName("f070201.nyukaList[" + i
                + "].checkFlg")[0].disabled){
        document.getElementsByName("f070201.nyukaList[" + i
                + "].checkFlg")[0].checked = true;
        document.getElementById("checktd["+i+"]1").className="";  
        document.getElementById("checktd["+i+"]2").className="";  
        document.getElementById("checktd["+i+"]3").className="";  
        document.getElementById("checktd["+i+"]4").className=""; 
        document.getElementById("checktd["+i+"]5").className=""; 
        document.getElementById("checktd["+i+"]6").className=""; 
        document.getElementById("checktd["+i+"]7").className=""; 
        document.getElementById("checktd["+i+"]8").className=""; 
        document.getElementById("checktd["+i+"]9").className=""; 
        document.getElementById("checktd["+i+"]10").className=""; 
        document.getElementById("checktd["+i+"]11").className=""; 
        document.getElementById("checktd["+i+"]12").className=""; 
        }
    }
    
}

	function print() {
		var index = document.form1.rowIndex.value;
		var commid = document.getElementsByName("f070201.nyukaList[" + index
				+ "].commodityId")[0].value;
		var suryo = document.getElementsByName("f070201.nyukaList[" + index
				+ "].getsu")[0].value;
		var shubeId = document.getElementsByName("f070201.nyukaList[" + index
                + "].shubetuId")[0].value;
		if (suryo == 0) {
			hideDiv();
			alert("数量不能为0");
			return;
		}

		$.post("A07020103", {
			commodity_id : commid,
			kosu : suryo,
			shubetuId : shubeId
		}, function(result) {
			isSuccessPrint(result);
		}, "json");
	}

	function isSuccessPrint(result) {
		hideDiv();
		if (result != "true") {
			alert("操作失败");
		}else{
			var index = document.form1.rowIndex.value;
			document.getElementsByName("f070201.nyukaList[" + index + "].printStatus")[0].value = "1";	
			setDisplay();
		}
	}
	
	function isSuccessGet(result) {
		hideDiv();
        if (result != "true") {
            alert("操作失败");
        }else{
        	var index = document.form1.rowIndex.value;
            document.getElementsByName("f070201.nyukaList[" + index + "].commodityStatus")[0].value = "1";  
            if(document.getElementsByName("printFlg")[0].checked){
            	document.getElementsByName("f070201.nyukaList[" + index + "].printStatus")[0].value = "1";
            }
            setDisplay();
            alert("操作成功");
        }
        document.getElementsByName("button1")[0].disabled = false;
    }
	
	function isSuccessGetAll(result) {
		hideDiv();
        if (result != "true") {
            alert("操作失败");
        }else{
        	for ( var i = 0; i < listLength; i++) {
                if (document.getElementsByName("f070201.nyukaList[" + i
                        + "].checkFlg")[0].checked == true) {
                	 document.getElementsByName("f070201.nyukaList[" + i + "].commodityStatus")[0].value = "1";  
                	 document.getElementsByName("f070201.nyukaList[" + i
                             + "].checkFlg")[0].checked = false;
                }
                if(document.getElementsByName("printFlg")[0].checked){
                    document.getElementsByName("f070201.nyukaList[" + i + "].printStatus")[0].value = "1";
                }
            };
            setDisplay();
            alert("操作成功");
        }
        document.getElementsByName("button6")[0].disabled = false;
    }
	
	function isSuccessPrintAll(result) {
		hideDiv();
        if (result != "true") {
            alert("操作失败");
        }else{
            for ( var i = 0; i < listLength; i++) {
                if (document.getElementsByName("f070201.nyukaList[" + i
                        + "].checkFlg")[0].checked == true) {
                     document.getElementsByName("f070201.nyukaList[" + i + "].printStatus")[0].value = "1";  
                }
            };
            setDisplay();
        }
    }
	
	
	function isSuccessUpdate(result) {
		hideDiv();
        if (result != "true") {
            alert("操作失败");
        }else{
            alert("操作成功");
        }
    }
	
	   
	    
	function isSuccessProblem(result) {
		hideDiv();
        if (result != "true") {
            alert("操作失败");
        }else{
        	var index = document.form1.rowIndex.value;
            alert("操作成功");
            document.getElementsByName("f070201.nyukaList[" + index + "].commodityStatus")[0].value = "2";
            setDisplay();
        }
    }
	
	function isSuccessOk(result) {
		hideDiv();
        if (result != "true") {
            alert("操作失败");
        }else{
        	var index = document.form1.rowIndex.value;
            alert("操作成功");
            document.getElementsByName("f070201.nyukaList[" + index + "].commodityStatus")[0].value = "3";
            setDisplay();
        }
    }
	
	function setDisplay(){
		for ( var i = 0; i < listLength; i++) {
            if (document.getElementsByName("f070201.nyukaList[" + i
                    + "].commodityStatus")[0].value == "0") {
                 document.getElementById("checktd[" + i + "]6").innerHTML = "未签收"; 
                 document.getElementsByName("button1")[i].style.display = "";
                 document.getElementsByName("button2")[i].style.display = "";
                 document.getElementsByName("button3")[i].style.display = "";
                 document.getElementsByName("button4")[i].style.display = "none";
                 document.getElementsByName("button5")[i].style.display = "none";
                 document.getElementsByName("f070201.nyukaList[" + i + "].getsu")[0].readOnly = false;
                 document.getElementsByName("f070201.nyukaList[" + i + "].getsu")[0].className = "";
                 document.getElementsByName("f070201.nyukaList[" + i + "].getsu")[0].value = document.getElementsByName("f070201.nyukaList[" + i + "].nyukasu")[0].value;
                 
            }else if (document.getElementsByName("f070201.nyukaList[" + i
                    + "].commodityStatus")[0].value == "1") {
            	document.getElementById("checktd[" + i + "]6").innerHTML = "已签收";  
            	document.getElementsByName("button1")[i].style.display = "none";
                document.getElementsByName("button2")[i].style.display = "";
                document.getElementsByName("button3")[i].style.display = "";
                document.getElementsByName("button4")[i].style.display = "";
                document.getElementsByName("button5")[i].style.display = "none";
                document.getElementsByName("f070201.nyukaList[" + i + "].getsu")[0].readOnly = true;
                document.getElementsByName("f070201.nyukaList[" + i + "].getsu")[0].className = "readonly";
                document.getElementById("checktd["+i+"]1").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]2").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]3").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]4").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]5").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]6").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]7").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]8").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]9").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]10").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]11").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]12").style.background ="#E4F1FA"; 
                document.getElementsByName("f070201.nyukaList["+i+"].checkFlg")[0].disabled = true;
            }else if (document.getElementsByName("f070201.nyukaList[" + i
                    + "].commodityStatus")[0].value == "2") {
            	document.getElementById("checktd[" + i + "]6").innerHTML = "问题件"; 
            	document.getElementsByName("button1")[i].style.display = "none";
                document.getElementsByName("button2")[i].style.display = "";
                document.getElementsByName("button3")[i].style.display = "";
                document.getElementsByName("button4")[i].style.display = "none";
                document.getElementsByName("button5")[i].style.display = "";
                document.getElementsByName("f070201.nyukaList[" + i + "].getsu")[0].readOnly = true;
                document.getElementsByName("f070201.nyukaList[" + i + "].getsu")[0].className = "readonly";
                document.getElementById("checktd["+i+"]1").style.background ="yellow";  
                document.getElementById("checktd["+i+"]2").style.background ="yellow"; 
                document.getElementById("checktd["+i+"]3").style.background ="yellow";  
                document.getElementById("checktd["+i+"]4").style.background ="yellow"; 
                document.getElementById("checktd["+i+"]5").style.background ="yellow"; 
                document.getElementById("checktd["+i+"]6").style.background ="yellow"; 
                document.getElementById("checktd["+i+"]7").style.background ="yellow"; 
                document.getElementById("checktd["+i+"]8").style.background ="yellow"; 
                document.getElementById("checktd["+i+"]9").style.background ="yellow"; 
                document.getElementById("checktd["+i+"]10").style.background ="yellow";  
                document.getElementById("checktd["+i+"]11").style.background ="yellow"; 
                document.getElementById("checktd["+i+"]12").style.background ="yellow"; 
            }else if (document.getElementsByName("f070201.nyukaList[" + i
                    + "].commodityStatus")[0].value == "3") {
            	document.getElementById("checktd[" + i + "]6").innerHTML = "已解决";
            	document.getElementsByName("button1")[i].style.display = "none";
                document.getElementsByName("button2")[i].style.display = "";
                document.getElementsByName("button3")[i].style.display = "";
                document.getElementsByName("button4")[i].style.display = "none";
                document.getElementsByName("button5")[i].style.display = "none";
                document.getElementsByName("f070201.nyukaList[" + i + "].getsu")[0].readOnly = true;
                document.getElementsByName("f070201.nyukaList[" + i + "].getsu")[0].className = "readonly";
                document.getElementById("checktd["+i+"]1").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]2").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]3").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]4").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]5").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]6").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]7").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]8").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]9").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]10").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]11").style.background ="#E4F1FA";  
                document.getElementById("checktd["+i+"]12").style.background ="#E4F1FA";
            }
            
            if (document.getElementsByName("f070201.nyukaList[" + i
                    + "].printStatus")[0].value == "0") {
                 document.getElementById("checktd[" + i + "]7").innerHTML = "未打印";  
            }else if (document.getElementsByName("f070201.nyukaList[" + i
                    + "].printStatus")[0].value == "1") {
                document.getElementById("checktd[" + i + "]7").innerHTML = "已打印";  
            }
        };
		
	};
	
	function setProblem(){
	      var index = document.form1.rowIndex.value;
	        var commid = document.getElementsByName("f070201.nyukaList[" + index
	                + "].commodityId")[0].value;
	        var suryo = document.getElementsByName("f070201.nyukaList[" + index
	                + "].getsu")[0].value;
	        var shubeId = document.getElementsByName("f070201.nyukaList[" + index
	                + "].shubetuId")[0].value;
	        var remarks = document.getElementsByName("f070201.nyukaList[" + index
	                + "].remarks")[0].value;

	        $.post("A07020106", {
	            commodity_id : commid,
	            kosu : suryo,
	            shubetuId : shubeId,
	            remark : remarks
	        }, function(result) {
	            isSuccessProblem(result);
	        }, "json");	
	}
	
	function setOk(){
        var index = document.form1.rowIndex.value;
        var commid = document.getElementsByName("f070201.nyukaList[" + index
                + "].commodityId")[0].value;
        var suryo = document.getElementsByName("f070201.nyukaList[" + index
                + "].getsu")[0].value;
        var shubeId = document.getElementsByName("f070201.nyukaList[" + index
                + "].shubetuId")[0].value;
        var remarks = document.getElementsByName("f070201.nyukaList[" + index
                + "].remarks")[0].value;

        $.post("A07020107", {
            commodity_id : commid,
            kosu : suryo,
            shubetuId : shubeId,
            remark : remarks
        }, function(result) {
            isSuccessOk(result);
        }, "json"); 
		
	}
	
	function getChecked(){
		var commArr = "";
        var suryoArr = "";
        var shubetuArr = "";
        var remarksArr = "";
        for ( var i = 0; i < listLength; i++) {
            if (document.getElementsByName("f070201.nyukaList[" + i
                    + "].checkFlg")[0].checked == true) {
                document.form1.rowIndex.value = i;
                var index = document.form1.rowIndex.value;
                var commid = document.getElementsByName("f070201.nyukaList[" + index
                        + "].commodityId")[0].value;
                var jinhuosu = document.getElementsByName("f070201.nyukaList[" + index
                        + "].nyukasu")[0].value;
                var suryo = document.getElementsByName("f070201.nyukaList[" + index
                        + "].getsu")[0].value;
                var shubeId = document.getElementsByName("f070201.nyukaList[" + index
                        + "].shubetuId")[0].value;
                var remarks = document.getElementsByName("f070201.nyukaList[" + index
                        + "].remarks")[0].value;
                if(remarks == ""){
                	remarks = " ";
                }
                if(jinhuosu != suryo){
                    if(!confirm(commid+"收货数与进货数不同，确认吗？")){
                        return false;
                    }
                }
                if(suryo == 0){
                if(!confirm(commid+"数量为0，确认吗")){
                    return false;
                }}
                commArr = commArr + commid + "&";
                suryoArr = suryoArr + suryo + "&";
                shubetuArr = shubetuArr + shubeId + "&";
                remarksArr = remarksArr + remarks + "&";
            }
        }
        if(commArr != ""){
        	document.getElementsByName("button6")[0].disabled = true;
            $.post("A07020104", {
                commodity_id : commArr,
                kosu : suryoArr,
                shubetuId : shubetuArr,
                printFlg : document.getElementsByName("printFlg")[0].checked,
                remark : remarksArr
            }, function(result) {
            	isSuccessGetAll(result);
            }, "json");
        }else{
        	hideDiv();
            alert("请选择");
        }
		
	}
	
	function getItem() {
        var index = document.form1.rowIndex.value;
        var commid = document.getElementsByName("f070201.nyukaList[" + index
                + "].commodityId")[0].value;
        var jinhuosu = document.getElementsByName("f070201.nyukaList[" + index
                + "].nyukasu")[0].value;
        var suryo = document.getElementsByName("f070201.nyukaList[" + index
                + "].getsu")[0].value;
        var shubeId = document.getElementsByName("f070201.nyukaList[" + index
                + "].shubetuId")[0].value;
        var remarks = document.getElementsByName("f070201.nyukaList[" + index
                + "].remarks")[0].value;
        if(jinhuosu != suryo){
        	if(!confirm("收货数与进货数不同，确认吗？")){
        		return false;
        	}
        }
        if (suryo == 0) {
            if(!confirm("数量为0，确认吗")){
                return false;
            }
            
        }
        document.getElementsByName("button1")[0].disabled = true;
        $.post("A07020104", {
            commodity_id : commid,
            kosu : suryo,
            shubetuId : shubeId,
            printFlg : document.getElementsByName("printFlg")[0].checked,
            remark : remarks
        }, function(result) {
            isSuccessGet(result);
        }, "json");
    }
	
	function updateCommodity(){
		var index = document.form1.rowIndex.value;
        var commid = document.getElementsByName("f070201.nyukaList[" + index
                + "].commodityId")[0].value;
        var suryo = document.getElementsByName("f070201.nyukaList[" + index
                + "].getsu")[0].value;
        var shubeId = document.getElementsByName("f070201.nyukaList[" + index
                + "].shubetuId")[0].value;
        var remarks = document.getElementsByName("f070201.nyukaList[" + index
                + "].remarks")[0].value;

        $.post("A07020105", {
            commodity_id : commid,
            kosu : suryo,
            shubetuId : shubeId,
            remark : remarks
        }, function(result) {
            isSuccessUpdate(result);
        }, "json");		
	}
	
	
	function printChecked() {
		var commArr = "";
        var suryoArr = "";
        var shubetuArr = "";
		for ( var i = 0; i < listLength; i++) {
			if (document.getElementsByName("f070201.nyukaList[" + i
					+ "].checkFlg")[0].checked == true) {
				document.form1.rowIndex.value = i;
				var index = document.form1.rowIndex.value;
		        var commid = document.getElementsByName("f070201.nyukaList[" + index
		                + "].commodityId")[0].value;
		        var suryo = document.getElementsByName("f070201.nyukaList[" + index
		                + "].getsu")[0].value;
		        var shubeId = document.getElementsByName("f070201.nyukaList[" + index
		                + "].shubetuId")[0].value;
		        if (suryo == 0) {
		        	hideDiv();
		            alert(commid + "数量不能为0");
		            return;
		        }
		        commArr = commArr + commid + "&";
		        suryoArr = suryoArr + suryo + "&";
		        shubetuArr = shubetuArr + shubeId + "&";
			}
		}
		if(commArr != ""){
            $.post("A07020103", {
                commodity_id : commArr,
                kosu : suryoArr,
                shubetuId : shubetuArr
            }, function(result) {
                isSuccessPrintAll(result);
            }, "json");
        }else{
        	hideDiv();
            alert("请选择");
        }
	}
	function popupDiv() {   
	    var div_obj = $("#pop-div");    
	    //添加并显示遮罩层   
	    //$("#mask").show("slow");
	    //div_obj.show("slow");  
	                    
	}   
	  
	function hideDiv() {  
		$("#mask").hide();
	  $("#pop-div").hide();   
	  $("#pop-div2").hide(); 
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
	width:1200px;
	position: relative;
}
#div3 {
	height: 400px;
	width:1220px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 20px;
}
#div4 {
	height: 50px;
	width:1220px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 20px;
}
  .pop-box {   
            z-index: 9999; /*这个数值要足够大，才能够显示在最上层*/  
            margin-bottom: 3px;   
            position: absolute;   
            background: #FFF;   
            border:solid 1px #6e8bde;   
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
            filter: Alpha(Opacity=30);
            -moz-opacity:.3;    
            opacity:0.3;   
            z-index:1000
        } 

-->
</style>
</head>
<body onload="init();">
<div id='pop-div' style="left:300px;top:200px;width:200px;height:100px;position:absolute;" class="pop-box">   
            <div class="pop-box-body" >  
                <p>
                <br/><br/>
                处理中...........
                </p>  
            </div>  
        </div>
    <s:form name="form1" theme="simple"  enctype="multipart/form-data">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id='mask' class="mask" style="width:100%;height:100%;"></div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="80px">商品编号：</td>
                <td class="td_bg" width="210px">
                    <s:textfield size="20" maxlength="20" name="f070201.commodityId"/>
                </td>
                <td class="td_bg" width="80px">进货日期：</td>
                <td class="td_bg">
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="8" name="f070201.nyukabiStart" /> ～
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="8" name="f070201.nyukabiEnd" />
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">中文名：</td>
                <td class="td_bg" width="210px" colspan="3">
                    <s:textfield size="20" maxlength="40" name="f070201.chineseName"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">签收状态：</td>
                <td class="td_bg" width="210px">
                    <s:select list="#{ '1':'全部', '2':'已签收', '3':'未签收', '4':'问题件' , '5':'已解决'}"   name="f070201.commodityStatus"/>
                </td>
                <td class="td_bg" width="80px">打印状态：</td>
                <td class="td_bg">
                    <s:select list="#{ '1':'全部', '2':'已打印','3':'未打印'}"   name="f070201.printStatus"/>
                </td>
            </tr>
            
		</table>
		<table  width="700px" cellspacing="0" cellpadding="0">
		    <tr>
		        <td><input type="file" name="inputPath2" /><input type="button" value="上传进货单" onclick="popupDiv();actionSubmit('A07010103')">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="actionSubmit('A10010107');" value="下载进货单"/></td>
                <td align="right"><input type="button" onclick="popupDiv();actionSubmit('A07020102')" value="检索" style="width:50px;height:25px"/></td>
		    </tr>
		</table>
		</div>
		<div id="div2">
		
		<table width="1100px">
            <tr>
                <td width="600px">检索结果：<s:label name="f070201.resultCount" />件</td>
                <td align="right">
                                              签收完成后打印标签<s:checkbox name="printFlg"/>
                    <input type="button" name="button6" onclick="popupDiv();getChecked()" value="批量签收">
                    <input type="button" onclick="popupDiv();printChecked()" value="批量打印">
                            
            </tr>
        </table>
        
        <table width="1200px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="40px" align="center"><a href="#" onclick="checkAll()">全选</a></td>
			    <td width="100px" align="center">进货日期</td>
			    <td width="100px" align="center">商品编号</td>
			    <td width="100px" align="center">描述</td>
			    <td width="100px" align="center">图片</td>
				<td width="180px" align="center">中文名</td>
				<td width="60px" align="center">签收状态</td>
				<td width="60px" align="center">打印状态</td>
				<td width="60px" align="center">进货数量</td>
				<td width="60px" align="center">收货数量</td>
				<td width="100px" align="center">备注</td>
				<td align="center">操作</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="1200px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f070201.nyukaList" status="status">
			<tr class="bg_tr">
			    <td width="40px" class="td_bg" align="center" id="checktd[${status.index}]1"><s:checkbox onclick="checkIt(this.name)" name="f070201.nyukaList[%{#status.index}].checkFlg"/></td>
			    <td width="100px" class="td_bg" align="center" id="checktd[${status.index}]2"><s:property value='nyukabi'/><s:hidden name="f070201.nyukaList[%{#status.index}].nyukabi" value="%{nyukabi}"/>
			    <td width="100px" class="td_bg" align="center" id="checktd[${status.index}]3"><s:if test="commodityId.contains('-S5')"><font color="red"></s:if><s:property value='commodityId'/><s:hidden name="f070201.nyukaList[%{#status.index}].commodityId" value="%{commodityId}"/>
			    <s:hidden name="f070201.nyukaList[%{#status.index}].shubetuId" value="%{shubetuId}"/></td>
			    <td width="100px" class="td_bg" align="center" id="checktd[${status.index}]12"><s:if test="commodityId.contains('-S5')"><font color="red"></s:if><s:property value='describe'/><s:hidden name="f070201.nyukaList[%{#status.index}].describe" value="%{describe}"/>
			    <td width="100px" class="td_bg" align="center" id="checktd[${status.index}]4"><a href="javascript:showImg('<s:property value='picUrl'/>')"><img name="detailImg" width="70px" height="70px"></a><s:hidden name="f070201.nyukaList[%{#status.index}].picUrl" value="%{picUrl}"/></td>
				<td width="180px" class="td_bg" align="center" id="checktd[${status.index}]5"><s:property value='chineseName'/><s:hidden name="f070201.nyukaList[%{#status.index}].chineseName" value="%{chineseName}"/></td>
				<td width="60px" class="td_bg" align="center" id="checktd[${status.index}]6"><s:property value='commodityStatusName'/><s:hidden name="f070201.nyukaList[%{#status.index}].commodityStatusName" value="%{commodityStatusName}"/></td>
				<td width="60px" class="td_bg" align="center" id="checktd[${status.index}]7"><s:property value='printStatusName'/><s:hidden name="f070201.nyukaList[%{#status.index}].printStatusName" value="%{printStatusName}"/></td>
				<td width="60px" class="td_bg" align="center" id="checktd[${status.index}]8"><s:property value='nyukasu'/><s:hidden name="f070201.nyukaList[%{#status.index}].nyukasu" value="%{nyukasu}"/></td>
				<td width="60px" class="td_bg" align="center" id="checktd[${status.index}]9"><s:textfield readOnly="true" cssClass="readonly" size="2" name="f070201.nyukaList[%{#status.index}].getsu" value="%{getsu}"/></td>
				<td width="100px" class="td_bg" align="center" id="checktd[${status.index}]10"><s:textarea style="width:90px;height:40px" name="f070201.nyukaList[%{#status.index}].remarks" value="%{remarks}"/></td>
				<td class="td_bg" align="center" id="checktd[${status.index}]11">
				    <s:hidden name="f070201.nyukaList[%{#status.index}].commodityStatus" value="%{commodityStatus}"/>
				    <s:hidden name="f070201.nyukaList[%{#status.index}].printStatus" value="%{printStatus}"/>
				    <input type="button" name="button1" style="width:70px" onclick="popupDiv();document.form1.rowIndex.value=${status.index};getItem();" value="签　收"/>&nbsp;&nbsp;&nbsp;
				    <input type="button" name="button2" style="width:70px" onclick="popupDiv();document.form1.rowIndex.value=${status.index};updateCommodity();" value="更　新"/>&nbsp;&nbsp;&nbsp;
				    <input type="button" name="button3" style="width:70px" onclick="popupDiv();document.form1.rowIndex.value=${status.index};print();" value="打印标签"/>&nbsp;&nbsp;&nbsp;<br>
				    <input type="button" name="button4" style="width:70px" onclick="popupDiv();document.form1.rowIndex.value=${status.index};setProblem();" value="设为问题件"/>&nbsp;&nbsp;&nbsp;
				    <input type="button" name="button5" style="width:70px" onclick="popupDiv();document.form1.rowIndex.value=${status.index};setOk();" value="解　决"/>&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		    </s:iterator>
		</table>
		
		</div>
<!-- 		<div id="div4"> -->
<!-- 		<table width="1200px" class="table" cellspacing="1" cellpadding="1" align="left" border="0"> -->
<!-- 		    <tr> -->
<!-- 		        <td> -->
<!-- 		            <a href="#" id="firstPage" onclick="actionSubmit('A07020108?page=first')">首页</a>&nbsp;<a href="#" id="lastPage" onclick="actionSubmit('A07020108?page=last')">上一页</a>&nbsp; -->
<!-- 		            <a href="#" id="nextPage" onclick="actionSubmit('A07020108?page=next')">下一页</a>&nbsp;<a href="#" id="endPage" onclick="actionSubmit('A07020108?page=end')">尾页</a> -->
<%-- 		            &nbsp;&nbsp;&nbsp;&nbsp;第<s:label name="nowPage"/><s:hidden id="nowPage" name="nowPage"/>页&nbsp;/&nbsp;共<s:label name="allPage"/><s:hidden id="allPage" name="allPage"/>页 --%>
<%-- 		            &nbsp;&nbsp;&nbsp;&nbsp;跳转到<s:textfield cssErrorStyle="background:red;" name="goPage" size="1"/>页 <input onclick="actionSubmit('A07020108?page=go')" type="button" value="GO"> --%>
<!-- 		        </td> -->
<!-- 		    </tr> -->
<!-- 		</table> -->
<!-- 		</div> -->
		<s:hidden name="viewId" value="V070201"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
	</s:form>
	      <div id='pop-div2' style="left:30%;width:700px;height:700px;" class="pop-box">   
            <div class="pop-box-body" >
            <table align="center">
                <tr height="80px">
                    <td align="center" colspan="2">
                        <img src="" id="showimgurl"/>
                    </td>
                </tr> 
            </table>
            <table align="center" width="200px">
                <tr>
                    <td align="center"><input type="button" onclick="hideDiv();" value="关闭" /></td>
                </tr>
            </table>
            </div>
        </div>
</body>
</html>