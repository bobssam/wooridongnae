package com.suba.user.menuController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.CategoryDao;
import com.suba.user.dao.TenderDao;
import com.suba.user.service.MemberService;
import com.suba.user.service.TenderService;
import com.suba.vo.CategoryVO;
import com.suba.vo.MemberVO;
import com.suba.vo.SmallAreaVO;
import com.suba.vo.TenderVO;

@Controller
@RequestMapping(value="/tender")
public class TenderController extends MenuController {

	@Autowired
	private TenderDao tenderDao;
	@Autowired
	private TenderService tenderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping(value="/")
	public String main( ) {

		return "tender/main";
	}

	@RequestMapping(value="/detail")
	public String detail( ) {

		return "tender/detail";
	}

	@RequestMapping(value="/make")
	public ModelAndView make( ModelAndView model, int categoryNo2,  HttpServletRequest request, HttpSession session ) {

		if( !isLogin( session ) ) {
			return new ModelAndView(sendRedirect( request ));
		}

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.getWhere().putNumber("categoryNo", categoryNo2);

		CategoryVO categoryVO2 = categoryDao.view(categoryVO);
		model.addObject("categoryName", categoryVO2.getCategoryName());

		model.setViewName("tender/tender");

		return model;
	}

	@RequestMapping(value="/doMake")
	@ResponseBody
	public Result doMake(TenderVO tenderVO, HttpServletRequest request, HttpServletResponse response, HttpSession session ) {

		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		tenderVO.setMemberNo(memberVO.getMemberNo());

		return tenderService.make(tenderVO);

	}

	@RequestMapping(value="/lists")
	public ModelAndView lists( int categoryNo2, ModelAndView model, HttpSession session ) {

		// 무조건 카테고리는 자동차
		int categoryNo = 1;

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( memberVO != null ) {

			model.addObject("memberType" , memberVO.getType());

			if( memberVO.getType() == MemberVO.TYPE_DEALER || memberVO.getType() == MemberVO.TYPE_ADMIN ) {
				List<SmallAreaVO> wantAreaList = memberService.getWantArea(memberVO.getWantArea());
				model.addObject("wantArea", wantAreaList );
			}
			if( memberVO.getType() == MemberVO.TYPE_USER ) {

				PageVO pageVO = new PageVO();
				pageVO.getWhere().putNumber("categoryNo", categoryNo);
				pageVO.getWhere().putNumber("categoryNo2", categoryNo2);
				pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
				pageVO.getWhere().putString("status", "T");
				int tenderNo = tenderDao.selectMyTendingNo(pageVO);

				model.addObject("tenderNo", tenderNo );
			}

		}

		model.setViewName("tender/list");

		return model;
	}

	@RequestMapping(value="/view")
	public String view( TenderVO tenderVO, Model model ) {

		model.addAttribute("tender", tenderService.getView(tenderVO));
		model.addAttribute("reply", tenderService.getViewReply(tenderVO));
		return "tender/view";
	}

	@RequestMapping(value="/shop")
	public String shop() {

		return "tender/shop";
	}

}
