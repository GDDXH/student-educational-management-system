package com.scy.service;


import java.util.List;

import com.scy.bean.ClassCourse;
import com.scy.bean.Semester;
import com.scy.bean.Student;
import com.scy.bean.StudentCourseReport;
import com.scy.dao.StudentDao;

import net.sf.json.JSONArray;

public class StudentService {
	private StudentDao studentDao = new StudentDao();
	public void editPassword(String account,String password) {
		studentDao.update("UPDATE Student as s SET s.spassword="+password+" WHERE s.sno="+account);
	}

	public String termList() {
		List<Semester> list = studentDao.getTermname();		
		//以json格式返回数据
        String result = JSONArray.fromObject(list).toString();	
		return result;
	}

	public String getScoreList(String sno,String termname) {
		List<StudentCourseReport> list = studentDao.getScoreList(sno,termname);
        //以json格式返回数据
        String result = JSONArray.fromObject(list).toString();
		return result;
	}
	public String getCourseList(String clno,String termname) {
		List<ClassCourse> list = studentDao.getCourseList(clno,termname);
        //以json格式返回数据
        String result = JSONArray.fromObject(list).toString();

		return result;
	}


}
