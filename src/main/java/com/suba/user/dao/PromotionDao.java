package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.HotdealVO;
import com.suba.vo.PromotionVO;

@Repository
public class PromotionDao {

	@Autowired
	private SqlSession sqlSession;

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "promotion.total", pageVO );
	}

	public PromotionVO view( PromotionVO hotdealAsk ) {
		return sqlSession.selectOne( "promotion.view", hotdealAsk );
	}

	public List<PromotionVO> list( PageVO pageVO ) {
		return sqlSession.selectList( "promotion.list", pageVO );
	}


}
