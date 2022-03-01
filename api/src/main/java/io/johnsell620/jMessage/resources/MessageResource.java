/**
 * @api {get} /webapi/messages/:id Request Message
 * @apiName getMessage
 * @apiGroup Resource
 * 
 * @apiParam {long} messageID Message's unique ID.
 * 
 * @apiSuccess {Message} Message object with Profile and Comments entries.
 * @apiSuccess {uri} Message URI.
 * 
 * @apiSuccessExample Success-Response:
 * 		HTTP/1.1 200 OK
 * 		{
 * 			"body": "message body",
 * 			"Profile":  {
 * 							"profilename": "user",
 * 						},
 * 			"Comments": {
 * 							"comment 1": "comment",
 * 								...
 * 						}
 * 		}
 * 
 * @apiErrorExample Error-Response:
 * 		HTTP/1.1 404 Not Found
 * 		{
 * 			"error": "MessageNotFound"
 * 		}
 *
 */
package io.johnsell620.jMessage.resources;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
// import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.web.bind.annotation.RestController;

import io.johnsell620.jMessage.model.Message;
// import io.johnsell620.jMessage.resources.beans.MessageFilterBean;
import io.johnsell620.jMessage.service.MessageService;


@RestController
@Path("/messages")
//@Path("/secured/messages")
public class MessageResource {
	
	@Resource(name = "messageService")
	MessageService messageService;
		
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Message> getJsonMessages(@BeanParam MessageFilterBean filterBean) {
//		System.out.println("JSON method called");
//		if (filterBean.getYear() > 0) {
//			return messageService.getAllMessagesForYear(filterBean.getYear());
//		}
//		if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
//			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
//		}
//		return messageService.getAllMessages();
//	}	
	
//	@GET
//	@Produces(MediaType.TEXT_XML)
//	public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterBean) {
//		System.out.println("XML method called");
//		if (filterBean.getYear() > 0) {
//			return messageService.getAllMessagesForYear(filterBean.getYear());
//		}
//		if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
//			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
//		}
//		return messageService.getAllMessages();
//	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		System.out.print(uriInfo.getAbsolutePath());
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}	
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) { //messageId is a string but Jersey will accept long
		Message message = messageService.getMessage(id).get();
		String uri = getUriForSelf(uriInfo, message);
		message.addLink(uri, "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");
		
		return message;
	}
	
	private String getUriForComments(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build();
		return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build();
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
			.path(MessageResource.class)
			.path(Long.toString(message.getId()))
			.build()
			.toString();
		return uri;
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	@Path("/{messageId}/links")
	public LinkResource getLinkResource() {
		return new LinkResource();
	}

	@GET
	@Path("/{threadName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getThreadMessages(@PathParam("threadName") String threadName, @Context UriInfo uriInfo) {
		return messageService.getThreadMessages(threadName);
	}
	
}
