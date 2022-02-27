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
// import javax.persistence.OneToMany;
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
	private User creatingUser;
	
	
	public Thread() {}
	
	public Thread(String threadName, long creatorId, User creatingUser) {
		this.threadName = threadName;
		this.creatorId = creatorId;
		this.creatingUser = creatingUser;
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

	public User getCreatingUser() {
		return creatingUser;
	}

	public void setCreatingUser(User creatingUser) {
		this.creatingUser = creatingUser;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}
