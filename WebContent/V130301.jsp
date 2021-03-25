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
function init(){
	
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
a {
  text-decoration: none
}
#div1 {
	width: 99%;
	position: relative;
	top: 0px;
	margin-left:auto;
	margin-right:auto;
	overflow: auto
}
#div2 {
	width: 99%;
	position: relative;
	top: 0px;
	margin-left:auto;
	margin-right:auto;
	overflow: auto
}
#div3 {
	width: 99%;
	position: relative;
	top: 0px;
	margin-left:auto;
	margin-right:auto;
	overflow: auto
}

-->
</style>
</head>
<body onload="init();">
    <s:form name="form1" theme="simple" enctype="multipart/form-data">
        <div style="width:900px;margin-top: 5px;margin-left: 10px">
        <b><s:label name="title"/></b>
        <hr>
        </div>
        <s:actionerror name="err" cssStyle="color:red;font-size:13"/>
        <div id="div1">
        ヤマト運輸
        <table cellspacing="0" cellpadding="0" border="1">
            <tr>
                <td width="55px">&nbsp;</td>
                <td width="70px" align="center">北海道</td>
                <td width="70px" align="center">青森県</td>
                <td width="70px" align="center">岩手県</td>
                <td width="70px" align="center">宮城県</td>
                <td width="70px" align="center">秋田県</td>
                <td width="70px" align="center">山形県</td>
                <td width="70px" align="center">福島県</td>
                <td width="70px" align="center">茨城県</td>
                <td width="70px" align="center">栃木県</td>
                <td width="70px" align="center">群馬県</td>
                <td width="70px" align="center">埼玉県</td>
                <td width="70px" align="center">千葉県</td>
                <td width="70px" align="center">東京都</td>
                <td width="70px" align="center">神奈川</td>
                <td width="70px" align="center">新潟県</td>
                <td width="70px" align="center">富山県</td>
                <td width="70px" align="center">石川県</td>
                <td width="70px" align="center">福井県</td>
                <td width="70px" align="center">山梨県</td>
                <td width="70px" align="center">長野県</td>
                <td width="70px" align="center">岐阜県</td>
                <td width="70px" align="center">静岡県</td>
                <td width="70px" align="center">愛知県</td>
                <td width="70px" align="center">三重県</td>
                <td width="70px" align="center">滋賀県</td>
                <td width="70px" align="center">京都府</td>
                <td width="70px" align="center">大阪府</td>
                <td width="70px" align="center">兵庫県</td>
                <td width="70px" align="center">奈良県</td>
                <td width="70px" align="center">和歌山</td>
                <td width="70px" align="center">鳥取県</td>
                <td width="70px" align="center">島根県</td>
                <td width="70px" align="center">岡山県</td>
                <td width="70px" align="center">広島県</td>
                <td width="70px" align="center">山口県</td>
                <td width="70px" align="center">徳島県</td>
                <td width="70px" align="center">香川県</td>
                <td width="70px" align="center">愛媛県</td>
                <td width="70px" align="center">高知県</td>
                <td width="70px" align="center">福岡県</td>
                <td width="70px" align="center">佐賀県</td>
                <td width="70px" align="center">長崎県</td>
                <td width="70px" align="center">熊本県</td>
                <td width="70px" align="center">大分県</td>
                <td width="70px" align="center">宮崎県</td>
                <td width="70px" align="center">鹿児島</td>
                <td width="70px" align="center">沖縄県</td>
                <td width="70px" align="center">その他</td>
            </tr>
            <tr>
                <td>size20</td>
                <td align="center"><s:textfield name="f130301.y101" size="4"/></td>
<td align="center"><s:textfield name="f130301.y102" size="4"/></td>
<td align="center"><s:textfield name="f130301.y103" size="4"/></td>
<td align="center"><s:textfield name="f130301.y104" size="4"/></td>
<td align="center"><s:textfield name="f130301.y105" size="4"/></td>
<td align="center"><s:textfield name="f130301.y106" size="4"/></td>
<td align="center"><s:textfield name="f130301.y107" size="4"/></td>
<td align="center"><s:textfield name="f130301.y108" size="4"/></td>
<td align="center"><s:textfield name="f130301.y109" size="4"/></td>
<td align="center"><s:textfield name="f130301.y110" size="4"/></td>
<td align="center"><s:textfield name="f130301.y111" size="4"/></td>
<td align="center"><s:textfield name="f130301.y112" size="4"/></td>
<td align="center"><s:textfield name="f130301.y113" size="4"/></td>
<td align="center"><s:textfield name="f130301.y114" size="4"/></td>
<td align="center"><s:textfield name="f130301.y115" size="4"/></td>
<td align="center"><s:textfield name="f130301.y116" size="4"/></td>
<td align="center"><s:textfield name="f130301.y117" size="4"/></td>
<td align="center"><s:textfield name="f130301.y118" size="4"/></td>
<td align="center"><s:textfield name="f130301.y119" size="4"/></td>
<td align="center"><s:textfield name="f130301.y120" size="4"/></td>
<td align="center"><s:textfield name="f130301.y121" size="4"/></td>
<td align="center"><s:textfield name="f130301.y122" size="4"/></td>
<td align="center"><s:textfield name="f130301.y123" size="4"/></td>
<td align="center"><s:textfield name="f130301.y124" size="4"/></td>
<td align="center"><s:textfield name="f130301.y125" size="4"/></td>
<td align="center"><s:textfield name="f130301.y126" size="4"/></td>
<td align="center"><s:textfield name="f130301.y127" size="4"/></td>
<td align="center"><s:textfield name="f130301.y128" size="4"/></td>
<td align="center"><s:textfield name="f130301.y129" size="4"/></td>
<td align="center"><s:textfield name="f130301.y130" size="4"/></td>
<td align="center"><s:textfield name="f130301.y131" size="4"/></td>
<td align="center"><s:textfield name="f130301.y132" size="4"/></td>
<td align="center"><s:textfield name="f130301.y133" size="4"/></td>
<td align="center"><s:textfield name="f130301.y134" size="4"/></td>
<td align="center"><s:textfield name="f130301.y135" size="4"/></td>
<td align="center"><s:textfield name="f130301.y136" size="4"/></td>
<td align="center"><s:textfield name="f130301.y137" size="4"/></td>
<td align="center"><s:textfield name="f130301.y138" size="4"/></td>
<td align="center"><s:textfield name="f130301.y139" size="4"/></td>
<td align="center"><s:textfield name="f130301.y140" size="4"/></td>
<td align="center"><s:textfield name="f130301.y141" size="4"/></td>
<td align="center"><s:textfield name="f130301.y142" size="4"/></td>
<td align="center"><s:textfield name="f130301.y143" size="4"/></td>
<td align="center"><s:textfield name="f130301.y144" size="4"/></td>
<td align="center"><s:textfield name="f130301.y145" size="4"/></td>
<td align="center"><s:textfield name="f130301.y146" size="4"/></td>
<td align="center"><s:textfield name="f130301.y147" size="4"/></td>
<td align="center"><s:textfield name="f130301.y148" size="4"/></td>
            </tr>
            <tr>
                <td>size40</td>
                <td align="center"><s:textfield name="f130301.y201" size="4"/></td>
<td align="center"><s:textfield name="f130301.y202" size="4"/></td>
<td align="center"><s:textfield name="f130301.y203" size="4"/></td>
<td align="center"><s:textfield name="f130301.y204" size="4"/></td>
<td align="center"><s:textfield name="f130301.y205" size="4"/></td>
<td align="center"><s:textfield name="f130301.y206" size="4"/></td>
<td align="center"><s:textfield name="f130301.y207" size="4"/></td>
<td align="center"><s:textfield name="f130301.y208" size="4"/></td>
<td align="center"><s:textfield name="f130301.y209" size="4"/></td>
<td align="center"><s:textfield name="f130301.y210" size="4"/></td>
<td align="center"><s:textfield name="f130301.y211" size="4"/></td>
<td align="center"><s:textfield name="f130301.y212" size="4"/></td>
<td align="center"><s:textfield name="f130301.y213" size="4"/></td>
<td align="center"><s:textfield name="f130301.y214" size="4"/></td>
<td align="center"><s:textfield name="f130301.y215" size="4"/></td>
<td align="center"><s:textfield name="f130301.y216" size="4"/></td>
<td align="center"><s:textfield name="f130301.y217" size="4"/></td>
<td align="center"><s:textfield name="f130301.y218" size="4"/></td>
<td align="center"><s:textfield name="f130301.y219" size="4"/></td>
<td align="center"><s:textfield name="f130301.y220" size="4"/></td>
<td align="center"><s:textfield name="f130301.y221" size="4"/></td>
<td align="center"><s:textfield name="f130301.y222" size="4"/></td>
<td align="center"><s:textfield name="f130301.y223" size="4"/></td>
<td align="center"><s:textfield name="f130301.y224" size="4"/></td>
<td align="center"><s:textfield name="f130301.y225" size="4"/></td>
<td align="center"><s:textfield name="f130301.y226" size="4"/></td>
<td align="center"><s:textfield name="f130301.y227" size="4"/></td>
<td align="center"><s:textfield name="f130301.y228" size="4"/></td>
<td align="center"><s:textfield name="f130301.y229" size="4"/></td>
<td align="center"><s:textfield name="f130301.y230" size="4"/></td>
<td align="center"><s:textfield name="f130301.y231" size="4"/></td>
<td align="center"><s:textfield name="f130301.y232" size="4"/></td>
<td align="center"><s:textfield name="f130301.y233" size="4"/></td>
<td align="center"><s:textfield name="f130301.y234" size="4"/></td>
<td align="center"><s:textfield name="f130301.y235" size="4"/></td>
<td align="center"><s:textfield name="f130301.y236" size="4"/></td>
<td align="center"><s:textfield name="f130301.y237" size="4"/></td>
<td align="center"><s:textfield name="f130301.y238" size="4"/></td>
<td align="center"><s:textfield name="f130301.y239" size="4"/></td>
<td align="center"><s:textfield name="f130301.y240" size="4"/></td>
<td align="center"><s:textfield name="f130301.y241" size="4"/></td>
<td align="center"><s:textfield name="f130301.y242" size="4"/></td>
<td align="center"><s:textfield name="f130301.y243" size="4"/></td>
<td align="center"><s:textfield name="f130301.y244" size="4"/></td>
<td align="center"><s:textfield name="f130301.y245" size="4"/></td>
<td align="center"><s:textfield name="f130301.y246" size="4"/></td>
<td align="center"><s:textfield name="f130301.y247" size="4"/></td>
<td align="center"><s:textfield name="f130301.y248" size="4"/></td>
            </tr>
            <tr>
                <td>size60</td>
                <td align="center"><s:textfield name="f130301.y301" size="4"/></td>
<td align="center"><s:textfield name="f130301.y302" size="4"/></td>
<td align="center"><s:textfield name="f130301.y303" size="4"/></td>
<td align="center"><s:textfield name="f130301.y304" size="4"/></td>
<td align="center"><s:textfield name="f130301.y305" size="4"/></td>
<td align="center"><s:textfield name="f130301.y306" size="4"/></td>
<td align="center"><s:textfield name="f130301.y307" size="4"/></td>
<td align="center"><s:textfield name="f130301.y308" size="4"/></td>
<td align="center"><s:textfield name="f130301.y309" size="4"/></td>
<td align="center"><s:textfield name="f130301.y310" size="4"/></td>
<td align="center"><s:textfield name="f130301.y311" size="4"/></td>
<td align="center"><s:textfield name="f130301.y312" size="4"/></td>
<td align="center"><s:textfield name="f130301.y313" size="4"/></td>
<td align="center"><s:textfield name="f130301.y314" size="4"/></td>
<td align="center"><s:textfield name="f130301.y315" size="4"/></td>
<td align="center"><s:textfield name="f130301.y316" size="4"/></td>
<td align="center"><s:textfield name="f130301.y317" size="4"/></td>
<td align="center"><s:textfield name="f130301.y318" size="4"/></td>
<td align="center"><s:textfield name="f130301.y319" size="4"/></td>
<td align="center"><s:textfield name="f130301.y320" size="4"/></td>
<td align="center"><s:textfield name="f130301.y321" size="4"/></td>
<td align="center"><s:textfield name="f130301.y322" size="4"/></td>
<td align="center"><s:textfield name="f130301.y323" size="4"/></td>
<td align="center"><s:textfield name="f130301.y324" size="4"/></td>
<td align="center"><s:textfield name="f130301.y325" size="4"/></td>
<td align="center"><s:textfield name="f130301.y326" size="4"/></td>
<td align="center"><s:textfield name="f130301.y327" size="4"/></td>
<td align="center"><s:textfield name="f130301.y328" size="4"/></td>
<td align="center"><s:textfield name="f130301.y329" size="4"/></td>
<td align="center"><s:textfield name="f130301.y330" size="4"/></td>
<td align="center"><s:textfield name="f130301.y331" size="4"/></td>
<td align="center"><s:textfield name="f130301.y332" size="4"/></td>
<td align="center"><s:textfield name="f130301.y333" size="4"/></td>
<td align="center"><s:textfield name="f130301.y334" size="4"/></td>
<td align="center"><s:textfield name="f130301.y335" size="4"/></td>
<td align="center"><s:textfield name="f130301.y336" size="4"/></td>
<td align="center"><s:textfield name="f130301.y337" size="4"/></td>
<td align="center"><s:textfield name="f130301.y338" size="4"/></td>
<td align="center"><s:textfield name="f130301.y339" size="4"/></td>
<td align="center"><s:textfield name="f130301.y340" size="4"/></td>
<td align="center"><s:textfield name="f130301.y341" size="4"/></td>
<td align="center"><s:textfield name="f130301.y342" size="4"/></td>
<td align="center"><s:textfield name="f130301.y343" size="4"/></td>
<td align="center"><s:textfield name="f130301.y344" size="4"/></td>
<td align="center"><s:textfield name="f130301.y345" size="4"/></td>
<td align="center"><s:textfield name="f130301.y346" size="4"/></td>
<td align="center"><s:textfield name="f130301.y347" size="4"/></td>
<td align="center"><s:textfield name="f130301.y348" size="4"/></td>
            </tr>
            <tr>
                <td>size80</td>
                <td align="center"><s:textfield name="f130301.y401" size="4"/></td>
<td align="center"><s:textfield name="f130301.y402" size="4"/></td>
<td align="center"><s:textfield name="f130301.y403" size="4"/></td>
<td align="center"><s:textfield name="f130301.y404" size="4"/></td>
<td align="center"><s:textfield name="f130301.y405" size="4"/></td>
<td align="center"><s:textfield name="f130301.y406" size="4"/></td>
<td align="center"><s:textfield name="f130301.y407" size="4"/></td>
<td align="center"><s:textfield name="f130301.y408" size="4"/></td>
<td align="center"><s:textfield name="f130301.y409" size="4"/></td>
<td align="center"><s:textfield name="f130301.y410" size="4"/></td>
<td align="center"><s:textfield name="f130301.y411" size="4"/></td>
<td align="center"><s:textfield name="f130301.y412" size="4"/></td>
<td align="center"><s:textfield name="f130301.y413" size="4"/></td>
<td align="center"><s:textfield name="f130301.y414" size="4"/></td>
<td align="center"><s:textfield name="f130301.y415" size="4"/></td>
<td align="center"><s:textfield name="f130301.y416" size="4"/></td>
<td align="center"><s:textfield name="f130301.y417" size="4"/></td>
<td align="center"><s:textfield name="f130301.y418" size="4"/></td>
<td align="center"><s:textfield name="f130301.y419" size="4"/></td>
<td align="center"><s:textfield name="f130301.y420" size="4"/></td>
<td align="center"><s:textfield name="f130301.y421" size="4"/></td>
<td align="center"><s:textfield name="f130301.y422" size="4"/></td>
<td align="center"><s:textfield name="f130301.y423" size="4"/></td>
<td align="center"><s:textfield name="f130301.y424" size="4"/></td>
<td align="center"><s:textfield name="f130301.y425" size="4"/></td>
<td align="center"><s:textfield name="f130301.y426" size="4"/></td>
<td align="center"><s:textfield name="f130301.y427" size="4"/></td>
<td align="center"><s:textfield name="f130301.y428" size="4"/></td>
<td align="center"><s:textfield name="f130301.y429" size="4"/></td>
<td align="center"><s:textfield name="f130301.y430" size="4"/></td>
<td align="center"><s:textfield name="f130301.y431" size="4"/></td>
<td align="center"><s:textfield name="f130301.y432" size="4"/></td>
<td align="center"><s:textfield name="f130301.y433" size="4"/></td>
<td align="center"><s:textfield name="f130301.y434" size="4"/></td>
<td align="center"><s:textfield name="f130301.y435" size="4"/></td>
<td align="center"><s:textfield name="f130301.y436" size="4"/></td>
<td align="center"><s:textfield name="f130301.y437" size="4"/></td>
<td align="center"><s:textfield name="f130301.y438" size="4"/></td>
<td align="center"><s:textfield name="f130301.y439" size="4"/></td>
<td align="center"><s:textfield name="f130301.y440" size="4"/></td>
<td align="center"><s:textfield name="f130301.y441" size="4"/></td>
<td align="center"><s:textfield name="f130301.y442" size="4"/></td>
<td align="center"><s:textfield name="f130301.y443" size="4"/></td>
<td align="center"><s:textfield name="f130301.y444" size="4"/></td>
<td align="center"><s:textfield name="f130301.y445" size="4"/></td>
<td align="center"><s:textfield name="f130301.y446" size="4"/></td>
<td align="center"><s:textfield name="f130301.y447" size="4"/></td>
<td align="center"><s:textfield name="f130301.y448" size="4"/></td>
            </tr>
			<tr>
                <td>size100</td>
                <td align="center"><s:textfield name="f130301.y501" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y502" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y503" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y504" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y505" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y506" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y507" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y508" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y509" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y510" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y511" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y512" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y513" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y514" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y515" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y516" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y517" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y518" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y519" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y520" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y521" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y522" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y523" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y524" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y525" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y526" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y527" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y528" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y529" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y530" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y531" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y532" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y533" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y534" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y535" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y536" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y537" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y538" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y539" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y540" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y541" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y542" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y543" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y544" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y545" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y546" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y547" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y548" size="4"/></td>
				</tr>
				<tr>
                <td>size120</td>
                <td align="center"><s:textfield name="f130301.y601" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y602" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y603" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y604" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y605" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y606" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y607" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y608" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y609" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y610" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y611" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y612" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y613" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y614" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y615" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y616" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y617" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y618" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y619" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y620" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y621" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y622" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y623" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y624" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y625" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y626" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y627" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y628" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y629" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y630" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y631" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y632" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y633" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y634" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y635" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y636" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y637" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y638" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y639" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y640" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y641" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y642" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y643" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y644" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y645" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y646" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y647" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y648" size="4"/></td>
				</tr>
				<tr>
                <td>size140</td>
                <td align="center"><s:textfield name="f130301.y701" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y702" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y703" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y704" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y705" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y706" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y707" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y708" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y709" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y710" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y711" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y712" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y713" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y714" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y715" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y716" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y717" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y718" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y719" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y720" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y721" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y722" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y723" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y724" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y725" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y726" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y727" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y728" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y729" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y730" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y731" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y732" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y733" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y734" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y735" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y736" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y737" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y738" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y739" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y740" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y741" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y742" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y743" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y744" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y745" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y746" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y747" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y748" size="4"/></td>
				</tr>
				<tr>
                <td>size160</td>
                <td align="center"><s:textfield name="f130301.y801" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y802" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y803" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y804" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y805" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y806" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y807" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y808" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y809" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y810" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y811" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y812" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y813" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y814" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y815" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y816" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y817" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y818" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y819" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y820" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y821" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y822" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y823" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y824" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y825" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y826" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y827" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y828" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y829" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y830" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y831" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y832" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y833" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y834" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y835" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y836" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y837" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y838" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y839" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y840" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y841" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y842" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y843" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y844" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y845" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y846" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y847" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y848" size="4"/></td>
				</tr>
				<tr>
                <td>size180</td>
                <td align="center"><s:textfield name="f130301.y901" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y902" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y903" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y904" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y905" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y906" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y907" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y908" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y909" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y910" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y911" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y912" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y913" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y914" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y915" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y916" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y917" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y918" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y919" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y920" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y921" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y922" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y923" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y924" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y925" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y926" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y927" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y928" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y929" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y930" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y931" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y932" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y933" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y934" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y935" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y936" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y937" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y938" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y939" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y940" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y941" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y942" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y943" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y944" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y945" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y946" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y947" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y948" size="4"/></td>
				</tr>
				<tr>
                <td>size200</td>
                <td align="center"><s:textfield name="f130301.y1001" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1002" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1003" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1004" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1005" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1006" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1007" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1008" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1009" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1010" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1011" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1012" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1013" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1014" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1015" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1016" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1017" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1018" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1019" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1020" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1021" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1022" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1023" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1024" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1025" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1026" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1027" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1028" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1029" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1030" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1031" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1032" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1033" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1034" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1035" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1036" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1037" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1038" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1039" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1040" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1041" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1042" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1043" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1044" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1045" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1046" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1047" size="4"/></td>
                <td align="center"><s:textfield name="f130301.y1048" size="4"/></td>
				</tr>
        </table>
		</div>
		<br/>
		<div id="div2">
        佐川急便
        <table cellspacing="0" cellpadding="0" border="1">
            <tr>
                <td width="55px">&nbsp;</td>
                <td width="70px" align="center">北海道</td>
                <td width="70px" align="center">青森県</td>
                <td width="70px" align="center">岩手県</td>
                <td width="70px" align="center">宮城県</td>
                <td width="70px" align="center">秋田県</td>
                <td width="70px" align="center">山形県</td>
                <td width="70px" align="center">福島県</td>
                <td width="70px" align="center">茨城県</td>
                <td width="70px" align="center">栃木県</td>
                <td width="70px" align="center">群馬県</td>
                <td width="70px" align="center">埼玉県</td>
                <td width="70px" align="center">千葉県</td>
                <td width="70px" align="center">東京都</td>
                <td width="70px" align="center">神奈川</td>
                <td width="70px" align="center">新潟県</td>
                <td width="70px" align="center">富山県</td>
                <td width="70px" align="center">石川県</td>
                <td width="70px" align="center">福井県</td>
                <td width="70px" align="center">山梨県</td>
                <td width="70px" align="center">長野県</td>
                <td width="70px" align="center">岐阜県</td>
                <td width="70px" align="center">静岡県</td>
                <td width="70px" align="center">愛知県</td>
                <td width="70px" align="center">三重県</td>
                <td width="70px" align="center">滋賀県</td>
                <td width="70px" align="center">京都府</td>
                <td width="70px" align="center">大阪府</td>
                <td width="70px" align="center">兵庫県</td>
                <td width="70px" align="center">奈良県</td>
                <td width="70px" align="center">和歌山</td>
                <td width="70px" align="center">鳥取県</td>
                <td width="70px" align="center">島根県</td>
                <td width="70px" align="center">岡山県</td>
                <td width="70px" align="center">広島県</td>
                <td width="70px" align="center">山口県</td>
                <td width="70px" align="center">徳島県</td>
                <td width="70px" align="center">香川県</td>
                <td width="70px" align="center">愛媛県</td>
                <td width="70px" align="center">高知県</td>
                <td width="70px" align="center">福岡県</td>
                <td width="70px" align="center">佐賀県</td>
                <td width="70px" align="center">長崎県</td>
                <td width="70px" align="center">熊本県</td>
                <td width="70px" align="center">大分県</td>
                <td width="70px" align="center">宮崎県</td>
                <td width="70px" align="center">鹿児島</td>
                <td width="70px" align="center">沖縄県</td>
                <td width="70px" align="center">その他</td>
            </tr>
            <tr>
                <td>size20</td>
                <td align="center"><s:textfield name="f130301.s101" size="4"/></td>
<td align="center"><s:textfield name="f130301.s102" size="4"/></td>
<td align="center"><s:textfield name="f130301.s103" size="4"/></td>
<td align="center"><s:textfield name="f130301.s104" size="4"/></td>
<td align="center"><s:textfield name="f130301.s105" size="4"/></td>
<td align="center"><s:textfield name="f130301.s106" size="4"/></td>
<td align="center"><s:textfield name="f130301.s107" size="4"/></td>
<td align="center"><s:textfield name="f130301.s108" size="4"/></td>
<td align="center"><s:textfield name="f130301.s109" size="4"/></td>
<td align="center"><s:textfield name="f130301.s110" size="4"/></td>
<td align="center"><s:textfield name="f130301.s111" size="4"/></td>
<td align="center"><s:textfield name="f130301.s112" size="4"/></td>
<td align="center"><s:textfield name="f130301.s113" size="4"/></td>
<td align="center"><s:textfield name="f130301.s114" size="4"/></td>
<td align="center"><s:textfield name="f130301.s115" size="4"/></td>
<td align="center"><s:textfield name="f130301.s116" size="4"/></td>
<td align="center"><s:textfield name="f130301.s117" size="4"/></td>
<td align="center"><s:textfield name="f130301.s118" size="4"/></td>
<td align="center"><s:textfield name="f130301.s119" size="4"/></td>
<td align="center"><s:textfield name="f130301.s120" size="4"/></td>
<td align="center"><s:textfield name="f130301.s121" size="4"/></td>
<td align="center"><s:textfield name="f130301.s122" size="4"/></td>
<td align="center"><s:textfield name="f130301.s123" size="4"/></td>
<td align="center"><s:textfield name="f130301.s124" size="4"/></td>
<td align="center"><s:textfield name="f130301.s125" size="4"/></td>
<td align="center"><s:textfield name="f130301.s126" size="4"/></td>
<td align="center"><s:textfield name="f130301.s127" size="4"/></td>
<td align="center"><s:textfield name="f130301.s128" size="4"/></td>
<td align="center"><s:textfield name="f130301.s129" size="4"/></td>
<td align="center"><s:textfield name="f130301.s130" size="4"/></td>
<td align="center"><s:textfield name="f130301.s131" size="4"/></td>
<td align="center"><s:textfield name="f130301.s132" size="4"/></td>
<td align="center"><s:textfield name="f130301.s133" size="4"/></td>
<td align="center"><s:textfield name="f130301.s134" size="4"/></td>
<td align="center"><s:textfield name="f130301.s135" size="4"/></td>
<td align="center"><s:textfield name="f130301.s136" size="4"/></td>
<td align="center"><s:textfield name="f130301.s137" size="4"/></td>
<td align="center"><s:textfield name="f130301.s138" size="4"/></td>
<td align="center"><s:textfield name="f130301.s139" size="4"/></td>
<td align="center"><s:textfield name="f130301.s140" size="4"/></td>
<td align="center"><s:textfield name="f130301.s141" size="4"/></td>
<td align="center"><s:textfield name="f130301.s142" size="4"/></td>
<td align="center"><s:textfield name="f130301.s143" size="4"/></td>
<td align="center"><s:textfield name="f130301.s144" size="4"/></td>
<td align="center"><s:textfield name="f130301.s145" size="4"/></td>
<td align="center"><s:textfield name="f130301.s146" size="4"/></td>
<td align="center"><s:textfield name="f130301.s147" size="4"/></td>
<td align="center"><s:textfield name="f130301.s148" size="4"/></td>
            </tr>
            <tr>
                <td>size40</td>
                <td align="center"><s:textfield name="f130301.s201" size="4"/></td>
<td align="center"><s:textfield name="f130301.s202" size="4"/></td>
<td align="center"><s:textfield name="f130301.s203" size="4"/></td>
<td align="center"><s:textfield name="f130301.s204" size="4"/></td>
<td align="center"><s:textfield name="f130301.s205" size="4"/></td>
<td align="center"><s:textfield name="f130301.s206" size="4"/></td>
<td align="center"><s:textfield name="f130301.s207" size="4"/></td>
<td align="center"><s:textfield name="f130301.s208" size="4"/></td>
<td align="center"><s:textfield name="f130301.s209" size="4"/></td>
<td align="center"><s:textfield name="f130301.s210" size="4"/></td>
<td align="center"><s:textfield name="f130301.s211" size="4"/></td>
<td align="center"><s:textfield name="f130301.s212" size="4"/></td>
<td align="center"><s:textfield name="f130301.s213" size="4"/></td>
<td align="center"><s:textfield name="f130301.s214" size="4"/></td>
<td align="center"><s:textfield name="f130301.s215" size="4"/></td>
<td align="center"><s:textfield name="f130301.s216" size="4"/></td>
<td align="center"><s:textfield name="f130301.s217" size="4"/></td>
<td align="center"><s:textfield name="f130301.s218" size="4"/></td>
<td align="center"><s:textfield name="f130301.s219" size="4"/></td>
<td align="center"><s:textfield name="f130301.s220" size="4"/></td>
<td align="center"><s:textfield name="f130301.s221" size="4"/></td>
<td align="center"><s:textfield name="f130301.s222" size="4"/></td>
<td align="center"><s:textfield name="f130301.s223" size="4"/></td>
<td align="center"><s:textfield name="f130301.s224" size="4"/></td>
<td align="center"><s:textfield name="f130301.s225" size="4"/></td>
<td align="center"><s:textfield name="f130301.s226" size="4"/></td>
<td align="center"><s:textfield name="f130301.s227" size="4"/></td>
<td align="center"><s:textfield name="f130301.s228" size="4"/></td>
<td align="center"><s:textfield name="f130301.s229" size="4"/></td>
<td align="center"><s:textfield name="f130301.s230" size="4"/></td>
<td align="center"><s:textfield name="f130301.s231" size="4"/></td>
<td align="center"><s:textfield name="f130301.s232" size="4"/></td>
<td align="center"><s:textfield name="f130301.s233" size="4"/></td>
<td align="center"><s:textfield name="f130301.s234" size="4"/></td>
<td align="center"><s:textfield name="f130301.s235" size="4"/></td>
<td align="center"><s:textfield name="f130301.s236" size="4"/></td>
<td align="center"><s:textfield name="f130301.s237" size="4"/></td>
<td align="center"><s:textfield name="f130301.s238" size="4"/></td>
<td align="center"><s:textfield name="f130301.s239" size="4"/></td>
<td align="center"><s:textfield name="f130301.s240" size="4"/></td>
<td align="center"><s:textfield name="f130301.s241" size="4"/></td>
<td align="center"><s:textfield name="f130301.s242" size="4"/></td>
<td align="center"><s:textfield name="f130301.s243" size="4"/></td>
<td align="center"><s:textfield name="f130301.s244" size="4"/></td>
<td align="center"><s:textfield name="f130301.s245" size="4"/></td>
<td align="center"><s:textfield name="f130301.s246" size="4"/></td>
<td align="center"><s:textfield name="f130301.s247" size="4"/></td>
<td align="center"><s:textfield name="f130301.s248" size="4"/></td>
            </tr>
            <tr>
                <td>size60</td>
                <td align="center"><s:textfield name="f130301.s301" size="4"/></td>
<td align="center"><s:textfield name="f130301.s302" size="4"/></td>
<td align="center"><s:textfield name="f130301.s303" size="4"/></td>
<td align="center"><s:textfield name="f130301.s304" size="4"/></td>
<td align="center"><s:textfield name="f130301.s305" size="4"/></td>
<td align="center"><s:textfield name="f130301.s306" size="4"/></td>
<td align="center"><s:textfield name="f130301.s307" size="4"/></td>
<td align="center"><s:textfield name="f130301.s308" size="4"/></td>
<td align="center"><s:textfield name="f130301.s309" size="4"/></td>
<td align="center"><s:textfield name="f130301.s310" size="4"/></td>
<td align="center"><s:textfield name="f130301.s311" size="4"/></td>
<td align="center"><s:textfield name="f130301.s312" size="4"/></td>
<td align="center"><s:textfield name="f130301.s313" size="4"/></td>
<td align="center"><s:textfield name="f130301.s314" size="4"/></td>
<td align="center"><s:textfield name="f130301.s315" size="4"/></td>
<td align="center"><s:textfield name="f130301.s316" size="4"/></td>
<td align="center"><s:textfield name="f130301.s317" size="4"/></td>
<td align="center"><s:textfield name="f130301.s318" size="4"/></td>
<td align="center"><s:textfield name="f130301.s319" size="4"/></td>
<td align="center"><s:textfield name="f130301.s320" size="4"/></td>
<td align="center"><s:textfield name="f130301.s321" size="4"/></td>
<td align="center"><s:textfield name="f130301.s322" size="4"/></td>
<td align="center"><s:textfield name="f130301.s323" size="4"/></td>
<td align="center"><s:textfield name="f130301.s324" size="4"/></td>
<td align="center"><s:textfield name="f130301.s325" size="4"/></td>
<td align="center"><s:textfield name="f130301.s326" size="4"/></td>
<td align="center"><s:textfield name="f130301.s327" size="4"/></td>
<td align="center"><s:textfield name="f130301.s328" size="4"/></td>
<td align="center"><s:textfield name="f130301.s329" size="4"/></td>
<td align="center"><s:textfield name="f130301.s330" size="4"/></td>
<td align="center"><s:textfield name="f130301.s331" size="4"/></td>
<td align="center"><s:textfield name="f130301.s332" size="4"/></td>
<td align="center"><s:textfield name="f130301.s333" size="4"/></td>
<td align="center"><s:textfield name="f130301.s334" size="4"/></td>
<td align="center"><s:textfield name="f130301.s335" size="4"/></td>
<td align="center"><s:textfield name="f130301.s336" size="4"/></td>
<td align="center"><s:textfield name="f130301.s337" size="4"/></td>
<td align="center"><s:textfield name="f130301.s338" size="4"/></td>
<td align="center"><s:textfield name="f130301.s339" size="4"/></td>
<td align="center"><s:textfield name="f130301.s340" size="4"/></td>
<td align="center"><s:textfield name="f130301.s341" size="4"/></td>
<td align="center"><s:textfield name="f130301.s342" size="4"/></td>
<td align="center"><s:textfield name="f130301.s343" size="4"/></td>
<td align="center"><s:textfield name="f130301.s344" size="4"/></td>
<td align="center"><s:textfield name="f130301.s345" size="4"/></td>
<td align="center"><s:textfield name="f130301.s346" size="4"/></td>
<td align="center"><s:textfield name="f130301.s347" size="4"/></td>
<td align="center"><s:textfield name="f130301.s348" size="4"/></td>
            </tr>
            <tr>
                <td>size80</td>
                <td align="center"><s:textfield name="f130301.s401" size="4"/></td>
<td align="center"><s:textfield name="f130301.s402" size="4"/></td>
<td align="center"><s:textfield name="f130301.s403" size="4"/></td>
<td align="center"><s:textfield name="f130301.s404" size="4"/></td>
<td align="center"><s:textfield name="f130301.s405" size="4"/></td>
<td align="center"><s:textfield name="f130301.s406" size="4"/></td>
<td align="center"><s:textfield name="f130301.s407" size="4"/></td>
<td align="center"><s:textfield name="f130301.s408" size="4"/></td>
<td align="center"><s:textfield name="f130301.s409" size="4"/></td>
<td align="center"><s:textfield name="f130301.s410" size="4"/></td>
<td align="center"><s:textfield name="f130301.s411" size="4"/></td>
<td align="center"><s:textfield name="f130301.s412" size="4"/></td>
<td align="center"><s:textfield name="f130301.s413" size="4"/></td>
<td align="center"><s:textfield name="f130301.s414" size="4"/></td>
<td align="center"><s:textfield name="f130301.s415" size="4"/></td>
<td align="center"><s:textfield name="f130301.s416" size="4"/></td>
<td align="center"><s:textfield name="f130301.s417" size="4"/></td>
<td align="center"><s:textfield name="f130301.s418" size="4"/></td>
<td align="center"><s:textfield name="f130301.s419" size="4"/></td>
<td align="center"><s:textfield name="f130301.s420" size="4"/></td>
<td align="center"><s:textfield name="f130301.s421" size="4"/></td>
<td align="center"><s:textfield name="f130301.s422" size="4"/></td>
<td align="center"><s:textfield name="f130301.s423" size="4"/></td>
<td align="center"><s:textfield name="f130301.s424" size="4"/></td>
<td align="center"><s:textfield name="f130301.s425" size="4"/></td>
<td align="center"><s:textfield name="f130301.s426" size="4"/></td>
<td align="center"><s:textfield name="f130301.s427" size="4"/></td>
<td align="center"><s:textfield name="f130301.s428" size="4"/></td>
<td align="center"><s:textfield name="f130301.s429" size="4"/></td>
<td align="center"><s:textfield name="f130301.s430" size="4"/></td>
<td align="center"><s:textfield name="f130301.s431" size="4"/></td>
<td align="center"><s:textfield name="f130301.s432" size="4"/></td>
<td align="center"><s:textfield name="f130301.s433" size="4"/></td>
<td align="center"><s:textfield name="f130301.s434" size="4"/></td>
<td align="center"><s:textfield name="f130301.s435" size="4"/></td>
<td align="center"><s:textfield name="f130301.s436" size="4"/></td>
<td align="center"><s:textfield name="f130301.s437" size="4"/></td>
<td align="center"><s:textfield name="f130301.s438" size="4"/></td>
<td align="center"><s:textfield name="f130301.s439" size="4"/></td>
<td align="center"><s:textfield name="f130301.s440" size="4"/></td>
<td align="center"><s:textfield name="f130301.s441" size="4"/></td>
<td align="center"><s:textfield name="f130301.s442" size="4"/></td>
<td align="center"><s:textfield name="f130301.s443" size="4"/></td>
<td align="center"><s:textfield name="f130301.s444" size="4"/></td>
<td align="center"><s:textfield name="f130301.s445" size="4"/></td>
<td align="center"><s:textfield name="f130301.s446" size="4"/></td>
<td align="center"><s:textfield name="f130301.s447" size="4"/></td>
<td align="center"><s:textfield name="f130301.s448" size="4"/></td>
            </tr>
			<tr>
                <td>size100</td>
                <td align="center"><s:textfield name="f130301.s501" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s502" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s503" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s504" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s505" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s506" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s507" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s508" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s509" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s510" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s511" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s512" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s513" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s514" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s515" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s516" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s517" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s518" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s519" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s520" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s521" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s522" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s523" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s524" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s525" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s526" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s527" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s528" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s529" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s530" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s531" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s532" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s533" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s534" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s535" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s536" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s537" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s538" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s539" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s540" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s541" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s542" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s543" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s544" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s545" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s546" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s547" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s548" size="4"/></td>
				</tr>
				<tr>
                <td>size120</td>
                <td align="center"><s:textfield name="f130301.s601" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s602" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s603" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s604" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s605" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s606" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s607" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s608" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s609" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s610" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s611" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s612" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s613" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s614" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s615" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s616" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s617" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s618" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s619" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s620" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s621" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s622" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s623" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s624" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s625" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s626" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s627" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s628" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s629" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s630" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s631" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s632" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s633" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s634" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s635" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s636" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s637" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s638" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s639" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s640" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s641" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s642" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s643" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s644" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s645" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s646" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s647" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s648" size="4"/></td>
				</tr>
				<tr>
                <td>size140</td>
                <td align="center"><s:textfield name="f130301.s701" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s702" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s703" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s704" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s705" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s706" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s707" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s708" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s709" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s710" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s711" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s712" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s713" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s714" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s715" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s716" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s717" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s718" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s719" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s720" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s721" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s722" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s723" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s724" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s725" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s726" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s727" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s728" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s729" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s730" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s731" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s732" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s733" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s734" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s735" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s736" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s737" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s738" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s739" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s740" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s741" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s742" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s743" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s744" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s745" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s746" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s747" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s748" size="4"/></td>
				</tr>
				<tr>
                <td>size160</td>
                <td align="center"><s:textfield name="f130301.s801" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s802" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s803" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s804" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s805" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s806" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s807" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s808" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s809" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s810" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s811" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s812" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s813" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s814" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s815" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s816" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s817" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s818" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s819" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s820" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s821" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s822" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s823" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s824" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s825" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s826" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s827" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s828" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s829" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s830" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s831" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s832" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s833" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s834" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s835" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s836" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s837" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s838" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s839" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s840" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s841" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s842" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s843" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s844" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s845" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s846" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s847" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s848" size="4"/></td>
				</tr>
				<tr>
                <td>size180</td>
                <td align="center"><s:textfield name="f130301.s901" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s902" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s903" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s904" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s905" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s906" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s907" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s908" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s909" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s910" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s911" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s912" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s913" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s914" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s915" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s916" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s917" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s918" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s919" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s920" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s921" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s922" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s923" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s924" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s925" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s926" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s927" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s928" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s929" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s930" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s931" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s932" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s933" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s934" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s935" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s936" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s937" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s938" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s939" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s940" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s941" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s942" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s943" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s944" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s945" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s946" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s947" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s948" size="4"/></td>
				</tr>
				<tr>
                <td>size200</td>
                <td align="center"><s:textfield name="f130301.s1001" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1002" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1003" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1004" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1005" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1006" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1007" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1008" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1009" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1010" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1011" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1012" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1013" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1014" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1015" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1016" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1017" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1018" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1019" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1020" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1021" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1022" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1023" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1024" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1025" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1026" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1027" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1028" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1029" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1030" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1031" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1032" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1033" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1034" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1035" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1036" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1037" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1038" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1039" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1040" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1041" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1042" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1043" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1044" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1045" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1046" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1047" size="4"/></td>
                <td align="center"><s:textfield name="f130301.s1048" size="4"/></td>
				</tr>
        </table>
        
		</div>
		<br/>
		<div id="div3">
        郵便局
        <table cellspacing="0" cellpadding="0" border="1">
            <tr>
                <td width="55px">&nbsp;</td>
                <td width="70px" align="center">北海道</td>
                <td width="70px" align="center">青森県</td>
                <td width="70px" align="center">岩手県</td>
                <td width="70px" align="center">宮城県</td>
                <td width="70px" align="center">秋田県</td>
                <td width="70px" align="center">山形県</td>
                <td width="70px" align="center">福島県</td>
                <td width="70px" align="center">茨城県</td>
                <td width="70px" align="center">栃木県</td>
                <td width="70px" align="center">群馬県</td>
                <td width="70px" align="center">埼玉県</td>
                <td width="70px" align="center">千葉県</td>
                <td width="70px" align="center">東京都</td>
                <td width="70px" align="center">神奈川</td>
                <td width="70px" align="center">新潟県</td>
                <td width="70px" align="center">富山県</td>
                <td width="70px" align="center">石川県</td>
                <td width="70px" align="center">福井県</td>
                <td width="70px" align="center">山梨県</td>
                <td width="70px" align="center">長野県</td>
                <td width="70px" align="center">岐阜県</td>
                <td width="70px" align="center">静岡県</td>
                <td width="70px" align="center">愛知県</td>
                <td width="70px" align="center">三重県</td>
                <td width="70px" align="center">滋賀県</td>
                <td width="70px" align="center">京都府</td>
                <td width="70px" align="center">大阪府</td>
                <td width="70px" align="center">兵庫県</td>
                <td width="70px" align="center">奈良県</td>
                <td width="70px" align="center">和歌山</td>
                <td width="70px" align="center">鳥取県</td>
                <td width="70px" align="center">島根県</td>
                <td width="70px" align="center">岡山県</td>
                <td width="70px" align="center">広島県</td>
                <td width="70px" align="center">山口県</td>
                <td width="70px" align="center">徳島県</td>
                <td width="70px" align="center">香川県</td>
                <td width="70px" align="center">愛媛県</td>
                <td width="70px" align="center">高知県</td>
                <td width="70px" align="center">福岡県</td>
                <td width="70px" align="center">佐賀県</td>
                <td width="70px" align="center">長崎県</td>
                <td width="70px" align="center">熊本県</td>
                <td width="70px" align="center">大分県</td>
                <td width="70px" align="center">宮崎県</td>
                <td width="70px" align="center">鹿児島</td>
                <td width="70px" align="center">沖縄県</td>
                <td width="70px" align="center">その他</td>
            </tr>
            <tr>
                <td>size20</td>
                <td align="center"><s:textfield name="f130301.p101" size="4"/></td>
<td align="center"><s:textfield name="f130301.p102" size="4"/></td>
<td align="center"><s:textfield name="f130301.p103" size="4"/></td>
<td align="center"><s:textfield name="f130301.p104" size="4"/></td>
<td align="center"><s:textfield name="f130301.p105" size="4"/></td>
<td align="center"><s:textfield name="f130301.p106" size="4"/></td>
<td align="center"><s:textfield name="f130301.p107" size="4"/></td>
<td align="center"><s:textfield name="f130301.p108" size="4"/></td>
<td align="center"><s:textfield name="f130301.p109" size="4"/></td>
<td align="center"><s:textfield name="f130301.p110" size="4"/></td>
<td align="center"><s:textfield name="f130301.p111" size="4"/></td>
<td align="center"><s:textfield name="f130301.p112" size="4"/></td>
<td align="center"><s:textfield name="f130301.p113" size="4"/></td>
<td align="center"><s:textfield name="f130301.p114" size="4"/></td>
<td align="center"><s:textfield name="f130301.p115" size="4"/></td>
<td align="center"><s:textfield name="f130301.p116" size="4"/></td>
<td align="center"><s:textfield name="f130301.p117" size="4"/></td>
<td align="center"><s:textfield name="f130301.p118" size="4"/></td>
<td align="center"><s:textfield name="f130301.p119" size="4"/></td>
<td align="center"><s:textfield name="f130301.p120" size="4"/></td>
<td align="center"><s:textfield name="f130301.p121" size="4"/></td>
<td align="center"><s:textfield name="f130301.p122" size="4"/></td>
<td align="center"><s:textfield name="f130301.p123" size="4"/></td>
<td align="center"><s:textfield name="f130301.p124" size="4"/></td>
<td align="center"><s:textfield name="f130301.p125" size="4"/></td>
<td align="center"><s:textfield name="f130301.p126" size="4"/></td>
<td align="center"><s:textfield name="f130301.p127" size="4"/></td>
<td align="center"><s:textfield name="f130301.p128" size="4"/></td>
<td align="center"><s:textfield name="f130301.p129" size="4"/></td>
<td align="center"><s:textfield name="f130301.p130" size="4"/></td>
<td align="center"><s:textfield name="f130301.p131" size="4"/></td>
<td align="center"><s:textfield name="f130301.p132" size="4"/></td>
<td align="center"><s:textfield name="f130301.p133" size="4"/></td>
<td align="center"><s:textfield name="f130301.p134" size="4"/></td>
<td align="center"><s:textfield name="f130301.p135" size="4"/></td>
<td align="center"><s:textfield name="f130301.p136" size="4"/></td>
<td align="center"><s:textfield name="f130301.p137" size="4"/></td>
<td align="center"><s:textfield name="f130301.p138" size="4"/></td>
<td align="center"><s:textfield name="f130301.p139" size="4"/></td>
<td align="center"><s:textfield name="f130301.p140" size="4"/></td>
<td align="center"><s:textfield name="f130301.p141" size="4"/></td>
<td align="center"><s:textfield name="f130301.p142" size="4"/></td>
<td align="center"><s:textfield name="f130301.p143" size="4"/></td>
<td align="center"><s:textfield name="f130301.p144" size="4"/></td>
<td align="center"><s:textfield name="f130301.p145" size="4"/></td>
<td align="center"><s:textfield name="f130301.p146" size="4"/></td>
<td align="center"><s:textfield name="f130301.p147" size="4"/></td>
<td align="center"><s:textfield name="f130301.p148" size="4"/></td>
            </tr>
            <tr>
                <td>size40</td>
                <td align="center"><s:textfield name="f130301.p201" size="4"/></td>
<td align="center"><s:textfield name="f130301.p202" size="4"/></td>
<td align="center"><s:textfield name="f130301.p203" size="4"/></td>
<td align="center"><s:textfield name="f130301.p204" size="4"/></td>
<td align="center"><s:textfield name="f130301.p205" size="4"/></td>
<td align="center"><s:textfield name="f130301.p206" size="4"/></td>
<td align="center"><s:textfield name="f130301.p207" size="4"/></td>
<td align="center"><s:textfield name="f130301.p208" size="4"/></td>
<td align="center"><s:textfield name="f130301.p209" size="4"/></td>
<td align="center"><s:textfield name="f130301.p210" size="4"/></td>
<td align="center"><s:textfield name="f130301.p211" size="4"/></td>
<td align="center"><s:textfield name="f130301.p212" size="4"/></td>
<td align="center"><s:textfield name="f130301.p213" size="4"/></td>
<td align="center"><s:textfield name="f130301.p214" size="4"/></td>
<td align="center"><s:textfield name="f130301.p215" size="4"/></td>
<td align="center"><s:textfield name="f130301.p216" size="4"/></td>
<td align="center"><s:textfield name="f130301.p217" size="4"/></td>
<td align="center"><s:textfield name="f130301.p218" size="4"/></td>
<td align="center"><s:textfield name="f130301.p219" size="4"/></td>
<td align="center"><s:textfield name="f130301.p220" size="4"/></td>
<td align="center"><s:textfield name="f130301.p221" size="4"/></td>
<td align="center"><s:textfield name="f130301.p222" size="4"/></td>
<td align="center"><s:textfield name="f130301.p223" size="4"/></td>
<td align="center"><s:textfield name="f130301.p224" size="4"/></td>
<td align="center"><s:textfield name="f130301.p225" size="4"/></td>
<td align="center"><s:textfield name="f130301.p226" size="4"/></td>
<td align="center"><s:textfield name="f130301.p227" size="4"/></td>
<td align="center"><s:textfield name="f130301.p228" size="4"/></td>
<td align="center"><s:textfield name="f130301.p229" size="4"/></td>
<td align="center"><s:textfield name="f130301.p230" size="4"/></td>
<td align="center"><s:textfield name="f130301.p231" size="4"/></td>
<td align="center"><s:textfield name="f130301.p232" size="4"/></td>
<td align="center"><s:textfield name="f130301.p233" size="4"/></td>
<td align="center"><s:textfield name="f130301.p234" size="4"/></td>
<td align="center"><s:textfield name="f130301.p235" size="4"/></td>
<td align="center"><s:textfield name="f130301.p236" size="4"/></td>
<td align="center"><s:textfield name="f130301.p237" size="4"/></td>
<td align="center"><s:textfield name="f130301.p238" size="4"/></td>
<td align="center"><s:textfield name="f130301.p239" size="4"/></td>
<td align="center"><s:textfield name="f130301.p240" size="4"/></td>
<td align="center"><s:textfield name="f130301.p241" size="4"/></td>
<td align="center"><s:textfield name="f130301.p242" size="4"/></td>
<td align="center"><s:textfield name="f130301.p243" size="4"/></td>
<td align="center"><s:textfield name="f130301.p244" size="4"/></td>
<td align="center"><s:textfield name="f130301.p245" size="4"/></td>
<td align="center"><s:textfield name="f130301.p246" size="4"/></td>
<td align="center"><s:textfield name="f130301.p247" size="4"/></td>
<td align="center"><s:textfield name="f130301.p248" size="4"/></td>
            </tr>
            <tr>
                <td>size60</td>
                <td align="center"><s:textfield name="f130301.p301" size="4"/></td>
<td align="center"><s:textfield name="f130301.p302" size="4"/></td>
<td align="center"><s:textfield name="f130301.p303" size="4"/></td>
<td align="center"><s:textfield name="f130301.p304" size="4"/></td>
<td align="center"><s:textfield name="f130301.p305" size="4"/></td>
<td align="center"><s:textfield name="f130301.p306" size="4"/></td>
<td align="center"><s:textfield name="f130301.p307" size="4"/></td>
<td align="center"><s:textfield name="f130301.p308" size="4"/></td>
<td align="center"><s:textfield name="f130301.p309" size="4"/></td>
<td align="center"><s:textfield name="f130301.p310" size="4"/></td>
<td align="center"><s:textfield name="f130301.p311" size="4"/></td>
<td align="center"><s:textfield name="f130301.p312" size="4"/></td>
<td align="center"><s:textfield name="f130301.p313" size="4"/></td>
<td align="center"><s:textfield name="f130301.p314" size="4"/></td>
<td align="center"><s:textfield name="f130301.p315" size="4"/></td>
<td align="center"><s:textfield name="f130301.p316" size="4"/></td>
<td align="center"><s:textfield name="f130301.p317" size="4"/></td>
<td align="center"><s:textfield name="f130301.p318" size="4"/></td>
<td align="center"><s:textfield name="f130301.p319" size="4"/></td>
<td align="center"><s:textfield name="f130301.p320" size="4"/></td>
<td align="center"><s:textfield name="f130301.p321" size="4"/></td>
<td align="center"><s:textfield name="f130301.p322" size="4"/></td>
<td align="center"><s:textfield name="f130301.p323" size="4"/></td>
<td align="center"><s:textfield name="f130301.p324" size="4"/></td>
<td align="center"><s:textfield name="f130301.p325" size="4"/></td>
<td align="center"><s:textfield name="f130301.p326" size="4"/></td>
<td align="center"><s:textfield name="f130301.p327" size="4"/></td>
<td align="center"><s:textfield name="f130301.p328" size="4"/></td>
<td align="center"><s:textfield name="f130301.p329" size="4"/></td>
<td align="center"><s:textfield name="f130301.p330" size="4"/></td>
<td align="center"><s:textfield name="f130301.p331" size="4"/></td>
<td align="center"><s:textfield name="f130301.p332" size="4"/></td>
<td align="center"><s:textfield name="f130301.p333" size="4"/></td>
<td align="center"><s:textfield name="f130301.p334" size="4"/></td>
<td align="center"><s:textfield name="f130301.p335" size="4"/></td>
<td align="center"><s:textfield name="f130301.p336" size="4"/></td>
<td align="center"><s:textfield name="f130301.p337" size="4"/></td>
<td align="center"><s:textfield name="f130301.p338" size="4"/></td>
<td align="center"><s:textfield name="f130301.p339" size="4"/></td>
<td align="center"><s:textfield name="f130301.p340" size="4"/></td>
<td align="center"><s:textfield name="f130301.p341" size="4"/></td>
<td align="center"><s:textfield name="f130301.p342" size="4"/></td>
<td align="center"><s:textfield name="f130301.p343" size="4"/></td>
<td align="center"><s:textfield name="f130301.p344" size="4"/></td>
<td align="center"><s:textfield name="f130301.p345" size="4"/></td>
<td align="center"><s:textfield name="f130301.p346" size="4"/></td>
<td align="center"><s:textfield name="f130301.p347" size="4"/></td>
<td align="center"><s:textfield name="f130301.p348" size="4"/></td>
            </tr>
            <tr>
                <td>size80</td>
                <td align="center"><s:textfield name="f130301.p401" size="4"/></td>
<td align="center"><s:textfield name="f130301.p402" size="4"/></td>
<td align="center"><s:textfield name="f130301.p403" size="4"/></td>
<td align="center"><s:textfield name="f130301.p404" size="4"/></td>
<td align="center"><s:textfield name="f130301.p405" size="4"/></td>
<td align="center"><s:textfield name="f130301.p406" size="4"/></td>
<td align="center"><s:textfield name="f130301.p407" size="4"/></td>
<td align="center"><s:textfield name="f130301.p408" size="4"/></td>
<td align="center"><s:textfield name="f130301.p409" size="4"/></td>
<td align="center"><s:textfield name="f130301.p410" size="4"/></td>
<td align="center"><s:textfield name="f130301.p411" size="4"/></td>
<td align="center"><s:textfield name="f130301.p412" size="4"/></td>
<td align="center"><s:textfield name="f130301.p413" size="4"/></td>
<td align="center"><s:textfield name="f130301.p414" size="4"/></td>
<td align="center"><s:textfield name="f130301.p415" size="4"/></td>
<td align="center"><s:textfield name="f130301.p416" size="4"/></td>
<td align="center"><s:textfield name="f130301.p417" size="4"/></td>
<td align="center"><s:textfield name="f130301.p418" size="4"/></td>
<td align="center"><s:textfield name="f130301.p419" size="4"/></td>
<td align="center"><s:textfield name="f130301.p420" size="4"/></td>
<td align="center"><s:textfield name="f130301.p421" size="4"/></td>
<td align="center"><s:textfield name="f130301.p422" size="4"/></td>
<td align="center"><s:textfield name="f130301.p423" size="4"/></td>
<td align="center"><s:textfield name="f130301.p424" size="4"/></td>
<td align="center"><s:textfield name="f130301.p425" size="4"/></td>
<td align="center"><s:textfield name="f130301.p426" size="4"/></td>
<td align="center"><s:textfield name="f130301.p427" size="4"/></td>
<td align="center"><s:textfield name="f130301.p428" size="4"/></td>
<td align="center"><s:textfield name="f130301.p429" size="4"/></td>
<td align="center"><s:textfield name="f130301.p430" size="4"/></td>
<td align="center"><s:textfield name="f130301.p431" size="4"/></td>
<td align="center"><s:textfield name="f130301.p432" size="4"/></td>
<td align="center"><s:textfield name="f130301.p433" size="4"/></td>
<td align="center"><s:textfield name="f130301.p434" size="4"/></td>
<td align="center"><s:textfield name="f130301.p435" size="4"/></td>
<td align="center"><s:textfield name="f130301.p436" size="4"/></td>
<td align="center"><s:textfield name="f130301.p437" size="4"/></td>
<td align="center"><s:textfield name="f130301.p438" size="4"/></td>
<td align="center"><s:textfield name="f130301.p439" size="4"/></td>
<td align="center"><s:textfield name="f130301.p440" size="4"/></td>
<td align="center"><s:textfield name="f130301.p441" size="4"/></td>
<td align="center"><s:textfield name="f130301.p442" size="4"/></td>
<td align="center"><s:textfield name="f130301.p443" size="4"/></td>
<td align="center"><s:textfield name="f130301.p444" size="4"/></td>
<td align="center"><s:textfield name="f130301.p445" size="4"/></td>
<td align="center"><s:textfield name="f130301.p446" size="4"/></td>
<td align="center"><s:textfield name="f130301.p447" size="4"/></td>
<td align="center"><s:textfield name="f130301.p448" size="4"/></td>
            </tr>
			<tr>
                <td>size100</td>
                <td align="center"><s:textfield name="f130301.p501" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p502" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p503" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p504" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p505" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p506" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p507" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p508" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p509" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p510" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p511" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p512" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p513" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p514" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p515" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p516" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p517" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p518" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p519" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p520" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p521" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p522" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p523" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p524" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p525" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p526" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p527" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p528" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p529" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p530" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p531" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p532" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p533" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p534" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p535" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p536" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p537" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p538" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p539" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p540" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p541" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p542" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p543" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p544" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p545" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p546" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p547" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p548" size="4"/></td>
				</tr>
				<tr>
                <td>size120</td>
                <td align="center"><s:textfield name="f130301.p601" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p602" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p603" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p604" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p605" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p606" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p607" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p608" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p609" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p610" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p611" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p612" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p613" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p614" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p615" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p616" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p617" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p618" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p619" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p620" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p621" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p622" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p623" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p624" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p625" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p626" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p627" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p628" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p629" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p630" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p631" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p632" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p633" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p634" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p635" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p636" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p637" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p638" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p639" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p640" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p641" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p642" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p643" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p644" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p645" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p646" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p647" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p648" size="4"/></td>
				</tr>
				<tr>
                <td>size140</td>
                <td align="center"><s:textfield name="f130301.p701" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p702" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p703" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p704" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p705" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p706" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p707" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p708" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p709" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p710" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p711" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p712" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p713" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p714" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p715" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p716" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p717" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p718" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p719" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p720" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p721" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p722" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p723" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p724" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p725" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p726" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p727" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p728" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p729" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p730" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p731" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p732" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p733" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p734" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p735" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p736" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p737" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p738" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p739" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p740" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p741" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p742" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p743" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p744" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p745" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p746" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p747" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p748" size="4"/></td>
				</tr>
				<tr>
                <td>size160</td>
                <td align="center"><s:textfield name="f130301.p801" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p802" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p803" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p804" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p805" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p806" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p807" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p808" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p809" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p810" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p811" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p812" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p813" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p814" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p815" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p816" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p817" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p818" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p819" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p820" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p821" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p822" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p823" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p824" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p825" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p826" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p827" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p828" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p829" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p830" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p831" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p832" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p833" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p834" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p835" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p836" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p837" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p838" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p839" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p840" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p841" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p842" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p843" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p844" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p845" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p846" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p847" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p848" size="4"/></td>
				</tr>
				<tr>
                <td>size180</td>
                <td align="center"><s:textfield name="f130301.p901" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p902" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p903" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p904" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p905" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p906" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p907" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p908" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p909" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p910" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p911" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p912" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p913" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p914" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p915" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p916" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p917" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p918" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p919" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p920" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p921" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p922" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p923" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p924" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p925" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p926" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p927" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p928" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p929" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p930" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p931" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p932" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p933" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p934" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p935" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p936" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p937" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p938" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p939" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p940" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p941" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p942" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p943" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p944" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p945" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p946" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p947" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p948" size="4"/></td>
				</tr>
				<tr>
                <td>size200</td>
                <td align="center"><s:textfield name="f130301.p1001" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1002" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1003" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1004" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1005" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1006" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1007" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1008" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1009" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1010" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1011" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1012" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1013" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1014" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1015" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1016" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1017" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1018" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1019" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1020" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1021" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1022" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1023" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1024" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1025" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1026" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1027" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1028" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1029" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1030" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1031" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1032" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1033" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1034" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1035" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1036" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1037" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1038" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1039" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1040" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1041" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1042" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1043" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1044" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1045" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1046" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1047" size="4"/></td>
                <td align="center"><s:textfield name="f130301.p1048" size="4"/></td>
				</tr>
        </table>
		</div>
		<br/>
		<br/>
		<table width="100px">
            <tr>
                <td><input type="button" value="保存" onclick="actionSubmit('A13030102')"></td>
            </tr>
        </table>
	</s:form>
</body>

</html>