package com.operation.dto;

public class QnaAnswerFileDTO {
	private int id;
	private int qna_answer_board_id;
	private String system_name;
	private String origin_name;
	
	public QnaAnswerFileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public QnaAnswerFileDTO(int id, int qna_answer_board_id, String system_name, String origin_name) {
		super();
		this.id = id;
		this.qna_answer_board_id = qna_answer_board_id;
		this.system_name = system_name;
		this.origin_name = origin_name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQna_answer_board_id() {
		return qna_answer_board_id;
	}
	public void setQna_answer_board_id(int qna_answer_board_id) {
		this.qna_answer_board_id = qna_answer_board_id;
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
