package com.suba.user.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.TenderDao;
import com.suba.user.dao.TenderJoinDao;
import com.suba.user.dao.TenderReplyDao;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;
import com.suba.vo.TenderJoinVO;
import com.suba.vo.TenderReplyVO;
import com.suba.vo.TenderVO;

@Service
public class TenderReplyService {

	@Autowired
	private TenderDao tenderDao;
	@Autowired
	private TenderReplyDao tenderReplyDao;
	@Autowired
	private TenderJoinDao tenderJoinDao;
	@Autowired
	private MemberService memberService;
	
	private Result noPointResult = new Result("fail", "포인트가 부족합니다.");
	private Result failToTenderResult = new Result("fail", "입찰에 실패 하였습니다.");
	
	public Result insertTenderReply(TenderReplyVO tenderReplyVO, MemberVO memberVO) {
		
		// TODO 포인트 차감
		if( !memberService.minersPoint(memberVO) ) {
			return noPointResult;
		}
		tenderReplyVO.setMemberNo(memberVO.getMemberNo());
		
		int row = tenderReplyDao.insert(tenderReplyVO);
		if( row > 0 ) {
			
			TenderJoinVO tenderJoinVO = new TenderJoinVO();
			tenderJoinVO.setMemberNo(tenderReplyVO.getMemberNo());
			tenderJoinVO.setTenderNo(tenderReplyVO.getTenderNo());
			tenderJoinDao.insert(tenderJoinVO);
			
			TenderVO tender = new TenderVO();
			tender.getWhere().putNumber("tenderNo", tenderJoinVO.getTenderNo());
			tenderDao.plusHit(tender);
			// 입찰글의 정보를 가져옵니다.
			PageVO pageVO = new PageVO();
			pageVO.getWhere().putNumber("tenderNo", tenderReplyVO.getTenderNo());
			
			TenderVO tenderVO = tenderDao.selectTender(pageVO);
			memberService.sendPush(tenderVO.getMemberNo(), "당신의 견적서에 신규 입찰이 있습니다. 확인해보세요");
			

			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("tenderNo", tenderReplyVO.getTenderNo());
			data.put("tenderSeq", tenderReplyVO.getTenderSeq());
			
			Result rs = new Result("ok", row);
			rs.setData(data);
			return rs;
			
		} 
		return failToTenderResult;
	}

	public ListVo<TenderReplyVO> selectReplyList(PageVO pageVO, MemberVO memberVO) {
		
		List<TenderReplyVO> TenderReplyVOList = tenderReplyDao.list(pageVO);
		int total = tenderReplyDao.total(pageVO);
		ListVo<TenderReplyVO> list = new ListVo<TenderReplyVO>( total, TenderReplyVOList );
		
		makeMask(TenderReplyVOList, memberVO);
		
		if( memberVO != null ) {
			pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
			int myTotal = tenderReplyDao.total(pageVO);
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("myTotal", myTotal);
			list.setData(data);
		} 
		
		return list;
	}
	
	
	private void makeMask( List<TenderReplyVO> TenderReplyVOList, MemberVO memberVO ) {
		
		if( memberVO != null ) {
			
			boolean isAdmin = memberVO.isAdmin();
			for( TenderReplyVO tenderReplyVO : TenderReplyVOList ) {
				// TODO 혹은 관리자 거나
				if( !isAdmin &&  tenderReplyVO.getMemberNo() != memberVO.getMemberNo() ) {
					tenderReplyVO.setId( makeMask(tenderReplyVO.getId()) );
				}
			}
		} else {
			for( TenderReplyVO tenderReplyVO : TenderReplyVOList ) 
				tenderReplyVO.setId( makeMask(tenderReplyVO.getId()) );
		}
	}
	private String makeMask( String data ) {
		if( data.length() > 3 ) return "***"+data.substring(3);
		else return "***";
	}
}
