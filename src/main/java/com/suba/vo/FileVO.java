package com.suba.vo;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import com.suba.common.vo.PageVO;

public class FileVO extends PageVO{

	public static final String BOARD_TYPE_NOTICE = "notice";
	public static final String BOARD_TYPE_FAQ = "faq";
	public static final String BOARD_TYPE_QNA = "qna";
	public static final String BOARD_TYPE_TENDER = "tender";
	public static final String BOARD_TYPE_TENDER_REPLY = "tender_reply";
	public static final String BOARD_TYPE_DEALER = "dealer";
	public static final String BOARD_TYPE_BOARD = "board";
	public static final String BOARD_HOT_DEAL = "hotdeal";

	private int fileNo;
	private Date regDate;
	private String path;
	private int size;
	private int memberNo;
	private String boardType;
	private int boardNo;
	private int boardNo2;

	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo( int fileNo ) {
		this.fileNo = fileNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate( Date regDate ) {
		this.regDate = regDate;
	}
	@JsonIgnore
	public String getPath() {
		return path;
	}
	public void setPath( String path ) {
		this.path = path;
	}
	public int getSize() {
		return size;
	}
	public void setSize( int size ) {
		this.size = size;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo( int memberNo ) {
		this.memberNo = memberNo;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBoardNo2() {
		return boardNo2;
	}
	public void setBoardNo2(int boardNo2) {
		this.boardNo2 = boardNo2;
	}
}
