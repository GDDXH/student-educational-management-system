package com.scy.service;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.scy.bean.Course;
import com.scy.bean.Stu;
import com.scy.bean.Student;
import com.scy.bean.StudentCourseReport;
import com.scy.bean.Teacher;
import com.scy.dao.AdminDao;

import net.sf.json.JSONArray;

public class AdminService {
	private AdminDao adminDao = new AdminDao();
	public String getScoreList(String termname) {
		DecimalFormat df = new DecimalFormat("#.00");  
		List<StudentCourseReport> list = adminDao.getScoreList(termname);
		List<StudentCourseReport> list2 = new ArrayList<StudentCourseReport>();
		StudentCourseReport studentCourseReport = new StudentCourseReport();
		studentCourseReport = list.get(0);
		studentCourseReport.setScy_report(studentCourseReport.getScy_report()*studentCourseReport.getScy_credit());
		int i = 1;
		for(i = 1 ; i < list.size() ; i++) {
			if(studentCourseReport.getScy_sno().equals(list.get(i).getScy_sno()))
			{
				studentCourseReport.setScy_report(studentCourseReport.getScy_report() + list.get(i).getScy_report()*list.get(i).getScy_credit());
				studentCourseReport.setScy_credit(studentCourseReport.getScy_credit()+list.get(i).getScy_credit());
			}
			else {
				studentCourseReport.setScy_report(studentCourseReport.getScy_report()/studentCourseReport.getScy_credit()/20);
				studentCourseReport.setScy_report(Double.parseDouble(df.format(studentCourseReport.getScy_report()))); 
				list2.add(studentCourseReport);
				studentCourseReport = new StudentCourseReport();
				studentCourseReport = list.get(i);
				studentCourseReport.setScy_report(studentCourseReport.getScy_report()*studentCourseReport.getScy_credit());
			}
		}
		studentCourseReport.setScy_report(studentCourseReport.getScy_report()/studentCourseReport.getScy_credit()/20);
		studentCourseReport.setScy_report(Double.parseDouble(df.format(studentCourseReport.getScy_report()))); 
		list2.add(studentCourseReport);
        //以json格式返回数据
        String result = JSONArray.fromObject(list2).toString();
		return result;
	}
	public String getStudentList() {
		List<Student> list = adminDao.getStudentList();
        //以json格式返回数据
        String result = JSONArray.fromObject(list).toString();
		return result;
	}
	public String addStudent(Stu student) {	
		return adminDao.addStudent(student);
	}	
	public String editStudent(Stu student) {
		try {
			String sno = student.getSno();		
			adminDao.setStudentName(sno,student.getSname());
			adminDao.setStudentSex(sno,student.getSsex());
			adminDao.setStudentAge(sno,student.getSage());
			adminDao.setStudentYear(sno,student.getSyear());
			adminDao.setStudentAddress(sno,student.getSaddress());
			adminDao.setStudentCredit(sno,student.getScredit());
			adminDao.setStudentClno(sno,student.getClno());
			return "success";
		}
        catch(Exception e) {
        	e.printStackTrace();
        	return "fail";
		}

	}
	public String deleteStudent(Stu student) {
		return adminDao.deleteStudent(student);
	}
	public String addTeacher(Teacher teacher) {
		return adminDao.addTeacher(teacher);
	}
	public String editTeacher(Teacher teacher) {
		try {
			String tno = teacher.getTno();
			adminDao.setTeacherName(tno,teacher.getTname());
			adminDao.setTeacherSex(tno,teacher.getTsex());
			adminDao.setTeacherAge(tno,teacher.getTage());
			adminDao.setTeacherPosition(tno,teacher.getTposition());
			adminDao.setTeacherPhone(tno,teacher.getTphone());
			return "success";
		}
        catch(Exception e) {
        	e.printStackTrace();
        	return "fail";
		}
	}
	public String deleteTeacher(Teacher teacher) {
		return adminDao.deleteTeacher(teacher);
	}
	public String getTeacherList() {
		List<Teacher> list = adminDao.getTeacherList();
        //以json格式返回数据
        String result = JSONArray.fromObject(list).toString();
		return result;
	}
	public String editCourse(Course course) {
		try {
			String cno = course.getCno();
			adminDao.setCourseName(cno,course.getCname());
			adminDao.setCourseTime(cno,course.getCtime());
			adminDao.setCourseCredit(cno,course.getCredit());
			adminDao.setCourseTest(cno,course.getCtest());
			adminDao.setCourseTerm(cno,course.getCterm());
			return "success";
		}
        catch(Exception e) {
        	e.printStackTrace();
        	return "fail";
		}
	}
	public String getCourseList() {
		List<Course> list = adminDao.getCourseList();
        //以json格式返回数据
        String result = JSONArray.fromObject(list).toString();
		return result;
	}
	public String addCourse(Course course) {
		return adminDao.addCourse(course);
	}
	public String deleteCourse(Course course) {
		return adminDao.deleteCourse(course);
	}

}
