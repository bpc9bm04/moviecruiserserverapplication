package com.stackroute.moviecruiserserverapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.exception.MoviewAlreadyExistException;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

@RestController
@RequestMapping(path = "/api/movie")
@CrossOrigin
public class MovieController {
	
	private MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	/**
	 * This method is used to Save a new Movie in DB
	 * 
	 * @param movie
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveNewMovie(@RequestBody final Movie movie) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MoviewAlreadyExistException e) {
			responseEntity = new ResponseEntity<String>("message:" + e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	/**
	 * This method is used to update the existing Movie comments in DB
	 * @param id
	 * @param movie
	 * @return
	 */
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") final Integer id, @RequestBody final Movie movie) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("message:" + e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(id);
			responseEntity = new ResponseEntity<String>("Movie deleted successfully!", HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("message:" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	/**
	 * This method is used to fetch an existing Movie from DB based on id
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		Movie movieFromDB=null;
		try {
			movieFromDB=movieService.getMovieById(id);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("message:" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<Movie>(movieFromDB, HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * This method is used to fetch all existing Movie from DB
	 * @return List<Movie>
	 */
	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovies() {
		return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
	}
}
