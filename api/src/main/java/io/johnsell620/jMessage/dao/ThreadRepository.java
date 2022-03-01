package io.johnsell620.jMessage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.johnsell620.jMessage.model.Profile;
import io.johnsell620.jMessage.model.Thread;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long>, JpaSpecificationExecutor<Thread> {
    @Query(value = "select t from threads t where threadName = :threadName", nativeQuery = true)
    Thread findByThreadName(@Param("threadName") String threadName);

    
    @Query(value = "select distinct p from profiles p, messages m where p.id = m.profileId and m.threadName = :threadName", nativeQuery = true)
    List<Profile> findProfilesByThreadName(@Param("threadName") String threadName);
}