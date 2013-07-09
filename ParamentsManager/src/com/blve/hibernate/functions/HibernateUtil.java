package com.blve.hibernate.functions;


import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil 
{
	private static Logger logger = Logger.getLogger(HibernateUtil.class);

    private static final SessionFactory sessionFactory;
    static 
    {
        try 
        {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            logger.info("Initial SessionFactory creation success");
        } 
        catch (Throwable ex) 
        {        
        	logger.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() 
    {
        return sessionFactory;
    }
}
