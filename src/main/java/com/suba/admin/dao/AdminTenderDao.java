package com.suba.admin.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.TenderVO;

@Repository
public class AdminTenderDao {

	@Autowired
	private SqlSession sqlSession;


	public List<TenderVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "tender.list", pageVO );
	}

	public List<HashMap<String, Object>> selectTenderExcel( PageVO pageVO	) {
		return sqlSession.selectList( "tender.listExcel", pageVO );
	}

	public int total( PageVO pageVO ) {
		return sqlSession.selectOne( "tender.total", pageVO );
	}

	public int insert( TenderVO tenderVO ){
		return sqlSession.insert( "tender.insert", tenderVO );
	}

	public int plusHit( TenderVO tenderVO ){
		return sqlSession.update( "tender.plusHit", tenderVO );
	}

	public int modifyBidMemberNo( TenderVO tenderVO ){
		return sqlSession.update( "tender.modifyBidMemberNo", tenderVO );
	}

	public int modifyStatus( TenderVO tenderVO ){
		return sqlSession.update( "tender.modifyStatus", tenderVO );
	}

	public int modifyTender( TenderVO tenderVO ){
		return sqlSession.update( "tender.modifyTender", tenderVO );
	}

	public int delete( TenderVO tenderVO ){
		return sqlSession.delete( "tender.delete", tenderVO );
	}



}
