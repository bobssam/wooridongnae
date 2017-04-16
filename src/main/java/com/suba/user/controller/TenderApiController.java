package com.suba.user.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.TenderDao;
import com.suba.user.menuController.MenuController;
import com.suba.user.service.TenderService;
import com.suba.util.ExcelTemplate;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.TenderReplyVO;
import com.suba.vo.TenderVO;

@Controller
@RequestMapping(value="/api/tender")
public class TenderApiController extends MenuController{

	@Autowired
	private TenderDao tenderDao;
	@Autowired
	private ExcelTemplate excelTemplate;
	@Autowired
	private TenderService tenderService;

	private SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<TenderVO> list( HttpServletRequest request, HttpServletResponse response,  HttpSession session, PageVO pageVO ) {


		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

		return tenderService.getTenderList(pageVO, memberVO);
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public TenderVO view( HttpServletRequest request, HttpServletResponse response,  HttpSession session, PageVO pageVO ) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		return tenderService.getTender(pageVO, memberVO);
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( HttpServletRequest request, HttpServletResponse response,  HttpSession session, TenderVO tenderVO ) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}

		int row = tenderDao.insert(tenderVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( HttpServletRequest request, HttpServletResponse response,  HttpSession session, TenderVO tenderVO ) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		int row = tenderDao.modifyTender(tenderVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( HttpServletRequest request, HttpServletResponse response,  HttpSession session, TenderVO tenderVO ) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		int row = tenderDao.delete(tenderVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@ResponseBody
	@RequestMapping(value="/excel")
	public List<Map<String, Object>> testMethod( PageVO pageVO , HttpServletResponse response ) {


		List<HashMap<String, Object>> TenderVOList = tenderDao.listExcel(pageVO);

		String productName= "입찰리스트";
		try {
			productName= URLEncoder.encode(productName, "utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}

		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "Attachment;Filename=\""+ productName+"_"+sdf.format(new Date())+".xlsx\"");

		try {
			excelTemplate.makeExcelFile( "tender", TenderVOList, response.getOutputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 딜러용 입찰 리스트
	 *
	 * @param request
	 * @param response
	 * @param session
	 * @param pageVO
	 * @return
	 */
	@RequestMapping(value="/myList/list")
	@ResponseBody
	public ListVo<TenderVO> myListList( HttpServletRequest request, HttpServletResponse response,  HttpSession session, PageVO pageVO ) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

		if( MemberVO.TYPE_DEALER == memberVO.getType() )
			return tenderService.getDealerMyTenderList(pageVO, memberVO);
		else
			return tenderService.getMyTenderList(pageVO, memberVO);
	}

	/**
	 * 유저용 입찰중 리스트
	 *
	 * @param request
	 * @param response
	 * @param session
	 * @param pageVO
	 * @return
	 */
	@RequestMapping(value="/tendering/list")
	@ResponseBody
	public ListVo<TenderReplyVO> tenderingList( HttpServletRequest request, HttpServletResponse response,  HttpSession session, PageVO pageVO ) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

		return tenderService.getMyTenderReplyList(pageVO, memberVO);
	}

}
