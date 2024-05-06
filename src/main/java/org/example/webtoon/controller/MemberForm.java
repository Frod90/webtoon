package org.example.webtoon.controller;

public class MemberForm {

	private String id;
	private String pwd;
	private String name;

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
