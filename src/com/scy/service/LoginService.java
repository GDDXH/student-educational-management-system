package com.scy.service;

import java.util.List;
import com.scy.bean.Admin;
import com.scy.bean.Student;
import com.scy.bean.Teacher;
import com.scy.dao.LoginDao;

public class LoginService {
	private LoginDao loginDao = new LoginDao();
	public Student LoginStudent(String account,String password) {
		 List<Student>list = loginDao.getStudent(account,password);
		 if(list == null || list.size() != 1)
			 return null;
		 else {
			 Student student  = list.get(0);
			 return student;
		 }	
	}
	public Teacher LoginTeacher(String account,String password) {
		 List<Teacher>list = loginDao.getTeacher(account,password);
		 if(list == null || list.size() != 1)
			 return null;
		 else {
			 Teacher teacher  = list.get(0);
			 return teacher;
		 }	
	}
	public Admin LoginAdmin(String account,String password) {
		 List<Admin>list = loginDao.getAdmin(account,password);
		 if(list == null || list.size() != 1)
			 return null;
		 else {
			 Admin admin  = list.get(0);
			 return admin;
		 }	
		
	}
}
