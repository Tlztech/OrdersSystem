<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<script type="text/javascript" charset="UTF-8">
    var html;
    function getHtml(){
        html = document.getElementById("productInfo").innerHTML;
    }
    function addproduct(){
       document.getElementById("productInfo").innerHTML +=html;
    }
</script>
</head>
<body onload="getHtml()">
<form>
    <div style="width:900px;align:center;margin-top: 5px;margin-left: auto;margin-right: auto;">
        <b>商品信息</b>
        <hr>
        <input type="button" onclick="addproduct()" value="添加商品" style="width:60px;height:15px">
    </div>
    <div id="productInfo">
	<table class="table" cellspacing="1" cellpadding="2" width="900px" align="center" border="0">
		<tbody>
			<tr class="bg_tr">
			    <td width="160px" class="td_bg" rowspan="4"></td>
				<td width="50px" height="20px">搜索</td>
				<td class="td_bg" colspan="9">
					<input type="text" size="60"/>
				</td>
			</tr>
			<tr class="bg_tr">
				<td width="50px" height="20px">商品名</td>
				<td class="td_bg" colspan="5">
					<input type="text" readonly class="readonly" size="60" />
				</td>
				<td>数量</td>
				<td class="td_bg">
					<input type="text" size="7"/>
				</td>
				<td>金额</td>
				<td class="td_bg">
					<input type="text" size="7"/>
				</td>
			</tr>
			<tr class="bg_tr">
				<td height="20px">日文名</td>
				<td class="td_bg" colspan="9">
					<input type="text" readonly class="readonly" size="60"/>
				</td>
			</tr>		
			<tr class="bg_tr">
				<td height="20px">货号</td>
				<td class="td_bg" width="60px">
					<input type="text" size="7" readonly class="readonly"/>
				</td>
				<td width="50px">颜色</td>
				<td class="td_bg" width="60px">
					<input type="text" size="7"/>
				</td>
				<td width="50px">尺寸</td>
				<td class="td_bg" width="60px">
					<input type="text" size="7"/>
				</td>
				<td width="50px">供应商</td>
				<td class="td_bg" width="60px">
					<select>
					    <option></option>
		                <option>夏星</option>
		                <option>石意群（深圳）</option>
		                <option>淘宝（殷）</option>
		                <option>赵健</option>
		                <option>李子梦</option>
		                <option>高道喜</option>
					</select>
				</td>
				<td width="50px">仓库</td>
				<td class="td_bg" width="60px">
					<select>
					    <option></option>
					    <option>上海仓</option>
   	                    <option>日本仓</option>
   	                    <option>李康仓</option>
   	                    <option>tony-上海仓</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
	<br/>
	<div style="width:900px;align:center;margin-top: 5px;margin-left: auto;margin-right: auto;">
        <b>订单信息</b>
        <hr>
    </div>
    <table class="table" cellspacing="1" cellpadding="2" width="900px" align="center" border="0">
		<tbody>
			<tr class="bg_tr">
				<td width="60px" height="20px">订单来源</td>
				<td class="td_bg" width="300px" colspan="3">
					<select>
					    <option></option>
					    <option>乐天店铺ABC</option>
					    <option>乐天ex-dream</option>
					    <option>ecshop(seafree) </option>
					    <option>ecshop(izone) </option>
					    <option>ecshop(ex-dream) </option>
					    <option>bidders</option>
					    <option>亚马逊エントラン ファッション</option>
					</select>
				</td>
				<td width="60px" height="20px">支付方式</td>
				<td class="td_bg" width="160px">
					<select>
					    <option></option>
					    <option>银行</option>
					    <option>第3方</option>
					    <option>其他</option>
					    <option>信用卡</option>
					    <option>货到付款</option>
					    <option>smartbit</option>
					    <option>楽天バンク決済</option>
					    <option>便利店支付</option>
					    <option>Amazon第三方</option>
					    <option>楽天クレジット</option>
					    <option>楽天コンビ二</option>
					    <option>Edy決済</option>
					</select>
				</td>
				<td width="60px" height="20px">交易状态</td>
				<td class="td_bg" width="150px" colspan="2">
				    <input type="radio" name="status">未入金
				    <input type="radio" name="status">入金
				</td>
			</tr>
			<tr class="bg_tr">
				<td width="60px" height="20px">外部订单号</td>
				<td class="td_bg" colspan="3"><input type="text" size="40"/></td>
				<td width="60px" height="20px">快递方式</td>
				<td class="td_bg" width="60px" colspan="3">
					<select>
					    <option></option>
			            <option>信封寄</option>
                        <option>宅急便</option>
                        <option>货到付款</option>
					</select>
				</td>
			</tr>
			<tr class="bg_tr">
				<td width="60px" height="20px">商品总额</td>
				<td class="td_bg"><input type="text" size="10"/></td>
				<td width="60px" height="20px">运费</td>
				<td class="td_bg"><input type="text" size="10"/></td>
				<td width="60px" height="20px">会员点</td>
				<td class="td_bg"><input type="text" size="10"/></td>
				<td width="60px" height="20px">优惠券</td>
				<td class="td_bg"><input type="text" size="10"/></td>
			</tr>
			<tr class="bg_tr">
			    <td width="60px" height="20px"> 实际入金</td>
				<td class="td_bg" colspan="7"><input type="text" size="13"/></td>
			</tr>
		</tbody>
	</table>
	<br/>
    <div style="width:900px;align:center;margin-top: 5px;margin-left: auto;margin-right: auto;">
        <b>客户信息</b>
        <hr>
    </div>
    <table class="table" cellspacing="1" cellpadding="2" width="900px" align="center" border="0">
		<tbody>
			<tr class="bg_tr">
				<td width="60px" height="20px">姓名</td>
				<td class="td_bg"><input type="text" size="15"/></td>
				<td width="60px" height="20px">读音</td>
				<td class="td_bg"><input type="text" size="15"/></td>
				<td width="60px" height="20px">昵称</td>
				<td class="td_bg"><input type="text" size="15"/></td>
			</tr>
			<tr class="bg_tr">
				<td width="60px" height="20px">邮编</td>
				<td class="td_bg"><input type="text" size="15"/></td>
				<td width="60px" height="20px">手机</td>
				<td class="td_bg"><input type="text" size="15"/></td>
				<td width="60px" height="20px">电子邮件</td>
				<td class="td_bg"><input type="text" size="30"/></td>
			</tr>
			</tr>
			<tr class="bg_tr">
				<td width="60px" height="20px">客户地址一</td>
				<td class="td_bg" colspan="5"><input type="text" size="80"/></td>
			</tr>
			<tr class="bg_tr">
				<td width="60px" height="20px">客户地址二</td>
				<td class="td_bg" colspan="5"><input type="text" size="80"/></td>
			</tr>
			<tr class="bg_tr">
				<td width="60px" height="20px">领受书</td>
				<td class="td_bg" colspan="5"><input type="checkbox"/></td>
			</tr>
			<tr class="bg_tr">
				<td width="60px" height="20px">配送时间段</td>
				<td class="td_bg" colspan="3">
				    <select>
				        <option></option>
				        <option>午前</option>
				        <option>午后</option>
				    </select>
				    <select>
				        <option></option>
				        <option>1时</option>
				        <option>2时</option>
				        <option>3时</option>
				        <option>4时</option>
				        <option>5时</option>
				        <option>6时</option>
				        <option>7时</option>
				        <option>8时</option>
				        <option>9时</option>
				        <option>10时</option>
				        <option>11时</option>
				        <option>12时</option>
				    </select>
				    <select>
				        <option></option>
				        <option>1分</option>
				        <option>2分</option>
				    </select>
				</td>
				<td width="60px" height="20px">上门取件</td>
				<td class="td_bg">
				    <input type="checkbox"><input type="text">
				</td>
			</tr>
			<tr class="bg_tr">
				<td width="60px" height="90px">顾客留言</td>
				<td class="td_bg" colspan="5"><textarea style="height:80px;width:420px"></textarea></td>
			</tr>
		</tbody>
	</table>
	<table align="center">
		<tr>
			<td>
				<input type="button" value="提交" style="width:50px;height:25px">
			</td>
		</tr>
	</table>
</form>
</body>
</html>