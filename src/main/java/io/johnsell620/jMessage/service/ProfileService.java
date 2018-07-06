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
		Query profile = session.createQuery("from profiles where profileName = :profileName")
						.setParameter("profileName", profileName);
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
		session.createQuery("delete from profiles where profileName = :profileName")
			.setParameter("profileName", profileName)
			.executeUpdate();
		session.getTransaction().commit();	
		session.close();
		return profile;
	}
	
	public List<Profile> getAllProfiles() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from profiles");
		query.setCacheable(true);
		session.getTransaction().commit();
		session.close();
		
		//TODO needs to be safer
		@SuppressWarnings("unchecked")
		List<Profile> list = Collections.checkedList(query.list(), Profile.class); 
		return list;
	}


}
