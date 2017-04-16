package com.suba.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.PartnerAskVO;

@Repository
public class PartnerAskDao {

	@Autowired
	private SqlSession sqlSession;

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "partnerask.total", pageVO );
	}

	public PartnerAskVO view( PartnerAskVO partnerAskVO ) {
		return sqlSession.selectOne( "partnerask.view", partnerAskVO );
	}

	public Integer add( PartnerAskVO partnerAskVO ) {
		return sqlSession.insert( "partnerask.add", partnerAskVO );
	}

}