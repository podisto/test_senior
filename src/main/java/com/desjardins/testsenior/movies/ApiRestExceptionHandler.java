/**
 * 
 */
package com.desjardins.testsenior.movies;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yacinediop
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ApiRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ApiError> handleEntityNotFound(MovieNotFoundException ex) {
		log.info("Handle Movie not found");
		ApiError error = new ApiError(NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(NOT_FOUND).body(error);
	}
}
