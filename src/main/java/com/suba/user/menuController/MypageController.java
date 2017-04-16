package com.suba.user.menuController;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suba.user.service.MemberService;
import com.suba.vo.MemberVO;
import com.suba.vo.SmallAreaVO;

@Controller
@RequestMapping(value="/mypage")
public class MypageController extends MenuController{

	@Autowired
	private MemberService memberService;

	@RequestMapping(value="/")
	public ModelAndView main( ModelAndView model, HttpServletRequest request, HttpSession httpSession ) {

		if( !isLogin( httpSession ) ) {
			model.setViewName( sendRedirect(request ) );
			return model;
		}
		MemberVO memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		// 정보 갱신이 될수 있기 때문에 다시 가져온다.
		MemberVO newMemberVO = memberService.getMember( memberVO );
		model.addObject("memberVO", newMemberVO);

		if( memberVO.getType() == MemberVO.TYPE_USER ) {

			HashMap<String, Long> tenderStats = memberService.selectMyTenderStats(memberVO);
			HashMap<String, Integer> stats = memberService.selectTenderStats(memberVO);

			model.addObject("tenderStats", tenderStats);
			model.addObject("stats", stats);
			model.setViewName("mypage/main");


		} else {

			model.addObject("gradeName", memberVO.getGradeName());
			model.addObject("gradeKrName", memberVO.getGradeKrName());

			HashMap<String, Long> tenderStats = memberService.selectMyDealerTenderStats(memberVO);
			HashMap<String, Integer> stats = memberService.selectDealerTenderStats(memberVO);

			model.addObject("tenderStats", tenderStats);
			model.addObject("stats", stats);
			model.setViewName("mypage/main_dealer");
		}
		return model;
	}

	@RequestMapping(value="/dealer")
	public String dealer( HttpServletRequest request, HttpSession httpSession ) {

		if( !isLogin( httpSession ) ) {
			return sendRedirect( request );
		}
		return "mypage/main_dealer";
	}


	@RequestMapping(value="/memo")
	public String terms( HttpServletRequest request, HttpSession httpSession  ) {

		if( !isLogin( httpSession ) ) {
			return sendRedirect( request );
		}
		return "mypage/memo";
	}

	@RequestMapping(value="/modify")
	public ModelAndView modifyUserinfo( ModelAndView model, HttpServletRequest request, HttpSession httpSession  ) {

		if( !isLogin( httpSession ) ) {
			model.setViewName( sendRedirect(request ) );
			return model;
		}

		MemberVO  memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		model.addObject("memberVO", memberVO);
		List<SmallAreaVO> wantAreaList = memberService.getWantArea(memberVO.getWantArea());
		model.addObject("wantArea", wantAreaList );

		if( memberVO.getType() == MemberVO.TYPE_USER )
			model.setViewName("mypage/modify_userinfo");
		else
			model.setViewName("mypage/modify_userinfo_dealer");

		return model;
	}

	@RequestMapping(value="/payment")
	public String payment( HttpServletRequest request, HttpSession httpSession ) {

		if( !isLogin( httpSession ) ) {
			return sendRedirect( request );
		}
		return "mypage/payment";
	}
}
