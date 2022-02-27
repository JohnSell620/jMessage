package io.johnsell620.jMessage.rest;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import org.hibernate.Session;

import io.johnsell620.jMessage.dao.HibernateUtil;
import io.johnsell620.jMessage.model.User;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization"; 
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic "; 
	private static final String SECURED_URL_PREFIX = "secured"; 

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				byte[] decodedBytes = Base64.getDecoder().decode(authToken);
                String decodedString = new String(decodedBytes, "UTF-8");
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				var user = session.createQuery("from users where username = :username")
						.setParameter("username", username);
				session.getTransaction().commit();
				session.close();
				
				User u = (User) user;
				String uname = u.getUsername();
				String pword = u.getPassword();
				
				if (uname.equals(username) && pword.equals(password)) {
					return;
				}				
			}
			Response unauthorizedStatus = Response
								            .status(Response.Status.UNAUTHORIZED)
								            .entity("User cannot access the resource.")
								            .build();
					
			requestContext.abortWith(unauthorizedStatus);
		}		
	}
}
