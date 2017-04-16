package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.BoardVO;
import com.suba.vo.SmallAreaVO;

@Repository
public class AdminSmallAreaDao {

	@Autowired
	private SqlSession sqlSession;


	public List<BoardVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "smallArea.list", pageVO );
	}
	
	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "smallArea.total", pageVO );
	}

	public int insert( SmallAreaVO smallAreaVO ){
		return sqlSession.insert( "smallArea.insert", smallAreaVO );
	}

	public int modify( SmallAreaVO smallAreaVO ){
		return sqlSession.update( "smallArea.modify", smallAreaVO );
	}

	public int delete( SmallAreaVO smallAreaVO ){
		return sqlSession.delete( "smallArea.delete", smallAreaVO );
	}

	
	
}
