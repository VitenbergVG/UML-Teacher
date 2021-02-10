package umlteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import umlteacher.model.dao.User;
import umlteacher.model.dao.chat.ChatMessage;
import umlteacher.model.impl.chat.ChatNotification;
import umlteacher.service.dao.UserServiceImpl;
import umlteacher.service.impl.chat.ChatMessageService;
import umlteacher.service.impl.chat.ChatRoomService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/messenger")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private UserServiceImpl userService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        String chatId = chatRoomService.getChatId(chatMessage.getSenderUserId(),
                chatMessage.getRecipientUserId(), true);
        chatMessage.setChatId(chatId);

        ChatMessage saved = chatMessageService.save(chatMessage);
        User sender = userService.findUserById(saved.getSenderUserId());
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientUserId().toString(),
                "/queue/messages",
                new ChatNotification(saved.getId(), saved.getSenderUserId(), sender.getFullname()));
    }

    @GetMapping("/users")
    public List<User> get() {
        return userService.getAllUsers();
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable Long senderId,
            @PathVariable Long recipientId) {

        return ResponseEntity.ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages(@PathVariable Long senderId,
                                              @PathVariable Long recipientId) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage(@PathVariable Long id) {
        return ResponseEntity.ok(chatMessageService.findById(id));
    }
}
