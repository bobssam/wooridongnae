package com.suba.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.NoticeDao;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/api/notice")
public class NoticeApiController {

	@Autowired
	private NoticeDao noticeDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardVO> list( PageVO pageVO ) {

		List<BoardVO> memberVOList = noticeDao.list(pageVO);
		int total = noticeDao.total(pageVO);
		ListVo<BoardVO> list = new ListVo<BoardVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public BoardVO view( BoardVO boardVO ) {

		noticeDao.plusHit(boardVO);
		return noticeDao.view(boardVO);
	}

	@RequestMapping(value="/updateHIt")
	@ResponseBody
	public Result updateHIt( BoardVO boardVO) {
		if (0 > noticeDao.plusHit(boardVO)) {
			return new Result("ok", "성공");
		} else {
			return new Result("fail", "실패");
		}
	}

}
