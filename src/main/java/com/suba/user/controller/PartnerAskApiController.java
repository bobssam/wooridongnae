package com.suba.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.PartnerAskDao;
import com.suba.vo.PartnerAskVO;

@Controller
@RequestMapping(value="/api/partnerask")
public class PartnerAskApiController {

	@Autowired
	private PartnerAskDao partnerAskDao;

	@RequestMapping(value="/total")
	@ResponseBody
	public int total( PageVO pageVO ) {
		return partnerAskDao.total(pageVO);
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public PartnerAskVO view( PartnerAskVO hotdealAskVO, HttpSession session  ) {
		return partnerAskDao.view(hotdealAskVO);
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( PartnerAskVO hotdealAskVO, HttpSession session ) {

		int row = partnerAskDao.add(hotdealAskVO);
		Result rs = new Result("ok", row);
		return rs;
	}

}
