package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.CategoryVO;
import com.suba.vo.MemberVO;
import com.suba.vo.UserCategoryVO;

@Repository
public class AdminUserDao {

	@Autowired
	private SqlSession sqlSession;


	public List<MemberVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "user.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "user.total", pageVO );
	}

	public int insert( MemberVO memberVO ){
		return sqlSession.insert( "user.insert", memberVO );
	}

	public int modify( MemberVO memberVO ){
		return sqlSession.update( "user.modify", memberVO );
	}

	public int delete( MemberVO memberVO ){
		return sqlSession.delete( "user.delete", memberVO );
	}

	public MemberVO login(MemberVO memberVO) {
		return sqlSession.selectOne( "user.login", memberVO );
	}

	public MemberVO view(PageVO pageVO) {
		return sqlSession.selectOne( "user.view", pageVO );
	}
	
	public List<CategoryVO> listCategory3(PageVO pageVO) {
		return sqlSession.selectList("user.listCategory3", pageVO );
	}

	public int clearCategory3(MemberVO memberVO) {
		return sqlSession.delete("user.clearCategory3", memberVO );
	}
	
	public int insertCategory3(UserCategoryVO userCategoryVO) {
		return sqlSession.delete("user.insertCategory3", userCategoryVO );
	}
	
}
