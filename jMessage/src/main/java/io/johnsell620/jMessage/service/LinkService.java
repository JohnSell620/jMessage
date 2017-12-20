package io.johnsell620.jMessage.service;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Query;
import org.hibernate.Session;

import io.johnsell620.jMessage.dao.HibernateUtil;
import io.johnsell620.jMessage.model.Link;
import io.johnsell620.jMessage.model.ErrorMessage;

public class LinkService {
	
	public Link getLink(long messageId, long linkId) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "documentation");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
				
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query link = session.createQuery("from links where linkId = :linkId")
						.setParameter("linkId", linkId);
		session.getTransaction().commit();
		session.close();
		
		if (link == null) {
			throw new NotFoundException(response);	// Look at java documentation
		}
		return (Link) link;		
	}
	
	public Link addLink(long messageId, Link link) {
		link.setMessageId(messageId);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(link);
		session.getTransaction().commit();	
		session.close();	
		return link;
	}
	
	public Link updateLink(long messageId, Link link) {
		if (link.getId() <= 0) {
			return null;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(link.getLink(), link);
		session.getTransaction().commit();	
		session.close();
		return link;
	}
	
	public Link removeLink(long messageId, long linkId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Link comment = (Link) session.get(Link.class, linkId);
		session.getTransaction().commit();
		session.beginTransaction();
		session.createQuery("DELETE from links where linkId = :linkId")
			.setParameter("commentId", linkId)
			.executeUpdate();
		session.getTransaction().commit();	
		session.close();
		return comment;
	}

	public List<Link> getAllLinks(long messageId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from links");
		query.setCacheable(true);
		session.getTransaction().commit();
		session.close();
		
		//TODO needs to be safer
		@SuppressWarnings("unchecked")
		List<Link> list = Collections.checkedList(query.list(), Link.class); 
		return list;
	}
}
