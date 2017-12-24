/*
 * App global object
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
     * 获取浏览器名称和版本
     * @return {name,version}
     */
    App.browser = (function() {
        var ua = navigator.userAgent,
            tem,
            M = ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
        if (/trident/i.test(M[1])) {
            tem = /\brv[ :]+(\d+)/g.exec(ua) || [];
            return { name: 'MSIE', version: (tem[1] || '') };
        }
        if (M[1] === 'Chrome') {
            tem = ua.match(/\b(OPR|Edge)\/(\d+)/);
            if (tem != null)
                return { name: tem[1], version: tem[2] };
        }
        M = M[2] ? [M[1], M[2]] : [navigator.appName, navigator.appVersion, '-?'];
        if ((tem = ua.match(/version\/(\d+)/i)) != null) M.splice(1, 1, tem[1]);
        return { name: M[0], version: M[1] };
    })();

    /**
     * 去掉Html的min-height属性
     */
    App.htmlAutoHeight = function() {
        $('html').css('min-height', '0');
        $("body").css("min-height", '0');
    }

    /**
     * 获取内容标签块section可视高度
     * @return 高度
     */
    App.getContentVisiableHeight = function() {
        var wheight = $(".content-wrapper").height();
        var hheight = $(".content-header").height();
        console.log("content height:" + wheight);
        return wheight - hheight;
    }

    /**
     * 获取url参数值
     * @name 参数名
     * @return 参数值
     */
    App.getUrlParameter = function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    /**
     * Web App初始化函数
     */
    App.init = function() {
        return this;
    }

    // 全局Web在这里初始化
    App.init();

})();


$(document).ready(function() {

});