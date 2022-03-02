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

import io.johnsell620.jMessage.model.Comment;
import io.johnsell620.jMessage.service.CommentService;

@RestController
@Path("/messages/{messageId}/comments")
//@Path("/secured/comments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	@Resource(name = "commentService")
	private CommentService commentService;
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentService.getAllComments(messageId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, 
								 @PathParam("commentId") long id, Comment comment) {
		comment.setId(id);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId, 
							  @PathParam("commentId") long commentId) {
		commentService.removeComment(messageId, commentId);
	}
		
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, 
							  @PathParam("commentId") long commentId) {
		return commentService.getComment(messageId, commentId).get();
	}

}
