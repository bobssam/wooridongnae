package com.suba.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.SmallAreaDao;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;
import com.suba.vo.SmallAreaVO;

@Controller
@RequestMapping(value="/api/smallarea")
public class SmallAreaApiController {

	@Autowired
	private SmallAreaDao smallAreaDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardVO> list( PageVO pageVO ) {

		List<BoardVO> memberVOList = smallAreaDao.list(pageVO);
		int total = smallAreaDao.total(pageVO);
		ListVo<BoardVO> list = new ListVo<BoardVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( SmallAreaVO boardVO ) {

		int row = smallAreaDao.insert(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( SmallAreaVO boardVO ) {

		int row = smallAreaDao.modify(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( SmallAreaVO pageVO ) {

		int row = smallAreaDao.delete(pageVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
