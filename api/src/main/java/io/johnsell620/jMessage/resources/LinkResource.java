package io.johnsell620.jMessage.resources;

import java.util.List;

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

import io.johnsell620.jMessage.model.Link;
import io.johnsell620.jMessage.service.LinkService;
/**
 * 
 * @author johnny
 *
 */
@RestController
@Path("/messages/{messageId}/links")
//@Path("/secured/links")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LinkResource {
	
	@Resource(name = "linkService")
	private LinkService linkService;
	
	@GET
	public List<Link> getAllLinks(@PathParam("messageId") long messageId) {
		return linkService.getAllLinks(messageId);
	}
	
	@POST
	public Link addLink(@PathParam("messageId") long messageId, Link link) {
		return linkService.addLink(messageId, link);
	}
	
	@PUT
	@Path("/{linkId}")
	public Link updateLink(@PathParam("linkId") long id, Link link) {
		link.setId(id);
		return linkService.updateLink(link);
	}
	
	@DELETE
	@Path("/{linkId}")
	public void deleteLink(@PathParam("messageId") long messageId, @PathParam("linkId") long linkId) {
		linkService.removeLink(messageId, linkId);
	}
		
	@GET
	@Path("/{linkId}")
	public Link getLink(@PathParam("messageId") long messageId, @PathParam("linkId") long linkId) {
		return linkService.getLink(messageId, linkId).get();
	}

}
