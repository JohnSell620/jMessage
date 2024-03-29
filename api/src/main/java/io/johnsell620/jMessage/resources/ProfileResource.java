package io.johnsell620.jMessage.resources;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.johnsell620.jMessage.model.Profile;
import io.johnsell620.jMessage.service.ProfileService;

@RestController
@Path("/profiles")
//@Path("/secured/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	@Resource(name = "profileService")
	private ProfileService profileService;
	
	@GET
	public List<Profile> getProfiles() {
		return profileService.getAllProfiles();
	}

	@GET
	@Path("/threads/{threadName}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Profile>> getThreadProfiles(@PathParam("threadName") String threadName) {
		System.out.println("threadName = " + threadName);
		return new ResponseEntity<>(profileService.getThreadProfiles(threadName), HttpStatus.OK);
	}
	
	@POST
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
	}
	
	@GET
	@Path("/{profileId}")
	public Optional<Profile> getProfile(@PathParam("profileId") Long profileId) {
		return profileService.getProfile(profileId);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
	}

}
