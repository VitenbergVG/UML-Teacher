package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umlteacher.model.dao.chat.ChatMessage;
import umlteacher.model.impl.chat.MessageStatus;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

	@Query(value = "SELECT count(*) FROM chat_message WHERE sender_user_id = ?1 " +
			"and recipient_user_id = ?2 and status = ?3", nativeQuery = true)
	long countBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, MessageStatus status);

	List<ChatMessage> findByChatId(String chatId);
}
