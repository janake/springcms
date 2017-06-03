package org.prodet.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
		value=HttpStatus.NOT_FOUND, 
		reason="Entity not found"
		)
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(Class<?> entityClass, Long id) {
		super(buildMessage(entityClass, id));
	}

	private static String buildMessage(Class<?> entityClass, Long id) {
		StringBuilder message = new StringBuilder(entityClass.getSimpleName());
		message.append(" with id ");
		message.append(id);
		message.append(" node found.");
		return message.toString();
	}

}
