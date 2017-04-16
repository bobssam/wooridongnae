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
import com.suba.user.dao.CropReplyDao;
import com.suba.user.menuController.MenuController;
import com.suba.vo.BoardReplyVO;
import com.suba.vo.BoardVO;
import com.suba.vo.CropReplyVO;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/api/cropreply")
public class CropReplyApiController extends MenuController{

	@Autowired
	private CropReplyDao cropReplyDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<CropReplyVO> list( PageVO pageVO, HttpServletRequest request, HttpServletResponse response,  HttpSession session ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		List<CropReplyVO> boardCategoryVOList = cropReplyDao.list(pageVO);
		int total = cropReplyDao.total(pageVO);
		ListVo<CropReplyVO> list = new ListVo<CropReplyVO>( total, boardCategoryVOList );

		boolean isAdmin = isAdmin(session);
		for( CropReplyVO br : boardCategoryVOList ) {
			if( isAdmin )
				br.setIsMine(1);
			else if( memberVO != null && memberVO.getMemberNo() == br.getMemberNo()  )
				br.setIsMine(1);
		}
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( CropReplyVO boardReplyVO, HttpServletRequest request, HttpServletResponse response,  HttpSession session ) {

		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		boardReplyVO.setMemberNo(memberVO.getMemberNo());

		int row = cropReplyDao.insert(boardReplyVO);
		Result rs = new Result("ok", row);

		// 댓글 숫자를 하나 늘림
		/*
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardNo(boardReplyVO.getBoardNo());
		boardDao.plusReplyCount(boardVO);
		*/

		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( CropReplyVO boardCategoryVO ) {

		int row = cropReplyDao.modify(boardCategoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete(  HttpServletRequest request, HttpServletResponse response,  HttpSession session, CropReplyVO boardReplyVO ) {

		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		CropReplyVO rvo = cropReplyDao.view(boardReplyVO);

		if( isAdmin(session) ||  memberVO.getMemberNo() == rvo.getMemberNo() ) {
			int row = cropReplyDao.delete(boardReplyVO);
			Result rs = new Result("ok", row);
			return rs;

			// 댓글 숫자를 하나 늘림
			/*
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardNo(rvo.getBoardNo());
			boardDao.minersReplyCount(boardVO);
			return rs;
			*/
		}

		return new Result( "fail",  "본인의 글이 아닙니다" );
	}
}
