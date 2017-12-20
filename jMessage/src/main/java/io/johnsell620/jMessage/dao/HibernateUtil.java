package io.johnsell620.jMessage.dao;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
//import javax.transaction.Transaction;
//import org.hibernate.Session;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	 
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } 
        catch (Throwable ex) {
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
    
//    public static long rowCount() {
//    	return (Number) session.createCriteria("Book").setProjection(Projections.rowCount()).uniqueResult();
//    }
}
