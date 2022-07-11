/**
 * 
 */
package com.desjardins.testsenior.movies;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Actor> actors = new ArrayList<>();
	
	public void addActor(Actor anActor) {
		this.actors.add(anActor);
		anActor.setMovie(this);
	}
}
