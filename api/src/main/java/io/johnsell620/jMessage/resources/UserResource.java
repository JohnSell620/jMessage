package io.johnsell620.jMessage.resources;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RestController;

import io.johnsell620.jMessage.model.User;
import io.johnsell620.jMessage.service.UserService;
/**
 * 
 * @author johnny
 *
 */
@RestController
@Path("/users")
//@Path("/secured/users")
public class UserResource {
	
	@Resource(name = "userService")
	private UserService userService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User addUser(User user) {
		return userService.addUser(user);
	}
	
	@GET
	@Path("/{userId}")
	public Optional<User> getUser(@PathParam("userId") Long userId) {
		return userService.getUser(userId);
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
