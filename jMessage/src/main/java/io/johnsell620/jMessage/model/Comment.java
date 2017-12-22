package io.johnsell620.jMessage.model;

import java.util.Date;
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
@Table(name="comments")
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Comment {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="commentId")
	private long id;
    private String message;
	private Long messageId;
    private Date created;
    private String author;
    
    public Comment() {
    	
    }
    
    public Comment(long id, String message, String author) {
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

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
    
}
