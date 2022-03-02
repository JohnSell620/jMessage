package io.johnsell620.jMessage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.johnsell620.jMessage.dao.ProfileRepository;
import io.johnsell620.jMessage.model.Profile;
/**
 * 
 * @author johnny
 *
 */
@Service("profileService")
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	public Profile addProfile(Profile profile) {
		return profileRepository.save(profile);
	}

	public Optional<Profile> getProfile(Long profileId) {
		return profileRepository.findById(profileId);
	}

	public Profile updateProfile(Profile profile) {
		return profileRepository.save(profile);
	}

	public void removeProfile(String profileName) {
		profileRepository.delete(profileRepository.findByProfileName(profileName));
	}

	public List<Profile> getAllProfiles() {
		return profileRepository.findAll();
	}

	public List<Profile> getThreadProfiles(String threadName) {
		System.out.println("threadName = " + threadName);
		return profileRepository.findProfilesByThreadName(threadName);
	}

}
