package com.suba.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vertx.java.core.json.JsonObject;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.PaymentDao;
import com.suba.user.menuController.MenuController;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.PaymentVO;

@Controller
@RequestMapping(value="/api/payment")
public class PaymentApiController extends MenuController{

	@Autowired
	private PaymentDao paymentDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<PaymentVO> list( HttpServletRequest request, HttpServletResponse response,  HttpSession session ) {
		
		if( !isLogin( session ) ) {
			sendAccessDenine(response);
			return null;
		}
		
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.putNumber("memberNo", memberVO.getMemberNo());
		PageVO pageVO = new PageVO();
		pageVO.setWhere(jsonObject);
		
		List<PaymentVO> PaymentVOList = paymentDao.list(pageVO);
		int total = paymentDao.total(pageVO);
		ListVo<PaymentVO> list = new ListVo<PaymentVO>( total, PaymentVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( PaymentVO paymentVO ) {

		int row = paymentDao.insert(paymentVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( PaymentVO paymentVO ) {

		int row = paymentDao.modify(paymentVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( PaymentVO paymentVO ) {

		int row = paymentDao.delete(paymentVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
