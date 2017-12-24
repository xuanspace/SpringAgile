/*
 * 通用表(DataTable)对象
 * 
 * @Author  linweixuan@gmail.com
 * @version 2.3.3
 * @license MIT <http://opensource.org/licenses/MIT>
 */

// 全局对象
var App = window.App || {};

// 表格对象
App.Table = (function() {

    /**
     * 表格关联属性
     * @param tableId 表标签id
     * @param operationId  按钮组标签id
     * @param url  表数据请求url
     */
    var table = function(tableId) {
        // Datatable标签id
        this.tableId = "#" + tableId;
        // 操作按钮组标签id
        this.operationId = null;
        // 表数据请求Url
        this.url = "";
        // 表单显示宽度
        this.formWidth = '800px';
        // 表是否单行选择
        this.singleSelect = true;
        // 表是已经加载数据
        this.loaded = false;
        // 表加载数据对象
        this.data = null;
        // 表Datatable对象
        this.grid = null;
        // 表搜索表单对象
        this.searchForm = null;
        // Datatable列设置
        this.columns = [];
        // Datatable列定义
        this.columnDefs = [];
        // 行添加回调函数
        this.fnAdd = null;
        // 行编辑回调函数
        this.fnEdit = null;
        // 行删除回调函数
        this.fnDelete = null;
        // 行格式化回调函数
        this.fnFormat = null;
        // 设置列数据字段
        this.setTableColunms();

        // 添加选择列
        //this.addCheckboxColumn();

        // 添加操作列
        //this.addOperationColumn();

        // 表格宽度自动
        //this.setTableAutoWidth();
    };

    /**
     * 表格中文设置
     */
    var lang = {
        sProcessing: "处理中...",
        "sLengthMenu": "_MENU_ 条记录",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
        "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "加载中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页",
            "sJump": "跳转"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    };


    /**
     * 初始化Datatable表格
     * @param tableId table的id
     */
    table.prototype.create = function() {
        var me = this;
        var operId = me.tableId + "_oper";

        // 初始化表格
        this.grid = $(me.tableId).DataTable({
            //设置项
            "language": lang, // 配置中文语言提示
            "autoWidth": false, //禁用自动调整列宽
            "stripeClasses": ["odd", "even"], //为奇偶行加上样式
            "processing": true, //显示正在加载中
            "serverSide": true, //服务器端分页
            "sServerMethod": "POST",
            "searching": false, //禁用原生搜索框
            "orderMulti": false, //启用多列排序		
            "renderer": "bootstrap", //渲染样式：Bootstrap和jquery-ui
            "pagingType": "simple_numbers", //分页样式(simple,simple_numbers,full,full_numbers)
            "pageLength": 10, //每页显示行数
            // 插入dom
            "dom": "<'row'<'col-sm-3'l><'col-sm-9'f <'" + operId + "'> >r>" + "<'row'<'col-sm-12't>>" + "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            //列表排序
            "order": [
                // [1, 'asc'] //第一列排序
            ],
            //列表字段
            "columns": me.columns,
            //列表定义
            "columnDefs": me.columnDefs,
            //数据请求
            'ajax': {},
            "sAjaxSource": me.url,
            "fnServerData": $.proxy(me.fetchData, me),
            //初始化完成
            initComplete: $.proxy(me.initComplete, me),
            //行回调
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                if (me.fnFormat) {
                    me.fnFormat(nRow, aData);
                } else {
                    me.formatRowData(nRow, aData);
                }
            }
        });

    }

    /**
     * 格式化行数据
     * @param row 行索引
     * @param data 行数据对象
     */
    table.prototype.formatRowData = function(row, data) {
        for (var i = 0; i < this.columns.length; i++) {
            var name = this.columns[i].data;
            var type = this.columns[i].datatype;
            if (type && type === "datetime") {
                try {
                    var value = eval('data.' + name);
                    $('td:eq(' + i + ')', row).html(App.formatDate(value));
                } catch (e) {
                    //console.log(e.message);
                }
            }
        }
    }

    /**
     * 绑定表格数据字段
     *   通过表定义的<th field="name">绑定的数据字段名
     */
    table.prototype.setTableColunms = function() {
        // 遍历表头获取绑定的字段名
        var me = this;
        $(this.tableId + ' th').each(function() {
            var name = $(this).attr("data-name");
            var type = $(this).attr("data-type");
            if (name) {
                me.columns.push({ "data": name, "datatype": type });
            } else {
                me.columns.push({ "data": null });
            }
        });
    }

    /**
     * 设置表格列的宽度
     * @param columnIndex 列索引
     * @param width 宽度
     */
    table.prototype.setColunmWidth = function(columnIndex, width) {
        if (this.columns.length > columnIndex) {
            this.columns[columnIndex].width = width;
        }
    }

    /**
     * 设置表格宽度自动与页面大小变化而变化
     */
    table.prototype.setTableAutoWidth = function() {
        $(this.tableId).css("width", "100%");
    }

    /**
     * 设置弹出表单宽度
     * @param width 表单宽度
     */
    table.prototype.setTableFormWidth = function(width) {
        this.formWidth = width;
    }

    /**
     * 隐藏表格列
     * @param columnIndex 列索引
     */
    table.prototype.hideTableColunm = function(columnIndex) {}


    /**
     * 向表格添加CheckBox列
     *    表格的第一列为Checkbox
     */
    table.prototype.addCheckboxColumn = function() {
        // 添加第一列
        var th = '<th class="text-center" field=""><input type="checkbox" class="checkall"/></th>';
        $(this.tableId + ' th:first').before(th);

        // 第一列设置
        var column = {
            "data": null,
            "bSortable": false,
            "width": "4px",
            "className": "text-center",
            "targets": 0,
            "render": function(data, type, full, meta) {
                // 显示行号 
                //var startIndex = meta.settings._iDisplayStart;
                //var rowIndex = startIndex + meta.row + 1;                
                return '<input type="checkbox" class="checkrow" value="' + data + '" />';
            }
        };
        this.columns.unshift(column);

        // 第一列定义
        var columnDef = {
            targets: 0,
            data: null,
            defaultContent: null
        };
        this.columnDefs.push(columnDef);
    }

    /**
     * 选择框选择所有事件
     */
    table.prototype.setCheckAllEvent = function() {
        console.log("table checkall click");
        $(this.tableId + ' .checkall').on('click', function() {
            var check = $(this).prop("checked");
            $(this.tableId + ' .checkrow').prop("checked", check);
        });
    }

    /**
     * 向表格添加操作按钮列
     *     表格最后一列为操作按钮列
     */
    table.prototype.addOperationColumn = function() {
        // 操作列添加到最后一列
        var th = '<th field="">操作</th>';
        $(this.tableId + ' th:last').after(th);

        // 操作列设置
        var column = {
            "data": null,
            "bSortable": false,
            "width": "70px",
            "className": "text-center",
            "targets": 0,
            "render": function(data, type, full, meta) {
                return "<div class='btn-group'>" +
                    "<button id='editRow' class='btn btn-primary btn-sm' type='button'><i class='fa fa-edit'></i></button>" +
                    "<button id='delRow' class='btn btn-primary btn-sm' type='button'><i class='fa fa-trash-o'></i></button></div>";
            }
        };
        this.columns.push(column);

        // 操作列定义
        var columnDef = {
            "targets": -1,
            "data": null,
            "defaultContent": null
        };
        this.columnDefs.push(columnDef);
    }

    /**
     * 表格后台数据请求
     */
    table.prototype.fetchData = function(sSource, aoData, fnCallback) {
        $.ajax({
            url: sSource,
            data: { "aoData": JSON.stringify(aoData) },
            type: 'post',
            dataType: 'json',
            async: false,
            success: function(result) {
                fnCallback(result);
            },
            error: function(msg) {
                console.log(msg);
            }
        });
    }

    /**
     * 表格完成初始化回调
     */
    table.prototype.initComplete = function() {
        var me = this;

        // 行选择框选择事件
        me.setRowCheckClick();

        // 设置表格行选择事件
        me.setRowSelectClick();

        // 设置表格行双击事件
        me.setRowDoubleClick();

        // 设置表格操作按钮组
        if (me.operationId) {
            me.setTableButton();
        }
    }


    /**
     * 将表格按钮移到记录/页同行
     */
    table.prototype.setTableButton = function(btnGroup) {
        var me = this;

        // 表格还未初始化返回
        if (me.operationId == null && me.grid == null) {
            me.operationId = "#" + btnGroup;
            return;
        }

        if (btnGroup) {
            me.operationId = "#" + btnGroup;
        }

        // 获取按钮组移至表格头
        var btnGroupDiv = $(me.operationId);
        var tableOperationId = me.tableId + "_oper";
        if (btnGroupDiv) {
            $(tableOperationId).append(btnGroupDiv.html());
            $(tableOperationId).addClass("dataTables_filter");
            btnGroupDiv.remove();
        } else {
            return;
        }

        // 表格按钮事件处理
        $(tableOperationId + ' button').on('click', function() {
            var name = $(this).attr("id");
            if (name == "btn-query")
                me.search();
            else if (name == "btn-add")
                me.add();
            else if (name == "btn-edit")
                me.edit();
            else if (name == "btn-delete")
                me.del();
            else if (name == "btn-refresh")
                me.refresh();
        });
    }

    /**
     * 设置表格搜索表单
     */
    table.prototype.setSearchForm = function(formId) {
        this.searchForm = new App.Form("#" + formId);
        this.searchForm.setSubmitHandle(this.search, this);
    }

    /**
     * 获取表格搜索条件
     */
    table.prototype.getSearchInputs = function() {
        var formdata = null;
        if (this.searchForm) {
            formdata = this.searchForm.getFormData();
        }
        return formdata;
    }

    /**
     * 获取选择多行的数据
     */
    table.prototype.setRowSelectClick = function() {
        // 表格行选择
        var me = this;
        $(me.tableId + ' tbody').on('click', 'tr', function() {
            // 单行选择
            if (me.singleSelect) {
                if ($(this).hasClass('selected')) {
                    $(this).removeClass('selected');
                } else {
                    me.grid.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            }
            // 多行选择
            else {
                $(this).toggleClass('selected');
            }
        });
    }

    /**
     * 行选择框选择事件
     */
    table.prototype.setRowCheckClick = function() {
        var me = this;
        // 表头选择框
        $(me.tableId + ' .checkall').on('click', function() {
            var check = $(this).prop("checked");
            $(me.tableId + ' .checkrow').prop("checked", check);
        });

        // 行选择框
        $(me.tableId + ' .checkrow').on('click', function() {
            var tr = $(this).parents('tr');
            tr.toggleClass('selected');
            //var $tmp = $('[name=checkList]:checkbox');
            //$('#checkAll').prop('checked', $tmp.length == $tmp.filter(':checked').length);
        });
    }

    /**
     * 表格行双击事件
     */
    table.prototype.setRowDoubleClick = function() {
        var me = this;
        $(me.tableId + ' tr').dblclick(function() {
            var data = me.grid.row(this).data();
            console.log(data);
        });
    }

    /**
     * 获取选择多行的数据
     */
    table.prototype.setRowHighlight = function() {
        $(this.tableId + ' tbody')
            .on('mouseover', 'td', function() {
                var colIdx = table.cell(this).index().column;
                if (colIdx !== lastIdx) {
                    $(table.cells().nodes()).removeClass('highlight');
                }
            })
            .on('mouseleave', function() {
                $(table.cells().nodes()).removeClass('highlight');
            });

    }

    /**
     * 获取选择单行的数据
     */
    table.prototype.getSelectedRowData = function() {
        if (this.grid.row('.selected').length > 0)
            return this.grid.row('.selected').data();
        return null;
    }

    /**
     * 获取选择多行的数据
     */
    table.prototype.getSelectedRowsData = function() {
        var datas = null;
        var rows = this.grid.rows('.selected').data();
        if (rows.length == 0)
            return datas;
        datas = [];
        for (var i = 0; i < rows.length; i++) {
            datas.push(rows[i]);
        }
        return datas;
    }


    /**
     * 设置行列格式化, 在create前调用
     * @param fnFormat 行格式化函数
     */
    table.prototype.setRowFormat = function(fnFormat) {
        this.fnFormat = fnFormat;
    }

    /**
     * 设置行编辑回调函数
     * @param fnEdit 行格式化函数
     */
    table.prototype.setRowAdd = function(fnAdd) {
        this.fnAdd = fnAdd;
    }

    /**
     * 设置行编辑回调函数
     * @param fnEdit 行格式化函数
     */
    table.prototype.setRowEdit = function(fnEdit) {
        this.fnEdit = fnEdit;
    }

    /**
     * 设置行编辑回调函数
     * @param fnEdit 行格式化函数
     */
    table.prototype.setRowDelete = function(fnDelete) {
        this.fnDelete = fnDelete;
    }

    /**
     * 添加行
     */
    table.prototype.add = function() {
        var addUrl = "";
        if (this.fnAdd)
            addUrl = this.fnAdd();
        if (typeof addUrl !== 'string' || addUrl.length < 1) {
            console.log("not set add url for table.");
            return;
        }

        layer.open({
            type: 2,
            title: '添加',
            fix: false,
            maxmin: true,
            shadeClose: true,
            area: this.formWidth,
            content: addUrl,
            success: function(layero, index) {
                //var body = layer.getChildFrame('body', index);
            },
            end: function() {
                //layer.tips('Hi', '#about', {tips: 1})
            }
        });
    }

    /**
     *编辑行
     */
    table.prototype.edit = function() {
        var editUrl = "";
        var data = this.getSelectedRowData();
        if (!data) {
            console.log("not select table row for edtion.");
            return;
        }
        if (this.fnEdit)
            editUrl = this.fnEdit(data);
        if (typeof editUrl !== 'string' || editUrl.length < 1) {
            console.log("not set delete url for table.");
            return;
        }

        layer.open({
            type: 2,
            title: '编辑',
            fix: false,
            maxmin: true,
            shadeClose: true,
            area: this.formWidth,
            content: editUrl,
            success: function(layero, index) {
                //var body = layer.getChildFrame('body', index);
            },
            end: function() {
                //layer.tips('Hi', '#about', {tips: 1})
            }
        });
    }

    /**
     * 删除行
     * @param name
     */
    table.prototype.del = function() {
        var me = this;
        var data = this.getSelectedRowData();
        if (!data) {
            console.log("not select table row for deletion.");
            return;
        }

        var delUrl = "";
        if (this.fnDelete)
            delUrl = this.fnDelete(data);
        if (typeof delUrl !== 'string' || delUrl.length < 1) {
            console.log("not set deletion url for table.");
            return;
        }

        $.ajax({
            url: delUrl,
            type: 'delete',
            dataType: "json",
            cache: "false",
            success: function(data) {
                if (data.status == 1) {
                    toastr.success("成功删除");
                    me.grid.api().row().remove().draw(false);
                    //me.grid.rows('.selected').remove().draw(false);
                } else {
                    toastr.error("删除失败");
                }
            },
            error: function(err) {
                toastr.error("Server Connection Error...");
            }
        });
    }

    /**
     * 刷新表格
     */
    table.prototype.refresh = function() {
        console.log("table refresh click");
        this.grid.ajax.reload();
    }

    /**
     * 搜索
     * @param name
     */
    table.prototype.search = function() {
        console.log("table search click");
        // 获取搜索数据
        var inputs = this.getSearchInputs();
        var params = [];
        if (inputs) {
            for (var i = 0; i < inputs.length; i++) {
                if (inputs[i].value)
                    params.push(inputs[i]);
            }
        }
        // 设置搜索参数
        this.grid.settings()[0].ajax.data = { 'extra': params };
        this.grid.ajax.reload();
    }

    /**
     * 行单元列格式化
     * @param row 行对象
     * @param data 行数据
     *
    table.prototype.fnFormat = function(row, data) {
        var v = null;
        if (data.id == "1") {
            $('td:eq(1)', row).html('<b>A</b>');
        }
    }
    */
    return table;

})();