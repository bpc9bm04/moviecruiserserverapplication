/**
 * 
 */
package com.stackroute.moviecruiserserverapplication.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.exception.MoviewAlreadyExistException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

/**
 * @author ubuntu
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

	@Mock
	private MovieRepository movieRepositoryMock;

	private Movie movie;

	@InjectMocks
	private MovieServiceImpl movieServiceImpl;

	private Optional<Movie> options;

	@Before
	public void setUp() {
		movie = new Movie(1, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019");
		options = Optional.of(movie);
	}

	/**
	 * @throws MoviewAlreadyExistException
	 */
	@Test
	public void testSaveMovieSuccess() throws MoviewAlreadyExistException {
		when(movieRepositoryMock.save(movie)).thenReturn(movie);
		boolean movieSaved = movieServiceImpl.saveMovie(movie);
		assertTrue("Movie saved successfully", movieSaved);
		verify(movieRepositoryMock, times(1)).existsById(movie.getId());
		verify(movieRepositoryMock, times(1)).save(movie);
	}

	/**
	 * @throws MoviewAlreadyExistException
	 */
	@Test(expected = MoviewAlreadyExistException.class)
	public void testSaveMovieFailure() throws MoviewAlreadyExistException {
		when(movieRepositoryMock.existsById(movie.getId())).thenReturn(true);
		movieServiceImpl.saveMovie(movie);
	}

	/**
	 * @throws MovieNotFoundException
	 */
	@Test
	public void testUpdateMovieSuccess() throws MovieNotFoundException {
		when(movieRepositoryMock.findById(movie.getId())).thenReturn(options);
		when(movieRepositoryMock.save(movie)).thenReturn(movie);
		movieServiceImpl.updateMovie(movie);
		assertEquals("Nice Movie", movie.getComments());
		verify(movieRepositoryMock, times(1)).findById(movie.getId());
		verify(movieRepositoryMock, times(1)).save(movie);
	}

	/**
	 * @throws MovieNotFoundException
	 */
	@Test(expected = MovieNotFoundException.class)
	public void testUpdateMovieFailure() throws MovieNotFoundException {
		movieServiceImpl.updateMovie(movie);
	}

	/**
	 * @throws MovieNotFoundException
	 */
	@Test
	public void testDeleteMovieByIdSuccess() throws MovieNotFoundException {
		when(movieRepositoryMock.existsById(movie.getId())).thenReturn(true);
		boolean movieDeleted = movieServiceImpl.deleteMovieById(movie.getId());
		assertTrue("Movie deleted successfully", movieDeleted);
		verify(movieRepositoryMock, times(1)).existsById(movie.getId());
		verify(movieRepositoryMock, times(1)).deleteById(movie.getId());
	}

	/**
	 * @throws MovieNotFoundException
	 */
	@Test(expected = MovieNotFoundException.class)
	public void testDeleteMovieByIdFailure() throws MovieNotFoundException {
		movieServiceImpl.deleteMovieById(movie.getId());
	}

	/**
	 * @throws MovieNotFoundException
	 */
	@Test
	public void testGetMovieByIdSuccess() throws MovieNotFoundException {
		when(movieRepositoryMock.findById(movie.getId())).thenReturn(options);
		movieServiceImpl.getMovieById(movie.getId());
		verify(movieRepositoryMock, times(1)).findById(movie.getId());
	}

	/**
	 * @throws MovieNotFoundException
	 */
	@Test(expected = MovieNotFoundException.class)
	public void testGetMovieByIdFailure() throws MovieNotFoundException {
		movieServiceImpl.getMovieById(movie.getId());
	}

	@Test
	public void testGetAllMovies() {
		List<Movie> allMovies = new ArrayList<Movie>();
		allMovies.add(movie);
		when(movieRepositoryMock.findAll()).thenReturn(allMovies);
		List<Movie> Movies = movieServiceImpl.getAllMovies();
		assertEquals(Movies, allMovies);
		verify(movieRepositoryMock, times(1)).findAll();
	}
}
