/*
 * Load page to content section 
 * 
 * @Author  linweixuan@gmail.com
 * @version 2.3.3
 * @license MIT <http://opensource.org/licenses/MIT>
 */

// 全局对象
var App = window.App || {};

// 页面对象
(function() {

    /** 
     * 获取页面内容,回调结果
     * @param url 地址
     * @param params 参数
     * @param callback  回调函数
     */
    jQuery.fn.load = function(url, params, callback) {
        var type, response, self = this;
        if (jQuery.isFunction(params)) {
            callback = params;
            params = undefined;
        } else if (params && typeof params === "object") {
            type = "POST";
        }

        if (self.length > 0) {
            jQuery.ajax({
                cache: false,
                url: url,
                beforeSend: function(xhr) {
                    xhr.setRequestHeader('X-Requested-With', { toString: function() { return ''; } });
                },
                type: type || "GET",
                dataType: "html",
                data: params
            }).done(function(responseText) {
                response = arguments;
                var dom = $(responseText);
                //self.append(dom);
                $(".content").html(responseText);
                //self.html(responseText);
            }).always(callback && function(jqXHR, status) {
                self.each(function() {
                    callback.apply(this, response || [jqXHR.responseText, status, jqXHR]);
                });
            });
        }

        return this;
    };


    /**
     * 提交页面, 结果回调
     * @param url 地址
     * @param params 参数
     * @param callback  回调函数
     */
    App.post = function(url, params, callback) {
        var result = null;
        var headers = {};
        headers['CSRFToken'] = $("#csrftoken").val();

        $.ajax({
            type: 'post',
            async: false,
            url: url,
            data: params,
            dataType: 'json',
            headers: headers,
            success: function(data, status) {
                result = data;
                if (data && data.code && data.code == '101') {
                    modals.error("操作失败，请刷新重试，具体错误：" + data.message);
                    return false;
                }
                if (callback) {
                    callback.call(this, data, status);
                }
            },
            error: function(err, err1, err2) {
                if (err && err.readyState && err.readyState == '4') {
                    var responseBody = err.responseText;
                    if (responseBody) {
                        responseBody = "{'retData':" + responseBody;
                        var resJson = eval('(' + responseBody + ')');
                        $("#csrftoken").val(resJson.csrf.CSRFToken);
                        this.success(resJson.retData, 200);
                    }
                    return;
                }
                modals.error({
                    text: JSON.stringify(err) + '<br/>err1:' + JSON.stringify(err1) + '<br/>err2:' + JSON.stringify(err2),
                    large: true
                });
            }
        });
        return result;
    }

})();