package com.safetyline.apptest.dao;

import java.util.List;

public interface UserDao {

	List<User> getUsers(String name, String password);

	User getUser(long id);

	User addUser(User user);

}
