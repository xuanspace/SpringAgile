/*
 * Web page layout
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
     * 创建一个iframe添加到标签内
     * @param selector 标签
     */
    App.getContentVisiableHeight = function() {
        var wheight = $(".content-wrapper").height();
        var hheight = $(".content-header").height();
        return wheight - hheight;
    }


})();