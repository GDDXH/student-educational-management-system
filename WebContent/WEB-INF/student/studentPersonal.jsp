<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>个人信息</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		
		//修改密码窗口
	    $("#passwordDialog").dialog({
	    	title: "修改密码",
	    	width: 500,
	    	height: 300,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	  	    		{
	  					text:'提交',
	  					iconCls:'icon-user_add',
	  					handler:function(){
	  						var validate = $("#editPassword").form("validate");
	  						if(!validate){
	  							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
	  							return;
	  						} else{
	  							$.ajax({
	  								type: "post",
	  								url: "StudentServlet?method=editPassword",
	  								data: $("#editPassword").serialize(),
	  								success: function(msg){
	  									if(msg == "success"){
	  										$.messager.alert("消息提醒","修改成功，将重新登录","info")
	  										setTimeout(function(){
	  											top.location.href = "LoginServlet?method=LoginOut";
	  										}, 1000);
	  									}
	  								}
	  							});
	  						}
	  					}
	  				},
	  				{
	  					text:'重置',
	  					iconCls:'icon-reload',
	  					handler:function(){
	  						//清空表单
	  						$("#old_password").textbox('setValue', "");
	  						$("#new_password").textbox('setValue', "");
	  						$("#re_password").textbox('setValue', "");
	  					}
	  				}
	  			],
	    })
		
		//设置编辑学生窗口
	    $("#editDialog").dialog({
	    	title: "修改密码",
	    	width: 500,
	    	height: 400,
	    	fit: true,
	    	modal: false,
	    	noheader: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: false,
	    	toolbar: [
				{
					text:'修改密码',
					plain: true,
					iconCls:'icon-password',
					handler:function(){
						$("#passwordDialog").dialog("open");
					}
				}
			],
			
	    });
	})
	</script>
</head>
<body>
	<!--学生信息窗口-->
	<div id="editDialog" style="padding: 20px">     
    	<form id="editForm">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>学号:</td>
	    			<td><input id="edit_number" value="${user.sno}" data-options="readonly: true" class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="number" disabled="disabled"/></td>
	    		</tr>
	    		<tr>
	    			<td>姓名:</td>
	    			<td><input id="edit_name" value="${user.sname}" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name" data-options="required:true, missingMessage:'请填写姓名'" disabled="disabled"/></td>
	    		</tr>
	    		<tr>
	    			<td>性别:</td>
	    			<td><select id="edit_sex" class="easyui-combobox" data-options="editable: false, panelHeight: 50, width: 60, height: 30" name="sex" disabled="disabled"><option ${user.ssex == 'M'? 'selected':''} value="男">男</option><option ${user.ssex == 'F'? 'selected':''} value="女">女</option></select></td>
	    		</tr>
	    		<tr>
	    			<td>地址:</td>
	    			<td><input id="edit_address"  value="${user.saddress}" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="address" disabled="disabled"/></td>
	    		</tr>
	    		<tr>
	    			<td>年龄:</td>
	    			<td><input id="edit_age"  value="${user.sage}" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="age" validType="number" disabled="disabled"/></td>
	    		</tr>
	    		<tr>
	    			<td>入学年份:</td>
	    			<td><input id="edit_year"  value="${user.syear}" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="year" validType="number" disabled="disabled"/></td>
	    		</tr>
	    		<tr>
	    			<td>学分:</td>
	    			<td><input id="edit_credit"  value="${user.scredit}" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="credit" validType="number" disabled="disabled"/></td>
	    		</tr>
	    		<tr>
	    			<td>年级:</td>
	    			<td><input id="edit_grade"  value="${user.mname }" style="width: 200px; height: 30px;" class="easyui-textbox" data-options="readonly: true" disabled="disabled"/></td>
	    		</tr>
	    		<tr>
	    			<td>班级:</td>
	    			<td><input id="edit_class"  value="${user.clname }" style="width: 200px; height: 30px;" class="easyui-textbox" data-options="readonly: true" disabled="disabled"/></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	<!-- 修改密码窗口 -->
	<div id="passwordDialog" style="padding: 20px">
    	<form id="editPassword">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>原密码:</td>
	    			<td><input id="old_password" style ="width: 200px; height: 30px;" class="easyui-textbox" type="password" validType="oldPassword[${user.spassword}]"  data-options="required:true, missingMessage:'请输入原密码'" /></td>
	    		</tr>
	    		<tr>
	    			<td>新密码:</td>
	    			<td>
	    				<input  type="hidden" name="account" value="${user.sno}" />
	    				<input id="new_password" style="width: 200px; height: 30px;" class="easyui-textbox" type="password" validType="password" name="password" data-options="required:true, missingMessage:'请输入新密码'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>新密码:</td>
	    			<td><input id="re_password" style="width: 200px; height: 30px;" class="easyui-textbox" type="password" validType="equals['#new_password']"  data-options="required:true, missingMessage:'再次输入密码'" /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>  
</body>
</html>