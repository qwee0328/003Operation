package com.operation.dto;

import java.sql.Timestamp;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String nickname;
	private String phone;
	private String email;
	private Timestamp birthday;
	private String gender;
	private String profile_image;
	private int point;
	private int level_id;
	private Timestamp signup_date;
	private String recommender_id;
	
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(String id, String pw, String name, String nickname, String phone, String email, Timestamp birthday,
			String gender, String profile_image, int point, int level_id, Timestamp signup_date,
			String recommender_id) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.profile_image = profile_image;
		this.point = point;
		this.level_id = level_id;
		this.signup_date = signup_date;
		this.recommender_id = recommender_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getLevel_id() {
		return level_id;
	}

	public void setLevel_id(int level_id) {
		this.level_id = level_id;
	}

	public Timestamp getSignup_date() {
		return signup_date;
	}

	public void setSignup_date(Timestamp signup_date) {
		this.signup_date = signup_date;
	}

	public String getRecommender_id() {
		return recommender_id;
	}

	public void setRecommender_id(String recommender_id) {
		this.recommender_id = recommender_id;
	}
}
