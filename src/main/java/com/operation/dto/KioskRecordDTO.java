package com.operation.dto;

import java.time.Instant;

public class KioskRecordDTO {
	private int id;
	private int kiosk_id;
	private String member_id;
	private String member_nickname;
	private Instant play_date;
	private int play_time;
	private boolean is_success;
	
	public KioskRecordDTO() {}

	public KioskRecordDTO(int id, int kiosk_id, String member_id, String member_nickname, Instant play_date,
			int play_time, boolean is_success) {
		this.id = id;
		this.kiosk_id = kiosk_id;
		this.member_id = member_id;
		this.member_nickname = member_nickname;
		this.play_date = play_date;
		this.play_time = play_time;
		this.is_success = is_success;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKiosk_id() {
		return kiosk_id;
	}

	public void setKiosk_id(int kiosk_id) {
		this.kiosk_id = kiosk_id;
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

	public Instant getPlay_date() {
		return play_date;
	}

	public void setPlay_date(Instant play_date) {
		this.play_date = play_date;
	}

	public int getPlay_time() {
		return play_time;
	}

	public void setPlay_time(int play_time) {
		this.play_time = play_time;
	}

	public boolean isIs_success() {
		return is_success;
	}

	public void setIs_success(boolean is_success) {
		this.is_success = is_success;
	}

	@Override
	public String toString() {
		return "KioskRecordDTO [id=" + id + ", kiosk_id=" + kiosk_id + ", member_id=" + member_id + ", member_nickname="
				+ member_nickname + ", play_date=" + play_date + ", play_time=" + play_time + ", is_success="
				+ is_success + "]";
	}
	
}
