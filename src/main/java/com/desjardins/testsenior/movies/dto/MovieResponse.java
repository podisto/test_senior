/**
 * 
 */
package com.desjardins.testsenior.movies.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author yacinediop
 *
 */
@Data
public class MovieResponse {
	
	private Long id;
	@JsonProperty("titre")
	private String title;
	@JsonProperty("description")
	private String description;
	@JsonProperty("acteurs")
	private List<ActorResponse> actors = new ArrayList<>();

}
