package umlteacher.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EduNotFoundException extends RuntimeException {
	
	public EduNotFoundException() {}
	
	public EduNotFoundException(int id) {
		super("Edu not found by edu_id " + id);
	}
	
	public EduNotFoundException(String message) {
		super(message);
	}
	
	public EduNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public EduNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
