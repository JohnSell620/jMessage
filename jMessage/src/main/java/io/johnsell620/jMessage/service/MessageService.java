package io.johnsell620.jMessage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import io.johnsell620.jMessage.dao.HibernateUtil;
import io.johnsell620.jMessage.exception.DataNotFoundException;
import io.johnsell620.jMessage.model.Message;
import io.johnsell620.jMessage.dao.dataUtil;
/**
 * 
 * @author johnny
 *
 */
public class MessageService {
		
	public MessageService() {
	}
	
	public Message getMessage(long id) {
		Message message = (Message) dataUtil.get("Message", id);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
		return message;
	}
	
	public Message addMessage(Message message) {
		dataUtil.add(message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		dataUtil.update(message);
		return message;
	}
	
	public Message removeMessage(long id) {
		Message message = (Message) dataUtil.get("Message", id);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
		dataUtil.remove("Message", id);
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
	
	// Needs error handling
	public List<Message> getAllMessagesForYear(int year) { 
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
