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

import io.johnsell620.jMessage.model.Thread;
import io.johnsell620.jMessage.service.ThreadService;
import io.johnsell620.jMessage.model.Profile;

@RestController
@Path("/threads")
//@Path("/secured/threads")
public class ThreadResource {
	
	@Resource(name = "threadService")
	private ThreadService threadService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Thread> getThreads() {
		return threadService.getAllThreads();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Thread addThread(Thread thread) {
		return threadService.addThread(thread);
	}
	
	@GET
	@Path("/{threadName}")
	public Optional<Thread> getThread(@PathParam("threadId") Long threadId) {
		return threadService.getThread(threadId);
	}
	
	@PUT
	@Path("/{threadName}")
	public Thread updateThread(@PathParam("threadName") String threadName, Thread thread) {
		thread.setThreadName(threadName);
		return threadService.updateThread(thread);
	}
	
	@DELETE
	@Path("/{threadName}")
	public void deleteThread(@PathParam("threadName") String threadName) {
		threadService.removeThread(threadName);
	}
	
	@GET
	@Path("/{threadName}/users")
	public List<Profile> getThreadUsers(@PathParam("threadName") String threadName) {
		return threadService.getThreadProfiles(threadName);
	}

}
