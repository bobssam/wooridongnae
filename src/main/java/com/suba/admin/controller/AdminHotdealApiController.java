package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminHotdealDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.HotdealVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/hotdeal")
public class AdminHotdealApiController {

	@Autowired
	private AdminHotdealDao hotdealDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<HotdealVO> selectBoardList( PageVO pageVO ) {

		List<HotdealVO> memberVOList = hotdealDao.list(pageVO);
		int total = hotdealDao.total(pageVO);
		ListVo<HotdealVO> list = new ListVo<HotdealVO>( total, memberVOList );
		return list;
	}


	@RequestMapping(value="/view")
	@ResponseBody
	public Result viewBoard( PageVO pageVO ) {

		return new Result("ok", hotdealDao.view(pageVO));
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addBoard( HotdealVO hotdealVO ) {

		int row = hotdealDao.insert(hotdealVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoard( HotdealVO hotdealVO ) {

		int row = hotdealDao.modify(hotdealVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( HotdealVO hotdealVO ) {

		int row = hotdealDao.delete(hotdealVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
