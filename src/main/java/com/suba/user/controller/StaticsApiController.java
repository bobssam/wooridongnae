package com.suba.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.StaticsDao;
import com.suba.vo.ListVo;
import com.suba.vo.StaticsVO;

@Controller
@RequestMapping(value="/api/statics")
public class StaticsApiController {

	@Autowired
	private StaticsDao staticsDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<StaticsVO> list( PageVO pageVO ) {

		List<StaticsVO> StaticsVOList = staticsDao.list(pageVO);
		int total = staticsDao.total(pageVO);
		ListVo<StaticsVO> list = new ListVo<StaticsVO>( total, StaticsVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( StaticsVO staticsVO ) {

		int row = staticsDao.insert(staticsVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( StaticsVO staticsVO ) {

		int row = staticsDao.modify(staticsVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( StaticsVO staticsVO ) {

		int row = staticsDao.delete(staticsVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
