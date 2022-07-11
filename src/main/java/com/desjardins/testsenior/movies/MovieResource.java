/**
 * 
 */
package com.desjardins.testsenior.movies;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yacinediop
 *
 */
@RestController
@RequestMapping(value = "/film", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class MovieResource {
	
	private final MovieService movieService;

	@GetMapping("/{id}")
	public ResponseEntity<MovieResponse> findById(@PathVariable("id") Long id) {
		log.info("Get Movie by id {}", id);
		MovieResponse movie = movieService.byId(id);
		return ResponseEntity.ok(movie);
	}
	
}
