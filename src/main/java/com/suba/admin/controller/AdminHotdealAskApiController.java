package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminHotdealAskDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.HotdealAskVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/hotdealask")
public class AdminHotdealAskApiController {

	@Autowired
	private AdminHotdealAskDao hotdealAskDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<HotdealAskVO> selectBoardList( PageVO pageVO ) {

		List<HotdealAskVO> memberVOList = hotdealAskDao.list(pageVO);
		int total = hotdealAskDao.total(pageVO);
		ListVo<HotdealAskVO> list = new ListVo<HotdealAskVO>( total, memberVOList );
		return list;
	}


	@RequestMapping(value="/view")
	@ResponseBody
	public Result viewBoard( PageVO pageVO ) {

		return new Result("ok", hotdealAskDao.view(pageVO));
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addBoard( HotdealAskVO hotdealVO ) {

		int row = hotdealAskDao.insert(hotdealVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoard( HotdealAskVO hotdealVO ) {

		int row = hotdealAskDao.modify(hotdealVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( HotdealAskVO hotdealVO ) {

		int row = hotdealAskDao.delete(hotdealVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
