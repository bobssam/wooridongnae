package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.CategoryVO;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;


	public List<CategoryVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "category.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "category.total", pageVO );
	}

	public int insert( CategoryVO categoryVO ){
		return sqlSession.insert( "category.insert", categoryVO );
	}

	public int modify( CategoryVO categoryVO ){
		return sqlSession.update( "category.modify", categoryVO );
	}

	public int delete( CategoryVO categoryVO ){
		return sqlSession.delete( "category.delete", categoryVO );
	}

	public CategoryVO view( CategoryVO categoryVO ){
		return sqlSession.selectOne( "category.view", categoryVO );
	}



}
