package com.scy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.scy.bean.Course;
import com.scy.bean.Stu;
import com.scy.bean.Student;
import com.scy.bean.StudentCourseReport;
import com.scy.bean.Teacher;
import com.scy.util.HibernateUtil;

public class AdminDao {
    private Session session;
    private Transaction transaction;
	public List<StudentCourseReport> getScoreList(String termname) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="from StudentCourseReport as s where s.scy_cterm=? order by s.scy_sno";
            Query<StudentCourseReport> query=session.createQuery(hql,StudentCourseReport.class);
            query.setParameter(0, termname);
            List<StudentCourseReport> list= query.list();
            transaction.commit();
            session.close();
            return list;
    	}
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
	}
	public List<Student> getStudentList() {
		try{
        	session=HibernateUtil.getSession();
        	transaction=session.beginTransaction();
            String hqlsql="from Student";
            Query<Student> query=session.createQuery(hqlsql,Student.class);
            List<Student> list= query.list();
            transaction.commit();
            session.close();
            return list;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;     
        }	
	}
	public String addStudent(Stu student) {
		try{
        	session=HibernateUtil.getSession();
        	transaction=session.beginTransaction();
        	session.save(student);
            transaction.commit();
            session.close();
            return "success";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "fail";     
        }	
	}
	public String deleteStudent(Stu student) {
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.delete(student);
			transaction.commit();
			session.close();
			return "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	public void setStudentName(String sno, String sname) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Stu as s set s.sname=? WHERE s.sno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, sname);
    		query.setParameter(1,sno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	
	public void setStudentPassword(String sno, String spassword) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Stu as s set s.spassword=? WHERE s.sno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, spassword);
    		query.setParameter(1,sno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
		
	}
	public void setStudentAddress(String sno, String saddress) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Stu as s set s.saddress=? WHERE s.sno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, saddress);
    		query.setParameter(1,sno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
		
	}
	public void setStudentSex(String sno, String ssex) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Stu as s set s.ssex=? WHERE s.sno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, ssex);
    		query.setParameter(1,sno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
		
	}
	public void setStudentAge(String sno, int sage) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Stu as s set s.sage=? WHERE s.sno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, sage);
    		query.setParameter(1,sno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setStudentYear(String sno, int syear) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Stu as s set s.syear=? WHERE s.sno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, syear);
    		query.setParameter(1,sno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setStudentCredit(String sno, double scredit) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Stu as s set s.scredit=? WHERE s.sno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, scredit);
    		query.setParameter(1,sno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setStudentClno(String sno, String clno) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Stu as s set s.clno=? WHERE s.sno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, clno);
    		query.setParameter(1,sno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public List<Teacher> getTeacherList() {
		try {
    		session=HibernateUtil.getSession();
            String hql="from Teacher";
            Query<Teacher> query=session.createQuery(hql,Teacher.class);
            List<Teacher> list= query.list();
            transaction=session.beginTransaction();
            transaction.commit();
            session.close();
            return list;
    	}
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
	}
	public String addTeacher(Teacher teacher) {
		try{
        	session = HibernateUtil.getSession();
        	transaction = session.beginTransaction();
        	session.save(teacher);
            transaction.commit();
            session.close();
            return "success";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "fail";     
        }	
	}
	public String deleteTeacher(Teacher teacher) {
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.delete(teacher);
			transaction.commit();
			session.close();
			return "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	public void setTeacherName(String tno, String tname) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Teacher as t set t.tname=? WHERE t.tno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, tname);
    		query.setParameter(1,tno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setTeacherSex(String tno, String tsex) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Teacher as t set t.tsex=? WHERE t.tno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, tsex);
    		query.setParameter(1,tno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
		
	}
	public void setTeacherAge(String tno, int tage) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Teacher as t set t.tage=? WHERE t.tno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, tage);
    		query.setParameter(1,tno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setTeacherPosition(String tno, String tposition) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Teacher as t set t.tposition=? WHERE t.tno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, tposition);
    		query.setParameter(1,tno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setTeacherPhone(String tno, String tphone) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Teacher as t set t.tphone=? WHERE t.tno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, tphone);
    		query.setParameter(1,tno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setCourseName(String cno, String cname) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Course as c set c.cname=? WHERE c.cno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, cname);
    		query.setParameter(1,cno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setCourseTime(String cno, int ctime) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Course as c set c.ctime=? WHERE c.cno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, ctime);
    		query.setParameter(1,cno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setCourseCredit(String cno, int credit) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Course as c set c.credit=? WHERE c.cno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, credit);
    		query.setParameter(1,cno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setCourseTerm(String cno, String cterm) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Course as c set c.cterm=? WHERE c.cno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, cterm);
    		query.setParameter(1,cno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public void setCourseTest(String cno, String ctest) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Course as c set c.ctest=? WHERE c.cno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, ctest);
    		query.setParameter(1,cno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e) {
            e.printStackTrace();
		}
	}
	public List<Course> getCourseList() {
		try {
    		session=HibernateUtil.getSession();
            String hql="from Course";
            Query<Course> query=session.createQuery(hql,Course.class);
            List<Course> list= query.list();
            transaction=session.beginTransaction();
            transaction.commit();
            session.close();
            return list;
    	}
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
	}
	public String addCourse(Course course) {
		try{
        	session=HibernateUtil.getSession();
        	transaction=session.beginTransaction();
        	session.save(course);
            transaction.commit();
            session.close();
            return "success";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "fail";     
        }	
	}
	public String deleteCourse(Course course) {
		try{
        	session=HibernateUtil.getSession();
        	transaction=session.beginTransaction();
        	session.delete(course);
            transaction.commit();
            session.close();
            return "success";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "fail";     
        }
	}
}
