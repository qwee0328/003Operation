package com.operation.dto;

public class BoardBookMarkDTO {
	private int id;
	private String bulletin_category_id;
	private String member_id;
	
	public BoardBookMarkDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BoardBookMarkDTO(int id, String bulletin_category_id, String member_id) {
		super();
		this.id = id;
		this.bulletin_category_id = bulletin_category_id;
		this.member_id = member_id;
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
}
