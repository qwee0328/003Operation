package com.operation.dto;

public class KioskDTO {
	private int id;
	private String kiosk_category_id;
	private String image_url;
	private int width;
	private int height;
	private String description;
	private boolean isGame;
	
	public KioskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public KioskDTO(int id, String kiosk_category_id, String image_url, int width, int height, String description,
			boolean isGame) {
		super();
		this.id = id;
		this.kiosk_category_id = kiosk_category_id;
		this.image_url = image_url;
		this.width = width;
		this.height = height;
		this.description = description;
		this.isGame = isGame;
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
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
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
	public boolean isGame() {
		return isGame;
	}
	public void setGame(boolean isGame) {
		this.isGame = isGame;
	}
}
