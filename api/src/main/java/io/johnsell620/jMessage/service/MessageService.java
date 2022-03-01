package io.johnsell620.jMessage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.johnsell620.jMessage.dao.MessageRepository;
import io.johnsell620.jMessage.model.Message;
/**
 * 
 * @author johnny
 *
 */
@Service("messageService")
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public Message addMessage(Message message) {
		return messageRepository.save(message);
	}

	public Optional<Message> getMessage(Long messageId) {
		return messageRepository.findById(messageId);
	}

	public Message updateMessage(Message message) {
		return messageRepository.save(message);
	}

	public void removeMessage(Long messageId) {
		messageRepository.delete(messageRepository.findByMessageName(messageId));
	}

	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	public List<Message> getThreadMessages(String threadName) {
		return messageRepository.findMessagesByThreadName(threadName);
	}

}
