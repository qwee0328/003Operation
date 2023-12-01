package com.operation.dto;

public class FileDTO {
	private int id;
	private int bulletin_board_id;
	private String system_name;
	private String origin_name;
	
	public FileDTO() {}
	public FileDTO(int id, int bulletin_board_id, String system_name, String origin_name) {
		this.id = id;
		this.bulletin_board_id = bulletin_board_id;
		this.system_name = system_name;
		this.origin_name = origin_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBulletin_board_id() {
		return bulletin_board_id;
	}
	public void setBulletin_board_id(int bulletin_board_id) {
		this.bulletin_board_id = bulletin_board_id;
	}
	public String getSystem_name() {
		return system_name;
	}
	public void setSystem_name(String system_name) {
		this.system_name = system_name;
	}
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
}
