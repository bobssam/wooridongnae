package com.suba.user.menuController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.user.dao.PaymentDao;
import com.suba.vo.PaymentVO;

@Controller
@RequestMapping(value="/payment")
public class PaymentController {


	@Autowired
	private PaymentDao paymentDao;

	@RequestMapping(value="/")
	public String main( ) {

		return "payment/main";
	}

	@RequestMapping(value="/doPayment")
	@ResponseBody
	public String doPayment( PaymentVO paymentVO) {

		paymentDao.insert(paymentVO);

		return "payment/doPayment";
	}
}
