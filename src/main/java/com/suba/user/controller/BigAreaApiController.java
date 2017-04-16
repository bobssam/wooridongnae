package com.suba.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.BigAreaDao;
import com.suba.vo.BigAreaVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/api/bigarea")
public class BigAreaApiController {

	@Autowired
	private BigAreaDao bigAreaDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BigAreaVO> list( PageVO pageVO ) {

		List<BigAreaVO> memberVOList = bigAreaDao.list(pageVO);
		int total = bigAreaDao.total(pageVO);
		ListVo<BigAreaVO> list = new ListVo<BigAreaVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( BigAreaVO boardVO ) {

		int row = bigAreaDao.insert(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( BigAreaVO boardVO ) {

		int row = bigAreaDao.modify(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( BigAreaVO pageVO ) {

		int row = bigAreaDao.delete(pageVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
