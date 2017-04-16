package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.PaymentVO;

@Repository
public class PaymentDao {

	@Autowired
	private SqlSession sqlSession;


	public List<PaymentVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "payment.list", pageVO );
	}

	public int total( PageVO pageVO ) {
		return sqlSession.selectOne( "payment.total", pageVO );
	}

	public int insert( PaymentVO paymentVO ){
		return sqlSession.insert( "payment.insert", paymentVO );
	}

	public int modify( PaymentVO paymentVO ){
		return sqlSession.update( "payment.modify", paymentVO );
	}

	public int delete( PaymentVO paymentVO ){
		return sqlSession.delete( "payment.delete", paymentVO );
	}



}
