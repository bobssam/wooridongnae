package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminLoginHistoryDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.LoginHistoryVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/loginHistory")
public class AdminLoginHistoryApiController {

	@Autowired
	private AdminLoginHistoryDao loginHistoryDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<LoginHistoryVO> selectLoginHistoryList( PageVO pageVO ) {

		List<LoginHistoryVO> LoginHistoryVOList = loginHistoryDao.list(pageVO);
		int total = loginHistoryDao.total(pageVO);
		ListVo<LoginHistoryVO> list = new ListVo<LoginHistoryVO>( total, LoginHistoryVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addLoginHistory( LoginHistoryVO loginHistoryVO ) {

		int row = loginHistoryDao.insert(loginHistoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateLoginHistory( LoginHistoryVO loginHistoryVO ) {

		int row = loginHistoryDao.modify(loginHistoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteLoginHistory( LoginHistoryVO loginHistoryVO ) {

		int row = loginHistoryDao.delete(loginHistoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
