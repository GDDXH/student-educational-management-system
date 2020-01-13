<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>考试列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/themes/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	$(function() {	
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
	        toolbar: "#toolbar"
	    }); 
	    //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    }); 
	    //设置工具类按钮
	    $("#add").click(function(){
	    	$("#addDialog").dialog("open");
	    });
	    
	    //成绩统计
	    $("#escore").click(function(){
	    	var exam = $("#dataList").datagrid("getSelected");
	    	
        	if(exam == null){
            	$.messager.alert("消息提醒", "请选择学期查看!", "warning");
            } else{
            	var data = {termname: exam.termname};                    	
            	setTimeout(function(){
			    	$("#escoreList").datagrid("options").url = "AdminServlet?method=scoreList";
			    	$("#escoreList").datagrid("options").queryParams = data;
			    	$("#escoreList").datagrid("reload");
			    	$("#escoreListDialog").dialog("open");
            	}, 100)	        		    	
	    	}	                	                	
        	$("#escoreDialog").dialog("open");
	    });

	  	//考试成绩窗口
	    $("#escoreListDialog").dialog({
	    	title: "成绩统计",
	    	width: 850,
	    	height: 550,
	    	iconCls: "icon-chart_bar",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	onClose: function(){
   	        	$("#escoreClazzList").combobox("clear");
   	        }
	    });
	  	//成绩列表
	    $('#escoreList').datagrid({ 
   	        border: true, 
   	        collapsible: false,//是否可折叠的 
   	        fit: true,//自动大小 
   	        method: "post",
   	        noheader: true,
   	        singleSelect: true,//是否单选 
   	        rownumbers: true,//行号 
   	        sortName:"scy_report",
   	        sortOrder:'DESC', 
   	        remoteSort: false,
   	        toolbar: "#escoreToolbar",
   	        frozenColumns: [[  
   				{field:'scy_sno',title:'学号',width:120,resizable: false,sortable: false},    
   				{field:'scy_sname',title:'姓名',width:120,resizable: false},    
   				{field:'scy_report',title:'绩点',width:120,resizable: false,sortable: true}, 
   	        ]],
   	    }); 
	  	 
	});
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="escore" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-chart_bar',plain:true">成绩统计</a></div>
	</div>
	
	<!-- 考试成绩表 -->
	<div id="escoreListDialog">
		<table id="escoreList" cellspacing="0" cellpadding="0"> 
	    
		</table> 
	</div>
</body>
</html>