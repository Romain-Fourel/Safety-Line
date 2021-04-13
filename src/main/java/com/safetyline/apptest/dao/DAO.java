package com.safetyline.apptest.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class DAO {

	static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("safetyline");

	public static UserDao getUserDao() {
		return new UserDaoImpl(pmf);
	}

	public static MovieDao getMovieDao() {
		return new MovieDaoImpl(pmf);
	}

}
