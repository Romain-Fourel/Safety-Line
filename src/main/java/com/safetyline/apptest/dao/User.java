package com.safetyline.apptest.dao;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {

	@PrimaryKey
	long id;

	String name;
	String hashPassword;
	List<Movie> movies;

}
