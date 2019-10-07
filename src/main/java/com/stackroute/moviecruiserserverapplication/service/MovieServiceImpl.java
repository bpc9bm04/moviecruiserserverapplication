package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.exception.MoviewAlreadyExistException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;
@Service
public class MovieServiceImpl implements MovieService {
	
	private MovieRepository movieRepository;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public boolean saveMovie(Movie movie) throws MoviewAlreadyExistException {
		if(movieRepository.existsById(movie.getId())) {
			throw new MoviewAlreadyExistException("Movie already present in DB, can't save it!");
		}
		movieRepository.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		Optional<Movie> movieIsPresent=movieRepository.findById(movie.getId());
		if(!movieIsPresent.isPresent()) {
			throw new MovieNotFoundException("Movie does not exist in DB, can't update it's comments!");
		}
		Movie movieFromDB=movieIsPresent.get();
		movieFromDB.setComments(movie.getComments());
		return movieRepository.save(movieFromDB);
	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		if(!movieRepository.existsById(id)) {
			throw new MovieNotFoundException("Movie does not exist in DB, can't delete it!");
		}
		movieRepository.deleteById(id);
		return true;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		Optional<Movie> movieIsPresent=movieRepository.findById(id);
		if(!movieIsPresent.isPresent()) {
			throw new MovieNotFoundException("Movie does not exist in DB!");
		}
		return movieIsPresent.get();
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

}
