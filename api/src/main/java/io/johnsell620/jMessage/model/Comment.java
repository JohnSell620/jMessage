package io.johnsell620.jMessage.model;

// import io.johnsell620.jMessage.model.CustomCreatedDate;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Cacheable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
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
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	@Column(name="commentId")
	private long id;
    private String comment;
	private Long messageId;
    private String author;
	@JsonDeserialize(using=CustomCreatedDate.class)
    private Date created;
    
    public Comment() {}
    
    public Comment(long id, String comment, Long messageId, String author) {
    	this.id = id;
    	this.comment = comment;
    	this.messageId = messageId;
    	this.author = author;
    	this.created = new Date();
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
    
}
