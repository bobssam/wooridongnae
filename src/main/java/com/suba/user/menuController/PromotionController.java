package com.suba.user.menuController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/promotion")
@Controller
public class PromotionController {

	@RequestMapping(value="/")
	public String privacy( ) {

		return "promotion/main";
	}

}
