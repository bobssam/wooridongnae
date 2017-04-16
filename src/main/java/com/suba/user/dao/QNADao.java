package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.BoardVO;

@Repository
public class QNADao {

	@Autowired
	private SqlSession sqlSession;


	public List<BoardVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "qna.list", pageVO );
	}
	
	public BoardVO view( BoardVO boardVO	) {
		return sqlSession.selectOne( "qna.view", boardVO );
	}

	public int selectTotal( PageVO pageVO	) {
		return sqlSession.selectOne( "qna.total", pageVO );
	}

	public int insert( BoardVO boardVO ){
		return sqlSession.insert( "qna.insert", boardVO );
	}

	public int modify( BoardVO boardVO ){
		return sqlSession.update( "qna.modify", boardVO );
	}

	public int delete( BoardVO boardVO ){
		return sqlSession.delete( "qna.delete", boardVO );
	}

	public int plusHit(BoardVO boardVO) {
		return sqlSession.update( "qna.plusHit", boardVO );
	}

}
