package com.safetyline.apptest.dao;

public class MovieInfo {

	String title;
	String type;
	String year;
	String poster;
	String imdb;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	@Override
	public String toString() {
		return "MovieInfo [title=" + title + ", type=" + type + ", year=" + year + ", poster=" + poster + ", imdb="
				+ imdb + "]";
	}

}
