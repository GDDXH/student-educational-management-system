package com.scy.service;

import java.util.ArrayList;
import java.util.List;

import com.scy.bean.ClassCourse;
import com.scy.bean.Semester;
import com.scy.bean.StudentCourseReport;
import com.scy.dao.TeacherDao;

import net.sf.json.JSONArray;

public class TeacherService {
	private TeacherDao teacherDao = new TeacherDao();
	public void editPassword(String account,String password) {
		teacherDao.update("UPDATE Teacher as t SET t.tpassword="+password+" WHERE t.tno="+account);
	}
	public String termList() {
		List<Semester> list = teacherDao.getTermname();		
		//格式化以json格式返回数据
        String result = JSONArray.fromObject(list).toString();	
		return result;
	}
	public String getCourseList(String tno, String termname) {
		List<ClassCourse> list = teacherDao.getCourseList(tno,termname);
        //格式化以json格式返回数据
        String result = JSONArray.fromObject(list).toString();
		return result;
	}
	public String getCourseList(String tno) {
		List<ClassCourse> list = teacherDao.getCourseList(tno);
        //格式化以json格式返回数据
        String result = JSONArray.fromObject(list).toString();
		return result;
	}
	public String getScoreList(String cno) {
		List<StudentCourseReport> list = teacherDao.getScoreList(cno);
        //格式化以json格式返回数据
		StudentCourseReport studentCourseReport = new StudentCourseReport();
		int sum=0;
		for(int i = 0 ; i < list.size() ; i++)
			 sum += list.get(i).getScy_report();
		studentCourseReport.setScy_report(sum/list.size());
		studentCourseReport.setScy_sname("平均分为：");
		list.add(studentCourseReport);
        String result = JSONArray.fromObject(list).toString();
		return result;
	}
	public String getStudentList(String tno) {
		List<ClassCourse> Courselist = teacherDao.getCourseList(tno);
		List<StudentCourseReport> list = new ArrayList<StudentCourseReport>();
		for(int i=0;i<Courselist.size();i++)
		{
			List<StudentCourseReport> templist = teacherDao.getStudentList(Courselist.get(i).getScy_cno());
			if(templist!=null)
				list.addAll(templist);
		}
        //格式化json格式返回数据
        String result = JSONArray.fromObject(list).toString();
		return result;
	}
	public void setStudentScore(String sno, String cname, String cterm, int report) {
		teacherDao.setStudentScore(sno,cname,cterm,(double)report);
	}
	public void setStudentCredit(String sno, double credit) {
		teacherDao.setStudentCredit(sno,credit);		
	}
}
