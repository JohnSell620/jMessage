package io.johnsell620.jMessage.service;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import io.johnsell620.jMessage.dao.HibernateUtil;
import io.johnsell620.jMessage.model.Comment;
import io.johnsell620.jMessage.model.ErrorMessage;

public class CommentService {
	
	public Comment getComment(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "documentation");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
				
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query comment = session.createQuery("from comments where commentId = :commentId")
						.setParameter("commentId", commentId);
		session.getTransaction().commit();
		session.close();
		
		if (comment == null) {
			throw new NotFoundException(response);	// Look at java documentation
		}
		return (Comment) comment;		
	}
	
	public Comment addComment(long messageId, Comment comment) {
		comment.setMessageId(messageId);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(comment);
		session.getTransaction().commit();	
		session.close();	
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		if (comment.getId() <= 0) {
			return null;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(comment.getMessage(), comment);
		session.getTransaction().commit();	
		session.close();
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		Comment comment = (Comment) session1.get(Comment.class, commentId);
		session1.getTransaction().commit();	
		session1.close();

		Session session2 = HibernateUtil.getSessionFactory().openSession();
		session2.beginTransaction();
		session2.createQuery("DELETE from comments where commentId = :commentId")
			.setParameter("commentId", commentId)
			.executeUpdate();
		session2.getTransaction().commit();	
		session2.close();
		return comment;
	}

	public List<Comment> getAllComments(long messageId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from comments");
		query.setCacheable(true);
		session.getTransaction().commit();
		session.close();
		
		//TODO needs to be safer
		@SuppressWarnings("unchecked")
		List<Comment> list = Collections.checkedList(query.list(), Comment.class); 
		return list;
	}
	
}
