package io.johnsell620.jMessage.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import io.johnsell620.jMessage.dao.HibernateUtil;
import io.johnsell620.jMessage.model.Comment;
import io.johnsell620.jMessage.model.Link;
import io.johnsell620.jMessage.model.Message;
import io.johnsell620.jMessage.model.Profile;
import io.johnsell620.jMessage.model.Thread;
/**
 * 
 * @author johnny
 *
 */
public class ServiceUtil {

	private static Object val;
	
	public static Object get (String objectType, Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			if (objectType == "Message")	{
				val = (Message) session.get(Message.class, id);
			}
			else if (objectType == "Thread") {
				val = (Thread) session.get(Thread.class, id);
			}
			else if (objectType == "Comment") {
				val = (Comment) session.get(Comment.class, id);
			}
			else if (objectType == "Link") {
				val = (Link) session.get(Link.class, id);
			}
			session.getTransaction().commit();
			session.close();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return val;
	}

	public static void add (Object object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
			session.close();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	public static void update(Object object) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String update = null;
			if (object instanceof Message) {
				update = ((Message) object).getMessage();
			}
			if (object instanceof Thread) {
				update = ((Thread) object).getThreadName();
			}
			else if (object instanceof Comment) {
				update = ((Comment) object).getMessage();
			}
			else if (object instanceof Link) {
				update = ((Link) object).getLink();
			}
			else if (object instanceof Profile) {
				update = ((Profile) object).getProfileName();
			}
			
			session.beginTransaction();
			session.update(update, object);
			session.getTransaction().commit();	
			session.close();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}		
	}
	
	public static void remove (String objectType, Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String table = null; 
			if (objectType == "Message") {
				table = "messages";
			}
			else if (objectType == "Thread") {
				table = "threads";
			}
			else if (objectType == "Comment") {
				table = "comments";
			}
			else if (objectType == "Link") {
				table = "links";
			}
			
			session.beginTransaction();
			session.createQuery("DELETE from :table where id = :id")
				.setParameter("table", table)
				.setParameter("id", id)
				.executeUpdate();
			session.getTransaction().commit();	
			session.close();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
}
