package com.suba.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suba.common.vo.Result;
import com.suba.user.dao.FAQDao;
import com.suba.user.dao.NoticeDao;
import com.suba.user.dao.QNADao;
import com.suba.vo.BoardVO;

@Service
public class InfoService {

	@Autowired
	private FAQDao faqDao;
	@Autowired
	private QNADao qnaDao;
	@Autowired
	private NoticeDao noticeDao;
	public Result faqInsert(BoardVO boardVO) {
		if (0 > faqDao.insert(boardVO)) {
			return new Result("ok", "성공");
		} else {
			return new Result("fail", "실패");
		}
	}
	public Result faqUpdateHit(BoardVO boardVO) {
		if (0 > faqDao.plusHit(boardVO)) {
			return new Result("ok", "성공");
		} else {
			return new Result("fail", "실패");
		}
	}
	public Result qnaUpdateHit(BoardVO boardVO) {
		if (0 > qnaDao.plusHit(boardVO)) {
			return new Result("ok", "성공");
		} else {
			return new Result("fail", "실패");
		}
	}
	public Result qnaInsert(BoardVO boardVO) {
		if (0 > qnaDao.insert(boardVO)) {
			return new Result("ok", "성공");
		} else {
			return new Result("fail", "실패");
		}
	}
	public Result noticeUpdateHit(BoardVO boardVO) {
		if (0 > noticeDao.plusHit(boardVO)) {
			return new Result("ok", "성공");
		} else {
			return new Result("fail", "실패");
		}
	}
	public Result noticeInsert(BoardVO boardVO) {
		if (0 > noticeDao.insert(boardVO)) {
			return new Result("ok", "성공");
		} else {
			return new Result("fail", "실패");
		}
	}
}