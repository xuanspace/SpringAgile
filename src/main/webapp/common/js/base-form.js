/**
 *  add by billJiang 2016/10/9
 *  form 表单数据回填/获取/重置
 */
(function($, window, document, undefined) {
	'use strict';

	var pluginName = 'form';

	var BaseForm=function(element,options){
		this.$element=$(element);
		this.initFormComponent();
	}

	//初始化
	BaseForm.prototype.initFormComponent=function() {
		var form = this.$element;
		form = form.length == 0 ? $('form[name="' + form_flag + '"]') : form;
		//给form表单增加BaseEntity中的属性
		if (form.find('[name="deleted"]').length == 0) {
			form.prepend("<input type='hidden' name='deleted'>");
		}
		if (form.find(':hidden[name="createDateTime"]').length == 0) {
			form.prepend('<input type="hidden" name="createDateTime" data-flag="date" data-format="yyyy-MM-dd HH:mm:ss">');
		}
		if (form.find(':hidden[name="version"]').length == 0) {
			form.prepend("<input type='hidden' name='version'>");
		}
		if (form.find(':hidden[name="id"]').length == 0) {
			form.prepend("<input type='hidden' id='id' name='id'>");
		}
		// icheck
		if (form.find('[data-flag="icheck"]').length > 0) {
			form.find('[data-flag="icheck"]').iCheck({
				checkboxClass: 'icheckbox_square-green',
				radioClass: 'iradio_square-green'
			}).on('ifChanged', function (e) {
				// Get the field name
				var field = $(this).attr('name');
				form
				// Mark the field as not validated
					.bootstrapValidator('updateStatus', field, 'NOT_VALIDATED')
					// Validate field
					.bootstrapValidator('validateField', field);
			});
		}
		// datepicker
		if (form.find('[data-flag="datepicker"]').length > 0) {
			form.find('[data-flag="datepicker"]').datepicker({
				autoclose: true,
				format: 'yyyy-mm-dd',
				language: 'cn'
			}).on('change', function (e) {
				// Validate the date when user change it
				var field = $(this).attr('name');
				// Get the bootstrapValidator instance
				form.data('bootstrapValidator')
				// Mark the field as not validated, so it'll be re-validated when the
				// user change date
					.updateStatus(field, 'NOT_VALIDATED', null)
					// Validate the field
					.validateField(field);
			}).parent().css("padding-left", "15px").css("padding-right", "15px");
		}
	}

	/**
	 * 获取表单数据
	 */
	BaseForm.prototype.getFormSimpleData=function () {
		var datas = {};
		var form = this.$element;
		if (form.length == 0)
			return datas;
		var elems = form.find('input[name], select[name], textarea[name]');

		// 设置datas属性
		elems.each(function (ind, elem) {
			var el_name = elem.name;
			if (!el_name)
				return;
			var assembly = function (name) {
				var res = {}, sind = name.indexOf('.');
				res[sind > -1 ? name.substring(0, sind) : name] = sind > -1 ? assembly(name.substring(sind + 1)) : '';
				return res;
			};
			var ind = el_name.indexOf('.');
			datas[ind > -1 ? el_name.substring(0, ind) : el_name] = ind > -1 ? assembly(el_name.substring(ind + 1)) : '';
		});

		// 设置datas属性值
		elems.each(function (ind, elem) {
			var el_name = elem.name, is_radio = elem.type == 'radio', is_ckbox = elem.type == 'checkbox';
			if (!el_name || ((is_radio || is_ckbox) && !elem.checked))
				return;
			var old_val = eval('datas.' + el_name); // checkbox值用逗号分割
			eval('datas.' + el_name + '="' + (is_ckbox ? (old_val ? (old_val + ',') : '') : '') + elem.value + '"');
		});
		return datas;
	}

	/**
	 * 	表单数据回填
	 * @param json_data 回填的数据
	 */
	BaseForm.prototype.initFormData=function(json_data) {
		if (!json_data)
			return;
		var form =this.$element;
		if (form.length == 0)
			return;
		form.find('input[name], select[name], textarea[name], label[name]').each(function(ind, elem) {
			var obj = $(elem), el_name = obj.attr('name'), value;
			try {
				value = eval('json_data.' + el_name);
			} catch (e) {
				value = null;
			}
			if (value != undefined && value != null && $.trim(value) != '') {
				var is_radio = elem.type == 'radio', is_ckbox = elem.type == 'checkbox';
				var is_date=$(elem).data("flag")=="datepicker"||$(elem).data("flag")=="date";
				var date_format=$(elem).data("format")||"yyyy-MM-dd";
				if(is_date)
					value=formatDate(value,date_format);
				if (is_radio) {
					//icheck
					if($(elem).data("flag")=="icheck"){
						$(elem).iCheck( elem.value == value?'check':'uncheck');
						form.data('bootstrapValidator').updateStatus(el_name, 'NOT_VALIDATED', null);
					}else{
						//原生radio
						elem.checked = elem.value == value;
					}
				} else if (is_ckbox) {
					//icheck
					if($(elem).data("flag")=="icheck"){
						$(elem).iCheck($.inArray(elem.value, value.split(',')) > -1?'check':'uncheck');
						form.data('bootstrapValidator').updateStatus(el_name, 'NOT_VALIDATED', null);
					}else{
						//原生checkbox
						elem.checked = $.inArray(elem.value, value.split(',')) > -1 ? true : false;
					}
				} else if (elem.tagName.toUpperCase() == 'LABEL') {
					elem.innerText = value;
				} else {
					elem.value = value;
				}
			}
		});
	}

	/**
	 * 表单重置
	 */
	BaseForm.prototype.clearForm=function() {
		if (this.$element.length>0) {
			var form =this.$element;
			form.find(':input[name]:not(:radio)').val('');
			form.find(':radio').attr('checked', false);
			form.find(':radio[data-flag]').iCheck('update');
			form.find(':checkbox').attr('checked', false);
			form.find(':checkbox[data-flag]').iCheck('update');
			form.find('label[name]').text('');
			if(form.data('bootstrapValidator'))
				form.data('bootstrapValidator').resetForm();
		} else {
			$(':input[name]:not(:radio)').val('');
			$(':radio').removeAttr('checked');
			$(':radio[data-flag]').iCheck('update');
			$(':checkbox').removeAttr('checked');
			$(':checkbox[data-flag]').iCheck('update');
			$('label[name]').text('');
		}
	}


	$.fn[pluginName] = function(options, args) {
		if(this==null)
			return null;
		return new BaseForm(this, $.extend(true, {},options));
	};

})(jQuery, window, document);


