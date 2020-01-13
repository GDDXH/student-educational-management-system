package com.scy.dao;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scy.bean.Admin;
import com.scy.bean.Student;
import com.scy.bean.Teacher;
import com.scy.util.HibernateUtil;

public class LoginDao {
    private Session session;
    private Transaction transaction;
	public void update(String hql) {
    	try {
    		session=HibernateUtil.getSession();
        	//session=sessionFactory.getCurrentSession();
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
    public List<Student> getStudent(String account,String password){
        try
        {
        	session=HibernateUtil.getSession();
        	//session=sessionFactory.getCurrentSession();
            transaction=session.beginTransaction();
            String hql="from Student as s where s.sno=? and s.spassword=?";
            Query<Student> query=session.createQuery(hql,Student.class);
            query.setParameter(0, account);
            query.setParameter(1,password);
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
	public List<Teacher> getTeacher(String account,String password){    
        try
        {
        	session=HibernateUtil.getSession();
        	//session=sessionFactory.getCurrentSession();
        	transaction=session.beginTransaction();
            String hql="from Teacher as t where t.tno=? and t.tpassword=?";
            Query<Teacher> query = session.createQuery(hql,Teacher.class);
            query.setParameter(0, account);
            query.setParameter(1,password);
            List<Teacher> list= query.list();          
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
	public List<Admin> getAdmin(String account,String password){
        try
        {
        	session=HibernateUtil.getSession();
        	//session=sessionFactory.getCurrentSession();
        	transaction=session.beginTransaction();
            String hql="from Admin as a where a.ano=? and a.apassword=?";
            Query<Admin> query = session.createQuery(hql,Admin.class);
            query.setParameter(0, account);
            query.setParameter(1,password);
            List<Admin> list= query.list();        
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
