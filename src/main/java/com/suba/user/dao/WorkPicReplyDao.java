package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.WorkPicReplyVO;

@Repository
public class WorkPicReplyDao {

	@Autowired
	private SqlSession sqlSession;

	public List<WorkPicReplyVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "workPicReply.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "workPicReply.total", pageVO );
	}

	public int insert( WorkPicReplyVO boardVO ){
		return sqlSession.insert( "workPicReply.add", boardVO );
	}

	public int modify( WorkPicReplyVO boardVO ){
		return sqlSession.update( "workPicReply.modify", boardVO );
	}

	public int delete( WorkPicReplyVO boardVO ){
		return sqlSession.delete( "workPicReply.delete", boardVO );
	}

	public WorkPicReplyVO view( WorkPicReplyVO boardVO ){
		return sqlSession.selectOne( "workPicReply.view", boardVO );
	}


}
