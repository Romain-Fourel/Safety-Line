package com.safetyline.apptest.dao;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	Long id;

	String name;
	String hashPassword;
	List<Movie> movies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Long getId() {
		return id;
	}

	public Movie addMovie(Movie movie) {
		movies.add(movie);
		return movie;
	}

	public void removeMovie(Movie movie) {
		movies.remove(movie);
	}

	@Override
	public boolean equals(Object obj) {
		User user = (User) obj;
		return user.getId().equals(getId());
	}

}
