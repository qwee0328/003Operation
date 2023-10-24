package com.operation.dto;

public class QnaQuestionFileDTO {
	private int id;
	private int qna_question_board__id;
	private String system_name;
	private String origin_name;
	private boolean img;
	
	public QnaQuestionFileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public QnaQuestionFileDTO(int id, int qna_question_board__id, String system_name, String origin_name, boolean img) {
		super();
		this.id = id;
		this.qna_question_board__id = qna_question_board__id;
		this.system_name = system_name;
		this.origin_name = origin_name;
		this.img = img;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQna_question_board__id() {
		return qna_question_board__id;
	}
	public void setQna_question_board__id(int qna_question_board__id) {
		this.qna_question_board__id = qna_question_board__id;
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
	public boolean isImg() {
		return img;
	}
	public void setImg(boolean img) {
		this.img = img;
	}
}
