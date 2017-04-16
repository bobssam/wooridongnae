package com.suba.user.menuController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suba.common.vo.PageVO;
import com.suba.user.dao.BigAreaDao;
import com.suba.user.dao.SmallAreaDao;
import com.suba.vo.BoardVO;

@Controller
@RequestMapping(value="/area")
public class AreaController {

	@Autowired
	private BigAreaDao bigAreaDao;
	@Autowired
	private SmallAreaDao smallAreaDao;

	@RequestMapping(value="/big/list")
	@ResponseBody
	public Model bigList( PageVO pageVO, Model model) {
		model.addAttribute("bigArea", bigAreaDao.list(pageVO));
		return model;
	}

	@RequestMapping("/small/list")
	@ResponseBody
	public List<BoardVO> smallList( PageVO pageVO ) {
		return smallAreaDao.list(pageVO);
	}
	
	@RequestMapping("/select")
	public ModelAndView select( PageVO pageVO, ModelAndView model ) {
		model.addObject("bigArea", bigAreaDao.list(pageVO));
		model.setViewName("global/arealist");

		return model;
	}	
}
