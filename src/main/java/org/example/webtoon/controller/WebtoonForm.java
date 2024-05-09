package org.example.webtoon.controller;

public class WebtoonForm {

	private String webtoonID;
	private String title;
	private String author;

	public WebtoonForm(String wetoonID, String title, String author) {
		this.webtoonID = wetoonID;
		this.title = title;
		this.author = author;

	}

	public String getTitle() {
		return title;
	}

	public String getWebtoonID() {
		return webtoonID;
	}

	public String getAuthor() {
		return author;
	}
}
