package io.johnsell620.jMessage.model;

//import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="messages")
@XmlRootElement
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String message;
	private String author;
	private Date created;
	
	@OneToMany(mappedBy="messageId", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@MapKeyColumn(name="commentId")
	private Map<Long, Comment> comments = new HashMap<>();
	
	@OneToMany(mappedBy="messageId", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@MapKeyColumn(name="linkId")
	private Map<Long, Link> links = new HashMap<>();
//	private List<Link> links = new ArrayList<>();
	
	public Message() {
		
	}
	
	public Message(long id, String message, String author) {
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = new Date();
	}
	
	public long getId() {
		return id;
	}	
	public void setId(long id) {
		this.id = id;
	}	
	public String getMessage() {
		return message;
	}	
	public void setMessage(String message) {
		this.message = message;
	}	
	public Date getCreated() {
		return created;
	}	
	public void setCreated(Date created) {
		this.created = created;
	}	
	public String getAuthor() {
		return author;
	}	
	public void setAuthor(String author) {
		this.author = author;
	}	
	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
	public void setLinks(Map<Long, Link> links) {
		this.links = links;
	}
	public Map<Long, Link> getLinks() {
		return links;
	}

//	public List<Link> getLinks() {
//		return links;
//	}
//
//	public void setLinks(List<Link> links) {
//		this.links = links;
//	}
	
	//TODO fix to eliminate LinkResource, etc.
	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.put(id, link);
	}

}
