package com.suba.user.menuController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/hotdeal")
@Controller
public class HotDealController {
	
	@RequestMapping(value="/")
	public String privacy( ) {

		return "hotdeal/main";
	}

}
