package com.operation.dto;

public class KioskDTO {
	private int id;
	private String kiosk_category_id;
	private String name;
	private String url;
	private int play_stage;
	private int width;
	private int height;
	private boolean is_game;
	
	public KioskDTO() {
		super();
	}	

	public KioskDTO(int id, String kiosk_category_id, String name, String url, int play_stage, int width, int height,
			boolean is_game) {
		this.id = id;
		this.kiosk_category_id = kiosk_category_id;
		this.name = name;
		this.url = url;
		this.play_stage = play_stage;
		this.width = width;
		this.height = height;
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

	public int getPlay_stage() {
		return play_stage;
	}

	public void setPlay_stage(int play_stage) {
		this.play_stage = play_stage;
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

	public boolean isIs_game() {
		return is_game;
	}

	public void setIs_game(boolean is_game) {
		this.is_game = is_game;
	}
	
}
