package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminStaticsDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.StaticsVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/statics")
public class AdminStaticsApiController {

	@Autowired
	private AdminStaticsDao staticsDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<StaticsVO> selectStaticsList( PageVO pageVO ) {

		List<StaticsVO> StaticsVOList = staticsDao.list(pageVO);
		int total = staticsDao.total(pageVO);
		ListVo<StaticsVO> list = new ListVo<StaticsVO>( total, StaticsVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addStatics( StaticsVO staticsVO ) {

		int row = staticsDao.insert(staticsVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateStatics( StaticsVO staticsVO ) {

		int row = staticsDao.modify(staticsVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteStatics( StaticsVO staticsVO ) {

		int row = staticsDao.delete(staticsVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
