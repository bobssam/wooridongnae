package com.suba.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.NoteVO;

@Repository
public class NoteDao {

	@Autowired
	private SqlSession sqlSession;


	public List<NoteVO> list( PageVO pageVO	) {
		return sqlSession.selectList( "note.list", pageVO );
	}
	
	public int total( PageVO pageVO	) {
		return sqlSession.selectOne( "note.total", pageVO );
	}

	public int sendNote( NoteVO noteVO ){
		return sqlSession.insert( "note.sendNote", noteVO );
	}
	
	public int readNote( PageVO pageVO ){
		return sqlSession.update( "note.readNote", pageVO );
	}

	public int delete( PageVO pageVO ){
		return sqlSession.delete( "note.delete", pageVO );
	}

	public int receivePoint(NoteVO noteVO) {
		return sqlSession.update( "note.receivePoint", noteVO );
	}

	public int replyNote( PageVO pageVO ){
		return sqlSession.insert( "note.replyNote", pageVO );
	}
	
}
