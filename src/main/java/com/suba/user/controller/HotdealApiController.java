package com.suba.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.user.dao.HotdealDao;
import com.suba.user.menuController.MenuController;
import com.suba.vo.HotdealVO;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/api/hotdeal")
public class HotdealApiController extends MenuController{

	@Autowired
	private HotdealDao hotdealDao;

	@RequestMapping(value="/total")
	@ResponseBody
	public int total( PageVO pageVO ) {
		return hotdealDao.total(pageVO);
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public HotdealVO view( HotdealVO hotdealVO, HttpSession session, HttpServletResponse response ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( memberVO != null )
			hotdealVO.setMemberNo(memberVO.getMemberNo());
		return hotdealDao.view(hotdealVO);
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<HotdealVO> list( PageVO pageVO, HttpSession session ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( memberVO != null )
			pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
		else
			pageVO.getWhere().putNumber("memberNo", 0);

		List<HotdealVO> list = hotdealDao.list(pageVO);

		if( memberVO != null )
		for( HotdealVO hotdealVO : list  ) {
			hotdealVO.setMemberNo(memberVO.getMemberNo());
		}

		return new ListVo<HotdealVO>(list.size(), list) ;
	}


}
