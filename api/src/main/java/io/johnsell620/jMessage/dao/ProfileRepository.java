package io.johnsell620.jMessage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.johnsell620.jMessage.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>, JpaSpecificationExecutor<Profile> {
    @Query(value = "select p from profiles p where userName = :profileName", nativeQuery = true)
    Profile findByProfileName(@Param("profileName") String profileName);
    
    @Query(value = "select p.profile_id, p.created, p.first_name, p.last_name, p.profile_name, p.user_id "
            + "from profiles p left join messages m on p.profile_id = m.profile_id "
            + "inner join threads t on m.thread_id = t.thread_id "
            + "where t.thread_name = :threadName", nativeQuery = true)
    List<Profile> findProfilesByThreadName(@Param("threadName") String threadName);
}