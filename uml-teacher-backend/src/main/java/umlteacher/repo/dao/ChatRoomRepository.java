package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import umlteacher.model.dao.chat.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    ChatRoom findBySenderUserIdAndRecipientUserId(Long senderUserId, Long recipientUserId);
}