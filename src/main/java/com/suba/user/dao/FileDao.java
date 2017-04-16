package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.FileVO;

@Repository
public class FileDao {

	@Autowired
	private SqlSession sqlSession;


	public List<FileVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "file.list", pageVO );
	}

	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "file.total", pageVO );
	}

	public FileVO viewFile( FileVO fileVO	) {
		return sqlSession.selectOne( "file.view", fileVO );
	}

	public int insert( FileVO fileVO ){
		return sqlSession.insert( "file.insert", fileVO );
	}

	public int modify( FileVO fileVO ){
		return sqlSession.update( "file.modify", fileVO );
	}

	public int delete( FileVO fileVO ){
		return sqlSession.delete( "file.delete", fileVO );
	}
	public int modifyTender( FileVO fileVO ){
		return sqlSession.delete( "file.modifyTender", fileVO );
	}
	public int modifyTenderReply( FileVO fileVO ){
		return sqlSession.delete( "file.modifyTenderReply", fileVO );
	}
	public int modifyNotice( FileVO fileVO ){
		return sqlSession.delete( "file.modifyNotice", fileVO );
	}
	public int modifyFaq( FileVO fileVO ){
		return sqlSession.delete( "file.modifyFaq", fileVO );
	}
	public int modifyQna( FileVO fileVO ){
		return sqlSession.delete( "file.modifyQna", fileVO );
	}
	public int modifyDealer( FileVO fileVO ){
		return sqlSession.delete( "file.modifyDealer", fileVO );
	}
}
