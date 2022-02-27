package io.johnsell620.jMessage.service;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

// import org.hibernate.Query;
import org.hibernate.Session;

import io.johnsell620.jMessage.dao.HibernateUtil;
import io.johnsell620.jMessage.model.ErrorMessage;
import io.johnsell620.jMessage.model.Thread;
import io.johnsell620.jMessage.model.Profile;
/**
 * 
 * @author johnny
 *
 */
public class ThreadService {

	public Thread getThread(String threadName) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "documentation");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
				
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Object thread = session.createQuery("from Thread where threadName = :threadName")
						.setParameter("threadName", threadName)
						.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		if (thread == null) {
			throw new NotFoundException(response);	// Look at java documentation
		}
		return (Thread) thread;	
	}
	
	public Thread addThread(Thread thread) {
		ServiceUtil.add(thread);
		return thread;
	}
	
	public Thread updateThread(Thread thread) {
		if (thread.getThreadName().isEmpty()) {
			return null;
		}
		ServiceUtil.update(thread);
		return thread;
	}
	
	public Thread removeThread(String threadName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Thread thread = (Thread) session.get(Thread.class, threadName);
		session.getTransaction().commit();
		session.beginTransaction();
		session.createQuery("delete from Thread where threadName = :threadName")
			.setParameter("threadName", threadName)
			.executeUpdate();
		session.getTransaction().commit();	
		session.close();
		return thread;
	}
	
	public List<Thread> getAllThreads() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		var query = session.createQuery("from Thread");
		query.setCacheable(true);
		session.getTransaction().commit();
				
		//TODO needs to be safer
		@SuppressWarnings("unchecked")
		List<Thread> list = Collections.checkedList(query.list(), Thread.class); 
		session.close();
		return list;
	}
	
	public List<Profile> getThreadProfiles(String threadName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String queryStatement = "select distinct p from Profile p, Message m "
				+ "where p.id = m.profileId and m.threadName = :threadName";
		var query = session.createQuery(queryStatement);
		query.setParameter("threadName", threadName);
		query.setCacheable(true);
		session.getTransaction().commit();
				
		//TODO needs to be safer
		@SuppressWarnings("unchecked")
		List<Profile> list = Collections.checkedList(query.list(), Profile.class); 
		session.close();
		return list;
	}


}
