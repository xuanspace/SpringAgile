/*
 * Load sidebar menu url to content section or iframe 
 * 
 * @Author  linweixuan@gmail.com
 * @version 2.3.3
 * @license MIT <http://opensource.org/licenses/MIT>
 */

// 全局对象
var App = window.App || {};

// 菜单对象
App.SideBarMenu = (function() {

    /**
     * 根据Admin左侧菜单URL加载页面
     * 自动判断内容区是否有iframe
     */
    var sideBarMenu = function() {
        // 是否iframe加载
        this.hasContentIframe = false;
        // 菜单项链接URL
        this.menuSelector = ".sidebar-menu a[url]";
        // 页面加载到div
        this.contentSelector = ".content";
        // 页面加载到iframe
        this.iframeSelector = '.content-iframe';
    };

    /**
     * 初始化Admin左侧菜单
     */
    sideBarMenu.prototype.init = function() {
        // 是否使用iframe加载内容
        if ($(this.iframeSelector).length > 0) {
            this.hasContentIframe = true;
            //App.iframeAutoSize(this.iframeSelector.substr(1));
        }

        //this.handleClickEvent();
    }

    /**
     * 点击左边菜单加载页面
     * @param tableId table的id
     */
    sideBarMenu.prototype.handleClickEvent = function() {
        var self = this;
        $(this.menuSelector).on("click", function() {
            var url = $(this).attr("url");
            if (url) {
                $("ul li").removeClass("active");
                $(this).parent().addClass("active");
                if (self.hasContentIframe) {
                    self.loadIframe(url);
                } else {
                    self.loadPage(url);
                }
            }
        });
    }

    /**
     * 将网页加载到标签中
     * @param selector 标签
     * @param url 地址
     */
    sideBarMenu.prototype.loadPage = function(url) {
        var self = this;
        $(self.contentSelector).load(url, function(response, status, xhr) {
            if (status == "success") {
                if (response) {
                    try {
                        var result = jQuery.parseJSON(response);
                        if (result.code == 100) {
                            $(self.contentSelector).html("");
                            alert(result.data);
                        }
                    } catch (e) {
                        return response;
                    }
                }
            }
        });
    }

    /**
     * 通过iframe来加载网页
     * @param selector 标签
     * @param url 地址
     */
    sideBarMenu.prototype.loadIframe = function(url) {
        var iframe = $(this.iframeSelector);
        iframe.attr('src', url);
    }

    // 初始化左侧菜单
    if (App.sideBarMenu == null) {
        App.sideBarMenu = new sideBarMenu();
        App.sideBarMenu.init();
    }
    return this;

})();