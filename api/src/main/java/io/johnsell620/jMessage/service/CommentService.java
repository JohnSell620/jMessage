package io.johnsell620.jMessage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.johnsell620.jMessage.dao.CommentRepository;
import io.johnsell620.jMessage.model.Comment;
/**
 * 
 * @author johnny
 *
 */
@Service("commentService")
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public Comment addComment(Long messageId, Comment comment) {
		return commentRepository.save(comment);
	}

	public Optional<Comment> getComment(Long commentId) {
		return commentRepository.findById(commentId);
	}

	public Comment updateComment(Long messageId, Comment comment) {
		return commentRepository.save(comment);
	}

	public void removeComment(Long messageId, Long commentId) {
		commentRepository.deleteCommentByMessage(messageId, commentId);;
	}

	public void removeComments(Long messageId, Long commentId) {
		commentRepository.deleteCommentsByMessage(messageId);;
	}

	public List<Comment> getAllComments(Long messageId) {
		return commentRepository.findCommentsByMessage(messageId);
	}
}
