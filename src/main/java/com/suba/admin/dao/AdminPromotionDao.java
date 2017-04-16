package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.PromotionVO;

@Repository
public class AdminPromotionDao {

	@Autowired
	private SqlSession sqlSession;

	public List<PromotionVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "admin_promotion.list", pageVO );
	}

	public PromotionVO view( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_promotion.view", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_promotion.total", pageVO );
	}

	public int insert( PromotionVO hotdealVO ){
		return sqlSession.insert( "admin_promotion.add", hotdealVO );
	}

	public int modify( PromotionVO hotdealVO ){
		return sqlSession.update( "admin_promotion.modify", hotdealVO );
	}

	public int delete( PromotionVO hotdealVO ){
		return sqlSession.delete( "admin_promotion.delete", hotdealVO );
	}

}
