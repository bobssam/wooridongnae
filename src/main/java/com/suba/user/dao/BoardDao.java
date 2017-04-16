package com.suba.user.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.BoardVO;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public List<BoardVO> list( PageVO pageVO ) {
		return sqlSession.selectList( "board.list", pageVO );
	}
	
	public BoardVO view( int boardNo ) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("boardNo", boardNo);
		return sqlSession.selectOne( "board.view", boardNo );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "board.total", pageVO );
	}

	public int insert( BoardVO boardVO ){
		return sqlSession.insert( "board.insert", boardVO );
	}

	public int modify( BoardVO boardVO ){
		return sqlSession.update( "board.modify", boardVO );
	}

	public int delete( BoardVO boardVO ){
		return sqlSession.delete( "board.delete", boardVO );
	}
	
	public int plusHit( BoardVO boardVO ){
		return sqlSession.delete( "board.plusHit", boardVO );
	}

	public int plusReplyCount(BoardVO boardVO) {
		return sqlSession.update( "board.plusReplyCount", boardVO );
	}

	public int minersReplyCount(BoardVO boardVO) {
		return sqlSession.update( "board.minersReplyCount", boardVO );
	}



}
