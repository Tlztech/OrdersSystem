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

function init() {
    var imgs = document.getElementsByName("detailImg");
    if (imgs) {
        for ( var i = 0; i < imgs.length; i++) {
            document.getElementsByName("detailImg")[i].src = "/OrdersSystem/taobaoPic/"
                    + document.getElementsByName("f080201.shumokubango")[0].value + "/"
                    + document.getElementsByName("f080201.shohinList[" + i
                            + "].mekabango")[0].value  + "/"
                    + document.getElementsByName("f080201.shohinList[" + i
                            + "].mekabango")[0].value + "_1.jpg";
        }
    }
    setNameAndType();
    hideDiv();
}

function doAction(actionId){
	popupDiv();
    var logKey = "";
    logKey = _getRandomString(10);
    actionSubmit(actionId+'?logKey='+logKey);
    setInterval(function(){
        getLog(logKey);
    },2000);
}

function popupDiv() {   
    var div_obj = $("#pop-div");    
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}   
function popupDiv2() {   
    var div_obj = $("#pop-div2");    
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
}  
function hideDiv() {   
  $("#mask").hide();   
  $("#pop-div").hide(); 
  $("#pop-div2").hide();
} 

function setNameAndType(){
	var taobaoSts = null;
	var shoHinSts = null;
    for ( var i = 0; typeof (document
            .getElementsByName("f080201.shohinList[" + i
                    + "].mekabango")[0]) != "undefined"; i++) {
    	taobaoSts = document.getElementsByName("f080201.shohinList[" + i + "].taobaoStatus")[0].value;
    	shoHinSts = document.getElementsByName("f080201.shohinList[" + i + "].shouhinStatus")[0].value;
    	if(taobaoSts == "00"){
            document.getElementById("td2["+i+"]").innerHTML+="未上架";
    	}else if(taobaoSts == "01"){
            document.getElementById("td2["+i+"]").innerHTML+="已上架";
    	}
    	if(shoHinSts == "00"){
            document.getElementById("td3["+i+"]").innerHTML+="通常"; 
            setTdColor(i,"");
    	}else if(shoHinSts == "01"){
            document.getElementById("td3["+i+"]").innerHTML+="新規"; 
            setTdColor(i,"#9ACD32");
    	}else if(shoHinSts == "02"){
            document.getElementById("td3["+i+"]").innerHTML+="更新あり"; 
            setTdColor(i,"#FFE4c4");
    	}else if(shoHinSts == "03"){
            document.getElementById("td3["+i+"]").innerHTML+="削除"; 
            setTdColor(i,"#A9A9A9");
    	}
        }
}
function setTdColor(i,color){
	document.getElementById("td1["+i+"]").style.backgroundColor=color;
	document.getElementById("td2["+i+"]").style.backgroundColor=color;
	document.getElementById("td3["+i+"]").style.backgroundColor=color;
	document.getElementById("td4["+i+"]").style.backgroundColor=color;
	document.getElementById("td5["+i+"]").style.backgroundColor=color;
	document.getElementById("td6["+i+"]").style.backgroundColor=color;
	document.getElementById("td7["+i+"]").style.backgroundColor=color;
	document.getElementById("td8["+i+"]").style.backgroundColor=color;
	document.getElementById("td9["+i+"]").style.backgroundColor=color;
	document.getElementById("td10["+i+"]").style.backgroundColor=color;
	document.getElementById("td11["+i+"]").style.backgroundColor=color;
	document.getElementById("td12["+i+"]").style.backgroundColor=color;
	document.getElementById("td13["+i+"]").style.backgroundColor=color;
	document.getElementById("td14["+i+"]").style.backgroundColor=color;
	document.getElementById("td15["+i+"]").style.backgroundColor=color;
}
</script>
<style type="text/css">
<!--

#div1 {
	width: 1050px;
	position: relative;
	top: 10px;
	left:20px;
}

#div2 {
    left: 20px;
	top: 40px;
	width:1100px;
	overflow-x:hidden;
	position: relative;
}
#div3 {
	height: 460px;
	width:1120px;
	overflow-y:scroll;
	overflow-x:scroll;
	position: relative;
	top: 39px;
	left: 20px;
}
#div5 {
    left: 20px;
	top: 40px;
	width:1100px;
	overflow-x:hidden;
	position: relative;
}
#div4 {
	height: 70px;
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
<div id='mask' class="mask" style="width:100%;height:100%;"></div>
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
    
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="100%" border="0">
		　　　<tr class="bg_tr">
                <td class="td_bg" colspan="4">種目：<s:label name="f080201.shumokumei"/><s:hidden name="f080201.shumokumei"/><s:hidden name="f080201.shumokubango"/></td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="70px">メーカ番号：</td>
                <td class="td_bg" width="140px">
                    <s:textfield size="15" maxlength="20" name="f080201.mekabango"/>
                </td>
                <td class="td_bg" width="60px">商品名：</td>
                <td class="td_bg" width="140px">
                    <s:textfield size="15" maxlength="20" id="categoryName" name="f080201.shouhinmei"/>
                </td>
                <td class="td_bg" width="120px">TAOBAOステータス：</td>
                <td class="td_bg" width="90px">
                    <s:select list="#{ '0':'','1':'登録済み', '2':'未登録'}"   name="f080201.taobaoStatus"/>
                </td>
                <td class="td_bg" width="100px">商品ステータス：</td>
                <td class="td_bg" width="90px">
                    <s:select list="#{ '0':'','1':'新規', '2':'更新あり','3':'削除済み'}"   name="f080201.shouhinStatus"/>
                </td>
                <td class="td_bg" width="60px">ブランド：</td>
                <td class="td_bg" width="130px">
                    <s:select list="#{ '0':'','1':'snidel'}"   name="f080201.burando"/>
                </td>
                <td class="td_bg" align="right"><input type="button" onclick="actionSubmit('A03020102')" value="検索" style="width:50px;height:25px"/></td>
            </tr>
		</table>
		</div>
		<div id="div5">
		<table width="1100px">
            <tr>
                <td>检索结果：<s:label name="f080201.resultCount" />件<s:hidden name="f080201.resultCount" /></td>
                <td align="right"><input type="button" onclick="popupDiv2();" value="検索したデータのステータスを変更する"/>&nbsp;&nbsp;&nbsp;<input type="button" onclick="popupDiv2();" value="検索したデータの画像を取得する"/>&nbsp;&nbsp;&nbsp;<input type="button" onclick="popupDiv2();" value="検索したデータを更新する"/></td>
            </tr>
        </table>
        </div>
        <div id="div2">
        <table width="2600px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
<!-- 			　　　<td width="50px" align="center">チェック</td> -->
			    <td width="100px" align="center">メーカー番号</td>
			    <td width="120px" align="center">TAOBAOステータス</td>
				<td width="90px" align="center">商品ステータス</td>
			    <td width="140px" align="center">画像</td>
			    <td width="180px" align="center">商品名</td>
			    <td width="100px" align="center">ブランド</td>
			    <td width="80px" align="center">価格</td>
				<td width="180px" align="center">リンク</td>
				<td width="180px" align="center">素材</td>
				<td width="180px" align="center">カラー</td>
				<td width="180px" align="center">サイズ</td>
				<td width="180px" align="center">在庫</td>
				<td width="180px" align="center">商品詳細</td>
				<td width="180px" align="center">備考</td>				
				<td width="100px" align="center">最終更新日</td>
				<td align="center">&nbsp;</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="2600px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f080201.shohinList" status="status">
			<tr class="bg_tr">
<!-- 			    <td width="50px" class="td_bg" align="center" id="checktd[${status.index}]1"><s:checkbox onclick="checkIt(this.name)" name="f080201.shohinList[%{#status.index}].checkFlg"/></td> -->
			    <td width="100px" class="td_bg" align="center" id="td1[${status.index}]"><s:property value='mekabango'/><s:hidden name="f080201.shohinList[%{#status.index}].mekabango" value="%{mekabango}"/></td>
				<td width="120px" class="td_bg" align="center" id="td2[${status.index}]"><s:hidden name="f080201.shohinList[%{#status.index}].taobaoStatus" value="%{taobaoStatus}"/></td>
				<td width="90px" class="td_bg" align="center" id="td3[${status.index}]"><s:hidden name="f080201.shohinList[%{#status.index}].shouhinStatus" value="%{shouhinStatus}"/></td>
				<td width="140px" class="td_bg" align="center" id="td4[${status.index}]"><img width="135px" name="detailImg"><s:hidden name="f080201.shohinList[%{#status.index}].picUrl" value="%{picUrl}"/></td>
				<td width="180px" class="td_bg" align="left" id="td5[${status.index}]"><s:property value='shouhinmei'/><s:hidden name="f080201.shohinList[%{#status.index}].shouhinmei" value="%{shouhinmei}"/></td>
				<td width="100px" class="td_bg" align="left" id="td6[${status.index}]"><s:property value='burando'/><s:hidden name="f080201.shohinList[%{#status.index}].burando" value="%{burando}"/></td>
				<td width="80px" class="td_bg" align="right" id="td7[${status.index}]"><s:property value='kakaku'/><s:hidden name="f080201.shohinList[%{#status.index}].kakaku" value="%{kakaku}"/></td>
				<td width="180px" class="td_bg" align="left" id="td8[${status.index}]"><a target="_blank" href="<s:property value='rinku'/>"><s:property value='rinku'/></a><s:hidden name="f080201.shohinList[%{#status.index}].rinku" value="%{rinku}"/></td>
				<td width="180px" class="td_bg" align="left" id="td9[${status.index}]"><s:property value='sozai'/><s:hidden name="f080201.shohinList[%{#status.index}].sozai" value="%{sozai}"/></td>
				<td width="180px" class="td_bg" align="left" id="td10[${status.index}]"><s:property value='kara'/><s:hidden name="f080201.shohinList[%{#status.index}].kara" value="%{kara}"/></td>
				<td width="180px" class="td_bg" align="left" id="td11[${status.index}]"><s:property value='saizu'/><s:hidden name="f080201.shohinList[%{#status.index}].saizu" value="%{saizu}"/></td>
				<td width="180px" class="td_bg" align="left" id="td12[${status.index}]"><s:property value='zaiko'/><s:hidden name="f080201.shohinList[%{#status.index}].zaiko" value="%{zaiko}"/></td>
				<td width="180px" class="td_bg" align="left" id="td13[${status.index}]"><s:property value='shouhinshousai'/><s:hidden name="f080201.shohinList[%{#status.index}].shouhinshousai" value="%{shouhinshousai}"/></td>
				<td width="180px" class="td_bg" align="left" id="td14[${status.index}]"><s:property value='biko'/><s:hidden name="f080201.shohinList[%{#status.index}].biko" value="%{biko}"/></td>
				<td width="100px" class="td_bg" align="left" id="td15[${status.index}]"><s:property value='saishukoshinbi'/><s:hidden name="f080201.shohinList[%{#status.index}].saishukoshinbi" value="%{saishukoshinbi}"/></td>
				<td class="td_bg" align="center" id="td16[${status.index}]">
				    <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.mode.value='2';doAction('A08010202');" value="更新する"/>&nbsp;&nbsp;&nbsp;
				    <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.mode.value='2';doAction('A08010203');" value="画像ダウンロード"/>&nbsp;&nbsp;&nbsp;
				    <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.mode.value='2';actionConfirm()" value="ステータス変更"/>&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		    </s:iterator>
		</table>
		</div>
		<s:hidden name="viewId" value="V030201"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		
		<div id='pop-div' style="left:350px;top:100px;width:450px;height:260px;position:absolute;" class="pop-box">   
            <div class="pop-box-body" >
            <table>
                <tr>
                    <td colspan="2" align="center"><textarea id="logArea" style="width:400px;height:200px" readOnly></textarea></td>
                </tr>
            </table>
            </div>
        </div>
        <div id='pop-div2' style="left:350px;top:100px;width:450px;height:260px;position:absolute;" class="pop-box">   
            <div class="pop-box-body" >
            <table>
                <tr>
                    <td colspan="2" align="center">取り込み中。。。。。。。。</td>
                </tr>
            </table>
            </div>
        </div>
        		<div id="div4">
		<table width="1120px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
		    <tr>
		        <td>
		            <a href="#" id="firstPage" onclick="actionSubmit('A03020106?page=first')">首页</a>&nbsp;<a href="#" id="lastPage" onclick="actionSubmit('A03020106?page=last')">上一页</a>&nbsp;
		            <a href="#" id="nextPage" onclick="actionSubmit('A03020106?page=next')">下一页</a>&nbsp;<a href="#" id="endPage" onclick="actionSubmit('A03020106?page=end')">尾页</a>
		            &nbsp;&nbsp;&nbsp;&nbsp;第<s:label name="nowPage"/><s:hidden id="nowPage" name="nowPage"/>页&nbsp;/&nbsp;共<s:label name="allPage"/><s:hidden id="allPage" name="allPage"/>页
		            &nbsp;&nbsp;&nbsp;&nbsp;跳转到<s:textfield cssErrorStyle="background:red;" name="goPage" size="1"/>页 <input onclick="actionSubmit('A03020106?page=go')" type="button" value="GO">
		        </td>
		    </tr>
		</table>
		<table width="1120px" cellspacing="1" cellpadding="1" align="left" border="0">
            <tr>
                <td align="left"><input type="button" onclick="document.form1.mode.value='1';doAction('A08010203');" value="一括画像取得"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" onclick="document.form1.mode.value='1';doAction('A08010202');" value="一括更新する"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" onclick="actionSubmit('A08010204');" value="EXCELダウンロード"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" onclick="popupDiv2();" value="画像ダウンロード"/>&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
        </table>
		</div>
		
	</s:form>
</body>
<script type="text/javascript">
$('#div3').scroll( function() { 
$('#div2').scrollLeft($(this).scrollLeft()); 
}); 
$('#div2').scroll( function() { 
$('#div3').scrollLeft($(this).scrollLeft()); 
}); 
</script>
</html>