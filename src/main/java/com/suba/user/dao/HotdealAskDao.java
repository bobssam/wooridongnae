package com.suba.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.HotdealAskVO;

@Repository
public class HotdealAskDao {

	@Autowired
	private SqlSession sqlSession;

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "hotdealask.total", pageVO );
	}

	public HotdealAskVO view( HotdealAskVO hotdealAsk ) {
		return sqlSession.selectOne( "hotdealask.view", hotdealAsk );
	}

	public Integer add( HotdealAskVO hotdealAsk ) {
		return sqlSession.insert( "hotdealask.add", hotdealAsk );
	}

	public int delete( PageVO pageVO ){
		return sqlSession.delete( "hotdealask.delete", pageVO );
	}

}
