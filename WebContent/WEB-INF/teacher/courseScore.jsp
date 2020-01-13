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
		//验证只能为数字
		function scoreBlur(score){
			if(!/^[1-9]\d*$/.test($(score).val())){
				$(score).val("");
			}
		}
	$(function() {	
		
		
		var table;
		
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'课程列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible: false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"TeacherServlet?method=courseList",
	        idField:'id', 
	        singleSelect: true,//是否单选 
	        pagination: false,//分页控件 
	        rownumbers: true,//行号 
	        remoteSort: false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},
 		        {field:'scy_cno',title:'课程编号',width:50, sortable: true},    
 		        {field:'scy_cname',title:'课程名',width:200, sortable: true},    
 		        {field:'scy_cterm',title:'开课学期',width:150},
 		        {field:'scy_ctest',title:'考核形式',width:100},    
	 		]], 
	        toolbar: "#toolbar"
	    }); 	

	    //成绩统计
	   $("#escore").click(function(){
	    	var exam = $("#dataList").datagrid("getSelected");	    	
        	if(exam == null){
            	$.messager.alert("消息提醒", "请选择课程!", "warning");
            } else{           	            		
               		setTimeout(function(){               		
                		var data = {cno:exam.scy_cno};              
    			    	setTimeout(function(){
    				    	$("#escoreList").datagrid("options").url = "TeacherServlet?method=scoreList";
    				    	$("#escoreList").datagrid("options").queryParams = data;
    				    	$("#escoreList").datagrid("reload");
    			    	}, 30)	
    			    	setTimeout(function(){
    				    	$("#escoreListDialog").dialog("open");
    			    	}, 80)
    			    	
                	}, 100);
               		
            	}

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
	    });
	  	//成绩列表
	    $("#escoreList, #regEscoreList").datagrid({ 
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
   				{field:'scy_sno',title:'学号',width:120,resizable: false},    
   				{field:'scy_sname',title:'姓名',width:120,resizable: false},        
   				{field:'scy_report',title:'成绩',width:120,resizable: false},
   			]],
   	    });
	  	
	    $("#escoreList").datagrid({ 
   	        toolbar: "#escoreToolbar",
   	    });
	    
	    $("#regEscoreList").datagrid({ 
   	        toolbar: "#regEscoreToolbar",
   	    });
	  	
	  	//考试成绩窗口
	    $("#regEscoreDialog").dialog({
	    	title: "成绩登记",
	    	width: 850,
	    	height: 550,
	    	iconCls: "icon-vcard-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    
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
		<div><a id="escore" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-chart_bar',plain:true">成绩统计</a></div>
	</div>
	
	<!-- 考试成绩表 -->
	<div id="escoreListDialog">
		<table id="escoreList" cellspacing="0" cellpadding="0"> 
	    
		</table> 
	</div>	
</body>
</html>