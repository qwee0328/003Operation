package com.operation.dto;

public class WarningDTO {
	private int id;
	private String member_id;
	private String warning_reason;
	
	public WarningDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WarningDTO(int id, String member_id, String warning_reason) {
		super();
		this.id = id;
		this.member_id = member_id;
		this.warning_reason = warning_reason;
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
	public String getWarning_reason() {
		return warning_reason;
	}
	public void setWarning_reason(String warning_reason) {
		this.warning_reason = warning_reason;
	}
}
