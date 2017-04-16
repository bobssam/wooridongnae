package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.BoardCategoryVO;

@Repository
public class BoardCategoryDao {

	@Autowired
	private SqlSession sqlSession;


	public List<BoardCategoryVO> selectBoardCategoryList( PageVO pageVO	) {
		return sqlSession.selectList( "boardCategory.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "boardCategory.total", pageVO );
	}

	public int insert( BoardCategoryVO boardCategoryVO ){
		return sqlSession.insert( "boardCategory.insert", boardCategoryVO );
	}

	public int modify( BoardCategoryVO boardCategoryVO ){
		return sqlSession.update( "boardCategory.modify", boardCategoryVO );
	}

	public int delete( BoardCategoryVO boardCategoryVO ){
		return sqlSession.delete( "boardCategory.delete", boardCategoryVO );
	}



}
