package com.operation.dto;

public class BlackListDTO {
	private int id;
	private String member_id;
	private String member_nickname;
	private String member_phone;
	private String member_email;
	
	public BlackListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BlackListDTO(int id, String member_id, String member_nickname, String member_phone, String member_email) {
		super();
		this.id = id;
		this.member_id = member_id;
		this.member_nickname = member_nickname;
		this.member_phone = member_phone;
		this.member_email = member_email;
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
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}	
}
