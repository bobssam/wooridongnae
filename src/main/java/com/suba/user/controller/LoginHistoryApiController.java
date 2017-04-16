package com.suba.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.LoginHistoryDao;
import com.suba.vo.ListVo;
import com.suba.vo.LoginHistoryVO;

@Controller
@RequestMapping(value="/api/loginHistory")
public class LoginHistoryApiController {

	@Autowired
	private LoginHistoryDao loginHistoryDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<LoginHistoryVO> list( PageVO pageVO ) {

		List<LoginHistoryVO> LoginHistoryVOList = loginHistoryDao.list(pageVO);
		int total = loginHistoryDao.total(pageVO);
		ListVo<LoginHistoryVO> list = new ListVo<LoginHistoryVO>( total, LoginHistoryVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( LoginHistoryVO loginHistoryVO ) {

		int row = loginHistoryDao.insert(loginHistoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( LoginHistoryVO loginHistoryVO ) {

		int row = loginHistoryDao.modify(loginHistoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( LoginHistoryVO loginHistoryVO ) {

		int row = loginHistoryDao.delete(loginHistoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
