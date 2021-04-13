package com.safetyline.apptest.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.safetyline.apptest.dao.DAO;
import com.safetyline.apptest.dao.User;

@Path("/User")
public class UserResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public User addUser(User user) {
		user = DAO.getUserDao().addUser(user);

		return user;
	}

	/**
	 * 
	 * @param data has to be like: [name,password]
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/connect")
	public User findUser(String[] data) {

		System.out.println(data);

		String name = (String) data[0];
		String password = (String) data[1];

		// we assume that there is only one user who has one specific pair of
		// name/password :
		User user = DAO.getUserDao().getUsers(name, password).get(0);

		return user;
	}

	/**
	 * TODO: DELETE THIS
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/fake")
	public String fakeUser(String data) {

		System.out.println("data:'" + data + "'");
		return data;
	}

}
