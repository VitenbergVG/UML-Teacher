package umlteacher.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadFiledValueException extends RuntimeException {
	
	public BadFiledValueException(String message) {
		super(message);
	}

}
