package com.suba.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.user.dao.CropDao;
import com.suba.user.dao.UserDao;
import com.suba.user.menuController.MenuController;
import com.suba.vo.CategoryVO;
import com.suba.vo.CropVO;
import com.suba.vo.ListVo;
import com.suba.vo.MemberVO;

@Controller
@RequestMapping(value="/api/crop")
public class CropApiController extends MenuController {

	@Autowired
	private CropDao cropDao;
	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<CropVO> list( PageVO pageVO ) {

		List<CropVO> cropVOList = cropDao.list(pageVO);
		int total = cropDao.total(pageVO);
		ListVo<CropVO> list = new ListVo<CropVO>( total, cropVOList );

		for( CropVO cropVO : cropVOList ) {
			PageVO pVo = new PageVO();
			pVo.getWhere().putNumber("memberNo", cropVO.getMemberNo());
			List<CategoryVO> cateList = userDao.listCategory3(pVo);
			String cateNames[] = new String[cateList.size()];
			int cateNos[] = new int[cateList.size()];

			int i=0;
			for( CategoryVO cateVo : cateList ) {
				cateNames[i] = cateVo.getCategoryName();
				cateNos[i] = cateVo.getCategoryNo();
				i++;
			}

			cropVO.setCategoryNo3Name(cateNames);
			cropVO.setCategoryNo3(cateNos);
		}

		return list;
	}

	@RequestMapping(value="/view")
	@ResponseBody
	public Result view( CropVO _cropVO, HttpSession session ) {

		CropVO cropVO = cropDao.view(_cropVO);
		if( cropVO == null ) return new Result("fail", "없는 협력업체 번호");

		PageVO pVo = new PageVO();
		pVo.getWhere().putNumber("memberNo", cropVO.getMemberNo());
		List<CategoryVO> cateList = userDao.listCategory3(pVo);
		String cateNames[] = new String[cateList.size()];
		int cateNos[] = new int[cateList.size()];

		int i=0;
		for( CategoryVO cateVo : cateList ) {
			cateNames[i] = cateVo.getCategoryName();
			cateNos[i] = cateVo.getCategoryNo();
			i++;
		}

		// 본인 글이거나, 관리자 일때 수정가능
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if( memberVO != null ) {
			if( memberVO.getMemberNo() == cropVO.getMemberNo() ) cropVO.setIsMine(1);
		}
		if( isAdmin(session) ) {
			cropVO.setIsMine(1);
		}


		cropVO.setCategoryNo3Name(cateNames);
		cropVO.setCategoryNo3(cateNos);

		return new Result("ok", cropVO);

	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result add( CropVO cropVO ) {

		int row = cropDao.insert(cropVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result modify( CropVO cropVO ) {

		int row = cropDao.modify(cropVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete( CropVO cropVO ) {

		int row = cropDao.delete(cropVO);
		Result rs = new Result("ok", row);
		return rs;
	}
}
