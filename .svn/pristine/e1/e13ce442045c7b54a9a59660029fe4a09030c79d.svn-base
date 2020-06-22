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
<script type="text/javascript" src="js/popup.js"></script>  
<script type="text/javascript" src="js/popupclass.js"></script>  
<style type="text/css">
<!--
body {
	margin-left: 4px;
	margin-top: 0px;
	margin-right: 0px;
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 0px;
	PADDING-TOP: 0px;
}
#div2 {
    top: 5%;
    width:270px;
    height:34%;
    position:fixed;
    overflow-y: scroll;
    overflow-x: hidden;
}
#div6 {
    top: 39%;
    width:290px;
    height:55%;
    position:fixed;
    overflow-x:auto;
    overflow-y:hidden;
}
#div7 {
    top: 94%;
    width:290px;
    height:55%;
    position:fixed;
}
#div3 {
    left: 300px;
    top: 5%;
    width:540px;
    height:86%;
    position: fixed;
    overflow-x:auto;
    overflow-y:scroll;
}
#div5 {
    left: 300px;
    top: 94%;
    width:540px;
    height:20px;
    position: fixed;

}
#div4 {
    left: 1270px;
    top: 35px;
    width:420px;
    height:94%;
    position: fixed;
    overflow-x:auto;
    overflow-y:auto;
}
#div8 {
    left: 840px;
    top: 35px;
    width:420px;
    height:94%;
    position: fixed;
    overflow-x:auto;
    overflow-y:scroll;
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
<script type="text/javascript">
function popupDiv() {   
    var div_obj = $("#pop-div");    
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
}   
function hideDiv() {   
	  $("#mask").hide();   
	  $("#pop-div").hide(); 
	  clearVal();
	} 
    
    function closeYulan(){
    	$(window.parent.document).find("#yulan").attr("src","");
    	$("#yulanDiv").hide("slow");
    }
    function openYulan(){
    	//$("#yulanDiv").show("slow");  
    	var url = document.getElementsByName("detail.shiireurl")[0].value;
    	//$(window.parent.document).find("#yulan").attr("src",url); 
    	window.open (url, 'newwindow', 'height=600, width=1000, top=0,left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')  


    }
	
	function checkThisItem(obj){
		clearVal();
		document.getElementsByName("plxg.input_selectedItem")[0].value = obj.name;
		$("input[name^='plxg.']").css({"background":""});
		obj.style.background = "#1E90FF";
		if(obj.name.indexOf("_str")>0){
			hideAllPlxg();
			$("#typeSelect").show();
			$("#addAtFirst").show();
			$("#addAtEnd").show();
		}else if(obj.name == "plxg.merubin_sel"){
			hideAllPlxg();
			$("#merubintaio").show();
		}else if(obj.name == "plxg.soryobetsu_sel"){
			hideAllPlxg();
			$("#soryosentaku").show();
		}else if(obj.name == "plxg.shohizeibetsu_sel"){
			hideAllPlxg();
			$("#shohizeisentaku").show();
		}else if(obj.name == "plxg.merubinsoryomuryokyanpen_chk"){
			hideAllPlxg();
			$("#xpjxfby").show();
		}else if(obj.name == "plxg.hogofirumu_chk"){
			hideAllPlxg();
			$("#xpjssjtmfcs").show();
		}else if(obj.name == "plxg.sizepic_chk"){
			hideAllPlxg();
			$("#nzccsyt").show();
		}else if(obj.name == "plxg.pycommonpic_chk"){
			hideAllPlxg();
			$("#pygtt").show();
		}else if(obj.name == "plxg.xfpycommonpic_chk"){
			hideAllPlxg();
			$("#xfpygtt").show();
		}else if(obj.name == "plxg.osusume1_chk"){
			hideAllPlxg();
			$("#osusume1").show();
		}else if(obj.name == "plxg.osusume2_chk"){
			hideAllPlxg();
			$("#osusume2").show();
		}else if(obj.name == "plxg.osusume3_chk"){
			hideAllPlxg();
			$("#osusume3").show();
		}else if(obj.name == "plxg.nouki_chk"){
			hideAllPlxg();
			$("#nouki").show();
		}
	}
	
	function clearVal(){
		document.getElementsByName("plxg.input_selectedItem")[0].value = "";
		$('input[name="plxg.radio_typeSelect"]')[0].checked = true;
		document.getElementsByName("plxg.input_addAtFirst")[0].value = "";
		document.getElementsByName("plxg.input_addAtEnd")[0].value = "";
		document.getElementsByName("plxg.input_replaceFrom")[0].value = "";
		document.getElementsByName("plxg.input_replaceTo")[0].value = "";
		document.getElementsByName("plxg.input_strDelete")[0].value = "";
		document.getElementsByName("plxg.input_resetStr")[0].value = "";
		
		
		document.getElementsByName("plxg.sel_merubintaio")[0].value = "0";
		document.getElementsByName("plxg.sel_soryosentaku")[0].value = "0";
		document.getElementsByName("plxg.sel_shohizeisentaku")[0].value = "0";
		document.getElementsByName("plxg.chk_xpjxfby")[0].value = "0";
		document.getElementsByName("plxg.chk_xpjssjtmfcs")[0].value = "0";
		document.getElementsByName("plxg.chk_nzccsyt")[0].value = "0";
		document.getElementsByName("plxg.chk_pygtt")[0].value = "0";
		document.getElementsByName("plxg.chk_xfpygtt")[0].value = "0";
		document.getElementsByName("plxg.chk_osusume1")[0].value = "0";
		document.getElementsByName("plxg.chk_osusume2")[0].value = "0";
		document.getElementsByName("plxg.chk_nouki")[0].value = "0";
		document.getElementsByName("plxg.chk_osusume3")[0].value = "0";
		
	}
	
	
	function hideAllPlxg(){
		$("#typeSelect").hide();
		$("#typeSelect").hide();
		$("#addAtFirst").hide();
		$("#addAtEnd").hide();
		$("#replaceFrom").hide();
		$("#replaceTo").hide();
		$("#strDelete").hide();
		$("#resetStr").hide();
		$("#merubintaio").hide();
		$("#soryosentaku").hide();
		$("#shohizeisentaku").hide();
		$("#xpjxfby").hide();
		$("#xpjssjtmfcs").hide();
		$("#nzccsyt").hide();
		$("#pygtt").hide();
		$("#xfpygtt").hide();
		$("#osusume1").hide();
		$("#osusume2").hide();
		$("#osusume3").hide();
		$("#nouki").hide();
	}
	
	function selectType(value){
		if("0" == value){
			$("#addAtFirst").show();
			$("#addAtEnd").show();
			$("#replaceFrom").hide();
			$("#replaceTo").hide();
			$("#strDelete").hide();
			$("#resetStr").hide();
		}else if("1" == value){
			$("#addAtFirst").hide();
			$("#addAtEnd").hide();
			$("#replaceFrom").show();
			$("#replaceTo").show();
			$("#strDelete").hide();
			$("#resetStr").hide();
		}else if("2" == value){
			$("#addAtFirst").hide();
			$("#addAtEnd").hide();
			$("#replaceFrom").hide();
			$("#replaceTo").hide();
			$("#strDelete").show();
			$("#resetStr").hide();
		}else if("3" == value){
			$("#addAtFirst").hide();
			$("#addAtEnd").hide();
			$("#replaceFrom").hide();
			$("#replaceTo").hide();
			$("#strDelete").hide();
			$("#resetStr").show();
		}
	}
	
    function setDisplay(){
    	var disType = document.getElementsByName("f120201.displayFlg")[0].value;
    	if('0' == disType){
    		document.getElementById("kihonjoho").style.display="";
    		//document.getElementById("keywordandprice").style.display="none";
    		document.getElementById("yokoandshitaga").style.display="none";
    		document.getElementById("sizetbl").style.display="none";
    		document.getElementById("sonotajoho").style.display="none";
    	}else if('1' == disType){
    		document.getElementById("kihonjoho").style.display="none";
            //document.getElementById("keywordandprice").style.display="";
            document.getElementById("yokoandshitaga").style.display="none";
            document.getElementById("sizetbl").style.display="none";
            document.getElementById("sonotajoho").style.display="none";
    	}else if('2' == disType){
    		document.getElementById("kihonjoho").style.display="none";
           // document.getElementById("keywordandprice").style.display="none";
            document.getElementById("yokoandshitaga").style.display="";
            document.getElementById("sizetbl").style.display="none";
            document.getElementById("sonotajoho").style.display="none";
        }else if('3' == disType){
        	document.getElementById("kihonjoho").style.display="none";
           // document.getElementById("keywordandprice").style.display="none";
            document.getElementById("yokoandshitaga").style.display="none";
            document.getElementById("sizetbl").style.display="";
            document.getElementById("sonotajoho").style.display="none";
        }else if('4' == disType){
        	document.getElementById("kihonjoho").style.display="none";
            //document.getElementById("keywordandprice").style.display="none";
            document.getElementById("yokoandshitaga").style.display="none";
            document.getElementById("sizetbl").style.display="none";
            document.getElementById("sonotajoho").style.display="";
        }
    	
    	var honyakuhituyoList = new Array( "yokomei", "shitagamei", "shohinshosaimeisho1",
    			"shohinshosainaiyo1", "shohinshosaimeisho2", "shohinshosainaiyo2",
    			"shohinshosaimeisho3", "shohinshosainaiyo3", "shohinshosaimeisho4",
    			"shohinshosainaiyo4", "shohinshosaimeisho5", "shohinshosainaiyo5",
    			"shohinshosaimeisho6", "shohinshosainaiyo6", "yokonaiyo1",
    			"yokonaiyo2", "yokonaiyo3", "yokonaiyo4", "yokonaiyo5",
    			"yokonaiyo6", "yokonaiyo7", "yokonaiyo8", "yokonaiyo9",
    			"yokonaiyo10", "yokonaiyo11", "yokonaiyo12", "yokonaiyo13",
    			"yokonaiyo14", "yokonaiyo15", "yokonaiyo16", "yokonaiyo17",
    			"yokonaiyo18", "yokonaiyo19", "yokonaiyo20", "shitaganaiyo1",
    			"shitaganaiyo2", "shitaganaiyo3", "shitaganaiyo4", "shitaganaiyo5",
    			"shitaganaiyo6", "shitaganaiyo7", "shitaganaiyo8", "shitaganaiyo9",
    			"shitaganaiyo10", "shitaganaiyo11", "shitaganaiyo12",
    			"shitaganaiyo13", "shitaganaiyo14", "shitaganaiyo15",
    			"shitaganaiyo16", "shitaganaiyo17", "shitaganaiyo18",
    			"shitaganaiyo19", "shitaganaiyo20", "sizeyoko1", "sizeyoko2",
    			"sizeyoko3", "sizeyoko4", "sizeyoko5", "sizeyoko6",
    			"sizeyoko7", "sizeyoko8", "sizeyoko9", "sizeyoko10",
    			"sizeshitaga1", "sizeshitaga2", "sizeshitaga3",
    			"sizeshitaga4", "sizeshitaga5", "sizeshitaga6",
    			"sizeshitaga7", "sizeshitaga8", "sizeshitaga9",
    			"sizeshitaga10" );
    	for(var i=0;i<honyakuhituyoList.length;i++){    	
    		if(document.getElementsByName("detail." + honyakuhituyoList[i])[0].value != ""){
    			var valueJp = document.getElementsByName("detail." + honyakuhituyoList[i] + "_jp")[0].value;
    			if(valueJp == ""){
    				document.getElementsByName("detail." + honyakuhituyoList[i] + "_jp")[0].style.background="yellow";
    				if(typeof (document.getElementsByName("detail." + honyakuhituyoList[i] + "_subid")[0]) != "undefined"){
						document.getElementsByName("detail." + honyakuhituyoList[i] + "_subid")[0].style.background="yellow";
					}
    				continue;
    			}else {
    				document.getElementsByName("detail." + honyakuhituyoList[i] + "_jp")[0].style.background="";
    				if(typeof (document.getElementsByName("detail." + honyakuhituyoList[i] + "_subid")[0]) != "undefined"){
    					document.getElementsByName("detail." + honyakuhituyoList[i] + "_subid")[0].style.background="";
					}
    			}
    		}
    	}
    	
    	if(document.getElementsByName("detail.sozainaiyo")[0].value != ""){
    	    var valueJp = document.getElementsByName("detail.sozainaiyo_jp")[0].value;
    	    if(valueJp == ""){
    	    	document.getElementsByName("detail.sozainaiyo_jp")[0].value == ""
    			document.getElementsByName("detail.sozainaiyo_jp")[0].style.background="yellow";
    		}else {
    			document.getElementsByName("detail.sozainaiyo_jp")[0].style.background="";
    	    	var valueJpArr = valueJp.split(",");
    			for(var j=0;j<valueJpArr.length;j++){
    				if(valueJpArr[j] == ""){
    					document.getElementsByName("detail.sozainaiyo_jp")[0].style.background="yellow";
    					break;
    				}
    			}
    		}
    	}
    	
    }
    
    function setMihozon(){
    	if(document.getElementsByName('detail.detailDelFlg')[0].value == '0'){
    	    document.getElementsByName('detail.hozonFlg')[0].value = '0';
    	}
    }
    
    function setChecked(){
    	var checkedArr = "";
    	var checkedIdx = "";
    	var checked = document.getElementsByName("checkeFlg");
        for(var i=0;i<checked.length;i++){
            if(document.getElementsByName("checkeFlg")[i].checked == true){
                if("" == checkedArr){
                	checkedArr = document.getElementsByName("f120201.shohinList["+i+"].shohinbango")[0].value;
                	checkedIdx = i;
                }else{
                	checkedArr += "&" + document.getElementsByName("f120201.shohinList["+i+"].shohinbango")[0].value;
                	checkedIdx += "&" + i;
                }
            }
        }
        document.getElementsByName("f120201.shohinbango_checked")[0].value = checkedArr;
        document.getElementsByName("f120201.checkedIndex")[0].value = checkedIdx;
        
    }
    
    function copyCheck(){
    	var shohinbango = document.getElementsByName('f120201.shohinbango_checked')[0].value;
    	if("" == shohinbango){
    		alert("请选择一个商品进行复制");
            return false;
    	}
    	if(shohinbango.indexOf("&")>0){
    		alert("只能选择一个商品进行复制");
    		return false;
    	}
    	
    	return true;
    }
    function checkSelected(){
    	var shohinbango = document.getElementsByName('f120201.shohinbango_checked')[0].value;
    	if("" == shohinbango){
    		alert("请选择商品");
            return false;
    	}

    	
    	return true;
    }
    
    function checkAll(){
    	var allcheck = true;
    	var checked = document.getElementsByName("checkeFlg");
    	for(var i=0;i<checked.length;i++){
    		if(document.getElementsByName("checkeFlg")[i].checked == false){
    			allcheck = false;
    			break;
    		}
    	}
    	if(allcheck){
    		for(var i=0;i<checked.length;i++){
    			document.getElementsByName("checkeFlg")[i].checked = false;
            }
    	}else{
    		  for(var i=0;i<checked.length;i++){
                  document.getElementsByName("checkeFlg")[i].checked = true;
              }
    	}
    	setChecked();
    }
    
    function getJp(obj){
    	obj.value = obj.value.replace("，",",").replace("、",",").replace("　"," ");
    	var name = obj.name;
    	var value = obj.value;
    	if(value == ''){
    		deleteHonyaku(name);
    	}else{
    	    $.post("getJpstr", {cnstr : value}, function(result) {
                if("" == result){
                	addHonyaku(name);
                }else{
                	setJpstr(name,result);
                }
            });
    	}
    }
    function getBango(){
    	var shohinbangofirst = document.getElementsByName("detail.shohinbango")[0].value;
    	if(shohinbangofirst == ''){
    		return;
    	}else{
    	    $.post("getBango", {first : shohinbangofirst}, function(result) {
    	    	document.getElementsByName("detail.shohinbango")[0].value = result;
            });
    	}
    }
    function checkurl(){
    	
    	var thisurl = document.getElementsByName("detail.shiireurl")[0].value;
    	if(thisurl == ''){
    		return;
    	}else{
    		
    	    $.post("checkUrl", {url : thisurl}, function(result) {
    	    	if(result =="0"){
    	            alert("未登录");
    	    	}else{
    	    		alert("已登录");
    	    	}
            });
    	}
    }
    
    function getSozaiJp(obj){
    	obj.value = obj.value.replace("，",",").replace("、",",").replace("　"," ");
    	var name = obj.name;
    	var value = obj.value;
    	if(value == ''){
    		deleteHonyaku(name);
    	}else{
    	    $.post("getSozaiJpstr", {cnstr : value}, function(result) {
                if("" == result){
                	addHonyaku(name);
                }else{
                	setJpstr(name,result);
                	var resultArr = result.split(",");
                	for(var i=0;i<resultArr.length;i++){
                		if("" == resultArr[i]){
                			document.getElementsByName(name + "_jp")[0].style.background="yellow";
                			break;
                		}
                	}
                }
            });
    	}
    }
    
    function getJpAndSubid(obj){
    	obj.value = obj.value.replace("，",",").replace("、",",").replace("　"," ");
    	var name = obj.name;
    	var value = obj.value;
    	if(value == ''){
    		deleteHonyaku(name);
    	}else{
    	    $.post("getJpstrAndSubid", {cnstr : value}, function(result) {
                if("" == result){
                	addHonyakuAndSubid(name);
                }else{
                	setJpstrAndSubid(name,result);
                }
            });
    	}
    }
    function init(){
    	setDisplay();
    	
    
    	var checkedIdx = document.getElementsByName("f120201.checkedIndex")[0].value.split("&");
    	if(checkedIdx != ''){
    	    for(var i=0;i<checkedIdx.length;i++){
        		document.getElementsByName("checkeFlg")[checkedIdx[i]].checked = true;
        	}
    	}
    	var flg = document.getElementsByName("f120201.successFlg")[0].value;
    	if("1" == flg){
    		alert("操作成功");
    		document.getElementsByName("f120201.successFlg")[0].value = "0";
    	}else if("2" == flg){
    		alert("操作失败");
    		document.getElementsByName("f120201.successFlg")[0].value = "0";
        }
    }
    
    function saveSession(){
    	
    }
    
    function addHonyaku(name){
    	document.getElementsByName(name + "_jp")[0].value="";
    	document.getElementsByName(name + "_jp")[0].style.background="yellow";
    }
    function addHonyakuAndSubid(name){
    	document.getElementsByName(name + "_jp")[0].style.background="yellow";
    	document.getElementsByName(name + "_subid")[0].style.background="yellow";
    }
    
    function deleteHonyaku(name){
    	document.getElementsByName(name + "_jp")[0].value="";
        document.getElementsByName(name + "_jp")[0].style.background="lightgrey";
        if(typeof (document.getElementsByName(name + "_subid")[0]) != "undefined"){
        	document.getElementsByName(name + "_subid")[0].value="";
   		    document.getElementsByName(name + "_subid")[0].style.background="lightgrey";
		}
    }
    
    function setJpstr(name,result){
    	document.getElementsByName(name + "_jp")[0].style.background="";
    	document.getElementsByName(name + "_jp")[0].value=result;
    }
    function setJpstrAndSubid(name,result){
    	var resArr = result.split("&");
    	document.getElementsByName(name + "_jp")[0].style.background="";
    	document.getElementsByName(name + "_jp")[0].value=resArr[0];
    	document.getElementsByName(name + "_subid")[0].value=resArr[1];
    	document.getElementsByName(name + "_subid")[0].style.background="";
    }
    
    function checkPicSelect(obj){
    	var checkCount = 0;
    	for (var i = 0; typeof (document.getElementsByName("detail.picList[" + i + "].checked")[0]) != "undefined"; i++) {
            if(document.getElementsByName("detail.picList[" + i + "].checked")[0].checked == true){
            	checkCount++;
            }
        }
    	if(checkCount>3){
    		obj.checked = false;
    		alert("最多选择3张图片");
    	}
    }
   function tongbuSizeName(){
    	
    	for (var i = 1;i <= 10;i++) {
    		getJp(document.getElementsByName("detail.sizeyoko" + i)[0]);
    		getJp(document.getElementsByName("detail.sizeshitaga" + i)[0]);
    	}
    }
    function tongbuY(){
    	
    	for (var i = 1;i <= 10;i++) {
    		document.getElementsByName("detail.sizeshitaga" + i)[0].value = document.getElementsByName("detail.yokonaiyo" + i)[0].value;
    		getJp(document.getElementsByName("detail.sizeshitaga" + i)[0]);
    	}
    	setMihozon();
    }
    function tongbuT(){
    	
    	for (var i = 1;i <= 10;i++) {
    		document.getElementsByName("detail.sizeshitaga" + i)[0].value = document.getElementsByName("detail.shitaganaiyo" + i)[0].value;
    		getJp(document.getElementsByName("detail.sizeshitaga" + i)[0]);
    	}
    	setMihozon();
    }
    function getLastName(){
    	
    	 $.post("getLastName", {shohinbango_sel : document.getElementsByName("f120201.shohinbango_selected")[0].value}, function(result) {
             if(""!=result){
            	 document.getElementsByName("detail.shohinmei_jp")[0].value = result;
            	 setMihozon();
             }
         });
    	
    }
    function getLastSize(){
   	 $.post("getLastSize", {shohinbango_sel : document.getElementsByName("f120201.shohinbango_selected")[0].value}, function(result) {
            if(""!=result){
            	var resultinfo = result.split("&");
           	    var yokoNameList = resultinfo[0].split("ddpeg");
           	    var shitagaNameList = resultinfo[1].split("ddpeg");
           	 var sizeList1 = resultinfo[2].split("ddpeg");
           	 var sizeList2 = resultinfo[3].split("ddpeg");
           	 var sizeList3 = resultinfo[4].split("ddpeg");
           	 var sizeList4 = resultinfo[5].split("ddpeg");
           	 var sizeList5 = resultinfo[6].split("ddpeg");
           	 var sizeList6 = resultinfo[7].split("ddpeg");
           	 var sizeList7 = resultinfo[8].split("ddpeg");
           	 var sizeList8 = resultinfo[9].split("ddpeg");
           	 var sizeList9 = resultinfo[10].split("ddpeg");
           	 var sizeList10 = resultinfo[11].split("ddpeg");
           	 
           	document.getElementsByName("detail.sizeyoko1")[0].value = yokoNameList[0];
           	document.getElementsByName("detail.sizeyoko2")[0].value = yokoNameList[1];
           	document.getElementsByName("detail.sizeyoko3")[0].value = yokoNameList[2];
           	document.getElementsByName("detail.sizeyoko4")[0].value = yokoNameList[3];
           	document.getElementsByName("detail.sizeyoko5")[0].value = yokoNameList[4];
           	document.getElementsByName("detail.sizeyoko6")[0].value = yokoNameList[5];
           	document.getElementsByName("detail.sizeyoko7")[0].value = yokoNameList[6];
           	document.getElementsByName("detail.sizeyoko8")[0].value = yokoNameList[7];
           	document.getElementsByName("detail.sizeyoko9")[0].value = yokoNameList[8];
           	document.getElementsByName("detail.sizeyoko10")[0].value = yokoNameList[9];
           	
           	document.getElementsByName("detail.sizeshitaga1")[0].value = shitagaNameList[0];
           	document.getElementsByName("detail.sizeshitaga2")[0].value = shitagaNameList[1];
           	document.getElementsByName("detail.sizeshitaga3")[0].value = shitagaNameList[2];
           	document.getElementsByName("detail.sizeshitaga4")[0].value = shitagaNameList[3];
           	document.getElementsByName("detail.sizeshitaga5")[0].value = shitagaNameList[4];
           	document.getElementsByName("detail.sizeshitaga6")[0].value = shitagaNameList[5];
           	document.getElementsByName("detail.sizeshitaga7")[0].value = shitagaNameList[6];
           	document.getElementsByName("detail.sizeshitaga8")[0].value = shitagaNameList[7];
           	document.getElementsByName("detail.sizeshitaga9")[0].value = shitagaNameList[8];
           	document.getElementsByName("detail.sizeshitaga10")[0].value = shitagaNameList[9];
           	
           	document.getElementsByName("detail.size11")[0].value = sizeList1[0];
           	document.getElementsByName("detail.size12")[0].value = sizeList1[1];
           	document.getElementsByName("detail.size13")[0].value = sizeList1[2];
           	document.getElementsByName("detail.size14")[0].value = sizeList1[3];
           	document.getElementsByName("detail.size15")[0].value = sizeList1[4];
           	document.getElementsByName("detail.size16")[0].value = sizeList1[5];
           	document.getElementsByName("detail.size17")[0].value = sizeList1[6];
           	document.getElementsByName("detail.size18")[0].value = sizeList1[7];
           	document.getElementsByName("detail.size19")[0].value = sizeList1[8];
           	document.getElementsByName("detail.size110")[0].value = sizeList1[9];

           	document.getElementsByName("detail.size21")[0].value = sizeList2[0];
           	document.getElementsByName("detail.size22")[0].value = sizeList2[1];
           	document.getElementsByName("detail.size23")[0].value = sizeList2[2];
           	document.getElementsByName("detail.size24")[0].value = sizeList2[3];
           	document.getElementsByName("detail.size25")[0].value = sizeList2[4];
           	document.getElementsByName("detail.size26")[0].value = sizeList2[5];
           	document.getElementsByName("detail.size27")[0].value = sizeList2[6];
           	document.getElementsByName("detail.size28")[0].value = sizeList2[7];
           	document.getElementsByName("detail.size29")[0].value = sizeList2[8];
           	document.getElementsByName("detail.size210")[0].value = sizeList2[9];

           	document.getElementsByName("detail.size31")[0].value = sizeList3[0];
           	document.getElementsByName("detail.size32")[0].value = sizeList3[1];
           	document.getElementsByName("detail.size33")[0].value = sizeList3[2];
           	document.getElementsByName("detail.size34")[0].value = sizeList3[3];
           	document.getElementsByName("detail.size35")[0].value = sizeList3[4];
           	document.getElementsByName("detail.size36")[0].value = sizeList3[5];
           	document.getElementsByName("detail.size37")[0].value = sizeList3[6];
           	document.getElementsByName("detail.size38")[0].value = sizeList3[7];
           	document.getElementsByName("detail.size39")[0].value = sizeList3[8];
           	document.getElementsByName("detail.size310")[0].value = sizeList3[9];

           	document.getElementsByName("detail.size41")[0].value =  sizeList4[0];
           	document.getElementsByName("detail.size42")[0].value =  sizeList4[1];
           	document.getElementsByName("detail.size43")[0].value =  sizeList4[2];
           	document.getElementsByName("detail.size44")[0].value =  sizeList4[3];
           	document.getElementsByName("detail.size45")[0].value =  sizeList4[4];
           	document.getElementsByName("detail.size46")[0].value =  sizeList4[5];
           	document.getElementsByName("detail.size47")[0].value =  sizeList4[6];
           	document.getElementsByName("detail.size48")[0].value =  sizeList4[7];
           	document.getElementsByName("detail.size49")[0].value =  sizeList4[8];
           	document.getElementsByName("detail.size410")[0].value = sizeList4[9];

           	document.getElementsByName("detail.size51")[0].value =  sizeList5[0];
           	document.getElementsByName("detail.size52")[0].value =  sizeList5[1];
           	document.getElementsByName("detail.size53")[0].value =  sizeList5[2];
           	document.getElementsByName("detail.size54")[0].value =  sizeList5[3];
           	document.getElementsByName("detail.size55")[0].value =  sizeList5[4];
           	document.getElementsByName("detail.size56")[0].value =  sizeList5[5];
           	document.getElementsByName("detail.size57")[0].value =  sizeList5[6];
           	document.getElementsByName("detail.size58")[0].value =  sizeList5[7];
           	document.getElementsByName("detail.size59")[0].value =  sizeList5[8];
           	document.getElementsByName("detail.size510")[0].value = sizeList5[9];

           	document.getElementsByName("detail.size61")[0].value =  sizeList6[0];
           	document.getElementsByName("detail.size62")[0].value =  sizeList6[1];
           	document.getElementsByName("detail.size63")[0].value =  sizeList6[2];
           	document.getElementsByName("detail.size64")[0].value =  sizeList6[3];
           	document.getElementsByName("detail.size65")[0].value =  sizeList6[4];
           	document.getElementsByName("detail.size66")[0].value =  sizeList6[5];
           	document.getElementsByName("detail.size67")[0].value =  sizeList6[6];
           	document.getElementsByName("detail.size68")[0].value =  sizeList6[7];
           	document.getElementsByName("detail.size69")[0].value =  sizeList6[8];
           	document.getElementsByName("detail.size610")[0].value = sizeList6[9];

           	document.getElementsByName("detail.size71")[0].value =  sizeList7[0];
           	document.getElementsByName("detail.size72")[0].value =  sizeList7[1];
           	document.getElementsByName("detail.size73")[0].value =  sizeList7[2];
           	document.getElementsByName("detail.size74")[0].value =  sizeList7[3];
           	document.getElementsByName("detail.size75")[0].value =  sizeList7[4];
           	document.getElementsByName("detail.size76")[0].value =  sizeList7[5];
           	document.getElementsByName("detail.size77")[0].value =  sizeList7[6];
           	document.getElementsByName("detail.size78")[0].value =  sizeList7[7];
           	document.getElementsByName("detail.size79")[0].value =  sizeList7[8];
           	document.getElementsByName("detail.size710")[0].value = sizeList7[9];

           	document.getElementsByName("detail.size81")[0].value =  sizeList8[0];
           	document.getElementsByName("detail.size82")[0].value =  sizeList8[1];
           	document.getElementsByName("detail.size83")[0].value =  sizeList8[2];
           	document.getElementsByName("detail.size84")[0].value =  sizeList8[3];
           	document.getElementsByName("detail.size85")[0].value =  sizeList8[4];
           	document.getElementsByName("detail.size86")[0].value =  sizeList8[5];
           	document.getElementsByName("detail.size87")[0].value =  sizeList8[6];
           	document.getElementsByName("detail.size88")[0].value =  sizeList8[7];
           	document.getElementsByName("detail.size89")[0].value =  sizeList8[8];
           	document.getElementsByName("detail.size810")[0].value = sizeList8[9];

           	document.getElementsByName("detail.size91")[0].value =  sizeList9[0];
           	document.getElementsByName("detail.size92")[0].value =  sizeList9[1];
           	document.getElementsByName("detail.size93")[0].value =  sizeList9[2];
           	document.getElementsByName("detail.size94")[0].value =  sizeList9[3];
           	document.getElementsByName("detail.size95")[0].value =  sizeList9[4];
           	document.getElementsByName("detail.size96")[0].value =  sizeList9[5];
           	document.getElementsByName("detail.size97")[0].value =  sizeList9[6];
           	document.getElementsByName("detail.size98")[0].value =  sizeList9[7];
           	document.getElementsByName("detail.size99")[0].value =  sizeList9[8];
           	document.getElementsByName("detail.size910")[0].value = sizeList9[9];

           	document.getElementsByName("detail.size101")[0].value =  sizeList10[0];
           	document.getElementsByName("detail.size102")[0].value =  sizeList10[1];
           	document.getElementsByName("detail.size103")[0].value =  sizeList10[2];
           	document.getElementsByName("detail.size104")[0].value =  sizeList10[3];
           	document.getElementsByName("detail.size105")[0].value =  sizeList10[4];
           	document.getElementsByName("detail.size106")[0].value =  sizeList10[5];
           	document.getElementsByName("detail.size107")[0].value =  sizeList10[6];
           	document.getElementsByName("detail.size108")[0].value =  sizeList10[7];
           	document.getElementsByName("detail.size109")[0].value =  sizeList10[8];
           	document.getElementsByName("detail.size1010")[0].value = sizeList10[9];

           	tongbuSizeName();

            }
        });
   	
   }
    function editName(){
    	
    }
    function addsize(index){
    	var plusnum = parseInt(document.getElementById("plus"+index).value);
    	for(var i=2;i<=10;i++){
    		var lastnum = document.getElementsByName("detail.size"+(i-1)+index)[0].value;
    		if(document.getElementsByName("detail.sizeshitaga"+i)[0].value !=""){
    			if(lastnum.indexOf("-")<0){
    		        document.getElementsByName("detail.size"+i+index)[0].value = parseInt(lastnum) + plusnum;
    			}else{
    				document.getElementsByName("detail.size"+i+index)[0].value = (
    					(parseInt(lastnum.split("-")[0]) + plusnum) +"-"+ (parseInt(lastnum.split("-")[1]) + plusnum));
    			}
    		}
    	}
    	
    }
</script>
</head>
<body onload="init();">
<div id='mask' class="mask" style="width:100%;height:500%;display:none"></div>
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:500px;top:5%;left:10px;height:40px;overflow-y:auto">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div style="width:300px;top:0px;left:520px;height:40px;overflow-y:auto;position: absolute;">
        <s:actionerror name="err" cssStyle="color:red;"/>
        </div>
        <div id="div2">
        <table width="95%" >
            <tr>
                <td width="70px">商品编号:</td>
                <td width="80px" align="left"><s:textfield name="f120201.keyword" size="15"></s:textfield></td>
                <td><input type="button" value="検索" onclick="actionSubmit('A12020106');"></td>
            </tr>
        </table>
        <table width="95%">
            <tr height="20px">
                <td align="left">基本信息</td>
                <td align="left"><s:select name="f120201.kihon_kensaku" list="#{ '':'--', '1':'完','0':'未'}" /></td>
                <td align="left">关键字</td>
                <td align="left"><s:select name="f120201.kyachikopi_kensaku" list="#{ '':'--','1':'完','0':'未'}" /></td>
            </tr>
            <tr height="20px">
                <td align="left">价格</td>
                <td align="left"><s:select name="f120201.kakaku_kensaku" list="#{ '':'--','1':'完','0':'未'}" /></td>
                <td align="left">图片</td>
                <td align="left"><s:select name="f120201.pic_kensaku" list="#{ '':'--','1':'完','0':'未'}" /></td>
            </tr>
            <tr height="20px">
                <td align="left">其他信息</td>
                <td align="left"><s:select name="f120201.sonota_kensaku" list="#{ '':'--','1':'完','0':'未'}" /></td>
                <td align="left">翻译</td>
                <td align="left" colspan="3"><s:select name="f120201.honyaku_kensaku" list="#{ '':'--','1':'完','0':'未'}" /></td>
            </tr>
            <tr height="20px">
                <td align="left">保存状态</td>
                <td align="left"><s:select name="f120201.hozon_kensaku" list="#{ '':'--','1':'完','0':'未'}" /></td>
                <td align="left">删除状态</td>
                <td align="left" colspan="3"><s:select name="f120201.del_kensaku" list="#{ '':'--','1':'完','0':'未'}" /></td>
            </tr>
        </table>
        <br/>
        <div style="width:250px;position:absolute;bottom: 0px;left:2px">
        <table width="250px">
            <tr>
               <td align="right">
                   <input type="button" value="添加" onclick="actionSubmit('A12020102');">&nbsp;&nbsp;
                   <input type="button" value="复制" onclick="if(copyCheck()){actionSubmit('A12020103');}">&nbsp;&nbsp;
                   <input type="button" value="批量" onclick="if(checkSelected()){popupDiv();}">&nbsp;&nbsp;
                   <input type="button" value="CSV" onclick="if(checkSelected()){actionSubmit('A12020116');}">
               </td>
            </tr>
            <tr>
               <td align="right">
                   <s:file name="picfileIkatu" style="width:120px"/>&nbsp;&nbsp;
                   <input type="button" value="批量加图" onclick="actionSubmit('A12020117');">&nbsp;&nbsp;
               </td>
            </tr>
        </table>
        <table class="table" align="center" width="250px" cellpadding="1" cellspacing="1" >
           <tr class="bg_tr">
               <td width="18px" align="center"><a href="javascript:checkAll();">全</a></td>
               <td align="center" width="18px">存</td>
               <td align="center" >商品编号</td>
               <td align="center" width="16px">基</td>
               <td align="center" width="16px">关</td>
               <td align="center" width="16px">价</td>
               <td align="center" width="16px">图</td>
               <td align="center" width="16px">翻</td>
               <td align="center" width="16px">其</td>
               
           </tr>
        </table>
        </div>
        <div id="div6">
        <div style="overflow-y:scroll;width:270px;height:99%;position:absolute">
        <table class="table" align="center" width="250px" cellpadding="1" cellspacing="1" >
           <s:iterator value="f120201.shohinList" status="status">
           <s:if test="showFlg == 1">
           <s:if test="f120201.shohinbango_selected != shohinbango">
           <s:if test="delFlg == 1">
           <tr>
               <td align="center" class="td_bg" style="background-color:lightgrey" width="18px"></td>
               <td align="center" class="td_bg" style="background-color:lightgrey" width="16px"><s:if test="hozonFlg == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].hozonFlg" value="%{hozonFlg}"/></td>
               <td align="center" class="td_bg" style="background-color:lightgrey" ><a href="javascript:document.getElementsByName('f120201.shohinbango_selected')[0].value='<s:property value='shohinbango'/>';actionSubmit('A12020113');" style="text-decoration: underline;"><s:property value='shohinbango'/></a><s:hidden name="f120201.shohinList[%{#status.index}].shohinbango" value="%{shohinbango}"/></td>
               <td align="center" class="td_bg" style="background-color:lightgrey" width="16px"><s:if test="kihonnyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].kihonnyuryoku" value="%{kihonnyuryoku}"/></td>
               <td align="center" class="td_bg" style="background-color:lightgrey" width="16px"><s:if test="kyachikopinyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].kyachikopinyuryoku" value="%{kyachikopinyuryoku}"/></td>
               <td align="center" class="td_bg" style="background-color:lightgrey" width="16px"><s:if test="kakakunyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].kakakunyuryoku" value="%{kakakunyuryoku}"/></td>
               <td align="center" class="td_bg" style="background-color:lightgrey" width="16px"><s:if test="picnyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].picnyuryoku" value="%{picnyuryoku}"/></td>
               <td align="center" class="td_bg" style="background-color:lightgrey" width="16px"><s:if test="honyakunyuflg == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].honyakunyuflg" value="%{honyakunyuflg}"/></td>
               <td align="center" class="td_bg" style="background-color:lightgrey" width="16px"><s:if test="sonotanyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].sonotanyuryoku" value="%{sonotanyuryoku}"/><s:hidden name="f120201.shohinList[%{#status.index}].delFlg" value="%{delFlg}"/></td>
           </tr>
           </s:if>
           <s:else>
           <tr>
               <td align="center" class="td_bg" width="18px"><s:checkbox name="checkeFlg" onchange="setChecked();"/></td>
               <td align="center" class="td_bg" width="16px"><s:if test="hozonFlg == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].hozonFlg" value="%{hozonFlg}"/></td>
               <td align="center" class="td_bg"><a href="javascript:document.getElementsByName('f120201.shohinbango_selected')[0].value='<s:property value='shohinbango'/>';actionSubmit('A12020113');" style="text-decoration: underline;"><s:property value='shohinbango'/></a><s:hidden name="f120201.shohinList[%{#status.index}].shohinbango" value="%{shohinbango}"/></td>
               <td align="center" class="td_bg" width="16px"><s:if test="kihonnyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].kihonnyuryoku" value="%{kihonnyuryoku}"/></td>
               <td align="center" class="td_bg" width="16px"><s:if test="kyachikopinyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].kyachikopinyuryoku" value="%{kyachikopinyuryoku}"/></td>
               <td align="center" class="td_bg" width="16px"><s:if test="kakakunyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].kakakunyuryoku" value="%{kakakunyuryoku}"/></td>
               <td align="center" class="td_bg" width="16px"><s:if test="picnyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].picnyuryoku" value="%{picnyuryoku}"/></td>
               <td align="center" class="td_bg" width="16px"><s:if test="honyakunyuflg == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].honyakunyuflg" value="%{honyakunyuflg}"/></td>
               <td align="center" class="td_bg" width="16px"><s:if test="sonotanyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].sonotanyuryoku" value="%{sonotanyuryoku}"/><s:hidden name="f120201.shohinList[%{#status.index}].delFlg" value="%{delFlg}"/></td>
           </tr>
           </s:else>
           </s:if>      
           <s:if test="f120201.shohinbango_selected == shohinbango">
           <tr>
               <td align="center" class="td_bg" style="background-color:lightblue" width="18px"><s:if test="delFlg != 1"><s:checkbox name="checkeFlg" onchange="setChecked();"/></s:if></td>
               <td align="center" class="td_bg" style="background-color:lightblue" width="16px"><s:if test="hozonFlg == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].hozonFlg" value="%{hozonFlg}"/></td>
               <td align="center" class="td_bg" style="background-color:lightblue" ><a href="javascript:document.getElementsByName('f120201.shohinbango_selected')[0].value='<s:property value='shohinbango'/>';actionSubmit('A12020113');" style="text-decoration: underline;"><s:property value='shohinbango'/></a><s:hidden name="f120201.shohinList[%{#status.index}].shohinbango" value="%{shohinbango}"/></td>
               <td align="center" class="td_bg" style="background-color:lightblue" width="16px"><s:if test="kihonnyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].kihonnyuryoku" value="%{kihonnyuryoku}"/></td>
               <td align="center" class="td_bg" style="background-color:lightblue" width="16px"><s:if test="kyachikopinyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].kyachikopinyuryoku" value="%{kyachikopinyuryoku}"/></td>
               <td align="center" class="td_bg" style="background-color:lightblue" width="16px"><s:if test="kakakunyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].kakakunyuryoku" value="%{kakakunyuryoku}"/></td>
               <td align="center" class="td_bg" style="background-color:lightblue" width="16px"><s:if test="picnyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].picnyuryoku" value="%{picnyuryoku}"/></td>
               <td align="center" class="td_bg" style="background-color:lightblue" width="16px"><s:if test="honyakunyuflg == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].honyakunyuflg" value="%{honyakunyuflg}"/></td>
               <td align="center" class="td_bg" style="background-color:lightblue" width="16px"><s:if test="sonotanyuryoku == 0"><font color="red">×</font></s:if><s:else><font color="green">○</font></s:else><s:hidden name="f120201.shohinList[%{#status.index}].sonotanyuryoku" value="%{sonotanyuryoku}"/><s:hidden name="f120201.shohinList[%{#status.index}].delFlg" value="%{delFlg}"/></td>
           </tr>
           </s:if>
           </s:if>
           </s:iterator>
        </table>
        </div>
        </div>
        </div>
        <div id="div7">
        <hr>
        <table>
            <tr>
                <td>
                    <input type="button" value="全部保存" onclick="actionSubmit('A12020107');">&nbsp;&nbsp;
                    <input type="button" value="导出翻译文本" onclick="actionSubmit('A12020115');">
                </td>
            </tr>
        </table>
        </div>
        <div id="div3">
        <table cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <s:select name="f120201.displayFlg" list="#{ '0':'基本信息','2':'横纵轴','3':'尺寸表','4':'共通设置'}"  onchange="setDisplay()"/>
                </td>
                <td>
                    <s:select name="f120201.shop" list="#{ 'coverforefront':'coverforefront'}"/>
                </td>
            </tr>
        </table>
        <br/>
        <table class="table" cellspacing="1" cellpadding="1">
            <tbody id="kihonjoho" style="display:none">
            <tr class="bg_tr">
                <td width="150px">商品编号 <font color="red">*</font></td>
                <td width="300px" class="td_bg" colspan="10">
                <s:if test="null == detail || '' == detail.shohinbango_moto">
                    <s:textfield name="detail.shohinbango" size="20"/>
                </s:if>
                <s:else>
                    <s:textfield readonly="true" style="background-color:lightgrey" name="detail.shohinbango" onkeydown="setMihozon();" size="20"/>
                </s:else>
                <input type="button" value="获得编号"  onclick="getBango();"/>
                </td>
                
            </tr>
            <tr class="bg_tr">
                <td>中文名<font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.shohinmei_cn" onkeydown="setMihozon();" size="35"/></td>
            </tr>
            <tr class="bg_tr">
                <td>进货地址 <font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.shiireurl" onkeydown="setMihozon();" size="32"/>
                    <input type="button" value="检查" onclick="checkurl();"><input type="button" value="预览" onclick="openYulan();">
                </td>
            </tr>
            <tr class="bg_tr">
                <td>进货价格 <font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.shiirekakaku" onkeydown="setMihozon();" size="35"/></td>
            </tr>
            <tr class="bg_tr">
                <td>信封对应 <font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:select name="detail.merubin" list="#{ '':'请选择','0':'不可','1':'可'}"  onchange="setMihozon()"/></td>
            </tr>
            <tr class="bg_tr">
                <td>表示先カテゴリ1<font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.kategori1" onkeydown="setMihozon();" size="35"/></td>
            </tr>
            <tr class="bg_tr">
                <td>表示先カテゴリ2</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.kategori2" onkeydown="setMihozon();" size="35"/></td>
            </tr>
            <tr class="bg_tr">
                <td>表示先カテゴリ3</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.kategori3" onkeydown="setMihozon();" size="35"/></td>
            </tr>
            <tr class="bg_tr">
                <td>表示先カテゴリ4</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.kategori4" onkeydown="setMihozon();" size="35"/></td>
            </tr>
            <tr class="bg_tr">
                <td>横軸項目名<font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.yokomei" size="15" onkeydown="setMihozon();" onkeyup="getJp(this);"/>&nbsp;&nbsp;<s:textfield name="detail.yokomei_jp" readonly = "true" style="background-color:lightgrey" size="15"/></td>
            </tr>
            <tr class="bg_tr">
                <td>纵軸項目名</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.shitagamei" size="15" onkeydown="setMihozon();" onkeyup="getJp(this);"/>&nbsp;&nbsp;<s:textfield name="detail.shitagamei_jp" readonly = "true" style="background-color:lightgrey" size="15"/></td>
            </tr>
            <tr class="bg_tr">
                <td>素材<font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.sozainaiyo" onkeydown="setMihozon();" onkeyup="getSozaiJp(this);" size="15"/>&nbsp;&nbsp;<s:textfield name="detail.sozainaiyo_jp" readonly = "true" style="background-color:lightgrey" size="15"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細1（名称）</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.shohinshosaimeisho1" onkeydown="setMihozon();" onkeyup="getJp(this);" size="15"/>&nbsp;&nbsp;<s:textfield name="detail.shohinshosaimeisho1_jp" readonly = "true" style="background-color:lightgrey" size="15"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細1（内容）</td>
                <td class="td_bg" colspan="10"><s:textarea name="detail.shohinshosainaiyo1" onkeydown="setMihozon();" onkeyup="getJp(this);" style="width:270px;height:40px"/><br/><s:textarea name="detail.shohinshosainaiyo1_jp" readonly = "true" style="width:270px;height:40px;background-color:lightgrey"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細2（名称）</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.shohinshosaimeisho2" onkeydown="setMihozon();" onkeyup="getJp(this);" size="15"/>&nbsp;&nbsp;<s:textfield name="detail.shohinshosaimeisho2_jp" readonly = "true" style="background-color:lightgrey" size="15"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細2（内容）</td>
                <td class="td_bg" colspan="10"><s:textarea name="detail.shohinshosainaiyo2" onkeydown="setMihozon();" onkeyup="getJp(this);" style="width:270px;height:40px"/><br/><s:textarea name="detail.shohinshosainaiyo2_jp" readonly = "true" style="width:270px;height:40px;background-color:lightgrey"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細3（名称）</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.shohinshosaimeisho3" onkeydown="setMihozon();" onkeyup="getJp(this);" size="15"/>&nbsp;&nbsp;<s:textfield name="detail.shohinshosaimeisho3_jp" readonly = "true" style="background-color:lightgrey" size="15"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細3（内容）</td>
                <td class="td_bg" colspan="10"><s:textarea name="detail.shohinshosainaiyo3" onkeydown="setMihozon();" onkeyup="getJp(this);" style="width:270px;height:40px"/><br/><s:textarea name="detail.shohinshosainaiyo3_jp" readonly = "true" style="width:270px;height:40px;background-color:lightgrey"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細4（名称）</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.shohinshosaimeisho4" onkeydown="setMihozon();" onkeyup="getJp(this);" size="15"/>&nbsp;&nbsp;<s:textfield name="detail.shohinshosaimeisho4_jp" readonly = "true" style="background-color:lightgrey" size="15"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細4（内容）</td>
                <td class="td_bg" colspan="10"><s:textarea name="detail.shohinshosainaiyo4" onkeydown="setMihozon();" onkeyup="getJp(this);" style="width:270px;height:40px"/><br/><s:textarea name="detail.shohinshosainaiyo4_jp" readonly = "true" style="width:270px;height:40px;background-color:lightgrey"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細5（名称）</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.shohinshosaimeisho5" onkeydown="setMihozon();" onkeyup="getJp(this);" size="15"/>&nbsp;&nbsp;<s:textfield name="detail.shohinshosaimeisho5_jp" readonly = "true" style="background-color:lightgrey" size="15"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細5（内容）</td>
                <td class="td_bg" colspan="10"><s:textarea name="detail.shohinshosainaiyo5" onkeydown="setMihozon();" onkeyup="getJp(this);" style="width:270px;height:40px"/><br/><s:textarea name="detail.shohinshosainaiyo5_jp" readonly = "true" style="width:270px;height:40px;background-color:lightgrey"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細6（名称）</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.shohinshosaimeisho6" onkeydown="setMihozon();" onkeyup="getJp(this);" size="15"/>&nbsp;&nbsp;<s:textfield name="detail.shohinshosaimeisho6_jp" readonly = "true" style="background-color:lightgrey" size="15"/></td>
            </tr>
            <tr class="bg_tr">
                <td>商品詳細6（内容）</td>
                <td class="td_bg" colspan="10"><s:textarea name="detail.shohinshosainaiyo6" onkeydown="setMihozon();" onkeyup="getJp(this);" style="width:270px;height:40px"/><br/><s:textarea name="detail.shohinshosainaiyo6_jp" readonly = "true" style="width:270px;height:40px;background-color:lightgrey"/></td>
            </tr>
            </tbody>
            
            
            
            <tbody id="yokoandshitaga" style="display:none">
            <tr class="bg_tr">
                <td colspan="11" width="450px">横軸<input type="button" value="同步尺寸表纵轴" onclick="tongbuY();">&nbsp;<font color="red">*</font></td>
            </tr>
            <tr class="bg_tr">
            <td colspan="11" class="td_bg">
            <table class="table" cellpadding="1" cellspacing="1">
            <tr class="bg_tr">
                <td width="20px">1</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo1" onkeyup="getJpAndSubid(this);" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo1_jp" readonly = "true" style="background-color:lightgrey" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo1_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">2</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo2" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo2_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo2_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">3</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo3" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo3_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo3_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">4</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo4" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo4_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo4_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">5</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo5" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo5_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo5_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">6</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo6" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo6_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo6_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">7</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo7" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo7_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo7_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">8</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo8" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo8_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo8_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">9</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo9" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo9_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo9_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">10</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo10" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo10_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo10_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">11</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo11" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo11_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo11_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">12</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo12" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo12_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo12_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">13</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo13" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo13_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo13_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">14</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo14" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo14_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo14_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">15</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo15" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo15_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo15_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">16</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo16" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo16_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo16_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">17</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo17" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo17_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo17_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">18</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo18" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo18_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo18_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">19</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo19" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo19_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo19_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">20</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo20" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo20_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.yokonaiyo20_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td colspan="11" width="450px">従軸<input type="button" value="同步尺寸表纵轴" onclick="tongbuT();">&nbsp;</td>
                
                
            </tr>
            <tr class="bg_tr">
                <td width="20px">1</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo1" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo1_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo1_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">2</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo2" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo2_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo2_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">3</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo3" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo3_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo3_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">4</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo4" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo4_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo4_subid" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">5</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo5" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo5_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo5_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">6</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo6" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo6_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo6_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">7</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo7" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo7_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo7_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">8</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo8" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo8_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo8_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">9</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo9" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo9_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo9_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">10</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo10" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo10_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo10_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">11</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo11" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo11_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo11_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">12</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo12" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo12_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo12_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">13</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo13" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo13_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo13_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">14</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo14" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo14_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo14_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">15</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo15" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo15_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo15_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">16</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo16" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo16_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo16_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">17</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo17" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo17_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo17_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">18</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo18" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo18_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo18_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">19</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo19" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo19_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo19_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td width="20px">20</td>
                <td class="td_bg" width="430px">&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo20" onkeyup="getJpAndSubid(this);" size="11"/>&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo20_jp" readonly = "true" style="background-color:lightgrey" onkeydown="setMihozon();" size="11"/>&nbsp;&nbsp;子货号:&nbsp;&nbsp;
                    <s:textfield name="detail.shitaganaiyo20_subid" readonly = "true" style="background-color:lightgrey" size="11"/>
                </td>
            </tr>
            </table>
            
            </tbody>
            <tbody id="sizetbl" style="display:none">
<tr class="bg_tr">
    <td align="center" ><input type="button" style="font-size:11px" value="与上同" onclick = "getLastSize();"></td>
    <td align="center" ><input type="text" size="1" id="plus1"><input style="font-size:9px" type="button" onclick="addsize(1)" value="+"></td>
    <td align="center" ><input type="text" size="1" id="plus2"><input style="font-size:9px" type="button" onclick="addsize(2)" value="+"></td>
    <td align="center" ><input type="text" size="1" id="plus3"><input style="font-size:9px" type="button" onclick="addsize(3)" value="+"></td>
    <td align="center" ><input type="text" size="1" id="plus4"><input style="font-size:9px" type="button" onclick="addsize(4)" value="+"></td>
    <td align="center" ><input type="text" size="1" id="plus5"><input style="font-size:9px" type="button" onclick="addsize(5)" value="+"></td>
    <td align="center" ><input type="text" size="1" id="plus6"><input style="font-size:9px" type="button" onclick="addsize(6)" value="+"></td>
    <td align="center" ><input type="text" size="1" id="plus7"><input style="font-size:9px" type="button" onclick="addsize(7)" value="+"></td>
    <td align="center" ><input type="text" size="1" id="plus8"><input style="font-size:9px" type="button" onclick="addsize(8)" value="+"></td>
    <td align="center" ><input type="text" size="1" id="plus9"><input style="font-size:9px" type="button" onclick="addsize(9)" value="+"></td>
    <td align="center" ><input type="text" size="1" id="plus10"><input style="font-size:9px" type="button" onclick="addsize(10)" value="+"></td>
</tr>
            <tr class="bg_tr">
                <td>&nbsp;</td>
                <td><s:textfield name="detail.sizeyoko1" onkeyup="getJp(this);" onkeydown="setMihozon();" size="4"/><br/><s:textfield name="detail.sizeyoko1_jp" readonly = "true" style="background-color:lightgrey" size="4"/></td>
                <td><s:textfield name="detail.sizeyoko2" onkeyup="getJp(this);" onkeydown="setMihozon();" size="4"/><br/><s:textfield name="detail.sizeyoko2_jp" readonly = "true" style="background-color:lightgrey" size="4"/></td>
                <td><s:textfield name="detail.sizeyoko3" onkeyup="getJp(this);" onkeydown="setMihozon();" size="4"/><br/><s:textfield name="detail.sizeyoko3_jp" readonly = "true" style="background-color:lightgrey" size="4"/></td>
                <td><s:textfield name="detail.sizeyoko4" onkeyup="getJp(this);" onkeydown="setMihozon();" size="4"/><br/><s:textfield name="detail.sizeyoko4_jp" readonly = "true" style="background-color:lightgrey" size="4"/></td>
                <td><s:textfield name="detail.sizeyoko5" onkeyup="getJp(this);" onkeydown="setMihozon();" size="4"/><br/><s:textfield name="detail.sizeyoko5_jp" readonly = "true" style="background-color:lightgrey" size="4"/></td>
                <td><s:textfield name="detail.sizeyoko6" onkeyup="getJp(this);" onkeydown="setMihozon();" size="4"/><br/><s:textfield name="detail.sizeyoko6_jp" readonly = "true" style="background-color:lightgrey" size="4"/></td>
                <td><s:textfield name="detail.sizeyoko7" onkeyup="getJp(this);" onkeydown="setMihozon();" size="4"/><br/><s:textfield name="detail.sizeyoko7_jp" readonly = "true" style="background-color:lightgrey" size="4"/></td>
                <td><s:textfield name="detail.sizeyoko8" onkeyup="getJp(this);" onkeydown="setMihozon();" size="4"/><br/><s:textfield name="detail.sizeyoko8_jp" readonly = "true" style="background-color:lightgrey" size="4"/></td>
                <td><s:textfield name="detail.sizeyoko9" onkeyup="getJp(this);" onkeydown="setMihozon();" size="4"/><br/><s:textfield name="detail.sizeyoko9_jp" readonly = "true" style="background-color:lightgrey" size="4"/></td>
                <td><s:textfield name="detail.sizeyoko10" onkeyup="getJp(this);" onkeydown="setMihozon();" size="4"/><br/><s:textfield name="detail.sizeyoko10_jp" readonly = "true" style="background-color:lightgrey" size="4"/></td>
            </tr>
            <tr class="bg_tr">
                <td><s:textfield name="detail.sizeshitaga1" onkeyup="getJp(this);" onkeydown="setMihozon();" size="3"/>&nbsp;<s:textfield name="detail.sizeshitaga1_jp" readonly = "true" style="background-color:lightgrey" size="3"/></td>
                <td><s:textfield name="detail.size11" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size12" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size13" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size14" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size15" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size16" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size17" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size18" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size19" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size110" onkeydown="setMihozon();" size="4"/></td>
            </tr>
            <tr class="bg_tr">
                <td><s:textfield name="detail.sizeshitaga2" onkeyup="getJp(this);" onkeydown="setMihozon();" size="3"/>&nbsp;<s:textfield name="detail.sizeshitaga2_jp" readonly = "true" style="background-color:lightgrey" size="3"/></td>
                <td><s:textfield name="detail.size21" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size22" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size23" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size24" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size25" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size26" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size27" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size28" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size29" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size210" onkeydown="setMihozon();" size="4"/></td>
            </tr>
            <tr class="bg_tr">
                <td><s:textfield name="detail.sizeshitaga3" onkeyup="getJp(this);" onkeydown="setMihozon();" size="3"/>&nbsp;<s:textfield name="detail.sizeshitaga3_jp" readonly = "true" style="background-color:lightgrey" size="3"/></td>
                <td><s:textfield name="detail.size31" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size32" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size33" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size34" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size35" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size36" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size37" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size38" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size39" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size310" onkeydown="setMihozon();" size="4"/></td>
            </tr>
            <tr class="bg_tr">
                <td><s:textfield name="detail.sizeshitaga4" onkeyup="getJp(this);" onkeydown="setMihozon();" size="3"/>&nbsp;<s:textfield name="detail.sizeshitaga4_jp" readonly = "true" style="background-color:lightgrey" size="3"/></td>
                <td><s:textfield name="detail.size41" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size42" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size43" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size44" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size45" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size46" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size47" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size48" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size49" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size410" onkeydown="setMihozon();" size="4"/></td>
            </tr>
            <tr class="bg_tr">
                <td><s:textfield name="detail.sizeshitaga5" onkeyup="getJp(this);" onkeydown="setMihozon();" size="3"/>&nbsp;<s:textfield name="detail.sizeshitaga5_jp" readonly = "true" style="background-color:lightgrey" size="3"/></td>
                <td><s:textfield name="detail.size51" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size52" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size53" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size54" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size55" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size56" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size57" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size58" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size59" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size510" onkeydown="setMihozon();" size="4"/></td>
            </tr>
            <tr class="bg_tr">
                <td><s:textfield name="detail.sizeshitaga6" onkeyup="getJp(this);" onkeydown="setMihozon();" size="3"/>&nbsp;<s:textfield name="detail.sizeshitaga6_jp" readonly = "true" style="background-color:lightgrey" size="3"/></td>
                <td><s:textfield name="detail.size61" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size62" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size63" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size64" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size65" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size66" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size67" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size68" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size69" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size610" onkeydown="setMihozon();" size="4"/></td>
            </tr>
            <tr class="bg_tr">
                <td><s:textfield name="detail.sizeshitaga7" onkeyup="getJp(this);" onkeydown="setMihozon();" size="3"/>&nbsp;<s:textfield name="detail.sizeshitaga7_jp" readonly = "true" style="background-color:lightgrey" size="3"/></td>
                <td><s:textfield name="detail.size71" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size72" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size73" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size74" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size75" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size76" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size77" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size78" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size79" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size710" onkeydown="setMihozon();" size="4"/></td>
            </tr>
            <tr class="bg_tr">
                <td><s:textfield name="detail.sizeshitaga8" onkeyup="getJp(this);" onkeydown="setMihozon();" size="3"/>&nbsp;<s:textfield name="detail.sizeshitaga8_jp" readonly = "true" style="background-color:lightgrey" size="3"/></td>
                <td><s:textfield name="detail.size81" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size82" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size83" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size84" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size85" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size86" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size87" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size88" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size89" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size810" onkeydown="setMihozon();" size="4"/></td>
            </tr>
            <tr class="bg_tr">
                <td><s:textfield name="detail.sizeshitaga9" onkeyup="getJp(this);" onkeydown="setMihozon();" size="3"/>&nbsp;<s:textfield name="detail.sizeshitaga9_jp" readonly = "true" style="background-color:lightgrey" size="3"/></td>
                <td><s:textfield name="detail.size91" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size92" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size93" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size94" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size95" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size96" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size97" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size98" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size99" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size910" onkeydown="setMihozon();" size="4"/></td>
            </tr>
            <tr class="bg_tr">
                <td><s:textfield name="detail.sizeshitaga10" onkeyup="getJp(this);" onkeydown="setMihozon();" size="3"/>&nbsp;<s:textfield name="detail.sizeshitaga10_jp" readonly = "true" style="background-color:lightgrey" size="3"/></td>
                <td><s:textfield name="detail.size101" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size102" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size103" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size104" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size105" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size106" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size107" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size108" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size109" onkeydown="setMihozon();" size="4"/></td>
                <td><s:textfield name="detail.size1010" onkeydown="setMihozon();" size="4"/></td>
            </tr>
            </tbody>
            <tbody id="sonotajoho" style="display:none">
            <tr class="bg_tr">
                <td class="td_bg" colspan="11" width="450px">
                    <s:checkbox name="detail.merubinsoryomuryokyanpen" />写评价信封包邮
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" colspan="11" width="450px">
                    <s:checkbox name="detail.hogofirumu" />写评价送手机贴膜防尘塞
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" colspan="11" width="450px">
                    <s:checkbox name="detail.sizepic" />女装尺寸示意图
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" colspan="11" width="450px">
                    <s:checkbox name="detail.pycommonpic" />皮衣共通图
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" colspan="11" width="450px">
                    <s:checkbox name="detail.xfpycommonpic" />橡菲皮衣共通图
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" colspan="11" width="450px">
                    <s:checkbox name="detail.osusume1" />osusume1
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" colspan="11" width="450px">
                    <s:checkbox name="detail.osusume2" />osusume2
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" colspan="11" width="450px">
                    <s:checkbox name="detail.osusume3" />osusume3
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" colspan="11" width="450px">
                    <s:checkbox name="detail.nouki" />纳期图片
                </td>
            </tr>
            </tbody>
        </table>
        </div>
        <div id="div5">
        <hr>
        <table>
            <tr>
                <td>
                    <s:if test="detail == null || detail.detailDelFlg == 0">
                    <input type="button" value="保存" onclick="actionSubmit('A12020108');">&nbsp;
                    <input type="button" value="保存为关键字模板" onclick="actionSubmit('A12020109');">&nbsp;
                    <input type="button" value="载入关键字模板" onclick="actionSubmit('A12020110');">&nbsp;
                    <input type="button" value="生成上架csv" onclick="actionSubmit('A12020111');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="删除" onclick="actionSubmit('A12020105');">
                    </s:if>
                    <s:else>
                    <input type="button" value="恢复" onclick="actionSubmit('A12020114');">
                    </s:else>
                </td>
            </tr>
        </table>
        </div>
        <div id="div4">
        <table>
            <tr>
                <td><s:file name="picfile"/><input type="button" value="上传图片" onclick="actionSubmit('A12020112');"></td>
            </tr>
        </table>
        <table class="table" cellspacing="1" cellpadding="1">
            <tr class="bg_tr">
                <td width="100px">图片保存文件夹</td>
                <td class="td_bg" width="200px"><s:textfield onkeydown="setMihozon();" name="detail.picdir" size="20" /></td>
            </tr>
        </table>
        <br/>预览<br/>
        <table class="table" cellspacing="1" cellpadding="1">
            <s:iterator value="detail.picList" status="status">
            <tr class="bg_tr">
                <td class="td_bg" width="30px"><s:checkbox onclick="checkPicSelect(this)" onchange="setMihozon();" name="detail.picList[%{#status.index}].checked"/></td>
                <td class="td_bg" width="300px" height="300px"><img src='<s:property value="picurl"/>' width="300px"><s:hidden name="detail.picList[%{#status.index}].picurl" value="%{picurl}"/></td>
            </tr>
            </s:iterator>
        </table>
        </div>
        <div id="div8">
        <table>
        <tr class="bg_tr">
                <td>商品名<br/>(127文字(半角255文字))<font color="red">*</font>
                <br/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="width:100px;height:30px" value="与上个相同" onclick="getLastName();">
                <br/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="width:100px;height:30px" value="编辑" onclick="editName();">
                </td>
                <td class="td_bg" colspan="10"><s:textarea name="detail.shohinmei_jp" onkeydown="setMihozon();" style="width:270px;height:200px"/></td>
            </tr>
            <tr class="bg_tr">
                <td>PC用キャッチコピー <br/>(87文字(半角174文字))<font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:textarea name="detail.pckyachikopi" onkeydown="setMihozon();" style="width:270px;height:160px"/></td>
            </tr>
            <tr class="bg_tr">
                <td>モバイル用キャッチコピー<br/>(30文字(半角60文字)) <font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:textarea name="detail.mobairukyachikopi" onkeydown="setMihozon();" style="width:270px;height:40px"/></td>
            </tr>
            <tr class="bg_tr">
                <td>全商品ディレクトリID<font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.direkutoriid" onkeydown="setMihozon();" size="35"/></td>
            </tr>
            <tr class="bg_tr">
                <td>タグID</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.taguid" onkeydown="setMihozon();" size="35"/></td>
            </tr>
            <tr class="bg_tr">
                <td>販売価格 <font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.hanbaikakaku" onkeydown="setMihozon();" size="35"/></td>
            </tr>
            <tr class="bg_tr">
                <td>表示価格</td>
                <td class="td_bg" colspan="10"><s:textfield name="detail.hyojikakaku" onkeydown="setMihozon();" size="35"/></td>
            </tr>
            <tr class="bg_tr">
                <td>送料<font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:select name="detail.soryobetsu" onchange="setMihozon();"  list="#{ '0':'別','1':'込'}"/></td>
            </tr>
            <tr class="bg_tr">
                <td>消費税<font color="red">*</font></td>
                <td class="td_bg" colspan="10"><s:select name="detail.shohizeibetsu" onchange="setMihozon();"  list="#{'1':'込' ,'0':'別'}"/></td>
            </tr>
            </table>
        </div>
        <div id='pop-div' style="width:800px;height:500px;position:fixed;display:none" class="pop-box">   
            <div class="pop-box-body" style="height:420px">
            <table width="95%" align="center">
                <tr>
                    <td align="center"><input type="button" style="width:150px" value="商品编号" onclick="checkThisItem(this)" name="plxg.shohinbango_str" />
<input type="button" style="width:150px" value="中文名" onclick="checkThisItem(this)" name="plxg.shohinmei_cn_str" />
<input type="button" style="width:150px" value="进货地址" onclick="checkThisItem(this)" name="plxg.shiireurl_str" />
<input type="button" style="width:150px" value="进货价格" onclick="checkThisItem(this)" name="plxg.shiirekakaku_str" />
<input type="button" style="width:150px" value="表示先カテゴリ1" onclick="checkThisItem(this)" name="plxg.kategori1_str" />
<input type="button" style="width:150px" value="表示先カテゴリ2" onclick="checkThisItem(this)" name="plxg.kategori2_str" />
<input type="button" style="width:150px" value="表示先カテゴリ3" onclick="checkThisItem(this)" name="plxg.kategori3_str" />
<input type="button" style="width:150px" value="表示先カテゴリ4" onclick="checkThisItem(this)" name="plxg.kategori4_str" />
<input type="button" style="width:150px" value="横軸項目名" onclick="checkThisItem(this)" name="plxg.yokomei_str" />
<input type="button" style="width:150px" value="纵軸項目名" onclick="checkThisItem(this)" name="plxg.shitagamei_str" />
<input type="button" style="width:150px" value="素材" onclick="checkThisItem(this)" name="plxg.sozainaiyo_str" />
<input type="button" style="width:150px" value="商品詳細1（名称）" onclick="checkThisItem(this)" name="plxg.shohinshosaimeisho1_str" />
<input type="button" style="width:150px" value="商品詳細1（内容）" onclick="checkThisItem(this)" name="plxg.shohinshosainaiyo1_str" />
<input type="button" style="width:150px" value="商品詳細2（名称）" onclick="checkThisItem(this)" name="plxg.shohinshosaimeisho2_str" />
<input type="button" style="width:150px" value="商品詳細2（内容）" onclick="checkThisItem(this)" name="plxg.shohinshosainaiyo2_str" />
<input type="button" style="width:150px" value="商品詳細3（名称）" onclick="checkThisItem(this)" name="plxg.shohinshosaimeisho3_str" />
<input type="button" style="width:150px" value="商品詳細3（内容）" onclick="checkThisItem(this)" name="plxg.shohinshosainaiyo3_str" />
<input type="button" style="width:150px" value="商品詳細4（名称）" onclick="checkThisItem(this)" name="plxg.shohinshosaimeisho4_str" />
<input type="button" style="width:150px" value="商品詳細4（内容）" onclick="checkThisItem(this)" name="plxg.shohinshosainaiyo4_str" />
<input type="button" style="width:150px" value="商品詳細5（名称）" onclick="checkThisItem(this)" name="plxg.shohinshosaimeisho5_str" />
<input type="button" style="width:150px" value="商品詳細5（内容）" onclick="checkThisItem(this)" name="plxg.shohinshosainaiyo5_str" />
<input type="button" style="width:150px" value="商品詳細6（名称）" onclick="checkThisItem(this)" name="plxg.shohinshosaimeisho6_str" />
<input type="button" style="width:150px" value="商品詳細6（内容）" onclick="checkThisItem(this)" name="plxg.shohinshosainaiyo6_str" />
<input type="button" style="width:150px" value="商品名" onclick="checkThisItem(this)" name="plxg.shohinmei_jp_str" />
<input type="button" style="width:150px" value="PC用キャッチコピー" onclick="checkThisItem(this)" name="plxg.pckyachikopi_str" />
<input type="button" style="width:150px" value="モバイル用キャッチコピー" onclick="checkThisItem(this)" name="plxg.mobairukyachikopi_str" />
<input type="button" style="width:150px" value="全商品ディレクトリID" onclick="checkThisItem(this)" name="plxg.direkutoriid_str" />
<input type="button" style="width:150px" value="タグID" onclick="checkThisItem(this)" name="plxg.taguid_str" />
<input type="button" style="width:150px" value="販売価格" onclick="checkThisItem(this)" name="plxg.hanbaikakaku_str" />
<input type="button" style="width:150px" value="表示価格" onclick="checkThisItem(this)" name="plxg.hyojikakaku_str" />
<input type="button" style="width:150px" value="图片文件夹" onclick="checkThisItem(this)" name="plxg.picdir_str" />
<input type="button" style="width:150px" value="信封对应" onclick="checkThisItem(this)" name="plxg.merubin_sel" />
<input type="button" style="width:150px" value="送料" onclick="checkThisItem(this)" name="plxg.soryobetsu_sel" />
<input type="button" style="width:150px" value="消費税" onclick="checkThisItem(this)" name="plxg.shohizeibetsu_sel" />
<input type="button" style="width:150px" value="写评价信封包邮 " onclick="checkThisItem(this)" name="plxg.merubinsoryomuryokyanpen_chk" />
<input type="button" style="width:150px" value="写评价送贴膜防尘塞" onclick="checkThisItem(this)" name="plxg.hogofirumu_chk" />
<input type="button" style="width:150px" value="女装尺寸示意图 " onclick="checkThisItem(this)" name="plxg.sizepic_chk" />
<input type="button" style="width:150px" value="皮衣共通图 " onclick="checkThisItem(this)" name="plxg.pycommonpic_chk" />
<input type="button" style="width:150px" value="橡菲皮衣共通图" onclick="checkThisItem(this)" name="plxg.xfpycommonpic_chk" />
<input type="button" style="width:150px" value="osusume1" onclick="checkThisItem(this)" name="plxg.osusume1_chk" />
<input type="button" style="width:150px" value="osusume2" onclick="checkThisItem(this)" name="plxg.osusume2_chk" />
<input type="button" style="width:150px" value="osusume3" onclick="checkThisItem(this)" name="plxg.osusume3_chk" />
<input type="button" style="width:150px" value="纳期图片" onclick="checkThisItem(this)" name="plxg.nouki_chk" />
<s:hidden name="plxg.input_selectedItem" />
</td>
                </tr>
            </table>
            <br/>
            <div id="typeSelect" style="display:none">
            <table align="center" width="95%">
                <tr>
                    <td align="center" style="font-size:15px"><s:radio name="plxg.radio_typeSelect" onchange="selectType(this.value)" value="0" list="#{ '0':'添加&nbsp;&nbsp;', '1':'置换&nbsp;&nbsp;','2':'删除','3':'重新设置为'}" /></td>
                </tr>
            </table>
            </div>
            <div>
            <table align="center" width="95%">
                <tr id="addAtFirst" style="display:none">
                    <td width="100px"><b><font size="3">在开头添加</font></b></td>
                    <td><s:textarea name="plxg.input_addAtFirst" style="width:620px"/></td>
                </tr>
                <tr id="addAtEnd" style="display:none">
                    <td width="100px"><b><font size="3">在结尾添加</font></b></td>
                    <td><s:textarea name="plxg.input_addAtEnd" style="width:620px"/></td>
                </tr>
                <tr id="replaceFrom" style="display:none">
                    <td width="100px"><b><font size="3">置换</font></b></td>
                    <td><s:textarea name="plxg.input_replaceFrom" style="width:620px"/></td>
                </tr>
                <tr id="replaceTo" style="display:none">
                    <td width="100px"><b><font size="3">为</font></b></td>
                    <td><s:textarea name="plxg.input_replaceTo" style="width:620px"/></td>
                </tr>
                <tr id="strDelete" style="display:none">
                    <td width="100px"><b><font size="3">删除文字列</font></b></td>
                    <td><s:textarea name="plxg.input_strDelete" style="width:620px"/></td>
                </tr>
                <tr id="resetStr" style="display:none">
                    <td width="100px"><b><font size="3">重新设定为</font></b></td>
                    <td><s:textarea name="plxg.input_resetStr" style="width:620px"/></td>
                </tr>
                <tr id="merubintaio" style="display:none">
                    <td width="300px" align="center"><b><font size="3">信封 </font></b></td>
                    <td><s:select name="plxg.sel_merubintaio" list="#{'0':'不可','1':'可'}"/></td>
                </tr>
                <tr id="soryosentaku" style="display:none">
                    <td width="300px" align="center"><b><font size="3">送料 </font></b></td>
                    <td><s:select name="plxg.sel_soryosentaku" list="#{'0':'別','1':'込'}"/></td>
                </tr>
                <tr id="shohizeisentaku" style="display:none">
                    <td width="300px" align="center"><b><font size="3">消費税 </font></b></td>
                    <td><s:select name="plxg.sel_shohizeisentaku" list="#{'0':'別','1':'込'}"/></td>
                </tr>
                <tr id="xpjxfby" style="display:none">
                    <td width="300px" align="center"><b><font size="3">写评价信封包邮 </font></b></td>
                    <td><s:select name="plxg.chk_xpjxfby" list="#{'0':'不选择','1':'选择'}"/></td>
                </tr>
                <tr id="xpjssjtmfcs" style="display:none">
                    <td width="300px" align="center"><b><font size="3">写评价送手机贴膜防尘塞 </font></b></td>
                    <td><s:select name="plxg.chk_xpjssjtmfcs" list="#{'0':'不选择','1':'选择'}"/></td>
                </tr>
                <tr id="nzccsyt" style="display:none">
                    <td width="300px" align="center"><b><font size="3">女装尺寸示意图 </font></b></td>
                    <td><s:select name="plxg.chk_nzccsyt" list="#{'0':'不选择','1':'选择'}"/></td>
                </tr>
                <tr id="pygtt" style="display:none">
                    <td width="300px" align="center"><b><font size="3">皮衣共通图 </font></b></td>
                    <td><s:select name="plxg.chk_pygtt" list="#{'0':'不选择','1':'选择'}"/></td>
                </tr>
                <tr id="xfpygtt" style="display:none">
                    <td width="300px" align="center"><b><font size="3">橡菲皮衣共通图 </font></b></td>
                    <td><s:select name="plxg.chk_xfpygtt" list="#{'0':'不选择','1':'选择'}"/></td>
                </tr>
                <tr id="osusume1" style="display:none">
                    <td width="300px" align="center"><b><font size="3">osusume1 </font></b></td>
                    <td><s:select name="plxg.chk_osusume1" list="#{'0':'不选择','1':'选择'}"/></td>
                </tr>
                <tr id="osusume2" style="display:none">
                    <td width="300px" align="center"><b><font size="3">osusume2 </font></b></td>
                    <td><s:select name="plxg.chk_osusume2" list="#{'0':'不选择','1':'选择'}"/></td>
                </tr>
                <tr id="osusume3" style="display:none">
                    <td width="300px" align="center"><b><font size="3">osusume3 </font></b></td>
                    <td><s:select name="plxg.chk_osusume3" list="#{'0':'不选择','1':'选择'}"/></td>
                </tr>
                <tr id="nouki" style="display:none">
                    <td width="300px" align="center"><b><font size="3">nouki </font></b></td>
                    <td><s:select name="plxg.chk_nouki" list="#{'0':'不选择','1':'选择'}"/></td>
                </tr>
            </table>
            </div>
            </div>
            <table align="center" width="95%">
                <tr>
                    <td width="25%" align="right"><input type="button" onclick="if(checkSelected()){actionSubmit('A12020104');}" value="確定" /></td>
                    <td width="25%"></td>
                    <td width="25%"></td>
                    <td width="25%"><input type="button" onclick="hideDiv();" value="返回" /></td>
                </tr>
            </table>
        </div>
        <div id="yulanDiv"  style="position:absolute;right:0px;top:0px;width:620px;height:700px;display:none">
            <table cellpadding="0" cellspacing="0" width=99% height="700px" >
                <tr>
                    <td width="40px" valign="top"><input type="button" value="关闭" onclick="closeYulan();"></td>
                    <td width="580px"><iframe id="yulan" frameborder="1" width="560px"  height="700px" scrolling="auto"></iframe></td>
                </tr>
            </table>
            
        </div>
        <s:hidden name="f120201.shumokuid"/>
        <s:hidden name="f120201.shohinbango_selected"/>
        <s:hidden name="f120201.successFlg"/>
        <s:hidden name="f120201.shohinbango_checked"/>
        <s:hidden name="f120201.checkedIndex"/>
        <s:hidden name="detail.shohinbango_moto"/>
        <s:hidden name="detail.hozonFlg"/>
        <s:hidden name="detail.detailDelFlg"/>
		<s:hidden name="viewId" value="V120201"/>
		<s:hidden name="mode"/>
		<s:hidden name="rowIndex"/>
	</s:form>
</body>
</html>