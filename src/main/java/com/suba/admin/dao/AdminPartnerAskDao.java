package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.PartnerAskVO;

@Repository
public class AdminPartnerAskDao {

	@Autowired
	private SqlSession sqlSession;

	public List<PartnerAskVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "admin_partner_ask.list", pageVO );
	}

	public PartnerAskVO view( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_partner_ask.view", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_partner_ask.total", pageVO );
	}

	public int modify( PartnerAskVO boardVO ){
		return sqlSession.update( "admin_partner_ask.modify", boardVO );
	}

	public int delete( PartnerAskVO boardVO ){
		return sqlSession.delete( "admin_partner_ask.delete", boardVO );
	}

}
