/**
 * AdminLTE Demo Menu ------------------ You should not use this file in
 * production. This file is for demo purposes only.
 */
(function($, AdminLTE) {

	"use strict";

	/**
	 * List of all the available skins
	 * 
	 * @type Array
	 */
	var my_skins = [ 
	     "skin-blue", 
	     "skin-black", 
	     "skin-red", 
	     "skin-yellow", 
	     "skin-purple", 
	     "skin-green",
	     "skin-blue-light", 
	     "skin-black-light", 
	     "skin-red-light", 
	     "skin-yellow-light", 
	     "skin-purple-light",
	     "skin-green-light" 
	];

	// Create the new tab
	var tab_pane = $("<div />", {
		"id" : "control-sidebar-theme-demo-options-tab",
		"class" : "tab-pane active"
	});

	// Create the tab button
	var tab_button = $("<li />", {"class" : "active"}).html("<a href='#control-sidebar-theme-demo-options-tab' data-toggle='tab'>"
			+ "<i class='fa fa-wrench'></i>"
		    + "</a>");
 
	// Add the tab button to the right sidebar tabs
	$("[href='#control-sidebar-home-tab']").parent().before(tab_button);

	// Create the menu
	var demo_settings = $("<div />");

	// Layout options
	demo_settings.append("<h4 class='control-sidebar-heading'>" + "Layout Options" + "</h4>"
	// Fixed layout
	+ getSidebarObj({
		title : "Fixed layout",
		data_prop : "data-layout='fixed'",
		text : "Activate the fixed layout. You can\'t use fixed and boxed layouts together"
	})
	// Boxed layout
	+ getSidebarObj({
		title : "Boxed layout",
		data_prop : "data-layout='layout-boxed'",
		text : "Activate the boxed layout"
	})
	// Sidebar Toggle
	+ getSidebarObj({
		title : "Toggle Sidebar",
		data_prop : "data-layout='sidebar-collapse'",
		text : "Toggle the left sidebar's state (open or collapse)"
	})
	// Sidebar mini expand on hover toggle
	+ getSidebarObj({
		title : "Sidebar Expand on Hover",
		data_prop : "data-enable='expandOnHover'",
		text : "Let the sidebar mini expand on hover"
	})
	// Control Sidebar Toggle
	+ getSidebarObj({
		title : "Toggle Right Sidebar Slide",
		data_prop : "data-controlsidebar='control-sidebar-open'",
		text : "Toggle between slide over content and push content effects"
	})
	// Control Sidebar Skin Toggle
	+ getSidebarObj({
		title : "Toggle Right Sidebar Skin",
		data_prop : "data-sidebarskin='toggle'",
		text : "Toggle between dark and light skins for the right sidebar"
	}));

	function getSidebarObj(obj) {
		var str = "<div class='form-group'><label class='control-sidebar-subheading'>" 
			    + "<input type='checkbox' " + obj.data_prop + " class='pull-right'/> "  
			    + obj.title + "</label>" 
			    + "<p>" + obj.text + "</p>"
				+ "</div>";
		return str;
	}

	var skins_list = $("<ul />", {
		"class" : 'list-unstyled clearfix'
	});

	// Dark sidebar skins
	var skin_blue = getSkinObj({
		data_skin : 'skin-blue',
		bg_1 : '#367fa9',
		cls_2 : 'bg-light-blue',
		bg_3 : '#222d32',
		bg_4 : '#f4f5f7',
		text : 'Blue'
	});
	skins_list.append(skin_blue);
	var skin_black = getSkinObj({
		data_skin : 'skin-black',
		bg_1 : '#fefefe',
		bg_2 : '#fefefe',
		bg_3 : '#222',
		bg_4 : '#f4f5f7',
		text : 'Black'
	});
	skins_list.append(skin_black);
	var skin_purple = getSkinObj({
		data_skin : 'skin-purple',
		cls_1 : 'bg-purple-active',
		cls_2 : 'bg-purple',
		bg_3 : '#222d32',
		bg_4 : '#f4f5f7',
		text : 'Purple'
	});
	skins_list.append(skin_purple);
	var skin_green = getSkinObj({
		data_skin : 'skin-green',
		cls_1 : 'bg-green-active',
		cls_2 : 'bg-green',
		bg_3 : '#222d32',
		bg_4 : '#f4f5f7',
		text : 'Green'
	});
	skins_list.append(skin_green);
	var skin_red = getSkinObj({
		data_skin : 'skin-red',
		cls_1 : 'bg-red-active',
		cls_2 : 'bg-red',
		bg_3 : '#222d32',
		bg_4 : '#f4f5f7',
		text : 'Red'
	});
	skins_list.append(skin_red);
	var skin_yellow = getSkinObj({
		data_skin : 'skin-yellow',
		cls_1 : 'bg-yellow-active',
		cls_2 : 'bg-yellow',
		bg_3 : '#222d32',
		bg_4 : '#f4f5f7',
		text : 'Yellow'
	});
	skins_list.append(skin_yellow);

	// Light sidebar skins
	var skin_blue_light = getSkinObj({
		data_skin : 'skin-blue-light',
		bg_1 : '#367fa9',
		cls_2 : 'bg-light-blue',
		bg_3 : '#f9fafc',
		bg_4 : '#f4f5f7',
		text : 'Blue Light'
	});
	skins_list.append(skin_blue_light);
	var skin_black_light = getSkinObj({
		data_skin : 'skin-black-light',
		bg_1 : '#fefefe',
		bg_2 : '#fefefe',
		bg_3 : '#f9fafc',
		bg_4 : '#f4f5f7',
		text : 'Black Light'
	});
	skins_list.append(skin_black_light);
	var skin_purple_light = getSkinObj({
		data_skin : 'skin-purple-light',
		cls_1 : 'bg-purple-active',
		cls_2 : 'bg-purple',
		bg_3 : '#f9fafc',
		bg_4 : '#f4f5f7',
		text : 'Purple Light'
	});
	skins_list.append(skin_purple_light);
	var skin_green_light = getSkinObj({
		data_skin : 'skin-green-light',
		cls_1 : 'bg-green-active',
		cls_2 : 'bg-green',
		bg_3 : '#f9fafc',
		bg_4 : '#f4f5f7',
		text : 'Green Light'
	});
	skins_list.append(skin_green_light);
	var skin_red_light = getSkinObj({
		data_skin : 'skin-red-light',
		cls_1 : 'bg-red-active',
		cls_2 : 'bg-red',
		bg_3 : '#f9fafc',
		bg_4 : '#f4f5f7',
		text : 'Red Light'
	});
	skins_list.append(skin_red_light);
	var skin_yellow_light = getSkinObj({
		data_skin : 'skin-yellow-light',
		cls_1 : 'bg-yellow-active',
		cls_2 : 'bg-yellow',
		bg_3 : '#f9fafc',
		bg_4 : '#f4f5f7',
		text : 'Yellow Light'
	});
	skins_list.append(skin_yellow_light);

	demo_settings.append("<h4 class='control-sidebar-heading'>Skins</h4>");
	demo_settings.append(skins_list);

	tab_pane.append(demo_settings);
	$("#control-sidebar-home-tab").after(tab_pane);

	setup();

	function getSkinObj(obj) {
		var skinObj = $("<li />", {	style : "float:left; width: 33.33333%; padding: 5px;"}).append(
						"<a href='javascript:void(0);' data-skin='"+ obj.data_skin
								+ "' style='display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)' class='clearfix full-opacity-hover'>"
								+ "<div>"
								+ "<span style='display:block; width: 20%; float: left; height: 7px;" + getBgCls(obj.bg_1, obj.cls_1) + "></span>"
								+ "<span style='display:block; width: 80%; float: left; height: 7px;" + getBgCls(obj.bg_2, obj.cls_2) + "></span>" + "</div>" + "<div>"
								+ "<span style='display:block; width: 20%; float: left; height: 20px;"+ getBgCls(obj.bg_3, obj.cls_3) + "></span>"
								+ "<span style='display:block; width: 80%; float: left; height: 20px;"+ getBgCls(obj.bg_4, obj.cls_4) + "></span>" + "</div>" + "</a>"
								+ "<p class='text-center no-margin'>" + obj.text + "</p>");
		if (obj.data_skin.indexOf("-light") > -1)
			skinObj.find("p.text-center").css("font-size", "12px");
		return skinObj;
	}

	function getBgCls(bg, cls) {
		var str = "";
		if (bg)
			str += "background:" + bg;
		if (cls)
			str += "' class='" + cls + "'";
		else
			str += "'";
		return str;
	}

	/**
	 * Toggles layout classes
	 * 
	 * @param String
	 *            cls the layout class to toggle
	 * @returns void
	 */
	function change_layout(cls) {
		$("body").toggleClass(cls);
		AdminLTE.layout.fixSidebar();
		// Fix the problem with right sidebar and layout boxed
		if (cls == "layout-boxed")
			AdminLTE.controlSidebar._fix($(".control-sidebar-bg"));
		if ($('body').hasClass('fixed') && cls == 'fixed') {
			AdminLTE.pushMenu.expandOnHover();
			AdminLTE.layout.activate();
		}
		AdminLTE.controlSidebar._fix($(".control-sidebar-bg"));
		AdminLTE.controlSidebar._fix($(".control-sidebar"));
	}

	/**
	 * Replaces the old skin with the new skin
	 * 
	 * @param String
	 *            cls the new skin class
	 * @returns Boolean false to prevent link's default action
	 */
	function change_skin(cls) {
		$.each(my_skins, function(i) {
			$("body").removeClass(my_skins[i]);
		});

		$("body").addClass(cls);
		store('skin', cls);
		return false;
	}

	/**
	 * Store a new settings in the browser
	 * 
	 * @param String
	 *            name Name of the setting
	 * @param String
	 *            val Value of the setting
	 * @returns void
	 */
	function store(name, val) {
		if (typeof (Storage) !== "undefined") {
			localStorage.setItem(name, val);
		} else {
			window.alert('Please use a modern browser to properly view this template!');
		}
	}

	/**
	 * Get a prestored setting
	 * 
	 * @param String
	 *            name Name of of the setting
	 * @returns String The value of the setting | null
	 */
	function get(name) {
		if (typeof (Storage) !== "undefined") {
			return localStorage.getItem(name);
		} else {
			window.alert('Please use a modern browser to properly view this template!');
		}
	}

	/**
	 * Retrieve default settings and apply them to the template
	 * 
	 * @returns void
	 */
	function setup() {
		var tmp = get('skin');
		if (tmp && $.inArray(tmp, my_skins))
			change_skin(tmp);

		// Add the change skin listener
		$("[data-skin]").on('click', function(e) {
			e.preventDefault();
			change_skin($(this).data('skin'));
		});

		// Add the layout manager
		$("[data-layout]").on('click', function() {
			change_layout($(this).data('layout'));
		});

		$("[data-controlsidebar]").on('click', function() {
			change_layout($(this).data('controlsidebar'));
			var slide = !AdminLTE.options.controlSidebarOptions.slide;
			AdminLTE.options.controlSidebarOptions.slide = slide;
			if (!slide)
				$('.control-sidebar').removeClass('control-sidebar-open');
		});

		$("[data-sidebarskin='toggle']").on('click', function() {
			var sidebar = $(".control-sidebar");
			if (sidebar.hasClass("control-sidebar-dark")) {
				sidebar.removeClass("control-sidebar-dark")
				sidebar.addClass("control-sidebar-light")
			} else {
				sidebar.removeClass("control-sidebar-light")
				sidebar.addClass("control-sidebar-dark")
			}
		});

		$("[data-enable='expandOnHover']").on('click', function() {
			$(this).attr('disabled', true);
			AdminLTE.pushMenu.expandOnHover();
			if (!$('body').hasClass('sidebar-collapse'))
				$("[data-layout='sidebar-collapse']").click();
		});

		// Reset options
		if ($('body').hasClass('fixed')) {
			$("[data-layout='fixed']").attr('checked', 'checked');
		}
		if ($('body').hasClass('layout-boxed')) {
			$("[data-layout='layout-boxed']").attr('checked', 'checked');
		}
		if ($('body').hasClass('sidebar-collapse')) {
			$("[data-layout='sidebar-collapse']").attr('checked', 'checked');
		}

	}
})(jQuery, $.AdminLTE);
