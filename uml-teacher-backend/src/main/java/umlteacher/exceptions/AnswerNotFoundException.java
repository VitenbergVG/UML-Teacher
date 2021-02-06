package umlteacher.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AnswerNotFoundException extends RuntimeException {
	
	public AnswerNotFoundException(int answer_id) {
		super("Answer not found by answer_id " + answer_id);
	}
	
	public AnswerNotFoundException(String message) {
		super(message);
	}
}
