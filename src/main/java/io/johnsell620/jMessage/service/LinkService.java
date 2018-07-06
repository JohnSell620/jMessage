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
/**
 * 
 * @author johnny
 *
 */
public class LinkService {
	
	public Link getLink(long messageId, long linkId) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "documentation");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();

		Link link = (Link) ServiceUtil.get("Link", linkId);
		if (link == null) {
			throw new NotFoundException(response);	// Look at java documentation
		}
		return link;		
	}
	
	public Link addLink(long messageId, Link link) {
		link.setMessageId(messageId);
		ServiceUtil.add(link);
		return link;
	}
	
	public Link updateLink(long messageId, Link link) {
		if (link.getId() <= 0) {
			return null;
		}
		ServiceUtil.update(link);
		return link;
	}
	
	public Link removeLink(long messageId, long linkId) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "documentation");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();

		Link link = (Link) ServiceUtil.get("Link", linkId);
		if (link == null) {
			throw new NotFoundException(response);	// Look at java documentation
		}
		ServiceUtil.remove("Link", linkId);
		return link;
	}

	public List<Link> getAllLinks(long messageId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from links");
		query.setCacheable(true);
		session.getTransaction().commit();
		
		//TODO needs to be safer
		@SuppressWarnings("unchecked")
		List<Link> list = Collections.checkedList(query.list(), Link.class);
		session.close(); 
		return list;
	}
}
