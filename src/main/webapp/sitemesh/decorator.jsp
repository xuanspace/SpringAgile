<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>XXX管理系统</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">  
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    
  <!-- 框架CSS -->
  <link rel="stylesheet" href="${ctx}/adminlte/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${ctx}/adminlte/css/font-awesome.min.css">
  <link rel="stylesheet" href="${ctx}/adminlte/css/ionicons.min.css">
  <link rel="stylesheet" href="${ctx}/adminlte/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${ctx}/adminlte/dist/css/skins/skin-blue.min.css">

  <!-- 自定义CSS -->
  <link rel="stylesheet" href="${ctx}/adminlte/css/content-tabs.css">
  <link rel="stylesheet" href="${ctx}/adminlte/css/content-iframe.css">  
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- 顶部头 -->
  <header class="main-header">
    <!-- 部头左边 -->
    <a href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>系统</b>LT</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>短信管理系统</b></span>
    </a>
    <!-- 部头右边 -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <!--span class="label label-success">4</span-->
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 4 messages</li>
              <li>
                <!-- inner menu: contains the messages -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                        <!-- User Image -->
                        <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                      </div>
                      <!-- Message title and timestamp -->
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <!-- The message -->
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <!-- end message -->
                </ul>
                <!-- /.menu -->
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>
          <!-- /.messages-menu -->

          <!-- Notifications Menu -->
          <li class="dropdown notifications-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <!-- span class="label label-warning">10</span-->
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- Inner Menu: contains the notifications -->
                <ul class="menu">
                  <li><!-- start notification -->
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <!-- end notification -->
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          <!-- Tasks Menu -->
          <li class="dropdown tasks-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <!--span class="label label-danger">9</span-->
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li>
                <!-- Inner menu: contains the tasks -->
                <ul class="menu">
                  <li><!-- Task item -->
                    <a href="#">
                      <!-- Task title and progress text -->
                      <h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
                      <!-- The progress bar -->
                      <div class="progress xs">
                        <!-- Change the css width attribute to simulate progress -->
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li>
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">Admin</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">Followers</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Sales</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Friends</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  
  <!-- 左边菜单栏  -->
  <aside class="main-sidebar">
    <section class="sidebar">
      <!-- 左边菜单用户显示区 -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>系统管理员</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- 左边菜单栏搜索框 -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="搜索...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- 左边菜单项选择区 -->
      <ul class="sidebar-menu">
        <li class="header">菜单</li>
        <!-- Optionally, you can add icons to the links -->
        <li class="active"><a class="menuItem" href="#" url="login.html"><i class="fa fa-link"></i> <span>用户</span></a></li>
        <li><a class="menuItem" href="#" url="register.html" data-id="F2DAD50B-95DF-48F7-8638-BA503B539143"><i class="fa fa-link"></i> <span>权限</span></a></li>
        <li class="treeview">
          <a class="menuItem" href="#" url="table10.html"><i class="fa fa-link"></i> <span>系统</span> <i class="fa fa-angle-left pull-right"></i></a>
          <ul class="treeview-menu">
            <li><a href="#">Link in level 2</a></li>
            <li><a href="#">Link in level 2</a></li>
          </ul>
        </li>
      </ul>      
    </section>    
  </aside>

  <!-- 内容区 -->
  <div class="content-wrapper">  
  	 <!-- 内容Tab选择区 -->
     <div class="content-tabs">
        <button class="roll-nav roll-left tabLeft">
            <i class="fa fa-backward"></i>
        </button>
        <nav class="page-tabs menuTabs">
            <div class="page-tabs-content" style="margin-left: 0px;">
                <a href="javascript:;" class="menuTab active" data-id="/Home/Default">欢迎首页</a>
            </div>
        </nav>
        <button class="roll-nav roll-right tabRight">
            <i class="fa fa-forward" style="margin-left: 3px;"></i>
        </button>
        <div class="btn-group roll-nav roll-right">
            <button class="dropdown tabClose" data-toggle="dropdown">页签操作
            	<i class="fa fa-caret-down" style="padding-left: 3px;"></i>
            </button>
            <ul class="dropdown-menu dropdown-menu-right">
                <li><a class="tabReload" href="javascript:void();">刷新当前</a></li>
                <li><a class="tabCloseCurrent" href="javascript:void();">关闭当前</a></li>
                <li><a class="tabCloseAll" href="javascript:void();">全部关闭</a></li>
                <li><a class="tabCloseOther" href="javascript:void();">除此之外全部关闭</a></li>
            </ul>
        </div>
        <button class="roll-nav roll-right fullscreen"><i class="fa fa-arrows-alt"></i></button>
    </div>
    
    <!-- 内容Iframe区 -->
    <div class="content-iframe" style="background-color: #f9f9f9; overflow: hidden;">
      <div class="mainContent" id="content-main" style="margin: 10px 10px 0px; padding: 0px; height: 874px;">
          <iframe class="NFine_iframe" width="100%" height="100%" src="./login.html" frameborder="0" data-id="/Home/Default" style="display: none;"></iframe>                            
      </div>
    </div>                            
  </div>

  <!-- 底部栏 -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
      Anything you want
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2015 <a href="#">Company</a>.</strong> All rights reserved.
  </footer>

  <!-- 右边控制栏 -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- 右边控制栏各Tab面板 -->
    <div class="tab-content">
      <!-- Tab面板 1 -->
      <div class="tab-pane active" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript::;">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>
              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>
                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
        </ul>        
        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript::;">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="label label-danger pull-right">70%</span>
              </h4>
              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
        </ul>
      </div>
      <!-- Tab面板2 -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>
            <p>
              Some information about this general settings option
            </p>
          </div>
        </form>
      </div>
    </div>
  </aside>
  
  <!-- 右边控制栏颜色 -->
  <div class="control-sidebar-bg"></div>
</div>

<!-- 框架JS-->
<script src="${ctx}/adminlte/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/adminlte/dist/js/app.min.js"></script>

<!-- 自定义JS -->
<script src="${ctx}/adminlte/dist/js/global.js"></script>
<script src="${ctx}/adminlte/dist/js/page.js"></script>
<script src="${ctx}/adminlte/dist/js/iframe.js"></script>
<script src="${ctx}/adminlte/dist/js/tabs.js"></script>
<script src="${ctx}/adminlte/dist/js/menu.js"></script>
<script src="${ctx}/adminlte/dist/js/table.js"></script>

</body>
</html>
