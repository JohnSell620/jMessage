package io.johnsell620.jMessage.service;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Query;
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
	
	public User getUser(String userName) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "documentation");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
				
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query user = session.createQuery("from users where userName = :userName")
						.setParameter("userName", userName);
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
		ServiceUtil.update(user);
		return user;
	}
	
	public User removeUser(String userName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, userName);
		session.getTransaction().commit();
		session.beginTransaction();
		session.createQuery("delete from users where userName = :userName")
			.setParameter("userName", userName)
			.executeUpdate();
		session.getTransaction().commit();	
		session.close();
		return user;
	}
	
	public List<User> getAllUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from users");
		query.setCacheable(true);
		session.getTransaction().commit();
		session.close();
		
		//TODO needs to be safer
		@SuppressWarnings("unchecked")
		List<User> list = Collections.checkedList(query.list(), User.class); 
		return list;
	}

}
