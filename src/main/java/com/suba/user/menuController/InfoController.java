package com.suba.user.menuController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suba.user.dao.NoticeDao;
import com.suba.user.service.InfoService;

@Controller
@RequestMapping(value="/infos")
public class InfoController {

	@Autowired
	private InfoService infoService;
	@Autowired
	private NoticeDao noticeDao;

	@RequestMapping(value="/privacy")
	public String privacy( ) {

		return "infos/privacy";
	}

	@RequestMapping(value="/terms")
	public String terms( ) {

		return "infos/terms";
	}


	@RequestMapping(value="/faq")
	public String faq( ) {

		return "infos/faq";
	}


	@RequestMapping(value="/qna")
	public String qna( ) {

		return "infos/qna";
	}

	@RequestMapping(value="/notice")
	public String notice( ) {

		return "infos/notice";
	}

	@RequestMapping(value="/appdown")
	public String appdown(  ) {

		return "infos/appdown";
	}
	
	@RequestMapping(value="/company")
	public String company() {

		return "infos/company";
	}
	
	@RequestMapping(value="/partner")
	public String partner() {
		return "infos/partner";
	}		
}
