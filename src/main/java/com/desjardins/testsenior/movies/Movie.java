/**
 * 
 */
package com.desjardins.testsenior.movies;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author yacinediop
 *
 */
@Data
@Entity
@Table(name = "movies")
public class Movie {
	
	@Id
	private Long id;
	private String title;
	private String description;
	@OneToMany
	private List<Actor> actors;

}
