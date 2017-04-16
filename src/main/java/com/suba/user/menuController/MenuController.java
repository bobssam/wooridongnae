package com.suba.user.menuController;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suba.util.PropertieManager;
import com.suba.vo.MemberVO;

public class MenuController {

	private PropertieManager pm = new PropertieManager();
	protected boolean isLogin( HttpSession session  ) {

		return session.getAttribute("memberVO") != null;
	}

	protected boolean isAdmin( HttpSession session  ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( memberVO != null && memberVO.getType() == MemberVO.TYPE_ADMIN ) return true;
		return false;
	}

	protected String sendRedirect( HttpServletRequest request ) {

		//return "redirect:"+pm.get("root")+"/member/login?rurl="+request.getRequestURI()+"?"+request.getQueryString();
		return "redirect:/member/login?rurl="+request.getRequestURI()+"?"+request.getQueryString();
	}

	protected void sendAccessDenine( HttpServletResponse response ) {

		try {
			response.sendError(403);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
