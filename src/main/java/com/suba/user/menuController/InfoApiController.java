package com.suba.user.menuController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.FAQDao;
import com.suba.user.dao.NoticeDao;
import com.suba.user.dao.QNADao;
import com.suba.user.service.InfoService;
import com.suba.vo.BoardVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/infos")
public class InfoApiController {
	@Autowired
	private InfoService infoService;
	
	@Autowired
	private FAQDao faqDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private QNADao qnaDao;
	

	@RequestMapping(value="/faq/list")
	@ResponseBody
	public ListVo<BoardVO> faqList( PageVO pageVO ) {

		List<BoardVO> memberVOList = faqDao.list(pageVO);
		int total = faqDao.total(pageVO);
		ListVo<BoardVO> list = new ListVo<BoardVO>( total, memberVOList );
		return list;
	}
	
	@RequestMapping(value="/qna/updateHIt")
	@ResponseBody
	public Result qnaUpdateHit( BoardVO boardVO) {
		return infoService.qnaUpdateHit(boardVO);
	}
}
