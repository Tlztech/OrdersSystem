<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>V040201:复制代码生成</title>
<link href="Images/css1/css.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/commonjs.js"></script>
<script type="text/javascript">
var obj;
var mng_number;
var item_number;
var item_name;
var catch_copy;
var mobile_catch_copy;
var price;
var regular_price;
var mobile_caption;
var smart_caption;
var display_caption;
var image_url1;
var image_url2;
var image_url3;
var image_alt1;
var image_alt2;
var image_alt3;
var genre_id;
var category_1;
var category_2;
var category_3;
var ha_name;
var va_name;
var catalog_caption;
var str1;
function proc(){
	    var i = document.getElementById("inputText3").value;
	    var input = document.getElementById("inputText").value.split("\n");
        if(input[i] !="" && i<input.length){
            document.getElementById("mainFrm").src = ("/OrdersSystem/conv/"+input[i]+"_01.htm");
            setTimeout(function(){
                    obj=document.getElementById("mainFrm").contentWindow;
                    mng_number = obj.document.getElementsByName('mng_number')[0].value;
                    item_number = obj.document.getElementsByName('item_number')[0].value;
                    item_name = obj.document.getElementsByName('item_name')[0].value;
                    catch_copy = obj.document.getElementsByName('catch_copy')[0].value;
                    mobile_catch_copy = obj.document.getElementsByName('mobile_catch_copy')[0].value;
                    price = obj.document.getElementsByName('price')[0].value;
                    regular_price = obj.document.getElementsByName('regular_price')[0].value;
                    mobile_caption = obj.document.getElementsByName('mobile_caption')[0].value;
                    smart_caption = obj.document.getElementsByName('smart_caption')[0].value;
                    display_caption = obj.document.getElementsByName('display_caption')[0].value;
                    image_url1 = obj.document.getElementsByName('image_url1')[0].value;
                    image_url2 = obj.document.getElementsByName('image_url2')[0].value;
                    image_url3 = obj.document.getElementsByName('image_url3')[0].value;
                    image_alt1 = obj.document.getElementsByName('image_alt1')[0].value;
                    image_alt2 = obj.document.getElementsByName('image_alt2')[0].value;
                    image_alt3 = obj.document.getElementsByName('image_alt3')[0].value;
                    genre_id = obj.document.getElementsByName('genre_id')[0].value;
                    category_1 = obj.document.getElementsByName('category_1')[0].value;
                    category_2 = obj.document.getElementsByName('category_2')[0].value;
                    category_3 = obj.document.getElementsByName('category_3')[0].value;
                    ha_name = obj.document.getElementsByName('ha_name')[0].value;
                    va_name = obj.document.getElementsByName('va_name')[0].value;
                    catalog_caption =  obj.document.getElementsByName('catalog_caption')[0].value;
                    
                    str1 = null;
                    str1 = '';
                    
                    
                    str1+="javascript:void(document.getElementsByName('mng_number')[0].value='"+mng_number+"');\n";
                    str1+="javascript:void(document.getElementsByName('item_number')[0].value='"+item_number+"');\n";
                    str1+="javascript:void(document.getElementsByName('item_name')[0].value='"+item_name+"');\n";
                    str1+="javascript:void(document.getElementsByName('catch_copy')[0].value='"+catch_copy+"');\n";
                    str1+="javascript:void(document.getElementsByName('mobile_catch_copy')[0].value='"+mobile_catch_copy+"');\n";
                    str1+="javascript:void(document.getElementsByName('price')[0].value='"+price+"');\n";
                    str1+="javascript:void(document.getElementsByName('regular_price')[0].value='"+regular_price+"');\n";
                    str1+="javascript:void(document.getElementsByName('postage_flag')[0].checked='true')"+";\n";
                    str1+="javascript:void(document.getElementsByName('mobile_caption')[0].value='"+mobile_caption+"');\n";
                    str1+="javascript:void(document.getElementsByName('smart_caption')[0].value='"+smart_caption+"');\n";
                    str1+="javascript:void(document.getElementsByName('display_caption')[0].value='"+display_caption+"');\n";
                    str1+="javascript:void(document.getElementsByName('image_url1')[0].value='"+'http://image.rakuten.co.jp/3eshop/cabinet/commodity'+image_url1.substring(image_url1.lastIndexOf('/'))+"');\n";
                    str1+="javascript:void(document.getElementsByName('image_url2')[0].value='"+'http://image.rakuten.co.jp/3eshop/cabinet/commodity'+image_url2.substring(image_url1.lastIndexOf('/'))+"');\n";
                    str1+="javascript:void(document.getElementsByName('image_url3')[0].value='"+'http://image.rakuten.co.jp/3eshop/cabinet/commodity'+image_url3.substring(image_url1.lastIndexOf('/'))+"');\n";
                    str1+="javascript:void(document.getElementsByName('image_alt1')[0].value='"+image_alt1+"');\n";
                    str1+="javascript:void(document.getElementsByName('image_alt2')[0].value='"+image_alt2+"');\n";
                    str1+="javascript:void(document.getElementsByName('image_alt3')[0].value='"+image_alt3+"');\n";
                    str1+="javascript:void(document.getElementsByName('genre_id')[0].value='"+genre_id+"');\n";
                    str1+="javascript:void(document.getElementsByName('category_1')[0].value='"+category_1+"');\n";
                    str1+="javascript:void(document.getElementsByName('category_2')[0].value='"+category_2+"');\n";
                    str1+="javascript:void(document.getElementsByName('category_3')[0].value='"+category_3+"');\n";
                    str1+="javascript:void(document.getElementsByName('ha_name')[0].value='"+ha_name+"');\n";
                    str1+="javascript:void(document.getElementsByName('va_name')[0].value='"+va_name+"');\n";
                    str1+="javascript:void(document.getElementsByName('catalog_caption')[0].value='"+catalog_caption+"');\n";
                    str1+="javascript:void(document.getElementsByName('inventory_type')[2].checked='true')"+";\n";
                    str1+="javascript:void(document.getElementsByName('inventory_disp_type')[1].checked='true');";
                    str1+="javascript:void(document.getElementsByName('regist_newitem')[0].submit());";
  
                    
                    
                    
                    document.getElementById("inputText2").value+=image_url1+"\n";
                    document.getElementById("inputText2").value+=image_url2+"\n";
                    document.getElementById("inputText2").value+=image_url3+"\n";
                    i++;
                    document.getElementById("inputText3").value=i;
                    $.post("A04010202",{inputText2:str1,mngnumber:mng_number,picUrl:document.getElementById("inputText2").value},proc());
            },3000);
        }
}
</script>
</head>
<body>
    <s:form name="form1" theme="simple">
    <table>
        <tr>
            <td>
                <textarea name="inputText" id="inputText" style="width:300px;height:350px">nzlf004</textarea>
            </td>
            <td>
                <input type="button" id="gogogo" value="提交" style="width:40px;height:20px" onclick="proc()">
            </td>
            <td>
                <textarea id="inputText3" style="width:200px;height:150px">0</textarea>
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <td>
                <textarea id="inputText2" name="inputText2" style="width:600px;height:100px"></textarea>
            </td>
        </tr>
    </table>
    </s:form>
    <iframe id="mainFrm" style="display:none" width="500px" height="500px"></iframe>
</body>
</html>