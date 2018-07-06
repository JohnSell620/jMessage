package io.johnsell620.jMessage.model;

import io.johnsell620.jMessage.model.CustomCreatedDate;
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
@Table(name="profiles")
@XmlRootElement
public class Profile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="profileId")
	private long id; 
	private String profileName;
	private String firstName;
	private String lastName;
	@JsonDeserialize(using=CustomCreatedDate.class)
	private Date created;
	
	@OneToOne
	private User user;
	
	
	public Profile() {}
	
	public Profile(String profileName, String firstName, String lastName, User user) {
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.user = user;
		this.created = new Date();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
