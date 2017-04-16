package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.BoardVO;

@Repository
public class NoticeDao {

	@Autowired
	private SqlSession sqlSession;


	public List<BoardVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "notice.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "notice.total", pageVO );
	}

	public int insert( BoardVO boardVO ){
		return sqlSession.insert( "notice.insert", boardVO );
	}

	public int modify( BoardVO boardVO ) {
		return sqlSession.update( "notice.modify", boardVO );
	}

	public int delete( BoardVO boardVO ){
		return sqlSession.delete( "notice.delete", boardVO );
	}

	public int plusHit(BoardVO boardVO) {
		return sqlSession.update( "notice.plusHit", boardVO );
	}

	public List<BoardVO> selectMainNotice() {
		return sqlSession.selectList( "notice.listMain" );
	}
	
	public BoardVO view( BoardVO boardVO ) {
		return sqlSession.selectOne( "notice.view", boardVO );
	}
}
