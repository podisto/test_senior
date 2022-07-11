/**
 * 
 */
package com.desjardins.testsenior.movies;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * @author yacinediop
 *
 */
@Data
public class ApiError {
	
	private HttpStatus status;
	private LocalDateTime timestamp;
	private String message;
	
	private ApiError() {
		this.timestamp = LocalDateTime.now();
	}
	
	public ApiError(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

}
