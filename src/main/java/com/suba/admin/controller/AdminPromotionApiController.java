package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminPromotionDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.ListVo;
import com.suba.vo.PromotionVO;

@Controller
@RequestMapping(value="/admin/api/promotion")
public class AdminPromotionApiController {

	@Autowired
	private AdminPromotionDao promotionDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<PromotionVO> selectBoardList( PageVO pageVO ) {

		List<PromotionVO> memberVOList = promotionDao.list(pageVO);
		int total = promotionDao.total(pageVO);
		ListVo<PromotionVO> list = new ListVo<PromotionVO>( total, memberVOList );
		return list;
	}


	@RequestMapping(value="/view")
	@ResponseBody
	public Result viewBoard( PageVO pageVO ) {

		return new Result("ok", promotionDao.view(pageVO));
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addBoard( PromotionVO hotdealVO ) {

		int row = promotionDao.insert(hotdealVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoard( PromotionVO promotionVO ) {

		int row = promotionDao.modify(promotionVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( PromotionVO promotionVO ) {

		int row = promotionDao.delete(promotionVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
