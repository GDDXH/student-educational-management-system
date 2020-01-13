<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>教师|高校成绩管理系统</title>
    <link rel="shortcut icon" href="favicon.ico"/>
	<link rel="bookmark" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="easyui/css/default.css" />
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" />
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='easyui/js/outlook2.js'> </script>
    <script type="text/javascript">
	var _menus = 
	{
		"menus":
		[
			{
				"menuid":"1","icon":"","menuname":"信息查询",
				"menus":[			
							{"menuid":"11","menuname":"个人信息","icon":"icon-password","url":"TeacherServlet?method=TeacherPersonalView"},
							
							{"menuid":"12","menuname":"成绩录入","icon":"icon-find","url":"TeacherServlet?method=ScoreManageView"},					      
						]
			},
			{"menuid":"2","icon":"","menuname":"课程",
				"menus":[
						{"menuid":"21","menuname":"成绩查询","icon":"icon-note","url":"TeacherServlet?method=CourseScoreView"},
					]
			},
			{
				"menuid":"3","icon":"","menuname":"课表查询",
				"menus":[
							{"menuid":"21","menuname":"教学课表","icon":"icon-note","url":"TeacherServlet?method=CourseListView"},					
						]
			}
		]
	};
    </script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	<noscript>
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
		    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head"><span style="color:red; font-weight:bold;">${user.tname}&nbsp;</span>您好&nbsp;&nbsp;&nbsp;<a href="LoginServlet?method=LoginOut" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; ">高校成绩管理系统</span>
    </div>
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
	<div id="nav" class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
	</div>
	
    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" ></div>
    </div>
</body>
</html>