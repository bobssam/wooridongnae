package com.suba.user.menuController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.suba.common.vo.Result;
import com.suba.user.service.MemberService;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value="/login")
	public String login( ) {

		return "member/login";
	}

	@RequestMapping(value="/sendSms")
	public String sendSms(  ) {
		//sms 전송

		return "member/sendSms";
	}


	@RequestMapping(value="/reg")
	public String reg( ) {

		return "member/reg";
	}


	@RequestMapping(value="/reg_member")
	public String regMember( ) {

		return "member/reg_member";
	}

	@RequestMapping(value="/reg_success")
	public String regSuccess( ) {

		return "member/reg_success";
	}

	@RequestMapping(value="/reg_fail")
	public String regFail( ) {

		return "member/reg_fail";
	}

	@RequestMapping(value="/reg_dealer")
	public String regDealer( ) {

		return "member/reg_dealer";
	}

	@RequestMapping(value="/lost_password")
	public String lostPassword( ) {

		return "member/lost_password";
	}
	@RequestMapping(value="/lost_id")
	public String lostId( ) {

		return "member/lost_id";
	}
	@RequestMapping(value="/find_id")
	public ModelAndView find_id( ModelAndView model, MemberVO memberVO, String phoneNumber1, String phoneNumber2, String phoneNumber3 ) {

		memberVO.setPhoneNo(phoneNumber1+phoneNumber2+phoneNumber3);
		Result result = memberService.findId(memberVO);

		// 패스워드 마스킹
		if( "ok".equals(result.getStatus()) ){
			String id = (String) result.getData();
			id = "***"+id.substring(3);
			result.setData(id);
		}

		model.setViewName("member/find_id");
		model.addObject("result", result);

		return model;
	}
	@RequestMapping(value="/find_password")
	public String findPassword( ) {

		return "member/find_password";
	}
	
	@RequestMapping(value="/modifyed_info")
	public String modifyInfo( ) {

		return "member/modifyed_info";
	}

	@RequestMapping(value="/insertRegId")
	public String insertRegId(@RequestParam(defaultValue="") String regId, @RequestParam(defaultValue="") String memberNo) {
		memberService.updateRegId(regId, memberNo);
		return "";
	}
	
	@RequestMapping(value="/want_area")
	public String wantArea() {
		return "member/want_area";
	}	
	
	
}
