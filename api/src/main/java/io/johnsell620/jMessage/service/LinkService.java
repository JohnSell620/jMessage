package io.johnsell620.jMessage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.johnsell620.jMessage.dao.LinkRepository;
import io.johnsell620.jMessage.model.Link;
/**
 * 
 * @author johnny
 *
 */
@Service("linkService")
public class LinkService {

	@Autowired
	private LinkRepository linkRepository;

	public Link addLink(Long messageId, Link link) {
		return linkRepository.save(link);
	}

	public Optional<Link> getLink(Long messageId, Long linkId) {
		// return linkRepository.findById(linkId);
		return linkRepository.findLinkByMessage(messageId, linkId);
	}

	public Link updateLink(Link link) {
		return linkRepository.save(link);
	}

	public void removeLink(Long messageId, Long linkId) {
		linkRepository.deleteLinkByMessage(messageId, linkId);
	}

	public List<Link> getAllLinks(Long messageId) {
		return linkRepository.findAllLinksByMessage(messageId);
	}
}
