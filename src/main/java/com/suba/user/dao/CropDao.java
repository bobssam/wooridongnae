package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.CropVO;

@Repository
public class CropDao {

	@Autowired
	private SqlSession sqlSession;


	public List<CropVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "crop.list", pageVO );
	}

	public CropVO view( PageVO pageVO	) {
		return sqlSession.selectOne( "crop.view", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "crop.total", pageVO );
	}

	public int insert( CropVO cropVO ){
		return sqlSession.insert( "crop.insert", cropVO );
	}

	public int modify( CropVO cropVO ){
		return sqlSession.update( "crop.modify", cropVO );
	}

	public int delete( CropVO cropVO ){
		return sqlSession.delete( "crop.delete", cropVO );
	}

	public int plusHit(CropVO cropVO) {
		return sqlSession.update( "crop.plusHit", cropVO );
	}

	public List<CropVO> mainList() {
		return sqlSession.selectList( "crop.mainList" );
	}

}
