package com.suba.user.menuController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.service.MemoService;
import com.suba.user.service.TenderService;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.NoteVO;

@Controller
@RequestMapping(value="/mypage")
public class MypageApiController extends MenuController{
	
	@Autowired
	private TenderService tenderService;
	@Autowired
	private MemoService memoService;
	
	@RequestMapping(value="/user/tender/ing")
	@ResponseBody
	public Result userTendering(HttpServletResponse response, HttpSession httpSession, PageVO pageVO) {
		
		if( !isLogin( httpSession ) ) {
			sendAccessDenine(response);
			return null;
		}		
		MemberVO  memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		return tenderService.myPageTender(memberVO, "1");
	}

	@RequestMapping(value="/user/tender/end")
	@ResponseBody
	public Result userTendered(HttpServletResponse response, HttpSession httpSession, PageVO pageVO) {
		
		if( !isLogin( httpSession ) ) {
			sendAccessDenine(response);
			return null;
		}		

		MemberVO  memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		return tenderService.myPageTender(memberVO, "2");
	}
	@RequestMapping(value="/user/tender/pass")
	@ResponseBody
	public Result userTenderpass(HttpServletResponse response, HttpSession httpSession, PageVO pageVO) {
		
		if( !isLogin( httpSession ) ) {
			sendAccessDenine(response);
			return null;
		}		

		MemberVO  memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		return tenderService.myPageTender(memberVO, "3");
	}
	@RequestMapping(value="/user/tender/ok")
	@ResponseBody
	public Result userTenderok(HttpServletResponse response, HttpSession httpSession, PageVO pageVO) {
		
		if( !isLogin( httpSession ) ) {
			sendAccessDenine(response);
			return null;
		}		
		
		MemberVO  memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		return tenderService.myPageTender(memberVO, "4");
	}
	@RequestMapping(value="/user/memo/list")
	@ResponseBody
	public ListVo<NoteVO> userMemoList(HttpServletResponse response, HttpSession httpSession) {
		
		if( !isLogin( httpSession ) ) {
			sendAccessDenine(response);
			return null;
		}

		MemberVO  memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		ListVo<NoteVO> list = new ListVo<NoteVO>( memoService.myMemoTotal(memberVO), memoService.myMemoList(memberVO) );
		return list;
	}
	@RequestMapping(value="/user/memo/read")
	@ResponseBody
	public Result userMemoRead(HttpServletResponse response, HttpSession httpSession, PageVO pageVO) {
		
		if( !isLogin( httpSession ) ) {
			sendAccessDenine(response);
			return null;
		}

		MemberVO  memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
		if( memoService.myMemoRead(pageVO) > 0 ) {
			return new Result();
		}
		return new Result("fail");
	}
	@RequestMapping(value="/user/memo/del")
	@ResponseBody
	public Result userMemoDel(HttpServletResponse response, HttpSession httpSession, PageVO pageVO) {
		
		if( !isLogin( httpSession ) ) {
			sendAccessDenine(response);
			return null;
		}

		MemberVO  memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
		if( memoService.myMemoDel(pageVO) > 0 ) {
			return new Result();
		}
		return new Result("fail");
	}
	@RequestMapping(value="/user/memo/reply")
	@ResponseBody
	public Result userMemoReply(HttpServletResponse response, HttpSession httpSession, PageVO pageVO) {
		
		if( !isLogin( httpSession ) ) {
			sendAccessDenine(response);
			return null;
		}

		MemberVO  memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
		if( memoService.replyMemo(pageVO) > 0 ) {
			return new Result();
		}
		return new Result("fail");
	}		
	@RequestMapping(value="/user/memo/total")
	@ResponseBody
	public int userMemoTotal(HttpServletResponse response, HttpSession httpSession) {
		
		if( !isLogin( httpSession ) ) {
			sendAccessDenine(response);
			return 0;
		}		
		
		MemberVO  memberVO = (MemberVO) httpSession.getAttribute("memberVO");
		return memoService.myUnreadMemoTotal(memberVO);
	}

}
