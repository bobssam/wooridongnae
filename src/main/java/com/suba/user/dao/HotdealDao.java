package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.HotdealVO;

@Repository
public class HotdealDao {

	@Autowired
	private SqlSession sqlSession;

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "hotdeal.total", pageVO );
	}

	public HotdealVO view( HotdealVO hotdealAsk ) {
		return sqlSession.selectOne( "hotdeal.view", hotdealAsk );
	}

	public List<HotdealVO> list( PageVO pageVO ) {
		return sqlSession.selectList( "hotdeal.list", pageVO );
	}

	public Integer plusAsk( HotdealVO hotdealAsk ) {
		return sqlSession.update( "hotdeal.plusAsk", hotdealAsk );
	}

	public Object mainList() {
		return sqlSession.selectList( "hotdeal.mainList" );
	}

}
