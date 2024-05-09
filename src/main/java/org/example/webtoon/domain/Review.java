package org.example.webtoon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	private String title;
	private String content;
	private String userID;
	private String webtoonID;

	public void setID(long ID) {
		this.ID = ID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getID() {
		return ID;
	}

	public String getTitle() {
		return title;
	}

	public void setWebtoonID(String webtoonID) {
		this.webtoonID = webtoonID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setContent(String content) {
		this.content = content;
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
}
