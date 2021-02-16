package umlteacher.service.impl.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umlteacher.model.dao.chat.ChatRoom;
import umlteacher.repo.dao.ChatRoomRepository;

import java.util.Objects;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public String getChatId(Long senderId, Long recipientId, boolean createIfNotExist) {
        ChatRoom chatRoom = chatRoomRepository.findBySenderUserIdAndRecipientUserId(senderId, recipientId);
        if (Objects.isNull(chatRoom) && createIfNotExist) {
            String chatId = String.format("%s_%s", senderId, recipientId);

            ChatRoom senderRecipient = new ChatRoom();
            senderRecipient.setChatId(chatId);
            senderRecipient.setSenderUserId(senderId);
            senderRecipient.setRecipientUserId(recipientId);

            ChatRoom recipientSender = new ChatRoom();
            senderRecipient.setChatId(chatId);
            senderRecipient.setSenderUserId(recipientId);
            senderRecipient.setRecipientUserId(senderId);
            chatRoomRepository.save(senderRecipient);
            chatRoomRepository.save(recipientSender);
            return chatId;
        }
        return chatRoom.getChatId();
    }
}
