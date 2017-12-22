package io.johnsell620.jMessage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Cacheable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * 
 * @author johnny
 *
 */
@Entity
@Table(name="links")
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Link {

	@Id
	@Column(name="linkId")
	private long id;
	private String link;
	private String rel;
	private Long messageId;
    
    public Link() {
    	
    }
    
    public Link(long id, String link) {
    	this.id = id;
    	this.link = link;
    }

	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	
} 
// In the java documentation there is a provided link class that could be 
// more suited for this.
