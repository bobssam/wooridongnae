package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.FileVO;

@Repository
public class AdminFileDao {

	@Autowired
	private SqlSession sqlSession;


	public List<FileVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "file.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "file.total", pageVO );
	}

	public int insert( FileVO fileVO ){
		return sqlSession.insert( "file.insert", fileVO );
	}

	public int modify( FileVO fileVO ){
		return sqlSession.update( "file.modify", fileVO );
	}

	public int delete( FileVO fileVO ){
		return sqlSession.delete( "file.delete", fileVO );
	}



}
