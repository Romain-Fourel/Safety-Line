package com.safetyline.apptest.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	Long id;

	String name;
	List<Movie> movies;
	
	byte[] hashPassword; // we have chosen SHA-512 to hash the password
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getHashPassword() {
		return hashPassword;
	}

	public void setPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			hashPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test if the password is the one of the user
	 * @param password
	 * @return true if "password" is the password of the user
	 */
	public boolean hasPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));			
			
			if (hash.length != hashPassword.length) {
				return false;
			}
			
			return bytesEquals(hash, hashPassword);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	static public boolean bytesEquals(byte[] tab1, byte[] tab2) {
		
		for (int i = 0; i < tab1.length; i++) {
			if (tab1[i] != tab2[i]) {
				return false;
			}
		}
		return true;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Long getId() {
		return id;
	}

	public Movie addMovie(Movie movie) {
		if (movies==null) {
			movies = new ArrayList<Movie>();
		}
		movies.add(movie);
		return movie;
	}

	public void removeMovie(Movie movie) {
		movies.remove(movie);
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", hashPassword=" + hashPassword + ", movies=" + movies + "]";
	}

	@Override
	public boolean equals(Object obj) {
		User user = (User) obj;
		return user.getId().equals(getId());
	}

}
