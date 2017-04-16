package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminPartnerAskDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.ListVo;
import com.suba.vo.PartnerAskVO;

@Controller
@RequestMapping(value="/admin/api/partnerask")
public class AdminPartnerAskApiController {

	@Autowired
	private AdminPartnerAskDao partnerAskDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<PartnerAskVO> selectBoardList( PageVO pageVO ) {

		List<PartnerAskVO> memberVOList = partnerAskDao.list(pageVO);
		int total = partnerAskDao.total(pageVO);
		ListVo<PartnerAskVO> list = new ListVo<PartnerAskVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public Result viewBoard( PageVO pageVO ) {

		return new Result("ok", partnerAskDao.view(pageVO));
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateBoard( PartnerAskVO hotdealVO ) {

		int row = partnerAskDao.modify(hotdealVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteBoard( PartnerAskVO hotdealVO ) {

		int row = partnerAskDao.delete(hotdealVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
