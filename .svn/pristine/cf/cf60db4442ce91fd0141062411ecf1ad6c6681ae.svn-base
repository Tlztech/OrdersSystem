function actionSubmit(action){
	document.form1.action=action; 
    document.form1.submit(); 
}

function openShowModalDialog(width,height,url){
	var DialogLocation = CalcShowModalDialogLocation(width, height);
	return window.showModalDialog(url, window, DialogLocation);
	
}

function openShowModalDialogShuseika(width,height,url){
	var DialogLocation = CalcShowModalDialogLocationShuseika(width, height);
	return window.showModalDialog(url, window, DialogLocation);
	
}

function CalcShowModalDialogLocationShuseika(dialogWidth, dialogHeight) {
	var iWidth = dialogWidth;
	var iHeight = dialogHeight;
	var iTop = (window.screen.availHeight - 20 - iHeight) / 2;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	return 'dialogWidth:' + iWidth + 'px;dialogHeight:' + iHeight
			+ 'px;dialogTop: ' + iTop + 'px; dialogLeft: ' + iLeft
			+ 'px;center:yes;status:no;resizable:0;location:no';
}

function CalcShowModalDialogLocation(dialogWidth, dialogHeight) {
	var iWidth = dialogWidth;
	var iHeight = dialogHeight;
	var iTop = (window.screen.availHeight - 20 - iHeight) / 2;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	return 'dialogWidth:' + iWidth + 'px;dialogHeight:' + iHeight
			+ 'px;dialogTop: ' + iTop + 'px; dialogLeft: ' + iLeft
			+ 'px;center:yes;scroll:yes;status:no;resizable:0;location:no';
}

function _getRandomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; // 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1
    var maxPos = $chars.length;
    var pwd = '';
    for (var i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

function getLog(logKey) {
	var rs = "";
		$.post("getLogAction?logKey="+logKey, null, function(result) {
			rs = result;
			setLog(result);
		}, "json");
	return rs;
}
function setLog(result){
	if(result!=null){
	    document.getElementById("logArea").value = document.getElementById("logArea").value + "\r\n" + result;
	    $("#logArea").scrollTop(document.getElementById("logArea").scrollHeight); 
	}
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

//获取页面的高度、宽度
function getPageSize() {
    var xScroll, yScroll;
    if (window.innerHeight && window.scrollMaxY) {
        xScroll = window.innerWidth + window.scrollMaxX;
        yScroll = window.innerHeight + window.scrollMaxY;
    } else {
        if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac    
            xScroll = document.body.scrollWidth;
            yScroll = document.body.scrollHeight;
        } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari    
            xScroll = document.body.offsetWidth;
            yScroll = document.body.offsetHeight;
        }
    }
    var windowWidth, windowHeight;
    if (self.innerHeight) { // all except Explorer    
        if (document.documentElement.clientWidth) {
            windowWidth = document.documentElement.clientWidth;
        } else {
            windowWidth = self.innerWidth;
        }
        windowHeight = self.innerHeight;
    } else {
        if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode    
            windowWidth = document.documentElement.clientWidth;
            windowHeight = document.documentElement.clientHeight;
        } else {
            if (document.body) { // other Explorers    
                windowWidth = document.body.clientWidth;
                windowHeight = document.body.clientHeight;
            }
        }
    }       
    // for small pages with total height less then height of the viewport    
    if (yScroll < windowHeight) {
        pageHeight = windowHeight;
    } else {
        pageHeight = yScroll;
    }    
    // for small pages with total width less then width of the viewport    
    if (xScroll < windowWidth) {
        pageWidth = xScroll;
    } else {
        pageWidth = windowWidth;
    }
    arrayPageSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight);
    return arrayPageSize;
}
