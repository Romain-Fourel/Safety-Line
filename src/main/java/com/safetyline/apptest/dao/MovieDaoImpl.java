package com.safetyline.apptest.dao;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

public class MovieDaoImpl implements MovieDao {

	private PersistenceManagerFactory pmf;

	public MovieDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@Override
	public Movie getMovie(long id) {
		Movie movie = null;
		Movie detached = null;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			movie = pm.getObjectById(Movie.class, id);

			detached = pm.detachCopy(movie);
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	@Override
	public Movie addMovieTo(Movie movie, long userId) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Movie detached = new Movie();

		try {
			tx.begin();
			User user = pm.getObjectById(User.class, userId);
			user.addMovie(movie);

			System.out.println(movie);

			detached = pm.detachCopy(movie);

			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	@Override
	public User deleteMovieTo(Movie movie, long userId) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		User detached = new User();

		try {
			tx.begin();

			User user = pm.getObjectById(User.class, userId);
			user.removeMovie(movie);

			Movie moviePersistent = pm.getObjectById(Movie.class, movie.getId());
			pm.deletePersistent(moviePersistent);

			detached = pm.detachCopy(user);

			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

}
