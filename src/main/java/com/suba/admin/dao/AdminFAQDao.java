package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.BoardVO;

@Repository
public class AdminFAQDao {

	@Autowired
	private SqlSession sqlSession;


	public List<BoardVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "faq.list", pageVO );
	}
	
	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "faq.total", pageVO );
	}

	public int insert( BoardVO boardVO ){
		return sqlSession.insert( "faq.insert", boardVO );
	}

	public int modify( BoardVO boardVO ){
		return sqlSession.update( "faq.modify", boardVO );
	}

	public int delete( BoardVO boardVO ){
		return sqlSession.delete( "faq.delete", boardVO );
	}
	
	public int plusHit( BoardVO boardVO ){
		return sqlSession.update( "faq.plusHit", boardVO );
	}

	

	
	
}
