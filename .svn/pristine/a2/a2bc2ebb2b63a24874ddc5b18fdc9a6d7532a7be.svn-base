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

function popupDiv() { 

    var div_obj = $("#pop-div");    
	div_obj.css("top",($(window).height()-220)/2);
	div_obj.css("left",($(window).width()-400)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
}   
function popupDiv2() { 

    var div_obj = $("#pop-div2");    
	div_obj.css("top",($(window).height()-220)/2);
	div_obj.css("left",($(window).width()-400)/2);
    //添加并显示遮罩层   
    $("#mask").show();  
    div_obj.show();  
}   

function hideDiv() {   
	  $("#mask").hide();   
	  $("#pop-div").hide(); 
	  $("#pop-div2").hide(); 
	} 
function setFenlei(index,zhangwuliushuihao){
	popupDiv2();
	$("#zhangwuliushuihaotd").html(zhangwuliushuihao);
	$("#rowindex_hid").val(index);
	$("#zhangwuliushuihao_hid").val(zhangwuliushuihao);
}

function pushBikoData(index,value){
	
	var bango = document.getElementsByName("f150104.alipayMeisaiList["+index+"].zhangwuliushuihao")[0].value;
	$.post("A15010405", {
		zhangwuNo : bango,
		biko : value,
	}, function(result) {
		if (result != "true") {
			alert("アップデート失敗！");
		}
	}, "json");
}
function pushFenleiData(){
	var idx = $("#rowindex_hid").val();
	var zhangwuliushuihao = $("#zhangwuliushuihao_hid").val();
	var leibie = document.getElementsByName("f150104.leibie")[1].value;
	var leibieming = $("#leibieid").find("option:selected").text();
	$.post("A15010404", 
			{
		    zhangwuNo:zhangwuliushuihao,
		    fenleimingcheng:leibie,
			}, 
			function(result) {
				if("true" == result){
				
					document.getElementById("td1["+idx+"]").innerHTML=(leibieming+"<br/><input type=\"button\" value=\"设定\" onclick=\"setFenlei("+idx+","+zhangwuliushuihao+")\"/>");
					hideDiv();
				}else{
					alert("操作失败");
				}
		    }
		,"json");
}

function init(){
	var selObj1 = document.getElementsByName("f150104.leibie")[0];
	var selObj2 = document.getElementsByName("f150104.leibie")[1];
	document.getElementsByName("f150104.leibie")[1].options.length = 0; 
	document.getElementsByName("f150104.leibie")[0].options.length = 0; 
	$.post("getJiaoyiFenleiList", 
			{
			area:'1',
			}, 
			function(result) {
				var typeList = result.split("&");
				selObj1.options.add(new Option("全部",""));
				for(var i=0;i<typeList.length;i++){
					var valList = typeList[i].split("%");
					selObj1.options.add(new Option(valList[1],valList[0]));
					selObj2.options.add(new Option(valList[1],valList[0]));
				}	
				document.getElementsByName("f150104.leibie")[0].value  = "";
				document.getElementsByName("f150104.leibie")[1].value  = "";
		    }
		,"json");
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
	width:920px;
	overflow-y:auto;
	overflow-x:auto;
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
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f150104.dayStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="15" cssClass="Wdate" onClick="WdatePicker()" maxlength="10" name="f150104.dayEnd"/>
                </td>
                <td class="td_bg" width="100px">区分</td>
                <td class="td_bg">
                    <s:select list="#{'':'全部', '0':'进账', '1':'出账'}" name="f150104.kubun" onchange="getFenleiList();"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="100px">备注</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f150104.biko"/>
                </td>
                <td class="td_bg" width="100px">类别</td>
                <td class="td_bg">
                    <s:select list="#{'':' '}" name="f150104.leibie"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="100px">出账金额</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f150104.outStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f150104.outEnd"/>
                </td>
                <td class="td_bg" width="100px">进账金额</td>
                <td class="td_bg">
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f150104.inStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="10" maxlength="10" style="text-align:right" name="f150104.intEnd"/>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="right"><input type="button" onclick="actionSubmit('A15010402')" value="检索" style="width:50px;height:25px"/></td>
            </tr>
		</table>
		</div>
		<div id="div2">
		<table width="900px">
            <tr>
                <td>検索結果：<s:label name="f150104.resultCount" />件<s:hidden name="f150104.resultCount" /></td>
                <td align="right"><input type="button" onclick="popupDiv();" value="支付宝CSV导入"></td>
            </tr>
        </table>
        <div id="topDiv" style="width:900px;overflow:hidden">
        <table class="table" width="2000px" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="100px" align="center">发生时间</td>
			    <td width="120px" align="center">账单分类</td>
				<td width="200px" align="center">商品名称</td>
				<td width="140px" align="center">对方账号</td>
				<td width="80px" align="center">收入金额</td>
				<td width="80px" align="center">支出金额</td>
				<td width="80px" align="center">账户余额</td>
				<td width="110px" align="center">交易渠道</td>
				<td width="90px" align="center">业务类型</td>
				<td width="170px" align="center">备注1</td>
				<td width="260px" align="center">备注2</td>
				<td width="130px" align="center">账务流水号</td>
			    <td width="200px" align="center">业务流水号</td>
				<td align="center">商户订单号</td>
			</tr>
		</table>
		</div>
		</div>
		<div id="div3">
	    <table width="2000px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f150104.alipayMeisaiList" status="status">
			<tr class="bg_tr" height="40px">
			    <td width="100px" class="td_bg" id="td5[${status.index}]"><s:property value='fashengshijian'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].fashengshijian" value="%{fashengshijian}"/></td>
			    <td width="120px" class="td_bg" id="td1[${status.index}]" align="center"><s:property value='zhangdanfenlei'/><br/><input type="button" value="设定" onclick="setFenlei(${status.index},<s:property value='zhangwuliushuihao'/>)"/></td>
			    <td width="200px" class="td_bg" id="td4[${status.index}]" ><s:property value='shangpinmingcheng'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].shangpinmingcheng" value="%{shangpinmingcheng}"/></td>
			    <td width="140px" class="td_bg" id="td6[${status.index}]"><s:property value='duifangzhanghao'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].duifangzhanghao" value="%{duifangzhanghao}"/></td>
			    <td width="80px" align="right" class="td_bg" id="td7[${status.index}]" align="center"><s:property value='shourujine'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].shourujine" value="%{shourujine}"/></td>
			    <td width="80px" align="right" class="td_bg" id="td7[${status.index}]" align="center"><s:property value='zhichujine'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].zhichujine" value="%{zhichujine}"/></td>
			    <td width="80px" align="right" class="td_bg" id="td7[${status.index}]" align="center"><s:property value='zhanghuyue'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].zhanghuyue" value="%{zhanghuyue}"/></td>
			    <td width="110px" class="td_bg" id="td7[${status.index}]" align="center"><s:property value='jiaoyiqudao'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].jiaoyiqudao" value="%{jiaoyiqudao}"/></td>
			    <td width="90px" class="td_bg" id="td7[${status.index}]" align="center"><s:property value='yewuleixing'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].yewuleixing" value="%{yewuleixing}"/></td>
			    <td width="170px" class="td_bg" id="td7[${status.index}]" align="center"><s:property value='beizhu'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].beizhu" value="%{beizhu}"/></td>
			    <td width="260px" class="td_bg" id="td7[${status.index}]" align="center"><s:textarea style="width:240px;height:38px" onblur="pushBikoData(%{#status.index},this.value);" name="f150104.alipayMeisaiList[%{#status.index}].beizhu2" value="%{beizhu2}"/></td>
			    <td width="130px" class="td_bg" id="td1[${status.index}]" ><s:property value='zhangwuliushuihao'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].zhangwuliushuihao" value="%{zhangwuliushuihao}"/></td>
			    <td width="200px" class="td_bg" id="td2[${status.index}]" ><s:property value='yewuliushuihao'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].yewuliushuihao" value="%{yewuliushuihao}"/></td>
			    <td class="td_bg" id="td3[${status.index}]" ><s:property value='shanghudingdanhao'/><s:hidden name="f150104.alipayMeisaiList[%{#status.index}].shanghudingdanhao" value="%{shanghudingdanhao}"/></td>
			    <s:hidden name="f150104.alipayMeisaiList[%{#status.index}].fenleiKanrigango" value="%{fenleiKanrigango}"/>
			    <s:hidden name="f150104.alipayMeisaiList[%{#status.index}].addedfile1" value="%{addedfile1}"/>
			    <s:hidden name="f150104.alipayMeisaiList[%{#status.index}].addedfile2" value="%{addedfile2}"/>
			    <s:hidden name="f150104.alipayMeisaiList[%{#status.index}].addedfile3" value="%{addedfile3}"/>
			    <s:hidden name="f150104.alipayMeisaiList[%{#status.index}].addedfile4" value="%{addedfile4}"/>
			    <s:hidden name="f150104.alipayMeisaiList[%{#status.index}].addedfile5" value="%{addedfile5}"/>
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
		<div id='pop-div' style="width:400px;height:220px;display:none;position: fixed;" class="pop-box">   
            <div class="pop-box-body" style="height:220px">
            <table align="center" width="395px" height="170px">
                <tr>
                    <td>支付宝CSV文件</td>
                    <td><s:file name="f150104.alipayCsvFile"/></td>
                </tr>
            </table>
            <table align="center" width="95%">
                <tr>
                    <td width="25%" align="right"><input type="button" onclick="actionSubmit('A15010403')"  value="確定" /></td>
                    <td width="25%"></td>
                    <td width="25%"></td>
                    <td width="25%"><input type="button" onclick="hideDiv();" value="返回" /></td>
                </tr>
            </table>
            </div>
            
        </div>
        <div id='pop-div2' style="width:400px;height:220px;display:none;position: fixed;" class="pop-box">   
            <div class="pop-box-body" style="height:220px">
            <table align="center" width="395px" height="170px">
                <tr>
                    <td width="80px">账务流水号：</td>
                    <td id="zhangwuliushuihaotd"></td>
                </tr>
                <tr>
                    <td>账单分类:</td>
                    <td><s:select list="#{'':' '}" name="f150104.leibie" id='leibieid'/></td>
                </tr>
            </table>
            <table align="center" width="95%">
                <tr>
                    <td width="25%" align="right"><input type="button" onclick="pushFenleiData()"  value="確定" /></td>
                    <td width="25%"></td>
                    <td width="25%"></td>
                    <td width="25%"><input type="button" onclick="hideDiv();" value="返回" /></td>
                </tr>
            </table>
            </div>
            
        </div>
		<s:hidden name="viewId" value="V030201"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="accountId"/>
		<s:hidden name="area"/>
		<s:hidden name="f150104.account_id"/>
		<s:hidden id="rowindex_hid"/>
		<s:hidden id="zhangwuliushuihao_hid"/>
	</s:form>
</body>
<script type="text/javascript">
document.getElementById("div3").onscroll = function(){
	$("#topDiv").scrollLeft($("#div3").scrollLeft());
}
</script>
</html>