package com.operation.dto;

import java.sql.Timestamp;

public class ReplyDTO {
	private int id;
	private String reply_statement_id;
	private int bulletin_board_id;
	private int parent_reply_id;
	private String member_id;
	private String member_nickname;
	private String content;
	private Timestamp write_date;
	
	public ReplyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReplyDTO(int id, String reply_statement_id, int bulletin_board_id, int parent_reply_id, String member_id,
			String member_nickname, String content, Timestamp write_date) {
		super();
		this.id = id;
		this.reply_statement_id = reply_statement_id;
		this.bulletin_board_id = bulletin_board_id;
		this.parent_reply_id = parent_reply_id;
		this.member_id = member_id;
		this.member_nickname = member_nickname;
		this.content = content;
		this.write_date = write_date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReply_statement_id() {
		return reply_statement_id;
	}
	public void setReply_statement_id(String reply_statement_id) {
		this.reply_statement_id = reply_statement_id;
	}
	public int getBulletin_board_id() {
		return bulletin_board_id;
	}
	public void setBulletin_board_id(int bulletin_board_id) {
		this.bulletin_board_id = bulletin_board_id;
	}
	public int getParent_reply_id() {
		return parent_reply_id;
	}
	public void setParent_reply_id(int parent_reply_id) {
		this.parent_reply_id = parent_reply_id;
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
}
