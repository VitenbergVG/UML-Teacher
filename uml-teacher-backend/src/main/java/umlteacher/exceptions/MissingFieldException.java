package umlteacher.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingFieldException extends RuntimeException {
	
	public MissingFieldException(String fieldName, String typeName) {
		super("missing " + fieldName + " field for type " + typeName);
	}
}
