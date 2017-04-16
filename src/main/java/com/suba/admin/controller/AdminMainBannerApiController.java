package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminMainBannerDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.ListVo;
import com.suba.vo.MainBannerVO;

@Controller
@RequestMapping(value="/admin/api/mainbanner")
public class AdminMainBannerApiController {

	@Autowired
	private AdminMainBannerDao mainBannerDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<MainBannerVO> list( PageVO pageVO ) {

		List<MainBannerVO> memberVOList = mainBannerDao.list(pageVO);
		int total = mainBannerDao.total(pageVO);
		ListVo<MainBannerVO> list = new ListVo<MainBannerVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public Result view( PageVO pageVO ) {
		MainBannerVO memberVO = mainBannerDao.view(pageVO);
		return new Result("ok", memberVO);
	}


	@RequestMapping(value="/add")
	@ResponseBody
	public Result addBoard( MainBannerVO boardVO ) {

		int row = mainBannerDao.insert(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoard( MainBannerVO boardVO ) {

		int row = mainBannerDao.modify(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( MainBannerVO boardVO ) {

		int row = mainBannerDao.delete(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
