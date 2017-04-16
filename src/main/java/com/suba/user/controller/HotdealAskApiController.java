package com.suba.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.HotdealAskDao;
import com.suba.user.dao.HotdealDao;
import com.suba.vo.HotdealAskVO;
import com.suba.vo.HotdealVO;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/api/hotdealask")
public class HotdealAskApiController {

	@Autowired
	private HotdealAskDao hotdealAskDao;
	@Autowired
	private HotdealDao hotdealDao;

	@RequestMapping(value="/total")
	@ResponseBody
	public int total( PageVO pageVO ) {
		return hotdealAskDao.total(pageVO);
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public HotdealAskVO view( HotdealAskVO hotdealAskVO, HttpSession session  ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( memberVO != null )
			hotdealAskVO.setMemberNo(memberVO.getMemberNo());
		return hotdealAskDao.view(hotdealAskVO);
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( HotdealAskVO hotdealAskVO, HttpSession session ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( memberVO != null ) {
			hotdealAskVO.setMemberNo( memberVO.getMemberNo() );
		}

		int row = hotdealAskDao.add(hotdealAskVO);
		Result rs = new Result("ok", row);

		HotdealVO hotdealVO = new HotdealVO();
		hotdealVO.setHotdealNo(hotdealAskVO.getHotdealNo());
		hotdealDao.plusAsk(hotdealVO);

		return rs;
	}

}
