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
function openShuseiWin() {
    var returnVal = openShowModalDialog("1024", "700", "A10010301?orderNo="+document.getElementsByName("f100102.juchubango")[0].value);
    if (returnVal) {
    	reloadPage();
    }
}

function setZumi(){
	var bango1 = document.getElementsByName("f100102.juchubango")[0].value;
	var bango2 = document.getElementsByName("f100102.nyukafukaBangoArr")[0].value;
	$.post("A10010222", {juchubango:bango1,shohinbango:bango2}, function(result) {
        if("true" == result){
        	alert("設定済み");
        }else{
        	alert("エラー！");
        }
    }, "json");
}

function getShohinStsInfo(bango){
	openShowModalDialogShuseika("1024", "700", "P01020101?shohinbango="+bango);
}

function init() {
	var scrollxVal = document.getElementsByName("scrollx")[0].value;
	var scrollyVal = document.getElementsByName("scrolly")[0].value;
	if(scrollxVal != ""){
		$(document).scrollLeft(scrollxVal);
	}
	if(scrollyVal != ""){
		$(document).scrollTop(scrollyVal);
	}

	replaceBr();
	setButtonDisable();
    hideDiv();
}

function getShohinInfo(){
	var bango = $("#tuikaShohin").val();
    var kosu = $("#tuikaKosu").val();
	if(bango == ""){
		alert("商品番号を入力してください");
		return;
	}
	
	if(kosu == "" || kosu =="0"){
		alert("個数を入力してください");
		return;
	}
	
	if(isNaN(kosu) || kosu == " "){
		alert("数字を入力してください");
		return;
	}
	
	$.post("checkShohinAri", {shohinbango:bango}, function(result) {
        if("true" != result){
        	alert("この商品は存在してません");
        }else{
        	setShohin(bango,kosu);
        }
    }, "json");
		
}

function setShohin(shohinbango,kosu){
	$('#tuikaShohin').val("");
	$('#tuikaKosu').val("");
	var object;
	var jsonArrs = $("#jsonArr").val();

	if(jsonArrs == null || jsonArrs == ""){
		jsonArrs = '{"shohin":[]}';
	}
	object = JSON.parse(jsonArrs);

	var data = '{"shohinbango":"'+ shohinbango +'","kosu":"' + kosu + '"}';
	var objtemp = JSON.parse(data);

	var ariflg = false;
	for(var i=0;i<object.shohin.length;i++){
		if(shohinbango == object.shohin[i].shohinbango){
			ariflg = true;
			object.shohin[i].kosu = parseInt(object.shohin[i].kosu) + parseInt(kosu);
		}
	}
	if(!ariflg){
	    object.shohin.push(objtemp);
	}
	
	jsonArrs = JSON.stringify(object);
	$("#jsonArr").val(jsonArrs);
	
	setShohinDisplay();
}

function deleteShohinInTuikaList(index){
	var object;
	var jsonArrs = $("#jsonArr").val();
	object = JSON.parse(jsonArrs);
	object.shohin.splice(index,1);
	
	$("#jsonArr").val(JSON.stringify(object));
	
	setShohinDisplay();
}

function setShohinDisplay(){
	var object;
	var jsonArrs = $("#jsonArr").val();
	object = JSON.parse(jsonArrs);
	
    var tableStrStart = '<table cellspacing="1" cellpadding="2" class="table" width="800px" align="center">';
    var trStrStart = '<tr class="bg_tr">';
    var tdStrStart5 = '<td class="td_bg" align="center" width="50%">';
    var tdStrStart3 = '<td class="td_bg" align="center" width="30%">';
    var tdStrStart2 = '<td class="td_bg" align="center" width="20%">';

    var tableStrEnd = '</table>';
    var trStrEnd = '</tr>';
    var tdStrEnd = '</td>';
    
    var tuikadiv = $("#tuikahasoListDiv");
    var naiyo ='';
    
    for(var i=0;i<object.shohin.length;i++){
    	naiyo += trStrStart;
    	naiyo += tdStrStart5;
    	naiyo += object.shohin[i].shohinbango;
    	naiyo += tdStrEnd;
    	naiyo += tdStrStart3;
    	naiyo += object.shohin[i].kosu;
    	naiyo += tdStrEnd;
    	naiyo += tdStrStart2;
    	naiyo += '<input type="button" value="削除" name="tuikaListSakujoBtn" onclick="deleteShohinInTuikaList(' + i +')">';
    	naiyo += tdStrEnd;
    	naiyo += trStrEnd;
    }
    
    naiyo = tableStrStart + naiyo + tableStrEnd;
    tuikadiv.html(naiyo);
    
}

function setTuikaValue(){
	$('input[name="f100102.tuikashoribango_shuseihaso"]').val($('input[name="f100102.tuikashoribango_tuikashusei"]').val());
	var riyu = $('input[name="f100102.tuikariyu_tuikashusei"]').val();
	var riyuArr = riyu.split("&");
	if(riyuArr != ""){
	for(var j=0;j<riyuArr.length;j++){
        $('input[name="f100102.tuikariyu"]')[riyuArr[j]].checked = true;
	}
	}
	$('input[name="f100102.tuikariyuSonota"]').val($('input[name="f100102.tuikariyusonota_tuikashusei"]').val());
	$('input[name="f100102.tuikasoryofutan"]')[$('input[name="f100102.hasosoryofutan_tuikashusei"]').val()].checked = true;
	$('textarea[name="f100102.tuikabiko"]').val($('input[name="f100102.biko_tuikashusei"]').val());
}

function setTuikaShuseiFuka(){
	
    $('input[name="f100102.tuikariyu"]').attr("disabled",true);
	$('input[name="f100102.tuikariyuSonota"]').attr("disabled",true);
	$('input[name="f100102.tuikasoryofutan"]').attr("disabled",true);
	$('textarea[name="f100102.tuikabiko"]').attr("disabled",true);
	document.getElementById("tuikaTbl").style.display = "none";
	for (var i = 0; typeof (document.getElementsByName("tuikaListSakujoBtn")[i]) != "undefined"; i++) {
		document.getElementsByName("tuikaListSakujoBtn")[i].disabled = true;
	}	
	document.getElementById("hasoInfoTuika").style.display = "";
}

function setTuikaShuseika(){
	
    $('input[name="f100102.tuikariyu"]').attr("disabled",false);
	$('input[name="f100102.tuikariyuSonota"]').attr("disabled",false);
	$('input[name="f100102.tuikasoryofutan"]').attr("disabled",false);
	$('textarea[name="f100102.tuikabiko"]').attr("disabled",false);
	document.getElementById("tuikaTbl").style.display = "";
	for (var i = 0; typeof (document.getElementsByName("tuikaListSakujoBtn")[i]) != "undefined"; i++) {
		document.getElementsByName("tuikaListSakujoBtn")[i].disabled = false;
	}
	document.getElementById("hasoInfoTuika").style.display = "none";
}

function replaceBr(){
//	+ "<br>"
//	+ "<a target='_blank' href='https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=RP03_001_001&order_number="
//	+ orderList.get(i).getJuchubango() + "'>"
//	+ "個別カード決済と履歴" + "</a>";
    document.getElementById("td0").innerHTML = document.getElementById("td0").innerHTML.replace(new RegExp("&lt;br&gt;", 'g'),"<br/>");
    //if(document.getElementById("td0").innerHTML.indexOf("クレジットカード")>0){
    //	document.getElementById("td0").innerHTML += ("<br><a target='_blank' href='https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=RP03_001_001&order_number="
    //			+ document.getElementsByName("f100102.juchubango")[0].value + "'><font color='blue'>個別カード決済と履歴</font></a>");
    //}
	
	document.getElementById("td1").innerHTML = document.getElementById("td1").innerHTML.replace(new RegExp("&lt;br&gt;", 'g'),"<br/>");
	for (var i = 0; typeof (document
            .getElementsByName("f100102.shohinList[" + i
                    + "].shouhinmei")[0]) != "undefined"; i++) {
            document.getElementById("td4["+i+"]").innerHTML = document.getElementById("td4["+i+"]").innerHTML.replace(new RegExp("&lt;br&gt;", 'g'),"<br/>");
    }
}
function setSoshinzumi(bango){
	$.post("A10010204", {
		shoribango : bango,
	}, function(result) {
		if('true' == result){
			alert("設定成功しました！");
			reloadPage();
		}else{
			alert("設定失敗しました！");
		}
	});
	
}
function popupDiv() {   
    var div_obj = $("#pop-div");    
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
}   
function popupDivFustuhasou() {   
    var div_obj = $("#pop-div-futuhaso");
	div_obj.css("top",($(window).height()-500)/2);
	div_obj.css("left",($(window).width()-550)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
} 

function popupDivHenpinhaso() {   
    var div_obj = $("#pop-div-henpinhaso");
	div_obj.css("top",($(window).height()-500)/2);
	div_obj.css("left",($(window).width()-550)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
}

function popHenkin(type,shoribango){
	var div_obj = $("#pop-div-henkin");
	div_obj.css("top",($(window).height()-400)/2);
	div_obj.css("left",($(window).width()-300)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show(); 
    if(type == '2'){
    	setHenkinVal(shoribango);
    	document.getElementById("henkinBtn1").style.display = "none";
    	document.getElementById("henkinBtn2").style.display = "";
    	document.getElementById("henkinshoribangotbl").style.display = "";
    }else{
    	document.getElementById("henkinBtn1").style.display = "";
    	document.getElementById("henkinBtn2").style.display = "none";
    	document.getElementById("henkinshoribangotbl").style.display = "none";
    }
}

function popDokon(){
	var div_obj = $("#pop-div-dokon");
	div_obj.css("top",($(window).height()-400)/2);
	div_obj.css("left",($(window).width()-300)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show(); 
    document.getElementsByName("juchubango_dokon").value = "";
}

function setHenkinVal(shoribango){
	$('input[name="f100102.shoribango_henkin"]').val(shoribango);
	for (var i = 0; typeof (document.getElementsByName("f100102.henkinList[" + i + "].shoribango")[0]) != "undefined"; i++) {
        if(document.getElementsByName("f100102.henkinList[" + i + "].shoribango")[0].value == shoribango){
        	$('input[name="f100102.henkinkinkaku"]').val(document.getElementsByName("f100102.henkinList[" + i + "].henkinkingaku")[0].value);
        	$('textarea[name="f100102.henkinbiko"]').val(document.getElementsByName("f100102.henkinList[" + i + "].biko")[0].value);
        }
    }
}

function hideDivHenkin(){
	clearHenkinValue();
	hideDiv();
}

function clearHenkinValue(){
	$('input[name="f100102.henkinkinkaku"]').val("");
	$('textarea[name="f100102.henkinbiko"]').val("");
}

function popupDivBunnouhasou() {   
    var div_obj = $("#pop-div-bunnou"); 
	div_obj.css("top",($(window).height()-500)/2);
	div_obj.css("left",($(window).width()-550)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
} 

function popupDivHenpin(){
	var div_obj = $("#pop-div-henpin");   
	div_obj.css("top",($(window).height()-500)/2);
	div_obj.css("left",($(window).width()-850)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show(); 
}

function popupDivTuika(type){
	var div_obj = $("#pop-div-tuika");   
	div_obj.css("top",($(window).height()-500)/2);
	div_obj.css("left",($(window).width()-850)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();
    if("0" == type){
    	document.getElementById("tuikaTuikaBtn").style.display="";
    	document.getElementById("tuikaShuseiBtn").style.display="none";
    	document.getElementById("tuikaHasoBtn").style.display="none";
    }else if("1" == type){
    	setTuikaValue();
    	setShohinDisplay();
    	setTuikaShuseika();
    	document.getElementById("tuikaTuikaBtn").style.display="none";
    	document.getElementById("tuikaShuseiBtn").style.display="";
    	document.getElementById("tuikaHasoBtn").style.display="none";
    }else if("2" == type){
    	setTuikaValue();
    	setShohinDisplay();
    	setTuikaShuseiFuka();
    	document.getElementById("tuikaTuikaBtn").style.display="none";
    	document.getElementById("tuikaShuseiBtn").style.display="none";
    	document.getElementById("tuikaHasoBtn").style.display="";
    	
    }
}
function popupDivHenpinhasoushusei(){
	var div_obj = $("#pop-div-henpin-shusei");   
	div_obj.css("top",($(window).height()-500)/2);
	div_obj.css("left",($(window).width()-850)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show(); 
}


function popupDivHenpinzumi(){
	var div_obj = $("#pop-div-henpin-zumi");   
	div_obj.css("top",($(window).height()-500)/2);
	div_obj.css("left",($(window).width()-850)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show(); 
}

function hideDiv() {   
  $("#mask").hide();   
  $("#pop-div").hide(); 
  $("#pop-div-futuhaso").hide();
  $("#pop-div-bunnou").hide();
  $("#pop-div-henpin").hide();
  $("#pop-div-henpin-shusei").hide();
  $("#pop-div-henpinhaso").hide();
  $("#pop-div-henpin-zumi").hide();
  $("#pop-div-tuika").hide();
  $("#pop-div-henkin").hide();
  $("#pop-div-dokon").hide();
} 

function hideDivTuika(){
	cleartuika();
	$("#jsonArr").val($("#jsonArr_moto").val());
	hideDiv();
}
function reloadPage(){
	var scrollxVal = document.getElementsByName("scrollx")[0].value;
	var scrollyVal = document.getElementsByName("scrolly")[0].value;
	actionSubmit('A10010201?OrderNo=' + document.getElementsByName("f100102.juchubango")[0].value + '&scrollx = ' + scrollxVal + '&scrolly = ' + scrollyVal);
}
function showBunnoDetail(flg){
	if(flg){
		setStatusAction('1&5','3&1');
	}else{
		setStatusAction('0&2','3&1');
	}
	
}
function showShikyuDetail(flg){
	if(flg){
		setStatusAction('1','2');
	}else{
		setStatusAction('0','2');
	}
}

function showStatusDetail(flg){
	if(flg){
	    var nowStatus = document.getElementsByName("f100102.chumonsts1")[0].value;
	    $("#statusSelect").empty();
	    if("0" == nowStatus){
	    	$("<option value='0'>新規</option>").appendTo("#statusSelect");
	    	$("<option value='1'>発送前確認</option>").appendTo("#statusSelect");
	    	$("<option value='2'>発送待ち</option>").appendTo("#statusSelect");
	    	$("<option value='4'>キャンセル</option>").appendTo("#statusSelect");
	    	$("<option value='6'>その他</option>").appendTo("#statusSelect");
	    	$("#statusSelect").val("0");
	    }else if("1" == nowStatus){
	    	$("<option value='1'>発送前確認</option>").appendTo("#statusSelect");
	    	$("<option value='2'>発送待ち</option>").appendTo("#statusSelect");
	    	$("<option value='4'>キャンセル</option>").appendTo("#statusSelect");
	    	$("<option value='6'>その他</option>").appendTo("#statusSelect");
	    	$("#statusSelect").val("1");
	    }else if("2" == nowStatus){
	    	$("<option value='1'>発送前確認</option>").appendTo("#statusSelect");
	    	$("<option value='2'>発送待ち</option>").appendTo("#statusSelect");
	    	$("<option value='4'>キャンセル</option>").appendTo("#statusSelect");
	    	$("<option value='6'>その他</option>").appendTo("#statusSelect");
	    	$("#statusSelect").val("2");
	    }else if("3" == nowStatus){
	    	$("<option value='3'>処理済</option>").appendTo("#statusSelect");
	    	$("#statusSelect").val("3");
	    }else if("4" == nowStatus){
	    	$("<option value='1'>発送前確認</option>").appendTo("#statusSelect");
	    	$("<option value='2'>発送待ち</option>").appendTo("#statusSelect");
	    	$("<option value='4'>キャンセル</option>").appendTo("#statusSelect");
	    	$("<option value='6'>その他</option>").appendTo("#statusSelect");
	    	$("#statusSelect").val("4");
	    }else if("6" == nowStatus){
	    	$("<option value='1'>発送前確認</option>").appendTo("#statusSelect");
	    	$("<option value='2'>発送待ち</option>").appendTo("#statusSelect");
	    	$("<option value='4'>キャンセル</option>").appendTo("#statusSelect");
	    	$("<option value='6'>その他</option>").appendTo("#statusSelect");
	    	$("#statusSelect").val("6");
	    }
	    $("#statusSelect").show();
	    $("#stsBtn1").hide();
	    $("#statusSelect").focus();
	}else{
		$("#statusSelect").hide();
		$("#stsBtn1").show();
	}
}
function setTd(){
	var value1 = document.getElementsByName("f100102.chumonsts1")[0].value;
	if("0" == value1){
		$("#stsTd").html("新規");
    }else if("1" == value1){
    	$("#stsTd").html("発送前確認");
    }else if("2" == value1){
    	$("#stsTd").html("発送待ち");
    }else if("3" == value1){
    	$("#stsTd").html("処理済");
    }else if("4" == value1){
    	$("#stsTd").html("キャンセル");
    }else if("5" == value1){
    	$("#stsTd").html("分納・交換・返品・追加");
    }else if("6" == value1){
    	$("#stsTd").html("その他");
    }
}
function setStatus(status){
	var text = "";
	if("0" == status){
		text = "新規に設定しますか？";
    }else if("1" == status){
    	text = "発送前確認に設定しますか？";
    }else if("2" == status){
    	text = "発送待ちに設定しますか？";
    }else if("3" == status){
    	text = "処理済に設定しますか？";
    }else if("4" == status){
    	text = "キャンセルに設定しますか？";
    }else if("5" == status){
    	text = "分納・交換・返品・追加に設定しますか？";
    }else if("6" == status){
    	text = "その他に設定しますか？";
    }
	
	if(confirm(text)){
		setStatusAction(status,'1');
	}
}
function setDisplay(){
	var value2 = document.getElementsByName("f100102.chumonsts2")[0].value;
	var value3 = document.getElementsByName("f100102.chumonsts3")[0].value;
	setTd();
    $("#statusSelect").blur();
    if(value2 == "1"){
		$("#shikyuSts").html("<font color='red'>至急発送</font>");
		$("#shikyuBtn1").hide();
		$("#shikyuBtn2").show();
	}else{
        $("#shikyuSts").html("なし");
        $("#shikyuBtn2").hide();
	    $("#shikyuBtn1").show();
	}
    if(value3 == "1"){
		$("#bunnouSts").html("<font color='green'>分納可</font>");
		$("#bunnouBtn1").hide();
		$("#bunnouBtn2").show();
	}else{
		$("#bunnouSts").html("なし");
		$("#bunnouBtn2").hide();
		$("#bunnouBtn1").show();
	}
    
    setButtonDisable();
}
function setStatusAction(statusVal,shubetsu){
	$.post("A10010202", {
		status : statusVal,
		statusShubetsu : shubetsu,
		chumonbango : document.getElementsByName("f100102.juchubango")[0].value,
	}, function(result) {
		if (result == "true") {
			if("1" == shubetsu){
			    document.getElementsByName("f100102.chumonsts1")[0].value = statusVal;
			}else if("2" == shubetsu){
			    document.getElementsByName("f100102.chumonsts2")[0].value = statusVal;
			}else if("3" == shubetsu){
			    document.getElementsByName("f100102.chumonsts3")[0].value = statusVal;
			}else if("4" == shubetsu){
			    document.getElementsByName("f100102.chumonsts4")[0].value = statusVal;
			}else if("5" == shubetsu){
			    document.getElementsByName("f100102.chumonsts5")[0].value = statusVal;
			}else if("6" == shubetsu){
			    document.getElementsByName("f100102.chumonsts6")[0].value = statusVal;
			}
	        alert("設定成功しました！");
	        //setDisplay();
	        reloadPage();
		}else{
			alert("設定失敗しました！");
		}
	}, "json");
}
function setResult(result){
	if(result){
	    $("#hid_result").val("true");
	}else{
		$("#hid_result").val("false");
	}
}

function getResult(){
	if("true" == $("#hid_result").val()){
		return true;
	}else{
		return false;
	}
}

function setButtonDisable(){
	var status = document.getElementsByName("f100102.chumonsts1")[0].value;
	if("0" == status){
		$("#stsBtn1").attr("disabled",false);
		$("#shikyuBtn1").attr("disabled",true);
		$("#bunnouBtn1").attr("disabled",true);
		//$("#hassouBtn1").attr("disabled",true);
		$("#henpinBtn1").attr("disabled",true);
		$("#koukanBtn1").attr("disabled",true);
		$("#tuikaBtn1").attr("disabled",true);
    }else if("1" == status){
    	$("#stsBtn1").attr("disabled",false);
		$("#shikyuBtn1").attr("disabled",true);
		$("#bunnouBtn1").attr("disabled",true);
		//$("#hassouBtn1").attr("disabled",true);
		$("#henpinBtn1").attr("disabled",true);
		$("#koukanBtn1").attr("disabled",true);
		$("#tuikaBtn1").attr("disabled",true);
    }else if("2" == status){
    	$("#stsBtn1").attr("disabled",false);
		$("#shikyuBtn1").attr("disabled",false);
		$("#bunnouBtn1").attr("disabled",false);
		//$("#hassouBtn1").attr("disabled",false);
		$("#henpinBtn1").attr("disabled",true);
		$("#koukanBtn1").attr("disabled",true);
		$("#tuikaBtn1").attr("disabled",true);
    }else if("3" == status){
    	$("#stsBtn1").attr("disabled",true);
		$("#shikyuBtn1").attr("disabled",true);
		$("#bunnouBtn1").attr("disabled",true);
		//$("#hassouBtn1").attr("disabled",true);
		$("#henpinBtn1").attr("disabled",false);
		$("#koukanBtn1").attr("disabled",false);
		$("#tuikaBtn1").attr("disabled",false);
    }else if("4" == status){
    	$("#stsBtn1").attr("disabled",false);
		$("#shikyuBtn1").attr("disabled",true);
		$("#bunnouBtn1").attr("disabled",true);
		//$("#hassouBtn1").attr("disabled",true);
		$("#henpinBtn1").attr("disabled",true);
		$("#koukanBtn1").attr("disabled",true);
		$("#tuikaBtn1").attr("disabled",true);
    }else if("5" == status){
    	$("#stsBtn1").attr("disabled",true);
		$("#shikyuBtn1").attr("disabled",false);
		$("#bunnouBtn1").attr("disabled",false);
		//$("#hassouBtn1").attr("disabled",false);
		$("#henpinBtn1").attr("disabled",false);
		$("#koukanBtn1").attr("disabled",false);
		$("#tuikaBtn1").attr("disabled",false);
    }else if("6" == status){
    	$("#stsBtn1").attr("disabled",false);
		$("#shikyuBtn1").attr("disabled",true);
		$("#bunnouBtn1").attr("disabled",true);
		//$("#hassouBtn1").attr("disabled",true);
		$("#henpinBtn1").attr("disabled",true);
		$("#koukanBtn1").attr("disabled",true);
		$("#tuikaBtn1").attr("disabled",true);
    }else {
    	$("#stsBtn1").attr("disabled",true);
		$("#shikyuBtn1").attr("disabled",true);
		$("#bunnouBtn1").attr("disabled",true);
		//$("#hassouBtn1").attr("disabled",true);
		$("#henpinBtn1").attr("disabled",true);
		$("#koukanBtn1").attr("disabled",true);
		$("#tuikaBtn1").attr("disabled",true);
    }
}

function showFutuhasoDetail(flg){
	if(flg){
		popupDivFustuhasou();
	}else{
	}
}

function showBunnouDetail(flg){
	if(flg){
		popupDivBunnouhasou();
	}else{
	}
}

function showHenpinDetail(flg){
	if(flg){
		popupDivHenpin();
	}else{
	}
}

function showTuikaDetail(flg,type){
	if(flg){
		popupDivTuika(type);
	}else{
	}
}

function showHenpinshuseiDetail(flg,shoribango){
	if(flg){
		popupDivHenpinhasoushusei();
		clearhenpin();
		setValue(shoribango);
	}else{
	}
}

function showHenpinzumiDetail(flg,shoribango){
	if(flg){
		popupDivHenpinzumi();
		setValuezumi(shoribango);
		setHenpinZumiValue(shoribango);
	}else{
	}
}

function showHenpinhasoDetail(flg,shoribango){
	if(flg){
		$('input[name="f100102.shoribango_henpinhaso"]').val(shoribango);
		popupDivHenpinhaso();
		setHenpinHasoValue(shoribango);	
	}else{
	}
}


function setHenpinHasoValue(shoribango){
	for (var i = 0; document.getElementById(shoribango + (i+"")) != null; i++) {
		document.getElementById(shoribango + (i+"")).style.display = "";
	}
	
}

function setHenpinZumiValue(shoribango){
	for (var i = 0; document.getElementById(shoribango + (i+"") + "_zumi") != null; i++) {
		
		document.getElementById(shoribango + (i+"") + "_zumi").style.display = "";
		document.getElementById(shoribango + (i+"") + "_check").childNodes[0].checked = true;
	}
	
}

function hideDivHenpinhaso(){
	var shoribango = $('input[name="f100102.shoribango_henpinhaso"]').val();
	for (var i = 0; document.getElementById(shoribango + (i+"")) != null; i++) {
		document.getElementById(shoribango + (i+"")).style.display = "none";
	}
	$('input[name="f100102.shoribango_henpinhaso"]').val("");
	$('input[name="f100102.toiawasebango_henpinhaso"]').val("");
	hideDiv();

}

function setValue(shoribango){
	for (var i = 0; typeof (document
            .getElementsByName("f100102.henpinList[" + i
                    + "].henpinshoribango")[0]) != "undefined"; i++) {
		if(document.getElementsByName("f100102.henpinList[" + i + "].henpinshoribango")[0].value == shoribango){
			for (var j = 0; typeof (document
		            .getElementsByName("f100102.henpinList[" + i
		                    + "].henpinzumiList[" + j  + "].shohinbango")[0]) != "undefined"; j++) {
				var shohinbango = document.getElementsByName("f100102.henpinList[" + i + "].henpinzumiList[" + j  + "].shohinbango")[0].value;
				var henpinkosu = document.getElementsByName("f100102.henpinList[" + i + "].henpinzumiList[" + j  + "].kosu")[0].value;
				var shubetsu = document.getElementsByName("f100102.henpinList[" + i + "].henpinzumiList[" + j  + "].shubetsu")[0].value;
				var sairiyokano = document.getElementsByName("f100102.henpinList[" + i + "].henpinzumiList[" + j  + "].sairiyokanoflg")[0].value;
				var kakuninhituyo = document.getElementsByName("f100102.henpinList[" + i + "].henpinzumiList[" + j  + "].kakuninflg")[0].value;
				
				for (var k = 0; typeof (document
			            .getElementsByName("f100102.henpinshuseikaShohinList[" + k
			                    + "].shohinbango")[0]) != "undefined"; k++) {
					if(shohinbango == $('input[name="f100102.henpinshuseikaShohinList['+ k +'].shohinbango"]').val() 
							&& shubetsu == $('input[name="f100102.henpinshuseikaShohinList['+ k +'].hassoushubetsu"]').val()){
						$('input[name="f100102.henpinshuseikaShohinList['+ k +'].checkdFlg"]')[0].checked = true;
						$('input[name="f100102.henpinshuseikaShohinList['+ k +'].henpinkosu"]').val(henpinkosu);
						if(sairiyokano == "1"){
						    $('input[name="f100102.henpinshuseikaShohinList['+ k +'].sairiyokano"]')[0].checked = true;
						}
						if(kakuninhituyo =="1"){
						    $('input[name="f100102.henpinshuseikaShohinList['+ k +'].kakuninhituyo"]')[0].checked = true;
						}
					}
				}
			}
		}
	}
	
	for(var i = 0; typeof (document.getElementsByName("f100102.henpinshuseikaList[" + i + "].shoribango")[0]) != "undefined"; i++){
		if(shoribango == document.getElementsByName("f100102.henpinshuseikaList[" + i + "].shoribango")[0].value){
			$('input[name="f100102.shoribango_henpinshusei"]').val($('input[name="f100102.henpinshuseikaList['+ i +'].shoribango"]').val());
			var riyutenpo = $('input[name="f100102.henpinshuseikaList['+ i +'].henpinriyutenpogawa"]').val();
			var riyutenpoArr = riyutenpo.split("&");
			if(riyutenpoArr != ""){
			for(var j=0;j<riyutenpoArr.length;j++){
		        $('input[name="f100102.henpinriyuTenpo_henpinshusei"]')[riyutenpoArr[j]].checked = true;
			}
			}
			$('input[name="f100102.henpinriyuTenpoSonota_henpinshusei"]').val($('input[name="f100102.henpinshuseikaList['+ i +'].henpinriyutenpogawasonota"]').val());
			var riyuokyaku = $('input[name="f100102.henpinshuseikaList['+ i +'].henpinriyuokyakugawa"]').val();
			var riyuokyakuArr = riyuokyaku.split("&");
			if(riyuokyakuArr != ""){
			for(var j=0;j<riyuokyakuArr.length;j++){
		        $('input[name="f100102.henpinriyuOkyaku_henpinshusei"]')[riyuokyakuArr[j]].checked = true;
			}
			}
			$('input[name="f100102.henpinriyuOkyakuSonota_henpinshusei"]').val($('input[name="f100102.henpinshuseikaList['+ i +'].henpinriyuokyakugawasonota"]').val());
			$('input[name="f100102.henpinsoryofutanshokihasou_henpinshusei"]')[$('input[name="f100102.henpinshuseikaList['+ i +'].shokihasosoryofutan"]').val()].checked = true;
			$('input[name="f100102.henpinsoryofutanuketori_henpinshusei"]')[$('input[name="f100102.henpinshuseikaList['+ i +'].uketorisoryofutan"]').val()].checked = true;
			
			
			document.getElementsByName("f100102.henpinhenkinhituyoFlg_henpinshusei")[0].disabled = false;
			document.getElementsByName("f100102.henpinhenkinkigaku_henpinshusei")[0].disabled = false;

			for (var j = 0; typeof (document.getElementsByName("f100102.henkinList[" + j + "].shoribango")[0]) != "undefined"; j++) {
		        if(document.getElementsByName("f100102.henkinList[" + j + "].shoribango")[0].value == shoribango){
		        	
		        	if(document.getElementsByName("f100102.henkinList[" + j + "].henkinzumiflg")[0].value == "1"){
		        		document.getElementsByName("f100102.henpinhenkinhituyoFlg_henpinshusei")[0].disabled = true;
						document.getElementsByName("f100102.henpinhenkinkigaku_henpinshusei")[0].disabled = true;
		        	}
		        	if($('input[name="f100102.henpinshuseikaList['+ i +'].henkinhistsuyoflg"]').val() == "1"){
	    				$('input[name="f100102.henpinhenkinhituyoFlg_henpinshusei"]')[0].checked = true;
	    			}
	    			$('input[name="f100102.henpinhenkinkigaku_henpinshusei"]').val($('input[name="f100102.henpinshuseikaList['+ i +'].henkinkagaku"]').val());
		        }
		    }
			
			
			//if($('input[name="f100102.henpinshuseikaList['+ i +'].sairiyokanoflg"]').val() == "1"){
				//$('input[name="f100102.henpinSairiyoukanoFlg_henpinshusei"]')[0].checked = true;
			//}
			$('textarea[name="f100102.henpinbiko_henpinshusei"]').val($('input[name="f100102.henpinshuseikaList['+ i +'].biko"]').val());
			
		}
	}
}

function setValuezumi(shoribango){
	
	for(var i = 0; typeof (document.getElementsByName("f100102.henpinshuseikaList[" + i + "].shoribango")[0]) != "undefined"; i++){
		if(shoribango == document.getElementsByName("f100102.henpinshuseikaList[" + i + "].shoribango")[0].value){
			
			$('input[name="f100102.shoribango_henpinzumi"]').val($('input[name="f100102.henpinshuseikaList['+ i +'].shoribango"]').val());
			var riyutenpo = $('input[name="f100102.henpinshuseikaList['+ i +'].henpinriyutenpogawa"]').val();
			var riyutenpoArr = riyutenpo.split("&");
			if(riyutenpoArr != ""){
			for(var j=0;j<riyutenpoArr.length;j++){
		        $('input[name="f100102.henpinriyuTenpo_henpinzumi"]')[riyutenpoArr[j]].checked = true;
			}
			}
			$('input[name="f100102.henpinriyuTenpoSonota_henpinzumi"]').val($('input[name="f100102.henpinshuseikaList['+ i +'].henpinriyutenpogawasonota"]').val());
			var riyuokyaku = $('input[name="f100102.henpinshuseikaList['+ i +'].henpinriyuokyakugawa"]').val();
			var riyuokyakuArr = riyuokyaku.split("&");
			if(riyuokyakuArr != ""){
			for(var j=0;j<riyuokyakuArr.length;j++){
		        $('input[name="f100102.henpinriyuOkyaku_henpinzumi"]')[riyuokyakuArr[j]].checked = true;
			}
			}
			$('input[name="f100102.henpinriyuOkyakuSonota_henpinzumi"]').val($('input[name="f100102.henpinshuseikaList['+ i +'].henpinriyuokyakugawasonota"]').val());
			$('input[name="f100102.henpinsoryofutanshokihasou_henpinzumi"]')[$('input[name="f100102.henpinshuseikaList['+ i +'].shokihasosoryofutan"]').val()].checked = true;
			$('input[name="f100102.henpinsoryofutanuketori_henpinzumi"]')[$('input[name="f100102.henpinshuseikaList['+ i +'].uketorisoryofutan"]').val()].checked = true;
			if($('input[name="f100102.henpinshuseikaList['+ i +'].henkinhistsuyoflg"]').val() == "1"){
				$('input[name="f100102.henpinhenkinhituyoFlg_henpinzumi"]')[0].checked = true;
			}
			//$('input[name="f100102.henpinhenkinkigaku_henpinzumi"]').val($('input[name="f100102.henpinshuseikaList['+ i +'].henkinkagaku"]').val());
			//if($('input[name="f100102.henpinshuseikaList['+ i +'].sairiyokanoflg"]').val() == "1"){
			//	$('input[name="f100102.henpinSairiyoukanoFlg_henpinzumi"]')[0].checked = true;
		//	}
			$('textarea[name="f100102.henpinbiko_henpinzumi"]').val($('input[name="f100102.henpinshuseikaList['+ i +'].biko"]').val());
		}
	}
}

function clearhenpin(){
	$('input[name="f100102.shoribango_henpinshusei"]').val("");
	$('input[name="f100102.henpinriyuTenpo_henpinshusei"]')[0].checked = false;
	$('input[name="f100102.henpinriyuTenpo_henpinshusei"]')[1].checked = false;
	$('input[name="f100102.henpinriyuTenpo_henpinshusei"]')[2].checked = false;
	$('input[name="f100102.henpinriyuTenpo_henpinshusei"]')[3].checked = false;
	$('input[name="f100102.henpinriyuTenpoSonota_henpinshusei"]').val("");
	$('input[name="f100102.henpinriyuOkyaku_henpinshusei"]')[0].checked = false;
	$('input[name="f100102.henpinriyuOkyaku_henpinshusei"]')[1].checked = false;
	$('input[name="f100102.henpinriyuOkyaku_henpinshusei"]')[2].checked = false;
	$('input[name="f100102.henpinriyuOkyaku_henpinshusei"]')[3].checked = false;
	$('input[name="f100102.henpinriyuOkyakuSonota_henpinshusei"]').val("");
	$('input[name="f100102.henpinsoryofutanshokihasou_henpinshusei"]')[0].checked = true;
	$('input[name="f100102.henpinsoryofutanuketori_henpinshusei"]')[0].checked = true;
	$('input[name="f100102.henpinhenkinhituyoFlg_henpinshusei"]')[0].checked = false;
	$('input[name="f100102.henpinhenkinkigaku_henpinshusei"]').val("");
	//$('input[name="f100102.henpinSairiyoukanoFlg_henpinshusei"]')[0].checked = false;
	$('textarea[name="f100102.henpinbiko_henpinshusei"]').val("");
	
	
	for (var i = 0; typeof (document
            .getElementsByName("f100102.henpinshuseikaShohinList[" + i
                    + "].shohinbango")[0]) != "undefined"; i++) {
		$('input[name="f100102.henpinshuseikaShohinList['+ i +'].checkdFlg"]')[0].checked = false;
		$('input[name="f100102.henpinshuseikaShohinList['+ i +'].henpinkosu"]').val("0");
    }
}

function clearhenpinzumi(shoribango){
	$('input[name="f100102.shoribango_henpinzumi"]').val("");
	$('input[name="f100102.henpinriyuTenpo_henpinzumi"]')[0].checked = false;
	$('input[name="f100102.henpinriyuTenpo_henpinzumi"]')[1].checked = false;
	$('input[name="f100102.henpinriyuTenpo_henpinzumi"]')[2].checked = false;
	$('input[name="f100102.henpinriyuTenpo_henpinzumi"]')[3].checked = false;
	$('input[name="f100102.henpinriyuTenpoSonota_henpinzumi"]').val("");
	$('input[name="f100102.henpinriyuOkyaku_henpinzumi"]')[0].checked = false;
	$('input[name="f100102.henpinriyuOkyaku_henpinzumi"]')[1].checked = false;
	$('input[name="f100102.henpinriyuOkyaku_henpinzumi"]')[2].checked = false;
	$('input[name="f100102.henpinriyuOkyaku_henpinzumi"]')[3].checked = false;
	$('input[name="f100102.henpinriyuOkyakuSonota_henpinzumi"]').val("");
	$('input[name="f100102.henpinsoryofutanshokihasou_henpinzumi"]')[0].checked = true;
	$('input[name="f100102.henpinsoryofutanuketori_henpinzumi"]')[0].checked = true;
	$('input[name="f100102.henpinhenkinhituyoFlg_henpinzumi"]')[0].checked = false;
	$('input[name="f100102.henpinhenkinkigaku_henpinzumi"]').val("");
	//$('input[name="f100102.henpinSairiyoukanoFlg_henpinzumi"]')[0].checked = false;
	$('textarea[name="f100102.henpinbiko_henpinzumi"]').val("");

	for (var i = 0; document.getElementById(shoribango + (i+"") + "_zumi") != null; i++) {
		document.getElementById(shoribango + (i+"") + "_zumi").style.display = "none";
		document.getElementById(shoribango + (i+"") + "_check").childNodes[0].checked = false;
	}
}

function cleartuika(){
	$('input[name="f100102.tuikariyu"]')[0].checked = false;
	$('input[name="f100102.tuikariyu"]')[1].checked = false;
	$('input[name="f100102.tuikariyu"]')[2].checked = false;
	$('input[name="f100102.tuikariyuSonota"]').val("");
	$('input[name="f100102.tuikasoryofutan"]')[0].checked = true;
	$('textarea[name="f100102.tuikabiko"]').val("");
	$('#jsonArr').val("");
	$('#tuikaShohin').val("");
	$('#tuikaKosu').val("");
	$("#tuikahasoListDiv").html("");	
	$('input[name="f100102.toiawasebango_tuikahaso"]').val("");	
}

function hideDivZumi(shoribango){
	clearhenpinzumi(shoribango);
	hideDiv();
}
function orderDetail(){
	//window.location.href='A10010301?orderNo='+document.getElementsByName('f100102.juchubango')[0].value;
	window.open('A10010301?orderNo='+document.getElementsByName('f100102.juchubango')[0].value);
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

#div1 {
	width: 99%;
	position: relative;
	top: 10px;
	height: 10%;
	margin-left: auto;
	margin-right: auto;
}

#div2 {
	top: 40px;
	width: 99%;
	overflow-x: hidden;
	position: relative;
	margin-left: auto;
	margin-right: auto;
}

#div3 {
	width: 99%;
	position: relative;
	top: 39px;
	margin-left: auto;
	margin-right: auto;
}

#div4 {
	width: 99%;
	position: relative;
	top: 50px;
	margin-left: auto;
	margin-right: auto;
}

#div5 {
	top: 40px;
	width: 99%;
	overflow-x: hidden;
	position: relative;
	margin-left: auto;
	margin-right: auto;
}

.pop-box {
	z-index: 9999; /*这个数值要足够大，才能够显示在最上层*/
	margin-bottom: 3px;
	position: absolute;
	background: #FFF;
	border: solid 1px #6e8bde;
}

.pop-box h4 {
	color: #FFF;
	cursor: default;
	height: 18px;
	font-size: 14px;
	font-weight: bold;
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
	color: #C7EDCC;
	background-color: #C7EDCC;
	position: absolute;
	top: 0px;
	left: 0px;
	filter: Alpha(Opacity = 60);
	-moz-opacity: .6;
	opacity: 0.6;
	z-index: 1000;
}
-->
</style>
</head>
<body onload="init();">
	<div id='mask' class="mask"
		style="width: 100%; height: 500%; display: none"></div>
	<s:form name="form1" theme="simple">
		<div style="width: 90%; margin-top: 5px; margin-left: 10px">
			<h2>
				<s:label name="title" />
			</h2>
			<hr>

			<table cellspacing="1" cellpadding="2" width="800px" class="table">
				<tr>
					<s:if
						test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
						<s:if test="f100102.chumonsts1 != 7">
							<!--<td style="font-size:13px"><a target="_blank" href="javascript:actionSubmit('A10010301?orderNo='+document.getElementsByName('f100102.juchubango')[0].value);">▲注文情報修正</a></td>-->
							<td style="font-size: 13px"><a a
								href="javascript:orderDetail();">▲注文情報修正</a></td>
						</s:if>
					</s:if>
					<s:if test="f100102.site == '楽天'">
						<td style="font-size: 13px"><a id="mailUrl1" target="_blank"
							href="https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO03_001_007&order_number=<s:property value="f100102.juchubango"/>">▲メール送信</a></td>
						<td style="font-size: 13px"><a id="rakutenUrl"
							target="_blank"
							href="https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number=<s:property value="f100102.juchubango"/>">▲RMSから開く
						</a></td>
						<td style="font-size: 13px"><a href="#">▲RMSから最新情報取得</a></td>
					</s:if>
				</tr>
			</table>

		</div>
		<div id="div1">
			<s:actionerror name="err" cssStyle="color:red;font-size:16" />
			<table>
				<tr>
					<td><font color="blue" size="5"><s:label
								name="messageInfo"></s:label> <s:hidden name="messageInfo" /></font></td>
				</tr>
			</table>
			<h3>詳細情報</h3>
			<table cellspacing="1" cellpadding="2" width="99%">
				<tr>
					<td width="60%" valign="top">
						<table cellspacing="1" cellpadding="2" width="100%" class="table">
							<tr class="bg_tr">
								<td width="200px">サイト：</td>
								<td class="td_bg"><s:label name="f100102.site" /> <s:hidden
										name="f100102.site" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">店舗：</td>
								<td class="td_bg"><s:label name="f100102.tenpobetsu" /> <s:hidden
										name="f100102.tenpobetsu" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">同梱：</td>
								<td class="td_bg"><s:if
										test="f100102.dokonFlg == 1 && f100102.site != 'DENA'">
										<s:iterator value="f100102.dokonList" status="status">
											<table>
												<tr>
													<td><a target="_blank"
														href="A10010201?OrderNo=<s:property value='juchubango'/>"><s:property
																value='juchubango' /></a>&nbsp;(<s:property value='type' />)
														<s:hidden
															name="f100102.dokonList[%{#status.index}].juchubango"
															value="%{juchubango}" /> <s:hidden
															name="f100102.dokonList[%{#status.index}].type"
															value="%{type}" /></td>
												</tr>
											</table>
										</s:iterator>
									</s:if> <s:else>なし</s:else> <s:if
										test="(f100102.chumonsts1 == 1 || f100102.chumonsts1 == 2 || f100102.chumonsts3 == 1) && f100102.site != 'DENA' ">
										<input type="button" onclick="popDokon()" value="設定">
									</s:if></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">受注アイコン：</td>
								<td class="td_bg"><s:if test="f100102.dokonFlg == 1">
										<img src="icon/package.gif">&nbsp;&nbsp;&nbsp;</s:if> <s:if
										test="f100102.okurisakuchuiFlg == 0">
										<img src="icon/gift.gif">&nbsp;&nbsp;&nbsp;</s:if> <s:if
										test="f100102.mobileMailFlg == 1">
										<img src="icon/mobile.gif">&nbsp;&nbsp;&nbsp;</s:if> <s:if
										test="f100102.asurakuFlg == 1">
										<img src="icon/icn_asuraku_on.gif">&nbsp;&nbsp;&nbsp;</s:if> <s:hidden
										name="f100102.dokonFlg" /> <s:hidden
										name="f100102.okurisakuchuiFlg" /> <s:hidden
										name="f100102.mobileMailFlg" /> <s:hidden
										name="f100102.asurakuFlg" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">受注番号 ：</td>
								<td class="td_bg" id="td01"><s:label
										name="f100102.juchubango" /> <s:hidden
										name="f100102.juchubango" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">注文日時：</td>
								<td class="td_bg"><s:label name="f100102.chumonnichiji" />
									<s:hidden name="f100102.chumonnichiji" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">订单导入日：</td>
								<td class="td_bg"><s:label name="f100102.dingdandaoruri" />
									<s:hidden name="f100102.dingdandaoruri" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">注文者情報：</td>
								<td class="td_bg"><s:label name="f100102.chumonshanamae" />
									<s:hidden name="f100102.chumonshanamae" /><br /> <s:if
										test="f100102.site == '楽天'">
										<a target="_blank" id="mailUrl2"
											href="https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO03_001_007&order_number=<s:property value="f100102.juchubango"/>"><s:property
												value="f100102.chumonshameruadoresu" /></a>
										<s:hidden name="f100102.chumonshameruadoresu" />
										<br />
									</s:if> <s:else>
										<s:property value="f100102.chumonshameruadoresu" />
										<br />
									</s:else> <s:label name="f100102.chumonshatanjoubi" /> <s:hidden
										name="f100102.chumonshatanjoubi" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">注文者住所：</td>
								<td class="td_bg"><s:label name="f100102.chumonshajusho" />
									<s:hidden name="f100102.chumonshajusho" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">注文者電話番号：</td>
								<td class="td_bg"><s:label
										name="f100102.chumonshadenwabango" /> <s:hidden
										name="f100102.chumonshadenwabango" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">お支払い方法：</td>
								<td class="td_bg" id="td0"><s:label
										name="f100102.oshiharaihoho" /> <s:hidden
										name="f100102.oshiharaihoho" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">配送方法：</td>
								<td class="td_bg"><s:label name="f100102.haisohoho" /> <s:hidden
										name="f100102.haisohoho" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">お届け日指定：</td>
								<td class="td_bg"><s:textfield size="15" cssClass="Wdate"
										onClick="WdatePicker()" maxlength="10"
										name="f100102.otodokebishitei" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">発送約束日：</td>
								<td class="td_bg"><s:textfield size="15" cssClass="Wdate"
										onClick="WdatePicker()" maxlength="10"
										name="f100102.hasoyakusokubi" /></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">お届け時間帯：</td>
								<td class="td_bg"><s:select
										list="#{ '':'▼選択してください','01':'午前中','12':'12:00~14:00','14':'14:00~16:00','16':'16:00~18:00','04':'18:00~21:00'}"
										name="f100102.otodokejikantai1" /> <%--                     <s:select list="#{ '0':'--','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24'}" name="f100102.otodokejikantai2" />&nbsp;-&nbsp; --%>
									<%--                     <s:select list="#{ '0':'--','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24'}" name="f100102.otodokejikantai3" /> --%>
								</td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">備考：</td>
								<td class="td_bg"><s:textarea name="f100102.biko"
										style="height:50px;width:500px"></s:textarea></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">発送者へのコメント：</td>
								<td class="td_bg"><s:textarea name="f100102.hasoshahe"
										style="height:50px;width:500px"></s:textarea></td>
							</tr>
							<tr class="bg_tr">
								<td width="200px">入荷不可に関する</td>
								<s:if
									test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
									<td class="td_bg"><input type="button" value="入荷不可、連絡済"
										onclick="setZumi();" /></td>
								</s:if>
							</tr>
							<s:if
								test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
								<tr>
									<td align="center" colspan="2"><input type="button"
										onclick="actionSubmit('A10010220');" value="入力内容を反映する"></td>
								</tr>
							</s:if>
						</table>
					</td>
					<td width="40%" valign="top">
						<table cellspacing="1" cellpadding="2" width="90%">
							<!-- 		        <tr> -->
							<!--                     <td width="200px">処理履歴<hr></td> -->
							<!--                 </tr> -->
							<!--                 <tr> -->
							<!--                     <td width="200px">■受注ステータス</td> -->
							<!--                 </tr> -->
							<!--                 <tr class="bg_tr"> -->
							<!--                 <td class="td_bg" align="center"> -->
							<%--                     <s:textarea name="f100102.stsrireki" readonly="true" style="height:90px;width:600px"></s:textarea> --%>
							<!--                 </td> -->
							<!--                 </tr> -->
							<tr>
								<td width="200px">送信履歴
									<hr>
								</td>
							</tr>
						</table>
						<table width="95%" cellspacing="1" cellpadding="2" class="table">
							<tr class="bg_tr">
								<td class="td_bg" align="center" width="30%">送信タイプ</td>
								<td class="td_bg" align="center" width="20%">送信済みフラグ</td>
								<td class="td_bg" align="center" width="25%">日時</td>
								<td class="td_bg" align="center" width="25%">設定</td>
							</tr>
							<s:iterator value="f100102.soshinList" status="status">
								<tr class="bg_tr">
									<td class="td_bg" align="center" valign="middle"><s:if
											test="soushintype == 0">普通発送済み</s:if> <s:if
											test="soushintype == 1">分納発送済み</s:if> <s:if
											test="soushintype == 2">追加発送済み</s:if> <s:if
											test="soushintype == 3">交換発送済み</s:if> <s:if
											test="soushintype == 4">返金済み</s:if> <s:hidden
											name="f100102.soshinList[%{#status.index}].soushintype"
											value="%{soushintype}" /></td>
									<td class="td_bg" align="center" valign="middle"><s:if
											test="soushinzumiflg == 0">
											<font color="red">送信待ち</font>
										</s:if> <s:if test="soushinzumiflg == 1">
											<font color="green">送信済み</font>
										</s:if> <s:hidden
											name="f100102.soshinList[%{#status.index}].soushinzumiflg"
											value="%{soushinzumiflg}" /></td>
									<td class="td_bg" align="center" valign="middle"><s:property
											value='setteibi' /> <s:hidden
											name="f100102.soshinList[%{#status.index}].setteibi"
											value="%{setteibi}" /></td>
									<s:if
										test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
										<td class="td_bg" align="center" valign="middle"><s:if
												test="soushinzumiflg == 0">
												<input type="button"
													onclick="setSoshinzumi(<s:property value='shoribango'/>)"
													value="送信済みに設定">
											</s:if> <s:hidden
												name="f100102.soshinList[%{#status.index}].shoribango"
												value="%{shoribango}" /></td>
									</s:if>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
			</table>
			<br />
			<table cellspacing="1" cellpadding="2" width="99%" class="table">
				<tr class="bg_tr">
					<td class="td_bg" colspan="3">■受注ステータス</td>
				</tr>
				<tr class="bg_tr" height="28px">
					<td width="200px">通常ステータス：</td>
					<td class="td_bg" id="stsTd"><s:if
							test="f100102.chumonsts1 == 0">新規</s:if> <s:if
							test="f100102.chumonsts1 == 1">発送前確認</s:if> <s:if
							test="f100102.chumonsts1 == 2">発送待ち</s:if> <s:if
							test="f100102.chumonsts1 == 3">処理済</s:if> <s:if
							test="f100102.chumonsts1 == 4">キャンセル</s:if> <s:if
							test="f100102.chumonsts1 == 5">保留</s:if> <s:if
							test="f100102.chumonsts1 == 6">その他</s:if></td>
					<td class="td_bg" align="center" width="200px"><select
						id="statusSelect" style="display: none"
						onchange="setStatus(this.value);"
						onblur="showStatusDetail(false);">
					</select> <s:hidden name="f100102.chumonsts1" /> <s:if
							test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
							<input type="button" id="stsBtn1"
								onclick="showStatusDetail(true);" value="設定" />
						</s:if></td>
				</tr>
				<tr class="bg_tr" height="28px">
					<td>至急発送フラグ：</td>
					<td class="td_bg" id="shikyuSts"><s:if
							test="f100102.chumonsts2 == 0">なし</s:if> <s:if
							test="f100102.chumonsts2 == 1">
							<font color="red">至急発送</font>
						</s:if></td>
					<td align="center" class="td_bg"><s:if
							test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
							<s:hidden name="f100102.chumonsts2" />
							<s:if test="f100102.chumonsts2 == 0">
								<input type="button" id="shikyuBtn1"
									onclick="showShikyuDetail(true);" value="至急発送に設定" />
								<input type="button" id="shikyuBtn2" style="display: none"
									onclick="showShikyuDetail(false);" value="取消" />
							</s:if>
							<s:if test="f100102.chumonsts2 == 1">
								<input type="button" id="shikyuBtn1" style="display: none"
									onclick="showShikyuDetail(true);" value="至急発送に設定" />
								<input type="button" id="shikyuBtn2"
									onclick="showShikyuDetail(false);" value="取消" />
							</s:if>
						</s:if></td>
				</tr>
				<tr class="bg_tr" height="28px">
					<td>分納フラグ：</td>
					<td class="td_bg" id="bunnouSts"><s:if
							test="f100102.chumonsts3 == 0">なし</s:if> <s:if
							test="f100102.chumonsts3 == 1">
							<font color="green">分納可</font>
						</s:if></td>
					<td align="center" class="td_bg"><s:hidden
							name="f100102.chumonsts3" /> <s:if
							test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
							<s:if test="f100102.hasouzumiFlg == 1">
								<input type="button" disabled value="分納可に設定" />
							</s:if>
							<s:else>
								<s:if test="f100102.chumonsts3 == 0">
									<input type="button" id="bunnouBtn1"
										onclick="showBunnoDetail(true);" value="分納可に設定" />
									<input type="button" id="bunnouBtn2" style="display: none"
										onclick="showBunnoDetail(false);" value="取消" />
								</s:if>
								<s:if test="f100102.chumonsts3 == 1">
									<input type="button" id="bunnouBtn1" style="display: none"
										onclick="showBunnoDetail(true);" value="分納可に設定" />
									<s:if test="f100102.bunnotorikeshikaFlg == 1">
										<input type="button" id="bunnouBtn2"
											onclick="showBunnoDetail(false);" value="取消" />
									</s:if>
									<s:if test="f100102.bunnotorikeshikaFlg == 0">
										<input type="button" id="bunnouBtn2" disabled value="取消" />
									</s:if>
								</s:if>
							</s:else>
						</s:if></td>
				</tr>
				<tr class="bg_tr" height="28px">
					<td>発送：</td>
					<td class="td_bg" id="hassouSts"><s:if
							test="f100102.hasouzumiFlg == 1">
							<table cellspacing="1" cellpadding="2" width="98%">
								<tr>
									<td>発送ステータス:<font color="green">発送済み</font></td>
									<td>発送日時:<s:label name="f100102.hasonichiji_hasozumi" />
										<s:hidden name="f100102.hasonichiji_hasozumi" /></td>
								<tr>
									<td>配送方法:<s:label name="f100102.haisohoho_hasozumi" /> <s:hidden
											name="f100102.haisohoho_hasozumi" /></td>
									<td>配送会社:<s:label name="f100102.haisokaisha_hasozumi" />
										<s:hidden name="f100102.haisokaisha_hasozumi" /></td>
								</tr>
								<tr>
									<td>問い合わせ番号:<s:label name="f100102.toiawasebango_hasozumi" />
										<s:hidden name="f100102.toiawasebango_hasozumi" /></td>
									<td><s:if
											test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
											<s:if test="f100102.haisokaisha_hasozumi == '佐川急便'">
												<input type="button" value="追跡"
													onclick="window.open ('http://k2k.sagawa-exp.co.jp/p/web/okurijosearch.do?okurijoNo=' + document.getElementsByName('f100102.toiawasebango_hasozumi')[0].value, 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
											</s:if>
											<s:if test="f100102.haisokaisha_hasozumi == 'ヤマト運輸'">
												<input type="button" value="追跡"
													onclick="window.open ('http://toi.kuronekoyamato.co.jp/cgi-bin/tneko', 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
											</s:if>
											<s:if test="f100102.haisokaisha_hasozumi == '郵便局'">
												<input type="button" value="追跡"
													onclick="window.open ('https://trackings.post.japanpost.jp/services/srv/search/input', 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
											</s:if>
										</s:if></td>
								</tr>
							</table>
							<table cellspacing="1" cellpadding="2" class="table" width="98%"
								align="center">
								<tr class="bg_tr">
									<td class="td_bg" width="50%" align="center">商品番号</td>
									<td class="td_bg" width="50%" align="center">個数</td>
								</tr>
								<s:iterator value="f100102.hasozumiList" status="status">
									<tr class="bg_tr">
										<td class="td_bg" align="center" valign="middle"><s:if
												test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
												<a
													href="javascript:getShohinStsInfo('<s:property value='shohinbango'/>')">
													<s:property value='shohinbango' />
												</a>
											</s:if> <s:else>
												<s:property value='shohinbango' />
											</s:else> <s:hidden
												name="f100102.hasozumiList[%{#status.index}].shohinbango"
												value="%{shohinbango}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='kosu' /> <s:hidden
												name="f100102.hasozumiList[%{#status.index}].kosu"
												value="%{kosu}" /></td>
									</tr>
								</s:iterator>
							</table>
						</s:if> <s:if test="f100102.chumonsts1 == 2 && f100102.chumonsts3 == 0">
							<table cellspacing="1" cellpadding="2" width="98%">
								<tr>
									<td>発送ステータス:<font color="red">発送待ち</font></td>
									<td>発送予定日:<s:property value='f100102.hasoyoteibi' /> <s:hidden
											name="f100102.hasoyoteibi" /></td>
								<tr>
							</table>
							<table cellspacing="1" cellpadding="2" class="table" width="98%"
								align="center">
								<tr class="bg_tr">
									<td class="td_bg" width="40%" align="center">商品番号</td>
									<td class="td_bg" width="20%" align="center">個数</td>
									<td class="td_bg" width="40%" align="center">ステータス</td>
								</tr>
								<s:iterator value="f100102.hasomachiList" status="status">
									<tr class="bg_tr">
										<td class="td_bg" align="center" valign="middle"><s:if
												test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
												<a
													href="javascript:getShohinStsInfo('<s:property value='shohinbango'/>')"><s:property
														value='shohinbango' /></a>
											</s:if> <s:else>
												<s:property value='shohinbango' />
												</a>
											</s:else> <s:hidden
												name="f100102.hasomachiList[%{#status.index}].shohinbango"
												value="%{shohinbango}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='kosu' /> <s:hidden
												name="f100102.hasomachiList[%{#status.index}].kosu"
												value="%{kosu}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='sutetasu' /> <s:hidden
												name="f100102.hasomachiList[%{#status.index}].sutetasu"
												value="%{sutetasu}" /></td>
									</tr>
								</s:iterator>
							</table>
						</s:if> <s:if test="f100102.chumonsts3 == 1">
							<table cellspacing="1" cellpadding="2" width="98%">
								<tr>
									<td>発送ステータス:分納</td>
								<tr>
							</table>
							<table cellspacing="1" cellpadding="2" width="98%">
								<tr>
									<td><font color="green">発送済みリスト</font></td>
								<tr>
							</table>
							<table cellspacing="1" cellpadding="2" class="table" width="98%"
								align="center">
								<tr class="bg_tr">
									<td class="td_bg" align="center" width="20%">商品番号</td>
									<td class="td_bg" align="center" width="5%">個数</td>
									<td class="td_bg" align="center" width="15%">発送日時</td>
									<td class="td_bg" align="center" width="15%">配送会社</td>
									<td class="td_bg" align="center" width="10%">配送方法</td>
									<td class="td_bg" align="center" width="20%">問い合わせ番号</td>
									<td class="td_bg" align="center">&nbsp;</td>
								</tr>
								<s:iterator value="f100102.bunnohasozumiList" status="status">
									<tr class="bg_tr">
										<td class="td_bg" align="center" valign="middle"><s:if
												test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
												<a
													href="javascript:getShohinStsInfo('<s:property value='shohinbango'/>')"><s:property
														value='shohinbango' /></a>
											</s:if> <s:else>
												<s:property value='shohinbango' />
												</a>
											</s:else> <s:hidden
												name="f100102.bunnohasozumiList[%{#status.index}].shohinbango"
												value="%{shohinbango}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='kosu' /> <s:hidden
												name="f100102.bunnohasozumiList[%{#status.index}].kosu"
												value="%{kosu}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='hasonichiji' /> <s:hidden
												name="f100102.bunnohasozumiList[%{#status.index}].hasonichiji"
												value="%{hasonichiji}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='haisokaisha' /> <s:hidden
												name="f100102.bunnohasozumiList[%{#status.index}].haisokaisha"
												value="%{haisokaisha}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='haisohoho' /> <s:hidden
												name="f100102.bunnohasozumiList[%{#status.index}].haisohoho"
												value="%{haisohoho}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='toiawasebango' /> <s:hidden
												name="f100102.bunnohasozumiList[%{#status.index}].toiawasebango"
												value="%{toiawasebango}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:if
												test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
												<s:if test="haisokaisha == '佐川急便'">
													<input type="button" value="追跡"
														onclick="window.open ('http://k2k.sagawa-exp.co.jp/p/web/okurijosearch.do?okurijoNo=' + <s:property value='toiawasebango'/>, 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
												</s:if>
												<s:if test="haisokaisha == 'ヤマト運輸'">
													<input type="button" value="追跡"
														onclick="window.open ('http://toi.kuronekoyamato.co.jp/cgi-bin/tneko', 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
												</s:if>
												<s:if test="haisokaisha == '郵便局'">
													<input type="button" value="追跡"
														onclick="window.open ('https://trackings.post.japanpost.jp/services/srv/search/input', 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
												</s:if>
											</s:if></td>
									</tr>
								</s:iterator>
							</table>
							<table cellspacing="1" cellpadding="2" width="98%">
								<tr>
									<td><font color="red">発送待ちリスト</font></td>
								<tr>
							</table>
							<table cellspacing="1" cellpadding="2" class="table" width="98%"
								align="center">
								<tr class="bg_tr">
									<td class="td_bg" width="40%" align="center">商品番号</td>
									<td class="td_bg" width="20%" align="center">個数</td>
									<td class="td_bg" width="40%" align="center">ステータス</td>
								</tr>
								<s:iterator value="f100102.bunnohasomachiList" status="status">
									<tr class="bg_tr">
										<td class="td_bg" align="center" valign="middle"><s:if
												test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
												<a
													href="javascript:getShohinStsInfo('<s:property value='shohinbango'/>')"><s:property
														value='shohinbango' /></a>
											</s:if> <s:else>
												<s:property value='shohinbango' />
												</a>
											</s:else> <s:hidden
												name="f100102.bunnohasomachiList[%{#status.index}].shohinbango"
												value="%{shohinbango}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='kosu' /> <s:hidden
												name="f100102.bunnohasomachiList[%{#status.index}].kosu"
												value="%{kosu}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='sutetasu' /> <s:hidden
												name="f100102.bunnohasomachiList[%{#status.index}].sutetasu"
												value="%{sutetasu}" /></td>
									</tr>
								</s:iterator>
							</table>
						</s:if></td>
					<td align="center" class="td_bg"><s:if
							test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
							<s:if test="f100102.hasouzumiFlg == 1">
								<input id="hassouBtn1" type="button" disabled value="発送" />
							</s:if>
							<s:if test="f100102.chumonsts1 == 2 && f100102.chumonsts3 == 0">
								<s:if test="f100102.futuhasokanoFlg == 0">
									<input type="button" disabled value="発送" />
								</s:if>
								<s:if test="f100102.futuhasokanoFlg == 1">
									<input id="hassouBtn1" type="button"
										onclick="showFutuhasoDetail(true);" value="発送" />
								</s:if>
							</s:if>
							<s:if test="f100102.chumonsts3 == 1">
								<s:if test="f100102.bunnohasoukaFlg == 0">
									<input type="button" disabled value="発送" />
								</s:if>
								<s:if test="f100102.bunnohasoukaFlg == 1">
									<input id="hassouBtn1" type="button"
										onclick="showBunnouDetail(true);" value="発送" />
								</s:if>
							</s:if>
						</s:if></td>
				</tr>
				<tr class="bg_tr" height="28px">
					<td>返品：</td>
					<s:hidden name="f100102.chumonsts4" />
					<td class="td_bg" id="henpinSts"><s:if
							test="f100102.henpinariFlg == 0">なし</s:if> <s:else>
							<s:iterator value="f100102.henpinList" status="status">
								<table width="98%" align="center">
									<tr>
										<td width="85%">
											<table cellspacing="1" cellpadding="2" width="98%"
												class="table" align="center">
												<tr class="bg_tr">
													<td align="left" width="100px">処理番号:</td>
													<td class="td_bg" align="left" width="13%"><s:property
															value='henpinshoribango' /> <s:hidden
															name="f100102.henpinList[%{#status.index}].henpinshoribango" /></td>
													<td align="left" width="100px">ステータス:</td>
													<td class="td_bg" align="left" width="13%"><s:property
															value='henpinstsName' /> <s:hidden
															name="f100102.henpinList[%{#status.index}].henpinstsName" />
														<s:hidden
															name="f100102.henpinList[%{#status.index}].henpinsts" /></td>
													<td align="left" width="100px">発送日時:</td>
													<td class="td_bg" align="left" width="13%"><s:property
															value='henpinhasonichiji' /> <s:hidden
															name="f100102.henpinList[%{#status.index}].henpinhasonichiji" /></td>
													<td align="left" width="100px">配送会社:</td>
													<td class="td_bg" align="left"><s:property
															value='henpinhaisokaisha' /> <s:hidden
															name="f100102.henpinList[%{#status.index}].henpinhaisokaisha" /></td>
												</tr>
												<tr class="bg_tr">
													<td align="left">配送方法:</td>
													<td class="td_bg" align="left"><s:property
															value='henpinhaisouhoho' /> <s:hidden
															name="f100102.henpinList[%{#status.index}].henpinhaisouhoho" /></td>
													<td align="left">問い合わせ番号:</td>
													<td class="td_bg" align="left" colspan="5"><s:property
															value='toiawasebango' /> <s:hidden
															name="f100102.henpinList[%{#status.index}].toiawasebango" />&nbsp;
														<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
														<s:if test="henpinhaisokaisha == '佐川急便'">
															<input type="button" value="追跡"
																onclick="window.open ('http://k2k.sagawa-exp.co.jp/p/web/okurijosearch.do?okurijoNo=' + <s:property value='toiawasebango'/>, 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
														</s:if> <s:if test="henpinhaisokaisha == 'ヤマト運輸'">
															<input type="button" value="追跡"
																onclick="window.open ('http://toi.kuronekoyamato.co.jp/cgi-bin/tneko', 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
														</s:if> <s:if test="henpinhaisokaisha == '郵便局'">
															<input type="button" value="追跡"
																onclick="window.open ('https://trackings.post.japanpost.jp/services/srv/search/input', 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
														</s:if>
														</s:if>
														</td>
												</tr>
												<tr class="bg_tr">
													<td align="left">備考:</td>
													<td class="td_bg" align="left" colspan="7"><s:property
															value='henpinbiko' /> <s:hidden
															name="f100102.henpinList[%{#status.index}].henpinbiko" /></td>
												</tr>
											</table>
											<table cellspacing="1" cellpadding="2" class="table"
												width="98%" align="center">
												<tr>
													<td align="left">返品商品リスト:</td>
												</tr>
												<tr class="bg_tr">
													<td class="td_bg" align="center" width="40%">商品番号</td>
													<td class="td_bg" align="center" width="20%">個数</td>
													<td class="td_bg" align="center">返品種別</td>
												</tr>
												<s:iterator value="detailList" status="status1" id="detail">
													<tr class="bg_tr">
														<td class="td_bg" align="center" valign="middle">
														<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
														<a
															href="javascript:getShohinStsInfo('<s:property value='shohinbango'/>')"><s:property
																	value='shohinbango' /></a>
														</s:if>
														<s:else>
														<s:property
																	value='shohinbango' /></a>
														</s:else>
														 <s:hidden
																name="f100102.henpinList[%{#status.index}].henpinzumiList[%{#status1.index}].shohinbango"
																value="%{shohinbango}" /></td>
														<td class="td_bg" align="center" valign="middle"><s:property
																value='kosu' /> <s:hidden
																name="f100102.henpinList[%{#status.index}].henpinzumiList[%{#status1.index}].kosu"
																value="%{kosu}" /></td>
														<td class="td_bg" align="center" valign="middle"><s:property
																value='shubetsu' /> <s:hidden
																name="f100102.henpinList[%{#status.index}].henpinzumiList[%{#status1.index}].shubetsu"
																value="%{shubetsu}" /></td>
														<s:hidden
															name="f100102.henpinList[%{#status.index}].henpinzumiList[%{#status1.index}].sairiyokanoflg"
															value="%{sairiyokanoflg}" />
														<s:hidden
															name="f100102.henpinList[%{#status.index}].henpinzumiList[%{#status1.index}].kakuninflg"
															value="%{kakuninflg}" />
													</tr>
												</s:iterator>
											</table>
										</td>
										<td width="15%">
										<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
											<table cellspacing="1" cellpadding="2" width="98%"
												align="center">
												<s:if test="henpinsts == 0 || henpinsts == 1">
													<tr>
														<td align="center" valign="top"><input type="button"
															style="width: 120px"
															onclick="showHenpinshuseiDetail(true,<s:property value='henpinshoribango'/>);"
															value="修正"></td>
													</tr>
												</s:if>
												<s:if test="henpinsts == 0">
													<tr>
														<td align="center" valign="top"><input type="button"
															style="width: 120px"
															onclick="if(confirm('削除しますか？'))actionSubmit('A10010208?shoribango=<s:property value='henpinshoribango'/>');"
															value="削除"></td>
													</tr>
												</s:if>
												<s:if test="henpinsts == 1">
													<tr>
														<td align="center" valign="top"><input type="button"
															style="width: 120px"
															onclick="showHenpinzumiDetail(true,<s:property value='henpinshoribango'/>)"
															value="返品済みに設定"></td>
													</tr>
												</s:if>
												<s:if test="henpinsts == 0">
													<tr>
														<td align="center" valign="top"><input type="button"
															style="width: 120px"
															onclick="showHenpinhasoDetail(true,<s:property value='henpinshoribango'/>)"
															value="返品中に設定"></td>
													</tr>
												</s:if>
												<s:if test="henpinsts == 1">
													<tr>
														<td align="center" valign="top"><input type="button"
															style="width: 120px"
															onclick="if(confirm('返品待ちに設定しますか？'))actionSubmit('A10010210?shoribango=<s:property value='henpinshoribango'/>')"
															value="返品待ちに設定"></td>
													</tr>
												</s:if>
											</table>
										</s:if>
										</td>
									</tr>
								</table>
								<hr />
							</s:iterator>
						</s:else></td>
					<td align="center" class="td_bg">
					<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
					<input id="henpinBtn1"
						type="button" onclick="showHenpinDetail(true);" value="追加" />
					</s:if>
					</td>
				</tr>
				<!--                 <tr class="bg_tr" height="28px"> -->
				<!--                     <td>交換：</td> -->
				<%--                     <s:hidden name="f100102.chumonsts5"/> --%>
				<!--                     <td class="td_bg"  id="koukanSts">なし</td> -->
				<!--                     <td align="center" class="td_bg" ><input id="koukanBtn1" type="button" value="設定"/></td> -->
				<!--                 </tr> -->
				<tr class="bg_tr" height="28px">
					<td>追加発送：</td>
					<s:hidden name="f100102.chumonsts6" />
					<td class="td_bg" id="koukanSts"><s:if
							test="f100102.tuikaariFlg == 0">なし</s:if> <s:else>
							<s:iterator value="f100102.tuikaList" status="status">
								<table width="98%" align="center">
									<tr>
										<td width="85%">
											<table cellspacing="1" cellpadding="2" width="98%"
												class="table" align="center">
												<tr class="bg_tr">
													<td align="left" width="100px">処理番号:</td>
													<td class="td_bg" align="left" width="13%"><s:property
															value='tuikashoribango' /> <s:hidden
															name="f100102.tuikaList[%{#status.index}].tuikashoribango" /></td>
													<td align="left" width="100px">ステータス:</td>
													<td class="td_bg" align="left" width="13%"><s:property
															value='tuikastsName' /> <s:hidden
															name="f100102.tuikaList[%{#status.index}].tuikastsName" />
														<s:hidden
															name="f100102.tuikaList[%{#status.index}].tuikasts" /></td>
													<td align="left" width="100px">発送日時:</td>
													<td class="td_bg" align="left" width="13%"><s:property
															value='tuikahasonichiji' /> <s:hidden
															name="f100102.tuikaList[%{#status.index}].tuikahasonichiji" /></td>
													<td align="left" width="100px">配送会社:</td>
													<td class="td_bg" align="left"><s:property
															value='tuikahaisokaisha' /> <s:hidden
															name="f100102.tuikaList[%{#status.index}].tuikahaisokaisha" /></td>
												</tr>
												<tr class="bg_tr">
													<td align="left">配送方法:</td>
													<td class="td_bg" align="left"><s:property
															value='tuikahaisouhoho' /> <s:hidden
															name="f100102.tuikaList[%{#status.index}].tuikahaisouhoho" /></td>
													<td align="left">問い合わせ番号:</td>
													<td class="td_bg" align="left" colspan="5"><s:property
															value='toiawasebango' /> <s:hidden
															name="f100102.tuikaList[%{#status.index}].toiawasebango" />&nbsp;
														<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
														<s:if test="tuikahaisokaisha == '佐川急便'">
															<input type="button" value="追跡"
																onclick="window.open ('http://k2k.sagawa-exp.co.jp/p/web/okurijosearch.do?okurijoNo=' + <s:property value='toiawasebango'/>, 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
														</s:if> <s:if test="tuikahaisokaisha == 'ヤマト運輸'">
															<input type="button" value="追跡"
																onclick="window.open ('http://toi.kuronekoyamato.co.jp/cgi-bin/tneko', 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
														</s:if> <s:if test="tuikahaisokaisha == '郵便局">
															<input type="button" value="追跡"
																onclick="window.open ('https://trackings.post.japanpost.jp/services/srv/search/input', 'newwindow', 'height=1024, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') ">
														</s:if>
														</s:if>
													</td>
												</tr>
												<tr class="bg_tr">
													<td align="left">備考:</td>
													<td class="td_bg" align="left" colspan="7"><s:property
															value='tuikabiko' /> <s:hidden
															name="f100102.tuikaList[%{#status.index}].tuikabiko" /></td>
												</tr>
											</table>
											<table cellspacing="1" cellpadding="2" class="table"
												width="98%" align="center">
												<tr>
													<td align="left">追加発送商品リスト:</td>
												</tr>
												<tr class="bg_tr">
													<td class="td_bg" align="center" width="40%">商品番号</td>
													<td class="td_bg" align="center" width="20%">個数</td>
													<td class="td_bg" align="center" width="40%">ステータス</td>
												</tr>
												<s:iterator value="detailList" status="status1" id="detail2">
													<tr class="bg_tr">
														<td class="td_bg" align="center" valign="middle">
														<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
														<a
															href="javascript:getShohinStsInfo('<s:property value='shohinbango'/>')"><s:property
																	value='shohinbango' /></a>
														</s:if>
														<s:else>
														<s:property
																	value='shohinbango' /></a>
														</s:else>
														<s:hidden
																name="f100102.tuikaList[%{#status.index}].tuikazumiList[%{#status1.index}].shohinbango"
																value="%{shohinbango}" /></td>
														<td class="td_bg" align="center" valign="middle"><s:property
																value='kosu' /> <s:hidden
																name="f100102.tuikaList[%{#status.index}].tuikazumiList[%{#status1.index}].kosu"
																value="%{kosu}" /></td>
														<td class="td_bg" align="center" valign="middle"><s:property
																value='tuikastatus' /> <s:hidden
																name="f100102.tuikaList[%{#status.index}].tuikazumiList[%{#status1.index}].tuikastatus"
																value="%{tuikastatus}" /></td>
													</tr>
												</s:iterator>
											</table>
										</td>
										<td width="15%">
										<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
											<table cellspacing="1" cellpadding="2" width="98%"
												align="center">
												<s:if test="tuikasts == 0">
													<tr>
														<td align="center" valign="top"><input type="button"
															style="width: 120px" onclick="showTuikaDetail(true,'1');"
															value="修正"></td>
													</tr>
													<tr>
														<td align="center" valign="top"><input type="button"
															style="width: 120px"
															onclick="if(confirm('削除しますか？'))actionSubmit('A10010214?shoribango=<s:property value='tuikashoribango'/>');"
															value="削除"></td>
													</tr>
													<tr>
														<td align="center" valign="top"><input type="button"
															style="width: 120px" onclick="showTuikaDetail(true,'2');"
															value="発送済みに設定"></td>
													</tr>
												</s:if>
												<s:else>
													<tr>
														<td></td>
													</tr>
												</s:else>
											</table>
										</s:if>
										</td>
									</tr>
								</table>
								<hr />
							</s:iterator>
						</s:else></td>
					<td align="center" class="td_bg">
					<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
					<s:if
							test="f100102.tuikahasokaFlg == 1">
							<input id="tuikaBtn1" type="button"
								onclick="showTuikaDetail(true,'0');" value="追加" />
						</s:if> <s:else>
							<input id="tuikaBtn1" style="display: none" type="button"
								value="追加" />
						</s:else> <s:if test="f100102.tuikahasokaFlg == 0">
							<input type="button" disabled value="追加" />
						</s:if>
					</s:if>
					</td>
				</tr>
				<tr class="bg_tr" height="28px">
					<td>返金：</td>
					<s:hidden name="f100102.chumonsts6" />
					<td class="td_bg"><s:if test="f100102.henkinariFlg == 0">なし</s:if>
						<s:else>
							<table cellspacing="1" cellpadding="2" class="table" width="98%"
								align="center">
								<tr class="bg_tr">
									<td class="td_bg" align="center" width="8%">処理番号</td>
									<td class="td_bg" align="center" width="12%">受注番号</td>
									<td class="td_bg" align="center" width="7%">返金金額</td>
									<td class="td_bg" align="center" width="9%">返金種別</td>
									<td class="td_bg" align="center" width="9%">返金済みフラグ</td>
									<td class="td_bg" align="center" width="10%">返金日</td>
									<td class="td_bg" align="center" width="15%">備考</td>
									<td class="td_bg" align="center" width="10%">設定日</td>
									<td class="td_bg" align="center">&nbsp;</td>
								</tr>
								<s:iterator value="f100102.henkinList" status="status"
									id="detail">
									<tr class="bg_tr">
										<td class="td_bg" align="center" valign="middle"><s:property
												value='shoribango' /> <s:hidden
												name="f100102.henkinList[%{#status.index}].shoribango"
												value="%{shoribango}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='juchubango' /> <s:hidden
												name="f100102.henkinList[%{#status.index}].juchubango"
												value="%{juchubango}" /></td>
										<td class="td_bg" align="right" valign="middle"><s:property
												value='henkinkingaku' /> <s:hidden
												name="f100102.henkinList[%{#status.index}].henkinkingaku"
												value="%{henkinkingaku}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:if
												test="henkinshubetsu == 0">交換による返金</s:if> <s:if
												test="henkinshubetsu == 1">返品による返金</s:if> <s:if
												test="henkinshubetsu == 2">その他</s:if> <s:hidden
												name="f100102.henkinList[%{#status.index}].henkinshubetsu"
												value="%{henkinshubetsu}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:if
												test="henkinzumiflg == 0">
												<font color="red">返金待ち</font>
											</s:if> <s:if test="henkinzumiflg == 1">
												<font color="green">返金済み</font>
											</s:if> <s:hidden
												name="f100102.henkinList[%{#status.index}].henkinzumiflg"
												value="%{henkinzumiflg}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='henkinbi' /> <s:hidden
												name="f100102.henkinList[%{#status.index}].henkinbi"
												value="%{henkinbi}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='biko' /> <s:hidden
												name="f100102.henkinList[%{#status.index}].biko"
												value="%{biko}" /></td>
										<td class="td_bg" align="center" valign="middle"><s:property
												value='settibi' /> <s:hidden
												name="f100102.henkinList[%{#status.index}].settibi"
												value="%{settibi}" /></td>
										<td class="td_bg" align="center" valign="middle">
										<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
										<s:if
												test="henkinzumiflg == 0">
												<input type="button" value="返金済み"
													onclick="if(confirm('返品済みに設定しますか？'))actionSubmit('A10010219?shoribango=<s:property value='shoribango'/>');">
											</s:if> <s:if test="henkinzumiflg == 0 && henkinshubetsu == 2">
												<input type="button" value="修正"
													onclick="popHenkin('2',<s:property value='shoribango'/>)" />
											</s:if> <s:if test="henkinzumiflg == 0 && henkinshubetsu == 2">
												<input type="button" value="削除"
													onclick="if(confirm('削除しますか？'))actionSubmit('A10010218?shoribango=<s:property value='shoribango'/>');" />
											</s:if>
										</s:if>
										</td>
									</tr>
								</s:iterator>
							</table>
						</s:else></td>
					<%--                     <s:if test="f100102.chumonsts1 == 3 || f100102.chumonsts1 == 5"> --%>
					<td align="center" class="td_bg">
					<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
					<input type="button"
						onclick="popHenkin('1','');" value="追加" />
					</s:if>
					</td>
					<%--                     </s:if> --%>
					<%--                     <s:else> --%>
					<!--                         <td align="center" class="td_bg" ><input type="button" disabled value="追加"/></td> -->
					<%--                     </s:else> --%>
				</tr>
			</table>
		</div>
		<div id="div2">
			<table width="99%" align="left" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17%">
						<table class="table" width="100%" border="0" cellspacing="1"
							cellpadding="0">
							<tr class="bg_tr" height="40px">
								<td width="90%" align="center">送付先</td>
							</tr>
						</table>
					</td>
					<td width="83%">
						<table class="table" width="100%" border="0" cellspacing="1"
							cellpadding="0">
							<tr class="bg_tr" height="40px">
								<td align="center">商品【商品内訳】<br>（商品番号）
								</td>
								<td width="19%" align="center">項目・選択肢</td>
								<td width="4%" align="center">単価</td>
								<td width="4%" align="center">個数</td>
								<td width="4%" align="center">ポイント倍率</td>
								<td width="5%" align="center">お届けの目安</td>
								<td width="70px" align="center">小計</td>
								<td width="30px" align="center">税</td>
								<td width="30px" align="center">送料</td>
								<td width="30px" align="center">代引</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<table width="99%" align="left" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17%">
						<table width="100%" class="table" cellspacing="1" cellpadding="0"
							align="left" border="0">
							<tr class="bg_tr" height="100px" id="tr1">
								<td class="td_bg" width="90%" align="left" valign="middle"
									id="td1"><s:label name="f100102.sofusakijoho" /> <s:hidden
										name="f100102.sofusakijoho" /> <!-- 			        <br>お荷物伝票番号 -->
									<%-- 			        <s:textfield size="15" maxlength="50" name="f100102.onimotsudenpyobango" /><br>配送会社 --%>
									<%-- 			        <s:select list="#{ '1000':'その他','1001':'ヤマト運輸','1002':'佐川急便'}" name="f100102.haisokaisha" /> --%>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table width="100%" class="table" cellspacing="1" cellpadding="0"
							align="left" border="0">
							<s:iterator value="f100102.shohinList" status="status">
								<tr class="bg_tr" height="100px">
									<td class="td_bg" align="center" valign="middle"
										id="td3[${status.index}]">
										<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
										<a target="_blank"
										href="<s:property value='shohinURL'/>"><s:property
												value='shouhinmei' /></a>
										</s:if>
										<s:else>
										<s:property
												value='shouhinmei' /></a>
										</s:else>
										 <s:hidden
											name="f100102.shohinList[%{#status.index}].shouhinmei"
											value="%{shouhinmei}" /></td>
									<td width="19%" class="td_bg" align="left" valign="middle"
										id="td4[${status.index}]"><s:property
											value='komokusentakushi' /> <s:hidden
											name="f100102.shohinList[%{#status.index}].komokusentakushi"
											value="%{komokusentakushi}" /></td>
									<td width="4%" class="td_bg" align="right" valign="middle"
										id="td5[${status.index}]"><s:property value='tankaku' />
										<s:hidden name="f100102.shohinList[%{#status.index}].tankaku"
											value="%{tankaku}" />円</td>
									<td width="4%" class="td_bg" align="center" valign="middle"
										id="td6[${status.index}]"><s:property value='kosu' /> <s:hidden
											name="f100102.shohinList[%{#status.index}].kosu"
											value="%{kosu}" /></td>
									<td width="4%" class="td_bg" align="center" valign="middle"
										id="td7[${status.index}]"><s:property
											value='pointobairitsu' /> <s:hidden
											name="f100102.shohinList[%{#status.index}].pointobairitsu"
											value="%{pointobairitsu}" />倍</td>
									<td width="5%" class="td_bg" align="center" valign="middle"
										id="td8[${status.index}]"><s:property value='nouki' /> <s:hidden
											name="f100102.shohinList[%{#status.index}].nouki"
											value="%{nouki}" /></td>
									<td width="70px" class="td_bg" align="right" valign="middle"
										id="td9[${status.index}]"><s:property value='shoukei' />
										<s:hidden name="f100102.shohinList[%{#status.index}].shoukei"
											value="%{shoukei}" />円</td>
									<td width="30px" class="td_bg" align="center" valign="middle"
										id="td10[${status.index}]"><s:property value='zei' /> <s:hidden
											name="f100102.shohinList[%{#status.index}].zei"
											value="%{zei}" /></td>
									<td width="30px" class="td_bg" align="center" valign="middle"
										id="td10[${status.index}]"><s:property value='sourou' />
										<s:hidden name="f100102.shohinList[%{#status.index}].sourou"
											value="%{sourou}" /></td>
									<td width="30px" class="td_bg" align="center"
										id="td11[${status.index}]"><s:property value='daibiki' />
										<s:hidden name="f100102.shohinList[%{#status.index}].daibiki"
											value="%{daibiki}" /></td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
			</table>
			<table class="table" width="99%" align="left" cellspacing="1"
				cellpadding="0">
				<tr>
					<td align="right" style="background-color: lightgrey">合計</td>
					<td class="td_bg" align="right" width="70px"><s:label
							name="f100102.gokeishouhin" /> <s:hidden
							name="f100102.gokeishouhin" />円</td>
					<td class="td_bg" align="right" width="30px"><s:label
							name="f100102.gokeizei" /> <s:hidden name="f100102.gokeizei" />円</td>
					<td class="td_bg" align="right" width="30px"><s:label
							name="f100102.gokeisouryou" /> <s:hidden
							name="f100102.gokeisouryou" />円</td>
					<td class="td_bg" align="right" width="30px"><s:label
							name="f100102.gokeidaibikiryou" /> <s:hidden
							name="f100102.gokeidaibikiryou" />円</td>
				</tr>
				<tr>
					<td align="right" style="background-color: lightgrey">ポイント利用</td>
					<td class="td_bg" align="right" colspan="4"><s:label
							name="f100102.pointriyou" /> <s:hidden name="f100102.pointriyou" />円</td>
				</tr>
				<tr>
					<td align="right" style="background-color: lightgrey">その他利用額</td>
					<td class="td_bg" align="right" colspan="4"><s:label
							name="f100102.sonotariyogaku" /> <s:hidden
							name="f100102.sonotariyogaku" />円</td>
				</tr>
				<tr>
					<td align="right" style="background-color: lightgrey">請求金額</td>
					<td class="td_bg" align="right" colspan="4"><s:label
							name="f100102.seikyukingaku" /> <s:hidden
							name="f100102.seikyukingaku" />円</td>
				</tr>
			</table>
		</div>
		<div id="div4">
			<table cellspacing="0" cellpadding="0" width="99%">
				<tr>
					<td width="50%" align="left">
						<table align="left" cellspacing="1" cellpadding="1" width="90%"
							class="table">
							<tr class="bg_tr">
								<td width="100%">備考(楽天)</td>
							</tr>
							<tr class="bg_tr" height="100px">
								<td class="td_bg" valign="top"><s:label
										name="f100102.bikorakuten" /> <s:hidden
										name="f100102.bikorakuten" /></td>
							</tr>
						</table>
					</td>
					<td width="50%" align="left">
						<table align="left" cellspacing="1" cellpadding="1" width="90%"
							class="table">
							<tr class="bg_tr">
								<td width="100%">お客様へのメッセージ (メール送信の際に差し込むことができます)</td>
							</tr>
							<tr class="bg_tr" height="100px">
								<td class="td_bg" valign="top"><s:label
										name="f100102.okyakusamahenomeseji" /> <s:hidden
										name="f100102.okyakusamahenomeseji" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br /> <br />
			<s:hidden name="viewId" value="V100101" />
			<s:hidden name="f100102.resultcount" />
			<s:hidden name="mode" />
			<s:hidden name="shoriMode" />
			<s:hidden name="rowIndex" />
		</div>
		<div id='pop-div'
			style="left: 350px; top: 100px; width: 450px; height: 260px; position: absolute; display: none"
			class="pop-box">
			<div class="pop-box-body">
				<table>
					<tr>
						<td colspan="2" align="center"><textarea id="logArea"
								style="width: 400px; height: 200px" readOnly></textarea></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div-futuhaso'
			style="width: 550px; height: 500px; position: fixed; display: none"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center" width="100%">
					<tr>
						<td align="left">発送情報を入力してください</td>
					</tr>
				</table>
				<table align="center">
					<tr>
						<td>問い合わせ番号:</td>
						<td><s:textfield name="f100102.toiawasebango_futsuhasou"></s:textfield></td>
						<td>配送方法 <s:select list="#{ '0':'その他','1':'宅急便','2':'メール便'}"
								name="f100102.haisohoho_futsuhasou" />
						</td>
						<td>配送会社 <s:select
								list="#{ '1000':'その他','1001':'ヤマト運輸','1002':'佐川急便','1003':'郵便局'}"
								name="f100102.haisokaisha_futsuhasou" />
						</td>
					</tr>
				</table>
				<br />
				<table align="center" width="100%">
					<tr>
						<td align="left">発送商品を確認してください</td>
					</tr>
				</table>
				<div style="height: 360px; overflow: auto">
					<table cellspacing="1" cellpadding="2" class="table" width="90%"
						align="center">
						<tr class="bg_tr">
							<td class="td_bg" width="90%" align="center">商品</td>
							<td class="td_bg" width="10%" align="center">個数</td>
						</tr>
						<s:iterator value="f100102.shohinList" status="status">
							<tr class="bg_tr">
								<td class="td_bg" align="center"><s:property
										value='shouhinmei' /></td>
								<td width="4%" class="td_bg" align="center" valign="middle"
									id="td6[${status.index}]"><s:property value='kosu' /> <s:hidden
										name="f100102.shohinList[%{#status.index}].kosu"
										value="%{kosu}" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010203');" value="発送" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div-bunnou'
			style="width: 550px; height: 500px; position: fixed; display: none"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center" width="100%">
					<tr>
						<td align="left">発送情報を入力してください</td>
					</tr>
				</table>
				<table align="center">
					<tr>
						<td>問い合わせ番号:</td>
						<td><s:textfield name="f100102.toiawasebango_bunnou"></s:textfield></td>
						<td>配送方法 <s:select list="#{ '0':'その他','1':'宅急便','2':'メール便'}"
								name="f100102.haisohoho_bunnou" />
						</td>
						<td>配送会社 <s:select
								list="#{ '1000':'その他','1001':'ヤマト運輸','1002':'佐川急便','1003':'郵便局'}"
								name="f100102.haisokaisha_bunnou" />
						</td>
					</tr>
				</table>
				<br />
				<table align="center" width="100%">
					<tr>
						<td align="left">発送商品を選択してください</td>
					</tr>
				</table>
				<div style="height: 360px; overflow: auto">
					<table cellspacing="1" cellpadding="2" class="table" width="90%"
						align="center">
						<tr class="bg_tr">
							<td class="td_bg" width="4%" align="center">&nbsp;</td>
							<td class="td_bg" width="86%" align="center">商品</td>
							<td class="td_bg" width="10%" align="center">個数</td>
						</tr>
						<s:iterator value="f100102.bunnouhasokaList" status="status">
							<tr class="bg_tr">
								<td class="td_bg" align="center"><s:checkbox
										name="f100102.bunnouhasokaList[%{#status.index}].checkdFlg" /></td>
								<td class="td_bg" align="center"><s:property
										value='shouhinmei' /></td>
								<td width="4%" class="td_bg" align="center" valign="middle"><s:textfield
										size="3"
										name="f100102.bunnouhasokaList[%{#status.index}].kosu"
										value="%{kosu}" /> <s:hidden
										name="f100102.bunnouhasokaList[%{#status.index}].shohinbango"
										value="%{shohinbango}" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010205');" value="発送" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div-henpin'
			style="width: 850px; height: 500px; position: fixed; display: none"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center" width="100%">
					<tr>
						<td align="left">返品情報を入力してください</td>
					</tr>
				</table>
				<table align="center" width="95%" class="table">
					<tr class="bg_tr">
						<td colspan="4">返品理由(店舗側):</td>
					<tr>
					<tr class="bg_tr">
						<td colspan="4" class="td_bg">初期不良<s:checkbox
								name="f100102.henpinriyuTenpo" fieldValue="0" />&nbsp;&nbsp;
							商品誤送<s:checkbox name="f100102.henpinriyuTenpo" fieldValue="1" />&nbsp;&nbsp;
							バーコード貼り違い<s:checkbox name="f100102.henpinriyuTenpo"
								fieldValue="2" />&nbsp;&nbsp; 商品編集ミス<s:checkbox
								name="f100102.henpinriyuTenpo" fieldValue="3" />&nbsp;&nbsp;
							その他&nbsp;<s:textfield name="f100102.henpinriyuTenpoSonota" /></td>
					</tr>
					<tr class="bg_tr">
						<td colspan="4">返品理由(お客様側):</td>
					</tr>
					<tr class="bg_tr">
						<td colspan="4" class="td_bg">気に入らない<s:checkbox
								name="f100102.henpinriyuOkyaku" fieldValue="0" />&nbsp;&nbsp;
							サイズ合わない<s:checkbox name="f100102.henpinriyuOkyaku" fieldValue="1" />&nbsp;&nbsp;
							受け取り拒否<s:checkbox name="f100102.henpinriyuOkyaku" fieldValue="2" />&nbsp;&nbsp;
							長期不在<s:checkbox name="f100102.henpinriyuOkyaku" fieldValue="3" />&nbsp;&nbsp;
							その他&nbsp;<s:textfield name="f100102.henpinriyuOkyakuSonota" /></td>
					</tr>
					<tr class="bg_tr">
						<td width="20%">初期発送送料負担:</td>
						<td width="30%" class="td_bg"><s:radio
								list="#{ '0':'店舗&nbsp;&nbsp;&nbsp;','1':'お客様&nbsp;&nbsp;&nbsp;'}"
								value="0" name="f100102.henpinsoryofutanshokihasou" /></td>
						<td width="20%">受け取り送料負担:</td>
						<td width="30%" class="td_bg"><s:radio
								list="#{ '0':'店舗&nbsp;&nbsp;&nbsp;','1':'お客様&nbsp;&nbsp;&nbsp;'}"
								value="0" name="f100102.henpinsoryofutanuketori" /></td>
					</tr>
					<tr class="bg_tr">
						<td>返金:</td>
						<td class="td_bg" colspan="3">返金必要&nbsp;<s:checkbox
								name="f100102.henpinhenkinhituyoFlg" />&nbsp;&nbsp;&nbsp;&nbsp;返金金額&nbsp;&nbsp;<s:textfield
								size="6" style="text-align:right"
								name="f100102.henpinhenkinkigaku" /></td>

					</tr>
					<tr class="bg_tr">
						<td>備考:</td>
						<td class="td_bg" colspan="3"><s:textarea
								style="width:300px;height:20px" name="f100102.henpinbiko" /></td>
					</tr>
				</table>
				<br />
				<table align="center" width="100%">
					<tr>
						<td align="left">返品商品を選択してください</td>
					</tr>
				</table>
				<div style="height: 200px; overflow: auto">
					<table cellspacing="1" cellpadding="2" class="table" width="800px"
						align="center">
						<tr class="bg_tr">
							<td class="td_bg" width="5%" align="center">選択</td>
							<td class="td_bg" width="32%" align="center">商品</td>
							<td class="td_bg" width="10%" align="center">発送種別</td>
							<td class="td_bg" width="10%" align="center">発送個数</td>
							<td class="td_bg" width="13%" align="center">発送日時</td>
							<td class="td_bg" width="10%" align="center">返品個数</td>
							<td class="td_bg" width="10%" align="center">再利用可能</td>
							<td class="td_bg" width="10%" align="center">確認必要</td>
						</tr>
						<s:iterator value="f100102.henpinkaList" status="status">
							<tr class="bg_tr">
								<td class="td_bg" align="center"><s:checkbox
										name="f100102.henpinkaList[%{#status.index}].checkdFlg" /></td>
								<td class="td_bg" align="center"><s:property
										value='shohinbango' /> <s:hidden
										name="f100102.henpinkaList[%{#status.index}].shohinbango"
										value="%{shohinbango}" /></td>
								<td class="td_bg" align="center"><s:property
										value='hassoushubetsu' /> <s:hidden
										name="f100102.henpinkaList[%{#status.index}].hassoushubetsu"
										value="%{hassoushubetsu}" /></td>
								<td class="td_bg" align="center" valign="middle"><s:property
										value='hasokosu' /> <s:hidden
										name="f100102.henpinkaList[%{#status.index}].hasokosu"
										value="%{hasokosu}" /></td>
								<td class="td_bg" align="center"><s:property
										value='hasounichiji' /> <s:hidden
										name="f100102.henpinkaList[%{#status.index}].hasounichiji"
										value="%{hasounichiji}" /></td>
								<td class="td_bg" align="center"><s:property
										value='henpinkosu' /> <s:textfield
										name="f100102.henpinkaList[%{#status.index}].henpinkosu"
										value="0" size="4" /></td>
								<td class="td_bg" align="center"><s:checkbox
										name="f100102.henpinkaList[%{#status.index}].sairiyokano"
										checked="true" /></td>
								<td class="td_bg" align="center"><s:checkbox
										name="f100102.henpinkaList[%{#status.index}].kakuninhituyo" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010206');" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div-henpin-shusei'
			style="width: 850px; height: 500px; position: fixed; display: none"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center" width="100%">
					<tr>
						<td align="left">返品情報を入力してください</td>
					</tr>
				</table>
				<table align="center" width="95%">
					<tr>
						<td align="left">処理番号：<s:textfield
								name="f100102.shoribango_henpinshusei" readonly="true"
								cssClass="readonly" /></td>
					</tr>
				</table>
				<table align="center" width="95%" class="table">
					<tr class="bg_tr">
						<td colspan="4">返品理由(店舗側):</td>
					<tr>
					<tr class="bg_tr">
						<td colspan="4" class="td_bg">初期不良<s:checkbox
								name="f100102.henpinriyuTenpo_henpinshusei" fieldValue="0" />&nbsp;&nbsp;
							商品誤送<s:checkbox name="f100102.henpinriyuTenpo_henpinshusei"
								fieldValue="1" />&nbsp;&nbsp; バーコード貼り違い<s:checkbox
								name="f100102.henpinriyuTenpo_henpinshusei" fieldValue="2" />&nbsp;&nbsp;
							商品編集ミス<s:checkbox name="f100102.henpinriyuTenpo_henpinshusei"
								fieldValue="3" />&nbsp;&nbsp; その他&nbsp;<s:textfield
								name="f100102.henpinriyuTenpoSonota_henpinshusei" /></td>
					</tr>
					<tr class="bg_tr">
						<td colspan="4">返品理由(お客様側):</td>
					</tr>
					<tr class="bg_tr">
						<td colspan="4" class="td_bg">気に入らない<s:checkbox
								name="f100102.henpinriyuOkyaku_henpinshusei" fieldValue="0" />&nbsp;&nbsp;
							サイズ合わない<s:checkbox name="f100102.henpinriyuOkyaku_henpinshusei"
								fieldValue="1" />&nbsp;&nbsp; 受け取り拒否<s:checkbox
								name="f100102.henpinriyuOkyaku_henpinshusei" fieldValue="2" />&nbsp;&nbsp;
							長期不在<s:checkbox name="f100102.henpinriyuOkyaku_henpinshusei"
								fieldValue="3" />&nbsp;&nbsp; その他&nbsp;<s:textfield
								name="f100102.henpinriyuOkyakuSonota_henpinshusei" /></td>
					</tr>
					<tr class="bg_tr">
						<td width="20%">初期発送送料負担:</td>
						<td width="30%" class="td_bg"><s:radio
								list="#{ '0':'店舗&nbsp;&nbsp;&nbsp;','1':'お客様&nbsp;&nbsp;&nbsp;'}"
								value="0" name="f100102.henpinsoryofutanshokihasou_henpinshusei" /></td>
						<td width="20%">受け取り送料負担:</td>
						<td width="30%" class="td_bg"><s:radio
								list="#{ '0':'店舗&nbsp;&nbsp;&nbsp;','1':'お客様&nbsp;&nbsp;&nbsp;'}"
								value="0" name="f100102.henpinsoryofutanuketori_henpinshusei" /></td>
					</tr>
					<tr class="bg_tr">
						<td>返金:</td>
						<td class="td_bg" colspan="3">返金必要&nbsp;<s:checkbox
								name="f100102.henpinhenkinhituyoFlg_henpinshusei" />&nbsp;&nbsp;&nbsp;&nbsp;返金金額&nbsp;&nbsp;<s:textfield
								size="6" style="text-align:right;ime-mode:disabled"
								name="f100102.henpinhenkinkigaku_henpinshusei" /></td>
					</tr>
					<tr class="bg_tr">
						<td>備考:</td>
						<td class="td_bg" colspan="3"><s:textarea
								style="width:300px;height:20px"
								name="f100102.henpinbiko_henpinshusei" /></td>
					</tr>
				</table>
				<br />
				<table align="center" width="100%">
					<tr>
						<td align="left">返品商品を選択してください</td>
					</tr>
				</table>
				<div style="height: 180px; overflow: auto">
					<table cellspacing="1" cellpadding="2" class="table" width="800px"
						align="center">
						<tr class="bg_tr">
							<td class="td_bg" width="5%" align="center">選択</td>
							<td class="td_bg" width="32%" align="center">商品</td>
							<td class="td_bg" width="10%" align="center">発送種別</td>
							<td class="td_bg" width="10%" align="center">発送個数</td>
							<td class="td_bg" width="13%" align="center">発送日時</td>
							<td class="td_bg" width="10%" align="center">返品個数</td>
							<td class="td_bg" width="10%" align="center">再利用可能</td>
							<td class="td_bg" width="10%" align="center">確認必要</td>
						</tr>
						<s:iterator value="f100102.henpinshuseikaShohinList"
							status="status">
							<tr class="bg_tr">
								<td class="td_bg" align="center"><s:checkbox
										name="f100102.henpinshuseikaShohinList[%{#status.index}].checkdFlg" /></td>
								<td class="td_bg" align="center"><s:property
										value='shohinbango' /> <s:hidden
										name="f100102.henpinshuseikaShohinList[%{#status.index}].shohinbango"
										value="%{shohinbango}" /></td>
								<td class="td_bg" align="center"><s:property
										value='hassoushubetsu' /> <s:hidden
										name="f100102.henpinshuseikaShohinList[%{#status.index}].hassoushubetsu"
										value="%{hassoushubetsu}" /></td>
								<td class="td_bg" align="center" valign="middle"><s:property
										value='hasokosu' /> <s:hidden
										name="f100102.henpinshuseikaShohinList[%{#status.index}].hasokosu"
										value="%{hasokosu}" /></td>
								<td class="td_bg" align="center"><s:property
										value='hasounichiji' /> <s:hidden
										name="f100102.henpinshuseikaShohinList[%{#status.index}].hasounichiji"
										value="%{hasounichiji}" /></td>
								<td class="td_bg" align="center"><s:property
										value='henpinkosu' /> <s:textfield
										name="f100102.henpinshuseikaShohinList[%{#status.index}].henpinkosu"
										value="%{henpinkosu}" size="4" style="ime-mode:disabled" /></td>
								<td class="td_bg" align="center"><s:checkbox
										name="f100102.henpinshuseikaShohinList[%{#status.index}].sairiyokano" /></td>
								<td class="td_bg" align="center"><s:checkbox
										name="f100102.henpinshuseikaShohinList[%{#status.index}].kakuninhituyo" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010207');" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div-henpinhaso'
			style="width: 550px; height: 500px; position: fixed; display: none"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center" width="100%">
					<tr>
						<td align="left">返品情報を入力してください</td>
					</tr>
					<tr>
						<td align="left">処理番号：<s:textfield
								name="f100102.shoribango_henpinhaso" readonly="true"
								cssClass="readonly" /></td>
					</tr>
				</table>
				<table align="center" width="100%">
					<tr>
						<td>問い合わせ番号:</td>
						<td><s:textfield name="f100102.toiawasebango_henpinhaso"></s:textfield></td>
						<td>配送方法 <s:select list="#{ '0':'その他','1':'宅急便','2':'メール便'}"
								name="f100102.haisohoho_henpinhaso" />
						</td>
						<td>配送会社 <s:select
								list="#{ '1000':'その他','1001':'ヤマト運輸','1002':'佐川急便','1003':'郵便局'}"
								name="f100102.haisokaisha_henpinhaso" />
						</td>
					</tr>
				</table>
				<br />
				<table align="center" width="100%">
					<tr>
						<td align="left">返品商品を確認してください</td>
					</tr>
				</table>
				<div style="height: 300px; overflow: auto">
					<table cellspacing="1" cellpadding="2" class="table" width="90%"
						align="center">
						<tr class="bg_tr">
							<td class="td_bg" align="center" width="30%">商品番号</td>
							<td class="td_bg" align="center" width="20%">個数</td>
							<td class="td_bg" align="center" width="20%">返品種別</td>
						</tr>
						<s:iterator value="f100102.henpinList" status="status" id="t1">
							<s:iterator value="detailList" status="status1" id="detail">
								<tr class="bg_tr"
									id="<s:property value='#t1.henpinshoribango'/><s:property value='#status1.index'/>"
									style="display: none">
									<td class="td_bg" align="center" valign="middle"><s:property
											value='shohinbango' /> <s:hidden
											name="f100102.henpinList[%{#status.index}].henpinzumiList[%{#status1.index}].shohinbango"
											value="%{shohinbango}" /></td>
									<td class="td_bg" align="center" valign="middle"><s:property
											value='kosu' /> <s:hidden
											name="f100102.henpinList[%{#status.index}].henpinzumiList[%{#status1.index}].kosu"
											value="%{kosu}" /></td>
									<td class="td_bg" align="center" valign="middle"><s:property
											value='shubetsu' /> <s:hidden
											name="f100102.henpinList[%{#status.index}].henpinzumiList[%{#status1.index}].shubetsu"
											value="%{shubetsu}" /></td>
								</tr>
							</s:iterator>
						</s:iterator>
					</table>
				</div>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button"
							onclick="hideDivHenpinhaso();" value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010209');" value="発送" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div-henpin-zumi'
			style="width: 850px; height: 500px; position: fixed; display: none"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center" width="100%">
					<tr>
						<td align="left">返品情報を確認してください</td>
					</tr>
				</table>
				<table align="center" width="95%">
					<tr>
						<td align="left">処理番号：<s:textfield
								name="f100102.shoribango_henpinzumi" readonly="true"
								cssClass="readonly" /></td>
					</tr>
				</table>
				<table align="center" width="95%" class="table">
					<tr class="bg_tr">
						<td colspan="4">返品理由(店舗側):</td>
					<tr>
					<tr class="bg_tr">
						<td colspan="4" class="td_bg">初期不良<s:checkbox disabled="true"
								name="f100102.henpinriyuTenpo_henpinzumi" fieldValue="0" />&nbsp;&nbsp;
							商品誤送<s:checkbox disabled="true"
								name="f100102.henpinriyuTenpo_henpinzumi" fieldValue="1" />&nbsp;&nbsp;
							バーコード貼り違い<s:checkbox disabled="true"
								name="f100102.henpinriyuTenpo_henpinzumi" fieldValue="2" />&nbsp;&nbsp;
							商品編集ミス<s:checkbox disabled="true"
								name="f100102.henpinriyuTenpo_henpinzumi" fieldValue="3" />&nbsp;&nbsp;
							その他&nbsp;<s:textfield disabled="true"
								name="f100102.henpinriyuTenpoSonota_henpinzumi" /></td>
					</tr>
					<tr class="bg_tr">
						<td colspan="4">返品理由(お客様側):</td>
					</tr>
					<tr class="bg_tr">
						<td colspan="4" class="td_bg">気に入らない<s:checkbox
								disabled="true" name="f100102.henpinriyuOkyaku_henpinzumi"
								fieldValue="0" />&nbsp;&nbsp; サイズ合わない<s:checkbox
								disabled="true" name="f100102.henpinriyuOkyaku_henpinzumi"
								fieldValue="1" />&nbsp;&nbsp; 受け取り拒否<s:checkbox disabled="true"
								name="f100102.henpinriyuOkyaku_henpinzumi" fieldValue="2" />&nbsp;&nbsp;
							長期不在<s:checkbox disabled="true"
								name="f100102.henpinriyuOkyaku_henpinzumi" fieldValue="3" />&nbsp;&nbsp;
							その他&nbsp;<s:textfield disabled="true"
								name="f100102.henpinriyuOkyakuSonota_henpinzumi" /></td>
					</tr>
					<tr class="bg_tr">
						<td width="20%">初期発送送料負担:</td>
						<td width="30%" class="td_bg"><s:radio disabled="true"
								list="#{ '0':'店舗&nbsp;&nbsp;&nbsp;','1':'お客様&nbsp;&nbsp;&nbsp;'}"
								value="0" name="f100102.henpinsoryofutanshokihasou_henpinzumi" /></td>
						<td width="20%">受け取り送料負担:</td>
						<td width="30%" class="td_bg"><s:radio disabled="true"
								list="#{ '0':'店舗&nbsp;&nbsp;&nbsp;','1':'お客様&nbsp;&nbsp;&nbsp;'}"
								value="0" name="f100102.henpinsoryofutanuketori_henpinzumi" /></td>
					</tr>
					<tr class="bg_tr">
						<td>返金:</td>
						<td class="td_bg" colspan="3">返金必要&nbsp;<s:checkbox
								disabled="true" name="f100102.henpinhenkinhituyoFlg_henpinzumi" />&nbsp;&nbsp;&nbsp;&nbsp;返金金額&nbsp;&nbsp;<s:textfield
								disabled="true" size="6" style="text-align:right"
								name="f100102.henpinhenkinkigaku_henpinzumi" /></td>
					</tr>
					<tr class="bg_tr">
						<td>備考:</td>
						<td class="td_bg" colspan="3"><s:textarea disabled="true"
								style="width:300px;height:20px"
								name="f100102.henpinbiko_henpinzumi" /></td>
					</tr>
				</table>
				<br />
				<table align="center" width="100%">
					<tr>
						<td align="left">返品商品を確認してください</td>
					</tr>
				</table>
				<div style="height: 180px; overflow: auto" id="henpinkakunindiv">
					<table cellspacing="1" cellpadding="2" class="table" width="800px"
						align="center">
						<tr class="bg_tr">
							<td class="td_bg" align="center">商品番号</td>
							<td class="td_bg" width="15%" align="center">個数</td>
							<td class="td_bg" width="15%" align="center">再利用可能</td>
							<td class="td_bg" width="15%" align="center">確認必要</td>
						</tr>
						<s:iterator value="f100102.henpinListzumi" status="status" id="t2">
							<s:hidden
								name="f100102.henpinListzumi[%{#status.index}].henpinshoribango"
								value="%{henpinshoribango}" />
							<s:iterator value="detailList" status="status1" id="detail">
								<tr class="bg_tr"
									id="<s:property value='#t2.henpinshoribango'/><s:property value='#status1.index'/>_zumi"
									style="display: none">
									<td class="td_bg" align="center" valign="middle"
										id="<s:property value='#t2.henpinshoribango'/><s:property value='#status1.index'/>_check">
										<s:checkbox cssStyle="display:none"
											name="f100102.henpinListzumi[%{#status.index}].henpinzumiList[%{#status1.index}].checkedFlg" />
										<s:if test="shohinbango == 00000">
											<s:textfield
												name="f100102.henpinListzumi[%{#status.index}].henpinzumiList[%{#status1.index}].shohinbango"
												value="不明" />
										</s:if> <s:else>
											<s:property value='shohinbango' />
											<s:hidden
												name="f100102.henpinListzumi[%{#status.index}].henpinzumiList[%{#status1.index}].shohinbango" />
										</s:else>
									</td>
									<td class="td_bg" align="center" valign="middle"><s:property
											value='kosu' /> <s:hidden
											name="f100102.henpinListzumi[%{#status.index}].henpinzumiList[%{#status1.index}].sairiyokanoflg"
											value="%{kosu}" /></td>
									<td class="td_bg" align="center" valign="middle"><s:if
											test="sairiyokanoflg == 1">再利用可</s:if> <s:else>
											<font color="red">利用不可</font>
										</s:else> <s:hidden
											name="f100102.henpinListzumi[%{#status.index}].henpinzumiList[%{#status1.index}].sairiyokanoflg"
											value="%{sairiyokanoflg}" /></td>
									<td class="td_bg" align="center" valign="middle"><s:if
											test="kakuninflg == 1">
											<font color="red">確認必要</font>
										</s:if> <s:hidden
											name="f100102.henpinListzumi[%{#status.index}].henpinzumiList[%{#status1.index}].kakuninflg"
											value="%{kakuninflg}" /></td>
								</tr>
							</s:iterator>
						</s:iterator>
					</table>
				</div>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button"
							onclick="hideDivZumi(document.getElementsByName('f100102.shoribango_henpinzumi')[0].value);"
							value="戻る" /></td>
						<td align="center"><input type="button"
							onclick="actionSubmit('A10010211?shoribango='+document.getElementsByName('f100102.shoribango_henpinzumi')[0].value);"
							value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div-tuika'
			style="width: 850px; height: 500px; position: fixed; display: none"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center" width="100%">
					<tr>
						<td align="left">追加発送情報を入力してください</td>
					</tr>
				</table>
				<table align="center" width="95%" style="display: none">
					<tr>
						<td align="left">処理番号：<s:textfield
								name="f100102.tuikashoribango_shuseihaso" readonly="true"
								cssClass="readonly" /></td>
					</tr>
				</table>
				<table align="center" width="95%" class="table">
					<tr class="bg_tr">
						<td colspan="4">発送理由:</td>
					<tr>
					<tr class="bg_tr">
						<td colspan="4" class="td_bg">交換発送<s:checkbox
								name="f100102.tuikariyu" fieldValue="0" />&nbsp;&nbsp; 発送漏れ<s:checkbox
								name="f100102.tuikariyu" fieldValue="1" />&nbsp;&nbsp; プレゼント<s:checkbox
								name="f100102.tuikariyu" fieldValue="2" />&nbsp;&nbsp;
							その他&nbsp;<s:textfield name="f100102.tuikariyuSonota" /></td>
					</tr>
					<tr class="bg_tr">
						<td width="20%">発送送料負担:</td>
						<td class="td_bg" colspan="3"><s:radio
								list="#{ '0':'店舗&nbsp;&nbsp;&nbsp;','1':'お客様&nbsp;&nbsp;&nbsp;'}"
								value="0" name="f100102.tuikasoryofutan" /></td>
					</tr>
					<tr class="bg_tr">
						<td>備考:</td>
						<td class="td_bg" colspan="3"><s:textarea
								style="width:300px;height:20px" name="f100102.tuikabiko" /></td>
					</tr>
				</table>
				<br />
				<table align="center" width="95%" id="hasoInfoTuika"
					style="display: none">
					<tr>
						<td>問い合わせ番号:</td>
						<td><s:textfield name="f100102.toiawasebango_tuikahaso"></s:textfield></td>
						<td>配送方法 <s:select list="#{ '0':'その他','1':'宅急便','2':'メール便'}"
								name="f100102.haisohoho_tuikahaso" />
						</td>
						<td>配送会社 <s:select
								list="#{ '1000':'その他','1001':'ヤマト運輸','1002':'佐川急便','1003':'郵便局'}"
								name="f100102.haisokaisha_tuikahaso" />
						</td>
					</tr>
				</table>
				<table align="center" width="100%">
					<tr>
						<td align="left">発送商品を選択してください</td>
					</tr>
				</table>
				<table align="center" width="800px" id="tuikaTbl">
					<tr>
						<td align="left" width="250px">商品番号:&nbsp;&nbsp;<input
							type="text" id="tuikaShohin" size="20"></td>
						<td align="left" width="150px">個数:&nbsp;&nbsp;<input
							type="text" id="tuikaKosu" size="5" style="ime-mode: disabled"></td>
						<td align="left"><input type="button"
							onclick="getShohinInfo();" value="追加"></td>
					</tr>
				</table>
				<div style="height: 230px; overflow: auto">
					<table cellspacing="1" cellpadding="2" class="table" width="800px"
						align="center">
						<tr class="bg_tr">
							<td width="50%" align="center">商品</td>
							<td width="30%" align="center">発送個数</td>
							<td width="20%" align="center">&nbsp;</td>
						</tr>
					</table>
					<div id="tuikahasoListDiv"></div>
				</div>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button"
							onclick="hideDivTuika();" value="戻る" /></td>
						<td align="center"><input type="button" id="tuikaTuikaBtn"
							onclick="actionSubmit('A10010212');" value="確定" /> <input
							type="button" id="tuikaShuseiBtn"
							onclick="actionSubmit('A10010213');" value="確定" /> <input
							type="button" id="tuikaHasoBtn"
							onclick="actionSubmit('A10010215');" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div-henkin'
			style="width: 400px; height: 300px; position: fixed; display: none"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center" width="100%">
					<tr>
						<td align="left">返金情報を入力してください</td>
					</tr>
				</table>
				<table align="center" id="henkinshoribangotbl" width="95%">
					<tr>
						<td align="left">処理番号：<s:textfield
								name="f100102.shoribango_henkin" readonly="true"
								cssClass="readonly" /></td>
					</tr>
				</table>
				<table align="center" width="95%" class="table">
					<tr class="bg_tr">
						<td width="100px">発送返金種別</td>
						<td class="td_bg">その他</td>
					<tr>
					<tr class="bg_tr">
						<td>返金金額:</td>
						<td class="td_bg"><s:textfield name="f100102.henkinkinkaku" /></td>
					</tr>
					<tr class="bg_tr">
						<td>備考:</td>
						<td class="td_bg"><s:textarea name="f100102.henkinbiko"
								style="width:250px;height:120px" /></td>
					</tr>
				</table>
				<br>
				<table align="center" width="200px">
					<tr>
						<td align="center"><input type="button"
							onclick="hideDivHenkin();" value="戻る" /></td>
						<td align="center"><input type="button" id="henkinBtn1"
							onclick="actionSubmit('A10010216');" value="確定" /> <input
							type="button" id="henkinBtn2"
							onclick="actionSubmit('A10010217');" value="確定" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id='pop-div-dokon'
			style="width: 400px; height: 300px; position: fixed; display: none"
			class="pop-box">
			<div class="pop-box-body">
				<table align="center" width="100%">
					<tr>
						<td align="left">同梱情報を入力してください</td>
					</tr>
				</table>
				<div style="height: 180px; overflow: auto">
					<table cellspacing="1" cellpadding="2" class="table" width="95%"
						align="center">
						<tr class="bg_tr">
							<td align="center">受注番号</td>
						</tr>
						<s:iterator value="f100102.dokonList" status="status" id="t2">
							<tr class="bg_tr">
								<td class="td_bg" align="center" valign="middle"><s:property
										value='juchubango' />&nbsp;<s:property value='type' /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<table align="center" id="henkinshoribangotbl" width="95%">
					<tr>
						<td align="left">受注番号追加：<s:textfield
								name="f100102.juchubango_dokon" />&nbsp;&nbsp;&nbsp;<input
							type="button" id="dokonbtn" onclick="actionSubmit('A10010221');"
							value="確定" /></td>
					</tr>
				</table>
				<br />
				<table align="center" width="95%">
					<tr>
						<td align="left"><input type="button" onclick="hideDiv();"
							value="戻る" /></td>
					</tr>
				</table>
			</div>
		</div>
		<s:iterator value="f100102.henpinshuseikaList" status="status">
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].shoribango"
				value="%{shoribango}" />
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].henpinriyutenpogawa"
				value="%{henpinriyutenpogawa}" />
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].henpinriyutenpogawasonota"
				value="%{henpinriyutenpogawasonota}" />
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].henpinriyuokyakugawa"
				value="%{henpinriyuokyakugawa}" />
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].henpinriyuokyakugawasonota"
				value="%{henpinriyuokyakugawasonota}" />
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].shokihasosoryofutan"
				value="%{shokihasosoryofutan}" />
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].uketorisoryofutan"
				value="%{uketorisoryofutan}" />
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].henkinhistsuyoflg"
				value="%{henkinhistsuyoflg}" />
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].henkinkagaku"
				value="%{henkinkagaku}" />
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].sairiyokanoflg"
				value="%{sairiyokanoflg}" />
			<s:hidden
				name="f100102.henpinshuseikaList[%{#status.index}].kakuninhituyo"
				value="%{kakuninhituyo}" />
			<s:hidden name="f100102.henpinshuseikaList[%{#status.index}].biko"
				value="%{biko}" />
		</s:iterator>
		<s:hidden name="f100102.tuikashoribango_tuikashusei" />
		<s:hidden name="f100102.tuikariyu_tuikashusei" />
		<s:hidden name="f100102.tuikariyusonota_tuikashusei" />
		<s:hidden name="f100102.hasosoryofutan_tuikashusei" />>
        <s:hidden name="f100102.biko_tuikashusei" />
		<input type="hidden" id="hid_result">
		<s:hidden name="scrollx" />
		<s:hidden name="scrolly" />
		<s:hidden name="f100102.jsonArr" id="jsonArr" />
		<s:hidden name="f100102.jsonArr_moto" id="jsonArr_moto" />
		<s:hidden name="f100102.oyaFlg" />
		<s:hidden name="f100102.jsonArr_dokon" id="jsonArr_dokon" />
		<s:hidden name="f100102.jsonArr_moto_dokon" id="jsonArr_moto_dokon" />
		<s:hidden name="f100102.nyukafukaBangoArr" />
	</s:form>
</body>
<script type="text/javascript">
window.onscroll = function(){
	
	document.getElementsByName("scrolly")[0].value = $(document).scrollTop();
	document.getElementsByName("scrollx")[0].value = $(document).scrollLeft();
}
</script>
</html>