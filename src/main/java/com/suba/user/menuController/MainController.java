package com.suba.user.menuController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vertx.java.core.json.JsonObject;

import com.suba.common.vo.PageVO;
import com.suba.user.dao.CropDao;
import com.suba.user.dao.HotdealDao;
import com.suba.user.dao.MainBannerDao;
import com.suba.user.dao.NoticeDao;
import com.suba.user.dao.TenderDao;
import com.suba.user.service.TenderService;
import com.suba.vo.MemberVO;
import com.suba.vo.TenderVO;

@Controller
@RequestMapping(value="/")
public class MainController {

	@Autowired
	private TenderService tenderService;
	@Autowired
	private HotdealDao hotdealDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private CropDao cropDao;
	@Autowired
	private MainBannerDao mainBannerDao;

	@RequestMapping(value="/")
	public String main( Model model, HttpSession session ) {

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

		model.addAttribute("hotDeal", hotdealDao.mainList());
		model.addAttribute("notice", noticeDao.selectMainNotice());
		model.addAttribute("crops", cropDao.mainList());
		model.addAttribute("banners", mainBannerDao.mainList());



		PageVO pageVO = new PageVO();
		JsonObject where = new JsonObject();
		where.putString("status", TenderVO.TYPE_STATUS_SUCCESS);
		pageVO.setWhere(where);
		pageVO.setLen(10);
		model.addAttribute("finishDeal", tenderService.getList(pageVO));
		return "main";
	}


}
