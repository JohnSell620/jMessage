package io.johnsell620.jMessage.service;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import io.johnsell620.jMessage.dao.HibernateUtil;
import io.johnsell620.jMessage.exception.DataNotFoundException;
import io.johnsell620.jMessage.model.Comment;
import io.johnsell620.jMessage.model.ErrorMessage;
/**
 * 
 * @author johnny
 *
 */
public class CommentService {
	
	public Comment getComment(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "documentation");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		
		Comment comment = (Comment) ServiceUtil.get("Comment", commentId);
		if (comment == null) {
			throw new NotFoundException(response);	// Look at java documentation
		}
		return (Comment) comment;		
	}
	
	public Comment addComment(long messageId, Comment comment) {
		comment.setMessageId(messageId);
		ServiceUtil.add(comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		if (comment.getId() <= 0) {
			return null;
		}
		ServiceUtil.update(comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Comment comment = (Comment) ServiceUtil.get("Comment", commentId);
		if (comment == null) {
			throw new DataNotFoundException("Comment with id " + commentId + " not found");
		}
		ServiceUtil.remove("Comment", commentId);
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
