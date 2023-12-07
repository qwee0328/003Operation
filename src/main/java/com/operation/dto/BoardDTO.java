package com.operation.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardDTO {
	private int id;
	private String bulletin_category_id;
	private String member_id;
	private String member_nickname;
	private String title;
	private String content;
	private Timestamp write_date;
	private int view_count;
	private boolean is_fix;
	
	public BoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardDTO(int id, String bulletin_category_id, String member_id, String member_nickname, String title,
			String content, Timestamp write_date, int view_count, boolean is_fix) {
		super();
		this.id = id;
		this.bulletin_category_id = bulletin_category_id;
		this.member_id = member_id;
		this.member_nickname = member_nickname;
		this.title = title;
		this.content = content;
		this.write_date = write_date;
		this.view_count = view_count;
		this.is_fix = is_fix;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBulletin_category_id() {
		return bulletin_category_id;
	}

	public void setBulletin_category_id(String bulletin_category_id) {
		this.bulletin_category_id = bulletin_category_id;
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

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	public boolean isIs_fix() {
		return is_fix;
	}

	public void setIs_fix(boolean is_fix) {
		this.is_fix = is_fix;
	}
}
