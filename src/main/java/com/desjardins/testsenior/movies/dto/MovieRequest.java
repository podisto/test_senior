/**
 * 
 */
package com.desjardins.testsenior.movies.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * @author yacinediop
 *
 */
@Data
@Builder
public class MovieRequest {
	
	@JsonProperty("titre")
	private String title;
	
	private String description;
	
	@JsonProperty("acteurs")
	@Builder.Default
	private List<ActorRequest> actors = new ArrayList<>();

}
