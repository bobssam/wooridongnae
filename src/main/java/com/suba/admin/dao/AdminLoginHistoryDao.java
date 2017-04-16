package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.LoginHistoryVO;

@Repository
public class AdminLoginHistoryDao {

	@Autowired
	private SqlSession sqlSession;


	public List<LoginHistoryVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "loginHistory.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "loginHistory.total", pageVO );
	}

	public int insert( LoginHistoryVO loginHistoryVO ){
		return sqlSession.insert( "loginHistory.insert", loginHistoryVO );
	}

	public int modify( LoginHistoryVO loginHistoryVO ){
		return sqlSession.update( "loginHistory.modify", loginHistoryVO );
	}

	public int delete( LoginHistoryVO loginHistoryVO ){
		return sqlSession.delete( "loginHistory.delete", loginHistoryVO );
	}



}
