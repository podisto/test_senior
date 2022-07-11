/**
 * 
 */
package com.desjardins.testsenior.movies;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author yacinediop
 *
 */
@Data
@Entity
@Table(name = "actors")
public class Actor {

	@Id
	private Long id;
	private String lastName;
	private String firstName;
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
}
