<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link href="Images/css1/left_css.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
}
}
</script>
<body bgcolor="16ACFF">
<table width="98%" border="0" cellpadding="0" cellspacing="0" background="Images/tablemde.jpg">
	<tr>
        <td height="5" background="Images/tableline_top.jpg" bgcolor="#9BC2ED"></td>
    </tr>
    <tr>
        <td>
        <table class="leftframetable" cellspacing="0" cellpadding="0"
            width="97%" align="right" border="0">
            <tbody>
                <tr>
                    <td height="25"
                        style="background: url(Images/left_tt.gif) no-repeat">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="20"></td>
                            <td class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2);
                                height=25>库存管理</td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id=submenu2 cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A03010101" target=main>商品分类</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A03020101" target=main>库存列表</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A03030101" target=main>添加损益</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
            </tbody>
        </table>
        </td>
    </tr>
    <tr>
        <td height="5" background="Images/tableline_bottom.jpg"
            bgcolor="#9BC2ED"></td>
    </tr>
<tr>
        <td height="5" background="Images/tableline_top.jpg" bgcolor="#9BC2ED"></td>
    </tr>
    <tr>
        <td>
        <table class="leftframetable" cellspacing="0" cellpadding="0"
            width="97%" align="right" border="0">
            <tbody>
                <tr>
                    <td height="25"
                        style="background: url(Images/left_tt.gif) no-repeat">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="20"></td>
                            <td class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2);
                                height=25>订单管理</td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id=submenu2 cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A10010101" target="_blank">订单列表</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A14010101" target="main">发送预警</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A10020101" target="main">订单状态</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
            </tbody>
        </table>
        </td>
    </tr>
    <tr>
        <td height="5" background="Images/tableline_bottom.jpg"
            bgcolor="#9BC2ED"></td>
    </tr>
  <!-- <tr>
        <td height="5" background="Images/tableline_top.jpg" bgcolor="#9BC2ED"></td>
    </tr>
    <tr>
        <td>
        <table class="leftframetable" cellspacing="0" cellpadding="0"
            width="97%" align="right" border="0">
            <tbody>
                <tr>
                    <td height="25"
                        style="background: url(Images/left_tt.gif) no-repeat">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="20"></td>
                            <td class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2);
                                height=25>工作管理</td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id=submenu2 cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A05010101" target=main>新商品审核</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A05020101" target=main>美工作图列表</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A05030101" target=main>文案录入</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A05040101" target=main>上架管理</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
            </tbody>
        </table>
        </td>
    </tr>
    <tr>
        <td height="5" background="Images/tableline_bottom.jpg"
            bgcolor="#9BC2ED"></td>
    </tr>
    -->
   
<tr>
        <td height="5" background="Images/tableline_top.jpg" bgcolor="#9BC2ED"></td>
    </tr>
    <tr>
        <td>
        <table class="leftframetable" cellspacing="0" cellpadding="0"
            width="97%" align="right" border="0">
            <tbody>
                <tr>
                    <td height="25"
                        style="background: url(Images/left_tt.gif) no-repeat">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="20"></td>
                            <td class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2);
                                height=25>发货管理</td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id=submenu2 cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A06010101" target=main>发货管理（广东至日本）</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A13010101" target=_blank>发货管理（日本）</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A13020101" target=_blank>发货未反映一览</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A13030101" target=main>运费设置</a></td>
                            </tr>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A13040101" target=main>报关单模板设置</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
            </tbody>
        </table>
               <tr>
        <td height="5" background="Images/tableline_bottom.jpg"
            bgcolor="#9BC2ED"></td>
    </tr>
<tr>
        <td height="5" background="Images/tableline_top.jpg" bgcolor="#9BC2ED"></td>
    </tr>
    <tr>
        <td> 
        <table class="leftframetable" cellspacing="0" cellpadding="0"
            width="97%" align="right" border="0">
            <tbody>
                <tr>
                    <td height="25"
                        style="background: url(Images/left_tt.gif) no-repeat">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="20"></td>
                            <td class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2);
                                height=25>进货管理</td>
                        </tr>
                    </table>
                    </td>
                </tr>
                
<!--                 <tr> -->
<!--                     <td> -->
<!--                     <table id=submenu2 cellSpacing=0 cellPadding=0 width="100%" -->
<!--                         border=0> -->
<!--                         <tbody> -->
<!--                             <tr> -->
<!--                                 <td width="2%"><img src="Images/closed.gif"></td> -->
<!--                                 <td height=23><a href="A07010101" target=main>进货单生成</a></td> -->
<!--                             </tr> -->
<!--                         </tbody> -->
<!--                     </table> -->
<!--                     </td> -->
<!--                 </tr> -->
                                <tr>
                    <td>
                    <table id=submenu2 cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A07020101" target=main>进货列表</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id=submenu2 cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A07030101" target=main>日本签收</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
            </tbody>
        </table>
        </td>
    </tr>
    <tr>
        <td height="5" background="Images/tableline_bottom.jpg"
            bgcolor="#9BC2ED"></td>
    </tr>
<tr>
        <td height="5" background="Images/tableline_top.jpg" bgcolor="#9BC2ED"></td>
    </tr>
    <tr>
        <td> 
        <table class="leftframetable" cellspacing="0" cellpadding="0"
            width="97%" align="right" border="0">
            <tbody>
                <tr>
                    <td height="25"
                        style="background: url(Images/left_tt.gif) no-repeat">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="20"></td>
                            <td class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2);
                                height=25>辅助功能</td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A11010101" target=main>文案字典</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A12010101" target=main>商品专辑列表</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
                 <tr>
                    <td>
                    <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A16010101" target=main>小工具</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
                 <tr>
                    <td>
                    <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A17010101" target=main>商品关键字分类</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A18010101" target=main>店铺管理</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
				<tr>
                    <td>
                    <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A19010101" target=main>パスワード変更</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
            </tbody>
        </table>
        </td>
    </tr>
    <tr>
        <td height="5" background="Images/tableline_bottom.jpg"
            bgcolor="#9BC2ED"></td>
    </tr>
    <tr>
        <td height="5" background="Images/tableline_top.jpg" bgcolor="#9BC2ED"></td>
    </tr>
    <tr>
        <td> 
        <table class="leftframetable" cellspacing="0" cellpadding="0"
            width="97%" align="right" border="0">
            <tbody>
                <tr>
                    <td height="25"
                        style="background: url(Images/left_tt.gif) no-repeat">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="20"></td>
                            <td class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2);
                                height=25>账务管理</td>
                        </tr>
                    </table>
                    </td>
                </tr>
<!--                 <tr> -->
<!--                     <td> -->
<!--                     <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%" -->
<!--                         border=0> -->
<!--                         <tbody> -->
<!--                             <tr> -->
<!--                                 <td width="2%"><img src="Images/closed.gif"></td> -->
<!--                                 <td height=23><a href="A09010101" target=main>群馬銀行入出金明細照会</a></td> -->
<!--                             </tr> -->
<!--                         </tbody> -->
<!--                     </table> -->
<!--                     </td> -->
<!--                 </tr> -->
                <tr>
                    <td>
                    <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A15010101?area=1" target=main>账目查询（上海）</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A15010101?area=0" target=main>账目查询（日本）</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A15030101" target=main>账本设置</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>
                    <table id="submenu2" cellSpacing=0 cellPadding=0 width="100%"
                        border=0>
                        <tbody>
                            <tr>
                                <td width="2%"><img src="Images/closed.gif"></td>
                                <td height=23><a href="A15040101" target=main>请求书下载</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </td>
                </tr>
            </tbody>
        </table>
        </td>
    </tr>
    <tr>
        <td height="5" background="Images/tableline_bottom.jpg"
            bgcolor="#9BC2ED"></td>
    </tr>
</table>
</body>
</html>
