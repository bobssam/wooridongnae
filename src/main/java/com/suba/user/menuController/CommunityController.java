package com.suba.user.menuController;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suba.common.vo.PageVO;

@Controller
@RequestMapping(value="/community")
public class CommunityController {

	// TODO DB 연동이 필요함
	HashMap<String, String> category = new HashMap<String, String>();
	
	@PostConstruct
	public void init(){
		
		category.put("1", "유머 게시판");
		category.put("2", "신차소식");
		category.put("3", "차량 구입 노하우");
		category.put("4", "출고 체크리스트");
		category.put("5", "자동차 정비상식");
		category.put("6", "차량시승기");
		
	}
	
	@RequestMapping("/")
	public ModelAndView select( PageVO pageVO, ModelAndView model ) {
		
		model.setViewName("/community/main");
		return model;
	}
	
	@RequestMapping("/{sub}")
	public ModelAndView cate( PageVO pageVO, ModelAndView model, @PathVariable(value="sub") String sub ) {
		
		
		model.setViewName("/community/sub");
		model.addObject("pageName", category.get(sub));
		model.addObject("pageId", sub);
		return model;
	}
	
	@RequestMapping("/category")
	@ResponseBody
	public HashMap<String, String>  category(  ) {
		
		return category;
	}
	
}
