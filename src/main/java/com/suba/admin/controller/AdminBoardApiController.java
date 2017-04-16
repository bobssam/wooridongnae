package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminBoardDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/board")
public class AdminBoardApiController {

	@Autowired
	private AdminBoardDao boardDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardVO> selectBoardList( PageVO pageVO ) {

		List<BoardVO> memberVOList = boardDao.list(pageVO);
		int total = boardDao.total(pageVO);
		ListVo<BoardVO> list = new ListVo<BoardVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addBoard( BoardVO boardVO ) {

		int row = boardDao.insert(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoard( BoardVO boardVO ) {

		int row = boardDao.modify(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}
	
	@RequestMapping(value="/view")
	@ResponseBody
	public Result viewBoard( BoardVO boardVO ) {

		BoardVO boVo = boardDao.view(boardVO.getBoardNo());
		Result rs = new Result("ok", boVo);
		return rs;
	}


	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( BoardVO boardVO ) {

		int row = boardDao.delete(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
