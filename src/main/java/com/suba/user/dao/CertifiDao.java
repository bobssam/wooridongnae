package com.suba.user.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CertifiDao {

	@Autowired
	private SqlSession sqlSession;

	public int insertCertificationKey(HashMap<Object, Object> param) {
		return sqlSession.insert("certification.insertCertificationKey", param);
	}

	public String getTempKey(String phone) {
		return sqlSession.selectOne("certification.getTempKey", phone);
	}

	public int deleteTempKey(String phone) {
		return sqlSession.delete("certification.deleteTempKey", phone);
	}
}
