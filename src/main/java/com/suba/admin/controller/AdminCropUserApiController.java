package com.suba.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminCropUserDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.CategoryDao;
import com.suba.user.service.PushService;
import com.suba.vo.CropVO;
import com.suba.vo.ListVo;
import com.suba.vo.ResultVO;

@Controller
@RequestMapping(value = "/admin/api/cropuser")
public class AdminCropUserApiController {

	@Autowired
	private AdminCropUserDao cropUserDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PushService pushService;
	

	@RequestMapping(value = "/list")
	@ResponseBody
	public ListVo<CropVO> selectBoardList(PageVO pageVO) {

		List<CropVO> memberVOList = cropUserDao.list(pageVO);
		int total = cropUserDao.total(pageVO);
		ListVo<CropVO> list = new ListVo<CropVO>(total, memberVOList);
		
		return list;
	}

	@RequestMapping(value = "/view")
	@ResponseBody
	public ResultVO<CropVO> view(PageVO pageVO) {

		return new ResultVO<CropVO>(cropUserDao.view(pageVO));
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public Result addBoard(CropVO memberVO) {

		int row = cropUserDao.insert(memberVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value = "/modify")
	@ResponseBody
	public Result updateBoard(CropVO memberVO) {

		int row = cropUserDao.modify(memberVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public Result deleteBoard(CropVO memberVO) {

		int row = cropUserDao.delete(memberVO);
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
