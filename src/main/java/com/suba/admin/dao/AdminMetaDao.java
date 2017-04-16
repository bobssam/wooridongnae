package com.suba.admin.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.vo.MetaVO;

@Repository
public class AdminMetaDao {

	@Autowired
	private SqlSession sqlSession;

	public List<MetaVO> selectList( String tableName	) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		return sqlSession.selectList( "meta.getMeta", map );
	}

}
