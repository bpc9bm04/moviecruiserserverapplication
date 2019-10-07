package com.stackroute.moviecruiserserverapplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public Movie(int id, String name, String comments, String posterPath, String releaseDate) {
		this.id = id;
		this.title = name;
		this.comments = comments;
		this.poster_path = posterPath;
		this.release_date = releaseDate;
	}
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String title;
	@Column(name = "comments")
	private String comments;
	@Column(name = "poster_path")
	private String poster_path;
	@Column(name = "release_date")
	private String release_date;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the poster_path
	 */
	public String getPoster_path() {
		return poster_path;
	}

	/**
	 * @param poster_path the poster_path to set
	 */
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	/**
	 * @return the release_date
	 */
	public String getRelease_date() {
		return release_date;
	}

	/**
	 * @param release_date the release_date to set
	 */
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	
}
