<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>学生列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'学生列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"TeacherServlet?method=studentList",
	        idField:'scy_sno', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        sortOrder:'Asc', 
	        remoteSort: false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},
 		        {field:'scy_sno',title:'学号',width:150, sortable: true},    
 		        {field:'scy_sname',title:'姓名',width:100,sortable: true},    
 		        {field:'scy_cname',title:'课程',width:200,sortable: true},
 		        {field:'scy_cterm',title:'学期',width:100,sortable: true},
 		        {field:'scy_report',title:'分数',width:150},    
 		        {field:'scy_credit',title:'学分',width:150},  
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
	    //修改
	    $("#edit").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	  	
	  	//设置编辑学生窗口
	    $("#editDialog").dialog({
	    	title: "修改学生信息",
	    	width: 650,
	    	height: 460,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'提交',
					plain: true,
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							$.ajax({
								type: "post",
								url: "TeacherServlet?method=editStudentScore",
								data: $("#editForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","更新成功!","info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//刷新表格
										$("#dataList").datagrid("reload");
										$("#dataList").datagrid("uncheckAll");			  			
									} 
									else{
										$.messager.alert("消息提醒","更新失败!","warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-reload',
					handler:function(){
						//清空表单
						$("#edit_sno").textbox('setValue', selectRow.scy_sno);
						$("#edit_sname").textbox('setValue', selectRow.scy_sname);
						$("#edit_cname").textbox('setValue', selectRow.scy_cname);
						$("#edit_cterm").textbox('setValue', selectRow.scy_cterm);
						$("#edit_report").textbox('setValue', selectRow.scy_report);
						$("#edit_credit").textbox('setValue', selectRow.scy_credit);
					}
				}
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#edit_sno").textbox('setValue', selectRow.scy_sno);
				$("#edit_sname").textbox('setValue', selectRow.scy_sname);
				$("#edit_cname").textbox('setValue', selectRow.scy_cname);
				$("#edit_cterm").textbox('setValue', selectRow.scy_cterm);
				$("#edit_report").textbox('setValue', selectRow.scy_report);	
				$("#edit_credit").textbox('setValue', selectRow.scy_credit);	
			}
	    });
	   
	});
	</script>
</head>
<body>
	<!-- 学生列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left; margin: 0 10px 0 10px">学期：<input id="gradeList" class="easyui-textbox" name="grade" /></div>
		<div style="margin-left: 10px;">课程：<input id="clazzList" class="easyui-textbox" name="clazz" /></div>
	
	</div>
	
	
	<!-- 修改学生窗口 -->
	<div id="editDialog" style="padding: 10px"> 
    	<form id="editForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>学号:</td>
	    			<td><input id="edit_sno" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="scy_sno" data-options="required:true, missingMessage:'请填写学号'"  readonly="readonly"/></td>
	    		</tr>
	    		<tr>
	    			<td>姓名:</td>
	    			<td><input id="edit_sname" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="scy_sname" data-options="required:true, missingMessage:'请填写姓名'" readonly="readonly"/></td>
	    		</tr>
	    		<tr>
	    			<td>课程:</td>
	    			<td><input id="edit_cname" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="scy_cname" readonly="readonly" /></td>
	    		</tr>
	    		<tr>
	    			<td>学期:</td>
	    			<td><input id="edit_cterm" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="scy_cterm"  readonly="readonly" /></td>
	    		</tr>
	    		<tr>
	    			<td>分数:</td>
	    			<td><input id="edit_report" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="scy_report"/></td>
	    		</tr>
	    		<tr>
	    			<td>学分:</td>
	    			<td><input id="edit_credit" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="scy_credit" readonly="readonly" /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</body>
</html>