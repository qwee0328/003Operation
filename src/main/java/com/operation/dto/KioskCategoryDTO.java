package com.operation.dto;

public class KioskCategoryDTO {
	private String id;
	private String name;
	
	public KioskCategoryDTO() {}
	public KioskCategoryDTO(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
