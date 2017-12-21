package io.johnsell620.jMessage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import org.hibernate.Query;
//import java.util.Map;
import org.hibernate.Session;
import io.johnsell620.jMessage.dao.HibernateUtil;
import io.johnsell620.jMessage.exception.DataNotFoundException;
import io.johnsell620.jMessage.model.Message;
//import io.johnsell620.jMessage.dao.dataUtil;
/**
 * 
 * @author johnny
 *
 */
public class MessageService {
		
	public MessageService() {
	}
	
	public Message getMessage(long id) {
		Message message = new Message();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		message = (Message) session.get(Message.class, id);
		session.getTransaction().commit();
		session.close();
		
		if (message == null) {
			throw new DataNotFoundException("Messgae with id " + id + " not found");
		}
		return message;
//		return dataUtil.get("Message", id);
	}
	
	public Message addMessage(Message message) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(message);
		session.getTransaction().commit();	
		session.close();	
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(message.getMessage(), message);
		session.getTransaction().commit();	
		session.close();
		return message;
	}
	
	public Message removeMessage(long id) {
		Message message = new Message();
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		message = (Message) session1.get(Message.class, id);
		session1.getTransaction().commit();
		session1.close();
		
		Session session2 = HibernateUtil.getSessionFactory().openSession();
		session2.beginTransaction();
		session2.createQuery("DELETE from messages where id = :id")
			.setParameter("id", id)
			.executeUpdate();
		session2.getTransaction().commit();	
		session2.close();
		
		return message;
	}
	
	public List<Message> getAllMessages() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from messages");
		query.setCacheable(true);
		session.getTransaction().commit();
		session.close();
		
		//TODO needs to be safer
		@SuppressWarnings("unchecked")
		List<Message> list = Collections.checkedList(query.list(), Message.class); 
		return list;
	}
	
	public List<Message> getAllMessagesForYear(int year) { // Needs some error handling!
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : getAllMessages()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(getAllMessages());
		if (start + size > list.size()) return new ArrayList<Message>();
		return list.subList(start, start + size);
	}

}
