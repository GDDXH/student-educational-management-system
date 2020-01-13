<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>学期列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/themes/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	$(function() {	
		
		var table;		
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'学期列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible: false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"StudentServlet?method=termList",
	        idField:'id', 
	        singleSelect: true,//是否单选 
	        pagination: false,//分页控件 
	        rownumbers: true,//行号 
	        remoteSort: false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},
			    {field:'termname',title:'学期',width:200},    		       
	 		]], 
	 		toolbar: [
	        	{
	        		text: '查看课表',
	        		iconCls: 'icon-zoom-in',
	        		handler: function(){
	                	var exam = $("#dataList").datagrid("getSelected");
	        	    	
	                	if(exam == null){
	                    	$.messager.alert("消息提醒", "请选择考试查看!", "warning");
	                    } else{
	                    	var data = {termname: exam.termname};
	                    	//动态显示该次考试的科目	                    	
	                    	setTimeout(function(){
	        			    	$("#escoreList").datagrid("options").url = "StudentServlet?method=courseList";
	        			    	$("#escoreList").datagrid("options").queryParams = data;
	        			    	$("#escoreList").datagrid("reload");
	        			    	$("#escoreListDialog").dialog("open");
	                    	}, 100)	        		    	
	        	    	}	                	                	
	                	$("#escoreDialog").dialog("open");
	        		}
	        	}          
	        ]
	    }); 
	  	
	  	//课程窗口
	    $("#escoreDialog").dialog({
	    	title: "课程",
	    	width: 900,
	    	height: 550,
	    	iconCls: "icon-chart_bar",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    });
	  	
	  	//课程列表
	    $("#escoreList").datagrid({ 
			border: true, 
	        collapsible: false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        noheader: true,
	        singleSelect: true,//是否单选 
	        rownumbers: true,//行号 
	     	sortOrder:'DESC', 
	        remoteSort: false,
	        frozenColumns: [[  
				{field:'scy_cname',title:'课程',width:120,resizable: false,sortable: false},    
				{field:'scy_ctest',title:'考核方式',width:120,resizable: false},    
				{field:'scy_tname',title:'教师',width:120,resizable: false},    
	        ]],
		    });
	  	
	});
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"></table> 
	<!-- 课程表 -->
	<div id="escoreDialog">
		<table id="escoreList" cellspacing="0" cellpadding="0"></table> 
	</div>
</body>
</html>