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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		List<User> users = null;
		List<User> detached = new ArrayList<User>();

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Query q = pm.newQuery(User.class);
			users = (List<User>) q.execute();
			
			users.toString();
			
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

	@Override
	public User getUser(long id) {
		User user = null;
		User detached = null;

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			user = pm.getObjectById(User.class, id);
			
			user.toString();

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

		User detached = null;
		
		
		try {
			tx.begin();
			user = pm.makePersistent(user);
			
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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(String name) {
		List<User> users = null;
		List<User> detached = new ArrayList<User>();

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Query q = pm.newQuery(User.class);

			q.declareParameters("String username");
			q.setFilter("name == username");
			users = (List<User>) q.execute(name);
			
			users.toString();
			
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
