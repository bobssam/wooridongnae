package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminCategoryDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.CategoryVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/category")
public class AdminCategoryApiController {

	@Autowired
	private AdminCategoryDao categoryDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<CategoryVO> selectCategoryList( PageVO pageVO ) {

		List<CategoryVO> CategoryVOList = categoryDao.list(pageVO);
		int total = categoryDao.total(pageVO);
		ListVo<CategoryVO> list = new ListVo<CategoryVO>( total, CategoryVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addCategory( CategoryVO categoryVO ) {

		int row = categoryDao.insert(categoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateCategory( CategoryVO categoryVO ) {

		int row = categoryDao.modify(categoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteCategory( CategoryVO categoryVO ) {

		int row = categoryDao.delete(categoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
