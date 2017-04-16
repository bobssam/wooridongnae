package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.BigAreaVO;

@Repository
public class BigAreaDao {

	@Autowired
	private SqlSession sqlSession;


	public List<BigAreaVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "bigArea.list", pageVO );
	}

	public BigAreaVO view( PageVO pageVO	) {
		return sqlSession.selectOne( "bigArea.view", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "bigArea.total", pageVO );
	}

	public int insert( BigAreaVO bigAreaVO ){
		return sqlSession.insert( "bigArea.insert", bigAreaVO );
	}

	public int modify( BigAreaVO bigAreaVO ){
		return sqlSession.update( "bigArea.modify", bigAreaVO );
	}

	public int delete( BigAreaVO bigAreaVO ){
		return sqlSession.delete( "bigArea.delete", bigAreaVO );
	}
}
