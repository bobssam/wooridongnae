package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.StaticsVO;

@Repository
public class AdminStaticsDao {

	@Autowired
	private SqlSession sqlSession;


	public List<StaticsVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "statics.list", pageVO );
	}

	public int total( PageVO pageVO ) {
		return sqlSession.selectOne( "statics.total", pageVO );
	}

	public int insert( StaticsVO staticsVO ){
		return sqlSession.insert( "statics.insert", staticsVO );
	}

	public int modify( StaticsVO staticsVO ){
		return sqlSession.update( "statics.modify", staticsVO );
	}

	public int delete( StaticsVO staticsVO ){
		return sqlSession.delete( "statics.delete", staticsVO );
	}



}
