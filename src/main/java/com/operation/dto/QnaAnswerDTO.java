package com.operation.dto;

import java.sql.Timestamp;

public class QnaAnswerDTO {
	private int id;
	private int qna_question_board_id;
	private String content;
	private Timestamp write_date;
	
	public QnaAnswerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public QnaAnswerDTO(int id, int qna_question_board_id, String content, Timestamp write_date) {
		super();
		this.id = id;
		this.qna_question_board_id = qna_question_board_id;
		this.content = content;
		this.write_date = write_date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQna_question_board_id() {
		return qna_question_board_id;
	}
	public void setQna_question_board_id(int qna_question_board_id) {
		this.qna_question_board_id = qna_question_board_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
}
