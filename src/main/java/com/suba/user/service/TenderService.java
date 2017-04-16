package com.suba.user.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vertx.java.core.json.JsonObject;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.BigAreaDao;
import com.suba.user.dao.CategoryDao;
import com.suba.user.dao.SmallAreaDao;
import com.suba.user.dao.TenderDao;
import com.suba.user.dao.TenderJoinDao;
import com.suba.util.Define;
import com.suba.vo.BigAreaVO;
import com.suba.vo.CategoryVO;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.SmallAreaVO;
import com.suba.vo.TenderReplyVO;
import com.suba.vo.TenderVO;

@Service
public class TenderService {

	@Autowired
	private TenderDao tenderDao;
	@Autowired
	private TenderJoinDao tenderJoinDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private BigAreaDao bigAreaDao;
	@Autowired
	private SmallAreaDao smallAreaDao;

	public ListVo<TenderVO> getTenderList(PageVO pageVO, MemberVO memberVO) {

		// TODO 이거 왜넣었는지 찾아야함
		if( memberVO != null )
			pageVO.getWhere().putNumber("sessionMemberNo", memberVO.getMemberNo());

		List<TenderVO> tenderVOList = tenderDao.list(pageVO);

		CategoryVO c = new CategoryVO();
		CategoryVO cate3 = null, cate4 = null;

		for( TenderVO tenderVO : tenderVOList  ) {

			c.getWhere().putNumber("categoryNo", tenderVO.getCategoryNo3());
			cate3 = categoryDao.view(c);
			c.getWhere().putNumber("categoryNo", tenderVO.getCategoryNo4());
			cate4 = categoryDao.view(c);

			if( cate3 != null ) tenderVO.setCategoryNo3Name(cate3.getCategoryName());
			if( cate4 != null ) tenderVO.setCategoryNo4Name(cate4.getCategoryName());
		}

		makeMask(tenderVOList, memberVO);
		int total = tenderDao.total(pageVO);
		ListVo<TenderVO> list = new ListVo<TenderVO>( total, tenderVOList );
		return list;
	}

	public TenderVO getTender(PageVO pageVO, MemberVO memberVO) {

		TenderVO tenderVO = tenderDao.selectTender(pageVO);


		CategoryVO c = new CategoryVO();
		CategoryVO cate3 = null, cate4 = null;

		c.getWhere().putNumber("categoryNo", tenderVO.getCategoryNo3());
		cate3 = categoryDao.view(c);

		c.getWhere().putNumber("categoryNo", tenderVO.getCategoryNo4());
		cate4 = categoryDao.view(c);

		if( cate3 != null ) tenderVO.setCategoryNo3Name(cate3.getCategoryName());
		if( cate4 != null ) tenderVO.setCategoryNo4Name(cate4.getCategoryName());

		// 지역 가져오기
		PageVO p = new PageVO();
		p.getWhere().putNumber("areaCode", tenderVO.getAreaCode());
		SmallAreaVO sarea = smallAreaDao.viewAreaCode(p);

		p.getWhere().putNumber("bigAreaNo", sarea.getBigAreaNo());
		BigAreaVO barea = bigAreaDao.view(p);

		tenderVO.setSmallAreaName(sarea.getSmallAreaName());
		tenderVO.setBigAreaName(barea.getBigAreaName());

		return tenderVO;
	}

	public List<TenderVO> getList(PageVO pageVO) {

		List<TenderVO> tenderVOList = tenderDao.list(pageVO);

		CategoryVO c = new CategoryVO();
		CategoryVO cate3 = null, cate4 = null;

		for( TenderVO tenderVO : tenderVOList  ) {

			c.getWhere().putNumber("categoryNo", tenderVO.getCategoryNo3());
			cate3 = categoryDao.view(c);
			c.getWhere().putNumber("categoryNo", tenderVO.getCategoryNo4());
			cate4 = categoryDao.view(c);

			if( cate3 != null ) tenderVO.setCategoryNo3Name(cate3.getCategoryName());
			if( cate4 != null ) tenderVO.setCategoryNo4Name(cate4.getCategoryName());
		}

		return tenderVOList;
	}

	public int getTotal(PageVO pageVO) {
		return tenderDao.total(pageVO);
	}

	public Result make(TenderVO tenderVO) {
		Result result = new Result();
		if("".equals(tenderVO.getTitle()) || null == tenderVO.getTitle()) {
			result.setStatus("fail");
			result.setReason("구입희망은 필수입력입니다.");
			return result;
		}
		/*
		if("".equals(tenderVO.getWantDate()) || null == tenderVO.getWantDate()) {
			result.setStatus("fail");
			result.setReason("구입시기는 필수입력입니다.");
			return result;
		}*/
		if(tenderVO.getAreaCode() == 0) {
			result.setStatus("fail");
			result.setReason("지역은 필수입력입니다.");
			return result;
		}
		if("".equals(tenderVO.getContent()) || null == tenderVO.getContent()) {
			result.setStatus("fail");
			result.setReason("특이사항은 필수입력입니다.");
			return result;
		}

		tenderDao.insert(tenderVO);
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("tenderNo", tenderVO.getTenderNo());
		result.setData(data);

		return result;
	}

	public Result myPageTender(MemberVO memberVO, String status) {
		Result result = new Result();
		PageVO pageVO = new PageVO();
		JsonObject jo = new JsonObject();
		jo.putNumber("memberNo", memberVO.getMemberNo());
		jo.putString("status", status);

		// 자동차
		jo.putNumber("categoryNo", Define.Common.CATEGORY_CAR);
		pageVO.setWhere(jo);
		List<TenderVO> list = getList(pageVO);

		// 인테리어
		jo.putNumber("categoryNo", Define.Common.CATEGORY_INTERIOR);
		pageVO.setWhere(jo);
		List<TenderVO> list2 = getList(pageVO);

		Map<String, List<TenderVO>> resultMap = new LinkedHashMap<String, List<TenderVO>>();
		resultMap.put("car", list);
		resultMap.put("interior", list2);
		result.setStatus("ok");
		result.setData(resultMap);
		return result;
	}

	/**
	 * 견적 상세 보기
	 * @param tenderVO
	 * @return
	 */
	public TenderVO getView(TenderVO tenderVO) {
		return tenderDao.selectTender(tenderVO);
	}

	/**
	 * 견적별 입찰 리스트
	 * @param tenderVO
	 * @return
	 */
	public List<TenderReplyVO> getViewReply(TenderVO tenderVO) {
		return tenderDao.selectTenderReplyList(tenderVO);
	}

	/**
	 * 딜러용 상태별 입찰 리스트
	 * @param pageVO
	 * @param memberVO
	 * @return
	 */
	public ListVo<TenderVO> getDealerMyTenderList(PageVO pageVO, MemberVO memberVO) {

		pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
		List<TenderVO> tenderVOList = tenderDao.selectDealerMyTendingList(pageVO);

		makeMask(tenderVOList, memberVO);
		int total = tenderDao.selectDealerMyTendingTotal(pageVO);
		ListVo<TenderVO> list = new ListVo<TenderVO>( total, tenderVOList );
		return list;

	}
	/**
	 * 유저용 상태별 입찰 리스트
	 * @param pageVO
	 * @param memberVO
	 * @return
	 */
	public ListVo<TenderVO> getMyTenderList(PageVO pageVO, MemberVO memberVO) {

		pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());

		List<TenderVO> tenderVOList = tenderDao.selectMyTendingList(pageVO);

		makeMask(tenderVOList, memberVO);
		int total = tenderDao.selectMyTendingTotal(pageVO);
		ListVo<TenderVO> list = new ListVo<TenderVO>( total, tenderVOList );
		return list;

	}

	/**
	 * 유저용 상태별 입찰 리스트
	 * @param pageVO
	 * @param memberVO
	 * @return
	 */
	public int getMyTenderTotal(PageVO pageVO, MemberVO memberVO) {

		pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
		int total = tenderDao.selectMyTendingTotal(pageVO);
		return total;

	}


	/**
	 * 유저용 상태별 입찰 리스트
	 * @param pageVO
	 * @param memberVO
	 * @return
	 */
	public ListVo<TenderReplyVO> getMyTenderReplyList(PageVO pageVO, MemberVO memberVO) {

		pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());

		List<TenderReplyVO> tenderVOList = tenderDao.selectMyTendingReplyList(pageVO);

		makeMask2(tenderVOList, memberVO);
		ListVo<TenderReplyVO> list = new ListVo<TenderReplyVO>( 0, tenderVOList );
		return list;

	}

	private void makeMask( List<TenderVO> TenderVOList, MemberVO memberVO ) {

		if(  memberVO != null ) {

			boolean isAdmin = memberVO.isAdmin();
			for( TenderVO tenderVO : TenderVOList ) {
				// TODO 혹은 관리자 거나
				if( !isAdmin &&  tenderVO.getMemberNo() != memberVO.getMemberNo() ) {
					tenderVO.setId( makeMask(tenderVO.getId()) );
				}
			}

		} else {

			for( TenderVO tenderVO : TenderVOList ) {
				tenderVO.setId( makeMask(tenderVO.getId()) );
			}
		}
	}

	private void makeMask2( List<TenderReplyVO> TenderVOList, MemberVO memberVO ) {

		boolean isAdmin = memberVO.isAdmin();
		for( TenderReplyVO tenderVO : TenderVOList ) {
			// TODO 혹은 관리자 거나
			if( !isAdmin &&  tenderVO.getMemberNo() != memberVO.getMemberNo() ) {
				tenderVO.setId( makeMask(tenderVO.getId()) );
			}
		}
	}

	private String makeMask( String data ) {
		if( data.length() > 3 ) return "***"+data.substring(3);
		else return "***";
	}



}
