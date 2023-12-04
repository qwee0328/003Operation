package com.operation.dto;

public class KioskDTO {
	private int id;
	private String kiosk_category_id;
	private String name;
	private String url;
	private int width;
	private int height;
	private String description;
	private boolean is_game;
	
	public KioskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public KioskDTO(int id, String kiosk_category_id, String name, String url, int width, int height,
			String description, boolean is_game) {
		this.id = id;
		this.kiosk_category_id = kiosk_category_id;
		this.name = name;
		this.url = url;
		this.width = width;
		this.height = height;
		this.description = description;
		this.is_game = is_game;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKiosk_category_id() {
		return kiosk_category_id;
	}
	public void setKiosk_category_id(String kiosk_category_id) {
		this.kiosk_category_id = kiosk_category_id;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isIs_game() {
		return is_game;
	}

	public void setIs_game(boolean is_game) {
		this.is_game = is_game;
	}


}
