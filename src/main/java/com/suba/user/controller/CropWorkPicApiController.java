package com.suba.user.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.CropWorkPicDao;
import com.suba.user.menuController.MenuController;
import com.suba.vo.CropWorkPicVO;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/api/cropworkpic")
public class CropWorkPicApiController extends MenuController{

	@Autowired
	private CropWorkPicDao cropWorkPicDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<CropWorkPicVO> list( PageVO pageVO, HttpServletRequest request, HttpServletResponse response,  HttpSession session ) {

		List<CropWorkPicVO> boardCategoryVOList = cropWorkPicDao.list(pageVO);
		int total = cropWorkPicDao.total(pageVO);
		ListVo<CropWorkPicVO> list = new ListVo<CropWorkPicVO>( total, boardCategoryVOList );
		return list;
	}


	@RequestMapping(value="/view")
	@ResponseBody
	public Result view( CropWorkPicVO workPic, HttpServletRequest request, HttpServletResponse response,  HttpSession session ) {

		CropWorkPicVO cropWorkPic = cropWorkPicDao.view(workPic);
		Result rs = new Result("ok", cropWorkPic);

		return rs;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( CropWorkPicVO boardReplyVO, HttpServletRequest request, HttpServletResponse response,  HttpSession session ) {

		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( boardReplyVO.getCropNo() != memberVO.getMemberNo() && !isAdmin(session) ) {
			return new Result("fail", "본인의 업체가 아닙니다");
		}

		int picNo = findFirstPic(boardReplyVO.getContent());
		boardReplyVO.setFileNo(picNo);

		int row = cropWorkPicDao.insert(boardReplyVO);
		Result rs = new Result("ok", row);

		return rs;
	}


	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( CropWorkPicVO boardReplyVO, HttpServletRequest request, HttpServletResponse response,  HttpSession session  ) {

		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		// TODO admin 예외처리 필요
		if( boardReplyVO.getCropNo() != memberVO.getMemberNo() && !isAdmin(session) ) {
			return new Result("fail", "본인의 업체가 아닙니다");
		}

		int picNo = findFirstPic(boardReplyVO.getContent());
		boardReplyVO.setFileNo(picNo);

		int row = cropWorkPicDao.modify(boardReplyVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete(  HttpServletRequest request, HttpServletResponse response,  HttpSession session, CropWorkPicVO boardReplyVO ) {

		// 댓글 삭제 권한은 본인이거나
		// 관리자거나
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( boardReplyVO.getCropNo() != memberVO.getMemberNo() && !isAdmin(session) ) {
			return new Result("fail", "본인의 업체가 아닙니다");
		}

		int row = cropWorkPicDao.delete(boardReplyVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	private int findFirstPic( String content ){
		Pattern pattern = Pattern.compile("/api/file/download/([0-9]*)");

		Matcher match = pattern.matcher(content);
		if( match.find() ) {
			try{
				return Integer.parseInt(match.group(1));
			}catch(Exception e){
				return 0;
			}
		}
		return 0;

	}
}
