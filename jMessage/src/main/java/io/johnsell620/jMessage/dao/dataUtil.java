package io.johnsell620.jMessage.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import io.johnsell620.jMessage.model.Comment;
import io.johnsell620.jMessage.model.Link;
import io.johnsell620.jMessage.model.Message;

public class dataUtil {
	
	private static Object val;
	
	public static Object get (String objectType, Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			if (objectType == "Message")	{
				val = (Message) session.get(Message.class, id);
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
			String message = null;
			if (object instanceof Message) {
				message = ((Message) object).getMessage();
			}
			else if (object instanceof Comment) {
				message = ((Comment) object).getMessage();
			}
			else if (object instanceof Link) {
				message = ((Link) object).getLink();
			}
			
			session.beginTransaction();
			session.update(message, object);
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
