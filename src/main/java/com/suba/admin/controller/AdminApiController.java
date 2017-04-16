package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suba.admin.dao.AdminUserDao;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/admin/api/admin")
public class AdminApiController {

	@Autowired
	private AdminUserDao userDao;

	@RequestMapping(value="selectMemberList")
	public List<MemberVO> selectMemberList() {
		List<MemberVO> memberVOList = userDao.list(null);
		return memberVOList;
	}
}
