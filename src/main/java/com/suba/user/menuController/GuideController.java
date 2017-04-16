package com.suba.user.menuController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suba.user.dao.TenderDao;
import com.suba.vo.TenderVO;

@Controller
@RequestMapping(value="/guide")
public class GuideController {

	@Autowired
	private TenderDao tenderDao;

	@RequestMapping(value="/")
	public String main( ) {

		return "guide/main";
	}

	@RequestMapping("/member")
	public String member( TenderVO tenderVO ) {


		return "guide/member";
	}
	
	@RequestMapping("/dealer")
	public String dealer( TenderVO tenderVO ) {

		return "guide/dealer";
	}
}
