package io.johnsell620.jMessage.model;

//import io.johnsell620.jMessage.model.CustomCreatedDate;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.xml.bind.annotation.XmlRootElement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="threads")
@XmlRootElement
public class Thread {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="threadId")
	private long id; 
	private String threadName;
	private long creatorId;
	@JsonDeserialize(using=CustomCreatedDate.class)
	private Date created;
	
	@OneToOne
	private User user;
	
	
	public Thread() {}
	
	public Thread(String threadName, long creatorId, User user) {
		this.threadName = threadName;
		this.creatorId = creatorId;
		this.user = user;
		this.created = new Date();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}
