package com.suba.user.menuController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suba.common.vo.PageVO;

@Controller
@RequestMapping(value="/crop")
public class CropController extends MenuController{

	@RequestMapping("/")
	public ModelAndView main( PageVO pageVO, ModelAndView model ) {

		model.setViewName("/crop/main");
		return model;
	}

	@RequestMapping("/workpic")
	public ModelAndView workpic( PageVO pageVO, ModelAndView model ) {

		model.setViewName("/crop/workpic");
		return model;
	}

}
