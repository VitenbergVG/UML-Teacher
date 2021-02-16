package umlteacher.model.dao.chat;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "chat_room_id", length = 4, nullable = false)
    private Long id;

    // chatId = concat: senderId_recipientId
    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "sender_user_id")
    private Long senderUserId;

    @Column(name = "recipient_user_id")
    private Long recipientUserId;
}
