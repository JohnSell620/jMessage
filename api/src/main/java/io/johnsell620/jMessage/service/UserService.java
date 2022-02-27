package io.johnsell620.jMessage.service;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;
// import org.hibernate.query.Query;
import org.hibernate.Session;

import io.johnsell620.jMessage.dao.HibernateUtil;
import io.johnsell620.jMessage.model.ErrorMessage;
import io.johnsell620.jMessage.model.User;
/**
 * 
 * @author johnny
 *
 */
public class UserService {
	
	public User getUser(String username) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "documentation");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
				
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Object user = session.createQuery("from User where username = :username")
						.setParameter("username", username)
						.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		if (user == null) {
			throw new NotFoundException(response);	// Look at java documentation
		}
		return (User) user;	
	}
	
	public User addUser(User user) {
		ServiceUtil.add(user);
		return user;
	}
	
	public User updateUser(User user) {
		if (user.getUsername().isEmpty()) {
			return null;
		}

		Long pId = user.getpId();
		String profileId = Long.toString(pId);
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {	
			session.beginTransaction();
			session.update(profileId, user);
			session.getTransaction().commit();	
			session.close();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}	
		return user;
	}
	
	public User removeUser(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, username);
		session.getTransaction().commit();
		session.beginTransaction();
		session.createQuery("delete from User where username = :username")
			.setParameter("username", username)
			.executeUpdate();
		session.getTransaction().commit();	
		session.close();
		return user;
	}
	
	public List<User> getAllUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		var query = session.createQuery("from User");
		query.setCacheable(true);
		session.getTransaction().commit();
		
		//TODO needs to be safer
		@SuppressWarnings("unchecked")
		List<User> list = Collections.checkedList(query.list(), User.class);
		session.close(); 
		return list;
	}

}
