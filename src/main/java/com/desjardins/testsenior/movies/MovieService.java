/**
 * 
 */
package com.desjardins.testsenior.movies;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

import org.springframework.stereotype.Service;

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
		Movie movie = new Movie();
		movie.setTitle(request.getTitle());
		movie.setDescription(request.getDescription());
		List<Actor> actors = getActors(request);
		for (Actor a: actors) {
			movie.addActor(a);
		}
		return movie;
	}
	
	private List<Actor> getActors(MovieRequest request) {
		return request.getActors().stream().map(this::toActorEntity).collect(toList());
	}

	private Actor toActorEntity(ActorRequest request) {
		Actor actor = new Actor();
		actor.setLastName(request.getLastName());
		actor.setFirstName(request.getFirstName());
		return actor;
	}
	
	private ActorResponse toActorDto(Actor actor) {
		return ActorResponse.builder()
				.id(actor.getId())
				.lastName(actor.getLastName())
				.firstName(actor.getFirstName())
				.build();
	}


}
