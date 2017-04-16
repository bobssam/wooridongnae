package com.suba.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.CategoryDao;
import com.suba.vo.CategoryVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/api/category")
public class CategoryApiController {

	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<CategoryVO> list( PageVO pageVO ) {

		List<CategoryVO> CategoryVOList = categoryDao.list(pageVO);
		int total = categoryDao.total(pageVO);
		ListVo<CategoryVO> list = new ListVo<CategoryVO>( total, CategoryVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( CategoryVO categoryVO ) {

		int row = categoryDao.insert(categoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( CategoryVO categoryVO ) {

		int row = categoryDao.modify(categoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( CategoryVO categoryVO ) {

		int row = categoryDao.delete(categoryVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
