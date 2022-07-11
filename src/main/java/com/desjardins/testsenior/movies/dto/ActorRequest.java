/**
 * 
 */
package com.desjardins.testsenior.movies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * @author yacinediop
 *
 */
@Data
@Builder
public class ActorRequest {
	
	@JsonProperty("nom")
	private String lastName;
	@JsonProperty("prenom")
	private String firstName;

}
