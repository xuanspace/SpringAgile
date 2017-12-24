/*
 * Auto adjust iframe width and height
 * 
 * @Author  linweixuan@gmail.com
 * @version 2.3.3
 * @license MIT <http://opensource.org/licenses/MIT>
 */

// 全局对象
var App = window.App || {};

// 全局对象
(function() {

    /**
     * App iframe自适应内容高度
     * @param selector  iframe的id或者iframe的DOM对象
     */
    App.iframeAutoHeight = function(selector, minHeight) {
        var iframe = null;
        var browser = App.browser;

        // 参数是否Dom对象
        if (typeof selector === 'object') {
            iframe = selector;
        } else {
            iframe = document.getElementById(selector);
            if (!iframe) return;
        }

        // 设置iframe高度
        try {
            var height = 0;
            if (browser.name == 'Edge')
                height = iframe.contentWindow.document.body.scrollHeight;
            if (browser.name == 'MSIE')
                height = iframe.contentWindow.document.body.scrollHeight + 4;
            else if (browser.name == 'Chrome')
                height = iframe.contentWindow.document.body.scrollHeight;
            else if (browser.name == 'Firefox')
                height = iframe.contentWindow.document.documentElement.scrollHeight;
            else if (browser.name == 'OPR')
                height = iframe.contentDocument.body.offsetHeight;
            else if (browser.name == 'Safari')
                height = iframe.contentDocument.body.offsetHeight;
            else {
                var bHeight = iframe.contentWindow.document.body.scrollHeight;
                var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
                height = Math.max(bHeight, dHeight);
            }

            //if (height < minHeight)
            //    height = minHeight;
            $(iframe).height(height);
        } catch (ex) {
            console.log(ex);
        }
    }

    /**
     * 导入Juqery iframe自适应高度
     * @param selector  iframe的id
     */
    jQuery.fn.iframeAutoHeight = function(selector) {
        App.iframeAutoHeight(selector);
        return this;
    };

    /**
     * 定时动态探测iframe的内容高度自适应
     * @param selector  iframe的id
     */
    App.iframeAutoSize = function(iframeId, fncall) {
        var iframe = document.getElementById(iframeId);
        if (iframe) {
            window.setInterval(function() {
                //var minHeight = App.getContentVisiableHeight();
                App.iframeAutoHeight(iframeId);
            }, 100);
        }
    }

    /**
     * 创建一个iframe添加到标签内
     * @param selector 标签
     */
    App.addIframe = function(selector, iframeId) {
        $(selector).html('<iframe id="' + iframeId + '" src="" width="100%" height="100%" scrolling="no" frameborder="0" allowtransparency="true" style="background-color=transparent"></iframe>');
    }

    /**
     * AdminLite内容iframe自适应高度
     * @param 主界面iframe的id  
     */
    //App.iframeAutoSize("content-iframe");

})();