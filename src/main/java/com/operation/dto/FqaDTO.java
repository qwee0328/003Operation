package com.operation.dto;

public class FqaDTO {
	private int id;
	private String questioin;
	private String answer;
	
	public FqaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FqaDTO(int id, String questioin, String answer) {
		super();
		this.id = id;
		this.questioin = questioin;
		this.answer = answer;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestioin() {
		return questioin;
	}
	public void setQuestioin(String questioin) {
		this.questioin = questioin;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
