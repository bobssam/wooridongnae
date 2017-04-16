package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.RegLogVO;

@Repository
public class AdminRegLogDao {

	@Autowired
	private SqlSession sqlSession;

	public List<RegLogVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "regLog.list", pageVO );
	}

	public int total( PageVO pageVO ) {
		return sqlSession.selectOne( "regLog.total", pageVO );
	}

	public int insert( RegLogVO regLogVO ){
		return sqlSession.insert( "regLog.insert", regLogVO );
	}

	public int modify( RegLogVO regLogVO ){
		return sqlSession.update( "regLog.modify", regLogVO );
	}

	public int delete( RegLogVO regLogVO ){
		return sqlSession.delete( "regLog.delete", regLogVO );
	}



}
