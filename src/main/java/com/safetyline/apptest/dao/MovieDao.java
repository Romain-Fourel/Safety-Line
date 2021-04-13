package com.safetyline.apptest.dao;

public interface MovieDao {

	Movie getMovie(long id);

	Movie addMovieTo(Movie movie, long userId);

	User deleteMovieTo(Movie movie, long userId);

}
