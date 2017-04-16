package com.suba.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	@RequestMapping(value="/test")
	public ModelAndView test( ModelAndView view ) {
		
		
		view.setViewName("test");
		
		return view;
		
	}

}
