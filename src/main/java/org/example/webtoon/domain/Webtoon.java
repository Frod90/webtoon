package org.example.webtoon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Webtoon {

	@Id
	private String webtoonID;
	private String title;
	private String author;

	public void setWebtoonID(String id) {
		this.webtoonID = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getWebtoonID() {
		return webtoonID;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}
}
