package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.CropVO;
import com.suba.vo.MemberVO;

@Repository
public class AdminCropUserDao {

	@Autowired
	private SqlSession sqlSession;


	public List<CropVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "admin_crop.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "admin_crop.total", pageVO );
	}

	public int insert( MemberVO memberVO ){
		return sqlSession.insert( "admin_crop.insert", memberVO );
	}

	public int modify( MemberVO memberVO ){
		return sqlSession.update( "admin_crop.modify", memberVO );
	}

	public int delete( MemberVO memberVO ){
		return sqlSession.delete( "admin_crop.delete", memberVO );
	}

	public CropVO view(PageVO pageVO) {
		return sqlSession.selectOne( "admin_crop.view", pageVO );
	}
}
