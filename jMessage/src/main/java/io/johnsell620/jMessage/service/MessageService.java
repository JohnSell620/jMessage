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

public class MessageService {
	
//	Map<Long, Message> messages = new HashMap<>();
	
	public MessageService() {
//		messages.put(1L, new Message(1, "Hello World!", "johnny"));
//		messages.put(2L, new Message(2, "Hello Jersey!", "johnny"));
	}
	
	public Message getMessage(long id) {
//		System.out.println("message request");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Message message = (Message) session.get(Message.class, id);
		session.getTransaction().commit();
		session.close();
		
//		Message message = messages.get(id);

		if (message == null) {
			throw new DataNotFoundException("Messgae with id " + id + " not found");
		}
		return message;
	}
	
	public Message addMessage(Message message) {
//		System.out.println("message post");
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
//		session.createQuery("INSERT :message)
		session.getTransaction().commit();	
		session.close();
		return message;
	}
	
	public Message removeMessage(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Message message = (Message) session.get(Message.class, id);
		session.getTransaction().commit();
		session.beginTransaction();
		session.createQuery("DELETE from messages where id = :id")
			.setParameter("id", id)
			.executeUpdate();
		session.getTransaction().commit();	
		session.close();
		return message;
	}
	
	public List<Message> getAllMessages() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from messages");
		query.setCacheable(true);
//		q = (Message)query.uniqueResult();
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
