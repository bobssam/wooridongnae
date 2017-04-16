package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.HotdealVO;

@Repository
public class AdminHotdealDao {

	@Autowired
	private SqlSession sqlSession;

	public List<HotdealVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "admin_hotdeal.list", pageVO );
	}

	public HotdealVO view( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_hotdeal.view", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_hotdeal.total", pageVO );
	}

	public int insert( HotdealVO hotdealVO ){
		return sqlSession.insert( "admin_hotdeal.add", hotdealVO );
	}

	public int modify( HotdealVO hotdealVO ){
		return sqlSession.update( "admin_hotdeal.modify", hotdealVO );
	}

	public int delete( HotdealVO hotdealVO ){
		return sqlSession.delete( "admin_hotdeal.delete", hotdealVO );
	}

}
