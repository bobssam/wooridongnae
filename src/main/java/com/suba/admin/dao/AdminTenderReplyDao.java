package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.TenderReplyVO;

@Repository
public class AdminTenderReplyDao {

	@Autowired
	private SqlSession sqlSession;


	public List<TenderReplyVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "tenderReply.list", pageVO );
	}

	public int total( PageVO pageVO ) {
		return sqlSession.selectOne( "tenderReply.total", pageVO );
	}

	public int insert( TenderReplyVO tenderReplyVO ){
		return sqlSession.insert( "tenderReply.insert", tenderReplyVO );
	}

	public int modify( TenderReplyVO tenderReplyVO ){
		return sqlSession.update( "tenderReply.modify", tenderReplyVO );
	}

	public int delete( TenderReplyVO tenderReplyVO ){
		return sqlSession.delete( "tenderReply.delete", tenderReplyVO );
	}



}
