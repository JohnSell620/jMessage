package io.johnsell620.jMessage.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.johnsell620.jMessage.model.Thread;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long>, JpaSpecificationExecutor<Thread> {
    @Query(value = "select t from threads t where threadName = :threadName", nativeQuery = true)
    Thread findByThreadName(@Param("threadName") String threadName);

    @Query(value = "select distinct * from threads where thread_name = :threadName", nativeQuery = true)
    Optional<Thread> findByName(@Param("threadName") String threadName);
}