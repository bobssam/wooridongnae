package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.BoardReplyVO;

@Repository
public class BoardReplyDao {

	@Autowired
	private SqlSession sqlSession;

	public List<BoardReplyVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "boardReply.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "boardReply.total", pageVO );
	}

	public int insert( BoardReplyVO boardVO ){
		return sqlSession.insert( "boardReply.add", boardVO );
	}

	public int modify( BoardReplyVO boardVO ){
		return sqlSession.update( "boardReply.modify", boardVO );
	}

	public int delete( BoardReplyVO boardVO ){
		return sqlSession.delete( "boardReply.delete", boardVO );
	}

	public BoardReplyVO view( BoardReplyVO boardVO ){
		return sqlSession.selectOne( "boardReply.view", boardVO );
	}


}
