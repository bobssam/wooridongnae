package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.HotdealAskVO;

@Repository
public class AdminHotdealAskDao {

	@Autowired
	private SqlSession sqlSession;

	public List<HotdealAskVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "admin_hotdeal_ask.list", pageVO );
	}

	public HotdealAskVO view( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_hotdeal_ask.view", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_hotdeal_ask.total", pageVO );
	}

	public int insert( HotdealAskVO boardVO ){
		return sqlSession.insert( "admin_hotdeal_ask.add", boardVO );
	}

	public int modify( HotdealAskVO boardVO ){
		return sqlSession.update( "admin_hotdeal_ask.modify", boardVO );
	}

	public int delete( HotdealAskVO boardVO ){
		return sqlSession.delete( "admin_hotdeal_ask.delete", boardVO );
	}

}
