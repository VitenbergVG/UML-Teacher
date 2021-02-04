package umlteacher.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException {
	public CourseNotFoundException() {}
	
	public CourseNotFoundException(int id) {
		super("Course not found by course_id" + id);
	}
	
	public CourseNotFoundException(String message) {
		super(message);
	}
	
	public CourseNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public CourseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
