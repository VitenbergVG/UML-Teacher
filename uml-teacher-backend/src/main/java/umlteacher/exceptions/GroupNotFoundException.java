package umlteacher.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GroupNotFoundException extends RuntimeException {
	public GroupNotFoundException() {}
	
	public GroupNotFoundException(int id) {
		super("Group not found by group_id " + id);
	}
	
	public GroupNotFoundException(String message) {
		super(message);
	}
	
	public GroupNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public GroupNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
