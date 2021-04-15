package com.safetyline.apptest.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.safetyline.apptest.dao.DAO;
import com.safetyline.apptest.dao.User;

@Path("/User")
public class UserResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addUser(User user) {
		user = DAO.getUserDao().addUser(user);
		return Response.ok(DAO.getUserDao().getUsers()).build();
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
	public Response findUser(String[] data) {

		String name = (String) data[0];
		String password = (String) data[1];
		
		List<User> users = DAO.getUserDao().getUsers(name);

		if (users.size()>0) {
			// we assume that there is only one user who has one specific pair of
			// name/password :
			for (User retrievedUser : users) {
				if (retrievedUser.hasPassword(password)) {
					return Response.ok(retrievedUser).build();
				};
			}
		}

		return Response.ok(null).build();
	}


}
