package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.MainBannerVO;

@Repository
public class AdminMainBannerDao {

	@Autowired
	private SqlSession sqlSession;

	public List<MainBannerVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "admin_main_banner.list", pageVO );
	}

	public MainBannerVO view( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_main_banner.view", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_main_banner.total", pageVO );
	}

	public int insert( MainBannerVO boardVO ){
		return sqlSession.insert( "admin_main_banner.add", boardVO );
	}

	public int modify( MainBannerVO boardVO ){
		return sqlSession.update( "admin_main_banner.modify", boardVO );
	}

	public int delete( MainBannerVO boardVO ){
		return sqlSession.delete( "admin_main_banner.delete", boardVO );
	}

}
