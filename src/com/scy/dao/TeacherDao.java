package com.scy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.scy.bean.ClassCourse;
import com.scy.bean.Semester;
import com.scy.bean.StudentCourseReport;
import com.scy.util.HibernateUtil;



public class TeacherDao {
    private Session session;
    private Transaction transaction;
    public void update(String hql) {
    	try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
    		Query query=session.createQuery(hql);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e)
        {
            e.printStackTrace();
        }	
    }
	public List<Semester> getTermname() {
    	try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="from Semester";
            Query<Semester> query=session.createQuery(hql,Semester.class);
            List<Semester> list= query.list();
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
	public List<ClassCourse> getCourseList(String tno, String termname) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="from ClassCourse as c where c.scy_tno=? and c.scy_cterm=?";
            Query<ClassCourse> query=session.createQuery(hql,ClassCourse.class);
            query.setParameter(0, tno);
            query.setParameter(1, termname);
            List<ClassCourse> list= query.list();
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
	public List<ClassCourse> getCourseList(String tno) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="from ClassCourse as c where c.scy_tno=?";
            Query<ClassCourse> query=session.createQuery(hql,ClassCourse.class);
            query.setParameter(0, tno);
            List<ClassCourse> list= query.list();
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
	public List<StudentCourseReport> getScoreList(String cno) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="from StudentCourseReport as s where s.scy_cno=?";
            Query<StudentCourseReport> query=session.createQuery(hql,StudentCourseReport.class);
            query.setParameter(0, cno);
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
	public List<StudentCourseReport> getStudentList(String cno) {
		try {
    		session=HibernateUtil.getSession();
            String hql="from StudentCourseReport as s where s.scy_cno=? and s.scy_report=0";
            Query<StudentCourseReport> query=session.createQuery(hql,StudentCourseReport.class);
            query.setParameter(0, cno);
            List<StudentCourseReport> list= query.list();
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
	public void setStudentScore(String sno, String cname, String cterm, double report) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  StudentCourseReport as s SET s.scy_report=? WHERE s.scy_sno=? and s.scy_cname=? and s.scy_cterm=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, report);
    		query.setParameter(1, sno);
    		query.setParameter(2, cname);
    		query.setParameter(3, cterm);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
	}
	public void setStudentCredit(String sno, double credit) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="UPDATE  Student as s SET s.scredit= s.scredit+? WHERE s.sno=?";
    		Query query=session.createQuery(hql);
    		query.setParameter(0, credit);
    		query.setParameter(1, sno);
    		query.executeUpdate();
            transaction.commit();
            session.close();
    	}
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
		
	}
}
