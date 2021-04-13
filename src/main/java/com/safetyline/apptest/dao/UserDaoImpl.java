package com.safetyline.apptest.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class UserDaoImpl implements UserDao {

	private PersistenceManagerFactory pmf;

	public UserDaoImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@Override
	public User getUser(long id) {
		User user = null;
		User detached = null;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			user = pm.getObjectById(User.class, id);

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

	@Override
	public User addUser(User user) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		User detached = new User();

		try {
			tx.begin();
			detached = pm.makePersistent(user);

			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(String name, String password) {
		List<User> users = null;
		List<User> detached = new ArrayList<User>();

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Query q = pm.newQuery(User.class);
			// TODO: hash the password !!!
			q.declareParameters("String username, String pw");
			q.setFilter("name == username && hashPassword==pw");
			users = (List<User>) q.execute(name, password);
			detached = (List<User>) pm.detachCopyAll(users);

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
