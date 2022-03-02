package io.johnsell620.jMessage.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.johnsell620.jMessage.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    @Query(value = "delete c from comments, messages m where c.message_id = m.id and m.id = :messageId and c.comment_id = :commentId", nativeQuery = true)
    void deleteCommentByMessage(@Param("messageId") Long messageId, @Param("commentId") Long commentId);

    @Query(value = "delete c from comments, messages m where c.message_id = m.id and m.id = :messageId", nativeQuery = true)
    void deleteAllCommentsByMessage(@Param("messageId") Long messageId);

    @Query(value = "select * from comments c, messages m where c.message_id = m.id and m.id = :messageId and c.comment_id = :commentId", nativeQuery = true)
    Optional<Comment> findCommentByMessage(@Param("messageId") Long messageId, @Param("commentId") Long commentId);

    @Query(value = "select * from comments c, messages m where c.message_id = m.id and m.id = :messageId", nativeQuery = true)
    List<Comment> findAllCommentsByMessage(@Param("messageId") Long messageId);
}
