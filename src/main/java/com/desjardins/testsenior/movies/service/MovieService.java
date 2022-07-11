/**
 * 
 */
package com.desjardins.testsenior.movies.service;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

import org.springframework.stereotype.Service;

import com.desjardins.testsenior.movies.dto.ActorRequest;
import com.desjardins.testsenior.movies.dto.ActorResponse;
import com.desjardins.testsenior.movies.dto.MovieRequest;
import com.desjardins.testsenior.movies.dto.MovieResponse;
import com.desjardins.testsenior.movies.errors.MovieNotFoundException;
import com.desjardins.testsenior.movies.model.Actor;
import com.desjardins.testsenior.movies.model.Movie;
import com.desjardins.testsenior.movies.persistence.MovieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yacinediop
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {
	
	private final MovieRepository movieRepository;
	
	public MovieResponse byId(Long id) {
		log.info("find movie by id {} ", id);
		Optional<Movie> optionalMovie = movieRepository.findById(id);
		return optionalMovie.map(this::toMovieDto).orElseThrow(() -> new MovieNotFoundException("Le film avec l'id " +id+ " est introuvable"));
	}
	
	public MovieResponse add(MovieRequest request) {
		log.info("Add movie {} ", request);
		Movie movie = toMovieEntity(request);
		Movie created = movieRepository.save(movie);
		return toMovieDto(created);
	}
	

	private MovieResponse toMovieDto(Movie movie) {
		MovieResponse dto = new MovieResponse();
		dto.setId(movie.getId());
		dto.setTitle(movie.getTitle());
		dto.setDescription(movie.getDescription());
		List<ActorResponse> actorsDto = movie.getActors().stream().map(this::toActorDto).collect(toList());
		dto.setActors(actorsDto);
		return dto;
	}


	private Movie toMovieEntity(MovieRequest request) {
		List<Actor> actors = request.getActors().stream().map(this::toActorEntity).collect(toList());
		return new Movie(request.getTitle(), request.getDescription(), actors);
	}

	private Actor toActorEntity(ActorRequest request) {
		return new Actor(request.getLastName(), request.getFirstName());
	}
	
	private ActorResponse toActorDto(Actor actor) {
		return ActorResponse.builder()
				.id(actor.getId())
				.lastName(actor.getLastName())
				.firstName(actor.getFirstName())
				.build();
	}


}
