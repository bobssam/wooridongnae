package com.suba.admin.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suba.admin.dao.AdminTenderDao;
import com.suba.common.vo.PageVO;
import com.suba.common.vo.Result;
import com.suba.util.ExcelTemplate;
import com.suba.vo.ListVo;
import com.suba.vo.TenderVO;

@Controller
@RequestMapping(value="/admin/api/tender")
public class AdminTenderApiController {

	@Autowired
	private AdminTenderDao tenderDao;
	@Autowired
	private ExcelTemplate excelTemplate;

	private SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

	@RequestMapping(value="/list")
	@ResponseBody
	public ListVo<TenderVO> selectTenderList( PageVO pageVO ) {

		List<TenderVO> TenderVOList = tenderDao.list(pageVO);
		int total = tenderDao.total(pageVO);
		ListVo<TenderVO> list = new ListVo<TenderVO>( total, TenderVOList );
		return list;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	public Result addTender( TenderVO tenderVO ) {

		int row = tenderDao.insert(tenderVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public Result updateTender( TenderVO tenderVO ) {

		int row = tenderDao.modifyTender(tenderVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Result deleteTender( TenderVO tenderVO ) {

		int row = tenderDao.delete(tenderVO);
		Result rs = new Result("ok", row);
		return rs;
	}

	@ResponseBody
	@RequestMapping(value="/excel")
	public List<Map<String, Object>> testMethod( PageVO pageVO , HttpServletResponse response ) {


		List TenderVOList = tenderDao.selectTenderExcel(pageVO);

		String productName= "입찰리스트";
		try {
			productName= URLEncoder.encode(productName, "utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "Attachment;Filename=\""+ productName+"_"+sdf.format(new Date())+".xlsx\"");

		try {
			excelTemplate.makeExcelFile( "tender", TenderVOList, response.getOutputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
