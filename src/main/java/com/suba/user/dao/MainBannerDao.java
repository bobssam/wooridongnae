package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.vo.MainBannerVO;

@Repository
public class MainBannerDao {

	@Autowired
	private SqlSession sqlSession;

	public List<MainBannerVO> mainList() {
		return sqlSession.selectList( "main_banner.mainList" );
	}

}
