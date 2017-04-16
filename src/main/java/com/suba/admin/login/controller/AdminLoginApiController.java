package com.suba.admin.login.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vertx.java.core.json.JsonObject;

import com.suba.admin.dao.AdminLoginHistoryDao;
import com.suba.admin.dao.AdminNoteDao;
import com.suba.admin.dao.AdminUserDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.LoginHistoryVO;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/admin/api/login")
public class AdminLoginApiController {

	@Autowired
	private AdminUserDao userDao;
	@Autowired
	private AdminLoginHistoryDao loginHistoryDao;
	@Autowired
	private AdminNoteDao noteDao;


	@ResponseBody
	@RequestMapping(value="login")
	public Result login( MemberVO memberVO, HttpSession httpSession, HttpServletResponse response, HttpServletRequest request) throws Exception{
		MemberVO dbAdminInfo = userDao.login(memberVO);
		if( dbAdminInfo == null ) {
			return new Result("-2", "ID 또는 패스워드를 확인하세요.");
		} else {
			httpSession.setAttribute("memberVO", dbAdminInfo);
			LoginHistoryVO loginHistoryVO = new LoginHistoryVO();

			String clientIp = request.getHeader( "HTTP_X_FORWARDED_FOR" );
			if ( null == clientIp || clientIp.length() == 0 || clientIp.toLowerCase().equals( "unknown" ) ) {
				clientIp = request.getHeader( "REMOTE_ADDR" );
			}
			if ( null == clientIp || clientIp.length() == 0 || clientIp.toLowerCase().equals( "unknown" ) ) {
				clientIp = request.getRemoteAddr();
			}
			loginHistoryVO.setMemberNo( dbAdminInfo.getMemberNo() );
			loginHistoryVO.setIp( clientIp );
			loginHistoryDao.insert( loginHistoryVO );
		}

		return new Result();
	}

	@ResponseBody
	@RequestMapping(value="logout")
	public Result logout( HttpSession httpSession ) {

		httpSession.removeAttribute("memberVO");
		return new Result();

	}

	@ResponseBody
	@RequestMapping(value="info")
	public Result info( HttpSession httpSession ) {

		Result res = new Result();
		PageVO pageVO = new PageVO();
		MemberVO memberVO =  (MemberVO) httpSession.getAttribute("memberVO");
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		if( memberVO != null ) {
			JsonObject json = new JsonObject();
			json.putNumber("memberNo", memberVO.getMemberNo());
			pageVO.setWhere( json );
			int total = noteDao.selectTotal(pageVO);
			data.put("memberNo", memberVO.getMemberNo());
			data.put("noteCnt", total);
			data.put("id", memberVO.getId());

			res.setData(data);
			
			
	
		} else {
			res.setStatus("fail");
			res.setReason("로그인 상태가 아님");
		}

		return res;
	}

//	@ResponseBody
//	@RequestMapping(value="loginCheck")
//	public AdminInfoVO loginCheck( HttpSession httpSession ) {
//		return (AdminInfoVO) httpSession.getAttribute("adminInfo");
//	}
}
