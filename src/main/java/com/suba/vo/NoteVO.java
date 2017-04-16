package com.suba.vo;

import java.util.Date;

import com.suba.common.vo.PageVO;


public class NoteVO extends PageVO{
	private int noteNo;
	private int memberNo;
	private int senderNo;
	private Date regDate;
	private String content;
	private int read;
	private int point;
	private String id;

	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public int getNoteNo() {
		return noteNo;
	}
	public void setNoteNo( int noteNo ) {
		this.noteNo = noteNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo( int memberNo ) {
		this.memberNo = memberNo;
	}
	public int getSenderNo() {
		return senderNo;
	}
	public void setSenderNo( int senderNo ) {
		this.senderNo = senderNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate( Date regDate ) {
		this.regDate = regDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent( String content ) {
		this.content = content;
	}
	public int getRead() {
		return read;
	}
	public void setRead( int read ) {
		this.read = read;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
