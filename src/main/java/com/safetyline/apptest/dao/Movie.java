package com.safetyline.apptest.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Movie {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	Long id;

	MovieInfo infos;
	boolean isFavorite;

	public MovieInfo getInfos() {
		return infos;
	}

	public void setInfos(MovieInfo infos) {
		this.infos = infos;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", infos=" + infos + ", isFavorite=" + isFavorite + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Movie movie = (Movie) obj;
		return movie.getId().equals(getId());
	}

}
