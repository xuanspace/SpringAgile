/*
 * 表单Form
 * 
 * @Author  linweixuan@gmail.com
 * @version 2.3.3
 * @license MIT <http://opensource.org/licenses/MIT>
 */

// App对象
var App = window.App || {};

// Form对象
App.Form = (function() {

    /**
     * 1. 该类通过form的元素属性name或者data-field来绑定数据字段
     * 2. 有时表单填充的数据可能是请求的数据实体一部分
     * 3. 如果表单有数据实体,提交更新后的数据实体(带回主键)
     */
    var me = function(selector) {
        // 表单选择器
        this.selector = selector;
        // 表单数据实体
        this.data = null;
        // 表单值集合
        this.values = [];
        // 表单提交函数
        this.fnSubmit = null;
        // 获取表单输入
        this.fnInput = null;

        // 初始化表单
        this.init();
    };

    /**
     * 初始化Form
     */
    me.prototype.init = function() {
        $(this.selector).data("obj", this);
        this.setDatePicker();
        this.handleEvent();
    }

    /**
     * 初始化input为Datepicker
     */
    me.prototype.setDatePicker = function() {
        App.setLayDatePicker();
    }

    /**
     * 设置表单输入值获取函数
     */
    me.prototype.setInputHandle = function(fnInput, that) {
        if (that)
            this.fnInput = $.proxy(fnInput, that);
        else
            this.fnInput = fnInput;
    }

    /**
     * 设置表单提交处理函数
     */
    me.prototype.setSubmitHandle = function(fnSubmit, that) {
        if (that)
            this.fnSubmit = $.proxy(fnSubmit, that);
        else
            this.fnSubmit = fnSubmit;
    }

    /**
     * 表单事件处理
     */
    me.prototype.handleEvent = function() {
        var self = this;
        // 提交事件处理
        $(this.selector + ' :input[type|="submit"]').on("click", function() {
            if (self.fnSubmit)
                self.fnSubmit();
            else
                self.submit();
            event.preventDefault();
        });
        // 清除事件处理
        $(this.selector + ' .btn_clean').on("click", function() {
            self.clean();
        });
    }

    /**
     * 获取表单input的值
     */
    me.prototype.getInputValue = function(input, name) {
        if (input.type === 'text') {
            var value = $(input).val();
            // 日期格式转为long
            if ($(input).attr("data-type") == "date") {
                value = App.date2Long(value);
            }
            // input附件属性
            var type = $(input).attr('data-op');
            var condition = $(input).attr('data-type');
            if (!type) type = "";
            if (!condition) condition = "";
            this.values.push({ 'name': name, 'value': value, 'type': type, 'operator': condition });
        }
        if (input.type === 'button' || input.type === 'submit' || input.type === 'reset') {

        }
        if (input.type === 'file') {

        }
        if (input.type === 'image') {

        }
    }

    /**
     * 获取表单select的值
     */
    me.prototype.getSelectValue = function(select, name) {
        // input值和附件属性
        var value = $(select).val();
        var condition = $(select).attr('data-condition');
        this.values.push({ 'name': name, 'value': value, 'condition': condition });
    }

    /**
     * 获取表单area的值
     */
    me.prototype.getAreaValue = function(area, name) {
        // input值和附件属性
        var value = $(area).val();
        var condition = $(area).attr('data-condition');
        this.values.push({ 'name': name, 'value': value, 'condition': condition });
    }

    /**
     * 获取表单数据
     * @return 返回表单数组(每项是对象)
     */
    me.prototype.getFormData = function() {
        var self = this;
        self.values = [];
        // 遍历form的元素        
        $(this.selector).find("*").each(function(i, element) {
            // 获取元素name
            var name = $(element).attr('name') || $(element).attr('data-field');
            if (typeof name === 'undefined' || name.replace(/(^s*)|(s*$)/g, "").length == 0)
                return;
            // 获取元素值
            var tag = element.tagName.toLowerCase();
            if (tag == "input")
                self.getInputValue(element, name);
            else if (tag === 'select')
                self.getSelectValue(element, name);
            else if (tag === 'area')
                self.getAreaValue(element, name);

        });
        return this.values;
    }

    /**
     * 获取表单数据
     * @return 返回表单对象
     */
    me.prototype.serialize = function() {
        var obj = {};
        this.values = this.getFormData();
        for (var i = 0; i < this.values.length; i++) {
            obj[this.values[i].name] = this.values[i].value;
        }
        return obj;
    }

    /**
     * 填充表单数据
     * @param data 表单数据对象
     */
    me.prototype.fill = function(data) {
        var self = this;
        // 检验数据对象
        if (data === null || typeof data === 'undefined') {
            return;
        }

        // 遍历form的元素        
        $(this.selector).find("*").each(function(i, element) {
            // 获取元素name
            var name = $(element).attr('name') || $(element).attr('data-field');
            if (typeof name === 'undefined' || name.replace(/(^s*)|(s*$)/g, "").length == 0)
                return;
            // 设置元素值
            try {
                var value = eval('data.' + name);
                // Long型日期类型格式化
                if ($(element).attr("data-type") == "date") {
                    value = App.formatDate(value);
                }
                self.setValue(element, value);
            } catch (e) {
                //console.log(e.message);
            }
        });
    }

    /**
     * 设置表单元素值
     * @param element dom对象
     * @param value 值
     */
    me.prototype.setValue = function(element, value) {
        switch ($(element).attr("type")) {
            case "hidden":
            case "password":
            case "textarea":
                $(element).val(value);
                break;
            case "text":
                if ($(element).hasClass("hasDatepicker")) {
                    var re = /^[-+]*[0-9]*$/;
                    var dateValue = null;
                    if (re.test(value)) {
                        dateValue = new Date(parseInt(value));
                        var strDate = dateValue.getUTCFullYear() + '-' + (dateValue.getUTCMonth() + 1) + '-' + dateValue.getUTCDate();
                        dateValue = $.datepicker.parseDate('yy-mm-dd', strDate);
                    } else if (value) {
                        dateValue = $.datepicker.parseDate(options.dateFormat, value);
                    }
                    $(element).datepicker('setDate', dateValue);
                } else if ($(element).attr("alt") == "double") {
                    $(element).val(value.toFixed(2));
                } else {
                    $(element).val(value);
                }
                break;

            case "select-one":
                if (value) {
                    $(element).val(value);
                }
                break;
            case "radio":
                $(element).each(function(i, radio) {
                    if ($(radio).val() == value) {
                        $(radio).attr("checked", "checked");
                    }
                });
                break;
            case "checkbox":
                if ($.isArray(value)) {
                    $.each(value, function(i, arrayItem) {
                        if (typeof(arrayItem) == 'object') {
                            arrayItemValue = eval("arrayItem." + arrayAtribute);
                        } else {
                            arrayItemValue = arrayItem;
                        }
                        if ($(element).val() == arrayItemValue) {
                            $(element).attr("checked", "checked");
                        }
                    });
                } else {
                    if ($(element).val() == value) {
                        $(element).attr("checked", "checked");
                    }
                }
                break;
        } //switch
    }

    /**
     * 加载表单数据
     * @param url 数据请求url
     */
    me.prototype.load = function(url) {
        var self = this;
        $.ajax({
            type: "get",
            url: url,
            success: function(data, status) {
                self.data = data;
                self.fill(data);
            },
            error: function(data) {
                alert("错误:" + data.responseText);
            }
        });
    }

    /**
     * 提交表单数据
     */
    me.prototype.submit = function() {
        var $form = $(this.selector);
        var method = $form.attr('method');

        // 表单请求方式
        if (typeof method === 'undefined') {
            method = "post";
        }
        // 表单请求地址
        var action = $form.attr('action');
        if (typeof action === 'undefined') {
            console.log("not define form action:" + this.selector);
            action = this.url;
        }
        // 如果表单有数据实体,更新提交
        var formdata = this.serialize();
        if (this.data) {
            for (var name in formdata) {
                this.data[name] = formdata[name];
            }
            formdata = this.data;
            // 表单支持PUT请求
            formdata._method = "put";
        }

        // 提交表单请求
        console.log(formdata);
        $.ajax({
            type: method,
            url: action,
            data: formdata,
            //dataType: "json",
            //contentType: 'application/json;charset=utf-8',
            success: function(data, status) {
                //$target.html(data);
            },
            error: function(data) {
                alert("错误:" + data.responseText);
            }
        });
    }

    /**
     * 重置表单数据
     */
    me.prototype.reset = function() {
        // 遍历form的元素        
        $(this.selector).find("*").each(function(i, item) {
            var tag = item.tagName.toLowerCase();
            switch (tag) {
                case 'input':
                    item.checked = item.defaultChecked;
                case 'textarea':
                    item.value = item.defaultValue;
                case 'option':
                case 'optgroup':
                    var select = $(item).parents('select');
                    if (select.length && select[0].multiple) {
                        if (tag === 'option') {
                            item.selected = item.defaultSelected;
                        }
                    }
                case 'select':
                    $(item).find('option').each(function(i) {
                        item.selected = item.defaultSelected;
                        if (item.defaultSelected && !$(item)[0].multiple) {
                            $(item)[0].selectedIndex = i;
                        }
                    });
            }
        });
    }

    /**
     * 重置表单数据
     */
    me.prototype.clean = function(includeHidden) {
        // 遍历form的元素        
        var re = /^(?:color|date|datetime|email|month|number|password|range|search|tel|text|time|url|week)$/i; // 'hidden' is not in this list
        $(this.selector).find("*").each(function(i, item) {
            var type = item.type,
                tag = item.tagName.toLowerCase();
            if (re.test(type) || tag === 'textarea') {
                item.value = '';
            } else if (type === 'checkbox' || type === 'radio') {
                item.checked = false;
            } else if (tag === 'select') {
                item.selectedIndex = -1;
            } else if (type === 'file') {
                if (/MSIE/.test(navigator.userAgent)) {
                    $(item).replaceWith($(item).clone(true));
                } else {
                    $(item).val('');
                }
            } else if (includeHidden) {
                if ((includeHidden === true && /hidden/.test(t)) ||
                    (typeof includeHidden === 'string' && $(item).is(includeHidden))) {
                    item.value = '';
                }
            }
        });
    }

    /**
     * 禁止表单元素
     */
    me.prototype.enable = function(isable) {
        if (typeof isable === 'undefined')
            isable = true;
        var fieldset = $(this.selector + ' fieldset').get(0);
        if (fieldset)
            fieldset.disabled = !isable;
    }

    // 返回表单类
    return me;

})();