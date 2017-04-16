package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.BoardVO;

@Repository
public class AdminNoticeDao {

	@Autowired
	private SqlSession sqlSession;


	public List<BoardVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "admin_notice.list", pageVO );
	}
	
	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_notice.total", pageVO );
	}

	public int insert( BoardVO boardVO ){
		return sqlSession.insert( "admin_notice.add", boardVO );
	}

	public int modify( BoardVO boardVO ){
		return sqlSession.update( "admin_notice.modify", boardVO );
	}

	public int delete( BoardVO boardVO ){
		return sqlSession.delete( "admin_notice.delete", boardVO );
	}

	
	
}
