<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>教师列表</title>
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
	        title:'课程列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"AdminServlet?method=courseList",
	        idField:'tno', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        sortName:'cno',
	        sortOrder:'ASC', 
	        remoteSort: false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},		       
 		        {field:'cno',title:'课程编号',width:150},    
 		        {field:'cname',title:'姓名',width:100},
 		        {field:'ctime',title:'学时',width:100},
 		        {field:'credit',title:'学分',width:50},  
		        {field:'ctest',title:'考核形式',width:50},
 		        {field:'cterm',title:'学期',width:100},
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
	    //修改
	    $("#add").click(function(){
	    	$("#addDialog").dialog("open");
	    });
	    $("#edit").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	  	//删除
	    $("#delete").click(function(){
	    	var selectRow = $("#dataList").datagrid("getSelected");
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var courseid = selectRow.cno;
            	$.messager.confirm("消息提醒", "将删除与课程相关的所有数据，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "AdminServlet?method=deleteCourse",
							data: {courseid: courseid},
							success: function(msg){
								if(msg == "success"){
									$.messager.alert("消息提醒","删除成功!","info");
									//刷新表格
									$("#dataList").datagrid("reload");
									$("#dataList").datagrid("uncheckAll");
								} else{
									$.messager.alert("消息提醒","删除失败!","warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });
	  	//设置添加
	    $("#addDialog").dialog({
	    	title: "添加课程",
	    	width: 650,
	    	height: 460,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'添加',
					plain: true,
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#addForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							$.ajax({
								type: "post",
								url: "AdminServlet?method=addCourse",
								data: $("#addForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","添加成功!","info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_cno").textbox('setValue', "");
										$("#add_cname").textbox('setValue', "");
										$("#add_ctime").textbox('setValue', "");
										$("#add_credit").textbox('setValue', "");
										$("#add_ctest").textbox('setValue', "");
										$("#add_cterm").textbox('setValue', "");
										//重新刷新页面数据
							  			$('#dataList').datagrid("reload");	
							  			$("#dataList").datagrid("uncheckAll");
									} else{
										$.messager.alert("消息提醒","添加失败!","warning");
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
						$("#add_cno").textbox('setValue', "");
						$("#add_cname").textbox('setValue', "");
						$("#add_ctime").textbox('setValue', "");
						$("#add_credit").textbox('setValue', "");
						$("#add_ctest").textbox('setValue', "");
						$("#add_cterm").textbox('setValue', "");	
					}
				},
			]
	    });

		//设置编辑
	    $("#editDialog").dialog({
	    	title: "课程信息",
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
								url: "AdminServlet?method=editCourse",
								data: $("#editForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","更新成功!","info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//刷新表格
										$("#dataList").datagrid("reload");
										$("#dataList").datagrid("uncheckAll");						  			
									} else{
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
						$("#edit_cno").textbox('setValue', selectRow.cno);
						$("#edit_cname").textbox('setValue', selectRow.cname);
						$("#edit_ctime").textbox('setValue', selectRow.ctime);
						$("#edit_credit").textbox('setValue', selectRow.credit);
						$("#edit_ctest").textbox('setValue', selectRow.ctest);
						$("#edit_cterm").textbox('setValue', selectRow.cterm);	
					}
				}
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#edit_cno").textbox('setValue', selectRow.cno);
				$("#edit_cname").textbox('setValue', selectRow.cname);
				$("#edit_ctime").textbox('setValue', selectRow.ctime);
				$("#edit_credit").textbox('setValue', selectRow.credit);
				$("#edit_ctest").textbox('setValue', selectRow.ctest);
				$("#edit_cterm").textbox('setValue', selectRow.cterm);	
			}
	    });
	});
	</script>
</head>
<body>
	<!-- 课程列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
	</div>
	<div id="addDialog" style="padding: 10px">
    	<form id="addForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>编号:</td>
	    			<td><input id="add_cno" class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="cno"  data-options="required:true,missingMessage:'请输入编号'" /></td>
	    		</tr>
	    		<tr>
	    			<td>课程名:</td>
	    			<td><input id="add_cname" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="cname" data-options="required:true, missingMessage:'请填写课程名'" /></td>
	    		</tr>
	    		<tr>
	    			<td>学时:</td>
	    			<td><input id="add_ctime" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="ctime" /></td>
	    		</tr>
	    		<tr>
	    			<td>学分:</td>
	    			<td><input id="add_credit" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="credit"/></td>
	    		</tr>
	    		<tr>
	    			<td>考核形式:</td>
	    			<td><input id="add_ctest" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="ctest"/></td>
	    		</tr>  
	    		<tr>
	    			<td>学期:</td>
	    			<td><input id="add_cterm" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="cterm"/></td>
	    		</tr>    
	    	</table>
	    </form>
	</div>

	<div id="editDialog" style="padding: 10px">
    	<form id="editForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>编号:</td>
	    			<td><input id="edit_cno" class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="cno" readonly="readonly" data-options="required:true,missingMessage:'请输入编号'" /></td>
	    		</tr>
	    		<tr>
	    			<td>课程名:</td>
	    			<td><input id="edit_cname" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="cname" data-options="required:true, missingMessage:'请填写课程名'" /></td>
	    		</tr>
	    		<tr>
	    			<td>学时:</td>
	    			<td><input id="edit_ctime" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="ctime" /></td>
	    		</tr>
	    		<tr>
	    			<td>学分:</td>
	    			<td><input id="edit_credit" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="credit"/></td>
	    		</tr>
	    		<tr>
	    			<td>考核形式:</td>
	    			<td><input id="edit_ctest" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="ctest"/></td>
	    		</tr>  
	    		<tr>
	    			<td>学期:</td>
	    			<td><input id="edit_cterm" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="cterm"/></td>
	    		</tr>    
	    	</table>
	    </form>
	</div>	
</body>
</html>