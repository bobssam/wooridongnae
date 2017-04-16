package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.CropWorkPicVO;

@Repository
public class CropWorkPicDao {

	@Autowired
	private SqlSession sqlSession;

	public List<CropWorkPicVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "cropWorkPic.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "cropWorkPic.total", pageVO );
	}

	public int insert( CropWorkPicVO boardVO ){
		return sqlSession.insert( "cropWorkPic.add", boardVO );
	}

	public int modify( CropWorkPicVO boardVO ){
		return sqlSession.update( "cropWorkPic.modify", boardVO );
	}

	public int delete( CropWorkPicVO boardVO ){
		return sqlSession.delete( "cropWorkPic.delete", boardVO );
	}

	public CropWorkPicVO view( CropWorkPicVO boardVO ){
		return sqlSession.selectOne( "cropWorkPic.view", boardVO );
	}


}
