package com.stackroute.moviecruiserserverapplication.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.moviecruiserserverapplication.domain.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepositoryTest {

	private transient MovieRepository movieRepo;

	@Autowired
	public void setMovieRepo(MovieRepository movieRepo) {
		this.movieRepo = movieRepo;
	}

	@Test
	public void testSaveMovie() {
		movieRepo.save(new Movie(1, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019"));
		Movie movie = movieRepo.getOne(1);
		assertEquals(1, movie.getId());
	}

	@Test
	public void testUpdateMovie() {
		movieRepo.save(new Movie(1, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019"));
		Movie movie = movieRepo.getOne(1);
		movie.setComments("Awesome Movie");
		movieRepo.save(movie);
		Movie updatedMovie = movieRepo.getOne(1);
		assertEquals("Awesome Movie", updatedMovie.getComments());
	}

	@Test
	public void testDeleteMovie() {
		movieRepo.save(new Movie(1, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019"));
		Movie movie = movieRepo.getOne(1);
		movieRepo.delete(movie);
		assertFalse(movieRepo.existsById(1));
	}

	@Test
	public void testGetMovieById() {
		movieRepo.save(new Movie(1, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019"));
		assertTrue(movieRepo.existsById(1));
	}

	@Test
	public void testGetAllMovies() {
		movieRepo.save(new Movie(1, "Avengers", "Nice Movie", "www.movieworld.com", "06-06-2019"));
		movieRepo.save(new Movie(2, "Sholey", "Super Hit Movie", "www.movieworld.com", "18-07-2019"));
		List<Movie> movies = movieRepo.findAll();
		//The above findAll() method will pick Avengers,Sholey and all the saved records in DB
		assertTrue(movies.size()>=2);
	}
}
