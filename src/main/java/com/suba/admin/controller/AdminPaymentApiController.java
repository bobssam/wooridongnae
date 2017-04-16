package com.suba.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminPaymentDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.vo.PaymentVO;
import com.suba.vo.ListVo;

@Controller
@RequestMapping(value="/admin/api/payment")
public class AdminPaymentApiController {

	@Autowired
	private AdminPaymentDao paymentDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<PaymentVO> selectPaymentList( PageVO pageVO ) {

		List<PaymentVO> PaymentVOList = paymentDao.list(pageVO);
		int total = paymentDao.total(pageVO);
		ListVo<PaymentVO> list = new ListVo<PaymentVO>( total, PaymentVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addPayment( PaymentVO paymentVO ) {

		int row = paymentDao.insert(paymentVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updatePayment( PaymentVO paymentVO ) {

		int row = paymentDao.modify(paymentVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deletePayment( PaymentVO paymentVO ) {

		int row = paymentDao.delete(paymentVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
