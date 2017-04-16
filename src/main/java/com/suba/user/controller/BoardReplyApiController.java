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
import com.suba.user.dao.BoardDao;
import com.suba.user.dao.BoardReplyDao;
import com.suba.user.menuController.MenuController;
import com.suba.vo.BoardReplyVO;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/api/boardreply")
public class BoardReplyApiController extends MenuController{

	@Autowired
	private BoardReplyDao boardReplyDao;
	@Autowired
	private BoardDao boardDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardReplyVO> list( PageVO pageVO, HttpServletRequest request, HttpServletResponse response,  HttpSession session ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		List<BoardReplyVO> boardCategoryVOList = boardReplyDao.list(pageVO);
		int total = boardReplyDao.total(pageVO);
		ListVo<BoardReplyVO> list = new ListVo<BoardReplyVO>( total, boardCategoryVOList );
		
		boolean isAdmin = isAdmin(session);
		for( BoardReplyVO br : boardCategoryVOList ) {
			if( isAdmin )
				br.setIsMine(1);
			else if( memberVO != null && memberVO.getMemberNo() == br.getMemberNo()  )
				br.setIsMine(1);
		}
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( BoardReplyVO boardReplyVO, HttpServletRequest request, HttpServletResponse response,  HttpSession session ) {

		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		boardReplyVO.setMemberNo(memberVO.getMemberNo());
		
		int row = boardReplyDao.insert(boardReplyVO);
		Result rs = new Result("ok", row);
		
		// 댓글 숫자를 하나 늘림
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardNo(boardReplyVO.getBoardNo());
		boardDao.plusReplyCount(boardVO);
		
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( BoardReplyVO boardCategoryVO ) {

		int row = boardReplyDao.modify(boardCategoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete(  HttpServletRequest request, HttpServletResponse response,  HttpSession session, BoardReplyVO boardReplyVO ) {

		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		BoardReplyVO rvo = boardReplyDao.view(boardReplyVO);
		
		if( isAdmin(session) ||  memberVO.getMemberNo() == rvo.getMemberNo() ) {
			int row = boardReplyDao.delete(boardReplyVO);
			Result rs = new Result("ok", row);
			
			// 댓글 숫자를 하나 늘림
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardNo(rvo.getBoardNo());
			boardDao.minersReplyCount(boardVO);
			return rs;
		}
		
		return new Result( "fail",  "본인의 글이 아닙니다" );
	}
}
