package com.suba.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.BoardCategoryDao;
import com.suba.vo.BoardCategoryVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/api/boardCategory")
public class BoardCategoryApiController {

	@Autowired
	private BoardCategoryDao boardCategoryDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardCategoryVO> list( PageVO pageVO ) {

		List<BoardCategoryVO> BoardCategoryVOList = boardCategoryDao.selectBoardCategoryList(pageVO);
		int total = boardCategoryDao.total(pageVO);
		ListVo<BoardCategoryVO> list = new ListVo<BoardCategoryVO>( total, BoardCategoryVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( BoardCategoryVO boardCategoryVO ) {

		int row = boardCategoryDao.insert(boardCategoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( BoardCategoryVO boardCategoryVO ) {

		int row = boardCategoryDao.modify(boardCategoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( BoardCategoryVO boardCategoryVO ) {

		int row = boardCategoryDao.delete(boardCategoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
