package com.suba.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.WorkPicReplyDao;
import com.suba.user.menuController.MenuController;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.WorkPicReplyVO;

@Controller
@RequestMapping(value="/api/workpicreply")
public class WorkPicReplyApiController extends MenuController{

	@Autowired
	private WorkPicReplyDao workPicReplyDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<WorkPicReplyVO> list( PageVO pageVO, HttpServletRequest request, HttpServletResponse response,  HttpSession session ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		List<WorkPicReplyVO> boardCategoryVOList = workPicReplyDao.list(pageVO);
		int total = workPicReplyDao.total(pageVO);
		ListVo<WorkPicReplyVO> list = new ListVo<WorkPicReplyVO>( total, boardCategoryVOList );

		boolean isAdmin = isAdmin(session);
		for( WorkPicReplyVO br : boardCategoryVOList ) {
			if( isAdmin )
				br.setIsMine(1);
			else if( memberVO != null && memberVO.getMemberNo() == br.getMemberNo()  )
				br.setIsMine(1);
		}
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( WorkPicReplyVO boardReplyVO, HttpServletRequest request, HttpServletResponse response,  HttpSession session ) {

		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		boardReplyVO.setMemberNo(memberVO.getMemberNo());

		int row = workPicReplyDao.insert(boardReplyVO);
		Result rs = new Result("ok", row);

		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( WorkPicReplyVO boardCategoryVO ) {

		int row = workPicReplyDao.modify(boardCategoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete(  HttpServletRequest request, HttpServletResponse response,  HttpSession session, WorkPicReplyVO boardReplyVO ) {

		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		WorkPicReplyVO rvo = workPicReplyDao.view(boardReplyVO);

		if( isAdmin(session) ||  memberVO.getMemberNo() == rvo.getMemberNo() ) {
			int row = workPicReplyDao.delete(boardReplyVO);
			Result rs = new Result("ok", row);
			return rs;
		}

		return new Result( "fail",  "본인의 글이 아닙니다" );
	}
}
