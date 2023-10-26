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

function savehu(id,hasouhoho,unsoukaisha){
	$.post("A16010116", {sizeId:id,hasouHoho:hasouhoho,unsouKaisha:unsoukaisha}, function(result) {

		if("true" == result){
		    alert("変更しました");
		}else{
			alert("失敗しました")
		}
    }, "json");
}

function changed(){

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
<body onload="init()">
	<%
		Connection conn = null;
	
	try {
		conn = com.rakuten.util.JdbcConnection.getConnection();
		Map<String, String> shopMap = new HashMap<String, String>();
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

		Map<String, String> unsouKaishaMap = new HashMap<String, String>();
		sql = "SELECT UNSOUKAISHACODE, UNSOUKAISHANAME FROM rakuten.unsoukaisha_tbl order by UNSOUKAISHACODE DESC";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			unsouKaishaMap.put(rs.getString("UNSOUKAISHACODE"), rs.getString("UNSOUKAISHANAME"));
		}
		request.setAttribute("unsoukaishamap", unsouKaishaMap);

		sql = "SELECT SIZEID,HASOUHOHO,UNSOUKAISHA FROM rakuten.hassouhoho_unsoukaisha_tbl where SIZEID = '1cm'";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next()) {
			//System.out.println( rs.getString("HASOUHOHO"));
			request.setAttribute("onecm", rs.getString("SIZEID"));
			request.setAttribute("onecmhasouhoho", rs.getString("HASOUHOHO"));
			request.setAttribute("onecmunsoukaisya", rs.getString("UNSOUKAISHA"));
		}
		sql = "SELECT SIZEID,HASOUHOHO,UNSOUKAISHA FROM rakuten.hassouhoho_unsoukaisha_tbl where SIZEID = '2cm'";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			request.setAttribute("twocm", rs.getString("SIZEID"));
			request.setAttribute("twocmhasouhoho", rs.getString("HASOUHOHO"));
			request.setAttribute("twocmunsoukaisya", rs.getString("UNSOUKAISHA"));
		}
		
		sql = "SELECT SIZEID,HASOUHOHO,UNSOUKAISHA FROM rakuten.hassouhoho_unsoukaisha_tbl where SIZEID = '3cm'";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			request.setAttribute("threecm", rs.getString("SIZEID"));
			request.setAttribute("threecmhasouhoho", rs.getString("HASOUHOHO"));
			request.setAttribute("threecmunsoukaisya", rs.getString("UNSOUKAISHA"));
		}
		
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
		    <tr>
		        <td>
		        楽天発送待ち注文比較：
		        <input type="button" style="width:130px;height:30px" value="比較結果ダウンロード" onclick="doAction('A16010115')">
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
        <br/>
		<br/>
		<br/>
		<table>
			<tr>
				<td>
				1CM(0.0-0.3)：<s:textfield style="width:170px;height:22px" value="%{#request.onecmhasouhoho}" id="size1cmdeliverymethod"/>
				<s:select list="#request.unsoukaishamap" style="width:120px;height:30px" value="%{#request.onecmunsoukaisya}" id="size1cmdeliverycompany"/>
				<input type="button" style="width:100px;height:30px" value="1cm 变更保存" onclick="savehu('1cm',document.getElementById('size1cmdeliverymethod').value,document.getElementById('size1cmdeliverycompany').value)">
				</td>
			</tr>
			<tr>
				<td>
				2CM(0.4-0.6)：<s:textfield style="width:170px;height:22px" value="%{#request.twocmhasouhoho}" id="size2cmdeliverymethod"/>
				<s:select list="#request.unsoukaishamap" style="width:120px;height:30px" value="%{#request.twocmunsoukaisya}" id="size2cmdeliverycompany"/>
				<input type="button" style="width:100px;height:30px" value="2cm 变更保存" onclick="savehu('2cm',document.getElementById('size2cmdeliverymethod').value,document.getElementById('size2cmdeliverycompany').value)">
				</td>
			</tr>
			<tr>
				<td>
				3CM(0.7-1.0)：<s:textfield style="width:170px;height:22px" value="%{#request.threecmhasouhoho}" id="size3cmdeliverymethod"/>
				<s:select list="#request.unsoukaishamap" style="width:120px;height:30px" value="%{#request.threecmunsoukaisya}" id="size3cmdeliverycompany"/>
				<input type="button" style="width:100px;height:30px" value="3cm 变更保存" onclick="savehu('3cm',document.getElementById('size3cmdeliverymethod').value,document.getElementById('size3cmdeliverycompany').value)">
				</td>
			</tr>
		</table>
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