package com.suba.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vertx.java.core.json.JsonObject;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.LoginHistoryDao;
import com.suba.user.dao.SmallAreaDao;
import com.suba.user.dao.TenderDao;
import com.suba.user.dao.UserDao;
import com.suba.vo.CategoryVO;
import com.suba.vo.LoginHistoryVO;
import com.suba.vo.MemberVO;
import com.suba.vo.SmallAreaVO;
import com.suba.vo.UserCategoryVO;

@Service
public class MemberService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginHistoryDao loginHistoryDao;
	@Autowired
	private SMSService smsService;
	@Autowired
	private TenderDao tenderDao;
	@Autowired
	private SmallAreaDao smallAreaDao;
	@Autowired
	private PushService pushService;

	public Result insertMember(MemberVO memberVO) {
		Result result = new Result();
		if("".equals(memberVO.getName()) || null == memberVO.getName()) {
			result.setStatus("fail");
			result.setReason("이름은 빈칸이어서는 안됩니다.");
			return result;
		}
		if("".equals(memberVO.getId()) || null == memberVO.getId()) {
			result.setStatus("fail");
			result.setReason("아이디 빈칸이어서는 안됩니다.");
			return result;
		}
		if("".equals(memberVO.getPw())|| null == memberVO.getPw()) {
			result.setStatus("fail");
			result.setReason("비밀번호는 빈칸이어서는 안됩니다.");
			return result;
		}
		if("".equals(memberVO.getPw2())|| null == memberVO.getPw2()) {
			result.setStatus("fail");
			result.setReason("비밀번호확인은 빈칸이어서는 안됩니다.");
			return result;
		}
		if(!memberVO.getPw().equals(memberVO.getPw2())) {
			result.setStatus("fail");
			result.setReason("비밀번호와 확인이 맞지 않습니다.");
			return result;
		}
		userDao.insert(memberVO);
		return result;
	}

	public Result doLogin(MemberVO memberVO, HttpSession httpSession, HttpServletRequest request) {
		Result result = new Result();
		MemberVO userInfo = userDao.login(memberVO);
		if( userInfo == null ) {
			result.setStatus("fail");
			result.setReason("회원정보가 없습니다.");
			return result;
		} else {
			PageVO pageVO = new PageVO();
			pageVO.getWhere().putNumber("memberNo", userInfo.getMemberNo());
			// 상세 정보를 위해서 유저 정보를 가져옴
			userInfo = userDao.view(pageVO);
			httpSession.setAttribute("memberVO", userInfo);
			LoginHistoryVO loginHistoryVO = new LoginHistoryVO();

			// 딜러 회원은 3차 카테고리를 가져옴
			if( userInfo.getType() == MemberVO.TYPE_DEALER || userInfo.getType() == MemberVO.TYPE_ADMIN )  {
				List<CategoryVO> cateList = userDao.listCategory3(pageVO);
				String cateNames[] = new String[cateList.size()];
				int cateNos[] = new int[cateList.size()];

				int i=0;
				for( CategoryVO cateVo : cateList ) {
					cateNames[i] = cateVo.getCategoryName();
					cateNos[i] = cateVo.getCategoryNo();
					i++;
				}
				userInfo.setCategoryNo3(cateNos);
				userInfo.setCategoryNo3Name(cateNames);

			}




			String clientIp = request.getHeader( "HTTP_X_FORWARDED_FOR" );
			if ( null == clientIp || clientIp.length() == 0 || clientIp.toLowerCase().equals( "unknown" ) ) {
				clientIp = request.getHeader( "REMOTE_ADDR" );
			}
			if ( null == clientIp || clientIp.length() == 0 || clientIp.toLowerCase().equals( "unknown" ) ) {
				clientIp = request.getRemoteAddr();
			}
			loginHistoryVO.setMemberNo( userInfo.getMemberNo() );
			loginHistoryVO.setIp( clientIp );
			loginHistoryDao.insert( loginHistoryVO );
			userDao.updateLoginDate(userInfo);
			result.setData(userInfo.getMemberNo());
			return result;
		}
	}

	public Result duplicateCheckId(MemberVO memberVO) {
		Result result = new Result();
		if(userDao.duplicate(memberVO) > 0) {
			result.setStatus("fail");
			result.setReason("동일한 아이디가 있습니다.");
			return result;
		} else {
			return result;
		}
	}

	public Result doLogout(HttpSession httpSession, HttpServletRequest request) {
		Result result = new Result();
		httpSession.removeAttribute("memberVO");

		return result;
	}

	public Result findId(MemberVO memberVO) {

		// 유저의 정보 확인
		String userId = userDao.findUserId(memberVO);
		if( userId == null ) {
			return new Result("fail", "일치하는 계정정보가 없습니다.");
		}

		Result result = new Result();
		result.setData(userId);
		return result;
	}

	/**
	 * 비밀번호 찾기
	 * 성공시 새로운 비밀번호를 전송해줘야한다.
	 *
	 * @param memberVO
	 * @return
	 */
	public Result findPassword(MemberVO memberVO) {


		// 유저의 정보 확인
		int userNo = 0;
		if( (userNo = userDao.findUser(memberVO)) <= 0 ) {
			return new Result("fail", "일치하는 계정정보가 없습니다.");
		}
		memberVO.setMemberNo(userNo);

		// 임시 비밀번호 발행후 전송함
		String newPassword = makeRandomPassword();
		memberVO.setPw(newPassword);
		if( userDao.updatePassword(memberVO) > 0 ) {
			smsService.sendMessage(memberVO.getPhoneNo(), "고객님의 새로운 비밀번호는 ["+newPassword+"] 입니다");
			return new Result();
		}
		return new Result("fail", "비밀번호 갱신 실패");

	}

	String randomChars = "1234567890qwertyuiopasdfghjklzxcvbnm@#$";
	/**
	 * 임시 비밀번호 발행기
	 * 랜덤 문자를 섞어서 만듬
	 * @return
	 */
	private String makeRandomPassword() {
		String newPassword = "";
		for( int i=0; i<10; i++ ) {
			newPassword += randomChars.charAt( (int)(Math.random()*randomChars.length()) );
		}
		return newPassword;
	}

	public MemberVO getMember(MemberVO memberVO) {
		JsonObject json = new JsonObject();
		json.putNumber("memberNo", memberVO.getMemberNo());

		PageVO pageVO = new PageVO();
		pageVO.setWhere(json);
		return userDao.view(pageVO);
	}

	public Result modifyMember(HttpSession session, String prev_pw, MemberVO memberVO, String areaCode[]) {

		// 기존 비번 확인
		// 유저의 정보 확인
		MemberVO dbMemberVO = null;

		String pw = memberVO.getPw();
		if( pw != null && !memberVO.getPw().equals(memberVO.getPw2())) {
			return new Result("fail", "비밀번호와 확인이 맞지 않습니다.");
		}
		memberVO.setPw(prev_pw);
		if( (dbMemberVO = userDao.login(memberVO)) == null ) {
			return new Result("fail", "비밀번호가 틀렸습니다.");
		}

		memberVO.setPw(pw);
		JsonObject json = new JsonObject();
		json.putNumber("memberNo", dbMemberVO.getMemberNo());
		memberVO.setWhere(json);

		// 딜러 회원은 3차 카테고리를 가져옴
		if( memberVO.getType() == MemberVO.TYPE_DEALER || memberVO.getType() == MemberVO.TYPE_ADMIN )  {
			modifyMemberArea(session, memberVO, areaCode);
		}


		userDao.modifyUserInfo(memberVO);


		PageVO pageVO = new PageVO();
		pageVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());

		// 수정이후 최신 정보 동기화
		MemberVO newMemberVO = userDao.view(pageVO);


		// 딜러 회원은 3차 카테고리를 가져옴
		if( newMemberVO.getType() == MemberVO.TYPE_DEALER || newMemberVO.getType() == MemberVO.TYPE_ADMIN )  {
			List<CategoryVO> cateList = userDao.listCategory3(pageVO);
			String cateNames[] = new String[cateList.size()];
			int cateNos[] = new int[cateList.size()];

			int i=0;
			for( CategoryVO cateVo : cateList ) {
				cateNames[i] = cateVo.getCategoryName();
				cateNos[i] = cateVo.getCategoryNo();
				i++;
			}
			newMemberVO.setCategoryNo3(cateNos);
			newMemberVO.setCategoryNo3Name(cateNames);
		}

		session.setAttribute("memberVO", newMemberVO);

		return new Result();
	}


	public HashMap<String, Integer> selectDealerTenderStats( MemberVO memberVO ) {

		HashMap<String, Integer> stats = tenderDao.selectMyTenderStats(memberVO);
		return stats;
	}

	public HashMap<String, Long> selectMyDealerTenderStats( MemberVO memberVO ) {

		List<HashMap<String, Object>> stats = tenderDao.dealerMyTendingSummary(memberVO);
		HashMap<String, Long> map = new HashMap<String, Long>();
		for( HashMap<String, Object> data : stats ) {

			map.put( (String)data.get("status"), (Long)data.get("cnt") );
		}

		return map;
	}



	public HashMap<String, Integer> selectTenderStats( MemberVO memberVO ) {

		HashMap<String, Integer> stats = tenderDao.selectMyTenderStats(memberVO);
		return stats;
	}

	public HashMap<String, Long> selectMyTenderStats( MemberVO memberVO ) {

		List<HashMap<String, Object>> stats = tenderDao.myTendingSummary(memberVO);
		HashMap<String, Long> map = new HashMap<String, Long>();
		for( HashMap<String, Object> data : stats ) {

			map.put( (String)data.get("status"), (Long)data.get("cnt") );
		}

		return map;
	}


	public String updateRegId(String regId, String memberNo) {
		MemberVO memberVO = new MemberVO();
		if (regId != null && memberNo != "") {
			memberVO.setMemberNo(Integer.parseInt(memberNo));
			memberVO.setAndroidToken(regId);
			userDao.updateRegId(memberVO);
		}
		return "";
	}

	public List<SmallAreaVO> getWantArea(String wantArea) {
		if( wantArea == null ) return null;
		String areas[] = wantArea.split(",");
		PageVO pageVO = new PageVO();
		ArrayList<SmallAreaVO> list = new ArrayList<SmallAreaVO>();

		for( String area : areas ) {
			int areaCode = Integer.parseInt(area);

			pageVO.getWhere().putNumber("bigAreaNo", areaCode/10000);
			pageVO.getWhere().putNumber("smallAreaNo", areaCode%10000 );
			list.add( smallAreaDao.view(pageVO) );
		}

		return list;
	}

	public boolean minersPoint(MemberVO memberVO) {
		// TODO 지금은 구현하지 않습니다.
		return true;
	}

	public void sendPush(int memberNo, String message) {

		PageVO pageVO = new PageVO();
		pageVO.getWhere().putNumber("memberNo", memberNo);

		// 견적 쓴사람에게 푸시
		MemberVO memberVO = userDao.view(pageVO);
		if( memberVO != null ) {
			if( memberVO.agreePush() ) {
				pushService.sendMessage(memberVO, message);
			}
		}

	}

	/**
	 * 유저의 지역 정보를 갱신한다.
	 * @param memberVO
	 * @param areaCode
	 * @return
	 */
	public Result modifyMemberArea(HttpSession session, MemberVO memberVO, String[] areaCode) {

		String wantArea = "";
		for( String area : areaCode ) {
			if( !"".equals(wantArea) ) wantArea += ",";
			wantArea += area;
		}

		// 기존 비번 확인
		memberVO.getWhere().putNumber("memberNo", memberVO.getMemberNo());
		memberVO.setWantArea( wantArea );

		userDao.modifyUserArea(memberVO);
		userDao.clearCategory3(memberVO);

		int[] categorys = memberVO.getCategoryNo3();
		for( int cate : categorys ) {

			UserCategoryVO userCategoryVO = new UserCategoryVO();
			userCategoryVO.setMemberNo(memberVO.getMemberNo());
			userCategoryVO.setCategoryNo(cate);
			userDao.insertCategory3(userCategoryVO);
		}


		MemberVO member = (MemberVO) session.getAttribute("memberVO");
		member.setCategoryNo1(memberVO.getCategoryNo1());
		member.setCategoryNo2(memberVO.getCategoryNo2());
		member.setCategoryNo3(memberVO.getCategoryNo3());
		member.setWantArea(wantArea);

		return new Result();
	}


}
