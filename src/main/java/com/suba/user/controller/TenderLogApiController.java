package com.suba.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.TenderLogDao;
import com.suba.vo.ListVo;
import com.suba.vo.TenderLogVO;

@Controller
@RequestMapping(value="/api/tenderLog")
public class TenderLogApiController {

	@Autowired
	private TenderLogDao tenderLogDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<TenderLogVO> list( PageVO pageVO ) {

		List<TenderLogVO> TenderLogVOList = tenderLogDao.list(pageVO);
		int total = tenderLogDao.total(pageVO);
		ListVo<TenderLogVO> list = new ListVo<TenderLogVO>( total, TenderLogVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( TenderLogVO tenderLogVO ) {

		int row = tenderLogDao.insert(tenderLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( TenderLogVO tenderLogVO ) {

		int row = tenderLogDao.modify(tenderLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( TenderLogVO tenderLogVO ) {

		int row = tenderLogDao.delete(tenderLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
