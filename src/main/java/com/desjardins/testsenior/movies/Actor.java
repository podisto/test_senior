/**
 * 
 */
package com.desjardins.testsenior.movies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yacinediop
 *
 */
@Getter
@Setter
@Entity
@Table(name = "actors")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String lastName;
	
	private String firstName;
	
	@ManyToOne
	private Movie movie;
	
}
