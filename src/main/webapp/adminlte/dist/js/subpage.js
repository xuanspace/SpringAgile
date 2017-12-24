/*
 * Auto adjust iframe width and height
 * 
 * @Author  linweixuan@gmail.com
 * @version 2.3.3
 * @license MIT <http://opensource.org/licenses/MIT>
 */

var browserVersion = window.navigator.userAgent.toUpperCase();
var browserVersion = window.navigator.userAgent.toUpperCase();
var isOpera = browserVersion.indexOf("OPERA") > -1 ? true : false;
var isFireFox = browserVersion.indexOf("FIREFOX") > -1 ? true : false;
var isChrome = browserVersion.indexOf("CHROME") > -1 ? true : false;
var isSafari = browserVersion.indexOf("SAFARI") > -1 ? true : false;
var isIE = (!!window.ActiveXObject || "ActiveXObject" in window);
var isIE9More = (! -[1, ] == false);


function autoSetFrameHeight() {
    var iframe;
    var height = $(document).height();    
     height = $(document.body).height()
    if (parent) {
        iframe = parent.document.getElementById("content-iframe");
        if (iframe) {
               
        }
    }
}



// JQuery code start here
$(document).ready(function() {
    
    // 自动设置frame的高度
    autoSetFrameHeight();

});