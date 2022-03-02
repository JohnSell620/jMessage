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
		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long userId) {
		return userRepository.findById(userId);
	}

	public Optional<User> getUserByName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public void removeUser(String userName) {
		userRepository.delete(userRepository.findByUserName(userName).get());
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
