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
		return optionalMovie.map(this::toDto).orElseThrow(() -> new MovieNotFoundException("Le film avec l'id " +id+ " est introuvable"));
	}

	private MovieResponse toDto(Movie movie) {
		MovieResponse dto = new MovieResponse();
		dto.setId(movie.getId());
		dto.setTitle(movie.getTitle());
		dto.setDescription(movie.getDescription());
		List<ActorResponse> actors = getActors(movie);
		dto.setActors(actors);
		return null;
	}

	private List<ActorResponse> getActors(Movie movie) {
		return movie.getActors().stream()
				.map(this::toActorDto)
				.collect(toList());
	}

	private ActorResponse toActorDto(Actor actor) {
		return ActorResponse.builder()
				.id(actor.getId())
				.lastName(actor.getLastName())
				.firstName(actor.getFirstName())
				.build();
	}

}
