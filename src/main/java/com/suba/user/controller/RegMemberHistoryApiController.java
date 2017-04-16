package com.suba.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.RegLogDao;
import com.suba.vo.ListVo;
import com.suba.vo.RegLogVO;

@Controller
@RequestMapping(value="/api/regLog")
public class RegMemberHistoryApiController {

	@Autowired
	private RegLogDao regLogDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<RegLogVO> list( PageVO pageVO ) {

		List<RegLogVO> LoginHistoryVOList = regLogDao.list(pageVO);
		int total = regLogDao.total(pageVO);
		ListVo<RegLogVO> list = new ListVo<RegLogVO>( total, LoginHistoryVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( RegLogVO regLogVO ) {

		int row = regLogDao.insert(regLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( RegLogVO regLogVO ) {

		int row = regLogDao.modify(regLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( RegLogVO regLogVO ) {

		int row = regLogDao.delete(regLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
