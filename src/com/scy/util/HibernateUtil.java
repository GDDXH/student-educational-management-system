package com.scy.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public HibernateUtil() {}
    static {
    	/*
    	Configuration configuration = new Configuration().configure();
    	sessionFactory = configuration.buildSessionFactory();
        */
    	final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
    	try {
    		sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    	}
    	catch (Exception e) {
    		StandardServiceRegistryBuilder.destroy(serviceRegistry);
    		e.printStackTrace();
    	}
    }
    public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}
	public static Session getSession(){
		if(sessionFactory!=null)	
				return sessionFactory.openSession();
		return null;
    }  
	public static void closeSession(Session session) {
		if(session!=null)
			if(session.isOpen())
				session.close();
	}
}
