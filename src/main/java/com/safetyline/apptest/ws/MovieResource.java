package com.safetyline.apptest.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.safetyline.apptest.dao.DAO;
import com.safetyline.apptest.dao.Movie;
import com.safetyline.apptest.dao.User;

@Path("/Movie")
public class MovieResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add/{id}")
	public Movie addMovie(@PathParam("id") String userId, Movie movie) {

		movie = DAO.getMovieDao().addMovieTo(movie, Long.parseLong(userId));

		return movie;
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/del/{id}")
	public User removeMovie(@PathParam("id") String userId, Movie movie) {
		User user = new User();
		user = DAO.getMovieDao().deleteMovieTo(movie, Long.parseLong(userId));

		return user;
	}

}
