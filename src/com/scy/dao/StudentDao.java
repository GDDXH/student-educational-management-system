package com.scy.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.scy.bean.ClassCourse;
import com.scy.bean.Semester;
import com.scy.bean.Student;
import com.scy.bean.StudentCourseReport;
import com.scy.util.HibernateUtil;

public class StudentDao {
    private Session session;
    private Transaction transaction;
    public List<Student> getStudent(String account,String password){
        try{
        	session=HibernateUtil.getSession();
            String hqlsql="from Student as s where s.sno=? and s.spassword=?";
            Query<Student> query=session.createQuery(hqlsql,Student.class);
            query.setParameter(0, account);
            query.setParameter(1,password);
            List<Student> list= query.list();
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
	public List<StudentCourseReport> getScoreList(String sno,String termname) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="from StudentCourseReport as s where s.scy_sno=? and s.scy_cterm=?";
            Query<StudentCourseReport> query=session.createQuery(hql,StudentCourseReport.class);
            query.setParameter(0, sno);
            query.setParameter(1, termname);
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
	public List<ClassCourse> getCourseList(String clno, String termname) {
		try {
    		session=HibernateUtil.getSession();
            transaction=session.beginTransaction();
            String hql="from ClassCourse as c where c.scy_clno=? and c.scy_cterm=?";
            Query<ClassCourse> query=session.createQuery(hql,ClassCourse.class);
            query.setParameter(0, clno);
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
	
}
