package umlteacher.model.dao.chat;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import umlteacher.model.impl.chat.MessageStatus;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "chat_message_id", length = 4, nullable = false)
    private Long id;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "sender_user_id")
    private Long senderUserId;

    @Column(name = "recipient_user_id")
    private Long recipientUserId;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
}
