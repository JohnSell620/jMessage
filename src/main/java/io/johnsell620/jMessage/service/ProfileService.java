package io.johnsell620.jMessage.service;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

//import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.context.internal.ManagedSessionContext;

import io.johnsell620.jMessage.dao.HibernateUtil;
import io.johnsell620.jMessage.model.ErrorMessage;
import io.johnsell620.jMessage.model.Profile;
/**
 * 
 * @author johnny
 *
 */
public class ProfileService {

	public Profile getProfile(String profileName) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "documentation");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
				
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Object profile = session.createQuery("from Profile where profileName = :profileName")
						.setParameter("profileName", profileName)
						.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		if (profile == null) {
			throw new NotFoundException(response);	// Look at java documentation
		}
		return (Profile) profile;	
	}
	
	public Profile addProfile(Profile profile) {
		ServiceUtil.add(profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		ServiceUtil.update(profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Profile profile = (Profile) session.get(Profile.class, profileName);
		session.getTransaction().commit();
		session.beginTransaction();
		session.createQuery("delete from Profile where profileName = :profileName")
			.setParameter("profileName", profileName)
			.executeUpdate();
		session.getTransaction().commit();	
		session.close();
		return profile;
	}
	
	public List<Profile> getAllProfiles() {
		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.setFlushMode(FlushMode.MANUAL);
//		ManagedSessionContext.bind(session);
		session.beginTransaction();
		Query query = session.createQuery("from Profile");
		query.setCacheable(true);
//		ManagedSessionContext.unbind(HibernateUtil.getSessionFactory());
//		session.flush();
		session.getTransaction().commit();
//		session.close();
				
		//TODO needs to be safer
		@SuppressWarnings("unchecked")
		List<Profile> list = Collections.checkedList(query.list(), Profile.class); 
		session.close();
		return list;
	}


}
