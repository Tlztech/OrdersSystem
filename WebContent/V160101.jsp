<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
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
function doAction(actionid){
	var div_obj = $("#pop-div");    
    //添加并显示遮罩层   
    $("#mask").show("slow");  
    div_obj.show("slow");  
    var logKey = "";
    logKey = _getRandomString(10);
    actionSubmit(actionid+'?logKey='+logKey);
    setInterval(function(){
        getLog(logKey);
    },500);
}


function hideDiv() {   
  $("#mask").hide();   
  $("#pop-div").hide();   
} 

function init(){
	hideDiv();
}

</script>
<style type="text/css">
<!--

#div1 {
	width: 500px;
	position: relative;
	top: 20px;
	left:100px;
}

#div2 {
    left: 50px;
	top: 40px;
	width:580px;
	position: relative;
}
#div3 {
	height: 520px;
	width:580px;
	overflow-y:auto;
	overflow-x:hidden;
	position: relative;
	top: 39px;
	left: 50px;
}
-->
</style>
</head>
<body onload="init()">
	<%
		Connection conn = null;
	Map<String, String> shopMap = new HashMap<String, String>();
	try {
		conn = com.rakuten.util.JdbcConnection.getConnection();
		String sql = "SELECT distinct SHOP_ID FROM rakuten.shop where SITE IN ('Yahoo', '楽天') and DELETE_FLG is null";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			shopMap.put(rs.getString("SHOP_ID"), rs.getString("SHOP_ID"));
		}

		request.setAttribute("shopmap", shopMap);
		
		Map<String, String> rakutenShopMap = new HashMap<String, String>();
		sql = "SELECT SHOP_ID FROM rakuten.shop where SITE = '楽天' and DELETE_FLG is null";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			rakutenShopMap.put(rs.getString("SHOP_ID"), rs.getString("SHOP_ID"));
		}
		
		request.setAttribute("rakutenshopmap", rakutenShopMap);

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
		<div id="div2">
		<s:actionerror name="err" cssStyle="color:red;font-size:13"/>
		<table>
		    <!--tr>
		        <td>
		        货号：<s:textfield style="width:100px;height:22px" name="f160101.shohinbango"/>
		        <s:select list="#{'楽天':'楽天','Yahoo':'Yahoo'}" style="width:120px;height:30px" name="f160101.site"/>
		        <s:select list="#request.shopmap" style="width:120px;height:30px" name="f160101.shop"/>
		        <input type="button" style="width:100px;height:30px" value="同步库存" onclick="actionSubmit('A16010102')">
		        </td>
		    </tr>-->
		    <tr>
		        <td>
		        订单编号：<s:textfield style="width:170px;height:22px" name="f160101.chumonbango"/>
		        <input type="button" style="width:100px;height:30px" value="单一订单删除" onclick="actionSubmit('A16010103')">
		        <input type="button" style="width:130px;height:30px" value="单一订单SIZE删除" onclick="actionSubmit('A16010112')">
		        </td>
		    </tr>
		    <tr>
		        <td>
		        订单编号CSV：<input type="file" style="width:170px;height:22px" name="inputPath" />
		        <input type="button" style="width:100px;height:30px" value="订单一括删除" onclick="actionSubmit('A16010109')">
		        <input type="button" style="width:130px;height:30px" value="订单SIZE一括删除" onclick="actionSubmit('A16010113')">
		        <input type="button" style="width:170px;height:30px" value="订单状態一括設定(その他)" onclick="actionSubmit('A16010114')">
		        </td>
		    </tr>
		    <tr>
		        <td>
		        商品编号：<s:textfield style="width:300px;height:22px" name="itemno"/>
		        <input type="button" style="width:100px;height:30px" value="单一商品删除" onclick="actionSubmit('A16010110')">
		        </td>
		    </tr>
		    <tr>
		        <td>
		        商品编号CSV：<input type="file" name="inputCommodityFilePath" />
		        <input type="button" style="width:100px;height:30px" value="商品一括删除" onclick="actionSubmit('A16010111')">
		        </td>
		    </tr>
		</table>
		<br/>
        <s:select list="#request.rakutenshopmap" style="width:120px;height:30px" name="shop2"/>
        <input type="button" style="width:100px;height:30px" value="下载商品" onclick="doAction('A16010104')">
        <input type="button" style="width:100px;height:30px" value="改网页" onclick="doAction('A16010105')">
        <input type="button" style="width:100px;height:30px" value="处理订单" onclick="doAction('A16010106')"><br/>
        <br/>
        <input type="file" name="inputPath" /><input type="button" style="width:130px;height:30px" value="获取最新在库情报" onclick="actionSubmit('A16010107')">
        <input type="button" style="width:130px;height:30px" value="qoo10在库情报" onclick="actionSubmit('A16010108')">
		</div>
		<s:hidden name="viewId" value="V160101"/>
		<s:hidden name="searchMode"/>
		<s:hidden name="mode"/>
		<s:hidden name="rowIndex"/>
		<s:hidden name="scrollx"/>
        <s:hidden name="scrolly"/>
        <div id='pop-div' style="left:350px;top:100px;width:450px;height:260px;position:absolute;" class="pop-box">   
            <div class="pop-box-body" >
            <table>
                <tr>
                    <td align="center"><textarea id="logArea" style="width:400px;height:200px" readOnly></textarea></td>
                </tr>
                <tr>
                    <td align="center"><input type="button" onclick="hideDiv();" value="关闭"></td>
                </tr>
            </table>
            </div>
        </div>
	</s:form>
</body>

</html>