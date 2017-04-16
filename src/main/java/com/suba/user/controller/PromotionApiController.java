package com.suba.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.user.dao.PromotionDao;
import com.suba.user.menuController.MenuController;
import com.suba.vo.HotdealVO;
import com.suba.vo.ListVo;
import com.suba.vo.PromotionVO;

@Controller
@RequestMapping(value="/api/promotion")
public class PromotionApiController extends MenuController{

	@Autowired
	private PromotionDao promotionDao;

	@RequestMapping(value="/total")
	@ResponseBody
	public int total( PageVO pageVO ) {
		return promotionDao.total(pageVO);
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public PromotionVO view( PromotionVO hotdealVO, HttpSession session, HttpServletResponse response ) {

		return promotionDao.view(hotdealVO);
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<PromotionVO> list( PageVO pageVO ) {

		List<PromotionVO> list = promotionDao.list(pageVO);
		return new ListVo<PromotionVO>(list.size(), list) ;
	}


}
