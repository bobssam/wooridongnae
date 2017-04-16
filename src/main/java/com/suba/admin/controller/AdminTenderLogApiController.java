package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminTenderLogDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.TenderLogVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/tenderLog")
public class AdminTenderLogApiController {

	@Autowired
	private AdminTenderLogDao tenderLogDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<TenderLogVO> selectTenderLogList( PageVO pageVO ) {

		List<TenderLogVO> TenderLogVOList = tenderLogDao.list(pageVO);
		int total = tenderLogDao.total(pageVO);
		ListVo<TenderLogVO> list = new ListVo<TenderLogVO>( total, TenderLogVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addTenderLog( TenderLogVO tenderLogVO ) {

		int row = tenderLogDao.insert(tenderLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateTenderLog( TenderLogVO tenderLogVO ) {

		int row = tenderLogDao.modify(tenderLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteTenderLog( TenderLogVO tenderLogVO ) {

		int row = tenderLogDao.delete(tenderLogVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
