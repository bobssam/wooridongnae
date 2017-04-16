package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminBoardCategoryDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.BoardCategoryVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/boardCategory")
public class AdminBoardCategoryApiController {

	@Autowired
	private AdminBoardCategoryDao boardCategoryDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardCategoryVO> selectBoardCategoryList( PageVO pageVO ) {

		List<BoardCategoryVO> BoardCategoryVOList = boardCategoryDao.list(pageVO);
		int total = boardCategoryDao.total(pageVO);
		ListVo<BoardCategoryVO> list = new ListVo<BoardCategoryVO>( total, BoardCategoryVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addBoardCategory( BoardCategoryVO boardCategoryVO ) {

		int row = boardCategoryDao.insert(boardCategoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoardCategory( BoardCategoryVO boardCategoryVO ) {

		int row = boardCategoryDao.modify(boardCategoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoardCategory( BoardCategoryVO boardCategoryVO ) {

		int row = boardCategoryDao.delete(boardCategoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
