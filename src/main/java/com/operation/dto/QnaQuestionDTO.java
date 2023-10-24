package com.operation.dto;

import java.sql.Timestamp;

public class QnaQuestionDTO {
	private int id;
	private String member_id;
	private String member_nickname;
	private String title;
	private String content;
	private Timestamp write_date;
	private boolean isSecret;
	
	public QnaQuestionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public QnaQuestionDTO(int id, String member_id, String member_nickname, String title, String content,
			Timestamp write_date, boolean isSecret) {
		super();
		this.id = id;
		this.member_id = member_id;
		this.member_nickname = member_nickname;
		this.title = title;
		this.content = content;
		this.write_date = write_date;
		this.isSecret = isSecret;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public boolean isSecret() {
		return isSecret;
	}
	public void setSecret(boolean isSecret) {
		this.isSecret = isSecret;
	}
}
