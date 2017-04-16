package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminBigAreaDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.BigAreaVO;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/bigarea")
public class AdminBigAreaApiController {

	@Autowired
	private AdminBigAreaDao bigAreaDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardVO> selectBoardList( PageVO pageVO ) {

		List<BoardVO> memberVOList = bigAreaDao.list(pageVO);
		int total = bigAreaDao.total(pageVO);
		ListVo<BoardVO> list = new ListVo<BoardVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addBoard( BigAreaVO boardVO ) {

		int row = bigAreaDao.insert(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoard( BigAreaVO boardVO ) {

		int row = bigAreaDao.modify(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( BigAreaVO pageVO ) {

		int row = bigAreaDao.delete(pageVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
