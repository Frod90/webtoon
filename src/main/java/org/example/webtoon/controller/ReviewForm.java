package org.example.webtoon.controller;

public class ReviewForm {

	private String title;
	private String content;
	private String webtoonID;
	private String userID;

	public ReviewForm(String webtoonID, String userID, String content, String title) {
		this.webtoonID = webtoonID;
		this.userID = userID;
		this.content = content;
		this.title = title;
	}

	public String getWebtoonID() {
		return webtoonID;
	}

	public String getUserID() {
		return userID;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}
}
