/**
 * 
 */
package com.desjardins.testsenior.movies;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * @author yacinediop
 *
 */
@Data
@Builder
public class ActorResponse {
	
	private Long id;
	@JsonProperty("nom")
	private String lastName;
	@JsonProperty("prenom")
	private String firstName;

}
