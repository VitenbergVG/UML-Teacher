package umlteacher.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ChatMessageNotFoundException extends RuntimeException {

    public ChatMessageNotFoundException() {
    }

    public ChatMessageNotFoundException(long id) {
        super("Chat message couldn't be founded by chat_message_id " + id);
    }

    public ChatMessageNotFoundException(String message) {
        super(message);
    }

    public ChatMessageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatMessageNotFoundException(Throwable cause) {
        super(cause);
    }
}
