/**
 * 
 */
package com.desjardins.testsenior.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yacinediop
 *
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

}
