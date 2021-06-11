package one.digitalinnovation.personapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExceptio extends Exception {

	public PersonNotFoundExceptio (Long id) {
		super("Person not found with id: " + id);
	}
}
