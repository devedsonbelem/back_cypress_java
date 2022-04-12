package br.com.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String text;
	private Date data;
	private UsersDto author;

	public CommentDto() {
	}

	public CommentDto(String text, Date data, UsersDto author) {
		super();
		this.text = text;
		this.data = data;
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public UsersDto getAuthor() {
		return author;
	}

	public void setAuthor(UsersDto author) {
		this.author = author;
	}
}
