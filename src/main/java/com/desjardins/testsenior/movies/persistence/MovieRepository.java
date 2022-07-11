/**
 * 
 */
package com.desjardins.testsenior.movies.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desjardins.testsenior.movies.model.Movie;

/**
 * @author yacinediop
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
