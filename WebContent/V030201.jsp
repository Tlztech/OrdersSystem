<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
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
function openWin1() {
    var returnVal = openShowModalDialog("615", "600", "P01010101");
    if (returnVal) {
        $.post("getPopUpDate", null, function(result) {
            setDisplay(result);
        }, "json");
    }
}
function setDisplay(result) {
    var object = JSON.parse(result);
    var cateName = object.cateName;
    var cateId = object.cateId;
    $("#categoryName").val(cateName);
    $("#categoryId").val(cateId);
}
function actionConfirm(){
    if(window.confirm("确认删除？")){
        actionSubmit('A03020103');
    }
}

function init() {
    //var imgs = document.getElementsByName("detailImg");
   // if (imgs) {
  //      for ( var i = 0; i < imgs.length; i++) {
        	
  //          document.getElementsByName("detailImg")[i].src = "/OrdersSystem/"
   //                 + document
   //                         .getElementsByName("f030201.commodityList["
   //                                 + i + "].picUrl")[0].value;
   //     }
   // }
    hideDiv();
}
function popupDiv() {   
    var div_obj = $("#pop-div");    
    //添加并显示遮罩层    
    div_obj.show("slow");  
}   
function popupDiv2() {   
    var div_obj = $("#pop-div2");    
    //添加并显示遮罩层   
    div_obj.show("slow");  
}
function popupDiv3() {
    var div_obj = $("#pop-div3");
    //添加并显示遮罩层  
    div_obj.show("slow");
}

function hideDiv() {   
  $("#mask").hide();   
  $("#pop-div").hide();   
  $("#pop-div2").hide(); 
} 
function hideDiv2() {   
	  $("#mask").hide("slow");   
	  $("#pop-div").hide("slow");   
	  $("#pop-div2").hide("slow");  
} 
function hideDiv3() {   
	  $("#mask").hide("slow");
	  $("#pop-div").hide("slow");  
	  $("#pop-div3").hide("slow");
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
	width:1100px;
	position: relative;
}
#div3 {
	height: 400px;
	width:1120px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 20px;
}
#div4 {
	height: 40px;
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
<%
		Connection conn = null;
	try {
		conn = com.rakuten.util.JdbcConnection.getConnection();
		int companyId = (Integer)session.getAttribute("comp");
		String sql = "SELECT distinct SITE FROM rakuten.shop where DELETE_FLG is null and COMPANY_ID = " + (companyId == 0 ? 1 : companyId);
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Map<String, String> siteMap = new HashMap<String, String>();
		siteMap.put("","--");
		while (rs.next()) {
			siteMap.put(rs.getString("SITE"), rs.getString("SITE"));
		}
		request.setAttribute("sitemap", siteMap);
		
		sql = "SELECT distinct SHOP_ID FROM rakuten.shop where DELETE_FLG is null and COMPANY_ID = " + (companyId == 0 ? 1 : companyId);
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		Map<String, String> shopMap = new HashMap<String, String>();
		shopMap.put("","--");
		while (rs.next()) {
			shopMap.put(rs.getString("SHOP_ID"), rs.getString("SHOP_ID"));
		}
		request.setAttribute("shopmap", shopMap);

	} catch (Exception e) {
		out.println(e);
	} finally {
		conn.close();
	}
	%>
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
    
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        
        <div id="div1">
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table cellspacing="1" cellpadding="2" width="90%" border="0">
            <tr class="bg_tr">
                <td class="td_bg" width="80px">商品编号：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f030201.commodityId"/>
                </td>
                <td class="td_bg" width="80px">商品分类：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" id="categoryName" name="f030201.categoryName" cssClass="readonly" readOnly="true"/>&nbsp;&nbsp;<input type="button" onclick="openWin1()" value="参照"/>
                    <input type="hidden" id="categoryId" name="f030201.categoryId">
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">中文名：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="20" maxlength="20" name="f030201.chineseName"/>
                </td>
                <td class="td_bg" width="80px">日文名：</td>
                <td class="td_bg">
                    <s:textfield size="20" maxlength="20" name="f030201.japaneseName"/>
                </td>
            </tr>
            <tr class="bg_tr">
                <td class="td_bg" width="80px">上海库存：</td>
                <td class="td_bg" width="280px">
                    <s:textfield size="10" maxlength="10" name="f030201.stockShStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="10" maxlength="10" name="f030201.stockShEnd"/>
                </td>
                <td class="td_bg" width="80px">日本库存：</td>
                <td class="td_bg">
                    <s:textfield size="10" maxlength="10" name="f030201.stockJpStart"/>&nbsp;&nbsp;～&nbsp;
                    <s:textfield size="10" maxlength="10" name="f030201.stockJpEnd"/>
                </td>
            </tr>
            <tr class="bg_tr">
            	<td class="td_bg" width="80px">更新時間：</td>
            	<td class="td_bg" width="300px"><s:textfield size="10"
							cssClass="Wdate" onClick="WdatePicker()" maxlength="10"
							name="f030201.updateTimeStart" />&nbsp;&nbsp;～&nbsp;<s:textfield
							size="10" cssClass="Wdate" onClick="WdatePicker()" maxlength="10"
							name="f030201.updateTimeEnd" /></td>
                <td class="td_bg" colspan="4" align="right"><input type="button" onclick="actionSubmit('A03020102')" value="检索" style="width:50px;height:25px"/></td>
            </tr>
		</table>
		</div>
		<div id="div2">
		<table width="1100px">
            <tr>
                <td>检索结果：<s:label name="f030201.resultCount" />件<s:hidden name="f030201.resultCount" /></td>
                <td align="right"><s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)"><input type="button" onclick="popupDiv2();" value="无销售记录库存查询"/>&nbsp;&nbsp;&nbsp;<input type="button" onclick="popupDiv();" value="CSV批量商品登陆更新"/>&nbsp;&nbsp;&nbsp;<input type="button" onclick="document.form1.shoriMode.value='1';actionSubmit('A03020104');" value="检索结果下载"/>&nbsp;&nbsp;&nbsp;</s:if><input type="button" onclick="popupDiv3();" value="一括添加商品"/></td>
            </tr>
        </table>
        <table width="1100px" class="table" cellspacing="1" cellpadding="1" align="left" border="0">
			<tr class="bg_tr">
			    <td width="70px" align="center">商品编号</td>
			    <td width="60px" align="center">分类</td>
				<td width="180px" align="center">中文名</td>
				<td width="180px" align="center">日文名</td>
				<td width="100px" align="center">导入日期</td>
				<td width="360px" align="center">库存信息</td>
				<td align="center">操作</td>
			</tr>
		</table>
		</div>
		<div id="div3">
	    <table width="1100px" class="table" cellspacing="1" cellpadding="1" align="left" border="0" style="word-break:break-all">
		    <s:iterator value="f030201.commodityList" status="status">
			<tr class="bg_tr">
			    <td width="70px" class="td_bg" align="center"><s:property value='commodityId'/><s:hidden name="f030201.commodityList[%{#status.index}].commodityId" value="%{commodityId}"/></td>
			    <td width="60px" class="td_bg" align="center"><s:property value='categoryName'/><s:hidden name="f030201.commodityList[%{#status.index}].categoryName" value="%{categoryName}"/></td>
				<td width="180px" class="td_bg" align="left"><s:property value='chineseName'/><s:hidden name="f030201.commodityList[%{#status.index}].chineseName" value="%{chineseName}"/></td>
				<td width="180px" class="td_bg" align="left"><s:property value='japaneseName'/><s:hidden name="f030201.commodityList[%{#status.index}].japaneseName" value="%{japaneseName}"/></td>
				<td width="100px" class="td_bg" align="center"><s:property value='createTime'/></td>
				<td width="87px" class="td_bg" align="left">上海库存：<s:property value='stockSh'/><s:hidden name="f030201.commodityList[%{#status.index}].stockSh" value="%{stockSh}"/></td>
				<td width="88px" class="td_bg" align="left">日本库存：<s:property value='stockJp'/><s:hidden name="f030201.commodityList[%{#status.index}].stockJp" value="%{stockJp}"/></td>
				<td width="88px" class="td_bg" align="left">运输途中：<s:property value='stockHandup'/><s:hidden name="f030201.commodityList[%{#status.index}].stockHandup" value="%{stockHandup}"/></td>
				<td width="88px" class="td_bg" align="left">进货途中：<s:property value='nyuka'/><s:hidden name="f030201.commodityList[%{#status.index}].nyuka" value="%{nyuka}"/></td>
				<td class="td_bg" align="center">
				<s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
				    <input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.shoriMode.value='2';actionSubmit('A03020201')" value="查看/修改"/>&nbsp;&nbsp;&nbsp;
				 
				    <input type="button" onclick="actionSubmit('A03020401?commodityId=<s:property value='commodityId'/>&shop=coverforefront')" value="cover详情页"/>&nbsp;&nbsp;&nbsp;
				</s:if>
				<s:if test="#session.comp!=null && (#session.comp!=1 && #session.comp!=0)">
					<input type="button" onclick="document.form1.rowIndex.value=${status.index};document.form1.shoriMode.value='2';actionSubmit('A03020201')" value="查看"/>&nbsp;&nbsp;&nbsp;
				</s:if>
				</td>
			</tr>
		    </s:iterator>
		</table>
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
		</div>
		<s:hidden name="viewId" value="V030201"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="shoriMode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="f030201.hid_categoryId"/>
		<s:hidden name="f030201.hid_chineseName"/>
		<s:hidden name="f030201.hid_commodityId"/>
		<s:hidden name="f030201.hid_japaneseName"/>
		<s:hidden name="f030201.hid_stockJpEnd"/>
		<s:hidden name="f030201.hid_stockShEnd"/>
		<s:hidden name="f030201.hid_stockJpStart"/>
		<s:hidden name="f030201.hid_stockShStart"/>
		<s:hidden name="f030201.hid_updateTimeStart"/>
		<s:hidden name="f030201.hid_updateTimeEnd"/>
		
		<div id='pop-div' style="left:400px;top:250px;width:400px;height:250px;position:absolute;display:none" class="pop-box">   
            <div class="pop-box-body" >
            <table>
                <tr height="50px">
                    <td>平台：<s:select	list="#{'楽天':'楽天','Yahoo':'Yahoo'}"	name="f030201.site" /></td>
                    <td>店舗：<s:select list="#request.shopmap" name="f030201.shop"/></td>
                </tr>
            </table>
            <table>
                <tr height="50px">
                    <td>商品列表CSV文件：<s:file name="itemCsv"/></td>
                </tr>
<!--                 <tr height="50px"> -->
<%--                     <td>所属类别CSV文件：<s:file name="itemCatCsv"/></td> --%>
<!--                 </tr> -->
                <tr height="50px">
                    <td>子货号选择项CSV文件：<s:file name="selectCsv"/></td>
                </tr>
            </table>
            <table align="center">
                <tr height="50px">
                    <td><input type="button" onclick="actionSubmit('A03020105');" value="确认"></td>
                    <td><input type="button" onclick="hideDiv2();" value="关闭"></td>
                </tr>
            </table>
            </div>
        </div>
        
        <div id='pop-div2' style="left:450px;top:110px;width:300px;height:250px;position:absolute;display:none" class="pop-box">   
            <div class="pop-box-body" >
            <table>
                <tr height="50px">
                    <td>无销售记录天数：&nbsp;&nbsp;<s:textfield size="5" name="noSellDays"/></td>
                </tr>
                <tr height="50px">
                    <td>匹配形式：&nbsp;&nbsp; <s:select list="#{ '01':'仅主货号匹配', '02':'主货号+子货号匹配'}" name="f030201.ptype"/></td>
                </tr>
            </table>
            <table align="center">
                <tr height="50px">
                    <td><input type="button" onclick="actionSubmit('A03020107');" value="确认"></td>
                    <td><input type="button" onclick="hideDiv2();" value="关闭"></td>
                </tr>
            </table>
            </div>
        </div>
        
        <div id='pop-div3' style="left:400px;top:250px;width:400px;height:250px;position:absolute;display:none" class="pop-box">   
            <div class="pop-box-body" >
            <table>
                <tr height="50px">
                    <td>平台：<s:select list="#request.sitemap" name="f030201.siteForGoods"/></td>
                    <s:if test="#session.comp!=null && (#session.comp==1 || #session.comp==0)">
                    <td>店舗：<s:select list="#request.shopmap" name="f030201.shopForGoods"/></td>
                    </s:if>
                </tr>
                <tr height="50px">
                	<td>文件语言：
                	<s:select list="#{'':'','0':'中文','1':'日文'}" name="f030201.charset"/>
                	</td>
                </tr>
            </table>
           <table>
                <tr height="50px">
                    <td>商品列表CSV文件：<s:file name="itemCsv"/></td>
                </tr>
            </table>
            <table align="center">
                <tr height="50px">
                    <td><input type="button" onclick="actionSubmit('A03020108');" value="确认"></td>
                    <td><input type="button" onclick="hideDiv3();" value="关闭"></td>
                </tr>
            </table>
            </div>
        </div>
    
	</s:form>
</body>
</html>