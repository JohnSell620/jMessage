package io.johnsell620.jMessage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.johnsell620.jMessage.model.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long>, JpaSpecificationExecutor<Link> {
    @Query(value = "delete l from links, messages m where l.messageId = m.id and m.id = :messageId", nativeQuery = true)
    List<Link> deleteLinksByMessage(@Param("messageId") Long messageId);

    @Query(value = "select distinct l from links l, messages m where l.messageId = m.id and m.id = :messageId", nativeQuery = true)
    List<Link> findLinksByMessage(@Param("messageId") Long messageId);
}