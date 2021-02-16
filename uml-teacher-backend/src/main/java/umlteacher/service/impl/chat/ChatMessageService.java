package umlteacher.service.impl.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umlteacher.exceptions.ChatMessageNotFoundException;
import umlteacher.model.dao.chat.ChatMessage;
import umlteacher.model.impl.chat.MessageStatus;
import umlteacher.repo.dao.ChatMessageRepository;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository repository;
    @Autowired
    private ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(Long senderId, Long recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(Long senderId, Long recipientId) {
        String chatId = chatRoomService.getChatId(senderId, recipientId, false);
        List<ChatMessage> messages = repository.findByChatId(chatId);
        if (messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }
        return messages;
    }

    public ChatMessage findById(Long id) {
        return repository.findById(id).map(chatMessage -> {
            chatMessage.setStatus(MessageStatus.DELIVERED);
            return repository.save(chatMessage);
        }).orElseThrow(() -> new ChatMessageNotFoundException(id));
    }

    public void updateStatuses(Long senderId, Long recipientId, MessageStatus status) {
//        Query query = new Query(
//                Criteria
//                        .where("senderId").is(senderId)
//                        .and("recipientId").is(recipientId));
//        Update update = Update.update("status", status);
//        mongoOperations.updateMulti(query, update, ChatMessage.class);
    }
}
