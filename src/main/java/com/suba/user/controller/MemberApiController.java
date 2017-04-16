package com.suba.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.Result;
import com.suba.user.dao.LoginHistoryDao;
import com.suba.user.dao.UserDao;
import com.suba.user.menuController.MenuController;
import com.suba.user.service.MemberService;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/member")
public class MemberApiController extends MenuController{
	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginHistoryDao loginHistoryDao;
	@Autowired
	private MemberService memberService;

	@RequestMapping(value="/duplicateCheckId")
	@ResponseBody
	public Result duplicateCheckId(MemberVO memberVO) {
		return memberService.duplicateCheckId(memberVO);
	}

	@RequestMapping(value="/doLogin")
	@ResponseBody
	public Result doLogin( MemberVO memberVO, HttpSession httpSession, HttpServletRequest request ) {
		Result result = memberService.doLogin(memberVO, httpSession, request);

		if( "ok".equals(result.getStatus()) ) {
			MemberVO meVo = (MemberVO) httpSession.getAttribute("memberVO");
			if( meVo.getType() == MemberVO.TYPE_DEALER ) {
				if( meVo.getWantArea() == null ) result.setReason("needWantArea");
			}
		}

		return result;
	}

	@RequestMapping(value="/doLogout")
	@ResponseBody
	public Result doLogout( HttpSession httpSession, HttpServletRequest request ) {
		return memberService.doLogout(httpSession, request);
	}

	@RequestMapping(value="/doPasswordChange")
	@ResponseBody
	public String doPasswordChange( HttpSession session, HttpServletResponse response, MemberVO memberVO) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}

		userDao.updatePassword( memberVO );
		return "member/reg";
	}


	@RequestMapping(value="/doRegMember")
	@ResponseBody
	public Result doRegMember( MemberVO memberVO) {
		return memberService.insertMember(memberVO);
	}

	@RequestMapping(value="/doModifyMember")
	@ResponseBody
	public Result doModifyMember( MemberVO memberVO, HttpServletResponse response, HttpServletRequest request,  HttpSession session, String prev_pw ) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO member = (MemberVO) session.getAttribute("memberVO");
		memberVO.setId(member.getId());
		memberVO.setMemberNo(member.getMemberNo());

		// 딜러 회원에 한해서 정보 보정
		String areaCode[] = request.getParameterValues("areaCode[]");
		String categoryNo3s[] = request.getParameterValues("categoryNo3s[]");
		if( categoryNo3s == null && request.getParameter("categoryNo3s") != null ) {
			categoryNo3s = new String[1];
			categoryNo3s[0] = request.getParameter("categoryNo3s");
		} else {
			categoryNo3s = new String[0];
		}

		if( areaCode == null ) {
			String areaCodeStr = request.getParameter("areaCode");
			if( areaCodeStr != null ) {
				areaCode = new String[1];
				areaCode[0] = areaCodeStr;
			} else {
				areaCode = new String[0];
			}
		}

		if( categoryNo3s != null ) {
			int cate3[] = new int[categoryNo3s.length];
			for( int i=0;i<categoryNo3s.length;i++ ) {
				cate3[ i ] = Integer.parseInt(categoryNo3s[i]);
			}
			memberVO.setCategoryNo3(cate3);
		}

		memberVO.setType(member.getType());
		return memberService.modifyMember(session, prev_pw, memberVO, areaCode);
	}

	@RequestMapping(value="/doModifyMemberArea")
	@ResponseBody
	public Result doModifyMemberArea( MemberVO memberVO, HttpServletResponse response, HttpServletRequest request, HttpSession session ) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO member = (MemberVO) session.getAttribute("memberVO");
		memberVO.setId(member.getId());
		memberVO.setMemberNo(member.getMemberNo());

		String areaCode[] = request.getParameterValues("areaCode[]");
		String categoryNo3s[] = request.getParameterValues("categoryNo3s[]");
		if( categoryNo3s == null && request.getParameter("categoryNo3s") != null ) {
			categoryNo3s = new String[1];
			categoryNo3s[0] = request.getParameter("categoryNo3s");
		} else {
			categoryNo3s = new String[0];
		}

		if( areaCode == null ) {
			String areaCodeStr = request.getParameter("areaCode");
			if( areaCodeStr != null ) {
				areaCode = new String[1];
				areaCode[0] = areaCodeStr;
			} else {
				areaCode = new String[0];
			}
		}

		int cate3[] = new int[categoryNo3s.length];
		for( int i=0;i<categoryNo3s.length;i++ ) {
			cate3[ i ] = Integer.parseInt(categoryNo3s[i]);
		}
		memberVO.setCategoryNo3(cate3);

		return memberService.modifyMemberArea(session, memberVO, areaCode);
	}

	@RequestMapping(value="/doFindPassword")
	@ResponseBody
	public Result doFindPassword( MemberVO memberVO) {
		return memberService.findPassword(memberVO);
	}
}
