package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminTenderReplyDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.TenderReplyVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/tenderReply")
public class AdminTenderReplyApiController {

	@Autowired
	private AdminTenderReplyDao tenderReplyDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<TenderReplyVO> selectTenderReplyList( PageVO pageVO ) {

		List<TenderReplyVO> TenderReplyVOList = tenderReplyDao.list(pageVO);
		int total = tenderReplyDao.total(pageVO);
		ListVo<TenderReplyVO> list = new ListVo<TenderReplyVO>( total, TenderReplyVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addTenderReply( TenderReplyVO tenderReplyVO ) {

		int row = tenderReplyDao.insert(tenderReplyVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateTenderReply( TenderReplyVO tenderReplyVO ) {

		int row = tenderReplyDao.modify(tenderReplyVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteTenderReply( TenderReplyVO tenderReplyVO ) {

		int row = tenderReplyDao.delete(tenderReplyVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
