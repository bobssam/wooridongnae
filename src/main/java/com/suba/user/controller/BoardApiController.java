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
import com.suba.user.menuController.MenuController;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/api/board")
public class BoardApiController extends MenuController{

	@Autowired
	private BoardDao boardDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<BoardVO> list( PageVO pageVO ) {

		List<BoardVO> boardList = boardDao.list(pageVO);
		int total = boardDao.total(pageVO);
		ListVo<BoardVO> list = new ListVo<BoardVO>( total, boardList );
		return list;
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public BoardVO detail( HttpServletRequest request, HttpServletResponse response,  HttpSession session, int boardNo ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

		BoardVO boardVo = new BoardVO();
		boardVo.setBoardNo(boardNo);
		boardDao.plusHit(boardVo);
		
		BoardVO result = boardDao.view(boardNo);
		if( memberVO != null && result.getMemberNo() == memberVO.getMemberNo() )
			result.setIsMine(1);
		if( isAdmin(session) )
			result.setIsMine(1);
		return result;
	}

	
	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( HttpServletRequest request, HttpServletResponse response,  HttpSession session, BoardVO boardVO ) {
		
		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		boardVO.setMemberNo(memberVO.getMemberNo());
		int row = boardDao.insert(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( HttpServletRequest request, HttpServletResponse response,  HttpSession session, BoardVO boardCategoryVO ) {
		
		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) || !isAdmin(session) ) {
			sendAccessDenine(response);
			return null;
		} 

		int row = boardDao.modify(boardCategoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( HttpServletRequest request, HttpServletResponse response,  HttpSession session, BoardVO boardVO ) {
		
		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		} 
		
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( !isAdmin(session) ) {
			BoardVO result = boardDao.view(boardVO.getBoardNo());
			if( result.getMemberNo() != memberVO.getMemberNo() )  {
				sendAccessDenine(response);
				return null;
			}
		}

		int row = boardDao.delete(boardVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
