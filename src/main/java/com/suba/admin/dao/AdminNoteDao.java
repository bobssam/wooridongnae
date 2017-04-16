package com.suba.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.NoteVO;

@Repository
public class AdminNoteDao {

	@Autowired
	private SqlSession sqlSession;


	public List<NoteVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "note.list", pageVO );
	}
	
	public int selectTotal( PageVO pageVO	) {
		return sqlSession.selectOne( "note.total", pageVO );
	}

	public int sendNote( NoteVO noteVO ){
		return sqlSession.insert( "note.sendNote", noteVO );
	}

	public int readNote( NoteVO noteVO ){
		return sqlSession.update( "note.readNote", noteVO );
	}

	public int delete( NoteVO noteVO ){
		return sqlSession.delete( "note.delete", noteVO );
	}

	public int receivePoint(NoteVO noteVO) {
		return sqlSession.update( "note.receivePoint", noteVO );
	}

	
	
}
