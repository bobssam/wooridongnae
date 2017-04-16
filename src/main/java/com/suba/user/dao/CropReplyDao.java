package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.BoardReplyVO;
import com.suba.vo.CropReplyVO;

@Repository
public class CropReplyDao {

	@Autowired
	private SqlSession sqlSession;

	public List<CropReplyVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "cropReply.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "cropReply.total", pageVO );
	}

	public int insert( CropReplyVO boardVO ){
		return sqlSession.insert( "cropReply.add", boardVO );
	}

	public int modify( CropReplyVO boardVO ){
		return sqlSession.update( "cropReply.modify", boardVO );
	}

	public int delete( CropReplyVO boardVO ){
		return sqlSession.delete( "cropReply.delete", boardVO );
	}

	public CropReplyVO view( CropReplyVO boardVO ){
		return sqlSession.selectOne( "cropReply.view", boardVO );
	}


}
