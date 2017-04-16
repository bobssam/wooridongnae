package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminRegLogDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.ListVo;
import com.suba.vo.RegLogVO;

@Controller
@RequestMapping(value="/admin/api/regLog")
public class AdminRegMemberHistoryApiController {

	@Autowired
	private AdminRegLogDao regLogDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<RegLogVO> selectLoginHistoryList( PageVO pageVO ) {

		List<RegLogVO> LoginHistoryVOList = regLogDao.list(pageVO);
		int total = regLogDao.total(pageVO);
		ListVo<RegLogVO> list = new ListVo<RegLogVO>( total, LoginHistoryVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addLoginHistory( RegLogVO regLogVO ) {

		int row = regLogDao.insert(regLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateLoginHistory( RegLogVO regLogVO ) {

		int row = regLogDao.modify(regLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteLoginHistory( RegLogVO regLogVO ) {

		int row = regLogDao.delete(regLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
