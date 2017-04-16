package com.suba.user.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suba.common.vo.PageVO;
import com.suba.vo.MemberVO;
import com.suba.vo.TenderReplyVO;
import com.suba.vo.TenderVO;

@Repository
public class TenderDao {

	@Autowired
	private SqlSession sqlSession;

	public List<TenderVO> list(PageVO pageVO) {
		return sqlSession.selectList("tender.list", pageVO);
	}

	// public TenderVO selectTender( PageVO pageVO ) {
	// return sqlSession.selectOne( "tender.selectTender" );
	// }

	public List<HashMap<String, Object>> listExcel(PageVO pageVO) {
		return sqlSession.selectList("tender.listExcel", pageVO);
	}

	public int total(PageVO pageVO) {
		return sqlSession.selectOne("tender.total", pageVO);
	}

	public int insert(TenderVO tenderVO) {
		return sqlSession.insert("tender.insert", tenderVO);
	}

	public int plusHit(TenderVO tenderVO) {
		return sqlSession.update("tender.plusHit", tenderVO);
	}

	public int modifyBidMemberNo(TenderVO tenderVO) {
		return sqlSession.update("tender.modifyBidMemberNo", tenderVO);
	}

	public int modifyStatus(TenderVO tenderVO) {
		return sqlSession.update("tender.modifyStatus", tenderVO);
	}

	public int modifyTender(TenderVO tenderVO) {
		return sqlSession.update("tender.modifyTender", tenderVO);
	}

	public int delete(TenderVO tenderVO) {
		return sqlSession.delete("tender.delete", tenderVO);
	}

	public List<TenderVO> selectTendering(MemberVO memberVO) {
		return sqlSession.selectList("tender.selectTendering", memberVO);
	}

	public TenderVO selectTender(PageVO pageVO) {
		return sqlSession.selectOne("tender.selectTender", pageVO);
	}

	public List<TenderReplyVO> selectTenderReplyList(TenderVO tenderVO) {
		return sqlSession.selectList("tender.selectTenderReplyList", tenderVO);
	}


	public HashMap<String, Integer> selectMyTenderStats(MemberVO memberVO) {
		return sqlSession.selectOne("tender.selectMyTenderStats", memberVO);
	}



	public List<HashMap<String, Object>> dealerMyTendingSummary(MemberVO memberVO) {
		return sqlSession.selectList("tender.dealerMyTendingSummary", memberVO);
	}

	public int selectDealerMyTendingTotal(PageVO pageVO) {
		return sqlSession.selectOne("tender.dealerMyTendingTotal", pageVO);
	}

	public List<TenderVO> selectDealerMyTendingList(PageVO pageVO) {
		return sqlSession.selectList("tender.dealerMyTendingList", pageVO);
	}

	/**
	 * 일반 유저의 거래 현황 집계
	 * @param memberVO
	 * @return
	 */
	public List<HashMap<String, Object>> myTendingSummary(MemberVO memberVO) {
		return sqlSession.selectList("tender.myTendingSummary", memberVO);
	}
	public List<TenderVO> selectMyTendingList(PageVO pageVO) {
		return sqlSession.selectList("tender.myTendingList", pageVO);
	}
	public List<TenderReplyVO> selectMyTendingReplyList(PageVO pageVO) {
		return sqlSession.selectList("tender.myTendingReplyList", pageVO);
	}
	public int selectMyTendingTotal(PageVO pageVO) {
		return sqlSession.selectOne("tender.myTendingTotal", pageVO);
	}
	public int selectMyTendingNo(PageVO pageVO) {
		Integer res = sqlSession.selectOne("tender.myTendingNo", pageVO);
		if( res ==  null ) return 0;
		return res;
	}


}
