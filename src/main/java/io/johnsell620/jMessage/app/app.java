package io.johnsell620.jMessage.app;

//import java.util.Collections;
//import java.util.List;
//
//import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;
import org.johnny.javahead.messenger.database.dataUtil;

import io.johnsell620.jMessage.model.Comment;
import io.johnsell620.jMessage.model.Message;
import io.johnsell620.jMessage.dao.HibernateUtil;

public class app {
	
	public static void main( String[] args ) {
		Message message1 = new Message(1, "hello", "johnny");
		Comment comment1 = new Comment(1, "hi", "amy");
		
		System.out.println(message1.getMessage());
		System.out.println(message1.getAuthor());
		System.out.println(comment1.getMessage());
		

		Message message2 = new Message(4, "chaoi", "james");
		
		System.out.println("message2 author: " + message2.getAuthor());
		System.out.println(message2);

		
		
//		String hq1 = "UPDATE messages set message = goodbye WHERE id = 1";
//		String hq2 = "from Message";
		
//		Configuration config = new Configuration().configure().addAnnotatedClass(Message.class);
//		ServiceRegistry registry = new ServiceRegistryBuilder()
//				.applySettings(config.getProperties())
//				.buildServiceRegistry();
//		SessionFactory sf = config.buildSessionFactory();
		
//		Session session1 = HibernateUtil.getSessionFactory().openSession();
//		session1.beginTransaction();
////		Query q1 = session1.createQuery(hq1);
////		int q1r = q1.executeUpdate();
//		session1.save(message2);
//		session1.getTransaction().commit();
//		session1.close();
		
//		System.out.println("Rows affected: " + q1r);

//		Session session2 = HibernateUtil.getSessionFactory().openSession();
//		session2.beginTransaction();		
////		Query q2 = session2.createQuery(hq2);
////		@SuppressWarnings("unchecked")
////		List<Message> results = Collections.checkedList(q2.list(), Message.class); 
//		
//		message2 = (Message) session2.get(Message.class, id2);
//		
//		session2.getTransaction().commit();
//		session2.close();

		long id2 = 3;
		Message message3 = new Message();
		message3 = (Message) dataUtil.get("Message", id2);
		
		
		System.out.println("message3 message: " + message3.getMessage());
		
		message3.setMessage("new goodbye");
		dataUtil.update(message3);
		System.out.println("New message3 message: " + message3.getMessage());
		
//		for (Message s : results) {
//			System.out.println(s.getMessage());
//		}
		
		
		
	}
}
