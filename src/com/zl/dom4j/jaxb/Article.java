package com.zl.dom4j.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Article {

	private String author;
	private String title;
	private String email;
	private String date;

	public Article() {
		// TODO Auto-generated constructor stub
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
