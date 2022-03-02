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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	
	@Resource(name = "userService")
	private UserService userService;
	
	@GET
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@POST
	public ResponseEntity<User> addUser(User user) {
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.TEXT_XML)
	public Optional<User> getUser(@PathParam("userId") Long userId) {
		return userService.getUserById(userId);
	}
	
	@GET
	@Path("/{userName}")
	public ResponseEntity<User> getUser(@PathParam("userName") String userName) {
		return new ResponseEntity<>(userService.getUserByName(userName).get(), HttpStatus.OK);
	}
	
	@PUT
	@Path("/{userName}")
	public ResponseEntity<User> updateUser(@PathParam("userName") String userName, User user) {
		Optional<User> _user = userService.getUserByName(userName);
		if (_user.isPresent()) {
			user.setUsername(userName);
			return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DELETE
	@Path("/{userName}")
	public ResponseEntity<HttpStatus> deleteUser(@PathParam("userName") String userName) {
		try {
			userService.removeUser(userName);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	
}
