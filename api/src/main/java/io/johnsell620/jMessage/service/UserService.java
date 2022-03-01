package io.johnsell620.jMessage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.johnsell620.jMessage.dao.UserRepository;
import io.johnsell620.jMessage.model.User;
/**
 * 
 * @author johnny
 *
 */
@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		return userRepository.save(createUserInstance(user));
	}

	public Optional<User> getUser(Long userId) {
		return userRepository.findById(userId);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public void removeUser(String userName) {
		userRepository.delete(userRepository.findByUserName(userName));
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	private User createUserInstance(User user) {
		User userModel = new User();
		userModel.setUsername(user.getUsername());
		userModel.setPassword(user.getPassword());
		// userModel.setProfile(user.getProfile());
		userModel.setId(user.getId());
		userModel.setpId(user.getpId());
		return userModel;
	}
}
