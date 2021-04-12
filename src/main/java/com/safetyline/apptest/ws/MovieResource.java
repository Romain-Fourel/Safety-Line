package com.safetyline.apptest.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.safetyline.apptest.dao.Movie;

@Path("/Movie")
public class MovieResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/save")
	public Movie saveMovie(Movie movie) {
		System.out.println("movie saved!");
		return movie;
	}

}
