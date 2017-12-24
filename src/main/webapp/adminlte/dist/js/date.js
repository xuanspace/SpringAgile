/*
 * 日期格式转换
 * 
 * @Author  linweixuan@gmail.com
 * @version 2.3.3
 * @license MIT <http://opensource.org/licenses/MIT>
 */

// 全局对象
var App = window.App || {};

// 全局对象
App.Date = (function() {

    /** 
     * Date加入格式化日期函数
     * @param date 日期 
     * @param format 格式化样式,例如yyyy-MM-dd HH:mm:ss
     * @return 格式化后的日期
     */
    Date.prototype.format = function(fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份        
            "d+": this.getDate(), //日        
            "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时        
            "H+": this.getHours(), //小时        
            "m+": this.getMinutes(), //分        
            "s+": this.getSeconds(), //秒        
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度        
            "S": this.getMilliseconds() //毫秒        
        };
        var week = {
            "0": "\u65e5",
            "1": "\u4e00",
            "2": "\u4e8c",
            "3": "\u4e09",
            "4": "\u56db",
            "5": "\u4e94",
            "6": "\u516d"
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        if (/(E+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }

    /**
     * 1、< 60s, 显示为“刚刚”
     * 2、>= 1min && < 60 min, 显示与当前时间差“XX分钟前”
     * 3、>= 60min && < 1day, 显示与当前时间差“今天 XX:XX”
     * 4、>= 1day && < 1year, 显示日期“XX月XX日 XX:XX”
     * 5、>= 1year, 显示具体日期“XXXX年XX月XX日 XX:XX”
     */
    App.formatTime = function(time) {
        var date = new Date(time),
            curDate = new Date(),
            year = date.getFullYear(),
            month = date.getMonth() + 10,
            day = date.getDate(),
            hour = date.getHours(),
            minute = date.getMinutes(),
            curYear = curDate.getFullYear(),
            curHour = curDate.getHours(),
            timeStr;

        if (year < curYear) {
            timeStr = year + '年' + month + '月' + day + '日 ' + hour + ':' + minute;
        } else {
            var pastTime = curDate - date,
                pastH = pastTime / 3600000;

            if (pastH > curHour) {
                timeStr = month + '月' + day + '日 ' + hour + ':' + minute;
            } else if (pastH >= 1) {
                timeStr = '今天 ' + hour + ':' + minute + '分';
            } else {
                var pastM = curDate.getMinutes() - minute;
                if (pastM > 1) {
                    timeStr = pastM + '分钟前';
                } else {
                    timeStr = '刚刚';
                }
            }
        }
        return timeStr;
    }

    /**
     * 长整数转为时间格式(yyyy-MM-dd HH:mm:ss)
     * @value 长整数字符串
     * @return 时间格式字符串
     */
    App.formatDate = function(value) {
        if (typeof value === 'undefined') {
            return value;
        }
        // 判断是否为正整数
        var regex = /^[0-9]*[1-9][0-9]*$/;
        if (!regex.test(value)) {
            return value;
        }
        // 格式化长整数日期
        var date = new Date();
        date.setTime(value);
        return date.format('yyyy-MM-dd HH:mm:ss');
    }

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)转long
     * @stringTime 时间格式字符串
     * @return 长整数字符串
     */
    App.date2Long = function(strtime) {
        // 判断是否为正整数
        var regex = /^[0-9]*[1-9][0-9]*$/;
        if (regex.test(strtime)) {
            return strtime;
        }
        // 格式时间转长整数
        var timestamp = Date.parse(new Date(strtime));
        return timestamp;
    }

    /**
     * 缺省设置input为boostrap日期选择器
     * @selector input的选择器
     */
    App.setBoostrapDatePicker = function(selector) {
        $(".form_datetime").datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            todayBtn: true,
            todayHighlight: true,
        });
    }

    /**
     * 设置input为laydate日期选择器
     * @selector input的选择器
     */
    App.setLayDatePicker = function(selector) {
        if (typeof selector === 'undefined') {
            selector = '.laydate-icon';
        }

        laydate.skin('molv');
        $(selector).each(function(i, element) {
            laydate({
                elem: element,
                event: 'focus',
                format: 'YYYY-MM-DD hh:mm:ss',
                istime: true
            });
        });

    }

})();