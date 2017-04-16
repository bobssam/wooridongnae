package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminSmallAreaDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;
import com.suba.vo.SmallAreaVO;

@Controller
@RequestMapping(value="/admin/api/smallarea")
public class AdminSmallAreaApiController {

	@Autowired
	private AdminSmallAreaDao smallAreaDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardVO> selectBoardList( PageVO pageVO ) {

		List<BoardVO> memberVOList = smallAreaDao.list(pageVO);
		int total = smallAreaDao.total(pageVO);
		ListVo<BoardVO> list = new ListVo<BoardVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addBoard( SmallAreaVO boardVO ) {

		int row = smallAreaDao.insert(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoard( SmallAreaVO boardVO ) {

		int row = smallAreaDao.modify(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( SmallAreaVO pageVO ) {

		int row = smallAreaDao.delete(pageVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
