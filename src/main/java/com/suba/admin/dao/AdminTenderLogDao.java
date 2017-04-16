package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.TenderLogVO;

@Repository
public class AdminTenderLogDao {

	@Autowired
	private SqlSession sqlSession;


	public List<TenderLogVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "tenderLog.list", pageVO );
	}

	public int total( PageVO pageVO ) {
		return sqlSession.selectOne( "tenderLog.total", pageVO );
	}

	public int insert( TenderLogVO tenderLogVO ){
		return sqlSession.insert( "tenderLog.insert", tenderLogVO );
	}

	public int modify( TenderLogVO tenderLogVO ){
		return sqlSession.update( "tenderLog.modify", tenderLogVO );
	}

	public int delete( TenderLogVO tenderLogVO ){
		return sqlSession.delete( "tenderLog.delete", tenderLogVO );
	}



}
