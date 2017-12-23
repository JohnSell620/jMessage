package io.johnsell620.jMessage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 
 * @author johnny
 *
 */
@Entity
@Table(name="users")
public class User {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private long pId;

	@OneToOne(targetEntity=Profile.class, mappedBy="user")
	@MapKeyColumn(name="profileId")
	private Profile profile; 
		

	public User() {}
	
	public User(String username, String password, long pId, Profile profile) {
		this.username = username;
		this.password = password;
		this.pId = pId;
		this.profile = profile;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getpId() {
		return pId;
	}
	public void setpId(long pId) {
		this.pId = pId;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
