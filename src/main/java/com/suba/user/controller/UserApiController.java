package com.suba.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.UserDao;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.ResultVO;

@Controller
@RequestMapping(value="/api/user")
public class UserApiController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<MemberVO> list( PageVO pageVO ) {

		List<MemberVO> memberVOList = userDao.list(pageVO);
		int total = userDao.total(pageVO);
		ListVo<MemberVO> list = new ListVo<MemberVO>( total, memberVOList );
		return list;
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public ResultVO<MemberVO> view( PageVO pageVO ) {

		return new ResultVO<MemberVO>(userDao.view(pageVO));
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( MemberVO memberVO ) {

		int row = userDao.insert(memberVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( MemberVO memberVO ) {

		int row = userDao.modify(memberVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( MemberVO memberVO ) {

		int row = userDao.delete(memberVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
