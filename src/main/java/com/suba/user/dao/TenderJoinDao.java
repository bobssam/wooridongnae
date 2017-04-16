package com.suba.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.vo.TenderJoinVO;

@Repository
public class TenderJoinDao {

	@Autowired
	private SqlSession sqlSession;

	public int insert(TenderJoinVO tenderJoinVO) {
		return sqlSession.insert("tenderJoin.insert", tenderJoinVO);
	}
	public int defer(TenderJoinVO tenderJoinVO) {
		return sqlSession.update("tenderJoin.defer", tenderJoinVO);
	}
	public int success(TenderJoinVO tenderJoinVO) {
		return sqlSession.update("tenderJoin.success", tenderJoinVO);
	}

}
