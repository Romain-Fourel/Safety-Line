package com.safetyline.apptest.dao;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Movie {

	@PrimaryKey
	long id;

	String name;
	boolean isFavorite;

}
