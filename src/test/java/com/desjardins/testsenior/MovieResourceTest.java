/**
 * 
 */
package com.desjardins.testsenior;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.desjardins.testsenior.movies.api.MovieResource;
import com.desjardins.testsenior.movies.dto.ActorRequest;
import com.desjardins.testsenior.movies.dto.ActorResponse;
import com.desjardins.testsenior.movies.dto.MovieRequest;
import com.desjardins.testsenior.movies.dto.MovieResponse;
import com.desjardins.testsenior.movies.errors.ApiError;
import com.desjardins.testsenior.movies.errors.MovieNotFoundException;
import com.desjardins.testsenior.movies.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author yacinediop
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MovieResource.class)
class MovieResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private MovieService movieService;

	@Test
	void findById_ShouldReturnAMovie() throws Exception {
		MovieResponse expectedMovie = getExpectedMovie();

		given(movieService.byId(1L)).willReturn(expectedMovie);

		mockMvc.perform(get("/film/1")).andExpect(status().isOk());

	}

	@Test
	void findById_ShouldThrowMovieNotFoundException_WhenProvidedIdNotExists() throws Exception {
		given(movieService.byId(1L)).willThrow(new MovieNotFoundException("Le film avec l'id 1 est introuvable"));
		
		MvcResult result = mockMvc.perform(get("/film/1"))
				.andExpect(status().isNotFound())
				.andReturn();

		ApiError error = new ApiError(HttpStatus.NOT_FOUND, "Le film avec l'id 1 est introuvable");

		String actualResponseBody = result.getResponse().getContentAsString();
		String expectedResponseBody = objectMapper.writeValueAsString(error);
		assertThat(actualResponseBody).isEqualTo(expectedResponseBody);

	}
	
	@Test
	void addAMovie() throws Exception {
		List<ActorRequest> actors = buildActors();
		MovieRequest request = MovieRequest.builder()
				.title("Star Wars: The Empire Strikes Back")
				.description("Darth Vader is adamant about turning Luke Skywalker to the dark side.")
				.actors(actors)
				.build();
		mockMvc.perform(post("/film")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isCreated());
	}
	
	@Test
	void addAMovie_ShouldReturnContentNotSupported_WhenProvidedContentTypeIsNotJsonCompliant() throws Exception {
		List<ActorRequest> actors = buildActors();
		MovieRequest request = MovieRequest.builder()
				.title("Star Wars: The Empire Strikes Back")
				.description("Darth Vader is adamant about turning Luke Skywalker to the dark side.")
				.actors(actors)
				.build();
		mockMvc.perform(post("/film")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().is(415));
	}

	private List<ActorRequest> buildActors() {
		ActorRequest actor1 = ActorRequest.builder().lastName("Ford").firstName("Harrison").build();
		ActorRequest actor2 = ActorRequest.builder().lastName("Hamill").firstName("Mark").build();
		return Arrays.asList(actor1, actor2);
	}

	private MovieResponse getExpectedMovie() {
		MovieResponse movie = new MovieResponse();
		movie.setId(1L);
		movie.setTitle("Star Wars: The Empire Strikes Back");
		movie.setDescription("Darth Vader is adamant about turning Luke Skywalker to the dark side.");
		List<ActorResponse> actors = getActors();
		movie.setActors(actors);
		return movie;
	}

	private List<ActorResponse> getActors() {
		ActorResponse actor1 = ActorResponse.builder().id(2L).lastName("Ford").firstName("Harrison").build();
		ActorResponse actor2 = ActorResponse.builder().id(3L).lastName("Hamill").firstName("Mark").build();
		return Arrays.asList(actor1, actor2);
	}

}
