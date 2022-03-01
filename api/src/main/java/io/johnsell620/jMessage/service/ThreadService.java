package io.johnsell620.jMessage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.johnsell620.jMessage.dao.ThreadRepository;
import io.johnsell620.jMessage.model.Profile;
import io.johnsell620.jMessage.model.Thread;
/**
 * 
 * @author johnny
 *
 */
@Service("threadService")
public class ThreadService {

	@Autowired
	private ThreadRepository threadRepository;

	public Thread addThread(Thread thread) {
		return threadRepository.save(thread);
	}

	public Optional<Thread> getThread(Long threadId) {
		return threadRepository.findById(threadId);
	}

	public Thread updateThread(Thread thread) {
		return threadRepository.save(thread);
	}

	public void removeThread(String threadName) {
		threadRepository.delete(threadRepository.findByThreadName(threadName));
	}

	public List<Thread> getAllThreads() {
		return threadRepository.findAll();
	}

	public List<Profile> getThreadProfiles(String threadName) {
		return threadRepository.findProfilesByThreadName(threadName);
	}

}
