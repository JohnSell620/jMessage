package io.johnsell620.jMessage.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.johnsell620.jMessage.model.User;
import io.johnsell620.jMessage.service.UserService;
/**
 * 
 * @author johnny
 *
 */
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	
	private UserService userService = new UserService();
	
	@GET
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@POST
	public User addUser(User user) {
		return userService.addUser(user);
	}
	
	@GET
	@Path("/{userName}")
	public User getUser(@PathParam("userName") String userName) {
		return userService.getUser(userName);
	}
	
	@PUT
	@Path("/{userName}")
	public User updateUser(@PathParam("userName") String userName, User user) {
		user.setUsername(userName);
		return userService.updateUser(user);
	}
	
	@DELETE
	@Path("/{userName}")
	public void deleteUser(@PathParam("userName") String userName) {
		userService.removeUser(userName);
	}

	
}
