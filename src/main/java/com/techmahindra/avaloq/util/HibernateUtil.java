package com.techmahindra.avaloq.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
 
public class HibernateUtil {
//    static SessionFactory sessionFactoryObj;
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static Session sessionObj; 
 
    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
 
        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
 
        // Creating Hibernate SessionFactory Instance
        sessionFactory = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactory;
    }
    public static void openSessionFactory() {
    	sessionObj = HibernateUtil.getSessionFactory().openSession();
    }
    public static void closeSessionFactory() {
    	sessionObj.close();
    }
    public static Session retriveSession() {
    	return sessionObj;
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdowns() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
 
}