/**
 * 
 */
package com.stackroute.moviecruiserserverapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.moviecruiserserverapplication.domain.Movie;

/**
 * @author ubuntu
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
