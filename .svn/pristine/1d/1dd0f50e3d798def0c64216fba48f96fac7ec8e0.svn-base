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

function init() {

		$.post("https://api.rms.rakuten.co.jp/es/2.0/order/searchOrder/", {
			"Authorization":"ESA U1AzMDg3NTlfY2lNZlE2MHEyMG12NmxxajpTTDMwODc1OV9ZOFFJZUlxd25SdGlzajZj",
			"Content-Type":"application/json",
			"Content-Type":"charset=utf-8","dateType":1,"startDatetime":"2018-07-31T00:00:00+0900","endDatetime":"2018-08-01T10:00:00+0900","PaginationRequestModel":{"requestRecordsAmount":1000,"requestPage":1,"SortModelList":[{"sortColumn":1,"sortDirection":1}]}
		}, function(result) {
			alert(result);
		},"json");
		
	
}

</script>

</head>
<body onload="init();">
</body>

</html>