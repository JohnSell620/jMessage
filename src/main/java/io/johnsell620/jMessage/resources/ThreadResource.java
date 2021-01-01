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

import io.johnsell620.jMessage.model.Thread;
import io.johnsell620.jMessage.service.ThreadService;
import io.johnsell620.jMessage.model.Profile;

@Path("/threads")
//@Path("/secured/threads")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ThreadResource {
	
	private ThreadService threadService = new ThreadService();
	
	@GET
	public List<Thread> getThreads() {
		return threadService.getAllThreads();
	}
	
	@POST
	public Thread addThread(Thread thread) {
		return threadService.addThread(thread);
	}
	
	@GET
	@Path("/{threadName}")
	public Thread getThread(@PathParam("threadName") String threadName) {
		return threadService.getThread(threadName);
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
	public List<Profile> getThreadUsers(@PathParam("threadName") String threadName, Thread thread) {
		return threadService.getThreadProfiles(threadName);
	}

}
