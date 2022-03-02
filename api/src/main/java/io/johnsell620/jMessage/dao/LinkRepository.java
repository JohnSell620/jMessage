package io.johnsell620.jMessage.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.johnsell620.jMessage.model.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long>, JpaSpecificationExecutor<Link> {
    @Query(value = "delete l from links, messages m where l.message_id = m.id and m.id = :messageId", nativeQuery = true)
    void deleteAllLinksByMessage(@Param("messageId") Long messageId);

    @Query(value = "delete l from links l, messages m where l.message_id = m.id and m.id = :messageId and l.link_id = :linkId", nativeQuery = true)
    void deleteLinkByMessage(@Param("messageId") Long messageId, @Param("linkId") Long linkId);

    @Query(value = "select * from links l, messages m where l.message_id = m.id and m.id = :messageId and l.link_id = :linkId", nativeQuery = true)
    Optional<Link> findLinkByMessage(@Param("messageId") Long messageId, @Param("linkId") Long linkId);

    @Query(value = "select * from links l, messages m where l.message_id = m.id and m.id = :messageId", nativeQuery = true)
    List<Link> findAllLinksByMessage(@Param("messageId") Long messageId);
}