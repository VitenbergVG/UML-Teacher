package umlteacher.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {
	public TaskNotFoundException(int task_id) {
		super("Task not found by task_id " + task_id);
	}
	
	public TaskNotFoundException(String message) {
		super(message);
	}
}
