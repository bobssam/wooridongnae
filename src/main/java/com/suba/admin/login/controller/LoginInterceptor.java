package com.suba.admin.login.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.suba.util.Define;
import com.suba.vo.MemberVO;

@Controller
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		ArrayList<String> allowList = new ArrayList<String>();
		allowList.add("/main/admin/api/login/login");
		allowList.add("/main/admin/api/login/logout");
		allowList.add("/main/admin/api/login/myPermission");
		allowList.add("/admin/api/login/login");
		allowList.add("/admin/api/login/logout");
		allowList.add("/admin/api/login/myPermission");

		try {
			String path = request.getRequestURI();
//			System.out.println(path);
			// 절대 허용경로는 오픈
			if( allowList.indexOf(path) >= 0 ) {
				return true;
			}
			// 관리자 API를 제외하곤 오픈
			if( !(path.indexOf("/main/admin/api") == 0 || path.indexOf("/admin/api") == 0) ) {
				return true;
			}

			MemberVO memberVO = (MemberVO) request.getSession().getAttribute("memberVO");
			if(memberVO == null || memberVO.getType() != Define.Common.ADMIN_TYPE){
				response.setStatus(403);
				response.getWriter().write("permission denined");
				response.getWriter().flush();
				response.getWriter().close();
				return false;
			}


			//String permissionKey = permissionService.getPermssionKey(path);
			/*
			// 기능 접근 권한이 없다
			if( !permission.hasPermission(permissionKey) ) {

				response.setStatus(402);
				response.getWriter().write("permission denined");
				response.getWriter().flush();
				response.getWriter().close();
				return false;
			}
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
