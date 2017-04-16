package com.suba.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminUserDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.CategoryDao;
import com.suba.user.service.PushService;
import com.suba.vo.CategoryVO;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.ResultVO;
import com.suba.vo.UserCategoryVO;

@Controller
@RequestMapping(value = "/admin/api/user")
public class AdminUserApiController {

	@Autowired
	private AdminUserDao userDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PushService pushService;
	

	@RequestMapping(value = "/list")
	@ResponseBody
	public ListVo<MemberVO> selectBoardList(PageVO pageVO) {

		List<MemberVO> memberVOList = userDao.list(pageVO);
		int total = userDao.total(pageVO);
		ListVo<MemberVO> list = new ListVo<MemberVO>(total, memberVOList);
		
		return list;
	}

	@RequestMapping(value = "/listCategory3")
	@ResponseBody
	public ListVo<CategoryVO> listCategory3(PageVO pageVO) {

		List<CategoryVO> memberVOList = userDao.listCategory3(pageVO);
		ListVo<CategoryVO> list = new ListVo<CategoryVO>(memberVOList.size(), memberVOList);
		return list;
	}

	
	@RequestMapping(value = "/view")
	@ResponseBody
	public ResultVO<MemberVO> view(PageVO pageVO) {

		return new ResultVO<MemberVO>(userDao.view(pageVO));
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public Result addBoard(MemberVO memberVO) {

		int row = userDao.insert(memberVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value = "/modify")
	@ResponseBody
	public Result updateBoard(MemberVO memberVO) {

		int row = userDao.modify(memberVO);
		Result rs = new Result("ok", row);
		
		userDao.clearCategory3(memberVO);
		
		int[] categorys = memberVO.getCategoryNo3();
		for( int cate : categorys ) {
			
			UserCategoryVO userCategoryVO = new UserCategoryVO();
			userCategoryVO.setMemberNo(memberVO.getMemberNo());
			userCategoryVO.setCategoryNo(cate);
			userDao.insertCategory3(userCategoryVO);
		}
		return rs;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public Result deleteBoard(MemberVO memberVO) {

		int row = userDao.delete(memberVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value = "/sendPush")
	@ResponseBody
	public Result sendPush(String title, String msg) throws UnsupportedEncodingException {

		Result rs = pushService.sendPush(title, msg, null);

		return rs;
	}
}
