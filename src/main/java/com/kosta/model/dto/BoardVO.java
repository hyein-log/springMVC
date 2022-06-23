package com.kosta.model.dto;

import java.sql.Date;

public class BoardVO {
	private int bno;
	private String title;
	private String content;
	private int writer;
	private Date regdate;
	private Date update_date;
	private String pic;
	
	private EMPVO emp;
	
	
	public String getPic() {
		return pic;
	}


	public void setPic(String pic) {
		this.pic = pic;
	}


	public EMPVO getEmp() {
		System.out.println("getEmp!"+ emp);
		return emp;
	}


	public void setEmp(EMPVO emp) {
		System.out.println("setEmp!"+ emp);
		this.emp = emp;
	}


	public BoardVO(String title, String content, int writer, EMPVO emp) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.emp = emp;
	}


	public BoardVO() {
		
	}
	
	
	public BoardVO(String title, String content, int writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}


	public BoardVO(int bno, String title, String content, int writer, Date regdate, Date update_date) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.update_date = update_date;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}


	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", update_date=" + update_date + ", pic=" + pic + ", emp=" + emp + "]";
	}
	
}
