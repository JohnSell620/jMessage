package io.johnsell620.jMessage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.johnsell620.jMessage.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {
    @Query(value = "select m from messages m where id = :messageId", nativeQuery = true)
    Message findByMessageName(@Param("messageId") Long messageId);
    
    @Query(value = "select m from messages m, threads t where m.thread_threadId = t.threadId and m.threadName = :threadName", nativeQuery = true)
    List<Message> findMessagesByThreadName(@Param("threadName") String threadName);
}